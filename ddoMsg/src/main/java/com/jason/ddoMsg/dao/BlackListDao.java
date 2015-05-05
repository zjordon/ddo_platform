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

/**
 * 黑名单Dao
 * 处理黑名单数据与数据库之间的交互逻揖
 * @author jasonzhang
 *
 */
public class BlackListDao extends BaseDao {
	private static final Logger logger = Logger.getLogger(BlackListDao.class);
	private final static String GET_BLACK_LISTS = "select msisdn from ddo_black_list where state = 1";

	public List<Long> getBlackLists() throws DaoException {
		List<Long> blackListList = new ArrayList<Long>();
		Connection conn = null;
		try {
			conn = super.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(GET_BLACK_LISTS);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				blackListList.add(new Long(rs.getLong(1)));
			}
			super.closeResultSet(rs);
			super.closePstmt(pstmt);
		} catch (SQLException e) {
			logger.error("exception when getBlackLists", e);
			throw new DaoException(e.getMessage());
		} finally {
			super.closeConnction(conn);
		}
		return blackListList;
	}
}
