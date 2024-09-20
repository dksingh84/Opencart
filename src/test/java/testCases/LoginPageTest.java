package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;

public class LoginPageTest extends BaseClass {

	@Test(groups = {"sanity"})
	public void loginTest() {
		logger.info("***************Started Login Test**************");
		try {
		HomePage homePage = new HomePage(driver);
		logger.info("Click on MyAccount");
		homePage.clickOnMyAccount();
		logger.info("Click on Login");
		homePage.clickOnLogin();
		
		LoginPage loginPage= new LoginPage(driver);
		loginPage.setEmai(prop.getProperty("email"));
		loginPage.setPassword(prop.getProperty("password"));
		loginPage.clickLogin();
		
		MyAccountPage myAccountPage= new MyAccountPage(driver);
		boolean headingMessage=myAccountPage.isMyAccountMessageHeadingExists();
		//Assert.assertEquals(headingMessage,true,"Login Failed");
		Assert.assertTrue(headingMessage);
		
	}
		catch(Exception e) {
			Assert.fail();
		}
		
		logger.info("****************Completed Test*******************");
	}
}
