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
import pageobjects.HomePage;
import pageobjects.LoginPage;
import pageobjects.MfHomePage;
import pageobjects.Portfolio;
import pageobjects.ResusableMethods;
import pageobjects.StocksHomePage;
import pageobjects.Watchlist;
import utils.Commons;

public class MoRise {
	AndroidDriver Driver;
	String status;
	int Global_Search_Results_loop = 100;
	int Homepage_portfolio_snap_loop = 80;
	int Stocks_homepage_portfolio_snap_loop = 100;
	int mf_homepage_portfolio_snap_loop = 100;
	int watchlis_loop = 100; // same for option watch list
	int Get_quote_loop = 100; // same for Get quote, Fundamentals, Technicals, News
	WebDriverWait wait = new WebDriverWait(Driver, Duration.ofSeconds(2));

	@Test(priority = 1, enabled = true)
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

	@Test(priority = 2, enabled = false)
	public void Verify_Global_search_result() throws InterruptedException, IOException {

		HomePage homepage = new HomePage(Driver);
		String tableName = "Global_Search_Results";
		logTableStart(tableName);
		for (int i = 1; i <= Global_Search_Results_loop; i++) {
			homepage.Globalsearchbeforetap.click();
			homepage.Globalsearchaftertap.get(1).sendKeys(Commons.getGlobalPropertiesValue("global_search_scrip"));
			try {
				long startTime = System.currentTimeMillis();
				wait.until(ExpectedConditions.visibilityOf(homepage.Globalsearchresult));
				long endTime = System.currentTimeMillis();
				homepage.Globalsearchresult.isDisplayed();
				status = "Pass";
				logTableRow(tableName, i, endTime - startTime, status);
			} catch (Exception e) {
				long startTime = System.currentTimeMillis();
				status = "Fail";
				long endTime = System.currentTimeMillis();
				logTableRow(tableName, i, endTime - startTime, status);
			}
			Driver.hideKeyboard();
			Driver.navigate().back();
		}
		logTableEnd(tableName);
	}

	@Test(priority = 3, enabled = false)
	public void Verify_homepage_portfolio_snapshot() throws InterruptedException, IOException {
		HomePage homepage = new HomePage(Driver);
		String tableName = "Homepage_portfolio_snap";
		logTableStart(tableName);
		for (int i = 1; i <= Homepage_portfolio_snap_loop; i++) {
			homepage.ExpandIcon.click();
			try {
				wait.until(ExpectedConditions.elementToBeClickable(homepage.availablemargin));
				long startTime = System.currentTimeMillis();
				homepage.availablemargin.isDisplayed();
				long endTime = System.currentTimeMillis();
				status = "Pass";
				logTableRow(tableName, i, endTime - startTime, status);
			} catch (Exception e) {
				long startTime = System.currentTimeMillis();
				status = "Fail";
				long endTime = System.currentTimeMillis();
				logTableRow(tableName, i, endTime - startTime, status);
			}
			homepage.CollapseIcon.click();
		}
		logTableEnd(tableName);

	}

	@Test(priority = 4, enabled = false)
	public void Verify_Stock_homepage_Portfolio_Snapshot() throws InterruptedException, IOException {
		StocksHomePage stockshomepage = new StocksHomePage(Driver);
		stockshomepage.stocksHomepageTab.click();
		String tableName = "Stocks_homepage_portfolio_snap";
		logTableStart(tableName);
		for (int i = 1; i <= Stocks_homepage_portfolio_snap_loop; i++) {
			stockshomepage.stocksPortfolioexpand.click();
			try {
				wait.until(ExpectedConditions.visibilityOf(stockshomepage.stocksHomepageCollapseIcon));
				long startTime = System.currentTimeMillis();
				stockshomepage.stocksHomepageCollapseIcon.isDisplayed();
				long endTime = System.currentTimeMillis();
				logTableRow(tableName, i, endTime - startTime, status);
				status = "Pass";
			} catch (Exception e) {
				long startTime = System.currentTimeMillis();
				status = "Fail";
				long endTime = System.currentTimeMillis();
				logTableRow(tableName, i, endTime - startTime, status);
			}
			stockshomepage.stocksHomepageCollapseIcon.click();
		}
		logTableEnd(tableName);
	}

