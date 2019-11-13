package Model;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;


/**
 * The Model class is responsible for the creation of the database and data table that
 * will store client information. It handles any queries, manipulation, deleting, and addition
 * of data records.
 * @author Kush
 * @version 1.0
 * @since 11/12/2019
 */
public class ClientMgmtModel {
	
	/**
	 * The conn variable which is responsible for creating a connection with the database.
	 */
	private Connection conn;
	/**
	 * pStat takes in queries for data insertion, manipulation, retrieval, and removal.
	 */
	private PreparedStatement pStat;
	/**
	 * The database name, table name, and file name containing information about the clients.
	 */
	private String databaseName = "clientdb", tableName = "Client", dataFile = "clients.txt";
	
	/**
	 * Connection URL, user-name, and password for the database.
	 */
	public String connectionURL = "jdbc:mysql://localhost:3306/clientdb",  
			  username          = "root",
			  password       ;  //Password for database system will be prompted from user.
	
	/**
	 * This method initializes the connection between the Java program and the database system by connecting
	 * to a specific driver of the database system.
	 */
	public void initializeConnection(String userPass) {
		
		password = userPass;
            //Register JDBC driver
			try {
				System.out.println("INITIALIZING...");
				Class.forName("com.mysql.cj.jdbc.Driver");
				
				conn = DriverManager.getConnection(connectionURL, username, password);    //making a connection
				System.out.println("Connected to: " + connectionURL + "\n");
			} catch(SQLException e) { e.printStackTrace(); }
			catch(Exception e) { e.printStackTrace(); }
	}
	
	/**
	 * This method creates an empty database in the database system.
	 */
	public void createDatabase() {
		System.out.println("CREATING DB...");
		String sql = "CREATE DATABASE " + databaseName;
		try {
			pStat = conn.prepareStatement(sql);
			pStat.executeUpdate();
			System.out.println("Created Database " + databaseName);
		}catch(SQLException e) { e.printStackTrace(); }
		catch(Exception e) { e.printStackTrace(); }
	}
	
