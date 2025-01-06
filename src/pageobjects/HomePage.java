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
}
