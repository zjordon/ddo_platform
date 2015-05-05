/**
 * 
 */
package com.jason.ddoMsg.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.jason.ddoMsg.bean.channel.ChannelDayLimit;
import com.jason.ddoMsg.bean.channel.ChannelMonthLimit;
import com.jason.ddoMsg.util.UUIDGenerator;

/**
 * 渠道限额Dao 处理与渠道限额相关数据与数据库交互的逻揖
 * 
 * @author jasonzhang
 *
 */
public class ChannelLimitDao extends BaseDao {
	private static final Logger logger = Logger.getLogger(ChannelLimitDao.class);
	private final static String GET_CHANNEL_DAY_LIMIT_NUMS ="select count(*) from ddo_channel_day_limit where day = ?";
	private final static String INSERT_CHANNEL_DAY_LIMIT = "insert into ddo_channel_day_limit(id, day, limit_amount, channel_id) values(?, ? ,?, ?)";
	private final static String GET_CHANNEL_MONTH_LIMIT_NUMS ="select count(*) from ddo_channel_month_limit where month = ?";
	private final static String INSERT_CHANNEL_MONTH_LIMIT = "insert into ddo_channel_month_limit(id, month, limit_amount, channel_id) values(?, ? ,?, ?)";
	private final static String GET_CHANNEL_DAY_LIMIT = "select id, limit_amount from ddo_channel_day_limit where channel_id = ? and day = ?";
	private final static String GET_CHANNEL_MONTH_LIMIT = "select id, limit_amount from ddo_channel_month_limit where channel_id = ? and month = ?";
	private final static String UPDATE_CHANNEL_DAY_LIMIT = "update ddo_channel_day_limit set limit_amount = ? where id = ?";
	private final static String UPDATE_CHANNEL_MONTH_LIMIT = "update ddo_channel_month_limit set limit_amount = ? where id = ?";
	private final static String INSERT_LIMIT_USE_LOG = "insert into ddo_limit_use_log(id, use_amount, remain_day_amount, remain_month_amount, create_date"
			+ ",request_id) values(?, ?, ?, ?, ?, ?)";
	private final static String GET_ALL_CHANNEL_DAY_LIMIT = "select id, limit_amount, day, channel_id from ddo_channel_day_limit where day = ?";
	private final static String GET_ALL_CHANNEL_MONTH_LIMIT = "select id, limit_amount, month, channel_id from ddo_channel_month_limit where month = ?";
	private final static String DELETE_CHANNEL_DAY_LIMIT = "delete from ddo_channel_day_limit where channel_id = ? and day = ?";
	private final static String DELETE_CHANNEL_MONTH_LIMIT = "delete from ddo_channel_month_limit where channel_id = ? and month = ?";
	
	/**
	 * 保存日限额列表
	 * @param dayLimitList 日限额列表
	 * @param day 日期
	 * @throws DaoException
	 */
	public void saveChannelDayLimits(List<ChannelDayLimit> dayLimitList, int day) throws DaoException {
		Connection conn = null;
		try {
			conn = super.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(GET_CHANNEL_DAY_LIMIT_NUMS);
			pstmt.setInt(1, day);
			ResultSet rs = pstmt.executeQuery();
			int nums = 0;
			if (rs.next()) {
				nums = rs.getInt(1);
			}
			super.closeResultSet(rs);
			super.closePstmt(pstmt);
			if (nums > 0) {
				throw new DaoException("channel day limit data is exisit with day " + day);
			}
			pstmt = conn.prepareStatement(INSERT_CHANNEL_DAY_LIMIT);
			for (ChannelDayLimit dayLimit : dayLimitList) {
				this.setDayLimitParam(dayLimit, pstmt);
				pstmt.addBatch();
			}
			pstmt.executeBatch();
			conn.commit();
		} catch (SQLException e) {
			super.rollbackConnection(conn);
			logger.error("exception when saveChannelDayLimits", e);
			throw new DaoException(e.getMessage());
		} finally {
			super.closeConnction(conn);
		}
	}
	
