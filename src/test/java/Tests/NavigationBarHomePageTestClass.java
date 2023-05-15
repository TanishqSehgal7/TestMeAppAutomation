package Tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class NavigationBarHomePageTestClass {


	WebDriver driver;

	@BeforeTest
	public void beforeTest() {
		System.setProperty("webdriver.chrome.driver", "src/test/resources/WebDrivers/chromedriver.exe");
		driver = Utility.DriverUtilClass.getBrowserInstance("chrome");
		driver.manage().window().maximize();
	}


	@Test
	public void TestHomePageNavigationBar() throws InterruptedException {

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


	@AfterTest
	public void afterTest() throws InterruptedException {
		Thread.sleep(5000);
		driver.close();
	}

}
