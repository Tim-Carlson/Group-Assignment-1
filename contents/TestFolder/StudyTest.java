
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import assignment_2.Reading;
import assignment_2.Site;
import assignment_2.Study;

class StudyTest {
	Reading reading_1 = new Reading("10.0", "01/10/19", "A001", "Temp");
	Site site1 = new Site("S001");
	Site site2 = new Site("S002");
	Study study1 = new Study("FirstStudy", "ST001");

	@Test
	public void testAddSite() {
		study1.addSite(site1);
		assertEquals("ST001", study1.getStudyID());
		
	}
	@Test
	public void testGetNameOfStudy() {
		study1.addSite(site1);
		assertEquals("FirstStudy", study1.getNameOfStudy());
	}
	@Test
	public void testGetSite() {
		study1.addSite(site1);
		assertEquals(site1, study1.getSite("S001"));
	}
	@Test
	public void testGetSiteList() {
		study1.addSite(site1);
		study1.addSite(site2);
		ArrayList <Site> List = new ArrayList<>();
		List.add(site1);
		List.add(site2);
		assertEquals(List , study1.getSiteList());
		
	}
	
	@Test
	public void testSetStudyID() {
		study1.addSite(site1);
		study1.setStudyID("STU01");
		assertEquals("STU01",study1.getStudyID());
	}
	
	@Test
	public void testGetStudyID() {
		assertEquals("ST001", study1.getStudyID());
	}
	@Test
	public void testSetNameOfStudy() {
		study1.setNameOfStudy("SecondStudy");
		assertEquals("SecondStudy", study1.getNameOfStudy());
	}
	@Test
	public void testSetSiteClose() {
		study1.addSite(site1);
		study1.setSiteClose("S001");
		assertFalse(study1.isCollectionOpen("S001"));
		
	}
	@Test
	public void testSetSiteOpen(){
		study1.addSite(site1);
		study1.setSiteClose("S001"); // since site is open by default, this will close it. 
		study1.setSiteOpen("S001"); // opening the site
		assertTrue(study1.isCollectionOpen("S001"));
	}
	


}
