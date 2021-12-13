package model.customer;

/**
 * This class creates the customer object
 * @author Tomek
 *
 */
public class Customer {
    private String firstName;
    private String secondName;
    private String phone;
    private String email;

    /**
     * Constructor that takes the first name, last name, email and phone number of the customer
     * @author Tomasz
     * @param firstName
     * @param secondName
     * @param phone
     * @param email
     */
    public Customer(String firstName, String secondName, String phone, String email) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.phone = phone;
        this.email = email;

    }
    
    /**
     * Get method
     * @author Tomasz
     * @return Customers first name
     */
    public String getFirstName() {
        return firstName;
    }
    
    /**
     * Set method that sets the customers first name
     * @param firstName
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    
    /**
     * Get method
     * @author Tomasz
     * @return Customers second name
     */
    public String getSecondName() {
        return secondName;
    }
    
    /**
     * Set method that sets the customers second name
     * @author Tomasz
     */
    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }
    
    /**
     * Get method
     * @return customers phone number
     */
    public String getPhone() {
        return phone;
    }
    
    /**
     * Set method that sets the customers phone number
     * @param phone
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }
    
    /**
     * Get method
     * @return customers email
     */
    public String getEmail() {
        return email;
    }
    
    /**
     * Set method that sets the customers email
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }
    
    /**
	 * Method that returns a string with all the details of the customer
	 */
    @Override
    public String toString() {
        return "First Name: " + this.firstName + "\r\n"
                + "Last Name: " + this.secondName + "\r\n"
                + "Phone Number: "+ this.phone + "\r\n" +
                "Email: " + email;
    }
}