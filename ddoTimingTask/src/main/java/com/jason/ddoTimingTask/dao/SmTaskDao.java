/**
 * 
 */
package com.jason.ddoTimingTask.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.jason.ddoTimingTask.bean.SmTask;

/**
 * 短信任务Dao
 * 
 * @author jasonzhang
 *
 */
public class SmTaskDao extends BaseDao {
	private static final Logger logger = Logger.getLogger(SmTaskDao.class);
	private final static String GET_SM_TASKS = "select id, msisdn_num, recapture, send_type, time_to_send_time, bill_business_id, channel_user_name, channel_user_pass from ddo_sm_task where state = 0 limit 0, ?";
	private final static String UPDATE_STATE_SUCCESS = "update ddo_sm_task set state = 2, execute_time = ? where id = ?";
	private final static String UPDATE_STATE_FAIL = "update ddo_sm_task set state = 3, fail_msg = ?, execute_time = ? where id = ?";
	/**
	 * 获取未处理短信任务列表
	 * 
	 * @param num
	 * @return
	 * @throws DaoException
	 */
	public List<SmTask> getSmTaskList(int num) throws DaoException {
		List<SmTask> list = new ArrayList<SmTask>(num);
		Connection conn = null;
		try {
			conn = super.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(GET_SM_TASKS);
			pstmt.setInt(1, num);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				SmTask record = new SmTask();
				this.setParamToRecord(record, rs);
				list.add(record);
			}

			super.closeResultSet(rs);
			super.closeStmt(pstmt);
		} catch (SQLException e) {

			logger.error("exception when getSmTaskList", e);
			throw new DaoException(e.getMessage());
		} finally {
			super.closeConnction(conn);
		}
		return list;
	}

	/**
	 * 更新短信任务状态为成功
	 * 
	 * @param id
	 * @throws DaoException
	 */
	public void updateStateSuccess(String id, Date executeTime) throws DaoException {
		Connection conn = null;
		try {
			conn = super.getConnection();
			PreparedStatement pstmt = conn
					.prepareStatement(UPDATE_STATE_SUCCESS);
			pstmt.setTimestamp(1, super.convertSqlDate(executeTime));
			pstmt.setString(2, id);
			pstmt.executeUpdate();
			super.closePstmt(pstmt);
			conn.commit();
		} catch (SQLException e) {
			super.rollbackConnection(conn);
			logger.error("exception when updateStateSuccess", e);
			throw new DaoException(e.getMessage());
		} finally {
			super.closeConnction(conn);
		}
	}

	/**
	 * 更新短信任务状态为失败
	 * @param id
	 * @param failMsg
	 * @throws DaoException
	 */
	public void updateStateFail(String id, String failMsg, Date executeTime) throws DaoException {
		Connection conn = null;
		try {
			conn = super.getConnection();
			PreparedStatement pstmt = conn
					.prepareStatement(UPDATE_STATE_FAIL);
			pstmt.setString(1, failMsg);
			pstmt.setTimestamp(2, super.convertSqlDate(executeTime));
			pstmt.setString(3, id);
			pstmt.executeUpdate();
			super.closePstmt(pstmt);
			conn.commit();
		} catch (SQLException e) {
			super.rollbackConnection(conn);
			logger.error("exception when updateStateFail", e);
			throw new DaoException(e.getMessage());
		} finally {
			super.closeConnction(conn);
		}
	}

	private void setParamToRecord(SmTask record, ResultSet rs)
			throws SQLException {
		record.setId(rs.getString(1));
		record.setMsisdnNum(new Integer(rs.getInt(2)));
		record.setRecapture(new Integer(rs.getInt(3)));
		record.setSendType(new Integer(rs.getInt(4)));
		record.setTimeToSendTime(rs.getDate(5));
		record.setBillBusinessId(rs.getString(6));
		record.setChannelUserName(rs.getString(7));
		record.setChannelUserPass(rs.getString(8));
	}
}
