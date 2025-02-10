package functions;

import org.openqa.selenium.WebDriver;
import pages.LoginPage;
import utills.PropFileReaderUtil;

public class Login {

    WebDriver driver;
    LoginPage loginPage;
    PropFileReaderUtil propFileReaderUtil = new PropFileReaderUtil();

    public Login(WebDriver driver){
        this.driver = driver;
        this.loginPage = new LoginPage(driver);
    }

    public void logIntoSystem(){
        String username = propFileReaderUtil.getProperty("config", "username");
        String password = propFileReaderUtil.getProperty("config", "password");

        loginPage.setUsername(username);
        loginPage.setPassword(password);
        loginPage.clickLoginBtn();
    }
}
