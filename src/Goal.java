import java.util.Calendar;
import java.util.concurrent.TimeUnit;

/**
 * This class describes a Goal set by the user.
 * @author Radi Antonova
 *
 */

public class Goal {
	public String name; //name of goal
	public int startTime; // start time of goal as an int (as the user would put it)
	public int duration;  //duration of goal
	public int day;  // date on which the goal starts (day of month)
	public int month;  //month in which the goal starts
	public int year;   //year in which the goal starts
	public Calendar rightNow = Calendar.getInstance();  //Calendar to assist with the conversion of time to milliseconds
	public long timeInMillis; //start time calculated in milliseconds
	
	/**
	 * Constructor of class Goal. 
	 * @param	goalName			name of goal
	 * @param	time				start time of goal
	 * @param   duration			duration of goal
	 */
	public Goal(String goalName, int time, int duration, int day, int month, int year) {
		setGoalName(goalName);	
		setGoalStartTime(time);
		setGoalDuration(duration);
		this.day = day;
		this.month = month;
		this.year = year;
		calculateInMillis();
	}
	
	/**
	 * Method to calculate the start time of the goal in milliseconds.
	 */
	public void calculateInMillis() {
		int years = year - rightNow.get(Calendar.YEAR);
		int months = Math.abs(rightNow.get(Calendar.MONTH) + 1 - month);
		int days = Math.abs(rightNow.get(Calendar.DAY_OF_MONTH) - day);
		long startTimeInMillis = TimeUnit.MILLISECONDS.convert((long) getGoalStartTime(), TimeUnit.HOURS);
		long daysInMillis = TimeUnit.MILLISECONDS.convert((long) days, TimeUnit.DAYS);
		long monthsInMillis = TimeUnit.MILLISECONDS.convert((long) months*30, TimeUnit.DAYS);
		long yearsInMillis = TimeUnit.MILLISECONDS.convert((long) years*365, TimeUnit.DAYS);
		rightNow.setTimeInMillis(rightNow.getTimeInMillis() + startTimeInMillis+ daysInMillis + monthsInMillis + yearsInMillis);
		timeInMillis = rightNow.getTimeInMillis();
	}
	
	/**
	 * Method to set the name of the goal.
	 * @param	name	name of the goal
	 */
	public boolean setGoalName(String name) {
		if (name == null || name.isEmpty()) {
			return false;
		}
		this.name = name;
		return true;
	}
	
	/**
	 * Method to get the name of the goal.
	 * @return	the name of the goal
	 */
	public String getGoalName() {
		return name;
	}
	
	/**
	 * Method to set the start time of the goal.
	 * @param	time	time of the goal
	 */
	public boolean setGoalStartTime(int time) {
		if (time < 0) {
			return false;
		}
		this.startTime = time;
		return true;
	}
	
	/**
	 * Method to get the start time of the goal.
	 * @return	the start time of the entry
	 */
	public int getGoalStartTime() {
		return startTime;
	}
	
	/**
	 * Method to set the duration of the goal.
	 * @param	duration	duration of the goal
	 */
	public boolean setGoalDuration(int duration) {
		if (duration < 0) {
			return false;
		}
		this.duration = duration;
		return true;
	}
	
	/**
	 * Method to get the duration of the goal.
	 * @return	the duration of the goal
	 */
	public int getGoalDuration() {
		return duration;
	}

	/**
	 * Method to set the deadline by which the goal has to be achieved.
	 * @param	deadline	deadline of the goal
	 */
	//public boolean setGoalDeadline(int deadline) {
	//	if (deadline < 0) {
	//		return false;
	//	}
	//	this.deadline = deadline;
	//	return true;
	//}
	
	/**
	 * Method to get the deadline of the goal.
	 * @return	the deadline of the goal
	 */
	//public int getGoalDeadline() {
	//	return deadline;
	//}
	
	/**
	 * Method to print details
	 */
	public void print() {
		System.out.println("Name: " + getGoalName());
		System.out.println("Start time: " + getGoalStartTime());
		System.out.println("Duration: " + getGoalDuration());
    //  System.out.println("Deadline: " + getGoalDeadline());
	}
}
