package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.ConfigReaders;
import utils.UIActions;
import utils.WaitUtils;

import java.time.Duration;

public class CartPage {
    WebDriver driver;
    UIActions uiActions;
    WaitUtils waitUtils;
    ProductPage pp;
    ConfigReaders configs = new ConfigReaders();
    String productName = configs.getProductOne();
    By addToCartButton = By.xpath("//button[contains(text(), 'ADD TO CART')]");
    By cartIcon = By.xpath("//a[@class='cart-icon']");
    By itemNameInCart = By.xpath("(//p[@class='product-name'])[1]");
    By itemCountInCart = By.xpath("(//p[@class='quantity'])[1]");
    By multipleItemsPriceInCart = By.xpath("(//*/Strong)[2]");
    By proceedToCheckout = By.xpath("//button[@type=\"button\"]");

    public CartPage(WebDriver driver) {
        this.driver = driver;
        this.uiActions = new UIActions(driver);
        this.waitUtils = new WaitUtils(driver, Duration.ofSeconds(20));
    }

    public void clickOnAddToCart() {
        uiActions = new UIActions(driver);
        waitUtils.waitForElementToBeVisible(addToCartButton);
        uiActions.click(addToCartButton);
    }

    public void openCart() {
        waitUtils.waitForElementToBeClickable(cartIcon);
        uiActions.click(cartIcon);
    }

    public boolean checkItemNameInCart() {
        waitUtils.waitForElementToBeVisible(itemNameInCart);
        String nameInCart = uiActions.getText(itemNameInCart);
        if (nameInCart.contains(productName)) {
            System.out.println("Product is added to cart and visible: " + nameInCart);
        } else {
            System.out.println("Product not found in cart.");
        }
        return true;
    }

    public boolean checkItemCountInCart() {
        waitUtils.waitForElementToBeVisible(itemCountInCart);
        String countInCart = uiActions.getText(itemCountInCart);
        if (countInCart.contains("1")) {
            System.out.println("Correct item count in cart and: " + countInCart);
        } else {
            System.out.println("Incorrect item count in cart.");
        }
        return true;
    }

    public int multipleItemsPrice(){
        pp = new ProductPage(driver);
        return pp.firstProductPrice() + pp.secondProductPrice() + pp.thirdProductPrice();
    }

    public int getTotalPriceInCart() {
        waitUtils.waitForElementToBeVisible(multipleItemsPriceInCart);
        String priceText = uiActions.getText(multipleItemsPriceInCart);
        return Integer.parseInt(priceText);
    }

    public void checkMultipleItemPriceInCart() {
       Integer totalCount = multipleItemsPrice();
       if (totalCount.equals(getTotalPriceInCart())) {
            System.out.println("Total item count in cart: " + getTotalPriceInCart());
        } else {
            System.out.println("Incorrect item count in cart.");
        }
    }

    public void clickOnProceedToCheckout() {
        waitUtils.waitForElementToBeClickable(proceedToCheckout);
        uiActions.click(proceedToCheckout);
    }


}