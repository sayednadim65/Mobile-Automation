package taskParallelExecution;

import java.time.Duration;
import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class testdemo {
	WebDriver driver;
	String url = "https://www.amazon.in";

	@Test(priority = 1)
	public void SearchMobileinSearchField() {
		System.out.println("searching mobiles");
		WebElement searchBox = driver.findElement(By.xpath("//input[@id='twotabsearchtextbox']"));
		searchBox.sendKeys("mobiles", Keys.ENTER);
	}

	@Test(priority = 2)
	public void ScrollTillSamsung() {
		System.out.println("scrolling to mobiles");
		WebElement samsung = driver.findElement(By.xpath(
				"//span[normalize-space()='Samsung Galaxy M05 (Mint Green, 4GB RAM, 64 GB Storage) | 50MP Dual Camera | Bigger 6.7\" HD+ Display | 5000mAh Battery | 25W Fast Charging | 2 Gen OS Upgrade & 4 Year Security Update | Without Charger']"));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true);", samsung);
		samsung.click();
	}

	@Test(priority = 3)
	public void tabSwitch() {
		System.out.println("switching tabs");
		String m_wnd = driver.getWindowHandle();
		Set<String> ch_wnd = driver.getWindowHandles();
		Iterator<String> it = ch_wnd.iterator();

		while (it.hasNext()) {
			String child = it.next();

			if (!m_wnd.equalsIgnoreCase(child)) {

				driver.switchTo().window(child);
				// driver.navigate().refresh();
			}
		}

	}

	@Test(priority = 4)
	public void clickOnAddtoCart() throws InterruptedException {
		System.out.println("clicking on add to cart");
		JavascriptExecutor scrolld = (JavascriptExecutor) driver;
		scrolld.executeScript("window.scrollBy(0,400)");
		driver.findElement(
				By.xpath("//div[@class='a-section a-spacing-none a-padding-none']//input[@id='add-to-cart-button']"))
				.click();

		Thread.sleep(3000);

	}

	@Test(priority = 5)
	public void clickOnCart() throws InterruptedException {
		System.out.println("clicking on cart");
		driver.findElement(By.xpath("//*[@id=\"attach-sidesheet-view-cart-button\"]/span/input")).click();
	}

	@BeforeTest
	private void launchBrowser() {
		System.setProperty("chromedriver",
				"C:\\Users\\nadeemuddinsayed\\Desktop\\Selenium sever\\driver\\chromedriver-win64\\chromedriver.exe");

		driver = new ChromeDriver();
		driver.get(url);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		System.out.println("Launching Browser");
	}
	
	@AfterTest
	public void quitBrowser () {
		driver.quit();
	}

}
