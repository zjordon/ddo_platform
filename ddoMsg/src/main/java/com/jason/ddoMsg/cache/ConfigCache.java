/**
 * 
 */
package com.jason.ddoMsg.cache;

import java.io.IOException;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import com.jason.ddoMsg.util.PropertiesHelper;

/**
 * 配置缓存
 * 各种参数的配置的缓存
 * @author jasonzhang
 *
 */
public class ConfigCache {

	private static final Logger logger = Logger.getLogger(ConfigCache.class);
	
	private boolean stopAll;
	
	private boolean stopAllTask;
	
	private String ddoUrl;
	
	private String ddoUsername;
	
	private String ddoPassword;
	
	private String ddoSpId;
	
	private String ddoAsyncNotifyURL;
	
	private int monthDeductionAmountLimit;
	
	private int monthDeductionNumLimit;
	
	private String ddoChannelId;
	public void loadConfigParam() {
		logger.info("start load config");
		Properties props = PropertiesHelper.getInstance().loadProps("config/sys-config.properties", "sys-config.properties");
		if (props != null) {
			String temp = props.getProperty("stopAll");
			if (StringUtils.isNotBlank(temp)) {
				this.stopAll = Boolean.parseBoolean(temp);
			}
			temp = props.getProperty("stopAllTask");
			if (StringUtils.isNotBlank(temp)) {
				this.stopAllTask = Boolean.parseBoolean(temp);
			}
			this.ddoUrl = props.getProperty("ddoUrl");
			this.ddoUsername =  props.getProperty("ddoUsername");
			this.ddoPassword = props.getProperty("ddoPassword");
			this.ddoSpId =  props.getProperty("ddoSpId");
			this.ddoAsyncNotifyURL = props.getProperty("ddoAsyncNotifyURL");
			if (props.get("monthDeductionAmountLimit") != null) {
				this.monthDeductionAmountLimit = Integer.parseInt(props.getProperty("monthDeductionAmountLimit"));
			}
			if (props.get("monthDeductionNumLimit") != null) {
				this.monthDeductionNumLimit = Integer.parseInt(props.getProperty("monthDeductionNumLimit"));
			}
			this.ddoChannelId = props.getProperty("ddoChannelId");
		} else {
			logger.error("can not load config!!!");
		}
		logger.info("end load config");
	}

	public boolean isStopAll() {
		return stopAll;
	}

	public void setStopAll(boolean stopAll) throws CacheException {
		this.stopAll = stopAll;
		this.syncFile("stopAll", Boolean.toString(stopAll));
	}

	public boolean isStopAllTask() {
		return stopAllTask;
	}

	public void setStopAllTask(boolean stopAllTask) throws CacheException {
		this.stopAllTask = stopAllTask;
		this.syncFile("stopAllTask", Boolean.toString(stopAllTask));
	}

	public String getDdoUrl() {
		return ddoUrl;
	}

	public void setDdoUrl(String ddoUrl) throws CacheException {
		this.ddoUrl = ddoUrl;
		this.syncFile("ddoUrl", ddoUrl);
	}

	public String getDdoUsername() {
		return ddoUsername;
	}

	public void setDdoUsername(String ddoUsername) throws CacheException {
		this.ddoUsername = ddoUsername;
		this.syncFile("ddoUsername", ddoUsername);
	}

	public String getDdoPassword() {
		return ddoPassword;
	}

	public void setDdoPassword(String ddoPassword) throws CacheException {
		this.ddoPassword = ddoPassword;
		this.syncFile("ddoPassword", ddoPassword);
	}

	public String getDdoSpId() {
		return ddoSpId;
	}

	public void setDdoSpId(String ddoSpId) throws CacheException {
		this.ddoSpId = ddoSpId;
		this.syncFile("ddoSpId", ddoSpId);
	}

	public String getDdoAsyncNotifyURL() {
		return ddoAsyncNotifyURL;
	}

	public void setDdoAsyncNotifyURL(String ddoAsyncNotifyURL) throws CacheException {
		this.ddoAsyncNotifyURL = ddoAsyncNotifyURL;
		this.syncFile("ddoAsyncNotifyURL", ddoAsyncNotifyURL);
	}

	public int getMonthDeductionAmountLimit() {
		return monthDeductionAmountLimit;
	}

	public void setMonthDeductionAmountLimit(int monthDeductionAmountLimit) throws CacheException {
		this.monthDeductionAmountLimit = monthDeductionAmountLimit;
		this.syncFile("monthDeductionAmountLimit", Integer.toString(monthDeductionAmountLimit));
	}

	public int getMonthDeductionNumLimit() {
		return monthDeductionNumLimit;
	}

	public void setMonthDeductionNumLimit(int monthDeductionNumLimit) throws CacheException {
		this.monthDeductionNumLimit = monthDeductionNumLimit;
		this.syncFile("monthDeductionNumLimit", Integer.toString(monthDeductionNumLimit));
	}

	public String getDdoChannelId() {
		return ddoChannelId;
	}

	public void setDdoChannelId(String ddoChannelId) throws CacheException {
		this.ddoChannelId = ddoChannelId;
		this.syncFile("ddoChannelId", this.ddoChannelId);
	}

	private void syncFile(String key, String value) throws CacheException {
		try {
			PropertiesHelper.getInstance().writeProps("config/sys-config.properties", "sys-config.properties", key, value);
		} catch (IOException e) {
			logger.error("exception when syncFile", e);
			throw new CacheException(e.getMessage());
		}
	}
}
