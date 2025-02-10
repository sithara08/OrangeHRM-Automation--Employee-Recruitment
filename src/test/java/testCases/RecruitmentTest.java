package testCases;

import base.Base;
import functions.Login;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.RecruitmentPage;

public class RecruitmentTest extends Base {

    Login login;
    RecruitmentPage recruitmentPage;

    /// Data provider method to supply test data for adding a candidate
    @DataProvider
    public Object[][] candidateData(){
        return new Object[][]{
                {"Caroline", "Melina", "Forbes", "test", "caroline@gmail.com", "0778667654"}
        };
    }

    /// Test method to add a new candidate using provided data
    @Test(dataProvider = "candidateData", priority = 1)
    public void addCandidateTest(String fName, String mName, String lName, String vacancy, String email, String conNumber){
        login = new Login(driver);
        recruitmentPage = new RecruitmentPage(driver);

        logIntoSystem();
        goToRecruitmentPage();

        recruitmentPage.clickAddButton();

        /// Filling the form
        recruitmentPage.setFName(fName);
        recruitmentPage.setMName(mName);
        recruitmentPage.setLName(lName);
        recruitmentPage.setVacancy(vacancy);
        recruitmentPage.setEmail(email);
        recruitmentPage.setConNumber(conNumber);
        recruitmentPage.setResume("C:\\Users\\User\\Desktop\\TEST.pdf");
        recruitmentPage.checkConsentCheckbox();

        /// Clicking the save button
        recruitmentPage.clickSaveButton();

        /// Verifying if the registration is successful
        String actualResult = fName + " " + mName + " " +  lName;
        Assert.assertEquals(actualResult, recruitmentPage.expectedResult(), "Employee registration unsuccessful.");
    }


    /// Test method to validate required fields.
    @Test(priority = 2)
    public void requiredFieldsValidationTest(){
        login = new Login(driver);
        recruitmentPage = new RecruitmentPage(driver);

        logIntoSystem();
        goToRecruitmentPage();

        recruitmentPage.clickAddButton();

        /// Clicking the save button without filling the form
        recruitmentPage.clickSaveButton();

        /// Verifying that required field error messages are displayed
        Assert.assertTrue(recruitmentPage.requiredFieldErrVisibility(), "Error message did not appear.");
    }


    /// Helper method to log in to the system
    public void logIntoSystem(){
        login.logIntoSystem();
    }

    /// Helper method to navigate to the Recruitment page
    public void goToRecruitmentPage(){
        recruitmentPage.clickMenuRecruitmentText();
    }
}