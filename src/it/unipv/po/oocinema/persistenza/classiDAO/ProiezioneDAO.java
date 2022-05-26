package it.unipv.po.oocinema.persistenza.classiDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import it.unipv.po.oocinema.persistenza.MySQLConnectionFactory;
import it.unipv.po.oocinema.persistenza.interfaccieDAO.IProiezioneDAO;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import it.unipv.po.oocinema.model.cinema.Film;
import it.unipv.po.oocinema.model.cinema.Proiezione;
import it.unipv.po.oocinema.model.cinema.Sala;

/**
 * Classe che implementa l'interfaccia IProiezioneDAO e quindi contiene i metodi per
 * gestire la persistenza di dati delle proiezioni.
 * 
 * @author GoF
 */
public class ProiezioneDAO implements IProiezioneDAO{
	
	/**
	 * Connessione al database.
	 */
	private Connection conn;

	/**
	 * Costruttore.
	 */	
	public ProiezioneDAO() {
		super();
	}
	
	/**
	 * Metodo che restituisce tutti gli attributi di una proiezione dato il suo identificativo. 
	 * 
	 * @param inputProiezione oggetto che contiene l'identificativo della proiezione da 
	 * 					      recuperare.
	 * @return Oggetto Proiezione con tutti i suoi attributi il cui identificativo è uguale
	 * 		   a quello fornito in input.
	 * @throws SQLException fornisce informazioni su un errore di accesso al database o 
	 * 						altri errori di relazione con quest'ultimo.
	 */
	@Override
	public Proiezione getProiezioneById(Proiezione inputProiezione) throws SQLException {
		conn = MySQLConnectionFactory.connect(conn);
		PreparedStatement st1;
		ResultSet result;
		String query = "SELECT * FROM proiezione WHERE id = ?;";
		st1 = conn.prepareStatement(query);
		st1.setString(1, ""+inputProiezione.getId());
		result = st1.executeQuery();
		Film f=new Film();
		Proiezione proiezione = null;
		while(result.next()) {
			f.setId(result.getInt("film_id"));
			FilmDAO filmDAO= new FilmDAO();
			f=filmDAO.getFilmbyId(f);
			Sala s=new Sala();
			s.setId(result.getInt("sala_id"));
			SalaDAO salaDAO= new SalaDAO();
			s=salaDAO.getSalaById(s);
			proiezione = new Proiezione( inputProiezione.getId() ,f, result.getString("giorno"), s
											,result.getDouble("prezzo"), result.getString("orario"));
			
		}
		MySQLConnectionFactory.closeConnection(conn);
		return proiezione;
	}
	
	/**
	 * Metodo usato dall'amministratore per registrare una nuova proiezione.
	 * 
	 * @param inputProiezione oggetto che contiene tutti gli attributi che andranno 
	 * 					 	  registrati.
	 * @throws SQLException fornisce informazioni su un errore di accesso al database o 
	 * 						altri errori di relazione con quest'ultimo.
	 */
	@Override
	public boolean aggiungiProiezione(Proiezione inputProiezione) throws SQLException{
		conn = MySQLConnectionFactory.connect(conn);
		
		String query_check = "select \r\n"
				+ "case \r\n"
				+ "	when giorno != ? then 0\r\n"
				+ "    when sala_id != ? then 0\r\n"
				+ "    when orario != ? then 0\r\n"
				+ "    else 1\r\n"
				+ "end as c\r\n"
				+ "from proiezione";
		
		PreparedStatement st = conn.prepareStatement(query_check);
		st.setString(1, inputProiezione.getGiorno());
		st.setInt(2, inputProiezione.getSala().getId());
		st.setString(3,inputProiezione.getOrario());
		ResultSet result = st.executeQuery();
		boolean c = false;
		while(result.next()) {
			if (result.getInt("c")==1) {
				c = true;
				break;
			}
		}
		
		if(!c) {
			String query = "INSERT INTO proiezione VALUES(?,?,?,?,?,?)";
			PreparedStatement st1 = conn.prepareStatement(query);
			st1.setInt(1, inputProiezione.getId());
			st1.setDouble(2, inputProiezione.getPrezzo());
			st1.setInt(3, inputProiezione.getFilm().getId());
			st1.setInt(4, inputProiezione.getSala().getId());
			st1.setString(5, inputProiezione.getGiorno());
			st1.setString(6, inputProiezione.getOrario());
			st1.executeUpdate();
			
		}else {
			Alert alert = new Alert(AlertType.WARNING, "Attenzione ai dati inseriti");
	    	alert.showAndWait(); 
		}
		MySQLConnectionFactory.closeConnection(conn);
		return c;
	}
	
