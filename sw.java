//switch

public class sw {
	public static void main(String[] args) {
		int n = 2;

		switch(n) {
		case 1:
			System.out.println("One");
			break;
		case 2:
			System.out.println("Two");
			break;
		case 3:
		case 4:
			System.out.println("Three or Four");
			break;
		default:
			System.out.println("None");
			break;
		}
	}
}
