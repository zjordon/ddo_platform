package com.jason.ddoMsg.cache;

import java.util.HashMap;
import java.util.Map;

import com.jason.ddoMsg.bean.channel.ChannelBusiness;
import com.jason.ddoMsg.dao.ChannelBusinessDao;

public class ChannelBusinessCacheTest extends BaseCacheTest {
	
	private ChannelBusinessCache channelBusinessCache;

	protected void setUp() throws Exception {
		super.setUp();
		ChannelBusinessDao channelBusinessDao = new ChannelBusinessDao();
		channelBusinessDao.setDataSource(super.dataSource);
		this.channelBusinessCache = new ChannelBusinessCache();
		this.channelBusinessCache.setChannelBusinessDao(channelBusinessDao);
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testLoadChannelBusinessList() throws CacheException {
		this.channelBusinessCache.loadChannelBusinessList();
		Map<String, ChannelBusiness> channelBusinessMap = this.channelBusinessCache.getChannelBusinessMap();
		super.assertNotNull(channelBusinessMap);
		super.assertEquals("10000000100000000001", channelBusinessMap.get("1").getInstruct());
		Map<String, String> instructMap = this.channelBusinessCache.getInstructMap();
		super.assertNotNull(instructMap);
		super.assertEquals("1", instructMap.get("10000000100000000001"));
		
	}

	public void testAddChannelBusiness() throws ElementExistsException {
		this.setupChannelBusinessMap();
		ChannelBusiness channelBusiness = this.getChannelBusiness("1", "10000000100000000001", "1", "1");
		ElementExistsException excepedEx = null;
		try {
			this.channelBusinessCache.addChannelBusiness(channelBusiness);
		} catch (ElementExistsException e) {
			excepedEx = e;
		}
		super.assertNotNull(excepedEx);
		channelBusiness = this.getChannelBusiness("11", "10000004100000000006", "6", "1");
		this.channelBusinessCache.addChannelBusiness(channelBusiness);
		Map<String, ChannelBusiness> channelBusinessMap = this.channelBusinessCache.getChannelBusinessMap();
		super.assertNotNull(channelBusinessMap);
		super.assertEquals("10000004100000000006", channelBusinessMap.get("11").getInstruct());
		Map<String, String> instructMap = this.channelBusinessCache.getInstructMap();
		super.assertNotNull(instructMap);
		super.assertEquals("11", instructMap.get("10000004100000000006"));
	}

	public void testUpdateCloseState() throws ElementNotFoundException {
		this.setupChannelBusinessMap();
		ElementNotFoundException excepedEx = null;
		try {
			this.channelBusinessCache.updateCloseState("11", 0);
		} catch (ElementNotFoundException e) {
			excepedEx = e;
		}
		super.assertNotNull(excepedEx);
		this.channelBusinessCache.updateCloseState("1", 0);
		Map<String, ChannelBusiness> channelBusinessMap = this.channelBusinessCache.getChannelBusinessMap();
		super.assertEquals(0, channelBusinessMap.get("1").getCloseState().intValue());
	}

	public void testUpdateChannelBusinessMsg() throws ElementNotFoundException {
		this.setupChannelBusinessMap();
		ElementNotFoundException excepedEx = null;
		try {
			this.channelBusinessCache.updateChannelBusinessMsg("11", "10000004100000000006");
		} catch (ElementNotFoundException e) {
			excepedEx = e;
		}
		super.assertNotNull(excepedEx);
		this.channelBusinessCache.updateChannelBusinessMsg("1", "10000004100000000006");
		Map<String, ChannelBusiness> channelBusinessMap = this.channelBusinessCache.getChannelBusinessMap();
		super.assertEquals("10000004100000000006", channelBusinessMap.get("1").getInstruct());
	}

	public void testUpdateState() throws ElementNotFoundException {
		this.setupChannelBusinessMap();
		ElementNotFoundException excepedEx = null;
		try {
			this.channelBusinessCache.updateState("11", 0);
		} catch (ElementNotFoundException e) {
			excepedEx = e;
		}
		super.assertNotNull(excepedEx);
		this.channelBusinessCache.updateState("1", 0);
		Map<String, ChannelBusiness> channelBusinessMap = this.channelBusinessCache.getChannelBusinessMap();
		super.assertEquals(0, channelBusinessMap.get("1").getState().intValue());
	}

	public void testDeleteChannelBusiness() throws ElementNotFoundException {
		this.setupChannelBusinessMap();
		ElementNotFoundException excepedEx = null;
		try {
			this.channelBusinessCache.deleteChannelBusiness("11");
		} catch (ElementNotFoundException e) {
			excepedEx = e;
		}
		super.assertNotNull(excepedEx);
		this.channelBusinessCache.deleteChannelBusiness("1");
		Map<String, ChannelBusiness> channelBusinessMap = this.channelBusinessCache.getChannelBusinessMap();
		super.assertNull(channelBusinessMap.get("1"));
		Map<String, String> instructMap = this.channelBusinessCache.getInstructMap();
		super.assertNull(instructMap.get("10000000100000000001"));
	}

	public void testGetChannelBusiness() throws ElementNotFoundException {
		this.setupChannelBusinessMap();
		ElementNotFoundException excepedEx = null;
		ChannelBusiness channelBusiness = null;
		try {
			channelBusiness = this.channelBusinessCache.getChannelBusiness("11");
		} catch (ElementNotFoundException e) {
			excepedEx = e;
		}
		super.assertNotNull(excepedEx);
		channelBusiness = this.channelBusinessCache.getChannelBusiness("1");
		super.assertNotNull(channelBusiness);
	}

	public void testGetChannelBusinessByInstruct() throws ElementNotFoundException {
		this.setupChannelBusinessMap();
		ElementNotFoundException excepedEx = null;
		ChannelBusiness channelBusiness = null;
		try {
			channelBusiness = this.channelBusinessCache.getChannelBusinessByInstruct("10000004100000000006");
		} catch (ElementNotFoundException e) {
			excepedEx = e;
		}
		super.assertNotNull(excepedEx);
		channelBusiness = this.channelBusinessCache.getChannelBusinessByInstruct("10000004100000000005");
		super.assertNotNull(channelBusiness);
		super.assertEquals("10", channelBusiness.getId());
		super.assertEquals("10000004100000000005", channelBusiness.getInstruct());
	}
	
	private void setupChannelBusinessMap() {
		Map<String, ChannelBusiness> channelBusinessMap = new HashMap<String, ChannelBusiness>();
		//福建中科亚创
		this.putInMap("1", "10000000100000000001", "1", "1", channelBusinessMap);
		this.putInMap("2", "10000000100000000002", "2", "1", channelBusinessMap);
		this.putInMap("3", "10000000100000000003", "3", "1", channelBusinessMap);
		this.putInMap("4", "10000000100000000004", "4", "1", channelBusinessMap);
		this.putInMap("5", "10000000100000000005", "5", "1", channelBusinessMap);
		//漫星社
		this.putInMap("6", "10000004100000000001", "1", "4", channelBusinessMap);
		this.putInMap("7", "10000004100000000002", "2", "5", channelBusinessMap);
		this.putInMap("8", "10000004100000000003", "3", "4", channelBusinessMap);
		this.putInMap("9", "10000004100000000004", "4", "4", channelBusinessMap);
		this.putInMap("10", "10000004100000000005", "5", "4", channelBusinessMap);
		this.channelBusinessCache.setChannelBusinessMap(channelBusinessMap);
	}
	
	private void putInMap(String id, String instruct, String billBusinessId, String channelId, Map<String, ChannelBusiness> channelBusinessMap) {
		ChannelBusiness channelBusiness = this.getChannelBusiness(id, instruct, billBusinessId, channelId);
		channelBusinessMap.put(channelBusiness.getId(), channelBusiness);
	}
	
	private ChannelBusiness getChannelBusiness(String id, String instruct, String billBusinessId, String channelId) {
		ChannelBusiness channelBusiness = new ChannelBusiness();
		channelBusiness.setId(id);
		channelBusiness.setInstruct(instruct);
		channelBusiness.setBillBusinessId(billBusinessId);
		channelBusiness.setChannelId(channelId);
		channelBusiness.setState(new Integer(1));
		channelBusiness.setCloseState(new Integer(1));
		return channelBusiness;
	}

}
