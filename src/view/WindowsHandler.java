package view;

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
            stage.setScene(new Scene(root, 450, 450));
            stage.show();
            // Hide this current window (if this is what you want)
            //((Node)(event.getSource())).getScene().getWindow().hide();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
