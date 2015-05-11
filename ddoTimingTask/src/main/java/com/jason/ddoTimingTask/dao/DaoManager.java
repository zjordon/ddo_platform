/**
 * 
 */
package com.jason.ddoTimingTask.dao;

import javax.sql.DataSource;

import com.jason.ddoTimingTask.util.DataSourceUtil;

/**
 * Dao管理类
 * @author jasonzhang
 *
 */
public class DaoManager {

	private final static DaoManager instance = new DaoManager();
	
	private DaoManager(){}
	
	public final static DaoManager getInstance() {
		return instance;
	}
	
	private BillResultRecordDao billResultRecordDao;
	private ChannelStatisticsDayDao channelStatisticsDayDao;
	private FullStatisticsDayDao fullStatisticsDayDao;
	private ChannelStatisticsMonthDao channelStatisticsMonthDao;
	private FullStatisticsMonthDao fullStatisticsMonthDao;
	private ProvinceStatisticsMonthDao provinceStatisticsMonthDao;
	private SendRecordDao sendRecordDao;
	private SendResultRecordDao sendResultRecordDao;
	private SmMsisdnListDao smMsisdnListDao;
	private SmRequestDao smRequestDao;
	private SmTaskDao smTaskDao;
	private BillBusinessDao billBusinessDao;
	
	public void init() {
		DataSource dataSource = DataSourceUtil.getDataSource();
		this.billResultRecordDao = new BillResultRecordDao();
		this.billResultRecordDao.setDataSource(dataSource);
		this.channelStatisticsDayDao = new ChannelStatisticsDayDao();
		this.channelStatisticsDayDao.setDataSource(dataSource);
		this.fullStatisticsMonthDao = new FullStatisticsMonthDao();
		this.fullStatisticsMonthDao.setDataSource(dataSource);
		this.fullStatisticsDayDao = new FullStatisticsDayDao();
		this.fullStatisticsDayDao.setDataSource(dataSource);
		this.channelStatisticsMonthDao = new ChannelStatisticsMonthDao();
		this.channelStatisticsMonthDao.setDataSource(dataSource);
		this.provinceStatisticsMonthDao = new ProvinceStatisticsMonthDao();
		this.provinceStatisticsMonthDao.setDataSource(dataSource);
		this.sendRecordDao = new SendRecordDao();
		this.sendRecordDao.setDataSource(dataSource);
		this.sendResultRecordDao = new SendResultRecordDao();
		this.sendResultRecordDao.setDataSource(dataSource);
		this.smMsisdnListDao = new SmMsisdnListDao();
		this.smMsisdnListDao.setDataSource(dataSource);
		this.smRequestDao = new SmRequestDao();
		this.smRequestDao.setDataSource(dataSource);
		this.smTaskDao = new SmTaskDao();
		this.smTaskDao.setDataSource(dataSource);
		this.billBusinessDao = new BillBusinessDao();
		this.billBusinessDao.setDataSource(dataSource);
	}

	public BillResultRecordDao getBillResultRecordDao() {
		return billResultRecordDao;
	}

	public ChannelStatisticsDayDao getChannelStatisticsDayDao() {
		return channelStatisticsDayDao;
	}

	public FullStatisticsDayDao getFullStatisticsDayDao() {
		return fullStatisticsDayDao;
	}

	public ChannelStatisticsMonthDao getChannelStatisticsMonthDao() {
		return channelStatisticsMonthDao;
	}

	public FullStatisticsMonthDao getFullStatisticsMonthDao() {
		return fullStatisticsMonthDao;
	}

	public ProvinceStatisticsMonthDao getProvinceStatisticsMonthDao() {
		return provinceStatisticsMonthDao;
	}

	public SendRecordDao getSendRecordDao() {
		return sendRecordDao;
	}

	public SendResultRecordDao getSendResultRecordDao() {
		return sendResultRecordDao;
	}

	public SmMsisdnListDao getSmMsisdnListDao() {
		return smMsisdnListDao;
	}

	public SmRequestDao getSmRequestDao() {
		return smRequestDao;
	}

	public SmTaskDao getSmTaskDao() {
		return smTaskDao;
	}

	public BillBusinessDao getBillBusinessDao() {
		return billBusinessDao;
	}
}
