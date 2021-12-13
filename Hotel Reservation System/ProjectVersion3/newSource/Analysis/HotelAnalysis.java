package Analysis;
import java.util.*;
import DateAndTime.Date;
import model.reservations.Reservation;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;


/**
 * This class contains method that analyse the three hotels and return data about the three
 * such as occupancy and total rates
 * 
 * @author Tomasz
 *
 */
public class HotelAnalysis {
	
	/**
	 * This method takes in a tree map that contains all the current and previously made reservations each with a unique
	 * reservation number. The tree map works by taking a string reservation number and returning the corresponding reservation
	 * 
	 * This method firstly converts the two String dates into localDates using the stringToDate method, it then uses the chronounit
	 * import to find the amount of days between the two localDates. Next the method starts iterating through the reservations
	 * in the tree map. The code used to iterate through the tree map was used from geeksforgeeks:
	 * https://www.geeksforgeeks.org/how-to-iterate-over-a-treemap-in-java/
	 * Every reservation that appears between the start and end date is then stored in an arrayList
	 * 
	 * @author Tomasz
	 * @param reservationList Tree map that contains all reservations
	 * @param start The start date from the data will be gathered
	 * @param end The date when the data will stop being gathered
	 * @return arrayList filled with reservations between the two given dates
	 */
	public static ArrayList<Reservation> AnalyseHotels(TreeMap<String, Reservation> reservationList, Date start, Date end) {
		ArrayList<Reservation> res = new ArrayList<>();
		LocalDate startDate = Date.stringToDate(start.getDate());
		LocalDate endDate = Date.stringToDate(end.getDate());
		long diff = ChronoUnit.DAYS.between(startDate, endDate);
		int daysBetween = (int)diff;
		

		for (Map.Entry<String, Reservation>
        		entry : reservationList.entrySet()) {
			LocalDate checkIn = Date.stringToDate(entry.getValue().getCheckInDate());//check-in date of reservation
			LocalDate checkOut = Date.stringToDate(entry.getValue().getCheckOutDate());//check-out date of reservation
			if(checkIn.isAfter(startDate) && checkOut.isBefore(endDate)) {
				res.add(entry.getValue());
			}
		}
		return res;
	}
	
	
	/**
	 * This method gets the total rates for all hotels over a certain period of time
	 * It uses the AnalyseHotels method to find all reservations between a certain period of time
	 * Loops through an arrayList containing all reservations for that period of time and gets the total amount of rates
	 * 
	 * @author Tomasz
	 * @param reservationList
	 * @param start The start date from the data will be gathered
	 * @param end end The date when the data will stop being gathered
	 * @return integer that's equal to the total rates
	 */
	public static int getTotalRates(TreeMap<String, Reservation> reservationList, Date start, Date end) {
		ArrayList<Reservation> res = AnalyseHotels(reservationList, start, end);
		int total = 0;
		for(int i=0; i<res.size(); i++) {
			total = (int) Double.parseDouble(res.get(i).getPrice()) + total;
		}
		return total;
	}
	
	
	/**
	 *  This method gets the total rates for a specific hotel over a certain period of time
	 *  It uses the AnalyseHotels method to find all reservations between a certain period of time
	 *  Loops through an arrayList containing all reservations for that period of time and adds the rates of only the hotel 
	 *  Specified
	 * 
	 * @author Tomasz
	 * @param reservationList
	 * @param start The start date from the data will be gathered
	 * @param end end The date when the data will stop being gathered
	 * @param hotelType The hotel you want to get the rates for
	 * @return integer that's equal to the total rates for a specific hotel
	
	 */
	public static int getTotalRates(TreeMap<String, Reservation> reservationList, Date start, Date end, String hotelType) {
		ArrayList<Reservation> res = AnalyseHotels(reservationList, start, end);
		int total = 0;
		for(int i=0; i<res.size(); i++) {
			if(hotelType.equals(res.get(i).getRoom().getRoomType().getHotelType())) {
				total = (int) Double.parseDouble(res.get(i).getPrice()) + total;
			}
			
		}
		return total;
	}
	
	/**
	 *  This overloaded method gets the total occupancy for all hotel over a certain period of time
	 *  It uses the AnalyseHotels method to find all reservations between a certain period of time
	 *  Loops through an arrayList containing all reservations for that period of time and adds the number of occupants
	 *  for all hotels
	 * 
	 * @author Tomasz
	 * @param reservationList TreeMap that contains all reservations
	 * @param start The start date from the data will be gathered
	 * @param end end The date when the data will stop being gathered
	 * @return integer that's equal to the total occupancy for all hotels
	
	 */
	public static int getTotalOccupancy(TreeMap<String, Reservation> reservationList, Date start, Date end) {
		ArrayList<Reservation> res = AnalyseHotels(reservationList, start, end);
		int total = 0;
		for(int i=0; i<res.size(); i++) {
			total = Integer.valueOf(res.get(i).getRoom().getOccupancy()) + total;
		}
		return total;
	}
	
	/**
	 *  This overloaded method gets the total occupancy for a specific hotel over a certain period of time
	 *  It uses the AnalyseHotels method to find all reservations between a certain period of time
	 *  Loops through an arrayList containing all reservations for that period of time and adds occupants of only the hotel 
	 *  Specified
	 * 
	 * @author Tomasz
	 * @param reservationList
	 * @param start The start date from the data will be gathered
	 * @param end end The date when the data will stop being gathered
	 * @param hotelType The hotel you want to get the rates for
	 * @return integer that's equal to the total occupancy for a specific hotel
	
	 */
	public static int getTotalOccupany(TreeMap<String, Reservation> reservationList, Date start, Date end, String hotelType) {
		ArrayList<Reservation> res = AnalyseHotels(reservationList, start, end);
		int total = 0;
		for(int i=0; i<res.size(); i++) {
			if(hotelType.equals(res.get(i).getRoom().getRoomType().getHotelType())) {
				total = Integer.valueOf(res.get(i).getRoom().getOccupancy()) + total;
			}
			
		}
		return total;
	}
}
