package a_Nadeem;

import java.io.IOException;
import java.net.URL;
import java.time.Duration;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import drivers.DriverFactory;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import pageobjects.LoginPage;
import utils.Commons;

public class PositionScreenLoadTime {
	AndroidDriver Driver;

	public static void tapWithActions(AppiumDriver Driver, int x, int y) {

		PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
		Sequence tap = new Sequence(finger, 1);

		tap.addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), x, y));
		tap.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
		tap.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

		Driver.perform(Collections.singletonList(tap));
	}

	@Test(priority = 1)
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
		Thread.sleep(500);
		loginpage.iUnderstandRddButton.click();
		Thread.sleep(500);
	
	}

// 		precondition trade 5 scripts
	@Test(priority = 2)
	public void Position_screen_time_load() throws InterruptedException {
		String tableName = "Positions";
		logTableStart(tableName);

		for (int i = 1; i <= 10; i++) {

			tapWithActions(Driver, 753, 2160);
			long startTime = System.currentTimeMillis();
			if (Driver.findElement(By.xpath("//android.view.View[contains(@content-desc,'Positions')]"))
					.isDisplayed()) {
				System.out.println("element displayed");
			}
			long endTime = System.currentTimeMillis();
			long timeTaken = endTime - startTime;
			WebElement tabposition = Driver
					.findElement(By.xpath("//android.view.View[contains(@content-desc,'Positions')]"));
			String Exitall = tabposition.getAttribute("content-desc");
			String positionVerification = Exitall.substring(0, 8);
			boolean isverified = positionVerification.equalsIgnoreCase("Position");
			String status = isverified ? "Pass" : "Fail";
			logTableRow(tableName, i, timeTaken, status);
			tapWithActions(Driver, 325, 2160);
			Thread.sleep(2000);
		}
		logTableEnd(tableName);
	}

	@Test(priority = 2)
	public void Position_screen_orderform_time_load() throws InterruptedException {
		tapWithActions(Driver, 325, 2160);
		String tableName = "Positions_orderform";
		logTableStart(tableName);
		for (int i = 1; i <= 10; i++) {

			PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
			int startX = 927; // Starting X coordinate
			int startY = 1718; // Starting Y coordinate
			int endX = 288; // Ending X coordinate
			int endY = 1704; // Ending Y coordinate
			Sequence swipe = new Sequence(finger, 0);
			swipe.addAction(
					finger.createPointerMove(Duration.ofMillis(0), PointerInput.Origin.viewport(), startX, startY));
			swipe.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
			swipe.addAction(
					finger.createPointerMove(Duration.ofMillis(1000), PointerInput.Origin.viewport(), endX, endY));
			swipe.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
			Driver.perform(Arrays.asList(swipe));

			Driver.navigate().back();

			long startTime = System.currentTimeMillis();
			if (Driver.findElement(By.xpath("//android.view.View[contains(@content-desc,'Positions')]"))
					.isDisplayed()) {
				System.out.println("element displayed");
			}
			long endTime = System.currentTimeMillis();
			long timeTaken = endTime - startTime;
			WebElement tabposition = Driver
					.findElement(By.xpath("//android.view.View[contains(@content-desc,'Positions')]"));
			String Exitall = tabposition.getAttribute("content-desc");
			String positionVerification = Exitall.substring(0, 8);
			boolean isverified = positionVerification.equalsIgnoreCase("Position");
			String status = isverified ? "Pass" : "Fail";
			logTableRow(tableName, i, timeTaken, status);

		}
		logTableEnd(tableName);
	}

	// Helper Methods for Logging Tables
	public void logTableStart(String tableName) {
		Reporter.log("<h3>" + tableName + "</h3>", true);
		Reporter.log("<table border='1' style='border-collapse: collapse; width: 75%; text-align: center;'>", true);
		Reporter.log("<tr><th>Iteration Count</th><th>Time Taken (ms)</th><th>Status</th></tr>", true);

	}

	public void logTableRow(String tableName, int iteration, long timeTaken, String status) {
		Reporter.log("<tr><td>" + iteration + "</td><td>" + timeTaken + "</td><td>" + status + "</td></tr>", true);
	}

	public void logTableEnd(String tableName) {
		Reporter.log("</table>", true);
	}

	// application launch beforetest
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
