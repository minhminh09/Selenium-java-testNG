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
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.internal.junit.ExactComparisonCriteria;

public class Topic_09_Web_Element_Customer_Dropdown {
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
	public void TC_01_Jquery() {
		
		driver.get("https://jqueryui.com/resources/demos/selectmenu/default.html");
		//Muốn chọn item cho speed Dropdown
		selectItemInDropdown("span#speed-button", "ul#speed-menu div[role='option']", "Fast");
		sleepInsecon(3);
		//verify text
		Assert.assertEquals(driver.findElement(By.cssSelector("span#speed-button>span.ui-selectmenu-text")).getText(), "Fast");
		
		selectItemInDropdown("span#speed-button", "ul#speed-menu div[role='option']", "Slower");
		sleepInsecon(3);
		Assert.assertEquals(driver.findElement(By.cssSelector("span#speed-button>span.ui-selectmenu-text")).getText(), "Slower");

		selectItemInDropdown("span#salutation-button", "ul#salutation-menu div[role='option']", "Prof.");
		sleepInsecon(3);
		Assert.assertEquals(driver.findElement(By.cssSelector("span#salutation-button>span.ui-selectmenu-text")).getText(), "Prof.");

	}

	//@Test
	public void TC_02_ReactJS() {
		driver.get("https://react.semantic-ui.com/maximize/dropdown-example-selection/");
		
		selectItemInDropdown("div.selection", "div.menu.transition>div[role='option']", "Elliot Fu");
		sleepInsecon(3);
		//verify text
		Assert.assertEquals(driver.findElement(By.cssSelector("div.selection>div[role='alert']")).getText(), "Elliot Fu");
	
		selectItemInDropdown("div.selection", "div.menu.transition>div[role='option']", "Justen Kitsune");
		sleepInsecon(3);
		//verify text
		Assert.assertEquals(driver.findElement(By.cssSelector("div.selection>div[role='alert']")).getText(), "Justen Kitsune");

	}

	@Test
	public void TC_03_VueJS() {
		driver.get("https://mikerodham.github.io/vue-dropdowns/");
		
		selectItemInDropdown("div.btn-group", "div.btn-group>ul.dropdown-menu>li>a", "Second Option");
		sleepInsecon(3);
		//verify text
		Assert.assertEquals(driver.findElement(By.cssSelector("div.btn-group>li")).getText(), "Second Option");
	
		selectItemInDropdown("div.btn-group", "div.btn-group>ul.dropdown-menu>li>a", "Third Option");
		sleepInsecon(3);
		//verify text
		Assert.assertEquals(driver.findElement(By.cssSelector("div.btn-group>li")).getText(), "Third Option");
	
	}
	
	@Test
	public void TC_04_edittable() {
		driver.get("https://react.semantic-ui.com/maximize/dropdown-example-search-selection/");
		
		enterAndSelectItemInDropdown("input.search", "span.text", "Angola");
		sleepInsecon(3);
		//verify text
		Assert.assertEquals(driver.findElement(By.cssSelector("div.divider.text")).getText(), "Angola");
	
		enterAndSelectItemInDropdown("input.search", "span.text", "Algeria");
		sleepInsecon(3);
		//verify text
		Assert.assertEquals(driver.findElement(By.cssSelector("div.divider.text")).getText(), "Algeria");
	
	}
	
	//trong cùng 1 class các hàm có thể gọi qua nhau
	public void selectItemInDropdown(String parentCss, String allItemCss, String expectedTextItem) {
		//1. Click vào 1 thẻ bất kỳ để làm sao cho nó sổ ra hết các Item trong dropdown
		driver.findElement(By.cssSelector(parentCss)).click();
		sleepInsecon(2);
		//2. Chờ tất cả cá item load ra thành công
		//Locator phải lấy để đại diện cho tất cả các item
		//Lấy đến thẻ chứa text
		driverWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(allItemCss)));
		
		//Đưa hết các item vào trong 1 list
		//List<WebElement> speeDropdownItems= driver.findElements(By.cssSelector(allItemCss));
		
		List<WebElement> speeDropdownItems= driverWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(allItemCss)));

		//3. Tìm item xem đúng cái đang cần hay không (dùng vòng lặp duyệt qua)
		for(WebElement temItem: speeDropdownItems) {
			String itemtext = temItem.getText();
			System.out.println(itemtext);
		
		
			//4. Kiểm tra cái text của item đúng với cái mình mong muốn không
			//Sử dụng hàm trim() để xóa khoảng trắng ở đầu hoặc cuối chuỗi
			if(itemtext.trim().equals(expectedTextItem)) {
				sleepInsecon(2);
				//5. Click vào item đó
				temItem.click();
				//thoát ra khỏi vòng lặp không check các case còn lại nữa
		break;
		}
		}
	
	}
	
	public void enterAndSelectItemInDropdown(String textCss, String allItemCss, String expectedTextItem) {
		//1. Nhập expected item vào - xổ ra tất cả các item matching
		driver.findElement(By.cssSelector(textCss)).sendKeys(expectedTextItem);
		sleepInsecon(2);
		
		//2. Chờ tất cả cá item load ra thành công
		//Locator phải lấy để đại diện cho tất cả các item
		//Lấy đến thẻ chứa text
		driverWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(allItemCss)));
		
		//Đưa hết các item vào trong 1 list
		//List<WebElement> speeDropdownItems= driver.findElements(By.cssSelector(allItemCss));
		
		List<WebElement> speeDropdownItems= driverWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(allItemCss)));

		//3. Tìm item xem đúng cái đang cần hay không (dùng vòng lặp duyệt qua)
		for(WebElement temItem: speeDropdownItems) {
			String itemtext = temItem.getText();
			System.out.println(itemtext);
		
		
			//4. Kiểm tra cái text của item đúng với cái mình mong muốn không
			//Sử dụng hàm trim() để xóa khoảng trắng ở đầu hoặc cuối chuỗi
			if(itemtext.trim().equals(expectedTextItem)) {
				sleepInsecon(2);
				//5. Click vào item đó
				temItem.click();
				//thoát ra khỏi vòng lặp không check các case còn lại nữa
		break;
		}
		}
		
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