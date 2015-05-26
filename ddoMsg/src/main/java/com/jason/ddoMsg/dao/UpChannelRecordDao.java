/**
 * 
 */
package com.jason.ddoMsg.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.jason.ddoMsg.bean.msg.UpChannelRecord;
import com.jason.ddoMsg.util.UUIDGenerator;

/**
 * 上行渠道记录Dao 处理上行渠道记录数据与数据库的交互逻揖
 * 
 * @author jasonzhang
 *
 */
public class UpChannelRecordDao extends BaseDao {
	private static final Logger logger = Logger
			.getLogger(UpChannelRecordDao.class);
	private final static String INSERT_UP_CHANNEL_RECORD = "insert into ddo_up_channel_record(id, create_date, result_code, process_result, ddo_msg_id, repeat_flag, response_time, send_time)"
			+ " values(?, ?, ?, ?, ?, ?, ?, ?)";
	private final static String UPDATE_UP_CHANNEL_RECORD = "update ddo_up_channel_record set result_code = ?, repeat_flag = ?, send_time = ?, response_time = ? where id = ?";
	private final static String UPDATE_NOT_REPEAT = "update ddo_up_channel_record set repeat_flag = 0 where id = ?";
	private final static String INSERT_REPEAT_UP_RECORD = "insert into ddo_repeat_up_record(id, create_date, record_id, result_code, response_time)"
			+ " values(?, ? ,?, ?, ?)";
	private final static String GET_NEED_REPEAT_UP_RECORD = "select t1.id, t1.create_date, t1.result_code, t1.process_result, t1.ddo_msg_id "
			+ "from ddo_up_channel_record t1 where t1.repeat_flag = 1 limit 0, ?";

	/**
	 * 保存上行渠道记录
	 * 
	 * @param record
	 *            上行渠道记录
	 * @throws DaoException
	 */
	public void saveUpChannelRecord(UpChannelRecord record, boolean needRepeat)
			throws DaoException {
		// resultCode是请求外部网址返回的状态，要控制其长度比数据库中的长度短
		String resultCode = record.getResultCode();
		if (resultCode != null && resultCode.length() > 32) {
			record.setResultCode(resultCode.substring(0, 31));
		}
		Connection conn = null;
		try {
			conn = super.getConnection();
			PreparedStatement pstmt = conn
					.prepareStatement(INSERT_UP_CHANNEL_RECORD);
			pstmt.setString(1, record.getId());
			pstmt.setTimestamp(2, super.convertSqlDate(record.getCreateDate()));
			pstmt.setString(3, record.getResultCode());
			pstmt.setInt(4, record.getProcessResult().intValue());
			pstmt.setString(5, record.getDdoMsgId());
			pstmt.setShort(6, needRepeat ? (short) 1 : (short)0);
			pstmt.setTimestamp(7, super.convertSqlDate(record.getResponseTime()));
			pstmt.setTimestamp(8, super.convertSqlDate(record.getSendTime()));
			pstmt.executeUpdate();
			super.closePstmt(pstmt);
//			if (needRepeat) {
//				this.saveRepeatUpRecord(conn, pstmt, record.getId(), resultCode);
//			}
			conn.commit();
		} catch (SQLException e) {
			super.rollbackConnection(conn);
			logger.error("exception when saveUpChannelRecord", e);
			throw new DaoException(e.getMessage());
		} finally {
			super.closeConnction(conn);
		}
	}
	/**
	 * 更新消息状态为不重发
	 * @param id
	 * @throws DaoException
	 */
	public void updateNoRepeat(String id) throws DaoException {
		Connection conn = null;
		try {
			conn = super.getConnection();
			PreparedStatement pstmt = conn
					.prepareStatement(UPDATE_NOT_REPEAT);
			pstmt.setString(1, id);
			pstmt.executeUpdate();
			super.closePstmt(pstmt);
			conn.commit();
		} catch (SQLException e) {
			super.rollbackConnection(conn);
			logger.error("exception when updateResultCode", e);
			throw new DaoException(e.getMessage());
		} finally {
			super.closeConnction(conn);
		}
	}

