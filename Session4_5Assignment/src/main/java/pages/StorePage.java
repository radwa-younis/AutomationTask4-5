package pages;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class StorePage {

	WebDriver driver;
public	static Double ExpectedTotalPrice = 0.0;

	By SearchInput = By.id("search_query_top");
	By SearchButton = By.className("btn btn-default button-search");
	By ItemPrice = By.xpath("//div[@class='right-block']/div[@class='content_price']/span[1]");
	By CountinueShopingButton = By.xpath("//div[@class='button-container']/span");
	By NumItemsInCard = By.xpath("//a/span[@class='ajax_cart_quantity unvisible']");

	
	
	public StorePage(WebDriver driver){
		this.driver = driver;
	}

	public void Search() {
		driver.findElement(SearchInput).sendKeys("dress"); 
		driver.findElement(SearchInput).sendKeys(Keys.RETURN);	
		driver.manage().timeouts().implicitlyWait(50,TimeUnit.SECONDS) ;  

	}
	public void Add2Dresses(int ItemsNum) throws InterruptedException {

		List<WebElement> DressesPrice = driver.findElements(ItemPrice);
		WebElement AddCardButton = null;
		for (int i = 0; i < ItemsNum; i++) {
			WebElement dressPrice = DressesPrice.get(i);
			ExpectedTotalPrice = ExpectedTotalPrice + (Double.parseDouble(dressPrice.getText().replaceAll("[$]", "")));
			Actions action = new Actions(driver);
			action.moveToElement(dressPrice).perform();	

			AddCardButton=	dressPrice.findElement(By.xpath("../../div[@class='button-container']/a[1]"));
			AddCardButton.click();
			
			Thread.sleep(2000);
			driver.findElement(CountinueShopingButton).click();

		}
		ExpectedTotalPrice = ExpectedTotalPrice +2;
		System.out.println("Items Added to Card and Total Price is "+ExpectedTotalPrice);

	}
	public int GetNumItemsIncard() {
		return  Integer.parseInt(driver.findElement(NumItemsInCard).getText());
	}
}
