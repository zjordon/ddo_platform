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

import com.jason.ddoWeb.controller.channel.ChannelController;
import com.jason.ddoWeb.entity.channel.ChannelEntity;
import com.jason.ddoWeb.entity.channel.ChannelUserEntity;
import com.jason.ddoWeb.service.channel.ChannelServiceI;

/**
 * @author jasonzhang
 *
 */
@Scope("prototype")
@Controller("frontChannelController")
@RequestMapping("/frontChannelController")
public class FrontChannelController extends BaseController {

	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(ChannelController.class);

	@Autowired
	private ChannelServiceI channelService;
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
		CriteriaQuery cq = new CriteriaQuery(ChannelEntity.class);
		ChannelUserEntity channelUser = (ChannelUserEntity)ContextHolderUtils.getSession().getAttribute("channelUser");
		List<ComboBox> comboxList = new ArrayList<ComboBox>();
		if (channelUser != null) {
			//用户只能查看自己渠道的短信任务
			cq.eq("id", channelUser.getChannelId());
			cq.add();
			List<ChannelEntity> ls = this.channelService.getListByCriteriaQuery(cq, false);
			
			for (ChannelEntity channel : ls) {
				ComboBox comboBox = new ComboBox();
				comboBox.setId(channel.getId());
				comboBox.setText(channel.getName());
				comboxList.add(comboBox);
			}
		}
		
		return comboxList;
	}
}