	@Test(priority = 5, enabled = false)
	public void Verify_MF_homepage_Portfolio_Snapshot() throws InterruptedException, IOException {
		MfHomePage mfHomePage = new MfHomePage(Driver);
		mfHomePage.MfTab.click();
		String tableName = "mf_homepage_portfolio_snap";
		logTableStart(tableName);
		for (int i = 1; i <= mf_homepage_portfolio_snap_loop; i++) {
			mfHomePage.MfExpandIcon.click();
			try {
				wait.until(ExpectedConditions.elementToBeClickable(mfHomePage.mfCollapseIcon));
				long startTime = System.currentTimeMillis();
				mfHomePage.mfCollapseIcon.isDisplayed();
				long endTime = System.currentTimeMillis();
				status = "Pass";
				logTableRow(tableName, i, endTime - startTime, status);
			} catch (Exception e) {
				long startTime = System.currentTimeMillis();
				status = "Fail";
				long endTime = System.currentTimeMillis();
				logTableRow(tableName, i, endTime - startTime, status);
			}
			mfHomePage.mfCollapseIcon.click();
		}
		logTableEnd(tableName);
	}

	@Test(priority = 6, enabled = false)
	public void Verify_User_clicks_on_watchlist() throws InterruptedException {
		HomePage homepage = new HomePage(Driver);
		Watchlist watchlist = new Watchlist(Driver);
		homepage.homeTabHeader.click();
		homepage.WatchlistBottombar.click();
		watchlist.donotdelte.click();
		String tableName = "Watchlist";
		logTableStart(tableName);
		for (int i = 1; i <= watchlis_loop; i++) {

			ResusableMethods.tapWithActions(Driver, 289, 526);
			try {
				long startTime = System.currentTimeMillis();
				wait.until(ExpectedConditions.visibilityOf(watchlist.scriptinwatchlist));
				watchlist.scriptinwatchlist.isDisplayed();
				long endTime = System.currentTimeMillis();
				logTableRow(tableName, i, endTime - startTime, status);
				status = "Pass";
			} catch (Exception e) {
				long startTime = System.currentTimeMillis();
				status = "Fail";
				long endTime = System.currentTimeMillis();
				logTableRow(tableName, i, endTime - startTime, status);
			}
			watchlist.optionlist.click();
		}
		logTableEnd(tableName);
	}

	@Test(priority = 7, enabled = false)
	public void Verify_User_clicks_on_Option_watchlist() throws InterruptedException {
		Watchlist watchlist = new Watchlist(Driver);
		GetQuote getquote = new GetQuote(Driver);
		HomePage homepage = new HomePage(Driver);
		String tableName = "Option_Watchlist";
		logTableStart(tableName);
		homepage.WatchlistBottombar.click();
		for (int i = 1; i <= watchlis_loop; i++) {
			watchlist.optionlist.click();
			try {
				wait.until(ExpectedConditions.visibilityOf(getquote.callbutton));
				long startTime = System.currentTimeMillis();
				getquote.callbutton.isDisplayed();
				long endTime = System.currentTimeMillis();
				logTableRow(tableName, i, endTime - startTime, status);
				status = "Pass";
			} catch (Exception e) {
				long startTime = System.currentTimeMillis();
				status = "Fail";
				long endTime = System.currentTimeMillis();
				logTableRow(tableName, i, endTime - startTime, status);
			}
			ResusableMethods.tapWithActions(Driver, 289, 526);
		}
		logTableEnd(tableName);
	}

