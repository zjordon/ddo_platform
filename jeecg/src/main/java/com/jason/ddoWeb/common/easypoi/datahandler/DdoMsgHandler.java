/**
 * 
 */
package com.jason.ddoWeb.common.easypoi.datahandler;

import java.util.Map;

import org.jeecgframework.poi.handler.inter.IExcelDataHandler;

/**
 * @author jasonzhang
 *
 */
public class DdoMsgHandler implements IExcelDataHandler {
	
	private String[] needHandlerFields = new String[]{"价格", "号码归属省份", "号码归属地市", "渠道"};
	
	private Map<String, Double> priceMap;
	private Map<String, String> provinceCodeMap;
	private Map<String, String> cityCodeMap;
	private Map<String, String> channelNameMap;

	/* (non-Javadoc)
	 * @see org.jeecgframework.poi.handler.inter.IExcelDataHandler#getNeedHandlerFields()
	 */
	@Override
	public String[] getNeedHandlerFields() {
		return this.needHandlerFields;
	}

	/* (non-Javadoc)
	 * @see org.jeecgframework.poi.handler.inter.IExcelDataHandler#setNeedHandlerFields(java.lang.String[])
	 */
	@Override
	public void setNeedHandlerFields(String[] fields) {
		this.needHandlerFields = fields;

	}

	/* (non-Javadoc)
	 * @see org.jeecgframework.poi.handler.inter.IExcelDataHandler#exportHandler(java.lang.Object, java.lang.String, java.lang.Object)
	 */
	@Override
	public Object exportHandler(Object obj, String name, Object value) {
		if ("价格".equals(name) && this.priceMap != null) {
			return this.priceMap.get(value);
		} else if ("号码归属省份".equals(name) && this.provinceCodeMap != null) {
			return this.provinceCodeMap.get(value);
		} else if ("号码归属地市".equals(name) && this.cityCodeMap != null) {
			return this.cityCodeMap.get(value);
		}else if ("渠道".equals(name) && this.channelNameMap != null) {
			return this.channelNameMap.get(value);
		}
		return value;
	}

	/* (non-Javadoc)
	 * @see org.jeecgframework.poi.handler.inter.IExcelDataHandler#importHandler(java.lang.Object, java.lang.String, java.lang.Object)
	 */
	@Override
	public Object importHandler(Object obj, String name, Object value) {
		//do nothing
		return null;
	}

	public void setPriceMap(Map<String, Double> priceMap) {
		this.priceMap = priceMap;
	}

	public void setProvinceCodeMap(Map<String, String> provinceCodeMap) {
		this.provinceCodeMap = provinceCodeMap;
	}

	public void setCityCodeMap(Map<String, String> cityCodeMap) {
		this.cityCodeMap = cityCodeMap;
	}

	public void setChannelNameMap(Map<String, String> channelNameMap) {
		this.channelNameMap = channelNameMap;
	}

}
