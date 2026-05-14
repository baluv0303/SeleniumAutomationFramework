import BaseTest.BaseTest;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.CartPage;
import pages.ProductCatalogue;
import utils.RetryTests;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;


public class LoginInWithInvalidCredentialsTest extends BaseTest {

    @Test(dataProvider ="getData", groups = {"errorHandling"}, retryAnalyzer = RetryTests.class)
        public void loginWithInvalidCredentials() throws IOException {
           landingPage.performLogin("testmail@gmail.com", "Testuser");
           String errorMessage = landingPage.getErrorMessage();
           Assert.assertEquals(errorMessage , "Incorrect email password.");

        }

        @Test
        public void productErrorValidations(){
            String productName = "ZARA COAT 3";

            ProductCatalogue productCatalogue = landingPage.performLogin("balaji143mahi@gmail.com", "Test@123");
            List<WebElement> products =  productCatalogue.getProductLists();
            productCatalogue.addItemToCart(productName);
            CartPage cartPage = productCatalogue.goToCartPage();
            Boolean match = cartPage.verifyCartItemDisplay("ZARA COAT 3");
            Assert.assertTrue(match);
        }

    @DataProvider
    public Object[][] getData() throws IOException {
//        HashMap<String , String> input = new HashMap<>();
//        input.put("email", "balaji143mahi@gmail.com");
//        input.put("password", "Test@123");
//        input.put("productName", "ZARA COAT 3");
//
//        HashMap<String , String> input2 = new HashMap<>();
//        input2.put("email", "testbaluwmt@gmail.com");
//        input2.put("password", "Test@123");
//        input2.put("productName", "ADIDAS ORIGINAL");
//        return new Object[][] {{input}, {input2}};

        List<HashMap<String, String>> data =  getJsonToHashmap(System.getProperty("user.dir") + "//src//main//data//PurchaseOrder.json");

        return new Object[][] {{data.get(0)}, {data.get(1)}};
        //return new Object[][] {{"balaji143mahi@gmail.com", "Test@123" , "ZARA COAT 3"},{"testbaluwmt@gmail.com","Test@123","ADIDAS ORIGINAL"}};
    }
    }

