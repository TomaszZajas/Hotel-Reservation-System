package CsvReaderWriter;
import model.reservations.Reservation;
import DateAndTime.Date;
import model.customer.Customer;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.TreeMap;
import java.util.zip.CheckedInputStream;
import model.room.*;
import rateCalculations.rateCalculations;
import DateAndTime.Date;


/**
 * This class is designed to read through the file that stores all reservations and store each individual reservations in a TreeMap
 * 
 * @author Tomasz
 * @author John
 *
 */
public class ReadReservationFile {
	
	/**
	 * This method takes a csv file and reads through it using a buffered reader
	 * storing each value and eventually converting them to a reservation
	 * using the reservation class and object and storing them in a TreeMap 
	 * The buffered reader reads the data column by column until it reaches the last column
	 * it then skips onto the next row and begins reading from column index 0 again until there are no more rows.
	 * 
	 * @author John
	 * @author Tomasz
	 * @param filepath Location of csv file where all the reservations are stored
	 * @return TreeMap that uses a unique reservation number as a key and a reservation as a value
	 */
	public static TreeMap<String, Reservation> readReservation(String filepath){
		TreeMap<String, Reservation> reservations = new TreeMap<>();//TreeMap<String, Reservation>
		String line = "";  
		String splitBy = ",";  
		try   
		{

			File file = new File(filepath);
			if(!file.exists()){
				file.createNewFile();
			}
			//parsing a CSV file into BufferedReader class constructor  
			BufferedReader br = new BufferedReader(new FileReader(filepath));

			br.readLine();
			while ((line = br.readLine()) != null)   //returns a Boolean value
			{  
				String[] reservation = line.split(splitBy);// use comma as separator  
				String firstNameString = reservation[0];
				String lastNameString = reservation[1];
				String numberString = reservation[2];
				String emailString = reservation[3];
				Customer customer = new Customer(firstNameString, lastNameString, numberString, emailString);
				String reservationNumberString = reservation[4];
				String reservationTypeString = reservation[5];
				String checkInString = String.valueOf(reservation[6]);
				String checkOutString = String.valueOf(reservation[7]);
				String hotelTypeString = reservation[8];
				String roomTypeString = reservation[9];
				RoomType roomType = new RoomType(hotelTypeString, roomTypeString);
				String occupancyString = reservation[10];
				Room room = new Room(roomType, occupancyString);
				String priceString = reservation[11];
				rateCalculations rate = new rateCalculations(checkInString, checkOutString, roomTypeString);
				Reservation res = new Reservation(customer, reservationTypeString, checkInString, checkOutString, 1, room, rate) ;
				res.setReservationNumber(Integer.parseInt(reservationNumberString));
				reservations.put(reservationNumberString, res);
				
			}  
		}   
		catch (IOException e)   
		{  
			e.printStackTrace();  
		} 
		return reservations;
	}
}
