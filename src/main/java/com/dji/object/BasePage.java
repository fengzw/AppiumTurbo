package com.dji.object;

import java.io.IOException;
import java.util.HashMap;

import com.dji.utils.AppiumExecutorImpl;
import com.dji.utils.LogUtil;
import com.dji.utils.XmlUtils;


import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;


/**
 * 封装一个BasePage的类，毕竟所有的页面都有共同的东西，每个页面都有元素，每个页面元素都有相应的方法
 * 
 * @author Charlie.chen
 *
 */

public class BasePage extends AppiumExecutorImpl {

	protected AppiumDriver<?> driver;
	protected String pageName;	//页面名称
	protected String xmlPath;   //页面元素路径
	protected HashMap<String, Locator> locatorMap;
	public LogUtil log = new LogUtil(this.getClass());

	
	public BasePage(AppiumDriver<?> driver,String pageName) throws Exception  {
		super(driver);
		this.driver = driver;
		this.pageName=pageName;
		
		//获取资源文件page.xml的路径
		//xmlPath=System.getProperty("user.dir")+"\\src\\main\\java\\com\\dji\\pageObject\\Page.xml";	
    	xmlPath=BasePage.class.getClassLoader().getResource("page.xml").getPath();
		
		//locatorMap = XmlUtils.readXMLDocument(xmlPath, this.getClass().getSimpleName());
		locatorMap = XmlUtils.readXMLDocument(xmlPath, pageName);

	}
	
	
	
	public void type(String locatorName, String values) {
		super.type(getLocator(locatorName), values);
		log.info("type value is:  " + values);
	}


	public void click(String locatorName) {
		super.click(getLocator(locatorName));
		log.info("click: "+locatorName);
	}
	
	
	public String getText(String locatorName) {
		// TODO Auto-generated method stub
		return super.getText(getLocator(locatorName));
	}


	public MobileElement findElement(String locatorName) {
		// TODO Auto-generated method stub
		return super.findElement(getLocator(locatorName));
	}

	public boolean isElementDisplayed(String locatorName) {
		// TODO Auto-generated method stub
		return super.isElementDisplayed(getLocator(locatorName));
	}

	
	public void dragElement(String locatorName,String locatorName2){
		super.dragElement(getLocator(locatorName), getLocator(locatorName2));

	}
	
	/**
	 * 根据locatorName获取Locator
	 * 
	 * @author Charlie.chen
	 * @param locatorName
	 * @return
	 * @throws IOException
	 */
	public  Locator getLocator(String locatorName) {

		Locator locator =  null;
		
		if(locatorMap!=null)
		{
			locator = locatorMap.get(locatorName);
		}
		return locator;
	}
		
}
