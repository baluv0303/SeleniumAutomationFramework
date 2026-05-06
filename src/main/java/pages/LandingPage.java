package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LandingPage extends BasePage {
    WebDriver driver;

    public LandingPage(WebDriver driver){
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(id="userEmail")
    WebElement userEmail;

    @FindBy(id="userPassword")
    WebElement usePassword;

    @FindBy(id = "login")
    WebElement loginButton;

    public void performLogin(String email, String password){
        userEmail.sendKeys(email);
        usePassword.sendKeys(password);
        loginButton.click();
    }

    public void goTo(){
        driver.get("https://rahulshettyacademy.com/client");
    }


    public static class BasePage {

    }
}
