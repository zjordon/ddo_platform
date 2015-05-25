package com.jason.ddoTimingTask.task;


public class BillResultRecordStatTaskTest extends BaseTaskTest {

	private BillResultRecordStatTask task = null;
	protected void setUp() throws Exception {
		super.setUp();
		this.task = new BillResultRecordStatTask();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testExecuteTask() {
		this.task.execute();
	}

}
