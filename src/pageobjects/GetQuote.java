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
	
	@FindBy(xpath = "//android.widget.Button[@content-desc=\"Buy\"]")
	public WebElement BuyButton;
	
	@FindBy (xpath = "//android.widget.Button[@content-desc=\"Sell\"]")
	public WebElement SellButton;
	
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
	
	@FindBy (xpath = "//android.view.View[@content-desc=\"BSE\"]")
	public WebElement bsebutton;
	
	@FindBy (xpath = "//android.widget.ImageView[@content-desc=\"Chart\"]")
	public WebElement chartsbutton;
	
	@FindBy (xpath = "//android.widget.Button[@content-desc=\"BUY\"]")
	public WebElement chartsbuybutton;

	@FindBy (xpath = "//android.widget.ImageView[@content-desc=\"Option Chain\"]")
	public WebElement optionchainbutton;
	
	@FindBy (xpath = "//android.view.View[@content-desc=\"OI\"]")
	public WebElement OIoptionchain;
	
	@FindBy (xpath = "//android.view.View[contains(@content-desc,'Fundamental')]")
	public WebElement fundamentaltab;
	
	@FindBy (xpath = "//android.view.View[@content-desc=\"Fundamental Ratios\"]")
	public WebElement fundamentalratios;
	
	@FindBy (xpath = "//android.view.View[contains(@content-desc,'Technical')]")
	public WebElement technicaltab;
	
	@FindBy (xpath = "//android.view.View[@content-desc=\"Delivery & Volume\"]")
	public WebElement deliveryvolume;
	
	@FindBy (xpath = "//android.view.View[contains(@content-desc,'News')]")
	public WebElement Newstab;
	
	@FindBy (xpath = "//android.view.View[@content-desc=\"News\"]")
	public WebElement news;
	
	@FindBy (xpath = "//android.view.View[contains(@content-desc,'Transactions')]")
	public WebElement transactionstab;
	
	@FindBy (xpath = "//android.view.View[contains(@content-desc,'Realised P&L')]")
	public WebElement realisedPL;
	
	@FindBy (xpath = "//android.view.View[@content-desc=\"Depth\"]")
	public WebElement depth;
	
	@FindBy(xpath = "//android.view.View[contains(@content-desc,'Overview')]")
	public WebElement overviewbutton;
	
	@FindBy (xpath = "//android.view.View[@content-desc=\"News\"]")
	public WebElement newsverification;
	
	@FindBy (xpath = "//android.widget.ImageView[@content-desc=\"Option Chain\"]")
	public WebElement optionchain;
	
	@FindBy (xpath = "//android.view.View[@content-desc=\"OI\"]")
	public WebElement optionchainverification;
}