	/**
	 * 保存日限额列表
	 * @param monthLimitList 月限额列表
	 * @param month 月份
	 * @throws DaoException
	 */
	public void saveChannelMonthLimits(List<ChannelMonthLimit> monthLimitList, int month) throws DaoException {
		Connection conn = null;
		try {
			conn = super.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(GET_CHANNEL_MONTH_LIMIT_NUMS);
			pstmt.setInt(1, month);
			ResultSet rs = pstmt.executeQuery();
			int nums = 0;
			if (rs.next()) {
				nums = rs.getInt(1);
			}
			super.closeResultSet(rs);
			super.closePstmt(pstmt);
			if (nums > 0) {
				throw new DaoException("channel month limit data is exisit with month " + month);
			}
			pstmt = conn.prepareStatement(INSERT_CHANNEL_MONTH_LIMIT);
			for (ChannelMonthLimit monthLimit : monthLimitList) {
				this.setMonthLimitParam(monthLimit, pstmt);
				pstmt.addBatch();
			}
			pstmt.executeBatch();
			conn.commit();
		} catch (SQLException e) {
			super.rollbackConnection(conn);
			logger.error("exception when saveChannelMonthLimits", e);
			throw new DaoException(e.getMessage());
		} finally {
			super.closeConnction(conn);
		}
	}
	/**
	 * 删除某个渠道的日限额
	 * @param channelId 渠道id
	 * @param day 日期
	 * @throws DaoException
	 */
	public void deleteChannelDayLimit(String channelId, int day) throws DaoException {
		Connection conn = null;
		try {
			conn = super.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(DELETE_CHANNEL_DAY_LIMIT);
			pstmt.setString(1, channelId);
			pstmt.setInt(1, day);
			pstmt.executeUpdate();
			super.closePstmt(pstmt);
			conn.commit();
		} catch (SQLException e) {
			super.rollbackConnection(conn);
			logger.error("exception when deleteChannelDayLimit", e);
			throw new DaoException(e.getMessage());
		} finally {
			super.closeConnction(conn);
		}
	}
	/**
	 * 删除某个渠道的月限额
	 * @param channelId 渠道id
	 * @param month 月份
	 * @throws DaoException
	 */
	public void deleteChannelMonthLimit(String channelId, int month) throws DaoException {
		Connection conn = null;
		try {
			conn = super.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(DELETE_CHANNEL_MONTH_LIMIT);
			pstmt.setString(1, channelId);
			pstmt.setInt(1, month);
			pstmt.executeUpdate();
			super.closePstmt(pstmt);
			conn.commit();
		} catch (SQLException e) {
			super.rollbackConnection(conn);
			logger.error("exception when deleteChannelMonthLimit", e);
			throw new DaoException(e.getMessage());
		} finally {
			super.closeConnction(conn);
		}
	}
	/**
	 * 新增某渠道的日限额
	 * @param dayLimit
	 * @throws DaoException
	 */
	public void addChannelDayLimit(ChannelDayLimit dayLimit) throws DaoException {
		Connection conn = null;
		try {
			conn = super.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(GET_CHANNEL_DAY_LIMIT);
			pstmt.setString(1, dayLimit.getChannelId());
			pstmt.setInt(2, dayLimit.getDay().intValue());
			ResultSet rs = pstmt.executeQuery();
			boolean isExist = false;
			if (rs.next()) {
				isExist = true;
			}
			super.closeResultSet(rs);
			if (isExist) {
				throw new DaoException("the daylimit is exist with channelId " + dayLimit.getChannelId() + " and day " + dayLimit.getDay());
			}
			pstmt = conn.prepareStatement(INSERT_CHANNEL_DAY_LIMIT);
			this.setDayLimitParam(dayLimit, pstmt);
			pstmt.executeUpdate();
			conn.commit();
		} catch (SQLException e) {
			super.rollbackConnection(conn);
			logger.error("exception when saveChannelMonthLimits", e);
			throw new DaoException(e.getMessage());
		} finally {
			super.closeConnction(conn);
		}
	}
	/**
	 * 新增某渠道的月限额
	 * @param monthLimit
	 * @throws DaoException
	 */
	public void addChannelMonthLimit(ChannelMonthLimit monthLimit) throws DaoException {
		Connection conn = null;
		try {
			conn = super.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(GET_CHANNEL_MONTH_LIMIT);
			pstmt.setString(1, monthLimit.getChannelId());
			pstmt.setInt(2, monthLimit.getMonth().intValue());
			ResultSet rs = pstmt.executeQuery();
			boolean isExist = false;
			if (rs.next()) {
				isExist = true;
			}
			super.closeResultSet(rs);
			if (isExist) {
				throw new DaoException("the daylimit is exist with channelId " + monthLimit.getChannelId() + " and month " + monthLimit.getMonth());
			}
			pstmt = conn.prepareStatement(INSERT_CHANNEL_MONTH_LIMIT);
			this.setMonthLimitParam(monthLimit, pstmt);
			pstmt.executeUpdate();
			conn.commit();
		} catch (SQLException e) {
			super.rollbackConnection(conn);
			logger.error("exception when saveChannelMonthLimits", e);
			throw new DaoException(e.getMessage());
		} finally {
			super.closeConnction(conn);
		}
	}
	
