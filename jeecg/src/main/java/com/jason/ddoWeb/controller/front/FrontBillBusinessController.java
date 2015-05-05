/**
 * 
 */
package com.jason.ddoWeb.controller.front;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.model.json.ComboBox;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.util.ContextHolderUtils;
import org.jeecgframework.web.system.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jason.ddoWeb.controller.channel.BillBusinessController;
import com.jason.ddoWeb.entity.channel.BillBusinessEntity;
import com.jason.ddoWeb.entity.channel.ChannelUserEntity;
import com.jason.ddoWeb.service.channel.BillBusinessServiceI;
import com.jason.ddoWeb.util.NumberUtils;

/**
 * @author jasonzhang
 *
 */
@Scope("prototype")
@Controller("frontBillBusinessController")
@RequestMapping("/frontBillBusinessController")
public class FrontBillBusinessController extends BaseController {

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
	
	@RequestMapping(params = "combox")
	@ResponseBody
	public List<ComboBox> combox(HttpServletRequest request, DataGrid dataGrid) {
		//CriteriaQuery cq = new CriteriaQuery(BillBusinessEntity.class);
		//只取出状态为有效的计费业务
		//cq.eq("state", "1");
		//cq.add();
		String text = request.getParameter("text");
		List<ComboBox> comboxList = new ArrayList<ComboBox>();
		
		ChannelUserEntity channelUser = (ChannelUserEntity)ContextHolderUtils.getSession().getAttribute("channelUser");
		if (channelUser != null) {
			//只获取本渠道启用的计费业务
			List<BillBusinessEntity> ls = this.billBusinessService.findEnableBusiness(channelUser.getChannelId());
			
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
		}
		
		
		return comboxList;
	}
}
