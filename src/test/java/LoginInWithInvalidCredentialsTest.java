import BaseTest.BaseTest;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.CartPage;
import pages.ProductCatalogue;

import java.io.IOException;
import java.util.List;


public class LoginInWithInvalidCredentialsTest extends BaseTest {

        @Test(groups = {"errorHandling"})
        public void loginWithInvalidCredentials() throws IOException {
           landingPage.performLogin("balaji143mahi@gmail.com", "Test@12");
           String errorMessage = landingPage.getErrorMessage();
           Assert.assertEquals(errorMessage , "Incorrect email or password.");

        }

        @Test
        public void productErrorValidations(){
            String productName = "ZARA COAT 3";

            ProductCatalogue productCatalogue = landingPage.performLogin("balaji143mahi@gmail.com", "Test@123");
            List<WebElement> products =  productCatalogue.getProductLists();
            productCatalogue.addItemToCart(productName);
            CartPage cartPage = productCatalogue.goToCartPage();
            Boolean match = cartPage.verifyCartItemDisplay("ZARA COAT 33");
            Assert.assertTrue(match);
        }
    }

