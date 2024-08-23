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
import org.openqa.selenium.WebElement;
import org.testng.Assert;
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

public class ForgotPasswordTestClass {

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
	public void forgotPasswordTestMethod() throws InterruptedException {
		
		logger = extentReport.createTest("Changing Password");
		logger.log(Status.INFO, MarkupHelper.createLabel("Changing Password for UserTan", ExtentColor.GREY));

		driver.get("https://lkmdemoaut.accenture.com/TestMeApp/fetchcat.htm");
		Assert.assertEquals(driver.getTitle(), "Home");

		Thread.sleep(5000);

		WebElement signinLink = driver.findElement(By.linkText("SignIn"));
		signinLink.click();
		Assert.assertEquals(driver.getTitle(), "Login");

		driver.findElement(By.linkText("Forgot Password?")).click();
		Assert.assertEquals(driver.getTitle(), "Security Question");

		driver.findElement(By.xpath("//*[@id=\"userName\"]")).sendKeys("UserTan");
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@id=\"securityAnswer\"]")).sendKeys("New Delhi");
		Thread.sleep(5000);
		driver.findElement(By.xpath("/html/body/main/div/div/div/form/fieldset/div[4]/center/div/input")).click();

		Assert.assertEquals(driver.getTitle(), "ChangePassword");

		Thread.sleep(5000);

		driver.findElement(By.xpath("//*[@id=\"password\"]")).sendKeys("pass123456");
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@id=\"pass_confirmation\"]")).sendKeys("pass123456");
		Thread.sleep(5000);

		driver.findElement(By.xpath("/html/body/main/div/div/div/form/fieldset/div/div/div[3]/center/div/input")).click();

		driver.findElement(By.xpath("//*[@id=\"userName\"]")).sendKeys("UserTan");
		driver.findElement(By.xpath("//*[@id=\"password\"]")).sendKeys("pass123456");

		Thread.sleep(5000);
		driver.findElement(By.xpath("/html/body/main/div/div/div/form/fieldset/div[4]/div/input[1]")).click();
		Thread.sleep(5000);

		driver.findElement(By.partialLinkText("SignOut")).click();

	} 

	@AfterMethod
	public void afterTest(ITestResult result) throws InterruptedException, IOException {
		
		
		if(result.getStatus() == ITestResult.SUCCESS) {
			logger.log(Status.PASS, MarkupHelper.createLabel("New Password Created", ExtentColor.GREEN));
		} else if (result.getStatus() == ITestResult.SKIP) {
			logger.log(Status.PASS, MarkupHelper.createLabel("Password Changed was skipped", ExtentColor.YELLOW));

			
		} else if(result.getStatus() == ITestResult.FAILURE) {
			logger.log(Status.PASS, MarkupHelper.createLabel("Failed to change password", ExtentColor.RED));
			
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
