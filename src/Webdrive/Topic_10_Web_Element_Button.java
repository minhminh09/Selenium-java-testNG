package Webdrive;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.internal.junit.ExactComparisonCriteria;

public class Topic_10_Web_Element_Button {
	WebDriver driver;
	WebDriverWait driverWait;//gọi là way linh động=>nếu chưa tìm thấy element thì chờ tiếp, nếu thấy rồi thì không cần chờ tiếp
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");

	@BeforeClass
	public void beforeClass() {
		System.out.println(osName);
		if (osName.contains("Windows")) {
			System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		} else {
			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		}

		driver = new ChromeDriver();
		driverWait= new WebDriverWait(driver, 30);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
	}

	//@Test
	public void TC_01_Button() {
		driver.get("https://www.fahasa.com/customer/account/create");
		driver.findElement(By.cssSelector("ul#popup-login-tab_list a")).click();
		//tạo biến loginButton để sử dụng lại nhiều lần
		By loginButton= By.cssSelector("button.fhs-btn-login");
		
		//verify login button is disable
		Assert.assertFalse(driver.findElement(loginButton).isEnabled());
		
		String loginButtonbackground=driver.findElement(loginButton).getCssValue("background-image");
		
		Assert.assertTrue(loginButtonbackground.contains("rgb(224, 224, 224)"));
		
	
		
		driver.findElement(By.cssSelector("input#login_username")).sendKeys("0987654678");
		driver.findElement(By.cssSelector("input#login_password")).sendKeys("12345678");
		sleepInsecon(2);

		//Verify button is enable
		
		Assert.assertTrue(driver.findElement(loginButton).isEnabled());
		loginButtonbackground=driver.findElement(loginButton).getCssValue("background-color");
		Color loginButtonbackgroundColor=Color.fromString(loginButtonbackground);
		//Assert.assertEquals(loginButtonbackgroundColor.asRgb(),"rgb(201, 33, 39)");
		Assert.assertEquals(loginButtonbackgroundColor.asHex().toUpperCase(),"#C92127");//toUperCase là in ra text in hoa
		//dùng Hex để có thể chạy được trên tất cả các trình duyệt
		System.out.println(loginButtonbackground);
	}

	//@Test
	public void TC_02_Defau_Checkbox_Radio_Single() {
		
		//Verify checkbox/ radio selenium sử dụng hàm isSelected
		//isSelected chỉ verify cho thẻ input
		driver.get("https://automationfc.github.io/multiple-fields/");
		
		//Click chọn 1 checkbox
		driver.findElement(By.xpath("//label[contains(text(),'Fainting Spells')]/preceding-sibling::input")).click();
		//Click chọn 1 radio
		driver.findElement(By.xpath("//label[contains(text(), \" I don't have a diet plan \")]/preceding-sibling::input")).click();
		
		//verify checkbox/radio đã chọn
		
		Assert.assertTrue(driver.findElement(By.xpath("//label[contains(text(),'Fainting Spells')]/preceding-sibling::input")).isSelected());
		Assert.assertTrue(driver.findElement(By.xpath("//label[contains(text(), \" I don't have a diet plan \")]/preceding-sibling::input")).isSelected());
		
		//Check box có thể bỏ chọn được (để bỏ chọn)
		driver.findElement(By.xpath("//label[contains(text(),'Fainting Spells')]/preceding-sibling::input")).click();
		//verify checkbox đã bỏchọn
		Assert.assertFalse(driver.findElement(By.xpath("//label[contains(text(),'Fainting Spells')]/preceding-sibling::input")).isSelected());
		//Click chọn 1 radio
		driver.findElement(By.xpath("//label[contains(text(), \" I don't have a diet plan \")]/preceding-sibling::input")).click();
		//verify Radio vẫn được chọn (radio không cho bỏ chọn)
		Assert.assertTrue(driver.findElement(By.xpath("//label[contains(text(), \" I don't have a diet plan \")]/preceding-sibling::input")).isSelected());

	}
	//@Test
	public void TC_02_Defau_Checkbox_Multiple() {
		
		//Verify checkbox/ radio selenium sử dụng hàm isSelected
		//isSelected chỉ verify cho thẻ input
		driver.get("https://automationfc.github.io/multiple-fields/");
		
		List<WebElement>allCheckbox= driver.findElements(By.cssSelector("input.form-checkbox"));
		//Dùng vòng lặp để duyệt qua và click vào tất cả các checkbox này
		for (WebElement checkbox : allCheckbox) {
			checkbox.click();
			//sleepInsecon(1);
		}
		//click tất cả các checkbox
		for (WebElement checkbox : allCheckbox) {
			Assert.assertTrue(checkbox.isSelected()); 	 	
		}
		
		//	Kiểm tra đúng checkbox cần click thì mới click
		for (WebElement checkbox : allCheckbox) {
			if (checkbox.getAttribute("value").equals("Gout")) {
			checkbox.click();}
		}
				
			}
			
		
		
	
	
	@Test
	public void TC_03_VueJS() {
		driver.get("https://demos.telerik.com/kendo-ui/checkbox/index");
		
		//Click vào checkbox và kiểm tra nó được chọn
		//Kiểm tra checkbox xem đã được tích chọn chưa dùng dấu !
		checktoCheckbox(By.xpath("//label[text()='Dual-zone air conditioning']/preceding-sibling::input"));
	 	Assert.assertTrue(driver.findElement(By.xpath("//label[text()='Dual-zone air conditioning']/preceding-sibling::input")).isSelected());
		sleepInsecon(2);
		
	
		//Click vào checkbox và kiểm tra nó đã bỏ chọn
		unchecktoCheckbox(By.xpath("//label[text()='Dual-zone air conditioning']/preceding-sibling::input")); 
		
		Assert.assertFalse(driver.findElement(By.xpath("//label[text()='Dual-zone air conditioning']/preceding-sibling::input")).isSelected());
		sleepInsecon(2);
		
		//Check Radio
		driver.get("https://demos.telerik.com/kendo-ui/radiobutton/index");
		sleepInsecon(2);
		
		driver.findElement(By.xpath("//label[contains(text(),'2.0 Petrol, 147kW')]/preceding-sibling::input")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//label[contains(text(),'2.0 Petrol, 147kW')]/preceding-sibling::input")).isSelected());

		
	}
	
	
	public void checktoCheckbox(By by) {
		if (!driver.findElement(by).isSelected()) {
			driver.findElement(by).click();
		}
		
	}
	
	public void unchecktoCheckbox(By by) {
		if (driver.findElement(by).isSelected()) {
			driver.findElement(by).click();
		}
		
	}
	
	@Test
	public void TC_04_edittable() {
	
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