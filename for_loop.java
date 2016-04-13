//forループ
//ループ抜けまとめて記述

public class for_loop {
	public static void main(String[] args) {
		for(int n = 0 ; n < 10 ; n++) {
			/*
			if(n==5){
				break;
			}
			*/
			if(n % 2 == 0){
				continue;
			}
			System.out.println(n);
		}
	}
}
