package utils;

import java.io.FileInputStream;
import java.util.Properties;

public class ConfigReaders {
    public static Properties properties;
    static {
        try {
            FileInputStream fis = new FileInputStream(
                    "src/test/resources/configurations.properties");
            properties = new Properties();
            properties.load(fis);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public String getURL() {
        return properties.getProperty("app.url");
    }
    public  String getBrowser() {

        return properties.getProperty("browser");
    }
    public String getProductOne() {
        return properties.getProperty("productOne");
    }
    public static String getWait() {
        return properties.getProperty("implicit.wait");
    }
    public String getProductTwo() {
        return properties.getProperty("productTwo");
    }
    public String getProductThree() {
        return properties.getProperty("productThree");
    }
    public String getCountry(){ return properties.getProperty("country"); }
    public String getSuccessMessage() { return properties.getProperty("successMessage"); }
    public String getFirstProductPrice(){return properties.getProperty("firstProductPrice");
    }

    public String getReportPath(){
            return properties.getProperty("report_path");
    }
}
