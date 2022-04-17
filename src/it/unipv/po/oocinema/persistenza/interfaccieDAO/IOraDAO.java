package it.unipv.po.oocinema.persistenza.interfaccieDAO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface IOraDAO {
	public ArrayList<String> getTutteOre() throws SQLException;
}
