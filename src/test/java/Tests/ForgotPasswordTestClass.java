package Tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class ForgotPasswordTestClass {

	WebDriver driver;

	@BeforeTest
	public void beforeTest() {

		System.setProperty("webdriver.chrome.driver", "src/test/resources/WebDrivers/chromedriver.exe");
		driver = Utility.DriverUtilClass.getBrowserInstance("chrome");
		driver.manage()
		.window()
		.maximize();
	}

	@Test
	public void forgotPasswordTestMethod() throws InterruptedException {

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

	@AfterTest
	public void afterTest() throws InterruptedException {
		Thread.sleep(5000);
		driver.close();
	}
}
