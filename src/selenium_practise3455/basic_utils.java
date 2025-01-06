package selenium_practise3455;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class basic_utils {
	static WebDriver wd;
	Date A = new Date();
	LocalDateTime now = LocalDateTime.now(); 
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss"); 
    String formattedTime = now.format(formatter);
	

    public static void main(String[] args) throws IOException {
	}

	public void launchBrowser () throws IOException, InterruptedException {
		
		System.setProperty("chromedriver","C:\\Users\\nadeemuddinsayed\\Desktop\\Selenium sever\\driver\\chromedriver-win64\\chromedriver.exe");
		wd = new ChromeDriver();		
		String url = "https://www.myntra.com/wall-lamp?rawQuery=wall%20lamp";
		wd.get(url);
		System.out.println("Browser launched sucessfully"+now);
		wd.manage().window().maximize();
		System.out.println("Browser maximized"+now);
		Thread.sleep(2000);
	}
	public void closeBroswer() throws InterruptedException {
	wd.quit();
	System.out.println("Browser close sucessfully"+now);

	}
	
	public void Screenshot () throws IOException {
		
		TakesScreenshot ss = ((TakesScreenshot)wd);
		File capture = ss.getScreenshotAs(OutputType.FILE);
		File location = new File ("C:\\Users\\nadeemuddinsayed\\Desktop\\Selenium sever\\SS\\PicCaptured66.png");
		FileUtils.copyFile(capture, location);
		System.out.println("Screen capture sucessfully");
		
	}
	
}
