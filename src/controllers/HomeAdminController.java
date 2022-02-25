package controllers;

import javafx.fxml.FXML;

import view.WindowsHandler;

/**
* Controller che gestisce la finestra admin
*/
public class HomeAdminController {
	/**
	* Apre la finestra per l'aggiunta di un film
	*/
	@FXML
	public void openAddFilmWindow() {
		WindowsHandler.openWindow(getClass(), "addFilm.fxml");
	}
	
	/**
	* Apre la finestra per la rimozione di un film
	*/
	@FXML
	public void openRemoveFilmWindow() {
		WindowsHandler.openWindow(getClass(), "removeFilm.fxml");
	}
	
	/**
	* Apre la finestra per l'aggiunta di una cassa
	*/
	@FXML
	public void openAddCassaWindow() {
		WindowsHandler.openWindow(getClass(), "addCassa.fxml");
	}
	
	/**
	* Apre la finestra per la rimozione di una
	*/
	@FXML
	public void openRemoveCassaWindow() {
		WindowsHandler.openWindow(getClass(), "removeCassa.fxml");
	}
	
	/**
	* Apre la finestra per l'aggiunta di una cassa
	*/
	@FXML
	public void openAddProiezioneWindow() {
		WindowsHandler.openWindow(getClass(), "addProiezione.fxml");
	}
	
	/**
	* Apre la finestra per la rimozione di una
	*/
	@FXML
	public void openRemoveProiezioneWindow() {
		WindowsHandler.openWindow(getClass(), "removeProiezione.fxml");
	}
}
