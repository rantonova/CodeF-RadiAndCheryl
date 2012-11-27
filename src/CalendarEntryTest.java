import static org.junit.Assert.*;

import org.junit.Test;


public class CalendarEntryTest {

	CalendarEntry entry = new CalendarEntry("Do school work", 13, 7);
	/**
	 * Test to set the name of an entry.
	 */
	@Test
	public void testSetName1() {
		entry.setName("Football");
		assertEquals("Football", entry.name);
	}
	
	/**
	 * Test to check if an entry string is accepted as a name.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testSetName2() {
		entry.setName("");
	}
	
	/**
	 * Test to check if the name could be null.
	 */
	@Test(expected = NullPointerException.class)
	public void testSetName3() {
		entry.setName(null);
	}

	/**
	 * Test to set the start time of an entry.
	 */
	@Test
	public void testSetStartTime1() {
		entry.setStartTime(16);
		assertEquals(16, entry.startTime);
	}
	
	/**
	 * Test to check if the start time of an entry could be a negative number.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testSetStartTime2() {
		entry.setStartTime(-4);
	}
	

	/**
	 * Test to set the duration of an entry.
	 */
	@Test
	public void testSetDuration1() {
		entry.setDuration(30);
		assertEquals(30, entry.duration);
	}
	
	/**
	 * Test to check if the duration of an entry could be a negative number.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testSetDuration2() {
		entry.setDuration(-20);
	}	
}
