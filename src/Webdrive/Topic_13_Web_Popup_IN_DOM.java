package Webdrive;

import java.awt.Desktop.Action;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_13_Web_Popup_IN_DOM {
	WebDriver driver;
	Actions action;
	JavascriptExecutor jsExecutor;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");

	@BeforeClass
	public void beforeClass() {
		System.out.println(osName);
		if (osName.contains("Windows")) {
			System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		} else {
			System.setProperty("webdriver.chrome.driver", projectPath + "/browserDrivers/chromedriver");
		}
		//Câu lệnh giúp đóng Chrome notifications
		//
		Map<String, Integer> prefs = new HashMap();
		prefs.put("profile.default_content_setting_values.notifications", 2);
		ChromeOptions options = new ChromeOptions();
		options.setExperimentalOption("prefs", prefs);
		//WebDriver driver = new ChromeDriver(options);
		driver = new ChromeDriver(options);
		action = new Actions(driver);
		//jsExecutor=(JavascriptExecutor) driver;
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
	}

	//@Test
	public void TC_01_Popup() {
		
		driver.get("https://ngoaingu24h.vn/");
		//Tạo biến 
		By loginButton=By.cssSelector("div#modal-login-v1 div.modal-content");
		//Kiểm tra popup Đăng nhập không hiển thị=>sdung assertFalse
		Assert.assertFalse(driver.findElement(loginButton).isDisplayed());
		
		//Click button Đăng nhập
		driver.findElement(By.cssSelector("button.login_.icon-before")).click();
		//Kiểm tra hiển thị popup Đăng nhập=>assertTrue
		Assert.assertFalse(driver.findElement(loginButton).isDisplayed());
		driver.findElement(By.cssSelector("input#account-input")).sendKeys("automationfc");
		driver.findElement(By.cssSelector("input#password-input")).sendKeys("automationfc");
		driver.findElement(By.cssSelector("button.btn-login-v1")).click();
		sleepInsecon(3);
		
		Assert.assertEquals(driver.findElement(By.cssSelector("div#modal-login-v1 div.error-login-panel")).getText(), "Tài khoản không tồn tại!");
		
		

	}


	@Test
	public void TC_02_RightClick() {
		driver.get("https://skills.kynaenglish.vn/");
		
		//Kiểm tra pupup hiển thị
		By loginbutton= By.cssSelector("div#k-popup-account-login");
		//Kiểm tra hiển thị của popup
		Assert.assertFalse(driver.findElement(loginbutton).isDisplayed());
		//Click Đăng nhập
		driver.findElement(By.cssSelector("a.login-btn")).click();
		sleepInsecon(3);
		
		//Kiểm tra hiển thị của popup
		Assert.assertTrue(driver.findElement(loginbutton).isDisplayed());
		sleepInsecon(3);
		
		
		driver.findElement(By.cssSelector("input#user-login")).sendKeys("haptt@gmail.com");
		driver.findElement(By.cssSelector("input#user-password")).sendKeys("123456");
		driver.findElement(By.cssSelector("button#btn-submit-login")).click();
		sleepInsecon(3);
		Assert.assertEquals(driver.findElement(By.cssSelector("div#password-form-login-message")).getText(), "Sai tên đăng nhập hoặc mật khẩu");
		sleepInsecon(3);
		driver.findElement(By.cssSelector("button.k-popup-account-close")).click();
		//Kiểm tra hiển thị của popup
		Assert.assertTrue(driver.findElement(loginbutton).isDisplayed());

	}

	//@Test
	public void TC_03_Fixed_Popup() {
		//Truy cap trang
		driver.get("https://tiki.vn/");
		//Click [[Tai khoan]
		driver.findElement(By.xpath("//span[text()='Tài khoản']")).click();
		sleepInsecon(2);
		//Verify popup hien thi
		Assert.assertTrue(driver.findElement(By.cssSelector("")).isDisplayed());
		
		
	}
	
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