package com.adobe.summit.demo.headless.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import javax.annotation.Nonnull;
import javax.inject.Inject;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Default;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ScriptVariable;
import org.apache.sling.models.annotations.injectorspecific.Self;

import com.adobe.cq.export.json.ComponentExporter;
import com.day.cq.wcm.api.Page;

@Model(adaptables = { SlingHttpServletRequest.class, Resource.class },
        adapters = { List.class})
public class List {

    @Self
    private SlingHttpServletRequest request;

    @ScriptVariable
    private Page currentPage;

    @Inject
    @Default(booleanValues = true)
    private boolean linkItems;

    protected java.util.List<ListItem> pages;

    @Nonnull
    public Collection<ListItem> getItems() {

        pages = new ArrayList<>();
        Iterator<Page> children = currentPage.listChildren();
        while (children.hasNext()) {
            pages.add(new ListItem(request, children.next()));
        }
        return pages;
    }

    public boolean isLinkItems() {
        return linkItems;
    }

    public void setLinkItems(boolean linkItems) {
        this.linkItems = linkItems;
    }

}
