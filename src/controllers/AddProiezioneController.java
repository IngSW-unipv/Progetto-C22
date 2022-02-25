package controllers;

import java.net.URL;
import java.sql.Date;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import database.MySQLConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.stage.Stage;
import model.Cassa;
import model.FasciaOraria;
import model.Film;
import model.Proiezione;
import model.Sala;

/**
* Controller che gestiscee la finestra per l'aggiunta di un proiezione
*/
public class AddProiezioneController implements Initializable {
	@FXML
	public ChoiceBox choiceTitolo;
	
	@FXML
	public ChoiceBox choiceSala;
	
	@FXML
	public ChoiceBox choiceFasciaOraria;
	
	@FXML
	public DatePicker dataDa;
	
	@FXML
	public DatePicker dataA;
	
	private List<Sala> listaSale;
	private List<FasciaOraria> listaFascieOrarie;
	private List<Film> listaFilm;
	
	/**
	* Metodo chiamato all'apertura della finestra che riempie le ChoiceBox
	* recuperando i dati dal database
	* @see MySQLController#getAllCassa()
	*/
    @Override
    public void initialize(URL location, ResourceBundle resources) {
    	setupTitoli();
    	setupSale();
    	setupFascieOrarie();
    }
    
    /**
	* Effettua una query per ottenere tutte le sale e le imposta
	* nella relativa ChoiceBox
	*/
    private void setupSale() {
    	listaSale = MySQLConnection.getAllSale();
    	
    	List<String> nomiSale = new ArrayList<String>();
    	
    	for (int i = 0; i < listaSale.size(); i++) {
    		nomiSale.add(listaSale.get(i).getN_sala());
    	}
    	
    	ObservableList<String> sale = FXCollections.observableArrayList(nomiSale);
    	
    	choiceSala.setItems(sale);
    	
    	//Valore iniziale
    	if (sale.size() > 0) choiceSala.setValue(sale.get(0));
    }
    
    /**
	* Effettua una query per ottenere tutte le fascie orarie e le imposta
	* nella relativa ChoiceBox
	*/
    private void setupFascieOrarie() {
    	listaFascieOrarie = MySQLConnection.getAllFascieOrarie();
    	
    	List<String> nomiFascieOrarie = new ArrayList<String>();
    	
    	for (int i = 0; i < listaFascieOrarie.size(); i++) {
    		nomiFascieOrarie.add(listaFascieOrarie.get(i).getPossibilita());
    	}
    	
    	ObservableList<String> fascieOrarie = FXCollections.observableArrayList(nomiFascieOrarie);
    	
    	choiceFasciaOraria.setItems(fascieOrarie);
    	
    	//Valore iniziale
    	if (fascieOrarie.size() > 0) choiceFasciaOraria.setValue(fascieOrarie.get(0));
    }
    
    /**
	* Effettua una query per ottenere tutti i titoli e le imposta
	* nella relativa ChoiceBox
	*/
    private void setupTitoli() {
    	listaFilm = MySQLConnection.getAllFilms();
    	
    	List<String> nomiFilm = new ArrayList<String>();
    	
    	for (int i = 0; i < listaFilm.size(); i++) {
    		nomiFilm.add(listaFilm.get(i).getTitolo());
    	}
    	
    	ObservableList<String> films = FXCollections.observableArrayList(nomiFilm);
    	
    	choiceTitolo.setItems(films);
    	
    	//Valore iniziale
    	if (films.size() > 0) choiceTitolo.setValue(films.get(0));
    }
	
	
	/**
	* Prende i valori dalla UI ed aggiunge un Film al database
	* @return true se la query ha avuto successo, false altrimenti
	* @see MySQLController#insertProiezione(Proiezione)
	*/
	@FXML
	public void addProiezione() {
		String ID_film = "";
		String ID_sala = "";
		String possibilitaOrario = "";
		
		for (int i = 0; i < listaFilm.size(); i++) {
			if (listaFilm.get(i).getTitolo() == choiceTitolo.getValue().toString()) {
				ID_film = listaFilm.get(i).getID_film();
				break;
			}
		}
		
		String s= choiceSala.getValue().toString();
		
		for (int i = 0; i < listaSale.size(); i++) {
			if (listaSale.get(i).getN_sala() == choiceSala.getValue().toString()) {
				ID_sala = listaSale.get(i).getID_sala();
				break;
			}
		}
		
		for (int i = 0; i < listaFascieOrarie.size(); i++) {
			if (listaFascieOrarie.get(i).getPossibilita() == choiceFasciaOraria.getValue().toString()) {
				possibilitaOrario = listaFascieOrarie.get(i).getPossibilita();
				break;
			}
		}
		
		String dataDaString = dataA.getValue().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
		String dataAString = dataDa.getValue().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
		
	
		Proiezione proiezione = new Proiezione(
				ID_film,
				dataAString,
				dataDaString,
				ID_sala,
				possibilitaOrario
				);
		
		Alert alert;
		

		if (MySQLConnection.insertProiezione(proiezione)) {
			alert = new Alert(AlertType.INFORMATION, "Proiezione aggiunta con successo");
		} else {
			alert = new Alert(AlertType.ERROR, "C'è stato un problema nell'aggiunta della proiezione, controlla i dati");
		}
		
		alert.showAndWait();
		Stage stage = (Stage) choiceTitolo.getScene().getWindow();
	    stage.close();
	}
}