	private void setDayLimitParam(ChannelDayLimit dayLimit, PreparedStatement pstmt) throws SQLException {
		pstmt.setString(1, dayLimit.getId());
		pstmt.setInt(2, dayLimit.getDay().intValue());
		pstmt.setLong(3, dayLimit.getLimitAmount().longValue());
		pstmt.setString(4, dayLimit.getChannelId());
	}
	
	private void setMonthLimitParam(ChannelMonthLimit monthLimit, PreparedStatement pstmt) throws SQLException {
		pstmt.setString(1, monthLimit.getId());
		pstmt.setInt(2, monthLimit.getMonth().intValue());
		pstmt.setLong(3, monthLimit.getLimitAmount().longValue());
		pstmt.setString(4, monthLimit.getChannelId());
	}
	/**
	 * 改变渠道的日限额
	 * @param channelId
	 * @param day
	 * @param amount 正数表示加，负数表示减
	 * @throws SQLException
	 */
	public void changeDayLimit(String channelId, int day, long amount) throws DaoException {
		String channelDayLimitId = null;
		long tempAmount = 0L;
		long remainDayAmount = 0L;
		Connection conn = null;
		try {
			conn = super.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(GET_CHANNEL_DAY_LIMIT);
			pstmt.setString(1, channelId);
			pstmt.setInt(2, day);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				channelDayLimitId = rs.getString(1);
				tempAmount = rs.getLong(2);
			}
			super.closeResultSet(rs);
			super.closePstmt(pstmt);
			if (channelDayLimitId != null) {
				remainDayAmount = tempAmount + amount;
				pstmt = conn.prepareStatement(UPDATE_CHANNEL_DAY_LIMIT);
				pstmt.setLong(1, remainDayAmount);
				pstmt.setString(2, channelDayLimitId);
				pstmt.executeUpdate();
				super.closePstmt(pstmt);
				conn.commit();
			}
		} catch (SQLException e) {
			super.rollbackConnection(conn);
			logger.error("exception when changeDayLimit", e);
			throw new DaoException(e.getMessage());
		} finally {
			super.closeConnction(conn);
		}
	}
	/**
	 * 改变渠道的月限额
	 * @param channelId
	 * @param month
	 * @param amount 正数表示加，负数表示减
	 * @throws SQLException
	 */
	public void changeMonthLimit(String channelId, int month, long amount) throws DaoException {
		String channelMonthLimitId = null;
		long tempAmount = 0L;
		long remainDayAmount = 0L;
		Connection conn = null;
		try {
			conn = super.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(GET_CHANNEL_MONTH_LIMIT);
			pstmt.setString(1, channelId);
			pstmt.setInt(2, month);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				channelMonthLimitId = rs.getString(1);
				tempAmount = rs.getLong(2);
			}
			super.closeResultSet(rs);
			super.closePstmt(pstmt);
			if (channelMonthLimitId != null) {
				remainDayAmount = tempAmount + amount;
				pstmt = conn.prepareStatement(UPDATE_CHANNEL_MONTH_LIMIT);
				pstmt.setLong(1, remainDayAmount);
				pstmt.setString(2, channelMonthLimitId);
				pstmt.executeUpdate();
				super.closePstmt(pstmt);
				conn.commit();
			}
		} catch (SQLException e) {
			super.rollbackConnection(conn);
			logger.error("exception when changeMonthLimit", e);
			throw new DaoException(e.getMessage());
		} finally {
			super.closeConnction(conn);
		}
	}

	/**
	 * 扣除某个渠道的限额 扣除某个渠道的限额，需要扣除对应月份，对应天的限额，并往渠道限额使用流水插入一条流水记录
	 * 
	 * @param channelId
	 *            渠道id
	 * @param deductDate
	 *            日期
	 * @param amount
	 *            扣减限额(单元：分)
	 * @param requestId
	 *            请求id
	 */
	public void deductChannelLimit(String channelId, int day, int month,
			long amount, String requestId) throws DaoException {
		String channelDayLimitId = null;
		String channelMonthLimitId = null;
		long tempAmount = 0L;
		long remainDayAmount = 0L;
		long remainMonthAmount = 0L;
		Connection conn = null;
		try {
			conn = super.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(GET_CHANNEL_DAY_LIMIT);
			pstmt.setString(1, channelId);
			pstmt.setInt(2, day);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				channelDayLimitId = rs.getString(1);
				tempAmount = rs.getLong(2);
			}
			super.closeResultSet(rs);
			super.closePstmt(pstmt);
			if (channelDayLimitId != null) {
				remainDayAmount = tempAmount - amount;
				pstmt = conn.prepareStatement(GET_CHANNEL_MONTH_LIMIT);
				pstmt.setString(1, channelId);
				pstmt.setInt(2, month);
				rs = pstmt.executeQuery();
				tempAmount = 0L;
				if (rs.next()) {
					channelMonthLimitId = rs.getString(1);
					tempAmount = rs.getLong(2);
				}
				super.closeResultSet(rs);
				super.closePstmt(pstmt);
				if (channelMonthLimitId != null) {
					remainMonthAmount = tempAmount - amount;
					//更新日限额
					pstmt = conn.prepareStatement(UPDATE_CHANNEL_DAY_LIMIT);
					pstmt.setLong(1, remainDayAmount);
					pstmt.setString(2, channelDayLimitId);
					pstmt.executeUpdate();
					super.closePstmt(pstmt);
					//更新月限额
					pstmt = conn.prepareStatement(UPDATE_CHANNEL_MONTH_LIMIT);
					pstmt.setLong(1, remainMonthAmount);
					pstmt.setString(2, channelMonthLimitId);
					pstmt.executeUpdate();
					super.closePstmt(pstmt);
					//插入流水
					pstmt = conn.prepareStatement(INSERT_LIMIT_USE_LOG);
					pstmt.setString(1, (new UUIDGenerator()).generate());
					pstmt.setLong(2, amount);
					pstmt.setLong(3, remainDayAmount);
					pstmt.setLong(4, remainMonthAmount);
					pstmt.setTimestamp(5, new java.sql.Timestamp(System.currentTimeMillis()));
					pstmt.setString(6, requestId);
					pstmt.executeUpdate();
					super.closePstmt(pstmt);
				} else {
					throw new DaoException("could not find channel_month_limit with channelId " + channelId + " and month is " + month);
				}
			} else {
				throw new DaoException("could not find channel_day_limit with channelId " + channelId + " and day is " + day);
			}
			conn.commit();
		} catch (SQLException e) {
			super.rollbackConnection(conn);
			logger.error("exception when deductChannelLimit", e);
			throw new DaoException(e.getMessage());
		} finally {
			super.closeConnction(conn);
		}
	}

	/**
	 * 获取某天的渠道日限额列表
	 * 
	 * @param date
	 *            yyyymmdd格式
	 * @return
	 */
	public List<ChannelDayLimit> getChannelDayLimits(int date)
			throws DaoException {
		List<ChannelDayLimit> channelDayLimitList = new ArrayList<ChannelDayLimit>();
		Connection conn = null;
		try {
			conn = super.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(GET_ALL_CHANNEL_DAY_LIMIT);
			pstmt.setInt(1, date);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				ChannelDayLimit channelDayLimit = new ChannelDayLimit();
				channelDayLimit.setId(rs.getString(1));
				channelDayLimit.setLimitAmount(new Long(rs.getLong(2)));
				channelDayLimit.setDay(new Integer(rs.getInt(3)));
				channelDayLimit.setChannelId(rs.getString(4));
				channelDayLimitList.add(channelDayLimit);
			}
			super.closeResultSet(rs);
			super.closePstmt(pstmt);
		} catch (SQLException e) {
			logger.error("exception when getChannelDayLimits", e);
			throw new DaoException(e.getMessage());
		} finally {
			super.closeConnction(conn);
		}
		return channelDayLimitList;
	}

	/**
	 * 获取某月的渠道月限额列表
	 * 
	 * @param month yyyymm格式
	 * @return
	 * @throws DaoException
	 */
	public List<ChannelMonthLimit> getChannelMonthLimits(int month)
			throws DaoException {
		List<ChannelMonthLimit> channelMonthLimitList = new ArrayList<ChannelMonthLimit>();
		Connection conn = null;
		try {
			conn = super.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(GET_ALL_CHANNEL_MONTH_LIMIT);
			pstmt.setInt(1, month);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				ChannelMonthLimit channelMonthLimit = new ChannelMonthLimit();
				channelMonthLimit.setId(rs.getString(1));
				channelMonthLimit.setLimitAmount(new Long(rs.getLong(2)));
				channelMonthLimit.setMonth(new Integer(rs.getInt(3)));
				channelMonthLimit.setChannelId(rs.getString(4));
				channelMonthLimitList.add(channelMonthLimit);
			}
			super.closeResultSet(rs);
			super.closePstmt(pstmt);
		} catch (SQLException e) {
			logger.error("exception when getChannelDayLimits", e);
			throw new DaoException(e.getMessage());
		} finally {
			super.closeConnction(conn);
		}
		return channelMonthLimitList;
	}
}
