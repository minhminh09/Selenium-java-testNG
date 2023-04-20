package Webdrive;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_06_Web_Element {
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
		
	}

	@Test
	public void TC_01_WebElement() {
		driver.get("https://demo.nopcommerce.com/login?returnUrl=%2F");
		
		//Khi element được dùng lại nhiều thì khai báo biên, nếu dùng 1 lần thì không cần
		//WebElement emailtextbox=driver.findElement(By.id("Email"));
		//emailtextbox.isDisplayed();
		//emailtextbox.clear();
		//emailtextbox.sendKeys("");
		//Sử dụng 1 lần không cần khai báo biến
		//driver.findElement(By.id("Email")).sendKeys("");
		//WebElement passwordtextbox=driver.findElement(By.id("Password"));
		//Tương tác được với element được thì phải tìm được element đó
		//THông qua các Locator của nó
		//By: id/class/name/xpath/css/tagname/Linktext/partialLinktext
	//	emailtextbox.sendKeys("");
		//passwordtextbox.sendKeys("");
		
		WebElement element= driver.findElement(By.className(""));
		//clear dùng cho các textbox, textarea, dropdown (editable)
		//Dùng xóa dữ liệu đi trước khi nhập text
		element.clear(); //*
		
		//Sendkys là để nhập cái gì vào
		element.sendKeys(""); //**
		
		//dùng để click vào button, radio, ảnh...
		element.click();//**
		
		String Searchattribute = element.getAttribute("placeholder"); //** //khi chạy xong nó sẽ lấy ra chữ "Search store"
		
		//GUI: font, size, color, locator, positon,...
		element.getCssValue("background-color"); //*   //#4ab2f1; gọi tên thuộc tính để lấy ra màu
		element.getSize();
		element.getLocation();
		
		//chụp hình khi testcase fail
		element.getScreenshotAs(OutputType.BASE64);
		element.getScreenshotAs(OutputType.FILE);//*
		element.getScreenshotAs(OutputType.BYTES);
		
		//Cần lấy ra cái tên thẻ HTML của element đó=>truyền vào cho 1 locator khác
		driver.findElement(By.id("Email")).getTagName();
		driver.findElement(By.name("Email")).getTagName();
		
		String EmailtextboxTagname= driver.findElement(By.cssSelector("#Email")).getTagName();
		driver.findElement(By.xpath("//" + EmailtextboxTagname + "[@is='Email'"));

		//Lấy text từ Error message/success message/label/header...
		element.getText(); //**
		
		//Khi nào dùng gettext(), khi nào dùng getAttribute?
		//Khi value cần lấy nằm bên ngoài thẻ thì dùng =>getText()
		//Khi value cần lấy nằm trong thẻ thì dùng =>getAttribute()
		
		//Dùng để verify xem element có hiển thị hay không
		//Phạm vi: tất cả các element
		element.isDisplayed();
		Assert.assertTrue(element.isDisplayed());//**
		Assert.assertFalse(element.isDisplayed());
		
		//Dùng để verify xem element có thao tác được hay không
		//Phạm vi: tất cả các element
		Assert.assertTrue(element.isEnabled());
		Assert.assertFalse(element.isEnabled());
		
		//Dùng để verify xem element có thao tác được hay không
		//Phạm vi: checkbox/Radio  =>vì nó mới có thuộc tính chọn rồi hay chưa
		Assert.assertTrue(element.isSelected());//*
		Assert.assertFalse(element.isSelected());
	}


	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}