/**
 * 
 */
package com.jason.ddoMsg.cache;

import javax.sql.DataSource;

import com.jason.ddoMsg.dao.BillBusinessDao;
import com.jason.ddoMsg.dao.BillReportDao;
import com.jason.ddoMsg.dao.BlackListDao;
import com.jason.ddoMsg.dao.ChannelBusinessDao;
import com.jason.ddoMsg.dao.ChannelDao;
import com.jason.ddoMsg.dao.ChannelLimitDao;
import com.jason.ddoMsg.dao.ChannelRequestDao;
import com.jason.ddoMsg.dao.ChannelUserDao;
import com.jason.ddoMsg.dao.ConsumeDao;
import com.jason.ddoMsg.dao.DdoMsgDao;
import com.jason.ddoMsg.dao.EventDao;
import com.jason.ddoMsg.dao.ProviceCloseStateDao;
import com.jason.ddoMsg.dao.StatisticsDao;
import com.jason.ddoMsg.dao.UpChannelRecordDao;
import com.jason.ddoMsg.util.DataSourceUtil;

/**
 * 所有缓存类的管理类，单态模式
 * @author jasonzhang
 *
 */
public class CacheManager {

	private final static CacheManager instance = new CacheManager();
	
	private DataSource dataSource = null;
	
	private ChannelUserCache channelUserCache;
	private BlackListCache blackListCache;
	private BillBusinessCache billBusinessCache;
	private ChannelBusinessCache channelBusinessCache;
	private ChannelLimitCache channelLimitCache;
	private ConfigCache configCache;
	private UpChannelRecordCache upChannelRecordCache;
	private ProviceCloseStateCache proviceCloseStateCache;
	private DdoMsgCache ddoMsgCache;
	private ChannelCache channelCache;
	private ChannelRequestCache channelRequestCache;
	private BillReportCache billReportCache;
	private EventCache eventCache;
	private DmMobileCache dmMobileCache;
	private StatisticsCache statisticsCache;
	private ConsumeCache consumeCache;
	
	private CacheManager(){}
	
	public final static CacheManager getInstance() {
		return instance;
	}
	
