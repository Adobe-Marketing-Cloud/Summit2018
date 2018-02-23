# L737 - Building single-page apps with Experience Manager

Project for Summit 2018 Session L737: Building single-page apps with Experience Manager

Manage your single-page applications more easily than ever before. We'll walk through examples to examine the structure and integration of a single-page site. Explore how to add components and content. Take home a working integration with notes on the experiences of real-world integrations that are live in production today.

In this lab:

- Discover the integration of single-page application frameworks, such as Angular with Experience Manager
- Review the improved authoring features provided by new in-context editing support in Experience Manager 6.4
- Implement front-end and externally managed experiences that leverage the content service features of Experience Manager

This session is for an advanced audience familiar with Experience Manager development.

## About this project

This a content package project generated using the AEM Multimodule Lazybones template.

There are three samples which showcase very different integrations with AEM

- **Angular News** application runs independently and uses AEM as a headless content management provider with new content services features.  See README.md in that folder for more information.

- **Tic Tac Toe** application runs inside AEM as a component, showing the simple case where AEM is deploying the React app and passing basic configuration and localization.  This app is pre-created in the SPA Demos under Sites.

- **React News** application runs inside AEM as a page, and mirrors the Angular News application using the same content services integration.  To see this in action, add a new React News page and configure the API Url of the app component on that new page.

## Additional samples

The goal of this sample set was not to recommend one implementation style over another, but instead to inform a developer of various implementation approaches so that they can decide which one works for them.

There are some other supporting item in the project bundle that help make these samples possible, **please note** that none of these samples have been sufficiently performance tested and *are not* certified for production use, so any use is at your own risk:

- **JSX Compiler** adds much-needed JSX support to the AEM Client Library Manager, so developers can add JSX files to a client library and can expect it to generate an ES2015-compatible JS output without requiring pre-compilation.
- **Sling Models** are provided for the Tic Tac Toe app (a simple bean) and *SimpleList Component* which, despite the name, is very advanced and can generate JSON for any arbitrary folder of content fragments.
- **Synthetic Resource Helper** is used by the SimpleList model to generate synthetic (virtual) AEM ContentFragments in order to hot-wire the existing product models to generate JSON without having actual components in the JCR.
- **Fragment Json Exporter Servlet** demonstrates (albeit in a kind of hacky manner) what it might look like in the distant future as content services features of AEM continue to expand.

### Building

This project uses Maven for building. Common commands:

From the root directory, run ``mvn -PautoInstallPackage clean install`` to build the bundle and content package and install to a CQ instance.

From the bundle directory, run ``mvn -PautoInstallBundle clean install`` to build *just* the bundle and install to a CQ instance.

### Using with AEM Developer Tools for Eclipse

To use this project with the AEM Developer Tools for Eclipse, import the generated Maven projects via the Import:Maven:Existing Maven Projects wizard. Then enable the Content Package facet on the _content_ project by right-clicking on the project, then select Configure, then Convert to Content Package... In the resulting dialog, select _src/main/content_ as the Content Sync Root.

### Using with VLT

To use vlt with this project, first build and install the package to your local CQ instance as described above. Then cd to `content/src/main/content/jcr_root` and run

    vlt --credentials admin:admin checkout -f ../META-INF/vault/filter.xml --force http://localhost:4502/crx

Once the working copy is created, you can use the normal ``vlt up`` and ``vlt ci`` commands.

### Specifying CRX Host/Port

The CRX host and port can be specified on the command line with:
mvn -Dcrx.host=otherhost -Dcrx.port=4502 <goals>
