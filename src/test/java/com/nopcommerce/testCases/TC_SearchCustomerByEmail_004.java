package com.nopcommerce.testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.nopcommerce.pageObjects.AddcustomerPage;
import com.nopcommerce.pageObjects.LoginPage;
import com.nopcommerce.pageObjects.SearchCustomerPage;
import com.nopcommerce.testBase.BaseClass;

public class TC_SearchCustomerByEmail_004 extends BaseClass {
	
	@Test
	public void searchCustomerByEmail() throws InterruptedException
	{
        logger.info("********* starting TC_SearchCustomerByEmail_004 *************");
		
		driver.get(configPropObj.getProperty("baseURL"));
		LoginPage lp=new LoginPage(driver);
		lp.setUsername(configPropObj.getProperty("useremail"));
		lp.setPassword(configPropObj.getProperty("password"));
		lp.clickLogin();
		Thread.sleep(3000);
		
        
		
		AddcustomerPage addcust=new AddcustomerPage(driver);
		
		addcust.clickOnCustomersMenu();
		addcust.clickOnCustomersMenuItem();
		
		SearchCustomerPage searchcus = new SearchCustomerPage(driver);
		searchcus.setEmail("RNvjv@gmail.com");
		searchcus.clickOnSearch();
		Thread.sleep(3000);
		
		boolean status = searchcus.searchCustomerByEmail("RNvjv@gmail.com");
		if(status == true)
		{
			Assert.assertTrue(true);
		}
		else
		{
			Assert.assertTrue(false);
		}
	}


}
