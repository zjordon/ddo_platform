package com.jason.ddoMsg.dao;

import java.util.Date;
import java.util.List;

import com.jason.ddoMsg.bean.event.Event;
import com.jason.ddoMsg.util.UUIDGenerator;

public class EventDaoTest extends BaseDaoTest {
	
	private EventDao eventDao = null;

	protected void setUp() throws Exception {
		super.setUp();
		this.eventDao = new EventDao();
		this.eventDao.setDataSource(super.dataSource);
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testSaveEvent() throws DaoException {
		Event event = new Event();
		event.setId((new UUIDGenerator()).generate());
		event.setEventId("update_channel_user_state");
		event.setCreateDate(new Date());
		event.setParam("{id:'1', state:'0'}");
		this.eventDao.saveEvent(event);
	}

	public void testUpdateProcessTime() throws DaoException {
		Date current = new Date();
		this.eventDao.updateProcessTime("402848814c7280e5014c7280e52c0000", current, current, 1, null);
	}

	public void testGetPendingEvents() throws DaoException {
		List<Event> list = this.eventDao.getPendingEvents(10);
		super.assertNotNull(list);
		super.assertEquals(1, list.size());
	}

}
