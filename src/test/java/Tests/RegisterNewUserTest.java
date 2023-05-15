package Tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class RegisterNewUserTest {


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
	public void RegisterUser() throws InterruptedException {

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


	@AfterTest
	public void afterTest() throws InterruptedException {

		Thread.sleep(5000);
		//driver.close();
	}
}
