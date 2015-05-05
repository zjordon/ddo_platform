/**
 * 
 */
package com.jason.ddoTimingTask.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.jason.ddoTimingTask.bean.FullStatisticsDay;

/**
 * 全量统计Dao
 * 
 * @author jasonzhang
 *
 */
public class FullStatisticsDayDao extends BaseDao {

	private static final Logger logger = Logger
			.getLogger(FullStatisticsDayDao.class);

	private final static String GET_FULL_STATISTICS_DAY = "select id from ddo_full_statistics_day where sum_date = ?";
	private final static String INSERT_FULL_STATISTICS_DAY = "insert into ddo_full_statistics_day(id, sum_date, msisdn_num, sum_amount, msg_num, send_success_num, send_fail_num, bill_success_num, bill_fail_num)"
			+ " values(?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private final static String ADD_MSG_NUM = "update ddo_full_statistics_day set msg_num = msg_num + ? where id = ?";
	private final static String ADD_MSG_AND_MSISDN_NUM = "update ddo_full_statistics_day set msg_num = msg_num + ?, msisdn_num = msisdn_num + ? where id = ?";
	private static String ADD_SEND_SUCCESS_NUM = "update ddo_full_statistics_day set send_success_num = send_success_num + ? where id = ?";
	private static String ADD_SEND_FAIL_NUM = "update ddo_full_statistics_day set send_fail_num = send_fail_num + ? where id = ?";
	private static String ADD_BILL_SUCCESS_NUM = "update ddo_full_statistics_day set bill_success_num = bill_success_num + ?, sum_amount = sum_amount + ? where id = ?";
	private static String ADD_BILL_FAIL_NUM = "update ddo_full_statistics_day set bill_fail_num = bill_fail_num + ? where id = ?";
	private static String COUNT_EXIST_FULL_MSISDN = "select count(*) from ddo_exist_full_msisdn where msisdn = ? and sum_date = ?";
	private static String INSERT_EXIST_FULL_MSISDN = "insert into ddo_exist_full_msisdn(msisdn, sum_date) values(?, ?)";

