//ifレッスン

//比較演算子 > < >= <= == !=
//論理演算子 && || !


public class if_001 {
	public static void main(String[] args) {
		int age = 12;
		if(age > 20) {
			System.out.println("大人です。");
		} else if(age > 10) {
			System.out.println("子供です。");
		} else {
			System.out.println("幼児です。");
		}
	}
}
