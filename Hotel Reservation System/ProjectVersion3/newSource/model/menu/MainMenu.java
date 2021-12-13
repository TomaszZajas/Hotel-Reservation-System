package model.menu;

import CsvReaderWriter.CsvWriter;
import DateAndTime.Date;
import CsvReaderWriter.ReadReservationFile;
import Analysis.*;
import model.customer.Customer;
import model.reservations.Reservation;
import model.reservations.ReserveRoom;
import model.room.Room;
import model.room.RoomType;
import rateCalculations.rateCalculations;
import java.util.*;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;
import java.time.LocalDate;


/**
 * Main Menu class that has all the strings, values, and methods relating to the reservation system
 * 
 *
 */
public class MainMenu {
	private static String hotels = "(1) 5-star Hotel\n" +
			"(2) 4-star Hotel\n" + 
			"(3) 3-star Hotel\n";
	private static Reservation reservation;
	private static TreeMap<String,Reservation> reservationList = new TreeMap<String,Reservation>();
	private static ArrayList<String> reservationNumbers = new ArrayList<>();
	private static String regexName = "[a-zA-Z.]*";
	private static String regexNum = "\\d+";
	private static String regexMail = "(.+)@(.+)\\.(.+)$";
	private static String regexDate = "[0-9]{2}[/][0-9]{2}[/][0-9].*";
	private static String filePath = System.getProperty("user.dir") + "\\reservations.csv";

