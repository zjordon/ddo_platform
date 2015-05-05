package com.jason.ddoWeb.controller.statistics;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.constant.Globals;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.jeecgframework.web.system.pojo.base.TSDepart;
import org.jeecgframework.web.system.service.SystemService;
import org.jeecgframework.core.util.MyBeanUtils;

import com.jason.ddoWeb.entity.statistics.SendResultRecordEntity;
import com.jason.ddoWeb.service.statistics.SendResultRecordServiceI;

/**   
 * @Title: Controller
 * @Description: 发送结果
 * @author zhangdaihao
 * @date 2015-04-23 15:10:22
 * @version V1.0   
 *
 */
@Scope("prototype")
@Controller
@RequestMapping("/sendResultRecordController")
public class SendResultRecordController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(SendResultRecordController.class);

	@Autowired
	private SendResultRecordServiceI sendResultRecordService;
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
	 * 发送结果列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "sendResultRecord")
	public ModelAndView sendResultRecord(HttpServletRequest request) {
		return new ModelAndView("com/jason/ddoWeb/statistics/sendResultRecordList");
	}

	/**
	 * easyui AJAX请求数据
	 * 
	 * @param request
	 * @param response
	 * @param dataGrid
	 * @param user
	 */

	@RequestMapping(params = "datagrid")
	public void datagrid(SendResultRecordEntity sendResultRecord,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(SendResultRecordEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, sendResultRecord, request.getParameterMap());
		this.sendResultRecordService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除发送结果
	 * 
	 * @return
	 */
	@RequestMapping(params = "del")
	@ResponseBody
	public AjaxJson del(SendResultRecordEntity sendResultRecord, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		sendResultRecord = systemService.getEntity(SendResultRecordEntity.class, sendResultRecord.getId());
		message = "发送结果删除成功";
		sendResultRecordService.delete(sendResultRecord);
		systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加发送结果
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "save")
	@ResponseBody
	public AjaxJson save(SendResultRecordEntity sendResultRecord, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		if (StringUtil.isNotEmpty(sendResultRecord.getId())) {
			message = "发送结果更新成功";
			SendResultRecordEntity t = sendResultRecordService.get(SendResultRecordEntity.class, sendResultRecord.getId());
			try {
				MyBeanUtils.copyBeanNotNull2Bean(sendResultRecord, t);
				sendResultRecordService.saveOrUpdate(t);
				systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			} catch (Exception e) {
				e.printStackTrace();
				message = "发送结果更新失败";
			}
		} else {
			message = "发送结果添加成功";
			sendResultRecordService.save(sendResultRecord);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 发送结果列表页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "addorupdate")
	public ModelAndView addorupdate(SendResultRecordEntity sendResultRecord, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(sendResultRecord.getId())) {
			sendResultRecord = sendResultRecordService.getEntity(SendResultRecordEntity.class, sendResultRecord.getId());
			req.setAttribute("sendResultRecordPage", sendResultRecord);
		}
		return new ModelAndView("com/jason/ddoWeb/statistics/sendResultRecord");
	}
}
