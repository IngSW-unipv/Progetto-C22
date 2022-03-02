package view;

/**
* Classe che espone metodi per la gestione della finestra

*/
import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class WindowsHandler {
	public static void openWindow(Class c, String fxmlName) {
		Parent root;
		try {
			root = FXMLLoader.load(c.getResource("layouts/" + fxmlName));
			Stage stage = new Stage();
			stage.setTitle("Home admin");
			stage.getIcons().add(
					new Image("https://d1nhio0ox7pgb.cloudfront.net/_img/v_collection_png/512x512/shadow/movie.png")); // mi
																														// permette
																														// di
																														// avere
																														// un
																														// immagine
																														// nell'icona
																														// della
																														// finestra
																														// di
																														// dialogo
																														// che
																														// apparirà
																														// nella
																														// barra
																														// delle
																														// applicazioni
																														// del
																														// pc
			stage.setScene(new Scene(root, 700, 400));
			stage.show();
			// Hide this current window (if this is what you want)
			// ((Node)(event.getSource())).getScene().getWindow().hide();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
