package contents;

import java.io.*;

import java.util.ArrayList;
import java.util.Iterator;

import org.json.simple.*;
import org.json.simple.parser.*;

public class FileOperator {
	
	private JSONParser parser;
	private ArrayList<Site> siteList;
	private Site sitePlaceholder;
	private int siteId; 
	
	private double readingValue;
	private String readingDate;
	private String readingId;
	private String readingType;
	
	public FileOperator() {
		parser = new JSONParser();
		siteList = new ArrayList<Site>();
	}

	// Return true if the JSON file could be written.
	public boolean writeFile(String location) {
		boolean ret = false;
		String sbuf = "";
		Reading rbuf;
		
		JSONObject site;
		JSONObject readings[];
		
		for (int i = 0; i < siteList.size(); i++)
		{
			site = new JSONObject();
			site.put("site_id", siteList.get(i).getSite_id());
			readings = new JSONObject[siteList.get(i).size()];
			for (int j = 0; j < readings.length; j++) {
				rbuf = siteList.get(i).getReading(j);
				readings[j] = new JSONObject();
				readings[j].put("reading_id", rbuf.getReading_id());
				readings[j].put("reading_type", rbuf.getReading_type());
				readings[j].put("reading_date", rbuf.getReading_date());
				readings[j].put("reading_value", rbuf.getReading_value());
				
				site.put("reading_" + (j + 1), readings[j]);
			}
		}
		
		return ret;
	}
	
	public void readFile(String location) {
		
		
		try {//try block because the following is very exception-prone
			
			
			Object obj = parser.parse(new FileReader(location));
			JSONObject jObj = (JSONObject) obj;
			
			JSONArray siteArray = (JSONArray) jObj.get("site_readings");
			Iterator<String> siteScan = siteArray.iterator();
			
			
			while(siteScan.hasNext()) {
				
				Object readings = siteScan.next();
				JSONObject jReadings = (JSONObject) readings;
				
				siteId = Integer.parseInt(String.valueOf(jReadings.get("site_id")));//this portion converts json readings into local variables - in the most messy way possible
				readingValue = Double.valueOf(String.valueOf(jReadings.get("reading_value")));
				readingDate = String.valueOf(jReadings.get("reading_date"));
				readingId = String.valueOf(jReadings.get("reading_id"));
				readingType = String.valueOf(jReadings.get("reading_type"));
				
				
				sitePlaceholder = findOrMakeSite(siteId);
				sitePlaceholder.addReading(readingValue, readingDate, readingId, readingType);
			
			}
			
		} catch(FileNotFoundException e ) {
			e.printStackTrace();
		} catch(IOException e) {
			e.printStackTrace();
		} catch(ParseException e ) {
			e.printStackTrace();
		} catch(Exception e ) {
			e.printStackTrace();
		}
		
	}
	
	private Site findOrMakeSite(int siteId) {//This returns a site with a matching site id, regardless of it previously existing
		
		int size = siteList.size();
		for(int i = 0; i < size; i++) {
			if(siteId == siteList.get(i).getSite_id())
				return siteList.get(i);	
			
		}
		
		Site newSite = new Site(siteId);
		siteList.add(newSite);
		return newSite;
	}
	
	// Sets site to open.
	public void setSiteOpen(int siteId) {
		int size = siteList.size();
		for(int i = 0; i < size; i++) {
			if(siteId == siteList.get(i).getSite_id())
				 siteList.get(i).openCollection();	
		}
	}
	
	// Sets site to close.
	public void setSiteClose(int siteId) {
		int size = siteList.size();
		for(int i = 0; i < size; i++) {
			if(siteId == siteList.get(i).getSite_id())
				 siteList.get(i).closeCollection();	
		}
	}
		
	public String displaySite(String siteString) {
		
		int siteId = Integer.parseInt(siteString);
		int size = siteList.size();
		
		for(int i = 0; i < size; i++) {
			if(siteId == siteList.get(i).getSite_id())
				return siteList.get(i).toString();	
			
		}
		
		return null;
	}
}
