package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class WebLogin {
	WebDriver driver;

	public WebLogin(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	@FindBy(id = "btnNewVersion")
	public WebElement newversion;
	
	@FindBy(id = "RiseLoginId")
	public WebElement userid;
	
	@FindBy(xpath = "//*[@id=\"btnRiseTwoButtons\"]/button[1]")
	public WebElement loginwithpwd;

	@FindBy(id = "MainPasswordRise")
	public WebElement password;

	@FindBy(id = "btnLoginRise")
	public WebElement loginbutton;

	@FindBy(id = "Login2FAPanDOB")
	public WebElement dob;

	@FindBy(xpath = "//*[@id=\"Understand_popup\"]/div/div/button")
	public WebElement rdd;

	@FindBy(id = "btnVerify2FAPanDob")
	public WebElement FAlogin;
	
	@FindBy(id = "okButton")
	public WebElement invalidokbutton;
	
	@FindBy(id = "alertMessage")
	public WebElement invalidmsg;
	
	@FindBy(xpath = "//p[text()='Invalid Credentials']")
	public WebElement invalidcredstext;

}
