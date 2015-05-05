/**
 * 
 */
package com.jason.ddoTimingTask.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;
/**
 * @author jasonzhang
 *
 */
public class BillBusinessDao extends BaseDao {

	private static final Logger logger = Logger.getLogger(BillBusinessDao.class);
	private final static String GET_CHANNEL_BILL_CODE = "select channel_bill_code from ddo_bill_business where id = ?";
	private final static String GET_PRICE = "select price from ddo_bill_business where id = ?";
	
	public String getChannelBillCode(String billBusinessId) throws DaoException {
		String channelBillCode = null;
		Connection conn = null;
		try {
			conn = super.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(GET_CHANNEL_BILL_CODE);
			pstmt.setString(1, billBusinessId);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				channelBillCode = rs.getString(1);
			}
			super.closeResultSet(rs);
			super.closeStmt(pstmt);
		} catch (SQLException e) {

			logger.error("exception when getChannelBillCode", e);
			throw new DaoException(e.getMessage());
		} finally {
			super.closeConnction(conn);
		}
		return channelBillCode;
	}
	
	public double getPrice(String billBusinessId) throws DaoException {
		double price = 0;
		Connection conn = null;
		try {
			conn = super.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(GET_PRICE);
			pstmt.setString(1, billBusinessId);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				long l = rs.getLong(1);
				//把分转成元
				price = ((double)l)/100;
			}
			super.closeResultSet(rs);
			super.closeStmt(pstmt);
		} catch (SQLException e) {

			logger.error("exception when getPrice", e);
			throw new DaoException(e.getMessage());
		} finally {
			super.closeConnction(conn);
		}
		return price;
	}
}
