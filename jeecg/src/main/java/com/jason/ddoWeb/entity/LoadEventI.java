/**
 * 
 */
package com.jason.ddoWeb.entity;

/**
 * 需要监听hibernate load event事件并做出自应处理的实体类都需要实现该接口
 * @author jasonzhang
 *
 */
public interface LoadEventI {

	void onLoad();
}