	/**
	 * Metodo usato dall'amministratore per rimuovere una proiezione registrata.
	 * 
	 * @param inputProiezione oggetto che contiene l'identificativo della proiezione da 
	 * 					      cancellare.
	 * @throws SQLException fornisce informazioni su un errore di accesso al database o 
	 * 						altri errori di relazione con quest'ultimo.
	 */
	@Override
	public void rimuoviProiezione(Proiezione inputProiezione) throws SQLException {
		conn = MySQLConnectionFactory.connect(conn);
		String query = "DELETE FROM proiezione where id= ?";
		PreparedStatement st1 = conn.prepareStatement(query);
		st1.setInt(1, inputProiezione.getId());
		st1.executeUpdate();
		MySQLConnectionFactory.closeConnection(conn);
	}
	
	/**
	 * Metodo che restituisce tutte le proiezioni future programmate. 
	 * 
	 * @return lista delle proiezioni future programmate con tutti i loro 
	 * 		   attributi.
	 * @throws SQLException fornisce informazioni su un errore di accesso al database o 
	 * 						altri errori di relazione con quest'ultimo.
	 */
	@Override
	public ArrayList<Proiezione> getTutteProiezioniFuture() throws SQLException {
		conn = MySQLConnectionFactory.connect(conn);
		String query = "SELECT * FROM proiezione where giorno>curdate();";
		PreparedStatement st1 = conn.prepareStatement(query);
		ResultSet result=st1.executeQuery();
		ArrayList<Proiezione> p = new ArrayList<Proiezione>();
		while (result.next()) {
			Film f=new Film();
			f.setId(result.getInt("film_id")); 
			FilmDAO filmDAO= new FilmDAO();
			f=filmDAO.getFilmbyId(f);
			Sala s=new Sala();
			s.setId(result.getInt("sala_id"));
			SalaDAO salaDAO= new SalaDAO();
			s=salaDAO.getSalaById(s);
			p.add(new Proiezione(result.getInt("id"),f, result.getString("giorno"), s, 
					result.getDouble("prezzo"), result.getString("orario")));
		}
		MySQLConnectionFactory.closeConnection(conn);
		return p;
	}
	
	/**
	 * Metodo che restituisce la proiezione selezionando film, data e ora;
	 * non è possibile programmare lo stesso film contemporaneamente in due sale 
	 * diverse. 
	 * 
	 * @param inputProiezione oggetto che contiene il film, la data e
	 * 						  l'ora scelti. 
	 * @return oggetto Proiezione i cui attributi film, ora e data corrispondono a  
	 * 		   quelli da input.
	 * @throws SQLException fornisce informazioni su un errore di accesso al database o 
	 * 						altri errori di relazione con quest'ultimo.
	 */
	@Override
	public Proiezione getProiezioneByFilmGiornoOra(Proiezione inputProiezione) throws SQLException{
		conn = MySQLConnectionFactory.connect(conn);
		PreparedStatement st1;
		Proiezione proiezione;
		ResultSet result;
		String query = "SELECT * FROM proiezione WHERE film_id = ? and giorno = ? and orario = ?;";
		st1 = conn.prepareStatement(query);
		st1.setInt(1, inputProiezione.getFilm().getId());
		st1.setString(2, inputProiezione.getGiorno());
		st1.setString(3, inputProiezione.getOrario());
		result = st1.executeQuery();
		Sala s=new Sala();
		if(result.next()) { 
			s.setId(result.getInt("sala_id"));
			SalaDAO salaDAO= new SalaDAO();
			s=salaDAO.getSalaById(s);
			proiezione = new Proiezione( result.getInt("id") ,inputProiezione.getFilm() , inputProiezione.getGiorno(), s
								,result.getDouble("prezzo"), inputProiezione.getOrario());
		}else {proiezione = null;}
		MySQLConnectionFactory.closeConnection(conn);
		return proiezione;
	}
	
