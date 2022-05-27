package it.unipv.po.oocinema.persistenza;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Classe che espone diverse funzioni statiche che permettono di interfacciarsi
 * al database
 */

public class MySQLConnectionFactory {

	/**
	 * Nome proprietà driver Db
	 */
	private static final String PROPERTYDRIVER = "DB_driver";
	
	/**
	 * Nome proprietà url DB
	 */
	private static final String PROPERTYURL = "DB_url";
	
	/**
	 * Nome proprietà username dell'utente del DB
	 */
	private static final String PROPERTYUSER = "DB_user";
	
	/**
	 * Nome proprietà password dell'utente del DB
	 */
	private static final String PROPERTYPSW = "DB_psw";
	
	/**
	 * Driver del DB
	 */
	private static String DB_driver;
	
	/**
	 * URL del DB
	 */
	private static String DB_url;
	
	/**
	 * Username dell'utente del DB
	 */
	private static String DB_user;
	
	/**
	 * Password dell'utente del DB
	 */
	private static String DB_psw;
	
	/**
	 * Inizializza le proprietà del DB
	 */
	private static void init() { 
		Properties p = new Properties(System.getProperties());
		try {
			p.load(new FileInputStream("properties/properties"));
			DB_driver = p.getProperty(PROPERTYDRIVER);
			DB_url = p.getProperty(PROPERTYURL);
			DB_user = p.getProperty(PROPERTYUSER);
			DB_psw = p.getProperty(PROPERTYPSW);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * 
	 * @param conn
	 * @return connessione aperta al database
	 */
	public static Connection connect(Connection conn) {
		
		init();
		
		if (isOpen(conn))
			closeConnection(conn);
		
		try {
			Class.forName(DB_driver);
			conn = DriverManager.getConnection(DB_url,DB_user,DB_psw);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
		return conn;
	}

	/**
	 * 
	 * @param conn
	 * @return true se la connessione al DB è gia aperta, altrimenti false
	 */
	public static boolean isOpen(Connection conn)
	{
		if (conn == null)
			return false;
		else
			return true;
	}

	/**
	 * 
	 * @param conn
	 * @return connessione chiusa al db
	 */
	public static Connection closeConnection(Connection conn)
	{
		if ( !isOpen(conn) )
			return null;

		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		conn = null;
		
		return conn;
	}

}