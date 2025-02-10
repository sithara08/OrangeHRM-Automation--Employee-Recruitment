package drivers;

import org.openqa.selenium.InvalidArgumentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverManager {

    public static WebDriver driver;

    public static void initializeDriver(String browser){
        switch (browser.toLowerCase()){
            case "chrome" :
                driver = new ChromeDriver();
                break;
            case "edge" :
                driver = new EdgeDriver();
                break;
            case "firefox" :
                driver = new FirefoxDriver();
                break;
            default:
                throw new InvalidArgumentException("Unsupported browser : " + browser);
        }
    }

    public void quitDriver(){
        if (driver != null){
            driver.quit();
        }
    }
}
