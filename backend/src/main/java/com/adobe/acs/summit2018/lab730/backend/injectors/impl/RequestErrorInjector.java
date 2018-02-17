package com.adobe.acs.summit2018.lab730.backend.injectors.impl;

import java.util.concurrent.ThreadLocalRandom;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class RequestErrorInjector {
	
	@Value("${requestErrorInjector.faultRate:1}")
	private int faultRate;
	
	private static final Logger LOG = LoggerFactory.getLogger(RequestErrorInjector.class);

	
	public ResponseEntity<String> transform (ResponseEntity<String> input) {
		
		ResponseEntity<String> output = input;
		int fault = ThreadLocalRandom.current().nextInt(0, 100);
		
		if (fault < faultRate) {
			// inject fault
			LOG.info("inject request error");
			output = new ResponseEntity<>("{\"status\":\"injected internal server error\"}", HttpStatus.INTERNAL_SERVER_ERROR);
		} 
		return output;
		
	}
	
}
