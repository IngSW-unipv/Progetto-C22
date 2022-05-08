package it.unipv.po.oocinema.model.acquirenti;

import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Acquirente {
	
	private String user;
	private String password;
	
	private String compleanno;
	
	private String nome;
	
	private String cognome;

	
	
	public Acquirente(String user, String password, String nome,String cognome, String compleanno) {
		this.user = user;
		this.password = password;
		this.compleanno = compleanno;
		this.nome = nome;
		this.cognome = cognome;
	}
	
	public Acquirente(String user){
		this.user = user;
	}
	
	public Acquirente(){
	}

	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public String getCompleanno() {
		return compleanno;
	}

	public void setCompleanno(String compleanno) {
		this.compleanno = compleanno;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	
}
