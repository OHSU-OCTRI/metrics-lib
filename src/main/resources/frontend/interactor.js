/* Derived from Interactor.js: https://github.com/greenstick/interactor */
/*
BSD 2-Clause License

Copyright (c) 2016, Benjamin Cordier
All rights reserved.

Redistribution and use in source and binary forms, with or without
modification, are permitted provided that the following conditions are met:

* Redistributions of source code must retain the above copyright notice, this
  list of conditions and the following disclaimer.

* Redistributions in binary form must reproduce the above copyright notice,
  this list of conditions and the following disclaimer in the documentation
  and/or other materials provided with the distribution.

THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE
FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
*/

import 'core-js/stable';
import 'whatwg-fetch';

const videoEvents = [
  'play',
  'ended',
  'pause',
  'ratechange',
  'waiting',
  'seeking',
  'seeked'
];

/**
 * Content that should be displayed for the given event.
 *
 * @param {Event} evt
 */
function eventContents(evt) {
  if (videoEvents.includes(evt.type)) {
    const video = evt.target;

    // TODO: played intervals seems buggy
    // let totalPlayed = 0;
    // const played = video.played;
    // for (let i = 0; i < played.length; i++) {
    //   totalPlayed += played.end(i) - played.start(i);
    // }

    let videoData = {
      title: video.title,
      duration: video.duration,
      currentTime: video.currentTime
      // totalPlayed: totalPlayed
    };
    if (video.textTracks && video.textTracks.length > 0) {
      videoData.captions = video.textTracks[0].mode;
    }
    if (video.playbackRate) {
      videoData.playbackRate = video.playbackRate;
    }
    return JSON.stringify(videoData);
  }

  const target = evt.target;
  if (target.matches('img')) {
    return JSON.stringify({
      title: target.title,
      source: target.src
    });
  }
  let contents = target?.innerText || '';

  // If the target has no readable inner text, walk up the DOM until we find
  // an element with usable text (handles icons inside links/buttons, etc.).
  if (!contents.trim()) {
    let el = target;
    while (el && el !== document.body && el !== document.documentElement) {
      // Check innerText first
      const text = (el.innerText || '').trim();
      if (text) {
        contents = text;
        break;
      }

      // Check common accessible attributes that might contain a label
      const aria = el.getAttribute && el.getAttribute('aria-label');
      if (aria && aria.trim()) {
        contents = aria.trim();
        break;
      }
      if (el.title && el.title.trim()) {
        contents = el.title.trim();
        break;
      }
      const alt = el.getAttribute && el.getAttribute('alt');
      if (alt && alt.trim()) {
        contents = alt.trim();
        break;
      }
      // Move up to the parent and try again
      el = el.parentElement;
    }
  }

  // Return the first 255 characters of the resolved contents
  return contents.substring(0, 254) || '';
}

export default class Interactor {
  constructor(config) {
    this.init(config);
  }

  init(config) {
    // whether to record interactions with elements of interest
    this.interactions =
      typeof config.interactions === 'boolean' ? config.interactions : true;

    // CSS selector used to identify elements that trigger interactions
    let interactionElement =
      typeof config.interactionElement === 'string'
        ? config.interactionElement
        : 'interaction';

    // list of events that trigger an interaction to be recorded
    let interactionEvents =
      Array.isArray(config.interactionEvents) === true
        ? config.interactionEvents
        : ['mouseup', 'touchend'];

    // whether to record conversions - interactions that represent desirable actions
    this.conversions =
      typeof config.conversions === 'boolean' ? config.conversions : true;

    // CSS class used to identify elements that should trigger conversions
    this.conversionElement =
      typeof config.conversionElement === 'string'
        ? config.conversionElement
        : 'conversion';

    // list of events that trigger a conversion to be recorded
    this.conversionEvents =
      Array.isArray(config.conversionEvents) === true
        ? config.conversionEvents
        : ['mouseup', 'touchend'];

    // path of the server endpoint
    this.endpoint =
      typeof config.endpoint === 'string' ? config.endpoint : '/interactions';

    // enable debugging
    this.debug = typeof config.debug === 'boolean' ? config.debug : false;

    // name of the CSRF token header
    this.csrfHeader = typeof config.csrfHeader === 'string' ? config.csrfHeader : '';

    // CSRF token header value
    this.csrfToken = typeof config.csrfToken === 'string' ? config.csrfToken : '';

    // log interval; an event is recorded every `logInterval` ms
    this.logInterval =
      typeof config.logInterval === 'number' ? config.logInterval : 60000;

    // duration of the idle timer in ms
    this.idleTimerDuration =
      typeof config.idleTimerDuration === 'number' ? config.idleTimerDuration : 10000;

    // application version
    this.appVersion =
      typeof config.appVersion === 'string' ? config.appVersion : 'NOT SET';

    this.records = [];
    this.state = {};
    this.initializeState();
    this.bindEvents(interactionElement, interactionEvents);
  }

