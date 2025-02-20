package Bharat;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import pageobjects.ExplorePage;
import pageobjects.WebLogin;
import pageobjects.Webhomepage;
import utils.Commons;

public class MoWebsite {
	WebDriver driver;
	String status;
	TableLogger logger = new TableLogger();
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));

	@Test(priority = 1)
	public void Websitetest() throws IOException, InterruptedException {

		logger.logTableStart("Execution Report");

		/*
		 * InvalidUserID(); Invalidpassword();
		 */

		validcredentials();
		SearchFieldtest();
		Createwatchlist();
		Addscrip();
		deletescrip();
		deletewatchlist();
		indices();
		indiceschart();
		indiceoptionchain();
		editindice();

		logger.logTableEnd();
	}

	public void InvalidUserID() throws IOException, InterruptedException {
		WebLogin weblogin = new WebLogin(driver);
		weblogin.newversion.click();
		weblogin.userid.sendKeys("dfkasfdfj");
		weblogin.loginwithpwd.click();
		weblogin.password.sendKeys("abc@123");
		weblogin.loginbutton.click();
		long startTime = System.currentTimeMillis();
		try {
			weblogin.invalidcredstext.isDisplayed();
			status = "Pass";
		} catch (Exception e) {
			status = "Fail";
		} finally {
			long endTime = System.currentTimeMillis();
			weblogin.invalidokbutton.click();
			weblogin.userid.clear();
			weblogin.password.clear();
			Thread.sleep(2000);
			logger.logTableRow("InvalidUserID", status, endTime - startTime);
		}
	}

	public void Invalidpassword() throws IOException, InterruptedException {
		WebLogin weblogin = new WebLogin(driver);
		weblogin.newversion.click();
		weblogin.userid.sendKeys("EMUM378474");
		weblogin.loginwithpwd.click();
		weblogin.password.sendKeys("abc#123");
		weblogin.loginbutton.click();
		long startTime = System.currentTimeMillis();

		String alertText = weblogin.invalidmsg.getText();
		System.out.println(alertText);
		try {
			weblogin.invalidmsg.isDisplayed();
			status = "Pass";
		} catch (Exception e) {
			status = "Fail";
			System.out.println("Assertion Failed: " + e.getMessage());
		} finally {
			long endTime = System.currentTimeMillis();
			weblogin.invalidokbutton.click();
			weblogin.userid.clear();
			weblogin.password.clear();
			Thread.sleep(2000);
			status = "Pass";
			logger.logTableRow("test Invalidpassword", status, endTime - startTime);
		}
	}

	public void validcredentials() throws IOException, InterruptedException {
		WebLogin weblogin = new WebLogin(driver);
		weblogin.newversion.click();
		weblogin.userid.sendKeys(Commons.getGlobalPropertiesValue("userId"));
		weblogin.loginwithpwd.click();
		weblogin.password.sendKeys(Commons.getGlobalPropertiesValue("password"));
		weblogin.loginbutton.click();
		weblogin.dob.sendKeys(Commons.getGlobalPropertiesValue("dob"));
		Thread.sleep(2000);
//		wait.until(ExpectedConditions.visibilityOf(weblogin.FAlogin));
		weblogin.FAlogin.click();
		long startTime = System.currentTimeMillis();
		try {

			if (weblogin.rdd.isDisplayed()) {
				weblogin.rdd.click();
				System.out.println("Rdd displayed");
				status = "Pass";
			}

		} catch (Exception e) {
			System.err.println("RDD not displayed");
			status = "Fail";
		} finally {
			long endTime = System.currentTimeMillis();
			logger.logTableRow("Validpassword", status, endTime - startTime);
		}
	}

	public void SearchFieldtest() throws IOException {
		Webhomepage webhome = new Webhomepage(driver);
		webhome.Searchfield.sendKeys(Commons.getGlobalPropertiesValue("global_search_scrip"));
		long startTime = System.currentTimeMillis();
		try {
			wait.until(ExpectedConditions.visibilityOf(webhome.Yesbank));
			webhome.Yesbank.isDisplayed();
			String a = webhome.Yesbank.getText();
			boolean isVerified = a.equalsIgnoreCase("Yesbank");
			status = isVerified ? "Pass" : "Fail";
			/*
			 * if (a.equalsIgnoreCase("Yes")) { status = "Pass"; } else { status = "Fail"; }
			 */
		} catch (Exception e) {
			status = "Fail";
			System.out.println(e.getMessage());
		} finally {
			long endTime = System.currentTimeMillis();
			webhome.closesearch.click();
			logger.logTableRow("Search Field test", status, endTime - startTime);
		}

	}

	public void Createwatchlist() throws InterruptedException {
		Webhomepage webhome = new Webhomepage(driver);
		WebElement dropdownIcon = wait.until(ExpectedConditions.elementToBeClickable(webhome.Watchlistdropdown));
		dropdownIcon.click();
		WebElement option = webhome.Createwatchlist;
		option.click();
		webhome.watchlistname.sendKeys("Automate");
		webhome.create.click();
		long startTime = System.currentTimeMillis();
		String wlcreatemsg = webhome.createwlmsg.getText();
		System.out.println(wlcreatemsg);
		try {
			webhome.createwlmsg.isDisplayed();
			status = "Pass";
		} catch (Exception e) {
			status = "Fail";
			System.out.println(e.getMessage());
		} finally {
			long endTime = System.currentTimeMillis();
			webhome.closesearch.click();
			Thread.sleep(7000);
			logger.logTableRow("Create Watchlist", status, endTime - startTime);
		}

	}

	public void Addscrip() throws InterruptedException {
		Webhomepage webhome = new Webhomepage(driver);
		webhome.addscrip.click();
		webhome.Searchfield.click();
		webhome.Searchfield.sendKeys("TCS");
		webhome.addwlicon.click();
		webhome.closesearch.click();
		Thread.sleep(1000);

	}

	public void deletescrip() throws InterruptedException {
		Webhomepage webhome = new Webhomepage(driver);
		webhome.selectkebabmenu.click();
		webhome.removescrips.click();
		webhome.deletescrip.click();
		webhome.savebtn.click();
		long startTime = System.currentTimeMillis();
		String scripdeletemsg = webhome.deletescripmsg.getText();
		wait.until(ExpectedConditions.elementToBeClickable(webhome.deletescripmsg));
		System.out.println(scripdeletemsg);
		try {
			webhome.deletescripmsg.isDisplayed();
			status = "Pass";
		} catch (Exception e) {
			status = "Fail";
			System.out.println(e.getMessage());
		} finally {
			long endTime = System.currentTimeMillis();
			webhome.closesearch.click();
			Thread.sleep(7000);
			logger.logTableRow("delete scrip", status, endTime - startTime);
		}

		Thread.sleep(1000);

	}

	public void deletewatchlist() throws InterruptedException {
		Webhomepage webhome = new Webhomepage(driver);
		WebElement dropdownIcon = wait.until(ExpectedConditions.elementToBeClickable(webhome.Watchlistdropdown));
		dropdownIcon.click();
		webhome.deletewatchlist.click();
		Thread.sleep(1000);
		webhome.deletebtn.click();
		long startTime = System.currentTimeMillis();
		String wldeletemsg = webhome.deletewlmsg.getText();
		System.out.println(wldeletemsg);
		try {
			webhome.deletewlmsg.isDisplayed();
			status = "Pass";
		} catch (Exception e) {
			status = "Fail";
			System.out.println(e.getMessage());
		} finally {
			long endTime = System.currentTimeMillis();
			logger.logTableRow("delete Watchlist", status, endTime - startTime);
		}
	}

	public void indices() throws InterruptedException {
		Webhomepage webhome = new Webhomepage(driver);
		webhome.indice.click();

	}

	public void indiceschart() throws InterruptedException {
		Webhomepage webhome = new Webhomepage(driver);
		webhome.indicechart.click();
	}

	public void indiceoptionchain() throws InterruptedException {
		Webhomepage webhome = new Webhomepage(driver);
		webhome.indiceoptionchain.click();
	}

	public void editindice() {
		Webhomepage webhome = new Webhomepage(driver);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement editButton = wait.until(ExpectedConditions.elementToBeClickable(webhome.editindices));
		editButton.click();
		WebElement checkbox1 = wait.until(ExpectedConditions.elementToBeClickable(webhome.indicecheckbox1));
		if (!checkbox1.isSelected()) {
			checkbox1.click();
		} else {
			checkbox1.click();
		}
		WebElement checkbox2 = wait.until(ExpectedConditions.elementToBeClickable(webhome.indicecheckbox2));
		if (!checkbox2.isSelected()) {
			checkbox2.click();
		} else {
			checkbox2.click();
		}
		WebElement applyButton = wait.until(ExpectedConditions.elementToBeClickable(webhome.indiceapplycta));
		applyButton.click();
	}

	public void explorepage() {
		ExplorePage explore = new ExplorePage(driver);
		explore.explorepage.click();
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
				statusColor = "background-color:red;";
			} else if ("Pass".equalsIgnoreCase(status)) {
				statusColor = "background-color:green;";
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
	public void openbrowser() throws IOException {
		System.setProperty("chromedriver", Commons.getGlobalPropertiesValue("Nadeempath"));

		driver = new ChromeDriver();
		driver.get("https://invest.motilaloswal.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));

	}

	@AfterTest
	public void closebrowser() {
		if (driver != null) {
			driver.quit();
		}
	}
}
