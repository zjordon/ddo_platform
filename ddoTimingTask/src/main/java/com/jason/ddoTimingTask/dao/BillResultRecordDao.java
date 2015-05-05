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

import com.jason.ddoTimingTask.bean.BillResultRecord;

/**
 * 计费结果记录Dao
 * @author jasonzhang
 *
 */
public class BillResultRecordDao extends BaseDao {

	private static final Logger logger = Logger.getLogger(BillResultRecordDao.class);
	private final static String GET_BILL_RESULT_RECORDS = "select id, ddo_msg_id, bill_result, state from ddo_bill_result_record where state = 0 limit 0, ?";
	private final static String UPDATE_STATE_PROCESSED = "update ddo_bill_result_record set state = 1 where id = ?";

	/**
	 *获取发送结果记录列表
	 * @param num 获取数目
	 * @return
	 */
	public List<BillResultRecord> getBillResultRecordList(int num) throws DaoException {
		List<BillResultRecord> list = new ArrayList<BillResultRecord>(num);
		Connection conn = null;
		try {
			conn = super.getConnection();
			PreparedStatement pstmt = conn
					.prepareStatement(GET_BILL_RESULT_RECORDS);
			pstmt.setInt(1, num);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				BillResultRecord BillResultRecord = new BillResultRecord();
				this.setParamToBillResultRecord(BillResultRecord, rs);
				list.add(BillResultRecord);
			}

			super.closeResultSet(rs);
			super.closeStmt(pstmt);
		} catch (SQLException e) {

			logger.error("exception when getBillResultRecordList", e);
			throw new DaoException(e.getMessage());
		} finally {
			super.closeConnction(conn);
		}
		return list;
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
	
	private void setParamToBillResultRecord(BillResultRecord billResultRecord, ResultSet rs)
			throws SQLException {
		billResultRecord.setId(rs.getString(1));
		billResultRecord.setDdoMsgId(rs.getString(2));
		billResultRecord.setBillResult(new Integer(rs.getInt(3)));
	}
}
