package it.unipv.po.oocinema.model.acquirenti;

import java.sql.Date;

public class Cliente extends Acquirente {
	
	private Date compleanno;
	private String nome;
	private String cognome;


	public Cliente(String email, String psw, String nome, String cognome, Date compleanno) {
		super(email,psw);
		this.nome = nome;
		this.cognome = cognome;
		this.compleanno = compleanno;
	}


	@Override
	public Date getCompleanno() {
		
		return compleanno;
	}

	

}
