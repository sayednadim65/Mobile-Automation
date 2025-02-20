package a_Nadeem;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Collections;
import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import drivers.DriverFactory;
<<<<<<< HEAD
import io.appium.java_client.AppiumBy.ByAccessibilityId;
import io.appium.java_client.AppiumDriver;
=======
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.AppiumBy.ByAccessibilityId;
>>>>>>> 19e10434cbb4abc1684fb163f3c00c8b16657062
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;

public class BuyOrderModify {
	AndroidDriver Driver;
	// AppiumDriver Driver;
	String Rise_app_package = "com.mosl.mobile";
	String Rise_app_activity = "mosl.supperfina.com.MainActivity";
	String userId = "Y05120";
	String password = "Dell@123";
	String dob = "18052005 ";
	String Execution = "BrowserStack"; // "BrowserStack" for browser stack & "RealDevice" for RealDevice
	int wait_time = 1;
	int i;

	public static void tapWithActions(AppiumDriver Driver, int x, int y) {

		PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
		Sequence tap = new Sequence(finger, 1);

		tap.addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), x, y));
		tap.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
		tap.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

		Driver.perform(Collections.singletonList(tap));
	}

// 	Login
	@Test(priority = 1)
	public void Verify_user_login_and_RDD_() throws MalformedURLException, InterruptedException {
		Thread.sleep(1000);
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
			Driver.findElement(ByAccessibilityId.accessibilityId("Login")).click();
			Thread.sleep(20000);
			Driver.findElement(By.xpath("//android.widget.EditText[@package=\"com.mosl.mobile\"]")).click();
			Driver.findElement(By.xpath("//android.widget.EditText[@package=\"com.mosl.mobile\"]")).sendKeys(dob);
			Driver.hideKeyboard();
			Driver.findElement(ByAccessibilityId.accessibilityId("Confirm")).click();
			Thread.sleep(2000);
			Driver.findElement(ByAccessibilityId.accessibilityId("Explore the app")).click();
			System.out.println("manual Login completed");
			Thread.sleep(3000);
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
	public void VerifyUserPlaceBuyOrder() throws InterruptedException {
		String tableName = "Buy Orders";
		String tableName1 = "Modify Buy Orders";
		String tableName2 = "Modify Buy Orders";

		logTableStart(tableName);
		logTableStart(tableName1);
		logTableStart(tableName2);

		for (i = 1; i <= 2; i++) {
			tapWithActions(Driver, 327, 2163);
			Thread.sleep(500);
			WebDriverWait wait = new WebDriverWait(Driver, Duration.ofSeconds(5));
			Driver.findElement(By.xpath("//android.view.View[contains(@content-desc,\"GTLINFRA\")]")).click();
			long startTime = System.currentTimeMillis(); // Start time
			Driver.findElement(ByAccessibilityId.accessibilityId("Buy")).click();
			WebElement quantity = Driver
					.findElement(By.xpath("//android.widget.EditText[@package='com.mosl.mobile']"));
			WebElement limit = Driver
					.findElement(By.xpath("(//android.widget.EditText[@package='com.mosl.mobile'])[2]"));
			quantity.click();
			quantity.clear();
			quantity.sendKeys("1");

			limit.click();
			limit.clear();
			limit.sendKeys("2.00");

			Driver.hideKeyboard();
			Driver.findElement(ByAccessibilityId.accessibilityId("Buy")).click();
			Driver.findElement(ByAccessibilityId.accessibilityId("Order Anyway")).click();
			Driver.findElement(ByAccessibilityId.accessibilityId("Confirm Order")).click();
			WebElement order_success = wait
					.until(ExpectedConditions.elementToBeClickable(ByAccessibilityId.accessibilityId("View Order")));

			String order_succes = order_success.getAttribute("content-desc");
			boolean isVerified = order_succes.equalsIgnoreCase("View Order");
			String status = isVerified ? "Pass" : "Fail";
			Driver.findElement(ByAccessibilityId.accessibilityId("View Order")).click();

			try {
				WebElement tryAgainButton = wait.until(
						ExpectedConditions.presenceOfElementLocated(ByAccessibilityId.accessibilityId("Try Again")));

				if (tryAgainButton.isDisplayed()) {
					System.out.println("'Try Again' button is displayed. Clicking on it.");
					tryAgainButton.click();
				} else {
					System.out.println("'Try Again' button is not displayed. Moving ahead.");
				}
			} catch (Exception e) {
				// Handle case when element is not found within the wait duration
				System.out.println("'Try Again' button is not found. Moving ahead.");
			}

			long endTime = System.currentTimeMillis(); // End time
			long timeTaken = endTime - startTime; // Time calculation
			Thread.sleep(1000);
			logTableRow(tableName, i, timeTaken, status);

			
//			Modify order
			long modifystartTime = System.currentTimeMillis(); // Start time
			Driver.findElement(By.xpath("//android.view.View[@content-desc='Modify']")).click();

			WebElement modifylimit = Driver
					.findElement(By.xpath("(//android.widget.EditText[@package='com.mosl.mobile'])[2]"));
			modifylimit.click();
			modifylimit.clear();
			modifylimit.sendKeys("2.05");

			Driver.hideKeyboard();
			Driver.findElement(By.xpath("//*[contains(@content-desc,'Modify')]")).click();
			Driver.findElement(ByAccessibilityId.accessibilityId("Confirm Order")).click();
			WebElement Modify_order_success = wait
					.until(ExpectedConditions.elementToBeClickable(ByAccessibilityId.accessibilityId("View Order")));

			String Modify_order_succes = Modify_order_success.getAttribute("content-desc");
			boolean isVerifieded = Modify_order_succes.equalsIgnoreCase("View Order");
			String Modifystatus = isVerifieded ? "Pass" : "Fail";
			Driver.findElement(ByAccessibilityId.accessibilityId("View Order")).click();

			try {
				WebElement tryAgainButton = wait.until(
						ExpectedConditions.presenceOfElementLocated(ByAccessibilityId.accessibilityId("Try Again")));

				if (tryAgainButton.isDisplayed()) {
					System.out.println("'Try Again' button is displayed. Clicking on it.");
					tryAgainButton.click();
				} else {
					System.out.println("'Try Again' button is not displayed. Moving ahead.");
				}
			} catch (Exception e) {
				// Handle case when element is not found within the wait duration
				System.out.println("'Try Again' button is not found. Moving ahead.");
			}

			long modifyendTime = System.currentTimeMillis(); // End time
			long modifytimeTaken = modifyendTime - modifystartTime; // Time calculation
			Thread.sleep(1000);
			Driver.navigate().back();
			logTableRow(tableName1, i, modifytimeTaken, Modifystatus);

		}
		logTableEnd(tableName);
		logTableEnd(tableName1);
		logTableEnd(tableName2);
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
			capabilities.setCapability("appium:app", "bs://ffc8b4c3c04ad63f80d50efe41b74c228a4b4b90");
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
