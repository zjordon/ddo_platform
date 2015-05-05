package com.jason.ddoWeb.service.impl.channel;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jason.ddoWeb.entity.channel.BillBusinessEntity;
import com.jason.ddoWeb.entity.event.EventEntity;
import com.jason.ddoWeb.service.channel.BillBusinessServiceI;
import com.jason.ddoWeb.service.channel.ChannelBusinessServiceI;
import com.jason.ddoWeb.util.NumberUtils;
import com.jason.ddoWeb.util.StringUtils;

import org.jeecgframework.core.common.service.impl.CommonServiceImpl;

@Service("billBusinessService")
@Transactional
public class BillBusinessServiceImpl extends CommonServiceImpl implements
		BillBusinessServiceI {
	private final static String GET_BILL_BUSINESS_INFO = "select name,price,code,state,channel_bill_code from ddo_bill_business where id = ?";
	private final static String FIND_ENABLE_BUSINESS = "select business from BillBusinessEntity business, ChannelBusinessEntity cb where business.id = cb.billBusiness.id and cb.channelId = ? and cb.state = 1";

	@Autowired
	private ChannelBusinessServiceI channelBusinessService;

	@Override
	public <T> Serializable save(T entity) {
		Serializable id = super.save(entity);
		BillBusinessEntity billBusiness = (BillBusinessEntity) entity;
		// 自动生成渠道计费点
		this.channelBusinessService.saveChannelBusinessesByBB(
				billBusiness.getId(), billBusiness.getCode());
		// 产生新增计费业务事件
		EventEntity event = new EventEntity();
		event.setEventId("AddBBusinessEvent");
		StringBuilder builder = new StringBuilder();
		builder.append("id:").append(id).append(",price:")
				.append(billBusiness.getPrice()).append(",code:")
				.append(billBusiness.getPrice());
		builder.append(",channelBillCode:")
				.append(billBusiness.getChannelBillCode()).append(",state:")
				.append(billBusiness.getState());
		builder.append(",name:").append(billBusiness.getName());
		event.setParam(builder.toString());
		super.save(event);
		return id;
	}

	@Override
	public <T> void saveOrUpdate(T entity) {
		BillBusinessEntity billBusiness = (BillBusinessEntity) entity;
		// 使用sql语句从数据库中直接获取原来数据的状态(绕过hibernate的一级缓存和二级缓存)
		Map map = super.findOneForJdbc(GET_BILL_BUSINESS_INFO,
				billBusiness.getId());
		Integer state = (Integer) map.get("state");
		// 检测状态是否改变
		if (state != null
				&& state.intValue() != billBusiness.getState().intValue()) {
			// 产生更新计费业务状态事件
			EventEntity event = new EventEntity();
			event.setEventId("UpdateBBusinessStateEvent");
			StringBuilder builder = new StringBuilder();
			builder.append("id:").append(billBusiness.getId())
					.append(",state:")
					.append(billBusiness.getState().intValue());
			event.setParam(builder.toString());
			super.save(event);
		}
		// 检测其它信息是否改变
		boolean change = false;
		Long price = (Long) map.get("price");
		if (price != null
				&& price.intValue() != billBusiness.getPrice().intValue()) {
			change = true;
		}
		String name = (String) map.get("name");
		String code = (String) map.get("code");
		String channelBillCode = (String) map.get("channel_bill_code");
		if (!StringUtils.isEquals(name, billBusiness.getName())
				|| !StringUtils.isEquals(code, billBusiness.getCode())
				|| !StringUtils.isEquals(channelBillCode,
						billBusiness.getChannelBillCode())) {
			change = true;
		}
		if (change) {
			// 产生更新计费业务信息事件
			EventEntity event = new EventEntity();
			event.setEventId("UpdateBBusinessInfoEvent");
			StringBuilder builder = new StringBuilder();
			builder.append("id:").append(billBusiness.getId())
					.append(",price:").append(billBusiness.getPrice())
					.append(",code:").append(billBusiness.getPrice());
			builder.append(",channelBillCode:").append(
					billBusiness.getChannelBillCode());
			builder.append(",name:").append(billBusiness.getName());
			event.setParam(builder.toString());
			super.save(event);
		}
		super.saveOrUpdate(entity);
	}

	@Override
	public List<BillBusinessEntity> findEnableBusiness(String channelId) {
		return super.findHql(FIND_ENABLE_BUSINESS, channelId);
	}

	@Override
	public Map<String, Double> getPriceMap() {
		Map<String, Double> priceMap = new HashMap<String, Double>();
		List<BillBusinessEntity> billBusinessList = super
				.getList(BillBusinessEntity.class);
		for (BillBusinessEntity entity : billBusinessList) {
			priceMap.put(entity.getId(), NumberUtils.intToDouble(entity.getPrice()));
		}
		return priceMap;
	}

}