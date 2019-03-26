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
	
	public ArrayList<Site> getSiteList() {
		return siteList;
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
	
	public int size() {
		return siteList.size();
	}
	
	public Site getSite(String siteID) {
		Site temp = null;
		int size = siteList.size();
		
		for(int i = 0; i < size; i++) {
			if(siteList.get(i).getSite_id().equals(siteID)) {
				temp = siteList.get(i);
			}
		}
			
			return temp;
	}
	/**
	 * This returns a site with a matching site id, regardless of it previously existing
	 * @param siteId
	 * @return
	 */
	public Site getOrMakeSite(String siteId) {
		
		if(getSite(siteId) == null) {
			Site newSite = new Site(siteId);
			siteList.add(newSite);
			return newSite;
		} else return getSite(siteId);
	}
	
	public boolean isCollectionOpen(String siteId) {//Defaults to true when site not found
		if(getSite(siteId) == null) return true;
		else return getSite(siteId).isCollection_open();
	}
	
	// Sets site to open.
	public void setSiteOpen(String siteId) {
		if(getSite(siteId) == null) {
			System.out.println("Site Id not Found");
		} else {
			getSite(siteId).openCollection();
		}
	}
	
	// Sets site to close.
	public void setSiteClose(String siteId) {
		if(getSite(siteId) == null) {
			System.out.println("Site Id not Found");
		} else {
			getSite(siteId).closeCollection();
		}
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
		int size = siteList.size();
		String output = "Study name: "+ nameOfStudy + "\nStudy ID: "+ studyID+ "\n";
		
		if (siteList != null) {
			for (int i = 0; i < size; i++) {
				output += siteList.get(i).toString() + "\n";
				System.out.println("used twice");
			}
		}
		return output;

	}

}
