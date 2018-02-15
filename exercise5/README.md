# 5. TTL / sling dynamic include

* using TTL (time-to-live) for content
* using SDI (sling-dynamic-include) for not caching a certain area of the page
* not caching personal info

estimated time: 15 min

## Exercise 1: Enabling basic TTL

Go to the dispatcher.any file and make sure that TTL is enabled

Once you have validated that, then go to the AEM-publish (http://localhost:4503/system/console/configMgr)

Create a new configuration for "ACS AEM Commons - Dispacher Cache Control Header - Max Age"

Add this for the pattern:  
`/content/we-retail/us/en/(.*)`  

Enter a number of seconds: for example 30

Test this functionality, by watching the dispatcher.log, but also the filesystem of the cache.
You'll notice that .ttl files are generated


You see log-messages like this:

cache file has expired: /Library/WebServer/docroot/publish/content/we-retail/us/en/men.html

cache file has not expired: /Library/WebServer/docroot/publish/content/we-retail/us/en/men.html


# Using SDI

In the vhost file add "Includes" to the Options

Options FollowSymLinks Includes

AddOutputFilter INCLUDES .html

In the dispatcher.any add the following rule to the cache-selection

## Exercise 2: Configuring SDI

Go to the configuration Manager (http://localhost:4503/system/console/configMgr), open the configuration for 'Apache Sling Dynamic Include - Configuration'

Configure it with the following options

Enabled -> true  
Base path -> /content  
Resource types -> weretail/components/content/productgrid  
Include type -> Apache SSI  

Save it, and remove the dispatcher cache

Open the products page (http://aem-publish.local/products)

Inspect the HTML-file on the dispatcher-cache. This now contains a statement to include the productgrid serverside.

<!--#include virtual="/content/we-retail/us/en/products/_jcr_content/root/product-grid-container/product-grid.nocache.html" -->

To test this: go to crx/de (http://localhost:4503/crx/de) on the publish side and change a price of a product (/etc/commerce/products/we-retail/me/coats).
In this case you only need to change the price, no need to activate.

## Exercise 3

In this exercise we are going to combine SDI + TTL.

Go to your SDI configuration and enter a number of seconds on the TTL field.

Now look at the logfiles to understand what extra change you need to make to make the working.

More info:

https://helpx.adobe.com/experience-manager/kt/platform-repository/using/sling-dynamic-include-technical-video-setup.html
