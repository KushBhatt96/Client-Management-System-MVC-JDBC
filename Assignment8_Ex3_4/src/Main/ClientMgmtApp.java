package Main;

import Controller.ClientMgmtController;
import Model.ClientMgmtModel;
import View.ClientMgmtView;

public class ClientMgmtApp {

	public static void main(String [] args) {
		ClientMgmtView appView = new ClientMgmtView();
		ClientMgmtModel appModel = new ClientMgmtModel();
		ClientMgmtController appController = new ClientMgmtController(appView, appModel);
	}
}
