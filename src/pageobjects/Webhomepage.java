package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Webhomepage {
	WebDriver driver;

	public Webhomepage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	@FindBy(id = "inputSearch")
	public WebElement Searchfield;

	@FindBy(id = "119150")
	public WebElement Yesbank;
	
	@FindBy(xpath = "//*[@id=\"root\"]/div[1]/div[2]/div[1]/div/div/div[1]/img[2]")
	public WebElement closesearch;
	
	@FindBy(xpath = "//*[@id=\"smallWatchTable\"]/div[1]/div/div[1]/img")
	public WebElement Watchlistdropdown;
	
	@FindBy(xpath = "//*[@id=\"smallWatchTable\"]/div[1]/div/div[2]/ul[2]/li/a")
	public WebElement Createwatchlist;
	
	@FindBy(xpath = "//*[@id=\"NewWatchlist\"]/div/div/div[1]/div/input")
	public WebElement watchlistname;
	
	@FindBy(xpath = "//*[@id=\"NewWatchlist\"]/div/div/div[1]/a")
	public WebElement create;
	
	@FindBy(xpath = "//div[contains(text(),'Watchlist added succesfully')]")
	public WebElement createwlmsg;
	
	@FindBy(xpath = "/html[1]/body[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[3]/div[2]/table[1]/tbody[1]/tr[1]/td[1]/div[1]/div[1]/div[2]/a[1]")
	public WebElement addscrip;
	
	@FindBy(xpath ="//div[contains(text(),'The scrip is successfully removed from watchlist.')]")
	public WebElement deletescripmsg;
	
	@FindBy(xpath = "//div[contains(text(),'Watchlist deleted Successfully!')]")
	public WebElement deletewlmsg;
	
	@FindBy(xpath = "//div[@class='global']//div[1]//li[1]//a[1]//img[1]")
	public WebElement addwlicon;
	
	@FindBy(xpath = "//li[9]")
	public WebElement selectwatchlist;
	
	@FindBy(xpath = "//div[@class='fileter_btn']//a[@class='tooltip']//img")
	public WebElement selectkebabmenu;
	
	@FindBy(xpath = "//a[normalize-space()='Remove Scrips']")
	public WebElement removescrips;
	
	@FindBy(xpath = "//td[@class='small_watchlist_changes_column']//div[@class='WatchListTteams']//div[@class='WatchListTteam dflex alignCenter gap12']//div//img")
	public WebElement deletescrip;
	
	@FindBy(xpath = "//a[normalize-space()='Save']")
	public WebElement savebtn;
	
	@FindBy(xpath = "//*[@id=\"smallWatchTable\"]/div[1]/div/div[2]/ul[1]/li[3]/div[2]/a[2]/img")
	public WebElement deletewatchlist;
	
	@FindBy(xpath = "//a[@class='btn Primary_btn Primary_outline w100 mtop15']")
	public WebElement deletebtn;
	
	@FindBy(xpath = "//span[contains(@class,'f12 black fw600 Head_026000')]")
	public WebElement indice;
	
	@FindBy(xpath = "//div[@class='dflex gap30']//div[1]//div[2]//a[1]//img[1]")
	public WebElement indicechart;
	
	@FindBy (xpath = "//div[contains(@class,'dflex gap30')]//div[1]//div[3]//a[1]//img[1]")
	public WebElement indiceoptionchain;
	
	@FindBy(xpath = "//*[@id=\"root\"]/div[1]/header/div[1]/div[2]/div[3]/a/img")
	public WebElement editindices;
	
	@FindBy(xpath = "//td[contains(@class,'tdind dflex alignitemCsenter justifyRight')]//span[contains(@for,'checkbox-999901')]")
	public WebElement indicecheckbox1;
	
	@FindBy(xpath = "//span[contains(@for,'checkbox-999901')]")
	public WebElement indicecheckbox2;
	
	@FindBy(xpath = "//button[normalize-space()='Apply']")
	public WebElement indiceapplycta;
	
}

