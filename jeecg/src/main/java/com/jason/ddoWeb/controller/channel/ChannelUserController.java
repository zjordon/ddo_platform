package com.jason.ddoWeb.controller.channel;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.digest.DigestUtils;
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
import org.jeecgframework.core.util.oConvertUtils;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.jeecgframework.web.system.pojo.base.TSDepart;
import org.jeecgframework.web.system.service.SystemService;
import org.jeecgframework.core.util.MyBeanUtils;

import com.jason.ddoWeb.entity.channel.ChannelEntity;
import com.jason.ddoWeb.entity.channel.ChannelUserEntity;
import com.jason.ddoWeb.service.channel.ChannelUserServiceI;

/**   
 * @Title: Controller
 * @Description: 渠道用户
 * @author zhangdaihao
 * @date 2015-04-14 17:11:17
 * @version V1.0   
 *
 */
@Scope("prototype")
@Controller
@RequestMapping("/channelUserController")
public class ChannelUserController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(ChannelUserController.class);

	@Autowired
	private ChannelUserServiceI channelUserService;
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
	 * 渠道用户列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "channelUser")
	public ModelAndView channelUser(HttpServletRequest request) {
		return new ModelAndView("com/jason/ddoWeb/channel/channelUserList");
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
	public void datagrid(ChannelUserEntity channelUser,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(ChannelUserEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, channelUser, request.getParameterMap());
		this.channelUserService.getDataGridReturn(cq, true);
//		StringBuilder builder = new StringBuilder();
//		List<ChannelEntity> channelList = this.channelUserService.getList(ChannelEntity.class);
//		for (ChannelEntity channel : channelList) {
//			builder.append(channel.getName()).append('_').append(channel.getId()).append(',');
//		}
//		if (builder.length() > 0) {
//			builder.deleteCharAt(builder.length() - 1);
//		}
//		request.setAttribute("channelsReplace", builder.toString());
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除渠道用户
	 * 
	 * @return
	 */
	@RequestMapping(params = "del")
	@ResponseBody
	public AjaxJson del(ChannelUserEntity channelUser, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		channelUser = systemService.getEntity(ChannelUserEntity.class, channelUser.getId());
		message = "渠道用户删除成功";
		channelUserService.delete(channelUser);
		systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加渠道用户
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "save")
	@ResponseBody
	public AjaxJson save(ChannelUserEntity channelUser, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		if (StringUtil.isNotEmpty(channelUser.getId())) {
			message = "渠道用户更新成功";
			ChannelUserEntity t = channelUserService.get(ChannelUserEntity.class, channelUser.getId());
			try {
				MyBeanUtils.copyBeanNotNull2Bean(channelUser, t);
				channelUserService.saveOrUpdate(t);
				systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			} catch (Exception e) {
				e.printStackTrace();
				message = "渠道用户更新失败";
			}
		} else {
			message = "渠道用户添加成功";
			//加密密码
			channelUser.setPassword(DigestUtils.md5Hex(channelUser.getPassword()));
			channelUserService.save(channelUser);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 渠道用户列表页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "addorupdate")
	public ModelAndView addorupdate(ChannelUserEntity channelUser, HttpServletRequest req) {
		this.loadChannelUser(channelUser, req);
		return new ModelAndView("com/jason/ddoWeb/channel/channelUser");
	}
	
	@RequestMapping(params = "changepasswordforuser")
	public ModelAndView changepasswordforuser(ChannelUserEntity channelUser, HttpServletRequest req) {
		this.loadChannelUser(channelUser, req);
		return new ModelAndView("com/jason/ddoWeb/channel/channeluserchangepwd");
	}
	
	@RequestMapping(params = "savenewpwdforuser")
	@ResponseBody
	public AjaxJson savenewpwdforuser(HttpServletRequest req) {
		AjaxJson j = new AjaxJson();
		String id = oConvertUtils.getString(req.getParameter("id"));
		String password = oConvertUtils.getString(req.getParameter("password"));
		this.channelUserService.updatePassword(id, password);
		message = "渠道用户: " +id + "密码重置成功";
		systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		j.setMsg(message);
		return j;
	}
	
	private void loadChannelUser(ChannelUserEntity channelUser,  HttpServletRequest req) {
		if (StringUtil.isNotEmpty(channelUser.getId())) {
			channelUser = channelUserService.getEntity(ChannelUserEntity.class, channelUser.getId());
			req.setAttribute("channelUserPage", channelUser);
		}
	}
	
}