	@Test(priority = 8, enabled = false)
	public void Verify_user_clicks_getquote_overview() throws InterruptedException {
		GetQuote getquote = new GetQuote(Driver);
		Watchlist watchlist = new Watchlist(Driver);
		ResusableMethods.tapWithActions(Driver, 289, 526);
		watchlist.donotdelte.click();
		watchlist.scriptinwatchlist.click();
		String tableName = "Get Quote overview";
		logTableStart(tableName);
		for (int i = 1; i <= Get_quote_loop; i++) {
			getquote.overviewbutton.click();
			try {
				wait.until(ExpectedConditions.visibilityOf(getquote.depth));
				long startTime = System.currentTimeMillis();
				getquote.depth.isDisplayed();
				long endTime = System.currentTimeMillis();
				logTableRow(tableName, i, endTime - startTime, status);
				status = "Pass";
			} catch (Exception e) {
				long startTime = System.currentTimeMillis();
				status = "Fail";
				long endTime = System.currentTimeMillis();
				logTableRow(tableName, i, endTime - startTime, status);
			}
			getquote.fundamentaltab.click();
		}
		logTableEnd(tableName);
	}

	@Test(priority = 9, enabled = false)
	public void Verify_user_clicks_getquote_Fundamentals() throws InterruptedException {
		GetQuote getquote = new GetQuote(Driver);
		String tableName = "Get Quote Fundamentals";
		logTableStart(tableName);
		for (int i = 1; i <= Get_quote_loop; i++) {
			getquote.fundamentaltab.click();
			try {
				wait.until(ExpectedConditions.elementToBeClickable(getquote.fundamentalratios));
				long startTime = System.currentTimeMillis();
				getquote.fundamentalratios.isDisplayed();
				long endTime = System.currentTimeMillis();
				status = "Pass";
				logTableRow(tableName, i, endTime - startTime, status);
			} catch (Exception e) {
				long startTime = System.currentTimeMillis();
				status = "Fail";
				long endTime = System.currentTimeMillis();
				logTableRow(tableName, i, endTime - startTime, status);
			}
			getquote.technicaltab.click();
		}
		logTableEnd(tableName);
	}

	@Test(priority = 10, enabled = false)
	public void Verify_user_clicks_getquote_technical() throws InterruptedException {
		GetQuote getquote = new GetQuote(Driver);
		String tableName = "Get Quote Technical";
		logTableStart(tableName);
		for (int i = 1; i <= Get_quote_loop; i++) {
			getquote.technicaltab.click();
			try {
				wait.until(ExpectedConditions.visibilityOf(getquote.deliveryvolume));
				long startTime = System.currentTimeMillis();
				getquote.deliveryvolume.isDisplayed();
				long endTime = System.currentTimeMillis();
				status = "Pass";
				logTableRow(tableName, i, endTime - startTime, status);
			} catch (Exception e) {
				long startTime = System.currentTimeMillis();
				status = "Fail";
				long endTime = System.currentTimeMillis();
				logTableRow(tableName, i, endTime - startTime, status);
			}
			getquote.Newstab.click();
		}
		logTableEnd(tableName);
	}

	@Test(priority = 11, enabled = false)
	public void Verify_user_clicks_getquote_news() throws InterruptedException {
		GetQuote getquote = new GetQuote(Driver);
		String tableName = "Get Quote News";
		logTableStart(tableName);
		for (int i = 1; i <= Get_quote_loop; i++) {
			getquote.Newstab.click();
			try {
				wait.until(ExpectedConditions.visibilityOf(getquote.newsverification));
				long startTime = System.currentTimeMillis();
				getquote.newsverification.isDisplayed();
				long endTime = System.currentTimeMillis();
				status = "Pass";
				logTableRow(tableName, i, endTime - startTime, status);
			} catch (Exception e) {
				long startTime = System.currentTimeMillis();
				status = "Fail";
				long endTime = System.currentTimeMillis();
				logTableRow(tableName, i, endTime - startTime, status);
			}
			getquote.technicaltab.click();
		}
		logTableEnd(tableName);
	}

