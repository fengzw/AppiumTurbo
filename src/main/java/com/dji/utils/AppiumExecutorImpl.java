package com.dji.utils;


import java.io.IOException;
import java.util.Set;

import org.openqa.selenium.By;

import com.dji.object.Locator;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;

/**
 * 封装一个AppiumExecutorImpl类，实现AppiumExecutor接口
 * 
 * @author Charlie.chen
 *
 */
public class AppiumExecutorImpl implements AppiumExecutor {
	
	private  AppiumDriver<?> driver;
	private TouchAction action;
	
	public LogUtil log=new LogUtil(this.getClass());
	
	
	public AppiumExecutorImpl(AppiumDriver<?> driver) {
		this.driver = driver;
		action=new TouchAction(driver);
	}
	
	
	public AppiumDriver<?> getDriver() {
		return driver;
	}
	public void setDriver(AppiumDriver<?> driver) {
		this.driver = driver;
	}
	
		
	
	/**
	 * 在元素中中输入文本
	 * 
	 * @author Charlie.chen
	 * @param locator
	 * @param values
	 * @throws Exception
	 */
	public void type(Locator locator, String values){
		MobileElement e = (MobileElement) findElement(locator);
		e.sendKeys(values);
	}


    /**
     * 点击元素
     * 
     * @author Charlie.chen
     * @param locator
     * @throws Exception
     */
	public void click(Locator locator) {
		MobileElement e = (MobileElement) findElement(locator);
		e.click();
	}

	
	
	/**
	 * 获取元素文本信息
	 * 
	 * @author Charlie.chen
	 * @param locator
	 */
	public String getText(Locator locator) {
		// TODO Auto-generated method stub
		MobileElement e = (MobileElement) findElement(locator);
		String text=e.getText();
		return text;
	}
	
	
	/**
	 * 获取元素
	 * 
	 * @author Charlie.chen
	 * @param locator
	 * @return
	 */
	public MobileElement findElement(Locator locator) {
		MobileElement e=null;
		switch (locator.getBy()) {
		case xpath:
			e = (MobileElement) driver.findElement(By.xpath(locator.getAddress()));	
			break;
		case id:
			e = (MobileElement) driver.findElement(By.id(locator.getAddress()));
			break;
		case name:
			e = (MobileElement) driver.findElement(By.name(locator.getAddress()));
			break;
		case className:
			e = (MobileElement) driver.findElement(By.className(locator.getAddress()));
			break;
		default:
			e = (MobileElement) driver.findElement(By.id(locator.getAddress()));
		}
		return e;

	}
		
	
	/**
	 * 向左滑动
	 */
	public void swipeToLeft() {
		int x = driver.manage().window().getSize().width;
		int y = driver.manage().window().getSize().height;
		try {
			driver.swipe((x / 8 * 7), (y / 2 * 1), (x / 8 * 1), (y / 2 * 1), 1000);
		} catch (Exception e) {
			driver.swipe((x / 8 * 7), (y / 2 * 1), (x / 8 * 1), (y / 2 * 1), 1000);
		}
	}

	/**
	 * 向右滑动
	 */
	public void swipeToRight() {
		int x = driver.manage().window().getSize().width;
		int y = driver.manage().window().getSize().height;
		try {
			driver.swipe((x / 8 * 1), (y / 2 * 1), (x / 8 * 7), (y / 2 * 1), 1000);
		} catch (Exception e) {
			driver.swipe((x / 8 * 1), (y / 2 * 1), (x / 8 * 7), (y / 2 * 1), 1000);
		}
	}

	/**
	 * 向上滑动
	 */
	public void swipeToUp() {
		int x = driver.manage().window().getSize().width;
		int y = driver.manage().window().getSize().height;
		try {
			driver.swipe((x / 2 * 1), (y / 4 * 3), (x / 2 * 1), (y / 4 * 1), 1500);
		} catch (Exception e) {
			driver.swipe((x / 2 * 1), (y / 4 * 3), (x / 2 * 1), (y / 4 * 1), 1500);
		}
	}

	/**
	 * 向下滑动
	 */
	public void swipeToDown() {
		int x = driver.manage().window().getSize().width;
		int y = driver.manage().window().getSize().height;
		try {
			driver.swipe((x / 2 * 1), (y / 8 * 1), (x / 2 * 1), (y / 8 * 7), 1000);
		} catch (Exception e) {
			driver.swipe((x / 2 * 1), (y / 8 * 1), (x / 2 * 1), (y / 8 * 7), 1000);
		}
	}
	
	

	/**
	 * 通过坐标点击 
	 */
	public void tapByXY(int x, int y) {

		try {
			action.tap(x, y).release().perform();
		} catch (Exception e) {
			System.out.println("点击失败");
		}
	}
	
	
	/**
	 * 拖动元素
	 * 
	 * @author Charlie.chen
	 */
	public void dragElement(Locator locator,Locator locator2){
		action.press(findElement(locator)).moveTo(findElement(locator2)).release().perform();

	}
	
		
	/**
	 * 判断元素是否出现
	 * 
	 * @author Charlie.chen
	 * @param locator
	 * @param timeOut
	 * @return
	 * @throws IOException
	 */
	public boolean isElementDisplayed( Locator locator) {
		boolean flag = false;
		try {
			findElement(locator).isDisplayed();
			flag = true;
		} catch (Exception e) {
			flag = false;
		}
		return flag;
	}
	
	/**
	 * 切换Webview页面查找元素
	 * 
	 * @author Charlie.chen
	 */
	public  void switchtoWebview(){
		try {
			
			 Set<String> contextNames  =driver.getContextHandles();
				for (String contextName : contextNames ) {
					System.out.println(contextName);
				    //if(contextName.contains("WEBVIEW")||contextName.contains("webview"))
					if (contextName.toLowerCase().contains("webview")) {
					    driver.context(contextName);	
					    System.out.println("跳转到web页 开始操作web页面"); 
						break;
					}
				}
		}catch (Exception e) {
		     e.printStackTrace();
		}
	}

}
