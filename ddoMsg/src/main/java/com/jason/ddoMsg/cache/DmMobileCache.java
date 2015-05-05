/**
 * 
 */
package com.jason.ddoMsg.cache;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.jason.ddoMsg.util.DmMobile;
import com.jason.ddoMsg.util.DmMobileArrays;
import com.jason.ddoMsg.util.PropertiesHelper;

/**
 * 手机号归属地缓存
 * 
 * @author jasonzhang
 *
 */
public class DmMobileCache {
	private static final Logger logger = Logger.getLogger(DmMobileCache.class);

	private DmMobile[] mobileArray;

	public void init() {
		// 从配置文件中获取存放数据的路径
		Properties props = PropertiesHelper.getInstance().loadProps(
				"config/setting.properties", "setting.properties");
		if (props != null) {
			String filePath = props.getProperty("dmMobileFilePath");
			if (filePath != null) {
				// 从文件中读取数据
				File file = new File(filePath);
				if (file.exists()) {
					try {
						FileReader fileReader = new FileReader(file);
						int ch = -1;
						StringBuilder builder = new StringBuilder();
						int recordNum = 0;
						// 第1行是记录总数
						while ((ch = fileReader.read()) != -1) {
							char c = (char) ch;
							if (c == '\n') {
								// 换行符，读取记录总数绿豆
								recordNum = Integer
										.parseInt(builder.toString());
								builder.delete(0, builder.length());
								break;
							} else if (c != '\r') {

								builder.append(c);

							}
						}

						if (recordNum > 0) {
							// 每2行开始是数据
							this.mobileArray = new DmMobile[recordNum];
							int idx = 0;
							int separateNum = 0;
							this.mobileArray[idx] = new DmMobile();
							while ((ch = fileReader.read()) != -1) {
								char c = (char) ch;
								if (c == '\n') {
									// 换行符
									this.mobileArray[idx++].cityCode = Integer
											.parseInt(builder.toString());
									builder.delete(0, builder.length());
									separateNum = 0;
									// 初始化数组中的下一个对象
									if (idx < recordNum) {
										this.mobileArray[idx] = new DmMobile();
									}
								} else if (c == '|') {
									separateNum++;
									if (separateNum == 1) {
										// 第一个分隔符读取完后取得的是手机号码
										this.mobileArray[idx].msisdn = Integer
												.parseInt(builder.toString());
									} else {
										// 第二个分隔符读取完后取得的是省份编码
										this.mobileArray[idx].provinceCode = Integer
												.parseInt(builder.toString());
									}
									builder.delete(0, builder.length());
								} else if (c != '\r') {
									builder.append(c);
								}
							}
						}

						// 关闭文件流
						fileReader.close();
					} catch (FileNotFoundException e) {
						logger.error("exception when init dm mobile cache", e);
					} catch (IOException e) {
						logger.error("exception when init dm mobile cache", e);
					}

				} else {
					logger.error("dm-mobile.txt not found!!!");
				}
			} else {
				logger.error("can not get dmMobileFilePath prop");
			}
		} else {
			logger.error("can not load setting config file");
		}

	}

	public DmMobile getDmMobile(long msisdn) {
		// 截取手机号的前三位以判断是哪个运营商的号码
		int mobileType = (int) (msisdn / 100000000);
		if (mobileType == 134 || mobileType == 135 || mobileType == 136
				|| mobileType == 137 || mobileType == 138 || mobileType == 139
				|| mobileType == 150 || mobileType == 151 || mobileType == 152
				|| mobileType == 157 || mobileType == 158 || mobileType == 159
				|| mobileType == 182 || mobileType == 183 || mobileType == 184
				|| mobileType == 187 || mobileType == 188) {
			// 截取手机号的前七位
			int dmMobile = (int) (msisdn / 10000);
			int i = DmMobileArrays.binarySearch(this.mobileArray, dmMobile);
			if (i > -1) {
				return this.mobileArray[i];
			}
		}
		return null;
	}

	public void setMobileArray(DmMobile[] mobileArray) {
		this.mobileArray = mobileArray;
	}

}
