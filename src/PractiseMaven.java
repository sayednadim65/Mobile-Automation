import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class PractiseMaven {

	public PractiseMaven() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) throws InterruptedException {

		System.setProperty("chromedriver",
				"C:\\Users\\nadeemuddinsayed\\Desktop\\Selenium sever\\driver\\chromedriver-win64\\chromedriver.exe");
		WebDriver wd = new ChromeDriver();
		String url = "https://www.hyrtutorials.com/p/alertsdemo.html";
		wd.get(url);
		wd.manage().window().maximize();

		JavascriptExecutor js = (JavascriptExecutor) wd;
		js.executeScript("window.scrollBy(0,200)");

		wd.findElement(By.id("alertBox")).click();
		Alert alert = wd.switchTo().alert();
		System.out.println("Alert text: " + alert.getText());
		Thread.sleep(2000);
		alert.accept();
		System.out.println("Alert accepted successfully!");

		wd.findElement(By.id("confirmBox")).click();
		System.out.println("Alert text: " + alert.getText());
		Thread.sleep(2000);
		alert.accept();
		System.out.println("Alert accepted successfully!");

		wd.findElement(By.id("promptBox")).click();
		System.out.println("Alert text: " + alert.getText());
		Thread.sleep(2000);
		String existing_text = alert.getText();
		System.out.println(existing_text);
		alert.sendKeys("");
		alert.sendKeys("Nadeem Sayed");
		Thread.sleep(2000);
		alert.accept();
		System.out.println("Alert accepted successfully!");

	}

}
