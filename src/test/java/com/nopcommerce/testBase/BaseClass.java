package com.nopcommerce.testBase;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {
	
		// below code is for we don't need to hard code data
	public Properties configPropObj; // Properties is pre-defined class in java we have to import it
	
	public Logger logger=LogManager.getLogger(this.getClass()); // Log4j2 --> this is for logger
	
	
	
public WebDriver driver;
	
	@BeforeClass
	@Parameters("browser") // To run the test cases on the specified browser that we can change on the XML file
	public void setup(String br) throws IOException
	{
		
		// Load config.properties file // It will take data frome config file
				configPropObj = new Properties();
				FileInputStream configfile = new FileInputStream(System.getProperty("user.dir") + "\\resources\\config.properties");
				configPropObj.load(configfile);
		// end of loading gconfig.properties file
				
		// Do not need to go through System.setProperty because we added maven webdrivermanager repository in the dependency
		
				if(br.equals("chrome"))
				{
		WebDriverManager.chromedriver().setup(); // It will launch my chrome browser
		driver = new ChromeDriver();
				}
				else if(br.equals("firefox"))
				{
					WebDriverManager.firefoxdriver().setup();
					driver = new FirefoxDriver();
				}
				else if(br.equals("edge"))
				{
					WebDriverManager.edgedriver().setup();
					driver = new EdgeDriver();
//					System.setProperty("webdriver.edge.driver", System.getProperty("user.dir") + "\\drivers\\msedgedriver.exe");
//					driver = new EdgeDriver();
				}
				
				driver.manage().window().maximize();
				//driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
	
	@AfterClass
	public void tearDown()
	{
		driver.quit();
	}
	
	public void captureScreen(WebDriver driver, String tname) throws IOException {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File target = new File(System.getProperty("user.dir") + "\\Screenshots\\" + tname + ".png"); // Here tname is Test name in testCases package
		FileUtils.copyFile(source, target);
		System.out.println("Screenshot taken");

}
	
	public String randomestring() { // user defined method to generate random email address
		String generatedString1 = RandomStringUtils.randomAlphabetic(5);
		return (generatedString1);
	}
	
	public int randomeNum() { // it will generate random number
		String generatedString2 = RandomStringUtils.randomNumeric(4); // that number get stored in the String
		return (Integer.parseInt(generatedString2)); // so converted into the integer
	}
	}
