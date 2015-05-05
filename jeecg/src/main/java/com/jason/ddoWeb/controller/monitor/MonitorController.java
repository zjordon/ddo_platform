/**
 * 
 */
package com.jason.ddoWeb.controller.monitor;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.http.client.ClientProtocolException;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.constant.Globals;
import org.jeecgframework.core.util.ResourceUtil;
import org.jeecgframework.web.system.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.jason.ddoWeb.util.HttpUtils;

/**
 * @author jasonzhang
 *
 */
@Scope("prototype")
@Controller
@RequestMapping("/monitorController")
public class MonitorController {

	@Autowired
	private SystemService systemService;
	private String message;
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	/**
	 * 黑名单管理列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "monitor")
	public ModelAndView monitor(HttpServletRequest request) {
		return new ModelAndView("com/jason/ddoWeb/monitor/monitor");
	}
	
	/**
	 * 查看收发引擎的运行状态，包括各个队列中的剩余数据及引擎的运行情况
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "ddoMsgStatus")
	@ResponseBody
	public AjaxJson ddoMsgStatus(HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		String postUrl = ResourceUtil.getConfigByName("ddoMsg.url") + ResourceUtil.getConfigByName("ddoMsg.monitorPath");
		Map<String, String> paramMap = new HashMap<String, String>();
		String resp = null;
		try {
			resp = HttpUtils.post(postUrl, paramMap);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (resp != null) {
			j.setMsg(resp);
		}
		return j;
	}
	
	/**
	 * 停止或启动收发引擎
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "controlDdoMsg")
	@ResponseBody
	public AjaxJson controlDdoMsg(HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		String postUrl = ResourceUtil.getConfigByName("ddoMsg.url") + ResourceUtil.getConfigByName("ddoMsg.stopServicePath");
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("command", "control");
		String resp = null;
		try {
			resp = HttpUtils.post(postUrl, paramMap);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (resp != null) {
			j.setMsg(resp);
		}
		message = "停止/启动ddo收发引擎成功";
		systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		return j;
	}
}
