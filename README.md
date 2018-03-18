# Adobe Summit 2018 Hands-On Lab L731

In this lab you will learn how to use Adobe Experience Manager (AEM) as a headless CMS while reusing and exposing content to mobile applications. We will deep-dive into two major ways Adobe Experience Manager exposes data in a headless manner, allowing mobile apps to seamlessly consume content. We will also explore how Adobe Experience Manager Commerce Product data can easily be exposed to other systems in a headless fashion to create fluid experiences.

Key takeaways:

* Use Content Services to build and deliver headless content in Adobe Experience Manager
* Use Sling Model Exporter to augment Adobe Experience Manager content for applying custom business-logic overlays and deliver it in a headless fashion
* Reuse and consume the content above in a mobile app to create a mobile-first experience for your customers built on AngularJS, Ionic, and PhoneGap

## Works with

* Adobe Experience Manager - 6.4 GA


## How To Use

1. Install [Node.js](https://nodejs.org/) & check the version.

    ` npm -v `

1. Install [PhoneGap](https://phonegap.com/) & [Ionic](https://ionicframework.com/)

    ` sudo npm install -g phonegap ionic `

      Check Phonegap Version: ` phonegap -v `

      Check Ionic Version: ` ionic -v `

1. Start your AEM Author & Publish instances.

1. Clone this project

1. Open the _content-packages_ director in terminal.

1. Install the provided content to both Author & Publish instances:

    `mvn com.day.jcr.vault:content-package-maven-plugin:install -Dvault.file=./L731-Assets-Fragments-1.0.zip`

    ` mvn com.day.jcr.vault:content-package-maven-plugin:install -Dvault.file=./L731-Assets-Fragments-1.0.zip -Dvault.targetURL="http://localhost:4503/crx/packmgr/service.jsp" `

    ` mvn com.day.jcr.vault:content-package-maven-plugin:install -Dvault.file=./L731-Content-Services-1.0.zip `

    ` mvn com.day.jcr.vault:content-package-maven-plugin:install -Dvault.file=./L731-Content-Services-1.0.zip -Dvault.targetURL="http://localhost:4503/crx/packmgr/service.jsp" `

    ` mvn com.day.jcr.vault:content-package-maven-plugin:install -Dvault.file=./L731-Publish-1.0.0.zip `

    ` mvn com.day.jcr.vault:content-package-maven-plugin:install -Dvault.file=./L731-Publish-1.0.0.zip -Dvault.targetURL="http://localhost:4503/crx/packmgr/service.jsp" `

1. Open the _summit-L731-aem_ directory in terminal.

1. Install the provided code into AEM

    ` mvn clean install -PautoInstallPackage,autoInstallPackagePublish `

1. Preview Mobile App:

   ` sudo ionic serve --lab `
