package TEST;

import java.util.Random;

public class Topic_05_Ramdom {

	public static void main(String[] args) {
		// cách tạo email radom khi phải sử dụng nhiều email để thao tác
		Random rand= new Random();
		System.out.println("haptt"+ rand.nextInt(9999)+ "@gmail.com");
	}

}
