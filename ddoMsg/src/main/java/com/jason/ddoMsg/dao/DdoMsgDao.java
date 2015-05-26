/**
 * 
 */
package com.jason.ddoMsg.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import com.jason.ddoMsg.bean.msg.DdoMsg;
import com.jason.ddoMsg.util.UUIDGenerator;

/**
 * DDO消息dao 处理ddo消息与数据库交付的逻揖
 * 
 * @author jasonzhang
 *
 */
public class DdoMsgDao extends BaseDao {

	private static final Logger logger = Logger.getLogger(DdoMsgDao.class);
	private final static String INSERT_DDO_MSG = "insert into ddo_msg(id, msisdn, billing_business_id, send_result, msisdn_province_code, msisdn_city_code, channel_id, request_id, create_date)"
			+ " values(?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private final static String UPDATE_MSG_TRANSATION_ID = "update ddo_msg set transation_id = ?, return_msg_code = ?, send_result = ?, send_time = ?, repeat_flag=0, response_time = ? where id = ?";
	private final static String UPDATE_MSG_RET_MSG_CODE = "update ddo_msg set return_msg_code = ?, send_result = ?, send_time = ?, repeat_flag = ?, response_time = ? where id = ?";
	private final static String UPDATE_MSG_BILLING_CODE = "update ddo_msg set bill_state_code = ?, bill_state_time = ? where id = ?";
	private final static String UPDATE_MSG_SEND_RESULT = "update ddo_msg set send_result = ?, repeat_flag = ? where id = ?";
	private final static String GET_DDO_MSG_BY_TRANSID = "select id, msisdn, billing_business_id, send_result, msisdn_province_code, msisdn_city_code, "
			+ "channel_id, request_id, transation_id, create_date from ddo_msg "
			+ "where transation_id in($ids)";
	private final static String GET_DDO_MSG_BY_REQID = "select id, msisdn, billing_business_id, send_result, msisdn_province_code, msisdn_city_code, "
			+ "channel_id, request_id, transation_id, create_date from ddo_msg "
			+ "where request_id = ?";
	private final static String INSERT_REPEAT_MSG_RECORD = "insert into ddo_repeat_msg_record(id, create_date, return_msg_code, ddo_msg_id, response_time)"
			+ " values(?, ?, ?, ?, ?)";
	private final static String GET_DDO_MSG = "select id, msisdn, billing_business_id, send_result, msisdn_province_code, msisdn_city_code, "
			+ "channel_id, request_id, transation_id, create_date from ddo_msg "
			+ "where id = ?";
	private final static String GET_NEED_REPEAT_MSG = "select id, msisdn, billing_business_id, send_result, msisdn_province_code, msisdn_city_code, "
			+ "channel_id, request_id, transation_id, create_date from ddo_msg"
			+ " where repeat_flag = 1 limit 0, ?";

	/**
	 * 保存消息列表
	 * 
	 * @param msgList
	 * @throws DaoException
	 */
	public void saveMsgList(List<DdoMsg> msgList) throws DaoException {
		Connection conn = null;
		try {
			conn = super.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(INSERT_DDO_MSG);
			for (DdoMsg ddoMsg : msgList) {
				this.setDdoMsgParam(ddoMsg, pstmt);
				pstmt.addBatch();
			}
			pstmt.executeBatch();
			super.closePstmt(pstmt);
			conn.commit();
		} catch (SQLException e) {
			super.rollbackConnection(conn);
			logger.error("exception when saveChannelRequest", e);
			throw new DaoException(e.getMessage());
		} finally {
			super.closeConnction(conn);
		}
	}

	/**
	 * 更新消息事务ID和返回编码
	 * 
	 * @param id
	 *            唯一标识
	 * @param transationId
	 *            事务ID
	 * @param returnMsgCode
	 *            返回编码
	 * @param sendResult
	 *            发送结果
	 * @param sendTime
	 *            发送时间
	 * @throws DaoException
	 */
	public void updateMsgTransationId(String id, String transationId,
			String returnMsgCode, int sendResult, Date sendTime, Date responseTime)
			throws DaoException {
		Connection conn = null;
		try {
			conn = super.getConnection();
			PreparedStatement pstmt = conn
					.prepareStatement(UPDATE_MSG_TRANSATION_ID);
			pstmt.setString(1, transationId);
			pstmt.setString(2, returnMsgCode);
			pstmt.setInt(3, sendResult);
			pstmt.setTimestamp(4, super.convertSqlDate(sendTime));
			pstmt.setTimestamp(5, super.convertSqlDate(responseTime));
			pstmt.setString(6, id);
			pstmt.executeUpdate();
			super.closePstmt(pstmt);
			conn.commit();
		} catch (SQLException e) {
			super.rollbackConnection(conn);
			logger.error("exception when saveChannelRequest", e);
			throw new DaoException(e.getMessage());
		} finally {
			super.closeConnction(conn);
		}
	}

