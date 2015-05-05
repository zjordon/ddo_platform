package org.jeecgframework.tag.core.easyui;

import java.io.IOException;

import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.commons.lang.StringUtils;
import org.jeecgframework.core.common.model.json.ComboBox;
import org.jeecgframework.core.util.StringUtil;

/**
 * 
 * 类描述：下拉选择框标签
 * 
 * @author: 张代浩
 * @date： 日期：2012-12-7 时间：上午10:17:45
 * @version 1.0
 */
public class ComboBoxTag extends TagSupport {
	protected String id;// ID
	protected String text;// 显示文本
	protected String url;// 远程数据
	protected String name;// 控件名称
	protected Integer width;// 宽度
	protected Integer listWidth;// 下拉框宽度
	protected Integer listHeight;// 下拉框高度
	protected boolean editable;// 定义是否可以直接到文本域中键入文本
	// modify by jasonzhang 20150415:增加multiple属性以控制下拉框可以单选还是多选
	private boolean multiple = true;
	// modify by jasonzhang 20150415:增加selectedValue属性设置下拉框的默认选中值
	private String selectedValue;

	public int doStartTag() throws JspTagException {
		return EVAL_PAGE;
	}

	public int doEndTag() throws JspTagException {
		try {
			JspWriter out = this.pageContext.getOut();
			out.print(end().toString());
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return EVAL_PAGE;
	}

	public StringBuffer end() {
		StringBuffer sb = new StringBuffer();
//		ComboBox comboBox = new ComboBox();
//		comboBox.setText(text);
//		comboBox.setId(id);
		//modify by jason 20150420
		//如果name带有点号如果"business.id"，则用点位符\\.替换掉.
		String jqueryIdName = name;
		if (name.indexOf('.') > -1) {
			jqueryIdName = StringUtils.replace(jqueryIdName, ".", "\\\\.");
		}
		sb.append("<script type=\"text/javascript\">")
				.append("$(function() {")
				.append("$(\'#" + jqueryIdName + "\').combobox({")
				.append("url:\'" + url + "&id=" + id + "&text=" + text + "\',")
				.append("editable:\'false\',")
				.append("multiple:").append((Boolean.toString(this.multiple))).append(",")
				.append("valueField:\'id\',")
				.append("textField:\'text\',")
				.append("width:\'" + width + "\',")
				.append("listWidth:\'" + listWidth + "\',")
				.append("listHeight:\'" + listWidth + "\',")
				.append("onChange:function(){")
				.append("var val = $(\'#" + jqueryIdName
						+ "\').combobox(\'getValues\');")
				.append("$(\'#" + jqueryIdName + "hidden\').val(val);").append("},");
		// modify by jasonzhang 20150415:增加selectedValue属性设置下拉框的默认选中值
		sb.append("onLoadSuccess:function(data){");
		if (StringUtil.isNotEmpty(this.selectedValue)) {
			sb.append("$(\'#").append(jqueryIdName).append("\').combobox(\'setValue\',\'").append(this.selectedValue).append("\');");
		}
		sb.append("}").append("});});");
	    sb.append("</script>");
		sb.append(
				"<input type=\"hidden\" name=\"").append(name).append("\" id=\"").append(name).append("hidden\"");
		// modify by jasonzhang 20150415:增加selectedValue属性设置下拉框的默认选中值
		if (StringUtil.isNotEmpty(this.selectedValue)) {
			sb.append(" value=\"").append(this.selectedValue).append("\"");
		}
		sb.append(" > ");
		
		sb.append("<input class=\"easyui-combobox\" ")
				.append(" panelHeight=\"auto\" name=\"" + name + "name\" id=\""
						+ name + "\" >");
		return sb;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setText(String text) {
		this.text = text;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setMultiple(boolean multiple) {
		this.multiple = multiple;
	}

	public void setSelectedValue(String selectedValue) {
		this.selectedValue = selectedValue;
	}

}
