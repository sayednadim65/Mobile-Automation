package keywordDrivenTesting;

import java.io.IOException;

import org.openqa.selenium.chrome.ChromeDriver;

public class AddressDriver {

	public static void main(String[] args) throws IOException, InterruptedException {
		System.setProperty("chromedriver",
				"C:\\Users\\nadeemuddinsayed\\Desktop\\Selenium sever\\driver\\chromedriver-win64\\chromedriver.exe");

		
		
		
		ChromeDriver wd = new ChromeDriver(); 
		Readkeys.Dataread(wd);
		// TODO Auto-generated method stub

	}

}
