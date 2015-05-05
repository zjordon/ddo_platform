package com.jason.ddoWeb.controller.channel;
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

import com.jason.ddoWeb.entity.channel.ProviceCloseStateEntity;
import com.jason.ddoWeb.service.channel.ProviceCloseStateServiceI;

/**   
 * @Title: Controller
 * @Description: 省份关停状态
 * @author zhangdaihao
 * @date 2015-05-04 15:17:37
 * @version V1.0   
 *
 */
@Scope("prototype")
@Controller
@RequestMapping("/proviceCloseStateController")
public class ProviceCloseStateController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(ProviceCloseStateController.class);

	@Autowired
	private ProviceCloseStateServiceI proviceCloseStateService;
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
	 * 省份关停状态列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "proviceCloseState")
	public ModelAndView proviceCloseState(HttpServletRequest request) {
		return new ModelAndView("com/jason/ddoWeb/channel/proviceCloseStateList");
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
	public void datagrid(ProviceCloseStateEntity proviceCloseState,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(ProviceCloseStateEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, proviceCloseState, request.getParameterMap());
		this.proviceCloseStateService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除省份关停状态
	 * 
	 * @return
	 */
	@RequestMapping(params = "del")
	@ResponseBody
	public AjaxJson del(ProviceCloseStateEntity proviceCloseState, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		proviceCloseState = systemService.getEntity(ProviceCloseStateEntity.class, proviceCloseState.getId());
		message = "省份关停状态删除成功";
		proviceCloseStateService.delete(proviceCloseState);
		systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 删除省份关停状态
	 * 
	 * @return
	 */
	@RequestMapping(params = "updateCloseState")
	@ResponseBody
	public AjaxJson updateCloseState(ProviceCloseStateEntity proviceCloseState, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		proviceCloseState = systemService.getEntity(ProviceCloseStateEntity.class, proviceCloseState.getId());
		if (proviceCloseState.getCloseState().intValue() == 1) {
			proviceCloseState.setCloseState(new Integer(0));
		} else {
			proviceCloseState.setCloseState(new Integer(1));
		}
		message = "省份关停状态修改成功";
		proviceCloseStateService.updateEntitie(proviceCloseState);
		systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加省份关停状态
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "save")
	@ResponseBody
	public AjaxJson save(ProviceCloseStateEntity proviceCloseState, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		if (StringUtil.isNotEmpty(proviceCloseState.getId())) {
			message = "省份关停状态更新成功";
			ProviceCloseStateEntity t = proviceCloseStateService.get(ProviceCloseStateEntity.class, proviceCloseState.getId());
			try {
				MyBeanUtils.copyBeanNotNull2Bean(proviceCloseState, t);
				proviceCloseStateService.saveOrUpdate(t);
				systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			} catch (Exception e) {
				e.printStackTrace();
				message = "省份关停状态更新失败";
			}
		} else {
			message = "省份关停状态添加成功";
			proviceCloseStateService.save(proviceCloseState);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 省份关停状态列表页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "addorupdate")
	public ModelAndView addorupdate(ProviceCloseStateEntity proviceCloseState, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(proviceCloseState.getId())) {
			proviceCloseState = proviceCloseStateService.getEntity(ProviceCloseStateEntity.class, proviceCloseState.getId());
			req.setAttribute("proviceCloseStatePage", proviceCloseState);
		}
		return new ModelAndView("com/jason/ddoWeb/channel/proviceCloseState");
	}
}
