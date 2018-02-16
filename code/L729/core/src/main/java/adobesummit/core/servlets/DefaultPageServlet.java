package adobesummit.core.servlets;

import java.io.IOException;

import javax.servlet.Servlet;
import javax.servlet.ServletException;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.osgi.framework.Constants;
import org.osgi.service.component.annotations.Component;

@Component(service=Servlet.class,
property={
        Constants.SERVICE_DESCRIPTION + "=Default Page Servlet",
        "sling.servlet.methods=" + HttpConstants.METHOD_GET,
        "sling.servlet.paths="+ "/bin/defaultpage"
})
public class DefaultPageServlet extends SlingSafeMethodsServlet {

	@Override
	protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response)
			throws ServletException, IOException {

		// condition to check if the user is "varmstrong", then we redirect directly
    // to we-retail/english
		if (request.getResourceResolver().getUserID().equalsIgnoreCase("varmstrong")) {
			response.sendRedirect("/assets.html/content/dam/we-retail/en");
			return;
		}

		response.sendRedirect("/assets.html/content/dam");
	}



}
