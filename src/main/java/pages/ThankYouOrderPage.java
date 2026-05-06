package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ThankYouOrderPage extends BasePage {
    WebDriver driver;

    public ThankYouOrderPage(WebDriver driver){
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = ".hero-primary")
    WebElement orderPlacedText;


    public String  getOrderConfirmationText(){
        return orderPlacedText.getText();
    }
}
