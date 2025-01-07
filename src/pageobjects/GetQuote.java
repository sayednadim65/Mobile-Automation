package pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.android.AndroidDriver;

public class GetQuote {
	AndroidDriver Driver;
	
	public GetQuote (AndroidDriver Driver) {
		this.Driver = Driver;
		PageFactory.initElements(Driver, this);
	}
	
	@FindBy(xpath = "(//android.widget.Button)[3]")
	public WebElement BuyButton;
	
	@FindBy (xpath = "//android.view.View[contains(@content-desc,'Futures')]")
	public WebElement FutTab;
	
	@FindBy (xpath = "//android.view.View[@content-desc=\"NSE_FO\"]")
	public WebElement NSEFO;
	
	@FindBy (xpath = "//android.view.View[contains(@content-desc,'Options')]")
	public WebElement optionsTab;
	
	@FindBy (xpath = "//android.view.View[@content-desc=\"Call\"]")
	public WebElement callbutton;
	
	@FindBy (xpath = "//android.view.View[contains(@content-desc,'Cash')]")
	public WebElement cashtab;
	
	@FindBy (xpath = "//android.view.View[@content-desc=\"NSE\"]")
	public WebElement nsebutton;

}
