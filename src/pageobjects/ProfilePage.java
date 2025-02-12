package pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.android.AndroidDriver;

public class ProfilePage {
	AndroidDriver Driver;

	public ProfilePage(AndroidDriver Driver) {
		this.Driver = Driver;
		PageFactory.initElements(Driver, this);
	}

	@FindBy(xpath = "//android.view.View[contains(@content-desc,'Client ID: Y05120')]")
	public WebElement profiledetails;

	@FindBy(xpath = "//android.view.View[@content-desc=\"Trading Account Details\"]")
	public WebElement tradingaccountdetails;
	
	@FindBy (xpath = "//android.view.View[@content-desc=\"ACTIVATE\"]")
	public WebElement asbaswitch;
	
	@FindBy (xpath = "//android.widget.ImageView[@content-desc=\"Reports\"]")
	public WebElement reportsbutton;
	
	@FindBy (xpath = "//android.view.View[@content-desc=\"Ledger Report\"]")
	public WebElement ledgerreports;
	
	@FindBy (xpath = "//android.widget.Button[@content-desc=\"Back\"]")
	public WebElement openwith;

}
