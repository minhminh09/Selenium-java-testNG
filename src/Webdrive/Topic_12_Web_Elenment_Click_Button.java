package Webdrive;

import java.awt.Desktop.Action;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_12_Web_Elenment_Click_Button {
	WebDriver driver;
	Actions action;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");

	@BeforeClass
	public void beforeClass() {
		System.out.println(osName);
		if (osName.contains("Windows")) {
			System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		} else {
			System.setProperty("webdriver.chrome.driver", projectPath + "/browserDrivers/chromedriver");
		}

		driver = new ChromeDriver();
		action = new Actions(driver);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
	}

	@Test
	public void TC_01_Click_and_Hold() {
		
		driver.get("https://automationfc.github.io/jquery-selectable/");
		List<WebElement> listNumber= driver.findElements(By.cssSelector("ol#selectable>li"));
		//List chứa 12 item
		
		//1. Click vào 1 số sourc  - 2. Vẫn giữ chuột và chưa thả ra
		action.clickAndHold(listNumber.get(0))
		//3. Di chuột tới số (target)
				.moveToElement(listNumber.get(7))
		//4. Thả chuột ra
				.release()
		//Excute
				.perform();
		sleepInsecon(5);
		
		List<WebElement> listSelectNumber= driver.findElements(By.cssSelector("ol#selectable>li.ui-selected"));
		Assert.assertEquals(listSelectNumber.size(), 8);
		
	}


	@Test
	public void TC_02_Click_Number_Ramdom() {
		
		driver.get("https://automationfc.github.io/jquery-selectable/");
		//Chạy được cho cả MAC VÀ WINDOWN
		Keys key=null;
		if(osName.contains("WINDOWN")) {
			key=Keys.CONTROL;
		}
		else {
			key=Keys.COMMAND;
		}
		
		//List chứa 12 item
		List<WebElement> listNumber= driver.findElements(By.cssSelector("ol#selectable>li"));
		
		//Nhấn Ctrl
		action.keyDown(key).perform();
		
		//Click chọn item random
		action.click(listNumber.get(1))
			.click(listNumber.get(5))
			.click(listNumber.get(6))
			.click(listNumber.get(8)).perform();
		//Thả Ctrl
		action.keyUp(key).perform();
		
		List<WebElement> listSelectNumber= driver.findElements(By.cssSelector("ol#selectable>li.ui-selected"));
		Assert.assertEquals(listSelectNumber.size(), 4);
	}

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
		driver.quit();
	}
}