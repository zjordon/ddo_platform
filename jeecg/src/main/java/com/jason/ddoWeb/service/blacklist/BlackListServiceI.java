package com.jason.ddoWeb.service.blacklist;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.common.service.CommonService;

import com.jason.ddoWeb.entity.blacklist.BlackListEntity;

public interface BlackListServiceI extends CommonService{

	AjaxJson saveBlackLists(List<BlackListEntity> listBlackList, HttpServletRequest request);
	
	AjaxJson saveFile(HttpServletRequest request);
}
