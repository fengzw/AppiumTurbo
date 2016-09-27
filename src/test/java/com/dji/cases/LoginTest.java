package com.dji.cases;

import java.util.concurrent.TimeUnit;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import static org.assertj.core.api.Java6Assertions.*;

import com.dji.action.Login;
import com.dji.object.BasePage;
import com.dji.utils.DriverFactory;
import com.dji.utils.TestNGListener;

import io.appium.java_client.android.AndroidDriver;

//@Listeners({ TestNGListener.class })
public class LoginTest {

	private AndroidDriver<?> ad = null;
	private Login login = null;

	@Parameters({ "userName", "pwd" })
	@Test(description = "通过登录按钮登录")
	public void loginByButton(String userName, String pwd) throws Exception {

		login.loginByButton(userName, pwd);
		ad.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		// 断言验证用户名是否正确
		//Assert.assertEquals(new BasePage(ad, "minePage").findElement("用户名").getText(), "charlie.chen");   //testNG自带的断言机制
		//采用AssertJ断言机制
		assertThat(new BasePage(ad, "minePage").findElement("用户名").getText()).isEqualTo("CharlieChen");
	}

	@Parameters({ "userName", "pwd" })
	@Test(description = "通过DJI Store登录")
	public void loginByStore(String userName, String pwd) throws Exception {

		login.loginByStore(userName, pwd);
		ad.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Parameters({ "userName", "pwd" })
	@Test(description = "通过DJI论坛登录")
	public void loginByAcademy(String userName, String pwd) throws Exception {

		login.loginByAcademy(userName, pwd);
		ad.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Parameters({ "userName", "pwd" })
	@Test(description = "通过飞行记录登录")
	public void loginByFly(String userName, String pwd) throws Exception {

	}

	// /*
	// * 通过Skypixel关注登录
	// */
	// @Test
	// public void loginByAttention() throws Exception {
	// }
	//
	// /*
	// * 通过Skypixel点赞登录
	// */
	// @Test
	// public void loginByPraise() throws Exception {
	// }
	//
	// /*
	// * 通过Skypixel收藏登录
	// */
	// @Test
	// public void loginByCollect() throws Exception {
	// }
	//
	// /*
	// * 通过Skypixel评论登录
	// */
	// @Test
	// public void loginByComment() throws Exception {
	// }

	@BeforeMethod
	public void beforeMethod() throws Exception {
		login = new Login(ad);
		TestNGListener.setDriver(ad);
	}

	@AfterMethod
	public void afterMethod() throws Exception {

	}

	@BeforeClass
	public void beforeClass() {

		ad = DriverFactory.createAndroidDriver("PBV0216615001449", "4723", "dji.pilot", "dji.pilot.main.activity.DJIAoaActivity"); 
		ad.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@AfterClass
	public void afterClass() {
		// ad.closeApp();

	}

	@BeforeTest()
	public void beforeTest() throws Exception {
	}

	@AfterTest
	public void afterTest() {
	}

	/*
	 * DJI H5页面
	 * 
	 * @SuppressWarnings("unchecked")
	 * 
	 * @Test public void djiForum() throws Exception {
	 * 
	 * ad.findElement(MinePage.djiForum).click(); Thread.sleep(5000);
	 * 
	 * Set<String> contextSet = ad.getContextHandles(); for (String context :
	 * contextSet) { System.out.println(context); if
	 * (context.toLowerCase().contains("webview")) { ad.context(context); break;
	 * } }
	 * 
	 * // PageResourceGetter.initalize("users/klien/pn.html",appium.driver.
	 * getPageSource());
	 * 
	 * // appium.element(By.id("dji.pilot:id/explore_share_btn")).click(); //
	 * //分享按钮 // Thread.sleep(1000);
	 * 
	 * // H5 // appium.swipeToLeft(); // Thread.sleep(1000); //
	 * appium.element(By.id("dji.pilot:id/share_dialog_item_image")).click(); //
	 * appium.tap_xy(1280, 2138); // 600,1100 // Thread.sleep(2000); }
	 */

	// public static void main(String[] args) throws Exception {
	//
	// AndroidDriver<?>
	// ad=DriverFactory.createAndroidDriver("PBV0216615001449","4723");
	// ad.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	//
	// LoginAction loginAction=new LoginAction(ad);
	// loginAction.loginByButton("charlie.chen@dji.com", "123456");
	// ad.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	//
	// loginAction.loginByStore("charlie.chen@dji.com", "1314lizai");
	//
	// }
}
