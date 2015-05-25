/**
 * 
 */
package com.jason.ddoWeb.service.warning;

import java.util.Map;

/**
 * @author jasonzhang
 *
 */
public interface ConsumeServiceI {

	void saveConsumeLimit(double amountLimit, int num);
	
	Map<String, String> getConsumeLimit();
}
