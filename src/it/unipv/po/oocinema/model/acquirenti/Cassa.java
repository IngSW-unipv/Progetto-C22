package it.unipv.po.oocinema.model.acquirenti;

import java.time.LocalDate;

/**
 * Classe che modella un oggetto di tipo Cassa
 */
public class Cassa extends Acquirente{

	public Cassa(String user, String pw_cassa, LocalDate compleanno) {
		super(user,pw_cassa, null);
	}
	
}
