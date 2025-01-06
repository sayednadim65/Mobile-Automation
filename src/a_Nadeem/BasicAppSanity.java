package a_Nadeem;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import drivers.DriverFactory;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import pageobjects.HomePage;
import pageobjects.LoginPage;
import utils.Commons;

public class BasicAppSanity {
	AndroidDriver Driver;

	@Test(priority = 1, dependsOnMethods = {"Verify_user_launch_application"})
	public void Verify_user_login_and_clicks_on_RDD() throws InterruptedException, IOException {

		LoginPage loginpage = new LoginPage(Driver);

		loginpage.loginButton.isDisplayed();
		loginpage.loginButton.click();
		Thread.sleep(1000);
		loginpage.userID.click();
		loginpage.userID.sendKeys(Commons.getGlobalPropertiesValue("userId"));
		Driver.hideKeyboard();
		loginpage.nextbutton.click();
		Thread.sleep(3000);
		loginpage.passwordTextField.click();
		loginpage.passwordTextField.sendKeys(Commons.getGlobalPropertiesValue("password"));
		Driver.hideKeyboard();
		loginpage.loginButton.click();
		Thread.sleep(5000);
		loginpage.dobTextField.get(0).click();
		loginpage.dobTextField.get(0).sendKeys(Commons.getGlobalPropertiesValue("dob"));
		Driver.hideKeyboard();
		loginpage.confirmDobButton.click();
		Thread.sleep(3000);

		loginpage.exploreTheAppButton.click();
		Thread.sleep(5000);
		loginpage.iUnderstandRddButton.click();
		Thread.sleep(3000);

	}

	@Test(priority = 2, dependsOnMethods = {"Verify_user_login_and_clicks_on_RDD"})
	public void homePageHeaderTabs() {
		HomePage homepage = new HomePage(Driver);
		
		String Home = homepage.homeTabHeader.getAttribute("content-desc");
		boolean isVerified = Home.equalsIgnoreCase("Home Tab 1 of 5");
		String status = isVerified ? "Pass" : "fail";
		System.out.println(status);
	}
	
	
//	Helper Methods for Logging Tables
	public class TableLogger {

		private int rowCounter = 0; // To keep track of the serial number

		// Start the table with a title
		public void logTableStart(String tableName) {
			Reporter.log("<h3>" + tableName + "</h3>", true);
			Reporter.log("<table border='1' style='border-collapse: collapse; width: 75%; text-align: center;'>", true);
			Reporter.log("<tr><th>Sr. No</th><th>Test Case</th><th>Status</th></tr>", true);
		}

		// Add a row to the table
		public void logTableRow(String testCase, String status) {
			rowCounter++; // Increment the serial number
			Reporter.log("<tr><td>" + rowCounter + "</td><td>" + testCase + "</td><td>" + status + "</td></tr>", true);
		}

		// End the table
		public void logTableEnd() {
			Reporter.log("</table>", true);
		}

	}

	@BeforeTest
	public void Verify_user_launch_application() throws InterruptedException, IOException {
		if ("RealDevice".equalsIgnoreCase(Commons.getGlobalPropertiesValue("Execution"))) {

			System.out.println("Initializing Appium...");
			DesiredCapabilities capabilities = new DesiredCapabilities();
			capabilities.setCapability("platformName", "Android");
			capabilities.setCapability("platformVersion", "13");
			capabilities.setCapability("deviceName", "CPH2467");
			capabilities.setCapability("udid", "97957054");
			capabilities.setCapability("appPackage", Commons.getGlobalPropertiesValue("Rise_app_package"));
			capabilities.setCapability("appActivity", Commons.getGlobalPropertiesValue("Rise_app_activity"));
			capabilities.setCapability("automationName", "UiAutomator2");
			capabilities.setCapability("autoGrantPermissions", true);

			Driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
			DriverFactory.addDriver(Driver);
			System.out.println("app launch succesfully");
		}

		else if ("BrowserStack".equalsIgnoreCase(Commons.getGlobalPropertiesValue("Execution"))) {

			System.out.println("Starting browserstack");
			UiAutomator2Options capabilities = new UiAutomator2Options();

			HashMap<String, Object> bstackOptions = new HashMap<String, Object>();
			bstackOptions.put("userName", "dheeraj142");
			bstackOptions.put("accessKey", "MhpLs2spj2FFtYv9TFiV");
			bstackOptions.put("appiumVersion", "2.0.1");
			bstackOptions.put("debug", "true");
			bstackOptions.put("interactiveDebugging", "true");
			// bstackOptions.put("build", buildName);

			capabilities.setCapability("platformName", "android");
			capabilities.setCapability("appium:platformVersion", "14.0");
			capabilities.setCapability("appium:deviceName", "Google Pixel 8 Pro");
			capabilities.setCapability("appium:app", "bs://30835cecdc1668bee867197b6dcbd3d06bbe28b4");
			capabilities.setCapability("appium:automationName", "UIAutomator2");
			capabilities.setCapability("autoGrantPermissions", true);
			capabilities.setCapability("bstack:options", bstackOptions);

			Driver = new AndroidDriver(new URL("https://hub-cloud.browserstack.com/wd/hub"), capabilities);
			System.out.println("app launch succesfully");

			DriverFactory.addDriver(Driver);

			Thread.sleep(500);

		}
	}

	@AfterTest
	public void verify_User_kills_app() {
		if (Driver != null) {
			Driver.quit();
		}
	}

}
