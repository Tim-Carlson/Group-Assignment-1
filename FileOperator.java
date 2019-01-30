package contents;

import java.io.*;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;

import org.json.simple.*;
import org.json.simple.parser.*;



public class FileOperator {
	
	private JSONParser parser;
	private ArrayList<Site> siteList;
	private Site sitePlaceholder;
	private int siteId; 
	private long readingDate;
	private double readingValue;
	private String readingId;
	private String readingType;
	
	public FileOperator() {
		parser = new JSONParser();
		siteList = new ArrayList();
	}

	
	public void readFile(String location) {
		
		int siteId, prevSiteId; 
		long readingDate;
		double readingValue;
		String readingId;
		String readingType;
		try {//try block because the following is very exception-prone
			
			//"/Users/Ryan/Desktop/example.json"
			//added location of Ryan's example.json file for hardcoding
			Object obj = parser.parse(new FileReader(location));
			JSONObject jObj = (JSONObject) obj;
			
			JSONArray siteArray = (JSONArray) jObj.get("site_readings");
			Iterator<String> siteScan = siteArray.iterator();
			
			
			while(siteScan.hasNext()) {
				
				Object readings = siteScan.next();
				JSONObject jReadings = (JSONObject) readings;
				
				siteId = Integer.parseInt(String.valueOf(jReadings.get("site_id")));//this portion converts json readings into local variables - in the most messy way possible
				readingDate = Long.parseLong(String.valueOf(jReadings.get("reading_date")));
				readingId = String.valueOf(jReadings.get("reading_id"));
				readingType = String.valueOf(jReadings.get("reading_type"));
				readingValue = Double.valueOf(String.valueOf(jReadings.get("reading_value")));
				
				sitePlaceholder = findOrMakeSite(siteId);
				
				if(readingType.equals("humidity")) {
					sitePlaceholder.setHumidity(readingDate, readingValue, readingId);
				} else if(readingType.equals("particulate")) {
					sitePlaceholder.setParticulate(readingDate, readingValue, readingId);
				} else if(readingType.equals("temp")) {
					sitePlaceholder.setTemperature(readingDate, readingValue, readingId);
				} else if(readingType.equals("bar_press")) {
					sitePlaceholder.setBarometric(readingDate, readingValue, readingId);
				}
				
				
				  
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
	
	public String displaySite(String siteString) {
		
		int siteId = Integer.parseInt(siteString);
		int size = siteList.size();
		for(int i = 0; i < size; i++) {
			if(siteId == siteList.get(i).getSite_id())
				return siteList.get(i).toString();	
			
		}
		
		return null;
	}
	
	public static void main(String[] args) {
		Gui gui = new Gui();
		gui.setVisible(true);
		
	}


	
}
