/**
 * 
 */
package com.jason.ddoMsg.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.jason.ddoMsg.bean.consumption.ConsumeRecord;
import com.jason.ddoMsg.bean.consumption.ConsumeTurnover;

/**
 * 手机消费记录dao
 * @author jasonzhang
 *
 */
public class ConsumeDao extends BaseDao {

	private static final Logger logger = Logger.getLogger(ConsumeDao.class);
	
	private final static String GET_CONSUME_RECORDS = "select sum_amount, num, msisdn from ddo_consume_record where sum_month = ?";
	private final static String INSERT_CONSUME_TRUNOVER = "insert into ddo_consume_turnover(id, msisdn, amount, record_date, state)"
			+ " values(?, ?, ?, ?, 0)";
	
	public void saveConsumeTurnoverList(List<ConsumeTurnover> list)  throws DaoException {
		Connection conn = null;
		try {
			conn = super.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(INSERT_CONSUME_TRUNOVER);
			for (ConsumeTurnover record : list) {
				pstmt.setString(1, record.getId());
				pstmt.setLong(2, record.getMsisdn());
				pstmt.setInt(3, record.getAmount());
				pstmt.setInt(4, record.getCreateDate());
				pstmt.addBatch();
			}
			pstmt.executeBatch();
			super.closePstmt(pstmt);
			conn.commit();
		} catch (SQLException e) {
			super.rollbackConnection(conn);
			logger.error("exception when saveConsumeTurnoverList", e);
			throw new DaoException(e.getMessage());
		} finally {
			super.closeConnction(conn);
		}
	}
	
	public List<ConsumeRecord> getConsumeRecordList(int sumMonth) throws DaoException {
		List<ConsumeRecord> list = new ArrayList<ConsumeRecord>();
		Connection conn = null;
		try {
			conn = super.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(GET_CONSUME_RECORDS);
			pstmt.setInt(1, sumMonth);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				ConsumeRecord record = new ConsumeRecord();
				record.setAmount(rs.getInt(1));
				record.setNum(rs.getInt(2));
				record.setMsisdn(rs.getLong(3));
			}
			super.closeResultSet(rs);
			super.closePstmt(pstmt);
		} catch (SQLException e) {
			logger.error("exception when getConsumeRecordList", e);
			throw new DaoException(e.getMessage());
		} finally {
			super.closeConnction(conn);
		}
		return list;
	}
}
