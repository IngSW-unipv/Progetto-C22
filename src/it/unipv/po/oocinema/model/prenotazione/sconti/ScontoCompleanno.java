package it.unipv.po.oocinema.model.prenotazione.sconti;

import it.unipv.po.oocinema.model.prenotazione.Prenotazione;

public class ScontoCompleanno implements IScontoPrenotazioneStrategy{
	
	private static final double PERCENTUALE = 0.75;
	
	
	// Decidere se sconto � nel giorno dell'acquisto o nel giorno della proiezione
	@Override
	public double getTotale(Prenotazione p) {
		if(java.time.LocalDate.now().getDayOfYear() == p.getAcquirente().getCompleanno().getDayOfYear()) {
			return p.getPrezzoTot()*PERCENTUALE;
		} else {
			return p.getPrezzoTot();
		}
		
		
	}

}