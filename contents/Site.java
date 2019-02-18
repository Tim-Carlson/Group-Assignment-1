package contents;

import java.util.ArrayList;

public class Site {

	
	private int site_id;
	private boolean collection_open; //made default of == true
	private ArrayList<Reading> readings;
	
	
	
	public Site(int site_id) {
		this.site_id = site_id;
		this.collection_open = true;
		readings = new ArrayList<Reading>();
	}

	public int getSite_id() {
		return site_id;
	}

	// Return a reading.
	public Reading getReading(int index) {
		Reading ret = null;
		if (index < readings.size())
			ret = readings.get(index);
		
		return ret;
	}
	
	// Return the number of readings in the Site
	public int size() {
		return readings.size();
	}
	
	public boolean isCollection_open() {
		return collection_open;
	}

	public void closeCollection() {
		this.collection_open = false;
	}
	
	public void openCollection() {
		this.collection_open = true;
	}
	
	public void addReading(double reading_value, String reading_date, String reading_id, String reading_type) {//This currently allows the addition of duplicates, creates a new object every call
		readings.add(new Reading(reading_value, reading_date, reading_id, reading_type));
	}
	

	@Override
	public String toString() {
		String openClose = "open";
		String output = "";
		
		if(!collection_open) openClose = "closed";
		
		for(int i = 0; i< readings.size(); i++) {
			output = output + readings.get(i) + "\n";

		}

		return output + "\nSite collection is " + openClose + " for siteId " + site_id;
	
	}
	
	
	
}
