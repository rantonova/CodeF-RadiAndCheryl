import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.TimeUnit;

/**
 * This class describes the calendar schedule which
 * consists of events and goals.
 * @author Radi Antonova
 *
 */

public class Schedule {
	/**
	 * List of all calendar entries.
	 */
	public ArrayList<CalendarEntry> calendarEntries = new ArrayList<CalendarEntry>();;

	/**
	 * List of all events.
	 */
	public ArrayList<Event> eventList = new ArrayList<Event>();

	/**
	 * Constructor of Schedule.
	 */
	public Schedule() {
	}

	public static void main(String[] args) {
		Event event1 = new Event("Coding", 20, 2, "Home", 5, 12, 2012);
		Event event2 = new Event("Testing", 21, 2, "Home", 6, 12, 2012);
		Goal goal1 = new Goal("Complete homework", 10, 7, 7, 12, 2012);
		Schedule mySchedule = new Schedule();
		mySchedule.addEvent(event1);
		mySchedule.addEvent(event2);
		mySchedule.addGoal(goal1);
		ArrayList<CalendarEntry> finalSchedule = mySchedule.produceSchedule();
		for (CalendarEntry element : finalSchedule) {
			element.print();
			System.out.print("\n");
		}
		mySchedule.produceGoalSessions(goal1);
		System.out.println("##############");
		ArrayList<CalendarEntry> schedule = mySchedule.produceSchedule();
		for (CalendarEntry element : schedule) {
			element.print();
			System.out.print("\n");
		}
		
	}
	
	/**
	 * Method to produce the schedule which is combination of events and goals.
	 * It combines all entries in a list, and sorts them according to their start time.
	 * @return	ArrayList<CalendarEntry>	List of calendar entries.
	 */
	public ArrayList<CalendarEntry> produceSchedule() {
		for (Event element : eventList) {
			calendarEntries.add((CalendarEntry) element);
		}
		
		eliminateDuplicates();		
		Collections.sort(calendarEntries);
		return calendarEntries;
	}

	/**
	 * Method to schedule as many session as necessary to achieve the goal.
	 * @param goal the specific goal that needs to be achieved
	 */
	public void produceGoalSessions(Goal goal) {
		while (goal.duration > 0) {
			breakIntoSessions(goal);
			System.out.println("Session scheduling happened.");
		}
	}
	
	/**
	 * Method to schedule a session for the achievement of the goal.
	 * Method checks if session could be scheduled. If yes, adds it and produces new version of the schedule.
	 * @param goal the goal that need to be broken into sessions
	 */
	public void breakIntoSessions(Goal goal) {
		CalendarEntry goalEntry = findGoal(goal);
		if (calculateTimeToNextEntry(goalEntry)) {
			scheduleSession(goal);
			produceSchedule();
		} else {
			int index = calendarEntries.indexOf(goalEntry);
			goalEntry.timeInMillis = calendarEntries.get(index + 1).getEndTimeInMillis(); 
		}
	}
	
	/**
	 * Method to calculate if it is possible to schedule a session before the next entry (if there is one).
	 * @param entry the entry that contains the reference to the goal
	 * @return true if a session can be scheduled
	 */
	public boolean calculateTimeToNextEntry(CalendarEntry entry) {
		int index = calendarEntries.indexOf(entry);
		long difference;
		if (index != (calendarEntries.size() - 1)) {
			difference = calendarEntries.get(index + 1).getTimeInMillis() - entry.getTimeInMillis();
			long twoHours = TimeUnit.MILLISECONDS.convert(2, TimeUnit.HOURS);;
			if (difference >= twoHours) {
				return true;
			} else {
				return false;
			}
		} else {
			return true;
		}
	}
	
