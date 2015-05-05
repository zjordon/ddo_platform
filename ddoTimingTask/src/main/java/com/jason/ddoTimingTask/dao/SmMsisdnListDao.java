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

import com.jason.ddoTimingTask.bean.SmMsisdnList;

/**
 * 号码清单Dao
 * @author jasonzhang
 *
 */
public class SmMsisdnListDao extends BaseDao {

	private static final Logger logger = Logger.getLogger(SmMsisdnListDao.class);
	
	private final static String GET_SM_MSISDN_LISTS = "select id, msisdn from ddo_sm_msisdn_list where sm_task_id = ?";
	private final static String UPDATE_SM_MSISDN_LIST = "update ddo_sm_msisdn_list set sm_request_id = ? where id = ?";
	
	/**
	 * 获取号码清单列表
	 * @param taskId
	 * @return
	 * @throws DaoException
	 */
	public List<SmMsisdnList> getSmMsisdnListList(String taskId) throws DaoException {
		List<SmMsisdnList> list = new ArrayList<SmMsisdnList>();
		Connection conn = null;
		try {
			conn = super.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(GET_SM_MSISDN_LISTS);
			pstmt.setString(1, taskId);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				SmMsisdnList record = new SmMsisdnList();
				record.setId(rs.getString(1));
				record.setMsisdn(new Long(rs.getLong(2)));
				list.add(record);
			}

			super.closeResultSet(rs);
			super.closeStmt(pstmt);
		} catch (SQLException e) {

			logger.error("exception when getSmMsisdnListList", e);
			throw new DaoException(e.getMessage());
		} finally {
			super.closeConnction(conn);
		}
		return list;
	}
	
	public void updateSmMsisdnList(List<SmMsisdnList> list) throws DaoException {
		Connection conn = null;
		try {
			conn = super.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(UPDATE_SM_MSISDN_LIST);
			for (SmMsisdnList record : list) {
				pstmt.setString(1, record.getSmRequestId());
				pstmt.setString(2, record.getId());
				pstmt.addBatch();
			}
			pstmt.executeBatch();
			super.closePstmt(pstmt);
			conn.commit();
		} catch (SQLException e) {
			super.rollbackConnection(conn);
			logger.error("exception when updateSmMsisdnList", e);
			throw new DaoException(e.getMessage());
		} finally {
			super.closeConnction(conn);
		}
	}
}
