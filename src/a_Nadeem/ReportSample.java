package a_Nadeem;

import java.io.IOException;
import java.net.URL;
import java.time.Duration;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import drivers.DriverFactory;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import pageobjects.GetQuote;
import pageobjects.HomePage;
import pageobjects.LoginPage;
import pageobjects.MfHomePage;
import pageobjects.OrderForm;
import pageobjects.Portfolio;
import pageobjects.Watchlist;
import utils.Commons;

public class ReportSample {

	ExtentReports extent;
	ExtentTest test;
	AndroidDriver Driver;
	String status;
	TableLogger logger = new TableLogger();
	WebDriverWait wait = new WebDriverWait(Driver, Duration.ofSeconds(10));

	@Test(priority = 1)
	public void Verify_user_login_and_clicks_on_RDD() throws InterruptedException, IOException {

		test = extent.createTest("Login");

		try {
			LoginPage loginpage = new LoginPage(Driver);

			if (loginpage.loginButton.isDisplayed()) {
				// Manual Login
				test.info("Attempting Manual Login...");
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
				test.pass("Manual Login successful.");
			} else {
				test.skip("Login button not displayed. Skipping Manual Login.");
			}

		} catch (Exception manualLoginException) {
			// Biometric Login as a fallback
			test.info("Attempting Biometric Login...");
			try {
				Thread.sleep(10000);
				test.pass("Biometric Login successful.");
			} catch (Exception biometricLoginException) {
				test.fail("Biometric Login failed: " + biometricLoginException.getMessage());
			}

		} finally {
			if (test.getStatus().toString().equalsIgnoreCase("fail")) {
				test.fail("Login failed due to an unexpected error.");
			}
		}
	}

	@Test(priority = 2)
	public void App_Regression() throws IOException, InterruptedException {

		logger.logTableStart("Execution Report");

		OrderForm_quantity_toggle();
		OrderForm_amount_toggle();
		MF_collection_viewall_returns();
		Portfolio_view_analysis();
		Portfolio_swipe_Tab_verification();
		Watchlist_script();
		Add_script_in_watchlist();
		Delete_script_in_watchlist();
		Delete_watchlist();

		logger.logTableEnd();
	}

	public void OrderForm_quantity_toggle() throws IOException, InterruptedException {
		test = extent.createTest("Order Form quantity toggle");
		try {
			HomePage homepage = new HomePage(Driver);
			GetQuote getquote = new GetQuote(Driver);
			OrderForm orderform = new OrderForm(Driver);
			homepage.Globalsearchbeforetap.click();
			Thread.sleep(2000);
			homepage.Globalsearchaftertap.get(1).sendKeys(Commons.getGlobalPropertiesValue("global_search_scrip"));

			wait.until(ExpectedConditions.visibilityOf(homepage.Globalsearchresult));
			homepage.Globalsearchresult.click();
			getquote.BuyButton.click();
			long startTime = System.currentTimeMillis(); // Start timer
			orderform.MarketButton.click();
			orderform.quantityMarket.click();
			orderform.quantityMarket.clear();
			orderform.quantityMarket.sendKeys(Commons.getGlobalPropertiesValue("Orderform_quantity_amount_toggle"));
			long endTime = System.currentTimeMillis(); // End timer
			Driver.hideKeyboard();
			String amount = orderform.investamount.getAttribute("content-desc");
			String investamount = amount.substring(12, 14);
			System.out.println(investamount);
			String NseLTP = orderform.NseSwitch.getAttribute("content-desc");
			String LTP = NseLTP.substring(4, 7);
			System.out.println(LTP);

			boolean isVerified = investamount.equalsIgnoreCase(LTP);
			status = isVerified ? "Fail" : "Pass";
			logger.logTableRow("Order Form quantity toggle", status, endTime - startTime); // Log search timing
			Thread.sleep(2000);
			test.pass("Order Form quantity toggle passed.");
		} catch (Exception e) {
			test.fail("Order Form quantity toggle Failed" + e.getMessage());
		}
	}

