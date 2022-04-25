package it.unipv.po.oocinema.persistenza.interfaccieDAO;

import java.sql.SQLException;
import java.util.ArrayList;

import it.unipv.po.oocinema.model.prenotazione.Prenotazione;
import it.unipv.po.oocinema.model.acquirenti.Acquirente;


public interface IPrenotazioneDAO {
	public ArrayList<Prenotazione> getPrenotazioniFutureByCliente(Acquirente inputAcquirente) throws SQLException;
	public void aggiungiPrenotazione(Prenotazione inputPrenotazione) throws SQLException;
	void occupaPosti(Prenotazione inputPrenotazione) throws SQLException;
}
