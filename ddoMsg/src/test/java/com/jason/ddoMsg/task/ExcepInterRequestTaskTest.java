package com.jason.ddoMsg.task;

public class ExcepInterRequestTaskTest extends BaseTaskTest {

	private ExcepInterRequestTask excepInterRequestTask;
	protected void setUp() throws Exception {
		super.setUp();
		excepInterRequestTask = new ExcepInterRequestTask();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testExecute() {
		int nums = excepInterRequestTask.execute();
		super.assertEquals(0, nums);
	}

}
