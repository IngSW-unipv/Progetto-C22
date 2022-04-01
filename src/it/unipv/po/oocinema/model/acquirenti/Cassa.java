package it.unipv.po.oocinema.model.acquirenti;

import java.time.LocalDate;

/**
 * Classe che modella un oggetto di tipo Cassa
 */
public class Cassa extends Acquirente{

	public Cassa(String user, String pw_cassa) {
		super(user,pw_cassa);
	}

	@Override
	public LocalDate getCompleanno() {
		return null;
	}
	
}
