package it.unipv.po.oocinema.model.prenotazione;


import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;


import it.unipv.po.oocinema.model.acquirenti.Acquirente;
import it.unipv.po.oocinema.model.cinema.Posto;
import it.unipv.po.oocinema.model.cinema.Proiezione;
import it.unipv.po.oocinema.model.prenotazione.sconti.IScontoPrenotazioneStrategy;
import it.unipv.po.oocinema.model.prenotazione.sconti.ScontoFactory;

public class Prenotazione {
	
	
	private final int id;
	
	private static int progressivo = 0;

	private String dataAcquisto;

	
	private Acquirente acquirente;

	private ArrayList<Posto> posti;

	
	private Proiezione proiezione;

	public IScontoPrenotazioneStrategy strategy;

	
	public Prenotazione(int id, Acquirente acquirente,Proiezione proiezione) throws ParseException {
		this.id = id;
		this.dataAcquisto = LocalDate.now().toString();
		this.acquirente = acquirente;
		this.posti = new ArrayList<Posto>();
		this.proiezione = proiezione;
		ScontoFactory f = ScontoFactory.getInstance();
		strategy = f.getScontoStartegy();
	}
	
	public Prenotazione(String dataAcquisto, Acquirente acquirente,Proiezione proiezione) {
		this.id = progressivo;
		progressivo++;
		this.dataAcquisto=dataAcquisto;
		this.acquirente = acquirente;
		this.posti = new ArrayList<Posto>();
		this.proiezione = proiezione;
		ScontoFactory f = ScontoFactory.getInstance();
		strategy = f.getScontoStartegy();
	}

	public Prenotazione() {
		this.id = progressivo;
		progressivo++;
	}

	public void addPosto(int row, int col) {
		boolean duplicato = false;
		if (proiezione.checkPostoDisponibile(row, col)) {
			for (Posto s : posti) {
				if (s == proiezione.getPosto(row, col)) {
					duplicato = true;
				}
			}
			if (!duplicato) {
				posti.add(proiezione.getPosto(row, col));
			}
		}
	}


	public void removePosto(int row, int col){
		posti.remove(proiezione.getPosto(row, col));
	}

	
	public double getTotale() {
		return strategy==null?getPrezzoTot():strategy.getTotale(this);
	}

	
	public double getPrezzoTot() {
		return Math.round(getNumPosti() * proiezione.getPrezzo()* 100.0) / 100.0;
	}

	public int getNumPosti() {
		return posti.size();
	}

	
	public void acquista(){
		if(!pagamento()) {
			posti.removeAll(posti);
		}
	}

	
	public boolean pagamento(){
		if (getNumPosti() > 0) {
			return true;
		} else
			return false;
	}


	public String getDataAcquisto() {
		return dataAcquisto;
	}


	public void setDataAcquisto(String dataAcquisto) {
		this.dataAcquisto = dataAcquisto;
	}


	public Acquirente getAcquirente() {
		return acquirente;
	}


	public void setAcquirente(Acquirente acquirente) {
		this.acquirente = acquirente;
	}


	public ArrayList<Posto> getPosti() {
		return posti;
	}


	public void setPosti(ArrayList<Posto> posti) {
		this.posti = posti;
	}


	public Proiezione getProiezione() {
		return proiezione;
	}


	public void setProiezione(Proiezione proiezione) {
		this.proiezione = proiezione;
	}

	public int getId() {
		return id;
	}
	
	
	
}
