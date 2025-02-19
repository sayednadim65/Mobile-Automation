package pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.android.AndroidDriver;

public class OrderForm {
	AndroidDriver Driver;
	
	public OrderForm(AndroidDriver Driver) {
		this.Driver = Driver;
		PageFactory.initElements(Driver, this);
	}

	@FindBy(xpath = "//android.view.View[@content-desc=\"Market\"]")
	public WebElement MarketButton;
	
	@FindBy (xpath = "//android.widget.EditText")
	public WebElement quantityMarket;
	
	@FindBy (xpath = "(//android.view.View)[22]")
	public WebElement investamount;
	
	@FindBy (xpath = "(//android.view.View)[23]")
	public WebElement quantityautocalculate;
	
	@FindBy (xpath = "(//android.widget.ImageView)[2]")
	public WebElement NseSwitch;
	
	@FindBy (xpath = "(//android.widget.ImageView)[3]")
	public WebElement BseSwitch;
	
	@FindBy (xpath = "(//android.view.View)[16]")
	public WebElement amountswitch;
	
	@FindBy (xpath = "(//android.widget.EditText)[2]")
	public WebElement limitprice;
	
	@FindBy (xpath = "//android.widget.Button[@content-desc=\"View Order\"]")
	public WebElement vieworder;
	
	@FindBy (xpath = "//android.view.View[@content-desc=\"Disclaimer\"]")
	public WebElement disclaimer;
	
	@FindBy (xpath = "//android.view.View[@content-desc=\"Confirm your order\"]")
	public WebElement confirmorder;
	
	@FindBy (xpath = "//android.widget.Button[@content-desc=\"Done\"]")
	public WebElement done;
	
	@FindBy (xpath = "//android.view.View[@content-desc='Cancel']")
	public WebElement cancelbutton;
	
	@FindBy (xpath = "//android.widget.ImageView[@content-desc=\"Cancel Orders\"]")
	public WebElement cancelbuttontext;
	
	@FindBy (xpath = "//android.view.View[contains(@content-desc,\"Cancelled\")]")
	public WebElement cancelorder;
}
