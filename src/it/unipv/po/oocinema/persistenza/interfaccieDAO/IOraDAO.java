package it.unipv.po.oocinema.persistenza.interfaccieDAO;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Interfaccia che contiene un metodo usato per recuperare le 
 * fascie orarie predefinite. 
 * 
 * @author GoF
 */
public interface IOraDAO {
	public ArrayList<String> getTutteOre() throws SQLException;
}
