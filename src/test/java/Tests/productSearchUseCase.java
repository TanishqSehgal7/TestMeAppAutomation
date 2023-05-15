package Tests;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterTest;

public class productSearchUseCase {

	WebDriver driver;

	@BeforeTest
	public void beforeTest() {

		driver = Utility.DriverUtilClass.getBrowserInstance("chrome");
		driver.manage().window().maximize();  
	}

	@Test
	public void productSearchUseCaseTest() throws InterruptedException {


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

	@AfterTest
	public void afterTest() throws InterruptedException {
		Thread.sleep(10000);
		driver.close();
	}

}