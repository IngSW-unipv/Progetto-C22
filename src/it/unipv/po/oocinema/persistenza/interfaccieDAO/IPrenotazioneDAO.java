package it.unipv.po.oocinema.persistenza.interfaccieDAO;

import java.sql.SQLException;
import java.util.ArrayList;

import it.unipv.po.oocinema.model.prenotazione.Prenotazione;
import it.unipv.po.oocinema.model.cinema.Proiezione;

public interface IPrenotazioneDAO {
	public ArrayList<Prenotazione> getPrenotazioneByProiezione(Proiezione inputProiezione) throws SQLException;

}
