package contents;

import java.util.ArrayList;


public class Controller {
	
	
	private ArrayList<Study> studyList;
	private Study notApplicable;
	private JsonReader jRead;
	private JsonWriter jWrite;
	private String memoryLocation;
	
	public Controller() {
		studyList = new ArrayList<Study>();
		notApplicable = new Study("Not Applicable", "404");
		studyList.add(notApplicable);
		jRead = new JsonReader();
		jWrite = new JsonWriter();
		memoryLocation = "./src/contents/memory.json";
		restoreMemory();
	}

	public static void main(String[]args) {
		Gui gui = new Gui();
		gui.setVisible(true);
	}
	
	private void restoreMemory() {
		if(readJson(memoryLocation));
		else System.out.println("Was unable to load from memory file");
	}
	
	public boolean saveToMemory() {
		return writeJson(memoryLocation);
	}
	
	public boolean readJson(String location) {
		return jRead.read(location, notApplicable);
	}
	
	public boolean writeJson(String location) {
		return jWrite.write(location, studyList);
	}
	
	
	public void addEntry(String site, String id, String key, String val, String date) {
		Site buf = notApplicable.getOrMakeSite(site);
		if (buf.isCollection_open())
			buf.addReading(val, "" + date, id, key);
		else System.out.println("Site " + buf.getSite_id() + " is closed.");
	}
	
	public void setSiteOpen(String siteID) {
		int size = studyList.size();
		
		for(int i = 0; i < size; i++) {
			if(studyList.get(i).getSite(siteID) == null);
			else {
				studyList.get(i).getSite(siteID).openCollection();
			}
		}
	}
	
	public void setSiteClosed(String siteID) {
		int size = studyList.size();
		
		for(int i = 0; i < size; i++) {
			if(studyList.get(i).getSite(siteID) == null);
			else {
				studyList.get(i).getSite(siteID).closeCollection();
			}
		}
	}
	
	/**
	 * Searches through all studies for sites with matching siteIDs to display
	 * @param siteID
	 * @return
	 */
	public String displaySite(String siteID) {
		int size = studyList.size();
		String output = "";
		
		for(int i = 0; i < size; i++) {
			if(studyList.get(i).getSite(siteID) == null);
			else {
				output += studyList.get(i).getSite(siteID).toString();
			}
		}
		
		return output;
	}
	
	public Study getStudy(String studyID) {
		Study temp = null;
		int size = studyList.size();
		for(int i = 0; i < size; i++) {
			if(studyList.get(i).getStudyID().equals(studyID)) {
				temp = studyList.get(i);
			}
		}
			
			return temp;
	}
	
	
	

}