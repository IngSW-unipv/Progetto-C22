package it.unipv.po.oocinema.controllers;


import it.unipv.po.oocinema.persistenza.DBFacade;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * Punto di partenza del programma (Entry Point)
 */
public class Main extends Application {

	/**
	 * Punto di partenza del programma, carica la schermata di login
	 */
	@Override
    public void start(Stage primaryStage) throws Exception{
		
		  Parent root = FXMLLoader.load(getClass().getResource("../view/scenes/login.fxml"));
		  primaryStage.setTitle("OOCinema");
		  primaryStage.getIcons().add(new Image("file:src/it/unipv/po/oocinema/resources/logo.png"));
		  primaryStage.setScene(new Scene(root)); 
		  primaryStage.setHeight(450); 
		  primaryStage.setWidth(600);
		  primaryStage.centerOnScreen(); 
		  primaryStage.setResizable(false);
		  primaryStage.show();
		  DBFacade.getInstance().initializeDB(); 
    }


	public static void main(String[] args) { 
		launch(args); 
	}
}
