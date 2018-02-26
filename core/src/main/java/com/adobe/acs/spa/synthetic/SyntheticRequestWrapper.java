package com.adobe.acs.spa.synthetic;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.request.RequestDispatcherOptions;
import org.apache.sling.api.request.RequestPathInfo;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.scripting.SlingBindings;
import org.apache.sling.api.scripting.SlingScript;
import org.apache.sling.api.scripting.SlingScriptHelper;
import org.apache.sling.api.wrappers.SlingHttpServletRequestWrapper;
import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;
import org.osgi.framework.InvalidSyntaxException;
import org.osgi.framework.ServiceReference;

/**
 * Wrap a synthetic request around a synthetic resource
 */
public class SyntheticRequestWrapper extends SlingHttpServletRequestWrapper {
    
    private final Resource resource;
    private String selectorString;
    private String extension;
    private SlingBindings bindings;

    public SyntheticRequestWrapper(SlingHttpServletRequest req, Resource res) {
        super(req);
        this.resource = res;
        this.selectorString = req.getRequestPathInfo().getSelectorString();
        this.extension = req.getRequestPathInfo().getExtension();
        setupBindings();
    }
    
    private void setupBindings() {
        BundleContext context = FrameworkUtil.getBundle(this.getClass()).getBundleContext();
        bindings = new SlingBindings();
        setAttribute(SlingBindings.class.getName(), bindings);
        bindings.setRequest(this);
        bindings.setResource(resource);
        bindings.setResourceResolver(resource.getResourceResolver());
        bindings.setSling(new SlingScriptHelper() {
            @Override
            public SlingHttpServletRequest getRequest() {
                return bindings.getRequest();
            }

            @Override
            public SlingHttpServletResponse getResponse() {
                return bindings.getResponse();
            }

            @Override
            public SlingScript getScript() {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void include(String string) {
                throw new UnsupportedOperationException("Not supported yet.");
            }

            @Override
            public void include(String string, String string1) {
                throw new UnsupportedOperationException("Not supported yet.");
            }

            @Override
            public void include(String string, RequestDispatcherOptions rdo) {
                throw new UnsupportedOperationException("Not supported yet.");
            }

            @Override
            public void include(Resource rsrc) {
                throw new UnsupportedOperationException("Not supported yet.");
            }

            @Override
            public void include(Resource rsrc, String string) {
                throw new UnsupportedOperationException("Not supported yet.");
            }

            @Override
            public void include(Resource rsrc, RequestDispatcherOptions rdo) {
                throw new UnsupportedOperationException("Not supported yet.");
            }

            @Override
            public void forward(String string) {
                throw new UnsupportedOperationException("Not supported yet.");
            }

            @Override
            public void forward(String string, String string1) {
                throw new UnsupportedOperationException("Not supported yet.");
            }

            @Override
            public void forward(String string, RequestDispatcherOptions rdo) {
                throw new UnsupportedOperationException("Not supported yet.");
            }

            @Override
            public void forward(Resource rsrc) {
                throw new UnsupportedOperationException("Not supported yet.");
            }

            @Override
            public void forward(Resource rsrc, String string) {
                throw new UnsupportedOperationException("Not supported yet.");
            }

            @Override
            public void forward(Resource rsrc, RequestDispatcherOptions rdo) {
                throw new UnsupportedOperationException("Not supported yet.");
            }

            @Override
            public <ServiceType> ServiceType getService(Class<ServiceType> type) {
                ServiceReference ref = context.getServiceReference(type.getName());
                return (ServiceType) context.getService(ref);
            }

            @Override
            public <ServiceType> ServiceType[] getServices(Class<ServiceType> type, String string) {
                try {
                    ServiceReference[] ref = context.getServiceReferences(type.getName(), string);
                    return Arrays.asList(ref).stream()
                            .map(context::getService)
                            .toArray(size->(ServiceType[]) Array.newInstance(type, size));
                } catch (InvalidSyntaxException ex) {
                    Logger.getLogger(SyntheticRequestWrapper.class.getName()).log(Level.SEVERE, null, ex);
                    return null;
                }
            }

            @Override
            public void dispose() {
            }
        });
    }
    
    public SlingBindings getBindings() {
        return bindings;
    }
    
    public void setSelectorString(String selStr) {
        this.selectorString = selStr;
    }

    public void setExtension(String ext) {
        extension = ext;
    }
    
    @Override
    public ResourceResolver getResourceResolver() {
        return resource.getResourceResolver();
    }

    @Override
    public RequestPathInfo getRequestPathInfo() {
        return new RequestPathInfo() {
            @Override
            public String getResourcePath() {
                return resource.getPath();
            }

            @Override
            public String getExtension() {
                return extension;
            }

            @Override
            public String getSelectorString() {
                return selectorString;
            }

            @Override
            public String[] getSelectors() {
                return selectorString == null ? new String[0] : selectorString.split(Pattern.quote("."));
            }

            @Override
            public String getSuffix() {
                return null;
            }

            @Override
            public Resource getSuffixResource() {
                return null;
            }
        };
    }
    
}
