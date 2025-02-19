package drivers;

import java.util.ArrayList;
import java.util.List;

import io.appium.java_client.AppiumDriver;

public class DriverFactory {



		private static ThreadLocal<AppiumDriver > drivers = new ThreadLocal<>();

		// To quit the drivers and browsers at the end only.
		private static List<AppiumDriver > storedDrivers = new ArrayList<>();

		
		public static AppiumDriver  getDriver() {
			return drivers.get();
		}

		public static void addDriver(AppiumDriver  driver) {
			storedDrivers.add(driver);
			drivers.set(driver);
		}

		public static void removeDriver() {
			storedDrivers.remove(drivers.get());
			drivers.remove();
		}

		static {
			Runtime.getRuntime().addShutdownHook(new Thread() {
				public void run() {
					try {
						Thread.sleep(1000);
						storedDrivers.forEach(AppiumDriver::quit);
					} catch (InterruptedException e) {
						System.out.println(
								"Unable to close browser[driver] threads properly, please kill webdriver instances from Task Manager.");
					}
				}
			});
		}

	}

