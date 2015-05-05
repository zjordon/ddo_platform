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

import com.jason.ddoMsg.bean.channel.ProviceCloseState;

/**
 * 省份关停状态Dao
 * @author jasonzhang
 *
 */
public class ProviceCloseStateDao extends BaseDao {
	private static final Logger logger = Logger.getLogger(ProviceCloseStateDao.class);
	private final static String GET_CHANNEL_BUSINESSEES = "select id, provice_code, close_state from ddo_provice_close_state";

	public List<ProviceCloseState> getProviceCloseStates() throws DaoException {
		List<ProviceCloseState> proviceCloseStateList = new ArrayList<ProviceCloseState>();
		Connection conn = null;
		try {
			conn = super.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(GET_CHANNEL_BUSINESSEES);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				ProviceCloseState proviceCloseState = new ProviceCloseState();
				proviceCloseState.setId(rs.getString(1));
				proviceCloseState.setProviceCode(rs.getString(2));
				proviceCloseState.setCloseState(new Integer(rs.getInt(3)));
				proviceCloseStateList.add(proviceCloseState);
			}
			super.closeResultSet(rs);
			super.closePstmt(pstmt);
		} catch (SQLException e) {
			logger.error("exception when getProviceCloseStates", e);
			throw new DaoException(e.getMessage());
		} finally {
			super.closeConnction(conn);
		}
		return proviceCloseStateList;
	}
}
