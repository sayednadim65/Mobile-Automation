package investmotilaloswal;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class WebsiteTest {
	WebDriver wd;
	int Positions_all_loop = 0;
	int Portfolio_Mutual_fund_loop = 0;
	int Global_Search_Results_loop = 100;
	int Portfolio_Equity_loop = 0;
	int All_Portfolio_loop = 0;
	String url = "https://invest.motilaloswal.com/";
	String userId = "Y05120";
	String password = "Dell@4321";
	String dob = "18052005 ";

	@Test(priority = 5)
	public void Positions_all() throws InterruptedException {
		String tableName = "Positions_all";
		logTableStart(tableName);

		wd.findElement(By.xpath("//a[normalize-space()='Positions']")).click();
		Thread.sleep(800);

		for (int i = 1; i <= Positions_all_loop; i++) {
			long startTime = System.currentTimeMillis(); // Start time

			WebDriverWait wait = new WebDriverWait(wd, Duration.ofSeconds(10));
			WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//label[@for='All']")));
			String Closed_Positions = element.getText();
			String Net_position = Closed_Positions.substring(0, 3);
			System.out.println(Net_position);
			boolean isVerified = Net_position.equalsIgnoreCase("Net");
			String status = isVerified ? "Pass" : "Fail";

			long endTime = System.currentTimeMillis(); // End time
			long timeTaken = endTime - startTime; // Time calculation

			logTableRow(tableName, i, timeTaken, status);

			wd.findElement(By.id("Position_two-tab")).click();
			Thread.sleep(100);
			wd.findElement(By.id("Position_one-tab")).click();
			Thread.sleep(300);
		}

		logTableEnd(tableName);
	}

	@Test(priority = 2)
	public void Portfolio_Mutual_fund() throws InterruptedException {
		String tableName = "Portfolio_Mutual_fund";
		logTableStart(tableName);

		wd.findElement(By.xpath("//a[normalize-space()='Portfolio']")).click();
		Thread.sleep(800);

		for (int i = 1; i <= Portfolio_Mutual_fund_loop; i++) {

			wd.findElement(By.id("Portfolio_two-tab")).click();
			long startTime = System.currentTimeMillis(); // Start time

			WebDriverWait wait = new WebDriverWait(wd, Duration.ofSeconds(10));
			WebElement element = wait.until(ExpectedConditions
					.visibilityOfElementLocated(By.xpath("//label[normalize-space()='Current Value']")));
			String currentValue = element.getText();
			System.out.println(currentValue);
			boolean isVerified = currentValue.equalsIgnoreCase("current value");
			String status = isVerified ? "Pass" : "Fail";

			long endTime = System.currentTimeMillis(); // End time
			long timeTaken = endTime - startTime; // Time calculation

			logTableRow(tableName, i, timeTaken, status);

			wd.findElement(By.id("Portfolio_five-tab")).click();
		}

		logTableEnd(tableName);
	}

	@Test(priority = 1)
	public void Global_Search_Results() throws InterruptedException {
		String tableName = "Global_Search_Results";
		logTableStart(tableName);

		for (int i = 1; i <= Global_Search_Results_loop; i++) {
			wd.findElement(By.id("inputSearch")).click();
			wd.findElement(By.id("inputSearch")).sendKeys("idea");

			long startTime = System.currentTimeMillis(); // Start time

			WebDriverWait wait = new WebDriverWait(wd, Duration.ofSeconds(10));
			WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.id("143660")));
			String idea1 = element.getText();
			System.out.println(idea1);
			boolean isVerified = idea1.equalsIgnoreCase("idea");
			String status = isVerified ? "Pass" : "Fail";
			long endTime = System.currentTimeMillis(); // End time
			long timeTaken = endTime - startTime; // Time calculation

			logTableRow(tableName, i, timeTaken, status);
			wd.findElement(By.xpath("//*[@id=\"root\"]/div[1]/div[2]/div[1]/div/div/div[1]/img[2]")).click();
			Thread.sleep(500);
		}

		logTableEnd(tableName);
	}

	@Test(priority = 3)
	public void Portfolio_Equity() throws InterruptedException {
		String tableName = "Portfolio_Equity";
		logTableStart(tableName);

		wd.findElement(By.xpath("//a[normalize-space()='Portfolio']")).click();
		Thread.sleep(800);
		for (int i = 1; i <= Portfolio_Equity_loop; i++) {
			System.out.println("Iteration: " + (i + 1));

			wd.findElement(By.id("Portfolio_one-tab")).click();
			long startTime = System.currentTimeMillis(); // Start time
			WebDriverWait wait = new WebDriverWait(wd, Duration.ofSeconds(10));
			WebElement element = wait
					.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[contains(@class,'f14')]")));
			String equity_portfolio_value = element.getText();
			System.out.println(equity_portfolio_value);
			boolean isVerified1 = equity_portfolio_value.equalsIgnoreCase("current value");
			String status = isVerified1 ? "Pass" : "Fail";
			long endTime = System.currentTimeMillis(); // End time
			long timeTaken = endTime - startTime; // Time calculation
			logTableRow(tableName, i, timeTaken, status);

			wd.findElement(By.id("Portfolio_five-tab")).click();
			Thread.sleep(500);
		}
		logTableEnd(tableName);
	}

	@Test(priority = 4)
	public void All_Portfolio() throws InterruptedException {
		String tableName = "All_Portfolio";
		logTableStart(tableName);

		wd.findElement(By.xpath("//a[normalize-space()='Portfolio']")).click();
		Thread.sleep(200);
		for (int i = 1; i <= All_Portfolio_loop; i++) {
			System.out.println("Iteration: " + (i + 1));

			wd.findElement(By.id("Portfolio_five-tab")).click();
			Thread.sleep(500);
			long startTime = System.currentTimeMillis(); // Start time
			WebDriverWait wait = new WebDriverWait(wd, Duration.ofSeconds(10));
			WebElement element = wait.until(ExpectedConditions
					.visibilityOfElementLocated(By.xpath("//label[normalize-space()='Current Value']")));
			String current_value = element.getText();
			System.out.println(current_value);
			boolean isVerified = current_value.equalsIgnoreCase("current value");
			String status = isVerified ? "Pass" : "Fail";
			long endTime = System.currentTimeMillis(); // End time
			long timeTaken = endTime - startTime; // Time calculation
			logTableRow(tableName, i, timeTaken, status);
			wd.findElement(By.id("Portfolio_one-tab")).click();
			Thread.sleep(500);
		}
		logTableEnd(tableName);
	}

	// Helper Methods for Logging Tables
	private void logTableStart(String tableName) {
		Reporter.log("<h3>" + tableName + "</h3>", true);
		Reporter.log("<table border='1' style='border-collapse: collapse; width: 75%; text-align: center;'>", true);
		Reporter.log("<tr><th>Iteration Count</th><th>Time Taken (ms)</th><th>Status</th></tr>", true);
	}

	private void logTableRow(String tableName, int iteration, long timeTaken, String status) {
		Reporter.log("<tr><td>" + iteration + "</td><td>" + timeTaken + "</td><td>" + status + "</td></tr>", true);
	}

	private void logTableEnd(String tableName) {
		Reporter.log("</table>", true);
	}

	@BeforeTest
	public void beforeTest() throws InterruptedException {
		System.setProperty("chromedriver",
				"C:\\Users\\nadeemuddinsayed\\Desktop\\Selenium sever\\driver\\chromedriver-win64\\chromedriver.exe");

		wd = new ChromeDriver();
		wd.get(url);
		wd.manage().window().maximize();
		wd.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		wd.findElement(By.id("LoginId")).sendKeys(userId);
		wd.findElement(By.id("MainPassword")).sendKeys(password);
		wd.findElement(By.xpath(
				"//a[@class='btn Primary_btn dflex aligncenter justifycenter mtop20 semibold openauthenticate w100']"))
				.click();
		wd.findElement(By.id("Login2FAPanDOB")).sendKeys(dob);
		Thread.sleep(1000);
		wd.findElement(By.xpath("//*[@id=\"btnVerify2FAPanDob\"]")).click();
		Thread.sleep(5000);
		wd.findElement(By.xpath("//button[@class='btn Primary_btn w100 mtop20']")).click();
		Thread.sleep(5000);
	}

	@AfterTest
	public void afterTest() {
		if (wd != null) {
			wd.quit();
		}
	}
}
