package CsvReaderWriter;
import java.io.BufferedReader;

import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import model.room.Room;
import model.customer.Customer;
import model.reservations.Reservation;


/**
 * This class reads a reservation into a csv file using the fileWriter
 * 
 * @author Tomasz
 *
 */
public class CsvWriter {
	//private static Reservation reservation;
	
	
/**
 * This method locates a csv file and fills it with a reservation stored in a reservation object
 * It uses the filewriter to append values into the csv file. The values of the reservation are stored in a list
 * which is then iterated appending the values of the reservation object into the csv file one by one
 * 
 * @author Tomasz
 * @author John
 * @param filePath Location of csv file that stores reservations
 * @param reservation Reservation object that contains all the reservation details
 */
	public static void writeCsv(String filePath, TreeMap<String,Reservation> reservations) {
		List<Reservation> users = new ArrayList<Reservation>();
		for (Map.Entry<String,Reservation> entry : reservations.entrySet()){
			users.add(entry.getValue());
		}



		FileWriter fileWriter = null;
		try {
			new FileWriter(filePath, false).close();
			fileWriter = new FileWriter(filePath, true);
			fileWriter.append("First Name, Second Name, Phone Number, Email, Booking Number, Reservation Type, Check-In Date, Check-Out Date, Rating, Room-Type, Occupancy, Price\n");

			for(Reservation u: users) {
				fileWriter.append(u.getCustomer().getFirstName());//add first name
				fileWriter.append(",");
				fileWriter.append(u.getCustomer().getSecondName());//add last name
				fileWriter.append(",");
				fileWriter.append(u.getCustomer().getPhone());//add phone
				fileWriter.append(",");
				fileWriter.append(u.getCustomer().getEmail());//add email
				fileWriter.append(",");
				fileWriter.append(u.getReservationNumber());
				fileWriter.append(",");
				fileWriter.append(u.getReservationType());
				fileWriter.append(",");
				fileWriter.append(u.getCheckInDate());
				fileWriter.append(",");
				fileWriter.append(u.getCheckOutDate());
				fileWriter.append(",");
				fileWriter.append(u.getRoom().getRoomType().getHotelType());
				fileWriter.append(",");
				fileWriter.append(u.getRoom().getRoomType().getType());
				fileWriter.append(",");
				fileWriter.append(u.getRoom().getOccupancy());
				fileWriter.append(",");
				fileWriter.append(u.getPrice());
				fileWriter.append("\n");
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				fileWriter.flush();
				fileWriter.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
	}
}