  // Create event handlers
  bindEvents(interactionElement, interactionEvents) {
    this.addInteractionElement(interactionElement, interactionEvents);

    // Capture conversions
    if (this.conversions === true) {
      document.querySelectorAll(this.conversionElement).forEach(element => {
        this.conversionEvents.forEach(evtType => {
          element.addEventListener(evtType, evt => {
            evt.stopPropagation();
            this.addInteraction(evt, 'CONVERSION');
          });
        });
      });
    }

    // Reset the idle timeout on any of the events listed below
    ['touchstart', 'mousemove', 'scroll'].forEach(evt => {
      document.addEventListener(
        evt,
        () => {
          this.updateIdleTimer('event');
        },
        false
      );
    });

    // Send data when the page changes
    let pageHideHandler = _evt => {
      this.sendData();
    };

    window.addEventListener('pagehide', pageHideHandler);

    // Ensure that data sends before logout
    let logoutLink = document.getElementById('logout_link');
    if (logoutLink) {
      logoutLink.addEventListener('click', evt => {
        // prevent navigation and disable the link
        evt.preventDefault();
        logoutLink.classList.add('pe-none');

        // uninstall the pagehide handler to prevent double submit
        window.removeEventListener('pagehide', pageHideHandler);

        // save data, then navigate
        this.sendData().finally(() => {
          this.initializeState();
          window.location.href = logoutLink.href;
        });
      });
    }
  }

  // Record interaction with a page element
  addInteraction(evt, type) {
    // Interaction Object
    let interaction = {
      type: type,
      event: evt.type,
      targetTag: evt.target.nodeName,
      targetClasses: evt.target.className,
      content: eventContents(evt),
      clientX: evt.clientX,
      clientY: evt.clientY,
      screenX: evt.screenX,
      screenY: evt.screenY,
      clientTimestamp: new Date()
    };

    this.records.push(interaction);

    // Log state if debugging
    if (this.debug) {
      this.finalizeState();
      // eslint-disable-next-line no-console
      console.info('State:\n', this.state);
    }
  }

  initializeState() {
    this.state = {
      loadTime: new Date(),
      unloadTime: new Date(),
      totalDurationSeconds: 0,
      activeSeconds: 0,
      language: window.navigator.language,
      platform: window.navigator.platform,
      port: window.location.port,
      startInnerWidth: window.innerWidth,
      startInnerHeight: window.innerHeight,
      startOuterWidth: window.outerWidth,
      startOuterHeight: window.outerHeight,
      pageLocation: window.location.pathname,
      referrer: document.referrer,
      userAgent: window.navigator.userAgent,
      appVersion: this.appVersion
    };

    this.records = [];
    this.firstEvent = null;
    this.latestEvent = null;
    this.idleTimer = null;
    this.activeMs = 0;
  }

  // Update state prior to sending data
  finalizeState() {
    this.state.unloadTime = new Date();
    this.state.interactions = this.records;
    this.state.endInnerWidth = window.innerWidth;
    this.state.endInnerHeight = window.innerHeight;
    this.state.endOuterWidth = window.outerWidth;
    this.state.endOuterHeight = window.outerHeight;
    this.state.totalDurationSeconds = Math.round(
      (this.state.unloadTime.valueOf() - this.state.loadTime.valueOf()) / 1000
    );
    this.state.activeSeconds = Math.round(this.activeMs / 1000);
  }

  sendData() {
    this.finalizeState();
    return fetch(this.endpoint, {
      method: 'POST',
      // allow requests to outlive the page
      keepalive: true,
      headers: {
        'Content-Type': 'application/json',
        [this.csrfHeader]: this.csrfToken
      },
      body: JSON.stringify(this.state)
    });
  }

  resetIdleTimer() {
    this.idleTimer = window.setTimeout(() => {
      this.updateIdleTimer('timeout');
    }, this.idleTimerDuration);
  }

  updateIdleTimer(cause) {
    const now = new Date();

    if (cause === 'timeout' && this.latestEvent) {
      // Give the user credit for activity up to the first timeout; successive idle time
      // will not be counted.
      this.activeMs += now.valueOf() - this.latestEvent.valueOf();
      this.latestEvent = null;
    }

    if (cause === 'event') {
      window.clearTimeout(this.idleTimer);
      if (this.firstEvent === null) {
        // First activity after startup or sending data. Don't count time spent idle
        // since state was initialized.
        this.state.loadTime = now;
        this.firstEvent = now;
        this.latestEvent = now;
      } else if (this.latestEvent === null) {
        // First activity since a timeout. Start counting activity again.
        this.latestEvent = now;
      } else {
        // Continued activity without a timeout. Add to the active time.
        this.activeMs += now.valueOf() - this.latestEvent.valueOf();
        this.latestEvent = now;
      }
    }

    if (now.valueOf() - this.state.loadTime.valueOf() >= this.logInterval) {
      // send the data and reset if the log interval is up
      this.sendData().finally(() => this.initializeState());
    } else {
      this.resetIdleTimer();
    }
  }

  // Add a new interaction element tied to different events
  addInteractionElement(interactionElement, interactionEvents) {
    // Capture additional interactions
    if (this.interactions === true) {
      document.querySelectorAll(interactionElement).forEach(element => {
        interactionEvents.forEach(evtType => {
          element.addEventListener(evtType, evt => {
            evt.stopPropagation();
            this.addInteraction(evt, 'INTERACTION');
          });
        });
      });
    }
  }
}