	@Test(priority = 12, enabled = false)
	public void Verify_user_clicks_getquote_optionchain() throws InterruptedException {
		GetQuote getquote = new GetQuote(Driver);
		String tableName = "option chain";
		logTableStart(tableName);
		for (int i = 1; i <= Get_quote_loop; i++) {
			getquote.optionchain.click();
			try {
				wait.until(ExpectedConditions.visibilityOf(getquote.optionchainverification));
				long startTime = System.currentTimeMillis();
				getquote.optionchainverification.isDisplayed();
				long endTime = System.currentTimeMillis();
				status = "Pass";
				logTableRow(tableName, i, endTime - startTime, status);
			} catch (Exception e) {
				long startTime = System.currentTimeMillis();
				status = "Fail";
				long endTime = System.currentTimeMillis();
				logTableRow(tableName, i, endTime - startTime, status);
			}
			Driver.navigate().back();
		}
		logTableEnd(tableName);
	}

	@Test(priority = 13, enabled = true)
	public void Verify_user_clicks_portfolio_all() throws InterruptedException {
		HomePage homepage = new HomePage(Driver);
		Portfolio portfolio = new Portfolio(Driver);
		String tableName = "portfolio All";
		logTableStart(tableName);
		homepage.portfolioBottombar.click();
		for (int i = 1; i <= Get_quote_loop; i++) {
			portfolio.AllTabPortfolio.click();
			try {
				long startTime = System.currentTimeMillis();
				wait.until(ExpectedConditions.visibilityOf(portfolio.viewAnalysis));
				portfolio.viewAnalysis.isDisplayed();
				long endTime = System.currentTimeMillis();
				status = "Pass";
				logTableRow(tableName, i, endTime - startTime, status);
			} catch (Exception e) {
				long startTime = System.currentTimeMillis();
				status = "Fail";
				long endTime = System.currentTimeMillis();
				logTableRow(tableName, i, endTime - startTime, status);
			}
			portfolio.StocksTabPortfolio.click();
		}
		logTableEnd(tableName);
	}

	@Test(priority = 14, enabled = true)
	public void Verify_user_clicks_portfolio_stocks() throws InterruptedException {
		Portfolio portfolio = new Portfolio(Driver);
		String tableName = "portfolio stocks";
		logTableStart(tableName);
		for (int i = 1; i <= Get_quote_loop; i++) {
			portfolio.StocksTabPortfolio.click();
			try {
				long startTime = System.currentTimeMillis();
				wait.until(ExpectedConditions.visibilityOf(portfolio.currentvaluestocks));
				long endTime = System.currentTimeMillis();
				portfolio.currentvaluestocks.isDisplayed();
				status = "Pass";
				logTableRow(tableName, i, endTime - startTime, status);
			} catch (Exception e) {
				long startTime = System.currentTimeMillis();
				status = "Fail";
				long endTime = System.currentTimeMillis();
				logTableRow(tableName, i, endTime - startTime, status);
			}
			portfolio.MFTabPortfolio.click();
		}
		logTableEnd(tableName);
	}

	@Test(priority = 15, enabled = true)
	public void Verify_user_clicks_portfolio_mf() throws InterruptedException {
		Portfolio portfolio = new Portfolio(Driver);
		String tableName = "portfolio MF";
		logTableStart(tableName);

		for (int i = 1; i <= Get_quote_loop; i++) {
			portfolio.MFTabPortfolio.click();
			try {
				long startTime = System.currentTimeMillis();
				wait.until(ExpectedConditions.visibilityOf(portfolio.currentvalueofmf));
				long endTime = System.currentTimeMillis();
				portfolio.currentvalueofmf.isDisplayed();
				status = "Pass";
				logTableRow(tableName, i, endTime - startTime, status);
			} catch (Exception e) {
				long startTime = System.currentTimeMillis();
				status = "Fail";
				long endTime = System.currentTimeMillis();
				logTableRow(tableName, i, endTime - startTime, status);
			}
			portfolio.PMSTabPortfolio.click();
		}
		logTableEnd(tableName);
	}

