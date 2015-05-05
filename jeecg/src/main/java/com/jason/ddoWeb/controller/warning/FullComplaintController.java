package com.jason.ddoWeb.controller.warning;
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

import com.jason.ddoWeb.entity.warning.FullComplaintEntity;
import com.jason.ddoWeb.service.warning.FullComplaintServiceI;

/**   
 * @Title: Controller
 * @Description: 全量预警
 * @author zhangdaihao
 * @date 2015-05-04 11:38:13
 * @version V1.0   
 *
 */
@Scope("prototype")
@Controller
@RequestMapping("/fullComplaintController")
public class FullComplaintController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(FullComplaintController.class);

	@Autowired
	private FullComplaintServiceI fullComplaintService;
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
	 * 全量预警列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "fullComplaint")
	public ModelAndView fullComplaint(HttpServletRequest request) {
		return new ModelAndView("com/jason/ddoWeb/warning/fullComplaintList");
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
	public void datagrid(FullComplaintEntity fullComplaint,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(FullComplaintEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, fullComplaint, request.getParameterMap());
		this.fullComplaintService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除全量预警
	 * 
	 * @return
	 */
	@RequestMapping(params = "del")
	@ResponseBody
	public AjaxJson del(FullComplaintEntity fullComplaint, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		fullComplaint = systemService.getEntity(FullComplaintEntity.class, fullComplaint.getId());
		message = "全量预警删除成功";
		fullComplaintService.delete(fullComplaint);
		systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加全量预警
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "save")
	@ResponseBody
	public AjaxJson save(FullComplaintEntity fullComplaint, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		if (StringUtil.isNotEmpty(fullComplaint.getId())) {
			message = "全量预警更新成功";
			FullComplaintEntity t = fullComplaintService.get(FullComplaintEntity.class, fullComplaint.getId());
			try {
				MyBeanUtils.copyBeanNotNull2Bean(fullComplaint, t);
				fullComplaintService.saveOrUpdate(t);
				systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			} catch (Exception e) {
				e.printStackTrace();
				message = "全量预警更新失败";
			}
		} else {
			message = "全量预警添加成功";
			fullComplaintService.save(fullComplaint);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 全量预警列表页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "addorupdate")
	public ModelAndView addorupdate(FullComplaintEntity fullComplaint, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(fullComplaint.getId())) {
			fullComplaint = fullComplaintService.getEntity(FullComplaintEntity.class, fullComplaint.getId());
			req.setAttribute("fullComplaintPage", fullComplaint);
		}
		return new ModelAndView("com/jason/ddoWeb/warning/fullComplaint");
	}
}
