package com.jason.ddoTimingTask.task;

public class SendResultRecordStatTaskTest extends BaseTaskTest {

	private SendResultRecordStatTask task;
	protected void setUp() throws Exception {
		super.setUp();
		this.task = new SendResultRecordStatTask();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testExecute() {
		this.task.execute();
	}

}
