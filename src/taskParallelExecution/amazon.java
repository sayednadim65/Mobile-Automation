package taskParallelExecution;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class amazon {
	WebDriver wd;
	String url = "https://demo.guru99.com/test/newtours/";

	@Test(priority = 1, dataProvider = "dp")
	public void f(String n, String s) throws InterruptedException {
		System.out.println("this is 1st class");
		wd.get(url);
		wd.findElement(By.name("userName")).sendKeys(n);
		wd.findElement(By.name("password")).sendKeys(s);
		Thread.sleep(1000);
		wd.findElement(By.id("submit")).click();
		Thread.sleep(2000);
	}

	@Test(priority = 2)
	public void signoff() {
//		wd.findElement(By.xpath("//a[normalize-space()='SIGN-OFF']")).click();
		System.out.println("logout");
	}

	@DataProvider
	public Object[][] dp() {

		Object[][] data = new Object[2][2];

		data[0][0] = "demo";
		data[0][1] = "demo";
		data[1][1] = "test";
		data[1][1] = "test";

		return data;
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
