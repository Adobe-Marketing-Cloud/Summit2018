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
--%><%
%><%@include file="/libs/granite/ui/global.jsp"%><%
%><%@page import="org.apache.sling.api.resource.Resource,
				  javax.jcr.Node,
				                                      javax.jcr.version.Version,
                                    javax.jcr.Session,
                                    javax.jcr.version.VersionHistory,
                                    javax.jcr.version.VersionManager,
				  com.day.cq.dam.api.Asset"%><%
%><%@taglib prefix="cq" uri="http://www.day.com/taglibs/cq/1.0"%><%
%><%@include file="/libs/dam/gui/coral/components/admin/contentrenderer/base/base.jsp"%><%
%><%@include file="/libs/dam/gui/coral/components/admin/contentrenderer/base/assetBase.jsp"%><%--###
ASSET Properties
=========

###--%><%
String versionlabel = "No version";

try {
  Session session = resourceResolver.adaptTo(Session.class);

  VersionManager versionManager = session.getWorkspace().getVersionManager();
  VersionHistory versionHistory = versionManager.getVersionHistory(resource.getPath());
  if ( versionHistory != null ){
    String[] versionlabels = versionHistory.getVersionLabels();
    int versionsize = versionlabels.length;

    if ( versionsize > 0 ) {
      versionlabel = versionlabels[versionsize-1];
    }
  }
} catch(Exception e){

}


long publishDateInMillis = request.getAttribute(PUBLISH_DATE_IN_MILLIS) != null ? (long) request.getAttribute(PUBLISH_DATE_IN_MILLIS) : -1;
String publishedDate = request.getAttribute(PUBLISHED_DATE) != null ? (String) request.getAttribute(PUBLISHED_DATE) : null;
boolean isDeactivated = request.getAttribute(IS_DEACTIVATED) != null ? (boolean) request.getAttribute(IS_DEACTIVATED) : false;
String publishedBy = request.getAttribute(PUBLISHED_BY) != null ? (String) request.getAttribute(PUBLISHED_BY) : "";
long assetLastModification = request.getAttribute(ASSET_LAST_MODIFICATION) != null ? (long) request.getAttribute(ASSET_LAST_MODIFICATION) : 0;
String lastModified = request.getAttribute(LAST_MODIFIED) != null ? (String) request.getAttribute(LAST_MODIFIED) : "";
int commentsCount = request.getAttribute(COMMENTS_COUNT) != null ? (int) request.getAttribute(COMMENTS_COUNT) : 0;
String status = request.getAttribute(STATUS) != null ? (String) request.getAttribute(STATUS) : "";
boolean isAssetExpired = request.getAttribute(IS_ASSETEXPIRED) != null ? (boolean) request.getAttribute(IS_ASSETEXPIRED) : false;
boolean isSubAssetExpired = request.getAttribute(IS_SUBASSET_EXPIRED) != null ? (boolean) request.getAttribute(IS_SUBASSET_EXPIRED) : false;
String size = request.getAttribute(SIZE) != null ? (String) request.getAttribute(SIZE) : "0.0 B";
String resolution = request.getAttribute(RESOLUTION) != null ? (String) request.getAttribute(RESOLUTION) : "";
double averageRating = request.getAttribute(AVERAGE_RATING) != null ? (double) request.getAttribute(AVERAGE_RATING) : 0.0;
double creativeRating = request.getAttribute(CREATIVE_RATING) != null ? (double) request.getAttribute(CREATIVE_RATING) : 0.0;
long width = request.getAttribute(WIDTH) != null ? (long) request.getAttribute(WIDTH) : 0;
long height = request.getAttribute(HEIGHT) != null ? (long) request.getAttribute(HEIGHT) : 0;
long bytes = request.getAttribute(BYTES) != null ? (long) request.getAttribute(BYTES) : 0;
boolean isCheckedOut = request.getAttribute(IS_CHECKED_OUT) != null ? (boolean) request.getAttribute(IS_CHECKED_OUT) : false;
boolean isMergedTemplate = request.getAttribute(IS_MERGED_PRINT_TEMPLATE) != null ? (boolean) request.getAttribute(IS_MERGED_PRINT_TEMPLATE) : false;
String checkedOutByFormatted = request.getAttribute(CHECKED_OUT_BY_FORMATTED) != null ? (String) request.getAttribute(CHECKED_OUT_BY_FORMATTED) : "";

