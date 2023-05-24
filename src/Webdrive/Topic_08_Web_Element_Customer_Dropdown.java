package Webdrive;

import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.internal.junit.ExactComparisonCriteria;

public class Topic_08_Web_Element_Customer_Dropdown {
	WebDriver driver;
	WebDriverWait driverWait;//gọi là way linh động=>nếu chưa tìm thấy element thì chờ tiếp, nếu thấy rồi thì không cần chờ tiếp
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
		driverWait= new WebDriverWait(driver, 30);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
	}

	@Test
	public void TC_01_Jquery() {
		
		driver.get("https://jqueryui.com/resources/demos/selectmenu/default.html");
		//Click vào thẻ bất kỳ cho sổ ra item
		driver.findElement(By.id("speed-button")).click();
		//Chờ tất cả các item load thành công
		//Locater phải lấy đại diện cho tất cả item
		//Lấy đến thẻ chứa text
		driverWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("ul#speed-menu div[role='option']")));
		//đưa hết các item trong dropdown vào 1 list
		List<WebElement> speeDropdownItems= driver.findElements(By.cssSelector("ul#speed-menu div[role='option']"));
		
		//Tìm item xem đúng cái cần chọn hay không (dùng vòng lặp duyệt qua)
		for (WebElement tempItem: speeDropdownItems) {
			String itemtext=tempItem.getText();
			System.out.println(itemtext);
			//Kiểm tra các text trong item đúng cái mình chọn không
			if(itemtext.equals("Fast")) {
			//Click vào item đó
			tempItem.click();
			//Thoát ra khỏi vòng lặp sau khi tìm kiếm từ khóa
			break;
			}
		}
		
		
	}

	@Test
	public void TC_02_Logo() {}

	@Test
	public void TC_03_Form() {}
	
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