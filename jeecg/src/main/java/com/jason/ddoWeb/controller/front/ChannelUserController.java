/**
 * 
 */
package com.jason.ddoWeb.controller.front;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.constant.Globals;
import org.jeecgframework.core.extend.datasource.DataSourceContextHolder;
import org.jeecgframework.core.extend.datasource.DataSourceType;
import org.jeecgframework.core.util.ContextHolderUtils;
import org.jeecgframework.core.util.NumberComparator;
import org.jeecgframework.core.util.ResourceUtil;
import org.jeecgframework.web.system.manager.ClientManager;
import org.jeecgframework.web.system.pojo.base.TSConfig;
import org.jeecgframework.web.system.pojo.base.TSFunction;
import org.jeecgframework.web.system.pojo.base.TSIcon;
import org.jeecgframework.web.system.pojo.base.TSUser;
import org.jeecgframework.web.system.service.MutiLangServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.jason.ddoWeb.entity.channel.ChannelEntity;
import com.jason.ddoWeb.entity.channel.ChannelUserEntity;
import com.jason.ddoWeb.service.channel.ChannelUserServiceI;

/**
 * 前端界面，提供给渠道用户使用
 * 
 * @author jasonzhang
 *
 */
@Scope("prototype")
@Controller("frontChannelUserController")
@RequestMapping("/frontChannelUserController")
public class ChannelUserController extends BaseController {

	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger
			.getLogger(ChannelUserController.class);

	@Autowired
	private ChannelUserServiceI channelUserService;
	@Autowired
	private MutiLangServiceI mutiLangService;

	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * 渠道用户列登录页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "login")
	public ModelAndView login(HttpServletRequest request) {
		return new ModelAndView("login/channel_user_login");
	}

	@RequestMapping(params = "checkuser")
	@ResponseBody
	public AjaxJson checkuser(ChannelUserEntity channelUser,
			HttpServletRequest req) {
		HttpSession session = ContextHolderUtils.getSession();
		DataSourceContextHolder
				.setDataSourceType(DataSourceType.dataSource_jeecg);
		AjaxJson j = new AjaxJson();
		if (req.getParameter("langCode") != null) {
			req.getSession().setAttribute("lang", req.getParameter("langCode"));
		}
		String randCode = req.getParameter("randCode");
		if (StringUtils.isEmpty(randCode)) {
			j.setMsg(mutiLangService.getLang("common.enter.verifycode"));
			j.setSuccess(false);
		} else if (!randCode.equalsIgnoreCase(String.valueOf(session
				.getAttribute("randCode")))) {
			// todo "randCode"和验证码servlet中该变量一样，通过统一的系统常量配置比较好，暂时不知道系统常量放在什么地方合适
			j.setMsg(mutiLangService.getLang("common.verifycode.error"));
			j.setSuccess(false);
		} else {
			channelUser = this.channelUserService.checkUserExits(channelUser);
			if (channelUser != null) {
				session.setAttribute("channelUser", channelUser);
			} else {
				j.setMsg(mutiLangService
						.getLang("common.username.or.password.error"));
				j.setSuccess(false);
			}
		}
		return j;
	}

	/**
	 * 用户登录
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "doLogin")
	public String doLogin(ModelMap modelMap, HttpServletRequest request) {
		DataSourceContextHolder
				.setDataSourceType(DataSourceType.dataSource_jeecg);
		HttpSession session = ContextHolderUtils.getSession();
		ChannelUserEntity channelUser = (ChannelUserEntity) session
				.getAttribute("channelUser");
		if (channelUser != null) {
			modelMap.put("username", channelUser.getUsername());
			ChannelEntity channel = this.channelUserService.get(
					ChannelEntity.class, channelUser.getChannelId());
			if (channel != null) {
				modelMap.put("channelName", channel.getName());
			}
			return "main/channel_main";
		}
		return "login/channel_user_login";
	}

	/**
	 * 退出系统
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "logout")
	public ModelAndView logout(HttpServletRequest request) {
		HttpSession session = ContextHolderUtils.getSession();
		session.removeAttribute("channelUser");
		ModelAndView modelAndView = new ModelAndView(new RedirectView(
				"frontChannelUserController.do?login"));
		return modelAndView;
	}

	/**
	 * 菜单跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "left")
	public ModelAndView left(HttpServletRequest request) {
		HttpSession session = ContextHolderUtils.getSession();
		ChannelUserEntity channelUser = (ChannelUserEntity) session
				.getAttribute("channelUser");
		ModelAndView modelAndView = new ModelAndView();
		// 登陆者的权限
		if (channelUser == null) {
			session.removeAttribute(Globals.USER_SESSION);
			modelAndView.setView(new RedirectView(
					"frontChannelUserController.do?login"));
		} else {
			// TODO 生成菜单
			modelAndView.setViewName("main/left");
			request.setAttribute("menuMap", getFunctionMap());
		}
		return modelAndView;
	}

	/**
	 * 首页跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "home")
	public ModelAndView home(HttpServletRequest request) {
		return new ModelAndView("main/home");
	}

	private Map<Integer, List<TSFunction>> getFunctionMap() {
		TSIcon defaultIcon = new TSIcon();
		defaultIcon.setId("1");
		defaultIcon.setExtend("png");
		defaultIcon.setIconClas("default");
		defaultIcon.setIconName("common.default.icon");
		defaultIcon.setIconPath("in/accordion/images/default.png");
		defaultIcon.setIconType(new Short((short)1));
		Map<Integer, List<TSFunction>> functionMap = new HashMap<Integer, List<TSFunction>>();
		// 手工构造菜单
		TSFunction parentFunction = new TSFunction();
		parentFunction.setId("1");
		parentFunction.setFunctionLevel(new Short((short) 0));
		parentFunction.setFunctionName("短信下发");
		parentFunction.setFunctionOrder("1");
		parentFunction.setFunctionType(new Short((short) 0));
		parentFunction.setTSIcon(defaultIcon);
		List<TSFunction> parentFunctionList = new ArrayList<TSFunction>(1);
		parentFunctionList.add(parentFunction);
		functionMap.put(new Integer(0), parentFunctionList);
		TSFunction childFunction = new TSFunction();
		childFunction.setId("2");
		childFunction.setFunctionLevel(new Short((short) 1));
		childFunction.setFunctionName("短信任务");
		childFunction.setFunctionOrder("1");
		childFunction.setFunctionType(new Short((short) 0));
		childFunction.setFunctionUrl("frontSmTaskController.do?smTask");
		childFunction.setTSFunction(parentFunction);
		childFunction.setTSIcon(defaultIcon);
		List<TSFunction> childFunctionList = new ArrayList<TSFunction>(1);
		parentFunction.setTSFunctions(childFunctionList);
		childFunctionList.add(childFunction);
		functionMap.put(new Integer(1), childFunctionList);
		// 菜单栏排序
		Collection<List<TSFunction>> c = functionMap.values();
		for (List<TSFunction> list : c) {
			Collections.sort(list, new NumberComparator());
		}
		return functionMap;
	}
}