	/**
	 * Creates a table in the database with information regarding the client as the columns.
	 */
	public void createTable()
	{
		System.out.println("CREATING Table...");
		String sql = "CREATE TABLE " + tableName + "(" +
					 "ID INT(4) NOT NULL, " +
				     "FIRST_NAME VARCHAR(20) NOT NULL, " +
				     "LAST_NAME VARCHAR(20) NOT NULL, " + 
				     "ADDRESS VARCHAR(50) NOT NULL, " + 
				     "POSTAL_CODE CHAR(7) NOT NULL, " + 
				     "PHONE_NUMBER CHAR(12) NOT NULL, " +
				     "CLIENT_TYPE CHAR (1) NOT NULL, " +
				     "PRIMARY KEY ( id ))";
		try{
			pStat = conn.prepareStatement(sql);
			pStat.executeUpdate();
			System.out.println("Created Table " + tableName);
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * Fills in the table with information from a given text file.
	 */
	public void fillTable()
	{
		System.out.println("FILLING Table...");
		try{
			Scanner sc = new Scanner(new FileReader(dataFile));
			int clientID = 1;
			while(sc.hasNext())
			{
				String clientInfo[] = sc.nextLine().split(";");
				addClient( new Client( 		clientID,
										clientInfo[0],
										clientInfo[1],
										clientInfo[2],
										clientInfo[3],
										clientInfo[4],
										clientInfo[5]));
				clientID ++;
			}
			sc.close();
		}
		catch(FileNotFoundException e)
		{
			System.err.println("File " + dataFile + " Not Found!");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * This function generates a new ID for a new user that is added to the data table.
	 * @return
	 */
	public int generateID() {
		String sql = "SELECT ID FROM " + tableName + " ORDER BY ID DESC LIMIT 1;" ;
		try {
			pStat = conn.prepareStatement(sql);
			ResultSet rSet = pStat.executeQuery();
			int lastID = 0;
			while(rSet.next()) {
				lastID = rSet.getInt("ID");
			}
			rSet.close();
			return lastID;
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			return 0;
		}
	}
	
	/**
	 * Adds a client to the data table.
	 * @param client
	 */
	public void addClient(Client client)
	{
		System.out.println("Adding Client...");
		String sql = "INSERT INTO " + tableName +
				" (ID, FIRST_NAME, LAST_NAME, ADDRESS, POSTAL_CODE, PHONE_NUMBER, CLIENT_TYPE) "
				+ "VALUES ( ?, ?, ?, ?, ?, ?, ?) ;";
		try{
			pStat = conn.prepareStatement(sql);
			pStat.setInt(1, client.getClientID());
			pStat.setString(2, client.getFirstName());
			pStat.setString(3, client.getLastName());
			pStat.setString(4, client.getAddress());
			pStat.setString(5, client.getPostalCode());
			pStat.setString(6, client.getPhoneNumber());
			pStat.setString(7, client.getClientType());
			pStat.executeUpdate();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * Updates information regarding an existing client in the data table.
	 * @param client
	 */
	public void updateClient(Client client)
	{
		System.out.println("Updating Client...");
		String sql = "UPDATE " + tableName + 
				" SET FIRST_NAME = ?, LAST_NAME = ?, ADDRESS = ?, POSTAL_CODE = ?, PHONE_NUMBER = ?, CLIENT_TYPE = ? " +
				"WHERE ID = ?;";

		try{
			pStat = conn.prepareStatement(sql);
			pStat.setString(1, client.getFirstName());
			pStat.setString(2, client.getLastName());
			pStat.setString(3, client.getAddress());
			pStat.setString(4, client.getPostalCode());
			pStat.setString(5, client.getPhoneNumber());
			pStat.setString(6, client.getClientType());
			pStat.setInt(7, client.getClientID());
			pStat.executeUpdate();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
	
	
	/**
	 * Removes a client record from the data table.
	 * @param client
	 */
	public void deleteClient(Client client)
	{
		System.out.println("Deleting Client...");
		String sql = "DELETE FROM " + tableName +
				" WHERE ID = ? AND (FIRST_NAME = ? OR LAST_NAME = ?);";
		try{
			pStat = conn.prepareStatement(sql);
			pStat.setInt(1, client.getClientID());
			pStat.setString(2, client.getFirstName());
			pStat.setString(3, client.getLastName());
			pStat.executeUpdate();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
	
	
	/**
	 * Searches for a client based on a given ID.
	 * @param searchID
	 * @return
	 */
	public String searchClientID(int searchID)
	{
		try {
			String sql = "SELECT * FROM " + tableName + " WHERE id = ?";
			pStat = conn.prepareStatement(sql);
			pStat.setInt(1, searchID);
			ResultSet clients = pStat.executeQuery();
			String clientString = "";
			while(clients.next())
			{
				clientString += "ID: " + clients.getInt("ID") + "     " + 
								"NAME: " + clients.getString("FIRST_NAME") + " " + 
								                clients.getString("LAST_NAME") + "     " + 
								"TYPE: " + clients.getString("CLIENT_TYPE");
				clientString+="\n";
			}
			clients.close();
			return clientString;
		} catch (SQLException e) {
			e.printStackTrace();
			return "Error in loading client names. Please try again.";
		}
	}
	
	
	/**
	 * Searches for clients based on entries given for last name.
	 * @param userSearchEntry
	 * @return
	 */
	public String searchLastName(String userSearchEntry)
	{
		try {
			String sql = "select * from client where LAST_NAME LIKE ?"; // ****NOTE --> JDBC does not allow '' in your SQL
			pStat = conn.prepareStatement(sql);						 // query like SQL does. This is the way to do it.	
			pStat.setString(1, "%"+userSearchEntry + "%");
			ResultSet clients = pStat.executeQuery();
			String clientString = "";
			while(clients.next())
			{
				clientString += "ID: " + clients.getInt("ID") + "     " + 
								"NAME: " + clients.getString("FIRST_NAME") + " " + 
								                clients.getString("LAST_NAME") + "     " + 
								"TYPE: " + clients.getString("CLIENT_TYPE");
				clientString+="\n";
			}
			clients.close();
			return clientString;
		} catch (SQLException e) {
			e.printStackTrace();
			return "Error in loading client names. Please try again.";
		}
	}
	
	/**
	 * Searches for clients based on their client type.
	 * @param userSearchEntry
	 * @return
	 */
	public String searchClientType(String userSearchEntry)
	{
		try {
			String sql = "select * from client where CLIENT_TYPE = ?"; 
			pStat = conn.prepareStatement(sql);						 
			pStat.setString(1, userSearchEntry);
			ResultSet clients = pStat.executeQuery();
			String clientString = "";
			while(clients.next())
			{
				clientString += "ID: " + clients.getInt("ID") + "     " + 
								"NAME: " + clients.getString("FIRST_NAME") + " " + 
								                clients.getString("LAST_NAME") + "     " + 
								"TYPE: " + clients.getString("CLIENT_TYPE");
				clientString+="\n";
			}
			clients.close();
			return clientString;
		} catch (SQLException e) {
			e.printStackTrace();
			return "Error in loading client names. Please try again.";
		}
	}
	
	/**
	 * Retrieves all information regarding a selected client to be displayed on the GUI by the View
	 * class.
	 * @param passedID
	 * @return
	 */
	public String showOnRightPanel(int passedID) {
		try {
			String sql = "SELECT * FROM " + tableName + " WHERE id = ?";
			pStat = conn.prepareStatement(sql);
			pStat.setInt(1, passedID);
			ResultSet clients = pStat.executeQuery();
			String clientString = "";
			while(clients.next())
			{
				clientString += clients.getInt("ID") +"\n"+ 
								clients.getString("FIRST_NAME") + "\n" + 
								clients.getString("LAST_NAME") + "\n" +
								clients.getString("ADDRESS") + "\n" +
								clients.getString("POSTAL_CODE") + "\n" +
								clients.getString("PHONE_NUMBER") + "\n" +
								clients.getString("CLIENT_TYPE");

				//clientString+="\n";
			}
			clients.close();
			return clientString;
		} catch (SQLException e) {
			e.printStackTrace();
			return "Error in loading client names. Please try again.";
		}
	}
	
	
}

