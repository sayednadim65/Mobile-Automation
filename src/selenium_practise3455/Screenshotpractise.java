package selenium_practise3455;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Screenshotpractise extends basic_utils{

	public static void main(String[] args) throws IOException {
		WebDriver wd = new ChromeDriver();		
		String url = "https://www.myntra.com/wall-lamp?rawQuery=wall%20lamp";
		wd.get(url);
		JavascriptExecutor js = (JavascriptExecutor) wd;
		js.executeScript("window.scrollBy(0,1500)");	

		Screenshotpractise a = new Screenshotpractise();
		a.Screenshot();
		wd.quit();
		
		
	}

	public void Screenshot () throws IOException {
		
		TakesScreenshot ss = ((TakesScreenshot)wd);
		File capture = ss.getScreenshotAs(OutputType.FILE);
		File location = new File ("C:\\Users\\nadeemuddinsayed\\Desktop\\Selenium sever\\SS\\PicCaptured6.png");
		FileUtils.copyFile(capture, location);
		System.out.println("Screen capture sucessfully");
		
		
		
	}
	
	
	
}
