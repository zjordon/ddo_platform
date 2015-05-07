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
import org.jeecgframework.web.system.service.SystemService;
import org.jeecgframework.core.util.MyBeanUtils;

import com.jason.ddoWeb.common.easypoi.datahandler.DdoMsgHandler;
import com.jason.ddoWeb.entity.statistics.ChannelStatisticsMonthEntity;
import com.jason.ddoWeb.service.channel.ChannelServiceI;
import com.jason.ddoWeb.service.statistics.ChannelStatisticsMonthServiceI;

/**   
 * @Title: Controller
 * @Description: 分渠道统计
 * @author zhangdaihao
 * @date 2015-05-07 15:25:39
 * @version V1.0   
 *
 */
@Scope("prototype")
@Controller
@RequestMapping("/channelStatisticsMonthController")
public class ChannelStatisticsMonthController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(ChannelStatisticsMonthController.class);

	@Autowired
	private ChannelStatisticsMonthServiceI channelStatisticsMonthService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private ChannelServiceI channelService;
	private String message;
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}


	/**
	 * 分渠道统计列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "channelStatisticsMonth")
	public ModelAndView channelStatisticsMonth(HttpServletRequest request) {
		return new ModelAndView("com/jason/ddoWeb/statistics/channelStatisticsMonthList");
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
	public void datagrid(ChannelStatisticsMonthEntity channelStatisticsMonth,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(ChannelStatisticsMonthEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, channelStatisticsMonth, request.getParameterMap());
		this.channelStatisticsMonthService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除分渠道统计
	 * 
	 * @return
	 */
	@RequestMapping(params = "del")
	@ResponseBody
	public AjaxJson del(ChannelStatisticsMonthEntity channelStatisticsMonth, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		channelStatisticsMonth = systemService.getEntity(ChannelStatisticsMonthEntity.class, channelStatisticsMonth.getId());
		message = "分渠道统计删除成功";
		channelStatisticsMonthService.delete(channelStatisticsMonth);
		systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加分渠道统计
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "save")
	@ResponseBody
	public AjaxJson save(ChannelStatisticsMonthEntity channelStatisticsMonth, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		if (StringUtil.isNotEmpty(channelStatisticsMonth.getId())) {
			message = "分渠道统计更新成功";
			ChannelStatisticsMonthEntity t = channelStatisticsMonthService.get(ChannelStatisticsMonthEntity.class, channelStatisticsMonth.getId());
			try {
				MyBeanUtils.copyBeanNotNull2Bean(channelStatisticsMonth, t);
				channelStatisticsMonthService.saveOrUpdate(t);
				systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			} catch (Exception e) {
				e.printStackTrace();
				message = "分渠道统计更新失败";
			}
		} else {
			message = "分渠道统计添加成功";
			channelStatisticsMonthService.save(channelStatisticsMonth);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 分渠道统计列表页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "addorupdate")
	public ModelAndView addorupdate(ChannelStatisticsMonthEntity channelStatisticsMonth, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(channelStatisticsMonth.getId())) {
			channelStatisticsMonth = channelStatisticsMonthService.getEntity(ChannelStatisticsMonthEntity.class, channelStatisticsMonth.getId());
			req.setAttribute("channelStatisticsMonthPage", channelStatisticsMonth);
		}
		return new ModelAndView("com/jason/ddoWeb/statistics/channelStatisticsMonth");
	}
	
	@RequestMapping(params = "exportXls")
	public String exportXls(ChannelStatisticsMonthEntity channelStatisticsMonth, HttpServletRequest request,
			HttpServletResponse response, DataGrid dataGrid, ModelMap map) {
		
		CriteriaQuery cq = new CriteriaQuery(ChannelStatisticsMonthEntity.class, dataGrid);
		//设置默认的排序及排序字雄姿英发
		cq.addOrder("sumMonth", SortDirection.asc);
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq,
				channelStatisticsMonth, request.getParameterMap());
		List<ChannelStatisticsMonthEntity> channelStatisticsMonthList = this.channelStatisticsMonthService
				.getListByCriteriaQuery(cq, false);

		map.put(NormalExcelConstants.FILE_NAME, "分渠道统计");
		map.put(NormalExcelConstants.CLASS, ChannelStatisticsMonthEntity.class);
		ExportParams exportParams = new ExportParams();
		// 使用handler替换数据字典的值
		DdoMsgHandler dataHandler = new DdoMsgHandler();
		dataHandler.setNeedHandlerFields(new String[]{"渠道"});
		// 初始化map
		dataHandler.setChannelNameMap(this.channelService.getChannelNameMap());
		exportParams.setDataHanlder(dataHandler);
		map.put(NormalExcelConstants.PARAMS, exportParams);
		map.put(NormalExcelConstants.DATA_LIST, channelStatisticsMonthList);
		return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}
}
