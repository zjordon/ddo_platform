/**
 * 
 */
package com.jason.ddoWeb.common.model;

import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecgframework.poi.excel.annotation.ExcelTarget;

/**
 * @author jasonzhang
 *
 */
@ExcelTarget("errorMsg")
public class ErrorMsg {

	@Excel(name="行号")
	private int num;
	@Excel(name="错误信息")
	private String msg;
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
}
