package it.unipv.po.oocinema.model.acquirenti;

import java.sql.Date;

public class Cliente extends Acquirente {
	
	private String compleanno;
	private String nome;
	private String cognome;


	public Cliente(String email, String psw, String nome, String cognome, String compleanno) {
		super(email,psw);
		this.setNome(nome);
		this.setCognome(cognome);
		this.compleanno = compleanno;
	}

	@Override
	public String getCompleanno() {
		return compleanno;
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
