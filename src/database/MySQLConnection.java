package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import model.Cassa;
import model.FasciaOraria;
import model.Film;
import model.Proiezione;
import model.Sala;

/**
* Classe che espone diverse funzioni statiche che
* permettono di interfacciarsi al database
*/
public class MySQLConnection {
	
	/**
	* Funzione che testa il collegamento al database
	*/
  public static boolean connect() {
    Connection con = null;

    String url = "jdbc:mysql://localhost:3306/ooc?serverTimezone=Europe/Rome";
    String username = "root";
    String password = "root";

    try {
      Class.forName("com.mysql.cj.jdbc.Driver");
      con = DriverManager.getConnection(url, username, password);

      System.out.println("Connected!");
      return true;
      
    } catch (Exception ex) {
      return false;
    }
  }
  
  /**
	* Funzione che inserisce un film all'interno del database
	* @return true se l'inserimento è andato a buon fine, false altrimenti
	* @param film da inserire
	*/
  public static boolean insertFilm(Film film) {
	  String query = " insert into film (ID_film, Titolo, Descrizione, Genere, Durata, Regista, Cast, Costo, CoverPath, TrailerPath)"
		        + " values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	  
	  try {
		  Connection conn = getConnection();
		  
		  PreparedStatement preparedStmt = conn.prepareStatement(query);
	      preparedStmt.setString (1, film.getID_film());
	      preparedStmt.setString (2, film.getTitolo());
	      preparedStmt.setString (3, film.getDescrizione());
	      preparedStmt.setString (4, film.getGenere());
	      preparedStmt.setInt (5, film.getDurata());
	      preparedStmt.setString (6, film.getRegista());
	      preparedStmt.setString (7, film.getCast());
	      preparedStmt.setInt (8, film.getCosto());
	      preparedStmt.setString (9, film.getCoverPath());
	      preparedStmt.setString (10, film.getTrailerPath());
	      
	  
	      preparedStmt.execute();
	      
	      conn.close();
	      
	      return true;
	  } catch (Exception e) {
		  e.printStackTrace();
		  return false;
	  }
  }
  
  /**
	* Funzione che inserisce una sala all'interno del database
	* @return true se l'inserimento è andato a buon fine, false altrimenti
	* @param sala da inserire
	*/
  public static boolean insertSala(Sala sala) {
	  String query = " insert into sala (ID_sala, n_sala, capienza_max)"
		        + " values (?, ?, ?)";
	  
	  try {
		  Connection conn = getConnection();
		  
		  PreparedStatement preparedStmt = conn.prepareStatement(query);
	      preparedStmt.setString (1, sala.getID_sala());
	      preparedStmt.setString (2, sala.getN_sala());
	      preparedStmt.setInt (3, sala.getCapienza_max());
	     
	      preparedStmt.execute();
	      
	      conn.close();
	      
	      return true;
	  } catch (Exception e) {
		  e.printStackTrace();
		  return false;
	  }
  }
  
  /**
	* Funzione che inserisce una fascia oraria all'interno del database
	* @return true se l'inserimento è andato a buon fine, false altrimenti
	* @param fascia oraria da inserire
	*/
  public static boolean insertFasciaOraria(FasciaOraria fasciaOraria) {
	  String query = " insert into fascia_oraria (possibilita)"
		        + " values (?)";
	  
	  try {
		  Connection conn = getConnection();
		  
		  PreparedStatement preparedStmt = conn.prepareStatement(query);
	      preparedStmt.setString (1, fasciaOraria.getPossibilita());
	     
	      preparedStmt.execute();
	      
	      conn.close();
	      
	      return true;
	  } catch (Exception e) {
		  e.printStackTrace();
		  return false;
	  }
  }
  
  /**
	* Funzione che rimuove un film all'interno del database
	* @return true se la rimozione è andata a buon fine, false altrimenti
	* @param film da rimuovere
	*/
  public static boolean removeFilm(Film film) {
	  String query = "DELETE FROM film where ID_film = ?";
		  
	  try {
		  Connection conn = getConnection();
		  
		  PreparedStatement preparedStmt = conn.prepareStatement(query);
	      preparedStmt.setString (1, film.getID_film());
	      
	      preparedStmt.execute();
	      
	      conn.close();
	      
	      return true;
	  } catch (Exception e) {
		  e.printStackTrace();
		  return false;
	  }
  }
  
