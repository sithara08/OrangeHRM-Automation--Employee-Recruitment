package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utills.WaitUtil;

public class UserManagementPage {
    WebDriver driver;

    public UserManagementPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//nav[@aria-label='Sidepanel']/descendant::a[contains(.,'Admin')]")
    WebElement menuTextAdmin;

    @FindBy(xpath = "//button[normalize-space()='Add']")
    WebElement addUserBtn;

    @FindBy(xpath = "//div[@class='orangehrm-card-container']")
    WebElement addUserContainer;

    @FindBy(xpath = "//label[normalize-space()='User Role']/ancestor::div[@class='oxd-input-group oxd-input-field-bottom-space']/descendant::div[contains(text(),'-- Select --')]")
    WebElement userRoleSelector;

    @FindBy(xpath = "//input[@placeholder='Type for hints...']")
    WebElement employeeNameSelector;

    @FindBy(xpath = "//label[contains(normalize-space(),'Status')]/ancestor::div[2]/descendant::div[contains(text(),'-- Select --')]")
    WebElement statusSelector;

    @FindBy(xpath = "//label[contains(text(),'Username')]/ancestor::div[2]/div/input")
    WebElement usernameField;

    @FindBy(xpath = "//label[text()='Password']/ancestor::div[2]/div/input")
    WebElement passwordField;

    @FindBy(xpath = "//label[text()='Confirm Password']/ancestor::div[2]/div/input")
    WebElement confirmPasswordField;

    @FindBy(xpath = "//button[@type='submit']")
    WebElement saveBtn;

    @FindBy(xpath = "//div[@class='oxd-toast-content oxd-toast-content--success']")
    WebElement successMsgBox;

    @FindBy(xpath = "//span[normalize-space()='Should be at least 5 characters']")
    WebElement validUsernameErrorField;

    @FindBy(xpath = "//span[normalize-space()='Should have at least 7 characters']")
    WebElement validPasswordErrorField;

    @FindBy(xpath = "//span[text()='Passwords do not match']")
    WebElement passwordMatchErrorField;


    public void clickManuAdminText(){
        menuTextAdmin.click();
    }

    public void clickAddUserBtn(){
        addUserBtn.click();
    }

    public void waitForAddUserContainerVisible(){
        WaitUtil waitUtil = new WaitUtil();
        waitUtil.waitUntilVisible(driver, addUserContainer, 10);
    }

    public void selectUserRole(String userRole ){
        userRoleSelector.click();
        userRoleSelector.sendKeys(userRole + Keys.ENTER);
    }

    public void selectEmployeeName(String employeeName){
        employeeNameSelector.click();
        employeeNameSelector.sendKeys(employeeName);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        employeeNameSelector.sendKeys(Keys.ARROW_DOWN);
        employeeNameSelector.sendKeys(Keys.ENTER);
    }

    public void selectStatus(){
        statusSelector.click();
        statusSelector.sendKeys("ena" + Keys.ENTER);
    }

    public void setUsername(String username){
        usernameField.click();
        usernameField.sendKeys(username);
    }

    public void setPassword(String password){
        passwordField.sendKeys(password);
    }

    public void setConfirmPassword(String confirmPassword){
        confirmPasswordField.sendKeys(confirmPassword);
    }

    public boolean IsUsernameValid(String username){
        char[] characters = username.toCharArray();
        int numberOfCharacters = characters.length;
        return numberOfCharacters >= 5;
    }

    public boolean IsPasswordValid(String password){
        char[] characters = password.toCharArray();
        int numberOfCharacters = characters.length;
        return numberOfCharacters >= 7;
    }

    public void clickSaveButton(){
        saveBtn.click();
    }

    public boolean getPopupMessage(){
        WaitUtil waitUtil = new WaitUtil();
        waitUtil.waitUntilVisible(driver, saveBtn, 5);
        return successMsgBox.getText().contains("Success");
    }

    public boolean validUsernameVerification(){
        return validUsernameErrorField.getText().contains("Should be at least 5 characters");
    }

    public boolean validPasswordVerification(){
        return validPasswordErrorField.getText().contains("Should have at least 7 characters");
    }

    public boolean passwordMatchVerification(){
        WaitUtil waitUtil = new WaitUtil();
        waitUtil.waitUntilVisible(driver, passwordMatchErrorField, 4);
        return passwordMatchErrorField.getText().contains("Passwords do not match");
    }
}
