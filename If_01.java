package training_if;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class If_01 {

	public static void main(String[] args) {
		System.out.println("数字の1か2を入力してください。");
		int i;
		InputStreamReader is = new InputStreamReader(System.in);
		//入力
		try{
			BufferedReader br = new BufferedReader(is);
			i = Integer.parseInt( br.readLine());
			
			if(i == 1){
				System.out.println("私は2よりも1の方が好きです。");
			}
			else if( i == 2 ){
				System.out.println("あなたは1よりも2の方が好きなのですか？");
			}
			else{
				System.out.println("特に言うことはありません");
			}
		}
		catch ( IOException e ){}
	}

}
