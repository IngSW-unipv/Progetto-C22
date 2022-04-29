package it.unipv.po.oocinema.controllers.client;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;
import com.google.zxing.WriterException;

import it.unipv.po.oocinema.controllers.LoginController;
import it.unipv.po.oocinema.controllers.TicketHandler;
import it.unipv.po.oocinema.model.cinema.Film;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Window;

public class PrenotazioneController extends ClientMenuController implements Initializable{

	 @FXML
	 private Button aggiungi;
	 
	 @FXML
	 private ComboBox<Character> filaCombo;

	 @FXML
	 private Label lista = new Label();


	 @FXML
	 private ImageView locandinaFilmSel;

	 @FXML
	 private ComboBox<Integer> postoCombo;

	 @FXML
	 private Button prenota;

	 @FXML
	 private Label titoloFilmSel;
    
	 private DBFacade facade = new DBFacade();
	 
	 private Prenotazione prenotazione;


    @FXML
    void prenota(MouseEvent event) {
    	
    	prenotazione.setProiezione(SchedaController.getProiezione());
    	prenotazione.setAcquirente(LoginController.getCliente());
    	prenotazione.setDataAcquisto(LocalDate.now().toString());
    	if (prenotazione.pagamento()) {
    		prenotazione.acquista();
	    	try {
				facade.aggiungiPrenotazione(prenotazione);
				TicketHandler ticket = new TicketHandler(prenotazione);
			} catch (SQLException | WriterException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
	
        prenotazione = new Prenotazione();
		
	
		Film f;
		try {
			f = facade.getFilmbyTitolo(new Film(CLIController.getTitoloFilmSel()));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			f = null;
			e.printStackTrace();
		}
		
		titoloFilmSel.setText(f.getTitolo());
		Image image = new Image(getClass().getResourceAsStream(f.getCoverPath()));
	    locandinaFilmSel.setImage(image);
		
	}
	
	public void aggiorna() {
		setLabelText();
		initializeRighe();	
		postoCombo.setValue(null);
	}
	
	public void initializeRighe() {
	
		ArrayList<Character> righe = new ArrayList<Character>();
	
		for(int i = 0; i <SchedaController.getProiezione().getSala().getRighe(); i++) {
			
			righe.add(i, (char) (i + 'A'));
		}
		ObservableList<Character> obListFila = FXCollections.observableList(righe);
        
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
		ArrayList<Integer> colonneOccupate = new ArrayList<Integer>();
		Posto p = new Posto(filaCombo.getValue()-'A');
		
		try {
			colonneOccupate = facade.getPostiLiberiByRiga(SchedaController.getProiezione(), p);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for(int i = 0; i<SchedaController.getProiezione().getSala().getColonne();i++) {
			colonne.add(i+1);
		}
		
		for(int i = 0; i< colonneOccupate.size();i++) {
			colonne.remove(colonneOccupate.get(i));
		}
		
		ObservableList<Integer> obListColonna = FXCollections.observableList(colonne);
        
		postoCombo.getItems().clear();
        postoCombo.setItems(obListColonna);
    }
	
	 @FXML
	 void aggiungi(MouseEvent event) {
		 if(filaCombo.getValue() != null && postoCombo.getValue() != null) {
			 
			 prenotazione.aggiungiPosto((int)(filaCombo.getValue()-'A'),postoCombo.getValue());
			 initialize(null, null);
		 } else {
			 
			 // FARE ALERT
		 }
	 }
	 
	 @FXML
	 void rimuovi(MouseEvent event) {
		 if(filaCombo.getValue() != null && postoCombo.getValue() != null) {
			 for(int i = 0 ; i <prenotazione.getNumPosti(); i++) {
				 if((prenotazione.getPosti().get(i).getColonna() == postoCombo.getValue()) && (prenotazione.getPosti().get(i).getRiga()+'A' == filaCombo.getValue())) {
					 prenotazione.rimuoviPosto(i);
				 	initialize(null,null);
				 }
			 }
		 } else {
			 
			 // FARE ALERT
		 }
	 }
	
	 public void setLabelText() {
		 lista.setText("");
		 for(int i = 0; i < prenotazione.getNumPosti(); i++) {
			 lista.setText(lista.getText()+"Fila: "+ (char)(prenotazione.getPosti().get(i).getRiga()+'A')+ " - Posto: "+ prenotazione.getPosti().get(i).getColonna()+"\n");
		 }
		 
		 
	 }
	
}
