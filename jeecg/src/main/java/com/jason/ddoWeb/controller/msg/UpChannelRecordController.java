package com.jason.ddoWeb.controller.msg;
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

import com.jason.ddoWeb.entity.msg.UpChannelRecordEntity;
import com.jason.ddoWeb.service.msg.UpChannelRecordServiceI;

/**   
 * @Title: Controller
 * @Description: 上行渠道记录
 * @author zhangdaihao
 * @date 2015-03-30 09:06:52
 * @version V1.0   
 *
 */
@Scope("prototype")
@Controller
@RequestMapping("/upChannelRecordController")
public class UpChannelRecordController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(UpChannelRecordController.class);

	@Autowired
	private UpChannelRecordServiceI upChannelRecordService;
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
	 * 上行渠道记录列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "upChannelRecord")
	public ModelAndView upChannelRecord(HttpServletRequest request) {
		return new ModelAndView("com/jason/ddoWeb/msg/upChannelRecordList");
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
	public void datagrid(UpChannelRecordEntity upChannelRecord,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(UpChannelRecordEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, upChannelRecord, request.getParameterMap());
		this.upChannelRecordService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除上行渠道记录
	 * 
	 * @return
	 */
	@RequestMapping(params = "del")
	@ResponseBody
	public AjaxJson del(UpChannelRecordEntity upChannelRecord, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		upChannelRecord = systemService.getEntity(UpChannelRecordEntity.class, upChannelRecord.getId());
		message = "上行渠道记录删除成功";
		upChannelRecordService.delete(upChannelRecord);
		systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加上行渠道记录
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "save")
	@ResponseBody
	public AjaxJson save(UpChannelRecordEntity upChannelRecord, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		if (StringUtil.isNotEmpty(upChannelRecord.getId())) {
			message = "上行渠道记录更新成功";
			UpChannelRecordEntity t = upChannelRecordService.get(UpChannelRecordEntity.class, upChannelRecord.getId());
			try {
				MyBeanUtils.copyBeanNotNull2Bean(upChannelRecord, t);
				upChannelRecordService.saveOrUpdate(t);
				systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			} catch (Exception e) {
				e.printStackTrace();
				message = "上行渠道记录更新失败";
			}
		} else {
			message = "上行渠道记录添加成功";
			upChannelRecordService.save(upChannelRecord);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 上行渠道记录列表页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "addorupdate")
	public ModelAndView addorupdate(UpChannelRecordEntity upChannelRecord, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(upChannelRecord.getId())) {
			upChannelRecord = upChannelRecordService.getEntity(UpChannelRecordEntity.class, upChannelRecord.getId());
			req.setAttribute("upChannelRecordPage", upChannelRecord);
		}
		return new ModelAndView("com/jason/ddoWeb/msg/upChannelRecord");
	}
}