  /**
	* Funzione che rimuove una sala all'interno del database
	* @return true se la rimozione è andata a buon fine, false altrimenti
	* @param sala da rimuovere
	*/
  public static boolean removeSala(Sala sala) {
	  String query = "DELETE FROM sala where ID_sala = ?";
		  
	  try {
		  Connection conn = getConnection();
		  
		  PreparedStatement preparedStmt = conn.prepareStatement(query);
	      preparedStmt.setString (1, sala.getID_sala());
	      
	      preparedStmt.execute();
	      
	      conn.close();
	      
	      return true;
	  } catch (Exception e) {
		  e.printStackTrace();
		  return false;
	  }
  }
  
  /**
	* Funzione che rimuove una fascia oraria all'interno del database
	* @return true se la rimozione è andata a buon fine, false altrimenti
	* @param fascia oraria da rimuovere
	*/
  public static boolean removeFasciaOraria(FasciaOraria fasciaOraria) {
	  String query = "DELETE FROM fascia_oraria where possibilita = ?";
		  
	  try {
		  Connection conn = getConnection();
		  
		  PreparedStatement preparedStmt = conn.prepareStatement(query);
	      preparedStmt.setString (1, fasciaOraria.getPossibilita());
	      
	      preparedStmt.execute();
	      
	      conn.close();
	      
	      return true;
	  } catch (Exception e) {
		  e.printStackTrace();
		  return false;
	  }
  }
  
  
  /**
	* Funzione che ritorna tutti i film nel database
	* @return lista di Film presenti nel database
	*/
  public static List<Film> getAllFilms() {
    try {
      Connection con = getConnection();

      String query = "SELECT * FROM film";

      Statement st = con.createStatement();
      
      ResultSet rs = st.executeQuery(query);
      
      List<Film> listaFilm = new ArrayList<Film>();
      
      while (rs.next()) {
        Film film = new Film();
        
        film.setID_film(rs.getString("ID_film"));
        film.setCast(rs.getString("Cast"));
        film.setCosto(rs.getInt("Costo"));
        film.setCoverPath(rs.getString("CoverPath"));
        film.setDescrizione(rs.getString("Descrizione"));
        film.setDurata(rs.getInt("Durata"));
        film.setGenere(rs.getString("Genere"));
        film.setRegista(rs.getString("Regista"));
        film.setTitolo(rs.getString("Titolo"));
        film.setTrailerPath(rs.getString("TrailerPath"));
        
        listaFilm.add(film);
            
      }
      
      return listaFilm;

    } catch (Exception ex) {
    	Alert alert = new Alert(AlertType.ERROR, "Connessione al database fallita");
		alert.showAndWait();
        ex.printStackTrace();
        return new ArrayList<Film>();
    }
  }
  
  
  /**
	* Funzione che ritorna tutte le sale nel database
	* @return lista di Sale presenti nel database
	*/
  public static List<Sala> getAllSale() {
	    try {
	      Connection con = getConnection();

	      String query = "SELECT * FROM sala";

	      Statement st = con.createStatement();
	      
	      ResultSet rs = st.executeQuery(query);
	      
	      List<Sala> listaSala = new ArrayList<Sala>();
	      
	      while (rs.next()) {
	    	Sala sala = new Sala();
	        
	        sala.setID_sala(rs.getString("ID_sala"));
	        sala.setN_sala(rs.getString("n_sala"));
	        sala.setCapienza_max(rs.getInt("capienza_max"));

	        
	        listaSala.add(sala);
	            
	      }
	      
	      return listaSala;

	    } catch (Exception ex) {
	    	Alert alert = new Alert(AlertType.ERROR, "Connessione al database fallita");
			alert.showAndWait();
	        ex.printStackTrace();
	        return new ArrayList<Sala>();
	    }
	  }
  
  
  /**
	* Funzione che ritorna tutte le fascie orarie nel database
	* @return lista di FasciaOraria presenti nel database
	*/
  public static List<FasciaOraria> getAllFascieOrarie() {
	    try {
	      Connection con = getConnection();

	      String query = "SELECT * FROM fascia_oraria";

	      Statement st = con.createStatement();
	      
	      ResultSet rs = st.executeQuery(query);
	      
	      List<FasciaOraria> listaSala = new ArrayList<FasciaOraria>();
	      
	      while (rs.next()) {
	    	FasciaOraria fasciaOraria = new FasciaOraria();
	        
	    	fasciaOraria.setPossibilita(rs.getString("possibilita"));
	        
	        listaSala.add(fasciaOraria);
	            
	      }
	      
	      return listaSala;

	    } catch (Exception ex) {
	    	Alert alert = new Alert(AlertType.ERROR, "Connessione al database fallita");
			alert.showAndWait();
	        ex.printStackTrace();
	        return new ArrayList<FasciaOraria>();
	    }
	  }
  
  
  /**
	* Funzione che ritorna tutte le proiezioni nel database
	* @return lista di Proiezione presenti nel database
	*/
  public static List<Proiezione> getAllProiezioni() {
	    try {
	      Connection con = getConnection();

	      String query = "SELECT * FROM proiezione";

	      Statement st = con.createStatement();
	      
	      ResultSet rs = st.executeQuery(query);
	      
	      List<Proiezione> listaProiezioni = new ArrayList<Proiezione>();
	      
	      while (rs.next()) {
	    	Proiezione proiezione = new Proiezione();
	        
	    	proiezione.setID_film(rs.getString("ID_Film"));
	    	proiezione.setGiorno_from(rs.getString("Giorno_from"));
	    	proiezione.setGiorno_to(rs.getString("Giorno_to"));
	    	proiezione.setID_sala(rs.getString("ID_sala"));
	    	proiezione.setOrario(rs.getString("Orario"));
	        
	    	listaProiezioni.add(proiezione);
	            
	      }
	      
	      return listaProiezioni;

	    } catch (Exception ex) {
	    	Alert alert = new Alert(AlertType.ERROR, "Connessione al database fallita");
			alert.showAndWait();
	        ex.printStackTrace();
	        return new ArrayList<Proiezione>();
	    }
	  }
  
  
  /**
	* Funzione che inserisce una cassa all'interno del database
	* @return true se l'inserimento è andato a buon fine, false altrimenti
	* @param cassa da inserire
	*/
  public static boolean insertCassa(Cassa cassa) throws ClassNotFoundException {
	  String query = " insert into cassa (ID_cash, pw_cash)"
		        + " values (?, ?)";
	  
	  try {
		  Connection conn = getConnection();
		  
		  PreparedStatement preparedStmt = conn.prepareStatement(query);
	      preparedStmt.setInt (1, cassa.getID_cash());
	      preparedStmt.setString (2, cassa.getPw_cash());
	  
	      preparedStmt.execute();
	      
	      conn.close();
	      
	      return true;
	  } catch (Exception e) {
		  e.printStackTrace();
		  return false;
	  }
  }
  
