<%--
  ADOBE CONFIDENTIAL

  Copyright 2015 Adobe Systems Incorporated
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
--%>
<%@taglib prefix="cq" uri="http://www.day.com/taglibs/cq/1.0"%><%
%><%@ page import="org.apache.jackrabbit.util.Text" %><%@include file="/libs/dam/gui/coral/components/admin/contentrenderer/base/init/assetBase.jsp"%>
<cq:include script="init.jsp"/><%

String assetActionRels = StringUtils.join(getAssetActionRels(hasJcrRead, hasJcrWrite, hasAddChild, canEdit, canAnnotate, isDownloadAllowedForAdmins, isAssetExpired, isSubAssetExpired, isContentFragment, isArchive), " ");

request.setAttribute("actionRels", actionRels.concat(" " + assetActionRels));
if (allowNavigation) {
attrs.addClass("foundation-collection-navigator");
}
%>
<cq:include script="link.jsp"/>
<%
    if (request.getAttribute("com.adobe.assets.card.nav")!=null){
        navigationHref =  (String) request.getAttribute("com.adobe.assets.card.nav");
        navigationHref = Text.escapePath(navigationHref);
    }
attrs.add("data-foundation-collection-navigator-href", xssAPI.getValidHref(navigationHref));
attrs.add("data-item-type", type);

request.setAttribute("com.adobe.assets.meta.attributes", metaAttrs);

%>
<cq:include script = "meta.jsp"/>
<coral-card <%= attrs %>>
    <coral-card-asset class ="coral-Card-asset">
        <cq:include script = "assetViewer.jsp"/>
    </coral-card-asset>
	<cq:include script = "insight.jsp"/>
	<coral-card-content>
        <coral-card-context class="coral-Card-context"><%= xssAPI.encodeForHTML(displayMimeType) %></coral-card-context>
        <coral-card-title class="foundation-collection-item-title" value="<%= xssAPI.encodeForHTMLAttr(resourceTitle) %>"><%= xssAPI.encodeForHTML(resourceTitle) %></coral-card-title>
        <cq:include script = "propertyList.jsp"/>
        <link rel="properties" href="<%=xssAPI.getValidHref(navigationHref)%>">
    </coral-card-content>

    <cq:include script = "../common/card-banner.jsp"/>

    <!-- Lab 729 insert line here -->

	<cq:include script = "applicableRelationships.jsp"/>
</coral-card>
<cq:include script = "quickActions.jsp"/>
<%!

%>
