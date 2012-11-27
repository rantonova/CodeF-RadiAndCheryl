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
	 * Constructor of class CalendarEntry.
	 * @param	entryName			name of entry
	 * @param	time				start time of entry
	 * @param   durationOfEntry		duration of entry
	 */
	public CalendarEntry(String entryName, int time, int durationOfEntry) {
		setCalendarEntry(entryName, time, durationOfEntry);	
	}
	
	/**
	 * Default constructor.
	 */
	public CalendarEntry(){
		
	}
	
	/**
	 * Method to set the details of a calendar entry.
	 * @param	entryName			name of entry
	 * @param	time				start time of entry
	 * @param   durationOfEntry		duration of entry	
	 */
	public void setCalendarEntry(String entryName, int time, int durationOfEntry) {
		setName(entryName);
		setStartTime(time);
		setDuration(durationOfEntry);
	}
	

	/**
	 * Method to set the name of the entry.
	 * @param	name	name of the entry
	 * @throws	IllegalArgumentException	If argument is empty or null.
	 */
	public void setName(String name) throws IllegalArgumentException, NullPointerException {
		if (name == null) {
			throw new NullPointerException("An event should have a name");
		} else if (name.isEmpty()) {
			throw new IllegalArgumentException("An event should have a name");
		} else {
		this.name = name;
		}
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
	 * @throws	IllegalArgumentException	If argument is a negative number.
	 */
	public void setStartTime(int time) throws IllegalArgumentException {
		if (time < 0) {
			throw new IllegalArgumentException("Start time of an event cannot be negative.");
		} else {
		startTime = time;
		}
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
	 * @throws	IllegalArgumentException	If argument is a negative number.
	 */
	public void setDuration(int duration) throws IllegalArgumentException {
		if (duration < 0) {
			throw new IllegalArgumentException("The duration of an event cannot be negative.");
		} else {
		this.duration = duration;
		}
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
		System.out.println("Name: " + getName());
		System.out.println("Start time: " + getStartTime());
		System.out.println("Duration: " + getDuration());
	}
	
	/**
     * @param entry The calendar entry to compare to.
     */
    @Override
    public int compareTo(CalendarEntry entry)
    {
        if (this.getStartTime() < entry.getStartTime())
        {
            return -1;
        }
        else if (this.getStartTime() == entry.getStartTime())
        {
            return 0;
        }
        else
        {
            return 1;
        }
    }
        
}
