//Calendarクラス
import java.util.Calendar;

public class calendarclass {

	public static void main(String[] args) {
		Calendar cal = Calendar.getInstance();
		System.out.println(cal.get(Calendar.DATE));
		cal.set(2016,2,27,12,3,22);
		System.out.println(cal.get(Calendar.DATE));
		cal.add(Calendar.DATE, 5);
		System.out.println(cal.get(Calendar.DATE));
	}

}
