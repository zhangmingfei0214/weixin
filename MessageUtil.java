package com.imooc.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import com.imooc.po.TextMessage;
import com.thoughtworks.xstream.XStream;

public class MessageUtil {
	
	public static final String MESSAGE_TEXT = "text";
	public static final String MESSAGE_IMAGE = "image";
	public static final String MESSAGE_VOICE = "voice";
	public static final String MESSAGE_VIDEO = "video";
	public static final String MESSAGE_LINK = "link";
	public static final String MESSAGE_LOCATION ="location";
	public static final String MESSAGE_EVENT ="event";
	public static final String MESSAGE_SUBSCRIBE = "subsctribe";
	public static final String MESSAGE_UNSUBSCRIBE = "unsubscribe";
	public static final String MESSAGE_CLICk = "click";
	public static final String MESSAGE_VIEW = "view";
			
	
	/**
	 * xml转为map集合
	 * @param request
	 * @return
	 * @throws DocumentException
	 * @throws IOException
	 */
	public static Map<String,String> xmlToMap(HttpServletRequest request) throws DocumentException, IOException {
		Map<String,String> map = new HashMap<String,String>();
		SAXReader reader = new SAXReader();
		InputStream ins = request.getInputStream();
		Document doc = reader.read(ins);
		
		Element root = (Element) doc.getRootElement();
		
		List<org.dom4j.Element> list = ((org.dom4j.Element) root).elements();
		for(org.dom4j.Element e:list) {
			map.put(e.getName(), e.getText());
		}
		ins.close();
		return map;
	}
	/**
	 * 将文本消息对象转为xml
	 * @param textMessage
	 */
	public static String textMessageToXml(TextMessage textMessage) {
		XStream xstream = new XStream();
		xstream.alias(("xml", textMessage.getClass());
		return xstream.toXML(textMessage);
	}
	public static String initText(String toUserName,String fromUserName,String content) {
		TextMessage text = new TextMessage();
		text.setFromUserName(toUserName);
		text.setToUserName(fromUserName);
		text.setMsgType(MessageUtil.MESSAGE_TEXT);
		text.setCreateTime(new Date().getTime());
		text.setContent(content);
		return textMessageXml(text);
		
	}
	
	/**
	 * 主菜单
	 * @return
	 */
	public static String menuText() {
		StringBuffer sb = new StringBuffer();
		sb.append("欢迎您的关注，请按照菜单提示进行操作：\n\n");
		sb.append("1.课程介绍\n");
		sb.append("2.慕课网介绍\n\n");
		sb.append("回复？调出此菜单。");
		return sb.toString();
		
	}
	public static String firstMenu() {
		StringBuffer sb = new StringBuffer();
		sb.append("本套课程介绍微信公众号开发，主要涉及公众号介绍。编辑模式介绍、开发模式介绍等");
		return sb.toString();
	}
	public static String SecondMenu() {
		StringBuffer sb = new StringBuffer();
		sb.append("本套课程介绍慕课网的学习和开发过程，主要涉及慕课网中学习的方法和方式等");
		return sb.toString();
	}
}
