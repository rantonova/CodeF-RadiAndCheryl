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
	 * Method to produce the schedule which is combination of events and goals.
	 * It combines all entries in a list, and sorts them according to their start time.
	 * @return	ArrayList<CalendarEntry>	List of calendar entries.
	 */
	public ArrayList<CalendarEntry> produceSchedule() {
		Collections.sort(calendarEntries);
		return calendarEntries;
	}

	
	/**
	 * Method to schedule as many session as necessary to achieve the goal.
	 * @param goal the specific goal that needs to be achieved
	 */
	public void produceGoalSessions(Goal goal) {
		CalendarEntry goalEntry = new CalendarEntry();
		while (goal.duration > 0) {
			if (calculateTimeToNextEntry(goalEntry)) {
				scheduleASession(goal);
				/*Update the final schedule.*/
				produceSchedule();
				
			} else {
				
				int index = calendarEntries.indexOf(goalEntry);
				goalEntry.timeInMillis = calendarEntries.get(index + 1).getEndTimeInMillis(); 
				produceSchedule();
			}
		}
		removeGoal(goal);
	}
		
	/**
	 * Method to calculate if it is possible to schedule a session before the next entry (if there is one).
	 * @param entry the entry that contains the reference to the goal
	 * @return true if a session can be scheduled
	 */
	public boolean calculateTimeToNextEntry(CalendarEntry entry) {
		int index = calendarEntries.indexOf(entry);
		System.out.println("INDEX IS " + index);
		long difference;
		if (index != (calendarEntries.size() - 1)) {
			difference = calendarEntries.get(index + 1).getTimeInMillis() - entry.getTimeInMillis();
			long twoHours = TimeUnit.MILLISECONDS.convert(2, TimeUnit.HOURS);;
			return difference >= twoHours;
		} else {
			return true;
		}
	}
	
	/**
	 * Method to create a single session and add it to the list of calendar entries.
	 * Session starts when the achievement of the goal starts.
	 * @param goal the goal that needs to broken down into sessions
	 */
	public void scheduleASession(Goal goal) {
		long duration = TimeUnit.MILLISECONDS.convert( 2, TimeUnit.HOURS);
		CalendarEntry session = new CalendarEntry();
		session.setName(goal.name);
		session.timeInMillis = goal.timeInMillis;
		session.duration = 2; //It is assumed that every session is 2 hours for now
		calendarEntries.add(session);
		/*Give timeInMillis a new value so that next session could start at a new time*/
		goal.timeInMillis = goal.timeInMillis + duration; 
		goal.duration = goal.duration - 2; 
	}
	
	/**
	 * Method to find a specific goal exists in the list of calendar entries.
	 * @param goal The goal we're looking for
	 * @return true if the goal exists in the list
	 */
	public CalendarEntry findGoal(Goal goal) {
		CalendarEntry foundGoal = new CalendarEntry();
		for (CalendarEntry element : calendarEntries) {
			if (element.goal == goal) {
				foundGoal = element;
			}
		}
		return foundGoal;
	}
	
	/**
	 * Method to find if an event with the same details as the passed event
	 * already exists in the list of calendar entries.
	 * @param event The event which details we're comparing
	 * @return true if an event the same details already exists in the list
	 */
	public boolean findEvent(Event event) {
		for (Event element : eventList) {
			if (checkIfSame(element, event)) {
				return true;
			}
		}
		return false;
	}	

	/**
	 * Method to add an event to list of events
	 * @param	event	Event to be added to list of events
	 * @return	true if event has been successfully added
	 */
	public boolean addEvent(Event event){
		if (findEvent(event)) {
			return false;
		} else {
			eventList.add(event); 
			calendarEntries.add((CalendarEntry) event);
			return true; 
		}		
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
	 * Method to check if two events are essentially the same.
	 * @param event1 One of the events
	 * @param event2 The other one of the events
	 * @return true if the events are essentially the same
	 */
	public boolean checkIfSame(Event event1, Event event2) {
		if (event1.name.equals(event2.name) && event1.duration == event2.duration
				&& event1.place.equals(event2.place) && event1.day == event2.day
				&& event1.month == event2.month && event1.year == event2.year) {
					return true;
			} else {
				return false;
			}
	}
}
