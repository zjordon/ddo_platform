package com.jason.ddoWeb.service.channel;

import org.jeecgframework.core.common.service.CommonService;

public interface ChannelBusinessServiceI extends CommonService{

	/**
	 * 根据传入的计费业务id自动生成渠道计费点，以计费业务id和渠道id作为唯一性判断，如果已经存在了则不能自动生成
	 * @param billBusinessId
	 */
	void saveChannelBusinessesByBB(String billBusinessId, String channelBillCode);
	/**
	 * 根据传入的渠道id自动生成渠道计费点,以计费业务id和渠道id作为唯一性判断，如果已经存在了则不能自动生成
	 * @param channelId
	 */
	void saveChannelBusinessesByChannel(String channelId, Long channelNo);
}
