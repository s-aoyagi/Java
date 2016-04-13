import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class if_02 {
	public static void main(String[] args) {
		String sex = null;
		String age = null;
		String yes = "y";
		String no = "n";
		
		System.out.println("あなたは男性ですか？");
		System.out.println("Yes : y  No : n");
		InputStreamReader is = new InputStreamReader(System.in);
		//入力
		try{
			BufferedReader br = new BufferedReader(is);
			sex =  new String(br.readLine());
			if( sex.equals(yes) ){
				System.out.println("私も男です。成人ですか？");
				System.out.println("Yes : y  No : n");
				InputStreamReader is1 = new InputStreamReader(System.in);
				BufferedReader br1 = new BufferedReader(is1);
				age =  new String(br1.readLine());
				
				if( age.equals(yes) ){
					System.out.println("あなたは成人の男性ですね");
				}
				else{
					System.out.println("あなたは未成年の男性ですね");
				}
			}
			else if( sex.equals(no) ){
				System.out.println("私とは性別が違いますね。成人ですか？");
				System.out.println("Yes : y  No : n");
				InputStreamReader is1 = new InputStreamReader(System.in);
				BufferedReader br1 = new BufferedReader(is1);
				age =  new String(br1.readLine());
				
				if( age.equals(yes) ){
					System.out.println("あなたは成人の女性ですね");
				}
				else{
					System.out.println("あなたは未成年の女性ですね");
				}
			}
			else{
				System.out.println("特に言うことはありません");
			}
		}
		catch ( IOException e ){}
	}

}
