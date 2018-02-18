package com.adobe.acs.summit2018.lab730.backend.controller;



import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.adobe.acs.summit2018.lab730.backend.events.EventInformation;
import com.adobe.acs.summit2018.lab730.backend.events.EventManager;
import com.adobe.acs.summit2018.lab730.backend.injectors.ResponseTransformer;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;


@RestController
public class EventController {
	
	private static final Logger LOG = LoggerFactory.getLogger(EventController.class);
	
	@Autowired
	EventManager eventManager;
	
	@Autowired
	ResponseTransformer transformer;

	@RequestMapping(value = "/events" ,method = RequestMethod.GET )
	public ResponseEntity<String> getAllEvents () {
		LOG.info("Requesting event listing");
		Gson gson = new Gson();
		JsonElement element = gson.toJsonTree(eventManager.getListing(), 
				new TypeToken<List<EventInformation.SummaryData>>() {}.getType());
		
		return transformer.build(element.toString(), HttpStatus.OK);
		
	}
	
	@RequestMapping(value = "/events/{id}", method = RequestMethod.GET)
	public ResponseEntity<String> getEvent (@PathVariable("id") String id) {
		LOG.info("Requesting event {}", id);
		EventInformation e = eventManager.getEventInformation(id);
		if (e != null) {
			return transformer.build (new Gson().toJson(e), HttpStatus.OK);
		} else {
			LOG.warn("event {} not found", id);
			return transformer.build ("{\"status\":\"not found\"}", HttpStatus.NOT_FOUND);
		}
	}
	
}
