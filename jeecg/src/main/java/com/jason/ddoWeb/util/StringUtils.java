/**
 * 
 */
package com.jason.ddoWeb.util;

/**
 * 字符串工具类
 * @author jasonzhang
 *
 */
public class StringUtils {

	public final static boolean isEquals(String oldValue, String newValue) {
		boolean flag = true;
		if (oldValue != null) {
			if (newValue == null) {
				flag = false;
			} else if (!newValue.equals(oldValue)){
				flag = false;
			}
		} else {
			if (newValue != null) {
				flag = false;
			}
		}
		return flag;
	}
}
