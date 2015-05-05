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

import com.jason.ddoMsg.bean.channel.BillBusiness;

/**
 * 计费业务Dao
 * @author jasonzhang
 *
 */
public class BillBusinessDao extends BaseDao {
	private static final Logger logger = Logger.getLogger(BillBusinessDao.class);
	private final static String GET_BILL_BUSINESSEES = "select id, name, price, code, state, channel_bill_code from ddo_bill_business";

	public List<BillBusiness> getBillBusinesses() throws DaoException {
		List<BillBusiness> billBusinessList = new ArrayList<BillBusiness>();
		Connection conn = null;
		try {
			conn = super.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(GET_BILL_BUSINESSEES);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				BillBusiness billBusiness = new BillBusiness();
				billBusiness.setId(rs.getString(1));
				billBusiness.setName(rs.getString(2));
				billBusiness.setPrice(new Long(rs.getLong(3)));
				billBusiness.setCode(rs.getString(4));
				billBusiness.setState(new Integer(rs.getInt(5)));
				billBusiness.setChannelBillCode(rs.getString(6));
				billBusinessList.add(billBusiness);
			}
			super.closeResultSet(rs);
			super.closePstmt(pstmt);
		} catch (SQLException e) {
			logger.error("exception when getBillBusinesses", e);
			throw new DaoException(e.getMessage());
		} finally {
			super.closeConnction(conn);
		}
		return billBusinessList;
	}
}
