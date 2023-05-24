package Webdrive;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_06_Web_Element_Part3 {
	WebDriver driver;
	Random rand;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	String emailAdress,firtname, lastname, fullname, password; //trường hợp sử dụng 1 giá trị từ 2 lần trở lên thì nên tạo biến
	
	By emailtextbox=By.id("mail");
	By ageUnder18Radio = By.cssSelector("#under_18");
	By educationTextArea = By.cssSelector("#edu");
	By user5 = By.xpath("//h5[text()='Name: User5']");
	By passwordTextbox = By.cssSelector("#disable_password");
	By BiographyTextArea = By.cssSelector("#bio");
	By devlopmentcheckbox = By.cssSelector("#development");

	@BeforeClass
	public void beforeClass() {
		System.out.println(osName);
		if (osName.contains("Windows")) {
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		} else {
			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		}
		rand=new Random();
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		emailAdress= "haptt"+ rand.nextInt(999) + "@gmail.com";//cú pháp tạo email tự động tăng sử dụng Random
		firtname="haptt";
		lastname="Pham";
		fullname= firtname + " "+ lastname; //" " =>là thêm dấu cách ở giữa họ tên
		password="123456";
		
		
	}

	@Test
	public void LoginEmailnull () {
		driver.get("http://live.techpanda.org/");
		//page Login =>click [My account]
		driver.findElement(By.xpath("//div[@class='footer']//a [@title='My Account']")).click();
		sleepInsecon(1);
		
		driver.findElement(By.id("send2")).click();
		sleepInsecon(1);
		Assert.assertEquals(driver.findElement(By.id("advice-required-entry-email")).getText(), "This is a required field.");
		Assert.assertEquals(driver.findElement(By.id("advice-required-entry-pass")).getText(), "This is a required field.");
	
	}
	@Test
	public void testcase02 () {
		
		driver.get("http://live.techpanda.org/");
		driver.findElement(By.xpath("//div[@class='footer']//a [@title='My Account']")).click();
		sleepInsecon(1);
		driver.findElement(By.id("email")).sendKeys("1333@e3242");
		driver.findElement(By.id("pass")).sendKeys("123456");
		driver.findElement(By.id("send2")).click();
		sleepInsecon(1);
		Assert.assertEquals(driver.findElement(By.id("advice-validate-email-email")).getText(), "Please enter a valid email address. For example johndoe@domain.com.");

	}
	
	@Test
	public void testcase03 () {
		
		driver.get("http://live.techpanda.org/");
		driver.findElement(By.xpath("//div[@class='footer']//a [@title='My Account']")).click();
		sleepInsecon(1);
		driver.findElement(By.id("email")).sendKeys("haptt12@gmail.com");
		driver.findElement(By.id("pass")).sendKeys("123");
		driver.findElement(By.id("send2")).click();
		sleepInsecon(1);
		Assert.assertEquals(driver.findElement(By.id("advice-validate-password-pass")).getText(), "Please enter 6 or more characters without leading or trailing spaces.");

	}
	@Test
	public void testcase04 () {
		
		driver.get("http://live.techpanda.org/");
		driver.findElement(By.xpath("//div[@class='footer']//a [@title='My Account']")).click();
		sleepInsecon(1);
		driver.findElement(By.id("email")).sendKeys("haptt@gmail.com");
		driver.findElement(By.id("pass")).sendKeys("123123123");
		driver.findElement(By.id("send2")).click();
		sleepInsecon(1);
		Assert.assertEquals(driver.findElement(By.xpath("//li[@class=\"error-msg\"]//span")).getText(),"Invalid login or password.");

	}
	@Test
	public void Create_new_account() {
		driver.get("http://live.techpanda.org/");
		driver.findElement(By.xpath("//div[@class='footer']//a [@title='My Account']")).click();
		sleepInsecon(1);
		driver.findElement(By.cssSelector("a[title='Create an Account']")).click();
		sleepInsecon(1);
		
		driver.findElement(By.id("firstname")).sendKeys(firtname);
		//driver.findElement(By.id("middlename")).sendKeys("");
		driver.findElement(By.id("lastname")).sendKeys(lastname);
		driver.findElement(By.id("email_address")).sendKeys(emailAdress);
		driver.findElement(By.id("password")).sendKeys(password);
		driver.findElement(By.id("confirmation")).sendKeys(password);
		driver.findElement(By.xpath("//button[@title='Register']")).click();
		Assert.assertEquals(driver.findElement(By.cssSelector("li.success-msg span")).getText(), "Thank you for registering with Main Website Store.");
		
		String contacttext= driver.findElement(By.xpath("//h3[text()='Contact Information']/parent::div/following-sibling::div/p")).getText();
		System.out.println(contacttext); //in ra text và kiểm tra tương đối text đó có firtname hoặc fullname
		Assert.assertTrue(contacttext.contains(firtname));
		Assert.assertTrue(contacttext.contains(fullname));
		
		//click Account =>click Logout
		driver.findElement(By.xpath("//div[@class='account-cart-wrapper']//span[text()='Account']")).click();
		driver.findElement(By.xpath("//a[text()='Log Out']")).click();
		//Quay về trang chủ
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='page-title']")).isDisplayed());

		
	}
	@Test
	public void login() {
		
		driver.get("http://live.techpanda.org/");
		driver.findElement(By.xpath("//div[@class='footer']//a [@title='My Account']")).click();
		sleepInsecon(1);
		driver.findElement(By.id("email")).sendKeys(emailAdress);
		driver.findElement(By.id("pass")).sendKeys(password);
		driver.findElement(By.id("send2")).click();
		sleepInsecon(1);
		
		String contacttext= driver.findElement(By.xpath("//h3[text()='Contact Information']/parent::div/following-sibling::div/p")).getText();
		System.out.println(contacttext); //in ra text và kiểm tra tương đối text đó có firtname hoặc fullname
		Assert.assertTrue(contacttext.contains(firtname));
		Assert.assertTrue(contacttext.contains(fullname));
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
		driver.quit();
	}
}