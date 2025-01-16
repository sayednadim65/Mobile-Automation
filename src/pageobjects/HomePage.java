package pageobjects;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.android.AndroidDriver;

public class HomePage {
	AndroidDriver Driver;

	public HomePage(AndroidDriver Driver) {
		this.Driver = Driver;
		PageFactory.initElements(Driver, this);
	}

	@FindBy(xpath = "//android.widget.ImageView[@content-desc=\"Search\"]")
	public WebElement Globalsearchbeforetap;

	@FindBy(xpath = "//android.widget.ImageView")
	public List<WebElement> Globalsearchaftertap;

	@FindBy(xpath = "//android.view.View[contains(@content-desc,'YESBANK')]")
	public WebElement Globalsearchresult;

	@FindBy(xpath = "//android.widget.ImageView[@content-desc=\"Expand\"]")
	public WebElement ExpandIcon;

	@FindBy(xpath = "//android.widget.ImageView[@content-desc=\"Collapse\"]")
	public WebElement CollapseIcon;

	@FindBy(xpath = "//android.widget.ImageView[@content-desc=\"Trades\"]")
	public WebElement Tradesbottombar;

	@FindBy(xpath = "//android.widget.ImageView[@content-desc=\"Watchlist\"]")
	public WebElement WatchlistBottombar;

	@FindBy(xpath = "//android.widget.ImageView[@content-desc=\"Portfolio\"]")
	public WebElement portfolioBottombar;

	@FindBy(xpath = "//android.view.View[contains(@content-desc,'Home')]")
	public WebElement homeTabHeader;

	@FindBy(xpath = "//android.view.View[contains(@content-desc,'MF')]")
	public WebElement MfTab;

	@FindBy(xpath = "//android.widget.ImageView[contains(@content-desc,'Intra')]")
	public WebElement IntaoptionsButton;

	@FindBy(xpath = "//android.widget.ImageView[@content-desc=\"Explore\"]")
	public WebElement explorebottombar;

	@FindBy(xpath = "//android.widget.ImageView[@content-desc=\"Hide\"]")
	public WebElement hidebutton;

	@FindBy(xpath = "//android.view.View[contains(@content-desc,'*****')]")
	public WebElement hideportfolio;

	@FindBy(xpath = "//android.widget.ImageView[@content-desc=\"Show\"]")
	public WebElement showbutton;

	@FindBy(xpath = "//android.widget.ImageView[@content-desc=\"Expand\"]")
	public WebElement homepageportfolioexpand;

	@FindBy(xpath = "//android.widget.ImageView[contains(@content-desc,'Available Margin')]")
	public WebElement availablemargin;

	@FindBy(xpath = "//android.widget.ImageView[@content-desc=\"Collapse\"]")
	public WebElement homepageportfoliocollapse;

	@FindBy(xpath = "(//android.widget.ImageView)[11]")
	public WebElement researchideaibutton;

	@FindBy(xpath = "//android.view.View[@content-desc=\"Research Ideas\"]")
	public WebElement reasearchideatext;

	@FindBy(xpath = "//android.widget.ImageView[@content-desc=\"View All\"]")
	public WebElement researchideaviewall;

	@FindBy(xpath = "//android.view.View[@content-desc=\"Equity\"]")
	public WebElement researchideaequitytab;

	@FindBy(xpath = "//android.widget.ImageView[contains(@content-desc,'Stock')]")
	public WebElement stockbasket;

	@FindBy(xpath = "//android.widget.ImageView[@content-desc=\" IPO\"]")
	public WebElement IPO;

	@FindBy(xpath = "//android.view.View[contains(@content-desc,'IPO List')]")
	public WebElement ipolist;

	@FindBy(xpath = "//android.widget.ImageView[contains(@content-desc,'Research')]")
	public WebElement researchideasbutton;

	@FindBy(xpath = "//android.widget.ImageView[@content-desc=\" IAP\"]")
	public WebElement IAPbutton;

	@FindBy(className = "android.webkit.WebView")
	public WebElement iappage;

	@FindBy(xpath = "//android.widget.ImageView[@content-desc=\" Bonds\"]")
	public WebElement bondsCTA;

	@FindBy(xpath = "(//android.widget.TextView)[3]")
	public WebElement bondspage;

	@FindBy(xpath = "//android.widget.ImageView[contains(@content-desc,'Options')]")
	public WebElement optionsstoreCTA;

	@FindBy(xpath = "//android.view.View[@content-desc=\"Other Free Option Strategies\"]")
	public WebElement optionsstorepage;

	@FindBy(xpath = "//android.widget.ImageView[contains(@content-desc,'Smallcase')]")
	public WebElement smallcasebutton;

	@FindBy(xpath = "//android.widget.ImageView[@content-desc=\" TGS\"]")
	public WebElement TGSbutton;

