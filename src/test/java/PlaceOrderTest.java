import BaseTest.BaseTest;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class PlaceOrderTest extends BaseTest {
      //String productName = "ZARA COAT 3";
  @Test(dataProvider ="getData", groups = {"Purchase"})
  public void placeOrder(HashMap<String, String> input) throws IOException {


        String country = "India";
        String orderConfirmMessageExpected = "Thankyou for the order.";

        ProductCatalogue productCatalogue = landingPage.performLogin(input.get("email"), input.get("password"));
        List<WebElement> products =  productCatalogue.getProductLists();
        productCatalogue.addItemToCart(input.get("productName"));
        CartPage cartPage = productCatalogue.goToCartPage();
        Boolean match = cartPage.verifyCartItemDisplay(input.get("productName"));
        Assert.assertTrue(match);
        CheckOutPage checkOutPage = cartPage.goToCheckout();
        checkOutPage.selectCountry(country);
        ThankYouOrderPage thankYouOrderPage = checkOutPage.placeOrder();
        String orderConfirmationMessage = thankYouOrderPage.getOrderConfirmationText();
        Assert.assertTrue(orderConfirmationMessage.equalsIgnoreCase(orderConfirmMessageExpected));
    }

    @Test(dependsOnMethods = {"placeOrder"})
    public void OrderVerificationTest(){
        ProductCatalogue productCatalogue = landingPage.performLogin("balaji143mahi@gmail.com", "Test@123");
        OrderHistoryPage orderHistoryPage = productCatalogue.goToOrderHistory();
        Assert.assertTrue(orderHistoryPage.verifyOrderDisplay("ZARA COAT 3"));
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
