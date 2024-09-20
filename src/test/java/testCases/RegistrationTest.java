package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.RegistrationPage;

public class RegistrationTest extends BaseClass {

	@Test(groups = {"sanity","regression"})
	public void registration() {
		logger.info("***************Started Registration Test**************");
		try {
		HomePage homePage = new HomePage(driver);
		logger.info("Click on MyAccount");
		homePage.clickOnMyAccount();
		logger.info("Click on Register");
		homePage.clickOnRegister();
		
		RegistrationPage registrationPage= new RegistrationPage(driver);
		logger.info("Enter Customer details");
		registrationPage.setFirstName(randomString().toUpperCase());
		registrationPage.setLastName(randomString().toUpperCase());
		registrationPage.setEmail(randomString()+"@gmail.com");
		registrationPage.setTelephone(randomNumber());
		String password=randomAlphaNumber();
		registrationPage.setPassword(password);
		registrationPage.setConfirmPassword(password);
		registrationPage.clickPrivacyPolicy();
		registrationPage.clickContinue();
		
		logger.info("Validating expected message");
		String confirmMsg=registrationPage.getConfirmationMsg();
		if(confirmMsg.equals("Your Account Has Been Created!")) {
			Assert.assertTrue(true);
		}
		else {
			logger.error("Test Failed.....");
			logger.debug("Debug Test....");
			Assert.assertTrue(false);
		}
		//Assert.assertEquals(confirmMsg, "Your Account Has Been Createdddd!");
		}
		catch(Exception e) {
			Assert.fail();
		}
		
		logger.info("****************Completed Test*******************");
	}
}
