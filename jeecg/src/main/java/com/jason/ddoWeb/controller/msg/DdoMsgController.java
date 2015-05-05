package com.jason.ddoWeb.controller.msg;

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
import org.jeecgframework.web.system.pojo.base.TSTerritory;
import org.jeecgframework.web.system.service.SystemService;
import org.jeecgframework.core.util.MyBeanUtils;

import com.jason.ddoWeb.common.easypoi.datahandler.DdoMsgHandler;
import com.jason.ddoWeb.entity.msg.DdoMsgEntity;
import com.jason.ddoWeb.service.channel.BillBusinessServiceI;
import com.jason.ddoWeb.service.channel.ChannelServiceI;
import com.jason.ddoWeb.service.msg.DdoMsgServiceI;

/**
 * @Title: Controller
 * @Description: ddo消息
 * @author zhangdaihao
 * @date 2015-04-23 16:40:39
 * @version V1.0
 *
 */
@Scope("prototype")
@Controller
@RequestMapping("/ddoMsgController")
public class DdoMsgController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger
			.getLogger(DdoMsgController.class);

	@Autowired
	private DdoMsgServiceI ddoMsgService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private ChannelServiceI channelService;
	@Autowired
	private BillBusinessServiceI billBusinessService;
	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * ddo消息列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "ddoMsg")
	public ModelAndView ddoMsg(HttpServletRequest request) {
		String channelId = request.getParameter("channelId");
		String sumDate = request.getParameter("sumDate");
		if (StringUtil.isNotEmpty(channelId) && StringUtil.isNotEmpty(sumDate)) {
			request.setAttribute("channelId", channelId);
			request.setAttribute("sumDate", sumDate);
		}
		return new ModelAndView("com/jason/ddoWeb/msg/ddoMsgList");
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
	public void datagrid(DdoMsgEntity ddoMsg, HttpServletRequest request,
			HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(DdoMsgEntity.class, dataGrid);
		String channelId = request.getParameter("channelId");
		String sumDate = request.getParameter("sumDate");
		if (StringUtil.isNotEmpty(channelId) && StringUtil.isNotEmpty(sumDate)) {
			cq.eq("channelId", channelId);
			cq.add();
			//TODO 根据yyyymmdd的时间格式组装从yyyymmddhhmmss到yyyymmddhhmmss的查询条件
		}
		// 查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq,
				ddoMsg, request.getParameterMap());
		this.ddoMsgService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除ddo消息
	 * 
	 * @return
	 */
	@RequestMapping(params = "del")
	@ResponseBody
	public AjaxJson del(DdoMsgEntity ddoMsg, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		ddoMsg = systemService.getEntity(DdoMsgEntity.class, ddoMsg.getId());
		message = "ddo消息删除成功";
		ddoMsgService.delete(ddoMsg);
		systemService.addLog(message, Globals.Log_Type_DEL,
				Globals.Log_Leavel_INFO);

		j.setMsg(message);
		return j;
	}

	/**
	 * 添加ddo消息
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "save")
	@ResponseBody
	public AjaxJson save(DdoMsgEntity ddoMsg, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		if (StringUtil.isNotEmpty(ddoMsg.getId())) {
			message = "ddo消息更新成功";
			DdoMsgEntity t = ddoMsgService.get(DdoMsgEntity.class,
					ddoMsg.getId());
			try {
				MyBeanUtils.copyBeanNotNull2Bean(ddoMsg, t);
				ddoMsgService.saveOrUpdate(t);
				systemService.addLog(message, Globals.Log_Type_UPDATE,
						Globals.Log_Leavel_INFO);
			} catch (Exception e) {
				e.printStackTrace();
				message = "ddo消息更新失败";
			}
		} else {
			message = "ddo消息添加成功";
			ddoMsgService.save(ddoMsg);
			systemService.addLog(message, Globals.Log_Type_INSERT,
					Globals.Log_Leavel_INFO);
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * ddo消息列表页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "addorupdate")
	public ModelAndView addorupdate(DdoMsgEntity ddoMsg, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(ddoMsg.getId())) {
			ddoMsg = ddoMsgService
					.getEntity(DdoMsgEntity.class, ddoMsg.getId());
			req.setAttribute("ddoMsgPage", ddoMsg);
		}
		return new ModelAndView("com/jason/ddoWeb/msg/ddoMsg");
	}

	/**
	 * 导出excel
	 *
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXls")
	public String exportXls(DdoMsgEntity ddoMsg, HttpServletRequest request,
			HttpServletResponse response, DataGrid dataGrid, ModelMap map) {

		CriteriaQuery cq = new CriteriaQuery(DdoMsgEntity.class, dataGrid);
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq,
				ddoMsg, request.getParameterMap());
		List<DdoMsgEntity> ddoMsgkList = this.ddoMsgService
				.getListByCriteriaQuery(cq, false);

		map.put(NormalExcelConstants.FILE_NAME, "流水统计");
		map.put(NormalExcelConstants.CLASS, DdoMsgEntity.class);
		ExportParams exportParams = new ExportParams();
		// 使用handler替换数据字典的值
		DdoMsgHandler dataHandler = new DdoMsgHandler();
		// 初始化map
		dataHandler.setChannelNameMap(this.channelService.getChannelNameMap());
		dataHandler.setPriceMap(this.billBusinessService.getPriceMap());
		dataHandler.setProvinceCodeMap(this.getProviceCodeMap());
		dataHandler.setCityCodeMap(this.getCityCodeMap());
		exportParams.setDataHanlder(dataHandler);
		map.put(NormalExcelConstants.PARAMS, exportParams);
		map.put(NormalExcelConstants.DATA_LIST, ddoMsgkList);
		return NormalExcelConstants.JEECG_EXCEL_VIEW;

	}

	private Map<String, String> getProviceCodeMap() {
		return this.getCodeMap(new Short(((short)1)));
	}

	private Map<String, String> getCityCodeMap() {
		return this.getCodeMap(new Short(((short)2)));
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