	/**
	 * 获取全量统计记录
	 * 
	 * @param sumDate
	 * @return
	 * @throws DaoException
	 */
	public FullStatisticsDay getFullStatisticsDay(int sumDate)
			throws DaoException {
		FullStatisticsDay record = null;
		Connection conn = null;
		try {
			conn = super.getConnection();
			PreparedStatement pstmt = conn
					.prepareStatement(GET_FULL_STATISTICS_DAY);
			pstmt.setInt(1, sumDate);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				record = new FullStatisticsDay();
				record.setId(rs.getString(1));
			}
			super.closeResultSet(rs);
			super.closeStmt(pstmt);
		} catch (SQLException e) {

			logger.error("exception when getFullStatisticsDay", e);
			throw new DaoException(e.getMessage());
		} finally {
			super.closeConnction(conn);
		}
		return record;
	}

	public void saveFullStatisticsDay(FullStatisticsDay record)
			throws DaoException {
		Connection conn = null;
		try {
			conn = super.getConnection();
			PreparedStatement pstmt = conn
					.prepareStatement(INSERT_FULL_STATISTICS_DAY);
			pstmt.setString(1, record.getId());
			pstmt.setInt(2, record.getSumDate().intValue());
			pstmt.setInt(3, record.getMsgNum().intValue());
			pstmt.setDouble(4, record.getSumAmount().doubleValue());
			pstmt.setInt(5, record.getMsgNum().intValue());
			pstmt.setInt(6, record.getSendSuccessNum().intValue());
			pstmt.setInt(7, record.getSendFailNum().intValue());
			pstmt.setInt(8, record.getBillSuccessNum().intValue());
			pstmt.setInt(9, record.getBillFailNum().intValue());
			pstmt.executeUpdate();
			super.closePstmt(pstmt);
			conn.commit();
		} catch (SQLException e) {
			super.rollbackConnection(conn);
			logger.error("exception when saveFullStatisticsDay", e);
			throw new DaoException(e.getMessage());
		} finally {
			super.closeConnction(conn);
		}
	}

	/**
	 * 增加全量统计发送消息数
	 * 
	 * @param id
	 * @param num
	 * @throws DaoException
	 */
	public void addMsgNum(String id, int num) throws DaoException {
		this.updateOnlyNum(ADD_MSG_NUM, num, id);
	}

	/**
	 * 增加全量统计发送消息数和用户数
	 * 
	 * @param id
	 * @param num
	 * @throws DaoException
	 */
	public void addMsgAndMsisdnNum(String id, int num, int msisdnNum)
			throws DaoException {
		Connection conn = null;
		try {
			conn = super.getConnection();
			PreparedStatement pstmt = conn
					.prepareStatement(ADD_MSG_AND_MSISDN_NUM);
			pstmt.setInt(1, num);
			pstmt.setInt(2, msisdnNum);
			pstmt.setString(3, id);
			pstmt.executeUpdate();
			super.closePstmt(pstmt);
			conn.commit();
		} catch (SQLException e) {
			super.rollbackConnection(conn);
			logger.error("exception when addMsgAndMsisdnNum", e);
			throw new DaoException(e.getMessage());
		} finally {
			super.closeConnction(conn);
		}
	}

	/**
	 * 增加全量统计成功消息数
	 * 
	 * @param id
	 * @param num
	 * @throws DaoException
	 */
	public void addSendSuccessNum(String id, int num) throws DaoException {
		this.updateOnlyNum(ADD_SEND_SUCCESS_NUM, num, id);
	}

	/**
	 * 增加全量统计计费失败消息数
	 * 
	 * @param id
	 * @param num
	 * @throws DaoException
	 */
	public void addBillFailNum(String id, int num) throws DaoException {
		this.updateOnlyNum(ADD_BILL_FAIL_NUM, num, id);
	}

	/**
	 * 增加全量统计失败消息数
	 * 
	 * @param id
	 * @param num
	 * @throws DaoException
	 */
	public void addSendFailNum(String id, int num) throws DaoException {
		this.updateOnlyNum(ADD_SEND_FAIL_NUM, num, id);
	}

	/**
	 * 增加全量统计成功消息数
	 * 
	 * @param id
	 * @param num
	 * @throws DaoException
	 */
	public void addBillSuccessNum(String id, int num, double price)
			throws DaoException {
		Connection conn = null;
		try {
			conn = super.getConnection();
			PreparedStatement pstmt = conn
					.prepareStatement(ADD_BILL_SUCCESS_NUM);
			pstmt.setInt(1, num);
			pstmt.setDouble(2, price);
			pstmt.setString(3, id);
			pstmt.executeUpdate();
			super.closePstmt(pstmt);
			conn.commit();
		} catch (SQLException e) {
			super.rollbackConnection(conn);
			logger.error("exception when addBillSuccessNum", e);
			throw new DaoException(e.getMessage());
		} finally {
			super.closeConnction(conn);
		}
	}

	/**
	 * 号码是否已统计
	 * 
	 * @param sumDate
	 * @param msisdn
	 * @return
	 * @throws DaoException
	 */
	public boolean isMsisdnExist(int sumDate, long msisdn) throws DaoException {
		boolean exist = false;
		Connection conn = null;
		try {
			conn = super.getConnection();
			PreparedStatement pstmt = conn
					.prepareStatement(COUNT_EXIST_FULL_MSISDN);
			pstmt.setLong(1, msisdn);
			pstmt.setInt(2, sumDate);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				if (rs.getInt(1) > 0) {
					exist = true;
				}
			}
			super.closeResultSet(rs);
			super.closePstmt(pstmt);

		} catch (SQLException e) {
			super.rollbackConnection(conn);
			logger.error("exception when isMsisdnExist", e);
			throw new DaoException(e.getMessage());
		} finally {
			super.closeConnction(conn);
		}
		return exist;
	}

	public void saveMsisdn(long msisdn, int sumDate) throws DaoException {
		Connection conn = null;
		try {
			conn = super.getConnection();
			PreparedStatement pstmt = conn
					.prepareStatement(INSERT_EXIST_FULL_MSISDN);
			pstmt.setLong(1, msisdn);
			pstmt.setInt(2, sumDate);
			pstmt.executeUpdate();
			super.closePstmt(pstmt);
			conn.commit();
		} catch (SQLException e) {
			super.rollbackConnection(conn);
			logger.error("exception when saveMsisdn", e);
			throw new DaoException(e.getMessage());
		} finally {
			super.closeConnction(conn);
		}
	}

	private void updateOnlyNum(String sql, int num, String id)
			throws DaoException {
		Connection conn = null;
		try {
			conn = super.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			pstmt.setString(2, id);
			pstmt.executeUpdate();
			super.closePstmt(pstmt);
			conn.commit();
		} catch (SQLException e) {
			super.rollbackConnection(conn);
			logger.error("exception when updateOnlyNum", e);
			throw new DaoException(e.getMessage());
		} finally {
			super.closeConnction(conn);
		}
	}
}