	public void OrderForm_amount_toggle() throws InterruptedException, IOException {
		test = extent.createTest("Order Form amount toggle");
		try {
			Thread.sleep(2000);
			OrderForm orderform = new OrderForm(Driver);
			long startTime = System.currentTimeMillis(); // Start timer
			orderform.amountswitch.click();
			orderform.quantityMarket.click();
			orderform.quantityMarket.clear();
			orderform.quantityMarket.sendKeys(Commons.getGlobalPropertiesValue("orderform_amount_toggle"));
			Driver.hideKeyboard();
			long endTime = System.currentTimeMillis(); // End timer

			String quantity = orderform.quantityautocalculate.getAttribute("content-desc");
			System.out.println(quantity);
			String autocalulatequantity = quantity.substring(5);
			System.out.println(autocalulatequantity);

			boolean isVerifyed = autocalulatequantity
					.equalsIgnoreCase(Commons.getGlobalPropertiesValue("orderform_amount_quantity"));
			status = isVerifyed ? "Pass" : "Fail";
			Driver.navigate().back();
			Driver.navigate().back();
			Driver.navigate().back();
			logger.logTableRow("Order Form amount toggle", status, endTime - startTime); // Log search timing
			Thread.sleep(2000);
			test.pass("Order Form amount toggle passed.");
		} catch (Exception e) {
			test.fail("Order Form amount toggle Failed " + e.getMessage());
		}
	}

	public void MF_collection_viewall_returns() throws InterruptedException {
		test = extent.createTest("Mutual Fund View All Returns");
		try {
			HomePage homepage = new HomePage(Driver);
			MfHomePage mfhomepage = new MfHomePage(Driver);
			homepage.MfTab.click();
			long startTime = System.currentTimeMillis(); // Start timer

			mfhomepage.MfHomepageCollectionViewAll.click();
			mfhomepage.MfViewAll1mReturns.click();
			boolean isVerified = mfhomepage.onemReturnsMFVerification.isDisplayed();
			status = isVerified ? "Pass" : "Fail";

			mfhomepage.MfViewAll3mReturns.click();
			boolean isVerified1 = mfhomepage.threeMReturnsMFVerification.isDisplayed();
			status = isVerified1 ? "Pass" : "Fail";

			mfhomepage.MfViewAll1YReturns.click();
			boolean isVerified2 = mfhomepage.oneyearReturnsMFVerification.isDisplayed();
			status = isVerified2 ? "Pass" : "Fail";

			mfhomepage.MfViewAll3YReturns.click();
			boolean isVerified3 = mfhomepage.threeYearsReturnsMFVerification.isDisplayed();
			status = isVerified3 ? "Pass" : "Fail";

			mfhomepage.MfViewAll5YReturns.click();
			boolean isVerified4 = mfhomepage.fiveYearsReturnsMFVerification.isDisplayed();
			status = isVerified4 ? "Pass" : "Fail";

			Driver.navigate().back();
			homepage.homeTabHeader.click();
			long endTime = System.currentTimeMillis(); // End timer
			logger.logTableRow("Mutual Fund View All Returns", status, endTime - startTime); // Log search timing
			test.pass("Mutual Fund View All Returns Passed.");
		} catch (Exception e) {
			test.fail("Mutual Fund View All Returns Failed" + e.getMessage());
		}

	}

	public void Intra_options_order_placement() throws InterruptedException {
		HomePage homepage = new HomePage(Driver);

		homepage.homeTabHeader.click();
		long startTime = System.currentTimeMillis(); // Start timer
		// Scroll and find the element
		Driver.findElement(AppiumBy.androidUIAutomator(
				"new UiScrollable(new UiSelector().scrollable(true)).scrollIntoView(new UiSelector().description(\" Smallcase\"))"));
		homepage.IntaoptionsButton.click();
		Thread.sleep(5000);
		long endTime = System.currentTimeMillis(); // End timer
		logger.logTableRow("Intra option order placment", status, endTime - startTime); // Log search timing
	}

