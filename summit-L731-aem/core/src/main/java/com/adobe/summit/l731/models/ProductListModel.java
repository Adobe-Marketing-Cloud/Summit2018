package com.adobe.summit.l731.models;

import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageManager;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang.StringUtils;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ValueMap;
import org.apache.sling.models.annotations.*;
import org.apache.sling.models.annotations.injectorspecific.*;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;
import javax.jcr.Session;
import java.util.Iterator;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import com.adobe.summit.l731.product.ProductCategory;

@Model(
        adaptables = { SlingHttpServletRequest.class },
        resourceType = "weretail/components/structure/page",
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL
)
@Exporter(name = "jackson", extensions = "json", selector = "api", options = {
        @ExporterOption(name = "MapperFeature.SORT_PROPERTIES_ALPHABETICALLY", value = "true"),
        @ExporterOption(name = "SerializationFeature.WRITE_DATES_AS_TIMESTAMPS", value="false")
})
public class ProductListModel {

    @Inject
    private Resource resource;

    // Inject a property name whose name does NOT match the Model field name
    // Since the Default inject strategy is OPTIONAL (set on the @Model), we can mark injections as @Required. @Optional can be used if the default strategy is REQUIRED.
    @ValueMapValue @Named("jcr:title")
    private String title;

    @ValueMapValue @Named("coverImage")
    private String image;

    @Inject
    boolean navRoot;

    // Injection will occur over all Injectors based on Ranking;
    // Force an Injector using @Source(..)
    // If an Injector is not working; ensure you are using the latest version of Sling Models
    @Inject @Source("sling-object") @Required
    private ResourceResolver resourceResolver;

    private Page page;

    private Iterator<Page> childPages;

    private List<ProductCategory> categories;

    @PostConstruct
    // PostConstructs are called after all the injection has occurred, but before the Model object is returned for use.
    private void init() {
        // Note that @PostConstruct code will always be executed on Model instantiation.
        // If the work done in PostConstruct is expensive and not always used in the consumption of the model, it is
        // better to lazy-execute the logic in the getter and persist the result in  model state if it is requested again.
        page = resourceResolver.adaptTo(PageManager.class).getContainingPage(resource);
        //Return all children of this particular Page
        childPages = page.listChildren(null, false);
    }

    /**
     * This getter wraps business logic around how an logic data point (title) is represented for this resource.
     *
     * @return The Page Title if exists, with fallback to the jcr:title
     */
    public String getTitle() {
        //return StringUtils.defaultIfEmpty(pageTitle, title);
        return title;
    }

    /**
     * This getter exposes the work of a @PostConstruct method.
     *
     * @return the list of cq:Pages that exist under this resource.
     */
    public List<ProductCategory> getCategories() {
        if(this.childPages.hasNext()){
            return getProductCategoryChildren(this.childPages);
        }
        return null;
    }

    /**
     * @return Get the image url for the product category.
     */
    public String getImage() {
        return image;
    }

    /**
     * @return A list of child ProductCategory objects
     */
    public List<ProductCategory> getProductCategoryChildren(Iterator<Page> pageIterator) {
        //Create empty list of categories
        List<ProductCategory> tmpCategories = new ArrayList<ProductCategory>();
        ProductCategory productCategory = null;
        while(pageIterator.hasNext()){
            //Get title and image from each child page
            Page childPage = pageIterator.next();
            String path = childPage.getPath();
            ValueMap pageProperties = childPage.getContentResource().adaptTo(ValueMap.class);
            String template = pageProperties.get("cq:template","");
            String title = pageProperties.get("jcr:title",childPage.getName());
            String image = "";
            Boolean isProduct = false;
            String productPath = "";

            if(template.equals("/conf/we-retail/settings/wcm/templates/section-page")){
                //category page
                image = pageProperties.get("coverImage","");
            } else if(template.equals("/conf/we-retail/settings/wcm/templates/product-page")){
                //product page
                isProduct = true;
                productPath = pageProperties.get("cq:productMaster","");
                Resource imageResource = ((childPage.getContentResource()).getChild("image"));
                if(imageResource != null){
                    ValueMap imageProperties = imageResource.adaptTo(ValueMap.class);
                    image = imageProperties.get("fileReference","");
                } else{
                    //Look under product node because some products have image in different locations. ugh.
                    imageResource = ((childPage.getContentResource()).getChild("root/product/image"));
                    if(imageResource != null) {
                        ValueMap imageProperties = imageResource.adaptTo(ValueMap.class);
                        image = imageProperties.get("fileReference", "");
                    }
                }
            }

            //Get grandchildren pages
            Iterator<Page> childPages = childPage.listChildren(null, false);
            List<ProductCategory> childCategories = new ArrayList<ProductCategory>();
            //Recursion if more children
            if(childPages.hasNext()){
                childCategories = getProductCategoryChildren(childPages);
            }
            productCategory = new ProductCategory(title, image, path, isProduct, productPath, childCategories);
            tmpCategories.add(productCategory);
        }
        return tmpCategories;
    }

}
