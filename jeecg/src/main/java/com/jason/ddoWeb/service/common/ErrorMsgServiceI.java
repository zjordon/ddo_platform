/**
 * 
 */
package com.jason.ddoWeb.service.common;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.jeecgframework.core.common.service.CommonService;

import com.jason.ddoWeb.common.model.ErrorMsg;

/**
 * 生成错误信息公共service
 * @author jasonzhang
 *
 */
public interface ErrorMsgServiceI extends CommonService {

	String saveErrorMsg(List<ErrorMsg> errorMsgs, HttpServletRequest request, Map<String, Object> attributes);
}
