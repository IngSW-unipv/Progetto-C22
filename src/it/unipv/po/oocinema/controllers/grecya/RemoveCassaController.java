package it.unipv.po.oocinema.controllers.grecya;

import java.net.URL;
import java.util.ResourceBundle;

import it.unipv.po.oocinema.model.acquirenti.Cassa;
import it.unipv.po.oocinema.persistenza.MySQLConnectionFactory;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

/**
 * Controller che gestisce la finestra di rimozione della cassa
 */
public class RemoveCassaController implements Initializable {
	@FXML
	private TableView<Cassa> tableView;
	@FXML
	private TableColumn<Cassa, String> ID_cash;
	@FXML
	private TableColumn<Cassa, String> pw_cash;

	/**
	 * Metodo chiamato all'apertura della finestra che riempie tableView recuperando
	 * i dati dal database
	 * 
	 * @see MySQLController#getAllCassa()
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		ID_cash.setCellValueFactory(new PropertyValueFactory<Cassa, String>("ID_cash"));
		pw_cash.setCellValueFactory(new PropertyValueFactory<Cassa, String>("pw_cash"));

		tableView.getItems().setAll(MySQLConnectionFactory.getAllCassa());
	}

	/**
	 * Rimuove un record cassa dal database recuperando i dati dall'interfaccia
	 * grafica
	 * 
	 * @see MySQLController#removeCassa(Cassa)
	 */
	@FXML
	public void removeCassa() {
		try {
			MySQLConnectionFactory.removeCassa(tableView.getSelectionModel().getSelectedItem());

			// Aggiorno la UI
			tableView.getItems().setAll(MySQLConnectionFactory.getAllCassa());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();

		}
	}

}