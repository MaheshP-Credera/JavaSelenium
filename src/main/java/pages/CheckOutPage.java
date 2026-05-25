package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.ConfigReaders;
import utils.UIActions;
import utils.WaitUtils;

public class CheckOutPage {

    WebDriver driver;
    UIActions uiActions;
    WaitUtils waitUtils;
    ProductPage pp;
    CartPage cp;

    ConfigReaders configs = new ConfigReaders();
    String productName = configs.getProductOne();

    //Elements in CheckOut Page - 1
    By productNameInCart = By.cssSelector(".product-name");
    By totalAmtValue = By.cssSelector(".totAmt");
    By placeOrder = By.xpath("//button[text()='Place Order']");

    //Element in Checkout Page -2
    By countryDropdown = By.xpath("//*[@class='wrapperTwo']/div/select");
    By checkBox = By.cssSelector(".chkAgree");
    By proceedCTA = By.xpath("//button[text()='Proceed']");

    //Place Order success message
    By successMessage = By.xpath("//div[@class='wrapperTwo']/span");

    public CheckOutPage(WebDriver driver) {
        this.driver = driver;
        this.uiActions = new UIActions(driver);
        this.waitUtils = new WaitUtils(driver, java.time.Duration.ofSeconds(20));
        this.pp = new ProductPage(driver);
        this.cp = new CartPage(driver);
    }

    public String checkProductPriceInCheckout() {
        waitUtils.waitForElementToBeVisible(totalAmtValue);
        String totalAmount = uiActions.getText(totalAmtValue);
        return "Total amount in checkout: " + totalAmount;
    }

    public boolean productNameMatching() {
        pp = new ProductPage(driver);
        configs = new ConfigReaders();
        String firstProductName = configs.getProductOne();
        String productNameInCheckout = uiActions.getText(productNameInCart);
        if (productNameInCheckout.contains(firstProductName)) {
            System.out.println("Added product name matches with the product name in checkout: " + productNameInCheckout);
            return true;
        } else {
            System.out.println("Total amount does not match the product name. Total: " + productNameInCheckout);
            return false;
        }
    }

    public boolean productPriceMatching() {
        pp = new ProductPage(driver);
        configs = new ConfigReaders();
        String firstProductPrice = configs.getFirstProductPrice();
        String productOnePrice = String.valueOf(firstProductPrice);
        String totalAmount = uiActions.getText(totalAmtValue);
        if (totalAmount.contains(productOnePrice)) {
            System.out.println("Total amount matches the product price: " + totalAmount);
            return true;
        } else {
            System.out.println("Total amount does not match the product price. Total: " + totalAmount + ", Product Price: " + productOnePrice);
            return false;
        }
    }

    public void clickOnPlaceOrder() {
        waitUtils.waitForElementToBeClickable(placeOrder);
        uiActions.click(placeOrder);
    }

    public void selectCountryFromDropdown() {
        configs = new ConfigReaders();
        String country = configs.getCountry();
        waitUtils.waitForElementToBeVisible(countryDropdown);
        uiActions.selectByVisibleText(countryDropdown, country);
    }

    public void clickCheckBox(){
        uiActions.click(checkBox);
    }

    public void clickOnProceed(){
        waitUtils.waitForElementToBeClickable(proceedCTA);
        uiActions.click(proceedCTA);
    }

    public String successMessage(){
        return uiActions.getText(successMessage);
    }

    public boolean isOrderPlacedSuccessfully() {
        configs = new ConfigReaders();
        String placeOrderMsg = configs.getSuccessMessage();
        String successMsg = successMessage();
        if (successMsg.contains(placeOrderMsg)) {
            System.out.println("Order placed successfully and success message: "+successMsg);
            return true;
        } else {
            System.out.println("Order placement failed. Message: " + successMsg);
            return false;
        }
    }
}