package pageobjects;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class LoginPage {
	AndroidDriver driver;

	public LoginPage(AndroidDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// login To Rise Text
	@FindBy(xpath = "//android.view.View[@content-desc=\"Login\"]")
	public WebElement loginToRiseText;

	// UserID Text field
	@FindBy(className = "android.widget.EditText")
	public WebElement userID;

	// user Id Next Button
	@FindBy(xpath = "//android.view.View[@content-desc=\"Next\"]")
	public WebElement nextbutton;

//		___________________________________________________________________________	
	// enter Your Password Text
	@AndroidFindBy(accessibility = "Enter Your Password")
	public WebElement enterYourPasswordText;

	// Password Text Field
	@FindBy(className = "android.widget.EditText")
	public WebElement passwordTextField;

	// login button
	@FindBy(xpath = "//android.view.View[@content-desc=\"Login\"]")
	public WebElement loginButton;

//		____________________________________________________________________________
	// DOB Text
	@AndroidFindBy(accessibility = "Verify details to Login")
	public WebElement verifyDobText;

	// Dob Text Field
	@FindBy(className = "android.widget.EditText")
	public List <WebElement> dobTextField;

	// Confirm Dob Button
	@FindBy(xpath = "//android.view.View[@content-desc=\"Confirm\"]")
	public WebElement confirmDobButton;

	// Explore the app
	@FindBy(xpath = "//android.widget.Button[@content-desc=\"Explore the app\"]")
	public WebElement exploreTheAppButton;

	// RDD
	@FindBy(xpath = "//android.view.View[@content-desc=\"I Understand\"]")
	public WebElement iUnderstandRddButton;

//		___________________________________________________________________

}
