import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;


public class ScheduleTest {

	Schedule schedule = new Schedule();
	Event event1 = new Event("Lunch with grandparents", 12, 2, "In Town"); 
	Event event2 = new Event("Go to bookshop", 16, 1, "In Town");
	Event event3 = new Event("Lecture", 9, 1, "LMB/030");
	Event event4 = new Event("Lecture", 10, 1, "LMB/030");
	Goal goal1 = new Goal("Complete project", 17, 16);
	Goal goal2 = new Goal("Finish the painting", 20, 20);
	Goal goal3 = new Goal("Learn how to cook", 12, 30);
	Goal goal4 = new Goal("Learn how to cook", 12, 30);
	
	/**
	 * Test to check if an event has been added to the schedule successfully.
	 */
	@Test
	public void testaddEvent() {
		assertTrue(schedule.addEvent(event1));
	}
	
	/**
	 * Test to check if an event has been removed from the schedule successfully.
	 */
	@Test
	public void testremoveEvent() {
		schedule.addEvent(event1);
		schedule.addEvent(event2);
		assertTrue(schedule.removeEvent(event2));
	}
	
	/**
	 * Test to check if a goal has been added to the schedule successfully.
	 */
	@Test
	public void testaddGoal() {
		assertTrue(schedule.addGoal(goal1));
	}

	/**
	 * Test to check if a goal has been removed from the schedule successfully.
	 */
	@Test
	public void testremoveGoal() {
		schedule.addGoal(goal1);
		schedule.addGoal(goal2);
		assertTrue(schedule.removeGoal(goal1));
	}

	/**
	 * Test to check if an event has been added and can be found in the final schedule.
	 */
	@Test
	public void testProcudeSchedule1() {
		schedule.addEvent(event1);
		schedule.addGoal(goal1);
		CalendarEntry foundEntry = new CalendarEntry();
		ArrayList<CalendarEntry> finalSchedule = schedule.produceSchedule();
		for (CalendarEntry element : finalSchedule) {
			if (element == event1) {
				foundEntry = element; break;
			} else {
				foundEntry = null;
			}
		}
		assertEquals(foundEntry, event1);
	}
	/**
	 * Test to check if events with the same details but different start times are considered the same.
	 */
	@Test
	public void testProcudeSchedule2() {
		schedule.addEvent(event1);
		schedule.addEvent(event3);
		schedule.addEvent(event4);
		schedule.addGoal(goal1);
		CalendarEntry foundEntry = new CalendarEntry();
		ArrayList<CalendarEntry> finalSchedule = schedule.produceSchedule();
		for (CalendarEntry element : finalSchedule) {
			if (element == event3) {
				foundEntry = element; break;
			} else {
				foundEntry = null;
			}
		}
		assertNotSame(foundEntry, event4);
	}
	
	/**
	 * Test to check if an event and a goal starting at the same time are considered the same.
	 */
	@Test
	public void testProcudeSchedule3() {
		schedule.addEvent(event1);
		schedule.addEvent(event3);
		schedule.addGoal(goal1);
		schedule.addGoal(goal2);
		schedule.addGoal(goal3);
		CalendarEntry foundEntry = new CalendarEntry();
		ArrayList<CalendarEntry> finalSchedule = schedule.produceSchedule();
		for (CalendarEntry element : finalSchedule) {
			if (element.equals(goal3)) {
				foundEntry = element;
			} else {
				foundEntry = null;
			}
		}
		assertNotSame(foundEntry, event1);
	}
	
	/**
	 * Test to check if one of two equal entries (have the same details) is removed from list, i.e.
	 * whether duplicates are removed from the list of entries.
	 */
	@Test
	public void testProcudeSchedule4() {
		schedule.addEvent(event1);
		schedule.addGoal(goal1);
		schedule.addGoal(goal2);
		schedule.addGoal(goal3);
		schedule.addGoal(goal4);
		CalendarEntry foundEntry = new CalendarEntry();
		ArrayList<CalendarEntry> finalSchedule = schedule.produceSchedule();
		for (CalendarEntry element : finalSchedule) {
			if (element == goal4) {
				foundEntry = element; break;
			} else {
				foundEntry = null;
			}
		}
		assertNull(foundEntry);
	}
	
	/**
	 * Test to demonstrate that only the added elements exist in the final schedule.
	 */
	@Test
	public void testProcudeSchedule5() {
		schedule.addGoal(goal2);		
		CalendarEntry foundEntry = new CalendarEntry();
		ArrayList<CalendarEntry> finalSchedule = schedule.produceSchedule();
		for (CalendarEntry element : finalSchedule) {
			if (element == event3) {
				foundEntry = element; break;
			} else {
				foundEntry = null;
			}
		}
		assertNull(foundEntry);
	}
	
	/**
	 * Test to check if two entries are essentially the same.
	 */
	@Test
	public void testCheckIfSame() {
		assertTrue(schedule.checkIfSame(goal3, goal4));
	}
}

