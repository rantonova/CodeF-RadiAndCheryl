import java.util.ArrayList;
import java.util.Collections;

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
	 * List of all goals.
	 */
	public ArrayList<Goal> goalList = new ArrayList<Goal>();;

	/**
	 * Constructor of Schedule.
	 */
	public Schedule() {
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
		for  (Goal element : goalList) {
			calendarEntries.add((CalendarEntry) element);
		}
		eliminateDuplicates();		
		Collections.sort(calendarEntries);
		return calendarEntries;
	}

	/**
	 * Method to find a specific entry exists in the list of calendar entries.
	 * @param entry The entry we're looking for
	 * @return true if entry exists in the list
	 */
	public CalendarEntry findEntry(CalendarEntry entry) {
		CalendarEntry foundEntry = new CalendarEntry();
		for (CalendarEntry element : calendarEntries) {
			if (element == entry) {
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
		try {
			eventList.add(event);
			return true;
		} catch (UnsupportedOperationException e) {
			System.out.println("Caught UnsupportedOperationException: " + e.getMessage());
			return false;
		}
	}

	/**
	 * Method to remove an event from list of events
	 * @param	event	Event to be removed from list of events
	 * @return	true if event has been successfully removed
	 */
	public boolean removeEvent(Event event){
		try {
			for (Event element : eventList) {
				if (element.equals(event)) {
					eventList.remove(element); break;
				}
			}
			return true;
		} catch (UnsupportedOperationException e) {
			System.out.println("Caught UnsupportedOperationException: " + e.getMessage());
			return false;
		}
	}

	/**
	 * Method to add a goal to list of goals
	 * @param	goal	Goal to be added to list of goals
	 * @return	true if goal has been successfully added
	 */
	public boolean addGoal(Goal goal){
		try {
			goalList.add(goal);
			return true;
		} catch (UnsupportedOperationException e) {
			System.out.println("Caught UnsupportedOperationException: " + e.getMessage());
			return false;
		}
	}

	/**
	 * Method to remove a goal from list of goals
	 * @param	goal	Goal to be removed from list of goals
	 * @return	true if goal has been successfully removed
	 */
	public boolean removeGoal(Goal goal){
		try {
			for (Goal element : goalList) {
				if (element.equals(goal)) {
					goalList.remove(element); break;
				}
			}
			return true;
		} catch (UnsupportedOperationException e) {
			System.out.println("Caught UnsupportedOperationException: " + e.getMessage());
			return false;
		}
	}

	/**
	 * Method to check if two entries are essentially the same.
	 * If essentially equal entries are found, the second one is removed.
	 * @param entry1 One of the calendar entries
	 * @param entry2 The other one of the calendar entries
	 * @return true if the entries are essentially the same
	 */
	public boolean checkIfSame(CalendarEntry entry1, CalendarEntry entry2) {
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
