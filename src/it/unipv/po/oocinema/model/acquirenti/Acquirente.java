package it.unipv.po.oocinema.model.acquirenti;

import java.time.LocalDate;

public class Acquirente {
	
	public String user;
	public String password;
	public LocalDate compleanno;
	
	
	public Acquirente(String user, String password, LocalDate compleanno) {
		this.user = user;
		this.password = password;
		this.compleanno = compleanno;
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
	public LocalDate getCompleanno() {
		return compleanno;
	}
	public void setCompleanno(LocalDate compleanno) {
		this.compleanno = compleanno;
	}
	
	
}
