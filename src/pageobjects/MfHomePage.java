package pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.android.AndroidDriver;

public class MfHomePage {
	AndroidDriver Driver;

	public MfHomePage (AndroidDriver Driver) {
		this.Driver = Driver;
		PageFactory.initElements(Driver, this);
	}
	
	@FindBy(xpath = "//android.view.View[contains(@content-desc,'MF')]")
	public WebElement MfTab;
	
	@FindBy(xpath = "//android.view.View[@content-desc=\"Expand\"]")
	public WebElement MfExpandIcon;
	
	@FindBy(xpath = "//android.view.View[@content-desc=\"Collapse\"]")
	public WebElement mfCollapseIcon;
	
	@FindBy (xpath = "(//android.widget.ImageView[@content-desc=\"View All\"])[2]")
	public WebElement MfHomepageCollectionViewAll;
	
	@FindBy (xpath = "//android.view.View[@content-desc=\"1M\"]")
	public WebElement MfViewAll1mReturns;
	
	@FindBy (xpath = "//android.view.View[@content-desc=\"3M\"]")
	public WebElement MfViewAll3mReturns;
	
	@FindBy (xpath = "//android.view.View[@content-desc=\"1Y\"]")
	public WebElement MfViewAll1YReturns;
	
	@FindBy (xpath = "//android.view.View[@content-desc=\"3Y\"]")
	public WebElement MfViewAll3YReturns;
	
	@FindBy (xpath = "//android.view.View[@content-desc=\"5Y\"]")
	public WebElement MfViewAll5YReturns;
	
	@FindBy (xpath = "(//android.widget.ImageView[contains(@content-desc,'1 M Returns')])[1]")
	public WebElement onemReturnsMFVerification;
	
	@FindBy (xpath = "(//android.widget.ImageView[contains(@content-desc,'3 M Returns')])[1]")
	public WebElement threeMReturnsMFVerification;
	
	@FindBy (xpath = "(//android.widget.ImageView[contains(@content-desc,'1 Y Returns')])[1]")
	public WebElement oneyearReturnsMFVerification;
	
	@FindBy (xpath = "(//android.widget.ImageView[contains(@content-desc,'3 Y Returns')])[1]")
	public WebElement threeYearsReturnsMFVerification;
	
	@FindBy (xpath = "(//android.widget.ImageView[contains(@content-desc,'5 Y Returns')])[1]")
	public WebElement fiveYearsReturnsMFVerification;

}
