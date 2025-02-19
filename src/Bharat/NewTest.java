package Bharat;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import drivers.DriverFactory;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;

public class NewTest {
	AndroidDriver Driver;
	
  @Test
  public void f() {
	  System.out.println("Test");
	  
  }
  
	@BeforeTest
	public void Verify_user_launch_app() throws InterruptedException, IOException {
		
/*
		if ("RealDevice".equalsIgnoreCase(Commons.getGlobalPropertiesValue("Execution"))) {

			System.out.println("Initializing Appium...");
			DesiredCapabilities capabilities = new DesiredCapabilities();
			capabilities.setCapability("platformName", "Android");
			capabilities.setCapability("platformVersion","12");
			capabilities.setCapability("deviceName","Galaxy M31");
			capabilities.setCapability("udid","RZ8N50B0WNB");
			capabilities.setCapability("appPackage", Commons.getGlobalPropertiesValue("Rise_app_package"));
			capabilities.setCapability("appActivity", Commons.getGlobalPropertiesValue("Rise_app_activity"));
			capabilities.setCapability("automationName", "UiAutomator2");
			capabilities.setCapability("autoGrantPermissions", true);
			capabilities.setCapability("noReset", true);

			Driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
			DriverFactory.addDriver(Driver);
			System.out.println("app launch succesfully");
			Driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		}

		else if ("BrowserStack".equalsIgnoreCase(Commons.getGlobalPropertiesValue("Execution"))) {
*/
			System.out.println("Starting browserstack");
			UiAutomator2Options capabilities = new UiAutomator2Options();

			HashMap<String, Object> bstackOptions = new HashMap<String, Object>();
			bstackOptions.put("userName", "dheeraj142");
			bstackOptions.put("accessKey", "MhpLs2spj2FFtYv9TFiV");
			bstackOptions.put("appiumVersion", "2.0.1");
			bstackOptions.put("debug", "true");
			bstackOptions.put("interactiveDebugging", "true");
			// bstackOptions.put("build", buildName);

			capabilities.setCapability("platformName", "android");
			capabilities.setCapability("appium:platformVersion", "14.0");
			capabilities.setCapability("appium:deviceName", "Google Pixel 8 Pro");
			capabilities.setCapability("appium:app", "bs://610bae8bd598ce355e633d4a101665e9a16248ba");
			capabilities.setCapability("appium:automationName", "UIAutomator2");
			capabilities.setCapability("autoGrantPermissions", true);
			capabilities.setCapability("bstack:options", bstackOptions);

			Driver = new AndroidDriver(new URL("https://hub-cloud.browserstack.com/wd/hub"), capabilities);
			System.out.println("app launch succesfully");

			DriverFactory.addDriver(Driver);

			Thread.sleep(500);

		}

	

}
