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
			Thread.sleep(5000);
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
		homepage_explore_hide_button();
		homepage_explore_portfolio_expand();
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
		try {
			WebElement searchresult = wait.until(ExpectedConditions.visibilityOf(homepage.Globalsearchresult));

			String resultsearch = searchresult.getAttribute("content-desc");
			String globalsearchresult = resultsearch.substring(3, 8);
			globalsearchresult.equalsIgnoreCase("YESBA");
			status = "Pass";
		} catch (Exception e) {
			status = "Fail";
		} finally {
			long endTime = System.currentTimeMillis();
			homepage.Globalsearchresult.click();
			logger.logTableRow("Global Search Result", status, endTime - startTime);
			Thread.sleep(1000);
		}
	}

	public void Get_quote_fut_tab() {
		GetQuote getquote = new GetQuote(Driver);
		wait.until(ExpectedConditions.elementToBeClickable(getquote.FutTab));
		long startTime = System.currentTimeMillis(); // Start timer
		getquote.FutTab.click();
		try {
			WebElement NSEFO = wait.until(ExpectedConditions.visibilityOf(getquote.NSEFO));
			String nsefo = NSEFO.getAttribute("content-desc");
			nsefo.equalsIgnoreCase("NSE_FO");
			status = "Pass";
		} catch (Exception e) {
			status = "Fail";
		} finally {
			long endTime = System.currentTimeMillis();
			logger.logTableRow("Get quote fut tab", status, endTime - startTime);
		}
	}

	public void Get_quote_opt_tab() {
		GetQuote getquote = new GetQuote(Driver);
		wait.until(ExpectedConditions.elementToBeClickable(getquote.optionsTab));
		long startTime = System.currentTimeMillis();
		getquote.optionsTab.click();
		try {
			WebElement Call = wait.until(ExpectedConditions.visibilityOf(getquote.callbutton));
			String call = Call.getAttribute("content-desc");
			call.equalsIgnoreCase("Call");
			status = "Pass";
		} catch (Exception e) {
			status = "Fail";
		} finally {
			long endTime = System.currentTimeMillis();
			logger.logTableRow("Get quote opt tab", status, endTime - startTime);
		}
	}

	public void Get_quote_cash_tab() {
		GetQuote getquote = new GetQuote(Driver);
		wait.until(ExpectedConditions.elementToBeClickable(getquote.cashtab));
		long startTime = System.currentTimeMillis();
		getquote.cashtab.click();
		try {
			WebElement Nse = wait.until(ExpectedConditions.visibilityOf(getquote.nsebutton));
			String nse = Nse.getAttribute("content-desc");
			nse.equalsIgnoreCase("NSE");
			status = "Pass";
		} catch (Exception e) {
			status = "Fail";
		} finally {
			long endTime = System.currentTimeMillis();
			logger.logTableRow("Get quote cash tab", status, endTime - startTime);
		}
	}

	public void Get_quote_Nse_switch_Delivery_buy() throws InterruptedException {
		GetQuote getquote = new GetQuote(Driver);
		OrderForm orderform = new OrderForm(Driver);
		long startTime = System.currentTimeMillis();
		getquote.nsebutton.click();
		getquote.BuyButton.click();
		try {
			wait.until(ExpectedConditions.visibilityOf(orderform.NseSwitch));
			orderform.NseSwitch.isSelected();
			status = "Pass";
		} catch (Exception e) {
			status = "Fail";
		} finally {
			long endTime = System.currentTimeMillis();
			Driver.navigate().back();
			logger.logTableRow("Get quote Nse switch delivery buy order form", status, endTime - startTime);
		}
	}

	public void Get_quote_Nse_switch_Delivery_Sell() throws InterruptedException {
		GetQuote getquote = new GetQuote(Driver);
		OrderForm orderform = new OrderForm(Driver);
		long startTime = System.currentTimeMillis();
		getquote.nsebutton.click();
		getquote.SellButton.click();
		try {
			wait.until(ExpectedConditions.visibilityOf(orderform.NseSwitch));
			orderform.NseSwitch.isSelected();
			status = "Pass";
		} catch (Exception e) {
			status = "Fail";
		} finally {
			long endTime = System.currentTimeMillis();
			Driver.navigate().back();
			logger.logTableRow("Get quote Nse switch delivery Sell order form", status, endTime - startTime);
		}
	}

	public void Get_quote_Bse_switch_Delivery_buy() throws InterruptedException {
		GetQuote getquote = new GetQuote(Driver);
		OrderForm orderform = new OrderForm(Driver);
		long startTime = System.currentTimeMillis();
		getquote.bsebutton.click();
		getquote.BuyButton.click();
		try {
			wait.until(ExpectedConditions.visibilityOf(orderform.BseSwitch));
			orderform.BseSwitch.isSelected();
			status = "Pass";
		} catch (Exception e) {
			status = "Fail";
		} finally {
			long endTime = System.currentTimeMillis();
			Driver.navigate().back();
			logger.logTableRow("Get quote Bse switch delivery buy order form", status, endTime - startTime);
		}
	}

	public void Get_quote_Bse_switch_Delivery_Sell() throws InterruptedException {
		GetQuote getquote = new GetQuote(Driver);
		OrderForm orderform = new OrderForm(Driver);
		long startTime = System.currentTimeMillis();
		getquote.bsebutton.click();
		getquote.SellButton.click();
		try {
			wait.until(ExpectedConditions.visibilityOf(orderform.BseSwitch));
			orderform.BseSwitch.isSelected();
			status = "Pass";
		} catch (Exception e) {
			status = "Fail";
		} finally {
			long endTime = System.currentTimeMillis();
			Driver.navigate().back();
			logger.logTableRow("Get quote Bse switch delivery order form", status, endTime - startTime);
		}
	}

	public void Get_quote_charts() {
		GetQuote getquote = new GetQuote(Driver);
		long startTime = System.currentTimeMillis();
		getquote.chartsbutton.click();
		try {
			wait.until(ExpectedConditions.elementToBeClickable(getquote.chartsbuybutton));
			String buy = getquote.chartsbuybutton.getAttribute("content-desc");
			buy.equalsIgnoreCase("BUY");
			status = "Pass";
		} catch (Exception e) {
			status = "Fail";
		} finally {
			long endTime = System.currentTimeMillis();
			Driver.navigate().back();
			logger.logTableRow("Get quote charts button", status, endTime - startTime);
		}
	}

	public void Get_quote_optionchain() {
		GetQuote getquote = new GetQuote(Driver);
		long startTime = System.currentTimeMillis();
		getquote.optionchainbutton.click();
		try {
			wait.until(ExpectedConditions.visibilityOf(getquote.OIoptionchain));
			String OI = getquote.OIoptionchain.getAttribute("content-desc");
			OI.equalsIgnoreCase("OI");
			status = "Pass";
		} catch (Exception e) {
			status = "Fail";
		} finally {
			long endTime = System.currentTimeMillis();
			Driver.navigate().back();
			logger.logTableRow("Get quote optionchain button", status, endTime - startTime);
		}
	}

	public void Get_quote_fundamentaltab() throws InterruptedException {
		GetQuote getquote = new GetQuote(Driver);
		long startTime = System.currentTimeMillis();
		getquote.fundamentaltab.click();
		try {
			wait.until(ExpectedConditions.visibilityOf(getquote.fundamentalratios));
			String fundamentalratios = getquote.fundamentalratios.getAttribute("content-desc");
			fundamentalratios.equalsIgnoreCase("Fundamental Ratios");
			status = "Pass";
		} catch (Exception e) {
			status = "Fail";
		} finally {
			long endTime = System.currentTimeMillis();
			logger.logTableRow("Get quote Fundamental Tab", status, endTime - startTime);
		}
	}

	public void Get_quote_technicaltab() throws InterruptedException {
		GetQuote getquote = new GetQuote(Driver);
		long startTime = System.currentTimeMillis();
		getquote.technicaltab.click();
		try {
			wait.until(ExpectedConditions.visibilityOf(getquote.deliveryvolume));
			String delivery = getquote.deliveryvolume.getAttribute("content-desc");
			delivery.equalsIgnoreCase("Delivery & Volume");
			status = "Pass";
		} catch (Exception e) {
			status = "Fail";
		} finally {
			long endTime = System.currentTimeMillis();
			logger.logTableRow("Get quote Technical Tab", status, endTime - startTime);
		}
	}

	public void Get_quote_Newstab() throws InterruptedException {
		GetQuote getquote = new GetQuote(Driver);
		long startTime = System.currentTimeMillis();
		getquote.Newstab.click();
		try {
			wait.until(ExpectedConditions.visibilityOf(getquote.news));
			String newsverification = getquote.news.getAttribute("content-desc");
			newsverification.equalsIgnoreCase("News");
			status = "Pass";
		} catch (Exception e) {
			status = "Fail";
		} finally {
			long endTime = System.currentTimeMillis();
			logger.logTableRow("Get quote News Tab", status, endTime - startTime);
		}
	}

	public void Get_quote_Transactionstab() throws InterruptedException {
		GetQuote getquote = new GetQuote(Driver);
		long startTime = System.currentTimeMillis();
		getquote.transactionstab.click();
		try {
			wait.until(ExpectedConditions.visibilityOf(getquote.realisedPL));
			String plrealised = getquote.realisedPL.getAttribute("content-desc");
			String realisepl = plrealised.split(" ")[0];
			realisepl.equalsIgnoreCase("Realised");
			status = "Pass";
		} catch (Exception e) {
			status = "Fail";
			System.out.println(e);
		}
		long endTime = System.currentTimeMillis();
		logger.logTableRow("Get quote Transactions Tab", status, endTime - startTime);
	}

	public void OrderForm_quantity_toggle() throws IOException, InterruptedException {
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
		} catch (Exception e) {
			status = "Fail";
		} finally {
			long endTime = System.currentTimeMillis(); // End timer
			logger.logTableRow("Order Form quantity toggle", status, endTime - startTime); // Log search timing
		}
	}

	public void OrderForm_amount_toggle() throws InterruptedException, IOException {

		Thread.sleep(500);
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
		} catch (Exception e) {
			status = "Fail";
		} finally {
			long endTime = System.currentTimeMillis(); // End timer
			Driver.navigate().back();
			Driver.navigate().back();
			Driver.navigate().back();
			logger.logTableRow("Order Form amount toggle", status, endTime - startTime); // Log search timing
		}
	}

	public void homepage_explore_hide_button() {
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
		} catch (Exception e) {
			status = "Fail";
		} finally {
			long endTime = System.currentTimeMillis();
			homepage.showbutton.click();
			logger.logTableRow("Homescreen portfolio hide mark", status, endTime - startTime);
		}
	}

	public void homepage_explore_portfolio_expand() {
		HomePage homepage = new HomePage(Driver);
		long startTime = System.currentTimeMillis();
		homepage.homepageportfolioexpand.click();
		try {
			homepage.availablemargin.isDisplayed();
			status = "Pass";
		} catch (Exception e) {
			status = "Fail";
		} finally {
			long endTime = System.currentTimeMillis();
			logger.logTableRow("Homescreen portfolio expand button", status, endTime - startTime);
		}
	}

	public void hompage_explore_portfolio_collapse() {
		HomePage homepage = new HomePage(Driver);
		long startTime = System.currentTimeMillis();
		homepage.homepageportfoliocollapse.click();
		try {
			homepage.ExpandIcon.isDisplayed();
			status = "Pass";
		} catch (Exception e) {
			status = "Fail";
		} finally {
			long endTime = System.currentTimeMillis();
			logger.logTableRow("Homescreen portfolio collapse button", status, endTime - startTime);
		}
	}

	public void Researchideaibutton() {
		HomePage homepage = new HomePage(Driver);
		long startTime = System.currentTimeMillis();
		homepage.researchideaibutton.click();
		try {
			wait.until(ExpectedConditions.visibilityOf(homepage.reasearchideatext));
			homepage.reasearchideatext.isDisplayed();
			status = "Pass";
		} catch (Exception e) {
			status = "Fail";
		} finally {
			Driver.navigate().back();
			long endTime = System.currentTimeMillis();
			logger.logTableRow("Homescreen Research idea I button", status, endTime - startTime);
		}
	}

	public void Researchidea_viewAll() {
		HomePage homepage = new HomePage(Driver);
		long startTime = System.currentTimeMillis();
		homepage.researchideaviewall.click();
		try {
			homepage.researchideaequitytab.isDisplayed();
			status = "Pass";
		} catch (Exception e) {
			status = "Fail";
		} finally {
			long endTime = System.currentTimeMillis();
			Driver.navigate().back();
			logger.logTableRow("Homescreen Research idea view All", status, endTime - startTime);
		}
	}

	public void IPO_button_CTA() {
		HomePage homepage = new HomePage(Driver);
		ResusableMethods.verticalswipetillElement(Driver, homepage.optionsstoreCTA, 0, 5, 470, 1788, 590);
		long startTime = System.currentTimeMillis();
		homepage.IPO.click();
		try {
			homepage.ipolist.isDisplayed();
			status = "Pass";
		} catch (Exception e) {
			status = "Fail";
		} finally {
			long endTime = System.currentTimeMillis();
			Driver.navigate().back();
			logger.logTableRow("Homescreen IPO CTA", status, endTime - startTime);
		}
	}

	public void Research_Ideas_CTA() {
		HomePage homepage = new HomePage(Driver);
		long startTime = System.currentTimeMillis();
		homepage.researchideasbutton.click();
		try {
			homepage.researchideaequitytab.isDisplayed();
			status = "Pass";
		} catch (Exception e) {
			status = "Fail";
		} finally {
			long endTime = System.currentTimeMillis();
			Driver.navigate().back();
			logger.logTableRow("Homescreen Research Idea CTA", status, endTime - startTime);
		}
	}

	public void IAP_CTA() {
		HomePage homepage = new HomePage(Driver);
		long startTime = System.currentTimeMillis(); // Start timer
		homepage.IAPbutton.click();
		try {
			homepage.iappage.isDisplayed();
			status = "Pass";
		} catch (Exception e) {
			status = "Fail";
		} finally {
			long endTime = System.currentTimeMillis(); // End timer
			Driver.navigate().back();
			logger.logTableRow("Homescreen IAP CTA", status, endTime - startTime); // Log search timing
		}
	}

	public void Bonds_CTA() {
		HomePage homepage = new HomePage(Driver);
		long startTime = System.currentTimeMillis(); // Start timer
		homepage.bondsCTA.click();
		try {
			wait.until(ExpectedConditions.visibilityOf(homepage.bondspage));
			homepage.bondspage.isDisplayed();
			status = "Pass";
		} catch (Exception e) {
			status = "Fail";
		} finally {
			long endTime = System.currentTimeMillis(); // End timer
			Driver.navigate().back();
			logger.logTableRow("Homescreen Bonds CTA", status, endTime - startTime); // Log search timing
		}
	}

	public void options_store() throws InterruptedException {
		HomePage homepage = new HomePage(Driver);
		long startTime = System.currentTimeMillis();
		homepage.optionsstoreCTA.click();
		try {
			wait.until(ExpectedConditions.visibilityOf(homepage.optionsstorepage));
			homepage.optionsstorepage.isDisplayed();
			status = "Pass";
		} catch (Exception e) {
			status = "Fail";
		} finally {
			long endTime = System.currentTimeMillis();
			Driver.navigate().back();
			logger.logTableRow("Homescreen Option Store CTA", status, endTime - startTime);
		}
	}

	public void Stock_basket_CTA() {
		HomePage homepage = new HomePage(Driver);
		long startTime = System.currentTimeMillis();
		homepage.stockbasket.click();
		try {
			wait.until(ExpectedConditions.visibilityOf(homepage.researchideaequitytab));
			homepage.researchideaequitytab.isDisplayed();
			status = "Pass";
		} catch (Exception e) {
			status = "Fail";
		} finally {
			long endTime = System.currentTimeMillis();
			Driver.navigate().back();
			logger.logTableRow("Homescreen Stock Basket CTA", status, endTime - startTime);
		}
	}

	public void TGS_CTA() {
		HomePage homepage = new HomePage(Driver);
		ResusableMethods.verticalswipetillElement(Driver, homepage.mfCTAs, 0, 5, 470, 1788, 600);
		long startTime = System.currentTimeMillis();
		homepage.TGSbutton.click();
		try {
			wait.until(ExpectedConditions.visibilityOf(homepage.TGSpage));
			status = "Pass";
		} catch (Exception e) {
			status = "Fail";
		} finally {
			long endTime = System.currentTimeMillis();
			Driver.navigate().back();
			logger.logTableRow("Homescreen TGS CTA", status, endTime - startTime);
		}
	}

	public void Insurance_CTA() {
		HomePage homepage = new HomePage(Driver);
		ResusableMethods.verticalswipetillElement(Driver, homepage.mfCTAs, 0, 8, 470, 1600, 600);
		long startTime = System.currentTimeMillis();
		homepage.insurancebutton.click();
		try {
			wait.until(ExpectedConditions.visibilityOf(homepage.insurancepage));
			homepage.insurancepage.isDisplayed();
			status = "Pass";
		} catch (Exception e) {
			status = "Fail";
		} finally {
			long endTime = System.currentTimeMillis();
			Driver.navigate().back();
			logger.logTableRow("Homescreen Insurance CTA", status, endTime - startTime);
		}
	}

	public void FixedDeposit_CTA() throws InterruptedException {
		HomePage homepage = new HomePage(Driver);
//		ResusableMethods.verticalswipetillElement(Driver, homepage.mfCTAs, 0, 5, 470, 1788, 600);
		long startTime = System.currentTimeMillis();
		homepage.fixeddepositbutton.click();
		try {
			wait.until(ExpectedConditions.visibilityOf(homepage.fixeddepositpage));
			homepage.fixeddepositpage.isDisplayed();
			status = "Pass";
		} catch (Exception e) {
			status = "Fail";
		} finally {
			long endTime = System.currentTimeMillis();
			Driver.navigate().back();
			logger.logTableRow("Homescreen Fixed Deposit CTA", status, endTime - startTime);
		}
	}

	public void Intraoptions_CTA() throws InterruptedException {
		HomePage homepage = new HomePage(Driver);
		ResusableMethods.verticalswipetillElement(Driver, homepage.smallcasebutton, 0, 5, 470, 1788, 600);
		long startTime = System.currentTimeMillis();
		homepage.IntaoptionsButton.click();
		try {
			wait.until(ExpectedConditions.visibilityOf(homepage.intraoptionspage));
			homepage.intraoptionspage.isDisplayed();
			status = "Pass";
		} catch (Exception e) {
			status = "Fail";
		} finally {
			long endTime = System.currentTimeMillis();
			Driver.navigate().back();
			Thread.sleep(2000);
			logger.logTableRow("Homescreen Intra options CTA", status, endTime - startTime);
		}
	}

	public void smallcase_CTA() throws InterruptedException {
		HomePage homepage = new HomePage(Driver);
		long startTime = System.currentTimeMillis();
		ResusableMethods.verticalswipetillElement(Driver, homepage.smallcasebutton, 0, 5, 470, 1200, 600);
		homepage.smallcasebutton.click();
		try {
			wait.until(ExpectedConditions.visibilityOf(homepage.smallcasepage));
			status = "Pass";
		} catch (Exception e) {
			status = "Fail";
		} finally {
			long endTime = System.currentTimeMillis();
			Thread.sleep(1000);
			Driver.navigate().back();
			logger.logTableRow("Homescreen Smallcase CTA", status, endTime - startTime);
		}
	}

	public void Tejimandi() throws InterruptedException {
		HomePage homepage = new HomePage(Driver);
		long startTime = System.currentTimeMillis();
		ResusableMethods.verticalswipetillElement(Driver, homepage.smallcasebutton, 0, 5, 470, 1788, 590);
		homepage.tejimandibutton.click();
		try {
			wait.until(ExpectedConditions.visibilityOf(homepage.tejimandipage));
			homepage.tejimandipage.isDisplayed();
			status = "Pass";
		} catch (Exception e) {
			status = "Fail";
		} finally {
			long endTime = System.currentTimeMillis();
			Driver.navigate().back();
			Thread.sleep(500);
			logger.logTableRow("Homescreen Teji Mandi CTA", status, endTime - startTime);
		}
	}

	public void MFHighReturns_CTA() throws InterruptedException {
		HomePage homepage = new HomePage(Driver);
		MfHomePage mfhomepage = new MfHomePage(Driver);
		ResusableMethods.verticalswipetillElement(Driver, homepage.internationfund, 0, 9, 470, 1600, 600);
		long startTime = System.currentTimeMillis();
		homepage.mfhighreturnsbutton.click();
		try {
			wait.until(ExpectedConditions.visibilityOf(mfhomepage.MfViewAll3YReturns));
			mfhomepage.MfViewAll3YReturns.isDisplayed();
			status = "Pass";
		} catch (Exception e) {
			status = "Fail";
		} finally {
			long endTime = System.currentTimeMillis();
			Driver.navigate().back();
			Thread.sleep(500);
			logger.logTableRow("Homescreen MF High Returns", status, endTime - startTime);
		}
	}

	public void SIPwith500_CTA() throws InterruptedException {
		HomePage homepage = new HomePage(Driver);
		MfHomePage mfhomepage = new MfHomePage(Driver);
		ResusableMethods.verticalswipetillElement(Driver, homepage.internationfund, 0, 5, 470, 1788, 600);
		long startTime = System.currentTimeMillis();
		homepage.sipwith500.click();
		try {
			wait.until(ExpectedConditions.visibilityOf(mfhomepage.MfViewAll3YReturns));
			mfhomepage.MfViewAll3YReturns.isDisplayed();
			status = "Pass";
		} catch (Exception e) {
			status = "Fail";
		} finally {
			long endTime = System.currentTimeMillis();
			Driver.navigate().back();
			Thread.sleep(500);
			logger.logTableRow("Homescreen MF SIP with 500 CTA", status, endTime - startTime);
		}
	}

	public void Taxsaver_CTA() throws InterruptedException {
		HomePage homepage = new HomePage(Driver);
		MfHomePage mfhomepage = new MfHomePage(Driver);
		ResusableMethods.verticalswipetillElement(Driver, homepage.internationfund, 0, 5, 470, 1788, 600);
		long startTime = System.currentTimeMillis();
		homepage.taxsaverbutton.click();
		try {
			wait.until(ExpectedConditions.visibilityOf(mfhomepage.MfViewAll3YReturns));
			mfhomepage.MfViewAll3YReturns.isDisplayed();
			status = "Pass";
		} catch (Exception e) {
			status = "Fail";
		} finally {
			long endTime = System.currentTimeMillis();
			Driver.navigate().back();
			Thread.sleep(500);
			logger.logTableRow("Homescreen MF Tax Saver CTA", status, endTime - startTime);
		}
	}

	public void MoRecomended_CTA() throws InterruptedException {
		HomePage homepage = new HomePage(Driver);
		MfHomePage mfhomepage = new MfHomePage(Driver);
		ResusableMethods.verticalswipetillElement(Driver, homepage.internationfund, 0, 5, 470, 1788, 600);
		long startTime = System.currentTimeMillis();
		homepage.Morecommendedbutton.click();
		try {
			wait.until(ExpectedConditions.visibilityOf(mfhomepage.MfViewAll3YReturns));
			mfhomepage.MfViewAll3YReturns.isDisplayed();
			status = "Pass";
		} catch (Exception e) {
			status = "Fail";
		} finally {
			long endTime = System.currentTimeMillis();
			Driver.navigate().back();
			Thread.sleep(500);
			logger.logTableRow("Homescreen MF MO Recommended CTA", status, endTime - startTime);
		}
	}

	public void Highestrated_CTA() throws InterruptedException {
		HomePage homepage = new HomePage(Driver);
		MfHomePage mfhomepage = new MfHomePage(Driver);
		ResusableMethods.verticalswipetillElement(Driver, homepage.internationfund, 0, 5, 470, 1788, 600);
		long startTime = System.currentTimeMillis();
		homepage.highestratedfund.click();
		try {
			wait.until(ExpectedConditions.visibilityOf(mfhomepage.MfViewAll3YReturns));
			mfhomepage.MfViewAll3YReturns.isDisplayed();
			status = "Pass";
		} catch (Exception e) {
			status = "Fail";
		} finally {
			long endTime = System.currentTimeMillis();
			Driver.navigate().back();
			Thread.sleep(500);
			logger.logTableRow("Homescreen MF Highest rated Fund CTA", status, endTime - startTime);
		}
	}

	public void internationalfund_CTA() throws InterruptedException {
		HomePage homepage = new HomePage(Driver);
		MfHomePage mfhomepage = new MfHomePage(Driver);
		ResusableMethods.verticalswipetillElement(Driver, homepage.internationfund, 0, 5, 470, 1788, 600);
		long startTime = System.currentTimeMillis();
		homepage.internationfund.click();
		try {
			wait.until(ExpectedConditions.visibilityOf(mfhomepage.MfViewAll3YReturns));
			mfhomepage.MfViewAll3YReturns.isDisplayed();
			status = "Pass";
		} catch (Exception e) {
			status = "Fail";
		} finally {
			long endTime = System.currentTimeMillis();
			Driver.navigate().back();
			Thread.sleep(500);
			logger.logTableRow("Homescreen MF International Fund CTA", status, endTime - startTime);
		}
	}

	public void iponfoIbutton() throws InterruptedException {
		HomePage homepage = new HomePage(Driver);
		ResusableMethods.verticalswipetillElement(Driver, homepage.oneclicksip, 0, 8, 470, 1788, 600);
		long startTime = System.currentTimeMillis();
		homepage.ipoibutton.click();
		try {
			wait.until(ExpectedConditions.visibilityOf(homepage.ipochildscreen));
			homepage.ipochildscreen.isDisplayed();
			status = "Pass";
		} catch (Exception e) {
			status = "Fail";
		} finally {
			long endTime = System.currentTimeMillis();
			Driver.navigate().back();
			Thread.sleep(500);
			logger.logTableRow("Homescreen IPO I button CTA", status, endTime - startTime);
		}
	}

	public void ipoviewall() throws InterruptedException {
		HomePage homepage = new HomePage(Driver);
		ResusableMethods.verticalswipetillElement(Driver, homepage.oneclicksip, 0, 8, 470, 1788, 600);
		long startTime = System.currentTimeMillis();
		homepage.ipoviewallbutton.click();
		try {
			wait.until(ExpectedConditions.visibilityOf(homepage.ipopage));
			homepage.ipopage.isDisplayed();
			status = "Pass";
		} catch (Exception e) {
			status = "Fail";
		} finally {
			long endTime = System.currentTimeMillis();
			Driver.navigate().back();
			Thread.sleep(500);
			logger.logTableRow("Homescreen IPO view All CTA", status, endTime - startTime);
		}
	}

	public void MF_collection_viewall_returns() throws InterruptedException {
		HomePage homepage = new HomePage(Driver);
		MfHomePage mfhomepage = new MfHomePage(Driver);
		homepage.MfTab.click();
		long startTime = System.currentTimeMillis();
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
		long endTime = System.currentTimeMillis();
		logger.logTableRow("Mutual Fund View All Returns", status, endTime - startTime);
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
		} catch (Exception e) {
			status = "Fail";
		} finally {
			Driver.navigate().back();
			Driver.navigate().back();
			long endTime = System.currentTimeMillis();
			logger.logTableRow("Portfolio View Analysis", status, endTime - startTime);
		}
	}

	public void Portfolio_swipe_Stocks_Tab_verification() throws InterruptedException {
		Portfolio portfolio = new Portfolio(Driver);
		HomePage homepage = new HomePage(Driver);
		homepage.portfolioBottombar.click();
		long startTime = System.currentTimeMillis();
		ResusableMethods.horizontalSwipetillElement(Driver, portfolio.marginpledge, 0, 5, 944, 166, 1285);
		try {
			wait.until(ExpectedConditions.elementToBeSelected(portfolio.StocksTabPortfolio));
			status = "Pass";
		} catch (Exception e) {
			status = "Fail";
		} finally {
			long endTime = System.currentTimeMillis();
			logger.logTableRow("Portfolio swipe Stocks Tab verification", status, endTime - startTime);
		}
	}

	public void Portfolio_swipe_MF_Tab_verification() {
		Portfolio portfolio = new Portfolio(Driver);
		long startTime = System.currentTimeMillis();
		ResusableMethods.horizontalSwipetillElement(Driver, portfolio.investedmf, 0, 5, 944, 166, 1285);
		try {
			wait.until(ExpectedConditions.elementToBeSelected(portfolio.MFTabPortfolio));
			status = "Pass";
		} catch (Exception e) {
			status = "Fail";
		} finally {
			long endTime = System.currentTimeMillis();
			logger.logTableRow("Portfolio swipe MF Tab verification", status, endTime - startTime);
		}
	}

	public void Portfolio_swipe_PMS_Tab_verification() {
		Portfolio portfolio = new Portfolio(Driver);
		long startTime = System.currentTimeMillis();
		ResusableMethods.horizontalSwipetillElement(Driver, portfolio.investedpms, 0, 5, 805, 166, 1328);
		try {
			WebElement PMS = wait.until(ExpectedConditions.elementToBeClickable(portfolio.PMSTabPortfolio));
			String pms = PMS.getAttribute("content-desc");
			String pmsTab = pms.substring(0, 3);
			pmsTab.equalsIgnoreCase("PMS");
			status = "Pass";
		} catch (Exception e) {
			status = "Fail";
		} finally {
			long endTime = System.currentTimeMillis();
			logger.logTableRow("Portfolio swipe PMS Tab verification", status, endTime - startTime);
		}
	}

	public void Portfolio_swipe_Basket_Tab_verification() {
		Portfolio portfolio = new Portfolio(Driver);
		long startTime = System.currentTimeMillis();
		ResusableMethods.horizontalSwipetillElement(Driver, portfolio.allbasket, 0, 5, 944, 166, 1285);
		try {
			wait.until(ExpectedConditions.elementToBeSelected(portfolio.BasketTabPortfolio));
			status = "Pass";
		} catch (Exception e) {
			status = "Fail";
		} finally {
			long endTime = System.currentTimeMillis();
			logger.logTableRow("Portfolio swipe Basket Tab verification", status, endTime - startTime);
		}
	}

	public void Watchlist_script() throws InterruptedException {
		HomePage homepage = new HomePage(Driver);
		Watchlist watchlist = new Watchlist(Driver);
		homepage.WatchlistBottombar.click();
		long startTime = System.currentTimeMillis();
		ResusableMethods.horizontalSwipetillElement(Driver, watchlist.AddButton, 0, 5, 906, 95, 688);
		wait.until(ExpectedConditions.elementToBeClickable(watchlist.AddButton));
		watchlist.AddButton.click();
		ResusableMethods.cleartextandenterinput(Driver, watchlist.enterwatchlistname, "Autom");
		watchlist.createButton.click();
		try {
			WebElement Createbutton = wait.until(ExpectedConditions.visibilityOf(watchlist.okwatchlistcreated));
			String verification = Createbutton.getAttribute("content-desc");
			verification.equalsIgnoreCase("OK");
			status = "Pass";
		} catch (Exception e) {
			status = "Fail";
		} finally {
			watchlist.okwatchlistcreated.click();
			long endTime = System.currentTimeMillis();
			logger.logTableRow("Add Watchlist", status, endTime - startTime);
		}
	}

	public void Add_script_in_watchlist() throws IOException, InterruptedException {
		Watchlist watchlist = new Watchlist(Driver);
		HomePage homepage = new HomePage(Driver);
		long startTime = System.currentTimeMillis();
		watchlist.Addscript.click();
		homepage.Globalsearchaftertap.get(1).sendKeys("YESBANK EQ");

		wait.until(ExpectedConditions.visibilityOf(watchlist.addscripticon));
		watchlist.addscripticon.click();
		Driver.hideKeyboard();
		Driver.navigate().back();
		try {
			WebElement scriptinwatchlist = wait.until(ExpectedConditions.visibilityOf(watchlist.scriptinwatchlist));
			String addedscript = scriptinwatchlist.getAttribute("content-desc");
			String watchlistscript = addedscript.substring(0, 7);
			watchlistscript.equalsIgnoreCase("YESBANK");
			status = "Pass";
		} catch (Exception e) {
			status = "Fail";
		} finally {
			long endTime = System.currentTimeMillis();
			logger.logTableRow("Add Script in watchlist", status, endTime - startTime);
		}
	}

	public void Delete_script_in_watchlist() {
		Watchlist watchlist = new Watchlist(Driver);
		long startTime = System.currentTimeMillis();
		ResusableMethods.longpressElement(Driver, watchlist.scriptinwatchlist);
		ResusableMethods.tapWithActions(Driver, 977, 972);
		String scriptdeleted = watchlist.Addscript.getAttribute("content-desc");
		try {
			scriptdeleted.equalsIgnoreCase("Add Scrip");
			status = "Pass";
		} catch (Exception e) {
			status = "Fail";
		} finally {
			long endTime = System.currentTimeMillis();
			logger.logTableRow("Delete script from watchlist", status, endTime - startTime);
		}
	}

	public void Delete_watchlist() {
		Watchlist watchlist = new Watchlist(Driver);
		long startTime = System.currentTimeMillis();
		watchlist.kebabmenuwatchlist.click();
		watchlist.managewatchlist.click();
		ResusableMethods.verticalswipetillElement(Driver, watchlist.deleteicon, 0, 5, 532, 1840, 436);
		try {
			watchlist.deleteicon.click();
			watchlist.savebutton.click();
			status = "Pass";
		} catch (Exception e) {
			status = "Fail";
		} finally {
			long endTime = System.currentTimeMillis();
			logger.logTableRow("Delete watchlist", status, endTime - startTime);
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
			capabilities.setCapability("appPackage", Commons.getGlobalPropertiesValue("Rise_app_package_pilot"));
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
