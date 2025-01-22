package a_Nadeem;

import java.io.IOException;
import java.net.URL;
import java.time.Duration;
import java.util.Collections;
import java.util.HashMap;

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
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import pageobjects.GetQuote;
import pageobjects.HomePage;
import pageobjects.LoginPage;
import pageobjects.MfHomePage;
import pageobjects.ResusableMethods;
import pageobjects.StocksHomePage;
import pageobjects.Watchlist;
import utils.Commons;

public class MoRise {
	AndroidDriver Driver;
	String status;
	int Global_Search_Results_loop = 20;
	int Homepage_portfolio_snap_loop = 20;
	int Stocks_homepage_portfolio_snap_loop = 0;
	int mf_homepage_portfolio_snap_loop = 20;
	int watchlis_loop = 20; // same for option watch list
	int Get_quote_loop = 20; // same for Get quote, Fundamentals, Technicals, News
	WebDriverWait wait = new WebDriverWait(Driver, Duration.ofSeconds(10));

	@Test(priority = 1)
	public void Verify_user_login_and_clicks_on_RDD() throws InterruptedException, IOException {
		LoginPage loginpage = new LoginPage(Driver);
		try {
			if (loginpage.loginButton.isDisplayed()) {
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
				Thread.sleep(5000);
				loginpage.dobTextField.get(0).click();
				loginpage.dobTextField.get(0).sendKeys(Commons.getGlobalPropertiesValue("dob"));
				Driver.hideKeyboard();
				loginpage.confirmDobButton.click();
				Thread.sleep(500);
				loginpage.exploreTheAppButton.click();
				Thread.sleep(500);
				loginpage.iUnderstandRddButton.click();
				Thread.sleep(500);
			}
		} catch (Exception biometricLoginException) {
			System.out.println("Biometric login");
			Thread.sleep(5000);
		}

	}

	@Test(priority = 2)
	public void Verify_Global_search_result() throws InterruptedException, IOException {

		HomePage homepage = new HomePage(Driver);
		String tableName = "Global_Search_Results";
		logTableStart(tableName);
		for (int i = 1; i <= Global_Search_Results_loop; i++) {
			homepage.Globalsearchbeforetap.click();
			homepage.Globalsearchaftertap.get(1).sendKeys(Commons.getGlobalPropertiesValue("global_search_scrip"));
			long startTime = System.currentTimeMillis();
			try {
				wait.until(ExpectedConditions.visibilityOf(homepage.Globalsearchresult));
				homepage.Globalsearchresult.isDisplayed();
				status = "Pass";
			} catch (Exception e) {
				status = "Fail";
			}
			long endTime = System.currentTimeMillis();
			long timeTaken = endTime - startTime;
			Driver.hideKeyboard();
			Thread.sleep(1000);
			Driver.navigate().back();
			logTableRow(tableName, i, timeTaken, status);
			Thread.sleep(1000);
		}
		logTableEnd(tableName);
	}

	@Test(priority = 3)
	public void Verify_homepage_portfolio_snapshot() throws InterruptedException, IOException {
		HomePage homepage = new HomePage(Driver);
		String tableName = "Homepage_portfolio_snap";
		logTableStart(tableName);
		for (int i = 1; i <= Homepage_portfolio_snap_loop; i++) {
			Thread.sleep(200);
			homepage.ExpandIcon.click();
			long startTime = System.currentTimeMillis();
			try {
				wait.until(ExpectedConditions.elementToBeClickable(homepage.availablemargin));
				homepage.availablemargin.isDisplayed();
				status = "Pass";
			} catch (Exception e) {
				status = "Fail";
			}
			long endTime = System.currentTimeMillis();
			long timeTaken = endTime - startTime; // Time calculation
			logTableRow(tableName, i, timeTaken, status);
			homepage.CollapseIcon.click();
			Thread.sleep(300);
		}
		logTableEnd(tableName);

	}

