package it.unipv.po.oocinema.controllers;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
		
		  Parent root = FXMLLoader.load(getClass().getResource("../view/scenes/homeCLI.fxml"));
		  primaryStage.setTitle("Home OOCinema"); 
		  primaryStage.setScene(new Scene(root)); 
		  primaryStage.setHeight(637); 
		  primaryStage.setWidth(1210);
		  primaryStage.centerOnScreen(); 
		  primaryStage.show();
		  primaryStage.setResizable(false);

    }


    public static void main(String[] args) {
        launch(args);
    }
}
