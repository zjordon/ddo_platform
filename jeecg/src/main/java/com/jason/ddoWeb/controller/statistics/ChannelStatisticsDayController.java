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
import com.jason.ddoWeb.entity.statistics.ChannelStatisticsDayEntity;
import com.jason.ddoWeb.service.channel.ChannelServiceI;
import com.jason.ddoWeb.service.statistics.ChannelStatisticsDayServiceI;

/**   
 * @Title: Controller
 * @Description: 分渠道统计
 * @author zhangdaihao
 * @date 2015-04-23 15:12:11
 * @version V1.0   
 *
 */
@Scope("prototype")
@Controller
@RequestMapping("/channelStatisticsDayController")
public class ChannelStatisticsDayController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(ChannelStatisticsDayController.class);

	@Autowired
	private ChannelStatisticsDayServiceI channelStatisticsDayService;
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
	@RequestMapping(params = "channelStatisticsDay")
	public ModelAndView channelStatisticsDay(HttpServletRequest request) {
		return new ModelAndView("com/jason/ddoWeb/statistics/channelStatisticsDayList");
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
	public void datagrid(ChannelStatisticsDayEntity channelStatisticsDay,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(ChannelStatisticsDayEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, channelStatisticsDay, request.getParameterMap());
		this.channelStatisticsDayService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除分渠道统计
	 * 
	 * @return
	 */
	@RequestMapping(params = "del")
	@ResponseBody
	public AjaxJson del(ChannelStatisticsDayEntity channelStatisticsDay, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		channelStatisticsDay = systemService.getEntity(ChannelStatisticsDayEntity.class, channelStatisticsDay.getId());
		message = "分渠道统计删除成功";
		channelStatisticsDayService.delete(channelStatisticsDay);
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
	public AjaxJson save(ChannelStatisticsDayEntity channelStatisticsDay, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		if (StringUtil.isNotEmpty(channelStatisticsDay.getId())) {
			message = "分渠道统计更新成功";
			ChannelStatisticsDayEntity t = channelStatisticsDayService.get(ChannelStatisticsDayEntity.class, channelStatisticsDay.getId());
			try {
				MyBeanUtils.copyBeanNotNull2Bean(channelStatisticsDay, t);
				channelStatisticsDayService.saveOrUpdate(t);
				systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			} catch (Exception e) {
				e.printStackTrace();
				message = "分渠道统计更新失败";
			}
		} else {
			message = "分渠道统计添加成功";
			channelStatisticsDayService.save(channelStatisticsDay);
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
	public ModelAndView addorupdate(ChannelStatisticsDayEntity channelStatisticsDay, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(channelStatisticsDay.getId())) {
			channelStatisticsDay = channelStatisticsDayService.getEntity(ChannelStatisticsDayEntity.class, channelStatisticsDay.getId());
			req.setAttribute("channelStatisticsDayPage", channelStatisticsDay);
		}
		return new ModelAndView("com/jason/ddoWeb/statistics/channelStatisticsDay");
	}
	
	@RequestMapping(params = "exportXls")
	public String exportXls(ChannelStatisticsDayEntity channelStatisticsDay, HttpServletRequest request,
			HttpServletResponse response, DataGrid dataGrid, ModelMap map) {
		
		CriteriaQuery cq = new CriteriaQuery(ChannelStatisticsDayEntity.class, dataGrid);
		//设置默认的排序及排序字雄姿英发
		cq.addOrder("sumDate", SortDirection.asc);
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq,
				channelStatisticsDay, request.getParameterMap());
		List<ChannelStatisticsDayEntity> channelStatisticsDayList = this.channelStatisticsDayService
				.getListByCriteriaQuery(cq, false);

		map.put(NormalExcelConstants.FILE_NAME, "分渠道统计");
		map.put(NormalExcelConstants.CLASS, ChannelStatisticsDayEntity.class);
		ExportParams exportParams = new ExportParams();
		// 使用handler替换数据字典的值
		DdoMsgHandler dataHandler = new DdoMsgHandler();
		dataHandler.setNeedHandlerFields(new String[]{"渠道"});
		// 初始化map
		dataHandler.setChannelNameMap(this.channelService.getChannelNameMap());
		exportParams.setDataHanlder(dataHandler);
		map.put(NormalExcelConstants.PARAMS, exportParams);
		map.put(NormalExcelConstants.DATA_LIST, channelStatisticsDayList);
		return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}
}
