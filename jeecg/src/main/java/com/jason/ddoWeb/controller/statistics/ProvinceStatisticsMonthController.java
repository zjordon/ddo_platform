package com.jason.ddoWeb.controller.statistics;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import org.jeecgframework.web.system.pojo.base.TSTerritory;
import org.jeecgframework.web.system.service.SystemService;
import org.jeecgframework.core.util.MyBeanUtils;

import com.jason.ddoWeb.common.easypoi.datahandler.DdoMsgHandler;
import com.jason.ddoWeb.entity.statistics.ProvinceStatisticsMonthEntity;
import com.jason.ddoWeb.service.statistics.ProvinceStatisticsMonthServiceI;

/**   
 * @Title: Controller
 * @Description: 分省统计
 * @author zhangdaihao
 * @date 2015-05-11 13:31:47
 * @version V1.0   
 *
 */
@Scope("prototype")
@Controller
@RequestMapping("/provinceStatisticsMonthController")
public class ProvinceStatisticsMonthController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(ProvinceStatisticsMonthController.class);

	@Autowired
	private ProvinceStatisticsMonthServiceI provinceStatisticsMonthService;
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
	 * 分省统计列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "provinceStatisticsMonth")
	public ModelAndView provinceStatisticsMonth(HttpServletRequest request) {
		return new ModelAndView("com/jason/ddoWeb/statistics/provinceStatisticsMonthList");
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
	public void datagrid(ProvinceStatisticsMonthEntity provinceStatisticsMonth,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(ProvinceStatisticsMonthEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, provinceStatisticsMonth, request.getParameterMap());
		this.provinceStatisticsMonthService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除分省统计
	 * 
	 * @return
	 */
	@RequestMapping(params = "del")
	@ResponseBody
	public AjaxJson del(ProvinceStatisticsMonthEntity provinceStatisticsMonth, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		provinceStatisticsMonth = systemService.getEntity(ProvinceStatisticsMonthEntity.class, provinceStatisticsMonth.getId());
		message = "分省统计删除成功";
		provinceStatisticsMonthService.delete(provinceStatisticsMonth);
		systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加分省统计
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "save")
	@ResponseBody
	public AjaxJson save(ProvinceStatisticsMonthEntity provinceStatisticsMonth, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		if (StringUtil.isNotEmpty(provinceStatisticsMonth.getId())) {
			message = "分省统计更新成功";
			ProvinceStatisticsMonthEntity t = provinceStatisticsMonthService.get(ProvinceStatisticsMonthEntity.class, provinceStatisticsMonth.getId());
			try {
				MyBeanUtils.copyBeanNotNull2Bean(provinceStatisticsMonth, t);
				provinceStatisticsMonthService.saveOrUpdate(t);
				systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			} catch (Exception e) {
				e.printStackTrace();
				message = "分省统计更新失败";
			}
		} else {
			message = "分省统计添加成功";
			provinceStatisticsMonthService.save(provinceStatisticsMonth);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 分省统计列表页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "addorupdate")
	public ModelAndView addorupdate(ProvinceStatisticsMonthEntity provinceStatisticsMonth, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(provinceStatisticsMonth.getId())) {
			provinceStatisticsMonth = provinceStatisticsMonthService.getEntity(ProvinceStatisticsMonthEntity.class, provinceStatisticsMonth.getId());
			req.setAttribute("provinceStatisticsMonthPage", provinceStatisticsMonth);
		}
		return new ModelAndView("com/jason/ddoWeb/statistics/provinceStatisticsMonth");
	}
	
	@RequestMapping(params = "exportXls")
	public String exportXls(ProvinceStatisticsMonthEntity provinceStatisticsMonth, HttpServletRequest request,
			HttpServletResponse response, DataGrid dataGrid, ModelMap map) {
		
		CriteriaQuery cq = new CriteriaQuery(ProvinceStatisticsMonthEntity.class, dataGrid);
		//设置默认的排序及排序字雄姿英发
		cq.addOrder("sumMonth", SortDirection.desc);
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq,
				provinceStatisticsMonth, request.getParameterMap());
		List<ProvinceStatisticsMonthEntity> provinceStatisticsMonthList = this.provinceStatisticsMonthService
				.getListByCriteriaQuery(cq, false);

		map.put(NormalExcelConstants.FILE_NAME, "分省统计");
		map.put(NormalExcelConstants.CLASS, ProvinceStatisticsMonthEntity.class);
		ExportParams exportParams = new ExportParams();
		// 使用handler替换数据字典的值
		DdoMsgHandler dataHandler = new DdoMsgHandler();
		dataHandler.setNeedHandlerFields(new String[]{"省份"});
		// 初始化map
		dataHandler.setProvinceCodeMap(this.getProviceCodeMap());
		exportParams.setDataHanlder(dataHandler);
		map.put(NormalExcelConstants.PARAMS, exportParams);
		map.put(NormalExcelConstants.DATA_LIST, provinceStatisticsMonthList);
		return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}
	
	private Map<String, String> getProviceCodeMap() {
		return this.getCodeMap(new Short(((short)1)));
	}

	private Map<String, String> getCodeMap(Short level) {
		Map<String, String> codeMap = new HashMap<String, String>();
		List<TSTerritory> territoryList = this.systemService.findByProperty(
				TSTerritory.class, "territoryLevel", level);
		for (TSTerritory entity : territoryList) {
			codeMap.put(entity.getTerritoryCode(), entity.getTerritoryName());
		}
		return codeMap;
	}
}
