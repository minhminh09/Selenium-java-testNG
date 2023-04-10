package TEST;

import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.collections.Lists;

public class Topic_02_Data_type {

	public static void main(String[] args) {
		//Data_type: Kiểu dữ liệu
		//Kiểu dữ liệu nguyên thủy (Primitive) có 8 loại bao gồm
		//Kiểu số nguyên: byte, short, int, long 	(số nguyên không có phần thập phân)
		byte bNumber=127;
		short sNumber= 32000;
		int iNumber=49903904;
		long lNumber=3424;
		
		//Số thực: Float, Double (có phần thập phân)
		float diemsv=9.5f;
		double employeeSaraly=35.5d;
		//Logic: boolean
		boolean status= true;//Nam
		status= false; //Nữ
		//Ký tự: char
		char a='A';
		
		//II- Kiểu dữ liệu tham chiếu (Reference)
		//Class
		FirefoxDriver driver = new FirefoxDriver ();
		//Interface
		WebElement firtnameTextbox;
		//String
		String firtname="Automation testing";
		//Object: cũng là kiểu class và nó là class cha của cá class
		Object peple;
		//Array: chưa nhiều giá trị của kiểu nguyên thủy hoặc kiểu tham chiếu (kiểu chuỗi)
		String[] studentname= {"Hoàng Văn Thành","Nguyễn Hữu Minh","Lương Văn Tú"};
		//Collection: List/Set/Queue
		List<WebElement> Checkbox=(List<WebElement>) driver.findElement(By.cssSelector(""));
		//Map
		HashMap<String, Integer> studen=new HashMap<String, Integer>();
	
			
		
	}

}
