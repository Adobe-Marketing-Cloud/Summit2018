Exercise 1 - Installing the Player

===========

## Objective
Install AEM Screens player


## Tasks

1.	Install from MacOS DMG file

![Image of ex1 outcome](../../Resources/Picture1.png) 

2.  Configuring AEM

**Note**
When starting AEM Screens player for the first time, you may get an error State.  In order for the Screens Player to function, a couple of AEM Author OSGI configurations must be made.
![AEM Screens Configuration](../../Resources/Picture2.png)


2.1  Open the Felix Console, find the Apache Sling Referrer filter --> http://localhost:4502/system/console/configMgr/org.apache.sling.security.impl.ReferrerFilter

Make sure that Allow Empty is checked
![Apache Sling Referrer filter](../../Resources/Picture3.png)


2.2  Open the Felix Console, find the Apache Sling Referrer filter -->  http://localhost:4502/system/console/configMgr/com.day.cq.dam.core.impl.servlet.DamContentDispositionFilter

Remove the line text/html
![Apache Dam Safe Binary Filter](../../Resources/Picture4.png)

3. Configuring the player for registration

**Note**
If the configuration page is not visible, you can access one of the hidden links in the player

(1)	Approximately 5x5 pixels off the top left, is a hidden link to open the Configuration Page
(2) Approximately 5x5 pixels off the bottom left, is a hidden link to open Channel switcher.
![Screens Player hidden menu](../../Resources/Picture5.png)

3.1 Open the Configuration Menu (if its not already open) 

3.2 Configure the Server URL:  Put http://localhost:4502
![Screens Configuration Menu](../../Resources/Picture6.png)

3.3 Click on the Device Registration link from the Configuration Menu.  Validate that the State says "Unregistered" & that there is a valid token
![Device Registration](../../Resources/Picture7.png)



