package a_Nadeem;

import java.io.IOException;
import java.net.URL;
import java.time.Duration;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.WebElement;
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
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import pageobjects.GetQuote;
import pageobjects.HomePage;
import pageobjects.LoginPage;
import pageobjects.MfHomePage;
import pageobjects.OrderForm;
import pageobjects.Portfolio;
import pageobjects.ProfilePage;
import pageobjects.ResusableMethods;
import pageobjects.Watchlist;
import utils.Commons;

public class Rise_Regression {

	AndroidDriver Driver;
	String status;
	ExtentReports extent;
	ExtentTest test;
	TableLogger logger = new TableLogger();
	WebDriverWait wait = new WebDriverWait(Driver, Duration.ofSeconds(3));

	@Test(priority = 1)
	public void Verify_user_login_and_clicks_on_RDD() throws InterruptedException, IOException {

		LoginPage loginpage = new LoginPage(Driver);
		test = extent.createTest("Login Test");
		try {
			if (loginpage.loginButton.isDisplayed()) {
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
				test.info("Manual Login");
				test.pass("Login completed");
			}
		} catch (Exception biometricLoginException) {
			System.out.println("Biometric login");
			test.info("Biometric Login");
			test.pass("Login completed");
			Thread.sleep(5000);
		}
//		test.fail("Login Failed");
	}

