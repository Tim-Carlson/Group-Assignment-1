package contents.TestFolder;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import contents.JsonReader;
import contents.Study;

public class JsonReaderTest {

	JsonReader testReader = new JsonReader();
	ArrayList<Study> testList = new ArrayList<Study>();
	
	@Test
	public void testRead()
	{
		assertEquals(false, testReader.read("", testList));
	}
	
}
