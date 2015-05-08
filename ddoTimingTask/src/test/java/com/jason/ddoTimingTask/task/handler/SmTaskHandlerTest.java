package com.jason.ddoTimingTask.task.handler;

import com.jason.ddoTimingTask.bean.SmTask;
import com.jason.ddoTimingTask.task.BaseTaskTest;
import com.jason.ddoTimingTask.task.handler.smTask.SmTaskHandler;

public class SmTaskHandlerTest extends BaseTaskTest {

	private SmTaskHandler smTaskHandler;
	protected void setUp() throws Exception {
		super.setUp();
		this.smTaskHandler = SmTaskHandler.getInstacne();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testHandle() throws HandlerException {
		SmTask smTask = new SmTask();
		smTask.setId("402848814cff40a4014cff493e8f0004");
		smTask.setMsisdnNum(new Integer(24));
		smTask.setRecapture(new Integer(1));
		smTask.setSendType(new Integer(1));
		smTask.setBillBusinessId("402848814cc52410014cc528a0d60007");
		smTask.setChannelUserName("test2");
		smTask.setChannelUserPass("a7ce44784868cbb9fc13c0427cd893ed");
		this.smTaskHandler.handle(smTask);
	}

}
