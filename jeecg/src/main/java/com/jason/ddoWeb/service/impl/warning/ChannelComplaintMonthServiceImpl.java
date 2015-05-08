package com.jason.ddoWeb.service.impl.warning;

import java.io.Serializable;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jason.ddoWeb.common.hibernate.HibernateUtil;
import com.jason.ddoWeb.entity.warning.ChannelComplaintMonthEntity;
import com.jason.ddoWeb.entity.warning.FullComplaintMonthEntity;
import com.jason.ddoWeb.service.warning.ChannelComplaintMonthServiceI;

import org.jeecgframework.core.common.service.impl.CommonServiceImpl;

@Service("channelComplaintMonthService")
@Transactional
public class ChannelComplaintMonthServiceImpl extends CommonServiceImpl implements ChannelComplaintMonthServiceI {

	@Override
	public <T> Serializable save(T entity) {
		ChannelComplaintMonthEntity channelComplaintMonthEntity = (ChannelComplaintMonthEntity)entity;
		// 查找对应的全量统计投诉记录
		FullComplaintMonthEntity fullComplaintMonthEntity = this.getFullComplaintMonthEntity(channelComplaintMonthEntity.getSumMonth().intValue());
		if (fullComplaintMonthEntity == null) {
			this.createNewFullComplaintMonthEntity(channelComplaintMonthEntity.getSumMonth().intValue(), channelComplaintMonthEntity.getNum().intValue());
		} else {
			fullComplaintMonthEntity.setNum(new Integer(fullComplaintMonthEntity.getNum().intValue() + channelComplaintMonthEntity.getNum().intValue()));
			super.updateEntitie(fullComplaintMonthEntity);
		}
		return super.save(entity);
	}

	@Override
	public <T> void saveOrUpdate(T entity) {
		ChannelComplaintMonthEntity channelComplaintDayEntity = (ChannelComplaintMonthEntity) entity;
		// 查找对应的全量统计投诉记录
		FullComplaintMonthEntity fullComplaintDayEntity = this
				.getFullComplaintMonthEntity(channelComplaintDayEntity
						.getSumMonth());
		if (fullComplaintDayEntity == null) {
			this.createNewFullComplaintMonthEntity(
					channelComplaintDayEntity.getSumMonth(),
					channelComplaintDayEntity.getNum());
		} else {
			//获取更新前的值
			//获取hibernate私有的api来获取更新前的值
			Integer oldNum = (Integer)HibernateUtil.getOldValue(super.getSession(), "com.jason.ddoWeb.entity.warning.ChannelComplaintMonthEntity", "num");
			int newNum = 0;
			if (oldNum != null) {
				newNum = fullComplaintDayEntity.getNum().intValue() + (channelComplaintDayEntity.getNum().intValue() - oldNum.intValue());
			} else {
				newNum = fullComplaintDayEntity.getNum().intValue() + channelComplaintDayEntity.getNum().intValue();
			}
			fullComplaintDayEntity.setNum(new Integer(newNum));
			super.updateEntitie(fullComplaintDayEntity);
		}
		super.saveOrUpdate(entity);
	}
	
	private FullComplaintMonthEntity getFullComplaintMonthEntity(Integer sumMonth) {
		List<FullComplaintMonthEntity> list = super.findByProperty(
				FullComplaintMonthEntity.class, "sumMonth", sumMonth);
		if (!list.isEmpty()) {
			return list.get(0);
		}
		return null;
	}

	private void createNewFullComplaintMonthEntity(Integer sumMonth, Integer num) {
		FullComplaintMonthEntity fullComplaintDayEntity = new FullComplaintMonthEntity();
		fullComplaintDayEntity.setSumMonth(sumMonth);
		fullComplaintDayEntity.setNum(num);
		super.save(fullComplaintDayEntity);
	}
	
}