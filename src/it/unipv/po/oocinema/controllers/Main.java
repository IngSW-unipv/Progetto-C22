package it.unipv.po.oocinema.controllers;

import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
		/*
		 * Parent root =
		 * FXMLLoader.load(getClass().getResource("../view/scenes/film.fxml"));
		 * primaryStage.setTitle("Home OOCinema"); primaryStage.setScene(new
		 * Scene(root)); primaryStage.setHeight(670); primaryStage.setWidth(1210);
		 * primaryStage.centerOnScreen(); primaryStage.show();
		 */
		DBFacade f = new DBFacade();
		f.aggiungiFilm(new Film("titolo"));
    }


    public static void main(String[] args) {
        launch(args);
        
    }
}
