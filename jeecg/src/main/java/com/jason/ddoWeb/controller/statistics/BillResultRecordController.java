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

import com.jason.ddoWeb.entity.statistics.BillResultRecordEntity;
import com.jason.ddoWeb.service.statistics.BillResultRecordServiceI;

/**   
 * @Title: Controller
 * @Description: 计费结果
 * @author zhangdaihao
 * @date 2015-04-23 15:10:58
 * @version V1.0   
 *
 */
@Scope("prototype")
@Controller
@RequestMapping("/billResultRecordController")
public class BillResultRecordController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(BillResultRecordController.class);

	@Autowired
	private BillResultRecordServiceI billResultRecordService;
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
	 * 计费结果列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "billResultRecord")
	public ModelAndView billResultRecord(HttpServletRequest request) {
		return new ModelAndView("com/jason/ddoWeb/statistics/billResultRecordList");
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
	public void datagrid(BillResultRecordEntity billResultRecord,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(BillResultRecordEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, billResultRecord, request.getParameterMap());
		this.billResultRecordService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除计费结果
	 * 
	 * @return
	 */
	@RequestMapping(params = "del")
	@ResponseBody
	public AjaxJson del(BillResultRecordEntity billResultRecord, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		billResultRecord = systemService.getEntity(BillResultRecordEntity.class, billResultRecord.getId());
		message = "计费结果删除成功";
		billResultRecordService.delete(billResultRecord);
		systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加计费结果
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "save")
	@ResponseBody
	public AjaxJson save(BillResultRecordEntity billResultRecord, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		if (StringUtil.isNotEmpty(billResultRecord.getId())) {
			message = "计费结果更新成功";
			BillResultRecordEntity t = billResultRecordService.get(BillResultRecordEntity.class, billResultRecord.getId());
			try {
				MyBeanUtils.copyBeanNotNull2Bean(billResultRecord, t);
				billResultRecordService.saveOrUpdate(t);
				systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			} catch (Exception e) {
				e.printStackTrace();
				message = "计费结果更新失败";
			}
		} else {
			message = "计费结果添加成功";
			billResultRecordService.save(billResultRecord);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 计费结果列表页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "addorupdate")
	public ModelAndView addorupdate(BillResultRecordEntity billResultRecord, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(billResultRecord.getId())) {
			billResultRecord = billResultRecordService.getEntity(BillResultRecordEntity.class, billResultRecord.getId());
			req.setAttribute("billResultRecordPage", billResultRecord);
		}
		return new ModelAndView("com/jason/ddoWeb/statistics/billResultRecord");
	}
}
