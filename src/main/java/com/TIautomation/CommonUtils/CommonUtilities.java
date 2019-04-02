package com.TIautomation.CommonUtils;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.mentorstudies.automationframework.exception.AutomationFrameworkException;
import com.mentorstudies.automationframework.util.common.ConfigReader;
import com.mentorstudies.automationframework.util.impl.DefaultDriverManager;

public class CommonUtilities {
	public static WebDriver driver;

	public static String UID;
	public static String PASSWORD;
	public static String csHeadUid;
	public static String prod;
	public static String sales;
	public static String URL;
	public static String logOutUrl;
	public static double currentPayout;
	public static boolean status;
	public static void setvaluefromconfig() throws IOException {

		UID = ConfigReader.getProperty("config.properties", "CsTeamUid");
		PASSWORD = ConfigReader.getProperty("config.properties", "PASSWORD");
		csHeadUid=ConfigReader.getProperty("config.properties", "CsheadUid");
		prod=ConfigReader.getProperty("config.properties", "prod");
		sales=ConfigReader.getProperty("config.properties", "sales");
		URL = ConfigReader.getProperty("config.properties", "URL");
		logOutUrl = ConfigReader.getProperty("config.properties", "logOutUrl");

	}

	public static void openBrowser(boolean status) throws IOException, AutomationFrameworkException {
		if (status == true) {
			CommonUtilities.setvaluefromconfig();
			driver = new DefaultDriverManager().getDriver();
			driver.get(CommonUtilities.URL);
			driver.manage().window().maximize();
		} else {
			System.out.println("Browser Quit");
			driver.quit();
		}
	}

	public static void AccessToTI(String userName)
			throws IOException, AutomationFrameworkException, InterruptedException {
//		if (status == true) {
//			CommonUtilities.setvaluefromconfig();
//			driver = new DefaultDriverManager().getDriver();
//			driver.get(CommonUtilities.URL);
//			driver.manage().window().maximize();
//			Thread.sleep(2000);
			System.out.println(CommonUtilities.URL + " " + userName + " " + PASSWORD);
			driver.findElement(By.id("login_username")).click();
			driver.findElement(By.id("login_username")).clear();
			if(userName.equalsIgnoreCase("csTeam")) {
			driver.findElement(By.id("login_username")).sendKeys(CommonUtilities.UID);
			}else if(userName.equalsIgnoreCase("csHead")) {
				driver.findElement(By.id("login_username")).sendKeys(CommonUtilities.csHeadUid);
			}else if(userName.equalsIgnoreCase("prod")) {
				driver.findElement(By.id("login_username")).sendKeys(CommonUtilities.prod);
			}else if(userName.equalsIgnoreCase("sales")) {
				driver.findElement(By.id("login_username")).sendKeys(CommonUtilities.sales);
			}
			driver.findElement(By.id("login_password")).click();
			driver.findElement(By.id("login_password")).clear();
			driver.findElement(By.id("login_password")).sendKeys(CommonUtilities.PASSWORD);
			Thread.sleep(1000);
			driver.findElement(
					By.xpath("//*[@id='loginBtn']"))
					.click();
			System.out.println("User " + userName + " loggedIn successfully ");
			Thread.sleep(1000);
//		} else {
//			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
//			driver.findElement(By.xpath("html/body/times-root/app-authorized/html/body/div[1]/div/ul/li/a/span"))
//					.click();
//			driver.findElement(By.xpath("html/body/times-root/app-authorized/html/body/div[1]/div/ul/li/ul/li/a"))
//					.click();
//			System.out.println("User " + user + " loggedOut successfully ");
//			driver.quit();
//
//		}
	}
	
	public static void tiLogout() {
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.findElement(By.xpath("html/body/times-root/app-authorized/div[1]/div/ul/li/a/span"))
				.click();
		driver.findElement(By.xpath("html/body/times-root/app-authorized/div[1]/div/ul/li/ul/li/a"))
				.click();
		System.out.println("User loggedOut successfully ");
	}
	
	public static String getDate(String format) {
		DateFormat dateFormat = new SimpleDateFormat(format);
		//yyyy/MM/dd HH:mm:ss
		Date date = new Date();
		String dt=dateFormat.format(date);
//		System.out.println(dt);
		return dt;
	}
	public static void main(String[] args) {
		System.out.println(CommonUtilities.getDate("d"));
	}
}
