package pageobjects;

import java.time.Duration;
import java.util.Arrays;
import java.util.Collections;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.android.AndroidDriver;

public class ResusableMethods {
	AndroidDriver Driver;

	public ResusableMethods(AndroidDriver Driver) {
		this.Driver = Driver;
		PageFactory.initElements(Driver, this);
	}

	public static void tapWithActions(AndroidDriver Driver, int x, int y) throws InterruptedException {

		PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
		Sequence tap = new Sequence(finger, 1);

		tap.addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), x, y));
		tap.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
		Thread.sleep(500);
		tap.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

		Driver.perform(Collections.singletonList(tap));
	}

	public static void horizontalSwipetillElement(AndroidDriver Driver, WebElement ElementToFind, int swipeCount,
			int maxSwipes, int startX, int endX, int centerY) {

		boolean elementFound = false;
		while (!elementFound && swipeCount < maxSwipes) {
			try {
				// Check if the element is present
				WebElement element = new WebDriverWait(Driver, Duration.ofSeconds(1))
						.until(ExpectedConditions.visibilityOf(ElementToFind));
				elementFound = element.isDisplayed();
			} catch (Exception e) {
				// Element not found yet, perform a swipe Create swipe action

				PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
				Sequence swipe = new Sequence(finger, 1)
						.addAction(finger
								.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), startX, centerY))
						.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
						.addAction(finger.createPointerMove(Duration.ofMillis(500), PointerInput.Origin.viewport(),
								endX, centerY))
						.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

				Driver.perform(Arrays.asList(swipe));
				swipeCount++;
				System.out.println("Performed swipe #" + swipeCount);
			}

		}

	}

	public static void verticalswipetillElement(AndroidDriver Driver, WebElement ElementToFind, int swipeCount,
			int maxSwipes, int centerX, int startY, int endY) {

		boolean elementFound = false;

		while (!elementFound && swipeCount < maxSwipes) {
			try {
				// Check if the element is present
				WebElement element = new WebDriverWait(Driver, Duration.ofSeconds(1))
						.until(ExpectedConditions.visibilityOf(ElementToFind));
				elementFound = element.isDisplayed();
			} catch (Exception e) {
				// Element not found yet, perform a swipe
				PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
				Sequence swipe = new Sequence(finger, 1)
						.addAction(finger
								.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), centerX, startY))
						.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
						.addAction(finger.createPointerMove(Duration.ofMillis(500), PointerInput.Origin.viewport(),
								centerX, endY))
						.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

				Driver.perform(Arrays.asList(swipe));

				swipeCount++;
				System.out.println("Performed swipe #" + swipeCount);
			}
		}
	}

	public static void swipeCorinates(AndroidDriver Driver, int startX, int startY, int endX, int endY,
			int swipeCount) {
		PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
		for (int i = 1; i <= swipeCount; i++) {
			Sequence swipe = new Sequence(finger, 1)
					.addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), startX, startY))
					.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg())).addAction(finger
							.createPointerMove(Duration.ofMillis(500), PointerInput.Origin.viewport(), endX, endY))
					.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

			Driver.perform(Arrays.asList(swipe));

			System.out.println("Performed swipe #" + i);
		}
	}

	public static void longpressElement(AndroidDriver Driver, WebElement elementtolongpress) {
		// Get element location
		int elementX = elementtolongpress.getRect().getX() + (elementtolongpress.getRect().getWidth() / 2);
		int elementY = elementtolongpress.getRect().getY() + (elementtolongpress.getRect().getHeight() / 2);
		// Create a PointerInput instance
		PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
		// Create a sequence for the long press
		Sequence longPress = new Sequence(finger, 1)
				.addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), elementX, elementY))
				.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg())) // Press down
				.addAction(finger.createPointerMove(Duration.ofMillis(2000), PointerInput.Origin.viewport(), elementX,
						elementY)) // Hold for 2 seconds
				.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg())); // Release
		// Perform the long press
		Driver.perform(Arrays.asList(longPress));

	}

	public static void cleartextandenterinput(AndroidDriver Driver, WebElement textbox, String text) {
		textbox.click();
		textbox.clear();
		textbox.sendKeys(text);
	}

	public static void longPressWithActions(AndroidDriver Driver, int x, int y, long pressDurationMillis) {
		PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
		Sequence longPress = new Sequence(finger, 1);
		// Move the pointer to the desired coordinates
		longPress.addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), x, y));
		// Press down at the location
		longPress.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
		// Perform the press down action
		Driver.perform(Collections.singletonList(longPress));
		// Sleep for the desired long press duration
		try {
			Thread.sleep(pressDurationMillis); // Long press duration
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		// Now release the press
		longPress = new Sequence(finger, 1);
		longPress.addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), x, y));
		longPress.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

		// Perform the release action
		Driver.perform(Collections.singletonList(longPress));
	}

}
