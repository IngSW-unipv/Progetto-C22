package it.unipv.po.oocinema.database;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Classe che espone diverse funzioni statiche che permettono di interfacciarsi
 * al database
 */

//Mi aspetto che i miei colleghi del gruppo poi, dovendo aggiungere altre classi, implementino meglio la persistenza con il pattern Dao

public class MySQLConnectionFactory {

	private static final String PROPERTYDRIVER = "DB_driver";
	private static final String PROPERTYURL = "DB_url";
	private static final String PROPERTYUSER = "DB_user";
	private static final String PROPERTYPSW = "DB_psw";
	
	private static String DB_driver;
	private static String DB_url;
	private static String DB_user;
	private static String DB_psw;
	
	
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
	
	public static Connection connect(Connection conn) {
		
		init();
		
		if ( isOpen(conn) )
			closeConnection(conn);
		
		try {
			Class.forName(DB_driver);
			conn = DriverManager.getConnection(DB_url, DB_user, DB_psw);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
		return conn;
	}

	public static boolean isOpen(Connection conn)
	{
		if (conn == null)
			return false;
		else
			return true;
	}

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