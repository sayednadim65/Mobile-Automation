package a_Nadeem;

import java.io.IOException;
import java.net.URL;
import java.time.Duration;
import java.util.Collections;
import java.util.HashMap;

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
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import pageobjects.HomePage;
import pageobjects.LoginPage;
import pageobjects.MfHomePage;
import pageobjects.StocksHomePage;
import utils.Commons;

public class MoRise {
	AndroidDriver Driver;
	int Global_Search_Results_loop = 5;
	int Homepage_portfolio_snap_loop = 5;
	int Stocks_homepage_portfolio_snap_loop = 5;
	int mf_homepage_portfolio_snap_loop = 5;
	int watchlis_loop = 5; // same for option watch list
	int Get_quote_loop = 5; // same for Get quote, Fundamentals, Technicals, News

	// Method to tap on x & y coordinate
	AppiumDriver appiumDriver = (AppiumDriver) Driver; // To cast android driver to appium driver
	public static void tapWithActions(AppiumDriver Driver, int x, int y) {

		PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
		Sequence tap = new Sequence(finger, 1);

		tap.addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), x, y));
		tap.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
		tap.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

		Driver.perform(Collections.singletonList(tap));
	}

	public LoginPage object1;
	public HomePage object2;
	public StocksHomePage object3;
	public MfHomePage object4;

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

	@Test(priority = 2)
	public void Verify_Global_search_result() throws InterruptedException, IOException {

		HomePage homepage = new HomePage(Driver);
		String tableName = "Global_Search_Results";
		logTableStart(tableName);
		for (int i = 1; i <= Global_Search_Results_loop; i++) {
			homepage.Globalsearchbeforetap.click();
			homepage.Globalsearchaftertap.get(1).sendKeys(Commons.getGlobalPropertiesValue("global_search_scrip"));
			long startTime = System.currentTimeMillis(); // Start time
			WebDriverWait wait = new WebDriverWait(Driver, Duration.ofSeconds(2));
			WebElement searchresult = wait
					.until(ExpectedConditions.elementToBeClickable(homepage.Globalsearchresult));
			long endTime = System.currentTimeMillis(); // End time
			long timeTaken = endTime - startTime; // Time calculation
			String search_result = searchresult.getAttribute("content-desc");
			String idea = search_result.substring(3, 7);
			boolean isVerified = idea.equalsIgnoreCase(Commons.getGlobalPropertiesValue("global_search_scrip"));
			String status = isVerified ? "Pass" : "Fail";
			Thread.sleep(1000);
			Driver.hideKeyboard();
			Driver.navigate().back();
			logTableRow(tableName, i, timeTaken, status);

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
			long startTime = System.currentTimeMillis(); // Start time
			WebDriverWait wait = new WebDriverWait(Driver, Duration.ofSeconds(1));
			WebElement homepagePortfolioSnapExpand = wait
					.until(ExpectedConditions.elementToBeClickable(homepage.CollapseIcon));
			long endTime = System.currentTimeMillis();
			long timeTaken = endTime - startTime; // Time calculation
			String a = homepagePortfolioSnapExpand.getAttribute("content-desc");
			boolean isVerified = a.equalsIgnoreCase(Commons.getGlobalPropertiesValue("homepagePortfolioVerification"));
			String status = isVerified ? "Pass" : "Fail";

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
			stockshomepage.stocksPortfolioCollapse.click();
			long startTime = System.currentTimeMillis(); // Start time
			WebDriverWait wait = new WebDriverWait(Driver, Duration.ofSeconds(1));
			WebElement stocksHomepagePortfolioSnapExpand = wait
					.until(ExpectedConditions.elementToBeClickable(stockshomepage.stocksHomepageCollapseIcon));
			long endTime = System.currentTimeMillis(); //
			long timeTaken = endTime - startTime; // Time calculation
			String holdings = stocksHomepagePortfolioSnapExpand.getAttribute("content-desc");
			System.out.println(holdings);
			boolean isVerified = holdings
					.equalsIgnoreCase(Commons.getGlobalPropertiesValue("homepagePortfolioVerification"));
			String status = isVerified ? "Pass" : "Fail";

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
			WebDriverWait wait = new WebDriverWait(Driver, Duration.ofSeconds(1));
			WebElement mfHomepagePortfolioExpand = wait
					.until(ExpectedConditions.elementToBeClickable(mfHomePage.mfCollapseIcon));
			long endTime = System.currentTimeMillis();
			long timeTaken = endTime - startTime;
			String collapse = mfHomepagePortfolioExpand.getAttribute("content-desc");
			boolean isVerified = collapse
					.equalsIgnoreCase(Commons.getGlobalPropertiesValue("homepagePortfolioVerification"));
			String status = isVerified ? "Pass" : "Fail";

			logTableRow(tableName, i, timeTaken, status);

			mfHomePage.mfCollapseIcon.click();;
			Thread.sleep(200);
		}
		logTableEnd(tableName);
	}

	/*
	 * // watchlist
	 * 
	 * @Test(priority = 6) public void Verify_User_clicks_on_watchlist() throws
	 * InterruptedException { Driver.findElement(By.xpath(
	 * "//android.view.View[contains(@content-desc,\"Home\")]")).click();
	 * Driver.findElement(ByAccessibilityId.accessibilityId("Watchlist")).click();
	 * String tableName = "Watchlist"; logTableStart(tableName);
	 * 
	 * for (int i = 1; i <= watchlis_loop; i++) { tapWithActions(Driver, 280, 550);
	 * // tap on watchlist long startTime = System.currentTimeMillis(); // start
	 * time WebDriverWait wait = new WebDriverWait(Driver, Duration.ofSeconds(1));
	 * WebElement automationwatchlist = wait.until(
	 * ExpectedConditions.elementToBeClickable(ByAccessibilityId.
	 * accessibilityId("Automation watchlist"))); long endTime =
	 * System.currentTimeMillis(); // End time long timeTaken = endTime - startTime;
	 * // Time calculation String watchlistVerification =
	 * automationwatchlist.getAttribute("content-desc");
	 * 
	 * boolean isVerified =
	 * watchlistVerification.equalsIgnoreCase("Automation watchlist"); String status
	 * = isVerified ? "Pass" : "Fail";
	 * 
	 * logTableRow(tableName, i, timeTaken, status);
	 * 
	 * Driver.findElement(ByAccessibilityId.accessibilityId("Option List")).click();
	 * Thread.sleep(1000);
	 * 
	 * } logTableEnd(tableName);
	 * 
	 * }
	 * 
	 * // Option watchlist
	 * 
	 * @Test(priority = 7) public void Verify_User_clicks_on_Option_watchlist()
	 * throws InterruptedException {
	 * 
	 * String tableName = "Option_Watchlist"; logTableStart(tableName);
	 * 
	 * for (int i = 1; i <= watchlis_loop; i++) {
	 * 
	 * Driver.findElement(ByAccessibilityId.accessibilityId("Option List")).click();
	 * long startTime = System.currentTimeMillis(); // start time WebDriverWait wait
	 * = new WebDriverWait(Driver, Duration.ofSeconds(1)); WebElement
	 * OPtionwatchlist = wait
	 * .until(ExpectedConditions.elementToBeClickable(ByAccessibilityId.
	 * accessibilityId("Call"))); long endTime = System.currentTimeMillis(); // End
	 * time long timeTaken = endTime - startTime; // Time calculation String
	 * OPtionwatchlistVerification = OPtionwatchlist.getAttribute("content-desc");
	 * 
	 * boolean isVerified = OPtionwatchlistVerification.equalsIgnoreCase("Call");
	 * String status = isVerified ? "Pass" : "Fail";
	 * 
	 * logTableRow(tableName, i, timeTaken, status);
	 * 
	 * tapWithActions(Driver, 280, 550); // tap on watchlist Thread.sleep(500);
	 * 
	 * } logTableEnd(tableName); }
	 * 
	 * // get quote overview
	 * 
	 * @Test(priority = 8) public void Verify_user_clicks_getquote_overview() throws
	 * InterruptedException { Driver.findElement(By.xpath(
	 * "//android.view.View[contains(@content-desc,\"GTLINFRA\")]")).click(); String
	 * tableName = "Get Quote overview"; logTableStart(tableName);
	 * 
	 * for (int i = 1; i <= Get_quote_loop; i++) { Driver.findElement(By.xpath(
	 * "//android.view.View[contains(@content-desc,\"Overview\")]")).click(); long
	 * startTime = System.currentTimeMillis(); // start time WebDriverWait wait =
	 * new WebDriverWait(Driver, Duration.ofSeconds(1)); WebElement Depth = wait
	 * .until(ExpectedConditions.elementToBeClickable(ByAccessibilityId.
	 * accessibilityId("Depth"))); long endTime = System.currentTimeMillis(); // End
	 * time long timeTaken = endTime - startTime; // Time calculation String Depth1
	 * = Depth.getAttribute("content-desc"); boolean isVerified =
	 * Depth1.equalsIgnoreCase("Depth"); String status = isVerified ? "Pass" :
	 * "Fail";
	 * 
	 * logTableRow(tableName, i, timeTaken, status);
	 * 
	 * Driver.findElement(By.xpath(
	 * "//android.view.View[contains(@content-desc,\"Fundamental\")]")).click();
	 * Thread.sleep(500); } logTableEnd(tableName); }
	 * 
	 * // get quote fundamental
	 * 
	 * @Test(priority = 9) public void Verify_user_clicks_getquote_Fundamentals()
	 * throws InterruptedException { String tableName = "Get Quote Fundamentals";
	 * logTableStart(tableName); for (int i = 1; i <= Get_quote_loop; i++) {
	 * Driver.findElement(By.xpath(
	 * "//android.view.View[contains(@content-desc,\"Fundamental\")]")).click();
	 * long startTime = System.currentTimeMillis(); // start time WebDriverWait wait
	 * = new WebDriverWait(Driver, Duration.ofSeconds(1)); WebElement fundamental =
	 * wait.until( ExpectedConditions.elementToBeClickable(ByAccessibilityId.
	 * accessibilityId("Fundamental Ratios"))); long endTime =
	 * System.currentTimeMillis(); // End time long timeTaken = endTime - startTime;
	 * // Time calculation String Fundamentals =
	 * fundamental.getAttribute("content-desc"); boolean isVerified =
	 * Fundamentals.equalsIgnoreCase("Fundamental Ratios"); String status =
	 * isVerified ? "Pass" : "Fail";
	 * 
	 * logTableRow(tableName, i, timeTaken, status);
	 * 
	 * Driver.findElement(By.xpath(
	 * "//android.view.View[contains(@content-desc,'Technical')]")).click();
	 * Thread.sleep(500); } logTableEnd(tableName); }
	 * 
	 * // get quote technical
	 * 
	 * @Test(priority = 10) public void Verify_user_clicks_getquote_technical()
	 * throws InterruptedException { String tableName = "Get Quote Technical";
	 * logTableStart(tableName); for (int i = 1; i <= Get_quote_loop; i++) {
	 * Driver.findElement(By.xpath(
	 * "//android.view.View[contains(@content-desc,'Technical')]")).click(); long
	 * startTime = System.currentTimeMillis(); // start time WebDriverWait wait =
	 * new WebDriverWait(Driver, Duration.ofSeconds(1)); WebElement delivery =
	 * wait.until( ExpectedConditions.elementToBeClickable(ByAccessibilityId.
	 * accessibilityId("Delivery & Volume"))); long endTime =
	 * System.currentTimeMillis(); // End time long timeTaken = endTime - startTime;
	 * // Time calculation String deliverys = delivery.getAttribute("content-desc");
	 * boolean isVerified = deliverys.equalsIgnoreCase("Delivery & Volume"); String
	 * status = isVerified ? "Pass" : "Fail"; logTableRow(tableName, i, timeTaken,
	 * status);
	 * 
	 * Driver.findElement(By.xpath(
	 * "//android.view.View[contains(@content-desc,\"News\")]")).click();
	 * Thread.sleep(500); } logTableEnd(tableName); }
	 * 
	 * // get quote News
	 * 
	 * @Test(priority = 10) public void Verify_user_clicks_getquote_news() throws
	 * InterruptedException { String tableName = "Get Quote News";
	 * logTableStart(tableName); for (int i = 1; i <= Get_quote_loop; i++) {
	 * Driver.findElement(By.xpath(
	 * "//android.view.View[contains(@content-desc,\"News\")]")).click(); long
	 * startTime = System.currentTimeMillis(); // start time WebDriverWait wait =
	 * new WebDriverWait(Driver, Duration.ofSeconds(1)); WebElement delivery = wait
	 * .until(ExpectedConditions.elementToBeClickable(ByAccessibilityId.
	 * accessibilityId("Buy"))); long endTime = System.currentTimeMillis(); // End
	 * time long timeTaken = endTime - startTime; // Time calculation String buy =
	 * delivery.getAttribute("content-desc"); boolean isVerified =
	 * buy.equalsIgnoreCase("Buy"); String status = isVerified ? "Pass" : "Fail";
	 * logTableRow(tableName, i, timeTaken, status);
	 * 
	 * Driver.findElement(By.xpath(
	 * "//android.view.View[contains(@content-desc,'Technical')]")).click(); }
	 * logTableEnd(tableName); Driver.navigate().back(); }
	 * 
	 */
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

	// kill application after test
	@AfterTest
	public void verify_User_kills_app() {
		if (Driver != null) {
			Driver.quit();
		}
	}

}
