package com.TIautomation.TimesInfluence;

import java.io.IOException;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.TIautomation.CommonUtils.CommonUtilities;
import com.TIautomation.CommonUtils.TakeScteenshot;
import com.mentorstudies.automationframework.common.TestClassUtil;
import com.mentorstudies.automationframework.exception.AutomationFrameworkException;
import com.mentorstudies.automationframework.util.common.KeyWordTool;
import com.mentorstudies.automationframework.util.impl.DefaultDriverManager;

public class TIlogin extends TestClassUtil {
	
		WebDriver driver;
		String actual, expected;
		/*
		 * openBrowser will open the configured URL as per config.properties
		 */
		@BeforeClass()
		public void openBrowser() throws InterruptedException, AutomationFrameworkException, IOException {
			CommonUtilities.setvaluefromconfig();
			driver = new DefaultDriverManager().getDriver();
			driver.get(CommonUtilities.URL);
			System.out.println("Chrome Opened");
			driver.manage().window().maximize();
		}

		/*
		 * This test case will check all the possible tests for login along with success
		 * case.
		 * 
		 */
		@Test(priority = 0, dataProvider = "defaultDP")
		public void tiLogin(String x, String y, String z) throws InterruptedException, AutomationFrameworkException {
			try {
				driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
				String loginPageURL = driver.getCurrentUrl();
				System.out.println(loginPageURL);
				driver.findElement(KeyWordTool.getLocator("tiLogin", "email")).click();
				driver.findElement(KeyWordTool.getLocator("tiLogin", "email")).clear();
				driver.findElement(KeyWordTool.getLocator("tiLogin", "email")).sendKeys(x);
				driver.findElement(KeyWordTool.getLocator("tiLogin", "password")).click();
				driver.findElement(KeyWordTool.getLocator("tiLogin", "password")).clear();
				driver.findElement(KeyWordTool.getLocator("tiLogin", "password")).sendKeys(y);
				driver.findElement(KeyWordTool.getLocator("tiLogin", "LogInButton")).click();
				Thread.sleep(2000);
				String afterLoginPageURL = driver.getCurrentUrl();
				System.out.println(afterLoginPageURL);
				if (loginPageURL.equals(afterLoginPageURL)) {
					System.out.println("Entered If");
					Set handles = driver.getWindowHandles();
					for (String handle1 : driver.getWindowHandles()) {
						driver.switchTo().window(handle1);
						String ErrorMessage = driver.findElement(KeyWordTool.getLocator("tiLogin", "popUpErrorMessage"))
								.getText();
						WebDriverWait wait = new WebDriverWait(driver, 10);
						wait.until(ExpectedConditions.elementToBeClickable(KeyWordTool.getLocator("tiLogin", "popUpOkButton")));
						System.out.println("Error message on the pop-UP is: "+ErrorMessage);
						driver.findElement(KeyWordTool.getLocator("tiLogin", "popUpOkButton"));
						Assert.assertEquals(ErrorMessage, z, "Test case failed");
						driver.navigate().refresh();
					}
				} else {
					System.out.println("Entered Else");
					String indexPgeURL = driver.getCurrentUrl();
					System.out.println("After Login URL are : "+indexPgeURL);
					Assert.assertEquals(indexPgeURL, z, "Test case passed");
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		/*
		 * The test case will verify the steps to logout. The test case is totally
		 * depend on login method
		 */
		@Test(dependsOnMethods = "tiLogin", dataProvider = "defaultDP")
		public void tiLogout() throws AutomationFrameworkException, InterruptedException {
			Thread.sleep(2000);
			String dashBoardPageURL = driver.getCurrentUrl();
			driver.findElement(KeyWordTool.getLocator("tiLogout", "PreLogOutBotton")).click();
			driver.findElement(KeyWordTool.getLocator("tiLogout", "logOutButton")).click();
			Thread.sleep(2000);
			Assert.assertNotEquals(dashBoardPageURL, driver.getCurrentUrl());
			System.out.println("Logged Out successfully :)");
		}

		@AfterClass()
		public void tearBrowser() {
			driver.quit();
		}
		@AfterMethod()
		public void takeScreenShotOnFailure(ITestResult testResult) throws IOException {
			if (testResult.getStatus() == ITestResult.FAILURE) {
				TakeScteenshot.getscreenshot( "HReckLogin/Logout" + System.currentTimeMillis());
			}
		}
	}


