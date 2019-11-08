package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
		//appModel.createTable();
		//appModel.fillTable();
	
		//calling to register the functions in View with ActionListener inner classes here in the Controller
		appView.registerSearchBtn(new SearchButtonListener());
		appView.registerClearSearchBtn(new ClearButtonListener());
		appView.registerSaveButton(new SaveButtonListener());
		appView.registerDeleteButton(new DeleteButtonListener());
		appView.registerClear2Button(new Clear2ButtonListener());
	}

//LEFT PANEL ACTION LISTENERS-------------------------------------------------------------
	public class SearchButtonListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent searchEvent) {
			String userSearchEntry;
			try {
				userSearchEntry = appView.getSearchTextField();
				if (appView.getRadioButton() == 1) {          //1 refers to a search based on Client ID
					appView.setTextArea(appModel.searchClientID(Integer.parseInt(userSearchEntry)));
				}
				else if (appView.getRadioButton() == 2) {     //2 refers to a search based on Last Name
					appView.setTextArea(appModel.searchLastName(userSearchEntry));
				}
				else if (appView.getRadioButton() == 3) {     //3 refers to a search based on Client Type
					appView.setTextArea(appModel.searchClientType(userSearchEntry));
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
			int ID = Integer.parseInt(appView.clientIDText());
			String fName = appView.fNameText();
			String lName = appView.lNameText();
			String address = appView.addressText();
			String postalCode = appView.postalCodeText();
			String phoneNum = appView.phoneNumText();
			String cType = appView.clientTypeText();
			
			if (appModel.searchClientID(ID).isEmpty()) { //Add new client
			appModel.addClient(new Client(ID, fName, lName, address, postalCode, phoneNum, cType));
			}
			else{   //Modify existing client
				appModel.updateClient(new Client(ID, fName, lName, address, postalCode, phoneNum, cType));
			}
			appView.saveSuccessfulMessage();
			
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

}
