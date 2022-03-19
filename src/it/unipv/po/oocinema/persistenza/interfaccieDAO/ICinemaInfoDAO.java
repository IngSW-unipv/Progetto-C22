package it.unipv.po.oocinema.persistenza.interfaccieDAO;

import java.sql.SQLException;

public interface ICinemaInfoDAO {
	
	public String getIndirizzo() throws SQLException;
	public String getTelefono() throws SQLException;
	public String getEmail() throws SQLException;
}