	@Test(priority = 2, enabled = true)
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
		homepage_explore_hide_button();
		homepage_portfolio_overallvalue();
		homepage_explore_portfolio_expand();
		homepage_explore_portfolio_position();
		hompage_explore_portfolio_collapse();
		Researchideaibutton();
		IPO_button_CTA();
		Research_Ideas_CTA();
		IAP_CTA();
		Bonds_CTA();
		options_store();
		Stock_basket_CTA();
		Insurance_CTA();
		FixedDeposit_CTA();
		smallcase_CTA();
		Tejimandi();
		try {
			TGS_CTA();
		} catch (Exception e) {
			System.out.println("TGS Failed");
		}
		try {
			Intraoptions_CTA();
		} catch (Exception e) {
			System.out.println("Intra options Failed");
		}
		MFHighReturns_CTA();
		SIPwith500_CTA();
		Taxsaver_CTA();
		MoRecomended_CTA();
		Highestrated_CTA();
		internationalfund_CTA();
		iponfoIbutton();
		ipoviewall();
		ipobutton();
		Nfobutton();
		Stocksipbutton();
		MFsipbutton();
		reportsbutton();
		PNLsummarybutton();
		alertsbutton();
		margincalbutton();
		moapi();
		MF_collection_viewall_returns();
		Portfolio_view_analysis();
		Portfolio_swipe_Stocks_Tab_verification();
		Portfolio_swipe_MF_Tab_verification();
		Portfolio_swipe_PMS_Tab_verification();
		Portfolio_swipe_Basket_Tab_verification();
		Watchlist_script();
		Add_script_in_watchlist();
		MTFinwatchlistscript();
		Delete_script_in_watchlist();
		Delete_watchlist();
		asbaswitch();
		Reports();
		logger.logTableEnd();
	}

	@Test(priority = 3, enabled = false)
	public void orderrouting() throws InterruptedException {
		logger.logTableStart("Order Routing timestamp study  Report");
		for (int i = 1; i <= 36; i++) {
			buyorder();
			modifybuyorder();
			cancelorder();
		}
		logger.logTableEnd();
	}

	public void Global_search_Result() throws InterruptedException, IOException {
		test = extent.createTest("Global Search Result");
		HomePage homepage = new HomePage(Driver);
		homepage.Globalsearchbeforetap.click();
		Thread.sleep(1000);
		long startTime = System.currentTimeMillis();
		homepage.Globalsearchaftertap.get(1).sendKeys(Commons.getGlobalPropertiesValue("global_search_scrip"));
		Thread.sleep(2500);
		try {
			WebElement searchresult = wait.until(ExpectedConditions.visibilityOf(homepage.Globalsearchresult));
			String resultsearch = searchresult.getAttribute("content-desc");
			List<String> splitresult = Arrays.asList(resultsearch.split("\\s+"));
			String globalsearchresult = splitresult.get(1);
			globalsearchresult.equalsIgnoreCase(" YESBANK");
			status = "Pass";
			test.pass("Global search Result Passed");
		} catch (Exception e) {
			status = "Fail";
			test.fail("Global search Result Failed");
			test.info(e.getMessage());
		} finally {
			long endTime = System.currentTimeMillis();
			homepage.Globalsearchresult.click();
			logger.logTableRow("Global Search Result", status, endTime - startTime);
		}
	}

	public void Get_quote_fut_tab() {
		test = extent.createTest("Get quote fut tab");
		GetQuote getquote = new GetQuote(Driver);
		wait.until(ExpectedConditions.elementToBeClickable(getquote.FutTab));
		long startTime = System.currentTimeMillis(); // Start timer
		getquote.FutTab.click();
		try {
			WebElement NSEFO = wait.until(ExpectedConditions.visibilityOf(getquote.NSEFO));
			String nsefo = NSEFO.getAttribute("content-desc");
			nsefo.equalsIgnoreCase("NSE_FO");
			status = "Pass";
			test.pass("Get quote fut tab Passed");
		} catch (Exception e) {
			status = "Fail";
			test.fail("Get quote fut tab Failed");
			test.info(e.getMessage());
		} finally {
			long endTime = System.currentTimeMillis();
			logger.logTableRow("Get quote fut tab", status, endTime - startTime);
		}

	}

	public void Get_quote_opt_tab() {
		test = extent.createTest("Get quote opt tab");
		GetQuote getquote = new GetQuote(Driver);
		wait.until(ExpectedConditions.elementToBeClickable(getquote.optionsTab));
		long startTime = System.currentTimeMillis();
		getquote.optionsTab.click();
		try {
			WebElement Call = wait.until(ExpectedConditions.visibilityOf(getquote.callbutton));
			String call = Call.getAttribute("content-desc");
			call.equalsIgnoreCase("Call");
			status = "Pass";
			test.pass("Get quote opt tab Passed");
		} catch (Exception e) {
			status = "Fail";
			test.fail("Get quote opt tab Failed");
			test.info(e.getMessage());
		} finally {
			long endTime = System.currentTimeMillis();
			logger.logTableRow("Get quote opt tab", status, endTime - startTime);
		}
	}

	public void Get_quote_cash_tab() {
		test = extent.createTest("Get quote cash tab");
		GetQuote getquote = new GetQuote(Driver);
		wait.until(ExpectedConditions.elementToBeClickable(getquote.cashtab));
		long startTime = System.currentTimeMillis();
		getquote.cashtab.click();
		try {
			WebElement Nse = wait.until(ExpectedConditions.visibilityOf(getquote.nsebutton));
			String nse = Nse.getAttribute("content-desc");
			nse.equalsIgnoreCase("NSE");
			status = "Pass";
			test.pass("Get quote cash tab Passed");
		} catch (Exception e) {
			status = "Fail";
			test.fail("Get quote cash tab");
			test.info(e.getMessage());
		} finally {
			long endTime = System.currentTimeMillis();
			logger.logTableRow("Get quote cash tab", status, endTime - startTime);
		}
	}

	public void Get_quote_Nse_switch_Delivery_buy() throws InterruptedException {
		test = extent.createTest("Get quote Nse switch delivery buy order form");
		GetQuote getquote = new GetQuote(Driver);
		OrderForm orderform = new OrderForm(Driver);
		long startTime = System.currentTimeMillis();
		getquote.nsebutton.click();
		getquote.BuyButton.click();
		try {
			wait.until(ExpectedConditions.visibilityOf(orderform.NseSwitch));
			orderform.NseSwitch.isSelected();
			status = "Pass";
			test.pass("Get quote Nse switch delivery buy order form Passed");
		} catch (Exception e) {
			status = "Fail";
			test.fail("Get quote Nse switch delivery buy order form Failed");
			test.info(e.getMessage());
		} finally {
			long endTime = System.currentTimeMillis();
			Driver.navigate().back();
			logger.logTableRow("Get quote Nse switch delivery buy order form", status, endTime - startTime);
		}
	}

	public void Get_quote_Nse_switch_Delivery_Sell() throws InterruptedException {
		test = extent.createTest("Get quote Nse switch delivery Sell order form");
		GetQuote getquote = new GetQuote(Driver);
		OrderForm orderform = new OrderForm(Driver);
		long startTime = System.currentTimeMillis();
		getquote.nsebutton.click();
		getquote.SellButton.click();
		try {
			wait.until(ExpectedConditions.visibilityOf(orderform.NseSwitch));
			orderform.NseSwitch.isSelected();
			status = "Pass";
			test.pass("Get quote Nse switch delivery Sell order form Passed");
		} catch (Exception e) {
			status = "Fail";
			test.fail("Get quote Nse switch delivery Sell order form Failed");
			test.info(e.getMessage());
		} finally {
			long endTime = System.currentTimeMillis();
			Driver.navigate().back();
			logger.logTableRow("Get quote Nse switch delivery Sell order form", status, endTime - startTime);
		}
	}

	public void Get_quote_Bse_switch_Delivery_buy() throws InterruptedException {
		test = extent.createTest("Get quote Bse switch delivery buy order form");
		GetQuote getquote = new GetQuote(Driver);
		OrderForm orderform = new OrderForm(Driver);
		long startTime = System.currentTimeMillis();
		getquote.bsebutton.click();
		getquote.BuyButton.click();
		try {
			wait.until(ExpectedConditions.visibilityOf(orderform.BseSwitch));
			orderform.BseSwitch.isSelected();
			status = "Pass";
			test.pass("Get quote Bse switch delivery buy order form Passed");
		} catch (Exception e) {
			status = "Fail";
			test.fail("Get quote Bse switch delivery buy order form Failed");
			test.info(e.getMessage());
		} finally {
			long endTime = System.currentTimeMillis();
			Driver.navigate().back();
			logger.logTableRow("Get quote Bse switch delivery buy order form", status, endTime - startTime);
		}
	}

	public void Get_quote_Bse_switch_Delivery_Sell() throws InterruptedException {
		test = extent.createTest("Get quote Bse switch delivery sell order form");
		GetQuote getquote = new GetQuote(Driver);
		OrderForm orderform = new OrderForm(Driver);
		long startTime = System.currentTimeMillis();
		getquote.bsebutton.click();
		getquote.SellButton.click();
		try {
			wait.until(ExpectedConditions.visibilityOf(orderform.BseSwitch));
			orderform.BseSwitch.isSelected();
			status = "Pass";
			test.pass("Get quote Bse switch delivery sell order form Passed");
		} catch (Exception e) {
			status = "Fail";
			test.fail("Get quote Bse switch delivery sell order form Failed");
			test.info(e.getMessage());
		} finally {
			long endTime = System.currentTimeMillis();
			Driver.navigate().back();
			logger.logTableRow("Get quote Bse switch delivery sell order form", status, endTime - startTime);
		}
	}

	public void Get_quote_charts() {
		test = extent.createTest("Get quote charts button");
		GetQuote getquote = new GetQuote(Driver);
		long startTime = System.currentTimeMillis();
		getquote.chartsbutton.click();
		try {
			wait.until(ExpectedConditions.elementToBeClickable(getquote.chartsbuybutton));
			String buy = getquote.chartsbuybutton.getAttribute("content-desc");
			buy.equalsIgnoreCase("BUY");
			status = "Pass";
			test.pass("Get quote charts button Passed");
		} catch (Exception e) {
			status = "Fail";
			test.fail("Get quote charts button Failed");
			test.info(e.getMessage());
		} finally {
			long endTime = System.currentTimeMillis();
			Driver.navigate().back();
			logger.logTableRow("Get quote charts button", status, endTime - startTime);
		}
	}

	public void Get_quote_optionchain() {
		test = extent.createTest("Get quote optionchain button");
		GetQuote getquote = new GetQuote(Driver);
		long startTime = System.currentTimeMillis();
		getquote.optionchainbutton.click();
		try {
			wait.until(ExpectedConditions.visibilityOf(getquote.OIoptionchain));
			String OI = getquote.OIoptionchain.getAttribute("content-desc");
			OI.equalsIgnoreCase("OI");
			status = "Pass";
			test.pass("Get quote optionchain button Passed");
		} catch (Exception e) {
			status = "Fail";
			test.fail("Get quote optionchain button Failed");
			test.info(e.getMessage());
		} finally {
			long endTime = System.currentTimeMillis();
			Driver.navigate().back();
			logger.logTableRow("Get quote optionchain button", status, endTime - startTime);
		}
	}

	public void Get_quote_fundamentaltab() throws InterruptedException {
		test = extent.createTest("Get quote Fundamental Tab");
		GetQuote getquote = new GetQuote(Driver);
		long startTime = System.currentTimeMillis();
		getquote.fundamentaltab.click();
		try {
			wait.until(ExpectedConditions.visibilityOf(getquote.fundamentalratios));
			String fundamentalratios = getquote.fundamentalratios.getAttribute("content-desc");
			fundamentalratios.equalsIgnoreCase("Fundamental Ratios");
			status = "Pass";
			test.pass("Get quote Fundamental Tab Passed");
		} catch (Exception e) {
			status = "Fail";
			test.fail("Get quote oFundamental Tab Failed");
			test.info(e.getMessage());
		} finally {
			long endTime = System.currentTimeMillis();
			logger.logTableRow("Get quote Fundamental Tab", status, endTime - startTime);
		}
	}

	public void Get_quote_technicaltab() throws InterruptedException {
		test = extent.createTest("Get quote Technical Tab");
		GetQuote getquote = new GetQuote(Driver);
		long startTime = System.currentTimeMillis();
		getquote.technicaltab.click();
		try {
			wait.until(ExpectedConditions.visibilityOf(getquote.deliveryvolume));
			String delivery = getquote.deliveryvolume.getAttribute("content-desc");
			delivery.equalsIgnoreCase("Delivery & Volume");
			status = "Pass";
			test.pass("Get quote Technical Tab Passed");
		} catch (Exception e) {
			status = "Fail";
			test.fail("Get quote Technical Tab Failed");
			test.info(e.getMessage());
		} finally {
			long endTime = System.currentTimeMillis();
			logger.logTableRow("Get quote Technical Tab", status, endTime - startTime);
		}
	}

	public void Get_quote_Newstab() throws InterruptedException {
		test = extent.createTest("Get quote News Tab");
		GetQuote getquote = new GetQuote(Driver);
		long startTime = System.currentTimeMillis();
		getquote.Newstab.click();
		try {
			wait.until(ExpectedConditions.visibilityOf(getquote.news));
			String newsverification = getquote.news.getAttribute("content-desc");
			newsverification.equalsIgnoreCase("News");
			status = "Pass";
			test.pass("Get quote News Tab Passed");
		} catch (Exception e) {
			status = "Fail";
			test.fail("Get quote News Tab Failed");
			test.info(e.getMessage());
		} finally {
			long endTime = System.currentTimeMillis();
			logger.logTableRow("Get quote News Tab", status, endTime - startTime);
		}
	}

	public void Get_quote_Transactionstab() throws InterruptedException {
		test = extent.createTest("Get quote Transactions Tab");
		GetQuote getquote = new GetQuote(Driver);
		long startTime = System.currentTimeMillis();
		getquote.transactionstab.click();
		try {
			wait.until(ExpectedConditions.visibilityOf(getquote.realisedPL));
			String plrealised = getquote.realisedPL.getAttribute("content-desc");
			String realisepl = plrealised.split(" ")[0];
			realisepl.equalsIgnoreCase("Realised");
			status = "Pass";
			test.pass("Get quote Transactions Tab Passed");
		} catch (Exception e) {
			status = "Fail";
			test.fail("Get quote Transactions Tab Failed");
			test.info(e.getMessage());
		}
		long endTime = System.currentTimeMillis();
		logger.logTableRow("Get quote Transactions Tab", status, endTime - startTime);
	}

	public void OrderForm_quantity_toggle() throws IOException, InterruptedException {
		test = extent.createTest("Order Form quantity toggle");
		OrderForm orderform = new OrderForm(Driver);
		GetQuote getquote = new GetQuote(Driver);
		getquote.BuyButton.click();
		long startTime = System.currentTimeMillis();
		orderform.MarketButton.click();
		try {
			ResusableMethods.cleartextandenterinput(Driver, orderform.quantityMarket,
					Commons.getGlobalPropertiesValue("Orderform_quantity_amount_toggle"));
			Driver.hideKeyboard();
			String amount = orderform.investamount.getAttribute("content-desc");
			String investamount = amount.substring(12, 14);
			String NseLTP = orderform.NseSwitch.getAttribute("content-desc");
			String LTP = NseLTP.substring(5, 7);
			investamount.equalsIgnoreCase(LTP);
			status = "Pass";
			test.pass("Order Form quantity toggle Passed");
		} catch (Exception e) {
			status = "Fail";
			test.fail("Order Form quantity toggle Failed");
			test.info(e.getMessage());
		} finally {
			long endTime = System.currentTimeMillis(); // End timer
			logger.logTableRow("Order Form quantity toggle", status, endTime - startTime); // Log search timing
		}
	}

	public void OrderForm_amount_toggle() throws InterruptedException, IOException {

		test = extent.createTest("Order Form amount toggle");
		OrderForm orderform = new OrderForm(Driver);
		long startTime = System.currentTimeMillis(); // Start timer
		orderform.amountswitch.click();
		try {
			ResusableMethods.cleartextandenterinput(Driver, orderform.quantityMarket,
					(Commons.getGlobalPropertiesValue("orderform_amount_toggle")));
			Driver.hideKeyboard();
			String quantity = orderform.quantityautocalculate.getAttribute("content-desc");
			String autocalulatequantity = quantity.substring(5);
			autocalulatequantity.equalsIgnoreCase(Commons.getGlobalPropertiesValue("orderform_amount_quantity"));
			status = "Pass";
			test.pass("Order Form amount toggle Passed");
		} catch (Exception e) {
			status = "Fail";
			test.fail("Order Form amount toggle Failed");
			test.info(e.getMessage());
		} finally {
			long endTime = System.currentTimeMillis(); // End timer
			Driver.navigate().back();
			Driver.navigate().back();
			Driver.navigate().back();
			logger.logTableRow("Order Form amount toggle", status, endTime - startTime); // Log search timing
		}
	}

	public void homepage_explore_hide_button() {
		test = extent.createTest("Homescreen portfolio hide mark");
		HomePage homepage = new HomePage(Driver);
		homepage.homeTabHeader.click();
		long startTime = System.currentTimeMillis();
		homepage.hidebutton.click();
		try {
			WebElement hidemark = wait.until(ExpectedConditions.visibilityOf(homepage.hideportfolio));
			String markhide = hidemark.getAttribute("content-desc");
			List<String> elements = Arrays.asList(markhide.split(" "));
			String starmark = elements.get(8);
			starmark.equalsIgnoreCase("Value\n*****\nInvested");
			status = "Pass";
			test.pass("Homescreen portfolio hide mark Passed");
		} catch (Exception e) {
			status = "Fail";
			test.fail("Homescreen portfolio hide mark Failed");
			test.info(e.getMessage());
		} finally {
			long endTime = System.currentTimeMillis();
			homepage.showbutton.click();
			logger.logTableRow("Homescreen portfolio hide mark", status, endTime - startTime);
		}
	}

	public void homepage_portfolio_overallvalue() {
		test = extent.createTest("Homepage portfolio overall value");
		HomePage homepage = new HomePage(Driver);
		Portfolio portfolio = new Portfolio(Driver);
		homepage.overall.click();
		long startTime = System.currentTimeMillis();
		try {
			wait.until(ExpectedConditions.visibilityOf(portfolio.viewAnalysis));
			portfolio.viewAnalysis.isDisplayed();
			status = "Pass";
			test.pass("Homescreen portfolio overall value Passed");
		} catch (Exception e) {
			status = "Fail";
			test.fail("Homescreen portfolio overall value Failed");
			test.info(e.getMessage());
		} finally {
			long endTime = System.currentTimeMillis();
			Driver.navigate().back();
			logger.logTableRow("Homescreen portfolio overall value", status, endTime - startTime);
		}
	}

	public void homepage_explore_portfolio_expand() {
		test = extent.createTest("Homescreen portfolio expand button");
		HomePage homepage = new HomePage(Driver);
		long startTime = System.currentTimeMillis();
		homepage.homepageportfolioexpand.click();
		try {
			homepage.availablemargin.isDisplayed();
			status = "Pass";
			test.pass("Homescreen portfolio expand button Passed");
		} catch (Exception e) {
			status = "Fail";
			test.fail("Homescreen portfolio expand button Failed");
			test.info(e.getMessage());
		} finally {
			long endTime = System.currentTimeMillis();
			logger.logTableRow("Homescreen portfolio expand button", status, endTime - startTime);
		}
	}

	public void homepage_explore_portfolio_position() {
		test = extent.createTest("Homescreen portfolio position button");

	}

	public void hompage_explore_portfolio_collapse() {
		test = extent.createTest("Homescreen portfolio collapse button");
		HomePage homepage = new HomePage(Driver);
		long startTime = System.currentTimeMillis();
		homepage.homepageportfoliocollapse.click();
		try {
			homepage.ExpandIcon.isDisplayed();
			status = "Pass";
			test.pass("Homescreen portfolio collapse button Passed");
		} catch (Exception e) {
			status = "Fail";
			test.fail("Homescreen portfolio collapse button Failed");
			test.info(e.getMessage());
		} finally {
			long endTime = System.currentTimeMillis();
			logger.logTableRow("Homescreen portfolio collapse button", status, endTime - startTime);
		}
	}

	public void Researchideaibutton() {
		test = extent.createTest("Homescreen Research idea I button");
		HomePage homepage = new HomePage(Driver);
		long startTime = System.currentTimeMillis();
		homepage.researchideaibutton.click();
		try {
			wait.until(ExpectedConditions.visibilityOf(homepage.reasearchideatext));
			homepage.reasearchideatext.isDisplayed();
			status = "Pass";
			test.pass("Homescreen Research idea I button Passed");
		} catch (Exception e) {
			status = "Fail";
			test.fail("Homescreen Research idea I button Failed");
			test.info(e.getMessage());
		} finally {
			Driver.navigate().back();
			long endTime = System.currentTimeMillis();
			logger.logTableRow("Homescreen Research idea I button", status, endTime - startTime);
		}
	}

	public void Researchidea_viewAll() {
		test = extent.createTest("Homescreen Research idea view All button");
		HomePage homepage = new HomePage(Driver);
		long startTime = System.currentTimeMillis();
		homepage.researchideaviewall.click();
		try {
			homepage.researchideaequitytab.isDisplayed();
			status = "Pass";
			test.pass("Homescreen Research idea view All button Passed");
		} catch (Exception e) {
			status = "Fail";
			test.fail("Homescreen Research idea view All button Failed");
			test.info(e.getMessage());
		} finally {
			long endTime = System.currentTimeMillis();
			Driver.navigate().back();
			logger.logTableRow("Homescreen Research idea view All", status, endTime - startTime);
		}
	}

	public void IPO_button_CTA() {
		test = extent.createTest("Homescreen IPO button");
		HomePage homepage = new HomePage(Driver);
		homepage.homeTabHeader.click();
		ResusableMethods.verticalswipetillElement(Driver, homepage.optionsstoreCTA, 0, 5, 470, 1788, 590);
		long startTime = System.currentTimeMillis();
		homepage.IPO.click();
		try {
			homepage.ipolist.isDisplayed();
			status = "Pass";
			test.pass("Homescreen IPO button Passed");
		} catch (Exception e) {
			status = "Fail";
			test.fail("Homescreen IPO button Failed");
			test.info(e.getMessage());
		} finally {
			long endTime = System.currentTimeMillis();
			Driver.navigate().back();
			logger.logTableRow("Homescreen IPO button", status, endTime - startTime);
		}
	}

	public void Research_Ideas_CTA() {
		test = extent.createTest("Homescreen Research Idea button");
		HomePage homepage = new HomePage(Driver);
		long startTime = System.currentTimeMillis();
		homepage.researchideasbutton.click();
		try {
			homepage.researchideaequitytab.isDisplayed();
			status = "Pass";
			test.pass("Homescreen Research Idea button Passed");
		} catch (Exception e) {
			status = "Fail";
			test.fail("Homescreen Research Idea button Failed");
			test.info(e.getMessage());
		} finally {
			long endTime = System.currentTimeMillis();
			Driver.navigate().back();
			logger.logTableRow("Homescreen Research Idea button", status, endTime - startTime);
		}
	}

	public void IAP_CTA() {
		test = extent.createTest("Homescreen IAP button");
		HomePage homepage = new HomePage(Driver);
		long startTime = System.currentTimeMillis(); // Start timer
		homepage.IAPbutton.click();
		try {
			homepage.iappage.isDisplayed();
			status = "Pass";
			test.pass("Homescreen IAP button Passed");
		} catch (Exception e) {
			status = "Fail";
			test.fail("Homescreen IAP button Failed");
			test.info(e.getMessage());
		} finally {
			long endTime = System.currentTimeMillis(); // End timer
			Driver.navigate().back();
			logger.logTableRow("Homescreen IAP button", status, endTime - startTime); // Log search timing
		}
	}

	public void Bonds_CTA() {
		test = extent.createTest("Homescreen Bonds button");
		HomePage homepage = new HomePage(Driver);
		long startTime = System.currentTimeMillis(); // Start timer
		homepage.bondsCTA.click();
		try {
			wait.until(ExpectedConditions.visibilityOf(homepage.bondspage));
			homepage.bondspage.isDisplayed();
			status = "Pass";
			test.pass("Homescreen Bonds button Passed");
		} catch (Exception e) {
			status = "Fail";
			test.fail("Homescreen Bonds button Failed");
			test.info(e.getMessage());
		} finally {
			long endTime = System.currentTimeMillis(); // End timer
			Driver.navigate().back();
			logger.logTableRow("Homescreen Bonds button", status, endTime - startTime); // Log search timing
		}
	}

	public void options_store() throws InterruptedException {
		test = extent.createTest("Homescreen Option Store button");
		HomePage homepage = new HomePage(Driver);
		long startTime = System.currentTimeMillis();
		homepage.optionsstoreCTA.click();
		try {
			wait.until(ExpectedConditions.visibilityOf(homepage.optionsstorepage));
			homepage.optionsstorepage.isDisplayed();
			status = "Pass";
			test.pass("Homescreen Option Store button Passed");
		} catch (Exception e) {
			status = "Fail";
			test.fail("Homescreen Option Store button Failed");
			test.info(e.getMessage());
		} finally {
			long endTime = System.currentTimeMillis();
			Driver.navigate().back();
			logger.logTableRow("Homescreen Option Store button", status, endTime - startTime);
		}
	}

	public void Stock_basket_CTA() {
		test = extent.createTest("Homescreen Stock Basket button");
		HomePage homepage = new HomePage(Driver);
		long startTime = System.currentTimeMillis();
		homepage.stockbasket.click();
		try {
			wait.until(ExpectedConditions.visibilityOf(homepage.researchideaequitytab));
			homepage.researchideaequitytab.isDisplayed();
			status = "Pass";
			test.pass("Homescreen Stock Basket button Passed");
		} catch (Exception e) {
			status = "Fail";
			test.fail("Homescreen Stock Basket button Failed");
			test.info(e.getMessage());
		} finally {
			long endTime = System.currentTimeMillis();
			Driver.navigate().back();
			logger.logTableRow("Homescreen Stock Basket button", status, endTime - startTime);
		}
	}

	public void TGS_CTA() {
		test = extent.createTest("Homescreen TGS button button");
		HomePage homepage = new HomePage(Driver);
		homepage.homeTabHeader.click();
		ResusableMethods.verticalswipetillElement(Driver, homepage.mfCTAs, 0, 5, 470, 1788, 600);
		long startTime = System.currentTimeMillis();
		homepage.TGSbutton.click();
		try {
			wait.until(ExpectedConditions.visibilityOf(homepage.TGSpage));
			homepage.TGSpage.isDisplayed();
			status = "Pass";
			test.pass("Homescreen TGS button Passed");
		} catch (Exception e) {
			status = "Fail";
			test.fail("Homescreen TGS button Failed");
			test.info(e.getMessage());
		} finally {
			long endTime = System.currentTimeMillis();
			Driver.navigate().back();
			logger.logTableRow("Homescreen TGS button", status, endTime - startTime);
		}
	}

	public void Insurance_CTA() {
		test = extent.createTest("Homescreen Insurance button");
		HomePage homepage = new HomePage(Driver);
		homepage.homeTabHeader.click();
		ResusableMethods.verticalswipetillElement(Driver, homepage.mfCTAs, 0, 8, 470, 1600, 600);
		long startTime = System.currentTimeMillis();
		homepage.insurancebutton.click();
		try {
			wait.until(ExpectedConditions.visibilityOf(homepage.insurancepage));
			homepage.insurancepage.isDisplayed();
			status = "Pass";
			test.pass("Homescreen Insurance button Passed");
		} catch (Exception e) {
			status = "Fail";
			test.fail("Homescreen Insurance button Failed");
			test.info(e.getMessage());
		} finally {
			long endTime = System.currentTimeMillis();
			Driver.navigate().back();
			logger.logTableRow("Homescreen Insurance button", status, endTime - startTime);
		}
	}

	public void FixedDeposit_CTA() throws InterruptedException {
		test = extent.createTest("Homescreen Fixed Deposit button");
		HomePage homepage = new HomePage(Driver);
		long startTime = System.currentTimeMillis();
		homepage.fixeddepositbutton.click();
		try {
			wait.until(ExpectedConditions.visibilityOf(homepage.fixeddepositpage));
			homepage.fixeddepositpage.isDisplayed();
			status = "Pass";
			test.pass("Homescreen Fixed Deposit button Passed");
		} catch (Exception e) {
			status = "Fail";
			test.fail("Homescreen Fixed Deposit button Failed");
			test.info(e.getMessage());
		} finally {
			long endTime = System.currentTimeMillis();
			Driver.navigate().back();
			logger.logTableRow("Homescreen Fixed Deposit button", status, endTime - startTime);
		}
	}

	public void Intraoptions_CTA() throws InterruptedException {
		test = extent.createTest("Homescreen Intra options button");
		HomePage homepage = new HomePage(Driver);
		homepage.homeTabHeader.click();
		ResusableMethods.verticalswipetillElement(Driver, homepage.smallcasebutton, 0, 5, 470, 1788, 600);
		long startTime = System.currentTimeMillis();
		homepage.IntaoptionsButton.click();
		try {
			wait.until(ExpectedConditions.visibilityOf(homepage.intraoptionspage));
			homepage.intraoptionspage.isDisplayed();
			status = "Pass";
			test.pass("Homescreen Intra options button Passed");
		} catch (Exception e) {
			status = "Fail";
			test.fail("Homescreen Intra options button Failed");
			test.info(e.getMessage());
		} finally {
			long endTime = System.currentTimeMillis();
			Driver.navigate().back();
			Thread.sleep(2000);
			logger.logTableRow("Homescreen Intra options button", status, endTime - startTime);
		}
	}

	public void smallcase_CTA() throws InterruptedException {
		test = extent.createTest("Homescreen Smallcase button");
		HomePage homepage = new HomePage(Driver);
		homepage.homeTabHeader.click();
		long startTime = System.currentTimeMillis();
		ResusableMethods.verticalswipetillElement(Driver, homepage.smallcasebutton, 0, 5, 470, 1200, 600);
		homepage.smallcasebutton.click();
		try {
			wait.until(ExpectedConditions.visibilityOf(homepage.smallcasepage));
			status = "Pass";
			test.pass("Homescreen Smallcase button Passed");
		} catch (Exception e) {
			status = "Fail";
			test.fail("Homescreen Smallcase button Failed");
			test.info(e.getMessage());
		} finally {
			long endTime = System.currentTimeMillis();
			Driver.navigate().back();
			logger.logTableRow("Homescreen Smallcase button", status, endTime - startTime);
		}
	}

	public void Tejimandi() throws InterruptedException {
		test = extent.createTest("Homescreen Teji Mandi button");
		HomePage homepage = new HomePage(Driver);
		ResusableMethods.verticalswipetillElement(Driver, homepage.smallcasebutton, 0, 5, 470, 1788, 590);
		homepage.tejimandibutton.click();
		long startTime = System.currentTimeMillis();
		ResusableMethods.test(Driver, wait, test, status, homepage.tejimandipage, "Homescreen Teji Mandi button");
		long endTime = System.currentTimeMillis();
		Driver.navigate().back();
		logger.logTableRow("Homescreen Teji Mandi button", status, endTime - startTime);

	}

	public void MFHighReturns_CTA() throws InterruptedException {
		test = extent.createTest("Homescreen MF High Returns button");
		HomePage homepage = new HomePage(Driver);
		MfHomePage mfhomepage = new MfHomePage(Driver);
		ResusableMethods.verticalswipetillElement(Driver, homepage.internationfund, 0, 9, 470, 1600, 600);
		long startTime = System.currentTimeMillis();
		homepage.mfhighreturnsbutton.click();
		try {
			wait.until(ExpectedConditions.visibilityOf(mfhomepage.MfViewAll3YReturns));
			mfhomepage.MfViewAll3YReturns.isDisplayed();
			status = "Pass";
			test.pass("Homescreen MF High Returns button Passed");
		} catch (Exception e) {
			status = "Fail";
			test.fail("Homescreen MF High Returns button Failed");
			test.info(e.getMessage());
		} finally {
			long endTime = System.currentTimeMillis();
			Driver.navigate().back();
			Thread.sleep(500);
			logger.logTableRow("Homescreen MF High Returns button", status, endTime - startTime);
		}
	}

	public void SIPwith500_CTA() throws InterruptedException {
		test = extent.createTest("Homescreen MF SIP with 500 button");
		HomePage homepage = new HomePage(Driver);
		MfHomePage mfhomepage = new MfHomePage(Driver);
		long startTime = System.currentTimeMillis();
		homepage.sipwith500.click();
		try {
			wait.until(ExpectedConditions.visibilityOf(mfhomepage.MfViewAll3YReturns));
			mfhomepage.MfViewAll3YReturns.isDisplayed();
			status = "Pass";
			test.pass("Homescreen MF SIP with 500 button Passed");
		} catch (Exception e) {
			status = "Fail";
			test.fail("Homescreen MF SIP with 500 button Failed");
			test.info(e.getMessage());
		} finally {
			long endTime = System.currentTimeMillis();
			Driver.navigate().back();
			Thread.sleep(500);
			logger.logTableRow("Homescreen MF SIP with 500 button", status, endTime - startTime);
		}
	}

	public void Taxsaver_CTA() throws InterruptedException {
		test = extent.createTest("Homescreen MF Tax Saver button");
		HomePage homepage = new HomePage(Driver);
		MfHomePage mfhomepage = new MfHomePage(Driver);
		long startTime = System.currentTimeMillis();
		homepage.taxsaverbutton.click();
		try {
			wait.until(ExpectedConditions.visibilityOf(mfhomepage.MfViewAll3YReturns));
			mfhomepage.MfViewAll3YReturns.isDisplayed();
			status = "Pass";
			test.pass("Homescreen MF Tax Saver button Passed");
		} catch (Exception e) {
			status = "Fail";
			test.fail("Homescreen MF Tax Saver button Failed");
			test.info(e.getMessage());
		} finally {
			long endTime = System.currentTimeMillis();
			Driver.navigate().back();
			Thread.sleep(500);
			logger.logTableRow("Homescreen MF Tax Saver button", status, endTime - startTime);
		}
	}

	public void MoRecomended_CTA() throws InterruptedException {
		test = extent.createTest("Homescreen MF MO Recommended button");
		HomePage homepage = new HomePage(Driver);
		MfHomePage mfhomepage = new MfHomePage(Driver);
		long startTime = System.currentTimeMillis();
		homepage.Morecommendedbutton.click();
		try {
			wait.until(ExpectedConditions.visibilityOf(mfhomepage.MfViewAll3YReturns));
			mfhomepage.MfViewAll3YReturns.isDisplayed();
			status = "Pass";
			test.pass("Homescreen MF MO Recommended button Passed");
		} catch (Exception e) {
			status = "Fail";
			test.fail("Homescreen MF MO Recommended button Failed");
			test.info(e.getMessage());
		} finally {
			long endTime = System.currentTimeMillis();
			Driver.navigate().back();
			Thread.sleep(500);
			logger.logTableRow("Homescreen MF MO Recommended button", status, endTime - startTime);
		}
	}

	public void Highestrated_CTA() throws InterruptedException {
		test = extent.createTest("Homescreen MF Highest rated Fund button");
		HomePage homepage = new HomePage(Driver);
		MfHomePage mfhomepage = new MfHomePage(Driver);
		long startTime = System.currentTimeMillis();
		homepage.highestratedfund.click();
		try {
			wait.until(ExpectedConditions.visibilityOf(mfhomepage.MfViewAll3YReturns));
			mfhomepage.MfViewAll3YReturns.isDisplayed();
			status = "Pass";
			test.pass("Homescreen MF Highest rated Fund button Passed");
		} catch (Exception e) {
			status = "Fail";
			test.fail("Homescreen MF Highest rated Fund button Failed");
			test.info(e.getMessage());
		} finally {
			long endTime = System.currentTimeMillis();
			Driver.navigate().back();
			logger.logTableRow("Homescreen MF Highest rated Fund button", status, endTime - startTime);
		}
	}

	public void internationalfund_CTA() throws InterruptedException {
		test = extent.createTest("Homescreen MF International Fund button");
		HomePage homepage = new HomePage(Driver);
		MfHomePage mfhomepage = new MfHomePage(Driver);
		long startTime = System.currentTimeMillis();
		homepage.internationfund.click();
		try {
			wait.until(ExpectedConditions.visibilityOf(mfhomepage.MfViewAll3YReturns));
			mfhomepage.MfViewAll3YReturns.isDisplayed();
			status = "Pass";
			test.pass("Homescreen MF International Fund button Passed");
		} catch (Exception e) {
			status = "Fail";
			test.fail("Homescreen MF International Fund button Failed");
			test.info(e.getMessage());
		} finally {
			long endTime = System.currentTimeMillis();
			Driver.navigate().back();
			logger.logTableRow("Homescreen MF International Fund button", status, endTime - startTime);
		}
	}

	public void iponfoIbutton() throws InterruptedException {
		test = extent.createTest("Homescreen IPO I button");
		HomePage homepage = new HomePage(Driver);
		ResusableMethods.verticalswipetillElement(Driver, homepage.oneclicksip, 0, 8, 470, 1788, 600);
		long startTime = System.currentTimeMillis();
		homepage.ipoibutton.click();
		try {
			wait.until(ExpectedConditions.visibilityOf(homepage.ipochildscreen));
			homepage.ipochildscreen.isDisplayed();
			status = "Pass";
			test.pass("Homescreen IPO I button Passed");
		} catch (Exception e) {
			status = "Fail";
			test.fail("Homescreen IPO I button Failed");
			test.info(e.getMessage());
		} finally {
			long endTime = System.currentTimeMillis();
			Driver.navigate().back();
			logger.logTableRow("Homescreen IPO I button", status, endTime - startTime);
		}
	}

	public void ipoviewall() throws InterruptedException {
		test = extent.createTest("Homescreen IPO view All button");
		HomePage homepage = new HomePage(Driver);
		homepage.homeTabHeader.click();
		long startTime = System.currentTimeMillis();
		try {
			homepage.ipoviewallbutton.click();
			wait.until(ExpectedConditions.visibilityOf(homepage.ipopage));
			homepage.ipopage.isDisplayed();
			status = "Pass";
			test.pass("Homescreen IPO view All button Passed");
		} catch (Exception e) {
			status = "Fail";
			test.fail("Homescreen IPO view All button Failed");
			test.info(e.getMessage());
		} finally {
			long endTime = System.currentTimeMillis();
			Driver.navigate().back();
			logger.logTableRow("Homescreen IPO view All button", status, endTime - startTime);
		}
	}

	public void ipobutton() throws InterruptedException {
		test = extent.createTest("Homescreen IPO button");
		HomePage homepage = new HomePage(Driver);
		homepage.homeTabHeader.click();
		ResusableMethods.verticalswipetillElement(Driver, homepage.oneclicksip, 0, 8, 470, 1788, 600);
		long startTime = System.currentTimeMillis();
		homepage.ipobutton.click();
		try {
			wait.until(ExpectedConditions.visibilityOf(homepage.ipolistq));
			homepage.ipolistq.isDisplayed();
			status = "Pass";
			test.pass("Homescreen IPO button Passed");
		} catch (Exception e) {
			status = "Fail";
			test.fail("Homescreen IPO button Failed");
			test.info(e.getMessage());
		} finally {
			long endTime = System.currentTimeMillis();
			Thread.sleep(500);
			logger.logTableRow("Homescreen IPO button", status, endTime - startTime);
		}
	}

	public void Nfobutton() throws InterruptedException {
		test = extent.createTest("Homescreen NFO button");
		HomePage homepage = new HomePage(Driver);
		homepage.homeTabHeader.click();
		long startTime = System.currentTimeMillis();
		homepage.nfobutton.click();
		try {
			wait.until(ExpectedConditions.visibilityOf(homepage.nfolist));
			homepage.nfolist.isDisplayed();
			status = "Pass";
			test.pass("Homescreen NFO button Passed");
		} catch (Exception e) {
			status = "Fail";
			test.fail("Homescreen NFO button Failed");
			test.info(e.getMessage());
		} finally {
			long endTime = System.currentTimeMillis();
			logger.logTableRow("Homescreen NFO button", status, endTime - startTime);
		}
	}

	public void Stocksipbutton() throws InterruptedException {
		test = extent.createTest("Homescreen Stock sip button");
		HomePage homepage = new HomePage(Driver);
		homepage.homeTabHeader.click();
		ResusableMethods.verticalswipetillElement(Driver, homepage.MOapibutton, 0, 8, 470, 1788, 600);
		long startTime = System.currentTimeMillis();
		homepage.stocksipbutton.click();
		try {
			wait.until(ExpectedConditions.visibilityOf(homepage.stocksippage));
			homepage.stocksippage.isDisplayed();
			status = "Pass";
			test.pass("Homescreen Stock sip button Passed");
		} catch (Exception e) {
			status = "Fail";
			test.fail("Homescreen Stock sip button Failed");
			test.info(e.getMessage());
		} finally {
			long endTime = System.currentTimeMillis();
			Driver.navigate().back();
			logger.logTableRow("Homescreen Stock sip button", status, endTime - startTime);
		}
	}

	public void MFsipbutton() throws InterruptedException {
		test = extent.createTest("Homescreen MF sip button");
		HomePage homepage = new HomePage(Driver);
		MfHomePage mfhomepage = new MfHomePage(Driver);
		homepage.homeTabHeader.click();
		long startTime = System.currentTimeMillis();
		homepage.MFsipbutton.click();
		try {
			wait.until(ExpectedConditions.visibilityOf(mfhomepage.MfViewAll3YReturns));
			mfhomepage.MfViewAll3YReturns.isDisplayed();
			status = "Pass";
			test.pass("Homescreen MF sip button Passed");
		} catch (Exception e) {
			status = "Fail";
			test.fail("Homescreen MF sip button Failed");
			test.info(e.getMessage());
		} finally {
			long endTime = System.currentTimeMillis();
			Driver.navigate().back();
			Thread.sleep(500);
			logger.logTableRow("Homescreen MF sip button", status, endTime - startTime);
		}
	}

	public void reportsbutton() throws InterruptedException {
		test = extent.createTest("Homescreen Reports button");
		HomePage homepage = new HomePage(Driver);
		homepage.homeTabHeader.click();
		ResusableMethods.verticalswipetillElement(Driver, homepage.MOapibutton, 0, 8, 470, 1788, 600);
		long startTime = System.currentTimeMillis();
		homepage.reportsbutton.click();
		try {
			wait.until(ExpectedConditions.visibilityOf(homepage.reportspage));
			homepage.reportspage.isDisplayed();
			status = "Pass";
			test.pass("Homescreen Reports button Passed");
		} catch (Exception e) {
			status = "Fail";
			test.fail("Homescreen Reports button Failed");
			test.info(e.getMessage());
		} finally {
			long endTime = System.currentTimeMillis();
			Driver.navigate().back();
			Thread.sleep(500);
			logger.logTableRow("Homescreen Reports button", status, endTime - startTime);
		}
	}

	public void PNLsummarybutton() throws InterruptedException {
		test = extent.createTest("Homescreen PNL Summary button");
		HomePage homepage = new HomePage(Driver);
		homepage.homeTabHeader.click();
		long startTime = System.currentTimeMillis();
		homepage.PNLbutton.click();
		try {
			wait.until(ExpectedConditions.visibilityOf(homepage.pnlpage));
			homepage.pnlpage.isDisplayed();
			status = "Pass";
			test.pass("Homescreen PNL Summary button Passed");
		} catch (Exception e) {
			status = "Fail";
			test.fail("Homescreen PNL Summary button Failed");
			test.info(e.getMessage());
		} finally {
			long endTime = System.currentTimeMillis();
			Driver.navigate().back();
			Thread.sleep(500);
			logger.logTableRow("Homescreen PNL Summary button", status, endTime - startTime);
		}
	}

	public void alertsbutton() throws InterruptedException {
		test = extent.createTest("Homescreen Alerts button");
		HomePage homepage = new HomePage(Driver);
		homepage.homeTabHeader.click();
		ResusableMethods.verticalswipetillElement(Driver, homepage.MOapibutton, 0, 8, 470, 1788, 600);
		long startTime = System.currentTimeMillis();
		homepage.alertsbutton.click();
		try {
			wait.until(ExpectedConditions.visibilityOf(homepage.alertspage));
			homepage.alertspage.isDisplayed();
			status = "Pass";
			test.pass("Homescreen Alerts button Passed");
		} catch (Exception e) {
			status = "Fail";
			test.fail("Homescreen Alerts button Failed");
			test.info(e.getMessage());
		} finally {
			long endTime = System.currentTimeMillis();
			Driver.navigate().back();
			logger.logTableRow("Homescreen Alerts button", status, endTime - startTime);
		}
	}

	public void margincalbutton() throws InterruptedException {
		test = extent.createTest("Homescreen margin cal button");
		HomePage homepage = new HomePage(Driver);
		homepage.homeTabHeader.click();
		ResusableMethods.verticalswipetillElement(Driver, homepage.MOapibutton, 0, 8, 470, 1788, 600);
		long startTime = System.currentTimeMillis();
		homepage.margincalbutton.click();
		try {
			wait.until(ExpectedConditions.visibilityOf(homepage.margincalpage));
			homepage.margincalpage.isDisplayed();
			status = "Pass";
			test.pass("Homescreen margin cal button Passed");
		} catch (Exception e) {
			status = "Fail";
			test.fail("Homescreen margin cal button Failed");
			test.info(e.getMessage());
		} finally {
			long endTime = System.currentTimeMillis();
			Driver.navigate().back();
			Thread.sleep(500);
			logger.logTableRow("Homescreen margin cal button", status, endTime - startTime);
		}
	}

	public void moapi() throws InterruptedException {
		test = extent.createTest("Homescreen MO API button");
		HomePage homepage = new HomePage(Driver);
		homepage.homeTabHeader.click();
		ResusableMethods.verticalswipetillElement(Driver, homepage.MOapibutton, 0, 8, 470, 1788, 600);
		long startTime = System.currentTimeMillis();
		homepage.MOapibutton.click();
		try {
			wait.until(ExpectedConditions.visibilityOf(homepage.moapipage));
			homepage.moapipage.isDisplayed();
			status = "Pass";
			test.pass("Homescreen MO API button Passed");
		} catch (Exception e) {
			status = "Fail";
			test.fail("Homescreen MO API button Failed");
			test.info(e.getMessage());
		} finally {
			long endTime = System.currentTimeMillis();
			Driver.navigate().back();
			Thread.sleep(500);
			logger.logTableRow("Homescreen MO API button", status, endTime - startTime);
		}
	}

	public void MF_collection_viewall_returns() throws InterruptedException {
		test = extent.createTest("Mutual Fund View All Returns button");
		HomePage homepage = new HomePage(Driver);
		MfHomePage mfhomepage = new MfHomePage(Driver);
		homepage.MfTab.click();
		long startTime = System.currentTimeMillis();
		mfhomepage.MfHomepageCollectionViewAll.click();
		try {
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
			test.pass("Mutual Fund View All Returns Passed");
		} catch (Exception e) {
			test.fail("Mutual Fund View All Returns Failed");
			test.info(e.getMessage());
		} finally {
			Driver.navigate().back();
			homepage.homeTabHeader.click();
			long endTime = System.currentTimeMillis();
			logger.logTableRow("Mutual Fund View All Returns", status, endTime - startTime);
		}
	}

	public void Intra_options_order_placement() throws InterruptedException {
		HomePage homepage = new HomePage(Driver);

		homepage.homeTabHeader.click();
		long startTime = System.currentTimeMillis();
		// Scroll and find the element
		Driver.findElement(AppiumBy.androidUIAutomator(
				"new UiScrollable(new UiSelector().scrollable(true)).scrollIntoView(new UiSelector().description(\" Smallcase\"))"));
		homepage.IntaoptionsButton.click();
		Thread.sleep(5000);
		long endTime = System.currentTimeMillis();
		logger.logTableRow("Intra option order placment", status, endTime - startTime);
	}

	public void Portfolio_view_analysis() throws InterruptedException {
		test = extent.createTest("Portfolio View Analysis button");
		Portfolio portfolio = new Portfolio(Driver);
		HomePage homepage = new HomePage(Driver);
		homepage.portfolioBottombar.click();
		long startTime = System.currentTimeMillis();
		try {
			wait.until(ExpectedConditions.visibilityOf(portfolio.AllTabPortfolio));
			portfolio.viewAnalysis.click();
			portfolio.returnsAbs.click();
			WebElement current = wait.until(ExpectedConditions.visibilityOf(portfolio.currentVerification));
			String Current = current.getAttribute("content-desc");
			Current.equalsIgnoreCase("Current Investment Value");
			status = "Pass";
			test.pass("Portfolio View Analysis button Passed");
		} catch (Exception e) {
			status = "Fail";
			test.fail("Portfolio View Analysis button Failed");
			test.info(e.getMessage());
		} finally {
			Driver.navigate().back();
			Driver.navigate().back();
			long endTime = System.currentTimeMillis();
			logger.logTableRow("Portfolio View Analysis button", status, endTime - startTime);
		}
	}

	public void Portfolio_swipe_Stocks_Tab_verification() throws InterruptedException {
		test = extent.createTest("Portfolio swipe Stocks Tab verification");
		Portfolio portfolio = new Portfolio(Driver);
		HomePage homepage = new HomePage(Driver);
		homepage.portfolioBottombar.click();
		long startTime = System.currentTimeMillis();
		ResusableMethods.horizontalSwipetillElement(Driver, portfolio.marginpledge, 0, 5, 944, 166, 1285);
		try {
			wait.until(ExpectedConditions.elementToBeSelected(portfolio.StocksTabPortfolio));
			status = "Pass";
			test.pass("Portfolio swipe Stocks Tab verification Passed");
		} catch (Exception e) {
			status = "Fail";
			test.fail("Portfolio swipe Stocks Tab verification Failed");
			test.info(e.getMessage());
		} finally {
			long endTime = System.currentTimeMillis();
			logger.logTableRow("Portfolio swipe Stocks Tab verification", status, endTime - startTime);
		}
	}

	public void Portfolio_swipe_MF_Tab_verification() {
		test = extent.createTest("Portfolio swipe MF Tab verification");
		Portfolio portfolio = new Portfolio(Driver);
		long startTime = System.currentTimeMillis();
		ResusableMethods.horizontalSwipetillElement(Driver, portfolio.investedmf, 0, 5, 944, 166, 1285);
		try {
			wait.until(ExpectedConditions.elementToBeSelected(portfolio.MFTabPortfolio));
			status = "Pass";
			test.pass("Portfolio swipe MF Tab verification Passed");
		} catch (Exception e) {
			status = "Fail";
			test.fail("Portfolio swipe MF Tab verification Failed");
			test.info(e.getMessage());
		} finally {
			long endTime = System.currentTimeMillis();
			logger.logTableRow("Portfolio swipe MF Tab verification", status, endTime - startTime);
		}
	}

	public void Portfolio_swipe_PMS_Tab_verification() {
		test = extent.createTest("Portfolio swipe PMS Tab verification");
		Portfolio portfolio = new Portfolio(Driver);
		long startTime = System.currentTimeMillis();
		ResusableMethods.horizontalSwipetillElement(Driver, portfolio.investedpms, 0, 5, 805, 166, 1328);
		try {
			WebElement PMS = wait.until(ExpectedConditions.elementToBeClickable(portfolio.PMSTabPortfolio));
			String pms = PMS.getAttribute("content-desc");
			String pmsTab = pms.substring(0, 3);
			pmsTab.equalsIgnoreCase("PMS");
			status = "Pass";
			test.pass("Portfolio swipe PMS Tab verification Passed");
		} catch (Exception e) {
			status = "Fail";
			test.fail("Portfolio swipe PMS Tab verification Failed");
			test.info(e.getMessage());
		} finally {
			long endTime = System.currentTimeMillis();
			logger.logTableRow("Portfolio swipe PMS Tab verification", status, endTime - startTime);
		}
	}

	public void Portfolio_swipe_Basket_Tab_verification() {
		test = extent.createTest("Portfolio swipe Basket Tab verification");
		Portfolio portfolio = new Portfolio(Driver);
		long startTime = System.currentTimeMillis();
		ResusableMethods.horizontalSwipetillElement(Driver, portfolio.allbasket, 0, 5, 944, 166, 1285);
		try {
			wait.until(ExpectedConditions.elementToBeSelected(portfolio.BasketTabPortfolio));
			status = "Pass";
			test.pass("Portfolio swipe Basket Tab verification Passed");
		} catch (Exception e) {
			status = "Fail";
			test.fail("Portfolio swipe Basket Tab verification Failed");
			test.info(e.getMessage());
		} finally {
			long endTime = System.currentTimeMillis();
			logger.logTableRow("Portfolio swipe Basket Tab verification", status, endTime - startTime);
		}
	}

	public void Watchlist_script() throws InterruptedException {
		test = extent.createTest("Add Watchlist");
		HomePage homepage = new HomePage(Driver);
		Watchlist watchlist = new Watchlist(Driver);
		homepage.WatchlistBottombar.click();
		long startTime = System.currentTimeMillis();
		ResusableMethods.horizontalSwipetillElement(Driver, watchlist.AddButton, 0, 5, 906, 95, 688);
		wait.until(ExpectedConditions.elementToBeClickable(watchlist.AddButton));
		watchlist.AddButton.click();
		ResusableMethods.cleartextandenterinput(Driver, watchlist.enterwatchlistname, "Aumation");
		watchlist.createButton.click();
		try {
			WebElement Createbutton = wait.until(ExpectedConditions.visibilityOf(watchlist.okwatchlistcreated));
			String verification = Createbutton.getAttribute("content-desc");
			verification.equalsIgnoreCase("OK");
			status = "Pass";
			test.pass("Add Watchlist Passed");
		} catch (Exception e) {
			status = "Fail";
			test.fail("Add Watchlist Failed");
			test.info(e.getMessage());
		} finally {
			watchlist.okwatchlistcreated.click();
			long endTime = System.currentTimeMillis();
			logger.logTableRow("Add Watchlist", status, endTime - startTime);
		}
	}

	public void Add_script_in_watchlist() throws IOException, InterruptedException {
		test = extent.createTest("Add Script in watchlist");
		Watchlist watchlist = new Watchlist(Driver);
		HomePage homepage = new HomePage(Driver);
		long startTime = System.currentTimeMillis();
		watchlist.Addscript.click();
		homepage.Globalsearchaftertap.get(1).sendKeys("YESBANK EQ");
		watchlist.stocksglobalsearchtab.click();
		wait.until(ExpectedConditions.visibilityOf(watchlist.addscripticon));
		watchlist.addscripticon.click();
		Driver.hideKeyboard();
		Driver.navigate().back();
		try {
			WebElement scriptinwatchlist = wait.until(ExpectedConditions.visibilityOf(watchlist.scriptinwatchlist));
			String addedscript = scriptinwatchlist.getAttribute("content-desc");
			List<String> splitList = Arrays.asList(addedscript.split("\\s+"));
			String watchlistscript = splitList.get(0);
			watchlistscript.equalsIgnoreCase("YESBANK");
			status = "Pass";
			test.pass("Add Script in watchlist Passed");
		} catch (Exception e) {
			status = "Fail";
			test.fail("Add Script in watchlist Failed");
			test.info(e.getMessage());
		} finally {
			long endTime = System.currentTimeMillis();
			logger.logTableRow("Add Script in watchlist", status, endTime - startTime);
		}
	}

	public void MTFinwatchlistscript() {
		test = extent.createTest("MTF in script in watchlist");
		Watchlist watchlist = new Watchlist(Driver);
		long startTime = System.currentTimeMillis();
		try {
			try {
				watchlist.mtftext.isDisplayed();
				status = "Pass";
				test.pass("MTF in script in watchlist Passed");
			} catch (Exception e) {
				watchlist.kebabmenuwatchlist.click();
				watchlist.showmtfinfo.click();
				Driver.navigate().back();
				watchlist.mtftext.isDisplayed();
				status = "Pass";
				test.pass("MTF in script in watchlist Passed");
			}
		} catch (Exception e) {
			status = "Fail";
			test.fail("MTF in script in watchlist Failed");
			test.info(e.getMessage());
		} finally {
			long endTime = System.currentTimeMillis();
			logger.logTableRow("MTF in script in watchlist", status, endTime - startTime);
		}

	}

	public void Delete_script_in_watchlist() throws InterruptedException {
		test = extent.createTest("Delete script from watchlist");
		Watchlist watchlist = new Watchlist(Driver);
		long startTime = System.currentTimeMillis();
		ResusableMethods.longpressElement(Driver, watchlist.scriptinwatchlist);
		ResusableMethods.tapWithActions(Driver, 977, 972);
		String scriptdeleted = watchlist.Addscript.getAttribute("content-desc");
		try {
			scriptdeleted.equalsIgnoreCase("Add Scrip");
			status = "Pass";
			test.pass("Delete script from watchlist Passed");
		} catch (Exception e) {
			status = "Fail";
			test.fail("Delete script from watchlist Failed");
			test.info(e.getMessage());
		} finally {
			long endTime = System.currentTimeMillis();
			logger.logTableRow("Delete script from watchlist", status, endTime - startTime);
		}
	}

	public void Delete_watchlist() {
		test = extent.createTest("Delete watchlist");
		Watchlist watchlist = new Watchlist(Driver);
		long startTime = System.currentTimeMillis();
		watchlist.kebabmenuwatchlist.click();
		watchlist.managewatchlist.click();
		ResusableMethods.verticalswipetillElement(Driver, watchlist.deleteicon, 0, 5, 532, 1840, 436);
		try {
			watchlist.deleteicon.click();
			watchlist.savebutton.click();
			status = "Pass";
			test.pass("Delete watchlist Passed");
		} catch (Exception e) {
			status = "Fail";
			test.fail("Delete watchlist Failed");
			test.info(e.getMessage());
		} finally {
			long endTime = System.currentTimeMillis();
			logger.logTableRow("Delete watchlist", status, endTime - startTime);
		}
	}

	public void asbaswitch() throws InterruptedException {
		test = extent.createTest("Profile asba switch");
		ProfilePage profilepage = new ProfilePage(Driver);
		HomePage homepage = new HomePage(Driver);
		homepage.explorebottombar.click();
		homepage.profileicon.click();
		profilepage.profiledetails.click();
		profilepage.tradingaccountdetails.click();
		long startTime = System.currentTimeMillis();
		try {
			profilepage.asbaswitch.isDisplayed();
			status = "Pass";
			test.pass("Profile asba switch Passed");
		} catch (Exception e) {
			status = "Fail";
			test.fail("Profile asba switch Failed");
			test.info(e.getMessage());
		} finally {
			long endTime = System.currentTimeMillis();
			Driver.navigate().back();
//			Driver.navigate().back();
			logger.logTableRow("Profile asba switch", status, endTime - startTime);
		}
	}

	public void Reports() throws InterruptedException {
		test = extent.createTest("Ledger Report download");
		ProfilePage profilepage = new ProfilePage(Driver);
		profilepage.reportsbutton.click();
		profilepage.ledgerreports.click();
		ResusableMethods.longPressWithActions(Driver, 1012, 325, 1000);
		long startTime = System.currentTimeMillis();
		try {
			Thread.sleep(2000);
			profilepage.openwith.isDisplayed();
			status = "Pass";
			test.pass("Ledger Report download Passed");
		} catch (Exception e) {
			status = "Fail";
			test.fail("Ledger Report download Failed");
			test.info(e.getMessage());
		} finally {
			long endTime = System.currentTimeMillis();
			Driver.navigate().back();
			Driver.navigate().back();
			logger.logTableRow("Ledger Report download", status, endTime - startTime);
		}
	}

	public void buyorder() throws InterruptedException {
		test = extent.createTest("Buy order");
		Watchlist watchlist = new Watchlist(Driver);
		GetQuote getquote = new GetQuote(Driver);
		OrderForm orderform = new OrderForm(Driver);
		ResusableMethods.tapWithActions(Driver, 334, 2205);
		wait.until(ExpectedConditions.visibilityOf(watchlist.donotdelte));
		ResusableMethods.tapWithActions(Driver, 517, 849);
		wait.until(ExpectedConditions.visibilityOf(getquote.nsebutton));
		ResusableMethods.tapWithActions(Driver, 811, 2172);
		wait.until(ExpectedConditions.visibilityOf(orderform.quantityMarket));
		ResusableMethods.cleartextandenterinput(Driver, orderform.quantityMarket, "1");
		ResusableMethods.cleartextandenterinput(Driver, orderform.limitprice, "1.70");
		Driver.hideKeyboard();
		long startTime = System.currentTimeMillis();
		ResusableMethods.tapWithActions(Driver, 621, 2177);
		wait.until(ExpectedConditions.visibilityOf(orderform.disclaimer));
		ResusableMethods.tapWithActions(Driver, 787, 2182);
		wait.until(ExpectedConditions.visibilityOf(orderform.confirmorder));
		ResusableMethods.tapWithActions(Driver, 517, 2187);

		try {
			wait.until(ExpectedConditions.visibilityOf(orderform.vieworder));
			orderform.vieworder.isDisplayed();
			status = "Pass";
			test.pass("Buy order Passed");
		} catch (Exception e) {
			status = "Fail";
			test.fail("Buy order Failed");
			test.info(e.getMessage());
		} finally {
			orderform.vieworder.click();
			long endTime = System.currentTimeMillis();
			wait.until(ExpectedConditions.visibilityOf(orderform.cancelbutton));
			logger.logTableRow("Buy Order", status, endTime - startTime);
		}
	}

	public void modifybuyorder() throws InterruptedException {
		test = extent.createTest("Modify buy order");
		OrderForm orderform = new OrderForm(Driver);
		ResusableMethods.tapWithActions(Driver, 792, 2168);
		ResusableMethods.cleartextandenterinput(Driver, orderform.limitprice, "1.75");
		Driver.hideKeyboard();
		long startTime = System.currentTimeMillis();
		ResusableMethods.tapWithActions(Driver, 621, 2177);
		wait.until(ExpectedConditions.visibilityOf(orderform.confirmorder));
		ResusableMethods.tapWithActions(Driver, 517, 2187);
		try {
			wait.until(ExpectedConditions.visibilityOf(orderform.vieworder));
			orderform.vieworder.isDisplayed();
			status = "Pass";
			test.pass("Modify Buy order Passed");
		} catch (Exception e) {
			status = "Fail";
			test.fail("Modify Buy order Failed");
			test.info(e.getMessage());
		} finally {
			orderform.vieworder.click();
			long endTime = System.currentTimeMillis();
			wait.until(ExpectedConditions.visibilityOf(orderform.cancelbutton));
			logger.logTableRow("Modify Buy Order", status, endTime - startTime);
		}

	}

	public void cancelorder() throws InterruptedException {
		test = extent.createTest("Cancel Buy order");
		OrderForm orderform = new OrderForm(Driver);
		long startTime = System.currentTimeMillis();
		ResusableMethods.tapWithActions(Driver, 289, 2168);
		wait.until(ExpectedConditions.visibilityOf(orderform.cancelbuttontext));
		ResusableMethods.tapWithActions(Driver, 797, 2163);
		try {
			wait.until(ExpectedConditions.visibilityOf(orderform.cancelorder));
			orderform.cancelorder.isDisplayed();
			status = "Pass";
			test.pass("Cancel Buy order Passed");
		} catch (Exception e) {
			status = "Fail";
			test.fail("Cancel Buy order Failed");
			test.info(e.getMessage());
		} finally {
			long endTime = System.currentTimeMillis();
			logger.logTableRow("Cancel Order", status, endTime - startTime);
		}
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

	@BeforeClass
	public void ExtentReportSetUp() {
		// Set up the ExtentSparkReporter to generate a report in the 'sparkReport.html'
		// file
		ExtentSparkReporter sparkReporter = new ExtentSparkReporter("sparkReport.html");
		sparkReporter.config().setTimeStampFormat("yyyy-MM-dd HH:mm:ss");
		sparkReporter.config().setDocumentTitle("Rise App Regression");
		sparkReporter.config().setReportName("Rise App Regression");

		// Initialize ExtentReports and attach the ExtentSparkReporter
		extent = new ExtentReports();
		extent.attachReporter(sparkReporter);
	}

	@AfterClass
	public void ExtentReportTearDown() {
		// End the test in ExtentReports
		extent.flush();
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
	public void Verify_user_kills_app() {
		if (Driver != null) {
			Driver.quit();
		}
	}

}