	/**
	 * This method prints out all the options for the main menu, it uses the scanner to take in inputs
	 * The exception prevents the user of the system to choose an option or index outside of the possible options the system has
	 * This method is used to run the entire reservation system and it connects everything together
	 */
	public static void run() {
		String line = "";
		Scanner scanner = new Scanner(System.in);

		printMainMenu();

		try {
			do {
				line = scanner.nextLine();

				if (line.length() == 1) {
					switch (line.charAt(0)) {
					case '1':
						try{
							findAndReserveRoom();
						}catch(Exception e){
							System.err.println(e.getMessage());
						}
						break;
					case '2':
						seeMyReservation();
						break;
					case '3':
						cancelReservation();
						break;
					case '4':
						try {
							hotelAnalysis();
						} catch (Exception e) {
							System.err.println(e.getMessage());
						}
						break;
					case '5':
						System.out.println("Exit");
						break;
					default:
						System.out.println("Unknown action\n");
						break;
					}
				} else {
					System.out.println("Error: Invalid action\n");
				}
			} while (line.charAt(0) != '5' || line.length() != 1);
		} catch (StringIndexOutOfBoundsException ex) {
			System.out.println("Empty input received. Exiting program...");
		}
	}
	
	
	/**
	 * This method essentially allows a user of the system to make a reservation, firstly it uses the readReservation method to
	 * read through the csv file that contains all the reservations and the stores all the reservations in the TreeMap reservationList
	 * Next the scanner is initialised and the scanner takes in all the inputs from the user and stores them in different classes
	 * and objects. The system is made in way that nearly every input is a number which is converted to data except details like
	 * first name etc.
	 * 
	 * This method uses almost all the objects in the system
	 * At the end of the reservation process
	 * 
	 * @author John
	 * @author Tomasz
	 * @throws Exception
	 */
	private static void findAndReserveRoom() throws Exception {
		reservationList = ReadReservationFile.readReservation(filePath);
		
		final Scanner scanner = new Scanner(System.in);
		
		

		System.out.println("Enter First Name example John:");
		String detailsFirstName = scanner.nextLine();
		boolean isMatch = detailsFirstName.matches(regexName);

		if(!isMatch){
			throw new Exception("First name must be valid and must not contain digits: Please press (1)");
		}

		System.out.println("Enter Last Name example Doe:");
		String detailsSecondName = scanner.nextLine();
		isMatch = detailsSecondName.matches(regexName);

		if(!isMatch){
			throw new Exception("Second name must be valid and must not contain digits: Please press (1)");
		}

		System.out.println("Enter Phone Number example 0892369049:");
		String detailsPhoneNumber= scanner.nextLine();
		isMatch = detailsPhoneNumber.matches(regexNum);

		if(!isMatch) {
			throw new Exception("Phone number must be valid: Please press (1)");
		}

		System.out.println("Enter Email example person@gmail.com:");
		String detailsEmail = scanner.nextLine();
		isMatch = detailsEmail.matches(regexMail);

		if(!isMatch) {
			throw new Exception("Email must be valid - must contain '@' and must not contain spaces: Please press (1)");
		}

		//create a customer object with the previous inputs
		Customer customer = new Customer(detailsFirstName,detailsSecondName,detailsPhoneNumber,detailsEmail);

		System.out.println("Enter Check-In Date example 26/04/2021");
		String checkIn = scanner.nextLine();//check-in String date
		isMatch = checkIn.matches(regexDate);

		if (!isMatch) {
			throw new Exception("Check-In Date must be valid: Please press (1)");
		}

		System.out.println("Enter Check-Out Date example 02/05/2021");
		String checkOut = scanner.nextLine();//check-out String date
		isMatch = checkOut.matches(regexDate);

		if (!isMatch) {
			throw new Exception("Check-Out Date must be valid: Please press (1)");
		}

		LocalDate checkInDate = Date.stringToDate(checkIn);
		LocalDate checkOutDate = Date.stringToDate(checkOut);
		if (checkOutDate.isBefore(checkInDate)) {
			throw new Exception("Check-Out Date must be after Check-In Date: Please press(1)");
		}

		System.out.println("Would you like make a standard reservation or an advanced reservation" + "\r\n" 
				+ "A 5% discount applies to all advanced reservations but they are non-refundable" + "\r\n" +
				"Please choose:\n" + "(1) Standard\n" + "(2) Advanced Reservation");
		
		String numCheck = scanner.nextLine();
		
		if (!numCheck.equals("1") && !numCheck.equals("2")) {
			throw new Exception("Invalid input: Please press (1)");
		}
		
		if(numCheck.equals("1")) {
			numCheck = "S";
		}else {
			numCheck = "AP";
		}
		
		String reservationType = numCheck;//reservation type
		
		/*System.out.println("Do you wish to book multiple rooms?\n" + "(1) Yes\n" + "(2) No");
		numCheck = scanner.nextLine();
		int numOfRooms;
		
		if (!numCheck.equals("1") && !numCheck.equals("2")) {
			throw new Exception("Invalid input: Please press (1)");
		}
		
		if(numCheck.equals("1")) {
			System.out.println("How many rooms?");
			numCheck = scanner.nextLine();
		}else {
			numCheck = "1";
		}
		
		numOfRooms = Integer.valueOf(numCheck); */ //number of rooms

		System.out.println("Choose Occupancy (Max Occupancy is 3)");
		String occupancy = scanner.nextLine(); //occupancy of rooms
		
		if(occupancy.equals("3"))
			System.out.println("Only 5-star hotel accommodates 3 occupants\r\n" + "(1) 5-star hotel");
		else {
			System.out.println(hotels);
		}
		
		if (!occupancy.equals("1") && !occupancy.equals("2") && !occupancy.equals("3"))
		{
			throw new Exception("Invalid occupancy selected: Please press (1)");
		}
		
		String num = scanner.nextLine();
		String hotelType; 
		
		hotelType = getHotelType(num); //method that returns the hotel type when given a corresponding number
		ReserveRoom.roomsAndHotels(hotelType, occupancy);//method that prints all the available hotels and rooms
		
		String num2 = scanner.nextLine();
		
		String roomType = ReserveRoom.chooseRoom(num2, hotelType, occupancy);
		
		if(Reservation.checkAvailabilty(reservationList, checkIn, checkOut, roomType)) {
			System.out.println("The room is available");
		}else {
			System.out.println("This room is unavailable for these dates please choose a different room");
			ReserveRoom.roomsAndHotels(hotelType, occupancy);
			num2  = scanner.nextLine();
			roomType = ReserveRoom.chooseRoom(num2, hotelType, occupancy);
		}
		
		
		
		RoomType r = new RoomType(hotelType, roomType); //roomType object made with previous inputs
		Room room = new Room(r, occupancy); //room object  
		
		rateCalculations rates = new rateCalculations(checkIn, checkOut, roomType);//rates object that contains the price for the room/s
		
		//reservation object that takes all the previously gathered details
		reservation = new Reservation(customer, reservationType, checkIn, checkOut, 1, room, rates);
		
		System.out.println(reservation.toString());
		
	    System.out.println("Please confirm this reservation: " + "\r\n"
		                    + "(1) Yes\r\n" + "(2) No");
	    String check = scanner.nextLine();
	    
	    if(!check.equals("1") && !check.equals("2")){
			throw new Exception("Invalid input: Please press (1)");
	    }
	    
	    if(check.equals("1")) {
	    	
		    reservationNumbers.add(reservation.getReservationNumber());
			reservationList.put(reservation.getReservationNumber(),reservation);
		    CsvWriter.writeCsv(filePath, reservationList);
	    	
	    }else {
	    	System.out.println("Reservation Cancelled");
	    }
	    //prints main menu
		printMainMenu();
	}

	
	/**
	 * This method allows the user to view their reservation details if they know their reservation.
	 * 
	 * @author Tomasz
	 */
	private static void seeMyReservation() {
		//loads the csv file containing the reservations into a TreeMap
		reservationList = ReadReservationFile.readReservation(filePath);
		final Scanner scanner = new Scanner(System.in);
		
		System.out.println("Please enter reservation number example 1234");
		String res = scanner.next();
		String check = "error: reservation not found, try different reservation number";
		
		//loops through TreeMap and returns reservation with corresponding reservation number
		for (Map.Entry<String, Reservation>
        		entry : reservationList.entrySet())
			if(res.equals(entry.getKey())) {
				System.out.println(reservationList.get(res).toString());
				check = ""; 
				break;
			};
			System.out.println(check);
	}
	
