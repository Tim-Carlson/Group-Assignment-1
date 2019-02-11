package contents;

import java.util.ArrayList;

public class Site {
	private String site_id;  // id is string data type because we don't use for computational reason
	private boolean collection_open;
	private  ArrayList<Reading> readings;

	//The constructor will initialize instance object
	//by assigning site_id and will make new readings Arraylist.
	public Site(String siteID) {
		this.site_id = siteID;
		readings = new ArrayList<Reading>();

	}
	
	//The getters and setters methods

	public String getSite_id() {
		return this.site_id;
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

	public Reading getReadings(String readingID) {
		Reading output = null;
		for(Reading i: readings) {
			if(i.getReading_id().equals(readingID)) {
				output = i;
			}
		}
		return output;
	}

	public void addReading(Reading reading) {
		readings.add(reading);
	}


	@Override
	public boolean equals(Object obj) {
		boolean result = false;
		if(obj instanceof Site) {
			if(((Site) obj).getSite_id().equals(site_id)) {
			result = true;
		}
		}
		return result;
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
