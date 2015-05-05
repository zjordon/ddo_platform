package com.jason.ddoMsg.externalInterface;

import org.apache.commons.codec.digest.DigestUtils;

public class ChannelRequestInterfaceTest extends BaseInterfaceTest {

	private ChannelRequestInterface channelRequestInterface;

	protected void setUp() throws Exception {
		super.setUp();
		channelRequestInterface = ChannelRequestInterface.getInstance();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

//	public void testReceiveRequest() {
//		String testUserPass = DigestUtils.md5Hex("test2");
//		// 以当前月限额剩余为700，日限额为800，连续发送4条价值为200的请求，到第四次发送时嘎然未超过日限额，但超过月限额
//		String ret = this.channelRequestInterface.receiveRequest("test2",
//				testUserPass, "13950077000", "", "100000000005", null, 1);
//		super.assertTrue(ret.startsWith("1"));
//		ret = this.channelRequestInterface.receiveRequest("test2",
//				testUserPass, "13950077001", "", "100000000005", null, 1);
//		super.assertTrue(ret.startsWith("1"));
//		ret = this.channelRequestInterface.receiveRequest("test2",
//				testUserPass, "13950077002", "", "100000000005", null, 1);
//		super.assertTrue(ret.startsWith("1"));
//		ret = this.channelRequestInterface.receiveRequest("test2",
//				testUserPass, "13950077002", "", "100000000005", null, 1);
//		super.assertEquals("4", ret);
//	}

	// public void testReceiveRequestParamInvalid() {
	// // 参数异常:用户名，密码，手机号未填写
	// String ret = this.channelRequestInterface.receiveRequest("", " ",
	// "test", "test", "test", null, 1);
	// super.assertEquals("12", ret);
	// // 参数异常:发送时间格式不正确的情况
	// ret = this.channelRequestInterface.receiveRequest("testaaa", "test",
	// "13950079348", "test", "test", "2015-04-03 111111", 1);
	// super.assertEquals("12", ret);
	// // 参数异常:来自渠道，但产品id未填写
	// ret = this.channelRequestInterface.receiveRequest("testaaa", "test",
	// "13950079348", "test", "", "", 1);
	// super.assertEquals("12", ret);
	// // 参数异常:来自特服号，但指令未填写
	// ret = this.channelRequestInterface.receiveRequest("testaaa", "test",
	// "13950079348", "", "test", "", 1);
	// super.assertEquals("12", ret);
	// }

	// public void testReceiveRequestUserInvalid() {
	// // 用户名不存在的情况
	// String ret = this.channelRequestInterface.receiveRequest("testaaa",
	// "test",
	// "13950079348", "test", "test", null, 1);
	// super.assertEquals("-1", ret);
	// // 密码不正确的情况
	// ret = this.channelRequestInterface.receiveRequest("test", "test",
	// "13950079348", "test", "test", null, 1);
	// super.assertEquals("-1", ret);
	// String testUserPass = DigestUtils.md5Hex("test");
	// // 用户已经被禁用的情况
	// ret = this.channelRequestInterface.receiveRequest("test", testUserPass,
	// "13950079347", "test", "test", null, 1);
	// super.assertEquals("9", ret);
	// }

	// public void testExceedMaxNum() {
	// String testUserPass = DigestUtils.md5Hex("test2");
	// // 号码条数超过2000条的情况
	// StringBuilder builder = new StringBuilder();
	// for (long i = 13950077000l; i < 13950079005l; i++) {
	// builder.append(i).append(",");
	// }
	// String ret = this.channelRequestInterface.receiveRequest("test2",
	// testUserPass, builder.toString(), "test", "test", null, 1);
	// super.assertEquals("10", ret);
	// }

	// public void testProductInvalid() {
	// String testUserPass = DigestUtils.md5Hex("test2");
	// // 产品id不存在的情况
	// String ret = this.channelRequestInterface.receiveRequest("test2",
	// testUserPass, "13950077000, 13950077001", "", "test", null, 1);
	// super.assertEquals("11", ret);
	// }
	//
	// public void testInstructInvalid() {
	// String testUserPass = DigestUtils.md5Hex("test2");
	// // 产品指令不存在的情况
	// String ret = this.channelRequestInterface.receiveRequest("test2",
	// testUserPass, "13950077000, 13950077001", "test", "", null, 2);
	// super.assertEquals("7", ret);
	// }

	public void testExceedMonthLimit() {
		String testUserPass = DigestUtils.md5Hex("test2");
		System.out.println(testUserPass);
		// 超过渠道月限额的情况，以当前月限额剩余700的情况测试
//		String ret = this.channelRequestInterface.receiveRequest("test2",
//				testUserPass,
//				"13950077000, 13950077001, 13950077002, 13950077003", "",
//				"100000000005", null, 1);
//		super.assertEquals("4", ret);
//		ret = this.channelRequestInterface.receiveRequest("test2",
//				testUserPass,
//				"13950077000, 13950077001, 13950077002, 13950077003",
//				"10000000100000000005", "", null, 2);
//		super.assertEquals("4", ret);
	}

	// public void testExceedDayLimit() {
	// String testUserPass = DigestUtils.md5Hex("test2");
	// //超过渠道日限额的情况
	// String ret = this.channelRequestInterface.receiveRequest("test2",
	// testUserPass, "13950077000, 13950077001, 13950077002", "",
	// "100000000005", null, 1);
	// super.assertEquals("4", ret);
	// ret = this.channelRequestInterface.receiveRequest("test2",
	// testUserPass, "13950077000, 13950077001, 13950077002",
	// "10000000100000000005", "", null, 2);
	// super.assertEquals("4", ret);
	// }

}
