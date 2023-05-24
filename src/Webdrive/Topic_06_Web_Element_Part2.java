package Webdrive;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_06_Web_Element_Part2 {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	
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

		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
	}

	//@Test
	public void TC_01_isDisplayed()
			 {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		//Textbox, textArea nếu có hiển thị thì điền thông tin và in ra
		if(driver.findElement(emailtextbox).isDisplayed()) {
			driver.findElement(emailtextbox).sendKeys("haptt@gmail.com");
			System.out.println("Email textbox is Displayed");
		}
		else {System.out.println("Email textbox is not Displayed");}
		
		//textArea nếu có hiển thị thì điền thông tin và in ra
		if (driver.findElement(educationTextArea).isDisplayed()) {
			driver.findElement(educationTextArea).sendKeys("Chào mừng bạn!");
			System.out.println("Education textArea is Displayed");
			
		} else { System.out.println("Education textArea is not Displayed");

		}
		//Radio nếu có hiển thị thì điền thông tin và in ra
		if (driver.findElement(ageUnder18Radio).isDisplayed()) {
			driver.findElement(ageUnder18Radio).click();
			System.out.println("ageUnder18Radio is Displayed");
					
			} else { System.out.println("ageUnder18Radio is not Displayed");

				}
		
		//textArea nếu có hiển thị thì điền thông tin và in ra
			if (driver.findElement(user5).isDisplayed()) {
				System.out.println("User 5 is Displayed");
							
			} else { System.out.println("User 5 is not Displayed");

			}
		
			 }

	//@Test
	public void TC_02_Enable_Disable() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		if (driver.findElement(passwordTextbox).isEnabled()) {
			System.out.println("passwork is Enableed");
		} else { System.out.println("passwork is Disable");

		}
		if (driver.findElement(BiographyTextArea).isEnabled()) {
			System.out.println("BiographyTextArea is Enableed");
		} else { System.out.println("BiographyTextArea is Disable");

		}
		if (driver.findElement(emailtextbox).isEnabled()) {
			System.out.println("emailtextbox is Enableed");
		} else { System.out.println("emailtextbox is Disable");

		}
		
		
	}

	//@Test
	public void TC_03_Checkbox() {
	driver.get("https://automationfc.github.io/basic-form/index.html");
	
	//verify checkbox/radio button is diselected
	//Kiem tra checkbox va radio button chua duoc chon
	Assert.assertFalse(driver.findElement(ageUnder18Radio).isSelected());
	Assert.assertFalse(driver.findElement(devlopmentcheckbox).isSelected());
	
	//Click to checkbox/radio button
	driver.findElement(ageUnder18Radio).click();
	driver.findElement(devlopmentcheckbox).click();
	
	sleepInsecon(3);
	
	//verify checkbox/radio button is selected
	//Kiem tra checkbox va radio button  duoc chon
	Assert.assertTrue(driver.findElement(ageUnder18Radio).isSelected());
	Assert.assertTrue(driver.findElement(devlopmentcheckbox).isSelected());
	
	
		
	}
	
	@Test
	public void TC_03_EmailChimp() {
		
		driver.get("https://login.mailchimp.com/signup/");
		driver.findElement(By.id("email")).sendKeys("tesst.o1@gmail.com");
		By Passwordtbox= By.id("new_password");
		By SignUpButton=By.id("create-account-enabled");
		
		
		driver.findElement(Passwordtbox).sendKeys("abc");
		driver.findElement(SignUpButton).click();
		sleepInsecon(3);
		
		//Verify lowercase
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class=\"lowercase-char completed\"]")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class=\"uppercase-char not-completed\"]")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class=\"number-char not-completed\"]")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class=\"special-char not-completed\"]")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class=\"8-char not-completed\"]")).isDisplayed());
		//Clear dữ liệu cũ và nhập cái mới
		driver.findElement(Passwordtbox).clear();
		driver.findElement(Passwordtbox).sendKeys("ABC");
		//driver.findElement(SignUpButton).click();
		sleepInsecon(3);
		//Verify uppercase
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class=\"lowercase-char not-completed\"]")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class=\"uppercase-char completed\"]")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class=\"number-char not-completed\"]")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class=\"special-char not-completed\"]")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class=\"8-char not-completed\"]")).isDisplayed());
		
		//Clear dữ liệu cũ và nhập cái mới
		driver.findElement(Passwordtbox).clear();
		driver.findElement(Passwordtbox).sendKeys("123");
		//driver.findElement(SignUpButton).click();
		sleepInsecon(3);
		//Verify number
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class=\"lowercase-char not-completed\"]")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class=\"uppercase-char not-completed\"]")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class=\"number-char completed\"]")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class=\"special-char not-completed\"]")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class=\"8-char not-completed\"]")).isDisplayed());
		
		//Clear dữ liệu cũ và nhập cái mới
		driver.findElement(Passwordtbox).clear();
		driver.findElement(Passwordtbox).sendKeys("#@");
		//driver.findElement(SignUpButton).click();
		sleepInsecon(3);
		//Verify special-char
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class=\"lowercase-char not-completed\"]")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class=\"uppercase-char not-completed\"]")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class=\"number-char not-completed\"]")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class=\"special-char completed\"]")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class=\"8-char not-completed\"]")).isDisplayed());	
		
		//Clear dữ liệu cũ và nhập cái mới
		driver.findElement(Passwordtbox).clear();
		driver.findElement(Passwordtbox).sendKeys("admin1234");
		//driver.findElement(SignUpButton).click();
		sleepInsecon(3);
		//Verify char>8
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class=\"lowercase-char completed\"]")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class=\"uppercase-char not-completed\"]")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class=\"number-char completed\"]")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class=\"special-char completed\"]")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class=\"8-char completed\"]")).isDisplayed());	
		
		//Clear dữ liệu cũ và nhập cái mới
		driver.findElement(Passwordtbox).clear();
		driver.findElement(Passwordtbox).sendKeys("Admin1234@");
		//driver.findElement(SignUpButton).click();
		sleepInsecon(3);
		//Verify 8-char
		Assert.assertFalse(driver.findElement(By.xpath("//li[@class=\"lowercase-char completed\"]")).isDisplayed());
		Assert.assertFalse(driver.findElement(By.xpath("//li[@class=\"uppercase-char completed\"]")).isDisplayed());
		Assert.assertFalse(driver.findElement(By.xpath("//li[@class=\"number-char completed\"]")).isDisplayed());
		Assert.assertFalse(driver.findElement(By.xpath("//li[@class=\"special-char completed\"]")).isDisplayed());
		Assert.assertFalse(driver.findElement(By.xpath("//li[@class=\"8-char completed\"]")).isDisplayed());	


		
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