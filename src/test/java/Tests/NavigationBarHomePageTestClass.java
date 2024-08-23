package Tests;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class NavigationBarHomePageTestClass {


	WebDriver driver;
	ExtentSparkReporter sparkReporter;
	ExtentReports extentReport;
	ExtentTest logger;

	@BeforeTest
	public void beforeTest() {
		System.setProperty("webdriver.chrome.driver", "src/test/resources/WebDrivers/chromedriver.exe");
		driver = Utility.DriverUtilClass.getBrowserInstance("chrome");
		driver.manage().window().maximize();
		
		driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
		
		String reportPath = System.getProperty("user.dir") 
				+ "/extent-reports/" + this.getClass().getName() + "/" + new SimpleDateFormat("dd-mm-yyyy-hh-mm-ss-ms").format(new java.util.Date()) + ".html";
		
		
		sparkReporter = new ExtentSparkReporter(reportPath);
		extentReport = new ExtentReports();
		extentReport.attachReporter(sparkReporter);
		extentReport.setSystemInfo("Stream", "QE");
		extentReport.setSystemInfo("Location", "IDC");
		extentReport.setSystemInfo("User Name", "TestUser 01");
		
		sparkReporter.config().setDocumentTitle("Navigate to Contact Page from About Us");
		sparkReporter.config().setReportName("Contact Page from About Us");
		sparkReporter.config().setTheme(Theme.DARK);
	}


	@Test
	public void TestHomePageNavigationBar() throws InterruptedException {
		
		logger = extentReport.createTest("Open Contact Page");
		logger.log(Status.INFO, MarkupHelper.createLabel("Opening Bangalore Office Contact", ExtentColor.GREY));

		driver.get("https://lkmdemoaut.accenture.com/TestMeApp/fetchcat.htm");

		//	  // giving error: no such element: Unable to locate element: {"method":"xpath","selector":"//*[@id="submenuul11290"]/li[1]/a/span"}
		//	  Actions hoverToHeadphonesInElectronics = new Actions(driver);
		//	  hoverToHeadphonesInElectronics.moveToElement(driver.findElement(By.linkText("All Categories"))).pause(1000)
		//	  .moveToElement(driver.findElement(By.xpath("//*[@id=\"menu3\"]/li[2]/ul/li[1]/a"))).pause(1000)
		//	  .moveToElement(driver.findElement(By.linkText("Head Phone"))).pause(1000)
		//	  .build().perform();

		//driver.switchTo().defaultContent();

		// navigation to Contact Page Working Correctly
		Actions hovertoBangalore = new Actions(driver);
		hovertoBangalore.moveToElement(driver.findElement(By.xpath("//*[@id=\"menu3\"]/li[3]/a/span"))).pause(1000)
		.moveToElement(driver.findElement(By.xpath("//*[@id=\"menu3\"]/li[3]/ul/li/a/span"))).pause(1000)
		.moveToElement(driver.findElement(By.xpath("//*[@id=\"menu3\"]/li[3]/ul/li/ul/li[2]/a/span"))).pause(1000).click().build().perform();


	}


	@AfterMethod
	public void afterTest(ITestResult result) throws InterruptedException, IOException {
		
		
		if(result.getStatus() == ITestResult.SUCCESS) {
			logger.log(Status.PASS, MarkupHelper.createLabel("Bangalore Contact Page Opened", ExtentColor.GREEN));
		} else if (result.getStatus() == ITestResult.SKIP) {
			logger.log(Status.PASS, MarkupHelper.createLabel("Bangalore Contact Page was skipped", ExtentColor.YELLOW));

			
		} else if(result.getStatus() == ITestResult.FAILURE) {
			logger.log(Status.PASS, MarkupHelper.createLabel("Failed to open Bangalore Contact Page", ExtentColor.RED));
			
			TakesScreenshot ss = (TakesScreenshot) driver;
			File source = ss.getScreenshotAs(OutputType.FILE);
			
			String imgPath = "C:/Users/Tanishq Sehgal/eclipse-workspace-new/TestMeApp/src/test/extent-reports/snapshots"
					+ result.getName() + ".png";
			
			FileUtils.copyFile(source, new File(imgPath));
		}
		
		Thread.sleep(5000);
		driver.quit();
		extentReport.flush();
	}

}
