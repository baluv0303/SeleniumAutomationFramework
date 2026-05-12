import BaseTest.BaseTest;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.*;

import java.io.IOException;
import java.util.List;

public class PlaceOrderTest extends BaseTest {
      String productName = "ZARA COAT 3";
  @Test
  public void placeOrder() throws IOException {


        String country = "India";
        String orderConfirmMessageExpected = "Thankyou for the order.";

        ProductCatalogue productCatalogue = landingPage.performLogin("balaji143mahi@gmail.com", "Test@123");
        List<WebElement> products =  productCatalogue.getProductLists();
        productCatalogue.addItemToCart(productName);
        CartPage cartPage = productCatalogue.goToCartPage();
        Boolean match = cartPage.verifyCartItemDisplay(productName);
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
        Assert.assertTrue(orderHistoryPage.verifyOrderDisplay(productName));
    }
}
