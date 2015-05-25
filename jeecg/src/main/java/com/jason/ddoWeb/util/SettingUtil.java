/**
 * 
 */
package com.jason.ddoWeb.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URISyntaxException;

import net.sf.ehcache.Cache;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.springframework.core.io.ClassPathResource;

import com.jason.ddoWeb.common.model.Setting;

/**
 * @author jasonzhang
 *
 */
public class SettingUtil {

	private static final String SETTING_XML_FILE_NAME = "setting.xml";// setting.xml配置文件名称
	

	/**
	 * 读取系统配置信息
	 * 
	 * @return Setting
	 * 
	 * @throws URISyntaxException 
	 * 
	 * @throws DocumentException 
	 * 
	 * @throws IOException 
	 */
	public static Setting readSetting() throws URISyntaxException, DocumentException, IOException {
		File settingXmlFile = new ClassPathResource(SETTING_XML_FILE_NAME).getFile();
		SAXReader saxReader = new SAXReader();
		Document document = saxReader.read(settingXmlFile);
		Node monthDeductionAmountLimitNode = document.selectSingleNode("/setting/monthDeductionAmountLimit");
		Node monthDeductionNumLimitNode = document.selectSingleNode("/setting/monthDeductionNumLimit");
		
		Setting setting = new Setting();
		setting.setMonthDeductionAmountLimit(Double.parseDouble(monthDeductionAmountLimitNode.getText()));
		setting.setMonthDeductionNumLimit(Integer.parseInt(monthDeductionNumLimitNode.getText()));
		
		return setting;
	}
	
	/**
	 * 获取系统配置信息
	 * 
	 * @return Setting
	 */
	public static Setting getSetting() {
		Setting setting = null;
		
			try {
				setting = readSetting();
			} catch (URISyntaxException e) {
				e.printStackTrace();
				
			} catch (DocumentException e) {
				e.printStackTrace();
				
			} catch (IOException e) {
				e.printStackTrace();
				
			}
		return setting;
	}
	
	/**
	 * 写入系统配置信息
	 * 
	 * @return Setting
	 */
	public static void writeSetting(Setting setting) {
		File settingXmlFile = null;
		Document document = null;
		try {
			settingXmlFile = new ClassPathResource(SETTING_XML_FILE_NAME).getFile();
			SAXReader saxReader = new SAXReader();
			document = saxReader.read(settingXmlFile);
		} catch (Exception e) {
			e.printStackTrace();
		}
		Element rootElement = document.getRootElement();
		Node monthDeductionAmountLimitNode = document.selectSingleNode("/setting/monthDeductionAmountLimit");
		Node monthDeductionNumLimitNode = document.selectSingleNode("/setting/monthDeductionNumLimit");
		
		
		if(monthDeductionAmountLimitNode == null){
			monthDeductionAmountLimitNode = rootElement.addElement("monthDeductionAmountLimit");
		}
		if(monthDeductionNumLimitNode == null){
			monthDeductionNumLimitNode = rootElement.addElement("monthDeductionNumLimit");
		}
		
		monthDeductionAmountLimitNode.setText(Double.toString(setting.getMonthDeductionAmountLimit()));
		monthDeductionNumLimitNode.setText(Integer.toString(setting.getMonthDeductionNumLimit()));
		
		try {
			OutputFormat outputFormat = OutputFormat.createPrettyPrint();// 设置XML文档输出格式
			outputFormat.setEncoding("UTF-8");// 设置XML文档的编码类型
			outputFormat.setIndent(true);// 设置是否缩进
			outputFormat.setIndent("	");// 以TAB方式实现缩进
			outputFormat.setNewlines(true);// 设置是否换行
			XMLWriter xmlWriter = new XMLWriter(new FileOutputStream(settingXmlFile), outputFormat);
			xmlWriter.write(document);
			xmlWriter.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
