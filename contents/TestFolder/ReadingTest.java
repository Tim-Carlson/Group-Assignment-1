package contents.TestFolder;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import contents.Reading;

class ReadingTest {
	Reading reading_1 = new Reading("10.0","01/10/19", "A001", "Temp");

	@Test
	public void testGetReading_ValueMethod() {
		assertEquals("10.0", reading_1.getReading_value());
	}

	@Test
	public void testGetReading_date() {
		assertEquals("01/10/19", reading_1.getReading_date());
	}

	@Test
	public void testGetReading_id() {
		assertEquals("A001", reading_1.getReading_id());
	}

	@Test
	public void testGetReading_type() {
		assertEquals("Temp", reading_1.getReading_type());
	}
}
