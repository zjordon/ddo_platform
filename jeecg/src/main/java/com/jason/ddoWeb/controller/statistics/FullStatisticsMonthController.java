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

import com.jason.ddoWeb.entity.statistics.FullStatisticsMonthEntity;
import com.jason.ddoWeb.service.statistics.FullStatisticsMonthServiceI;

/**   
 * @Title: Controller
 * @Description: 全量统计
 * @author zhangdaihao
 * @date 2015-05-08 16:07:20
 * @version V1.0   
 *
 */
@Scope("prototype")
@Controller
@RequestMapping("/fullStatisticsMonthController")
public class FullStatisticsMonthController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(FullStatisticsMonthController.class);

	@Autowired
	private FullStatisticsMonthServiceI fullStatisticsMonthService;
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
	@RequestMapping(params = "fullStatisticsMonth")
	public ModelAndView fullStatisticsMonth(HttpServletRequest request) {
		return new ModelAndView("com/jason/ddoWeb/statistics/fullStatisticsMonthList");
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
	public void datagrid(FullStatisticsMonthEntity fullStatisticsMonth,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(FullStatisticsMonthEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, fullStatisticsMonth, request.getParameterMap());
		this.fullStatisticsMonthService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除全量统计
	 * 
	 * @return
	 */
	@RequestMapping(params = "del")
	@ResponseBody
	public AjaxJson del(FullStatisticsMonthEntity fullStatisticsMonth, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		fullStatisticsMonth = systemService.getEntity(FullStatisticsMonthEntity.class, fullStatisticsMonth.getId());
		message = "全量统计删除成功";
		fullStatisticsMonthService.delete(fullStatisticsMonth);
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
	public AjaxJson save(FullStatisticsMonthEntity fullStatisticsMonth, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		if (StringUtil.isNotEmpty(fullStatisticsMonth.getId())) {
			message = "全量统计更新成功";
			FullStatisticsMonthEntity t = fullStatisticsMonthService.get(FullStatisticsMonthEntity.class, fullStatisticsMonth.getId());
			try {
				MyBeanUtils.copyBeanNotNull2Bean(fullStatisticsMonth, t);
				fullStatisticsMonthService.saveOrUpdate(t);
				systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			} catch (Exception e) {
				e.printStackTrace();
				message = "全量统计更新失败";
			}
		} else {
			message = "全量统计添加成功";
			fullStatisticsMonthService.save(fullStatisticsMonth);
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
	public ModelAndView addorupdate(FullStatisticsMonthEntity fullStatisticsMonth, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(fullStatisticsMonth.getId())) {
			fullStatisticsMonth = fullStatisticsMonthService.getEntity(FullStatisticsMonthEntity.class, fullStatisticsMonth.getId());
			req.setAttribute("fullStatisticsMonthPage", fullStatisticsMonth);
		}
		return new ModelAndView("com/jason/ddoWeb/statistics/fullStatisticsMonth");
	}
	
	@RequestMapping(params = "exportXls")
	public String exportXls(FullStatisticsMonthEntity fullStatisticsMonth, HttpServletRequest request,
			HttpServletResponse response, DataGrid dataGrid, ModelMap map) {
		
		CriteriaQuery cq = new CriteriaQuery(FullStatisticsMonthEntity.class, dataGrid);
		//设置默认的排序及排序字雄姿英发
		cq.addOrder("sumMonth", SortDirection.desc);
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq,
				fullStatisticsMonth, request.getParameterMap());
		List<FullStatisticsMonthEntity> fullStatisticsMonthList = this.fullStatisticsMonthService
				.getListByCriteriaQuery(cq, false);

		map.put(NormalExcelConstants.FILE_NAME, "全量统计");
		map.put(NormalExcelConstants.CLASS, FullStatisticsMonthEntity.class);
		ExportParams exportParams = new ExportParams();
		
		map.put(NormalExcelConstants.PARAMS, exportParams);
		map.put(NormalExcelConstants.DATA_LIST, fullStatisticsMonthList);
		return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}
}
