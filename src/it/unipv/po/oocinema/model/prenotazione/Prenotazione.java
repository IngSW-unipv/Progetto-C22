package it.unipv.po.oocinema.model.prenotazione;


import java.time.LocalDate;
import java.util.ArrayList;

import it.unipv.po.oocinema.model.acquirenti.Acquirente;
import it.unipv.po.oocinema.model.cinema.Posto;
import it.unipv.po.oocinema.model.cinema.Proiezione;
import it.unipv.po.oocinema.model.prenotazione.sconti.IScontoPrenotazioneStrategy;
import it.unipv.po.oocinema.model.prenotazione.sconti.ScontoFactory;

public class Prenotazione {
	
	
	private final int id;

	private LocalDate dataAcquisto;

	
	private Acquirente acquirente;

	private ArrayList<Posto> posti;

	
	private Proiezione proiezione;

	public IScontoPrenotazioneStrategy strategy;

	
	public Prenotazione(int id, Acquirente acquirente,Proiezione proiezione) {
		super();
		this.id = id;
		this.dataAcquisto = java.time.LocalDate.now();
		this.acquirente = acquirente;
		this.posti = new ArrayList<Posto>();
		this.proiezione = proiezione;
		ScontoFactory f = ScontoFactory.getInstance();
		strategy = f.getScontoStartegy();
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
		occupaPosti();
		if(!pagamento()) {
			liberaPosti();
			posti.removeAll(posti);
		}
	}

	public void occupaPosti() {
		for (int i = 0; i < posti.size(); i++) {
			int row = posti.get(i).getRiga();
			int col = posti.get(i).getColonna();
			if (proiezione.checkPostoDisponibile(row, col)){
				proiezione.occupaPosto(row, col);
			}
		}
	}

	
	public boolean pagamento(){
		if (getNumPosti() > 0) {
			return true;
		} else
			return false;
	}

	
	public void liberaPosti() {
		for (int i = 0; i < posti.size(); i++) {
			int row = posti.get(i).getRiga();
			int col = posti.get(i).getColonna();
			if (proiezione.checkPostoDisponibile(row, col)){
				proiezione.liberaPosto(row, col);
			}
		}
	}


	public LocalDate getDataAcquisto() {
		return dataAcquisto;
	}


	public void setDataAcquisto(LocalDate dataAcquisto) {
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
