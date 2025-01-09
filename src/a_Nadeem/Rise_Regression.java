package a_Nadeem;

import java.io.IOException;
import java.net.URL;
import java.time.Duration;
import java.util.HashMap;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import drivers.DriverFactory;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import pageobjects.GetQuote;
import pageobjects.HomePage;
import pageobjects.LoginPage;
import pageobjects.MfHomePage;
import pageobjects.OrderForm;
import pageobjects.Portfolio;
import pageobjects.ResusableMethods;
import pageobjects.Watchlist;
import utils.Commons;

public class Rise_Regression {

	AndroidDriver Driver;
	String status;
	TableLogger logger = new TableLogger();
	WebDriverWait wait = new WebDriverWait(Driver, Duration.ofSeconds(20));

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
				Thread.sleep(3000);
			}
		} catch (Exception biometricLoginException) {
			System.out.println("Biometric login");
			Thread.sleep(10000);
		}

	}

	@Test(priority = 2)
	public void App_Regression() throws IOException, InterruptedException {

		logger.logTableStart("Execution Report");

		Global_search_Result();
		Get_quote_fut_tab();
		Get_quote_opt_tab();
		Get_quote_cash_tab();
		Get_quote_Nse_switch_Delivery_buy();
		Get_quote_Nse_switch_Delivery_Sell();
		Get_quote_Bse_switch_Delivery_buy();
		Get_quote_Bse_switch_Delivery_Sell();
		Get_quote_charts();
		Get_quote_optionchain();
		Get_quote_fundamentaltab();
		Get_quote_technicaltab();
		Get_quote_Newstab();
		Get_quote_Transactionstab();
		OrderForm_quantity_toggle();
		OrderForm_amount_toggle();
		MF_collection_viewall_returns();
		Portfolio_view_analysis();
		Portfolio_swipe_Stocks_Tab_verification();
		Portfolio_swipe_MF_Tab_verification();
		Portfolio_swipe_PMS_Tab_verification();
		Portfolio_swipe_Basket_Tab_verification();
		Watchlist_script();
		Add_script_in_watchlist();
		Delete_script_in_watchlist();
		Delete_watchlist();

		logger.logTableEnd();
	}

	public void Global_search_Result() throws InterruptedException, IOException {

		HomePage homepage = new HomePage(Driver);
		homepage.Globalsearchbeforetap.click();
		Thread.sleep(1000);
		long startTime = System.currentTimeMillis(); // Start timer
		homepage.Globalsearchaftertap.get(1).sendKeys(Commons.getGlobalPropertiesValue("global_search_scrip"));
		Thread.sleep(2000);
		WebElement searchresult = wait.until(ExpectedConditions.visibilityOf(homepage.Globalsearchresult));
		long endTime = System.currentTimeMillis(); // End timer
		String resultsearch = searchresult.getAttribute("content-desc");
		String globalsearchresult = resultsearch.substring(3, 8);
		boolean isVerified = globalsearchresult.equalsIgnoreCase("YESBA");
		status = isVerified ? "Pass" : "Fail";
		homepage.Globalsearchresult.click();
		logger.logTableRow("Global Search Result", status, endTime - startTime); // Log search timing
		Thread.sleep(1000);

	}

	public void Get_quote_fut_tab() {
		GetQuote getquote = new GetQuote(Driver);
		wait.until(ExpectedConditions.elementToBeClickable(getquote.FutTab));
		long startTime = System.currentTimeMillis(); // Start timer
		getquote.FutTab.click();
		WebElement NSEFO = wait.until(ExpectedConditions.visibilityOf(getquote.NSEFO));
		String nsefo = NSEFO.getAttribute("content-desc");
		long endTime = System.currentTimeMillis(); // End timer
		boolean isVerified = nsefo.equalsIgnoreCase("NSE_FO");
		status = isVerified ? "Pass" : "Fail";
		logger.logTableRow("Get quote fut tab", status, endTime - startTime); // Log search timing
	}

	public void Get_quote_opt_tab() {
		GetQuote getquote = new GetQuote(Driver);
		wait.until(ExpectedConditions.elementToBeClickable(getquote.optionsTab));
		long startTime = System.currentTimeMillis(); // Start timer
		getquote.optionsTab.click();
		WebElement Call = wait.until(ExpectedConditions.visibilityOf(getquote.callbutton));
		String call = Call.getAttribute("content-desc");
		long endTime = System.currentTimeMillis(); // End timer
		boolean isVerified = call.equalsIgnoreCase("Call");
		status = isVerified ? "Pass" : "Fail";
		logger.logTableRow("Get quote opt tab", status, endTime - startTime); // Log search timing
	}

	public void Get_quote_cash_tab() {
		GetQuote getquote = new GetQuote(Driver);
		wait.until(ExpectedConditions.elementToBeClickable(getquote.cashtab));
		long startTime = System.currentTimeMillis(); // Start timer
		getquote.cashtab.click();
		WebElement Nse = wait.until(ExpectedConditions.visibilityOf(getquote.nsebutton));
		String nse = Nse.getAttribute("content-desc");
		long endTime = System.currentTimeMillis(); // End timer
		boolean isVerified = nse.equalsIgnoreCase("NSE");
		status = isVerified ? "Pass" : "Fail";
		logger.logTableRow("Get quote cash tab", status, endTime - startTime); // Log search timing
	}

	public void Get_quote_Nse_switch_Delivery_buy() throws InterruptedException {
		GetQuote getquote = new GetQuote(Driver);
		OrderForm orderform = new OrderForm(Driver);
		long startTime = System.currentTimeMillis(); // Start timer
		getquote.nsebutton.click();
		getquote.BuyButton.click();
		Thread.sleep(500);
		boolean isVerified = orderform.NseSwitch.isSelected();
		long endTime = System.currentTimeMillis(); // End timer
		status = isVerified ? "Fail" : "Pass";
		Driver.navigate().back();
		logger.logTableRow("Get quote Nse switch delivery buy order form", status, endTime - startTime); // Log search
																											// timing
	}

	public void Get_quote_Nse_switch_Delivery_Sell() throws InterruptedException {
		GetQuote getquote = new GetQuote(Driver);
		OrderForm orderform = new OrderForm(Driver);
		long startTime = System.currentTimeMillis(); // Start timer
		getquote.nsebutton.click();
		getquote.SellButton.click();
		Thread.sleep(500);
		boolean isVerified = orderform.NseSwitch.isSelected();
		long endTime = System.currentTimeMillis(); // End timer
		status = isVerified ? "Fail" : "Pass";
		Driver.navigate().back();
		logger.logTableRow("Get quote Nse switch delivery Sell order form", status, endTime - startTime); // Log search
																											// timing
	}

	public void Get_quote_Bse_switch_Delivery_buy() throws InterruptedException {
		GetQuote getquote = new GetQuote(Driver);
		OrderForm orderform = new OrderForm(Driver);
		long startTime = System.currentTimeMillis(); // Start timer
		getquote.bsebutton.click();
		getquote.BuyButton.click();
		Thread.sleep(500);
		boolean isVerified = orderform.BseSwitch.isSelected();
		long endTime = System.currentTimeMillis(); // End timer
		status = isVerified ? "Fail" : "Pass";
		Driver.navigate().back();
		logger.logTableRow("Get quote Bse switch delivery buy order form", status, endTime - startTime); // Log search
																											// timing
	}

	public void Get_quote_Bse_switch_Delivery_Sell() throws InterruptedException {
		GetQuote getquote = new GetQuote(Driver);
		OrderForm orderform = new OrderForm(Driver);
		long startTime = System.currentTimeMillis(); // Start timer
		getquote.bsebutton.click();
		getquote.SellButton.click();
		Thread.sleep(500);
		boolean isVerified = orderform.BseSwitch.isSelected();
		long endTime = System.currentTimeMillis(); // End timer
		status = isVerified ? "Fail" : "Pass";
		Driver.navigate().back();
		logger.logTableRow("Get quote Bse switch delivery order form", status, endTime - startTime); // Log search
																										// timing
	}

	public void Get_quote_charts() {
		GetQuote getquote = new GetQuote(Driver);
		long startTime = System.currentTimeMillis(); // Start timer
		getquote.chartsbutton.click();
		wait.until(ExpectedConditions.elementToBeClickable(getquote.chartsbuybutton));
		String buy = getquote.chartsbuybutton.getAttribute("content-desc");
		boolean isVerified = buy.equalsIgnoreCase("BUY");
		long endTime = System.currentTimeMillis(); // End timer
		status = isVerified ? "Pass" : "Fail";
		Driver.navigate().back();
		logger.logTableRow("Get quote charts button", status, endTime - startTime); // Log search timing
	}

	public void Get_quote_optionchain() {
		GetQuote getquote = new GetQuote(Driver);
		long startTime = System.currentTimeMillis(); // Start timer
		getquote.optionchainbutton.click();
		wait.until(ExpectedConditions.visibilityOf(getquote.OIoptionchain));
		String OI = getquote.OIoptionchain.getAttribute("content-desc");
		long endTime = System.currentTimeMillis(); // End timer
		boolean isVerified = OI.equalsIgnoreCase("OI");
		status = isVerified ? "Pass" : "Fail";
		Driver.navigate().back();
		logger.logTableRow("Get quote optionchain button", status, endTime - startTime); // Log search timing
	}

	public void Get_quote_fundamentaltab() throws InterruptedException {
		GetQuote getquote = new GetQuote(Driver);
		long startTime = System.currentTimeMillis(); // Start timer
		getquote.fundamentaltab.click();
		Thread.sleep(1000);
		String fundamentalratios = getquote.fundamentalratios.getAttribute("content-desc");
		long endTime = System.currentTimeMillis(); // End timer
		boolean isVerified = fundamentalratios.equalsIgnoreCase("Fundamental Ratios");
		status = isVerified ? "Pass" : "Fail";
		logger.logTableRow("Get quote Fundamental Tab", status, endTime - startTime); // Log search timing
	}

	public void Get_quote_technicaltab() throws InterruptedException {
		GetQuote getquote = new GetQuote(Driver);
		long startTime = System.currentTimeMillis(); // Start timer
		getquote.technicaltab.click();
		Thread.sleep(1000);
		String delivery = getquote.deliveryvolume.getAttribute("content-desc");
		long endTime = System.currentTimeMillis(); // End timer
		boolean isVerified = delivery.equalsIgnoreCase("Delivery & Volume");
		status = isVerified ? "Pass" : "Fail";
		logger.logTableRow("Get quote Technical Tab", status, endTime - startTime); // Log search timing
	}

	public void Get_quote_Newstab() throws InterruptedException {
		GetQuote getquote = new GetQuote(Driver);
		long startTime = System.currentTimeMillis(); // Start timer
		getquote.Newstab.click();
		Thread.sleep(1000);
		String newsverification = getquote.news.getAttribute("content-desc");
		long endTime = System.currentTimeMillis(); // End timer
		boolean isVerified = newsverification.equalsIgnoreCase("News");
		status = isVerified ? "Pass" : "Fail";
		logger.logTableRow("Get quote News Tab", status, endTime - startTime); // Log search timing
	}

	public void Get_quote_Transactionstab() throws InterruptedException {
		GetQuote getquote = new GetQuote(Driver);
		long startTime = System.currentTimeMillis(); // Start timer
		getquote.transactionstab.click();
		wait.until(ExpectedConditions.visibilityOf(getquote.realisedPL));
		String plrealised = getquote.realisedPL.getAttribute("content-desc");
		long endTime = System.currentTimeMillis(); // End timer
		String realisepl = plrealised.split(" ")[0];
		boolean isVerified = realisepl.equalsIgnoreCase("Realised");
		status = isVerified ? "Pass" : "Fail";
		logger.logTableRow("Get quote Transactions Tab", status, endTime - startTime); // Log search timing
	}

	public void OrderForm_quantity_toggle() throws IOException, InterruptedException {
		OrderForm orderform = new OrderForm(Driver);
		GetQuote getquote = new GetQuote(Driver);
		getquote.BuyButton.click();
		long startTime = System.currentTimeMillis(); // Start timer
		orderform.MarketButton.click();
		ResusableMethods.cleartextandenterinput(Driver, orderform.quantityMarket,
				Commons.getGlobalPropertiesValue("Orderform_quantity_amount_toggle"));
		long endTime = System.currentTimeMillis(); // End timer
		Driver.hideKeyboard();
		String amount = orderform.investamount.getAttribute("content-desc");
		String investamount = amount.substring(12, 14);
		String NseLTP = orderform.NseSwitch.getAttribute("content-desc");
		String LTP = NseLTP.substring(5, 7);
		boolean isVerified = investamount.equalsIgnoreCase(LTP);
		status = isVerified ? "Pass" : "Fail";
		logger.logTableRow("Order Form quantity toggle", status, endTime - startTime); // Log search timing
	}

	public void OrderForm_amount_toggle() throws InterruptedException, IOException {

		Thread.sleep(500);
		OrderForm orderform = new OrderForm(Driver);
		long startTime = System.currentTimeMillis(); // Start timer
		orderform.amountswitch.click();
		ResusableMethods.cleartextandenterinput(Driver, orderform.quantityMarket,
				(Commons.getGlobalPropertiesValue("orderform_amount_toggle")));
		Driver.hideKeyboard();
		long endTime = System.currentTimeMillis(); // End timer
		String quantity = orderform.quantityautocalculate.getAttribute("content-desc");
		String autocalulatequantity = quantity.substring(5);
		boolean isVerifyed = autocalulatequantity
				.equalsIgnoreCase(Commons.getGlobalPropertiesValue("orderform_amount_quantity"));
		status = isVerifyed ? "Pass" : "Fail";
		Driver.navigate().back();
		Driver.navigate().back();
		Driver.navigate().back();
		logger.logTableRow("Order Form amount toggle", status, endTime - startTime); // Log search timing
		Thread.sleep(500);
	}

	public void MF_collection_viewall_returns() throws InterruptedException {
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
	}

	public void Portfolio_swipe_Stocks_Tab_verification() throws InterruptedException {
		Portfolio portfolio = new Portfolio(Driver);
		HomePage homepage = new HomePage(Driver);
		homepage.portfolioBottombar.click();
		long startTime = System.currentTimeMillis(); // Start timer
		ResusableMethods.horizontalSwipetillElement(Driver, portfolio.marginpledge, 0, 5, 944, 166, 1285);
		boolean isVerified = wait.until(ExpectedConditions.elementToBeSelected(portfolio.StocksTabPortfolio));
		status = isVerified ? "Pass" : "Fail";

		long endTime = System.currentTimeMillis(); // End timer
		logger.logTableRow("Portfolio swipe Stocks Tab verification", status, endTime - startTime); // Log search

	}

	public void Portfolio_swipe_MF_Tab_verification() {
		Portfolio portfolio = new Portfolio(Driver);
		long startTime = System.currentTimeMillis(); // Start timer
		ResusableMethods.horizontalSwipetillElement(Driver, portfolio.investedmf, 0, 5, 944, 166, 1285);
		boolean isVerified = wait.until(ExpectedConditions.elementToBeSelected(portfolio.MFTabPortfolio));
		status = isVerified ? "Pass" : "Fail";
		long endTime = System.currentTimeMillis(); // End timer
		logger.logTableRow("Portfolio swipe MF Tab verification", status, endTime - startTime); // Log search timing
	}

	public void Portfolio_swipe_PMS_Tab_verification() {
		Portfolio portfolio = new Portfolio(Driver);
		long startTime = System.currentTimeMillis(); // Start timer
		ResusableMethods.horizontalSwipetillElement(Driver, portfolio.investedpms, 0, 5, 944, 166, 1285);
		WebElement PMS = wait.until(ExpectedConditions.elementToBeClickable(portfolio.PMSTabPortfolio));
		String pms = PMS.getAttribute("content-desc");
		String pmsTab = pms.substring(0, 3);
		boolean isVerified = pmsTab.equalsIgnoreCase("PMS");
		status = isVerified ? "Pass" : "Fail";
		long endTime = System.currentTimeMillis(); // End timer
		logger.logTableRow("Portfolio swipe PMS Tab verification", status, endTime - startTime); // Log search // timing
	}

	public void Portfolio_swipe_Basket_Tab_verification() {
		Portfolio portfolio = new Portfolio(Driver);
		long startTime = System.currentTimeMillis(); // Start timer
		ResusableMethods.horizontalSwipetillElement(Driver, portfolio.allbasket, 0, 5, 944, 166, 1285);
		boolean isVerified = wait.until(ExpectedConditions.elementToBeSelected(portfolio.BasketTabPortfolio));
		status = isVerified ? "Pass" : "Fail";
		long endTime = System.currentTimeMillis(); // End timer
		logger.logTableRow("Portfolio swipe Basket Tab verification", status, endTime - startTime); // Log search
	}

	public void Watchlist_script() throws InterruptedException {
		HomePage homepage = new HomePage(Driver);
		Watchlist watchlist = new Watchlist(Driver);
		homepage.WatchlistBottombar.click();
		long startTime = System.currentTimeMillis(); // Start timer
		ResusableMethods.horizontalSwipetillElement(Driver, watchlist.AddButton, 0, 5, 906, 95, 688);

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

	}

	public void Add_script_in_watchlist() throws IOException, InterruptedException {
		Watchlist watchlist = new Watchlist(Driver);
		HomePage homepage = new HomePage(Driver);
		long startTime = System.currentTimeMillis(); // Start timer
		watchlist.Addscript.click();
		homepage.Globalsearchaftertap.get(1).sendKeys(Commons.getGlobalPropertiesValue("global_search_scrip"));
		watchlist.addscripticon.click();
		Driver.hideKeyboard();
		Driver.navigate().back();
		Thread.sleep(2000);
		WebElement scriptinwatchlist = wait.until(ExpectedConditions.visibilityOf(watchlist.scriptinwatchlist));
		String addedscript = scriptinwatchlist.getAttribute("content-desc");
		String watchlistscript = addedscript.substring(0, 7);
		boolean isVerified = watchlistscript.equalsIgnoreCase("YESBANK");
		status = isVerified ? "Pass" : "Fail";
		long endTime = System.currentTimeMillis(); // End timer
		logger.logTableRow("Add Script in watchlist", status, endTime - startTime); // Log timing
	}

	public void Delete_script_in_watchlist() {
		Watchlist watchlist = new Watchlist(Driver);
		long startTime = System.currentTimeMillis(); // Start timer
		ResusableMethods.longpressElement(Driver, watchlist.scriptinwatchlist);
		ResusableMethods.tapWithActions(Driver, 977, 972);
		String scriptdeleted = watchlist.Addscript.getAttribute("content-desc");
		boolean isVerified = scriptdeleted.equalsIgnoreCase("Add Scrip");
		status = isVerified ? "Pass" : "Fail";
		long endTime = System.currentTimeMillis(); // End timer
		logger.logTableRow("Delete script from watchlist", status, endTime - startTime); // Log timing
	}

	public void Delete_watchlist() {
		Watchlist watchlist = new Watchlist(Driver);
		long startTime = System.currentTimeMillis(); // Start timer
		watchlist.kebabmenuwatchlist.click();
		watchlist.managewatchlist.click();
		ResusableMethods.verticalswipetillElement(Driver, watchlist.deleteicon, 0, 5, 532, 2025, 436);
		watchlist.deleteicon.click();

		WebElement verification = wait.until(ExpectedConditions.elementToBeClickable(watchlist.savebutton));
		String watchlistdelte = verification.getAttribute("content-desc");

		boolean isVerified = watchlistdelte.equalsIgnoreCase("Save");
		status = isVerified ? "Pass" : "Fail";
		watchlist.savebutton.click();
		watchlist.okwatchlistcreated.click();

		long endTime = System.currentTimeMillis(); // End timer
		logger.logTableRow("Delete watchlist", status, endTime - startTime); // Log timing

	}

	// Helper Methods for Logging Tables
	public class TableLogger {
		private int rowCounter = 0; // To keep track of the serial number

		// Start the table with a title
		public void logTableStart(String tableName) {
			Reporter.log("<h3>" + tableName + "</h3>", true);
			Reporter.log("<table border='1' style='border-collapse: collapse; width: 75%; text-align: center;'>", true);
			Reporter.log("<tr><th>Sr. No</th><th>Test Case</th><th>Status</th><th>Time Taken (ms)</th></tr>", true);
		}

		// Add a row to the table
		public void logTableRow(String testCase, String status, long timeTaken) {
			rowCounter++; // Increment the serial number
			Reporter.log("<tr><td>" + rowCounter + "</td><td>" + testCase + "</td><td>" + status + "</td><td>"
					+ timeTaken + "</td></tr>", true);
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
	public void Verify_user_kills_app() {
//		HomePage homepage = new HomePage(Driver);
//		homepage.explorebottombar.click();
		if (Driver != null) {
			Driver.quit();
		}
	}

}
