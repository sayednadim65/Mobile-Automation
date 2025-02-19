package Hybriddriven;

import java.io.IOException;

import org.openqa.selenium.chrome.ChromeDriver;

public class executionMain {

		public static void main(String[] args) throws IOException, InterruptedException {
			System.setProperty("chromedriver",
					"C:\\Users\\nadeemuddinsayed\\Desktop\\Selenium sever\\driver\\chromedriver-win64\\chromedriver.exe");

			ChromeDriver wd = new ChromeDriver(); 
			ReadtwoFiles.read2sheet(wd);

	}

}
