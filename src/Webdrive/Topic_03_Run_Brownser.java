package Webdrive;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_03_Run_Brownser {
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
	public void TC_01_Emtydata() {
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");
		//Cách làm ít bị sai cú pháp, không đúng, mở đóng ngoặc
		//Sugget code lại sử dụng phím: Ctrl space
		driver.findElement(By.xpath("//form[@id='frmLogin']//button[text()='ĐĂNG KÝ']")).click();
		//Verify--lấy 1 doạn text ra
		driver.findElement(By.id("txtFirstname-error")).getText();
		//Asser.asserTrue=>kiểm tra 1 điều kiện trả về đúng
		//Asser.asserFalse=>kiểm tra 1 điều kiện trả về sai
		//Asser.asserEquals=>kiểm tra thực tế với mong đợi như nhau
		//Data type Element # String =>do vậy cần gettext
		//Data type String=String
		//Khi gettext thì công thì Data value="Vui lòng nhập họ tên"= "Vui lòng nhập họ tên" (bằng với dữ liệu mong muốn)
		
		Assert.assertEquals(driver.findElement(By.id("txtFirstname-error")).getText(), "Vui lòng nhập họ tên");
		Assert.assertEquals(driver.findElement(By.id("txtEmail-error")).getText(), "Vui lòng nhập email");
		Assert.assertEquals(driver.findElement(By.id("txtCEmail-error")).getText(), "Vui lòng nhập lại địa chỉ email");
		Assert.assertEquals(driver.findElement(By.id("txtPassword-error")).getText(), "Vui lòng nhập mật khẩu");
		Assert.assertEquals(driver.findElement(By.id("txtCPassword-error")).getText(), "Vui lòng nhập lại mật khẩu");
		Assert.assertEquals(driver.findElement(By.id("txtPhone-error")).getText(), "Vui lòng nhập số điện thoại.");
	}

	@Test
	public void TC_02_Invalid_Email() {
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");
		//driver.findElement(By.id("txtFirstname"))=>gọi đến trường : Họ và tên
		//sendKeys("haptt"): nhập giá trị text vào trường
		//Action
		driver.findElement(By.id("txtFirstname")).sendKeys("haptt");
		driver.findElement(By.id("txtEmail")).sendKeys("haptt@@");
		driver.findElement(By.id("txtCEmail")).sendKeys("haptt@@");
		driver.findElement(By.id("txtPassword")).sendKeys("123456aA@");
		driver.findElement(By.id("txtCPassword")).sendKeys("123456aA@");
		driver.findElement(By.id("txtPhone")).sendKeys("0987323874");
		
		driver.findElement(By.xpath("//form[@id='frmLogin']//button[text()='ĐĂNG KÝ']")).click();
		//Very file
		Assert.assertEquals(driver.findElement(By.id("txtEmail-error")).getText(), "Vui lòng nhập email hợp lệ");
		Assert.assertEquals(driver.findElement(By.id("txtCEmail-error")).getText(), "Email nhập lại không đúng");
	}

	@Test
	public void TC_03_Incorrect_Email() {
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");
		//driver.findElement(By.id("txtFirstname"))=>gọi đến trường : Họ và tên
		//sendKeys("haptt"): nhập giá trị text vào trường
		//Action
		driver.findElement(By.id("txtFirstname")).sendKeys("haptt");
		driver.findElement(By.id("txtEmail")).sendKeys("haptt@gmail.com");
		driver.findElement(By.id("txtCEmail")).sendKeys("haptt@gmail.net");
		driver.findElement(By.id("txtPassword")).sendKeys("123456aA@");
		driver.findElement(By.id("txtCPassword")).sendKeys("123456aA@");
		driver.findElement(By.id("txtPhone")).sendKeys("0987323874");
		
		driver.findElement(By.xpath("//form[@id='frmLogin']//button[text()='ĐĂNG KÝ']")).click();
		//Very file
		//Assert.assertEquals(driver.findElement(By.id("txtEmail-error")).getText(), "Vui lòng nhập email hợp lệ");
		Assert.assertEquals(driver.findElement(By.id("txtCEmail-error")).getText(), "Email nhập lại không đúng");
	}
	
	
	@Test
	public void TC_04_Invalid_Password() {
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");
		//driver.findElement(By.id("txtFirstname"))=>gọi đến trường : Họ và tên
		//sendKeys("haptt"): nhập giá trị text vào trường
		//Action
		driver.findElement(By.id("txtFirstname")).sendKeys("haptt");
		driver.findElement(By.id("txtEmail")).sendKeys("haptt@gmail.com");
		driver.findElement(By.id("txtCEmail")).sendKeys("haptt@gmail.com");
		driver.findElement(By.id("txtPassword")).sendKeys("1234");
		driver.findElement(By.id("txtCPassword")).sendKeys("1234");
		driver.findElement(By.id("txtPhone")).sendKeys("0987323874");
		
		driver.findElement(By.xpath("//form[@id='frmLogin']//button[text()='ĐĂNG KÝ']")).click();
		//Very file
	    Assert.assertEquals(driver.findElement(By.id("txtPassword-error")).getText(), "Mật khẩu phải có ít nhất 6 ký tự");
		Assert.assertEquals(driver.findElement(By.id("txtCPassword-error")).getText(), "Mật khẩu phải có ít nhất 6 ký tự");
	}
	
	@Test
	public void TC_05_Incorrect_Password() {
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");
		//driver.findElement(By.id("txtFirstname"))=>gọi đến trường : Họ và tên
		//sendKeys("haptt"): nhập giá trị text vào trường
		//Action
		driver.findElement(By.id("txtFirstname")).sendKeys("haptt");
		driver.findElement(By.id("txtEmail")).sendKeys("haptt@gmail.com");
		driver.findElement(By.id("txtCEmail")).sendKeys("haptt@gmail.com");
		driver.findElement(By.id("txtPassword")).sendKeys("123456");
		driver.findElement(By.id("txtCPassword")).sendKeys("123465");
		driver.findElement(By.id("txtPhone")).sendKeys("0987323874");
		
		driver.findElement(By.xpath("//form[@id='frmLogin']//button[text()='ĐĂNG KÝ']")).click();
		//Very file
		//Assert.assertEquals(driver.findElement(By.id("txtPassword")).getText(), "Mật khẩu phải có ít nhất 6 ký tự");
		Assert.assertEquals(driver.findElement(By.id("txtCPassword-error")).getText(), "Mật khẩu bạn nhập không khớp");
	}
	
	@Test
	public void TC_04_Invalid_Phonenumber() {
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");
		//driver.findElement(By.id("txtFirstname"))=>gọi đến trường : Họ và tên
		//sendKeys("haptt"): nhập giá trị text vào trường
		//Action
		driver.findElement(By.id("txtFirstname")).sendKeys("haptt");
		driver.findElement(By.id("txtEmail")).sendKeys("haptt@gmail.com");
		driver.findElement(By.id("txtCEmail")).sendKeys("haptt@gmail.com");
		driver.findElement(By.id("txtPassword")).sendKeys("123456");
		driver.findElement(By.id("txtCPassword")).sendKeys("123465");
		driver.findElement(By.id("txtPhone")).sendKeys("0987");
		
		driver.findElement(By.xpath("//form[@id='frmLogin']//button[text()='ĐĂNG KÝ']")).click();
		//Verify 1-nhập SĐT<10 KÝ TỰ
		Assert.assertEquals(driver.findElement(By.id("txtPhone-error")).getText(), "Số điện thoại phải từ 10-11 số.");
		//Verify 2 - NHẬP SĐT>11 KÝ TỰ
		driver.findElement(By.id("txtPhone")).clear(); //Clear dữ liệu cũ sau đó mới nhập dữ liệu mới
		driver.findElement(By.id("txtPhone")).sendKeys("098702239403");
		driver.findElement(By.xpath("//form[@id='frmLogin']//button[text()='ĐĂNG KÝ']")).click();
		Assert.assertEquals(driver.findElement(By.id("txtPhone-error")).getText(), "Số điện thoại phải từ 10-11 số.");
		
		//Verify 3 - NHẬP SĐT KHÔNG ĐÚNG ĐỊNH DẠNG ĐẦU SỐ
		driver.findElement(By.id("txtPhone")).clear(); //Clear dữ liệu cũ sau đó mới nhập dữ liệu mới
		driver.findElement(By.id("txtPhone")).sendKeys("8479237934");
		driver.findElement(By.xpath("//form[@id='frmLogin']//button[text()='ĐĂNG KÝ']")).click();
		Assert.assertEquals(driver.findElement(By.id("txtPhone-error")).getText(), "Số điện thoại bắt đầu bằng: 09 - 03 - 012 - 016 - 018 - 019 - 088 - 03 - 05 - 07 - 08");
		
	}
	

	
	
	
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}