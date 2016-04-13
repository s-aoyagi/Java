
public class class_001 {

	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		int i = 0;
		System.out.println("10までの数字を奇数の場合と偶数の場合を振り分けます");
		while(i <= 10){
			hoge(i);
			i++;
		}
	}


	private static void hoge(int j){
		String str = j % 2 == 0 ? "偶数です。" : "奇数です。";
		System.out.println(j + "=" + str);
	}
}