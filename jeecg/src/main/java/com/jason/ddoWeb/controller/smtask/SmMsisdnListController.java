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

import com.jason.ddoWeb.entity.smtask.SmMsisdnListEntity;
import com.jason.ddoWeb.service.smtask.SmMsisdnListServiceI;

/**   
 * @Title: Controller
 * @Description: 号码清单
 * @author zhangdaihao
 * @date 2015-04-20 15:03:30
 * @version V1.0   
 *
 */
@Scope("prototype")
@Controller
@RequestMapping("/smMsisdnListController")
public class SmMsisdnListController extends BaseController {
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
		return new ModelAndView("com/jason/ddoWeb/smtask/smMsisdnListList");
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

	/**
	 * 删除号码清单
	 * 
	 * @return
	 */
	@RequestMapping(params = "del")
	@ResponseBody
	public AjaxJson del(SmMsisdnListEntity smMsisdnList, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		smMsisdnList = systemService.getEntity(SmMsisdnListEntity.class, smMsisdnList.getId());
		message = "号码清单删除成功";
		smMsisdnListService.delete(smMsisdnList);
		systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加号码清单
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "save")
	@ResponseBody
	public AjaxJson save(SmMsisdnListEntity smMsisdnList, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		if (StringUtil.isNotEmpty(smMsisdnList.getId())) {
			message = "号码清单更新成功";
			SmMsisdnListEntity t = smMsisdnListService.get(SmMsisdnListEntity.class, smMsisdnList.getId());
			try {
				MyBeanUtils.copyBeanNotNull2Bean(smMsisdnList, t);
				smMsisdnListService.saveOrUpdate(t);
				systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			} catch (Exception e) {
				e.printStackTrace();
				message = "号码清单更新失败";
			}
		} else {
			message = "号码清单添加成功";
			smMsisdnListService.save(smMsisdnList);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 号码清单列表页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "addorupdate")
	public ModelAndView addorupdate(SmMsisdnListEntity smMsisdnList, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(smMsisdnList.getId())) {
			smMsisdnList = smMsisdnListService.getEntity(SmMsisdnListEntity.class, smMsisdnList.getId());
			req.setAttribute("smMsisdnListPage", smMsisdnList);
		}
		return new ModelAndView("com/jason/ddoWeb/smtask/smMsisdnList");
	}
}
