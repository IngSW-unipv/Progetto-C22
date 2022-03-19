package it.unipv.po.oocinema.model.acquirenti;

import java.time.LocalDate;

public class Cliente extends Acquirente {
	
	private String nome;
	private String cognome;
	

	public Cliente(String email, String nome, String cognome, String psw, LocalDate compleanno) {
		super(email,psw,compleanno);
		this.nome = nome;
		this.cognome = cognome;
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
