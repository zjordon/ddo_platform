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

import com.jason.ddoWeb.entity.msg.RepeatMsgRecordEntity;
import com.jason.ddoWeb.service.msg.RepeatMsgRecordServiceI;

/**   
 * @Title: Controller
 * @Description: 消息重发记录
 * @author zhangdaihao
 * @date 2015-04-14 14:21:22
 * @version V1.0   
 *
 */
@Scope("prototype")
@Controller
@RequestMapping("/repeatMsgRecordController")
public class RepeatMsgRecordController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(RepeatMsgRecordController.class);

	@Autowired
	private RepeatMsgRecordServiceI repeatMsgRecordService;
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
	 * 消息重发记录列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "repeatMsgRecord")
	public ModelAndView repeatMsgRecord(HttpServletRequest request) {
		return new ModelAndView("com/jason/ddoWeb/msg/repeatMsgRecordList");
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
	public void datagrid(RepeatMsgRecordEntity repeatMsgRecord,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(RepeatMsgRecordEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, repeatMsgRecord, request.getParameterMap());
		this.repeatMsgRecordService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除消息重发记录
	 * 
	 * @return
	 */
	@RequestMapping(params = "del")
	@ResponseBody
	public AjaxJson del(RepeatMsgRecordEntity repeatMsgRecord, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		repeatMsgRecord = systemService.getEntity(RepeatMsgRecordEntity.class, repeatMsgRecord.getId());
		message = "消息重发记录删除成功";
		repeatMsgRecordService.delete(repeatMsgRecord);
		systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加消息重发记录
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "save")
	@ResponseBody
	public AjaxJson save(RepeatMsgRecordEntity repeatMsgRecord, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		if (StringUtil.isNotEmpty(repeatMsgRecord.getId())) {
			message = "消息重发记录更新成功";
			RepeatMsgRecordEntity t = repeatMsgRecordService.get(RepeatMsgRecordEntity.class, repeatMsgRecord.getId());
			try {
				MyBeanUtils.copyBeanNotNull2Bean(repeatMsgRecord, t);
				repeatMsgRecordService.saveOrUpdate(t);
				systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			} catch (Exception e) {
				e.printStackTrace();
				message = "消息重发记录更新失败";
			}
		} else {
			message = "消息重发记录添加成功";
			repeatMsgRecordService.save(repeatMsgRecord);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 消息重发记录列表页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "addorupdate")
	public ModelAndView addorupdate(RepeatMsgRecordEntity repeatMsgRecord, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(repeatMsgRecord.getId())) {
			repeatMsgRecord = repeatMsgRecordService.getEntity(RepeatMsgRecordEntity.class, repeatMsgRecord.getId());
			req.setAttribute("repeatMsgRecordPage", repeatMsgRecord);
		}
		return new ModelAndView("com/jason/ddoWeb/msg/repeatMsgRecord");
	}
}
