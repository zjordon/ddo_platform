package com.jason.ddoWeb.controller.front;

import java.io.IOException;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.constant.Globals;
import org.jeecgframework.core.util.ContextHolderUtils;
import org.jeecgframework.core.util.MyBeanUtils;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.jeecgframework.web.system.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.jason.ddoWeb.controller.smtask.SmTaskController;
import com.jason.ddoWeb.entity.channel.ChannelUserEntity;
import com.jason.ddoWeb.entity.smtask.SmTaskEntity;
import com.jason.ddoWeb.service.smtask.SmTaskServiceI;

@Scope("prototype")
@Controller
@RequestMapping("/frontSmTaskController")
public class FrontSmTaskController extends BaseController {
	
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(SmTaskController.class);

	@Autowired
	private SmTaskServiceI smTaskService;
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
	 * 短信任务列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "smTask")
	public ModelAndView smTask(HttpServletRequest request) {
		return new ModelAndView("com/jason/ddoWeb/smtask/frontSmTaskList");
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
	public void datagrid(SmTaskEntity smTask,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(SmTaskEntity.class, dataGrid);
		ChannelUserEntity channelUser = (ChannelUserEntity)ContextHolderUtils.getSession().getAttribute("channelUser");
		if (channelUser != null) {
			//用户只能查看自己渠道的短信任务
			cq.eq("channelId", channelUser.getChannelId());
			cq.add();
			//查询条件组装器
			org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, smTask, request.getParameterMap());
			this.smTaskService.getDataGridReturn(cq, true);
		}
		
		TagUtil.datagrid(response, dataGrid);
	}
	
	/**
	 * 短信任务列表页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "addorupdate")
	public ModelAndView addorupdate(SmTaskEntity smTask, HttpServletRequest req) {
		return new ModelAndView("com/jason/ddoWeb/smtask/frontSmTask");
	}
	/**
	 * 批量导入号码
	 * @param smTask
	 * @param req
	 * @return
	 */
	@RequestMapping(params = "upload")
	public ModelAndView upload(SmTaskEntity smTask, HttpServletRequest req) {
		return new ModelAndView("com/jason/ddoWeb/smtask/frontSmTaskUpload");
	}
	
	/**
	 * 添加短信任务
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "importTxt", method = RequestMethod.POST)
	@ResponseBody
	public AjaxJson importTxt(HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		String taskId = request.getParameter("taskId");
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest)request;
		Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
		for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
			try {
				
				this.smTaskService.saveFile(taskId, entity.getValue().getInputStream());
				message = "短信任务添加成功";
			} catch (IOException e1) {
				e1.printStackTrace();
				message = "短信任务添加失败";
			} finally {
				try {
					entity.getValue().getInputStream().close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 添加短信任务
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "save")
	@ResponseBody
	public AjaxJson save(SmTaskEntity smTask, HttpServletRequest request) {
		//获取当前登录的渠道用户的用户名密码设置到任务表中
		this.setTaskUserAndPass(smTask, request);
		AjaxJson j = new AjaxJson();
		if (StringUtil.isNotEmpty(smTask.getId())) {
			message = "短信任务更新成功";
			SmTaskEntity t = smTaskService.get(SmTaskEntity.class, smTask.getId());
			try {
				MyBeanUtils.copyBeanNotNull2Bean(smTask, t);
				smTaskService.saveOrUpdate(t);
				systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			} catch (Exception e) {
				e.printStackTrace();
				message = "短信任务更新失败";
			}
		} else {
			String msisdnContent = request.getParameter("msisdnContent");
			if (StringUtil.isNotEmpty(msisdnContent)) {
				message = "短信任务添加成功";
				smTaskService.save(smTask, msisdnContent);
				systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
			} else {
				message = "短信任务添加成功";
				smTask.setState(new Integer(0));
				smTaskService.save(smTask);
				j.setObj(smTask);
				systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
			}
			
		}
		j.setMsg(message);
		return j;
	}
	
	private ChannelUserEntity getCurrentLoginUser(HttpServletRequest request) {
		return (ChannelUserEntity)request.getSession().getAttribute("channelUser");
	}
	
	private void setTaskUserAndPass(SmTaskEntity smTask, HttpServletRequest request) {
		//获取当前登录的渠道用户的用户名密码设置到任务表中
		ChannelUserEntity channelUser = (ChannelUserEntity)request.getSession().getAttribute("channelUser");
		if (channelUser != null) {
			smTask.setChannelUserName(channelUser.getUsername());
			smTask.setChannelUserPass(channelUser.getPassword());
			smTask.setCreateDate(new Date());
			smTask.setCreateName(channelUser.getUsername());
		}
	}
}
