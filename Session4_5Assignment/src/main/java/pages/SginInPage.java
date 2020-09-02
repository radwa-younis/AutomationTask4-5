package pages;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SginInPage {
	WebDriver driver ;
	int rand =0;
	public String password="12345";	

	public static String email=null;
	By SignIn = By.className("login");

	By EmailCreate = By.id("email_create");
	By ButtonCreate = By.id("SubmitCreate");
	
	By LoginEmail = By.id("email");
	By LoginPassword = By.id("passwd");	
	By LoginButton = By.id("SubmitLogin");
	By LogOutButton = By.className("logout");

	
	public SginInPage(WebDriver driver) {
		this.driver = driver;
	}
	
	public void GoLoginPage() {
		driver.findElement(SignIn).click();
		driver.manage().timeouts().implicitlyWait(50,TimeUnit.SECONDS) ;  

	}
	
	public void Logout() {
		driver.findElement(LogOutButton).click();
		driver.manage().timeouts().implicitlyWait(50,TimeUnit.SECONDS) ;  

	}
	
	public void EnterRegEmail() throws InterruptedException {
		Random random = new Random(); 
	//	do {
			rand= random.nextInt(900);
			email = "radwaa.younns"+rand+"@email.com";
			driver.findElement(EmailCreate).clear();
			driver.findElement(EmailCreate).sendKeys(email); 
			driver.findElement(ButtonCreate).click();
			System.out.println(email);
		//	Thread.sleep(5000);

//		} while (isElementPresent(By.id("create_account_error")));
		
		driver.manage().timeouts().implicitlyWait(50,TimeUnit.SECONDS) ;
	//	System.out.println("while end");

	}	
	
	
	public void Login() {
		driver.findElement(LoginEmail).sendKeys(email); 
		driver.findElement(LoginPassword).sendKeys(password); 
		driver.findElement(LoginButton).click();

	}
	
}