%><coral-card-propertylist>
<% if (publishDateInMillis < assetLastModification) { %>
	<coral-card-property icon="edit" title="<%= xssAPI.encodeForHTMLAttr(i18n.get("Modified")) %>">
         <time datetime="<%= assetLastModification %>"><%= xssAPI.encodeForHTML(lastModified) %></time>
    </coral-card-property>
	<% } if (isMergedTemplate) { %>
	<coral-card-property icon="wrench" title="<%= xssAPI.encodeForHTMLAttr(i18n.get("Merged")) %>">
		<%=xssAPI.encodeForHTML(i18n.get("Merged"))%>
	</coral-card-property>
<% } if (isCheckedOut) { %>
  <coral-card-property icon="lockOn" title="<%= xssAPI.encodeForHTMLAttr(i18n.get("Checked Out By")) %>">
         <%= xssAPI.encodeForHTML(checkedOutByFormatted) %>
    </coral-card-property>
<% } if (publishedDate != null && !isDeactivated) { %>
	<coral-card-property icon="globe" title="<%= xssAPI.encodeForHTMLAttr(i18n.get("Asset Publication Status")) %>">
         <time data-timestamp="<%= publishDateInMillis %>"><%= xssAPI.encodeForHTML(publishedDate) %></time>
    </coral-card-property>
<% } else if (publishedDate != null && isDeactivated) { // expecting a previous publishing date otherwise it couldn't be deactivated
%>
	 <coral-card-property icon="unpublish" title="<%= xssAPI.encodeForHTMLAttr(i18n.get("Asset Publication Status")) %>">
         <time data-timestamp="<%= publishDateInMillis %>"><%= xssAPI.encodeForHTML(publishedDate) %></time>
    </coral-card-property>
<% } if (commentsCount > 0) { %>
<coral-card-property icon="comment" title="<%= xssAPI.encodeForHTMLAttr(i18n.get("Comments")) %>"><%= xssAPI.encodeForHTML(Integer.toString(commentsCount)) %></coral-card-property>
<% } if (status.equals("approved")) { %>
<coral-card-property class="status approved" icon="thumbUp" title="<%= xssAPI.encodeForHTMLAttr(i18n.get("Approved")) %>"></coral-card-property>
<% } else if (status.equals("rejected")) { %>
<coral-card-property class="status rejected" icon="thumbDown" title="<%= xssAPI.encodeForHTMLAttr(i18n.get("Rejected")) %>"></coral-card-property>
<% } else if (status.equals("changesRequested") || status.equals("pending")) { %>
<coral-card-property class="status changesrequested" icon="pending" title="<%= xssAPI.encodeForHTMLAttr(i18n.get("Changes Requested")) %>"></coral-card-property>
<% } if (isAssetExpired || isSubAssetExpired) { %>
<coral-card-property class="expirystatus" icon="flag" data-is-asset-expired="<%= isAssetExpired %>" data-is-sub-asset-expired="<%= isSubAssetExpired %>" title="<%= xssAPI.encodeForHTMLAttr(i18n.get("Expired")) %>"></coral-card-property>
<% } %>
<coral-card-property data-size="<%= bytes%>" title="<%= xssAPI.encodeForHTMLAttr(i18n.get("size")) %>"><%= xssAPI.encodeForHTML(size) %></coral-card-property>
</coral-card-propertylist>
<%  if (resolution.length() > 0 || averageRating > 0) {  %>
<coral-card-propertylist>
<%  if (resolution.length() > 0) {  %>
<coral-card-property title="<%= xssAPI.encodeForHTMLAttr(i18n.get("resolution")) %>" data-width="<%= width%>"><%= xssAPI.encodeForHTML(resolution)%></coral-card-property>

<!-- Lab 729 insert line here -->

<% } %>
<%
           Resource ratingConfRes= resourceResolver.getResource("/etc/dam/creativeratingconfig");
     	   Config cfg = new Config(ratingConfRes);
		   boolean creativeRatingEnabled = cfg.get("creativeRatingEnabled", false);
    	   double shownRating = 0.0;
 			if(creativeRatingEnabled)
    		{
       			 shownRating = creativeRating;
   			 }
   			 else{
       			 shownRating = averageRating;
			}
    if (shownRating > 0) {
int averageCharacterstic = (int) shownRating;
double averageMantissa = shownRating - averageCharacterstic;
double sizeinrem = 0.75;

double widthMantissa = averageMantissa * sizeinrem;
%><coral-card-property class="rating expired" title = "<%=averageRating%>">
	<div class = "rating-background" style = "color: rgba(0, 0, 0, 0.4);">
        <coral-icon icon="star" size="XS"></coral-icon>
		<coral-icon icon="star" size="XS"></coral-icon>
        <coral-icon icon="star" size="XS"></coral-icon>
        <coral-icon icon="star" size="XS"></coral-icon>
        <coral-icon icon="star" size="XS"></coral-icon>
	</div>
	<div class = "rating-foreground" style = "color: #ffd700;margin-top: -17px;">
		<%
		for (int i = 0 ; i < averageCharacterstic ; i++) {
		    %>
        	<coral-icon icon="star" size="XS" style="overflow: hidden;"></coral-icon>
		    <%
		}
		if (averageMantissa > 0) {
		    String style = "overflow: hidden; width:" + widthMantissa + "rem;";
		    %>
        	<coral-icon icon="star" size="XS" style="<%=style%>"></coral-icon>
		    <%
		}
		%>
	</div>
</coral-card-property>
<% } %>

</coral-card-propertylist>
<% } %>
