/**
 * 
 */
package com.jason.ddoMsg.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.jason.ddoMsg.bean.msg.ChannelRequest;
import com.jason.ddoMsg.bean.msg.RequestMsisdn;

/**
 * 外部请求Dao
 * @author jasonzhang
 *
 */
public class ChannelRequestDao extends BaseDao {
	private static final Logger logger = Logger.getLogger(ChannelRequestDao.class);
	private final static String INSERT_CHANNEL_REQUEST = 
			"insert into ddo_channel_request(id, username, password, content, product_id, dstime, request_time, state, channel_id, source_type, return_state)"
			+ " values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private final static String INSERT_REQUEST_MSISDN = 
			"insert into ddo_request_msisdn(id, msisdn, request_id) values(?, ?, ?)";
	private final static String UPDATE_PROCESS_RESULT = 
			"update ddo_channel_request set state = ?, begin_time = ?, end_time = ?, process_result = ? where id = ?";
	private final static String UPDATE_STATE =
			"update ddo_channel_request set state = ? where id = ?";
	private final static String GET_PENDING_TIMING_REQUEST = 
			"select id, username, password, content, product_id, dstime, request_time, state, channel_id, source_type from ddo_channel_request "
			+ "where dstime is not null and dstime <=?  and state = 0 limit 0, ?";
	private final static String GET_UNEXPECTED_END_REQUEST = 
			"select id, username, password, content, product_id, dstime, request_time, state, channel_id, source_type from ddo_channel_request "
			+ "where state = 3 limit 0, ?";
	private final static String GET_REQUEST_MSISDN_BY_REQ_ID = 
			"select id, msisdn from ddo_request_msisdn where request_id = ?";
	private final static String UPDATE_REQUEST_PROCESSING = 
			"update ddo_channel_request set state = 1 where id = ?";
	private final static String GET_REQUEST = "select id, username, password, content, product_id, dstime, request_time, state, channel_id, source_type from ddo_channel_request"
			+ " where id = ?";

	/**
	 * 保存外部请求列表
	 * @param requestList
	 * @throws DaoException
	 */
	public void saveChannelRequest(List<ChannelRequest> requestList) throws DaoException {
		logger.debug("save the requestList,requestList size is " + requestList.size());
		Connection conn = null;
		try {
			conn = super.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(INSERT_CHANNEL_REQUEST);
			List<RequestMsisdn> requestMsisdnList = new ArrayList<RequestMsisdn>();
			for (ChannelRequest request : requestList) {
				this.setChannelRequestParam(request, pstmt);
				pstmt.addBatch();
				if (request.getRequestMsisdns() != null && !request.getRequestMsisdns().isEmpty()) {
					requestMsisdnList.addAll(request.getRequestMsisdns());
				}
				
			}
			pstmt.executeBatch();
			super.closePstmt(pstmt);
			pstmt = conn.prepareStatement(INSERT_REQUEST_MSISDN);
			for (RequestMsisdn requestMsisdn : requestMsisdnList) {
				this.setRequestMsisdnParam(requestMsisdn, pstmt);
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
	 * 更新处理结果
	 * @param channelResult
	 * @throws DaoException
	 */
	public void updateProcessResult(String id, int state, Date beginTime, Date endTime, int processResult) throws DaoException {
		Connection conn = null;
		try {
			conn = super.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(UPDATE_PROCESS_RESULT);
			pstmt.setInt(1, state);
			pstmt.setTimestamp(2, super.convertSqlDate(beginTime));
			pstmt.setTimestamp(3, super.convertSqlDate(endTime));
			pstmt.setInt(4, processResult);
			pstmt.setString(5, id);
			pstmt.executeUpdate();
			super.closePstmt(pstmt);
			conn.commit();
		} catch (SQLException e) {
			super.rollbackConnection(conn);
			logger.error("exception when updateProcessResult", e);
			throw new DaoException(e.getMessage());
		} finally {
			super.closeConnction(conn);
		}
	}
	/**
	 * 更新状态
	 * @param id 唯一标识
	 * @param state 状态
	 * @throws DaoException
	 */
	public void updateState(String id, int state) throws DaoException {
		Connection conn = null;
		try {
			conn = super.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(UPDATE_STATE);
			pstmt.setInt(1, state);
			pstmt.setString(2, id);
			pstmt.executeUpdate();
			super.closePstmt(pstmt);
			conn.commit();
		} catch (SQLException e) {
			super.rollbackConnection(conn);
			logger.error("exception when updateState", e);
			throw new DaoException(e.getMessage());
		} finally {
			super.closeConnction(conn);
		}
	}
	
	/**
	 * 获取待发送的定时请求
	 * @param limitDate 待发送请求的时间限制，小于等于该时间的请求需要被返回
	 * @param limitNums 获取条数
	 * @return
	 * @throws SQLException
	 */
	public List<ChannelRequest> getPendingTimingRequests(Date limitDate, int limitNums) throws DaoException {
		List<ChannelRequest> requests = new ArrayList<ChannelRequest>();
		Connection conn = null;
		try {
			conn = super.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(GET_PENDING_TIMING_REQUEST);
			java.sql.Timestamp convertDate = super.convertSqlDate(limitDate);
			pstmt.setTimestamp(1, convertDate);
			pstmt.setInt(2, limitNums);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				ChannelRequest request = new ChannelRequest();
				this.setParamToRequest(request, rs);
				requests.add(request);
			}
			super.closeResultSet(rs);
			super.closePstmt(pstmt);
			this.fetchRelationMsisdns(requests, conn, pstmt);
			this.updateRequestProcessing(requests, conn, pstmt);
			super.closePstmt(pstmt);
			conn.commit();
		} catch (SQLException e) {
			super.rollbackConnection(conn);
			logger.error("exception when updateProcessResult", e);
			throw new DaoException(e.getMessage());
		} finally {
			super.closeConnction(conn);
		}
		return requests;
	}
	
	/**
	 * 获取异常中断的请求
	 * @param limitNums 获取条数
	 * @return
	 * @throws DaoException
	 */
	public List<ChannelRequest> getUnexpectedEndRequests(int limitNums) throws DaoException {
		List<ChannelRequest> requests = new ArrayList<ChannelRequest>();
		Connection conn = null;
		try {
			conn = super.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(GET_UNEXPECTED_END_REQUEST);
			pstmt.setInt(1, limitNums);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				ChannelRequest request = new ChannelRequest();
				this.setParamToRequest(request, rs);
				requests.add(request);
			}
			super.closeResultSet(rs);
			super.closePstmt(pstmt);
			this.fetchRelationMsisdns(requests, conn, pstmt);
			this.updateRequestProcessing(requests, conn, pstmt);
			super.closePstmt(pstmt);
			conn.commit();
		} catch (SQLException e) {
			super.rollbackConnection(conn);
			logger.error("exception when getUnexpectedEndRequests", e);
			throw new DaoException(e.getMessage());
		} finally {
			super.closeConnction(conn);
		}
		return requests;
	}
	
	public ChannelRequest getChannelRequest(String id) throws DaoException {
		ChannelRequest request = null;
		Connection conn = null;
		try {
			conn = super.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(GET_REQUEST);
			pstmt.setString(1, id);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				request = new ChannelRequest();
				this.setParamToRequest(request, rs);
			}
			super.closeResultSet(rs);
			super.closePstmt(pstmt);
		} catch (SQLException e) {
			logger.error("exception when getChannelRequest", e);
			throw new DaoException(e.getMessage());
		} finally {
			super.closeConnction(conn);
		}
		return request;
	}
	
