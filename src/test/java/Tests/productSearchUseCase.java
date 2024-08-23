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

public class productSearchUseCase {

	WebDriver driver;
	ExtentSparkReporter sparkReporter;
	ExtentReports extentReport;
	ExtentTest logger;

	@BeforeTest
	public void beforeTest() {

		driver = Utility.DriverUtilClass.getBrowserInstance("chrome");
		driver.manage().window().maximize();  
		
		String reportPath = System.getProperty("user.dir")  
				+ "/extent-reports/" + this.getClass().getName() + "/" + new SimpleDateFormat("dd-mm-yyyy-hh-mm-ss-ms").format(new java.util.Date()) + ".html";
		
		
		sparkReporter = new ExtentSparkReporter(reportPath);
		extentReport = new ExtentReports();
		extentReport.attachReporter(sparkReporter);
		extentReport.setSystemInfo("Stream", "QE");
		extentReport.setSystemInfo("Location", "IDC");
		extentReport.setSystemInfo("User Name", "TestUser 01");
		
		sparkReporter.config().setDocumentTitle("Product Search With Login");
		sparkReporter.config().setReportName("ProductSeachWithUserLoggedIn");
		sparkReporter.config().setTheme(Theme.DARK);
	}

	@Test
	public void productSearchUseCaseTest() throws InterruptedException {
		
		logger = extentReport.createTest("ProductSeachWithUserLoggedIn");
		logger.log(Status.INFO, MarkupHelper.createLabel("Searching product for testuser01", ExtentColor.GREY));


		driver.get("https://lkmdemoaut.accenture.com/TestMeApp/fetchcat.htm");
		Assert.assertEquals(driver.getTitle(), "Home");

		Thread.sleep(10000);

		driver.findElement(By.xpath("//*[@id=\"header\"]/div[1]/div/div/div[2]/div/ul/li[1]/a")).click();
		Assert.assertEquals(driver.getTitle(), "Login");

		WebElement userName = driver.findElement(By.xpath("//*[@id=\"userName\"]"));
		userName.sendKeys("testuser01");
		Thread.sleep(1000);

		WebElement password = driver.findElement(By.xpath("//*[@id=\"password\"]"));
		password.sendKeys("pass123");

		Thread.sleep(1000);

		if(userName.getText()!=null && password.getText()!=null) {
			driver.findElement(By.xpath("/html/body/main/div/div/div/form/fieldset/div[4]/div/input[1]")).click();
			Assert.assertEquals(driver.getTitle(), "Home");

			driver.findElement(By.id("myInput")).sendKeys("Gift Set");
			Thread.sleep(5000);
			driver.findElement(By.xpath("/html/body/div[1]/form/input")).click();

			driver.findElement(By.xpath("/html/body/section/div/div/div[2]/div/div/div/div[2]/center/a")).click();
			Thread.sleep(5000);
			driver.findElement(By.xpath("//*[@id=\"header\"]/div[1]/div/div/div[2]/div/a[2]")).click();
			Thread.sleep(5000);
			String productName = driver.findElement(By.xpath("//*[@id=\"cart\"]/tbody/tr/td[1]/div/div/h4")).getText();
			Assert.assertEquals(productName, "Gift set");

			driver.findElement(By.partialLinkText("SignOut")).click();
			Assert.assertEquals(driver.getTitle(), "Sign Out");
		}


	}

	@AfterMethod
	public void afterTest(ITestResult result) throws InterruptedException, IOException {
		
		if(result.getStatus() == ITestResult.SUCCESS) {
			logger.log(Status.PASS, MarkupHelper.createLabel("Product Searched was found", ExtentColor.GREEN));
		} else if (result.getStatus() == ITestResult.SKIP) {
			logger.log(Status.PASS, MarkupHelper.createLabel("Product search was skipped", ExtentColor.YELLOW));

			
		} else if(result.getStatus() == ITestResult.FAILURE) {
			logger.log(Status.PASS, MarkupHelper.createLabel("Failed to search the entered product", ExtentColor.RED));
			
			TakesScreenshot ss = (TakesScreenshot) driver;
			File source = ss.getScreenshotAs(OutputType.FILE);
			
			String imgPath = "C:/Users/Tanishq Sehgal/eclipse-workspace-new/TestMeApp/src/test/extent-reports/snapshots"
					+ result.getName() + ".png";
			
			FileUtils.copyFile(source, new File(imgPath));
		}
		
		
		
		Thread.sleep(10000);
		driver.quit();
		extentReport.flush();
	}

}
