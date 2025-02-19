package Hybriddriven;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Methods {

	public static void open(WebDriver c) {
		c.get("https://www.saucedemo.com");
		c.manage().window().maximize();
	}

	public static void username(WebDriver c, String UN) {
		c.findElement(By.id("user-name")).sendKeys(UN);
	}

	public static void password(WebDriver c, String ps) {
		c.findElement(By.id("password")).sendKeys(ps);
	}

	public static void hold() throws InterruptedException {
		Thread.sleep(2000);
	}

	public static void login(WebDriver c) {
		c.findElement(By.id("login-button")).click();
	}

	public static void logout(WebDriver c) {
		c.findElement(By.id("react-burger-menu-btn")).click();
		c.findElement(By.xpath("//*[@id=\"react-burger-menu-btn\"]")).click();
		c.quit();
		// react-burger-menu-btn
	}
}
