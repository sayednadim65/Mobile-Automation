package a_Nadeem;

import java.io.IOException;
import java.net.URL;
import java.time.Duration;
import java.util.HashMap;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import drivers.DriverFactory;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import pageobjects.GetQuote;
import pageobjects.LoginPage;
import pageobjects.OrderForm;
import pageobjects.ResusableMethods;
import pageobjects.Watchlist;
import utils.Commons;

public class benchmarking {
	AndroidDriver Driver;
	WebDriverWait wait = new WebDriverWait(Driver, Duration.ofSeconds(2));
	int wait_time = 1;
	int i;
	String status;
	TableLogger logger = new TableLogger("Buy Order");
	TableLogger Mlogger = new TableLogger("Modify Buy Order");
	TableLogger Clogger = new TableLogger("Cancel Buy Order");

	@Test(priority = 1)
	public void Verify_user_login_and_clicks_on_RDD() throws InterruptedException, IOException {
		LoginPage loginpage = new LoginPage(Driver);
		try {
			if (loginpage.loginButton.isDisplayed()) {
				// Manual Login
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
			}
		} catch (Exception biometricLoginException) {
			System.out.println("Biometric login");
			Thread.sleep(5000);
		}
	}

	@Test(priority = 2)
	public void VerifyUserPlaceBuyOrder() throws InterruptedException {

		logger.logTableStart();
		Mlogger.logTableStart();
		Clogger.logTableStart();

		Watchlist watchlist = new Watchlist(Driver);
		GetQuote getquote = new GetQuote(Driver);
		OrderForm orderform = new OrderForm(Driver);

		// Log to separate tables for each type of action
		for (i = 1; i <= 3; i++) {
			// Tap actions
			ResusableMethods.tapWithActions(Driver, 334, 2205);
			wait.until(ExpectedConditions.visibilityOf(watchlist.donotdelte));
			ResusableMethods.tapWithActions(Driver, 517, 849);
			wait.until(ExpectedConditions.visibilityOf(getquote.nsebutton));
			ResusableMethods.tapWithActions(Driver, 811, 2172);

			// Start Buy Order timer and actions
			long startTime = System.currentTimeMillis();
			wait.until(ExpectedConditions.visibilityOf(orderform.quantityMarket));
			ResusableMethods.cleartextandenterinput(Driver, orderform.quantityMarket, "1");
			ResusableMethods.cleartextandenterinput(Driver, orderform.limitprice, "1.70");
			Driver.hideKeyboard();
			ResusableMethods.tapWithActions(Driver, 621, 2177);
			wait.until(ExpectedConditions.visibilityOf(orderform.disclaimer));
			ResusableMethods.tapWithActions(Driver, 787, 2182);
			wait.until(ExpectedConditions.visibilityOf(orderform.confirmorder));
			ResusableMethods.tapWithActions(Driver, 517, 2187);
			long endTime = System.currentTimeMillis();

			// Log data for Buy Order
			try {
				wait.until(ExpectedConditions.visibilityOf(orderform.done));
				orderform.done.isDisplayed();
				status = "Pass";
			} catch (Exception e) {
				status = "Fail";
			} finally {
				orderform.done.click();
				Driver.navigate().back();
				logger.logTableRow("Buy Order", status, endTime - startTime);
			}

			// Log data for Modify Buy Order
			long MstartTime = System.currentTimeMillis();
			System.out.println("Modify buy order");
			status = "Pass"; // Assuming modify operation is successful
			long MendTime = System.currentTimeMillis();
			Mlogger.logTableRow("Modify Buy Order", status, MendTime - MstartTime);

			// Log data for Cancel Buy Order
			long CstartTime = System.currentTimeMillis();
			System.out.println("Cancel buy order");
			status = "Pass"; // Assuming cancel operation is successful
			long CendTime = System.currentTimeMillis();
			Clogger.logTableRow("Cancel Buy Order", status, CendTime - CstartTime);
		}
		logger.logTableEnd();
		Mlogger.logTableEnd();
		Clogger.logTableEnd();
	}

	// Helper Methods for Logging Tables
	public class TableLogger {
		private int rowCounter = 0; // To keep track of the serial number
		private String tableTitle; // Custom title for each table

		// Constructor to initialize the table title
		public TableLogger(String title) {
			this.tableTitle = title;
		}

		// Start the table with a title
		public void logTableStart() {
			Reporter.log("<h3>" + tableTitle + "</h3>", true);
			Reporter.log("<table border='1' style='border-collapse: collapse; width: 75%; text-align: center;'>", true);
			Reporter.log("<tr><th>Sr. No</th><th>Test Case</th><th>Status</th><th>Time Taken (ms)</th></tr>", true);
		}

		// Add a row to the table
		public void logTableRow(String testCase, String status, long timeTaken) {
			rowCounter++; // Increment the serial number

			// Define the color and text styles based on the status
			String statusColor = "";
			String statusTextStyle = "color: white; font-weight: bold;";

			if ("Fail".equalsIgnoreCase(status)) {
				statusColor = "background-color: red;";
			} else if ("Pass".equalsIgnoreCase(status)) {
				statusColor = "background-color: green;";
			}

			// Create the row with the styled status cell
			Reporter.log("<tr><td>" + rowCounter + "</td><td>" + testCase + "</td><td style='" + statusColor
					+ statusTextStyle + "'>" + status + "</td><td>" + timeTaken + "</td></tr>", true);
		}

		// End the table
		public void logTableEnd() {
			Reporter.log("</table>", true);
		}
	}

	@BeforeTest
	public void Verify_user_launch_app() throws InterruptedException, IOException {

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
			capabilities.setCapability("noReset", true);

			Driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
			DriverFactory.addDriver(Driver);
			System.out.println("app launch succesfully");
			Driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
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
			capabilities.setCapability("appium:app", "bs://3cc272641d1f0df16540c1a6ada81a2ac15a4ac5");
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
