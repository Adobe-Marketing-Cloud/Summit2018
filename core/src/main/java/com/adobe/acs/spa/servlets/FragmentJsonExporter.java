package com.adobe.acs.spa.servlets;

import com.adobe.acs.spa.sling.models.SimpleListModelExporter;
import com.adobe.acs.spa.synthetic.SyntheticRequestWrapper;
import com.adobe.acs.spa.synthetic.SyntheticResource;
import com.adobe.cq.export.json.ComponentExporter;
import java.io.IOException;
import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jcr.nodetype.NodeType;
import javax.servlet.*;
import org.apache.commons.lang3.StringUtils;
import org.apache.felix.scr.annotations.Reference;
import org.apache.felix.scr.annotations.sling.SlingFilter;
import org.apache.felix.scr.annotations.sling.SlingFilterScope;
import org.apache.sling.api.*;
import org.apache.sling.api.resource.*;
import org.apache.sling.models.factory.ExportException;
import org.apache.sling.models.factory.MissingExporterException;
import org.apache.sling.models.factory.ModelFactory;

/**
 * JSON output filter for content fragments (place-holder until updated Assets API is available 2H 2018)
 * Note: This is a bit of a hack to prove a concept.  You would be better off using the SimpleList content exporter.
 */
@SlingFilter(
        metatype = false,
        scope = SlingFilterScope.REQUEST,
        order = Integer.MIN_VALUE
)
public class FragmentJsonExporter implements Filter {

    @Reference
    private ModelFactory modelFactory;

    public static final String PREFIX = "/api/cf";
    public static final String BASE_PATH = "/content/dam";
    public static final String JSON_SUFFIX = ".json";

    @Override
    public void init(FilterConfig fc) throws ServletException {
    }

    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        if (request instanceof SlingHttpServletRequest && shouldFilter((SlingHttpServletRequest) request)) {
            processRequest((SlingHttpServletRequest) request, (SlingHttpServletResponse) response);
        } else {
            chain.doFilter(request, response);
        }
    }

    private boolean shouldFilter(SlingHttpServletRequest request) {
        return request.getMethod().equals("GET")
                && request.getRequestURI().startsWith(PREFIX)
                && request.getRequestURI().endsWith(JSON_SUFFIX);
    }

    private void processRequest(SlingHttpServletRequest request, SlingHttpServletResponse response) throws IOException, ServletException {
        try {
            String path = BASE_PATH + StringUtils.substringAfter(
                    StringUtils.substringBeforeLast(request.getRequestURI(), JSON_SUFFIX),
                    PREFIX);

            Resource component;
            ComponentExporter model;
            SyntheticRequestWrapper requestWrapper;
            if (isFolder(path, request.getResourceResolver())) {
                component = generateSyntheticList(path, request.getResourceResolver());
                requestWrapper = new SyntheticRequestWrapper(request, component);
                model = new SimpleListModelExporter(requestWrapper, modelFactory, path);
            } else {
                component = SimpleListModelExporter.generateSyntheticFragment("/fake", path, request.getResourceResolver());
                requestWrapper = new SyntheticRequestWrapper(request, component);
                model = modelFactory.getModelFromWrappedRequest(requestWrapper, component, ComponentExporter.class);
            }
            response.getWriter().write(modelFactory.exportModel(model, "jackson", String.class, Collections.emptyMap()));
        } catch (ExportException | MissingExporterException ex) {
            Logger.getLogger(FragmentJsonExporter.class.getName()).log(Level.SEVERE, null, ex);
            throw new ServletException(ex);
        }
    }

    public Resource generateSyntheticList(String path, ResourceResolver rr) {
        SyntheticResource list = new SyntheticResource("/fake/resource", SimpleListModelExporter.LIST_RESOURCE_TYPE);
        list.setResourceResolver(rr);
        return list;
    }

    public boolean isFolder(String path, ResourceResolver rr) {
        String type = rr.resolve(path).getValueMap().get("jcr:primaryType", String.class);
        return type.equals("sling:Folder") || type.equals(NodeType.NT_FOLDER);
    }
}
