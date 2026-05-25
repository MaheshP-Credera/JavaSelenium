package utils;


import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;


public class UIActions {
    WebDriver driver;
    public UIActions(WebDriver driver) {
        this.driver = driver;
    }
    // Clear and type text
    public void clearAndType(By element, String text) {
        driver.findElement(element).clear();
        driver.findElement(element).sendKeys(text);
    }
    // Click element
    public void click(By element) {
        driver.findElement(element).click();
    }
    // Get text from element
    public String getText(By element) {
        return driver.findElement(element).getText();
    }


    public void selectByVisibleText(By element, String value){
        WebElement dropdown = driver.findElement(element);
        Select select  = new Select(dropdown);
        select.selectByVisibleText(value);
    }

    public void browserBack(){

        driver.navigate().back();
    }

    public void browserForward(){

        driver.navigate().forward();
    }

    public void refreshPage(){
        driver.navigate().refresh();
    }

}
