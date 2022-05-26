package it.unipv.po.oocinema.controllers;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import it.unipv.po.oocinema.model.prenotazione.Prenotazione;

/**
 * Classe che crea i biglietti da dare agli utenti
 * @author GoF
 *
 */
public class TicketController {
	
	/**
	 * File di destinazione
	 */
	private final String file;

	/**
	 * Costruttore 
	 * @param prenotazione per la quale creare i biglietti
	 * @throws WriterException
	 * @throws IOException
	 */
	public TicketController(Prenotazione prenotazione) throws WriterException, IOException {
		file = "tickets/"+prenotazione.getId()+".pdf";
		prenotazione.setTicketPath(file);
		createTicket(prenotazione);
	}
	
	/**
	 * Getter
	 * @return percorso del file
	 */
	public String getFile() {
		return file;
	}

	/**
	 * Crea il biglietto
	 * @param prenotazione
	 */
	public void createTicket(Prenotazione prenotazione){
		
		try {
			Document document = new Document(PageSize.A5);
			PdfWriter.getInstance(document, new FileOutputStream(file));
			
		    document.open();
			addInformazioniPrenotazione(document, prenotazione);
		    document.close();
		} catch (Exception e) {
		       e.printStackTrace();
		}
	}
	
	/**
	 * Aggiunge al documento alcune informazioni e contenuti
	 * @param document
	 * @param prenotazione
	 * @throws MalformedURLException
	 * @throws IOException
	 * @throws DocumentException
	 * @throws WriterException
	 */
	public void addInformazioniPrenotazione(Document document, Prenotazione prenotazione) throws MalformedURLException, IOException, DocumentException, WriterException {
		document.addTitle("Prenotazione numero: " + prenotazione.getId());
		document.addCreationDate();
		document.addAuthor("Lo Staff di Oocinema");
		String[] arr = prenotazione.getProiezione().getFilm().getCoverPath().split("/");
		Image locandina = Image.getInstance(new URL("file:src/it/unipv/po/oocinema/resources/locandine/"+arr[arr.length-1]));
		
		for(int i = 0; i<prenotazione.getNumPosti();i++) {
			
			Paragraph p = createParagrafoPrenotazione(prenotazione,i);
			p.setSpacingBefore(100);
			document.add(p);
			
			locandina.scalePercent(35f);
			locandina.setAlignment(Image.BOTTOM);
			document.add(locandina); 
			
			printQRcode(prenotazione,i);
			Image qrcode = Image.getInstance(new URL("file:tickets/QRcodes/"+prenotazione.getId()+"_"+i+".png"));
			qrcode.scalePercent(20f);
			locandina.setAlignment(Image.BOTTOM);
			document.add(qrcode);
			document.newPage();
		}
	}
	
	/**
	 * 
	 * @param prenotazione
	 * @param i - numero del biglietto nella prenotazione
	 * @return paragrafo da aggiungere al documento
	 */
	public Paragraph createParagrafoPrenotazione(Prenotazione prenotazione, int i) {
		
		String giorno = ""+prenotazione.getProiezione().getGiorno().toString();
		
		Paragraph paragrafo = new Paragraph("Prenotazione effettuata da: " + prenotazione.getAcquirente().getUser()
				+ "\n Sala " + prenotazione.getProiezione().getSala().getId() + " "+prenotazione.getPosti().get(i).toString()+
				"\n" + giorno + "  alle  "+ prenotazione.getProiezione().getOrario() +"\nPrezzo: "+prenotazione.getTotale());

		return paragrafo;
	}
 
	/**
	 * crea il qrcode
	 * @param data
	 * @param path
	 * @param charset
	 * @param map
	 * @param h
	 * @param w
	 * @throws WriterException
	 * @throws IOException
	 */
	public void generateQRcode(String data, String path, String charset, Map<EncodeHintType, ErrorCorrectionLevel> map, int h, int w) throws WriterException, IOException {  
		
		com.google.zxing.common.BitMatrix matrix = new MultiFormatWriter().encode(new String(data.getBytes(charset), charset), BarcodeFormat.QR_CODE, w, h);  
		MatrixToImageWriter.writeToFile(matrix, path.substring(path.lastIndexOf('.') + 1), new File(path));  
	}  
	
	/**
	 * Stampa il qrcode
	 * @param prenotazione
	 * @param i - numero del qrcode relativo al biglietto
	 * @throws WriterException
	 * @throws IOException
	 */
	public void printQRcode(Prenotazione prenotazione, int i) throws WriterException, IOException{   
		String str= "Prenotazione effettuata da: " + prenotazione.getAcquirente().getUser()+ ".\n"+prenotazione.getPosti().get(i).toString()+"\n ID: "+prenotazione.getId();
		String path = "tickets/QRcodes/"+prenotazione.getId()+"_"+i+".png";  
		String charset = "UTF-8";  
		Map<EncodeHintType, ErrorCorrectionLevel> hashMap = new HashMap<EncodeHintType, ErrorCorrectionLevel>();  
		
		hashMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);  
	
		generateQRcode(str, path, charset, hashMap, 500, 500);
	}  
		
	
}
