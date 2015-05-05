package com.jason.ddoMsg.task;

public class RepeatUpChannelTaskTest extends BaseTaskTest {

	RepeatUpChannelTask repeatUpChannelTask;
	protected void setUp() throws Exception {
		super.setUp();
		repeatUpChannelTask = new RepeatUpChannelTask();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testExecute() {
		int num = repeatUpChannelTask.execute();
		super.assertEquals(3, num);
	}

}
