package com.adobe.acs.summit2018.lab730.backend.injectors.impl;

import java.util.concurrent.ThreadLocalRandom;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class DelayInjector {
	
	private static final Logger LOG = LoggerFactory.getLogger(DelayInjector.class);
	
	@Value("${delayInjector.minDelay:500}")
	private int minDelay;
	
	@Value("${delayInjector.maxDelay:1000}")
	private int maxDelay;
	
	
	public ResponseEntity<String> transform (ResponseEntity<String> input) throws InterruptedException {
		
		int delay = ThreadLocalRandom.current().nextInt(minDelay, maxDelay + 1);
		LOG.info("delayed by {}ms", delay);
		Thread.sleep(delay);
		return input;
	}
	
	

}
