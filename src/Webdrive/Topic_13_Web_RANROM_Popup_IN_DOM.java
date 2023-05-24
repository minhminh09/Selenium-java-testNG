package Webdrive;

import java.awt.Desktop.Action;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
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

public class Topic_13_Web_RANROM_Popup_IN_DOM {
	WebDriver driver;
	Actions action;
	JavascriptExecutor jsExecutor;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	String emailAdress="automation"+ getRandomnumber()+ "@gmail.com" ;

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

//	Yêu cầu:
	//Random popup nên nó có thể hiển thị 1 cách ngẫu nhiên hoặc không hiển thị
	//Nếu như nó hiển thị thì cần thao tác lên popup=>Đóng nó đi để qua step tiếp theo
	//Khi đóng popup thì khi Refresh nó hiển thị lại hoặc không
	//Nếu như nó không hiển thị thì qua step tiếp theo
	@Test
	public void TC_01_Random_Popup_InDom() {
		//Truy cap trang
		driver.get("https://www.javacodegeeks.com/");
		
		By lepopup= By.cssSelector("div.lepopup-popup-container>div:not([style^='display:none'])");
		
		//Vì nó luôn có trong DOM nên có thể dùng hàm isDisplay để kiểm tra
		if(driver.findElement(lepopup).isDisplayed()) {
			
			//Nhập Email vào
			driver.findElement(lepopup)			
			//Đóng popup đi=>Qua step tiếp theo
		}
		
		
		
		
	}
	@Test
	public void TC_02_Random_Popup_InDom() {
		//Truy cap trang
		driver.get("https://vnk.edu.vn/");
		By popup= By.cssSelector("div#tve_editor");
		//Kiểm tra hiển thị của popup
		Assert.assertEquals(driver.findElements(popup).size(), 1);
		//Close popup
		driver.findElement(By.cssSelector("svg.tcb-icon")).click();

		
		
	}
	@Test
	public void TC_03_Random_Popup_NOT_In_Dom() {
		//Truy cap trang
		driver.get("https://tiki.vn/");
		
		
	}
	//@Test
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
	public static int getRandomnumber() {
		Random rand=new Random();
		return rand.nextInt(999999);
	}
	@AfterClass
	public void afterClass() {
		//driver.quit();
	}
}