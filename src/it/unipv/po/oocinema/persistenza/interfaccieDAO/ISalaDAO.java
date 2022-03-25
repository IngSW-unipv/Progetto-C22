package it.unipv.po.oocinema.persistenza.interfaccieDAO;

import java.sql.SQLException;
import java.util.ArrayList;

import it.unipv.po.oocinema.model.cinema.Sala;


public interface ISalaDAO {
	public Sala getSalaById(Sala salaInput) throws SQLException;

	public ArrayList<Sala> getAllSale() throws SQLException;
}
