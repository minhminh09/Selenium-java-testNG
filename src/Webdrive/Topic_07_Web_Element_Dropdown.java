package Webdrive;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_07_Web_Element_Dropdown {
	WebDriver driver;
	//Select select;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	String firstName, lastName, emailAdress, CompanyName, password, day, moth, year,CountryName, provinceName, cityName, Adress, postalcode, Phonenumber;

	@BeforeClass
	public void beforeClass() {
		System.out.println(osName);
		if (osName.contains("Windows")) {
			System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		} else {
			System.setProperty("webdriver.chrome.driver", projectPath + "/browserDrivers/chromedriver");
		}

		//driver = new FirefoxDriver(); =>sử dụng để chạy trình duyệt firefox và trong câu lệnh IF-Else cần thay chrome=gecko
		driver= new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		firstName= "Elon";
		lastName= "Musk"; 
		emailAdress= "Elonmusk"+ getRandomNumber()+ "@gmail.com";
		CompanyName="New York";
		password="123456aA@";
		day="13";
		moth="March";
		year="1981";
		CountryName="Viet Nam";
		provinceName="Other";
		cityName="Ha Noi";
		Adress="Buiding";
		postalcode="11008";
		Phonenumber="0932947943";
	}

	@Test
	public void TC_01_defau_Dropdown() {
		driver.get("https://demo.nopcommerce.com/register");
		driver.findElement(By.xpath("//a[text()='Register']")).click();
		driver.findElement(By.id("FirstName")).sendKeys(firstName);
		driver.findElement(By.id("LastName")).sendKeys(lastName);

		//select=new Select(driver.findElement(By.name("DateOfBirthDay")));
		//Chọn ngày 7 sử dụng Index
		//index không dùng được nếu thay đổi tên, thứ tự
		//thực tế sẽ không nhớ được giá trị lấy là giá trị nào
		//select.selectByIndex(7);
		//Chọn ngày 14 sử dụng value
		//value là thuộc tính không bắt buộc của item, do vậy có thuộc tính có, có thuộc tính không
		//value có thông tin dài khó nhớ=>thì sẽ không nhớ được nó là giá trị gì
		//select.selectByValue("14");
		//Chọn ngày 18 sử dụng text
		//dùng được nếu droplist hay thay đổi
		//Text sẽ là giá trị thường xuyên được xử dụng
		//dùng Text thì khi testcase fail thì manual test rất dễ
		//select.selectByVisibleText("18");
		
		new Select(driver.findElement(By.name("DateOfBirthDay"))).selectByVisibleText(day);
		new Select(driver.findElement(By.name("DateOfBirthMonth"))).selectByVisibleText(moth);
		new Select(driver.findElement(By.name("DateOfBirthYear"))).selectByVisibleText(year);

		
		driver.findElement(By.id("Email")).sendKeys(emailAdress);
		driver.findElement(By.id("Company")).sendKeys(CompanyName);
		driver.findElement(By.id("Password")).sendKeys(password);
		driver.findElement(By.id("ConfirmPassword")).sendKeys(password);
		
		driver.findElement(By.id("register-button")).click();
		sleepInsecon(2);
		Assert.assertEquals(driver.findElement(By.cssSelector("div.result")).getText(), "Your registration completed");
		
		driver.findElement(By.xpath("//a[text()='Log in']")).click();
		driver.findElement(By.id("Email")).sendKeys(emailAdress);
		driver.findElement(By.id("Password")).sendKeys(password);
		driver.findElement(By.xpath("//button[text()='Log in']")).click();
		sleepInsecon(2);
		
		driver.findElement(By.xpath("//div[@class='header-links']/ul/li/a[text()='My account']")).click();
		sleepInsecon(3);
		
		//verify
		Assert.assertEquals(driver.findElement(By.id("FirstName")).getAttribute("value"),firstName );
		Assert.assertEquals(driver.findElement(By.id("LastName")).getAttribute("value"),lastName );
		
		//new Select(driver.findElement(By.name("DateOfBirthDay"))).getFirstSelectedOption().getText();
		Assert.assertEquals(new Select(driver.findElement(By.name("DateOfBirthDay"))).getFirstSelectedOption().getText(), day);
		Assert.assertEquals(new Select(driver.findElement(By.name("DateOfBirthMonth"))).getFirstSelectedOption().getText(), moth);
		Assert.assertEquals(new Select(driver.findElement(By.name("DateOfBirthYear"))).getFirstSelectedOption().getText(), year);
		Assert.assertEquals(driver.findElement(By.id("Company")).getAttribute("value"),CompanyName );
	}

	@Test
	public void TC_02_add_Adress() {
		driver.findElement(By.cssSelector("li.customer-addresses>a")).click();
		driver.findElement(By.cssSelector("div.add-button>button")).click();
		
		driver.findElement(By.id("Address_FirstName")).sendKeys(firstName);
		driver.findElement(By.id("Address_LastName")).sendKeys(lastName);
		driver.findElement(By.id("Address_Email")).sendKeys(emailAdress);
		driver.findElement(By.id("Address_Company")).sendKeys(CompanyName);
		new Select (driver.findElement(By.id("Address_CountryId"))).selectByVisibleText(CountryName);
		new Select (driver.findElement(By.id("Address_StateProvinceId"))).selectByVisibleText(provinceName);

		driver.findElement(By.id("Address_City")).sendKeys(cityName);
		driver.findElement(By.id("Address_Address1")).sendKeys(Adress);
		driver.findElement(By.id("Address_ZipPostalCode")).sendKeys(postalcode);
		driver.findElement(By.id("Address_PhoneNumber")).sendKeys(Phonenumber);
		driver.findElement(By.cssSelector("button.save-address-button")).click();
		
		Assert.assertEquals(driver.findElement(By.cssSelector("li.name")).getText(), firstName +" "+ lastName);
		Assert.assertTrue(driver.findElement(By.cssSelector("li.email")).getText().contains(emailAdress));
		Assert.assertTrue(driver.findElement(By.cssSelector("li.phone")).getText().contains(Phonenumber));
		Assert.assertEquals(driver.findElement(By.cssSelector("li.company")).getText(), CompanyName);
		Assert.assertEquals(driver.findElement(By.cssSelector("li.address1")).getText(), Adress);
		//Assert.assertEquals(driver.findElement(By.cssSelector("li.address1")).getText(), Adress);

	
		
	}

	public int getRandomNumber() {
		Random rand=new Random();
		return rand.nextInt(9999);
		
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