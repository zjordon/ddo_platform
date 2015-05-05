package com.jason.ddoWeb.controller.smtask;
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

import com.jason.ddoWeb.entity.smtask.SmRequestEntity;
import com.jason.ddoWeb.service.smtask.SmRequestServiceI;

/**   
 * @Title: Controller
 * @Description: 短信请求
 * @author zhangdaihao
 * @date 2015-04-20 13:41:25
 * @version V1.0   
 *
 */
@Scope("prototype")
@Controller
@RequestMapping("/smRequestController")
public class SmRequestController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(SmRequestController.class);

	@Autowired
	private SmRequestServiceI smRequestService;
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
	 * 短信请求列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "smRequest")
	public ModelAndView smRequest(HttpServletRequest request) {
		return new ModelAndView("com/jason/ddoWeb/smtask/smRequestList");
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
	public void datagrid(SmRequestEntity smRequest,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(SmRequestEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, smRequest, request.getParameterMap());
		this.smRequestService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除短信请求
	 * 
	 * @return
	 */
	@RequestMapping(params = "del")
	@ResponseBody
	public AjaxJson del(SmRequestEntity smRequest, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		smRequest = systemService.getEntity(SmRequestEntity.class, smRequest.getId());
		message = "短信请求删除成功";
		smRequestService.delete(smRequest);
		systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加短信请求
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "save")
	@ResponseBody
	public AjaxJson save(SmRequestEntity smRequest, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		if (StringUtil.isNotEmpty(smRequest.getId())) {
			message = "短信请求更新成功";
			SmRequestEntity t = smRequestService.get(SmRequestEntity.class, smRequest.getId());
			try {
				MyBeanUtils.copyBeanNotNull2Bean(smRequest, t);
				smRequestService.saveOrUpdate(t);
				systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			} catch (Exception e) {
				e.printStackTrace();
				message = "短信请求更新失败";
			}
		} else {
			message = "短信请求添加成功";
			smRequestService.save(smRequest);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 短信请求列表页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "addorupdate")
	public ModelAndView addorupdate(SmRequestEntity smRequest, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(smRequest.getId())) {
			smRequest = smRequestService.getEntity(SmRequestEntity.class, smRequest.getId());
			req.setAttribute("smRequestPage", smRequest);
		}
		return new ModelAndView("com/jason/ddoWeb/smtask/smRequest");
	}
}
