package it.unipv.po.oocinema.controllers;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.TextStyle;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.TabStop.Alignment;
import com.itextpdf.text.pdf.PdfWriter;

import it.unipv.po.oocinema.model.acquirenti.Acquirente;
import it.unipv.po.oocinema.model.cinema.Film;
import it.unipv.po.oocinema.model.cinema.Proiezione;
import it.unipv.po.oocinema.model.cinema.Sala;
import it.unipv.po.oocinema.model.prenotazione.Prenotazione;

public class TicketHandler {
	
	private final String file;

	public TicketHandler(Prenotazione prenotazione) throws WriterException, IOException {
		file = "tickets/"+prenotazione.getId()+".pdf";
		createTicket(prenotazione);
	}
	public void createTicket(Prenotazione prenotazione){
		
		try {
			Document document = new Document(PageSize.POSTCARD);
			PdfWriter.getInstance(document, new FileOutputStream(file));
			
		    document.open();
			addInformazioniPrenotazione(document, prenotazione);
		    document.close();
		} catch (Exception e) {
		       e.printStackTrace();
		}
	}
	
	public void addInformazioniPrenotazione(Document document, Prenotazione prenotazione) throws MalformedURLException, IOException, DocumentException, WriterException {
		document.addTitle("Prenotazione numero: " + prenotazione.getId());
		document.addCreationDate();
		document.addAuthor("Lo Staff di Oocinema");
		Image locandina = Image.getInstance(new URL(prenotazione.getProiezione().getFilm().getCoverPath()));
		
		for(int i = 0; i<prenotazione.getNumPosti();i++) {
			locandina.scalePercent(35f);
			locandina.setAbsolutePosition(10,300);
			document.add(locandina);
			document.add(createParagrafoPrenotazione(prenotazione,i));
			printQRcode(prenotazione,i);
			Image qrcode = Image.getInstance(new URL("file:tickets/QRcodes/"+prenotazione.getId()+"_"+i+".png"));
			qrcode.scalePercent(20f);
			locandina.setAbsolutePosition(0, 10);
			document.add(qrcode);
			document.newPage();
		}
	}
	
	public Paragraph createParagrafoPrenotazione(Prenotazione prenotazione, int i) {
		
		String giorno = ""+prenotazione.getProiezione().getGiorno().getDayOfWeek().getValue();
		String mese = ""+prenotazione.getProiezione().getGiorno().getMonth().getValue();
		String anno = ""+prenotazione.getProiezione().getGiorno().getYear();
		String data = giorno + '/' + mese  + '/' + anno;
		
		Paragraph paragrafo = new Paragraph("Prenotazione effettuata da: " + prenotazione.getAcquirente().getUser()
				+ "\n" + "Sala "+ prenotazione.getProiezione().getSala().getID_sala() +prenotazione.getPosti().get(i).toString()+
				"   -   " + data + "  alle  "
				+ prenotazione.getProiezione().getOrario().getHour()+":"+prenotazione.getProiezione().getOrario().getMinute());
		paragrafo.setSpacingBefore(30);
		return paragrafo;
	}
 
	
	public void generateQRcode(String data, String path, String charset, Map<EncodeHintType, ErrorCorrectionLevel> map, int h, int w) throws WriterException, IOException {  
		
		com.google.zxing.common.BitMatrix matrix = new MultiFormatWriter().encode(new String(data.getBytes(charset), charset), BarcodeFormat.QR_CODE, w, h);  
		MatrixToImageWriter.writeToFile(matrix, path.substring(path.lastIndexOf('.') + 1), new File(path));  
	}  
		
	public void printQRcode(Prenotazione prenotazione, int i) throws WriterException, IOException{   
		String str= "Prenotazione effettuata da: " + prenotazione.getAcquirente().getUser()+ ".\n Posto: "+prenotazione.getPosti().get(i).toString()+"\n ID: "+prenotazione.getId();
		String path = "tickets/QRcodes/"+prenotazione.getId()+"_"+i+".png";  
		String charset = "UTF-8";  
		Map<EncodeHintType, ErrorCorrectionLevel> hashMap = new HashMap<EncodeHintType, ErrorCorrectionLevel>();  
		
		hashMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);  
	
		generateQRcode(str, path, charset, hashMap, 200, 200);
	}  
		
	
	
	
	
	public static void main(String[] args) throws WriterException, IOException {
		Acquirente a = new Acquirente("user","psw",null);
		Film f = new Film(2,"Avengers", null,"Fantastico", 180,"Regi","sta","file:assets/2.jpeg",null);
		Proiezione p = new Proiezione(f, LocalDate.now(), new Sala("SALA 1",2,2),LocalTime.now());
		Prenotazione prenotazione = new Prenotazione(5,a,p);
		prenotazione.addPosto(0, 1);
		prenotazione.addPosto(0, 0);
		prenotazione.acquista();
		TicketHandler t = new TicketHandler(prenotazione);

	}
	
	
}
