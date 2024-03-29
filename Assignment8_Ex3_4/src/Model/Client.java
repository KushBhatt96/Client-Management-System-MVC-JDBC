package Model;

/**
 * The Client class holds information related to each client. This includes ID, first and last name, 
 * address, postal code, phone number, and client type.
 * @author Kush
 * @version 1.0
 * @since 11/12/2019
 */
public class Client {
	
	/**
	 * Client object attributes.
	 */
	private int clientID;
	private String firstName;
	private String lastName;
	private String address;
	private String postalCode;
	private String phoneNumber;
	private String clientType;
	
	public Client(int clientID ,String firstName, String lastName, String address, String postalCode, String phoneNumber, String clientType) {
		this.clientID = clientID;
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.postalCode = postalCode;
		this.phoneNumber = phoneNumber;
		this.clientType = clientType;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getAddress() {
		return address;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public String getClientType() {
		return clientType;
	}
	
	public String toString () {
		String clientInfo = this.firstName + " " + this.lastName + " " + this.address + " " + this.postalCode +
				" " + this.phoneNumber + " " + this.clientType;
		return clientInfo;
	}

	public int getClientID() {
		return clientID;
	}
	
	

}
