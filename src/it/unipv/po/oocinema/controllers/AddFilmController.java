package it.unipv.po.oocinema.controllers;

import java.io.File;

import it.unipv.po.oocinema.database.MySQLConnectionFactory;
import it.unipv.po.oocinema.model.Film;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * Controller che gestiscee la finestra per l'aggiunta di un film
 */
public class AddFilmController {
	@FXML
	private TextField txtTitolo;
	@FXML
	private TextArea txtDescrizione;
	@FXML
	private TextField txtGenere;
	@FXML
	private TextField txtID;
	@FXML
	private TextField txtDurata;
	@FXML
	private TextField txtCast;
	@FXML
	private TextField txtRegista;
	@FXML
	private TextField txtCosto;

	private String selectedCoverPath, selectedTrailerPath;

	/**
	 * Prende i valori dalla UI ed aggiunge un Film al database
	 * 
	 * @return true se la query ha avuto successo, false altrimenti
	 * @see MySQLController#insertFilm(Film)
	 */
	@FXML
	public void addFilm() {
		Film film = new Film(txtID.getText(), txtTitolo.getText(), txtDescrizione.getText(), txtGenere.getText(),
				Integer.valueOf(txtDurata.getText()), txtRegista.getText(), txtCast.getText(),
				Integer.valueOf(txtCosto.getText()), selectedCoverPath, selectedTrailerPath);

		Alert alert;

		if (MySQLConnectionFactory.insertFilm(film)) {
			alert = new Alert(AlertType.INFORMATION, "Film aggiunto con successo");
		} else {
			alert = new Alert(AlertType.ERROR, "C'è stato un problema nell'aggiunta del film, controlla i dati");
		}

		alert.showAndWait();
		Stage stage = (Stage) txtTitolo.getScene().getWindow();
		stage.close();
	}

	/**
	 * Permette all'utente di scegliere un file di tipo JPG o PNG dal proprio file
	 * system
	 */
	@FXML
	public void loadCoverImage() {
		final FileChooser fileChooser = new FileChooser();

		FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
		FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");
		fileChooser.getExtensionFilters().addAll(extFilterJPG, extFilterPNG);

		// Show open file dialog
		File file = fileChooser.showOpenDialog(null);

		if (file != null) {
			selectedCoverPath = file.getAbsolutePath();
		}

	}

	/**
	 * Permette all'utente di scegliere un file di tipo MP4 dal proprio file system
	 */
	@FXML
	public void loadTrailerVideo() {
		FileChooser fileChooser = new FileChooser();

		// Set extension filter
		FileChooser.ExtensionFilter extMp4 = new FileChooser.ExtensionFilter("MP4 files (*.mp4)", "*.MP4");
		fileChooser.getExtensionFilters().addAll(extMp4);

		// Show open file dialog
		File file = fileChooser.showOpenDialog(null);

		if (file != null) {
			selectedTrailerPath = file.getAbsolutePath();
		}

	}
}
