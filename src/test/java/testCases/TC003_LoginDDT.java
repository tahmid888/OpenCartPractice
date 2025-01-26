package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;
import utilities.DataProviders;

public class TC003_LoginDDT extends BaseClass{

	
	@Test(dataProvider="LoginData", dataProviderClass=DataProviders.class, groups="Datadriven") // getting dataProvider from the different class
	
	public void verify_loginDDT(String email, String pwd, String exp) {
		
		
		logger.info("***** Starting TC003_LoginDDT *****");
		
		try {
		// Home page
		HomePage hp = new HomePage(driver);
		hp.clickMyAccount();
		hp.clickLogin();
		
		// Login Page
		LoginPage lp = new LoginPage(driver);
		lp.setEmail(email);
		lp.setPassword(pwd);
		lp.clickLogin();
		
		// My Account Page
		MyAccountPage macc = new MyAccountPage(driver);
		boolean targetPage = macc.isMyAccountPageExists();
		
		
		if(exp.equalsIgnoreCase("Valid"))
		{
			if(targetPage == true)
			{
				macc.clickLogout();
				Assert.assertTrue(true);
				
			}
			else {
				Assert.assertTrue(false);
			}
		}
		
		if(exp.equalsIgnoreCase("Invalid"))
		{
			if(targetPage == true)
			{
				macc.clickLogout();
				Assert.assertTrue(false);
				
			}
			else {
				Assert.assertTrue(true);
			}
		}
		
		}catch(Exception e) {
			
			Assert.fail();
		}
		logger.info("***** Finished TC003_LoginDDT *****");
		
		}
		
	}

