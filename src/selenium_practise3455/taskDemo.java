package selenium_practise3455;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class taskDemo {

	WebDriver wd;
	String url = "https://www.google.com/";

	@Test(priority = 1)
	public void GoToGoogle() {
		wd.get(url);
		wd.manage().window().maximize();
		wd.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
	}

	@Test(dependsOnMethods = "GoToGoogle")
	public void Search() throws InterruptedException {
		wd.findElement(By.id("APjFqb")).sendKeys("swag labs", Keys.ENTER);
		Thread.sleep(500);
	}

	@Test(dependsOnMethods = "Search")
	public void clickOnFirstLink() throws InterruptedException {
		wd.findElement(By.xpath("//h3[normalize-space()='Swag Labs']")).click();
		Thread.sleep(5000);
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
	
	@BeforeClass
	
public void toTestbeforeclass() {
		System.out.println("this is before class");
		
	}
	
	@AfterClass
	public void toTestAfterclass() {
		System.out.println("this is to test after class");
	}

}
