package it.unipv.po.oocinema.view;

/**
* Classe che espone metodi per la gestione della finestra

*/
import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class WindowsHandler {
	public static void openWindow(Class c, String fxmlName) {
		Parent root;
		try {
			root = FXMLLoader.load(c.getResource("layouts/" + fxmlName));
			Stage stage = new Stage();
			stage.setTitle("Home admin");
																														// pc
			stage.setScene(new Scene(root, 700, 400));
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
