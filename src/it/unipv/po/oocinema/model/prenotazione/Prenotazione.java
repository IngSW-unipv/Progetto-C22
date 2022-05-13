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
	
	
	private int id;
	
	private static int progressivo = 0;

	private String dataAcquisto;

	
	private Acquirente acquirente;

	private ArrayList<Posto> posti;

	private int numPosti;
	
	private Proiezione proiezione;
	
	private String ticketPath;

	public IScontoPrenotazioneStrategy strategy;


	
	public Prenotazione(int id, String dataAcquisto, Acquirente acquirente,Proiezione proiezione) {
		this.id = id;
		this.dataAcquisto = dataAcquisto;
		this.acquirente = acquirente;
		this.posti = new ArrayList<Posto>();
		numPosti = posti.size();
		this.proiezione = proiezione;
		ScontoFactory f = ScontoFactory.getInstance();
		strategy = f.getScontoStartegy();
	}

	public Prenotazione() {
		progressivo++;
		this.id = progressivo;
		this.posti = new ArrayList<Posto>();
		numPosti = posti.size();
		ScontoFactory f = ScontoFactory.getInstance();
		strategy = f.getScontoStartegy();
	}

	public void setId(int id) {
		this.id = id;
	}
	public void aggiungiPosto(int row, int col) {
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


	public void rimuoviPosto(int i){
		posti.remove(i);
	}

	
	public double getTotale() {
		return strategy==null?getPrezzoTot():strategy.getTotale(this);
	}

	
	public double getPrezzoTot() {
		return getNumPosti() * proiezione.getPrezzo();
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


	public void setNumPosti(int numPosti) {
		this.numPosti = numPosti;
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

	public String getTicketPath() {
		return ticketPath;
	}
	
	public void setTicketPath(String ticketPath) {
		this.ticketPath = ticketPath;
	}
	
	
	
}