	public void Portfolio_view_analysis() throws InterruptedException {
		test = extent.createTest("Portfolio View Analysis");
		try {
			Portfolio portfolio = new Portfolio(Driver);
			HomePage homepage = new HomePage(Driver);
			homepage.portfolioBottombar.click();
			long startTime = System.currentTimeMillis(); // Start timer
			WebDriverWait wait = new WebDriverWait(Driver, Duration.ofSeconds(10));
			wait.until(ExpectedConditions.visibilityOf(portfolio.AllTabPortfolio));

			portfolio.viewAnalysis.click();
			portfolio.returnsAbs.click();
			WebElement current = wait.until(ExpectedConditions.visibilityOf(portfolio.currentVerification));
			String Current = current.getAttribute("content-desc");
			boolean isVerified = Current.equalsIgnoreCase("Current Investment Value");
			status = isVerified ? "Pass" : "Fail";
			Driver.navigate().back();
			Driver.navigate().back();

			wait.until(ExpectedConditions.visibilityOf(portfolio.AllTabPortfolio));

			long endTime = System.currentTimeMillis(); // End timer
			logger.logTableRow("Portfolio View Analysis", status, endTime - startTime); // Log search timing
			test.pass("Portfolio View Analysis Passed.");
		} catch (Exception e) {
			test.fail("Portfolio View Analysis Failed" + e.getMessage());
		}
	}

