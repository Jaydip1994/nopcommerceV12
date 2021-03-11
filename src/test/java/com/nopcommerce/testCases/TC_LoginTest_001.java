package com.nopcommerce.testCases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.nopcommerce.pageObjects.LoginPage;
import com.nopcommerce.testBase.BaseClass;

public class TC_LoginTest_001 extends BaseClass {
	
	@Test
	public void loginTest() throws IOException
	{
		logger.info("*** Starting TC_LoginTest_001 *** ");
		
		driver.get(configPropObj.getProperty("baseURL")); // Here data is not hard coded so we can use it from config.properties
		driver.manage().window().maximize();
		LoginPage lp = new LoginPage(driver); // LoginPage comes from different package so we have to import that package
		
		
		logger.info("*** Providing logging details *** ");
		
		lp.setUsername(configPropObj.getProperty("useremail")); // data is not hard coded
		lp.setPassword(configPropObj.getProperty("password"));
		lp.clickLogin();
		
		// validation
		
		logger.info("*** Validation login *** ");
		
		String exp_title = "Dashboard / nopCommerce administration";
		String act_title = driver.getTitle();
		
		if(exp_title.equals(act_title))
		{
			logger.info("*** logging is successful *** ");
			Assert.assertTrue(true);
		}
		else
		{
			logger.error("*** logging is un-successful *** ");
			captureScreen(driver,"loginTest");
			Assert.assertTrue(false);
		}
	}
	
	

}




