/**
 * 
 */
package com.jason.ddoMsg.util;

import java.io.IOException;
//import java.io.InputStreamReader;
//import java.io.Reader;
import java.net.URISyntaxException;
//import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
//import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;

import com.jason.ddoMsg.bean.msg.DeliverResponse;

/**
 * @author jasonzhang
 *
 */
public class HttpHelper {

	private final static HttpHelper instance = new HttpHelper();

	//private CloseableHttpClient httpclient;

	private HttpHelper() {
		//httpclient = HttpClients.createDefault();
	}

	public final static HttpHelper getInstnace() {
		return instance;
	}

	public DeliverResponse post(String httpUrl, Map<String, String> paramMap, int timeout)
			throws ClientProtocolException, IOException {
		DeliverResponse deliverResponse = null;
		// 打包将要传入的参数
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		Iterator<Map.Entry<String, String>> iter = paramMap.entrySet()
				.iterator();
		while (iter.hasNext()) {
			Map.Entry<String, String> entry = iter.next();
			params.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
		}
		HttpPost httppost = new HttpPost(httpUrl);
		//设置超时时间为3秒
		RequestConfig requestConfig = RequestConfig.custom().setConnectionRequestTimeout(timeout).setConnectTimeout(timeout).setSocketTimeout(timeout).build();
		httppost.setConfig(requestConfig);
		httppost.setEntity(new UrlEncodedFormEntity(params, "utf-8"));
		CloseableHttpResponse response = null;
		try {
			// 提交数据
			ResponseHandler<DeliverResponse> rh = new DeliverResponseHandler();
			CloseableHttpClient httpclient= HttpClients.createDefault();
			deliverResponse = httpclient.execute(httppost, rh);
			httpclient.close();
		} finally {
			if (response != null) {
				try {
					response.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return deliverResponse;
	}

	public DeliverResponse get(String httpUrl, Map<String, String> paramMap, int timeout)
			throws URISyntaxException, ClientProtocolException, IOException {
		DeliverResponse deliverResponse = null;
		URIBuilder urlBuilder = new URIBuilder(httpUrl);
		Iterator<Map.Entry<String, String>> iter = paramMap.entrySet()
				.iterator();
		while (iter.hasNext()) {
			Map.Entry<String, String> entry = iter.next();
			urlBuilder.setParameter(entry.getKey(), entry.getValue());
		}
		HttpGet httpget = new HttpGet(urlBuilder.build());
		//设置超时时间为3秒
		RequestConfig requestConfig = RequestConfig.custom().setConnectionRequestTimeout(timeout).setConnectTimeout(timeout).setSocketTimeout(timeout).build();
		httpget.setConfig(requestConfig);
		CloseableHttpResponse response = null;
		try {
			// 提交数据
			ResponseHandler<DeliverResponse> rh = new DeliverResponseHandler();
			CloseableHttpClient httpclient= HttpClients.createDefault();
			deliverResponse = httpclient.execute(httpget, rh);
			httpclient.close();
		} finally {
			if (response != null) {
				try {
					response.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return deliverResponse;
	}
	
	
}
