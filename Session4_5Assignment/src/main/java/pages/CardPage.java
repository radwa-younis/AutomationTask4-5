package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CardPage {
	WebDriver driver;
	By CardButton = By.xpath("//div[@class='shopping_cart']/a");
	By TotalPrice = By.id("total_price");

	public CardPage(WebDriver driver){
		this.driver = driver;
	}
	public void GoCardPage() {
		driver.findElement(CardButton).click();

	}
	public double ActualTotalPrice() {
		return  (Double.parseDouble(driver.findElement(TotalPrice).getText().replaceAll("[$]", "")));
	}
	

}
