import static org.junit.Assert.*;

import org.junit.Test;


public class GoalTest {

	Goal goal = new Goal("Do school work", 20, 368, 3, 6, 2013);
	/**
	 * Testing with different inputs to check if the name of a goal is set correctly.
	 */
	@Test
	public void testSetGoalName1() {	
		assertFalse(goal.setGoalName(""));
	}
	
	@Test
	public void testSetGoalName2() {	
		assertTrue(goal.setGoalName("Learn how to speak French"));
	}
	
	@Test
	public void testSetGoalStartTime1() {
		assertTrue(goal.setGoalStartTime(16));
	}
	
	@Test
	public void testSetGoalStartTime2() {
		assertFalse(goal.setGoalStartTime(-4));
	}

	@Test
	public void testSetGoalDuration1() {
		assertTrue(goal.setGoalDuration(40));
	}
	
	@Test
	public void testSetGoalDuration2() {
		assertFalse(goal.setGoalDuration(-40));
	}
	
	//@Test
	//public void testSetGoalDeadline1() {
	//	assertTrue(goal.setGoalDeadline(388));
	//}

	//@Test
	//public void testSetGoalDeadline2() {
	//	assertFalse(goal.setGoalDeadline(-88));
	//}
}
