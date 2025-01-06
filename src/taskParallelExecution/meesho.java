package taskParallelExecution;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class meesho {
	WebDriver wd;
	String url = "https://www.meesho.com/";

	@Test
	public void T() throws InterruptedException {
		System.out.println("this is 2nd class");
		Thread.sleep(2000);
	}

	@BeforeTest
	public void beforeTest() {
		System.setProperty("chromedriver",
				"C:\\Users\\nadeemuddinsayed\\Desktop\\Selenium sever\\driver\\chromedriver-win64\\chromedriver.exe");

		wd = new ChromeDriver();
		System.out.println("Browser_Launched_successfully");
	}

	@AfterTest
	public void afterTest() {
		if (wd != null) {
			wd.quit();
		}
	}
}
