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

import com.jason.ddoMsg.bean.msg.BillReport;
import com.jason.ddoMsg.util.UUIDGenerator;

/**
 * 计费状态报告dao
 * 
 * @author jasonzhang
 *
 */
public class BillReportDao extends BaseDao {
	private static final Logger logger = Logger.getLogger(BillReportDao.class);
	private final static String INSERT_BILL_REPORT = "insert into ddo_bill_report(id, transation_id, bill_state_code, state, create_date)"
			+ " values(?, ?, ?, ?, ?)";
	private final static String UPDATE_RESULT_CODE = "update ddo_bill_report set state = ?, process_result = ?, result_code = ?, repeat_flag = ?, send_time = ?, response_time = ? where id = ?";
	private final static String UPDATE_STATE = "update ddo_bill_report set state = ?, process_result = ?, repeat_flag = ? where id = ?";
	private final static String GET_MISS_TRANSID_RECORD = "select id, transation_id, bill_state_code from ddo_bill_report where process_result = 2 limit 0, ?";
	private final static String GET_UNEXPECTEDEND_RECORD = "select id, transation_id, bill_state_code from ddo_bill_report where state = 3 limit 0, ?";
	private final static String INSERT_REPEAT_REPORT_REOCRD = "insert into ddo_repeat_report_record(id, create_date, result_code, bill_report_id, response_time)"
			+ " values(?, ?, ?, ?, ?)";
	private final static String GET_NEED_REPEAT_REPORT = "select t1.id, t1.transation_id, t1.bill_state_code from ddo_bill_report t1 "
			+ "where t1.repeat_flag = 1 limit 0, ?";

	/**
	 * 保存计费状态报告列表
	 * 
	 * @param reportList
	 *            报告列表
	 * @throws DaoException
	 */
	public void saveBillReportList(List<BillReport> reportList)
			throws DaoException {
		Connection conn = null;
		try {
			conn = super.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(INSERT_BILL_REPORT);
			for (BillReport billReport : reportList) {
				this.setBillReportParam(billReport, pstmt);
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
	 * 更新下发结果
	 * 
	 * @param id
	 * @param state
	 * @param processResult
	 * @param resultCode
	 */
	public void updateResultCode(String id, int state, int processResult,
			String resultCode, boolean repeat, Date sendTime, Date responseTime) throws DaoException {
		// resultCode是请求外部网址返回的状态，要控制其长度比数据库中的长度短
		if (resultCode != null && resultCode.length() > 32) {
			resultCode = resultCode.substring(0, 31);
		}
		Timestamp sendTimestamp = super.convertSqlDate(sendTime);
		Timestamp responseTimestamp = super.convertSqlDate(responseTime);
		Connection conn = null;
		try {
			conn = super.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(UPDATE_RESULT_CODE);
			pstmt.setInt(1, state);
			pstmt.setInt(2, processResult);
			pstmt.setString(3, resultCode);
			pstmt.setShort(4, repeat ? (short) 1 : (short) 0);
			pstmt.setTimestamp(5, sendTimestamp);
			pstmt.setTimestamp(6, responseTimestamp);
			pstmt.setString(5, id);
			pstmt.executeUpdate();
			super.closePstmt(pstmt);
			if (repeat) {
				pstmt = conn.prepareStatement(INSERT_REPEAT_REPORT_REOCRD);
				pstmt.setString(1, (new UUIDGenerator()).generate());
				pstmt.setTimestamp(2, sendTimestamp);
				pstmt.setString(3, resultCode);
				pstmt.setString(4, id);
				pstmt.setTimestamp(5, responseTimestamp);
				pstmt.executeUpdate();
				super.closePstmt(pstmt);
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
	 * 更新状态
	 * 
	 * @param id
	 * @param state
	 * @param processResult
	 * @param resultCode
	 */
	public void updateState(String id, int state, int processResult,
			boolean needRepeat) throws DaoException {

		Connection conn = null;
		try {
			conn = super.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(UPDATE_STATE);
			pstmt.setInt(1, state);
			pstmt.setInt(2, processResult);
			pstmt.setShort(3, needRepeat ? (short) 1 : (short) 0);
			pstmt.setString(4, id);
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
	 * 获取未找到消息id的记录
	 * 
	 * @param limitNum
	 * @return
	 */
	public List<BillReport> getMissTransIdRecord(int limitNum)
			throws DaoException {
		List<BillReport> billReportList = new ArrayList<BillReport>();
		Connection conn = null;
		try {
			conn = super.getConnection();
			PreparedStatement pstmt = conn
					.prepareStatement(GET_MISS_TRANSID_RECORD);
			pstmt.setInt(1, limitNum);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				BillReport billReport = new BillReport();
				this.setParamToBillReport(billReport, rs);
				billReportList.add(billReport);
			}

			super.closeResultSet(rs);
			super.closeStmt(pstmt);
		} catch (SQLException e) {

			logger.error("exception when getMissTransIdRecord", e);
			throw new DaoException(e.getMessage());
		} finally {
			super.closeConnction(conn);
		}
		return billReportList;
	}

	/**
	 * 获取异常中断的记录
	 * 
	 * @param limitNum
	 * @return
	 */
	public List<BillReport> getUnexpectedEndReport(int limitNum)
			throws DaoException {
		List<BillReport> billReportList = new ArrayList<BillReport>();
		Connection conn = null;
		try {
			conn = super.getConnection();
			PreparedStatement pstmt = conn
					.prepareStatement(GET_UNEXPECTEDEND_RECORD);
			pstmt.setInt(1, limitNum);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				BillReport billReport = new BillReport();
				this.setParamToBillReport(billReport, rs);
				billReportList.add(billReport);
			}

			super.closeResultSet(rs);
			super.closeStmt(pstmt);
		} catch (SQLException e) {

			logger.error("exception when getUnexpectedEndReport", e);
			throw new DaoException(e.getMessage());
		} finally {
			super.closeConnction(conn);
		}
		return billReportList;
	}

	/**
	 * 获取需要重新下发的状态报告
	 * @param limitNums
	 * @return
	 * @throws DaoException
	 */
	public List<BillReport> getRepeatReportList(int limitNums)
			throws DaoException {
		List<BillReport> records = new ArrayList<BillReport>(limitNums);
		Connection conn = null;
		try {
			conn = super.getConnection();
			PreparedStatement pstmt = conn
					.prepareStatement(GET_NEED_REPEAT_REPORT);
			pstmt.setInt(1, limitNums);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				BillReport msg = new BillReport();
				this.setParamToBillReport(msg, rs);
				records.add(msg);
			}
			super.closeResultSet(rs);
			super.closePstmt(pstmt);
		} catch (SQLException e) {

			logger.error("exception when getRepeatReportList", e);
			throw new DaoException(e.getMessage());
		} finally {
			super.closeConnction(conn);
		}
		return records;
	}

	private void setBillReportParam(BillReport billReport,
			PreparedStatement pstmt) throws SQLException {
		pstmt.setString(1, billReport.getId());
		pstmt.setString(2, billReport.getTransationId());
		pstmt.setString(3, billReport.getBillStateCode());
		pstmt.setInt(4, billReport.getState().intValue());
		pstmt.setTimestamp(5, super.convertSqlDate(billReport.getCreateDate()));
	}

	private void setParamToBillReport(BillReport billReport, ResultSet rs)
			throws SQLException {
		billReport.setId(rs.getString(1));
		billReport.setTransationId(rs.getString(2));
		billReport.setBillStateCode(rs.getString(3));
	}
}