	@Test(priority = 4)
	public void Verify_Stock_homepage_Portfolio_Snapshot() throws InterruptedException, IOException {

		StocksHomePage stockshomepage = new StocksHomePage(Driver);
		Thread.sleep(500);
		stockshomepage.stocksHomepageTab.click();
		String tableName = "Stocks_homepage_portfolio_snap";
		logTableStart(tableName);
		for (int i = 1; i <= Stocks_homepage_portfolio_snap_loop; i++) {
			stockshomepage.stocksPortfolioexpand.click();
			long startTime = System.currentTimeMillis(); // Start time
			try {
				wait.until(ExpectedConditions.visibilityOf(stockshomepage.stocksHomepageCollapseIcon));
				stockshomepage.stocksHomepageCollapseIcon.isDisplayed();
				status = "Pass";
			} catch (Exception e) {
				status = "Fail";
			}
			long endTime = System.currentTimeMillis();
			long timeTaken = endTime - startTime;
			logTableRow(tableName, i, timeTaken, status);
			stockshomepage.stocksHomepageCollapseIcon.click();
			Thread.sleep(200);
		}
		logTableEnd(tableName);
	}

	@Test(priority = 5)
	public void Verify_MF_homepage_Portfolio_Snapshot() throws InterruptedException, IOException {

		MfHomePage mfHomePage = new MfHomePage(Driver);
		mfHomePage.MfTab.click();
		Thread.sleep(500);
		String tableName = "mf_homepage_portfolio_snap";
		logTableStart(tableName);

		for (int i = 1; i <= mf_homepage_portfolio_snap_loop; i++) {
			mfHomePage.MfExpandIcon.click();
			long startTime = System.currentTimeMillis();
			try {
				wait.until(ExpectedConditions.elementToBeClickable(mfHomePage.mfCollapseIcon));
				mfHomePage.mfCollapseIcon.isDisplayed();
				status = "Pass";
			} catch (Exception e) {
				status = "Fail";
			}
			long endTime = System.currentTimeMillis();
			long timeTaken = endTime - startTime;
			logTableRow(tableName, i, timeTaken, status);
			mfHomePage.mfCollapseIcon.click();
			Thread.sleep(200);
		}
		logTableEnd(tableName);
	}

	@Test(priority = 6)
	public void Verify_User_clicks_on_watchlist() throws InterruptedException {
		HomePage homepage = new HomePage(Driver);
		Watchlist watchlist = new Watchlist(Driver);
		homepage.homeTabHeader.click();
		homepage.WatchlistBottombar.click();
		ResusableMethods.horizontalSwipetillElement(Driver, watchlist.donotdelte, 0, 5, 123, 844, 692);
		watchlist.donotdelte.click();
		String tableName = "Watchlist";
		logTableStart(tableName);
		for (int i = 1; i <= watchlis_loop; i++) {
			tapWithActions(Driver, 289, 526);
			long startTime = System.currentTimeMillis();
			try {
				watchlist.donotdelte.isDisplayed();
				status = "Pass";
			} catch (Exception e) {
				status = "Fail";
			}
			long endTime = System.currentTimeMillis();
			long timeTaken = endTime - startTime;
			logTableRow(tableName, i, timeTaken, status);
			watchlist.optionlist.click();
		}
		logTableEnd(tableName);
	}

	@Test(priority = 7)
	public void Verify_User_clicks_on_Option_watchlist() throws InterruptedException {
		Watchlist watchlist = new Watchlist(Driver);
		GetQuote getquote = new GetQuote(Driver);
		String tableName = "Option_Watchlist";
		logTableStart(tableName);
		for (int i = 1; i <= watchlis_loop; i++) {
			watchlist.optionlist.click();
			long startTime = System.currentTimeMillis();
			try {
				getquote.callbutton.isDisplayed();
				status = "Pass";
			} catch (Exception e) {
				status = "Fail";
			}
			long endTime = System.currentTimeMillis();
			long timeTaken = endTime - startTime;
			logTableRow(tableName, i, timeTaken, status);
			tapWithActions(Driver, 289, 526);
		}
		logTableEnd(tableName);
	}

	@Test(priority = 8)
	public void Verify_user_clicks_getquote_overview() throws InterruptedException {
		GetQuote getquote = new GetQuote(Driver);
		Watchlist watchlist = new Watchlist(Driver);
		watchlist.watchlistbutton.click();
		ResusableMethods.horizontalSwipetillElement(Driver, watchlist.donotdelte, 0, 5, 123, 844, 692);
		watchlist.donotdelte.click();
		watchlist.scriptinwatchlist.click();
		String tableName = "Get Quote overview";
		logTableStart(tableName);
		for (int i = 1; i <= Get_quote_loop; i++) {
			getquote.overviewbutton.click();
			long startTime = System.currentTimeMillis();
			try {
				getquote.depth.isDisplayed();
				status = "Pass";
			} catch (Exception e) {
				status = "Fail";
			}
			long endTime = System.currentTimeMillis();
			long timeTaken = endTime - startTime;
			logTableRow(tableName, i, timeTaken, status);
			getquote.fundamentaltab.click();
		}
		logTableEnd(tableName);
	}

