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

public class RegisterNewUserTest {


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
				+ "/extent-reports/" + this.getClass().getName() + "/" + new SimpleDateFormat("dd-mm-yyyy-hh-mm-ss-ms")
				.format(new java.util.Date()) + ".html";
		
		
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
	public void RegisterUser() throws InterruptedException {
		
		logger = extentReport.createTest("UserRegistrationTest");
		logger.log(Status.INFO, MarkupHelper.createLabel("Registering New User", ExtentColor.GREY));

		driver.get("https://lkmdemoaut.accenture.com/TestMeApp/fetchcat.htm");
		Assert.assertEquals(driver.getTitle(), "Home");

		driver.findElement(By.xpath("//*[@id=\"header\"]/div[1]/div/div/div[2]/div/ul/li[2]/a")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@id=\"userName\"]")).sendKeys("NewTUserTan");
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@id=\"firstName\"]")).sendKeys("NewT");
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@id=\"lastName\"]")).sendKeys("UserTan");
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@id=\"password\"]")).sendKeys("pass12345");
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@id=\"pass_confirmation\"]")).sendKeys("pass12345");
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@id=\"gender\"]")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@id=\"emailAddress\"]")).sendKeys("tan1210@gmai.com");
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@id=\"mobileNumber\"]")).sendKeys("9971780315");
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@id=\"dob\"]")).sendKeys("12/09/1999");
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@id=\"address\"]")).sendKeys("Gurugram, Sector 21");
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@id=\"answer\"]")).sendKeys("New Delhi");
		Thread.sleep(2000);

		driver.findElement(By.xpath("/html/body/main/div/div/form/fieldset/div/div[13]/div/input[1]")).click();
		Thread.sleep(5000);
		System.out.print("New User Registered Successfully");

		driver.findElement(By.id("userName")).sendKeys("NewTUserTan");
		Thread.sleep(1000);

		driver.findElement(By.id("password")).sendKeys("pass12345");
		Thread.sleep(10000);

		driver.findElement(By.xpath("/html/body/main/div/div/div/form/fieldset/div[4]/div/input[1]")).click();
		Assert.assertEquals(driver.getTitle(), "Home");

		Thread.sleep(2000);

		driver.findElement(By.partialLinkText("SignOut")).click();

	}


	@AfterMethod
	public void afterTest(ITestResult result) throws InterruptedException, IOException {
		
	
		if(result.getStatus() == ITestResult.SUCCESS) {
			logger.log(Status.PASS, MarkupHelper.createLabel("New User Registered", ExtentColor.GREEN));
		} else if (result.getStatus() == ITestResult.SKIP) {
			logger.log(Status.PASS, MarkupHelper.createLabel("New User Registration skipped", ExtentColor.YELLOW));

			
		} else if(result.getStatus() == ITestResult.FAILURE) {
			logger.log(Status.PASS, MarkupHelper.createLabel("New User Registration falied", ExtentColor.RED));
			
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
