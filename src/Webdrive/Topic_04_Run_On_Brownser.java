package Webdrive;

import java.util.concurrent.TimeUnit;

import org.apache.commons.exec.OS;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_04_Run_On_Brownser {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	

	
			//Edge =>cau hinh tuong tu chrome vaf firefox
			//System.setProperty("webdriver.edge.driver", projectPath + "/browserDrivers/msedgedriver.exe");
			//driver = new EdgeDriver();

		
  @Test
	public void TC_01_Chrome() {
	
		//Chay tren trinh duyet Chrome
		System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("https://www.demo.guru99.com/");
		driver.quit();
	}

	@Test
	public void TC_02_Firefox() {
		//Chay tren trinh duyet Firefox
		System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver.exe");
		driver = new FirefoxDriver();
		driver.get("https://www.demo.guru99.com/");
		driver.quit();
	}

}