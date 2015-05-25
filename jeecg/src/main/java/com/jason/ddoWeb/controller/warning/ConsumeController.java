/**
 * 
 */
package com.jason.ddoWeb.controller.warning;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.constant.Globals;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.web.system.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.jason.ddoWeb.service.warning.ConsumeServiceI;

/**
 * @author jasonzhang
 *
 */
@Scope("prototype")
@Controller
@RequestMapping("/consumeController")
public class ConsumeController extends BaseController {

	@Autowired
	private ConsumeServiceI consumeService;
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
	 * 扣费限制页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "consume")
	public ModelAndView consume(HttpServletRequest request) {
		Map<String, String> map = this.consumeService.getConsumeLimit();
		request.setAttribute("map", map);
		return new ModelAndView("com/jason/ddoWeb/warning/consume");
	}
	
	/**
	 * 更新扣费限制参数
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "save")
	@ResponseBody
	public AjaxJson save(HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		String monthDeductionAmountLimit = request.getParameter("monthDeductionAmountLimit");
		String monthDeductionNumLimit = request.getParameter("monthDeductionNumLimit");
		if (StringUtil.isNotEmpty(monthDeductionAmountLimit) && StringUtil.isNotEmpty(monthDeductionNumLimit)) {
			message = "扣费限制参数更新成功!";
			this.consumeService.saveConsumeLimit(Double.parseDouble(monthDeductionAmountLimit), Integer.parseInt(monthDeductionNumLimit));
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		}
		return j;
	}
}
