package com.nopcommerce.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class AddcustomerPage {

	public WebDriver driver;
	WebElement listitem;
		
	public AddcustomerPage(WebDriver driver)
	{
		this.driver=driver;
		//driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS); //Implit wait
	}

	By lnkCustomers_menu=By.xpath("/html/body/div[3]/aside/div/div[4]/div/div/nav/ul/li[4]/a");
	By lnkCustomers_menuitem=By.xpath("/html/body/div[3]/aside/div/div[4]/div/div/nav/ul/li[4]/ul/li[1]/a/p");
	
	By btnAddnew=By.xpath("/html/body/div[3]/div[1]/form[1]/div/div/a"); //Add new
		
	By txtEmail=By.xpath("//*[@id=\"Email\"]");
	By txtPassword=By.xpath("//*[@id=\"Password\"]");
	
	By txtFirstName=By.xpath("//*[@id=\"FirstName\"]");
	By txtLastName=By.xpath("//*[@id=\"LastName\"]");
	
	By rdMaleGender=By.xpath("//*[@id=\"Gender_Male\"]");
	By rdFeMaleGender=By.xpath("//*[@id=\"Gender_Female\"]");
	
	By txtDob=By.xpath("//*[@id=\"DateOfBirth\"]");
	
	By txtCompanyName=By.xpath("//*[@id=\"Company\"]");
	
	By txtcustomerRoles=By.xpath("//div[@class='k-multiselect-wrap k-floatwrap']");
	
	By lstitemAdministrators=By.xpath("//*[@id=\"f21ad041-16b2-4880-8e64-922723f8bf45\"]");
	By lstitemRegistered=By.xpath("//*[@id=\"SelectedCustomerRoleIds_listbox\"]/li[4]");
	By lstitemGuests=By.xpath("//*[@id=\"SelectedCustomerRoleIds_listbox\"]/li[3]");
	By lstitemVendors=By.xpath("//*[@id=\"SelectedCustomerRoleIds_listbox\"]/li[5]");
	
	
	By drpmgrOfVendor=By.xpath("//*[@id=\"VendorId\"]");
	
	
	By txtAdminContent=By.xpath("//*[@id=\"AdminComment\"]");
	
	By btnSave=By.xpath("/html/body/div[3]/div[1]/form/div[1]/div/button[1]");
	
	By txtmsg=By.xpath("/html/body/div[3]/div[1]/div[1]");
			
	//Action methods
		public void clickOnCustomersMenu() {
			driver.findElement(lnkCustomers_menu).click();
		}

		public void clickOnCustomersMenuItem() {
			driver.findElement(lnkCustomers_menuitem).click();
		}
		
		public void clickOnAddnew() {
			driver.findElement(btnAddnew).click();
		}
		
		public void setEmail(String email)
		{
			driver.findElement(txtEmail).sendKeys(email);
		}
		
		public void setPassword(String password)
		{
			driver.findElement(txtPassword).sendKeys(password);
		}
		
		public void setFirstName(String fname)
		{
			driver.findElement(txtFirstName).sendKeys(fname);
		}
		
		public void setLastName(String lname)
		{
			driver.findElement(txtLastName).sendKeys(lname);
		}
		
		public void setGender(String gender)
		{
			if(gender.equals("Male"))
			{
				driver.findElement(rdMaleGender).click();
			}
			else if(gender.equals("Female"))
			{
				driver.findElement(rdFeMaleGender).click();
			}
			else
			{
				driver.findElement(rdMaleGender).click();//Default
			}
			
		}
		
		public void setDob(String dob)
		{
			driver.findElement(txtDob).sendKeys(dob);
		}
		
		public void setCompanyName(String comname)
		{
			driver.findElement(txtCompanyName).sendKeys(comname);
		}
		
		public void setCustomerRoles(String role) throws InterruptedException 
			{
			
				driver.findElement(txtcustomerRoles).click();
			
				Thread.sleep(3000);
				
				if(role.equals("Registered"))
				{
					listitem=driver.findElement(lstitemRegistered); 
				}
				else if(role.equals("Administrators"))
				{
					listitem=driver.findElement(lstitemAdministrators); 
				}
				else if(role.equals("Guests"))
				{
					// Here user can be Registered (or) Guest, only one
					driver.findElement(By.xpath("//*[@id=\"SelectedCustomerRoleIds_taglist\"]/li/span[2]")).click(); //delete registered
					listitem=driver.findElement(lstitemGuests);
				}
				else if(role.equals("Vendors"))
				{
					listitem=driver.findElement(lstitemVendors);
				}
				else
				{
					listitem=driver.findElement(lstitemGuests);
				}
						
				//listitem.click();  //Not working
				
				JavascriptExecutor js = (JavascriptExecutor)driver;
				js.executeScript("arguments[0].click();", listitem);
		}

		public void setManagerOfVendor(String value)
		{
			Select drp=new Select(driver.findElement(drpmgrOfVendor));
			drp.selectByVisibleText(value);
		}
		
	
		public void setAdminContent(String content)
		{
			driver.findElement(txtAdminContent).sendKeys(content);
		}
		
		public void clickOnSave()
		{
			driver.findElement(btnSave).click();
		}
		
		public boolean verifyConfirmationMsg()
		{
			String msg=driver.findElement(txtmsg).getText();
			if (msg.contains("The new customer has been added successfully"))
			{
				return true;
			}
			else
			{
				return false;
			}
	
		}
		
}