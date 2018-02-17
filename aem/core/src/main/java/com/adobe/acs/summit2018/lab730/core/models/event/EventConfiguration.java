package com.adobe.acs.summit2018.lab730.core.models.event;

import org.apache.sling.caconfig.annotation.Configuration;

@Configuration(label="Event Configuration", description="Event Configuration")
public @interface EventConfiguration {
	
	
	String getHostname() default "http://localhost:8080";
	
	String overViewPage() default "/content/summit2018/en";
	

}
