package Webdrive;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_11_Web_Element_CustomerCheckbox_Radio {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	private JavascriptExecutor js;
	
	

	@BeforeClass
	public void beforeClass() {
		System.out.println(osName);
		if (osName.contains("Windows")) {
			System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		} else {
			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		}

		driver = new ChromeDriver();
		js = (JavascriptExecutor) driver;
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
	}

	//@Test
	public void TC_01_() {
		driver.get("https://tiemchungcovid19.gov.vn/portal/register-person");;
		
		//Chọn 
		driver.findElement(By.cssSelector("//div[text()='Đăng ký cho người thân']/preceding-sibling::div/input")).click();
		sleepInsecon(3);
		
		//CASE1**//
		//Thẻ input bị che nên không thể thao tác được
		//Thẻ input lại dùng để verify được=>và hàm isSelected chỉ làm việc với input
		
		
		//verify giá trị đã chọn thành công
		Assert.assertTrue(driver.findElement(By.cssSelector("//div[text()='Đăng ký cho người thân']/preceding-sibling::div/input")).isSelected());
		
	}

	//@Test
	public void TC_02_() {
		driver.get("https://tiemchungcovid19.gov.vn/portal/register-person");;
		sleepInsecon(3);
		
		//CASE 2**//
		//Dùng 1 thẻ khác với input để thao tác như: span, div, label
		//Thẻ này lại dùng để verify được=>và hàm isSelected chỉ làm việc với input
		
		driver.findElement(By.xpath("//div[text()='Đăng ký cho người thân']/preceding-sibling::div")).click();
		//verify giá trị đã chọn thành công
		Assert.assertTrue(driver.findElement(By.xpath("//div[text()='Đăng ký cho người thân']/preceding-sibling::div")).isSelected());
		//Thẻ span/div/label=>luôn luôn trả về false
		
	}

	//@Test
	public void TC_03_() {
		driver.get("https://tiemchungcovid19.gov.vn/portal/register-person");;
		sleepInsecon(3);
		
		//CASE **//
		//Dùng 1 thẻ khác với input để thao tác như: span, div, label
		//Thẻ input lại dùng để verify được=>và hàm isSelected chỉ làm việc với input
		
		driver.findElement(By.xpath("//div[text()='Đăng ký cho người thân']/preceding-sibling::div")).click();
		sleepInsecon(3);
		//verify giá trị đã chọn thành công
		Assert.assertTrue(driver.findElement(By.xpath("//div[text()='Đăng ký cho người thân']/preceding-sibling::div/input")).isSelected());
		//có thể dùng 1 hàm để click và 1 hàm khác để verify tuy nhiên chỉ nên dùng khi demo
		//Nếu dự án thực tế thì không nên dùng=>dể gây hiểu nhầm và khó maintain
		
	}
	public void TC_04() {
		driver.get("https://tiemchungcovid19.gov.vn/portal/register-person");;
		sleepInsecon(3);
		
		//CASE 4**//
		//Thẻ input bị ẩn nhưng vẫn dùng để click
		//Hàm Click() của WebElement không thao tác với element bị ẩn được
		//Nên dùng 1 hàm Click() của Javascript để Click(Click vào element bị ẩn được)
		//Thẻ input lại dùng để verify được=>và hàm isSelected chỉ làm việc với input
		//Selenium có cung cấp 1 thư viện để có thể nhúng các đoạn code JS vào  kịch bản test được ->JavascriptExcutor
		
		By Radiobutton= By.xpath("//div[text()='Đăng ký cho người thân']/preceding-sibling::div/input");
		
		//jsExecutor.executeScript("argument[0].click();",driver.findElement(Radiobutton));
		
		 js.executeScript("arguments[0].click();", driver.findElement(Radiobutton));
		//jsExecutor.
		sleepInsecon(3);
		//verify giá trị đã chọn thành công
		Assert.assertTrue(driver.findElement(Radiobutton).isSelected());
		
		
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