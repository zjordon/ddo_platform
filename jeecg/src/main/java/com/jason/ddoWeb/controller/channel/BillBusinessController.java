package com.jason.ddoWeb.controller.channel;
import java.util.ArrayList;
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
import org.jeecgframework.core.common.model.json.ComboBox;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.constant.Globals;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.jeecgframework.web.system.service.SystemService;
import org.jeecgframework.core.util.MyBeanUtils;

import com.jason.ddoWeb.entity.channel.BillBusinessEntity;
import com.jason.ddoWeb.entity.channel.ChannelEntity;
import com.jason.ddoWeb.service.channel.BillBusinessServiceI;
import com.jason.ddoWeb.util.NumberUtils;

/**   
 * @Title: Controller
 * @Description: 计费业务
 * @author zhangdaihao
 * @date 2015-04-17 09:16:43
 * @version V1.0   
 *
 */
@Scope("prototype")
@Controller
@RequestMapping("/billBusinessController")
public class BillBusinessController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(BillBusinessController.class);

	@Autowired
	private BillBusinessServiceI billBusinessService;
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
	 * 计费业务列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "billBusiness")
	public ModelAndView billBusiness(HttpServletRequest request) {
		return new ModelAndView("com/jason/ddoWeb/channel/billBusinessList");
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
	public void datagrid(BillBusinessEntity billBusiness,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(BillBusinessEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, billBusiness, request.getParameterMap());
		this.billBusinessService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除计费业务
	 * 
	 * @return
	 */
	@RequestMapping(params = "del")
	@ResponseBody
	public AjaxJson del(BillBusinessEntity billBusiness, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		billBusiness = systemService.getEntity(BillBusinessEntity.class, billBusiness.getId());
		message = "计费业务删除成功";
		billBusinessService.delete(billBusiness);
		systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加计费业务
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "save")
	@ResponseBody
	public AjaxJson save(BillBusinessEntity billBusiness, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		//把界面上传过来的元转成分设置到实体中
		billBusiness.setPrice(NumberUtils.doubleToInt(billBusiness.getPriceDouble()));
		if (StringUtil.isNotEmpty(billBusiness.getId())) {
			message = "计费业务更新成功";
			BillBusinessEntity t = billBusinessService.get(BillBusinessEntity.class, billBusiness.getId());
			try {
				MyBeanUtils.copyBeanNotNull2Bean(billBusiness, t);
				billBusinessService.saveOrUpdate(t);
				systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			} catch (Exception e) {
				e.printStackTrace();
				message = "计费业务更新失败";
			}
		} else {
			message = "计费业务添加成功";
			billBusinessService.save(billBusiness);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 计费业务列表页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "addorupdate")
	public ModelAndView addorupdate(BillBusinessEntity billBusiness, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(billBusiness.getId())) {
			billBusiness = billBusinessService.getEntity(BillBusinessEntity.class, billBusiness.getId());
			req.setAttribute("billBusinessPage", billBusiness);
		}
		return new ModelAndView("com/jason/ddoWeb/channel/billBusiness");
	}
	
	@RequestMapping(params = "combox")
	@ResponseBody
	public List<ComboBox> combox(HttpServletRequest request, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(BillBusinessEntity.class);
		//只取出状态为有效的计费业务
		//cq.eq("state", "1");
		//cq.add();
		String text = request.getParameter("text");
		List<BillBusinessEntity> ls = this.billBusinessService.getListByCriteriaQuery(cq, false);
		List<ComboBox> comboxList = new ArrayList<ComboBox>(ls.size());
		if ("code".equals(text)) {
			for (BillBusinessEntity billBusiness : ls) {
				ComboBox comboBox = new ComboBox();
				comboBox.setId(billBusiness.getId());
				comboBox.setText(billBusiness.getCode());
				comboxList.add(comboBox);
			}
		} else if ("price".equals(text)) {
			for (BillBusinessEntity billBusiness : ls) {
				ComboBox comboBox = new ComboBox();
				comboBox.setId(billBusiness.getId());
				//把分转成元返回
				comboBox.setText((NumberUtils.intToDouble(billBusiness.getPrice())).toString());
				comboxList.add(comboBox);
			}
		}
		
		return comboxList;
	}
}
