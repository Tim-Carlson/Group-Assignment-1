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
	
	public FileOperator() {
		parser = new JSONParser();
		siteList = new ArrayList();
	}

	
	public void readFile(String location) {
		Site sitePlaceholder;
		int siteId, prevSiteId; 
		int readingDate;
		double readingValue;
		String readingId;
		String readingType;
		try {//try block because the following is very exception-prone
			
			//"/Users/Ryan/Desktop/example.json"
			//added location of Ryan's example.json file for hardcoding
			Object obj = parser.parse(new FileReader("/Users/Ryan/Desktop/example.json"));
			JSONObject jObj = (JSONObject) obj;
			
			JSONArray siteArray = (JSONArray) jObj.get("site_readings");
			Iterator<String> siteScan = siteArray.iterator();
			
			
			
			
			
			
			
			
			while(siteScan.hasNext()) {
				
				Object readings = siteScan.next();
				JSONObject jReadings = (JSONObject) readings;
				
				siteId = (int) jReadings.get("site_id");
				readingId = (String) jReadings.get("reading_id");
				readingType = (String) jReadings.get("reading_type");
				readingValue = (double) jReadings.get("reading_value");
				readingDate = (int) jReadings.get("reading_date");
				System.out.println(siteId + readingId + readingType + readingValue + readingDate);
				sitePlaceholder = findSite(siteId);
				
				
				  
				  
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
	
	private Site findSite(int siteId) {//This returns a site with a matching site id, regardless of it previously existing
		
		int size = siteList.size();
		
		for(int i = 0; i<size; i++) {
			if(siteId == siteList.get(i).getSite_id())
				return siteList.get(i);	
		}
		
		return new Site(siteId);
	}
	
	
	public static void main(String[] args) {
		Gui gui = new Gui();
		gui.setVisible(true);
		
	}
}
