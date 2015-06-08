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

import com.jason.ddoWeb.entity.warning.ChannelComplaintMonthEntity;
import com.jason.ddoWeb.service.warning.ChannelComplaintMonthServiceI;

/**   
 * @Title: Controller
 * @Description: 全量投诉按月
 * @author zhangdaihao
 * @date 2015-05-08 16:46:17
 * @version V1.0   
 *
 */
@Scope("prototype")
@Controller
@RequestMapping("/channelComplaintMonthController")
public class ChannelComplaintMonthController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(ChannelComplaintMonthController.class);

	@Autowired
	private ChannelComplaintMonthServiceI channelComplaintMonthService;
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
	 * 全量投诉按月列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "channelComplaintMonth")
	public ModelAndView channelComplaintMonth(HttpServletRequest request) {
		return new ModelAndView("com/jason/ddoWeb/warning/channelComplaintMonthList");
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
	public void datagrid(ChannelComplaintMonthEntity channelComplaintMonth,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(ChannelComplaintMonthEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, channelComplaintMonth, request.getParameterMap());
		this.channelComplaintMonthService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除全量投诉按月
	 * 
	 * @return
	 */
	@RequestMapping(params = "del")
	@ResponseBody
	public AjaxJson del(ChannelComplaintMonthEntity channelComplaintMonth, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		channelComplaintMonth = systemService.getEntity(ChannelComplaintMonthEntity.class, channelComplaintMonth.getId());
		message = "全量投诉按月删除成功";
		channelComplaintMonthService.delete(channelComplaintMonth);
		systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加全量投诉按月
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "save")
	@ResponseBody
	public AjaxJson save(ChannelComplaintMonthEntity channelComplaintMonth, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		if (StringUtil.isNotEmpty(channelComplaintMonth.getId())) {
			message = "全量投诉按月更新成功";
			ChannelComplaintMonthEntity t = channelComplaintMonthService.get(ChannelComplaintMonthEntity.class, channelComplaintMonth.getId());
			try {
				MyBeanUtils.copyBeanNotNull2Bean(channelComplaintMonth, t);
				channelComplaintMonthService.saveOrUpdate(t);
				systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			} catch (Exception e) {
				e.printStackTrace();
				message = "全量投诉按月更新失败";
			}
		} else {
			message = "全量投诉按月添加成功";
			channelComplaintMonthService.save(channelComplaintMonth);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 全量投诉按月列表页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "addorupdate")
	public ModelAndView addorupdate(ChannelComplaintMonthEntity channelComplaintMonth, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(channelComplaintMonth.getId())) {
			channelComplaintMonth = channelComplaintMonthService.getEntity(ChannelComplaintMonthEntity.class, channelComplaintMonth.getId());
		}
		req.setAttribute("channelComplaintMonthPage", channelComplaintMonth);
		return new ModelAndView("com/jason/ddoWeb/warning/channelComplaintMonth");
	}
	
	@RequestMapping(params = "editNumThreshold")
	public ModelAndView editNumThreshold(ChannelComplaintMonthEntity channelComplaintMonth, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(channelComplaintMonth.getId())) {
			channelComplaintMonth = channelComplaintMonthService.getEntity(ChannelComplaintMonthEntity.class, channelComplaintMonth.getId());
		}
		req.setAttribute("channelComplaintMonthPage", channelComplaintMonth);
		return new ModelAndView("com/jason/ddoWeb/warning/channelComplaintMonthENT");
	}
	
	@RequestMapping(params = "editRatioThreshold")
	public ModelAndView editRatioThreshold(ChannelComplaintMonthEntity channelComplaintMonth, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(channelComplaintMonth.getId())) {
			channelComplaintMonth = channelComplaintMonthService.getEntity(ChannelComplaintMonthEntity.class, channelComplaintMonth.getId());
		}
		req.setAttribute("channelComplaintMonthPage", channelComplaintMonth);
		return new ModelAndView("com/jason/ddoWeb/warning/channelComplaintMonthERT");
	}
}
