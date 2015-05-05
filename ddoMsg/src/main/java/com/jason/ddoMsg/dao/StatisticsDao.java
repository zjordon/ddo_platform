/**
 * 
 */
package com.jason.ddoMsg.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;

import com.jason.ddoMsg.bean.statistics.BillResultRecord;
import com.jason.ddoMsg.bean.statistics.SendRecord;
import com.jason.ddoMsg.bean.statistics.SendResultRecord;

/**
 * 统计dao
 * @author jasonzhang
 *
 */
public class StatisticsDao extends BaseDao {

	private static final Logger logger = Logger.getLogger(StatisticsDao.class);
	
	private final static String INSERT_SEND_RECORD = "insert into ddo_send_record(id, ddo_msg_id, msisdn, channel_id, billing_business_id, send_date)"
			+ " values(?, ?, ?, ?, ?, ?)";
	private final static String INSERT_SEND_RESULT_RECORD = "insert into ddo_send_result_record(id, ddo_msg_id,send_result)"
			+ " values(?, ?, ?)";
	private final static String INSERT_BILL_RESULT_RECORD = "insert into ddo_bill_result_record(id, ddo_msg_id,bill_result)"
			+ " values(?, ?, ?)";
	
	/**
	 * 保存发送列表
	 * @param list
	 * @throws DaoException
	 */
	public void saveSendRecordList(List<SendRecord> list) throws DaoException {
		Connection conn = null;
		try {
			conn = super.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(INSERT_SEND_RECORD);
			for (SendRecord record : list) {
				pstmt.setString(1, record.getId());
				pstmt.setString(2, record.getDdoMsgId());
				pstmt.setLong(3, record.getMsisdn().longValue());
				pstmt.setString(4, record.getChannelId());
				pstmt.setString(5, record.getBillingBusinessId());
				pstmt.setInt(6, record.getSendDate().intValue());
				pstmt.addBatch();
			}
			pstmt.executeBatch();
			super.closePstmt(pstmt);
			conn.commit();
		} catch (SQLException e) {
			super.rollbackConnection(conn);
			logger.error("exception when saveSendRecordList", e);
			throw new DaoException(e.getMessage());
		} finally {
			super.closeConnction(conn);
		}
	}
	

	/**
	 * 保存发送结果列表
	 * @param list
	 * @throws DaoException
	 */
	public void saveSendResultRecordList(List<SendResultRecord> list) throws DaoException {
		Connection conn = null;
		try {
			conn = super.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(INSERT_SEND_RESULT_RECORD);
			for (SendResultRecord record : list) {
				pstmt.setString(1, record.getId());
				pstmt.setString(2, record.getDdoMsgId());
				pstmt.setLong(3, record.getSendResult().intValue());
				pstmt.addBatch();
			}
			pstmt.executeBatch();
			super.closePstmt(pstmt);
			conn.commit();
		} catch (SQLException e) {
			super.rollbackConnection(conn);
			logger.error("exception when saveSendResultRecordList", e);
			throw new DaoException(e.getMessage());
		} finally {
			super.closeConnction(conn);
		}
	}
	
	/**
	 * 保存计费结果列表
	 * @param list
	 * @throws DaoException
	 */
	public void saveBillResultRecordList(List<BillResultRecord> list) throws DaoException {
		Connection conn = null;
		try {
			conn = super.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(INSERT_BILL_RESULT_RECORD);
			for (BillResultRecord record : list) {
				pstmt.setString(1, record.getId());
				pstmt.setString(2, record.getDdoMsgId());
				pstmt.setLong(3, record.getBillResult().intValue());
				pstmt.addBatch();
			}
			pstmt.executeBatch();
			super.closePstmt(pstmt);
			conn.commit();
		} catch (SQLException e) {
			super.rollbackConnection(conn);
			logger.error("exception when saveBillResultRecordList", e);
			throw new DaoException(e.getMessage());
		} finally {
			super.closeConnction(conn);
		}
	}
}
