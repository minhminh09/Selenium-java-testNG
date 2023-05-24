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
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_13_Web_Popup_IN_NOT_DOM {
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
		
		//implicitlyWait ảnh hưởng trực tiếp đến việc tìm element
		//findElement, findElements
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
	}


	//@Test
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
		
		//By: khai báo biến by=>nó chưa có đi tìm element
		By loginbutton= By.cssSelector("div.ReactModal__Content");
		
		//Verify popup chưa hiển thị khi chưa click vào button
		Assert.assertEquals(driver.findElements(loginbutton).size(), 0);
		
		//Click [[Tai khoan]
		driver.findElement(By.cssSelector("div[data-view-id*='header_account']")).click();
		sleepInsecon(2);
		
		//Verify popup hien thi
		Assert.assertEquals(driver.findElements(loginbutton).size(), 1);
		sleepInsecon(2);
		
		//Hoặc verify hiển thị bằng câu lệnh
		//Tuy nhiên trường hợp tìm Element không có trong DOM thì khong nên dùng fileElement số ít vì nó dễ bị false
		//Assert.assertTrue(driver.findElement(loginbutton).isDisplayed());	
		driver.findElement(By.cssSelector("input[name='tel']")).sendKeys("058797988");
		sleepInsecon(2);
		//Click icon x de dong popup
		driver.findElement(By.cssSelector("img.close-img")).click();
		sleepInsecon(2);
		//Verify popup khong hiển thị nua
		Assert.assertEquals(driver.findElements(loginbutton).size(), 0);
		
	}
	//@Test
	public void TC_03_Fixed_Popup1() {
		//Truy cap trang
		driver.get("https://tiki.vn/");
		
		//By: khai báo biến by=>nó chưa có đi tìm element
		By loginbutton= By.cssSelector("div.ReactModal__Content");
		
		//Verify popup chưa hiển thị khi chưa click vào button
		Assert.assertEquals(driver.findElements(loginbutton).size(), 0);
		
		//Click [[Tai khoan]
		driver.findElement(By.cssSelector("div[data-view-id*='header_account']")).click();
		sleepInsecon(2);
		
		//Verify popup hien thi
		Assert.assertEquals(driver.findElements(loginbutton).size(), 1);
		sleepInsecon(2);
		
		//click Dang nhap bang Email
		driver.findElement(By.cssSelector("p.login-with-email")).click();
		sleepInsecon(2);
		
		//
		Assert.assertEquals(driver.findElement(By.cssSelector("div.heading p")).getText(), "Nhập email và mật khẩu tài khoản Tiki");
		
		//
		driver.findElement(By.cssSelector("input[name='email']")).sendKeys("buingocminh09@gmail.com");
		driver.findElement(By.cssSelector("div.input input[type='password']")).sendKeys("minhminh09");
		
		driver.findElement(By.xpath("//button[text()='Đăng nhập']")).click();
		sleepInsecon(3);
		
		Assert.assertEquals(driver.findElement(By.cssSelector("div.sc-bCwfaz.cxyZgC")).getText(), "Chào mừng Phạm Thị Thu Hà đã trở lại, vui vẻ mua sắm cùng TIKI nhé");

	}
	@Test
	public void TC_03_Fixed_Popup2() {
		
		driver.get("https://www.facebook.com/");
		
		
		By creataccPopup= By.xpath("//div[text()='Sign Up']/parent::div/parent::div");
		//Kiểm tra popup không hiển thị
		Assert.assertEquals(driver.findElements(creataccPopup).size(), 0);
		sleepInsecon(2);
		
		//click creat account
		driver.findElement(By.cssSelector("a[data-testid='open-registration-form-button']")).click();
		sleepInsecon(3);
		
		//Kiểm tra popup hiển thị
		Assert.assertEquals(driver.findElements(creataccPopup).size(), 1);
		sleepInsecon(2);
		
		driver.findElement(By.cssSelector("input[name='firstname']")).sendKeys("haptt");
		driver.findElement(By.cssSelector("input[name='lastname']")).sendKeys("Phạm");
		driver.findElement(By.cssSelector("input[name='reg_email__']")).sendKeys("0982394932");
		driver.findElement(By.cssSelector("input[name='reg_passwd__']")).sendKeys("123456");
		new Select (driver.findElement(By.id("day"))).selectByVisibleText("15");
		new Select (driver.findElement(By.id("month"))).selectByVisibleText("Aug");
		new Select (driver.findElement(By.id("year"))).selectByVisibleText("2003");
		driver.findElement(By.xpath("//label[text()='Female']/following-sibling::input")).click();
		sleepInsecon(2);
		
		
		
		
		
		//Click close
		driver.findElement(By.cssSelector("img._8idr.img")).click();
		sleepInsecon(2);
		
		//verify popup không hiển thị
		Assert.assertEquals(driver.findElements(creataccPopup).size(), 0);
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