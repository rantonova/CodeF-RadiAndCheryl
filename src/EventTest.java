import static org.junit.Assert.*;

import org.junit.Test;


public class EventTest {

	Event event = new Event("Lunch with grandparents", 12, 2, "In Town");
	/**
	 * Test to set the place of an event.
	 */
	@Test
	public void testSetPlaceOfEvent1() {
		event.setPlaceOfEvent("Home");
		assertEquals("Home", event.place);
	}
	
	/**
	 * Test to check if empty string are accepted as names. 
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testSetPlaceOfEvent2() {
		event.setPlaceOfEvent("");
	}
	
	/**
	 * Test to check if names could be null.
	 */
	@Test(expected = NullPointerException.class)
	public void testSetPlaceOfEvent3() {
		event.setPlaceOfEvent(null);
	}

}
