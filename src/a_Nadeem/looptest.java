package a_Nadeem;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import drivers.DriverFactory;
import io.appium.java_client.AppiumBy.ByAccessibilityId;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;

public class looptest {
	AndroidDriver Driver;
	// AppiumDriver Driver;
	String Rise_app_package = "com.mosl.mobile";
	String Rise_app_activity = "mosl.supperfina.com.MainActivity";
	String userId = "Y05120";
	String password = "Dell@123";
	String dob = "18052005 ";
	String Execution = "RealDevice"; // "BrowserStack" for browser stack & "RealDevice" for RealDevice
	int wait_time = 2;
	int i;

	@Test(priority = 1)
	public void Verify_user_login_and_RDD_() throws MalformedURLException, InterruptedException {
		WebElement imageView = Driver.findElement(ByAccessibilityId.accessibilityId("Login"));
		if (imageView.isDisplayed()) {
			imageView.click();

			Thread.sleep(500);
			// Login Application
			Driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(wait_time));
			Driver.findElement(By.className("android.widget.EditText")).click();
			Driver.findElement(By.className("android.widget.EditText")).sendKeys(userId);
			Driver.hideKeyboard();
			Driver.findElement(ByAccessibilityId.accessibilityId("Next")).click();
			Thread.sleep(3000);
			Driver.findElement(By.className("android.widget.EditText")).click();
			Driver.findElement(By.className("android.widget.EditText")).sendKeys(password);
			Driver.hideKeyboard();
			// Driver.hideKeyboard();
			Driver.findElement(ByAccessibilityId.accessibilityId("Login")).click();
			Thread.sleep(7000);
			Driver.findElement(By.xpath("//android.widget.EditText[@package=\"com.mosl.mobile\"]")).click();
			Driver.findElement(By.xpath("//android.widget.EditText[@package=\"com.mosl.mobile\"]")).sendKeys(dob);
			Driver.hideKeyboard();
			Driver.findElement(ByAccessibilityId.accessibilityId("Confirm")).click();
			Thread.sleep(2000);
			Driver.findElement(ByAccessibilityId.accessibilityId("Explore the app")).click();
			System.out.println("manual Login completed");
			Thread.sleep(1000);
		} else {
			System.out.println("Biometric login");
			Thread.sleep(10000);

		}
//			 RDD
		WebElement RDDimageView = Driver.findElement(ByAccessibilityId.accessibilityId("I Understand"));

		if (RDDimageView.isDisplayed()) {
			RDDimageView.click();
			Thread.sleep(1000);
			System.out.println("RDD clicked");
		}

	}

	@Test(priority = 2)
	public void start_loop() throws InterruptedException {
		for (i = 1; i <= 1; i++) {

			Place_Buy_order();
			Modify_Buy_order();
			Cancel_Buy_order();
		}

	}

	@Test(priority = 2)
	public void Place_Buy_order() throws InterruptedException {
		Driver.findElement(ByAccessibilityId.accessibilityId("Watchlist")).click();
		Driver.findElement(By.xpath("//android.view.View[contains(@content-desc,'GTLINFRA')]")).click();
		Driver.findElement(ByAccessibilityId.accessibilityId("Buy")).click();
		Thread.sleep(500);
		WebElement quantity = Driver
				.findElement(By.xpath("(//android.widget.EditText[@package='com.mosl.mobile'])[1]"));
		WebElement limit = Driver.findElement(By.xpath("(//android.widget.EditText[@package='com.mosl.mobile'])[2]"));

		quantity.click();
		quantity.clear();
		quantity.sendKeys("1");

		limit.click();
		limit.clear();
		limit.sendKeys("2.05");

		Driver.hideKeyboard();
		Driver.findElement(ByAccessibilityId.accessibilityId("Buy")).click();
		Driver.findElement(ByAccessibilityId.accessibilityId("Order Anyway")).click();
		Driver.findElement(ByAccessibilityId.accessibilityId("Confirm Order")).click();
		System.out.println("Buy order placed");
	}

	@Test(priority = 3)
	public void Modify_Buy_order() throws InterruptedException {
		Driver.findElement(ByAccessibilityId.accessibilityId("View Order")).click();
		Driver.findElement(ByAccessibilityId.accessibilityId("Modify")).click();
		Thread.sleep(500);
		WebElement limit = Driver.findElement(By.xpath("(//android.widget.EditText[@package='com.mosl.mobile'])[2]"));
		limit.click();
		limit.clear();
		limit.sendKeys("2.08");
		Driver.hideKeyboard();
		Driver.findElement(ByAccessibilityId.accessibilityId("Modify")).click();
		Driver.findElement(ByAccessibilityId.accessibilityId("Confirm Order")).click();
		System.out.println("Buy order modified");

	}

	@Test(priority = 4)
	public void Cancel_Buy_order() throws InterruptedException {
		Driver.findElement(ByAccessibilityId.accessibilityId("View Order")).click();
		Driver.findElement(ByAccessibilityId.accessibilityId("Cancel")).click();
		Driver.findElement(ByAccessibilityId.accessibilityId("Yes, Proceed")).click();
		Driver.findElement(ByAccessibilityId.accessibilityId("Watchlist")).click();
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

	// application launch
	@BeforeTest
	public void Verify_user_launch_application() throws MalformedURLException, InterruptedException {
		if ("RealDevice".equalsIgnoreCase(Execution)) {

			System.out.println("Initializing Appium...");
			DesiredCapabilities capabilities = new DesiredCapabilities();
			capabilities.setCapability("platformName", "Android");
			capabilities.setCapability("platformVersion", "13");
			capabilities.setCapability("deviceName", "CPH2467");
			capabilities.setCapability("udid", "97957054");
			capabilities.setCapability("appPackage", Rise_app_package);
			capabilities.setCapability("appActivity", Rise_app_activity);
			capabilities.setCapability("automationName", "UiAutomator2");
			capabilities.setCapability("autoGrantPermissions", true);

			Driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
			DriverFactory.addDriver(Driver);
			System.out.println("app launch succesfully");

		}

		else if ("BrowserStack".equalsIgnoreCase(Execution)) {

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

	// kill application after test
	@AfterTest
	public void verify_User_kills_app() {
		if (Driver != null) {
			Driver.quit();
		}
	}
}
