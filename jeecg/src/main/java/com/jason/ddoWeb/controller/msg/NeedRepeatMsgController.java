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

import com.jason.ddoWeb.entity.msg.NeedRepeatMsgEntity;
import com.jason.ddoWeb.service.msg.NeedRepeatMsgServiceI;

/**   
 * @Title: Controller
 * @Description: 需要重发的消息
 * @author zhangdaihao
 * @date 2015-03-30 09:04:47
 * @version V1.0   
 *
 */
@Scope("prototype")
@Controller
@RequestMapping("/needRepeatMsgController")
public class NeedRepeatMsgController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(NeedRepeatMsgController.class);

	@Autowired
	private NeedRepeatMsgServiceI needRepeatMsgService;
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
	 * 需要重发的消息列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "needRepeatMsg")
	public ModelAndView needRepeatMsg(HttpServletRequest request) {
		return new ModelAndView("com/jason/ddoWeb/msg/needRepeatMsgList");
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
	public void datagrid(NeedRepeatMsgEntity needRepeatMsg,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(NeedRepeatMsgEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, needRepeatMsg, request.getParameterMap());
		this.needRepeatMsgService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除需要重发的消息
	 * 
	 * @return
	 */
	@RequestMapping(params = "del")
	@ResponseBody
	public AjaxJson del(NeedRepeatMsgEntity needRepeatMsg, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		needRepeatMsg = systemService.getEntity(NeedRepeatMsgEntity.class, needRepeatMsg.getId());
		message = "需要重发的消息删除成功";
		needRepeatMsgService.delete(needRepeatMsg);
		systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加需要重发的消息
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "save")
	@ResponseBody
	public AjaxJson save(NeedRepeatMsgEntity needRepeatMsg, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		if (StringUtil.isNotEmpty(needRepeatMsg.getId())) {
			message = "需要重发的消息更新成功";
			NeedRepeatMsgEntity t = needRepeatMsgService.get(NeedRepeatMsgEntity.class, needRepeatMsg.getId());
			try {
				MyBeanUtils.copyBeanNotNull2Bean(needRepeatMsg, t);
				needRepeatMsgService.saveOrUpdate(t);
				systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			} catch (Exception e) {
				e.printStackTrace();
				message = "需要重发的消息更新失败";
			}
		} else {
			message = "需要重发的消息添加成功";
			needRepeatMsgService.save(needRepeatMsg);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 需要重发的消息列表页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "addorupdate")
	public ModelAndView addorupdate(NeedRepeatMsgEntity needRepeatMsg, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(needRepeatMsg.getId())) {
			needRepeatMsg = needRepeatMsgService.getEntity(NeedRepeatMsgEntity.class, needRepeatMsg.getId());
			req.setAttribute("needRepeatMsgPage", needRepeatMsg);
		}
		return new ModelAndView("com/jason/ddoWeb/msg/needRepeatMsg");
	}
}
