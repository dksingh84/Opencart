package testCases;

import org.testng.annotations.Test;

import pageObjects.HomePage;

public class HomePageTest extends BaseClass {
	
	@Test
	public void launchUrl() {
		HomePage homePage= new HomePage(driver);
		homePage.clickOnMyAccount();
		homePage.clickOnRegister();
	}

}
