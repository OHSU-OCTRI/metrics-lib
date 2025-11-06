(function () {
  'use strict';

  window.addEventListener(
    'load',
    function () {
      if (!window.Interactor || !window.interactorConfig) {
        console.error(
          'Interactor class or config is not present. Analytics collection will not be installed.'
        );
        return;
      }
      const config = window.interactorConfig;
      let interactor = new window.Interactor({
        ...config,
        endpoint: `${config.contextPath}data/analytics_event`
      });
      // Video interactions
      interactor.addInteractionElement('video', [
        'play',
        'ended',
        'pause',
        'ratechange',
        'waiting',
        'seeking',
        'seeked'
      ]);
    },
    false
  );
})();
