/**
 * 
 */
package com.jason.ddoTimingTask.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.jason.ddoTimingTask.bean.SmRequest;

/**
 * 短信请求Dao
 * @author jasonzhang
 *
 */
public class SmRequestDao extends BaseDao {

	private static final Logger logger = Logger.getLogger(SmRequestDao.class);
	
	private final static String INSERT_SM_REQUEST = "insert into ddo_sm_request(id, response_no, response_state, response_time, send_time, sm_task_id)"
			+ " values(?, ?, ?, ?, ?, ?)";
	/**
	 * 保存短信请求
	 * @param record
	 * @throws DaoException
	 */
	public void saveSmRequest(SmRequest record) throws DaoException {
		Connection conn = null;
		try {
			conn = super.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(INSERT_SM_REQUEST);
			pstmt.setString(1, record.getId());
			pstmt.setString(2, record.getResponseNo());
			pstmt.setInt(3, record.getResponseState().intValue());
			pstmt.setTimestamp(4, super.convertSqlDate(record.getResponseTime()));
			pstmt.setTimestamp(5, super.convertSqlDate(record.getSendTime()));
			pstmt.setString(6, record.getSmTaskId());
			pstmt.executeUpdate();
			super.closePstmt(pstmt);
			conn.commit();
		} catch (SQLException e) {
			super.rollbackConnection(conn);
			logger.error("exception when saveSmRequest", e);
			throw new DaoException(e.getMessage());
		} finally {
			super.closeConnction(conn);
		}
	}
}
