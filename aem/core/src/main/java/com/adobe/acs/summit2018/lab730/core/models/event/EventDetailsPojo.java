package com.adobe.acs.summit2018.lab730.core.models.event;

import java.util.Calendar;

public class EventDetailsPojo {

	private String eventId;
	private String name;
	private String location;
	private Calendar startDate;
	private Calendar stopDate;
	private String facilitator;
	private String link;
	
	public EventDetailsPojo (String id) {
		eventId = id;
	}
	
	
	public String getEventId () {
		return eventId;
	}
	
	public String getName() {
		return name;
	}
	
	public String getLocation() {
		return location;
	}
	
	public Calendar getStartDate() {
		return startDate;
	}
	
	public Calendar getStopDate() {
		return stopDate;
	}
	
	public String getFacilitator() {
		return facilitator;
	}
	
	public String getLink() {
		return link;
	}
	
	
	public SummaryData getSummary () {
		return new SummaryData (eventId, name);
	}
	
	
	
	public class SummaryData {
		
		private String eventId;
		private String name;
		
		public SummaryData (String id, String name) {
			this.eventId = id;
			this.name = name;
		}
		
		public String getEventId() {
			return this.eventId;
		}
		
		public String getName () {
			return this.name;
		}
		
	}
	
	
}
