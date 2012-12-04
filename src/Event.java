import java.util.Calendar;
import java.util.concurrent.TimeUnit;

/**
 * This class describes an Event.
 * @author Radi Antonova
 *
 */
public class Event extends CalendarEntry{
	/**
	 * Name of the place where the event will happen.
	 */
	String place;
	
	int year; //Year in which the event starts
	int month;  //Month in which the event starts
	int day;  //Date on which the event starts (day of month)
	Calendar rightNow = Calendar.getInstance(); //Calendar to assist with the conversion of time to milliseconds 
	
	
	/**
	 * Constructor of class Event.
	 * @param	eventName			name of event
	 * @param	time				start time of event
	 * @param	placeName			name of place
	 * @param   durationOfEvent		duration of event
	 */
	public Event(String eventName, int time, int durationOfEvent, String placeName, int day, int month, int year) {
		super(eventName, time, durationOfEvent);
		isAGoal = false;
		setPlaceOfEvent(placeName);	
		this.day = day;
		this.month = month;
		this.year = year;
		calculateInMillis();
	}

	/**
	 * Default constructor.
	 */
	public Event() {
		
	}
	
	/**
	 * Method to calculate the start time of the event in milliseconds.
	 */
	public void calculateInMillis() {
		
		int years = year - rightNow.get(Calendar.YEAR);
		int months = Math.abs(rightNow.get(Calendar.MONTH) + 1 - month);
		int days = Math.abs(rightNow.get(Calendar.DAY_OF_MONTH) - day);
		long startTimeInMillis = TimeUnit.MILLISECONDS.convert((long) getStartTime(), TimeUnit.HOURS);
		long daysInMillis = TimeUnit.MILLISECONDS.convert((long) days, TimeUnit.DAYS);
		long monthsInMillis = TimeUnit.MILLISECONDS.convert((long) months*30, TimeUnit.DAYS);
		long yearsInMillis = TimeUnit.MILLISECONDS.convert((long) years*365, TimeUnit.DAYS);
		rightNow.setTimeInMillis(rightNow.getTimeInMillis() + startTimeInMillis+ daysInMillis + monthsInMillis + yearsInMillis);	
		timeInMillis = rightNow.getTimeInMillis();
	}
	
	/**
	 * Method to set the name of the place.
	 * @param	place	name of the place
	 */
	public boolean setPlaceOfEvent(String place) {
		if (place == null || place.isEmpty()) {
			return false;
		}
		this.place = place;
		return true;
	}
	
	/**
	 * Method to get the name of the place.
	 * @return	the name of the place
	 */
	public String getPlaceOfEvent() {
		return this.place;
	}
	
	/**
	 * Method to print details
	 */
	@Override
	public void print() {
		super.print();
		System.out.println("Place: " + getPlaceOfEvent());
	}
	

	
	
	
	
}
