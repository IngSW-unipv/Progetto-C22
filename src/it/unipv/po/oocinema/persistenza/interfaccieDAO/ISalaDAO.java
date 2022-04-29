package it.unipv.po.oocinema.persistenza.interfaccieDAO;

import java.sql.SQLException;
import java.util.ArrayList;

import it.unipv.po.oocinema.model.cinema.Sala;

/**
 * Interfaccia che contiene i metodi per gestire la persistenza di dati
 * delle sale. 
 * 
 * @author GoF
 */
public interface ISalaDAO {
	public ArrayList<Sala> getTutteSale() throws SQLException;
	public Sala getSalaById(Sala inputSala) throws SQLException;
}
