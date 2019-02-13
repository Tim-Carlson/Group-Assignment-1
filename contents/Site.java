package contents;

import java.util.ArrayList;

public class Site {

	
	private int site_id;
	private boolean collection_open; //made default of == true, this does not currently prevent data entry when false
	private ArrayList<Reading> readings;
	
	
	
	public Site(int site_id) {
		this.site_id = site_id;
		this.collection_open = true;
		readings = new ArrayList<Reading>();
	}

	public int getSite_id() {
		return site_id;
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
		String output= "";
		for(int i = 0; i< readings.size(); i++) {
			output = output + readings.get(i) + "\n";

		}

		return output;
	
	}
	
	
	
}
