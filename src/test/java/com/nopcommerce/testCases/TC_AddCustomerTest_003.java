package com.nopcommerce.testCases;
import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.nopcommerce.pageObjects.*;
//import com.nopcommerce.pageObjects.LoginPage;
import com.nopcommerce.testBase.BaseClass;


public class TC_AddCustomerTest_003 extends BaseClass {
	
	@Test
	public void addNewCustomer() throws InterruptedException, IOException
	{
		logger.info("********* starting TC_AddCustomerTest_003 *************");
		
		driver.get(configPropObj.getProperty("baseURL"));
		LoginPage lp=new LoginPage(driver);
		lp.setUsername(configPropObj.getProperty("useremail"));
		lp.setPassword(configPropObj.getProperty("password"));
		lp.clickLogin();
		Thread.sleep(3000);
		
		logger.info("*********Adding new customer *************");
		
		AddcustomerPage addcust=new AddcustomerPage(driver);
		
		addcust.clickOnCustomersMenu();
		addcust.clickOnCustomersMenuItem();
		addcust.clickOnAddnew();
		Thread.sleep(2000);

		logger.info("***************  Providing customer details  *********** ");

		String email=randomestring()+"@gmail.com";
		
		addcust.setEmail(email);
		addcust.setPassword("test123");
		addcust.setFirstName("Jaydip");
		addcust.setLastName("Kumar");
		addcust.setGender("Male");
		addcust.setDob("6/01/1994"); // Format: MM/DD/YYY
		addcust.setCompanyName("busyQA");
		addcust.setCustomerRoles("Vendors");
		Thread.sleep(3000);
		addcust.setManagerOfVendor("Vendor 2");
		addcust.setAdminContent("This is for testing.........");
		addcust.clickOnSave();
		Thread.sleep(3000);

		// validation
				if (addcust.verifyConfirmationMsg()) { // boolean method created in page object model
					logger.info("***************  Customer added succesfully *********** ");
					Assert.assertTrue(true);

				} else {
					logger.error("*************** Customer Not added succesfully *********** ");
					captureScreen(driver,"addNewCustomer");
					Assert.assertTrue(false);
				}
				logger.info("***************   TC_AddCustomerTest_003 Finished  *********** ");
	}
	

}
