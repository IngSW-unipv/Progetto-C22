package it.unipv.po.oocinema.controllers.client;

/**
* Classe che espone metodi per la gestione della finestra

*/
import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.Window;

public class WindowsHandler {
	
	public static void openWindow(Class<?> c, String fxmlName) {
		Parent root;
		try {
			root = FXMLLoader.load(c.getResource("../../view/scenes/" + fxmlName));
			Stage stage = new Stage();
																														
			stage.setScene(new Scene(root));
			stage.setHeight(670);
	        stage.setWidth(1210);
	        stage.centerOnScreen();
	   
			stage.show();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void closeWindow(Window window) {
		window.hide();
	}
	
}
