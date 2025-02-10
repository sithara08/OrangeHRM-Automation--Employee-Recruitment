package pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utills.ScrollUtil;
import utills.WaitUtil;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;

public class RecruitmentPage {
    WebDriver driver;

    public RecruitmentPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//nav[@aria-label='Sidepanel']/descendant::a[contains(.,'Recruitment')]")
    WebElement menuTextRecruitment;

    @FindBy(xpath = "//button[normalize-space()='Add']")
    WebElement addButton;

    @FindBy(xpath = "//input[@placeholder='First Name']")
    WebElement fNameBox;

    @FindBy(xpath = "//input[@placeholder='Middle Name']")
    WebElement mNameBox;

    @FindBy(xpath = "//input[@placeholder='Last Name']")
    WebElement lNameBox;

    @FindBy(xpath = "//label[contains(text(),'Vacancy')]/following::div[@class='oxd-select-text-input']")
    WebElement vacancyBox;

    @FindBy(xpath = "//label[text()='Email']/following::input[1]")
    WebElement emailBox;

    @FindBy(xpath = "//label[text()='Email']/following::input[2]")
    WebElement conNumberBox;

    @FindBy(xpath = "//div[@class='oxd-file-button']")
    WebElement resumeBox;

    @FindBy(xpath = "//input[@type='checkbox']/parent::label")
    WebElement consentCheckBox;

    @FindBy(xpath = "//button[text()=' Save ']")
    WebElement saveButton;

    @FindBy(xpath = "//form[contains(.,'Application Stage')]/child::div[@class='oxd-grid-3 orangehrm-full-width-grid']/child::div[1]/descendant::div[3]")
    WebElement candidateNameBox;

    @FindBy(xpath = "//div[@class='oxd-input-group']//div[1]//span[1]")
    WebElement fNameRequiredErrorField;

    @FindBy(xpath = "//input[@placeholder='Last Name']/following::span[1]")
    WebElement lNameRequiredErrorField;

    @FindBy(xpath = "//label[text()='Email']/following::span[1]")
    WebElement emailRequiredErrorField;


    public void clickMenuRecruitmentText(){
        menuTextRecruitment.click();
    }

    public void clickAddButton(){
        addButton.click();
    }

    public void setFName(String fName){
        fNameBox.sendKeys(fName);
    }

    public void setMName(String mName){
        mNameBox.sendKeys(mName);
    }

    public void setLName(String lName){
        lNameBox.sendKeys(lName);
    }

    public void setVacancy(String vacancy){
        vacancyBox.sendKeys(vacancy + Keys.ENTER);
    }

    public void setEmail(String email){
        emailBox.sendKeys(email);
    }

    public void setConNumber(String conNumber){
        conNumberBox.sendKeys(conNumber);
    }

    public void setResume(String filePath) {
        try {
            resumeBox.click();
            StringSelection selection = new StringSelection(filePath);
            Toolkit.getDefaultToolkit().getSystemClipboard().setContents(selection, null);
            Thread.sleep(3000);

            Robot robot = new Robot();
            robot.keyPress(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_V);
            robot.keyRelease(KeyEvent.VK_V);
            robot.keyRelease(KeyEvent.VK_CONTROL);

            Thread.sleep(4000);
            robot.keyPress(KeyEvent.VK_ENTER);
            robot.keyRelease(KeyEvent.VK_ENTER);
        } catch (Exception e) {
            throw new RuntimeException("Error in file upload: " + e.getMessage());
        }
    }

    public void checkConsentCheckbox(){
        ScrollUtil scrollUtil = new ScrollUtil();
        scrollUtil.scrollToElement(driver, consentCheckBox);
        consentCheckBox.click();
    }

    public void clickSaveButton(){
        ScrollUtil scrollUtil = new ScrollUtil();
        scrollUtil.scrollToElement(driver, saveButton);
        saveButton.click();
    }

    public String expectedResult(){
        WaitUtil waitUtil = new WaitUtil();
        waitUtil.waitUntilVisible(driver, candidateNameBox, 5);
        String registeredName = candidateNameBox.getText();
        System.out.println(registeredName);
        return registeredName;
    }

    public boolean requiredFieldErrVisibility() {
        return fNameRequiredErrorField.isDisplayed() && lNameRequiredErrorField.isDisplayed() && emailRequiredErrorField.isDisplayed();
    }
}