	@Test(priority = 16, enabled = true)
	public void Verify_user_clicks_portfolio_PMS() throws InterruptedException {
		Portfolio portfolio = new Portfolio(Driver);
		String tableName = "portfolio PMS";
		logTableStart(tableName);

		for (int i = 1; i <= Get_quote_loop; i++) {
			portfolio.PMSTabPortfolio.click();
			try {
				long startTime = System.currentTimeMillis();
				wait.until(ExpectedConditions.visibilityOf(portfolio.currentvaluestocks));
				portfolio.currentvaluestocks.isDisplayed();
				long endTime = System.currentTimeMillis();
				status = "Pass";
				logTableRow(tableName, i, endTime - startTime, status);
			} catch (Exception e) {
				long startTime = System.currentTimeMillis();
				status = "Fail";
				long endTime = System.currentTimeMillis();
				logTableRow(tableName, i, endTime - startTime, status);
			}
			portfolio.BasketTabPortfolio.click();
		}
		logTableEnd(tableName);
	}

	@Test(priority = 17, enabled = true)
	public void Verify_user_clicks_portfolio_basket() throws InterruptedException {
		Portfolio portfolio = new Portfolio(Driver);
		String tableName = "portfolio Basket";
		logTableStart(tableName);
		for (int i = 1; i <= Get_quote_loop; i++) {
			portfolio.BasketTabPortfolio.click();
			try {
				long startTime = System.currentTimeMillis();
				wait.until(ExpectedConditions.visibilityOf(portfolio.currentvaluebasket));
				portfolio.currentvaluebasket.isDisplayed();
				long endTime = System.currentTimeMillis();
				status = "Pass";
				logTableRow(tableName, i, endTime - startTime, status);
			} catch (Exception e) {
				long startTime = System.currentTimeMillis();
				status = "Fail";
				long endTime = System.currentTimeMillis();
				logTableRow(tableName, i, endTime - startTime, status);
			}
			portfolio.PMSTabPortfolio.click();

		}

		logTableEnd(tableName);
	}

	@Test(priority = 18, enabled = true)
	public void Verify_user_clicks_portfolio_Add_funds() throws InterruptedException {
		HomePage homepage = new HomePage(Driver);
		String tableName = "Add Funds";
		logTableStart(tableName);
		homepage.walleticon.click();

		for (int i = 1; i <= Get_quote_loop; i++) {
			homepage.addfundstab.click();
			try {
				long startTime = System.currentTimeMillis();
				wait.until(ExpectedConditions.visibilityOf(homepage.enteramount));
				homepage.enteramount.isDisplayed();
				long endTime = System.currentTimeMillis();
				status = "Pass";
				logTableRow(tableName, i, endTime - startTime, status);
			} catch (Exception e) {
				long startTime = System.currentTimeMillis();
				status = "Fail";
				long endTime = System.currentTimeMillis();
				logTableRow(tableName, i, endTime - startTime, status);
			}
			homepage.withdrawtab.click();
		}
		logTableEnd(tableName);
	}

	@Test(priority = 19, enabled = true)
	public void Verify_user_clicks_portfolio_withdraw_funds() throws InterruptedException {
		HomePage homepage = new HomePage(Driver);
		String tableName = "withdraw Funds";
		logTableStart(tableName);
		for (int i = 1; i <= Get_quote_loop; i++) {
			homepage.withdrawtab.click();
			try {
				long startTime = System.currentTimeMillis();
				wait.until(ExpectedConditions.visibilityOf(homepage.enteramount));
				homepage.enteramount.isDisplayed();
				long endTime = System.currentTimeMillis();
				status = "Pass";
				logTableRow(tableName, i, endTime - startTime, status);
			} catch (Exception e) {
				long startTime = System.currentTimeMillis();
				status = "Fail";
				long endTime = System.currentTimeMillis();
				logTableRow(tableName, i, endTime - startTime, status);
			}
			homepage.addfundstab.click();
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
