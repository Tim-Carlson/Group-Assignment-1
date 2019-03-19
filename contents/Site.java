package contents;

import java.util.ArrayList;
/**
 * A Site class is a collection class that hold a Reading objects. 
 *
 */
public class Site {
	private String site_id;
	private boolean isCollection_open;
	private ArrayList<Reading> readings;

	public Site(String site_id) {
		this.site_id = site_id;
		this.isCollection_open = true;
		readings = new ArrayList<Reading>();
	}

	public int size() {
		return readings.size();
	}

	public boolean isCollection_open() {
		return isCollection_open;
	}

	public void closeCollection() {
		this.isCollection_open = false;
	}

	public void openCollection() {
		this.isCollection_open = true;
	}

	public String getSite_id() {
		return site_id;
	}

	/**
	 * 
	 * @param readingID
	 * @return a desired reading object.
	 */
	public Reading getReading(String readingID) {
		Reading tempReading = null;
		for (int i = 0; i < readings.size(); i++) {
			if (readings.get(i).getReading_id().equals(readingID)) {
				tempReading = readings.get(i);
			}
		}
		return tempReading;
	}

	/**
	 * removes a reading for the collection.
	 * 
	 * @param readingID
	 */
	public void removeReading(String readingID) {

		for (int i = 0; i < readings.size(); i++) {
			if (readings.get(i).getReading_id().equals(readingID)) {
				readings.remove(i);
			}
		}

	}

	/**
	 * Adds a reading to the collection
	 * 
	 * @param reading
	 */
	public void addReading(Reading reading) {
		if (isCollection_open) {
			readings.add(reading);
		} else {
			System.out.println("Error: collection is closed");
		}

	}
	/**
	 * checks the equality of two site objects
	 */
	public boolean equals(Object obj) {
		boolean temp = false;
		if(obj instanceof Site) {
			if(((Site) obj).getSite_id().equals(getSite_id())) {
				temp = true;
			}
		}
		return temp;
	}

	@Override
	public String toString() {
		String openClose = "open";
		String output = "";

		if (!isCollection_open)
			openClose = "closed";

		for (int i = 0; i < readings.size(); i++) {
			output = output + readings.get(i) + "\n";

		}

		return output + "\nSite collection is " + openClose + " for siteId " + site_id;

	}

}
