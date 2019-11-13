package Main;

import Controller.ClientMgmtController;
import Model.ClientMgmtModel;
import View.ClientMgmtView;
/**
 * ClientMgmtApp is the class containing the programs main method.
 * @author Kush
 * @version 1.0
 * @since 11/12/2019
 */
public class ClientMgmtApp {
	/**
	 * Main method that initiates the creation of the GUI, database, and data table. It constructs the Model, View
	 * and Controller classes.
	 * @param args
	 */
	public static void main(String [] args) {
		ClientMgmtView appView = new ClientMgmtView();
		ClientMgmtModel appModel = new ClientMgmtModel();
		ClientMgmtController appController = new ClientMgmtController(appView, appModel);
	}
}
