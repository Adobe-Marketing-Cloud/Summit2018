# Exercise 5

Customized styling for packages

Outline of this exercise:
- Adding custom icon to your package
- Custom description
- Dependencies
- Example healthcheck


## Customizing your package

1. Go the location: $CODE/ui.apps/src/main/content/META-INF/vault/definition/
2. In here you see the thumbnail.png file, that is displayed in the package manager
3. Replace the icon with your own icon (63x64 pixels)
4. Redeploy the application, and check in package manager

### adding extra description

1. Open the pom.xml of the ui.apps application ($CODE/ui.apps/pom.xml)
2. Search for the description element
3. The content of this element is displayed in the package manager

### other package name

When you want a more meaningful name of the packagename displayed

1. Open the pom.xml of the ui.apps application ($CODE/ui.apps/pom.xml)
2. Search for the artifactId element
3. The content of this element is displayed as the name in the package manager

### dependencies

When you want to have dependencies that your package relies on
1. Open the pom.xml of the ui.apps application ($CODE/ui.apps/pom.xml)
2. Go to the properties-section of the content-package-maven-plugin
3. Add the following value: `<dependencies>adobe/consulting:acs-aem-commons-content:3.13.0</dependencies>`
4. In this case this is a dependency for ACS commons

## Custom Healthcheck

Enabling your custom health-check that is visible from the author instance.  

First we validate the health-check via the OSGi-console:
1. Go to http://localhost:4502/system/console/healthcheck
2. Fill in Tags: hcexample
3. Execute the health check

Now we are going to make this healthcheck visible from the author instance.

1. Go to http://localhost:4502/libs/granite/operations/content/healthreports/healthreportlist.html
2. Here you see that the custom health-check is disabled
3. Go to crx/de http://localhost:4502/crx/de/index.jsp
4. Go to the node: /apps/settings/granite/operations/hc/healthcheck_example
5. Add the property resource, with the following value /system/sling/monitoring/mbeans/org/apache/sling/healthcheck/HealthCheck/hcexample
6. Save the values
7. Refresh the dashboard: http://localhost:4502/libs/granite/operations/content/healthreports/healthreportlist.html
8. Now you see your activated health-check
