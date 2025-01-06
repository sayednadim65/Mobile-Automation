package a_Nadeem;

import java.io.IOException;

import io.appium.java_client.android.AndroidDriver;
import pageobjects.LoginPage;
import utils.Commons;

public class LandingPageStepdefs {
	AndroidDriver Driver;
	
	public void Verify_user_login_and_RDD() throws InterruptedException, IOException {
		LoginPage loginpage = new LoginPage(Driver);

		loginpage.loginButton.isDisplayed();
		loginpage.loginButton.click();
		Thread.sleep(1000);
		loginpage.userID.click();
		loginpage.userID.sendKeys(Commons.getGlobalPropertiesValue("userId"));
		Driver.hideKeyboard();
		loginpage.nextbutton.click();
		Thread.sleep(1000);
		loginpage.passwordTextField.click();
		loginpage.passwordTextField.sendKeys(Commons.getGlobalPropertiesValue("password"));
		Driver.hideKeyboard();
		loginpage.loginButton.click();
		Thread.sleep(1000);
		loginpage.dobTextField.get(0).click();
		loginpage.dobTextField.get(0).sendKeys(Commons.getGlobalPropertiesValue("dob"));
//		Driver.findElement(By.xpath("//android.widget.EditText[@package='com.mosl.mobile']")).click();
//		Driver.findElement(By.xpath("//android.widget.EditText[@package='com.mosl.mobile']")).sendKeys("18052005 ");
		Driver.hideKeyboard();
		loginpage.confirmDobButton.click();
		Thread.sleep(500);
		loginpage.exploreTheAppButton.click();
		Thread.sleep(500);
		loginpage.iUnderstandRddButton.click();
		Thread.sleep(500);

	}


}
