package com.adobe.acs.summit2018.lab730.core.models.event;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.caconfig.resource.ConfigurationResourceResolver;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.Optional;
import org.apache.sling.models.annotations.injectorspecific.OSGiService;
import org.apache.sling.models.annotations.injectorspecific.Self;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.adobe.acs.summit2018.lab730.core.services.impl.BackendEventConnection;

@Model(adaptables=Resource.class)
public class EventModel {

	
	private final Logger LOG = LoggerFactory.getLogger(EventModel.class);
	
	@Self
	Resource resource;
	
	@Inject @Optional
	private String eventId;
	
	@Inject @Optional
	private String imageRef;
	
	EventConfiguration config;
	
	@OSGiService
	BackendEventConnection connection;
	
	@OSGiService
	ConfigurationResourceResolver configResolver;
	
	EventDetailsPojo eventDetails;
	
	
	boolean valid = false;
	
	
	@PostConstruct
	protected void init()  {
		LOG.info("EventModel initiated on {}", resource.getPath());
		
		try {
			if (isValid()) {
				eventDetails = connection.getEvent(eventId);	
				if (eventDetails != null) {
					valid = true;
				} 
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public String getResult() {
		return eventDetails.getName();
	}
	
	
	public String getLocation() {
		return eventDetails.getLocation();
	}
	
	public String getImageRef() {
		return imageRef;
	}
	
	public EventDetailsPojo getEvent() {
		return eventDetails;
	}
	
	
	public boolean isValid() {
		return StringUtils.isNotEmpty(eventId);
	}
	
}
