package com.jason.ddoWeb.service.impl.channel;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jason.ddoWeb.entity.channel.BillBusinessEntity;
import com.jason.ddoWeb.entity.channel.ChannelBusinessEntity;
import com.jason.ddoWeb.entity.channel.ChannelEntity;
import com.jason.ddoWeb.entity.event.EventEntity;
import com.jason.ddoWeb.service.channel.ChannelBusinessServiceI;
import com.jason.ddoWeb.util.StringUtils;

import org.apache.log4j.Logger;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import org.jeecgframework.core.util.DynamicDBUtil;

@Service("channelBusinessService")
@Transactional
public class ChannelBusinessServiceImpl extends CommonServiceImpl implements
		ChannelBusinessServiceI {
	private static final Logger logger = Logger
			.getLogger(ChannelBusinessServiceImpl.class);
	// private final static String GET_COUNT =
	// "select count(*) from ddo_channel_business where bill_business_id = ? and channel_id = ?";
	private final static String GET_COUNT_BY_INSTRUCT = "select count(*) from ddo_channel_business where instruct = ?";
	private final static String GET_BUSINESS = "select state, instruct from ddo_channel_business where id = ?";
	private final static String INSERT_EVENT = "insert into sm_event(id, event_id, create_date, param) values(?, ?, ?, ?)";

	@Override
	public void saveChannelBusinessesByBB(String billBusinessId,
			String channelBillCode) {
		List<ChannelEntity> channelList = super.getList(ChannelEntity.class);
		for (ChannelEntity channel : channelList) {

			this.createChannelBusiness(billBusinessId, channel.getId(), channel
					.getNo().toString(), channelBillCode);

		}

	}

	@Override
	public void saveChannelBusinessesByChannel(String channelId, Long channelNo) {
		List<BillBusinessEntity> billBusinessList = super
				.getList(BillBusinessEntity.class);
		for (BillBusinessEntity billBusiness : billBusinessList) {

			this.createChannelBusiness(billBusiness.getId(), channelId,
					channelNo.toString(), billBusiness.getChannelBillCode());

		}

	}

	private void createChannelBusiness(String billBusinessId, String channelId,
			String channelNo, String channelBillCode) {
		String instruct = channelNo + channelBillCode;
		if (!this.isExist(instruct)) {
			ChannelBusinessEntity channelBusiness = new ChannelBusinessEntity();
			BillBusinessEntity billBusiness = new BillBusinessEntity();
			billBusiness.setId(billBusinessId);
			billBusiness.setChannelBillCode(channelBillCode);
			//channelBusiness.setBillBusinessId(billBusinessId);
			channelBusiness.setBillBusiness(billBusiness);
			channelBusiness.setChannelId(channelId);
			// 默认关停状态为未关停
			channelBusiness.setCloseState(new Integer(0));
			// 默认状态为启用
			channelBusiness.setState(new Integer(1));
			// 默认指令为渠道编号+渠道计费业务编号
			channelBusiness.setInstruct(channelNo + channelBillCode);
			super.save(channelBusiness);
			// 产生新增渠道业务事件
			this.createAddEvent(channelBusiness);
		} else {
			logger.info("the channel_business is exist with instruct "
					+ instruct);
		}

	}

	// private boolean isExist(String billBusinessId, String channelId) {
	// Long count = super.getCountForJdbcParam(GET_COUNT, new
	// Object[]{billBusinessId, channelId});
	// return count.longValue() > 0;
	// }

	private boolean isExist(String instruct) {
		Long count = super.getCountForJdbcParam(GET_COUNT_BY_INSTRUCT,
				new Object[] { instruct });
		return count.longValue() > 0;
	}

	private void createAddEvent(ChannelBusinessEntity channelBusiness) {
		EventEntity event = new EventEntity();
		event.setEventId("AddChannelBusinessEvent");
		StringBuilder builder = new StringBuilder();
		builder.append("id:").append(channelBusiness.getId())
				.append(",instruct:").append(channelBusiness.getInstruct());
		builder.append(",state:").append(channelBusiness.getState())
				.append(",closeState:").append(channelBusiness.getCloseState());
		builder.append(",billBusinessId:")
				.append(channelBusiness.getBillBusiness().getId())
				.append(",channelId:").append(channelBusiness.getChannelId());
		event.setParam(builder.toString());
		super.save(event);
		this.createEventToSm(event);
	}

	@Override
	public <T> Serializable save(T entity) {
		Serializable id =  super.save(entity);
		//产生新增渠道业务事件
		this.createAddEvent((ChannelBusinessEntity)entity);
		return id;
	}

	@Override
	public <T> void saveOrUpdate(T entity) {
		ChannelBusinessEntity channelBusiness = (ChannelBusinessEntity)entity;
		//从数据库中取出原来的状态和指令（使用jdbc直接从数据库中加载)
		Map map = super.findOneForJdbc(GET_BUSINESS, new Object[]{channelBusiness.getId()});
		Integer state = (Integer)map.get("state");
		if (state != null) {
			if (state.intValue() != channelBusiness.getState()) {
				//产生更新渠道业务状态事件
				EventEntity event = new EventEntity();
				event.setEventId("UpdateCBStateEvent");
				StringBuilder builder = new StringBuilder();
				builder.append("id:").append(((ChannelBusinessEntity)entity).getId()).append(",state:").append(channelBusiness.getState());
				event.setParam(builder.toString());
				super.save(event);
			}
		}
		String instruct = (String)map.get("instruct");
		if (!StringUtils.isEquals(instruct, channelBusiness.getInstruct())) {
			//产生更新渠道业务指令事件
			EventEntity event = new EventEntity();
			event.setEventId("UpdateCBInstruct");
			StringBuilder builder = new StringBuilder();
			builder.append("id:").append(((ChannelBusinessEntity)entity).getId()).append(",instruct:").append(channelBusiness.getInstruct());
			event.setParam(builder.toString());
			super.save(event);
			this.createEventToSm(event);
		}
		super.saveOrUpdate(entity);
	}

	@Override
	public <T> void delete(T entity) {
		//产生删除渠道业务事件
		this.createDeleteEvent((ChannelBusinessEntity)entity);
		super.delete(entity);
	}
	
	private void createEventToSm(EventEntity event) {
		DynamicDBUtil.update("SM_DB", INSERT_EVENT, event.getId(), event.getEventId(), event.getCreateDate(), event.getParam());
	}
	
	private void createDeleteEvent(ChannelBusinessEntity entity) {
		EventEntity event = new EventEntity();
		event.setEventId("DeleteChannelBusinessEvent");
		StringBuilder builder = new StringBuilder();
		builder.append("id:").append(entity.getId());
		event.setParam(builder.toString());
		super.save(event);
		this.createEventToSm(event);
	}

	@Override
	public void deleteByChannelId(String channelId) {
		List<ChannelBusinessEntity> list = super.findByProperty(ChannelBusinessEntity.class, "channelId", channelId);
		for (ChannelBusinessEntity entity : list) {
			super.delete(entity);
			this.createDeleteEvent(entity);
		}
		
	}

}