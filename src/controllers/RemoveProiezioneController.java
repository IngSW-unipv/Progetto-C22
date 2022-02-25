package controllers;

import java.net.URL;
import java.util.ResourceBundle;

import database.MySQLConnection;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import model.Proiezione;

/**
* Controller che gestisce la finestra di rimozione di
* una proiezione
*/
public class RemoveProiezioneController implements Initializable {
	@FXML
	private TableView<Proiezione> tableView;
	@FXML 
	private TableColumn<Proiezione, String> columnTitolo;
    @FXML 
    private TableColumn<Proiezione, String> columnSala;
    @FXML 
    private TableColumn<Proiezione, String> columnOrario;

    /**
	* Metodo chiamato all'apertura della finestra che riempie tableView
	* recuperando i dati dal database
	* @see MySQLController
	*/
    @Override
    public void initialize(URL location, ResourceBundle resources) {
    	columnTitolo.setCellValueFactory(new PropertyValueFactory<Proiezione, String>("ID_film"));
    	columnSala.setCellValueFactory(new PropertyValueFactory<Proiezione, String>("ID_sala"));
    	columnOrario.setCellValueFactory(new PropertyValueFactory<Proiezione, String>("Giorno_from"));
    	
    	
        tableView.getItems().setAll(MySQLConnection.getAllProiezioni());
    }
    
    /**
	* Rimuove un record Proiezione dal database recuperando i dati
	* dall'interfaccia grafica
	* @see MySQLController#removeProiezione(Proiezione)
	*/
	@FXML
	public void removeProiezione() {
		MySQLConnection.removeProiezione(tableView.getSelectionModel().getSelectedItem());
		
		//Aggiorno la UI
		tableView.getItems().setAll(MySQLConnection.getAllProiezioni());
	}
	
}