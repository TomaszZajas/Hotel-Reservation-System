package DateAndTime;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Date
{  
	private String date;
	private int year;
	private int month;
	private int day;
	
	/**
	 * Date constructor
	 * @param s String date
	 */
	public Date(String s){
		String[] input = s.split("/");

		this.date = s;
		this.year = Integer.parseInt(input[0]);
		this.month = Integer.parseInt(input[1]);
		this.day = Integer.parseInt(input[2]);

	}

	public int getYear(){
		return year;
	}

	public int getMonth(){
		return month;
	}

	public int getDay(){
		return day;
	}

	public String getDate() {
		return date;
	}
	
	/**
	 * This method takes a date as a string converts it to a localDate time object,
	 * uses the formatter
	 * 
	 * @author Tomek
	 * @param date This is the date that gets converted
	 * @return
	 */
	public static LocalDate stringToDate(String date) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");



		//convert String to LocalDate
		LocalDate localDate = LocalDate.parse(date, formatter);
		return localDate;
	}
	
	
	/**
	 * Prints the string date
	 */
	@Override
	public String toString(){
		return year + "/" + month + "/" + day;
	}
	
}
