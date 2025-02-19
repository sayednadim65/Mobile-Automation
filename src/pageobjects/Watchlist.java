package pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.android.AndroidDriver;

public class Watchlist {
	AndroidDriver Driver;

	public Watchlist(AndroidDriver Driver) {
		this.Driver = Driver;
		PageFactory.initElements(Driver, this);
	}

	@FindBy(xpath = "//android.view.View[@content-desc=' + Add']")
	public WebElement AddButton;

	@FindBy(xpath = "//android.view.View[@content-desc='Create']")
	public WebElement createButton;

	@FindBy(xpath = "(//android.widget.ImageView)[2]")
	public WebElement enterwatchlistname;

	@FindBy(xpath = "//android.view.View[@content-desc=\"OK\"]")
	public WebElement okwatchlistcreated;

	@FindBy(xpath = "//android.widget.ImageView[@content-desc=\"Add Scrip\"]")
	public WebElement Addscript;

	@FindBy(xpath = "(//android.widget.ImageView)[3]")
	public WebElement addscripticon;

	@FindBy(xpath = "//android.view.View[contains(@content-desc,'Stocks')]")
	public WebElement stocksglobalsearchtab;

	@FindBy(xpath = "//android.view.View[contains(@content-desc,'YESBANK')]")
	public WebElement scriptinwatchlist;

	@FindBy(xpath = "//android.view.View[contains(@content-desc,'Successfully removed')]")
	public WebElement scriptdeletedtoastmessage;

	@FindBy(xpath = "(//android.widget.ImageView)[6]")
	public WebElement kebabmenuwatchlist;

	@FindBy(xpath = "//android.widget.ImageView[@content-desc=\"Manage Watchlist\"]")
	public WebElement managewatchlist;

	@FindBy(xpath = "//android.widget.ImageView[contains(@content-desc,'Automation')]")
	public WebElement createdwatchlist;

	@FindBy(xpath = "//android.view.View[@content-desc=\"Save\"]")
	public WebElement savebutton;

	@FindBy(xpath = "//android.widget.ImageView[contains(@content-desc,'Aumation')]/android.view.View[2]")
	public WebElement deleteicon;

	@FindBy(xpath = "//android.widget.ImageView[@content-desc=\" Smallcase\"]")
	public WebElement smallcasebutton;

	@FindBy(xpath = "//android.view.View[contains(@content-desc,'Tap “save” to confirm changes')]")
	public WebElement watchlistdeletetoastmessage;

	@FindBy(xpath = "(//android.view.View)[13]")
	public WebElement watchlistbutton;

	@FindBy(xpath = "//android.view.View[@content-desc=\"do not delete\"]")
	public WebElement donotdelte;

	@FindBy(xpath = "//android.view.View[@content-desc=\"Option List\"]")
	public WebElement optionlist;

	@FindBy(xpath = "//android.view.View[contains(@content-desc,'MTF')]")
	public WebElement mtftext;

	@FindBy(xpath = "//android.widget.ImageView[@content-desc=\"Show MTF Info\"]/android.view.View")
	public WebElement showmtfinfo;

	@FindBy(xpath = "//android.widget.ImageView[@class='android.widget.ImageView' and @package='com.mosl.mobile' and @enabled='true' and @clickable='true' and @index='3']")
	public WebElement walleticon;
	
	@FindBy (xpath = "//android.view.View[@content-desc=\"WatchList 6\"]")
	public WebElement watchlist;
}
