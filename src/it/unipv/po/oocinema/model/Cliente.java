package it.unipv.po.oocinema.model;

import java.time.LocalDate;

public class Cliente implements IAcquirente {
	
	private String email;
	private String nome;
	private String cognome;
	private String psw;
	
	private LocalDate compleanno;
	

	public Cliente(String email, String nome, String cognome, String psw, LocalDate compleanno) {
		this.email = email;
		this.nome = nome;
		this.cognome = cognome;
		this.psw = psw;
		this.compleanno = compleanno;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public String getPsw() {
		return psw;
	}

	public void setPsw(String psw) {
		this.psw = psw;
	}
	
	public LocalDate getCompleanno() {
		return compleanno;
	}

	public void setCompleanno(LocalDate compleanno) {
		this.compleanno = compleanno;
	}
	
	
	

}
