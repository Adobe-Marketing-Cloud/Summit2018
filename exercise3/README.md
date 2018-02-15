# Exercise 3

Changing labels in AEM author interface.

Expected duration: 15 minutes

In this exercise we are going to do the following things:
- Changing "Adobe Experience Manager" label that is displayed top left
- Changing the Help -> About popup to display the version of your custom code
- Changing the name + link of one of the items in the help-menu
- Adding extra info on the assets card in the author interface
- Changing menus presented to the user

NOTE: The instructions below are done directly in CRX/DE.  
Of course you can do them also in your IDE, then deploy into AEM.

## Changing product-label (top left)

1. Login to crx/de (http://localhost:4502/crx/de/index.jsp)
2. Navigate to the following path: /apps/L729/i18n/en/aem
3. In the properties tab you see a property called sling:message, change this to a custom value (it was Adobe Experience Manager)
4. Save your changes and check on the author interface, this should now display the new value

## Changing version-box popup

1. Login to crx/de (http://localhost:4502/crx/de/index.jsp)
2. Navigate to /apps/granite/ui/components/shell/help/about/about.jsp
3. Between line 41 and 42, add the following line:
        `<p>Summit release: <%= version.getVersionInfo() %>	</p>`
4. Save your changes
5. Now look at the version pop-up (Help -> About screen), this now displays the version of the custom OSGi bundle.

## Changing name + link of the help-items

1. Login to crx/de (http://localhost:4502/crx/de/index.jsp)
2. Navigate to /apps/granite/ui/content/shell/help/items/customercare
3. Change the properties of href_i18n and text
4. Save your changes
5. Check on the Help items in the author interface to see your changed item.
6. If you want to change other items too, see the names in this location: /libs/granite/ui/content/shell/help/items

## Displaying more info on the assets-card

 1. Login to crx/de (http://localhost:4502/crx/de/index.jsp)
 2. Navigate to /apps/dam/gui/coral/components/admin/contentrenderer/card/asset/propertyList.jsp
 3. At line 115, add the following line:  
 `<coral-card-property title="versioninfo">Version: <%= versionlabel %></coral-card-property>`  
 4. Save the changes, and see the displayed version info on the Assets-card

## Display status info on the assets-card

1. Login to crx/de (http://localhost:4502/crx/de/index.jsp)
2. Navigate to /apps/dam/gui/coral/components/admin/contentrenderer/card/asset/asset.jsp
3. At line 53, add the following lines

    `<coral-card-info>`  
      `<coral-tag color="red" class="u-coral-pullLeft">Expired</coral-tag>`  
    `</coral-card-info>`

4. Save the changes, and see the Expired-status on the Assets-card
