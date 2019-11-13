package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import Model.Client;
import Model.ClientMgmtModel;
import View.ClientMgmtView;

public class ClientMgmtController {
	
	private ClientMgmtView appView;
	private ClientMgmtModel appModel;
	
	public ClientMgmtController(ClientMgmtView passedAppView, ClientMgmtModel passedAppModel) {
		appView = passedAppView;
		appModel = passedAppModel;
		
		appModel.initializeConnection();
		//appModel.createDatabase();
		appModel.createTable();
		appModel.fillTable();
	
		//calling to register the functions in View with ActionListener inner classes here in the Controller
		appView.registerSearchBtn(new SearchButtonListener());
		appView.registerClearSearchBtn(new ClearButtonListener());
		appView.registerSaveButton(new SaveButtonListener());
		appView.registerDeleteButton(new DeleteButtonListener());
		appView.registerClear2Button(new Clear2ButtonListener());
		appView.registerScrollArea(new ScrollAreaListener());
	}

//LEFT PANEL ACTION LISTENERS-------------------------------------------------------------
	public class SearchButtonListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent searchEvent) {
			String userSearchEntry;
			String clientText;
			appView.clearJList();
			try {
				userSearchEntry = appView.getSearchTextField();
				if (appView.getRadioButton() == 1) {          //1 refers to a search based on Client ID
					//appView.setTextArea(appModel.searchClientID(Integer.parseInt(userSearchEntry)));
					clientText = appModel.searchClientID(Integer.parseInt(userSearchEntry));
					String [] clientList = clientText.split("\n");
					for (int i = 0; i< clientList.length;i++) {
						appView.setTextArea(clientList[i]);
					}
				}
				else if (appView.getRadioButton() == 2) {     //2 refers to a search based on Last Name
					//appView.setTextArea(appModel.searchLastName(userSearchEntry));
					clientText = appModel.searchLastName(userSearchEntry);
					String [] clientList = clientText.split("\n");
					for (int i = 0; i< clientList.length;i++) {
						appView.setTextArea(clientList[i]);
					}
				}
				else if (appView.getRadioButton() == 3) {     //3 refers to a search based on Client Type
					//appView.setTextArea(appModel.searchClientType(userSearchEntry));
					clientText = appModel.searchClientType(userSearchEntry);
					String [] clientList = clientText.split("\n");
					for (int i = 0; i< clientList.length;i++) {
						appView.setTextArea(clientList[i]);
					}
				}
				
			}catch(Exception e) {
				appView.actionNotPerformed();
			}
		}
	}
	
	public class ClearButtonListener implements ActionListener{
	@Override
	public void actionPerformed(ActionEvent clearEvent) {
		try {
			appView.clearSearch();
		}catch(Exception e) {
			appView.actionNotPerformed();
		}
	}
	}
	
//FINISH-----------------------------------------------------------------------------------	
	
//RIGHT PANEL ACTION LISTENERS-------------------------------------------------------------
	public class SaveButtonListener implements ActionListener{
	@Override
	public void actionPerformed(ActionEvent saveEvent) {
		try {
			String ID = appView.clientIDText();
			String fName = appView.fNameText();
			String lName = appView.lNameText();
			String address = appView.addressText();
			String postalCode = appView.postalCodeText();
			String phoneNum = appView.phoneNumText();
			String cType = appView.clientTypeText();
			
			if(!(postalCode.matches("[A-Z][0-9][A-Z] [0-9][A-Z][0-9]"))) { //Double check postal code input
				appView.actionNotPerformed();
				System.out.println("PC! problem");
			}
			else if(!(phoneNum.matches("(?:\\d{3}-){2}\\d{4}"))) { //Double check phone number input
				appView.actionNotPerformed();
				System.out.println("PN! problem");
			}
			else if(!(cType.equals("C")) && !(cType.equals("R"))) { //Double check client type input
				appView.actionNotPerformed();
				System.out.println("cType! problem");
			}
			else if(fName.length()>20 || lName.length()>20 || address.length()>50) { //Double check input lengths
				appView.actionNotPerformed();
				System.out.println("length! problem");
			}
			//else if(ID) { //Ensure ID does not already exist
			//	appModel.searchClientID(ID).isEmpty()
			//}
			
			else {
			
			if (appView.clientIDText().isEmpty()) { //Add new client
			appModel.addClient(new Client(appModel.generateID()+1, fName, lName, address, postalCode, phoneNum, cType));
			}
			else{   //Modify existing client
				appModel.updateClient(new Client(Integer.parseInt(ID), fName, lName, address, postalCode, phoneNum, cType));
			}
			appView.saveSuccessfulMessage();
			}
			
		}catch(Exception e) {
			appView.actionNotPerformed();
		}
		
	}
	}
	
	public class DeleteButtonListener implements ActionListener{
	@Override
	public void actionPerformed(ActionEvent deleteEvent) {
		try {
			int ID = Integer.parseInt(appView.clientIDText());
			String fName = appView.fNameText();
			String lName = appView.lNameText();
			String address = appView.addressText();
			String postalCode = appView.postalCodeText();
			String phoneNum = appView.phoneNumText();
			String cType = appView.clientTypeText();
			System.out.println(appModel.searchClientID(ID).length());
			System.out.println(appModel.searchLastName(lName));
			if(appModel.searchClientID(ID).length() >=1  && !(lName.isEmpty())) {
			appModel.deleteClient(new Client(ID, fName, lName, address, postalCode, phoneNum, cType));
			}
			else {
				appView.deleteNotPerformed();
			}
			appView.deleteSuccessfulMessage();
		}catch(Exception e) {
			appView.actionNotPerformed();
		}
	}
	}
	
	public class Clear2ButtonListener implements ActionListener{
	@Override
	public void actionPerformed(ActionEvent clear2Event) {
		try {
			appView.clearRightPanel();
		}catch(Exception e) {
			appView.actionNotPerformed();
		}
	}
	}
	
	public class ScrollAreaListener implements MouseListener{

		@Override
		public void mouseClicked(MouseEvent e) {
			
		try {
			// TODO Auto-generated method stub
			String selectedClient = appView.clickOnTextArea();
			String onlyID = selectedClient.replaceAll("[^0-9]", "");
			String rightPanelText = appModel.showOnRightPanel(Integer.parseInt(onlyID));
			String [] rightPanelList = rightPanelText.split("\n");
			appView.clientIDSetText(rightPanelList[0]);
			appView.fNameSetText(rightPanelList[1]);
			appView.lNameSetText(rightPanelList[2]);
			appView.addressSetText(rightPanelList[3]);
			appView.postalCodeSetText(rightPanelList[4]);
			appView.phoneNumSetText(rightPanelList[5]);
			appView.clientTypeSetText(rightPanelList[6]);
		}catch(Exception e1) {
			appView.actionNotPerformed();
		}
		
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}

}
