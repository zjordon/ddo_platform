package com.jason.ddoMsg.externalInterface;

public class BillReportInterfaceTest extends BaseInterfaceTest {
	private BillReportInterface billReportInterface;

	protected void setUp() throws Exception {
		super.setUp();
		billReportInterface = BillReportInterface.getInstance();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testReceiveBillReport() {
		String ret = this.billReportInterface.receiveBillReport("123", "000000");
		super.assertNotNull(ret);
		super.assertEquals("00000000", ret);
	}

}
