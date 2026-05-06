import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import pages.*;

import java.time.Duration;
import java.util.List;

public class PlaceOrder {

    public static void main(String[] args){

        String productName = "ZARA COAT 3";
        String country = "India";
        String orderConfirmMessageExpected = "Thankyou for the order.";

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        LandingPage landingPage = new LandingPage(driver);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        landingPage.goTo();
        driver.manage().window().maximize();
        landingPage.performLogin("balaji143mahi@gmail.com", "Test@123");
        ProductCatalogue productCatalogue = new ProductCatalogue(driver);
        List<WebElement> products =  productCatalogue.getProductLists();
        productCatalogue.addItemToCart(productName);
        productCatalogue.goToCartPage();
        CartPage cartPage = new CartPage(driver);
        Boolean match = cartPage.verifyCartItemDisplay(productName);
        Assert.assertTrue(match);
        CheckOutPage checkOutPage = cartPage.goToCheckout();
        checkOutPage.selectCountry(country);
        ThankYouOrderPage thankYouOrderPage = checkOutPage.placeOrder();
        String orderConfirmationMessage = thankYouOrderPage.getOrderConfirmationText();
        Assert.assertTrue(orderConfirmationMessage.equalsIgnoreCase(orderConfirmMessageExpected));
        driver.close();
    }
}
