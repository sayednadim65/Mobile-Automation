package selenium_practise3455;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class readExcel {

	public static void main(String[] args) throws IOException {

		// Accessing the file
		File fp = new File("C:\\Users\\nadeemuddinsayed\\Desktop\\Selenium sever\\testData.Xlsx");
		// Access Data
		FileInputStream ip = new FileInputStream(fp);
		try (// workbook
				Workbook wp = new XSSFWorkbook(ip)) {
			// sheet
			int a = wp.getNumberOfSheets();
			// System.out.println(a);

			for (int i = 0; i < a; i++) {
				if (wp.getSheetName(i).equalsIgnoreCase("sheet1")) {
					XSSFSheet sh1 = (XSSFSheet) wp.getSheetAt(i);
					Iterator<Row> rows = sh1.iterator();
					Row firstrow = rows.next();
					Iterator<Cell> c = firstrow.cellIterator();
					int k = 0;
					int column = 0;
					while (c.hasNext()) {
						Cell value = c.next();
						if (value.getStringCellValue().equalsIgnoreCase("username")) {
							column = k;
						}
						k++;
					}
					// System.out.println(column);
					while (rows.hasNext()) {
						Row r = rows.next();
						if (r.getCell(column).getStringCellValue().equalsIgnoreCase("un4")) {
							Iterator<Cell> cv = r.cellIterator();
							while (cv.hasNext()) {
								System.out.println(cv.next().getStringCellValue());

								
								/*
								 XSSFSheet sh13 = (XSSFSheet) wp.getSheetAt(0);
								 
								 // Cell & cell data
								 
								 String Celldata1 = sh13.getRow(1).getCell(0).getStringCellValue();
								 System.out.println(Celldata1); System.out.println("___________________");
								 String Celldata2 = sh13.getRow(1).getCell(1).getStringCellValue();
								 System.out.println(Celldata2); } System.out.println("___________________");
								 */
							}
						}
					}

				}
			}
			
			
			 
		}

	}
}
