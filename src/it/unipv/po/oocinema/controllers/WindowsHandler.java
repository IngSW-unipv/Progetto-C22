package it.unipv.po.oocinema.controllers;

/**
* Classe che espone metodi per la gestione della finestra

*/
import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.Window;

public class WindowsHandler {
	
	public static void openWindow(Class<?> c, String fxmlName) {
		Parent root;
		try {
			root = FXMLLoader.load(c.getResource(fxmlName));
			Stage stage = new Stage();
			stage.setTitle("OOCinema");
			
		    stage.getIcons().add(new Image("file:src/it/unipv/po/oocinema/resources/logo.png"));
		     
			stage.setScene(new Scene(root));
			if(fxmlName.contains("registrazione") || fxmlName.contains("login")) {
				stage.setHeight(450);
		        stage.setWidth(600);
			}else {
				stage.setHeight(637);
		        stage.setWidth(1210);
			}
	        stage.centerOnScreen();
	        stage.setResizable(false);
			stage.show();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void closeWindow(Window window) {
		window.hide();
	}
	
}
