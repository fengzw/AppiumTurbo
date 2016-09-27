package com.dji.cases;

import org.testng.annotations.Test;

import com.dji.utils.LogUtil;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;

public class SharingTest {
	public LogUtil log = new LogUtil(this.getClass());

	@Test
	public void sharingBySkypixel() {
		log.info("ShanringTest Pass");
	}

	@Test
	public void sharingByEdit() {
	}

	@BeforeMethod
	public void beforeMethod() {
	}

	@AfterMethod
	public void afterMethod() {
	}

	@BeforeClass
	public void beforeClass() {
	}

	@AfterClass
	public void afterClass() {
	}

	@BeforeTest
	public void beforeTest() {
	}

	@AfterTest
	public void afterTest() {
	}

}
