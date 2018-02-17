package com.adobe.acs.summit2018.lab730.backend.injectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.adobe.acs.summit2018.lab730.backend.injectors.impl.DelayInjector;
import com.adobe.acs.summit2018.lab730.backend.injectors.impl.RequestErrorInjector;

@Component
public class ResponseTransformer {
	
	@Autowired
	DelayInjector delay;
	
	@Autowired
	RequestErrorInjector requestError;
	
	public ResponseEntity<String> build (String body, HttpStatus status) {
		
		
		ResponseEntity<String> result =  new ResponseEntity<String> (body, status);
		try {
			return delay.transform(requestError.transform(result));
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

}
