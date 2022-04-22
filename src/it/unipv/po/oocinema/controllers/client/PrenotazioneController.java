package it.unipv.po.oocinema.controllers.client;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.google.zxing.WriterException;

import it.unipv.po.oocinema.controllers.TicketHandler;
import it.unipv.po.oocinema.model.cinema.Posto;
import it.unipv.po.oocinema.model.prenotazione.Prenotazione;
import it.unipv.po.oocinema.persistenza.DBFacade;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
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
	 ArrayList<Posto> postiScelti = new ArrayList<Posto>();
    
	 private DBFacade facade = new DBFacade();


    @FXML
    void prenota(MouseEvent event) {
    	Prenotazione p = new Prenotazione();
    	p.setProiezione(SchedaController.getProiezione());
    	p.setPosti(postiScelti);
    	if (p.pagamento()) {
	    	try {
				facade.aggiungiPrenotazione(p);
				TicketHandler ticket = new TicketHandler(p);
			} catch (SQLException | WriterException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		titoloFilmSel.setText("aa");
		setLabelText();
		initializeRighe();	
	}
	
	public void initializeRighe() {
		posti.removeAll(posti);
		try {
			posti = facade.getRigheLibere(SchedaController.getProiezione());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
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
		ArrayList<Posto> c = new ArrayList<Posto>();
		int index = 0;
		for(int i = 0; i <posti.size(); i++) {
			if(filaCombo.getValue() == (char)posti.get(i).getRiga()+'A') {
				try {
					c = facade.getPostiLiberiByRiga(SchedaController.getProiezione(), posti.get(index));
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				colonne.set(index, c.get(index).getColonna());
				index++;
			}
		}
		ObservableList<Integer> obListFila = FXCollections.observableList(colonne);
        
		postoCombo.getItems().clear();
        postoCombo.setItems(obListFila);
    }
	
	 @FXML
	 void aggiungi(MouseEvent event) {
		 if(filaCombo.getValue() != null && postoCombo.getValue() != null) {
			 
			 postiScelti.add(new Posto((int)filaCombo.getValue()-'A',postoCombo.getValue()));
			 initialize(null, null);
		 } else {
			 
			 // FARE ALERT
		 }
	 }
	 
	 @FXML
	 void rimuovi(MouseEvent event) {
		 if(filaCombo.getValue() != null && postoCombo.getValue() != null) {
			 for(int i = 0 ; i < postiScelti.size(); i++) {
				 if((postiScelti.get(i).getColonna() == postoCombo.getValue()) && (postiScelti.get(i).getRiga()+'A' == filaCombo.getValue())) {
					 postiScelti.remove(i);
				 	initialize(null,null);
				 }
			 }
		 } else {
			 
			 // FARE ALERT
		 }
	 }
	
	 public void setLabelText() {
		 lista.setText("");
		 for(int i = 0; i < postiScelti.size(); i++) {
			 lista.setText(lista.getText()+"Fila: "+ (char)(postiScelti.get(i).getRiga()+'A')+ " - Posto: "+ postiScelti.get(i).getColonna()+"\n");
		 }
		 
		 
	 }
	public Window getWindow() {
    	return lista.getScene().getWindow();
    }
	
	
}
