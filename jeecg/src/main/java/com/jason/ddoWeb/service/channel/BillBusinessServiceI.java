package com.jason.ddoWeb.service.channel;

import java.util.List;
import java.util.Map;

import org.jeecgframework.core.common.service.CommonService;

import com.jason.ddoWeb.entity.channel.BillBusinessEntity;

public interface BillBusinessServiceI extends CommonService{

	List<BillBusinessEntity> findEnableBusiness(String channelId);
	
	Map<String, Double> getPriceMap();
}
