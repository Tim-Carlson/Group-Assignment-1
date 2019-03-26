package contents;

import java.util.ArrayList;
public class Site {
	
    private String site_id;
    private boolean collection_open; //made default of == true
    private ArrayList<Reading> readingList;

	
    public Site(String site_id) {
        this.site_id = site_id;
        this.collection_open = true;
        readingList = new ArrayList<Reading>();
    }

    public String getSite_id() {
        return site_id;
    }

    // Return a reading.
    public Reading getReading(int index) {
        Reading ret = null;
        if (index < readingList.size())
            ret = readingList.get(index);

        return ret;
    }

	
    // Return the number of readings in the Site
    public int size() {
        return readingList.size();
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

    public void addReading(String reading_value, String reading_date,
    String reading_id, String reading_type) {
        readingList.add(new Reading(reading_value, reading_date, reading_id, reading_type));
    }

    /**
     * Robert here with a quick addition; an additional "addReading" method that takes a reading object and implements it
     */
    public void addReading(Reading imported){
        readingList.add(imported);
    }
    /** This ends my contribution to the site object. -Robert
     * 
     */

    @Override
    public String toString() {
        String openClose = "open";
        String output = "";
        int size = readingList.size();

        if(!collection_open) openClose = "closed";

        for (int i = 0; i < size; i++) {
            output = output + readingList.get(i).toString() + "\n";
        }

        return output + "\tSite collection is " + openClose + " for siteId " + site_id+ "\n\n";

    }

	
}
