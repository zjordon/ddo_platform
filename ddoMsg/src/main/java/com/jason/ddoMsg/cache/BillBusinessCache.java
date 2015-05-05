/**
 * 
 */
package com.jason.ddoMsg.cache;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.jason.ddoMsg.bean.channel.BillBusiness;
import com.jason.ddoMsg.dao.BillBusinessDao;
import com.jason.ddoMsg.dao.DaoException;

/**
 * 计费业务Cache
 * @author jasonzhang
 *
 */
public class BillBusinessCache {
	private static final Logger logger = Logger.getLogger(BillBusinessCache.class);

	private BillBusinessDao billBusinessDao;
	private Map<String, BillBusiness> billBusinessMap;
	private Map<String, String> channelBillCodeMap;

	public void setBillBusinessDao(BillBusinessDao billBusinessDao) {
		this.billBusinessDao = billBusinessDao;
	}
	/**
	 * 加载业务列表
	 * @throws CacheException
	 */
	public void loadBillBusinessList() throws CacheException {
		logger.info("begin load billBusiness list into cache");
		List<BillBusiness> billBusinessList = null;
		try {
			billBusinessList = this.billBusinessDao.getBillBusinesses();
		} catch (DaoException e) {
			logger.error("exception when loadBillBusinessList", e);
			throw new CacheException(e.getMessage());
		}
		if (billBusinessList != null) {
			this.billBusinessMap = new HashMap<String, BillBusiness>();
			this.channelBillCodeMap = new HashMap<String, String>();
			for (BillBusiness billBusiness : billBusinessList) {
				this.billBusinessMap.put(billBusiness.getId(), billBusiness);
				this.channelBillCodeMap.put(billBusiness.getChannelBillCode(), billBusiness.getId());
			}
		}
		logger.info("end load billBusiness list into cache, billBusiness nums is " + billBusinessList.size());
	}
	/**
	 * 新增计费业务
	 * @param billBusiness 计费业务
	 * @throws CacheException
	 */
	public void addBillBusiness(BillBusiness billBusiness) throws ElementExistsException {
		if (this.billBusinessMap.get(billBusiness.getId()) != null) {
			throw new ElementExistsException("billBusiness is already exists with id " + billBusiness.getId());
		}
		this.billBusinessMap.put(billBusiness.getId(), billBusiness);
		this.channelBillCodeMap.put(billBusiness.getChannelBillCode(), billBusiness.getId());
	}
	/**
	 * 更新计费业务信息
	 * @param id 唯一标识
	 * @param price 价格
	 * @param code 编码
	 * @param channelBillCode 渠道计费编码
	 * @throws CacheException
	 */
	public void updateBillBusiness(String id, long price, String code, String channelBillCode) throws ElementNotFoundException {
		BillBusiness billBusiness = this.getBillBusiness(id);
		billBusiness.setPrice(new Long(price));
		billBusiness.setCode(code);
		billBusiness.setChannelBillCode(channelBillCode);
	}
	/**
	 * 删除计费业务
	 * @param id 唯一标识
	 * @throws CacheException
	 */
	public void deleteBillBusiness(String id) throws ElementNotFoundException {
		BillBusiness billBusiness = this.getBillBusiness(id);
		this.billBusinessMap.remove(id);
		this.channelBillCodeMap.remove(billBusiness.getChannelBillCode());
	}
	/**
	 * 获取计费业务
	 * @param id 唯一标识
	 * @return
	 * @throws ElementNotFoundException
	 */
	public BillBusiness getBillBusiness(String id) throws ElementNotFoundException {
		BillBusiness billBusiness = this.billBusinessMap.get(id);
		if (billBusiness == null) {
			throw new ElementNotFoundException("billBusiness element not found with id " + id);
		}
		return billBusiness;
	}
	/**
	 * 根据渠道计费编码获取计费业务
	 * @param code 渠道计费编码
	 * @return
	 * @throws ElementNotFoundException
	 */
	public BillBusiness getByChannelBillCode(String code) throws ElementNotFoundException {
		String id = this.channelBillCodeMap.get(code);
		if (id == null) {
			return null;
		}
		return this.getBillBusiness(id);
	}
	public Map<String, BillBusiness> getBillBusinessMap() {
		return billBusinessMap;
	}
	public void setBillBusinessMap(Map<String, BillBusiness> billBusinessMap) {
		this.billBusinessMap = billBusinessMap;
		this.channelBillCodeMap = new HashMap<String, String>();
		for (BillBusiness billBusiness : this.billBusinessMap.values()) {
			this.channelBillCodeMap.put(billBusiness.getChannelBillCode(), billBusiness.getId());
		}
	}
	public Map<String, String> getChannelBillCodeMap() {
		return channelBillCodeMap;
	}
	
}
