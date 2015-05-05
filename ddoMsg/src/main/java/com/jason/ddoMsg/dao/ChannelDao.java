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

import com.jason.ddoMsg.bean.channel.Channel;

/**
 * 渠道Dao
 * 处理渠道数据与数据库的交互逻揖
 * @author jasonzhang
 *
 */
public class ChannelDao extends BaseDao {
	private static final Logger logger = Logger.getLogger(ChannelDao.class);
	private final static String GET_CHANNELS = "select id, name, no, state, day_limit, month_limit, close_state, up_url, down_url from ddo_channel";

	public List<Channel> getChannels() throws DaoException {
		List<Channel> channelList = new ArrayList<Channel>();
		Connection conn = null;
		try {
			conn = super.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(GET_CHANNELS);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				Channel channel = new Channel();
				channel.setId(rs.getString(1));
				channel.setName(rs.getString(2));
				channel.setNo(new Long(rs.getLong(3)));
				channel.setState(new Integer(rs.getInt(4)));
				channel.setDayLimit(new Long(rs.getLong(5)));
				channel.setMonthLimit(new Long(rs.getLong(6)));
				channel.setCloseState(new Integer(rs.getInt(7)));
				channel.setUpUrl(rs.getString(8));
				channel.setDownUrl(rs.getString(9));
				channelList.add(channel);
			}
			super.closeResultSet(rs);
			super.closePstmt(pstmt);
		} catch (SQLException e) {
			logger.error("exception when getChannels", e);
			throw new DaoException(e.getMessage());
		} finally {
			super.closeConnction(conn);
		}
		return channelList;
	}
}
