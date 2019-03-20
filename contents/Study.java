package contents;

import java.util.ArrayList;

/**
 * 
 * The Study class is a collection class that holds objects of different studies. 
 * The constructor accepts a string type name of the study type and string ID.   
 */

public class Study {
	private String nameOfStudy;
	private String studyID;
	private ArrayList<Site> siteList;

	public Study(String nameOfStudy, String studyID) {
		this.nameOfStudy = nameOfStudy;
		this.studyID = studyID;
		siteList = new ArrayList<Site>();
	}

	public String getNameOfStudy() {
		return nameOfStudy;
	}

	public void setNameOfStudy(String nameOfStudy) {
		this.nameOfStudy = nameOfStudy;
	}

	public String getStudyID() {
		return studyID;
	}

	public void setStudyID(String studyID) {
		this.studyID = studyID;
	}
	/**
	 * Adds a Site object to the collection
	 * @param site
	 */
	public void addSite(Site site) {
		siteList.add(site);
	}
	/**
	 * Removes a desired Site object
	 * @param siteID
	 */
	public void removeSite(String siteID) {
		for(int i = 0; i < siteList.size(); i++) {
			if(siteList.get(i).getSite_id().equals(siteID)) {
				siteList.remove(i);
			}
		}
	}
	
	/**
	 * 
	 * @param siteID
	 * @return a Site object.
	 */
	public Site getSite(String siteID) {
		Site temp = null;
		for(int i = 0; i < siteList.size(); i++) {
			if(siteList.get(i).getSite_id().equals(siteID)) {
				temp = siteList.get(i);
			}
		}
			
			return temp;
	}

	@Override
	public boolean equals(Object obj) {
		boolean result = false;
		if(obj instanceof Study) {
			if(((Study) obj).getNameOfStudy().equals(nameOfStudy)&& ((Study) obj).getStudyID().equals(studyID)) {
				
				result = true;
			}
		}
		return  result;
	}

	public String toString() {
		String output = "Study name: "+ nameOfStudy + "\nStudy ID: "+ studyID+ "\n";
		if (siteList != null) {
			for (int i = 0; i < siteList.size(); i++) {
				output = output + siteList.get(i) + "\n";

			}

		}
		return output;

	}

}
