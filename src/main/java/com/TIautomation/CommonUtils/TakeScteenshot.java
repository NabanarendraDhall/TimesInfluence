package com.TIautomation.CommonUtils;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class TakeScteenshot {
	public static String folderName() {
		DateFormat df = new SimpleDateFormat("dd_MM_yyyy");
		Date dateobj = new Date();
		// System.out.println(df.format(dateobj));
		String folder;
		return folder = "D:\\" + "Failed Screenshot " + df.format(dateobj) + "\\ ";
	}

	public static void getscreenshot(String filename) throws IOException {

		try {
			File source = ((TakesScreenshot) CommonUtilities.driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(source, new File(TakeScteenshot.folderName() + filename + ".png"));
		} catch (Exception e) {
			System.out.println("Failed to take screen shot due to: " + e.getMessage());
		}
	}
}
