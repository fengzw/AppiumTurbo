package com.dji.utils;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;

import io.appium.java_client.AppiumDriver;


/**
 * 截图并保存至本地
 * 
 * @author Charlie.chen
 */
public class ScreenShot {

	private AppiumDriver<?> driver;
	// 测试失败截屏保存的路径
	private String path;
	public LogUtil log=new LogUtil(this.getClass());

	public ScreenShot(AppiumDriver<?> driver){
		this.driver=driver;
		path=System.getProperty("user.dir")+ "//snapshot//"+ this.getClass().getSimpleName()+"_"+getCurrentTime() + ".png";
	}
	
	public void getScreenShot() {

		File screen = driver.getScreenshotAs(OutputType.FILE);
		File screenFile = new File(path);
		try {
			FileUtils.copyFile(screen, screenFile);
			log.info("截图保存的路径:" + path);
		} catch (Exception e) {
			log.error("截图失败");
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 获取当前时间
	 */
	public String getCurrentTime(){
		Date date=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
		String currentTime=sdf.format(date);
		return currentTime;	
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
		
}
