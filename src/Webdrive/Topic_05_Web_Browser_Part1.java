package Webdrive;

import java.lang.StackWalker.Option;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriver.Navigation;
import org.openqa.selenium.WebDriver.Options;
import org.openqa.selenium.WebDriver.TargetLocator;
import org.openqa.selenium.WebDriver.Timeouts;
import org.openqa.selenium.WebDriver.Window;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_05_Web_Browser_Part1 {
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
		
		//Tương tác với trình duyệt thì thông qua biến driver
		//Tương tác với Element thì thông qua biến WebElement element
		
	}

	@Test
	public void TC_01_Url() {
		//Java Document (cách sử dụng hàm này như thế nào)
		// >=2 tab thì khi dùng lệnh close nó chỉ đóng tab nó đang đứng
		//>1 tab thì khi đóng tab sẽ đóng luôn browse
		driver.close();//*
		
		//đóng luôn browser
		driver.quit();//**
		
		//Có thể lưu nó vào 1 biến để sử dụng cho các step sau=>dùng lại nhiều lần 
		//(biến được gán ở ví dụ dưới là emailtextbox
		WebElement emailtextbox = driver.findElement(By.xpath("//input[@id='email']"));
		emailtextbox.clear();
		emailtextbox.sendKeys("");
		
		//Bar code=>có thể sử dụng luôn bán nhưng như thế thời gian chạy sẽ lâu hơn 
		//driver.findElement(By.xpath("//input[@id='email']")).clear();
		//driver.findElement(By.xpath("//input[@id='email']")).sendKeys("");
		
		//Có thể sử dụng luôn không cần tạo biên
		driver.findElement(By.xpath("//input[@id='login']")).click();
		
		//Tìm 1 element
		WebElement emailtextbox1 = driver.findElement(By.xpath(""));//**
		
		//Tìm nhiều element
		List<WebElement> checkboxs=driver.findElements(By.xpath(""));//*
		
		//hàm get mở ra 1 url bất kỳ
		driver.get("https://www.facebook.com/");//**
		
		//Click vào link tiếng việt
		
		//Trả về Url của page hiện tại
		//Có thể lưu nó vào 1 biến để sử dụng cho các step sau=>dùng lại nhiều lần 
		String pagefacebookVN = driver.getCurrentUrl();
		Assert.assertEquals(pagefacebookVN, "https://vi-vn.facebook.com/");
		
		//Có thể sử dụng luôn không cần tạo biên =>Dùng 1 lần thì không cần tạo biến
		Assert.assertEquals(driver.getCurrentUrl(), "https://vi-vn.facebook.com/");
		
		//Trả về source code HTML của page hiện tại
		//Verify tương đối
		driver.getPageSource();
		Assert.assertTrue(driver.getPageSource().contains("Facebook giúp bạn kết nối và chia sẻ với mọi người trong cuộc sống của bạn."));
		
		//getTitle trả về title của page hiện tại
		Assert.assertEquals(driver.getTitle(), "Facebook - Đăng nhập hoặc đăng ký");
		
		//Lấy ra được id của windown/tab mà các driver đang đứng
		String LoginWindownId= driver.getWindowHandle();//**
		
		//Lấy ra tất cả ID của Window/tab 
		Set<String> AllIDS= driver.getWindowHandles(); //*
		
		//cooke$$ cache
		Options opt= driver.manage();
		opt.getCookies();//*
		//
		Timeouts time=opt.timeouts();
		//Khoảng thời gian chờ element xuất hiện
		time.implicitlyWait(5, TimeUnit.SECONDS) ; //**
		//Khoảng thời gian chờ page load xong trong vòng x giây
		time.pageLoadTimeout(5, TimeUnit.SECONDS);
		//Khoảng thời gian chờ script thực thi xong trong vòng x giây
		time.setScriptTimeout(5, TimeUnit.SECONDS);
		
		Window win= opt.window();
		win.fullscreen();
		win.maximize(); //size kích thước màn hình //**
		
		//test GUI: Font/size/color/Position/Location/...
		win.getPosition();
		win.getSize();
		
		Navigation nav= driver.navigate();
		nav.back(); //dùng cho nút back
		nav.forward(); 
		nav.refresh();
		nav.to("https://www.facebook.com/");
		//driver.get("https://www.facebook.com/");
		
		TargetLocator Taget= driver.switchTo();
		Taget.alert(); //*
		Taget.frame("");//*
		Taget.parentFrame();//*


	}
	

	@Test
	public void TC_02_Logo() {}

	@Test
	public void TC_03_Form() {}
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}