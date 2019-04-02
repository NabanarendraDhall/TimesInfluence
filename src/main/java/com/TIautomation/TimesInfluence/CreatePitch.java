package com.TIautomation.TimesInfluence;

import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import com.TIautomation.CommonUtils.CommonUtilities;
import com.mentorstudies.automationframework.common.TestClassUtil;
import com.mentorstudies.automationframework.exception.AutomationFrameworkException;
import com.mentorstudies.automationframework.util.common.KeyWordTool;

public class CreatePitch extends TestClassUtil {
	String actual, expected;
	public static String pitchName = null;

	@Test(dataProvider = "defaultDP")
	public void createPitch(String PitchRegion, String clientName, String salesOption, String pitchBrief,
			String eventType, String industryVertical, String pitchValue, String pitchAssignTeam)
			throws IOException, AutomationFrameworkException, InterruptedException {
		CommonUtilities.openBrowser(true);
		CommonUtilities.AccessToTI("csTeam");
		CommonUtilities.driver.findElement(KeyWordTool.getLocator("createPitch", "addPitch")).click();
		Thread.sleep(1000);
		CommonUtilities.driver.findElement(KeyWordTool.getLocator("createPitch", "pitchRegion")).click();
		Thread.sleep(1000);
		for (int i = 1; i <= 100; i++) {
			try {
				WebElement e = CommonUtilities.driver.findElement(By.id("md2-option-" + i));
				String s = e.getText();
				System.out.println("Text for element " + i + "is: " + s);
				if (s.equalsIgnoreCase(PitchRegion)) {
					e.click();
					System.out.println("Clicked");
					break;

				}
			} catch (NoSuchElementException e) {

			}

		}
		CommonUtilities.driver.findElement(KeyWordTool.getLocator("createPitch", "pitchName")).click();
		CommonUtilities.driver.findElement(KeyWordTool.getLocator("createPitch", "pitchName")).clear();
		pitchName = "AutoPitch " + CommonUtilities.getDate("dd-M-yyyy hh:mm:ss");
		CommonUtilities.driver.findElement(KeyWordTool.getLocator("createPitch", "pitchName")).sendKeys(pitchName);
		CommonUtilities.driver.findElement(KeyWordTool.getLocator("createPitch", "clientName")).click();
		CommonUtilities.driver.findElement(KeyWordTool.getLocator("createPitch", "clientName")).clear();
		CommonUtilities.driver.findElement(KeyWordTool.getLocator("createPitch", "clientName")).sendKeys(clientName);
		WebDriverWait wait = new WebDriverWait(CommonUtilities.driver, 10);
		wait.until(ExpectedConditions
				.visibilityOfElementLocated(KeyWordTool.getLocator("createPitch", "clientNameOption")));
		CommonUtilities.driver.findElement(KeyWordTool.getLocator("createPitch", "clientNameOption")).click();
		System.out.println("Clicked on times");
		CommonUtilities.driver.findElement(KeyWordTool.getLocator("createPitch", "salesSpoc")).click();
		CommonUtilities.driver.findElement(KeyWordTool.getLocator("createPitch", "salesSpoc")).clear();
		CommonUtilities.driver.findElement(KeyWordTool.getLocator("createPitch", "salesSpoc")).sendKeys(salesOption);
		WebDriverWait wait1 = new WebDriverWait(CommonUtilities.driver, 10);
		wait1.until(ExpectedConditions
				.visibilityOfElementLocated(KeyWordTool.getLocator("createPitch", "salesteamOption")));
		CommonUtilities.driver.findElement(KeyWordTool.getLocator("createPitch", "salesteamOption")).click();
		CommonUtilities.driver.findElement(KeyWordTool.getLocator("createPitch", "pitchBrief")).click();
		CommonUtilities.driver.findElement(KeyWordTool.getLocator("createPitch", "pitchBrief")).clear();
		CommonUtilities.driver.findElement(KeyWordTool.getLocator("createPitch", "pitchBrief")).sendKeys(pitchBrief);
		CommonUtilities.driver.findElement(KeyWordTool.getLocator("createPitch", "eventType")).click();
		List<WebElement> element1 = CommonUtilities.driver
				.findElements(KeyWordTool.getLocator("createPitch", "eventOptions"));
		for (WebElement eventOptions : element1) {
			String options = eventOptions.getText();
			if (options.equals(eventType)) {
				eventOptions.click();
				System.out.println("Clicked on : " + eventType);
				break;
			}
		}
		CommonUtilities.driver.findElement(KeyWordTool.getLocator("createPitch", "actualDeliveryDate")).click();
		WebElement dateWidget = CommonUtilities.driver.findElement(KeyWordTool.getLocator("createPitch", "calander"));
		List<WebElement> columns = dateWidget.findElements(KeyWordTool.getLocator("createPitch", "calanderCells"));
		for (WebElement cell : columns) {
			if (cell.getText().equals(CommonUtilities.getDate("d"))) {
				cell.click();
				System.out.println("Clicked on : " + CommonUtilities.getDate("dd"));
				break;
			}
		}
		CommonUtilities.driver.findElement(KeyWordTool.getLocator("createPitch", "industryVertical")).click();
		for (int j = 0; j <= 100; j++) {
			try {
				WebElement e = CommonUtilities.driver.findElement(By.id("md2-option-" + j));
				String options = e.getText();
				if (options.equals(industryVertical)) {
					e.click();
					System.out.println("Clicked on : " + industryVertical);
					break;
				}
			} catch (NoSuchElementException e) {

			}
		}
		CommonUtilities.driver.findElement(KeyWordTool.getLocator("createPitch", "pitchValue")).click();
		CommonUtilities.driver.findElement(KeyWordTool.getLocator("createPitch", "pitchValue")).clear();
		CommonUtilities.driver.findElement(KeyWordTool.getLocator("createPitch", "pitchValue")).sendKeys(pitchValue);
		CommonUtilities.driver.findElement(KeyWordTool.getLocator("createPitch", "pitchAssignTo")).click();
		Thread.sleep(1000);
		for (int j = 0; j <= 100; j++) {
			try {
				WebElement e = CommonUtilities.driver.findElement(By.id("md2-option-" + j));
				String s = e.getText();
				if (s.equalsIgnoreCase(pitchAssignTeam)) {
					e.click();
					System.out.println("Clicked");
					break;

				}
			} catch (NoSuchElementException e) {

			}
		}
		CommonUtilities.driver.findElement(KeyWordTool.getLocator("createPitch", "pitchSubmit")).click();
		WebDriverWait wait11 = new WebDriverWait(CommonUtilities.driver, 20);
		WebElement element = wait11
				.until(ExpectedConditions.elementToBeClickable(KeyWordTool.getLocator("pitchApproval", "popUpModal")));
		element.click();
		System.out.println(CreatePitch.pitchName + ":    null");
		CommonUtilities.tiLogout();
		System.out.println("Trying logout");
	}

