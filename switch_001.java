import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class switch_001 {
	public static void main(String[] args) throws IOException {
		// TODO 自動生成されたメソッド・スタブ
		System.out.println("数字の1～10のどれかを入力してください。");
		int i= input();
		sw(i);
	}
	
	private static Integer input() throws IOException {
		InputStreamReader is = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(is);
		int i = Integer.parseInt( br.readLine());
		return i;
	}
	
	private static void sw(int j){
		switch(j){
		case 1:
			System.out.println("奇数です。");
			break;
		case 2:
			System.out.println("偶数です。");
			break;
		case 3:
			System.out.println("奇数です。");
			break;
		case 4:
			System.out.println("偶数です。");
			break;
		case 5:
			System.out.println("奇数です。");
			break;
		case 6:
			System.out.println("偶数です。");
			break;
		case 7:
			System.out.println("奇数です。");
			break;
		case 8:
			System.out.println("偶数です。");
			break;
		case 9:
			System.out.println("奇数です。");
			break;
		case 10:
			System.out.println("偶数です。");
			break;
		}
	}
}
