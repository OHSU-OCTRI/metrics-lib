# OCTRI Metrics Library

This package contains the infrastructure and resources needed to record browser activity for a User as defined by the [OCTRI Authentication Library](https://github.com/OHSU-OCTRI/authentication-lib). This includes:

* Domain objects for an AnalyticsEvent and its child Interactions
* A Controller/Repository for posting an AnalyticsEvent and Interactions and persisting these to the database
* A javascript definition for a configurable Interactor class that will add event listeners and submit AnalyticsEvents at a given interval (60 seconds by default)

There is currently no UI associated with the metrics, so interactions can only be viewed through database tables.

## Using this package

To use this package, add it to your `pom.xml` file.

```xml
	<dependency>
		<groupId>org.octri.metrics</groupId>
		<artifactId>metrics_lib</artifactId>
		<version>${metrics_lib.version}</version>
	</dependency>
```

### Flyway Migrations / Data Fixtures

To create the database tables used by the library, copy the SQL migrations from `setup/migrations/` into your project's Flyway migration directory (`src/main/resources/db/migration/`). You may need to rename the migrations so that they are applied after existing migrations.

### Modify the Application Template Advice

To work seamlessly with this library, your application should be using the OCTRI Authentication Library and ideally have been generated using the OCTRI Spring Boot archetype. This will set up some of the appropriate TemplateAdvice elements in the model. Your application should have a file called ApplicationTemplateAdvice. Here, you will want to determine the logic for when metrics should be included on a page. At a minimum, you should ensure the user is logged in. Typically we have only collected metrics on users that are not Admin or Super users. If metrics should be enabled, add the interactor scripts and properties to the page:

```java
SecurityHelper securityHelper = new SecurityHelper(SecurityContextHolder.getContext());
boolean enableAnalyticsCollection = securityHelper.isLoggedIn() && !securityHelper.isAdminOrSuper();
if (enableAnalyticsCollection) {
	model.addAttribute("interactorProperties", interactorProperties);
	ViewUtils.addPageScript(model.asMap(), "interactor.js");
	ViewUtils.addPageScript(model.asMap(), "install-interactor.js");
}
```

### Add configuration to the header

To use the Interactor in OCTRI applications, make sure the common header has the following script to inject the configuration properties into the Interactor. This script should only be added if there are interactorProperties:

```html
{{#interactorProperties}}
<script>
	window.interactorConfig = {
		contextPath: '{{req.contextPath}}/',
		appVersion: '{{appVersion}}',
		csrfHeader: '{{_csrf.headerName}}',
		csrfToken: '{{_csrf.token}}',
		interactionSelector: '{{interactionSelector}}',
		debug: {{debug}},
		logInterval: {{logInterval}}
	};
</script>
{{/interactorProperties}}
```

The above assumes that you have the model parameters appVersion and req.contextPath, which are created automatically if you use the Authentication Library and initiate your application with the Spring Boot Archetype.

## Future Direction

1. This currently depends on the OCTRI authentication library to tie the activity to a user in the system, but we probably want to change that. If we can tie to a uuid instead like we have with other libraries, we might be able to support the OPEN use case where we want to track client activity and they are not users. This would actually work better for COMPASS too where only Participants need to be tracked.

2. Admin UI for Metrics



