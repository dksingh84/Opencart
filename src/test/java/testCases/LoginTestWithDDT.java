package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import utilities.DataProviders;

public class LoginTestWithDDT extends BaseClass {

	@Test(dataProvider="LoginData", dataProviderClass = DataProviders.class)
	public void loginTest_DDT(String email, String pwd, String expected) {
		logger.info("***************Started LoginTestWithDDT Test**************");
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
		
		if(expected.equalsIgnoreCase("Valid")) {
			if(headingMessage==true) {
				myAccountPage.clickLogout();
				Assert.assertTrue(true);
			}
			
			else {
				Assert.assertTrue(false);
			}
		}
		
		if(expected.equalsIgnoreCase("Invalid")) {
			if(headingMessage==true) {
				myAccountPage.clickLogout();
				Assert.assertTrue(false);
			}
			
			else {
				Assert.assertTrue(true);
			}
		}
		
	}
		catch(Exception e) {
			Assert.fail();
		}
		
		logger.info("****************Completed LoginTestWithDDT*******************");
	}
}
