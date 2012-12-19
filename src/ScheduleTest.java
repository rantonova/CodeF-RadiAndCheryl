import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;


public class ScheduleTest {

	Schedule schedule = new Schedule();
	Event event1 = new Event("Lunch with grandparents", 12, 2, "In Town", 15, 12, 2012); 
	Event event2 = new Event("Go to bookshop", 16, 1, "In Town", 16, 12, 2012);
	Event event3 = new Event("Lecture", 9, 1, "LMB/030", 16, 12, 2012);
	Event event4 = new Event("Lecture", 10, 1, "LMB/030", 16, 12, 2012);
	Event event5 = new Event("Lecture", 9, 1, "LMB/030", 16, 12, 2012);

	Goal goal1 = new Goal("Complete project",9, 16, 15, 12, 2012);
	Goal goal2 = new Goal("Finish the painting", 20, 20, 15, 12, 2012);
	Goal goal3 = new Goal("Learn how to cook", 12, 30, 17, 12, 2012);
	Goal goal4 = new Goal("Learn how to cook", 12, 30, 20, 12, 2012);
	
	/**
	 * Test to check if two events are essentially the same (have the same details).
	 */
	@Test
	public void testCheckIfSame() {
		schedule.addEvent(event3);
		schedule.addEvent(event5);
		assertTrue(schedule.checkIfSame(event3, event5));
	}
	
	/**
	 * Test to check if an event already exists in the list of events.
	 */
	@Test
	public void testFindEvent() {
		/*Adding an event.*/
		schedule.addEvent(event3);
		/*Trying to add an event that's essentially the same. Should not be added.*/
		schedule.addEvent(event5);
		assertTrue(schedule.findEvent(event5));
	}
	
	/**
	 * Test to check if an event has been added to the schedule successfully.
	 */
	@Test
	public void testAddEvent() {
		schedule.addEvent(event3);
		assertFalse(schedule.addEvent(event5));
	}
	
	/**
	 * Test to check if an event has been removed from the schedule successfully.
	 */
	@Test
	public void testRemoveEvent() {
		schedule.addEvent(event1);
		schedule.addEvent(event2);
		assertTrue(schedule.removeEvent(event2));
	}
	
	/**
	 * Test to check if a goal exists in the list of entries.
	 */
	@Test
	public void testFindGoal() {
		schedule.addGoal(goal3);
		schedule.addGoal(goal2);
		assertEquals(schedule.findGoal(goal2), schedule.findGoal(goal2));
	}
	
	/**
	 * Test to check if a goal has been added to the schedule successfully.
	 */
	@Test
	public void testAddGoal() {
		assertTrue(schedule.addGoal(goal1));
	}

	/**
	 * Test to check if a goal has been removed from the schedule successfully.
	 */
	@Test
	public void testRemoveGoal() {
		schedule.addGoal(goal1);
		schedule.addGoal(goal2);
		assertTrue(schedule.removeGoal(goal1));
	}

	/**
	 * Test to check if an event has been added and can be found in the final schedule.
	 */
	@Test
	public void testProduceSchedule1() {
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
	public void testProduceSchedule2() {
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
	public void testProduceSchedule3() {
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
	 * Test to demonstrate that only the added elements exist in the final schedule.
	 */
	@Test
	public void testProduceSchedule4() {
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
	 * Test if there is enough time for a goal session to be scheduled before the next event in the calendar.
	 */
	@Test
	public void testCalculateTimeToNextEntry() {
		schedule.addEvent(event1);
		schedule.addEvent(event2);
		schedule.addGoal(goal1);
		schedule.produceSchedule();
		assertTrue(schedule.calculateTimeToNextEntry(schedule.findGoal(goal1)));
	}
	
	/**
	 * Test to check if a goal session has been scheduled successfully. 
	 */
	@Test
	public void testScheduleASession() {
		schedule.addEvent(event1);
		schedule.addEvent(event2);
		schedule.addGoal(goal1);
		schedule.produceSchedule();
		int sizeOfCalendarEntries = schedule.calendarEntries.size();
		schedule.scheduleASession(goal1);
		assertEquals(sizeOfCalendarEntries + 1, schedule.calendarEntries.size());
	}
	
	/**
	 * Method to test if a goal session has been successfully placed in the final schedule.
	 */
	@Test
	public void testProduceGoalSessions() {
		schedule.addEvent(event1);
		schedule.addEvent(event2);
		schedule.addGoal(goal1);
		schedule.produceSchedule();
		schedule.produceGoalSessions(goal1);
		ArrayList<CalendarEntry> finalSchedule = schedule.produceSchedule();
		for (CalendarEntry element : finalSchedule) {
			element.print();
			System.out.println("\n");
		}
	}
}

