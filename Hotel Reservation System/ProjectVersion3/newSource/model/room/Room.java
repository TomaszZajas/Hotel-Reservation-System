package model.room;

/**
 * Class that implements the RoomInterface and uses a roomType object to create a Room object
 * @author Ruairi
 *
 */
public class Room implements RoomInterface{
	private RoomType roomType;
	private String occupancy;
	
	/**
	 * Constructor that creates an instance of a Room object
	 * @param roomType 
	 * @param occupancy
	 */
	public Room(RoomType roomType, String occupancy) {
		this.roomType = roomType;
		this.occupancy = occupancy;
	}
	
	/**
	 * Get method that returns the roomType object
	 */
	public RoomType getRoomType() {
		return roomType;
	}
	
	/**
	 * Get method that returns the occupancy of the room
	 */
	public String getOccupancy() {
		return occupancy;
	}
	
	/**
	 * Method that returns a string with all the details of the room
	 */
	@Override
	public String toString() {
		return roomType.toString() + 
	           "Occupancy: " + occupancy;
	}

}
