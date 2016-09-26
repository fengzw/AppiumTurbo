package com.dji.action;

import java.util.concurrent.TimeUnit;

import com.dji.object.BasePage;

import io.appium.java_client.AppiumDriver;

/**
 * 登录action
 * @author charlie.chen
 *
 */

public class Login  {
   
	 private AppiumDriver<?> driver;

	 private BasePage loginPage=null;

	public Login(AppiumDriver<?> driver) {
		this.driver = driver;
	}

	
	//通过登录按钮登录
	public void loginByButton(String userName,String pwd) throws Exception{
		new BasePage(driver,"menuPage").click("我");
		isLogined();
		new BasePage(driver,"minePageNo").click("登录");
		login(userName,pwd);
	}

	
	//通过DJI Store登录
	public void loginByStore(String email,String pwd) throws Exception{
		isLogined();
		new BasePage(driver,"minePageNo").click("DJI商城");
		login(email,pwd);
	}
	
	
	// 通过DJI Academy登录
	public void loginByAcademy(String email,String pwd) throws Exception{
		isLogined();
		new BasePage(driver,"minePageNo").click("DJI论坛");
		login(email,pwd);
	}
	
	
	//判断是否已登录，如果已登录就先退出登录
	public void isLogined() throws Exception{
		BasePage minePage=new BasePage(driver,"minePage");
		boolean flag=minePage.isElementDisplayed("用户图像");
		if(flag){
			minePage.swipeToUp();
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			minePage.click("设置");
			loginOut();
		}
	}
	
	//登录操作
	public  void login(String userName,String pwd) throws Exception {	
		loginPage=new BasePage(driver,"loginPage");
		loginPage.type("登录输入账号框", userName);
		loginPage.type("登录输入密码框", pwd);
		loginPage.click("登录");	
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	}
	
	//退出登录
	public void loginOut() throws Exception{
		BasePage settingPage=new BasePage(driver,"settingPage");
		settingPage.click("退出DJI账号");
		settingPage.click("确定");
	}
}
