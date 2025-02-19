package pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.android.AndroidDriver;

public class StocksHomePage {
	AndroidDriver Driver;
	
	public StocksHomePage(AndroidDriver Driver) {
		this.Driver = Driver;
		PageFactory.initElements(Driver, this);
	}
	
	@FindBy(xpath = "//android.view.View[contains(@content-desc,'Stocks')]")
	public WebElement stocksHomepageTab;
	
	@FindBy(xpath = "//android.view.View[@content-desc=\"Expand\"]")
	public WebElement stocksPortfolioexpand;
	
	@FindBy(xpath = "//android.view.View[@content-desc=\"Collapse\"]")
	public WebElement stocksHomepageCollapseIcon;

}
