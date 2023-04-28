package Webdrive;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_05_Web_Browser_Part2 {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");

	@BeforeClass
	public void beforeClass() {
		System.out.println(osName);
		if (osName.contains("Windows")) {
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		} else {
			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		}

		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
	}

	
	//https://docs.google.com/document/d/1G0QsiVDI5KfhNGKxE6a03bhn6KpvgDh69BTx2ttCO94/edit#
	@Test
	public void TC_01_Url() {
		
		driver.get("http://live.techpanda.org/");
		
		//page Login =>click [My account]
		driver.findElement(By.xpath("//div[@class='footer']//a [@title='My Account']")).click();
		//driver.findElement(By.cssSelector("div[class='footer'] a[title='My Account']")).click();
		
		sleepInsecond(3); //Khi click xong chờ load trang 3s
		//Chuyển đến trang login
		Assert.assertEquals(driver.getCurrentUrl(), "http://live.techpanda.org/index.php/customer/account/login/");
		
		//Click vào Create an Account
		driver.findElement(By.cssSelector("a[title='Create an Account'")).click();
		sleepInsecond(3);//chờ load 3s
		//Chuyển đến trang tạo tài khoản
		Assert.assertEquals(driver.getCurrentUrl(), "http://live.techpanda.org/index.php/customer/account/create/");

	
	}

	@Test
	public void TC_02_Title() {
		driver.get("http://live.techpanda.org/");
		//click My account
		driver.findElement(By.cssSelector("div[class='footer'] a[title='My Account']")).click();
		sleepInsecond(3);
		//gọi đến URL login
		Assert.assertEquals(driver.getTitle(), "Customer Login");
		//Click Create an Account
		driver.findElement(By.cssSelector("a[title='Create an Account'")).click();
		sleepInsecond(3);
		Assert.assertEquals(driver.getTitle(), "Create New Customer Account");
		
		
		
		
	}

	@Test
	public void TC_03_Navigate_Function() {
		//Step 1
		driver.get("http://live.techpanda.org/");
		//Step 2: click My account
		driver.findElement(By.cssSelector("div[class='footer'] a[title='My Account']")).click();
		sleepInsecond(3);
		//Step 3: click Create an Account
		driver.findElement(By.cssSelector("a[title='Create an Account'")).click();
		sleepInsecond(3);
		//Step 4: Chuyển đến trang tạo tài khoản
		Assert.assertEquals(driver.getCurrentUrl(), "http://live.techpanda.org/index.php/customer/account/create/");
		//Step 5: Back lại trang login
		driver.navigate().back();
		sleepInsecond(3);
		//Step 6: quay lại trang login
		Assert.assertEquals(driver.getCurrentUrl(), "http://live.techpanda.org/index.php/customer/account/login/");
		sleepInsecond(3);
		//Step 7: forward tới trang Register page
		driver.navigate().forward();
		sleepInsecond(3);
		//Step 8: quay lại trang Creat an Account
		Assert.assertEquals(driver.getCurrentUrl(), "http://live.techpanda.org/index.php/customer/account/create/");

		
		
	}
	
	@Test
	public void TC_04_getPageSource() {
		//Step 1
		driver.get("http://live.techpanda.org/");
		//Step 2: click My account
		driver.findElement(By.cssSelector("div[class='footer'] a[title='My Account']")).click();
		sleepInsecond(3);
		//Verify page HTML có chứa 1 chuỗi mong muốn
		Assert.assertTrue(driver.getPageSource().contains("Login or Create an Account "));
		//Click vào Create an Account
		driver.findElement(By.cssSelector("a[title='Create an Account'")).click();
		sleepInsecond(3);//chờ load 3s
		//Verify page HTML có chứa 1 chuỗi mong muốn
		Assert.assertTrue(driver.getPageSource().contains("Create an Account"));
		
		
	}
	
	public void sleepInsecond (long timeInsecond) {
		try {
			Thread.sleep(timeInsecond *1000);
			
		}catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}