package tests;

import org.apache.logging.log4j.LogManager;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import utilities.UtilBase;
import utilities.WebElementLib;
import variables.LoginVariables;

public class LoginTest extends UtilBase {

	
	@BeforeClass
	public void beforeClass() {
//		logging
		logger=LogManager.getLogger(LoginTest.class);
		logger.info("Start :: Login Test ");

	}
	@AfterClass
	public void afterClass() {
		logger.info("END :: Login Test ");
	}
	
//	Login Test with Invalid Credentials
	@Test(priority = 1)
	public void loginTest_invalidCredentials() {
		String testName = "loginTest_invalidCredentials";
		test = extent.createTest(testName);

//		Invalid Credentials
		try {
			driver.get(LoginVariables.baseURL);
			pObj.input_email().sendKeys(LoginVariables.username_invalid);
			pObj.input_password().sendKeys(LoginVariables.password_invalid);
			logger.info(LoginVariables.username_invalid + ", " + LoginVariables.password_invalid);
			pObj.button_login().click();
			Thread.sleep(3000);

//			pass condition
			if (WebElementLib.doesElementExist(pObj.message_login_failed())) {
				testPassed(testName);
			} else {
				testFailed(testName);
			}

		} catch (Exception e) {
			testException(testName, e);
		}

	}

//Login Test with Valid Credentials
	@Test(priority = 2)
	public void loginTest_validCredentials() {
		String testName = "loginTest_validCredentials";
		test = extent.createTest(testName);

		try {
			driver.get(LoginVariables.baseURL);
			pObj.input_email().sendKeys(LoginVariables.username);
			pObj.input_password().sendKeys(LoginVariables.password);
			logger.info(LoginVariables.username + ", " + LoginVariables.password);
			pObj.button_login().click();
			Thread.sleep(3000);

//			pass condition
			if (WebElementLib.doesElementExist(pObj.link_dashboard_gearchecker())) {
				testPassed(testName);
			} else {
				testFailed(testName);
			}

		} catch (Exception e) {
			testException(testName, e);
		}
	}

}
