package pageobjects;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.android.AndroidDriver;

public class HomePage {
	AndroidDriver Driver;
	
	public HomePage (AndroidDriver Driver) {
		this.Driver = Driver;
		PageFactory.initElements(Driver , this);
	}

	@FindBy(xpath = "//android.widget.ImageView[@content-desc=\"Search\"]")
	public WebElement Globalsearchbeforetap;
	
	@FindBy(xpath = "//android.widget.ImageView")
	public List <WebElement> Globalsearchaftertap;
	
	@FindBy(xpath = "//android.view.View[contains(@content-desc,'YESBANK')]")
	public WebElement Globalsearchresult;
	
	@FindBy(xpath = "//android.widget.ImageView[@content-desc=\"Expand\"]")
	public WebElement ExpandIcon;
	
	@FindBy(xpath = "//android.widget.ImageView[@content-desc=\"Collapse\"]")
	public WebElement CollapseIcon;
	
	@FindBy (xpath = "//android.widget.ImageView[@content-desc=\"Trades\"]")
	public WebElement Tradesbottombar;
	
	@FindBy (xpath = "//android.widget.ImageView[@content-desc=\"Watchlist\"]")
	public WebElement WatchlistBottombar;
	
	@FindBy (xpath = "//android.widget.ImageView[@content-desc=\"Portfolio\"]")
	public WebElement portfolioBottombar;
	
	@FindBy(xpath = "//android.view.View[contains(@content-desc,'Home')]")
	public WebElement homeTabHeader;
	
	@FindBy (xpath = "//android.view.View[contains(@content-desc,'MF')]")
	public WebElement MfTab;
	
	@FindBy (xpath = "//android.widget.ImageView[contains(@content-desc,'Intra')]")
	public WebElement IntaoptionsButton;

	
	@FindBy (xpath = "//android.widget.ImageView[@content-desc=\"Explore\"]")
	public WebElement explorebottombar;
	
	@FindBy (xpath = "//android.widget.ImageView[@content-desc=\"Hide\"]")
	public WebElement hidebutton;
	
	@FindBy (xpath = "//android.view.View[contains(@content-desc,'*****')]")
	public WebElement hideportfolio;
	
	@FindBy (xpath = "//android.widget.ImageView[@content-desc=\"Show\"]")
	public WebElement showbutton;
	
	@FindBy (xpath = "//android.widget.ImageView[@content-desc=\"Expand\"]")
	public WebElement homepageportfolioexpand;
	
	@FindBy (xpath = "//android.widget.ImageView[contains(@content-desc,'Available Margin')]")
	public WebElement availablemargin;
	
	@FindBy (xpath = "//android.widget.ImageView[@content-desc=\"Collapse\"]")
	public WebElement homepageportfoliocollapse;
	
	@FindBy (xpath = "(//android.widget.ImageView)[11]")
	public WebElement researchideaibutton;
	
	@FindBy (xpath = "//android.view.View[@content-desc=\"Research Ideas\"]")
	public WebElement reasearchideatext;
	
	@FindBy (xpath = "//android.widget.ImageView[@content-desc=\"View All\"]")
	public WebElement researchideaviewall;
	
	@FindBy (xpath = "//android.view.View[@content-desc=\"Equity\"]")
	public WebElement researchideaequitytab;
	
	@FindBy (xpath = "//android.widget.ImageView[contains(@content-desc,'Stock')]")
	public WebElement stockbasket;
	
	@FindBy (xpath = "//android.widget.ImageView[@content-desc=\" IPO\"]")
	public WebElement IPO;
	
	@FindBy (xpath = "//android.view.View[contains(@content-desc,'IPO List')]")
	public WebElement ipolist;
	
	@FindBy (xpath = "//android.widget.ImageView[contains(@content-desc,'Research')]")
	public WebElement researchideasbutton;
	
	@FindBy (xpath = "//android.widget.ImageView[@content-desc=\" IAP\"]")
	public WebElement IAPbutton;
	
	@FindBy (className = "android.webkit.WebView")
	public WebElement iappage;
	
	@FindBy (xpath = "//android.widget.ImageView[@content-desc=\" Bonds\"]")
	public WebElement bondsCTA;
	
	@FindBy (xpath = "(//android.widget.TextView)[3]")
	public WebElement bondspage;
	
	@FindBy (xpath = "//android.widget.ImageView[contains(@content-desc,'Options')]")
	public WebElement optionsstoreCTA;
	
	@FindBy (xpath = "//android.view.View[@content-desc=\"Other Free Option Strategies\"]")
	public WebElement optionsstorepage;
}
