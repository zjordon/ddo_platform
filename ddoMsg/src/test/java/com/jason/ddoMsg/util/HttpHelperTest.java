package com.jason.ddoMsg.util;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.client.ClientProtocolException;

import com.jason.ddoMsg.bean.msg.DeliverResponse;

import junit.framework.TestCase;

public class HttpHelperTest extends TestCase {

	private HttpHelper httpHelper;
	protected void setUp() throws Exception {
		super.setUp();
		this.httpHelper = HttpHelper.getInstnace();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testGet() {
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("msgid", "ff8080814db79622014dbbf462ed668f");
		paramMap.put("mobile", "18711243522");
		paramMap.put("mobile", "1005");
		paramMap.put("status", "0");
		DeliverResponse res = null;
		try {
			res = this.httpHelper.get("http://118.144.79.162:50005/ddo_Mo.php", paramMap, 1000);
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (res != null) {
			System.out.println(res.getMsg());
		}
	}
	
//	public void testPost() {
//		Map<String, String> paramMap = new HashMap<String, String>();
//		paramMap.put("msgid", "ff8080814db79622014dbbf462ed668f");
//		paramMap.put("mobile", "18711243522");
//		paramMap.put("status", "0");
//		DeliverResponse res = null;
//		try {
//			res = this.httpHelper.post("http://118.144.79.162:50005/ddo_Mo.php", paramMap);
//		} catch (ClientProtocolException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		System.out.println(res.getMsg());
//	}

}
