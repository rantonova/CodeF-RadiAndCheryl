/**
 * This class describes a Goal set by the user.
 * @author Radi Antonova
 *
 */

public class Goal extends CalendarEntry{
	/**
	 * Constructor of class Goal. 
	 * @param	goalName			name of goal
	 * @param	time				start time of goal
	 * @param   duration			duration of goal
	 */
	public Goal(String goalName, int time, int duration) {
		super(goalName, time, duration);	
	}
	
	/**
	 * Method to set the name of the goal.
	 * @param	name	name of the goal
	 */
	public void setGoalName(String name) {
		super.setName(name);
	}
	
	/**
	 * Method to get the name of the goal.
	 * @return	the name of the goal
	 */
	public String getGoalName() {
		return super.getName();
	}
	
	/**
	 * Method to set the start time of the goal.
	 * @param	time	time of the goal
	 */
	public void setGoalStartTime(int time) {
		super.setStartTime(time);
	}
	
	/**
	 * Method to get the start time of the goal.
	 * @return	the start time of the entry
	 */
	public int getGoalStartTime() {
		return super.getStartTime();
	}
	
	/**
	 * Method to set the duration of the goal.
	 * @param	duration	duration of the goal
	 */
	public void setGoalDuration(int duration) {
		super.setDuration(duration);
	}
	
	/**
	 * Method to get the duration of the goal.
	 * @return	the duration of the goal
	 */
	public int getGoalDuration() {
		return super.getDuration();
	}

	/**
	 * Method to print details
	 */
	@Override
	public void print() {
		super.print();
	}
}
