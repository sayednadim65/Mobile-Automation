package Hybriddriven;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;

public class ReadtwoFiles {
	public static void read2sheet(WebDriver Driver) throws IOException, InterruptedException {
		File file = new File("C:\\Users\\nadeemuddinsayed\\Desktop\\Selenium sever\\HybridTestData.xlsx");
		FileInputStream fp = new FileInputStream(file);
		try (Workbook wb = new XSSFWorkbook(fp)) {
			XSSFSheet she1 = (XSSFSheet) wb.getSheet("testdata");
			int rowcount = she1.getLastRowNum();
			for (int i = 0; i < rowcount; i++) {
				String UN = she1.getRow(i).getCell(0).getStringCellValue();
				String ps = she1.getRow(i).getCell(1).getStringCellValue();

				XSSFSheet she2 = (XSSFSheet) wb.getSheet("keywords");
				for (int j = 0; j < she2.getLastRowNum(); j++) {
					String keywords = she2.getRow(j).getCell(0).getStringCellValue();
					if (keywords.equalsIgnoreCase("openurl")) {
						Methods.open(Driver);
					}
					if (keywords.equalsIgnoreCase("username")) {
						Methods.username(Driver, UN);
					}
					if (keywords.equalsIgnoreCase("hold")) {
						Methods.hold();
					}
					if (keywords.equalsIgnoreCase("password")) {
						Methods.password(Driver, ps);
					}
					if (keywords.equalsIgnoreCase("hold")) {
						Methods.hold();
					}
					if (keywords.equalsIgnoreCase("login")) {
						Methods.login(Driver);
					}
					if (keywords.equalsIgnoreCase("hold")) {
						Methods.hold();
					}
					if (keywords.equalsIgnoreCase("logout")) {
						Methods.logout(Driver);
					}
				}
			}
		}
	}
}
