package it.unipv.po.oocinema.persistenza.interfaccieDAO;

import java.sql.SQLException;

import it.unipv.po.oocinema.model.acquirenti.Acquirente;


public interface IAcquirenteDAO {
	
	public boolean login(Acquirente inputAcq) throws SQLException;
}
