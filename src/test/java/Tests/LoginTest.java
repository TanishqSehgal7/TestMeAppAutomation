package Tests;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterTest;

public class LoginTest {


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
	public void LoginTestMethod() throws InterruptedException {

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

	@AfterTest
	public void afterTest() throws InterruptedException {
		Thread.sleep(5000);
		driver.close();
	}

}
