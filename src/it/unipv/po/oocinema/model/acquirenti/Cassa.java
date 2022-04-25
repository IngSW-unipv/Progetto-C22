package it.unipv.po.oocinema.model.acquirenti;

/**
 * Classe che modella un oggetto di tipo Cassa
 */
public class Cassa extends Acquirente{

	public Cassa(String user, String pw_cassa) {
		super(user,pw_cassa);
	}
	
	public Cassa(String user) {
		super(user);
	}

	@Override
	public String getCompleanno() {
		return null;
	}
	
}
