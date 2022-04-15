package it.unipv.po.oocinema.model.acquirenti;

import java.sql.Date;

public class Cliente extends Acquirente {
	
	private String compleanno;
	private String nome;
	private String cognome;


	public Cliente(String email, String psw, String nome, String cognome, String compleanno) {
		super(email,psw);
		this.nome = nome;
		this.cognome = cognome;
		this.compleanno = compleanno;
	}

	@Override
	public String getCompleanno() {
		return compleanno;
	}
}