	@Test(priority = 9)
	public void Verify_user_clicks_getquote_Fundamentals() throws InterruptedException {
		GetQuote getquote = new GetQuote(Driver);
		String tableName = "Get Quote Fundamentals";
		logTableStart(tableName);
		for (int i = 1; i <= Get_quote_loop; i++) {
			getquote.fundamentaltab.click();
			long startTime = System.currentTimeMillis();
			try {
				wait.until(ExpectedConditions.elementToBeClickable(getquote.fundamentalratios));
				getquote.fundamentalratios.isDisplayed();
				status = "Pass";
			} catch (Exception e) {
				status = "Fail";
			}
			long endTime = System.currentTimeMillis(); // End time
			long timeTaken = endTime - startTime;
			logTableRow(tableName, i, timeTaken, status);
			getquote.technicaltab.click();
			Thread.sleep(500);
		}

		logTableEnd(tableName);
	}

	@Test(priority = 10)
	public void Verify_user_clicks_getquote_fundamental() throws InterruptedException {
		GetQuote getquote = new GetQuote(Driver);
		String tableName = "Get Quote Technical";
		logTableStart(tableName);
		for (int i = 1; i <= Get_quote_loop; i++) {
			getquote.fundamentaltab.click();
			long startTime = System.currentTimeMillis();
			try {
				wait.until(ExpectedConditions.visibilityOf(getquote.fundamentalratios));
				getquote.fundamentalratios.isDisplayed();
				status = "Pass";
			} catch (Exception e) {
				status = "Fail";
				long endTime = System.currentTimeMillis();
				long timeTaken = endTime - startTime;
				logTableRow(tableName, i, timeTaken, status);
				getquote.technicaltab.click();
			}
			logTableEnd(tableName);
		}
	}

	@Test(priority = 11)
	public void Verify_user_clicks_getquote_technical() throws InterruptedException {
		GetQuote getquote = new GetQuote(Driver);
		String tableName = "Get Quote News";
		logTableStart(tableName);
		for (int i = 1; i <= Get_quote_loop; i++) {
			getquote.technicaltab.click();
			long startTime = System.currentTimeMillis(); // start time
			try {
				wait.until(ExpectedConditions.visibilityOf(getquote.deliveryvolume));
				getquote.deliveryvolume.isDisplayed();
				status = "Pass";
			} catch (Exception e) {
				status = "Fail";
			}
			long endTime = System.currentTimeMillis(); // End
			long timeTaken = endTime - startTime; // Time calculation
			logTableRow(tableName, i, timeTaken, status);
			getquote.Newstab.click();
		}
		logTableEnd(tableName);
		Driver.navigate().back();
	}

	public static void tapWithActions(AppiumDriver Driver, int x, int y) {

		PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
		Sequence tap = new Sequence(finger, 1);

		tap.addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), x, y));
		tap.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
		tap.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

		Driver.perform(Collections.singletonList(tap));
	}

	// Helper Methods for Logging Tables
	public void logTableStart(String tableName) {
		Reporter.log("<h3>" + tableName + "</h3>", true);
		Reporter.log("<table border='1' style='border-collapse: collapse; width: 75%; text-align: center;'>", true);
		Reporter.log("<tr><th>Iteration Count</th><th>Time Taken (ms)</th><th>Status</th></tr>", true);

	}

	public void logTableRow(String tableName, int iteration, long timeTaken, String status) {
		// Define the color and text styles based on the status
		String statusColor = "";
		String statusTextStyle = "color: white; font-weight: bold;";

		if ("Fail".equalsIgnoreCase(status)) {
			statusColor = "background-color: red;";
		} else if ("Pass".equalsIgnoreCase(status)) {
			statusColor = "background-color: green;";
		}

		Reporter.log("<tr><td>" + iteration + "</td><td>" + timeTaken + "</td><td style='" + statusColor
				+ statusTextStyle + "'>" + status + "</td></tr>", true);
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
			capabilities.setCapability("noReset", true);

			Driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
			DriverFactory.addDriver(Driver);
			Driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
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

	// kill application after test
	@AfterTest
	public void verify_User_kills_app() {
		if (Driver != null) {
			Driver.quit();
		}
	}

}
