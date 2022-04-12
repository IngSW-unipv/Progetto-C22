package it.unipv.po.oocinema.persistenza.interfaccieDAO;

import java.sql.SQLException;
import java.util.ArrayList;

import it.unipv.po.oocinema.model.cinema.Posto;
import it.unipv.po.oocinema.model.cinema.Proiezione;

public interface IPostoDAO {
	public ArrayList<Posto> getTuttiPostiByPrenotazione(Proiezione inputPrenotazione) throws SQLException;
}
