package TEST;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Test
public class Topic_02_Selenium_Locate {
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
		//Mở trang Register ra
		driver.get("https://demo.nopcommerce.com/register");
	}
	
	//HTML của Firtname textbox
	//<input type="text" data-val="true" data-val-required="First name is required." 
	//id="FirstName" name="FirstName">
    //Tên thẻ element  (tagname HTML): input
	//Tên của thuộc tính (attribute name): type, data-val, data-val-required id name
	//Giá trị của thuộc tính (attribute value): text, true, first name is requited.
	public void TC_01_ID() {
		//Muốn thao tác lên Element nào thì phải tìm được Element đó: findElement
		//find theo cái đó: id/class/name/css/Xpath...
		//Find tìm thấy Element rồi thì action lên Element đó: click, sendKeys....
		//Sendkeys: là nhập text vào Element
		driver.findElement(By.id("FirstName")).sendKeys("Selenium");
	}

	public void TC_02_class() {
		//Mở màn hình search
		driver.get("https://demo.nopcommerce.com/search");
		//Nhập text vào search
		driver.findElement(By.className("search-text")).sendKeys("Windown");
	}


	public void TC_03_Name() {
		//Click vào Advanced search checkbox sử dụng Click
		driver.findElement(By.name("advs")).click();
	}
	
	public void TC_04_TagName() {
		//Tìm có bao nhiêu thẻ input trên màn hình hiện tại
		System.out.println(driver.findElement(By.tagName("input")).getSize());	
	}
	public void TC_05_Link() {
		//Click vào đường link Addressé (tuyệt đối)
		driver.findElement(By.linkText("My account")).click();
	}
	
	public void TC_06_partialLinkText() {
		//Click vào đường link Addressé (tương đối)
		driver.findElement(By.partialLinkText("My account")).click();
	}
	
	public void TC_07_CSS() {
		//Mở LẠI trang Register
		driver.get("https://demo.nopcommerce.com/register");
		//1
		driver.findElement(By.cssSelector("input#FirstName")).sendKeys("Windown");
		//2
		driver.findElement(By.cssSelector("input[id=LastName]")).sendKeys("Selenium");
		//3
		driver.findElement(By.cssSelector("input[Name=Email]")).sendKeys("haptt@gmail.com");
	}
	public void TC_08_Xpath() {
		//Mở LẠI trang Register
		driver.get("https://demo.nopcommerce.com/register");
		//1
		driver.findElement(By.xpath("//input[@data-val-required='First name is required.']")).sendKeys("Windown");
		//2
		driver.findElement(By.xpath("//input[@id='LastName']")).sendKeys("Selenium");
		//3
		driver.findElement(By.xpath("//label[text()='Email:']/following-sibling::input")).sendKeys("haptt@gmail.com");
	}
	@AfterClass
	public void afterClass() {
		//driver.quit();
	}
}