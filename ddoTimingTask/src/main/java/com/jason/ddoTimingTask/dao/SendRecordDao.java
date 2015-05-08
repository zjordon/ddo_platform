/**
 * 
 */
package com.jason.ddoTimingTask.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.jason.ddoTimingTask.bean.SendRecord;

/**
 * 发送记录Dao
 * @author jasonzhang
 *
 */
public class SendRecordDao extends BaseDao {
	
	private static final Logger logger = Logger.getLogger(SendRecordDao.class);
	private final static String GET_SEND_RECORDS = "select id, ddo_msg_id, msisdn, channel_id, billing_business_id, send_date, state from ddo_send_record where state = 0 limit 0, ?";
	private final static String GET_SEND_RECORD = "select id, ddo_msg_id, msisdn, channel_id, billing_business_id, send_date, state from ddo_send_record where ddo_msg_id = ?";
	private final static String UPDATE_STATE_PROCESSED = "update ddo_send_record set state = 1 where id = ?";

	/**
	 * 获取发送记录列表
	 * @param num 获取数目
	 * @return
	 */
	public List<SendRecord> getSendRecordList(int num) throws DaoException {
		List<SendRecord> list = new ArrayList<SendRecord>(num);
		Connection conn = null;
		try {
			conn = super.getConnection();
			PreparedStatement pstmt = conn
					.prepareStatement(GET_SEND_RECORDS);
			pstmt.setInt(1, num);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				SendRecord sendRecord = new SendRecord();
				this.setParamToSendRecord(sendRecord, rs);
				list.add(sendRecord);
			}

			super.closeResultSet(rs);
			super.closeStmt(pstmt);
		} catch (SQLException e) {

			logger.error("exception when getSendRecordList", e);
			throw new DaoException(e.getMessage());
		} finally {
			super.closeConnction(conn);
		}
		return list;
	}
	/**
	 * 获取发送记录
	 * @param ddoMsgId ddo消息标识
	 * @return
	 * @throws DaoException
	 */
	public SendRecord getSendRecord(String ddoMsgId) throws DaoException {
		SendRecord record = null;
		Connection conn = null;
		try {
			conn = super.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(GET_SEND_RECORD);
			pstmt.setString(1, ddoMsgId);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				record = new SendRecord();
				this.setParamToSendRecord(record, rs);
			}
			super.closeResultSet(rs);
			super.closeStmt(pstmt);
		} catch (SQLException e) {

			logger.error("exception when getSendRecord", e);
			throw new DaoException(e.getMessage());
		} finally {
			super.closeConnction(conn);
		}
		return record;
	}
	/**
	 * 更新发送记录状态为已处理
	 * @param id 唯一标识
	 */
	public void updateStateProcessed(String id) throws DaoException {
		Connection conn = null;
		try {
			conn = super.getConnection();
			PreparedStatement pstmt = conn
					.prepareStatement(UPDATE_STATE_PROCESSED);
			pstmt.setString(1, id);
			pstmt.executeUpdate();
			super.closePstmt(pstmt);
			conn.commit();
		} catch (SQLException e) {
			super.rollbackConnection(conn);
			logger.error("exception when updateStateProcessed", e);
			throw new DaoException(e.getMessage());
		} finally {
			super.closeConnction(conn);
		}
	}
	
	private void setParamToSendRecord(SendRecord sendRecord, ResultSet rs)
			throws SQLException {
		sendRecord.setId(rs.getString(1));
		sendRecord.setDdoMsgId(rs.getString(2));
		sendRecord.setMsisdn(new Long(rs.getLong(3)));
		sendRecord.setChannelId(rs.getString(4));
		sendRecord.setBillingBusinessId(rs.getString(5));
		sendRecord.setSendDate(new Integer(rs.getInt(6)));
		sendRecord.calcuateSendMonth();
		sendRecord.setState(rs.getInt(7));
	}
}
