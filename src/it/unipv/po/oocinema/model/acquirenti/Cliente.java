package it.unipv.po.oocinema.model.acquirenti;

import java.time.LocalDate;

public class Cliente extends Acquirente {
	
	private LocalDate compleanno;

	public Cliente(String email, String psw, LocalDate compleanno) {
		super(email,psw);
		this.compleanno = compleanno;
	}

	@Override
	public LocalDate getCompleanno() {
		
		return compleanno;
	}	

}