	/**
	 * 更新消息返回编码
	 * 
	 * @param id
	 *            唯一标识
	 * @param returnMsgCode
	 *            返回编码
	 * @param sendResult
	 *            发送结果
	 * @param sendTime
	 *            发送时间
	 * @throws DaoException
	 */
	public void updateMsgRetMsgCode(String id, String returnMsgCode,
			int sendResult, Date sendTime, boolean repeat, Date responseTime) throws DaoException {
		Connection conn = null;
		Timestamp sendDate = super.convertSqlDate(sendTime);
		Timestamp responseDate = super.convertSqlDate(responseTime);
		try {
			conn = super.getConnection();
			PreparedStatement pstmt = conn
					.prepareStatement(UPDATE_MSG_RET_MSG_CODE);
			pstmt.setString(1, returnMsgCode);
			pstmt.setInt(2, sendResult);
			pstmt.setTimestamp(3, sendDate);
			pstmt.setShort(4, repeat ? (short) 1 : (short) 0);
			pstmt.setTimestamp(5, responseDate);
			pstmt.setString(6, id);
			pstmt.executeUpdate();
			super.closePstmt(pstmt);
			if (repeat) {
				// 该类消息都是需要重发的
				pstmt = conn.prepareStatement(INSERT_REPEAT_MSG_RECORD);
				pstmt.setString(1, (new UUIDGenerator()).generate());
				pstmt.setString(3, returnMsgCode);
				pstmt.setTimestamp(2, sendDate);
				pstmt.setString(4, id);
				pstmt.setTimestamp(5, responseDate);
				pstmt.executeUpdate();
				super.closePstmt(pstmt);
			}
			
			conn.commit();
		} catch (SQLException e) {
			super.rollbackConnection(conn);
			logger.error("exception when saveChannelRequest", e);
			throw new DaoException(e.getMessage());
		} finally {
			super.closeConnction(conn);
		}
	}

	/**
	 * 更新消息计费编码
	 * 
	 * @param id
	 *            唯一标识
	 * @param billingCode
	 *            计费编码
	 * @param billingStateTime
	 *            计费编码返回时间
	 * @throws DaoException
	 */
	public void updateMsgBillingCode(String id, String billingCode,
			Date billingStateTime) throws DaoException {
		Connection conn = null;
		try {
			conn = super.getConnection();
			PreparedStatement pstmt = conn
					.prepareStatement(UPDATE_MSG_BILLING_CODE);
			pstmt.setString(1, billingCode);
			pstmt.setTimestamp(2, super.convertSqlDate(billingStateTime));
			pstmt.setString(3, id);
			pstmt.executeUpdate();
			super.closePstmt(pstmt);
			conn.commit();
		} catch (SQLException e) {
			super.rollbackConnection(conn);
			logger.error("exception when saveChannelRequest", e);
			throw new DaoException(e.getMessage());
		} finally {
			super.closeConnction(conn);
		}
	}

	/**
	 * 更新消息的发送状态
	 * 
	 * @param id
	 * @param sendResult
	 * @throws DaoException
	 */
	public void updateMsgSendResult(String id, int sendResult,
			boolean needRepeat) throws DaoException {
		Connection conn = null;
		try {
			conn = super.getConnection();
			PreparedStatement pstmt = conn
					.prepareStatement(UPDATE_MSG_SEND_RESULT);
			pstmt.setInt(1, sendResult);
			pstmt.setShort(2, needRepeat ? (short) 1 : (short) 0);
			pstmt.setString(3, id);
			pstmt.executeUpdate();
			super.closePstmt(pstmt);
			conn.commit();
		} catch (SQLException e) {
			super.rollbackConnection(conn);
			logger.error("exception when saveChannelRequest", e);
			throw new DaoException(e.getMessage());
		} finally {
			super.closeConnction(conn);
		}
	}

