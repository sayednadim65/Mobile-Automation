package keywordDrivenTesting;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class UserdefinedMethod {

	public static void open(WebDriver Driver) {
		Driver.get("https://www.saucedemo.com/");
	}
	public static void username(WebDriver Driver) {
		Driver.findElement(By.id("user-name")).sendKeys("standard_user");
	}
	public static void password(WebDriver Driver) {
		Driver.findElement(By.id("password")).sendKeys("secret_sauce");
	}
	public static void login (WebDriver Driver) {
		Driver.findElement(By.id("login-button")).click();
	}
	public static void logout (WebDriver Driver) throws InterruptedException {
		Driver.findElement(By.id("react-burger-menu-btn")).click();
		Thread.sleep(2000);
		Driver.findElement(By.id("logout_sidebar_link")).click();
		Thread.sleep(2000);
		Driver.quit();
	}
	public static void assertion (WebDriver Driver) {
		String s = Driver.findElement(By.xpath("//*[@id=\"header_container\"]/div[2]/span")).getText();
		if (s.equalsIgnoreCase("products")) {
		System.out.println("verified login successfully");}
		else {
			System.out.println("Verification failed");
		}
	}
}
