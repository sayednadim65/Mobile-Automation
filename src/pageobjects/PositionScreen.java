package pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.android.AndroidDriver;

public class PositionScreen {
	AndroidDriver Driver;
	
	public PositionScreen (AndroidDriver Driver) {
		this.Driver = Driver;
		PageFactory.initElements(Driver, this);
	}
	
	@FindBy(xpath = "//android.widget.ImageView[@content-desc=\"Exit all\"]")
	public WebElement ExitAllButton;
	
	@FindBy(xpath = "//android.view.View[contains(@content-desc,'Day Position')]")
	public WebElement DayPosition;
	
	@FindBy(xpath = "//android.view.View[contains(@content-desc,'Positions')]")
	public WebElement positions;
	
	@FindBy(xpath = "//android.view.View[contains(@content-desc,'KHOOBSURAT')]")
	public WebElement khoobsuratscript;
	
	@FindBy(xpath = "//android.view.View[@content-desc=\"Exit\"]")
	public WebElement exitbutton;
}
