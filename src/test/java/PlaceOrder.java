import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import pages.LandingPage;
import pages.ProductCatalogue;

import java.time.Duration;
import java.util.List;

public class PlaceOrder {

    public static void main(String[] args){

        String productName = "ZARA COAT 3";

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

        driver.findElement(By.cssSelector("[routerlink*='cart']")).click();

        List<WebElement> cartItems = driver.findElements(By.cssSelector(".cart h3"));

        Boolean match = cartItems.stream().anyMatch(item -> item.getText().equals(productName));

        Assert.assertTrue(match);

        driver.findElement(By.xpath("//button[normalize-space()='Checkout']")).click();

        Actions a = new Actions(driver);
        a.sendKeys(driver.findElement(By.cssSelector("[placeholder='Select Country'")), "india").build().perform();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
        driver.findElement(By.xpath("(//button[contains(@class, 'ta-item')])[2]")).click();
        driver.findElement(By.xpath("//a[normalize-space()='Place Order']")).click();
        String orderConfirmationMessage = driver.findElement(By.cssSelector(".hero-primary")).getText();
        Assert.assertTrue(orderConfirmationMessage.equalsIgnoreCase("Thankyou for the order."));

        driver.close();

    }
}
