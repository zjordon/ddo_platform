/**
 * 
 */
package com.jason.ddoWeb.controller.front;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.jeecgframework.web.system.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.jason.ddoWeb.controller.smtask.SmMsisdnListController;
import com.jason.ddoWeb.entity.smtask.SmMsisdnListEntity;
import com.jason.ddoWeb.service.smtask.SmMsisdnListServiceI;

/**
 * @author jasonzhang
 *
 */
@Scope("prototype")
@Controller
@RequestMapping("/frontSmMsisdnListController")
public class FrontSmMsisdnListController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(SmMsisdnListController.class);

	@Autowired
	private SmMsisdnListServiceI smMsisdnListService;
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
	 * 号码清单列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "smMsisdnList")
	public ModelAndView smMsisdnList(HttpServletRequest request) {
		String taskId = request.getParameter("taskId");
		if (StringUtil.isNotEmpty(taskId)) {
			request.setAttribute("taskId", taskId);
		}
		return new ModelAndView("com/jason/ddoWeb/smtask/frontSmMsisdnListList");
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
	public void datagrid(SmMsisdnListEntity smMsisdnList,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(SmMsisdnListEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, smMsisdnList, request.getParameterMap());
		this.smMsisdnListService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}
}
