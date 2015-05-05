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

import com.jason.ddoWeb.entity.msg.NeedUpChannelEntity;
import com.jason.ddoWeb.service.msg.NeedUpChannelServiceI;

/**   
 * @Title: Controller
 * @Description: 需要重新上行的记录
 * @author zhangdaihao
 * @date 2015-03-30 09:07:42
 * @version V1.0   
 *
 */
@Scope("prototype")
@Controller
@RequestMapping("/needUpChannelController")
public class NeedUpChannelController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(NeedUpChannelController.class);

	@Autowired
	private NeedUpChannelServiceI needUpChannelService;
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
	 * 需要重新上行的记录列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "needUpChannel")
	public ModelAndView needUpChannel(HttpServletRequest request) {
		return new ModelAndView("com/jason/ddoWeb/msg/needUpChannelList");
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
	public void datagrid(NeedUpChannelEntity needUpChannel,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(NeedUpChannelEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, needUpChannel, request.getParameterMap());
		this.needUpChannelService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除需要重新上行的记录
	 * 
	 * @return
	 */
	@RequestMapping(params = "del")
	@ResponseBody
	public AjaxJson del(NeedUpChannelEntity needUpChannel, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		needUpChannel = systemService.getEntity(NeedUpChannelEntity.class, needUpChannel.getId());
		message = "需要重新上行的记录删除成功";
		needUpChannelService.delete(needUpChannel);
		systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加需要重新上行的记录
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "save")
	@ResponseBody
	public AjaxJson save(NeedUpChannelEntity needUpChannel, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		if (StringUtil.isNotEmpty(needUpChannel.getId())) {
			message = "需要重新上行的记录更新成功";
			NeedUpChannelEntity t = needUpChannelService.get(NeedUpChannelEntity.class, needUpChannel.getId());
			try {
				MyBeanUtils.copyBeanNotNull2Bean(needUpChannel, t);
				needUpChannelService.saveOrUpdate(t);
				systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			} catch (Exception e) {
				e.printStackTrace();
				message = "需要重新上行的记录更新失败";
			}
		} else {
			message = "需要重新上行的记录添加成功";
			needUpChannelService.save(needUpChannel);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 需要重新上行的记录列表页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "addorupdate")
	public ModelAndView addorupdate(NeedUpChannelEntity needUpChannel, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(needUpChannel.getId())) {
			needUpChannel = needUpChannelService.getEntity(NeedUpChannelEntity.class, needUpChannel.getId());
			req.setAttribute("needUpChannelPage", needUpChannel);
		}
		return new ModelAndView("com/jason/ddoWeb/msg/needUpChannel");
	}
}
