/**
 * 
 */
package com.jason.ddoMsg.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.jason.ddoMsg.bean.event.Event;


/**
 * 事件dao
 * 处理事件数据与数据库的交互逻揖
 * @author jasonzhang
 *
 */
public class EventDao extends BaseDao {
	private static final Logger logger = Logger.getLogger(EventDao.class);
	private final static String INSERT_EVENT = "insert into ddo_event(id, event_id, create_date, param)"
			+ " values(?, ?, ?, ?)";
	private final static String UPDATE_PROCESS_TIME = "update ddo_event set begin_time = ?, end_time = ?, process_result = ?, fail_msg = ? where id = ?";
	private final static String GET_PENDING_EVENTS = "select id, event_id, param from ddo_event where begin_time is null and end_time is null order by create_date asc limit 0, ?";

	/**
	 * 保存事件
	 * @param event 事件
	 * @throws DaoException
	 */
	public void saveEvent(Event event) throws DaoException {
		Connection conn = null;
		try {
			conn = super.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(INSERT_EVENT);
			pstmt.setString(1, event.getId());
			pstmt.setString(2, event.getEventId());
			pstmt.setTimestamp(3, super.convertSqlDate(event.getCreateDate()));
			pstmt.setString(4, event.getParam());
			pstmt.executeUpdate();
			super.closePstmt(pstmt);
			conn.commit();
		} catch (SQLException e) {
			super.rollbackConnection(conn);
			logger.error("exception when saveEvent", e);
			throw new DaoException(e.getMessage());
		} finally {
			super.closeConnction(conn);
		}
	}
	
	/**
	 * 更新处理时间
	 * @param id 唯一标识
	 * @param beginTime 开始处理时间
	 * @param endTime 处理结束时间
	 * @param processResult 处理结果
	 * @param failMsg 失败原因
	 * @throws DaoException
	 */
	public void updateProcessTime(String id, Date beginTime, Date endTime, int processResult, String failMsg) throws DaoException {
		Connection conn = null;
		try {
			conn = super.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(UPDATE_PROCESS_TIME);
			pstmt.setTimestamp(1, super.convertSqlDate(beginTime));
			pstmt.setTimestamp(2, super.convertSqlDate(endTime));
			pstmt.setInt(3, processResult);
			pstmt.setString(4, failMsg);
			pstmt.setString(5, id);
			pstmt.executeUpdate();
			super.closePstmt(pstmt);
			conn.commit();
		} catch (SQLException e) {
			super.rollbackConnection(conn);
			logger.error("exception when updateProcessTime", e);
			throw new DaoException(e.getMessage());
		} finally {
			super.closeConnction(conn);
		}
	}
	
	public List<Event> getPendingEvents(int limitNums) throws DaoException {
		List<Event> events = new ArrayList<Event>(limitNums);
		Connection conn = null;
		try {
			conn = super.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(GET_PENDING_EVENTS);
			pstmt.setInt(1, limitNums);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				Event event = new Event();
				event.setId(rs.getString(1));
				event.setEventId(rs.getString(2));
				event.setParam(rs.getString(3));
				events.add(event);
			}
			super.closeResultSet(rs);
			super.closePstmt(pstmt);
		} catch (SQLException e) {
			logger.error("exception when updateProcessTime", e);
			throw new DaoException(e.getMessage());
		} finally {
			super.closeConnction(conn);
		}
		return events;
	}
}
