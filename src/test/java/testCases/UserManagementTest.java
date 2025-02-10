package testCases;

import base.Base;
import functions.Login;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.UserManagementPage;

public class UserManagementTest extends Base {

    Login login;
    UserManagementPage  userManagementPage;

    /// Data provider method to supply test data for adding a user.
    @DataProvider
    public Object[][] userData(){
        return new Object[][]{
                {"admin", "p", "newAdmin2", "newAdmin123"}
        };
    }

    /// Test method to add a new user using provided data
    @Test(dataProvider = "userData", priority = 1)
    public void addUserTest(String userRole, String employeeName, String username, String password){
        login = new Login(driver);
        userManagementPage = new UserManagementPage(driver);

        logIntoSystem();
        goToUserManagementPage();

        userManagementPage.clickAddUserBtn();
        userManagementPage.waitForAddUserContainerVisible();

        /// Filling the form
        userManagementPage.selectUserRole(userRole);
        userManagementPage.selectEmployeeName(employeeName);
        userManagementPage.selectStatus();
        userManagementPage.setUsername(username);
        userManagementPage.setPassword(password);
        userManagementPage.setConfirmPassword(password);

        /// Validating username and password requirements
        Assert.assertTrue(userManagementPage.IsUsernameValid(username), "The username must be at least 5 characters.");
        Assert.assertTrue(userManagementPage.IsPasswordValid(password), "The password must be at least 7 characters.");

        /// Clicking the save button
        userManagementPage.clickSaveButton();

        /// Verifying if the user is successfully added
        Assert.assertTrue(userManagementPage.getPopupMessage(), "Add User Unsuccessful.");
    }


    ///  Test method to validate username requirements
    @Test(priority = 2)
    public void validUsernameValidation(){
        login = new Login(driver);
        userManagementPage = new UserManagementPage(driver);

        logIntoSystem();
        goToUserManagementPage();

        userManagementPage.clickAddUserBtn();
        userManagementPage.waitForAddUserContainerVisible();

        /// Setting the username field with an invalid username
        userManagementPage.setUsername("abc");

        /// Verifying that an error message appears
        Assert.assertTrue(userManagementPage.validUsernameVerification(), "Username validation unsuccessful.");
    }


    /// Test method to validate password requirements
    @Test(priority = 3)
    public void validPasswordValidation(){
        login = new Login(driver);
        userManagementPage = new UserManagementPage(driver);

        logIntoSystem();
        goToUserManagementPage();

        userManagementPage.clickAddUserBtn();
        userManagementPage.waitForAddUserContainerVisible();

        /// Setting the password field with an invalid password
        userManagementPage.setPassword("abc");

        /// Verifying that an error message appears
        Assert.assertTrue(userManagementPage.validPasswordVerification(), "Password validation unsuccessful.");
    }


    /// Test method to validate password match.
    @Test(priority = 4)
    public void passwordMatchValidation(){
        login = new Login(driver);
        userManagementPage = new UserManagementPage(driver);

        logIntoSystem();
        goToUserManagementPage();

        userManagementPage.clickAddUserBtn();
        userManagementPage.waitForAddUserContainerVisible();

        /// Setting fields with different passwords
        userManagementPage.setPassword("abcd56789");
        userManagementPage.setConfirmPassword("abcd12345");

        /// /// Verifying that an error message appears
        Assert.assertTrue(userManagementPage.passwordMatchVerification(), "Password match validation unsuccessful.");
    }


    /// Helper method to log in to the system
    public void logIntoSystem(){
        login.logIntoSystem();
    }

    /// Helper method to navigate to the User Management page
    public void goToUserManagementPage(){
        userManagementPage.clickManuAdminText();
    }
}
