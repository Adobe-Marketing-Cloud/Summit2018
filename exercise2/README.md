# 2. basic caching
* Adjusting filters for the we-retail site
* Basic caching of your site
* Activating from author -> publish -> dispatcher
* Removing cache manually
* optional: configuring ignoreUrlParams

estimated time: 20 min

## Exercise 1

Browse through your website and check for any errors in the dispatcher.log file.

Example of error:

Filter rejects: GET /libs/granite/csrf/token.json HTTP/1.1

Also look for errors in your browser console and spot differences between the site
running on publish (http://localhost:4503/content/we-retail/us/en.html) and on the dispatcher.

Add rules for the errors you find to make the site runs smoothly.

As a help here is a list of filters that we added in the dispatcher.any:

`/0062 { /type "allow" /url "/libs/cq/personalization/*"  }  # enable personalization`  
`/0063 { /type "allow" /url "/libs/granite/csrf/token.json"  } # enable CSRF`  
`/0064 { /type "allow" /url "/libs/granite/security/currentuser.json"  } # enable current user info`  

`/0066 { /type "allow" /url "/home/users/*.social.json"  }  # enable user-info`

## Exercise 2

1. Go to your author instance (http://localhost:4502/sites.html/content/we-retail/us/en)
2. publish a page
3. check the dispatcher.log (in $DISP_LOGS) file what happens.

You should see messages like this

checking [/content/we-retail/us/en/dispatcher/invalidate.cache.html]  
Activation detected: action=Activate [/content/we-retail/us/en/men]  
Touched /Library/WebServer/docroot/publish/.stat  
Evicted /Library/WebServer/docroot/publish/content/we-retail/us/en/men.html  
Evicted /Library/WebServer/docroot/publish/content/we-retail/us/en/men.header_include.html  


## Exercise 3

Open the dispatcher.any file (in $DISP_CONF) and search for the statfileslevel

Now check the effect when increasing the value from 0 to 5

After changing the value, restart the dispatcher and remove the cache (at least of "content")

level 0 -> /  
level 1 -> /content  
level 2 -> /content/we-retail  
level 3 -> /content/we-retail/us  
level 4 -> /content/we-retail/us/en  
level 5 -> /content/we-retail/us/en/products  

"cache file is older than lastflush" (content will be refreshed)  
"cache file is newer than lastflush" (content will be served from cache)

Once you configure the statefileslevel, you see the following entries in the logfile when publishing a page:

Touched /Library/WebServer/docroot/publish/.stat  
Touched /Library/WebServer/docroot/publish/content/.stat  
Touched /Library/WebServer/docroot/publish/content/we-retail/.stat  
Touched /Library/WebServer/docroot/publish/content/we-retail/us/.stat  
Touched /Library/WebServer/docroot/publish/content/we-retail/us/en/.stat  
