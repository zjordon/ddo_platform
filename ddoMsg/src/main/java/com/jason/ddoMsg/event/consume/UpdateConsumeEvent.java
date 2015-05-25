/**
 * 
 */
package com.jason.ddoMsg.event.consume;

import java.util.Map;

import org.apache.log4j.Logger;

import com.jason.ddoMsg.cache.CacheException;
import com.jason.ddoMsg.cache.CacheManager;
import com.jason.ddoMsg.event.AbstractEvent;
import com.jason.ddoMsg.event.EventException;

/**
 * @author jasonzhang
 *
 */
public class UpdateConsumeEvent extends AbstractEvent {
	private static final Logger logger = Logger
			.getLogger(UpdateConsumeEvent.class);

	/* (non-Javadoc)
	 * @see com.jason.ddoMsg.event.AbstractEvent#processEvent(java.lang.String)
	 */
	@Override
	public void processEvent(String param) throws EventException {
		Map<String, String> paramMap = super.parseNecessaryParam(param, "monthDeductionAmountLimit", "monthDeductionNumLimit");
		String monthDeductionAmountLimit = paramMap.get("monthDeductionAmountLimit");
		String monthDeductionNumLimit = paramMap.get("monthDeductionNumLimit");
		super.validDigital(monthDeductionAmountLimit);
		super.validDigital(monthDeductionNumLimit);
		try {
			CacheManager.getInstance().getConfigCache().setMonthDeductionAmountLimit(Integer.parseInt(monthDeductionAmountLimit));
			CacheManager.getInstance().getConfigCache().setMonthDeductionNumLimit(Integer.parseInt(monthDeductionNumLimit));
		} catch (NumberFormatException e) {
			logger.error("exception when process UpdateConsumeEvent", e);
			throw new EventException(e.getMessage());
		} catch (CacheException e) {
			logger.error("exception when process UpdateConsumeEvent", e);
			throw new EventException(e.getMessage());
		}
		
	}

}
