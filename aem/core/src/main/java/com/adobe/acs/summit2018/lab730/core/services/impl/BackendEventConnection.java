package com.adobe.acs.summit2018.lab730.core.services.impl;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.metatype.annotations.Designate;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.adobe.acs.summit2018.lab730.core.models.event.EventDetailsPojo;
import com.google.gson.Gson;

@Component(service=BackendEventConnection.class)
@Designate(ocd = BackendEventConnection.Configuration.class)
public class BackendEventConnection {
	
	private static final Logger LOG = LoggerFactory.getLogger(BackendEventConnection.class);
	
	
	Configuration conf;
	
	@Activate
	protected void activate (Configuration config) {
		this.conf = config;
	}
	
	
	
	public EventDetailsPojo getEvent (String id) throws IOException {
		HttpGet request = buildRequest(id);
		
		try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
			ResponseHandler<String> responseHandler = new ResponseHandler<String>() {

                @Override
                public String handleResponse(
                        final HttpResponse response) throws ClientProtocolException, IOException {
                    int status = response.getStatusLine().getStatusCode();
                    if (status >= 200 && status < 300) {
                        HttpEntity entity = response.getEntity();
                        return entity != null ? EntityUtils.toString(entity) : null;
                    } else {
                        throw new ClientProtocolException("Unexpected response status: " + status);
                    }
                }

            };

            String responseBody = httpClient.execute(request, responseHandler);
			//result = config.getHostname();
			String result = responseBody;
			
			Gson gson = new Gson();
			return gson.fromJson(result, EventDetailsPojo.class);	
		}
		
	}
	
	
	private HttpGet buildRequest(String eventId) {
		
		String address = String.format("%s/%s/%s", conf.hostname(), conf.pathprefix(), eventId);
		HttpGet request = new HttpGet(address);
		request.addHeader("accept","application/json");
		
		return request;
	}
	
	
	@ObjectClassDefinition (name=" BackendEventConnectionConfiguration", description =" Backend Event Configuration")
	protected @interface Configuration {
		
		String hostname() default "http://localhost:8080";
		
		String  pathprefix() default "events";
		
	}
	
	

}
