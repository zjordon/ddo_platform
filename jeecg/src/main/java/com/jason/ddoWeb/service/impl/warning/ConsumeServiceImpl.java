/**
 * 
 */
package com.jason.ddoWeb.service.impl.warning;

import java.util.HashMap;
import java.util.Map;

import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jason.ddoWeb.common.model.Setting;
import com.jason.ddoWeb.entity.event.EventEntity;
import com.jason.ddoWeb.service.warning.ConsumeServiceI;
import com.jason.ddoWeb.util.SettingUtil;

/**
 * @author jasonzhang
 *
 */
@Service("consumeService")
@Transactional
public class ConsumeServiceImpl extends CommonServiceImpl implements ConsumeServiceI {

	/* (non-Javadoc)
	 * @see com.jason.ddoWeb.service.warning.ConsumeServiceI#saveConsumeLimit(double, int)
	 */
	@Override
	public void saveConsumeLimit(double amountLimit, int num) {
		Setting setting = SettingUtil.getSetting();
		setting.setMonthDeductionAmountLimit(amountLimit);
		setting.setMonthDeductionNumLimit(num);
		SettingUtil.writeSetting(setting);
		//创建到收发引擎的事件
		EventEntity event = new EventEntity();
		event.setEventId("UpdateConsumeEvent");
		StringBuilder builder = new StringBuilder();
		builder.append("monthDeductionAmountLimit:").append(Math.round(amountLimit*100));
		builder.append(",monthDeductionNumLimit:").append(num);
		event.setParam(builder.toString());
		super.save(event);
	}

	/* (non-Javadoc)
	 * @see com.jason.ddoWeb.service.warning.ConsumeServiceI#getConsumeLimit()
	 */
	@Override
	public Map<String, String> getConsumeLimit() {
		Map<String, String> map = new HashMap<String, String>(2);
		Setting setting = SettingUtil.getSetting();
		map.put("monthDeductionAmountLimit", Double.toString(setting.getMonthDeductionAmountLimit()));
		map.put("monthDeductionNumLimit", Integer.toString(setting.getMonthDeductionNumLimit()));
		return map;
	}

}
