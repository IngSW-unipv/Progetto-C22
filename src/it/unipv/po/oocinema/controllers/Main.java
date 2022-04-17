package it.unipv.po.oocinema.controllers;

import java.sql.Date;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import it.unipv.po.oocinema.model.cinema.Film;
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
		
		  Parent root = FXMLLoader.load(getClass().getResource("../view/scenes/prenotazione.fxml"));
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