	public void init() throws CacheException {
		 dataSource = DataSourceUtil.getDataSource();
		 ChannelUserDao channelUserDao = new ChannelUserDao();
		 channelUserDao.setDataSource(dataSource);
		 this.channelUserCache = new ChannelUserCache();
		 this.channelUserCache.setChannelUserDao(channelUserDao);
		 this.channelUserCache.loadChannelUserList();
		 BlackListDao blackListDao = new BlackListDao();
		 blackListDao.setDataSource(dataSource);
		 this.blackListCache= new BlackListCache();
		 this.blackListCache.setBlackListDao(blackListDao);
		 this.blackListCache.loadBlackListList();
		 BillBusinessDao billBusinessDao = new BillBusinessDao();
		 billBusinessDao.setDataSource(dataSource);
		 this.billBusinessCache = new BillBusinessCache();
		 this.billBusinessCache.setBillBusinessDao(billBusinessDao);
		 this.billBusinessCache.loadBillBusinessList();
		 ChannelBusinessDao channelBusinessDao = new ChannelBusinessDao();
		 channelBusinessDao.setDataSource(dataSource);
		 this.channelBusinessCache = new ChannelBusinessCache();
		 this.channelBusinessCache.setChannelBusinessDao(channelBusinessDao);
		 this.channelBusinessCache.loadChannelBusinessList();
		 ChannelLimitDao channelLimitDao = new ChannelLimitDao();
		 channelLimitDao.setDataSource(dataSource);
		 this.channelLimitCache = new ChannelLimitCache();
		 this.channelLimitCache.setChannelLimitDao(channelLimitDao);
		 ChannelDao channelDao = new ChannelDao();
		 channelDao.setDataSource(dataSource);
		 this.channelLimitCache.setChannelDao(channelDao);
		 this.channelLimitCache.loadChannelLimitList();
		 this.configCache = new ConfigCache();
		 this.configCache.loadConfigParam();
		 UpChannelRecordDao upChannelRecordDao = new UpChannelRecordDao();
		 upChannelRecordDao.setDataSource(dataSource);
		 this.upChannelRecordCache = new UpChannelRecordCache();
		 this.upChannelRecordCache.setUpChannelRecordDao(upChannelRecordDao);
		 ProviceCloseStateDao proviceCloseStateDao = new ProviceCloseStateDao();
		 proviceCloseStateDao.setDataSource(dataSource);
		 this.proviceCloseStateCache = new ProviceCloseStateCache();
		 this.proviceCloseStateCache.setProviceCloseStateDao(proviceCloseStateDao);
		 this.proviceCloseStateCache.loadProviceCloseStateList();
		 DdoMsgDao ddoMsgDao = new DdoMsgDao();
		 ddoMsgDao.setDataSource(dataSource);
		 this.ddoMsgCache = new DdoMsgCache();
		 this.ddoMsgCache.setDdoMsgDao(ddoMsgDao);
		 this.channelCache = new ChannelCache();
		 this.channelCache.setChannelDao(channelDao);
		 this.channelCache.loadChannelList();
		 ChannelRequestDao channelRequestDao = new ChannelRequestDao();
		 channelRequestDao.setDataSource(dataSource);
		 this.channelRequestCache = new ChannelRequestCache();
		 this.channelRequestCache.setChannelRequestDao(channelRequestDao);
		 BillReportDao billReportDao = new BillReportDao();
		 billReportDao.setDataSource(dataSource);
		 this.billReportCache = new BillReportCache();
		 this.billReportCache.setBillReportDao(billReportDao);
		 EventDao eventDao = new EventDao();
		 eventDao.setDataSource(dataSource);
		 this.eventCache = new EventCache();
		 this.eventCache.setEventDao(eventDao);
		 this.dmMobileCache = new DmMobileCache();
		 this.dmMobileCache.init();
		 StatisticsDao statisticsDao = new StatisticsDao();
		 statisticsDao.setDataSource(dataSource);
		 statisticsCache = new StatisticsCache();
		 statisticsCache.setStatisticsDao(statisticsDao);
		 this.consumeCache = new ConsumeCache();
		 ConsumeDao consumeDao = new ConsumeDao();
		 consumeDao.setDataSource(dataSource);
		 this.consumeCache.setConsumeDao(consumeDao);
		 this.consumeCache.init();
	}

	public ChannelUserCache getChannelUserCache() {
		return channelUserCache;
	}

	public BlackListCache getBlackListCache() {
		return blackListCache;
	}

	public BillBusinessCache getBillBusinessCache() {
		return billBusinessCache;
	}

	public ChannelBusinessCache getChannelBusinessCache() {
		return channelBusinessCache;
	}

	public ChannelLimitCache getChannelLimitCache() {
		return channelLimitCache;
	}

	public ConfigCache getConfigCache() {
		return configCache;
	}

	public UpChannelRecordCache getUpChannelRecordCache() {
		return upChannelRecordCache;
	}

	public ProviceCloseStateCache getProviceCloseStateCache() {
		return proviceCloseStateCache;
	}

	public DdoMsgCache getDdoMsgCache() {
		return ddoMsgCache;
	}

	public ChannelCache getChannelCache() {
		return channelCache;
	}

	public ChannelRequestCache getChannelRequestCache() {
		return channelRequestCache;
	}

	public BillReportCache getBillReportCache() {
		return billReportCache;
	}

	public EventCache getEventCache() {
		return eventCache;
	}
	
	public DmMobileCache getDmMobileCache() {
		return dmMobileCache;
	}

	public StatisticsCache getStatisticsCache() {
		return statisticsCache;
	}

	public ConsumeCache getConsumeCache() {
		return consumeCache;
	}

	public void destory() {
		if (this.dataSource != null) {
			DataSourceUtil.destoryDataSource(this.dataSource);
		}
		
	}
}
