import static org.junit.Assert.*;

import org.junit.Test;


public class GoalTest {

	Goal goal = new Goal("Do school work", 13, 7);
	/**
	 * Testing with different inputs to check if the name of a goal is set correctly.
	 */
	@Test
	public void testSetGoalName() {
		goal.setGoalName("");
		assertEquals("Finish homework", goal.name);
	}
	
	@Test
	public void testSetGoalStartTime() {
		goal.setGoalStartTime(16);
		assertEquals(16, goal.startTime);
	}

	@Test
	public void testSetGoalDuration() {
		
	}
	
	@Test
	public void testGoalConstructor() {
		
	}
}
