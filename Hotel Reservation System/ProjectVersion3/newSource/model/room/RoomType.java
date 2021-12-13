package model.room;

/**
 * Object class that defines the room type
 * 
 * @author Ruairi
 *
 */
public class RoomType {
    private String hotelType;
    private String roomType;
    
    /**
     * Constructor that initialises a roomType object
     * @param hotelType Type of the hotel the user is staying in
     * @param roomType Type of room the user is staying in
     */
    public RoomType(String hotelType, String roomType) {
    	this.hotelType = hotelType;
    	this.roomType = roomType;
    }
    
    
    /**
     * @return type of hotel from object instance
     */
    public String getHotelType() {
    	return hotelType;
    	
    }
    
    /**
     * 
     * @return type of room from object instance
     */
    public String getType() {
    	return roomType;
    }
    
    /**
     * Method that returns the hotel type and room type in a String format
     */
    @Override
    public String toString() {
    	return "Hotel: " + hotelType + "\r\n" +
               "Room: " + roomType + "\r\n";
    }
}
    
