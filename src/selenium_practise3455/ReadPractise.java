package selenium_practise3455;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

public class ReadPractise {
//	private static final char[]  = null;

	public static void main(String[] args) throws IOException {

		System.setProperty("chromedriver",
				"C:\\Users\\nadeemuddinsayed\\Desktop\\Selenium sever\\driver\\chromedriver-win64\\chromedriver.exe");

		ChromeDriver wd; 

		// Accessing the file
		File fp = new File("C:\\Users\\nadeemuddinsayed\\Desktop\\Selenium sever\\testData.xlsx");
		// Access Data
		FileInputStream ip = new FileInputStream(fp);
		try (// workbook
				Workbook wp = new XSSFWorkbook(ip)) {
			// sheet
			int a = wp.getNumberOfSheets();
			// System.out.println(a);
			XSSFSheet sh13 = (XSSFSheet) wp.getSheetAt(0);

			// Cell & cell data

			for (int i = 0; i <= sh13.getLastRowNum(); i++) {
				String username = sh13.getRow(i).getCell(0).getStringCellValue();
				System.out.println(username);
				String password = sh13.getRow(i).getCell(1).getStringCellValue();
				System.out.println(password);

				wd = new ChromeDriver();
				wd.get("https://demo.guru99.com/test/newtours/");
				wd.findElement(By.name("userName")).sendKeys(username);
				wd.findElement(By.name("password")).sendKeys(password);
				
				wd.quit();

			}

		}
	}
}
