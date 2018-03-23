package com.adobe.acs.spa.sling.models;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.models.annotations.*;
import org.apache.sling.models.annotations.injectorspecific.Self;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

import javax.annotation.PostConstruct;
import javax.inject.Named;

@Model(adaptables = {SlingHttpServletRequest.class},
resourceType = "spa-demo/components/content/tic-tac-toe",
defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
@Exporter(name = "jackson", extensions = "json", options = {
        @ExporterOption(name = "MapperFeature.SORT_PROPERTIES_ALPHABETICALLY", value = "true"),
        @ExporterOption(name = "SerializationFeature.WRITE_DATES_AS_TIMESTAMPS", value="false")
})
public class TicTacToeModelExporter {

    @Self
    private SlingHttpServletRequest request;

    @Self
    private Resource resource;

    @ValueMapValue
    @Named("title")
    @Optional
    private String title;

    @ValueMapValue
    @Named("nextPlayerText")
    @Optional
    private String nextPlayerText;

    @ValueMapValue
    @Named("xPlayerName")
    @Optional
    private String xPlayerName;

    @ValueMapValue
    @Named("yPlayerName")
    @Optional
    private String yPlayerName;

    @ValueMapValue
    @Named("gameStartText")
    @Optional
    private String gameStartText;

    @SlingObject
    @Required
    private ResourceResolver resourceResolver;

    @PostConstruct
    private void init() {
    }

    public String getTitle() {
        return title;
    }

    public String getNextPlayerText() {
        return nextPlayerText;
    }

    public String getxPlayerName() {
        return xPlayerName;
    }

    public String getyPlayerName() {
        return yPlayerName;
    }

    public String getGameStartText() {
        return gameStartText;
    }
}
