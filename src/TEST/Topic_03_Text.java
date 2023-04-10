package TEST;

public class Topic_03_Text {
	public static void main(String[] args) {
		System.out.println("không có khoảng trắng ở đầu hoặc cuối");
		System.out.println(" có khoảng trắng ở đầu hoặc cuối");
		System.out.println("có khoảng trắng ở đầu hoặc cuối   ");
		System.out.println("   có khoảng trắng ở đầu hoặc cuối  ");
		// \n là xuống dòng
		System.out.println("\n có xuống dòng ở đầu");
		System.out.println("có xuống dòng ở cuối\n");
		System.out.println("\tcó tab ở đầu");
		System.out.println("có tab ở cuối\t");

		String firstName="Michael";
		String lastName="Jack";
		//Nối chuối
		System.out.println(firstName+" "+ lastName);
		
		//Nối chuỗi
		System.out.println(firstName.concat(" "+lastName));
		//Nối chuỗi
		System.out.println(firstName.concat(" ") + (lastName.concat(" ")));
}
	
}
