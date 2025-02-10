package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

    WebDriver driver;

    public LoginPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//input[@name='username']")
    WebElement usernameBox;

    @FindBy(xpath = "//input[@name='password']")
    WebElement passwordBox;

    @FindBy(xpath = "//button[@type='submit']")
    WebElement loginBtn;


    public void setUsername(String username){
        usernameBox.sendKeys(username);
    }

    public void setPassword(String password){
        passwordBox.sendKeys(password);
    }

    public void clickLoginBtn(){
        loginBtn.click();
    }
}
