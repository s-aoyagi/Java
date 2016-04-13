
public class loop_if {

	public static void main(String[] args) {
		int i = 0;
		System.out.println("10までの数字を奇数の場合と偶数の場合を振り分けます");
		do{
			i += 1;
			if(i % 2 == 0){
				System.out.println(i+" = 偶数");
			}
			else{
				System.out.println(i+" = 奇数");
			}
		}while(i <= 9);
	}
}
