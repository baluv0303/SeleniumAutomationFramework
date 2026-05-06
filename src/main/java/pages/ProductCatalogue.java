package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ProductCatalogue extends BasePage {

    WebDriver driver;
    public ProductCatalogue(WebDriver driver){
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = ".mb-3")
    List<WebElement> products;

    @FindBy(css =".ng-animating")
    WebElement animator;



    By productBy = By.cssSelector(".mb-3");
    By addItem = By.cssSelector(".card-body button:last-of-type");
    By toast = By.cssSelector("#toast-container");

    public List<WebElement> getProductLists(){
        waitForElement(productBy);
        return products;
    }

    public WebElement getProductName(String productName){
        WebElement prod = products.stream().filter(product -> product.findElement(By.cssSelector("b"))
                .getText().equals(productName)).findFirst().orElse(null);
        return prod;
    }

    public void addItemToCart(String productName){
        WebElement prod = getProductName(productName);
        prod.findElement(addItem).click();
        waitForElement(toast);
        waitForElementToDisappear(animator);
    }

    public  void goToCartPage(){

    }


}
