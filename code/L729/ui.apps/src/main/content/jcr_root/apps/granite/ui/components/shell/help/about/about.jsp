<%--
  ADOBE CONFIDENTIAL

  Copyright 2016 Adobe Systems Incorporated
  All Rights Reserved.

  NOTICE:  All information contained herein is, and remains
  the property of Adobe Systems Incorporated and its suppliers,
  if any.  The intellectual and technical concepts contained
  herein are proprietary to Adobe Systems Incorporated and its
  suppliers and may be covered by U.S. and Foreign Patents,
  patents in process, and are protected by trade secret or copyright law.
  Dissemination of this information or reproduction of this material
  is strictly forbidden unless prior written permission is obtained
  from Adobe Systems Incorporated.
--%><%
%><%@include file="/libs/granite/ui/global.jsp" %><%
%><%@page session="false"
          import="com.adobe.granite.ui.components.AttrBuilder,
                  com.adobe.granite.license.ProductInfo,
                  com.adobe.granite.license.ProductInfoProvider,
                  adobesummit.core.service.Version,
                  com.adobe.granite.ui.components.Tag" %>
<% Tag tag = cmp.consumeTag();

    AttrBuilder attrs = tag.getAttrs();
    ProductInfoProvider productInfoProvider = sling.getService(ProductInfoProvider.class);
    Version version = sling.getService(Version.class);
    ProductInfo productInfo = productInfoProvider.getProductInfo();

    cmp.populateCommonAttrs(attrs);
    attrs.addClass("foundation-toggleable");
    attrs.addClass("granite-help-about-dialog");
    attrs.add("variant", "info");
    attrs.add("closable", "on");

%>
<coral-dialog <%= attrs %>>
    <coral-dialog-header><%= xssAPI.encodeForHTML(i18n.get("About {0}", "{0} is the product name, eg Adobe Experience Manager", productInfo.getName())) %></coral-dialog-header>
    <coral-dialog-content class="u-coral-noPadding-vertical">
        <p><%= xssAPI.encodeForHTML(productInfo.getName()) + " " + xssAPI.encodeForHTML(productInfo.getVersion().toString()) %></p>
        <!-- Lab 729 insert line here -->
        <p><%= xssAPI.filterHTML(i18n.get("Copyright &#169; {0} Adobe Systems Incorporated and its licensors. ", "{0} is the product year", productInfo.getYear())) %></p>
        <p><%= xssAPI.encodeForHTML(i18n.get("Adobe, the Adobe logo and {0} are either registered or trademarks of Adobe Systems Incorporated in the United States and/or other countries. All other trademarks are the property of their respective owners.", "{0} is the product name, eg Adobe Experience Manager", productInfo.getName())) %></p>
        <p><%= xssAPI.filterHTML(i18n.get("Third Party notices, terms and conditions pertaining to third party software can be found at {0} and are incorporated by reference.", "{0} is the eula link", "<a class='coral-Link' target='_blank' href='http://www.adobe.com/products/eula/third_party/pdfs/adobe_products_eula.pdf'>http://www.adobe.com/products/eula/third_party/pdfs/adobe_products_eula.pdf</a>")) %></p>
    </coral-dialog-content>
</coral-dialog>
