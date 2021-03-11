package com.nopcommerce.testCases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.nopcommerce.pageObjects.LoginPage;
import com.nopcommerce.testBase.BaseClass;
import com.nopcommerce.utilities.XLUtils;

public class TC_LoginDDT_002 extends BaseClass{
	
	@Test(dataProvider="LoginData")
	public void loginTest(String user,String pwd,String exp) throws InterruptedException
	{
		//logger.info("********* starting TC_LoginDDT_002 *************");
		
		driver.get(configPropObj.getProperty("baseURL"));
		
		LoginPage lp = new LoginPage(driver);
		lp.setUsername(user);
		lp.setPassword(pwd);
		lp.clickLogin();
		Thread.sleep(5000);
		
		String exp_title="Dashboard / nopCommerce administration";
		String act_title=driver.getTitle();
		
		if(exp_title.equals(act_title))
		{
			if(exp.equals("pass"))
			{
				logger.info("**************** loginTest is Passed ************* ");
				lp.clicklogout();
				Thread.sleep(3000);
				Assert.assertTrue(true);
			}
			else if(exp.equals("fail"))
			{
				logger.warn("**************** loginTest is Failed************* ");
				lp.clicklogout();
				Thread.sleep(3000);
				Assert.assertTrue(false);
			}
					
		}
		else if(exp_title.equals(act_title))
		{
			if(exp.equals("pass"))
			{
				logger.warn("**************** loginTest is Failed************* ");
				Assert.assertTrue(false);
			}
			else if(exp.equals("fail"))
			{
				logger.info("**************** loginTest is Passed ************* ");
				Assert.assertTrue(true);
			}

		}
		logger.info("********* Finished  TC_LoginDDT_002 *************");
	}
	
	
	
	
	@DataProvider(name="LoginData")
	public String [][] getData() throws IOException
	{
		String path=System.getProperty("user.dir")+"/TestData/LoginData.xlsx";
		
		int totalrows=XLUtils.getRowCount(path, "Sheet1");	// It is a class in utility package
		int totalcols=XLUtils.getCellCount(path,"Sheet1",1);
				
		String logindata[][]=new String[totalrows][totalcols];
		
		for(int i=1;i<=totalrows;i++)  //5
		{		
			for(int j=0;j<totalcols;j++)
			{
				logindata[i-1][j]= XLUtils.getCellData(path, "Sheet1",i, j);  //1,0 , i-1 because first raw is header part
			}
		}
	return logindata;
				
	}
	

}
