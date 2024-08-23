package Tests;

import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import org.testng.annotations.BeforeTest;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;

public class LoginTest {


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
		
		sparkReporter.config().setDocumentTitle("User Login Test Report");
		sparkReporter.config().setReportName("User Login Extent Report");
		sparkReporter.config().setTheme(Theme.DARK);
	}	

	@Test
	public void LoginTestMethod() throws InterruptedException {

		
		logger = extentReport.createTest("UserLoginTest");
		logger.log(Status.INFO, MarkupHelper.createLabel("UserLogin in browser", ExtentColor.GREY));
		
		driver.get("https://lkmdemoaut.accenture.com/TestMeApp/fetchcat.htm");
		Assert.assertEquals(driver.getTitle(), "Home");

		Thread.sleep(5000);

		WebElement signinLink = driver.findElement(By.linkText("SignIn"));
		signinLink.click();
		Assert.assertEquals(driver.getTitle(), "Login");

		WebElement userName = driver.findElement(By.id("userName"));
		userName.sendKeys("testuser01");
		Thread.sleep(5000);

		WebElement password = driver.findElement(By.id("password"));
		password.sendKeys("pass123");
		Thread.sleep(5000);

		WebElement loginBtn = driver.findElement(By.name("Login"));
		loginBtn.click();
		Assert.assertEquals(driver.getTitle(), "Home");
		Thread.sleep(5000);

		WebElement signoutBtn = driver.findElement(By.partialLinkText("SignOut"));
		signoutBtn.click();

		Thread.sleep(5000);
		Assert.assertEquals(driver.getTitle(), "Home");
	}

	@AfterMethod
	public void afterTest(ITestResult result) throws InterruptedException, IOException {
		
		
		if(result.getStatus() == ITestResult.SUCCESS) {
			logger.log(Status.PASS, MarkupHelper.createLabel("User Logged into the App", ExtentColor.GREEN));
		} else if (result.getStatus() == ITestResult.SKIP) {
			logger.log(Status.PASS, MarkupHelper.createLabel("User Login was skipped", ExtentColor.YELLOW));

			
		} else if(result.getStatus() == ITestResult.FAILURE) {
			logger.log(Status.PASS, MarkupHelper.createLabel("User Login failed. Home Page Loaded", ExtentColor.RED));
			
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
