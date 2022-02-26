package controllers;


import com.sun.javafx.tk.Toolkit;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
* Punto di partenza del programma
*/
public class Main extends Application {

	/**
	* Punto di partenza del programma, carica la schermata di login
	*/
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("layouts/login.fxml"));
        primaryStage.setTitle("OOCinema");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.getIcons().add(new Image("https://d1nhio0ox7pgb.cloudfront.net/_img/v_collection_png/512x512/shadow/movie.png"));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
