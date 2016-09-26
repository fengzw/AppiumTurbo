package com.dji.utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import com.dji.object.Locator;
import com.dji.object.Locator.ByType;


/**
 *  将元素放在page.xml统一管理，要获取元素的信息，通过pageName从xml文件中读取。
 *  读取xml的页面元素是使用dom4j开源框架
 *  
 * @author Charlie.chen
 *
 */

public class XmlUtils {


	public static HashMap<String, Locator> readXMLDocument(String xmlPath,String pageName) throws Exception {

		LogUtil log = new LogUtil(XmlUtils.class);
		
		HashMap<String, Locator> locatorMap = new HashMap<String, Locator>();
		locatorMap.clear();
		
		File file = new File(xmlPath);
		if (!file.exists()) {
			log.error("Can't find " + xmlPath);
			return locatorMap=null;
		}
		
		//创建SAXReader对象
		SAXReader reader = new SAXReader();
		//读取文件 转换成Document 
		Document document = reader.read(file);
		//获取根节点元素对象
		Element root = document.getRootElement();
		
		//遍历
		for (Iterator<?> i = root.elementIterator(); i.hasNext();) {
			Element page = (Element) i.next();
			if (page.attribute(0).getValue().equalsIgnoreCase(pageName)) {
				log.info("pageName is:" + pageName);
				
				for (Iterator<?> l = page.elementIterator(); l.hasNext();) {
					String type = null;
					String timeOut = "3";
					String value = null;
					String locatorName = null;
					Element locator = (Element) l.next();
					
					for (Iterator<?> j = locator.attributeIterator(); j.hasNext();) {
						Attribute attribute = (Attribute) j.next();
						if (attribute.getName().equals("type")) {
							type = attribute.getValue();
							//log.info("get locator type " + type);
						} else if (attribute.getName().equals("timeOut")) {
							timeOut = attribute.getValue();
							//log.info("get locator timeOut " + timeOut);
						} else {
							value = attribute.getValue();
							//log.info("get locator value " + value);
						}

					}
					Locator temp = new Locator(value,Integer.parseInt(timeOut), getByType(type));
					locatorName = locator.getText();
					//log.info("locatorName is " + locatorName);
					locatorMap.put(locatorName, temp);
				}
				continue;
			}

		}
		return locatorMap;

	}
	
	public static ByType getByType(String type) {
		ByType byType = ByType.xpath;
		if (type == null || type.equalsIgnoreCase("xpath")) {
			byType = ByType.xpath;
		} else if (type.equalsIgnoreCase("id")) {
			byType = ByType.id;
		} else if (type.equalsIgnoreCase("name")) {
			byType = ByType.name;
		} else if (type.equalsIgnoreCase("className")) {
			byType = ByType.className;
		}
		return byType;
	}

}
