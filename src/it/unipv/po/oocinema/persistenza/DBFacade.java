package it.unipv.po.oocinema.persistenza;

import java.sql.SQLException;

import it.unipv.po.oocinema.model.acquirenti.Acquirente;
import it.unipv.po.oocinema.persistenza.classiDAO.AcquirenteDAO;
import it.unipv.po.oocinema.persistenza.classiDAO.FilmDAO;
import it.unipv.po.oocinema.persistenza.interfaccieDAO.IAcquirenteDAO;
import it.unipv.po.oocinema.persistenza.interfaccieDAO.IFilmDAO;

public class DBFacade {
	IAcquirenteDAO iAcquirenteDAO;
	IFilmDAO iFilmDAO;

	public DBFacade() {
		iAcquirenteDAO = new AcquirenteDAO();
		iFilmDAO = new FilmDAO();
	}
	

	public boolean login(Acquirente inputAcq) {
		try {
			return iAcquirenteDAO.login(inputAcq);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
}
