package Webdrive;

import java.awt.Desktop.Action;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_07_Web_Element_text_textarea {
	WebDriver driver;
	Random rand=new Random();
	Actions action; //Giả lập chuột, bàn phím
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	String EmployID=String.valueOf(rand.nextInt(99999));
	String NumbertP="234-435-23";
	String Commnets="update number\n up date 8/5";

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
		action=new Actions(driver);
		
		//nextInt là kiểu số nguyên, còn EmpoyID sử dụng là String do vậy cần phải covert từ kiểu Int sang String
		
	}
//https://docs.google.com/document/d/1QRI6jdKoCiMB3K7s16f3jEtAVHICdROpw_t30RD8gac/edit#
	@Test
	public void TC_01_textbox_textare() {
		driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
		driver.findElement(By.xpath("//div/input [@name='username']")).sendKeys("Admin");
		driver.findElement(By.xpath("//div/input [@name='password']")).sendKeys("admin123");
		driver.findElement(By.cssSelector("button[type='submit']")).click();
		sleepInsecon(8);
		driver.findElement(By.cssSelector("a[href='/web/index.php/pim/viewPimModule']")).click();
		sleepInsecon(8);
		driver.findElement(By.xpath("//a[text()='Add Employee']")).click();
		sleepInsecon(9);
		driver.findElement(By.cssSelector("input[name='firstName']")).sendKeys("haptt");
		driver.findElement(By.cssSelector("input[name='lastName']")).sendKeys("Phạm");
		driver.findElement(By.xpath("//label[text()='Employee Id']/parent::div/following-sibling::div/input")).sendKeys(Keys.chord(Keys.CONTROL,"a"));
		sleepInsecon(1);
		driver.findElement(By.xpath("//label[text()='Employee Id']/parent::div/following-sibling::div/input")).sendKeys(Keys.DELETE);
		sleepInsecon(3);
		driver.findElement(By.xpath("//label[text()='Employee Id']/parent::div/following-sibling::div/input")).sendKeys(EmployID);
		sleepInsecon(3);
		
		driver.findElement(By.xpath("//p[text()='Create Login Details']/parent::div/div/label/span")).click();
		driver.findElement(By.xpath("//label[text()='Username']/parent::div/following-sibling::div/input")).sendKeys("haptt"+EmployID);
		driver.findElement(By.xpath("//label[text()='Password']/parent::div/following-sibling::div/input")).sendKeys("123456aA@");
		driver.findElement(By.xpath("//label[text()='Confirm Password']/parent::div/following-sibling::div/input")).sendKeys("123456aA@");
		driver.findElement(By.xpath("//button[contains(string(),'Save')]")).click();
		
		sleepInsecon(8);
		Assert.assertEquals(driver.findElement(By.name("firstName")).getAttribute("value"), "haptt");
		Assert.assertEquals(driver.findElement(By.name("lastName")).getAttribute("value"), "Phạm");
		
		Assert.assertEquals(driver.findElement(By.xpath("//label[text()='Employee Id']/parent::div/following-sibling::div/input")).getAttribute("value"), EmployID);
		driver.findElement(By.xpath("//a[text()='Immigration']")).click();
		sleepInsecon(8);
		driver.findElement(By.xpath("//h6[text()='Assigned Immigration Records']/following-sibling::button")).click();
		sleepInsecon(6);
		
		driver.findElement(By.xpath("//label[text()='Number']/parent::div/following-sibling::div/input")).sendKeys(NumbertP);
		driver.findElement(By.xpath("//label[text()='Comments']/parent::div/following-sibling::div/textarea")).sendKeys(Commnets);
		driver.findElement(By.xpath("//button[contains(string(),'Save')]")).click();
		sleepInsecon(6);
		
		driver.findElement(By.cssSelector("i.bi-pencil-fill")).click();
		sleepInsecon(6);
		
		Assert.assertEquals(driver.findElement(By.xpath("//label[text()='Number']/parent::div/following-sibling::div/input")).getAttribute("value"),NumbertP);
		Assert.assertEquals(driver.findElement(By.xpath("//label[text()='Comments']/parent::div/following-sibling::div/textarea")).getAttribute("value"),Commnets);
		
		driver.findElement(By.cssSelector("p.oxd-userdropdown-name")).click();
		driver.findElement(By.xpath("//a[text()='Logout']")).click();
		sleepInsecon(6);
		
		driver.findElement(By.xpath("//div/input [@name='username']")).sendKeys("haptt"+EmployID);
		driver.findElement(By.xpath("//div/input [@name='password']")).sendKeys("123456aA@");
		driver.findElement(By.cssSelector("button[type='submit']")).click();
		sleepInsecon(5);
		
			
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