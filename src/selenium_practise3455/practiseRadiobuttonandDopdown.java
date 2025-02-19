package selenium_practise3455;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class practiseRadiobuttonandDopdown{

	@SuppressWarnings("unused")
	public static void main(String[] args) throws Exception{
		WebDriver wd = new ChromeDriver();		
		String url = "https://www.tutorialspoint.com/selenium/practice/selenium_automation_practice.php";
		wd.get(url);
		wd.manage().window().maximize();
		
		wd.findElement(By.id("name")).sendKeys("Nadeem");
		wd.findElement(By.id("email")).sendKeys("abc@gmail.com");
		wd.findElement(By.id("gender")).click();
		wd.findElement(By.id("mobile")).sendKeys("12345678");
		wd.findElement(By.id("dob")).sendKeys("10021993 ");
		wd.findElement(By.id("subjects")).sendKeys("english");
		
		
		WebElement px = wd.findElement(By.id("state"));
		
		Point pnt1 = px.getLocation();
		System.out.println("X axis : "+pnt1.getX());
		System.out.println("Y axis : "+pnt1.getY());
		
		int xaxis = pnt1.getX();
		int yaxis = pnt1.getY();
		
		JavascriptExecutor js = (JavascriptExecutor)wd;
		js.executeScript("window.scrollBy(643,700)");
		
		
		wd.findElement(By.xpath("//div[7]//div[1]//div[1]//div[3]//input[1]")).click();
		WebElement state = wd.findElement(By.id("state"));
		Select dropdown = new Select(state);
		dropdown.selectByValue("Uttar Pradesh");
		Thread.sleep(2000);
		
		WebElement city = wd.findElement(By.id("city"));
		Select dropdown1 = new Select (city);
		dropdown1.selectByValue("Agra");
		Thread.sleep(2000);
		
		}		
}

