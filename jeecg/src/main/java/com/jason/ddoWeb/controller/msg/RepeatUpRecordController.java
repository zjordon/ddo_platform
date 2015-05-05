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

import com.jason.ddoWeb.entity.msg.RepeatUpRecordEntity;
import com.jason.ddoWeb.service.msg.RepeatUpRecordServiceI;

/**   
 * @Title: Controller
 * @Description: 重新上行记录
 * @author zhangdaihao
 * @date 2015-03-30 09:08:27
 * @version V1.0   
 *
 */
@Scope("prototype")
@Controller
@RequestMapping("/repeatUpRecordController")
public class RepeatUpRecordController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(RepeatUpRecordController.class);

	@Autowired
	private RepeatUpRecordServiceI repeatUpRecordService;
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
	 * 重新上行记录列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "repeatUpRecord")
	public ModelAndView repeatUpRecord(HttpServletRequest request) {
		return new ModelAndView("com/jason/ddoWeb/msg/repeatUpRecordList");
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
	public void datagrid(RepeatUpRecordEntity repeatUpRecord,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(RepeatUpRecordEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, repeatUpRecord, request.getParameterMap());
		this.repeatUpRecordService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除重新上行记录
	 * 
	 * @return
	 */
	@RequestMapping(params = "del")
	@ResponseBody
	public AjaxJson del(RepeatUpRecordEntity repeatUpRecord, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		repeatUpRecord = systemService.getEntity(RepeatUpRecordEntity.class, repeatUpRecord.getId());
		message = "重新上行记录删除成功";
		repeatUpRecordService.delete(repeatUpRecord);
		systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加重新上行记录
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "save")
	@ResponseBody
	public AjaxJson save(RepeatUpRecordEntity repeatUpRecord, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		if (StringUtil.isNotEmpty(repeatUpRecord.getId())) {
			message = "重新上行记录更新成功";
			RepeatUpRecordEntity t = repeatUpRecordService.get(RepeatUpRecordEntity.class, repeatUpRecord.getId());
			try {
				MyBeanUtils.copyBeanNotNull2Bean(repeatUpRecord, t);
				repeatUpRecordService.saveOrUpdate(t);
				systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			} catch (Exception e) {
				e.printStackTrace();
				message = "重新上行记录更新失败";
			}
		} else {
			message = "重新上行记录添加成功";
			repeatUpRecordService.save(repeatUpRecord);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 重新上行记录列表页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "addorupdate")
	public ModelAndView addorupdate(RepeatUpRecordEntity repeatUpRecord, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(repeatUpRecord.getId())) {
			repeatUpRecord = repeatUpRecordService.getEntity(RepeatUpRecordEntity.class, repeatUpRecord.getId());
			req.setAttribute("repeatUpRecordPage", repeatUpRecord);
		}
		return new ModelAndView("com/jason/ddoWeb/msg/repeatUpRecord");
	}
}
