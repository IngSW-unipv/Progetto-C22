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
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Font.FontStyle;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.qrcode.BitMatrix;

import it.unipv.po.oocinema.model.acquirenti.Acquirente;
import it.unipv.po.oocinema.model.cinema.Film;
import it.unipv.po.oocinema.model.cinema.Proiezione;
import it.unipv.po.oocinema.model.cinema.Sala;
import it.unipv.po.oocinema.model.prenotazione.Prenotazione;

public class TicketHandler {
	
	private final String file;

	public TicketHandler(Prenotazione prenotazione) {
		file = "./tickets/"+Integer.toString(prenotazione.getId())+".pdf";
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
	
	public void addInformazioniPrenotazione(Document document, Prenotazione prenotazione) throws MalformedURLException, IOException, DocumentException {
		document.addTitle("Prenotazione numero: " + prenotazione.getId());
		document.addCreationDate();
		document.addAuthor("Lo Staff di Oocinema");
		Image locandina = Image.getInstance(new URL(prenotazione.getProiezione().getFilm().getCoverPath()));
		locandina.scalePercent(20f);
		locandina.setAbsolutePosition(10, 10);
		document.add(locandina);
		document.add(new Paragraph("Prenotazine effettuata da: "+ prenotazione.getAcquirente().getUser(), new Font(Font.FontFamily.HELVETICA, 33, Font.BOLD)));
		
	}
 
	
	public void generateQRcode(String data, String path, String charset, Map<EncodeHintType, ErrorCorrectionLevel> map, int h, int w) throws WriterException, IOException {  
		
		com.google.zxing.common.BitMatrix matrix = new MultiFormatWriter().encode(new String(data.getBytes(charset), charset), BarcodeFormat.QR_CODE, w, h);  
		MatrixToImageWriter.writeToFile(matrix, path.substring(path.lastIndexOf('.') + 1), new File(path));  
	}  
		//main() method  
		public void printQRcode(Prenotazione prenotazione) throws WriterException, IOException
		{  
		//data that we want to store in the QR code  
		String str= "Prenotazione effettuata da: " + prenotazione.getAcquirente().getUser()+ ".\n Numero di posti: "+prenotazione.getNumPosti()+"\n ID: "+prenotazione.getId();
		//path where we want to get QR Code  
		String path = "C:\\Users\\teora\\Documents\\GitHub\\Progetto-C22\\tickets\\Quote.png";  
		
		String charset = "UTF-8";  
		Map<EncodeHintType, ErrorCorrectionLevel> hashMap = new HashMap<EncodeHintType, ErrorCorrectionLevel>();  
		//generates QR code with Low level(L) error correction capability  
		hashMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);  
		//invoking the user-defined method that creates the QR code  
		generateQRcode(str, path, charset, hashMap, 200, 200);//increase or decrease height and width accodingly   
		  
		System.out.println("QR Code created successfully.");  
		}  
		
	
	
	/*public Paragraph createPrenotazioneString(Prenotazione prenotazione) {
		String dayOfWeek = prenotazione.getProiezione().getGiorno().getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.ITALIAN);
		String month = prenotazione.getProiezione().getGiorno().getMonth().getDisplayName(TextStyle.FULL,
				Locale.ITALIAN);
		Paragraph infoReservationP = new Paragraph("Prenotazione effettuata da: " + prenotazione.getAcquirente().getUser()
				+ "\n" + "Sala "+ prenotazione.getProiezione().getSala().getID_sala() + 
				"   -   " + dayOfWeek.toUpperCase().charAt(0)
				+ " " + reservation.getProjection().getDateTime().getDayOfMonth() + " "
				+ month.toUpperCase().charAt(0) + month.substring(1) + " "
				+ prenotazione.getProiezione().getDateTime().getYear() + "  alle  "
				+ String.format("%02d", reservation.getProjection().getDateTime().getHour()) + ":"
				+ String.format("%02d", reservation.getProjection().getDateTime().getMinute()),
				allFonts.get("subFont25"));
		infoReservationP.setSpacingBefore(30);
		return infoReservationP;
	}*/
	
	public static void main(String[] args) throws WriterException, IOException {
		Acquirente a = new Acquirente("user","psw",null);
		Film f = new Film(2,"Avengers", null,"Fantastico", 180,"Matteo","Ragni","file:///C:/Users/teora/Documents/GitHub/Progetto-C22/assets/2.jpeg",null);
		Proiezione p = new Proiezione(f, LocalDate.now(), new Sala("SALA 1",2,2),LocalTime.now());
		Prenotazione prenotazione = new Prenotazione(1,a,p);
		TicketHandler t = new TicketHandler(prenotazione);
		t.printQRcode(prenotazione);
	}
	
	
}