	/**
	 * 更新返回消息编码
	 * 
	 * @param id
	 *            唯一标识
	 * @param resultCode
	 *            消息编码
	 * @param needRepeat
	 *            是否需要重新上行
	 * @throws DaoException
	 */
	public void updateResultCode(String id, String resultCode,
			boolean needRepeat, Date sendTime, Date responseTime) throws DaoException {
		// resultCode是请求外部网址返回的状态，要控制其长度比数据库中的长度短
		if (resultCode != null && resultCode.length() > 32) {
			resultCode = resultCode.substring(0, 31);
		}
		Timestamp sendTimestamp = super.convertSqlDate(sendTime);
		Timestamp responseTimestamp = super.convertSqlDate(responseTime);
		Connection conn = null;
		try {
			conn = super.getConnection();
			PreparedStatement pstmt = conn
					.prepareStatement(UPDATE_UP_CHANNEL_RECORD);
			pstmt.setString(1, resultCode);
			pstmt.setShort(2, needRepeat ? (short) 1 : (short) 0);
			pstmt.setTimestamp(3, sendTimestamp);
			pstmt.setTimestamp(4, responseTimestamp);
			pstmt.setString(5, id);
			pstmt.executeUpdate();
			super.closePstmt(pstmt);
			if (needRepeat) {
				this.saveRepeatUpRecord(conn, pstmt, id, resultCode, sendTimestamp, responseTimestamp);
			}
			conn.commit();
		} catch (SQLException e) {
			super.rollbackConnection(conn);
			logger.error("exception when updateResultCode", e);
			throw new DaoException(e.getMessage());
		} finally {
			super.closeConnction(conn);
		}
	}

	/**
	 * 获取需要重新上行的记录
	 * 
	 * @param limitNums
	 *            获取数目
	 * @return
	 * @throws DaoException
	 */
	public List<UpChannelRecord> getNeedUpChannel(int limitNums)
			throws DaoException {
		List<UpChannelRecord> records = new ArrayList<UpChannelRecord>(
				limitNums);
		Connection conn = null;
		try {
			conn = super.getConnection();
			PreparedStatement pstmt = conn
					.prepareStatement(GET_NEED_REPEAT_UP_RECORD);
			pstmt.setInt(1, limitNums);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				UpChannelRecord msg = new UpChannelRecord();
				this.setParamToUpRecord(msg, rs);
				records.add(msg);
			}
			super.closeResultSet(rs);
			super.closePstmt(pstmt);
		} catch (SQLException e) {

			logger.error("exception when getNeedUpChannel", e);
			throw new DaoException(e.getMessage());
		} finally {
			super.closeConnction(conn);
		}
		return records;
	}

	private void saveRepeatUpRecord(Connection conn, PreparedStatement pstmt,
			String id, String resultCode, Timestamp sendTimestamp, Timestamp responseTimestamp) throws SQLException, DaoException {
		pstmt = conn.prepareStatement(INSERT_REPEAT_UP_RECORD);
		pstmt.setString(1, (new UUIDGenerator()).generate());
		pstmt.setTimestamp(2, sendTimestamp);
		pstmt.setString(3, id);
		pstmt.setString(4, resultCode);
		pstmt.setTimestamp(5, responseTimestamp);
		pstmt.executeUpdate();
		super.closePstmt(pstmt);
	}

	private void setParamToUpRecord(UpChannelRecord msg, ResultSet rs)
			throws SQLException {
		msg.setId(rs.getString(1));
		msg.setCreateDate(rs.getDate(2));
		msg.setResultCode(rs.getString(3));
		msg.setProcessResult(rs.getInt(4));
		msg.setDdoMsgId(rs.getString(5));
	}
}
