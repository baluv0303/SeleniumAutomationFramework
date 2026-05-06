package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CartPage extends BasePage {
    WebDriver driver;
    public CartPage(WebDriver driver){
        super(driver);
        this.driver=driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = ".cart h3")
    List<WebElement> cartItems;

    @FindBy(xpath = "//button[normalize-space()='Checkout']")
    WebElement checkOutButton;


    public boolean verifyCartItemDisplay(String productName){
        return cartItems.stream().anyMatch(product -> product.getText().equalsIgnoreCase(productName));
    }

    public CheckOutPage goToCheckout(){
        checkOutButton.click();
        return new CheckOutPage(driver);
    }

}
