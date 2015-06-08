/**
 * 
 */
package com.jason.ddoMsg.bean.msg;

/**
 * @author jasonzhang
 *
 */
public class UpChannel {

	private DdoMsg ddoMsg;
	private String instruct;
	private String upUrl;
	
	public UpChannel(DdoMsg ddoMsg, String instruct, String upUrl) {
		super();
		this.ddoMsg = ddoMsg;
		this.instruct = instruct;
		this.upUrl = upUrl;
	}
	public DdoMsg getDdoMsg() {
		return ddoMsg;
	}
	public void setDdoMsg(DdoMsg ddoMsg) {
		this.ddoMsg = ddoMsg;
	}
	public String getInstruct() {
		return instruct;
	}
	public void setInstruct(String instruct) {
		this.instruct = instruct;
	}
	public String getUpUrl() {
		return upUrl;
	}
	public void setUpUrl(String upUrl) {
		this.upUrl = upUrl;
	}
}
