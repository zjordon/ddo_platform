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

import com.jason.ddoWeb.entity.statistics.SendRecordEntity;
import com.jason.ddoWeb.service.statistics.SendRecordServiceI;

/**   
 * @Title: Controller
 * @Description: 发送记录
 * @author zhangdaihao
 * @date 2015-04-23 15:09:38
 * @version V1.0   
 *
 */
@Scope("prototype")
@Controller
@RequestMapping("/sendRecordController")
public class SendRecordController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(SendRecordController.class);

	@Autowired
	private SendRecordServiceI sendRecordService;
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
	 * 发送记录列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "sendRecord")
	public ModelAndView sendRecord(HttpServletRequest request) {
		return new ModelAndView("com/jason/ddoWeb/statistics/sendRecordList");
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
	public void datagrid(SendRecordEntity sendRecord,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(SendRecordEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, sendRecord, request.getParameterMap());
		this.sendRecordService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除发送记录
	 * 
	 * @return
	 */
	@RequestMapping(params = "del")
	@ResponseBody
	public AjaxJson del(SendRecordEntity sendRecord, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		sendRecord = systemService.getEntity(SendRecordEntity.class, sendRecord.getId());
		message = "发送记录删除成功";
		sendRecordService.delete(sendRecord);
		systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加发送记录
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "save")
	@ResponseBody
	public AjaxJson save(SendRecordEntity sendRecord, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		if (StringUtil.isNotEmpty(sendRecord.getId())) {
			message = "发送记录更新成功";
			SendRecordEntity t = sendRecordService.get(SendRecordEntity.class, sendRecord.getId());
			try {
				MyBeanUtils.copyBeanNotNull2Bean(sendRecord, t);
				sendRecordService.saveOrUpdate(t);
				systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			} catch (Exception e) {
				e.printStackTrace();
				message = "发送记录更新失败";
			}
		} else {
			message = "发送记录添加成功";
			sendRecordService.save(sendRecord);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 发送记录列表页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "addorupdate")
	public ModelAndView addorupdate(SendRecordEntity sendRecord, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(sendRecord.getId())) {
			sendRecord = sendRecordService.getEntity(SendRecordEntity.class, sendRecord.getId());
			req.setAttribute("sendRecordPage", sendRecord);
		}
		return new ModelAndView("com/jason/ddoWeb/statistics/sendRecord");
	}
}
