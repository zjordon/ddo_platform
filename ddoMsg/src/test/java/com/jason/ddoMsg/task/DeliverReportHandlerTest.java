package com.jason.ddoMsg.task;

import com.jason.ddoMsg.task.handler.DeliverReportHandler;

public class DeliverReportHandlerTest extends BaseTaskTest {

	private DeliverReportHandler deliverReportHandler;
	protected void setUp() throws Exception {
		super.setUp();
		deliverReportHandler = DeliverReportHandler.getInstance();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testHandle() {
//		this.deliverReportHandler.handle("402848814c9680e2014c9680e2e80004", "1", 13950079348L, "0", "http://112.5.196.74:8080/", false);
		this.deliverReportHandler.handle("402848814c9724c1014c9724c1d60000", "1", 13950079348L, "0", "http://112.5.196.74:8080/");
	}

}
