package Tests;

import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class LaunchBrowserTest {


	WebDriver driver;
	ExtentSparkReporter sparkReporter;
	ExtentReports extentReport;
	ExtentTest logger;
	
	@BeforeTest
	public void beforeTest() {
		
		System.setProperty("webdriver.chrome.driver", "src/test/resources/WebDrivers/chromedriver.exe");
		driver = Utility.DriverUtilClass.getBrowserInstance("chrome");
		driver.manage()
		.window()
		.maximize();
		driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
		
		String reportPath = System.getProperty("user.dir") 
				+ "/extent-reports/" + this.getClass().getName() + "/" + new SimpleDateFormat("dd-mm-yyyy-hh-mm-ss-ms").format(new java.util.Date()) + ".html";
		
		
		sparkReporter = new ExtentSparkReporter(reportPath);
		extentReport = new ExtentReports();
		extentReport.attachReporter(sparkReporter);
		extentReport.setSystemInfo("Stream", "QE");
		extentReport.setSystemInfo("Location", "IDC");
		extentReport.setSystemInfo("User Name", "TestUser 01");
		
		sparkReporter.config().setDocumentTitle("Launch Browser Test Report");
		sparkReporter.config().setReportName("Launch Browser Extent Report");
		sparkReporter.config().setTheme(Theme.DARK);
		
	}

	@Test
	public void LaunchTestMeAppMethod() {
		
		logger = extentReport.createTest("LaunchBrowserTest");
		logger.log(Status.INFO, MarkupHelper.createLabel("Launching TestMeApp in browser", ExtentColor.GREY));
		
		driver.get("https://lkmdemoaut.accenture.com/TestMeApp/fetchcat.htm");
		Assert.assertEquals(driver.getTitle(), "Home");
	}

	@AfterMethod
	public void afterTest(ITestResult result) throws InterruptedException, IOException {

		
		if(result.getStatus() == ITestResult.SUCCESS) {
			logger.log(Status.PASS, MarkupHelper.createLabel("TestMeApp launched in browser. Home Page Loaded", ExtentColor.GREEN));
		} else if (result.getStatus() == ITestResult.SKIP) {
			logger.log(Status.PASS, MarkupHelper.createLabel("TestMeApp launch was skipped", ExtentColor.YELLOW));

			
		} else if(result.getStatus() == ITestResult.FAILURE) {
			logger.log(Status.PASS, MarkupHelper.createLabel("TestMeApp launched in browser. Home Page Loaded", ExtentColor.RED));
			
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
