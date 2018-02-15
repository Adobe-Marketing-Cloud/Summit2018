# Get to know the dispatcher

estimated time: 10 min

## Objectives:
Know the status of the dispatcher

With the following command you can check the status:

apachectl status

check the version of Apache:

httpd -V

Open two terminal windows

In one terminal-window do a tail on the dispatcher logfile:

tail -f $DISP_LOGS/dispatcher.log

In another one do a tail of the AEM-access.log

tail -f $AEM_PUBLISH/crx-quickstart/logs/access.log

Now stop / start / restart the dispatcher

* apachectl stop
* apachectl start
* apachectl restart

Via this url you can access the site via the dispatcher: http://aem-publish.local/content/we-retail/us/en.html

In the dispatcher logfile you can see the version, and which farms are used.

With the following command you can check whether the configs are valid

apachectl configtest

Make a change in the dispatcher.any that is wrong, and check this via the configtest.
