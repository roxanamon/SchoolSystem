package otherclasses;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class PersonalNumber {
	private String number;

	private int toInt(String str, int i) {
		if (Character.isDigit(str.charAt(i))) {
			return Integer.parseInt(str.substring(i, i+1));
		}
		else {
			throw new IllegalArgumentException("Not a valid Swedish personal number - should have this format: xxxxxx-xxxx where 'x' represents digits!");
		}
	}

	public PersonalNumber(String s) {

		int k = s.indexOf('-');
		if (k != 6 || s.length() != 11) 
			throw new IllegalArgumentException("Not a valid Swedish personal number - should have this format: xxxxxx-xxxx");
		String t = s.substring(0,6) + s.substring(7);

		// calculate checksum
		int sum = 0; 
		for (int i=0; i<9; i++) {
			int tal = toInt(t, i);
			int j = tal * (2-i%2);
			sum += j/10 + j%10;
		}
		sum %= 10;
		if ((toInt(t,9) + sum) % 10 != 0) {
			throw new IllegalArgumentException("Not a valid Swedish personal number - invalid checksum!");
		}

		number = s;
	} 

	public String toString() {
		return number;
	}

	public boolean isFemale() {
		return toInt(number, 9) % 2 == 0;
	}

	public boolean isMale() {
		return !isFemale();
	} 

	public int getAge() {
		try {
			DateFormat dateFormat = new SimpleDateFormat("yyMMdd");
			Calendar birthdate = getCalendar(dateFormat.parse(number.substring(0, 6)));
			Calendar now = getCalendar(new Date());
			int diff = now.get(Calendar.YEAR) - birthdate.get(Calendar.YEAR);
			if (birthdate.get(Calendar.MONTH) > now.get(Calendar.MONTH) || 
					(birthdate.get(Calendar.MONTH) == now.get(Calendar.MONTH) && birthdate.get(Calendar.DATE) > now.get(Calendar.DATE))) {
				diff--;
			}
			return diff;
		}
		catch(ParseException pe) {
			String errorMessage = "Personal number contains unparsable birth date: " + number.substring(0, 6);
			System.out.println(errorMessage);
			System.out.println(pe.toString());
			throw new RuntimeException("Unexpected error: " + errorMessage + System.lineSeparator() + pe.toString());
		}
	}

	public static Calendar getCalendar(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}
}
