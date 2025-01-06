package investmotilaloswal;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Global_Search {
	WebDriver wd;

	@Test(priority = 1)
	public void Global_Search_Results() throws InterruptedException {
		for (int i = 0; i < 100; i++) {
			System.out.println("Iteration: " + (i + 1));

			// Global Search
			wd.findElement(By.id("inputSearch")).click();
			wd.findElement(By.id("inputSearch")).sendKeys("idea");
			Thread.sleep(1000);
			//wd.findElement(By.id("143660")).click();// Verification of Result
			String idea = wd.findElement(By.id("143660")).getText();
			System.out.println(idea);

			// Start table HTML
			StringBuilder reportTable = new StringBuilder();
			reportTable.append("<table border='1' style='border-collapse: collapse; width: 75%; text-align: center;'>");
			reportTable.append("<tr><th>Iteration Count</th><th>Time Taken (ms)</th><th>Status</th></tr>");
			long startTime = System.currentTimeMillis(); // Start time
			//Thread.sleep(500);
			String idea1 = wd.findElement(By.id("143660")).getText();
			boolean isVerified = idea1.equalsIgnoreCase("idea");
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

			// Cancel Global Search
			wd.findElement(By.xpath("//*[@id=\"root\"]/div[1]/div[2]/div[1]/div/div/div[1]/img[2]")).click();
			Thread.sleep(800);
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
		wd.findElement(By.id("btnVerify2FAPanDob")).click();
		Thread.sleep(5000);
		wd.findElement(By.xpath("//button[@class='btn Primary_btn w100 mtop20']")).click();
		Thread.sleep(7000);
	}

	@AfterTest
	public void afterTest() {
		wd.quit();
	}

}
