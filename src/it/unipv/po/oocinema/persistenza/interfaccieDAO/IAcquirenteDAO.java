package it.unipv.po.oocinema.persistenza.interfaccieDAO;

import java.sql.SQLException;
import java.util.ArrayList;

import it.unipv.po.oocinema.model.acquirenti.Acquirente;
import it.unipv.po.oocinema.model.acquirenti.Cassa;

public interface IAcquirenteDAO {
	
	public boolean login(Acquirente inputAcq) throws SQLException;
	public void aggiungiCassa(Cassa inputCassa) throws SQLException;
	public void rimuoviCassa(Cassa inputCassa) throws SQLException;
	public ArrayList<Cassa> getTutteCasse() throws SQLException;
}
