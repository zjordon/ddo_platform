/**
 * 
 */
package com.jason.ddoMsg.cache;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.jason.ddoMsg.bean.channel.ProviceCloseState;
import com.jason.ddoMsg.dao.DaoException;
import com.jason.ddoMsg.dao.ProviceCloseStateDao;

/**
 * 省份关停状态Cache
 * @author jasonzhang
 *
 */
public class ProviceCloseStateCache {
	private static final Logger logger = Logger.getLogger(ProviceCloseStateCache.class);

	private ProviceCloseStateDao proviceCloseStateDao;
	private Map<String, ProviceCloseState> proviceCloseStateMap;
	private Map<String, String> proviceCodeMap;

	public void setProviceCloseStateDao(ProviceCloseStateDao proviceCloseStateDao) {
		this.proviceCloseStateDao = proviceCloseStateDao;
	}
	/**
	 * 加载省份关停状态列表
	 * @throws CacheException
	 */
	public void loadProviceCloseStateList() throws CacheException {
		List<ProviceCloseState> list = null;
		try {
			list = this.proviceCloseStateDao.getProviceCloseStates();
		} catch (DaoException e) {
			logger.error("exception when loadProviceCloseStateList", e);
			throw new CacheException(e.getMessage());
		}
		if (list != null) {
			this.proviceCloseStateMap = new HashMap<String, ProviceCloseState>();
			this.proviceCodeMap = new HashMap<String, String>();
			for (ProviceCloseState proviceCloseState : list) {
				this.proviceCloseStateMap.put(proviceCloseState.getId(), proviceCloseState);
				this.proviceCodeMap.put(proviceCloseState.getProviceCode(), proviceCloseState.getId());
			}
		}
	}
	/**
	 * 增加省份关停状态
	 * @param proviceCloseState 省份关停状态
	 * @throws ElementExistsException
	 */
	public void addProviceCloseState(ProviceCloseState proviceCloseState) throws ElementExistsException {
		if (this.proviceCloseStateMap.get(proviceCloseState.getId()) != null) {
			throw new ElementExistsException("proviceCloseState is already exists with id " + proviceCloseState.getId());
		}
		this.proviceCloseStateMap.put(proviceCloseState.getId(), proviceCloseState);
		this.proviceCodeMap.put(proviceCloseState.getProviceCode(), proviceCloseState.getId());
	}
	/**
	 * 更新省份关停状态
	 * @param id 唯一标识
	 * @param state 状态
	 * @throws ElementNotFoundException
	 */
	public void updateProviceCloseState(String id, int state) throws ElementNotFoundException {
		ProviceCloseState proviceCloseState = this.getProviceCloseState(id);
		proviceCloseState.setCloseState(new Integer(state));
	}
	
	public ProviceCloseState getProviceCloseState(String id) throws ElementNotFoundException {
		ProviceCloseState proviceCloseState = this.proviceCloseStateMap.get(id);
		if (proviceCloseState == null) {
			throw new ElementNotFoundException("proviceCloseState element not found with id " + id);
		}
		return proviceCloseState;
	}
	
	public ProviceCloseState getByProviceCode(String code) throws ElementNotFoundException {
		String id = this.proviceCodeMap.get(code);
		if (id == null) {
			throw new ElementNotFoundException("proviceCloseState element not found with code " + code);
		}
		return this.getProviceCloseState(id);
	}
}
