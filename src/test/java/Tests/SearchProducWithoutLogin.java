package Tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class SearchProducWithoutLogin {


	WebDriver driver;

	@BeforeTest
	public void beforeTest() {

		driver = Utility.DriverUtilClass.getBrowserInstance("chrome");
		driver.manage().window().maximize();  
	}	

	@Test
	public void SearchProductWithoutLoginTestMethod() throws InterruptedException {


		driver.get("https://lkmdemoaut.accenture.com/TestMeApp/fetchcat.htm");
		Assert.assertEquals(driver.getTitle(), "Home");
		
		Thread.sleep(10000);
		driver.findElement(By.id("myInput")).sendKeys("Gift Set");
		Thread.sleep(5000);
		driver.findElement(By.xpath("/html/body/div[1]/form/input")).click();
		Thread.sleep(5000);

		driver.findElement(By.xpath("/html/body/section/div/div/div[2]/div/div/div/div[2]/center/a")).click();
	}


	@AfterTest
	public void afterTest() throws InterruptedException {
		Thread.sleep(10000);
		driver.close();
	}

}
