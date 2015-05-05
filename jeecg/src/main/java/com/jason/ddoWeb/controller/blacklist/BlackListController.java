package com.jason.ddoWeb.controller.blacklist;
import java.io.IOException;
import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.constant.Globals;
import org.jeecgframework.core.util.ExceptionUtil;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.entity.TemplateExportParams;
import org.jeecgframework.poi.excel.entity.vo.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.vo.TemplateExcelConstants;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.jeecgframework.web.system.service.SystemService;
import org.jeecgframework.core.util.MyBeanUtils;

import com.jason.ddoWeb.entity.blacklist.BlackListEntity;
import com.jason.ddoWeb.service.blacklist.BlackListServiceI;

/**   
 * @Title: Controller
 * @Description: 黑名单管理
 * @author zhangdaihao
 * @date 2015-04-16 08:45:22
 * @version V1.0   
 *
 */
@Scope("prototype")
@Controller
@RequestMapping("/blackListController")
public class BlackListController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(BlackListController.class);

	@Autowired
	private BlackListServiceI blackListService;
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
	 * 黑名单管理列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "blackList")
	public ModelAndView blackList(HttpServletRequest request) {
		return new ModelAndView("com/jason/ddoWeb/blacklist/blackListList");
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
	public void datagrid(BlackListEntity blackList,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(BlackListEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, blackList, request.getParameterMap());
		this.blackListService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除黑名单管理
	 * 
	 * @return
	 */
	@RequestMapping(params = "del")
	@ResponseBody
	public AjaxJson del(BlackListEntity blackList, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		blackList = systemService.getEntity(BlackListEntity.class, blackList.getId());
		message = "黑名单管理删除成功";
		blackListService.delete(blackList);
		systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加黑名单管理
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "save")
	@ResponseBody
	public AjaxJson save(BlackListEntity blackList, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		if (StringUtil.isNotEmpty(blackList.getId())) {
			message = "黑名单管理更新成功";
			BlackListEntity t = blackListService.get(BlackListEntity.class, blackList.getId());
			try {
				MyBeanUtils.copyBeanNotNull2Bean(blackList, t);
				blackListService.saveOrUpdate(t);
				systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			} catch (Exception e) {
				e.printStackTrace();
				message = "黑名单管理更新失败";
			}
		} else {
			message = "黑名单管理添加成功";
			blackListService.save(blackList);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 黑名单管理列表页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "addorupdate")
	public ModelAndView addorupdate(BlackListEntity blackList, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(blackList.getId())) {
			blackList = blackListService.getEntity(BlackListEntity.class, blackList.getId());
			req.setAttribute("blackListPage", blackList);
		}
		return new ModelAndView("com/jason/ddoWeb/blacklist/blackList");
	}
	
	/**
	 * excel导入
	 *
	 * @return
	 */
	@RequestMapping(params = "upload")
	public String upload(HttpServletRequest req) {
		return "com/jason/ddoWeb/blacklist/blackListUpload";
	}
	
	@RequestMapping(params = "importExcel", method = RequestMethod.POST)
	@ResponseBody
	public AjaxJson importExcel(HttpServletRequest request, HttpServletResponse response) {

		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
		List<BlackListEntity> allRecords = new ArrayList<BlackListEntity>();
		for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
			MultipartFile file = entity.getValue();// 获取上传文件对象
			ImportParams params = new ImportParams();
			params.setTitleRows(0);
			params.setHeadRows(1);
			params.setNeedSave(true);
			try {
				List<BlackListEntity> listBlackList =  ExcelImportUtil.importExcelByIs(file.getInputStream(),BlackListEntity.class,params);
				allRecords.addAll(listBlackList);
			} catch (Exception e) {
				logger.error(ExceptionUtil.getExceptionMessage(e));
			}finally{
				try {
					file.getInputStream().close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		AjaxJson j = this.blackListService.saveBlackLists(allRecords, request);
		return j;
	}
	
	@RequestMapping(params = "importTxt", method = RequestMethod.POST)
	@ResponseBody
	public AjaxJson importTxt(HttpServletRequest request, HttpServletResponse response) {
		AjaxJson j = this.blackListService.saveFile(request);
		return j;
	}
	/**
	 * 导出excel
	 *
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXls")
	public String exportXls(BlackListEntity blackList,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap map) {

        CriteriaQuery cq = new CriteriaQuery(BlackListEntity.class, dataGrid);
        org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, blackList, request.getParameterMap());
        List<BlackListEntity> blackLists = this.blackListService.getListByCriteriaQuery(cq,false);

        map.put(NormalExcelConstants.FILE_NAME,"黑名单信息");
        map.put(NormalExcelConstants.CLASS,BlackListEntity.class);
        map.put(NormalExcelConstants.PARAMS,new ExportParams());
        map.put(NormalExcelConstants.DATA_LIST,blackLists);
        return NormalExcelConstants.JEECG_EXCEL_VIEW;

	}
	
	/**
	 * 导出excel 使模板
	 *
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXlsByT")
	public String exportXlsByT(BlackListEntity blackList,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
		modelMap.put(NormalExcelConstants.FILE_NAME,"黑名单导入模板");
		modelMap.put(NormalExcelConstants.CLASS,BlackListEntity.class);
		modelMap.put(NormalExcelConstants.PARAMS,new ExportParams());
		//构造示例数据
		List<BlackListEntity> demoList = new ArrayList<BlackListEntity>(1);
		BlackListEntity demoObj = new BlackListEntity();
		demoObj.setMsisdn(new Long(13950079348L));
		demoObj.setState(new Integer(1));
		demoList.add(demoObj);
		modelMap.put(NormalExcelConstants.DATA_LIST,demoList);
        return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}
}
