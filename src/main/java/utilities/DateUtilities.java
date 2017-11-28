package utilities;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtilities {

	public static String getDateNumber() {
		DateFormat dateTimeFormat = new SimpleDateFormat("dd/MM/yy HH:mm");
		Date date = new Date();
		return dateTimeFormat.format(date);
	}

	public static String getDateNumberNoTime() {
		DateFormat dateTimeFormat = new SimpleDateFormat("dd/MM/yy");
		Date date = new Date();
		System.out.println(dateTimeFormat.format(date) + " dataeeee");
		return dateTimeFormat.format(date);
	}

}