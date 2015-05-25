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

import com.jason.ddoTimingTask.bean.ConsumeRecord;
import com.jason.ddoTimingTask.bean.ConsumeTurnover;

/**
 * 消费记录Dao
 * @author jasonzhang
 *
 */
public class ConsumeDao extends BaseDao {

	private static final Logger logger = Logger.getLogger(ConsumeDao.class);
	
	private final static String INSERT_CONSUME_RECORD = "insert into ddo_consume_record(sum_month, sum_amount, num, msisdn) values(?, ?, ?, ?)";
	private final static String UPDATE_CONSUME_RECORD = "update ddo_consume_record set sum_amount = sum_amount + ?, num = num + ? where sum_month = ? and msisdn = ?";
	private final static String GET_CONSUME_RECORD = "select sum_month, sum_amount, num, msisdn from ddo_consume_record where sum_month = ? and msisdn = ?";
	private final static String GET_CONSUME_TRUNOVER = "select id, msisdn, amount, record_date, state from ddo_consume_turnover where state = 0 limit 0, ?";
	private final static String UPDATE_STATE_PROCESSED = "update ddo_consume_turnover set state = 1 where id = ?";
	
	/**
	 * 保存消费记录
	 * @param record
	 * @throws DaoException
	 */
	public void saveConsumeRecord(ConsumeRecord record) throws DaoException {
		Connection conn = null;
		try {
			conn = super.getConnection();
			PreparedStatement pstmt = conn
					.prepareStatement(INSERT_CONSUME_RECORD);
			pstmt.setInt(1, record.getSumMonth());
			pstmt.setInt(2, record.getAmount());
			pstmt.setInt(3,record.getNum());
			pstmt.setLong(4, record.getMsisdn());
			pstmt.executeUpdate();
			super.closePstmt(pstmt);
			conn.commit();
		} catch (SQLException e) {
			super.rollbackConnection(conn);
			logger.error("exception when saveConsumeRecord", e);
			throw new DaoException(e.getMessage());
		} finally {
			super.closeConnction(conn);
		}
	}
	/**
	 * 更新消费记录
	 * @param msisdn 手机号码
	 * @param sumMonth 消费月份
	 * @param amount 增加的消费金额
	 * @param num 增加的消费次数
	 * @throws DaoException
	 */
	public void updateConsumeRecord(long msisdn, int sumMonth, int amount, int num) throws DaoException {
		Connection conn = null;
		try {
			conn = super.getConnection();
			PreparedStatement pstmt = conn
					.prepareStatement(UPDATE_CONSUME_RECORD);
			pstmt.setInt(1, amount);
			pstmt.setInt(2, num);
			pstmt.setInt(3, sumMonth);
			pstmt.setLong(4, msisdn);
			pstmt.executeUpdate();
			super.closePstmt(pstmt);
			conn.commit();
		} catch (SQLException e) {
			super.rollbackConnection(conn);
			logger.error("exception when updateConsumeRecord", e);
			throw new DaoException(e.getMessage());
		} finally {
			super.closeConnction(conn);
		}
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
	/**
	 * 获取消费记录
	 * @param msisdn
	 * @param sumMonth
	 * @return
	 * @throws DaoException
	 */
	public ConsumeRecord getConsumeRecord(long msisdn, int sumMonth) throws DaoException {
		ConsumeRecord record = null;
		Connection conn = null;
		try {
			conn = super.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(GET_CONSUME_RECORD);
			pstmt.setInt(1, sumMonth);
			pstmt.setLong(2, msisdn);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				record = new ConsumeRecord();
				record.setSumMonth(rs.getInt(1));
				record.setAmount(rs.getInt(2));
				record.setNum(rs.getInt(3));
				record.setMsisdn(rs.getLong(4));
			}
			super.closeResultSet(rs);
			super.closeStmt(pstmt);
		} catch (SQLException e) {

			logger.error("exception when getConsumeRecord", e);
			throw new DaoException(e.getMessage());
		} finally {
			super.closeConnction(conn);
		}
		return record;
	}
	/**
	 * 获取未处理的消费流水记录
	 * @param num
	 * @throws DaoException
	 */
	public List<ConsumeTurnover> getUntreatedConsumeTurnover(int num) throws DaoException {
		List<ConsumeTurnover> list = new ArrayList<ConsumeTurnover>(num);
		Connection conn = null;
		try {
			conn = super.getConnection();
			PreparedStatement pstmt = conn
					.prepareStatement(GET_CONSUME_TRUNOVER);
			pstmt.setInt(1, num);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				ConsumeTurnover record = new ConsumeTurnover();
				record.setId(rs.getString(1));
				record.setMsisdn(rs.getLong(2));
				record.setAmount(rs.getInt(3));
				record.setCreateDate(rs.getInt(4));
				record.setState(rs.getInt(5));
				record.calcuateSendMonth();
				list.add(record);
			}

			super.closeResultSet(rs);
			super.closeStmt(pstmt);
		} catch (SQLException e) {
			logger.error("exception when getUntreatedConsumeTurnover", e);
			throw new DaoException(e.getMessage());
		} finally {
			super.closeConnction(conn);
		}
		return list;
	}
}
