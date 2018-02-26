package com.adobe.acs.spa.sling.models;

import com.adobe.acs.spa.synthetic.SyntheticResource;
import com.adobe.cq.export.json.ComponentExporter;
import com.adobe.cq.export.json.ContainerExporter;
import com.fasterxml.jackson.annotation.JsonInclude;
import java.util.LinkedHashMap;
import java.util.Map;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.*;
import org.apache.sling.models.annotations.injectorspecific.Self;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;
import org.apache.commons.lang.ArrayUtils;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;
import org.apache.sling.models.factory.ModelFactory;

/**
 * Sling model exporter for a content list
 * 
 * The resource may have direct content fragments as children, or it can have a specified "path" attribute which
 * will result in looking at that path for content fragments.
 * 
 * Those fragments will be rendered with this component as if they were content fragment components.
 * 
 */
@Model(adaptables = SlingHttpServletRequest.class, resourceType = SimpleListModelExporter.LIST_RESOURCE_TYPE,
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
@Exporter(name = "jackson", extensions = "json", options = {
        @ExporterOption(name = "MapperFeature.SORT_PROPERTIES_ALPHABETICALLY", value = "true"),
        @ExporterOption(name = "SerializationFeature.WRITE_DATES_AS_TIMESTAMPS", value="false")
})
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SimpleListModelExporter implements ContainerExporter {

    public static final String LIST_RESOURCE_TYPE = "spa-demo/components/content/list";
    
    @Self
    private SlingHttpServletRequest request;

    @Inject
    private ModelFactory modelFactory;
    
    @ValueMapValue
    @Named("path")
    private String path;

    Resource folderResource;    
    
    private Map<String, ComponentExporter> childModels = null;

    public SimpleListModelExporter() {}
    
    /**
     * This constructor is meant for mocks and for direct injection.  It is not normally called by the API.
     * @param request Current request
     * @param modelFactory Model Factory service
     * @param path Path containing fragments (if null, uses path from current request)
     */
    public SimpleListModelExporter(SlingHttpServletRequest request, ModelFactory modelFactory, String path) {
        this.request = request;
        this.modelFactory = modelFactory;
        this.path = path;
        init();
    }
    
    @PostConstruct
    private void init() {
        folderResource = path == null || ".".equals(path) ? request.getResource() : request.getResourceResolver().resolve(path);
    }

    public String getPath() {
        return folderResource.getPath();
    }
    
    public int getChildCount() {
        return getExportedItems().size();
    }
    
    @Override
    public Map<String, ? extends ComponentExporter> getExportedItems() {
        if (childModels == null) {
            childModels = getChildModels(request, ComponentExporter.class);
        }

        return childModels;
    }

    private <T> Map<String, T> getChildModels(SlingHttpServletRequest request, Class<T> modelClass) {
        Map<String, T> itemWrappers = new LinkedHashMap<>();

        for (final Resource child : folderResource.getChildren()) {
            if (child.getResourceType().equals("dam:Asset")) {
                Resource synthChild = generateSyntheticFragment(request.getResource().getPath(), child.getPath(), request.getResourceResolver());
                itemWrappers.put(child.getName(), modelFactory.getModelFromWrappedRequest(request, synthChild, modelClass));
            }
        }

        return itemWrappers;
    }

    @Override
    public String[] getExportedItemsOrder() {
        Map<String, ? extends ComponentExporter> models = getExportedItems();

        if (models.isEmpty()) {
            return ArrayUtils.EMPTY_STRING_ARRAY;
        }

        return models.keySet().toArray(ArrayUtils.EMPTY_STRING_ARRAY);

    }

    @Override
    public String getExportedType() {
        return LIST_RESOURCE_TYPE;
    }
    
    public static Resource generateSyntheticFragment(String parentPath, String fragmentPath, ResourceResolver rr) {
        String resourcePath = parentPath + "/node-" + Math.floor(Math.random() * 2000);
        SyntheticResource res = new SyntheticResource(resourcePath, "spa-demo/components/content/contentfragment");
        res.putValue("fragmentPath", fragmentPath);
        res.putValue("variationName", "master");
        res.setResourceResolver(rr);
        return res;
    }
}
