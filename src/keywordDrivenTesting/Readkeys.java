package keywordDrivenTesting;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;

public class Readkeys {

	public static void Dataread(WebDriver Driver) throws IOException, InterruptedException {
		// Accessing the file
		File fp = new File("C:\\Users\\nadeemuddinsayed\\Desktop\\Selenium sever\\keyword driven.xlsx");
		// Access Data
		FileInputStream ip = new FileInputStream(fp);
		try (// workbook
				Workbook wp = new XSSFWorkbook(ip) {

				}) {
			XSSFSheet she1 = (XSSFSheet) wp.getSheetAt(0);

			for (int i = 0; i <= she1.getLastRowNum(); i++) {
				String keys = she1.getRow(i).getCell(0).getStringCellValue();
				System.out.println(keys);
				// String password = she1.getRow(i).getCell(1).getStringCellValue();
				// System.out.println(password);

				if (keys.equalsIgnoreCase("Openurl")) {
					UserdefinedMethod.open(Driver);
				}
				if (keys.equalsIgnoreCase("username")) {
					UserdefinedMethod.username(Driver);
				}
				if (keys.equalsIgnoreCase("password")) {
					UserdefinedMethod.password(Driver);
				}
				if (keys.equalsIgnoreCase("login")) {
					UserdefinedMethod.login(Driver);
				}

				if (keys.equalsIgnoreCase("verify")) {
					UserdefinedMethod.assertion(Driver);
				}
				if (keys.equalsIgnoreCase("logout")) {
					UserdefinedMethod.logout(Driver);
				}

			}
		}
		;
	}
}
