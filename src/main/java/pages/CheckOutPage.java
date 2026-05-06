package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckOutPage extends BasePage{

    WebDriver driver;

    public CheckOutPage(WebDriver driver){
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css ="[placeholder='Select Country']")
    WebElement country;

    @FindBy(xpath = "(//button[contains(@class, 'ta-item')])[2]")
    WebElement selectCountry;

    @FindBy(xpath = "//a[normalize-space()='Place Order']")
    WebElement placeOrderButton;

    By results = By.cssSelector(".ta-results");

    public void selectCountry(String countryName){
        Actions a = new Actions(driver);
        a.sendKeys(country, countryName).build().perform();
        waitForElement(results);
        selectCountry.click();
    }

    public ThankYouOrderPage placeOrder(){
        placeOrderButton.click();
        return new ThankYouOrderPage(driver);
    }
}
