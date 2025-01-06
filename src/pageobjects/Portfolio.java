package pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.android.AndroidDriver;

public class Portfolio {
	AndroidDriver Driver;

	public Portfolio(AndroidDriver Driver) {
		this.Driver = Driver;
		PageFactory.initElements(Driver, this);
	}
	
	@FindBy (xpath = "//android.view.View[contains(@content-desc,'All')]")
	public WebElement AllTabPortfolio;
	
	@FindBy (xpath = "//android.view.View[contains(@content-desc,'Stocks')]")
	public WebElement StocksTabPortfolio;

	@FindBy (xpath = "//android.widget.ImageView[@content-desc='View Analysis']")
	public WebElement viewAnalysis;
	
	@FindBy (xpath = "//android.widget.ImageView[@content-desc=\"Returns (Abs.)\"]")
	public WebElement returnsAbs;
	
	@FindBy (xpath = "//android.view.View[@content-desc='Current Investment Value']")
	public WebElement currentVerification;
	
	@FindBy (xpath = "//android.view.View[contains(@content-desc,'Mutual Funds')]")
	public WebElement MFTabPortfolio;
	
	@FindBy (xpath = "//android.view.View[contains(@content-desc,'PMS')]")
	public WebElement PMSTabPortfolio;
	
	@FindBy (xpath = "//android.view.View[contains(@content-desc,'Basket')]")
	public WebElement BasketTabPortfolio;
	
}
