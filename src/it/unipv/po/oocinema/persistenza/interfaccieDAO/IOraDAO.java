package it.unipv.po.oocinema.persistenza.interfaccieDAO;

import java.sql.SQLException;
import java.util.ArrayList;

import it.unipv.po.oocinema.model.cinema.Film;

public interface IOraDAO {
	public ArrayList<String> getTutteOre() throws SQLException;
}
