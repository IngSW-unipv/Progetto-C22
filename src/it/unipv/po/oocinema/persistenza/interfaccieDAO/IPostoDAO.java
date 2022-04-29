package it.unipv.po.oocinema.persistenza.interfaccieDAO;

import java.sql.SQLException;
import java.util.ArrayList;

import it.unipv.po.oocinema.model.cinema.Posto;
import it.unipv.po.oocinema.model.cinema.Proiezione;
import it.unipv.po.oocinema.model.prenotazione.Prenotazione;

/**
 * Interfaccia che contiene i metodi per gestire la persistenza di dati 
 * dei posti occupati per una specifica prenotazione. 
 * 
 * @author GoF
 */
public interface IPostoDAO {
	public ArrayList<Posto> getTuttiPostiByPrenotazione(Prenotazione inputPrenotazione) throws SQLException;
	//public ArrayList<Posto> getRigheLibere(Proiezione inputProiezione) throws SQLException;
	public ArrayList<Integer> getPostiOccupatiByRiga(Proiezione inputProiezione, Posto inputPosto) throws SQLException;
}
