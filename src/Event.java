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
	
	/**
	 * Constructor of class Event.
	 * @param	eventName			name of event
	 * @param	time				start time of event
	 * @param	placeName			name of place
	 * @param   durationOfEvent		duration of event
	 */
	public Event(String eventName, int time, int durationOfEvent, String placeName) {
		super(eventName, time, durationOfEvent);
		setPlaceOfEvent(placeName);	
	}

	public Event() {
		
	}
	
	/**
	 * Method to set the name of the place.
	 * @param	place	name of the place
	 * @throws	IllegalArgumentException	If argument is empty or null.
	 */
	public void setPlaceOfEvent(String place) throws IllegalArgumentException {
		if (place == null) {
			throw new NullPointerException("A place should have a name");
		} else if (place.isEmpty()) {
			throw new IllegalArgumentException("A place should have a name");
		} else {
		this.place = place;
		}
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
