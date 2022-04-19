package it.unipv.po.oocinema.controllers.client;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import it.unipv.po.oocinema.controllers.admin.WindowsHandler;
import it.unipv.po.oocinema.model.cinema.Film;
import it.unipv.po.oocinema.model.cinema.Posto;
import it.unipv.po.oocinema.model.cinema.Proiezione;
import it.unipv.po.oocinema.model.cinema.Sala;
import it.unipv.po.oocinema.model.prenotazione.Prenotazione;
import it.unipv.po.oocinema.persistenza.DBFacade;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.stage.Window;

public class PrenotazioneController extends MenuController implements Initializable{

	 @FXML
	 private Button aggiungi;

	 @FXML
	 private ComboBox<Character> filaCombo;

	 @FXML
	 private Label lista;

	 @FXML
	 private ImageView locandinaFilmSel;

	 @FXML
	 private ComboBox<Integer> postoCombo;

	 @FXML
	 private Button prenota;

	 @FXML
	 private Label titoloFilmSel;
    
	 ArrayList<Posto> posti = new ArrayList<Posto>();
    
	 private DBFacade facade = new DBFacade();


    @FXML
    void prenota(MouseEvent event) {
    	Prenotazione p = null; 
    	try {
			facade.aggiungiPrenotazione(p);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		titoloFilmSel.setText("aaa");
		initializeFila();	
		initializePosto();
	}
	
	public void initializeFila() {
		//facade.getTuttiPostiLiberi(SchedaController.getProiezione());
		posti.add(new Posto(1,2,null));
		posti.add(new Posto(1,1,null));
		ArrayList<Character> file = new ArrayList<Character>();
	
		for(int i = 0; i <posti.size(); i++) {
			
			file.add(i, (char) (posti.get(i).getRiga()+'A'));
		}
		ObservableList<Character> obListFila = FXCollections.observableList(file);
        
		filaCombo.getItems().clear();
        filaCombo.setItems(obListFila);
       
	}
	
	
	@FXML
    void scegliPosto(MouseEvent event) {
		if(filaCombo.getValue()!=null) {
			initializePosto();
			
		}
    }
		
    public void initializePosto() {
		
		
		ArrayList<Integer> colonne = new ArrayList<Integer>();
		
		for(int i = 0; i <posti.size(); i++) {
			//if(filaCombo.getValue().equals(posti.get(i).getRiga()+'A'))
				colonne.add(i, posti.get(i).getColonna());
		}
		ObservableList<Integer> obListFila = FXCollections.observableList(colonne);
        
		postoCombo.getItems().clear();
        postoCombo.setItems(obListFila);
    }
	
	 @FXML
	 void aggiungi(MouseEvent event) {
		 if(filaCombo.getValue() != null && postoCombo.getValue() != null) {
			 lista.setText(lista.getText()+"\n Fila: "+filaCombo.getValue()+ " - Posto: "+postoCombo.getValue());
			 rimuoviPosto();
			 initialize(null, null);
		 } else {
			 
			 // FARE ALERT
		 }
	 }
	 
	 @FXML
	 void rimuovi(MouseEvent event) {
		 
	 }
	
	 public void rimuoviPosto() {
		 for(int i = 0; i <posti.size(); i++) {
			 if((filaCombo.getValue().equals(posti.get(i).getRiga()+'A')) && (posti.get(i).getColonna() == postoCombo.getValue())) {
				 posti.remove(i);
			 }
		 }
	 }
	public Window getWindow() {
    	return lista.getScene().getWindow();
    }
	
	
}
