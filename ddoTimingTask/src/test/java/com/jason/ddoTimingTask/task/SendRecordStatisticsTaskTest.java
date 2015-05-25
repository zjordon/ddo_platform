package com.jason.ddoTimingTask.task;


public class SendRecordStatisticsTaskTest extends BaseTaskTest {

	private SendRecordStatisticsTask task = null;
	protected void setUp() throws Exception {
		super.setUp();
		this.task = new SendRecordStatisticsTask();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testExecute() {
		this.task.execute();
	}

}
