package Utility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class DriverUtilClass {

	public static WebDriver getBrowserInstance(String browserName) {


		if(browserName.equals("chrome")) {

			System.setProperty("webdriver.chrome.driver", "src/test/resources/WebDrivers/chromedriver.exe");
			return new ChromeDriver();

		} else if(browserName.equals("edge")) {

			System.setProperty("webdriver.chrome.driver", "src/test/resources/WebDrivers/msedgedriver.exe");
			return new EdgeDriver();

		} else if(browserName.equals("ie")) {
			System.setProperty("webdriver.chrome.driver", "src/test/resources/WebDrivers/IEDriverServer.exe");
			return new InternetExplorerDriver();
		} else {
			return null;
		}
	}

}
