package com.jason.ddoTimingTask.task.handler;

import java.util.List;

import com.jason.ddoTimingTask.bean.SmTask;
import com.jason.ddoTimingTask.task.BaseTaskTest;
import com.jason.ddoTimingTask.bean.SmMsisdnList;
import com.jason.ddoTimingTask.dao.DaoException;
import com.jason.ddoTimingTask.dao.DaoManager;

public class SmRequestHandlerTest extends BaseTaskTest {

	private SmRequestHandler smRequestHandler;
	protected void setUp() throws Exception {
		super.setUp();
		this.smRequestHandler = SmRequestHandler.getInstacne();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testHandle() throws HandlerException, DaoException {
		SmTask smTask = new SmTask();
		smTask.setId("402848814cff40a4014cff4d79220025");
		smTask.setMsisdnNum(new Integer(24));
		smTask.setRecapture(new Integer(0));
		smTask.setSendType(new Integer(1));
		smTask.setBillBusinessId("1");
		smTask.setChannelUserName("test");
		smTask.setChannelUserPass("cc03e747a6afbbcbf8be7668acfebee5");
		List<SmMsisdnList> msisdnList = DaoManager.getInstance().getSmMsisdnListDao().getSmMsisdnListList(smTask.getId());
		this.smRequestHandler.handle(msisdnList, smTask);
	}

}
