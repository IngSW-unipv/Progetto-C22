package it.unipv.po.oocinema.model.prenotazione.sconti;

import java.sql.Date;
import java.time.LocalDate;

import it.unipv.po.oocinema.model.prenotazione.Prenotazione;

public class ScontoCompleanno implements IScontoPrenotazioneStrategy{
	
	private static final double PERCENTUALE = 0.75;
	
	
	// Decidere se sconto è nel giorno dell'acquisto o nel giorno della proiezione
	@Override
	public double getTotale(Prenotazione p) {
		if(Date.valueOf(LocalDate.now()) == p.getAcquirente().getCompleanno()) {
			return p.getPrezzoTot()*PERCENTUALE;
		} else {
			return p.getPrezzoTot();
		}
		
		
	}

}