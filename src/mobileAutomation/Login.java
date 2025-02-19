package mobileAutomation;

import org.testng.annotations.Test;

import drivers.DriverFactory;
import drivers.SharedDriver;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import pageobjects.LoginPage;

public class Login {
 
	public AppiumDriver driver;
	public LoginPage loginpage;
	
	
  public Login(SharedDriver driver, LoginPage loginpage) {
	  
	  this.driver = (AppiumDriver) DriverFactory.getDriver();
	  this.loginpage = loginpage;
  }
  
  @Test
  public void verify_user_login() {
	  if(loginpage.loginToRiseText.isDisplayed()) {
		  loginpage.loginToRiseText.click();
		  loginpage.userID.click();
		  loginpage.userID.sendKeys("Moq15960");
		  ((AndroidDriver) DriverFactory.getDriver()).hideKeyboard();;
		 // loginpage.nextUserID.click();
		  
	  }
	  
  }
}
