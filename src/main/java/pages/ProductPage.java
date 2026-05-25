package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.ConfigReaders;
import utils.UIActions;
import utils.WaitUtils;

import java.time.Duration;

public class ProductPage {
    WebDriver driver;
    UIActions uiActions;
    WaitUtils waitUtils;

    // Locators (By locators used directly for UIActions)
    By searchBox = By.cssSelector(".search-keyword");
    By searchButton = By.cssSelector(".search-button");
    By productNameInResults = By.cssSelector(".product-name");
    By firstProductName = By.cssSelector(".product-name");
  By secondProductName = By.cssSelector("(.product-name)[2]");
    By thirdProductName = By.cssSelector("(.product-name)[3]");
    By addFirstProductTOCart = By.xpath("(//button[@type='button' and text()='ADD TO CART'])[1]");
    By addSecondProductTOCart = By.xpath("(//button[@type='button' and text()='ADD TO CART'])[1]");
    By addThirdProductTOCart = By.xpath("(//button[@type='button' and text()='ADD TO CART'])[1]");
    By productOnePrice = By.xpath("(//p[@class='product-price'])[1]");
    By productTwoPrice = By.xpath("(//p[@class='product-price'])[2]");
    By productThreePrice = By.xpath("(//p[@class='product-price'])[3]");

    ConfigReaders configs = new ConfigReaders();
    String firstProduct = configs.getProductOne();
    String secondProduct = configs.getProductTwo();
    String thirdProduct = configs.getProductThree();

    // Constructor
    public ProductPage(WebDriver driver) {
        this.driver = driver;
        this.uiActions = new UIActions(driver);
        this.waitUtils = new WaitUtils(driver, Duration.ofSeconds(10));
    }

    // Methods
   public void addToCartFirstProduct(){
       waitUtils.waitForElementToBeVisible(addFirstProductTOCart);
       uiActions.click(addFirstProductTOCart);
       System.out.println("First product added to cart");
   }

   public void addToCartSecondProduct(){
       waitUtils.waitForElementToBeVisible(addSecondProductTOCart);
       uiActions.click(addSecondProductTOCart);
       System.out.println("Second product added to cart");
   }

   public void addToCartThirdProduct() {
       waitUtils.waitForElementToBeVisible(addThirdProductTOCart);
       uiActions.click(addThirdProductTOCart);
       System.out.println("Third product added to cart");
   }

    public void clickSearchButton() throws InterruptedException {
        waitUtils.waitForElementToBeClickable(searchButton);
        uiActions.click(searchButton);
        Thread.sleep(3000);
        System.out.println("Search button clicked");
    }

    public void enterProductName() {
        waitUtils.waitForElementToBeVisible(searchBox);
        uiActions.click(searchBox);
        uiActions.clearAndType(searchBox, thirdProduct);
        System.out.println("Entered product name: " + thirdProduct);
    }

    public String getProductNameFromResults() {
        waitUtils.waitForElementToBeVisible(productNameInResults);
        return uiActions.getText(productNameInResults);
    }


    public boolean isSearchProductDisplayed() {
        try {
            waitUtils.waitForElementToBeVisible(productNameInResults);
            String displayedProduct = getProductNameFromResults();
            return displayedProduct.contains(thirdProduct);
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isFirstProductDisplayed() {
        try {
            waitUtils.waitForElementToBeVisible(firstProductName);
            String displayedProduct = uiActions.getText(firstProductName);
            return displayedProduct.contains(firstProduct);
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isSecondProductDisplayed() {
        try {
            waitUtils.waitForElementToBeVisible(secondProductName);
            String displayedProduct = uiActions.getText(secondProductName);
            return displayedProduct.contains(secondProduct);
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isThirdProductDisplayed() {
        try {
            waitUtils.waitForElementToBeVisible(thirdProductName);
            String displayedProduct = uiActions.getText(thirdProductName);
            return displayedProduct.contains(thirdProduct);
        } catch (Exception e) {
            return false;
        }
    }

    public int firstProductPrice(){
        String productOnePriceText = driver.findElement(productOnePrice).getText();
        return Integer.parseInt(productOnePriceText);
    }

    public int secondProductPrice(){
        String productOnePriceText = driver.findElement(productTwoPrice).getText();
        return Integer.parseInt(productOnePriceText);
    }

    public int thirdProductPrice(){
        String productOnePriceText = driver.findElement(productThreePrice).getText();
        return Integer.parseInt(productOnePriceText);
    }



}
