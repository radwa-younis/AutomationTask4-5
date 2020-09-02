package Automation.Session4_5Assignment;


import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import pages.CardPage;
import pages.RegisterPage;
import pages.SginInPage;
import pages.StorePage;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
	
	WebDriver driver = null;

	@BeforeTest
	public void NavigateURL() throws AWTException {

		WebDriverManager.chromedriver().setup();
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--incognito");
		DesiredCapabilities capabilities = DesiredCapabilities.chrome();
		capabilities.setCapability(ChromeOptions.CAPABILITY, options);
		driver = new ChromeDriver(options);
		driver.manage().window().maximize();
		driver.get("http://automationpractice.com/index.php");
		driver.manage().timeouts().implicitlyWait(50,TimeUnit.SECONDS) ;  
	}

	@Test 
	public void CreateAccount() throws InterruptedException
	{	SginInPage SginInP = new SginInPage(driver);
	RegisterPage RegisterP = new RegisterPage(driver);
	SginInP.GoLoginPage();

	SginInP.EnterRegEmail();
	RegisterP.EnterRegPersonalInfo();
	Assert.assertEquals(RegisterP.RegWelcomeMessage(), "Welcome to your account. Here you can manage all of your personal information and orders.","Message not as expected");

	}
	
	@Test(priority=1,dependsOnMethods = {"CreateAccount"})
	public void AddtoCard() throws InterruptedException
	{
		SginInPage SginInP = new SginInPage(driver);
		StorePage StoreP = new StorePage(driver);	
		SginInP.Logout();
		SginInP.GoLoginPage();
		SginInP.Login();
		StoreP.Search();
		StoreP.Add2Dresses(2);
		Assert.assertEquals(StoreP.GetNumItemsIncard(),2,"Number of Items in Card not as expected");

		
	}
	
	@Test (priority=2)
	public void Card() throws InterruptedException
	{
		CardPage CardP = new CardPage(driver);
		StorePage StoreP = new StorePage(driver);

		CardP.GoCardPage();
		Assert.assertEquals(CardP.ActualTotalPrice(), StoreP.ExpectedTotalPrice , "Total Price not as expected");
	}
	
	@AfterTest
	public void Teardown() throws AWTException {
		driver.quit();
		
	}



}
