//クラス
/*
 * クラス：設計図
 * メンバー変数（フィールド）
 * メソッド

 * インスタンス：実体化
 */

/*
 アクセス修飾子
 public
 protected
 private
 */

class User{
	protected String name;
	String email;

	//コンストラクタ（初期化処理）
	User(String name) {
		this.name = name;
	}
	void test(){
		System.out.println("My name is "+ this.name);
	}
}

//クラスの継承
class SuperUser extends User{
	SuperUser(String name){
		super(name);
	}

	//メソッドのオーバーライド
	void test(){
		System.out.println("Your name is "+ this.name);
	}
}

public class class_01 {

	public static void main(String[] args) {
		User tom = new User("tom");
		SuperUser bob = new SuperUser("bob");
		tom.test();
		bob.test();
	}

}