	/**
	 * 获取请求相关联的手机号码对象
	 * @param requests
	 * @param conn
	 * @throws SQLException
	 * @throws DaoException
	 */
	private void fetchRelationMsisdns(List<ChannelRequest> requests, Connection conn, PreparedStatement pstmt) throws SQLException, DaoException {
		ResultSet rs = null;
		for (ChannelRequest request : requests) {
			List<RequestMsisdn> requestMsisdns = new ArrayList<RequestMsisdn>();
			pstmt = conn.prepareStatement(GET_REQUEST_MSISDN_BY_REQ_ID);
			pstmt.setString(1, request.getId());
			rs = pstmt.executeQuery();
			while (rs.next()) {
				RequestMsisdn requestMsisdn = new RequestMsisdn();
				this.setParameterToReqMsisdn(requestMsisdn, rs);
				requestMsisdns.add(requestMsisdn);
			}
			super.closeResultSet(rs);
			
			request.setRequestMsisdns(requestMsisdns);
		}
	}
	
	/**
	 * 把到请求状态更新为正在处理中
	 * @param requests
	 * @param conn
	 * @throws SQLException
	 * @throws DaoException
	 */
	private void updateRequestProcessing(List<ChannelRequest> requests, Connection conn, PreparedStatement pstmt) throws SQLException, DaoException {
		pstmt = conn.prepareStatement(UPDATE_REQUEST_PROCESSING);
		for (ChannelRequest request : requests) {
			pstmt.setString(1, request.getId());
			pstmt.addBatch();
		}
		pstmt.executeBatch();
	}
	
	private void setChannelRequestParam(final ChannelRequest request, PreparedStatement pstmt) throws SQLException {
		pstmt.setString(1, request.getId());
		pstmt.setString(2, request.getUsername());
		pstmt.setString(3, request.getPassword());
		pstmt.setString(4, request.getContent());
		pstmt.setString(5, request.getProductId());
		pstmt.setTimestamp(6, super.convertSqlDate(request.getDstime()));
		pstmt.setTimestamp(7, super.convertSqlDate(request.getRequestTime()));
		pstmt.setInt(8, request.getState().intValue());
		pstmt.setString(9, request.getChannelId());
		pstmt.setInt(10, request.getSourceType().intValue());
		pstmt.setInt(11, request.getReturnState().intValue());
	}
	
	private void setRequestMsisdnParam(final RequestMsisdn requestMsisdn, PreparedStatement pstmt) throws SQLException {
		pstmt.setString(1, requestMsisdn.getId());
		pstmt.setLong(2, requestMsisdn.getMsisdn().longValue());
		pstmt.setString(3, requestMsisdn.getRequestId());
	}
	
	private void setParamToRequest(ChannelRequest request, ResultSet rs) throws SQLException {
		request.setId(rs.getString(1));
		request.setUsername(rs.getString(2));
		request.setPassword(rs.getString(3));
		request.setContent(rs.getString(4));
		request.setProductId(rs.getString(5));
		request.setDstime(rs.getDate(6));
		request.setRequestTime(rs.getDate(7));
		request.setState(new Integer(rs.getInt(8)));
		request.setChannelId(rs.getString(9));
		request.setSourceType(rs.getInt(10));
	}
	
	private void setParameterToReqMsisdn(RequestMsisdn requestMsisdn, ResultSet rs) throws SQLException {
		requestMsisdn.setId(rs.getString(1));
		requestMsisdn.setMsisdn(new Long(rs.getLong(2)));
	}
}
