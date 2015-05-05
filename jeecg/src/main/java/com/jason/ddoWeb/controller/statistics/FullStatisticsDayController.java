package com.jason.ddoWeb.controller.statistics;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.constant.Globals;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.vo.NormalExcelConstants;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.jeecgframework.tag.vo.datatable.SortDirection;
import org.jeecgframework.web.system.pojo.base.TSDepart;
import org.jeecgframework.web.system.service.SystemService;
import org.jeecgframework.core.util.MyBeanUtils;

import com.jason.ddoWeb.common.easypoi.datahandler.DdoMsgHandler;
import com.jason.ddoWeb.entity.statistics.FullStatisticsDayEntity;
import com.jason.ddoWeb.service.statistics.FullStatisticsDayServiceI;

/**   
 * @Title: Controller
 * @Description: 全量统计
 * @author zhangdaihao
 * @date 2015-04-23 15:11:45
 * @version V1.0   
 *
 */
@Scope("prototype")
@Controller
@RequestMapping("/fullStatisticsDayController")
public class FullStatisticsDayController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(FullStatisticsDayController.class);

	@Autowired
	private FullStatisticsDayServiceI fullStatisticsDayService;
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
	 * 全量统计列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "fullStatisticsDay")
	public ModelAndView fullStatisticsDay(HttpServletRequest request) {
		return new ModelAndView("com/jason/ddoWeb/statistics/fullStatisticsDayList");
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
	public void datagrid(FullStatisticsDayEntity fullStatisticsDay,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(FullStatisticsDayEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, fullStatisticsDay, request.getParameterMap());
		this.fullStatisticsDayService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除全量统计
	 * 
	 * @return
	 */
	@RequestMapping(params = "del")
	@ResponseBody
	public AjaxJson del(FullStatisticsDayEntity fullStatisticsDay, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		fullStatisticsDay = systemService.getEntity(FullStatisticsDayEntity.class, fullStatisticsDay.getId());
		message = "全量统计删除成功";
		fullStatisticsDayService.delete(fullStatisticsDay);
		systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加全量统计
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "save")
	@ResponseBody
	public AjaxJson save(FullStatisticsDayEntity fullStatisticsDay, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		if (StringUtil.isNotEmpty(fullStatisticsDay.getId())) {
			message = "全量统计更新成功";
			FullStatisticsDayEntity t = fullStatisticsDayService.get(FullStatisticsDayEntity.class, fullStatisticsDay.getId());
			try {
				MyBeanUtils.copyBeanNotNull2Bean(fullStatisticsDay, t);
				fullStatisticsDayService.saveOrUpdate(t);
				systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			} catch (Exception e) {
				e.printStackTrace();
				message = "全量统计更新失败";
			}
		} else {
			message = "全量统计添加成功";
			fullStatisticsDayService.save(fullStatisticsDay);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 全量统计列表页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "addorupdate")
	public ModelAndView addorupdate(FullStatisticsDayEntity fullStatisticsDay, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(fullStatisticsDay.getId())) {
			fullStatisticsDay = fullStatisticsDayService.getEntity(FullStatisticsDayEntity.class, fullStatisticsDay.getId());
			req.setAttribute("fullStatisticsDayPage", fullStatisticsDay);
		}
		return new ModelAndView("com/jason/ddoWeb/statistics/fullStatisticsDay");
	}
	
	@RequestMapping(params = "exportXls")
	public String exportXls(FullStatisticsDayEntity fullStatisticsDay, HttpServletRequest request,
			HttpServletResponse response, DataGrid dataGrid, ModelMap map) {
		
		CriteriaQuery cq = new CriteriaQuery(FullStatisticsDayEntity.class, dataGrid);
		//设置默认的排序及排序字雄姿英发
		cq.addOrder("sumDate", SortDirection.asc);
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq,
				fullStatisticsDay, request.getParameterMap());
		List<FullStatisticsDayEntity> fullStatisticsDayList = this.fullStatisticsDayService
				.getListByCriteriaQuery(cq, false);

		map.put(NormalExcelConstants.FILE_NAME, "全量统计");
		map.put(NormalExcelConstants.CLASS, FullStatisticsDayEntity.class);
		ExportParams exportParams = new ExportParams();
		
		map.put(NormalExcelConstants.PARAMS, exportParams);
		map.put(NormalExcelConstants.DATA_LIST, fullStatisticsDayList);
		return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}
}
