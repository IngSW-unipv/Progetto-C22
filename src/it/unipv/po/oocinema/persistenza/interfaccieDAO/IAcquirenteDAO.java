package it.unipv.po.oocinema.persistenza.interfaccieDAO;

import java.sql.SQLException;
import java.util.ArrayList;

import it.unipv.po.oocinema.model.acquirenti.Acquirente;
import it.unipv.po.oocinema.model.acquirenti.Cassa;
import it.unipv.po.oocinema.model.acquirenti.Cliente;

public interface IAcquirenteDAO {
	
	public boolean login(Acquirente inputAcq) throws SQLException;
	public void registrazione(Cliente inputCliente) throws SQLException;
	public void aggiungiCassa(Cassa inputCassa) throws SQLException;
	public void rimuoviCassa(Cassa inputCassa) throws SQLException;
	public ArrayList<Cassa> getTutteCasse() throws SQLException;
	public int getTipoByUser(Acquirente inputAcquirente) throws SQLException;
	public boolean controllaUser(Acquirente inputAcquirente) throws SQLException;
}
