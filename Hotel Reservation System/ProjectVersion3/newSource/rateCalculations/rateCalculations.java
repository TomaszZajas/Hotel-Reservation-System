package rateCalculations;



import java.util.*;

import DateAndTime.*;
import DateAndTime.Date;

import java.time.format.DateTimeFormatter;
import java.time.LocalDate;
import java.time.DayOfWeek;
import java.time.temporal.ChronoUnit;
/**
 * Class that calculates the length
 * @author Tomek
 *
 */
public class rateCalculations {

	private int rate;
	
	/**
	 * Constructor that calculates the rates
	 * @param checkIn
	 * @param checkOut
	 * @param roomType
	 */
	public rateCalculations(String checkIn, String checkOut, String roomType) {
		rate = calcRate(checkIn, checkOut, roomType);
		
	}
	
	public int getRate() {
		return rate;
	}
	

	/**
	 * This method uses localDate and chronounit to find the length of the stay of the customer and the corresponding day
	 * of the week for each day to calculate the rate for each day and then added together to return the price of the stay
	 *
	 * @param checkIn Check in date of the customer
	 * @param checkOut Check out date of the customer
	 * @param roomType The room they want to reserve
	 * @return The price of the stay
	 */
	public static int calcRate(String checkIn, String checkOut, String roomType) {
		int rate=0;
		int monRate=0;
		int tueRate=0;
		int wedRate=0;
		int thuRate=0;
		int friRate=0;
		int satRate=0; 
		int sunRate=0;

		TreeMap<DayOfWeek,Integer> rates = new TreeMap<DayOfWeek,Integer>(); //TreeMap that stores the rates
		
		//converting strings to dates
		LocalDate checkInDate = Date.stringToDate(checkIn);
		LocalDate checkOutDate = Date.stringToDate(checkOut);
		
		//day of the week for a specific date
		DayOfWeek currentDay;

		long diff = ChronoUnit.DAYS.between(checkInDate, checkOutDate);
		int daysBetween = (int)diff;


		//rates for specified room type
		if(roomType.equals("Deluxe Double")) {
			monRate = 75;
			tueRate = 75;
			wedRate = 75;
			thuRate = 80;
			friRate = 90;
			satRate = 90;
			sunRate = 75;
		}else if(roomType.equals("Deluxe Twin")) {
			monRate = 75;
			tueRate = 75;
			wedRate = 75;
			thuRate = 80;
			friRate = 90;
			satRate = 90;
			sunRate = 75;
		}else if(roomType.equals("Deluxe Single")){
			monRate = 70;
			tueRate = 70;
			wedRate = 70;
			thuRate = 75; 
			friRate = 80;
			satRate = 80;
			sunRate = 65;
		}else if(roomType.equals("Deluxe Family")) {
			monRate = 80;
			tueRate = 80;
			wedRate = 80;
			thuRate = 80;
			friRate = 100;
			satRate = 100;
			sunRate = 100;
		}else if(roomType.equals("Executive Double")) {
			monRate = 70;
			tueRate = 70;
			wedRate = 70;
			thuRate = 70;
			friRate = 80;
			satRate = 85;
			sunRate = 85;
		}else if(roomType.equals("Executive Twin")) {
			monRate = 70;
			tueRate = 70;
			wedRate = 70;
			thuRate = 70;
			friRate = 80;
			satRate = 85;
			sunRate = 85;
		}else if(roomType.equals("Executive Single")) {
			monRate = 65;
			tueRate = 65;
			wedRate = 65;
			thuRate = 65;
			friRate = 70;
			satRate = 75;
			sunRate = 80;
		}else if(roomType.equals("Classic Double")) {
			monRate = 65;
			tueRate = 65;
			wedRate = 70;
			thuRate = 70;	
			friRate = 70;	
			satRate = 80;
			sunRate = 65;
		}else if(roomType.equals("Classic Twin")) {
			monRate = 65;
			tueRate = 65;
			wedRate = 70;
			thuRate = 70;
			friRate = 80;
			satRate = 85;
			sunRate = 65;
		}else if(roomType.equals("Classic Single")) {
			monRate = 50;
			tueRate = 50;
			wedRate = 50;
			thuRate = 60;
			friRate = 75;
			satRate = 75;
			sunRate = 50;
		}
		
		//Adding the rates to their corresponding days into a TreeMap
		rates.put(DayOfWeek.MONDAY, monRate);
		rates.put(DayOfWeek.TUESDAY, tueRate);
		rates.put(DayOfWeek.WEDNESDAY, wedRate);
		rates.put(DayOfWeek.THURSDAY, thuRate);
		rates.put(DayOfWeek.FRIDAY, friRate);
		rates.put(DayOfWeek.SATURDAY, satRate);
		rates.put(DayOfWeek.SUNDAY, sunRate);

		//Loop for the days of stay
		for(int i=0; i<daysBetween; i++) {
			currentDay = checkInDate.getDayOfWeek();//The currentDay dayOfWeek variable is equal to the day of the week 
													//of the check in date 

			checkInDate = checkInDate.plusDays(1);//the check in date gets incremented by 1

			rate = rate + rates.get(currentDay);//gets the rate for that particular day of the week from the tree map and adds it on

		}
		return rate;

	
	}
}


