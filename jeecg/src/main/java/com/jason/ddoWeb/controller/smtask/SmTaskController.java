package com.jason.ddoWeb.controller.smtask;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
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

import com.jason.ddoWeb.entity.smtask.SmTaskEntity;
import com.jason.ddoWeb.service.smtask.SmTaskServiceI;

/**   
 * @Title: Controller
 * @Description: 短信任务
 * @author zhangdaihao
 * @date 2015-04-20 13:53:12
 * @version V1.0   
 *
 */
@Scope("prototype")
@Controller
@RequestMapping("/smTaskController")
public class SmTaskController extends BaseController {
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
		return new ModelAndView("com/jason/ddoWeb/smtask/smTaskList");
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
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, smTask, request.getParameterMap());
		this.smTaskService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除短信任务
	 * 
	 * @return
	 */
	@RequestMapping(params = "del")
	@ResponseBody
	public AjaxJson del(SmTaskEntity smTask, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		smTask = systemService.getEntity(SmTaskEntity.class, smTask.getId());
		message = "短信任务删除成功";
		smTaskService.delete(smTask);
		systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		
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

	/**
	 * 短信任务列表页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "addorupdate")
	public ModelAndView addorupdate(SmTaskEntity smTask, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(smTask.getId())) {
			smTask = smTaskService.getEntity(SmTaskEntity.class, smTask.getId());
			req.setAttribute("smTaskPage", smTask);
		}
		return new ModelAndView("com/jason/ddoWeb/smtask/smTask");
	}
	/**
	 * 批量导入号码
	 * @param smTask
	 * @param req
	 * @return
	 */
	@RequestMapping(params = "upload")
	public ModelAndView upload(SmTaskEntity smTask, HttpServletRequest req) {
		return new ModelAndView("com/jason/ddoWeb/smtask/smTaskUpload");
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
}
