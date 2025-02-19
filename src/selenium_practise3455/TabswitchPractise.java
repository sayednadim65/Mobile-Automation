package selenium_practise3455;

import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


public class TabswitchPractise {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		WebDriver wd = new ChromeDriver();		
		String url = "https://demo.guru99.com/popup.php";
		wd.get(url);
		wd.manage().window().maximize();
		wd.findElement(By.xpath("//a[@href=\"../articles_popup.php\"]")).click();
		
		String m_wnd = wd.getWindowHandle();
		Set<String> ch_wnd = wd.getWindowHandles();
		Iterator<String>it = ch_wnd.iterator();
		
		while (it.hasNext()) {
			String child = it.next();
			
			if (!m_wnd.equalsIgnoreCase(child)) {
			
				wd.switchTo().window(child);
				wd.navigate().refresh();
				wd.findElement(By.xpath("//input[@name=\"emailid\"]")).sendKeys("abc@gmail.com");
				wd.findElement(By.xpath("//input[@name=\"btnLogin\"]")).click();
				System.out.println("Test pass");
			
			
			}
		}
	
	}

}


