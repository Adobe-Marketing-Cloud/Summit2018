# 4. nice urls
* outgoing mappings
* Apache rewrite rules
* extension less urls

estimated time: 20 min

By default we have urls like:

/content/we-retail/us/en.html  
/content/we-retail/us/en/products/men/shirts/expedition-tech-long-sleeved-shirt.html  

/content/we-retail/us/es.html  
/content/we-retail/us/es/products/men/shirts/expedition-tech-long-sleeved-shirt.html  

We want to have the following:

/en.html  
/products/men/shirts/expedition-tech-long-sleeved-shirt.html  

/es.html  
/es/products/men/shirts/expedition-tech-long-sleeved-shirt.html  

so

/content/we-retail/us/en -> /  
/content/we-retail/us/es -> /es/  

## Exercise 1

Go to http://localhost:4503/system/console/configMgr and open the configuration for:
Apache Sling Resource Resolver Factory

In the configuration "URL mappings" add the following entries

/content/we-retail/us/en</  
/content/we-retail/us/es</es/  

Save the changes, and render a page on the publish-server.  
Now inspect page on publish and validate the links on the pages.
You should see now that the links are rewritten, but the pages are still accessible via the 'normal' content path.

## Exercise 2

Now in order to do the translation from short to long url we are going to add rewrite urls on Apache.

Open the following file:

$APACHE_CONF/httpd.conf and enable the rewrite module

LoadModule rewrite_module libexec/apache2/mod_rewrite.so

Now we are adding the rewrite rules

Open the following file:

$VHOST/aem-publish.local.conf

Add the following lines before the closing <VirtualHost> element

RewriteEngine On  
RewriteRule ^/$ /content/we-retail/us/en.html [PT,L]  

RewriteCond %{REQUEST_URI} !^/apps  
RewriteCond %{REQUEST_URI} !^/bin  
RewriteCond %{REQUEST_URI} !^/content  
RewriteCond %{REQUEST_URI} !^/etc  
RewriteCond %{REQUEST_URI} !^/es  
RewriteCond %{REQUEST_URI} !^/home  
RewriteCond %{REQUEST_URI} !^/libs  
RewriteCond %{REQUEST_URI} !^/tmp  
RewriteCond %{REQUEST_URI} !^/var  
RewriteCond %{REQUEST_URI} !^/dispatcher  
RewriteRule ^/(.*)$ /content/we-retail/us/en/$1 [PT,L]  

RewriteCond %{REQUEST_URI} ^/es/  
RewriteRule ^/(.*)$ /content/we-retail/us/$1 [PT,L]  

More info on mod_rewrite: http://httpd.apache.org/docs/current/mod/mod_rewrite.html

## Exercise 3

For extension less urls go to this url on the publish:

http://localhost:4503/system/console/configMgr

Go to this configuration: Day CQ Link Checker Transformer

Enable the setting: "Strip HTML Extension"

And change the rewrite rule to also include the extension

RewriteRule ^/(.*)$ /content/we-retail/us/en/$1.html [PT,L]


Now test the change, now you have short urls without a .html extension
