# Exercise 4

In this exercise we will enabling the Adobe Marketingcloud icons.

Expected duration: 10 minutes

NOTE: The instructions below are done directly in CRX/DE.  
Of course you can do them also in your IDE, then deploy into AEM.

## Enabling Adobe Marketingcloud items

1. Login to crx/de (http://localhost:4502/crx/de/index.jsp)
2. Navigate to the following path: /apps/granite/ui/content/shell/solutionswitcher
3. There you see the icons displayed on the fly-out menu.
4. In the "primary"-folder you see the items of the solutions (like Analytics, Target etc)
5. In the "secondary"-folder you see the items of the core services (Activation, Assets etc)

You can change them like this:
- Enabling your icons in primary: add Color to the value icon property. (for example adobeCampaign to adobeCampaignColor)
- Changing the title: this is located in the text property
- Changing the url: this is located in the href_i18n property

## Displaying more info on the assets-card

 1. Login to crx/de (http://localhost:4502/crx/de/index.jsp)
 2. Navigate to /apps/dam/gui/coral/components/admin/contentrenderer/card/asset/propertyList.jsp
 3. Search for `<!-- Lab 729 insert line here -->`, replace it with the following line:
 `<coral-card-property title="versioninfo">Version: <%= versionlabel %></coral-card-property>`  
 4. Save the changes, and see the displayed version info on the Assets-card

## Display status info on the assets-card

1. Login to crx/de (http://localhost:4502/crx/de/index.jsp)
2. Navigate to /apps/dam/gui/coral/components/admin/contentrenderer/card/asset/asset.jsp
3. Search for `<!-- Lab 729 insert line here -->`, replace it with the following lines:

    `<coral-card-info>`  
      `<coral-tag color="red" class="u-coral-pullLeft">Expired</coral-tag>`  
    `</coral-card-info>`

4. Save the changes, and see the Expired-status on the Assets-card
