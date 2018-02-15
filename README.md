# L728 Introduction to the AEM dispatcher

Installation is done based on this article:
https://helpx.adobe.com/experience-manager/kt/platform-repository/using/dispatcher-macos-technical-video-setup.html

Dispatcher download:
https://www.adobeaemcloud.com/content/companies/public/adobe/dispatcher/dispatcher.html

Changes on AEM-publish:
* Enable the dispatcher-flush agent
* Modify the url to point to http://localhost/dispatcher/invalidate.cache
* Installing custom app on publish (l728.ui.apps-1.0-SNAPSHOT.zip)

Versions used:
* AEM 6.3.0 (author and publish)
* Dispatcher 4.2.3
* Apache 2.4.x

The following variables can be used in the terminal

* $DISP_LOGS (/private/var/log/apache2/)
* $DISP_CONF (/private/etc/apache2/conf/)
* $DISP_CACHE (/Library/WebServer/docroot/publish)
* $AEM_AUTHOR (~/Adobe/aem6.3_dispatcher_author/)
* $AEM_PUBLISH (~/Adobe/aem6.3_dispatcher_publish/)
* $APACHE_CONF (/private/etc/apache2/)
* $VHOST (/private/etc/apache2/vhosts/)

Outline on the lab

## [1. Getting the know the basics](exercise1)
* Stop / start / restart of the dispatcher
* configtest option of the dispatcher
* reading the logfiles

## [2. Basic caching](exercise2)
* Basic caching of your site
* Activating from author -> publish -> dispatcher
* Removing cache manually
* optional: configuring ignoreUrlParams

## [3. Performance](exercise3)
* enabling gzip
* enabling expiry headers
* enabling versioned clientlibs
* using chrome emulator to see the difference

## [4. Nice urls](exercise4)
* outgoing mappings
* Apache rewrite rules
* extension less urls

## [5. Sling dynamic include](exercise5)
* caching header/footer for all pages
* no caching of components with personalized info


## Troubleshooting
* If you removed the cache-folder:  
sudo mkdir -p /Library/WebServer/docroot/publish  
sudo chown -R _www:_www /Library/WebServer/docroot
