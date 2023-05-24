package Webdrive;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_11_Web_Element_CustomerCheckbox_Radio2 {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	private JavascriptExecutor jsExecutor;

	@BeforeClass
	public void beforeClass() {
		System.out.println(osName);
		if (osName.contains("Windows")) {
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		} else {
			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		}

		driver = new FirefoxDriver();
		jsExecutor = (JavascriptExecutor) driver;
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
	}

	@Test
	public void TC_01_Url() {
		driver.get("https://docs.google.com/forms/d/e/1FAIpQLSfiypnd69zhuDkjKgqvpID9kwO29UCzeCVrGGtbNPZXQok0jA/viewform");
		sleepInsecon(3);
		
		By radiobutton= By.cssSelector("div[aria-label='Hà Nội']");
		By checkbox= By.cssSelector("div[aria-label='Quảng Ngãi']");
		
		//Thao tác chọn
		jsExecutor.executeScript("arguments[0].click();", driver.findElement(radiobutton));
		sleepInsecon(3);
		
		jsExecutor.executeScript("arguments[0].click();", driver.findElement(checkbox));
		sleepInsecon(3);
		
		//Verify chọn thành công
		//Cach 1
		Assert.assertTrue(driver.findElement(By.cssSelector("div[aria-label='Hà Nội'][aria-checked='true'")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.cssSelector("div[aria-label='Quảng Ngãi'][aria-checked='true'")).isDisplayed());

		//Cach 2
		Assert.assertEquals(driver.findElement(radiobutton).getAttribute("aria-checked"), "true");
		Assert.assertEquals(driver.findElement(checkbox).getAttribute("aria-checked"), "true");

	}

	@Test
	public void TC_02_Logo() {}

	@Test
	public void TC_03_Form() {}
	
	public void sleepInsecon (long timeInsecond) {
		try {
			Thread.sleep(timeInsecond *1000);
			
		}catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	@AfterClass
	public void afterClass() {
		//driver.quit();
	}
}