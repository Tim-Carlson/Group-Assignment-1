package contents.TestFolder;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import contents.JsonWriter;
import contents.Site;
import contents.Study;

class JsonWriterTest {
	
	// instantiating necessary objects. 
	ArrayList<Study> studyList = new ArrayList<>();
	Site site = new Site("001");
	Study study1 = new Study("Alpha", "A001");
	JsonWriter writer =  new JsonWriter();
	
	

	@Test
	void test() {
		// populating a site object with readings. 
		site.addReading("78.7C", "01/02/19", "001", "Temp");
		site.addReading("35.8m/h", "01/02/19", "002", "Wind Speed");
		site.addReading("38", "01/02/19", "003", "Humidity");
		// adding a site object to a study object. 
		study1.addSite(site);
		studyList.add(study1);
		// writing a file to a Json format. address 
		
		// (PLEASE CHANGE THE FILE ADDRESS FIRST TO YOUR LOCAL COMPUTER TO RUN THE REST.)
		writer.write(" c:â�¨Usersâ�©\\fromssaolana\\Desktop\\test001.jsonâ�©", studyList);
		
		//checking if the file is written and exists. 
		boolean result = new File(" c:â�¨Usersâ�©\\fromssaolana\\Desktop\\test001.jsonâ�©").isFile();
		assertTrue(result);		
		
	}

}
