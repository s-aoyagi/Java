//メソッド
public class method {
	public static void main (String[] args){
		test("Javaboy");
		test();
	}

	public static void test(String name){
		System.out.println("test" + name);
	}
	//メソッドのオーバーロード
	public static void test(){
		System.out.println("てすと");
	}
}
