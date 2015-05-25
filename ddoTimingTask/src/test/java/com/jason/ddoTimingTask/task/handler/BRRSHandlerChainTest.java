package com.jason.ddoTimingTask.task.handler;

import com.jason.ddoTimingTask.task.BaseTaskTest;

public class BRRSHandlerChainTest extends BaseTaskTest {

	private BRRSHandlerChain chain = null;
	protected void setUp() throws Exception {
		super.setUp();
		this.chain = BRRSHandlerChain.getInstance();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testDoHandler() {
		//this.chain.doHandler(resultRecord);
	}

}
