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

public class MySQLConnection {
  public static void connect() throws ClassNotFoundException {
    Connection con = null;

    String url = "jdbc:mysql://localhost:3306/oocinema";
    String username = "root";
    String password = "root";

    try {
      Class.forName("com.mysql.cj.jdbc.Driver");
      con = DriverManager.getConnection(url, username, password);

      System.out.println("Connected!");

    } catch (SQLException ex) {
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
  }
  
  public static boolean insertFilm(Film film) throws ClassNotFoundException {
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
  
  public static boolean removeFilm(Film film) throws ClassNotFoundException {
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
  
  public static boolean insertProiezione(Proiezione proiezione) throws ClassNotFoundException {
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
		  e.printStackTrace();
		  return false;
	  }
  }
  
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
  
  public static boolean removeProiezione(Proiezione proiezione) throws ClassNotFoundException {
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
  
  public static Connection getConnection() throws ClassNotFoundException, SQLException {
	  String url = "jdbc:mysql://localhost:3306/oocinema";
	  String usernameDb = "root";
	  String passwordDb = "root";

	  Class.forName("com.mysql.cj.jdbc.Driver");
	  return DriverManager.getConnection(url, usernameDb, passwordDb);
  }
  
  public static boolean login(String email, String password) throws ClassNotFoundException {
	    Connection con = null;

	    String url = "jdbc:mysql://localhost:3306/oocinema";
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