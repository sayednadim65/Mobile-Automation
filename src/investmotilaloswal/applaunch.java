package investmotilaloswal;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumBy.ByAccessibilityId;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;

public class applaunch {
	AndroidDriver ad;
	String Rise_app_package = "com.mosl.mobile";
	String Rise_app_activity = "mosl.supperfina.com.MainActivity";
	String userId = "Y05120";
	String password = "Dell@4321";
	String dob = "18052005 ";
	int Global_Search_Results_loop = 10;
	int Homepage_portfolio_snap_loop = 1;
	int wait_time = 1;
//	String buildName = "CDN Hotfix 6.0.12" + System.currentTimeMillis();
	String Execution = "BrowserStack"; // "BrowserStack" for browser stack & "RealDevice" for RealDevice

	@Test
	public void GlobalSearch () throws InterruptedException {

		String tableName = "Global_Search_Results";
		logTableStart(tableName);
		for (int i = 1; i <= Global_Search_Results_loop; i++) {
			ad.findElement(ByAccessibilityId.accessibilityId("Search")).click();
			ad.findElement(By.xpath("(//android.widget.ImageView[@package=\"com.mosl.mobile\"])[2]")).sendKeys("idea");

			long startTime = System.currentTimeMillis(); // Start time
			WebDriverWait wait = new WebDriverWait(ad, Duration.ofSeconds(2));
			WebElement searchresult = wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath("//android.view.View[contains(@content-desc,\"IDEA\")]")));
			long endTime = System.currentTimeMillis(); // End time
			long timeTaken = endTime - startTime; // Time calculation
			String search_result = searchresult.getDomAttribute("content-desc");
			String idea = search_result.substring(3, 7); // slicing text
			System.out.println(idea);
			boolean isVerified = idea.equalsIgnoreCase("idea");
			String status = isVerified ? "Pass" : "Fail";
			String Test_Case = isVerified ? "Test Case Global Search is passed." : "Test Case Global Search is Failed.";

			// Logging with the new Test_Case parameter
			logTableRow(tableName, i, timeTaken, status, Test_Case);

			ad.hideKeyboard();
			ad.navigate().back();
			Thread.sleep(500);
		}
		logTableEnd(tableName);
	}

	private void logTableEnd(String tableName) {
		Reporter.log("</table>", true);
	}

	// Modified logTableRow to include a custom Test_Case
	private void logTableRow(String tableName, int iteration, long timeTaken, String status, String Test_Case) {
		Reporter.log("<tr><td>" + iteration + "</td><td>" + timeTaken + "</td><td>" + status + "</td><td>" + Test_Case
				+ "</td></tr>", true);
	}

	// Modified logTableStart to add a "Test_Case" column
	private void logTableStart(String tableName) {
		Reporter.log("<h3>" + tableName + "</h3>", true);
		Reporter.log("<table border='1' style='border-collapse: collapse; width: 75%; text-align: center;'>", true);
		Reporter.log("<tr><th>Iteration Count</th><th>Time Taken (ms)</th><th>Status</th><th>Test_Case</th></tr>", true);
	}

	@BeforeTest
	public void beforeTest() throws MalformedURLException, InterruptedException {

		System.out.println("Initializing Appium...");

		if ("RealDevice".equalsIgnoreCase(Execution)) {
			DesiredCapabilities capabilities = new DesiredCapabilities();
			capabilities.setCapability("platformName", "Android");
			capabilities.setCapability("platformVersion", "13");
			capabilities.setCapability("deviceName", "CPH2467");
			capabilities.setCapability("udid", "97957054");
			capabilities.setCapability("appPackage", Rise_app_package);
			capabilities.setCapability("appActivity", Rise_app_activity);
			capabilities.setCapability("automationName", "UiAutomator2");
			capabilities.setCapability("autoGrantPermissions", true);

			ad = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
		}

		else if ("BrowserStack".equalsIgnoreCase(Execution)) {

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

			ad = new AndroidDriver(new URL("https://hub-cloud.browserstack.com/wd/hub"), capabilities);

			Thread.sleep(500);

		}

		System.out.println("Application started successfully.");

		WebElement imageView = ad.findElement(ByAccessibilityId.accessibilityId("Login"));
		if (imageView.isDisplayed()) {
			imageView.click();
			Thread.sleep(500);
			// Login Application
			ad.manage().timeouts().implicitlyWait(Duration.ofSeconds(wait_time));
			ad.findElement(By.className("android.widget.EditText")).click();
			ad.findElement(By.className("android.widget.EditText")).sendKeys(userId);
			ad.hideKeyboard();
			ad.findElement(ByAccessibilityId.accessibilityId("Next")).click();
			Thread.sleep(2000);
			ad.findElement(By.className("android.widget.EditText")).click();
			ad.findElement(By.className("android.widget.EditText")).sendKeys(password);
			ad.hideKeyboard();
			ad.findElement(ByAccessibilityId.accessibilityId("Login")).click();
			Thread.sleep(12000);
			ad.findElement(By.xpath("//android.widget.EditText[@package=\"com.mosl.mobile\"]")).click();
			ad.findElement(By.xpath("//android.widget.EditText[@package=\"com.mosl.mobile\"]")).sendKeys(dob);
			ad.hideKeyboard();
			ad.findElement(ByAccessibilityId.accessibilityId("Confirm")).click();
			Thread.sleep(2000);
			System.out.println("manual Login completed");
			ad.findElement(ByAccessibilityId.accessibilityId("Explore the app")).click();
			Thread.sleep(8000);
		} else {
			System.out.println("Biometric login");
			Thread.sleep(10000);

		}
		// RDD
		WebElement RDDimageView = ad.findElement(ByAccessibilityId.accessibilityId("I Understand"));
		if (RDDimageView.isDisplayed()) {
			RDDimageView.click();
			Thread.sleep(1000);
			System.out.println("RDD clicked");
		}

	}

	@AfterTest
	public void afterTest() {
		if (ad != null) {
			ad.quit();
		}

	}

}
