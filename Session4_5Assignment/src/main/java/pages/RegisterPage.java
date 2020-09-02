package pages;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class RegisterPage {
	WebDriver driver ;
	
	By Gender = By.id("id_gender2");
	By FirstNameInput = By.id("customer_firstname");
	By LastNameInput = By.id("customer_lastname");
	By PasswordInput = By.id("passwd");
	By Day = By.id("days");
	By Month = By.id("months");
	By Year = By.id("years");
	By Adress = By.id("address1");
	By City = By.id("city");
	By State = By.id("id_state");
	By PostalCode = By.id("postcode");
	By Phone = By.id("phone_mobile");
	By SubmitAcc = By.id("submitAccount");

	By WelcomeMessage = By.className("info-account");


	
	public RegisterPage(WebDriver driver) {
		this.driver = driver;
	}

	
	public void EnterRegPersonalInfo() {
		SginInPage SginInP = new SginInPage(driver);
		driver.findElement(Gender).click();
		driver.findElement(FirstNameInput).sendKeys("Radwa");
		driver.findElement(LastNameInput).sendKeys("Younis");

		driver.findElement(PasswordInput).sendKeys(SginInP.password);
		
		Select dropdown1 = new Select(driver.findElement(Day));  
		dropdown1.selectByValue("14");
		Select dropdown2 = new Select(driver.findElement(Month));  
		dropdown2.selectByValue("5");
		Select dropdown3 = new Select(driver.findElement(Year));  
		dropdown3.selectByValue("1993");
		
		driver.findElement(Adress).sendKeys("Cairo");
		driver.findElement(City).sendKeys("Cairo");
		driver.findElement(State).sendKeys("Cairo");
		driver.findElement(PostalCode).sendKeys("12345");
		driver.findElement(Phone).sendKeys("01111111");
		driver.findElement(SubmitAcc).click();
		driver.manage().timeouts().implicitlyWait(50,TimeUnit.SECONDS) ;

	}
	public String RegWelcomeMessage() {
		return  driver.findElement(WelcomeMessage).getText();
	}

	
	
	
	
	public boolean isElementPresent(By by) {
        boolean isPresent = false;
        try {
        	if (driver.findElement(by).isDisplayed()) 
        
            isPresent = true;
        }
        catch (NoSuchElementException e) {
        	isPresent = false;
	}
        return isPresent;
    }	
	
}
