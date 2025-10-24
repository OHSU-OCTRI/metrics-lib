# OCTRI Metrics Library

This is the OCTRI library for recording browser activity. 

# TODO

1. This currently depends on the OCTRI authentication library to tie the activity to a user in the system, but we probably want to change that. If we can tie to a uuid instead like we have with other libraries, we might be able to support the OPEN use case where we want to track client activity and they are not users. This would actually work better for COMPASS too where only Participants need to be tracked. This might eliminate the next TODO

2. Add instructions to additions to TemplateAdvice. From STELLA-R:

```
	@ModelAttribute
	public void addModelAttributes(HttpServletRequest req, Model model) {
		SecurityHelper securityHelper = new SecurityHelper(SecurityContextHolder.getContext());
		boolean enableAnalyticsCollection = securityHelper.isLoggedIn() && !securityHelper.isAdminOrSuper();

		templateAdvice.addDefaultAttributes(req, model);
		model.addAttribute("enableAnalyticsCollection", enableAnalyticsCollection);
	}
```

3. Add instructions for modifying the header and footer of the application

4. Autoconfiguration to eliminate application component scanning (See Notification and Messaging libs)


