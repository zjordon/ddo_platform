package com.jason.ddoMsg.cache;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.jason.ddoMsg.bean.channel.BillBusiness;
import com.jason.ddoMsg.dao.BillBusinessDao;

public class BillBusinessCacheTest extends BaseCacheTest {
	private BillBusinessCache billBusinessCache;

	protected void setUp() throws Exception {
		super.setUp();
		BillBusinessDao billBusinessDao = new BillBusinessDao();
		billBusinessDao.setDataSource(super.dataSource);
		this.billBusinessCache = new BillBusinessCache();
		this.billBusinessCache.setBillBusinessDao(billBusinessDao);
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testLoadBillBusinessList() throws CacheException {
		this.billBusinessCache.loadBillBusinessList();
		Map<String, BillBusiness> billBusinessMap = this.billBusinessCache.getBillBusinessMap();
		super.assertNotNull(billBusinessMap);
		this.printlnBillBusinessMap(billBusinessMap);
		BillBusiness billBusiness = billBusinessMap.get("1");
		super.assertNotNull(billBusiness);
		super.assertEquals("200000003646", billBusiness.getCode());
		Map<String, String> channelBillCodeMap = this.billBusinessCache.getChannelBillCodeMap();
		super.assertNotNull(channelBillCodeMap);
		super.assertEquals("1", channelBillCodeMap.get("100000000001"));
	}

	public void testAddBillBusiness() throws ElementExistsException {
		this.setupBillBusinessMap();
		BillBusiness billBusiness = this.getBillBusiness("1", 10L, "200000003646", "100000000001");
		ElementExistsException excepedEx = null;
		try {
			this.billBusinessCache.addBillBusiness(billBusiness);
		} catch (ElementExistsException e) {
			excepedEx = e;
		}
		super.assertNotNull(excepedEx);
		billBusiness = this.getBillBusiness("6", 300L, "200000003651", "100000000006");
		this.billBusinessCache.addBillBusiness(billBusiness);
		Map<String, BillBusiness> billBusinessMap = this.billBusinessCache.getBillBusinessMap();
		super.assertNotNull(billBusinessMap);
		super.assertEquals("200000003651", billBusinessMap.get("6").getCode());
		Map<String, String> channelBillCodeMap = this.billBusinessCache.getChannelBillCodeMap();
		super.assertNotNull(channelBillCodeMap);
		super.assertEquals("6", channelBillCodeMap.get("100000000006"));
	}

	public void testUpdateBillBusiness() throws ElementNotFoundException {
		this.setupBillBusinessMap();
		ElementNotFoundException excepedEx = null;
		try {
			this.billBusinessCache.updateBillBusiness("6", 300L, "200000003651", "");
		} catch (ElementNotFoundException e) {
			excepedEx = e;
		}
		super.assertNotNull(excepedEx);
		this.billBusinessCache.updateBillBusiness("1", 20L, "200000003640", "");
		Map<String, BillBusiness> billBusinessMap = this.billBusinessCache.getBillBusinessMap();
		super.assertNotNull(billBusinessMap);
		super.assertEquals("200000003640", billBusinessMap.get("1").getCode());
		super.assertEquals(20L, billBusinessMap.get("1").getPrice().longValue());
	}

	public void testDeleteBillBusiness() throws ElementNotFoundException {
		this.setupBillBusinessMap();
		ElementNotFoundException excepedEx = null;
		try {
			this.billBusinessCache.deleteBillBusiness("8");
		} catch (ElementNotFoundException e) {
			excepedEx = e;
		}
		super.assertNotNull(excepedEx);
		this.billBusinessCache.deleteBillBusiness("1");
		Map<String, BillBusiness> billBusinessMap = this.billBusinessCache.getBillBusinessMap();
		super.assertNotNull(billBusinessMap);
		super.assertNull(billBusinessMap.get("1"));
		Map<String, String> channelBillCodeMap = this.billBusinessCache.getChannelBillCodeMap();
		super.assertNotNull(channelBillCodeMap);
		super.assertNull(channelBillCodeMap.get("100000000001"));
	}

	public void testGetBillBusiness() throws ElementNotFoundException {
		this.setupBillBusinessMap();
		ElementNotFoundException excepedEx = null;
		
		BillBusiness billBusiness = null;
		try {
			billBusiness = this.billBusinessCache.getBillBusiness("8");
		} catch (ElementNotFoundException e) {
			excepedEx = e;
		}
		super.assertNotNull(excepedEx);
		billBusiness = this.billBusinessCache.getBillBusiness("1");
		super.assertNotNull(billBusiness);
		super.assertEquals("200000003646", billBusiness.getCode());
	}
	
	private void printlnBillBusinessMap(Map<String, BillBusiness> billBusinessMap) {
		for (Entry<String, BillBusiness> entry: billBusinessMap.entrySet()) {

			System.out.println("key is " +  entry.getKey() + "value is " + entry.getValue().toString());

		}
	}
	
	private void setupBillBusinessMap() {
		Map<String, BillBusiness> billBusinessMap = new HashMap<String, BillBusiness>();
		this.putInMap("1", 10L, "200000003646", "100000000001", billBusinessMap);
		this.putInMap("2", 20L, "200000003647", "100000000002", billBusinessMap);
		this.putInMap("3", 50L, "200000003648", "100000000003", billBusinessMap);
		this.putInMap("4", 100L, "200000003649", "100000000004", billBusinessMap);
		this.putInMap("5", 200L, "200000003650", "100000000005", billBusinessMap);
		this.billBusinessCache.setBillBusinessMap(billBusinessMap);
	}
	
	private void putInMap(String id, long price, String code, String chnnelBillcode, Map<String, BillBusiness> billBusinessMap) {
		BillBusiness billBusiness = this.getBillBusiness(id, price, code, chnnelBillcode);
		billBusinessMap.put(billBusiness.getId(), billBusiness);
	}
	
	private BillBusiness getBillBusiness(String id, long price, String code, String channelBillCode) {
		BillBusiness billBusiness = new BillBusiness();
		billBusiness.setId(id);
		billBusiness.setName("购买积分");
		billBusiness.setPrice(new Long(price));
		billBusiness.setCode(code);
		billBusiness.setState(new Integer(1));
		billBusiness.setChannelBillCode(channelBillCode);
		return billBusiness;
	}

}
