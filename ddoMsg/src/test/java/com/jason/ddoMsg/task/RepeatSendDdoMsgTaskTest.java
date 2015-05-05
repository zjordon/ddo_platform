package com.jason.ddoMsg.task;

public class RepeatSendDdoMsgTaskTest extends BaseTaskTest {

	private RepeatSendDdoMsgTask repeatSendDdoMsgTask;
	protected void setUp() throws Exception {
		super.setUp();
		repeatSendDdoMsgTask = new RepeatSendDdoMsgTask();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
		
	}

	public void testExecute() {
		int num = repeatSendDdoMsgTask.execute();
		super.assertEquals(20, num);
	}

}
