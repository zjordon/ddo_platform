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

import com.jason.ddoMsg.bean.channel.ChannelBusiness;

/**
 * 渠道业务Dao
 * @author jasonzhang
 *
 */
public class ChannelBusinessDao extends BaseDao {

	private static final Logger logger = Logger.getLogger(ChannelBusinessDao.class);
	private final static String GET_CHANNEL_BUSINESSEES = "select id, instruct, state, close_state, bill_business_id, channel_id from ddo_channel_business";

	public List<ChannelBusiness> getChannelBusinesses() throws DaoException {
		List<ChannelBusiness> channelBusinessList = new ArrayList<ChannelBusiness>();
		Connection conn = null;
		try {
			conn = super.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(GET_CHANNEL_BUSINESSEES);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				ChannelBusiness channelBusiness = new ChannelBusiness();
				channelBusiness.setId(rs.getString(1));
				channelBusiness.setInstruct(rs.getString(2));
				channelBusiness.setState(new Integer(rs.getInt(3)));
				channelBusiness.setCloseState(new Integer(rs.getInt(4)));
				channelBusiness.setBillBusinessId(rs.getString(5));
				channelBusiness.setChannelId(rs.getString(6));
				channelBusinessList.add(channelBusiness);
			}
			super.closeResultSet(rs);
			super.closePstmt(pstmt);
		} catch (SQLException e) {
			logger.error("exception when getChannelBusinesses", e);
			throw new DaoException(e.getMessage());
		} finally {
			super.closeConnction(conn);
		}
		return channelBusinessList;
	}
}
