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
3. Search for `<!-- Lab 729 insert line here -->`, replace it with the following line:
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

## Hiding menu items (optional)

For hiding menu items you can use the sling:hideResource method from the Sling Resource Merger.

https://helpx.adobe.com/experience-manager/6-3/sites/developing/using/sling-resource-merger.html
