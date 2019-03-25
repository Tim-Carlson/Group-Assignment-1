
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import assignment_2.Reading;
import assignment_2.Site;

class SiteTest {
	Reading reading_1 = new Reading("10.0", "01/10/19", "A001", "Temp");
	Site site1 = new Site("S001");
	Site site2 = new Site("S002");
	

	@Test
	public void testGetSiteMethod() {
		assertEquals("S001", site1.getSite_id());
	
	}
	@Test
	private void testGetReading() {
		site1.addReading("11.4", "01/10/19","A001", "Humidity ");
		assertEquals(reading_1, site1.getReading(0));
	
	}
	@Test
	public void testAddReading() {
		site1.addReading("11.4", "01/10/19","A001", "Humidity");
		// checking if the method added a reading object by calling the reading_Id.
		assertEquals("A001", site1.getReading(0).getReading_id());
	}
	
	@Test
	public void testSize(){
		site1.addReading("11.4", "01/10/19","A001", "Humidity");
		assertEquals(1, site1.size());
		
	}
	@Test
	public void testIsCollection_open(){
		site1.closeCollection(); // since collection is open by default, this will close it for a test. 
		assertFalse(site1.isCollection_open());
	}
	@Test
	public void testClosCollection(){
		assertTrue(site1.isCollection_open());
	}
	@Test
	public void testOpenCollection(){
		site1.closeCollection();
		site1.openCollection();
		assertTrue(site1.isCollection_open());
		
	}
	

}
