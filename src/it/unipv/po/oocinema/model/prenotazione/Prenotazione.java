package it.unipv.po.oocinema.model.prenotazione;

import java.util.ArrayList;
import it.unipv.po.oocinema.model.acquirenti.Acquirente;
import it.unipv.po.oocinema.model.cinema.Posto;
import it.unipv.po.oocinema.model.cinema.Proiezione;
import it.unipv.po.oocinema.model.prenotazione.sconti.IScontoPrenotazioneStrategy;
import it.unipv.po.oocinema.model.prenotazione.sconti.ScontoFactory;

/**
 * Classe che  modella una prenotazione che è effettuata da un utente
 * @author GoF
 *
 */
public class Prenotazione {
	
	/**
	 * Id della prenotazione
	 */
	private int id;
	
	/**
	 * Attributo che contiene l'id da assegnare alla prossima prenotazione
	 */
	private static int progressivo = 0;

	/**
	 * Data d'acquisto dei biglietti
	 */
	private String dataAcquisto;

	/**
	 * Utente che ha effettuato la prenotazione
	 */
	private Acquirente acquirente;

	/**
	 * Posti occupati dalla prenotazione
	 */
	private ArrayList<Posto> posti;
	
	/**
	 * Proiezione selezionata
	 */
	private Proiezione proiezione;
	
	/**
	 * Percorso del file dei biglietti generati
	 */
	private String ticketPath;

	/**
	 * strategia di sconto utilizzata
	 */
	private IScontoPrenotazioneStrategy strategy;


	/**
	 * Costruttore completo.
	 * @param id
	 * @param dataAcquisto
	 * @param acquirente
	 * @param proiezione
	 */
	public Prenotazione(int id, String dataAcquisto, Acquirente acquirente,Proiezione proiezione) {
		this.id = id;
		this.dataAcquisto = dataAcquisto;  // non si può mettere now???
		this.acquirente = acquirente;
		this.posti = new ArrayList<Posto>();
		this.proiezione = proiezione;
		ScontoFactory f = ScontoFactory.getInstance();
		strategy = f.getScontoStartegy();
	}

	/**
	 * Costruttore vuoto
	 */
	public Prenotazione() {
		progressivo++;
		this.id = progressivo;
		this.posti = new ArrayList<Posto>();
		ScontoFactory f = ScontoFactory.getInstance();
		strategy = f.getScontoStartegy();
	}
	
	/**
	 * Setter
	 * @param id
	 */
	public void setId(int id) {
		this.id = id;
	}
	
	/**
	 * Aggiunge un posto all'elenco dei posti occupati dalla prenotazione
	 * @param riga
	 * @param col
	 */
	public void aggiungiPosto(int riga, int col) {
		boolean duplicato = false;
		if (proiezione.checkPostoDisponibile(riga, col)) {
			for (Posto s : posti) {
				if (s == proiezione.getPosto(riga, col)) {
					duplicato = true;
				}
			}
			if (!duplicato) {
				posti.add(proiezione.getPosto(riga, col));
			}
		}
	}

	/**
	 * Rimuove un posto alla posizione i
	 * @param i
	 */
	public void rimuoviPosto(int i){
		posti.remove(i);
	}

	/**
	 * 
	 * @return totale da pagare della prenotazione
	 */
	public double getTotale() {
		return strategy==null?getPrezzoTot():strategy.getTotale(this);
	}

	/**
	 * 
	 * @return prezzo totale prima degli sconti
	 */
	public double getPrezzoTot() {
		return getNumPosti() * proiezione.getPrezzo();
	}

	/**
	 * 
	 * @return numero di posti prenotati
	 */
	public int getNumPosti() {
		return posti.size();
	}

	/**
	 * 
	 * @return true se il pagamento è avvenuto con successo, altrimenti false
	 */
	public boolean pagamento(){
		if (getNumPosti() > 0) {
			return true;
		} else
			return false;
	}

	/**
	 * Getter
	 * @return data acquisto dei biglietti
	 */
	public String getDataAcquisto() {
		return dataAcquisto;
	}

	/**
	 * Setter
	 * @param dataAcquisto
	 */
	public void setDataAcquisto(String dataAcquisto) {
		this.dataAcquisto = dataAcquisto;
	}

	/**
	 * Getter
	 * @return utente che ha effettuato la prenotazione
	 */
	public Acquirente getAcquirente() {
		return acquirente;
	}

	/**
	 * Setter
	 * @param acquirente
	 */
	public void setAcquirente(Acquirente acquirente) {
		this.acquirente = acquirente;
	}

	/**
	 * Getter
	 * @return posti occupati dalla prenotazione
	 */
	public ArrayList<Posto> getPosti() {
		return posti;
	}


	/**
	 * Setter
	 * @param posti
	 */
	public void setPosti(ArrayList<Posto> posti) {
		this.posti = posti;
	}

	/**
	 * Getter
	 * @return proiezione 
	 */
	public Proiezione getProiezione() {
		return proiezione;
	}

	/**
	 * Setter
	 * @param proiezione
	 */
	public void setProiezione(Proiezione proiezione) {
		this.proiezione = proiezione;
	}

	/**
	 * Getter
	 * @return id della prenotazione
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * Getter
	 * @return posizione del file dei biglietti
	 */
	public String getTicketPath() {
		return ticketPath;
	}
	
	/**
	 * Setter
	 * @param ticketPath
	 */
	public void setTicketPath(String ticketPath) {
		this.ticketPath = ticketPath;
	}
	
	
}
