package it.unipv.po.oocinema.controllers.grecya;

import java.net.URL;
import java.util.ResourceBundle;

import it.unipv.po.oocinema.model.cinema.Film;
import it.unipv.po.oocinema.persistenza.MySQLConnectionFactory;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

/**
 * Controller che gestisce la finestra di rimozione di un film
 */
public class RemoveFilmController implements Initializable {
	@FXML
	private TableView<Film> tableView;
	@FXML
	private TableColumn<Film, String> ID_film;
	@FXML
	private TableColumn<Film, String> titolo;

	/**
	 * Metodo chiamato all'apertura della finestra che riempie tableView recuperando
	 * i dati dal database
	 * 
	 * @see MySQLController
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		ID_film.setCellValueFactory(new PropertyValueFactory<Film, String>("ID_film"));
		titolo.setCellValueFactory(new PropertyValueFactory<Film, String>("titolo"));

		tableView.getItems().setAll(MySQLConnectionFactory.getAllFilms());
	}

	/**
	 * Rimuove un record Film dal database recuperando i dati dall'interfaccia
	 * grafica
	 * 
	 * @see MySQLController#removeFilm(Film)
	 */
	@FXML
	public void removeFilm() {
		MySQLConnectionFactory.removeFilm(tableView.getSelectionModel().getSelectedItem());

		// Aggiorno la UI
		tableView.getItems().setAll(MySQLConnectionFactory.getAllFilms());
	}

}
