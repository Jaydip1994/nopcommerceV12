package com.nopcommerce.pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class SearchCustomerPage {
	
	WebDriver driver;
	
	// Constructor
	
	public SearchCustomerPage (WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	// WebElements
	
	@FindBy(how=How.ID, using = "SearchEmail")
	@CacheLookup
	WebElement txtEmail;
	
	@FindBy(how = How.ID, using = "SearchFirstName")
	@CacheLookup
	WebElement txtFirstName;
	
	@FindBy(how=How.ID, using = "SearchLastName")
	@CacheLookup
	WebElement txtLastName;
	
	@FindBy(how=How.ID, using = "search-customers")
	@CacheLookup
	WebElement btnSearch;
	
	@FindBy(how = How.XPATH, using = "//*[@id=\"customers-grid_wrapper\"]/div[1]/div/div")
	@CacheLookup
	WebElement tblSearchReasult;
	
	@FindBy(how = How.XPATH, using = "//*[@id=\"customers-grid_wrapper\"]/div[1]/div/div")
	@CacheLookup
	WebElement tbl;
	
	@FindBy(how=How.XPATH, using = "//*[@id=\"customers-grid\"]//tbody/tr")
	@CacheLookup
	List<WebElement> tblRaws;
	
	@FindBy(how=How.XPATH, using = "//*[@id=\"customers-grid\"]//tbody/tr/td")
	@CacheLookup
	List<WebElement> tblColumns;
	
	// Action method
	
	public void setEmail(String email)
	{
		txtEmail.clear();
		txtEmail.sendKeys(email);
	}
	
	public void setFirstName(String fname)
	{
		txtFirstName.clear();
		txtFirstName.sendKeys(fname);
	}
	
	public void setLastName(String lname)
	{
		txtLastName.clear();
		txtLastName.sendKeys(lname);
	}
	
	public void clickOnSearch()
	{
		btnSearch.click();
	}
	
	public int getNoOfRaws()
	{
		return(tblRaws.size());
	}
	
	public int getNoOfColumns()
	{
		return(tblColumns.size());
	}
	
	public boolean searchCustomerByEmail (String email)
	{
		boolean flag = false;
		
		for(int r=1;r<=getNoOfRaws();r++)
		{
			String emailId = tbl.findElement(By.xpath("//*[@id=\\\"customers-grid\\\"]/tbody/tr["+r+"]/td[2]")).getText();
			
			if(emailId.equals(email))
			{
				flag=true;
				break;
			}
		}
		return flag;
	}
	
	public boolean searchCustomerByName (String name)
	{
		boolean flag = false;
		
		for(int r=1;r<=getNoOfRaws();r++)
		{
			String emailId = tbl.findElement(By.xpath("//*[@id=\\\"customers-grid\\\"]/tbody/tr["+r+"]/td[3]")).getText();
			
			if(emailId.equals(name))
			{
				flag=true;
				break;
			}
		}
		return flag;
	}
	
	

}
