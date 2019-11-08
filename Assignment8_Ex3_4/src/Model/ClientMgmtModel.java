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



public class ClientMgmtModel {
	
	private Connection conn;
	private PreparedStatement pStat;
	private String databaseName = "clientdb", tableName = "Client", dataFile = "clients.txt";
	
	public String connectionURL = "jdbc:mysql://localhost:3306/clientdb",  
			  username          = "root",
			  password       = "Dimesfordays22";
	
	public void initializeConnection() {

            //Register JDBC driver
			try {
				System.out.println("INITIALIZING...");
				Class.forName("com.mysql.cj.jdbc.Driver");
				
				conn = DriverManager.getConnection(connectionURL, username, password);    //making a connection
				System.out.println("Connected to: " + connectionURL + "\n");
			} catch(SQLException e) { e.printStackTrace(); }
			catch(Exception e) { e.printStackTrace(); }
	}
	
	//Next we will use a preparedStatement to create a DB
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
	
	//Create a Table
	public void createTable()
	{
		System.out.println("CREATING Table...");
		String sql = "CREATE TABLE " + tableName + "(" +
					 "ID VARCHAR(4) NOT NULL, " +
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
	
	//Fill in the table
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
	
	
	// Search client by ID
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
	
	
	// Search client by Last Name
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
	
	// Search client by Client Type
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
	
	
}
