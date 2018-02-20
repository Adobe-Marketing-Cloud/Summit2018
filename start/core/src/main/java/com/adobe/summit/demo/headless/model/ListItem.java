package com.adobe.summit.demo.headless.model;

import java.util.Calendar;
import javax.annotation.Nonnull;

import org.apache.sling.api.SlingHttpServletRequest;

import com.day.cq.wcm.api.Page;

public class ListItem {

    protected SlingHttpServletRequest request;
    protected Page page;

    public ListItem(@Nonnull SlingHttpServletRequest request, @Nonnull Page page) {
        this.request = request;
        this.page = page;
    }

    public String getTitle() {
        String title = this.page.getNavigationTitle();
        if (title == null) {
            title = this.page.getPageTitle();
        }

        if (title == null) {
            title = this.page.getTitle();
        }

        if (title == null) {
            title = this.page.getName();
        }

        return title;
    }

    public String getURL() {
        return getPath() + ".html";
    }

    public String getDescription() {
        return this.page.getDescription();
    }

    public Calendar getLastModified() {
        return this.page.getLastModified();
    }

    public String getPath() {
        return this.page.getPath();
    }

}
