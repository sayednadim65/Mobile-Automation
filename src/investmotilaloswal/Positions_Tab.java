package investmotilaloswal;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Positions_Tab {
	WebDriver wd;

	@Test(priority = 1)
	public void Positions_all() throws InterruptedException {
		 wd.findElement(By.xpath("//a[normalize-space()='Positions']")).click();
		 Thread.sleep(800);

		for (int i = 1; i <= 5; i++) {
			System.out.println("Iteration: " + (i + 1));
			
			// Start table HTML
			StringBuilder reportTable = new StringBuilder();
			reportTable.append("<table border='1' style='border-collapse: collapse; width: 75%; text-align: center;'>");
			reportTable.append("<tr><th>Iteration Count</th><th>Time Taken (ms)</th><th>Status</th></tr>");

			long startTime = System.currentTimeMillis(); // Start time
			String Overall_MTM = wd.findElement(By.xpath("//span[normalize-space()='Overall MTM']")).getText();
			boolean isVerified = Overall_MTM.equalsIgnoreCase("Overall MTM");
			String status = isVerified ? "Pass" : "Fail";
			long endTime = System.currentTimeMillis(); // End time
			long timeTaken = endTime - startTime; // Time calculation
			// Add iteration details to table
			reportTable.append("<tr><td>").append(i).append("</td><td>").append(timeTaken).append("</td><td>")
					.append(status).append("</td></tr>");
			// End table HTML
			reportTable.append("</table>");
			// Log table in the report
			Reporter.log(reportTable.toString(), true);

			wd.findElement(By.id("Position_two-tab")).click();
			Thread.sleep(500);
			wd.findElement(By.id("Position_one-tab")).click();
			Thread.sleep(500);
		}
	}
	

	@BeforeTest
	public void beforeTest() throws InterruptedException {
		System.setProperty("chromedriver",
				"C:\\Users\\nadeemuddinsayed\\Desktop\\Selenium sever\\driver\\chromedriver-win64\\chromedriver.exe");
		wd = new ChromeDriver();
		String url = "https://invest.motilaloswal.com/";
		wd.get(url);
		wd.manage().window().maximize();
		wd.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		wd.findElement(By.id("LoginId")).sendKeys("Y05120");
		wd.findElement(By.id("MainPassword")).sendKeys("Dell@4321");
		wd.findElement(By.xpath(
				"//a[@class='btn Primary_btn dflex aligncenter justifycenter mtop20 semibold openauthenticate w100']"))
				.click();
		wd.findElement(By.id("Login2FAPanDOB")).sendKeys("18052005 ");
		Thread.sleep(500);
		wd.findElement(By.xpath("//*[@id=\"btnVerify2FAPanDob\"]")).click();
		Thread.sleep(5000);
		wd.findElement(By.xpath("//button[@class='btn Primary_btn w100 mtop20']")).click();
		Thread.sleep(7000);
	}

	@AfterTest
	public void afterTest() {
		wd.quit();
	}

}