	@FindBy(xpath = "//android.view.View[@content-desc=\"Positional\"]")
	public WebElement TGSpage;

	@FindBy(xpath = "//android.widget.ImageView[@content-desc=\" Insurance\"]")
	public WebElement insurancebutton;

	@FindBy(className = "android.webkit.WebView")
	public WebElement insurancepage;

	@FindBy(xpath = "//android.widget.ImageView[contains(@content-desc,'Fixed')]")
	public WebElement fixeddepositbutton;

	@FindBy(xpath = "//android.widget.Button[@text='Proceed']")
	public WebElement fixeddepositpage;

	@FindBy(xpath = "//android.view.View[@content-desc=\"TGS\"]")
	public WebElement intraoptionspage;

	@FindBy(xpath = "(//android.view.View)[9]")
	public WebElement smallcasepage;

	@FindBy(xpath = "//android.widget.ImageView[contains(@content-desc,'Teji')]")
	public WebElement tejimandibutton;

	@FindBy(xpath = "//android.widget.TextView")
	public WebElement tejimandipage;

	@FindBy(xpath = "//android.widget.ImageView[contains(@content-desc,'Mutual Funds')]")
	public WebElement mfCTAs;

	@FindBy(xpath = "//android.widget.ImageView[@content-desc=\"View All\"]")
	public WebElement ipoviewallbutton;

	@FindBy(xpath = "//android.widget.ImageView[contains(@content-desc,'High')]")
	public WebElement mfhighreturnsbutton;

	@FindBy(xpath = "//android.widget.ImageView[contains(@content-desc,'SIP')]")
	public WebElement sipwith500;

	@FindBy(xpath = "//android.widget.ImageView[contains(@content-desc,'Tax')]")
	public WebElement taxsaverbutton;

	@FindBy(xpath = "//android.widget.ImageView[contains(@content-desc,'MO')]")
	public WebElement Morecommendedbutton;

	@FindBy(xpath = "//android.widget.ImageView[contains(@content-desc,'Highest')]")
	public WebElement highestratedfund;

	@FindBy(xpath = "//android.widget.ImageView[contains(@content-desc,'International')]")
	public WebElement internationfund;

	@FindBy(xpath = "//android.view.View[@content-desc=\"One Click SIP\"]")
	public WebElement oneclicksip;

	@FindBy(xpath = "(//android.widget.ImageView)[5]")
	public WebElement ipoibutton;

	@FindBy(xpath = "//android.view.View[@content-desc=\"IPO & NFO\"]")
	public WebElement ipochildscreen;

	@FindBy(xpath = "//android.view.View[contains(@content-desc,'IPO List')]")
	public WebElement ipopage;

	@FindBy(xpath = "//android.view.View[@content-desc=\"IPO\"]")
	public WebElement ipobutton;

	@FindBy(xpath = "//android.view.View[contains(@content-desc,'Ends on')]")
	public WebElement ipolistq;

	@FindBy(xpath = "//android.view.View[@content-desc=\"NFO\"]")
	public WebElement nfobutton;

	@FindBy(xpath = "//android.view.View[contains(@content-desc,'Launch Date')]")
	public WebElement nfolist;

	@FindBy(xpath = "//android.widget.ImageView[@content-desc=\" MO API\"]")
	public WebElement MOapibutton;

	@FindBy(xpath = "//android.view.View[@content-desc=\"Stocks\"]")
	public WebElement stocksipbutton;

	@FindBy(xpath = "//android.view.View[@content-desc=\"Symbol\"]")
	public WebElement stocksippage;

	@FindBy(xpath = "//android.view.View[@content-desc=\"Mutual Fund\"]")
	public WebElement MFsipbutton;

	@FindBy(xpath = "//android.widget.ImageView[@content-desc=\" Reports\"]")
	public WebElement reportsbutton;

	@FindBy(xpath = "//android.webkit.WebView")
	public WebElement reportspage;

	@FindBy(xpath = "//android.widget.ImageView[contains(@content-desc,'P&L')]")
	public WebElement PNLbutton;

	@FindBy(xpath = "(//android.view.View[contains(@content-desc,'Holding Period')])[2]")
	public WebElement pnlpage;

	@FindBy(xpath = "//android.widget.ImageView[@content-desc=\" Alerts\"]")
	public WebElement alertsbutton;

	@FindBy(xpath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View[2]/android.webkit.WebView/android.webkit.WebView/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View[4]/android.view.View/android.view.View/android.widget.Button[2]")
	public WebElement alertspage;
	
	@FindBy (xpath = "//android.widget.ImageView[contains(@content-desc,'Margin')]")
	public WebElement margincalbutton;
	
	@FindBy (xpath = "//android.view.View[@text='Equity']")
	public WebElement margincalpage;
	
	@FindBy (xpath = "//android.webkit.WebView[@text='Motilal Oswal']")
	public WebElement moapipage;
}
