/**
 * 
 */
package com.jason.ddoMsg.util;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.util.EntityUtils;

import com.jason.ddoMsg.bean.msg.DeliverResponse;

/**
 * @author jasonzhang
 *
 */
public class DeliverResponseHandler implements ResponseHandler<DeliverResponse> {

	@Override
	public DeliverResponse handleResponse(HttpResponse response) throws ClientProtocolException,
			IOException {
		DeliverResponse deliverResponse = new DeliverResponse();
		deliverResponse.setStatusCode(response.getStatusLine()
				.getStatusCode());
		HttpEntity entity = response.getEntity();
		if (entity != null) {
			// ContentType contentType =
			// ContentType.getOrDefault(entity);
			// Charset charset = contentType.getCharset();
			// Reader reader = new
			// InputStreamReader(entity.getContent(), charset);
			// reader.close();
			String responsContent = EntityUtils.toString(entity);
			deliverResponse.setMsg(responsContent);
		}

		return deliverResponse;
	}

}
