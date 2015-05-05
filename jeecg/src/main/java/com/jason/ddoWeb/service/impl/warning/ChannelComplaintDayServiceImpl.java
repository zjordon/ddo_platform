package com.jason.ddoWeb.service.impl.warning;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jason.ddoWeb.entity.warning.ChannelComplaintDayEntity;
import com.jason.ddoWeb.entity.warning.FullComplaintDayEntity;
import com.jason.ddoWeb.service.warning.ChannelComplaintDayServiceI;
import com.jason.ddoWeb.common.hibernate.HibernateUtil;

import org.hibernate.engine.spi.EntityEntry;
import org.hibernate.engine.spi.PersistenceContext;
import org.hibernate.persister.entity.EntityPersister;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;

@Service("channelComplaintDayService")
@Transactional
public class ChannelComplaintDayServiceImpl extends CommonServiceImpl implements
		ChannelComplaintDayServiceI {

	@Override
	public <T> Serializable save(T entity) {
		ChannelComplaintDayEntity channelComplaintDayEntity = (ChannelComplaintDayEntity) entity;
		// 查找对应的全量统计投诉记录
		FullComplaintDayEntity fullComplaintDayEntity = this
				.getFullComplaintDayEntity(channelComplaintDayEntity
						.getSumDate());
		if (fullComplaintDayEntity == null) {
			this.createNewFullComplaintDayEntity(
					channelComplaintDayEntity.getSumDate(),
					channelComplaintDayEntity.getNum());
		} else {
			fullComplaintDayEntity.setNum(new Integer(fullComplaintDayEntity
					.getNum().intValue()
					+ channelComplaintDayEntity.getNum().intValue()));
			super.updateEntitie(fullComplaintDayEntity);
		}
		return super.save(entity);
	}

	@Override
	public <T> void saveOrUpdate(T entity) {
		ChannelComplaintDayEntity channelComplaintDayEntity = (ChannelComplaintDayEntity) entity;
		// 查找对应的全量统计投诉记录
		FullComplaintDayEntity fullComplaintDayEntity = this
				.getFullComplaintDayEntity(channelComplaintDayEntity
						.getSumDate());
		if (fullComplaintDayEntity == null) {
			this.createNewFullComplaintDayEntity(
					channelComplaintDayEntity.getSumDate(),
					channelComplaintDayEntity.getNum());
		} else {
			//获取更新前的值
			//获取hibernate私有的api来获取更新前的值
			Integer oldNum = (Integer)HibernateUtil.getOldValue(super.getSession(), "com.jason.ddoWeb.entity.warning.ChannelComplaintDayEntity", "num");
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

	private FullComplaintDayEntity getFullComplaintDayEntity(Integer sumDate) {
		List<FullComplaintDayEntity> list = super.findByProperty(
				FullComplaintDayEntity.class, "sumDate", sumDate);
		if (!list.isEmpty()) {
			return list.get(0);
		}
		return null;
	}

	private void createNewFullComplaintDayEntity(Integer sumDate, Integer num) {
		FullComplaintDayEntity fullComplaintDayEntity = new FullComplaintDayEntity();
		fullComplaintDayEntity.setSumDate(sumDate);
		fullComplaintDayEntity.setNum(num);
		super.save(fullComplaintDayEntity);
	}

}