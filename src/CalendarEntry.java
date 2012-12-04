import java.util.Calendar;
import java.util.concurrent.TimeUnit;

/**
 * This class describes a calendar entry which can be either 
 * a user-created Event, or a system-created entry from a Goal.
 * @author Radi Antonova
 *
 */

public class CalendarEntry implements Comparable<CalendarEntry> {
	/**
	 * Name of the event.
	 */
	String name;

	/**
	 * Time when the event starts.
	 */
	int startTime;

	/**
	 * How long the event will last.
	 */
	int duration;
	
	/**
	 * Start time calculated in milliseconds.
	 */
	long timeInMillis;
	
	/**
	 * End time of entry in milliseconds.
	 */
	long endTimeInMillis;
	
	/**
	 * A reference to a goal.
	 */
	Goal goal;
	
	/**
	 * Variable to indicate if the entry is an event or a reference to a goal.
	 */
	boolean isAGoal;
	
	/**
	 * Constructor of class CalendarEntry.
	 * @param	entryName			name of entry
	 * @param	time				start time of entry
	 * @param   durationOfEntry		duration of entry
	 */
	public CalendarEntry(String entryName, int time, int durationOfEntry) {
		name = entryName;
		startTime = time;
		duration = durationOfEntry;
	}
	
	/**
	 * Constructor for a reference to goal
	 * @param myGoal the goal that has to be referenced
	 */
	public CalendarEntry(Goal myGoal) {
		isAGoal = true;
		goal = myGoal;
	}
	/**
	 * Default constructor.
	 */
	public CalendarEntry(){
		
	}
	
	/**
	 * Method to get the start time of the entry in milliseconds.
	 * @return	the start time of the entry in milliseconds
	 */
	public long getTimeInMillis() {
		if (isAGoal) {
			timeInMillis = goal.timeInMillis;
		} 
		return timeInMillis;
	}
	
	/**
	 * Method to calculate when the entry will end in milliseconds
	 * @return the end time in milliseconds
	 */
	public long getEndTimeInMillis() {
		if (!isAGoal) {
			endTimeInMillis = timeInMillis + TimeUnit.MILLISECONDS.convert((long) duration, TimeUnit.HOURS);
		}
		return endTimeInMillis;
	}
	
	/**
	 * Method to set the name of the entry.
	 * @param	name	name of the entry
	 */
	public boolean setName(String name) {
		if (name == null || name.isEmpty()) {
			return false;
		}
		this.name = name;
		return true;
	}
	
	/**
	 * Method to get the name of the entry.
	 * @return	the name of the entry
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * Method to set the start time of the entry.
	 * @param	time	time of the entry
	 */
	public boolean setStartTime(int time) {
		if (time < 0) {
			return false;
		} 
		startTime = time;
		return true;
	}
	
	/**
	 * Method to get the start time of the entry.
	 * @return	the start time of the entry
	 */
	public int getStartTime() {
		return this.startTime;
	}
	
	
	/**
	 * Method to set the duration of the entry.
	 * @param	duration	duration of the entry
	 */
	public boolean setDuration(int duration) {
		if (duration < 0) {
			return false;
		} 
		this.duration = duration;
		return true;
	}
	
	/**
	 * Method to get the duration of the entry.
	 * @return	the duration of the entry
	 */
	public int getDuration() {
		return this.duration;
	}
	
	/**
	 * Method to print details
	 */
	public void print() {
		if (isAGoal) {
			goal.print();
		} else {
		System.out.println("Name: " + getName());
		System.out.println("Start time: " + getStartTime());
		System.out.println("Duration: " + getDuration());
		}
	}
	
	/**
     * @param entry The calendar entry to compare to.
     */
    @Override
    public int compareTo(CalendarEntry entry)
    {
        if (this.getTimeInMillis() < entry.getTimeInMillis())
        {
            return -1;
        }
        else if (this.getTimeInMillis() == entry.getTimeInMillis())
        {
            return 0;
        }
        else
        {
            return 1;
        }
    }
        
}
