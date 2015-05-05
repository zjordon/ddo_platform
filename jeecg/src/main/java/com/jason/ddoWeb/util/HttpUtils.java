/**
 * 
 */
package com.jason.ddoWeb.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;


/**
 * @author jasonzhang
 *
 */
public final class HttpUtils {

	public final static String post(String httpUrl, Map<String, String> paramMap)
			throws ClientProtocolException, IOException {
		CloseableHttpClient httpclient = HttpClients.createDefault();
		String httpResponse = null;
		// 打包将要传入的参数
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		Iterator<Map.Entry<String, String>> iter = paramMap.entrySet()
				.iterator();
		while (iter.hasNext()) {
			Map.Entry<String, String> entry = iter.next();
			params.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
		}
		HttpPost httppost = new HttpPost(httpUrl);
		httppost.setEntity(new UrlEncodedFormEntity(params, "utf-8"));
		CloseableHttpResponse response = null;
		try {
			// 提交数据
			ResponseHandler<String> rh = new ResponseHandler<String>(){

				@Override
				public String handleResponse(HttpResponse response)
						throws ClientProtocolException, IOException {
					HttpEntity entity = response.getEntity();
					if (entity != null) {
						String responsContent = EntityUtils.toString(entity);
						return responsContent;
					}
					return null;
				}
				
			};
			httpResponse = httpclient.execute(httppost, rh);
		} finally {
			if (response != null) {
				try {
					response.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return httpResponse;
	}
}
