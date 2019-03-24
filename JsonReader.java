package contents;

import java.io.*;
import java.util.*;
import org.json.simple.*;
import org.json.simple.parser.*;


public class JsonReader {
	
	private JSONParser parser;
	private Site sitePlaceholder;
	
	public JsonReader() {
		parser = new JSONParser();
		
	}
	
	@SuppressWarnings("unchecked")
	public boolean read(String location, Study studyPlaceholder) {
		String siteId, readingValue, readingDate, readingId, readingType;
		boolean ret = true;
		
		try {
			
			
			Object obj = parser.parse(new FileReader(location));
			JSONObject jObj = (JSONObject) obj;
			
			JSONArray siteArray = (JSONArray) jObj.get("site_readings");
			Iterator<String> siteScan = siteArray.iterator();
			
			
			while(siteScan.hasNext()) {
				
				Object readings = siteScan.next();
				JSONObject jReadings = (JSONObject) readings;
				
				siteId = String.valueOf(jReadings.get("site_id"));
				readingValue = String.valueOf(jReadings.get("reading_value"));
				readingDate = String.valueOf(jReadings.get("reading_date"));
				readingId = String.valueOf(jReadings.get("reading_id"));
				readingType = String.valueOf(jReadings.get("reading_type"));
				
				if(studyPlaceholder.isCollectionOpen(siteId)) {
					sitePlaceholder = studyPlaceholder.getOrMakeSite(siteId);
					sitePlaceholder.addReading(readingValue, readingDate, readingId, readingType);
					
				}
				
			}
			if(studyPlaceholder.getSite(sitePlaceholder.getSite_id()) == null) {
				studyPlaceholder.addSite(sitePlaceholder);
			}
			
		} catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
		
		
		
		return ret;
	}
	
}