	/**
	 * 获取消息列表
	 * 
	 * @param transationIds
	 *            事务id列表
	 * @return
	 * @throws DaoException
	 */
	public List<DdoMsg> getDdoMsgs(List<String> transationIds)
			throws DaoException {
		List<DdoMsg> ddoMsgList = new ArrayList<DdoMsg>();
		StringBuilder builder = new StringBuilder();
		for (String transationId : transationIds) {
			builder.append('\'').append(transationId).append("',");
		}
		builder.deleteCharAt(builder.length() - 1);
		String sql = StringUtils.replace(GET_DDO_MSG_BY_TRANSID, "$ids",
				builder.toString());
		Connection conn = null;
		try {
			conn = super.getConnection();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				DdoMsg ddoMsg = new DdoMsg();
				this.setParamToDdoMsg(ddoMsg, rs);
				ddoMsgList.add(ddoMsg);
			}

			super.closeResultSet(rs);
			super.closeStmt(stmt);
		} catch (SQLException e) {

			logger.error("exception when getDdoMsgs", e);
			throw new DaoException(e.getMessage());
		} finally {
			super.closeConnction(conn);
		}
		return ddoMsgList;
	}

	/**
	 * 获取某批请求的消息列表
	 * 
	 * @param reqId
	 *            请求id
	 * @return
	 * @throws DaoException
	 */
	public List<DdoMsg> getDdoMsgByReqId(String reqId) throws DaoException {
		List<DdoMsg> ddoMsgList = new ArrayList<DdoMsg>();
		Connection conn = null;
		try {
			conn = super.getConnection();
			PreparedStatement pstmt = conn
					.prepareStatement(GET_DDO_MSG_BY_REQID);
			pstmt.setString(1, reqId);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				DdoMsg ddoMsg = new DdoMsg();
				this.setParamToDdoMsg(ddoMsg, rs);
				ddoMsgList.add(ddoMsg);
			}

			super.closeResultSet(rs);
			super.closeStmt(pstmt);
		} catch (SQLException e) {

			logger.error("exception when getDdoMsgByReqId", e);
			throw new DaoException(e.getMessage());
		} finally {
			super.closeConnction(conn);
		}
		return ddoMsgList;
	}

	/**
	 * 获取需要重发的消息列表
	 * 
	 * @param limitNums
	 *            获取条数
	 * @return
	 * @throws DaoException
	 */
	public List<DdoMsg> getNeedRepeatMsg(int limitNums) throws DaoException {
		List<DdoMsg> msgList = new ArrayList<DdoMsg>(limitNums);
		Connection conn = null;
		try {
			conn = super.getConnection();
			PreparedStatement pstmt = conn
					.prepareStatement(GET_NEED_REPEAT_MSG);
			pstmt.setInt(1, limitNums);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				DdoMsg msg = new DdoMsg();
				this.setParamToDdoMsg(msg, rs);
				msgList.add(msg);
			}
			super.closeResultSet(rs);
			super.closePstmt(pstmt);

		} catch (SQLException e) {

			logger.error("exception when getNeedRepeatMsg", e);
			throw new DaoException(e.getMessage());
		} finally {
			super.closeConnction(conn);
		}
		return msgList;
	}

	/**
	 * 获取单条消息
	 * 
	 * @param id
	 * @return
	 * @throws DaoException
	 */
	public DdoMsg getDdoMsg(String id) throws DaoException {
		DdoMsg ddoMsg = null;
		Connection conn = null;
		try {
			conn = super.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(GET_DDO_MSG);
			pstmt.setString(1, id);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				ddoMsg = new DdoMsg();
				this.setParamToDdoMsg(ddoMsg, rs);
			}
			super.closeResultSet(rs);
			super.closeStmt(pstmt);
		} catch (SQLException e) {

			logger.error("exception when getDdoMsgByReqId", e);
			throw new DaoException(e.getMessage());
		} finally {
			super.closeConnction(conn);
		}
		return ddoMsg;
	}

	private void setDdoMsgParam(DdoMsg ddoMsg, PreparedStatement pstmt)
			throws SQLException {
		pstmt.setString(1, ddoMsg.getId());
		pstmt.setLong(2, ddoMsg.getMsisdn().longValue());
		pstmt.setString(3, ddoMsg.getBillingBusinessId());
		pstmt.setInt(4, ddoMsg.getSendResult().intValue());
		pstmt.setString(5, ddoMsg.getMsisdnProvinceCode());
		pstmt.setString(6, ddoMsg.getMsisdnCityCode());
		pstmt.setString(7, ddoMsg.getChannelId());
		pstmt.setString(8, ddoMsg.getRequestId());
		pstmt.setTimestamp(9, super.convertSqlDate(ddoMsg.getCreateDate()));
	}

	private void setParamToDdoMsg(DdoMsg ddoMsg, ResultSet rs)
			throws SQLException {
		ddoMsg.setId(rs.getString(1));
		ddoMsg.setMsisdn(new Long(rs.getLong(2)));
		ddoMsg.setBillingBusinessId(rs.getString(3));
		ddoMsg.setSendResult(new Integer(rs.getInt(4)));
		ddoMsg.setMsisdnProvinceCode(rs.getString(5));
		ddoMsg.setMsisdnCityCode(rs.getString(6));
		ddoMsg.setChannelId(rs.getString(7));
		ddoMsg.setRequestId(rs.getString(8));
		ddoMsg.setTransationId(rs.getString(9));
		ddoMsg.setCreateDate(rs.getDate(10));
	}
}