	/**
	 * Metodo che restituisce tutti i giorni futuri in cui un film sarà 
	 * priettato.
	 * 
	 * @param inputFilm oggetto che contiene il film per il quale si vuole conoscere
	 * 					i gironi di proiezione. 
	 * @return lista di tipo String contenente tutti i giorni in cui il film scelto viene
	 * 		   proiettato. 
	 * @throws SQLException fornisce informazioni su un errore di accesso al database o 
	 * 						altri errori di relazione con quest'ultimo.
	 */
	@Override
	public ArrayList<String> getGiorniByFilm(Film inputFilm) throws SQLException{
		FilmDAO filmDAO= new FilmDAO(); //ho solo il titolo del film in inputFilm
		inputFilm=filmDAO.getFilmbyTitolo(inputFilm);
		conn = MySQLConnectionFactory.connect(conn);
		String query = "SELECT distinct giorno FROM proiezione where film_id=?;";
		PreparedStatement st1 = conn.prepareStatement(query);
		st1.setInt(1, inputFilm.getId());
		ResultSet result=st1.executeQuery();
		ArrayList<String> giorni = new ArrayList<String>();
		while (result.next()) {
			giorni.add(result.getString("giorno"));
		}
		MySQLConnectionFactory.closeConnection(conn);
		return giorni;
	}
	
	/**
	 * Metodo che restituisce tutte le ore in cui un film sarà priettato scelto un giorno
	 * specifico.
	 * 
	 * @param inputProiezione oggetto che contiene il film e il giorno per cui si vuole conoscere
	 * 						  gli orari di proiezione. 
	 * @return lista di tipo String contenente tutte le ore in cui il film scelto viene
	 * 		   proiettato nel giorno selezionato. 
	 * @throws SQLException fornisce informazioni su un errore di accesso al database o 
	 * 						altri errori di relazione con quest'ultimo.
	 */
	@Override
	public ArrayList<String> getOreByProiezione(Proiezione inputProiezione) throws SQLException{
		conn = MySQLConnectionFactory.connect(conn);
		String query = "SELECT orario FROM proiezione where film_id=? and giorno=?;";
		PreparedStatement st1 = conn.prepareStatement(query);
		st1.setInt(1, inputProiezione.getFilm().getId());
		st1.setString(2, inputProiezione.getGiorno());
		ResultSet result=st1.executeQuery();
		ArrayList<String> ore = new ArrayList<String>();
		while (result.next()) {
			ore.add(result.getString("orario"));
		}
		MySQLConnectionFactory.closeConnection(conn);
		return ore;
	}
	
	/**
	 * Metodo che restituisce il numero di proiezioni programmate dato l'identificativo di 
	 * un film. 
	 * 
	 * @param inputFilm oggetto che contiene l'identificativo del film di cui contare le 
	 * 		  proiezioni.
	 * @return Numero di proiezioni programmate per il film fornito in input.
	 * @throws SQLException fornisce informazioni su un errore di accesso al database o 
	 * 						altri errori di relazione con quest'ultimo.
	 */
	@Override
	public int getNumProiezioniByFilm(Film inputFilm) throws SQLException { //conta anche quelle passate
		conn = MySQLConnectionFactory.connect(conn);
		int r;
		String query = "SELECT count(*) as NUM FROM proiezione GROUP BY film_id having film_id = ?";
		PreparedStatement st1 = conn.prepareStatement(query);
		st1.setInt(1, inputFilm.getId());
		ResultSet result = st1.executeQuery();
		if(result.next())
			r = result.getInt("NUM");
		else r = 0;
		MySQLConnectionFactory.closeConnection(conn);
		return r;
	}	
	
	
}
