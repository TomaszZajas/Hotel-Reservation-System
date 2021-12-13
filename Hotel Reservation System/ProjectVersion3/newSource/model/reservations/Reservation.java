package model.reservations;

import rateCalculations.rateCalculations;


import model.room.Room;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Map;
import java.util.TreeMap;

import CsvReaderWriter.ReadReservationFile;

import java.time.LocalDate;
import DateAndTime.Date;
import model.customer.*;

/**
 * Class that creates the reservation object
 * @author Tomek
 *
 */
public class Reservation {
	private int reservationNumber;
	private Customer customer;
	private String reservationType;
	private String checkInDate;
	private String checkOutDate;
	private int numberOfRooms;
	private Room roomType;
	private double totalCost;


	/**
	 * Constructor that creates a reservation object
	 * 
	 * @author Tomasz
	 * @param customer Object
	 * @param reservationType String
	 * @param checkInDate String
	 * @param checkOutDate String
	 * @param numberOfRooms Int
	 * @param roomType Object
	 * @param cost RateCalculations Object
	 */
	public Reservation(Customer customer, String reservationType, String checkInDate, String checkOutDate, int numberOfRooms, Room roomType, rateCalculations cost) {
		this.customer = customer;
		this.reservationType = reservationType;
		this.checkInDate = checkInDate;
		this.checkOutDate = checkOutDate;
		this.roomType = roomType;
		this.numberOfRooms = numberOfRooms;
		this.reservationNumber = 1000 + (int)(Math.random() * ((10000 - 1000) + 1));//Gives the customer a randomised reservation number
		int price = cost.getRate();//uses the getRate() method from the rateCalculations class to get the total cost of the hotel room

		//checks if the reservation type is S or AP and does the corresponding calculations
		if(reservationType.equals("S")){
			this.totalCost = price*numberOfRooms;
		}else if(reservationType.equals("AP")) {
			this.totalCost = (price - price*(0.05)) * numberOfRooms;
		}

	}

	/**
	 * Get method
	 * @return Customer object
	 */
	public Customer getCustomer() {
		return customer;
	}

	/**
	 * Get method
	 * @return Type of reservation(S or AP)
	 */
	public String getReservationType() {
		return reservationType;
	}

	/**
	 * Get method
	 * @return The reservation number as a String
	 */
	public String getReservationNumber() {
		return Integer.toString(reservationNumber);
	}

	/**
	 * Get method
	 * @return Customers check-out-date as a String
	 */
	public String getCheckOutDate() {
		return checkOutDate;
	}

	/**
	 * Get method
	 * @return Customers check-in-date as a String
	 */
	public String getCheckInDate() {
		return checkInDate;
	}

	public LocalDate in() {
		return Date.stringToDate(this.checkInDate);
	}

	public LocalDate out() {
		return Date.stringToDate(this.checkOutDate);
	}

	/**
	 * Get method
	 * @return roomType Object
	 */
	public Room getRoom() {
		return roomType;
	}

	/**
	 * Get method
	 * @return the total price of stay for the hotel room
	 */
	public String getPrice() {
		return Double.toString(totalCost);
	}

	/**
	 * Set method that sets the reservation number of the reservation
	 * @param reservationNumber
	 */
	public void setReservationNumber(int reservationNumber) {
		this.reservationNumber = reservationNumber;
	}

	/**
	 * Get method
	 * @return the length of time between the check-in and check-out date of the customer
	 */
	public int getLengthOfStay() {
		LocalDate in = Date.stringToDate(this.checkInDate);
		LocalDate out = Date.stringToDate(this.checkOutDate);

		long diff = ChronoUnit.DAYS.between(in, out);
		int daysBetween = (int)diff;

		return daysBetween;
	}
	
	/**
	 * Method that takes two string dates and returns the amount of days between them
	 *
	 * @param chIn startDate
	 * @param chOut endDate
	 * @return
	 */
	public static int lengthOfStay(String chIn, String chOut) {
		LocalDate in = Date.stringToDate(chIn);
		LocalDate out = Date.stringToDate(chOut);

		long diff = ChronoUnit.DAYS.between(in, out);
		int daysBetween = (int)diff;

		return daysBetween;
	}

	/**
	 * Method that returns all the details of the reservation in a String format
	 */
	@Override
	public String toString() {
		return customer.toString() + "\r\n" +
				"Reservation Type: " + reservationType + "\r\n" +
				"Check-in-date: " + checkInDate.toString() + "\r\n" +
				"Check-out-date: " + checkOutDate.toString() + "\r\n" +
				roomType.toString() + "\r\n" +
				"Number Of Rooms: " + numberOfRooms + "\r\n" +
				"Price: " + getPrice() + "\r\n" +
				"Reservation Number: " + getReservationNumber() + "\r\n";

	}
	
	
	
	/**
	 * This method checks the availabilty of a room when a person is making a booking
	 * 
	 * @param reservations
	 * @param checkIn
	 * @param checkOut
	 * @param roomType
	 * @return
	 */
	public static boolean checkAvailabilty(TreeMap<String, Reservation> reservations, String checkIn, String checkOut, String roomType) {
		LocalDate checkInDate = Date.stringToDate(checkIn);
		LocalDate checkOutDate = Date.stringToDate(checkOut);
		LocalDate compareDate;
		int count = 0;

		for (Map.Entry<String, Reservation> //Loops through all the reservations
		entry : reservations.entrySet()) {
			
			if(entry.getValue().getRoom().getRoomType().getType().equals(roomType)) {//check if the roomType of that reservation is equal to the room type of the new reservation
				for(int i=0; i<entry.getValue().getLengthOfStay(); i++) {//loops through every single date of a reservation
					compareDate = Date.stringToDate(entry.getValue().checkInDate);//gets the checkin date of that reservation and increments by days
					compareDate = compareDate.plusDays(i);
					checkInDate = Date.stringToDate(checkIn);
					for(int j=0; j<lengthOfStay(checkIn, checkOut); j++) {//loops through every date of the new reservation
						
						if(checkInDate.compareTo(compareDate) == 0) { //if two dates are equal the counter increments
							count++;
							break;
						}
						checkInDate = checkInDate.plusDays(1);
						
					}
					if(count>1) {
						break;
					}
				}
			}
		}
		
	    //if count is less than the max occupancy for that particular rooom
		if(roomType.equals("Deluxe Double")) {
			if(count<35) {
				return true;
			}
		}else if(roomType.equals("Deluxe Twin")) {
			if(count<25) {
				return true;
			}
		}else if(roomType.equals("Deluxe Single")) {
			if(count<10) {
				return true;
			}
		}else if(roomType.equals("Deluxe Family")) {
			if(count<10) {
				return true;
			}
		}else if(roomType.equals("Executive Double")) {
			if(count<40) {
				return true;
			}
		}else if(roomType.equals("Executive Twin")) {
			if(count<32) {
				return true;
			}
		}else if(roomType.equals("Executive Single")) {
			if(count<12) {
				return true;
			}
		}else if(roomType.equals("Classic Double")) {
			if(count<45) {
				return true;
			}
		}else if(roomType.equals("Classic Twin")) {
			if(count<45) {
				return true;
			}
		}else{
			if(count<10) {
				return true;
			}
		}
			
		return false;
	}
	
}
