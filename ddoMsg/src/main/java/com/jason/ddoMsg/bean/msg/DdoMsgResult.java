/**
 * 
 */
package com.jason.ddoMsg.bean.msg;

/**
 * ddo消息返回结果
 * @author jasonzhang
 *
 */
public class DdoMsgResult {

	private int sendResult;
	private String transationId;
	private String returnMsgCode;
	
	public DdoMsgResult() {
		super();
		//默认为发送成功
		this.sendResult = 1;
	}
	public String getTransationId() {
		return transationId;
	}
	public void setTransationId(String transationId) {
		this.transationId = transationId;
	}
	public String getReturnMsgCode() {
		return returnMsgCode;
	}
	public void setReturnMsgCode(String returnMsgCode) {
		this.returnMsgCode = returnMsgCode;
	}
	public int getSendResult() {
		return sendResult;
	}
	public void setSendResult(int sendResult) {
		this.sendResult = sendResult;
	}
	
}
