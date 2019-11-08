package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

public class ClientMgmtView extends JFrame {
	
	private JRadioButton rButton1;
	private JRadioButton rButton2;
	private JRadioButton rButton3;
	private ButtonGroup rBtnGroup;
	
	private JTextField enterSearchTF;
	private JTextField grid22;
	private JTextField grid32;
	private JTextField grid42;
	private JTextField grid52;
	private JTextField grid62;
	private JTextField grid72;
	private JTextField grid82;
	
	private JButton btnSearch; 
	private JButton btnClearSearch;
	private JButton grid91;
	private JButton grid92;
	private JButton grid93;
	
	private JTextArea scrollArea;


	public ClientMgmtView(){
		setTitle("Client Management System");
		setSize(900,600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);     // Terminates the program when JFrame is closed
		setLayout(new BorderLayout());                      // Sets a BorderLayout
		setMinimumSize(new Dimension(400, 300)); 
		
		//North: Panel with Heading/Title
		
		JLabel title = new JLabel("Client Management Screen");
		Font font = new Font("Segoe UI", Font.BOLD, 20);
		title.setFont(font);
		
		JPanel titlePanelN = new JPanel();
		titlePanelN.add(title);
		add("North", titlePanelN);
		titlePanelN.setBorder(BorderFactory.createLineBorder(Color.black, 1));
		
		JPanel titlePanelW = new JPanel();
		titlePanelW.setLayout(new BorderLayout());
		add("West", titlePanelW);
		titlePanelW.setBorder(BorderFactory.createLineBorder(Color.black, 1));
		
		JPanel titlePanelC = new JPanel();
		titlePanelC.setLayout(new GridLayout(9,3, 0,35));
		add("Center", titlePanelC);
		titlePanelC.setBorder(BorderFactory.createLineBorder(Color.black, 1));
		
		
		JPanel titlePanelWN = new JPanel();
		titlePanelWN.setLayout((new BoxLayout(titlePanelWN, BoxLayout.PAGE_AXIS)));
		titlePanelW.add("North",titlePanelWN);
		
		JPanel titlePanelWC = new JPanel();
		titlePanelWC.setLayout(new BorderLayout());
		titlePanelWC.setPreferredSize(new Dimension(450, 300));
		titlePanelW.add("Center",titlePanelWC);
		
		JLabel title2 = new JLabel("Search Clients");
		Font font2 = new Font("Segoe UI", Font.BOLD, 14);
		title2.setFont(font2);
		titlePanelWN.add(title2);
		
		JLabel searchType = new JLabel("Select type of search to be performed: ");
		Font fontsearchType = new Font("Segoe UI", Font.PLAIN, 12);
		searchType.setFont(fontsearchType);
		titlePanelWN.add(searchType);
		
		rButton1 = new JRadioButton("Client ID");
		rButton1.setFont(fontsearchType);
		titlePanelWN.add(rButton1);
		
		rButton2 = new JRadioButton("Last Name");
		rButton2.setFont(fontsearchType);
		titlePanelWN.add(rButton2);
		
		rButton3 = new JRadioButton("Client Type");
		rButton3.setFont(fontsearchType);
		titlePanelWN.add(rButton3);
		
		rButton1.setSelected(true);
		rBtnGroup = new ButtonGroup();
		rBtnGroup.add(rButton1);
		rBtnGroup.add(rButton2);
		rBtnGroup.add(rButton3);
		
		JLabel enterSearch = new JLabel("Enter the search parameter below: ");
		enterSearch.setFont(fontsearchType);
		titlePanelWN.add(enterSearch);
		
		enterSearchTF = new JTextField();
		titlePanelWN.add(enterSearchTF);
		
        btnSearch = new JButton("Search");
		titlePanelWN.add(btnSearch);
		
		btnClearSearch = new JButton("Clear Search");
		titlePanelWN.add(btnClearSearch);
		
		JLabel title3 = new JLabel("Search Results:");
		Font font3 = new Font("Segoe UI", Font.BOLD, 14);
		title3.setFont(font3);
		titlePanelWC.add("North",title3);
		
		JLabel grid11 = new JLabel("");
		titlePanelC.add(grid11);
		
		JLabel grid12 = new JLabel("Client Information:");
		Font font4 = new Font("Segoe UI", Font.BOLD, 14);
		grid12.setFont(font4);
		titlePanelC.add(grid12);
		
		JLabel grid13 = new JLabel("");
		titlePanelC.add(grid13);
		
		JLabel grid21 = new JLabel("Client ID: ");
		titlePanelC.add(grid21);
		
		grid22 = new JTextField();
		titlePanelC.add(grid22);
		
		JLabel grid23 = new JLabel("");
		titlePanelC.add(grid23);
		
		JLabel grid31 = new JLabel("First Name: ");
		titlePanelC.add(grid31);
		
		grid32 = new JTextField();
		titlePanelC.add(grid32);
		
		JLabel grid33 = new JLabel("");
		titlePanelC.add(grid33);
		
		JLabel grid41 = new JLabel("Last Name");
		titlePanelC.add(grid41);
		
		grid42 = new JTextField();
		titlePanelC.add(grid42);
		
		JLabel grid43 = new JLabel("");
		titlePanelC.add(grid43);

		JLabel grid51 = new JLabel("Address");
		titlePanelC.add(grid51);
		
		grid52 = new JTextField();
		titlePanelC.add(grid52);
		
		JLabel grid53 = new JLabel("");
		titlePanelC.add(grid53);
		
		JLabel grid61 = new JLabel("Postal Code");
		titlePanelC.add(grid61);
		
		grid62 = new JTextField();
		titlePanelC.add(grid62);
		
		JLabel grid63 = new JLabel("");
		titlePanelC.add(grid63);
		
		JLabel grid71 = new JLabel("Phone Number:");
		titlePanelC.add(grid71);
		
		grid72 = new JTextField();
		titlePanelC.add(grid72);
		
		JLabel grid73 = new JLabel("");
		titlePanelC.add(grid73);
		
		JLabel grid81 = new JLabel("Client Type (C or R):");
		titlePanelC.add(grid81);
		
		grid82 = new JTextField();
		titlePanelC.add(grid82);
		
		JLabel grid83 = new JLabel("");
		titlePanelC.add(grid83);
		
		grid91 = new JButton("Save");
		titlePanelC.add(grid91);
		
		grid92 = new JButton("Delete");
		titlePanelC.add(grid92);
		
		grid93 = new JButton("Clear");
		titlePanelC.add(grid93);

		scrollArea = new JTextArea();
		scrollArea.setEditable(false);
		JScrollPane scroll = new JScrollPane(scrollArea);
		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		titlePanelWC.add("Center",scroll);
		
		setVisible(true);
	}

//LEFT PANEL EVENT HANDLING---------------------------------------------------------------------
	
