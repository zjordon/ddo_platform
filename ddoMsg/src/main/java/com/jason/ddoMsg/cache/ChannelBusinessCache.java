/**
 * 
 */
package com.jason.ddoMsg.cache;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.jason.ddoMsg.dao.ChannelBusinessDao;
import com.jason.ddoMsg.dao.DaoException;
import com.jason.ddoMsg.bean.channel.ChannelBusiness;

/**
 * 渠道业务Cache
 * @author jasonzhang
 *
 */
public class ChannelBusinessCache {
	private static final Logger logger = Logger.getLogger(ChannelBusinessCache.class);

	private ChannelBusinessDao channelBusinessDao;
	private Map<String, ChannelBusiness> channelBusinessMap;
	private Map<String, String> instructMap;
	private Map<String, String> businessChannelMap;

	public void setChannelBusinessDao(ChannelBusinessDao channelBusinessDao) {
		this.channelBusinessDao = channelBusinessDao;
	}
	/**
	 * 加载业务列表
	 * @throws CacheException
	 */
	public void loadChannelBusinessList() throws CacheException {
		List<ChannelBusiness> list = null;
		try {
			list = this.channelBusinessDao.getChannelBusinesses();
		} catch (DaoException e) {
			logger.error("exception when loadChannelBusinessList", e);
			throw new CacheException(e.getMessage());
		}
		if (list != null) {
			this.channelBusinessMap = new HashMap<String, ChannelBusiness>();
			this.instructMap = new HashMap<String, String>();
			this.businessChannelMap = new HashMap<String, String>();
			for (ChannelBusiness channelBusiness : list) {
				this.channelBusinessMap.put(channelBusiness.getId(), channelBusiness);
				this.instructMap.put(channelBusiness.getInstruct(), channelBusiness.getId());
				//以计费业务ID加渠道id相加作为key缓存渠道计费业务实体
				this.businessChannelMap.put(channelBusiness.getBillBusinessId() + channelBusiness.getChannelId(), channelBusiness.getId());
			}
		}
	}
	/**
	 * 新增渠道业务
	 * @param channelBusiness 渠道业务
	 * @throws ElementExistsException
	 */
	public void addChannelBusiness(ChannelBusiness channelBusiness) throws ElementExistsException {
		if (this.channelBusinessMap.get(channelBusiness.getId()) != null) {
			throw new ElementExistsException("channelBusiness is already exists with id " + channelBusiness.getId());
		}
		this.businessChannelMap.put(channelBusiness.getBillBusinessId() + channelBusiness.getChannelId(), channelBusiness.getId());
		this.channelBusinessMap.put(channelBusiness.getId(), channelBusiness);
		this.instructMap.put(channelBusiness.getInstruct(), channelBusiness.getId());
	}
	/**
	 * 更新关停状态
	 * @param id 唯一标识
	 * @param closeState 关停状态
	 * @throws ElementNotFoundException
	 */
	public void updateCloseState(String id, int closeState) throws ElementNotFoundException {
		ChannelBusiness channelBusiness = this.getChannelBusiness(id);
		channelBusiness.setCloseState(closeState);
	}
	/**
	 * 更新产品信息
	 * @param id 唯一标识
	 * @param code 产品编码
	 * @param instruct 产品指令
	 * @throws ElementNotFoundException
	 */
	public void updateChannelBusinessMsg(String id, String instruct) throws ElementNotFoundException {
		ChannelBusiness channelBusiness = this.getChannelBusiness(id);
		if (!instruct.equals(channelBusiness.getInstruct())) {
			this.instructMap.remove(channelBusiness.getInstruct());
			this.instructMap.put(instruct, id);
		}
		channelBusiness.setInstruct(instruct);
	}
	/**
	 * 更新状态
	 * @param id 唯一标识
	 * @param state 状态
	 * @throws ElementNotFoundException
	 */
	public void updateState(String id, int state) throws ElementNotFoundException {
		ChannelBusiness channelBusiness = this.getChannelBusiness(id);
		channelBusiness.setState(new Integer(state));
	}
	/**
	 * 删除渠道业务
	 * @param id 唯一标识
	 * @throws ElementNotFoundException
	 */
	public void deleteChannelBusiness(String id) throws ElementNotFoundException {
		ChannelBusiness channelBusiness = this.getChannelBusiness(id);
		this.channelBusinessMap.remove(id);
		this.instructMap.remove(channelBusiness.getInstruct());
	}
	/**
	 * 获取渠道业务
	 * @param id 唯一标识
	 * @return
	 * @throws ElementNotFoundException
	 */
	public ChannelBusiness getChannelBusiness(String id) throws ElementNotFoundException {
		ChannelBusiness channelBusiness = this.channelBusinessMap.get(id);
		if (channelBusiness == null) {
			throw new ElementNotFoundException("channelBusiness element not found with id " + id);
		}
		return channelBusiness;
	}
	
	public ChannelBusiness getCBByBusinessIdAChannelId(String billBusinessId, String channelId) throws ElementNotFoundException {
		String id = this.businessChannelMap.get(billBusinessId + channelId);
		if (id == null) {
			throw new ElementNotFoundException("channelBusiness element not found with billBusinessId " + billBusinessId  + " and channelId " + channelId);
		}
		return this.getChannelBusiness(id);
	}
	
	
	public ChannelBusiness getChannelBusinessByInstruct(String instruct) throws ElementNotFoundException {
		String id = this.instructMap.get(instruct);
		if (id == null) {
			return null;
		}
		return this.getChannelBusiness(id);
	}
	public Map<String, ChannelBusiness> getChannelBusinessMap() {
		return channelBusinessMap;
	}
	public void setChannelBusinessMap(
			Map<String, ChannelBusiness> channelBusinessMap) {
		this.channelBusinessMap = channelBusinessMap;
		
		this.instructMap = new HashMap<String, String>();
		for (ChannelBusiness channelBusiness : this.channelBusinessMap.values()) {
			
			this.instructMap.put(channelBusiness.getInstruct(), channelBusiness.getId());
		}
	}
	
	public Map<String, String> getInstructMap() {
		return instructMap;
	}
}
