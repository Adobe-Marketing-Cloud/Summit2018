package com.adobe.acs.summit2018.lab730.backend.events;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

@Service
public class EventManager {
	
	private static final Logger LOG = LoggerFactory.getLogger(EventManager.class);
	
	private static final String DATA_DIR = "events";

	
	Map<String,EventInformation> allEvents = new HashMap<>();
	
	public EventManager() throws IOException {
		File dataDir = new File (DATA_DIR);
		if (dataDir.exists() && dataDir.isDirectory()) {
			for (File f: dataDir.listFiles()) {
				if (f.getName().endsWith("json")) {
					LOG.info("processing input file {}", f.getAbsolutePath());
					parseInputFile(f).forEach(e -> {
						allEvents.put(e.getEventId(), e);
					});
				}
			}
		} else {
			LOG.warn("Cannot find data directory at {}", dataDir.getAbsolutePath());
		}
		
	}
	
	
	
	public EventInformation getEventInformation (String eventId) {
		return allEvents.get(eventId);
	}
	
	
	public List<EventInformation.SummaryData> getListing() {
		
		List<EventInformation.SummaryData> output = new ArrayList<>();
		allEvents.values().forEach(e -> {
			output.add (e.getSummary());
		});
		
		return output;
		
	}
 	
	
	//
	
	private List<EventInformation> parseInputFile (File f) throws IOException {
		List<EventInformation> result;

		try (   InputStream in = new FileInputStream(f);
				InputStreamReader isr = new InputStreamReader(in);
				JsonReader reader = new JsonReader (isr)) {

			Type listType = new TypeToken<ArrayList<EventInformation>>(){}.getType();
			result = new Gson().fromJson(reader, listType);
			
		} 
		return result;
	}
	

	
}