	/**
	 * Method to create a single session and add it to the list of calendar entries.
	 * Session starts when the achievement of the goal starts.
	 * @param goal the goal that needs to broken down into sessions
	 */
	public void scheduleSession(Goal goal) {
		long duration = TimeUnit.MILLISECONDS.convert( 2, TimeUnit.HOURS);
		CalendarEntry session = new CalendarEntry();
		session.setName(goal.name);
		session.timeInMillis = goal.timeInMillis;
		session.duration = 2; //It is assumed that every session is 2 hours for now
		calendarEntries.add(session);
		goal.timeInMillis = goal.timeInMillis + duration; //give timeInMillis a new value so that next session could start at a new time
		goal.duration = goal.duration - 2; 
	}
	
	/**
	 * Method to find a specific event exists in the list of calendar entries.
	 * @param event The event we're looking for
	 * @return the entry
	 */
	public CalendarEntry findEvent(Event event) {
		CalendarEntry foundEntry = new CalendarEntry();
		for (CalendarEntry element : calendarEntries) {
			if (element == event) {
				foundEntry = element;
			}
		}
		return foundEntry;
	}
	
	/**
	 * Method to find a specific goal exists in the list of calendar entries.
	 * @param goal The goal we're looking for
	 * @return the entry
	 */
	public CalendarEntry findGoal(Goal goal) {
		CalendarEntry foundEntry = new CalendarEntry();
		for (CalendarEntry element : calendarEntries) {
			if (element.goal == goal) {
				foundEntry = element;
			}
		}
		return foundEntry;
	}
	

	/**
	 * Method to add an event to list of events
	 * @param	event	Event to be added to list of events
	 * @return	true if event has been successfully added
	 */
	public boolean addEvent(Event event){
		eventList.add(event);
		return true;
	}

	/**
	 * Method to remove an event from list of events
	 * @param	event	Event to be removed from list of events
	 * @return	true if event has been successfully removed
	 */
	public boolean removeEvent(Event event){
		for (Event element : eventList) {
			if (element.equals(event)) {
				eventList.remove(element); break;
			}
		}
		return true;
	}

	/**
	 * Method to add a goal to list of goals
	 * @param	goal	Goal to be added to list of goals
	 * @return	true if goal has been successfully added
	 */
	public boolean addGoal(Goal goal){
		CalendarEntry entry = new CalendarEntry(goal);
		calendarEntries.add(entry);
		return true;
	}

	/**
	 * Method to remove a goal from list of goals
	 * @param	goal	Goal to be removed from list of goals
	 * @return	true if goal has been successfully removed
	 */
	public boolean removeGoal(Goal goal){
		for (CalendarEntry element : calendarEntries) {
			if (element.isAGoal && element.goal.equals(goal)) {
				calendarEntries.remove(element); break;
			}
		}
		return true;
	}
	
	/**
	 * Method to check if two entries are essentially the same.
	 * If essentially equal entries are found, the second one is removed.
	 * @param entry1 One of the calendar entries
	 * @param entry2 The other one of the calendar entries
	 * @return true if the entries are essentially the same
	 */
	public boolean checkIfSame(CalendarEntry entry1, CalendarEntry entry2) {
		if (entry1 instanceof Event && entry2 instanceof Event) {
		if (entry1.name.equals(entry2.name) && entry1.duration == entry2.duration) {
			if (entry1 instanceof Event && entry2 instanceof Event) {
				Event event1 = new Event();
				Event event2 = new Event();
				event1 = (Event) entry1;
				event2 = (Event) entry2;
				if (event1.place.equals(event2.place)) {
					calendarEntries.remove(entry2);
					return true;
				}
			} else {
				calendarEntries.remove(entry2);
				return true;
			}
		}
		}
		return false;
	}

	/**
	 * Method to eliminate the duplicates in the calendar entry list.
	 */
	public void eliminateDuplicates() {
		for (int i = 0; i < calendarEntries.size() - 1; i++) {
			for (int j = i+1; j < calendarEntries.size(); j++) {
				checkIfSame(calendarEntries.get(i), calendarEntries.get(j));
			}
		}
	}
}
