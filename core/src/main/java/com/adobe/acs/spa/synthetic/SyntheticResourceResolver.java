package com.adobe.acs.spa.synthetic;

import java.util.HashMap;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.wrappers.ResourceResolverWrapper;

/**
 * Wrap a resource resolver and also try to be mindful of tracked synthetic resources too.
 */
public class SyntheticResourceResolver extends ResourceResolverWrapper {
    
    Map<String, Resource> trackedResource = new HashMap<>();

    public SyntheticResourceResolver(ResourceResolver rr) {
        super(rr);
    }

    public String normalizePath(String str) {
        return StringUtils.substringBefore(str, ".");
    }
    
    @Override
    public Resource getResource(String str) {
        String path = normalizePath(str);
        if (trackedResource.containsKey(path)) {
            return trackedResource.get(path);
        } else {
            return super.getResource(path);
        }
    }

    @Override
    public Resource resolve(String str) {
        String path = normalizePath(str);
        if (trackedResource.containsKey(path)) {
            return trackedResource.get(path);
        } else {
            return super.resolve(path);
        }
    }

    void trackSyntheticResource(Resource res) {
        trackedResource.put(res.getPath(), res);
    }

    void forgetSyntheticResource(String path) {
        trackedResource.remove(path);
    }
    
}