	//Determine which of the 3 radio buttons is selected
	public int getRadioButton() {
		if(rButton1.isSelected()) {
			return 1;
		}
		else if(rButton2.isSelected()) {
			return 2;
		}
		else if(rButton3.isSelected()) {
			return 3;
		}
		//default return
		return 1;
	}
	
	//This will return the contents of the search textfield
	public String getSearchTextField() {
		return enterSearchTF.getText();
	}
	
	//Here we are registering the search button to an ActionListener class in the controller
	public void registerSearchBtn(ActionListener listenSearchBtn) {
		btnSearch.addActionListener(listenSearchBtn);
	}
	
	//Here we are registering the clear search button to an ActionListener in the controller
	public void registerClearSearchBtn(ActionListener listenClearSearchBtn) {
		btnClearSearch.addActionListener(listenClearSearchBtn);
	}
	
	//This will display the records within the text area
	public void setTextArea(String filteredClientList) {
		scrollArea.setText(filteredClientList);
	}
	
	//Action could not be performed message in case of any problem in execution of actionEvent
	public void actionNotPerformed() {
		JOptionPane.showMessageDialog(btnSearch, "The action could not be performed. Please check your inputs.");
	}
	
	public void clearSearch() {
		scrollArea.setText("");
		enterSearchTF.setText("");
	}

//FINISH----------------------------------------------------------------------------------------
	
//RIGHT PANEL EVENT HANDLING--------------------------------------------------------------------

	public String clientIDText() {
		return grid22.getText();
	}
	
	public String fNameText() {
		return grid32.getText();
	}
	
	public String lNameText() {
		return grid42.getText();
	}
	
	public String addressText() {
		return grid52.getText();
	}
	
	public String postalCodeText() {
		return grid62.getText();
	}
	
	public String phoneNumText() {
		return grid72.getText();
	}
	
	public String clientTypeText() {
		return grid82.getText();
	}
	
	public void registerSaveButton(ActionListener listenForSave) {
		grid91.addActionListener(listenForSave);
	}
	
	public void registerDeleteButton(ActionListener listenForDelete) {
		grid92.addActionListener(listenForDelete);
	}
	
	public void registerClear2Button(ActionListener listenForClear2) {
		grid93.addActionListener(listenForClear2);
	}
	
	public void saveSuccessfulMessage() {
		JOptionPane.showMessageDialog(grid91, "Save Successful!");
	}
	
	public void deleteSuccessfulMessage() {
		JOptionPane.showMessageDialog(grid92, "Delete Successful");
	}
	
	public void deleteNotPerformed() {
		JOptionPane.showMessageDialog(btnSearch, "In order to delete a client record, you must input BOTH *id* and *last name*.");
	}
	
	public void clientExists() {
		JOptionPane.showMessageDialog(btnSearch, "A client by this ID already exists. Data for user will be modified.");
	}
	
	public void clearRightPanel() {
		grid22.setText("");
		grid32.setText("");
		grid42.setText("");
		grid52.setText("");
		grid62.setText("");
		grid72.setText("");
		grid82.setText("");
	}
	
}