  /**
	* Funzione che inserisce una proiezione all'interno del database
	* @return true se l'inserimento è andato a buon fine, false altrimenti
	* @param proiezione da inserire
	*/
  public static boolean insertProiezione(Proiezione proiezione) {
	  String query = " insert into proiezione (ID_Film, Giorno_to, Giorno_from, ID_sala, Orario)"
		        + " values (?, ?, ?, ?, ?)";
	  
	  try {
		  Connection conn = getConnection();
		  
		  PreparedStatement preparedStmt = conn.prepareStatement(query);
	      preparedStmt.setString(1, proiezione.getID_film());
	      preparedStmt.setString(2, proiezione.getGiorno_to());
	      preparedStmt.setString(3, proiezione.getGiorno_from());
	      preparedStmt.setString(4, proiezione.getID_sala());
	      preparedStmt.setString(5, proiezione.getOrario());
	  
	      preparedStmt.execute();
	      
	      conn.close();
	      
	      return true;
	  } catch (Exception e) {
		  //e.printStackTrace();
		  return false;
	  }
  }
  
  /**
	* Funzione che rimuove una cassa all'interno del database
	* @return true se la rimozione è andata a buon fine, false altrimenti
	* @param cassa da rimuovere
	*/
  public static boolean removeCassa(Cassa cassa) throws ClassNotFoundException {
	  String query = "DELETE FROM cassa where ID_cash = ?";
		  
	  try {
		  Connection conn = getConnection();
		  
		  PreparedStatement preparedStmt = conn.prepareStatement(query);
	      preparedStmt.setInt (1, cassa.getID_cash());
	      
	      preparedStmt.execute();
	      
	      conn.close();
	      
	      return true;
	  } catch (Exception e) {
		  e.printStackTrace();
		  return false;
	  }
  }
  
