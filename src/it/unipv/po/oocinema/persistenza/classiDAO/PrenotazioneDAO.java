package it.unipv.po.oocinema.persistenza.classiDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;

import it.unipv.po.oocinema.model.acquirenti.Acquirente;
import it.unipv.po.oocinema.model.cinema.Film;
import it.unipv.po.oocinema.model.cinema.Posto;
import it.unipv.po.oocinema.model.cinema.Proiezione;
import it.unipv.po.oocinema.model.prenotazione.Prenotazione;
import it.unipv.po.oocinema.persistenza.interfaccieDAO.IPrenotazioneDAO;

import it.unipv.po.oocinema.persistenza.MySQLConnectionFactory;

/**
 * Classe che implementa l'interfaccia IPrenotazioneDAO e quindi contiene i metodi per
 * gestire la persistenza di dati delle prenotazioni completate. 
 * 
 * @author GoF
 */
public class PrenotazioneDAO implements IPrenotazioneDAO {
	
	/**
	 * Connessione al database.
	 */
	private Connection conn;

	/**
	 * Costruttore.
	 */
	public PrenotazioneDAO() {
		super();
	}
	
	/**
	 * Metodo che restituisce tutte le prenotazioni future effettuate da un cliente. 
	 * 
	 * @param inputAcquirente contiene l'identificativo del cliente di cui si vuole 
	 * 						  conoscere le prenotazioni future.
	 * @return lista delle prenotazioni future.
	 * @throws SQLException fornisce informazioni su un errore di accesso al database o 
	 * 						altri errori di relazione con quest'ultimo.
	 */
	@Override
	public ArrayList<Prenotazione> getPrenotazioniFutureByCliente(Acquirente inputAcquirente) throws SQLException{
		conn = MySQLConnectionFactory.connect(conn);
		String query = "SELECT PRE.id as id_pre, PRO.id as id_pro, PRE.data_acquisto \r\n"
				+ "FROM prenotazione PRE join proiezione PRO on PRE.proiezione_id = PRO.id\r\n"
				+ "where PRE.acquirente_user= ?  and giorno > curdate();";
		PreparedStatement st1 = conn.prepareStatement(query);
		st1.setString(1, inputAcquirente.getUser());
		ResultSet result=st1.executeQuery();
		ArrayList<Prenotazione> prenotazioni = new ArrayList<Prenotazione>();
		while (result.next()) {
			Proiezione proiezione = new Proiezione();
			proiezione.setId(result.getInt("id_pro"));
			ProiezioneDAO proiezioneDAO= new ProiezioneDAO();
			proiezione=proiezioneDAO.getProiezioneById(proiezione);
			try {
				prenotazioni.add(new Prenotazione(result.getInt("id_pre"), result.getString("data_acquisto"), inputAcquirente, proiezione));
			} catch (SQLException e) { 
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		MySQLConnectionFactory.closeConnection(conn);
		return prenotazioni;
	}
	
	/**
	 * Metodo usato per rendere persistente una nuova prenotazione.
	 * 
	 * @param inputPrenotazione oggetto che contiene gli attributi della prenotazione
	 * 							da aggiungere.
	 * @throws SQLException fornisce informazioni su un errore di accesso al database o 
	 * 						altri errori di relazione con quest'ultimo.
	 */
	@Override
	public void aggiungiPrenotazione(Prenotazione inputPrenotazione) throws SQLException {
		conn = MySQLConnectionFactory.connect(conn);
		String query = "INSERT INTO prenotazione VALUES(?,?,?,?,?)";
		PreparedStatement st1 = conn.prepareStatement(query);
		st1.setInt(1, inputPrenotazione.getId());
		st1.setString(2, LocalDate.now().toString());
		st1.setInt(3, inputPrenotazione.getNumPosti());
		st1.setInt(4, inputPrenotazione.getProiezione().getId()); 
		st1.setString(5, inputPrenotazione.getAcquirente().getUser());
		st1.executeUpdate();
		MySQLConnectionFactory.closeConnection(conn);
	}
	
	/**
	 * Metodo usato per registrare tutti i posti specifici occupati da una prenotazione.
	 * 
	 * @param inputPrenotazione oggetto che contiene gli attributi della prenotazione.
	 * @throws SQLException fornisce informazioni su un errore di accesso al database o 
	 * 						altri errori di relazione con quest'ultimo.
	 */
	@Override
	public void occupaPosti(Prenotazione inputPrenotazione) throws SQLException {
		conn = MySQLConnectionFactory.connect(conn);

		for (Posto p : inputPrenotazione.getPosti()) {
			String query="INSERT INTO posto VALUES(?,?,?)";
			PreparedStatement st1 = conn.prepareStatement(query);
			st1.setInt(1, p.getRiga());
			st1.setInt(2, p.getColonna());
			st1.setInt(3, inputPrenotazione.getId());
			st1.executeUpdate();
		}
		MySQLConnectionFactory.closeConnection(conn);
	}

	@Override
	public ArrayList<Posto> getPostiByPrenotazione(Prenotazione inputPrenotazione) throws SQLException{
		conn = MySQLConnectionFactory.connect(conn);
		String query = "select * from posto where prenotazione_id = ?;";
		PreparedStatement st1 = conn.prepareStatement(query);
		st1.setInt(1, inputPrenotazione.getId());
		ResultSet result=st1.executeQuery();
		ArrayList<Posto> p = new ArrayList<Posto>();
		while (result.next()) {
			p.add(new Posto(result.getInt("riga"),result.getInt("colonna")));
		}
		
		MySQLConnectionFactory.closeConnection(conn);
		return p;
	}

	
}