	public void Portfolio_swipe_Tab_verification() throws InterruptedException {
		test = extent.createTest("portfolio swipe Tab verification");
		try {
			Portfolio portfolio = new Portfolio(Driver);
			long startTime = System.currentTimeMillis(); // Start timer

			// swipe left to left
			Dimension screenSize = Driver.manage().window().getSize();
			int screenWidth = screenSize.getWidth();
			int screenHeight = screenSize.getHeight();
			int startX = (int) (screenWidth * 0.9);
			int endX = (int) (screenWidth * 0.1);
			int centerY = screenHeight / 2;

			PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");

			Sequence swipe = new Sequence(finger, 1)
					.addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), startX, centerY))
					.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg())).addAction(finger
							.createPointerMove(Duration.ofMillis(500), PointerInput.Origin.viewport(), endX, centerY))
					.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg())); // Release

			Driver.perform(Arrays.asList(swipe));

			boolean isVerified = wait.until(ExpectedConditions.elementToBeSelected(portfolio.StocksTabPortfolio));
			status = isVerified ? "Pass" : "Fail";

			long endTime = System.currentTimeMillis(); // End timer
			logger.logTableRow("Portfolio swipe Tab verification", status, endTime - startTime); // Log search timing
			test.pass("portfolio swipe Tab verification passed");
		} catch (Exception e) {
			test.fail("portfolio swipe Tab verification Failed" + e.getMessage());
		}

	}

	public void Watchlist_script() throws InterruptedException {
		test = extent.createTest("Add Watchlist");
		try {
			HomePage homepage = new HomePage(Driver);
			Watchlist watchlist = new Watchlist(Driver);
			homepage.WatchlistBottombar.click();
			long startTime = System.currentTimeMillis(); // Start timer

			int swipeCount = 0;
			int maxSwipes = 5;
			boolean elementFound = false;

			while (!elementFound && swipeCount < maxSwipes) {
				try {
					// Check if the element is present
					WebElement element = new WebDriverWait(Driver, Duration.ofSeconds(1))
							.until(ExpectedConditions.visibilityOf(watchlist.AddButton));
					elementFound = element.isDisplayed();
				} catch (Exception e) {
					// Element not found yet, perform a swipe
					Dimension screenSize = Driver.manage().window().getSize();
					int screenWidth = screenSize.getWidth();

					int startX = (int) (screenWidth * 0.7); // Start from 90% width
					int endX = (int) (screenWidth * 0.1); // End at 10% width
					int centerY = 694; // Swipe in the middle of the screen height

					// Create swipe action
					PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
					Sequence swipe = new Sequence(finger, 1)
							.addAction(finger
									.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), startX, centerY))
							.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
							.addAction(finger.createPointerMove(Duration.ofMillis(500), PointerInput.Origin.viewport(),
									endX, centerY))
							.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

					Driver.perform(Arrays.asList(swipe));

					swipeCount++;
					System.out.println("Performed swipe #" + swipeCount);
				}

			}

			if (elementFound) {
				WebElement addButton = wait.until(ExpectedConditions.elementToBeClickable(watchlist.AddButton));
				addButton.click();
				watchlist.enterwatchlistname.click();
				watchlist.enterwatchlistname.clear();
				watchlist.enterwatchlistname.sendKeys("Created by Automatio");
				watchlist.createButton.click();

				WebElement Createbutton = wait.until(ExpectedConditions.visibilityOf(watchlist.okwatchlistcreated));
				String verification = Createbutton.getAttribute("content-desc");
				boolean isVerified = verification.equalsIgnoreCase("OK");
				status = isVerified ? "Pass" : "Fail";

				watchlist.okwatchlistcreated.click();

				long endTime = System.currentTimeMillis(); // End timer
				logger.logTableRow("Add Watchlist", status, endTime - startTime); // Log timing
			} else {
				System.out.println("Failed to find the element after " + maxSwipes + " swipes.");
			}
			test.pass("Add Watchlist Passed");
		} catch (Exception e) {
			test.fail("Add Watchlist Failed" + e.getMessage());
		}

	}

	public void Add_script_in_watchlist() throws IOException {
		test = extent.createTest("Add Script in watchlist");
		try {
			Watchlist watchlist = new Watchlist(Driver);
			HomePage homepage = new HomePage(Driver);
			long startTime = System.currentTimeMillis(); // Start timer
			watchlist.Addscript.click();
			homepage.Globalsearchaftertap.get(1).sendKeys(Commons.getGlobalPropertiesValue("global_search_scrip"));
			watchlist.addscripticon.click();
			Driver.hideKeyboard();
			Driver.navigate().back();
			try {
				WebElement scriptinwatchlist = wait.until(ExpectedConditions.visibilityOf(watchlist.scriptinwatchlist));

				String addedscript = scriptinwatchlist.getAttribute("content-desc");
				String watchlistscript = addedscript.substring(0, 7);

				System.out.println(watchlistscript);

				boolean isVerified = watchlistscript.equalsIgnoreCase("YESBANK");
				status = isVerified ? "Pass" : "Fail";
			} catch (Exception e) {
				System.out.println("Failed to capture toast message");
			}
			long endTime = System.currentTimeMillis(); // End timer
			logger.logTableRow("Add Script in watchlist", status, endTime - startTime); // Log timing
			test.pass("Add Script in watchlist Passed");
		} catch (Exception e) {
			test.fail("Add Script in watchlist Failed" + e.getMessage());
		}
	}

	public void Delete_script_in_watchlist() {
		test = extent.createTest("Delete script from watchlist");
		try {
			Watchlist watchlist = new Watchlist(Driver);

			long startTime = System.currentTimeMillis(); // Start timer
			// Get element location
			int elementX = watchlist.scriptinwatchlist.getRect().getX()
					+ (watchlist.scriptinwatchlist.getRect().getWidth() / 2);
			int elementY = watchlist.scriptinwatchlist.getRect().getY()
					+ (watchlist.scriptinwatchlist.getRect().getHeight() / 2);

			// Create a PointerInput instance
			PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");

			// Create a sequence for the long press
			Sequence longPress = new Sequence(finger, 1)
					.addAction(
							finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), elementX, elementY)) // Move
																															// to
																															// element
					.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg())) // Press down
					.addAction(finger.createPointerMove(Duration.ofMillis(2000), PointerInput.Origin.viewport(),
							elementX, elementY)) // Hold for 2 seconds
					.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg())); // Release

			// Perform the long press
			Driver.perform(Arrays.asList(longPress));

			tapWithActions(Driver, 977, 977);

			String scriptdeleted = watchlist.Addscript.getAttribute("content-desc");
			System.out.println(scriptdeleted);

			boolean isVerified = scriptdeleted.equalsIgnoreCase("Add Scrip");
			status = isVerified ? "Pass" : "Fail";

			long endTime = System.currentTimeMillis(); // End timer
			logger.logTableRow("Delete script from watchlist", status, endTime - startTime); // Log timing
			test.pass("Delete script from watchlist Passed");
		} catch (Exception e) {
			test.fail("Delete script from watchlist Failed" + e.getMessage());
		}

	}

	public void Delete_watchlist() {
		test = extent.createTest("Delete watchlist");
		try {
			Watchlist watchlist = new Watchlist(Driver);
			HomePage homepage = new HomePage(Driver);

			long startTime = System.currentTimeMillis(); // Start timer
			watchlist.kebabmenuwatchlist.click();
			watchlist.managewatchlist.click();

			int swipeCount = 0;
			int maxSwipes = 5;
			boolean elementFound = false;

			while (!elementFound && swipeCount < maxSwipes) {
				try {
					// Check if the element is present
					WebElement element = new WebDriverWait(Driver, Duration.ofSeconds(1))
							.until(ExpectedConditions.visibilityOf(watchlist.deleteicon));
					elementFound = element.isDisplayed();
				} catch (Exception e) {
					// Element not found yet, perform a swipe
					Dimension screenSize = Driver.manage().window().getSize();
					int screenWidth = screenSize.getWidth();

					int startY = (int) (screenWidth * 0.7); // Start from 90% width
					int endY = (int) (screenWidth * 0.1); // End at 10% width
					int centerX = 532; // Swipe in the middle of the screen height

					// Create swipe action
					PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
					Sequence swipe = new Sequence(finger, 1)
							.addAction(finger
									.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), centerX, startY))
							.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
							.addAction(finger.createPointerMove(Duration.ofMillis(500), PointerInput.Origin.viewport(),
									centerX, endY))
							.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

					Driver.perform(Arrays.asList(swipe));

					swipeCount++;
					System.out.println("Performed swipe #" + swipeCount);
				}

			}

			if (elementFound) {
				watchlist.deleteicon.click();
				watchlist.savebutton.click();

				String verification = homepage.explorebottombar.getAttribute("content-desc");
				boolean isVerified = verification.equalsIgnoreCase("Explore");
				status = isVerified ? "Pass" : "Fail";

				watchlist.okwatchlistcreated.click();

				long endTime = System.currentTimeMillis(); // End timer
				logger.logTableRow("Delete watchlist", status, endTime - startTime); // Log timing
			} else {
				System.out.println("Failed to find the element after " + maxSwipes + " swipes.");
			}
			test.pass("Delete watchlist Passed");
		} catch (Exception e) {
			test.fail("Delete watchlist Failed" + e.getMessage());
		}

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
	public class TableLogger {
		private int rowCounter = 0; // To keep track of the serial number

		// Start the table with a title
		public void logTableStart(String tableName) {
			Reporter.log("<h3>" + tableName + "</h3>", true);

			// Add containers for the charts
			Reporter.log("<div style='display: flex; justify-content: space-around; margin-bottom: 20px;'>", true);
			Reporter.log("<canvas id='statusChart' width='400' height='400'></canvas>", true);
			Reporter.log("<canvas id='timeChart' width='400' height='400'></canvas>", true);
			Reporter.log("</div>", true);

			// Start the table
			Reporter.log(
					"<table id='testTable' border='1' style='border-collapse: collapse; width: 75%; text-align: center;'>",
					true);
			Reporter.log("<tr><th>Sr. No</th><th>Test Case</th><th>Status</th><th>Time Taken (ms)</th></tr>", true);
		}

		// Add a row to the table
		public void logTableRow(String testCase, String status, long timeTaken) {
			rowCounter++; // Increment the serial number
			Reporter.log("<tr><td>" + rowCounter + "</td><td>" + testCase + "</td><td>" + status + "</td><td>"
					+ timeTaken + "</td></tr>", true);
		}

		// End the table and add scripts for charts
		public void logTableEnd() {
			Reporter.log("</table>", true);

			// Add Chart.js script
			Reporter.log("<script src='https://cdn.jsdelivr.net/npm/chart.js'></script>", true);

			// Add script to process table data and render charts
			Reporter.log("<script>", true);
			Reporter.log("document.addEventListener('DOMContentLoaded', function () {", true);

			// Extract data from the table
			Reporter.log("  const table = document.getElementById('testTable');", true);
			Reporter.log("  const rows = table.querySelectorAll('tr');", true);
			Reporter.log("  const statusData = {}; const timeData = [];", true);
			Reporter.log("  rows.forEach((row, index) => {", true);
			Reporter.log("    if (index === 0) return;", true); // Skip header
			Reporter.log("    const cells = row.querySelectorAll('td');", true);
			Reporter.log("    const status = cells[2].innerText.trim();", true);
			Reporter.log("    const time = parseInt(cells[3].innerText.trim(), 10);", true);
			Reporter.log("    statusData[status] = (statusData[status] || 0) + 1;", true);
			Reporter.log("    timeData.push(time);", true);
			Reporter.log("  });", true);

			// Render pie chart for status
			Reporter.log("  const statusCtx = document.getElementById('statusChart').getContext('2d');", true);
			Reporter.log("  new Chart(statusCtx, {", true);
			Reporter.log("    type: 'pie',", true);
			Reporter.log("    data: {", true);
			Reporter.log("      labels: Object.keys(statusData),", true);
			Reporter.log("      datasets: [{", true);
			Reporter.log("        data: Object.values(statusData),", true);
			Reporter.log("        backgroundColor: ['#4caf50', '#f44336', '#ffeb3b', '#2196f3'],", true);
			Reporter.log("      }]", true);
			Reporter.log("    },", true);
			Reporter.log("    options: { responsive: true }", true);
			Reporter.log("  });", true);

			// Render bar chart for time
			Reporter.log("  const timeCtx = document.getElementById('timeChart').getContext('2d');", true);
			Reporter.log("  new Chart(timeCtx, {", true);
			Reporter.log("    type: 'bar',", true);
			Reporter.log("    data: {", true);
			Reporter.log("      labels: timeData.map((_, index) => `Test ${index + 1}`),", true);
			Reporter.log("      datasets: [{", true);
			Reporter.log("        label: 'Time Taken (ms)',", true);
			Reporter.log("        data: timeData,", true);
			Reporter.log("        backgroundColor: '#2196f3',", true);
			Reporter.log("      }]", true);
			Reporter.log("    },", true);
			Reporter.log("    options: { responsive: true, scales: { y: { beginAtZero: true } } }", true);
			Reporter.log("  });", true);
			Reporter.log("});", true);
			Reporter.log("</script>", true);
		}
	}

	@BeforeTest
	public void Verify_user_launch_app() throws InterruptedException, IOException {

		if ("RealDevice".equalsIgnoreCase(Commons.getGlobalPropertiesValue("Execution"))) {

			System.out.println("Initializing Appium...");
			DesiredCapabilities capabilities = new DesiredCapabilities();
			capabilities.setCapability("platformName", "Android");
			Thread.sleep(500);
			capabilities.setCapability("platformVersion", "13");
			Thread.sleep(500);
			capabilities.setCapability("deviceName", "CPH2467");
			Thread.sleep(500);
			capabilities.setCapability("udid", "97957054");
			Thread.sleep(500);
			capabilities.setCapability("appPackage", "com.mosl.mobile.pilot");
			Thread.sleep(500);
			capabilities.setCapability("appActivity", "mosl.supperfina.com.MainActivity");
			Thread.sleep(500);
			capabilities.setCapability("automationName", "UiAutomator2");
			Thread.sleep(500);
			capabilities.setCapability("autoGrantPermissions", true);
			Thread.sleep(500);
			capabilities.setCapability("noReset", true);

			Driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
			DriverFactory.addDriver(Driver);
			System.out.println("app launch succesfully");
			Driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
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

	@BeforeClass
	public void setupExtentReports() {
		// Configure ExtentReports
		ExtentSparkReporter sparkReporter = new ExtentSparkReporter("ExtentReport.html");
		extent = new ExtentReports();
		extent.attachReporter(sparkReporter);
	}

	@AfterTest
	public void Verify_user_kills_app() {
//		HomePage homepage = new HomePage(Driver);
//		homepage.explorebottombar.click();
		if (Driver != null) {
			Driver.quit();
		}
	}

	@AfterClass
	public void tearDownExtentReports() {
		// Flush ExtentReports
		extent.flush();
	}

}
