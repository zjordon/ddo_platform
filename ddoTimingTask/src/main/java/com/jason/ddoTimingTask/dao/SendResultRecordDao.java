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

import com.jason.ddoTimingTask.bean.SendResultRecord;

/**
 * 发送结果记录Dao
 * @author jasonzhang
 *
 */
public class SendResultRecordDao extends BaseDao {

	private static final Logger logger = Logger.getLogger(SendResultRecordDao.class);
	private final static String GET_SEND_RESULT_RECORDS = "select id, ddo_msg_id, send_result, state from ddo_send_result_record where state = 0 limit 0, ?";
	private final static String GET_SEND_RESULT_RECORD = "select id, ddo_msg_id, send_result, state from ddo_send_result_record where ddo_msg_id = ?";
	private final static String UPDATE_STATE_PROCESSED = "update ddo_send_result_record set state = 1 where id = ?";

	/**
	 *获取发送结果记录列表
	 * @param num 获取数目
	 * @return
	 */
	public List<SendResultRecord> getSendResultRecordList(int num) throws DaoException {
		List<SendResultRecord> list = new ArrayList<SendResultRecord>(num);
		Connection conn = null;
		try {
			conn = super.getConnection();
			PreparedStatement pstmt = conn
					.prepareStatement(GET_SEND_RESULT_RECORDS);
			pstmt.setInt(1, num);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				SendResultRecord SendResultRecord = new SendResultRecord();
				this.setParamToSendResultRecord(SendResultRecord, rs);
				list.add(SendResultRecord);
			}

			super.closeResultSet(rs);
			super.closeStmt(pstmt);
		} catch (SQLException e) {

			logger.error("exception when getSendResultRecordList", e);
			throw new DaoException(e.getMessage());
		} finally {
			super.closeConnction(conn);
		}
		return list;
	}
	/**
	 * 获取发送结果记录
	 * @param ddoMsgId ddo消息标识
	 * @return
	 * @throws DaoException
	 */
	public SendResultRecord getSendResultRecord(String ddoMsgId) throws DaoException {
		SendResultRecord record = null;
		Connection conn = null;
		try {
			conn = super.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(GET_SEND_RESULT_RECORD);
			pstmt.setString(1, ddoMsgId);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				record = new SendResultRecord();
				this.setParamToSendResultRecord(record, rs);
			}
			super.closeResultSet(rs);
			super.closeStmt(pstmt);
		} catch (SQLException e) {

			logger.error("exception when getSendResultRecord", e);
			throw new DaoException(e.getMessage());
		} finally {
			super.closeConnction(conn);
		}
		return record;
	}
	/**
	 * 更新记录状态为已处理
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
	
	private void setParamToSendResultRecord(SendResultRecord sendResultRecord, ResultSet rs)
			throws SQLException {
		sendResultRecord.setId(rs.getString(1));
		sendResultRecord.setDdoMsgId(rs.getString(2));
		sendResultRecord.setSendResult(new Integer(rs.getInt(3)));
		sendResultRecord.setState(rs.getInt(4));
	}
}
