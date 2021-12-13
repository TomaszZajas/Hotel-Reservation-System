package model.reservations;

public class ReserveRoom {
	
	/**
	 * Method that returns the options of hotels the user, get printed by the main menu
	 * @param hotelType
	 * @param occupancy
	 */
	public static void roomsAndHotels(String hotelType, String occupancy) {
		if (hotelType.equals("5-star")) {
			if(occupancy.equals("3")) {
				System.out.println("Please choose room type: " + "\r\n"
						+ "(1) Deluxe Family\r\n");
			}else if(occupancy.equals("1")){
				System.out.println("Please choose room type: " + "\r\n"
						+ "(1) Deluxe Double\r\n"
						+ "(2) Deluxe Twin\r\n"
						+ "(3) Deluxe Single\r\n"
						+ "(4) Deluxe Family");
			}else {
				System.out.println("Please choose room type: " + "\r\n"
						+ "(1) Deluxe Double\r\n"
						+ "(2) Deluxe Twin\r\n"	
						+ "(3) Deluxe Family");
			}
		}else if(hotelType.equals("4-star")) {
			if(occupancy.equals("1")) {
				System.out.println("Please choose room type: " + "\r\n"
						+ "(1) Executive Single\r\n"
						+ "(2) Executive Twin\r\n"
						+ "(3) Executive Double");
			}else{        			 
				System.out.println("Please choose room type: " + "\r\n"
						+ "(1) Executive Twin\r\n"
						+ "(2) Executive Double");
			}
		}else {
			if(occupancy.equals("1")) {
				System.out.println("Please choose room type: " + "\r\n"
						+ "(1) Classic Twin\r\n"
						+ "(2) Classic Double\r\n"
						+ "(3) Classic Single");
			}else {
				System.out.println("Please choose room type: " + "\r\n"
						+ "(1) Classic Twin\r\n"
						+ "(2) Classic Double\r\n");
			}
		}
	}
	
	/**
	 * Follow up method that prints the possible room choices
	 * @param num 
	 * @param hotelType
	 * @param occupancy
	 * @return String of the room type the user chose
	 */
	public static String chooseRoom(String num, String hotelType, String occupancy) {
		String type = null;
		if(hotelType.equals("3-star")) {
			if(num.equals("1")) {
				type = "Classic Twin";
			}else if(num.equals("2")) {
				type = "Classic Double";
			}else{
				type = "Classic Single";
			}
		}else if(hotelType.equals("4-star")) {
			if(num.equals("1")) {
				type = "Executive Single";
			}else if(num.equals("2")) {
				type = "Executive Twin";
			}else{
				type = "Executive Double";
			}
		}else {
			if(occupancy.equals("3")) {
				type = "Deluxe Family";
			}else if(occupancy.equals("1")){
				if(num.equals("1")) {
					type = "Deluxe Double";
				}else if(num.equals("2")) {
					type = "Deluxe Twin";
				}else if(num.equals("3")){
					type = "Deluxe Single";
				}else {
					type = "Deluxe Family";
				}
			}else {
				if(num.equals("1")){
					type = "Deluxe Double";
				}else if(num.equals("2")) {
					type = "Deluxe Twin";
				}else if(num.equals("3")){
					type = "Deluxe Family";
				}
			}
		}
		return type;
	}
}
