package com.dji.utils;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;

/**
 * 创建基于Android和iOS的driver
 * 
 * @author Charlie.chen
 *
 */
public class DriverFactory {

	private static AndroidDriver<?> androidDriver = null;
	private static IOSDriver<?> iosDriver = null;
	
	private static LogUtil log = new LogUtil(DriverFactory.class);
	
	@SuppressWarnings("rawtypes")
	public static AndroidDriver<?> createAndroidDriver(String udid, String port, String appPackage,
			String appActivity) {

		DesiredCapabilities caps = new DesiredCapabilities();
		// apk地址，不需要安装的话这行不需要
		// File app=new File("C:\\Users\\charlie.chen\\djigo.apk");
		// 不需要安装的话就去掉这个
		// caps.setCapability("app", app.getAbsolutePath());

		caps.setCapability("platformName", "Android");
		caps.setCapability("platformVersion", "6.0");
		caps.setCapability("deviceName", "P9");
		caps.setCapability("udid", udid);
		caps.setCapability("appPackage", appPackage);
		caps.setCapability("appActivity", appActivity);
		// caps.setCapability(MobileCapabilityType.BROWSER_NAME, "Chrome");
		caps.setCapability("unicodeKeyboard", "True"); // 支持中文输入
		caps.setCapability("resetKeyboard", "True"); // 重置输入法为系统默认

		// 安装时不对apk进行重签名，设置很有必要，否则有的apk在重签名后无法正常使用
		// caps.setCapability("noSign", "True");

		try {
			androidDriver = new AndroidDriver(new URL("http://127.0.0.1:" + port + "/wd/hub"), caps);
		} catch (Exception e) {
			log.error("Android.appium连接失败");
		}
		return androidDriver;
	}

	@SuppressWarnings("rawtypes")
	public static IOSDriver<?> createIOSDriver(String udid, String port) {

		DesiredCapabilities caps = new DesiredCapabilities();
		caps.setCapability("platformName", "iOS");
		caps.setCapability("platformVersion", "9.3");
		caps.setCapability("deviceName", "iPhone 6s");

		caps.setCapability("unicodeKeyboard", "True");
		caps.setCapability("resetKeyboard", "True");

		try {
			iosDriver = new IOSDriver(new URL("http://127.0.0.1:" + port + "/wd/hub"), caps);
		} catch (MalformedURLException e) {
			log.error("iOS.appium连接失败");
		}

		return iosDriver;
	}

}