	/**
	 * Hotel cancellation method:
	 * This removes a reservation from the list of csv reservations
	 *
	 * @author John
	 */
	public static void cancelReservation(){
		final Scanner scanner = new Scanner(System.in);
		
		reservationList = ReadReservationFile.readReservation(filePath);		
		for (Map.Entry<String, Reservation> entry : reservationList.entrySet()) {
			String key = entry.getKey();
			Reservation value = entry.getValue();
			System.out.println("Key: " + key + "\n" + value.getCustomer().toString());
			System.out.println("");
		}
		String keyForRemoval;

		while(true) {

		 	keyForRemoval = scanner.nextLine();
			reservationList.remove(keyForRemoval);
			CsvWriter.writeCsv(filePath, reservationList);

			System.out.println("\nYour Reservation Has Been Cancelled  " +keyForRemoval + " input (-1) to exit, enter to continue");
			keyForRemoval= scanner.nextLine();
			if(keyForRemoval.equals("-1")){
				printMainMenu();
				break;
			}
		}System.out.println("Finished Cancelling Reservations");
	}
	
	/**
	 * This method uses the HotelAnalysis class and its' methods to analyse the hotels.
	 * 
	 * @author Tomasz
	 * @throws Exception 
	 */
	public static void hotelAnalysis() throws Exception {
		reservationList = ReadReservationFile.readReservation(filePath);
		
		final Scanner scanner = new Scanner(System.in);
		
		
		//Get password and username of user 
		System.out.println("Please enter hotel staff username");
		String username = scanner.nextLine();
		
		System.out.println("Please enter hotel staff password");
		String password = scanner.nextLine();
		
		if(username.equals("hotelstaff123") && password.equals("password123")) {
			System.out.println("Enter dates to view info\n" + "Enter start date: ");
		}else {
			throw new Exception("Wrong password or username: Please press (4)");
		}
		
		
		
		//Gets the details of when to do the analysis for
		Date startDate = new Date(scanner.nextLine());
		
		System.out.println("Enter end date: ");
		
		Date endDate = new Date(scanner.nextLine());
		
		//prints analysis options
		System.out.println("Would you like to see: \n" + "(1) Total Rates for all hotels\n" +
														 "(2) Total Rates for specific hotel\n" + 
														 "(3) Total Occupancy for all hotels\n" +
														 "(4) Total Occupancy for specific hotel\n");;
		
		//uses methods from the hotelAnalysis class 
		String check = scanner.nextLine();
		int totalRate = 0;
		int totalOccupancy = 0;
		String hotelType = null;
		String roomType = null;
		if(check.equals("1")) {
			
			totalRate = HotelAnalysis.getTotalRates(reservationList, startDate, endDate);
			System.out.println("The total income for all hotels from " + startDate + " to " + endDate + " is " + totalRate);
			
		}else if(check.equals("2")) {
			System.out.println("Which hotel would you like to see the rates for: \n" + hotels);
			check = scanner.nextLine();
			hotelType = getHotelType(check);
				
			totalRate = HotelAnalysis.getTotalRates(reservationList, startDate, endDate, hotelType);
			System.out.println("The total income for the " + hotelType + " from " + startDate + " to " + endDate + " is " + totalRate);
			
		}else if(check.equals("3")) {
			
			totalOccupancy = HotelAnalysis.getTotalOccupancy(reservationList, startDate, endDate);
			System.out.println("The total occupancy for all hotels from " + startDate + " to " + endDate + " is " + totalOccupancy);
			
		}else if(check.equals("4")) {
			System.out.println("Which hotel would you like to see the occupancy figures for: \n" +  hotels);
			
			check = scanner.nextLine();
			hotelType = getHotelType(check);
			
			totalOccupancy = HotelAnalysis.getTotalOccupany(reservationList, startDate, endDate, hotelType);
			System.out.println("The total occupancy for the " + hotelType + " from " + startDate + " to " + endDate + " is " + totalOccupancy);
			
			
		}
		printMainMenu();
	
	
	}
	
	
	/**
	 * Method that prints out the main menu
	 */
	public static void printMainMenu()
	{
		System.out.println("\nWelcome to the Hotel Reservation System\n" +
				"--------------------------------------------\n" +
				"1. Find and reserve a room\n" +
				"2. View reservations\n" +
				"3. Cancel reservation\n" +
				"4. Hotel Analysis\n" +
				"5. Exit\n" +
				"--------------------------------------------\n" +
				"Please select a number for the menu option:");
	}
	
	
	/**
	 * Method that checks what hotel the user chooses
	 * @param check Number between 1-3
	 * @return Hotel type
	 */
	public static String getHotelType(String check) {
		String hotelType = null;
		if(check.equals("1")) {
			hotelType = "5-star";
		}else if(check.equals("2")) {
			hotelType = "4-star";
		}else if(check.equals("3")) {
			hotelType = "3-star";
		}
		return hotelType;
	}

}


