import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

import com.google.gson.Gson;

/**
 * This class will be used to test if a calendar entry
 * is conforming to a list of constraints.
 * The constraints will be saved in a separate configuration 
 * file for easiness of maintenance and modification of the constraints. 
 * @author Radi Antonova
 *
 */
public class ScheduleConstraints {
	/**
	 * The maximum length of a session.
	 */
	private int MAX_HOURS_PER_SESSION;
	/**
	 * The maximum number of hours available for scheduling during a week day.
	 */
	private int MAX_HOURS_TO_SCHEDULE_WEEKDAY;
	/**
	 * The maximum number of hours available for scheduling in the weekend.
	 */
	private int MAX_HOURS_TO_SCHEDULE_WEEKENDS;
	/**
	 * The maximum number of scheduled hours at once.
	 */
	private int MAX_NUMBER_OF_CONSECUTIVE_HOURS;
	/**
	 * Scheduling should not start before this hour.
	 */
	private int START_SCHEDULING_WEEKDAY;
	/**
	 * All scheduled session should finish latest at this hour.
	 */
	private int FINISH_SCHEDULING_WEEKDAY;
	
	/**
	 * Method to check if an entry is valid based on the imposed constraints.
	 * @param entry The entry which validity is being checked
	 * @return true if the entry is valid
	 */
	public boolean isValidEntry(CalendarEntry entry) {
				
		return false;
	}
	
	/**
	 * Method to check if the duration of a session is at most MAX_HOURS_PER_SESSION long.
	 * @param entry The entry(session) duration is being validated.
	 * @return true if the duration of the session is not more than the acceptable (i.e. MAX_HOURS_PER_SESSION)
	 */
	public boolean isValidMaxHoursPerSession(CalendarEntry entry) {
		return entry.duration <= MAX_HOURS_PER_SESSION;
	}
	
	//public boolean isValidMaxHoursToScheduleWeekday(CalendarEntry entry) {
	//	return false;
	//}
	
	
	
	public void print() {
		System.out.println("MAX_HOURS_PER_SESSION " + MAX_HOURS_PER_SESSION);
		System.out.println("MAX_HOURS_TO_SCHEDULE_WEEKDAY " + MAX_HOURS_TO_SCHEDULE_WEEKDAY);
		System.out.println("MAX_HOURS_TO_SCHEDULE_WEEKENDS " + MAX_HOURS_TO_SCHEDULE_WEEKENDS);
		System.out.println("MAX_NUMBER_OF_CONSECUTIVE_HOURS " + MAX_NUMBER_OF_CONSECUTIVE_HOURS);
		System.out.println("START_SCHEDULING_WEEKDAY " +  START_SCHEDULING_WEEKDAY);
		System.out.println("FINISH_SCHEDULING_WEEKDAY " + FINISH_SCHEDULING_WEEKDAY);
	}
	
	public static void main(String[] args) throws FileNotFoundException {
		Scanner scanner = new Scanner(new FileInputStream ("constraints.txt"));
		StringBuilder constraints = new StringBuilder();
		try {
			while (scanner.hasNext()) {
				constraints.append(scanner.next() + "\n");
			}
		} 
		finally {
			scanner.close();
		}
		String text = new String(constraints);
		Gson gson = new Gson();
		ScheduleConstraints obj = gson.fromJson(text, ScheduleConstraints.class);
		obj.print();
	}
}

