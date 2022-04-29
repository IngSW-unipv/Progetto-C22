package it.unipv.po.oocinema.persistenza.classiDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import it.unipv.po.oocinema.model.cinema.Posto;
import it.unipv.po.oocinema.model.cinema.Proiezione;
import it.unipv.po.oocinema.model.prenotazione.Prenotazione;
import it.unipv.po.oocinema.persistenza.MySQLConnectionFactory;
import it.unipv.po.oocinema.persistenza.interfaccieDAO.IPostoDAO;

/**
 * Classe che implementa l'interfaccia IPostoDAO e quindi contiene i metodi per
 * gestire la persistenza di dati dei posti occupati per una specifica prenotazione. 
 * 
 * @author GoF
 */
public class PostoDAO implements IPostoDAO {
	
	/**
	 * Connessione al database.
	 */
	private Connection conn;

	/**
	 * Costruttore.
	 */
	public PostoDAO() {
		super();
	}
	
	/**
	 * Metodo che restituisce tutti i singoli posti prenotati attraverso una 
	 * prenotazione effettuata da un acquirente. 
	 * 
	 * @return lista dei posti prenotati.
	 * @throws SQLException fornisce informazioni su un errore di accesso al database o 
	 * 						altri errori di relazione con quest'ultimo.
	 */
	@Override
	public ArrayList<Posto> getTuttiPostiByPrenotazione(Prenotazione inputPrenotazione) throws SQLException{
		conn = MySQLConnectionFactory.connect(conn);
		String query = "SELECT * FROM posto where prenotazione_id=? ;";
		PreparedStatement st1 = conn.prepareStatement(query);
		st1.setString(1, ""+inputPrenotazione.getId());
		ResultSet result=st1.executeQuery();
		ArrayList<Posto> posti = new ArrayList<Posto>();
		while (result.next()) {
			posti.add(new Posto(result.getInt("riga"),result.getInt("colonna"), inputPrenotazione));
		}
		MySQLConnectionFactory.closeConnection(conn);
		return posti;
	}
	
	/*
	 * @Override public ArrayList<Posto> getRigheLibere(Proiezione inputProiezione)
	 * throws SQLException{ conn = MySQLConnectionFactory.connect(conn); //non va
	 * bene!! String query = "select count(*) as NUM, riga\r\n" +
	 * "from posto A join prenotazione B on A.prenotazione_id=B.id\r\n" +
	 * " where proiezione_id = ?\r\n" + "group by riga\r\n;"; PreparedStatement st1
	 * = conn.prepareStatement(query); st1.setInt(1, inputProiezione.getId());
	 * ResultSet result=st1.executeQuery(); ArrayList<Posto> posti = new
	 * ArrayList<Posto>(); while (result.next()) {
	 * if(result.getInt("NUM")<inputProiezione.getSala().getColonne()) posti.add(new
	 * Posto(result.getInt("riga"))); }
	 * MySQLConnectionFactory.closeConnection(conn); return posti; }
	 */
	
	/**
	 * Metodo che restituisce tutti i posti liberi di una specifica proiezione selezionata una 
	 * riga. 
	 * 
	 * @return lista dei posti liberi scelta una prenotazione e una riga.
	 * @throws SQLException fornisce informazioni su un errore di accesso al database o 
	 * 						altri errori di relazione con quest'ultimo.
	 */
	@Override
	public ArrayList<Integer> getPostiLiberiByRiga(Proiezione inputProiezione, Posto inputPosto) throws SQLException{
		conn = MySQLConnectionFactory.connect(conn);
		String query ="select colonna from posto A join prenotazione B on A.prenotazione_id=B.id\r\n"
				     + "where B.proiezione_id=? and riga=?;";
		PreparedStatement st1 = conn.prepareStatement(query);
		st1.setInt(1, inputProiezione.getId());
		st1.setInt(2, inputPosto.getRiga());
		ResultSet result=st1.executeQuery(); 
		ArrayList<Integer> colonne = new ArrayList<Integer>();
		while (result.next()) {
			colonne.add(result.getInt("colonna"));
		}
		MySQLConnectionFactory.closeConnection(conn);
		return colonne;
	}
}
