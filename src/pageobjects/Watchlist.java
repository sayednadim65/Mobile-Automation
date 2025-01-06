package pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidBy;

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
	
	@FindBy (xpath = "(//android.widget.ImageView)[2]")
	public WebElement enterwatchlistname;
	
	@FindBy (xpath = "//android.view.View[@content-desc=\"OK\"]")
	public WebElement okwatchlistcreated;
	
	@FindBy (xpath = "//android.widget.ImageView[@content-desc=\"Add Scrip\"]")
	public WebElement Addscript;
	
	@FindBy (xpath = "(//android.widget.ImageView)[4]")
	public WebElement addscripticon;
	
	@FindBy (xpath = "//android.view.View[contains(@content-desc,'YESBANK')]")
	public WebElement scriptinwatchlist;
	
	@FindBy (xpath = "//android.view.View[contains(@content-desc,'Successfully removed')]")
	public WebElement scriptdeletedtoastmessage;
	
	@FindBy (xpath = "(//android.widget.ImageView)[6]")
	public WebElement kebabmenuwatchlist;
	
	@FindBy (xpath = "//android.widget.ImageView[@content-desc=\"Manage Watchlist\"]")
	public WebElement managewatchlist;
	
	@FindBy (xpath = "//android.widget.ImageView[contains(@content-desc,'Created by Automatio')]")
	public WebElement createdwatchlist;
	
//	@FindBy (xpath = "//android.widget.ImageView[@content-desc=\"Created by Automatio\r\n"
//			+ "0 Scrips\"]/android.view.View[2]")
//	public WebElement deleteicon;
	
	@FindBy (xpath = "//android.view.View[@content-desc=\"Save\"]")
	public WebElement savebutton;
	
	@AndroidBy (accessibility = "Created by Automatio\r\n"
			+ "0 Scrips")
	public WebElement deleteicon;
}
