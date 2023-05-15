package Tests;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class LaunchBrowserTest {


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
	public void LaunchTestMeAppMethod() {

		driver.get("https://lkmdemoaut.accenture.com/TestMeApp/fetchcat.htm");
		Assert.assertEquals(driver.getTitle(), "Home");
	}

	@AfterTest
	public void afterTest() throws InterruptedException {

		//driver.close();
	}
}