  /**
	* Funzione che rimuove una proiezione all'interno del database
	* @return true se la rimozione è andata a buon fine, false altrimenti
	* @param proiezione da rimuovere
	*/
  public static boolean removeProiezione(Proiezione proiezione) {
	  String query = "DELETE FROM proiezione where ID_Film = ? and ID_sala = ? and Giorno_from = ?";
		  
	  try {
		  Connection conn = getConnection();
		  
		  PreparedStatement preparedStmt = conn.prepareStatement(query);
	      preparedStmt.setString (1, proiezione.getID_film());
	      preparedStmt.setString (2, proiezione.getID_sala());
	      preparedStmt.setString (3, proiezione.getGiorno_from());
	      
	      
	      preparedStmt.execute();
	      
	      conn.close();
	      
	      return true;
	  } catch (Exception e) {
		  e.printStackTrace();
		  return false;
	  }
  }
 
  /**
	* Funzione che ritorna tutte le casse nel database
	* @return lista di Cassa presenti nel database
	*/
  public static List<Cassa> getAllCassa() {
    try {
      Connection con = getConnection();

      String query = "SELECT * FROM cassa";

      Statement st = con.createStatement();
      
      ResultSet rs = st.executeQuery(query);
      
      List<Cassa> listaCassa = new ArrayList<Cassa>();
      
      while (rs.next()) {
    	 Cassa cassa = new Cassa();
        
    	 cassa.setID_cash(rs.getInt("ID_cash"));
    	 cassa.setPw_cash(rs.getString("pw_cash"));
    
    	 listaCassa.add(cassa);  
      }
      
      return listaCassa;

    } catch (Exception ex) {
    	Alert alert = new Alert(AlertType.ERROR, "Connessione al database fallita");
		alert.showAndWait();
        ex.printStackTrace();
        return new ArrayList<Cassa>();
    }
  }
  
  /**
	* Funzione che crea un oggetto di tipo connection al database
	* @return connection al db
	* @throws errore se la connessione non è stata creata
	*/
  public static Connection getConnection() throws ClassNotFoundException, SQLException {
	  String url = "jdbc:mysql://localhost:3306/ooc?serverTimezone=Europe/Rome";
	  String usernameDb = "root";
	  String passwordDb = "root";

	  Class.forName("com.mysql.cj.jdbc.Driver");
	  return DriverManager.getConnection(url, usernameDb, passwordDb);
  }
  
  /**
	* Funzione che effettua il login al database
	* @return true se il login ha avuto successo, false altrimenti
	* @param email e password dell'utente
	*/
  public static boolean login(String email, String password) throws ClassNotFoundException {
	    Connection con = null;

	    String url = "jdbc:mysql://localhost:3306/ooc?serverTimezone=Europe/Rome";     //BUG risolto così:https://github.com/TdP-2019/materiale/blob/master/faq/timezone.md
	    String usernameDb = "root";
	    String passwordDb = "root";
	    
	    boolean result = false;
	    
	    try {
	      Class.forName("com.mysql.cj.jdbc.Driver");
	      con = DriverManager.getConnection(url, usernameDb, passwordDb);

	      String query = "SELECT * FROM admin WHERE ID_admin='" + email + "' AND PW='" + password + "'";

	      Statement st = con.createStatement();
	      
	      ResultSet rs = st.executeQuery(query);
	      
	      result = rs.next();

	    } catch (Exception ex) {
	    	Alert alert = new Alert(AlertType.ERROR, "Connessione al database fallita");
			alert.showAndWait();
	        throw new Error("Error ", ex);
	    } finally {
	      try {
	        if (con != null) {
	            con.close();
	        }
	      } catch (SQLException ex) {
	          System.out.println(ex.getMessage());
	      }
	    }
	    
	    return result;
	  }

}