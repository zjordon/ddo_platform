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

import com.jason.ddoMsg.bean.channel.ChannelUser;

/**
 * 渠道用户Dao
 * 处理渠道用户数据与数据库的交互逻揖
 * @author jasonzhang
 *
 */
public class ChannelUserDao extends BaseDao {
	private static final Logger logger = Logger.getLogger(ChannelUserDao.class);
	private final static String GET_CHANNEL_USERS = "select id, username, password, msisdn, state, channel_id from ddo_channel_user";

	public List<ChannelUser> getChannelUsers() throws DaoException {
		List<ChannelUser> channelUserList = new ArrayList<ChannelUser>();
		Connection conn = null;
		try {
			conn = super.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(GET_CHANNEL_USERS);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				ChannelUser channelUser = new ChannelUser();
				channelUser.setId(rs.getString(1));
				channelUser.setUsername(rs.getString(2));
				channelUser.setPassword(rs.getString(3));
				channelUser.setMsisdn(new Long(rs.getLong(4)));
				channelUser.setState(new Integer(rs.getInt(5)));
				channelUser.setChannelId(rs.getString(6));
				channelUserList.add(channelUser);
			}
			super.closeResultSet(rs);
			super.closePstmt(pstmt);
		} catch (SQLException e) {
			logger.error("exception when getChannels", e);
			throw new DaoException(e.getMessage());
		} finally {
			super.closeConnction(conn);
		}
		return channelUserList;
	}
}