	@Test(dataProvider = "defaultDP", dependsOnMethods = "createPitch")
	//, dependsOnMethods = "createPitch"
	public void pitchApproval(String decision) throws IOException, AutomationFrameworkException, InterruptedException {
	//	CommonUtilities.openBrowser(true);
		CommonUtilities.AccessToTI("csHead");
		Thread.sleep(1000);
		CommonUtilities.driver.findElement(KeyWordTool.getLocator("pitchApproval", "pitchApprovalSearch")).click();
		CommonUtilities.driver.findElement(KeyWordTool.getLocator("pitchApproval", "pitchApprovalSearch")).clear();
	//	System.out.println(CreatePitch.pitchName + ":    null");
		CommonUtilities.driver.findElement(KeyWordTool.getLocator("pitchApproval", "pitchApprovalSearch"))
				.sendKeys(CreatePitch.pitchName);
		CommonUtilities.driver.findElement(KeyWordTool.getLocator("pitchApproval", "clickToApprove")).click();
		Thread.sleep(3000);
		CommonUtilities.driver.findElement(KeyWordTool.getLocator("pitchApproval", "approvalDdl")).click();
		Thread.sleep(2000);
		for (int j = 0; j <= 100; j++) {
			try {
				WebElement e = CommonUtilities.driver.findElement(By.id("md2-option-" + j));
				String s = e.getText();
				if (s.equalsIgnoreCase("Approved")) {
					e.click();
					System.out.println("Clicked");
					break;

				}
			} catch (NoSuchElementException e) {

			}

		}
		CommonUtilities.driver.findElement(KeyWordTool.getLocator("pitchApproval", "approvaldecision"))
				.sendKeys(decision);
		CommonUtilities.driver.findElement(KeyWordTool.getLocator("pitchApproval", "pitchapprove")).click();
		WebDriverWait wait12 = new WebDriverWait(CommonUtilities.driver, 15);
		WebElement element1 = wait12
				.until(ExpectedConditions.elementToBeClickable(KeyWordTool.getLocator("pitchApproval", "popUpModal")));
		element1.click();
		CommonUtilities.tiLogout();
	}

	@Test(dataProvider = "defaultDP", dependsOnMethods = "pitchApproval")
	public void pitchAssign(String assignTo) throws IOException, AutomationFrameworkException, InterruptedException {
		CommonUtilities.AccessToTI("prod");
		CommonUtilities.driver.navigate().to("http://uat.timesinfluence.com/#/pitch-list");
		CommonUtilities.driver.findElement(KeyWordTool.getLocator("pitchAssign", "pitchApprovalSearch"))
				.sendKeys(CreatePitch.pitchName);
		CommonUtilities.driver.findElement(KeyWordTool.getLocator("pitchAssign", "clickToApprove")).click();
		CommonUtilities.driver.findElement(KeyWordTool.getLocator("pitchAssign", "assignTo")).sendKeys(assignTo);
		Thread.sleep(2000);
		CommonUtilities.driver.findElement(KeyWordTool.getLocator("pitchAssign", "assignee")).click();
		CommonUtilities.driver.findElement(KeyWordTool.getLocator("pitchAssign", "pitchAssign")).click();
	}

}
