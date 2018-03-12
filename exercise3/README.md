# 3. performance
* enabling gzip
* enabling expiry headers
* enabling versioned clientlibs

estimated time: 10 min

First look in your browser window on file-sizes and content-encoding for css/js/html files.

## File compression

First enable mod_deflate in httpd.conf (in $APACHE_CONF)

To add gzip compression we go to the vhost file

sudo vi $VHOST/aem-publish.local.conf

Add the following line before the closing tag (`</VirtualHost>`):

AddOutputFilterByType DEFLATE text/html text/plain text/xml text/css text/javascript application/javascript

Test on this file: jquery-ui.css

before:
Content-Length:31204

after:
Content-Encoding:gzip  
Content-Length:5787

Extra doc: https://httpd.apache.org/docs/2.4/mod/mod_deflate.html

## Expiry headers

First enable mod_expires in httpd.conf (in $APACHE_CONF)

sudo vi $VHOST/aem-publish.local.conf

Add the following lines before the closing tag (`</VirtualHost>`):

ExpiresActive On
ExpiresByType text/css "access plus 1 month"

Test on this file: jquery-ui.css

Result:

Expires:Sat, 03 Feb 2018 15:13:52 GMT


http://httpd.apache.org/docs/current/mod/mod_expires.html

## Versioned clientlibs

Go to crx/de on your publish instance (http://localhost:4503/crx/de)

Go to the following node:

/apps/l308/config/rewriter/versioned-clientlibs

Add the following value in the property: transformerTypes

versioned-clientlibs

The result is that you have two values: linkchecker, versioned-clientlibs

Save the change and remove the dispatcher cache for "content":
- cd $DISP_CACHE
- sudo rm -rf content

When reloading the page and if you inspect the same css file you see that a unique selector is added:

jquery-ui.55e3b0ded8a1a513834780d77a48063a.css

This makes it possible for us the set a long expiry time on js / css files.

Extra doc: https://adobe-consulting-services.github.io/acs-aem-commons/features/versioned-clientlibs/index.html

Result:

We have now configured static design resources:
* files are compressed
* files can be cached in the browser
* js/css files have a unique selector to make them persistable in the browser

Extra: use the Google Clojure Compiler for minifying the clientlibs:
https://helpx.adobe.com/experience-manager/kb/how-to-change-the-minification-engine-for-client-libraries-in-AEM.html
