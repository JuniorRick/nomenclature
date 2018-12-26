package crdm.nomenclature.controller;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import crdm.nomenclature.entity.Purchase;

public class PdfGenerator {

	private List<Purchase> purchases;

	public PdfGenerator(List<Purchase> purchases) {
		this.purchases = purchases;
	}
	

	public void generatePDF(String filePath) throws DocumentException, URISyntaxException, IOException {

        float left = 20;
        float right = 20;
        float top = 20;
        float bottom = 20;
        Document document = new Document(PageSize.A4, left, right, top, bottom);
        
		PdfWriter.getInstance(document, new FileOutputStream(filePath));


		document.open();
		addPageHeader(document);
		document.add( new Paragraph("\n\n") );

		Paragraph paragraph1 = new Paragraph(new Phrase(purchases.get(0).getGood().getContract().getProvider().getName()));
		paragraph1.setAlignment(Element.ALIGN_RIGHT);
	    document.add(paragraph1);
		document.add( new Paragraph("\n\n") );
		
	    final String title = "IMSP Centrul Republican de Diagnosticare "
	    		+ "Medicala solicita livrearea urmatoarelor consumabile, "
	    		+ "conform contractului " + purchases.get(0).getGood().getContract().getNumber();
	    		
	    Phrase phrase = new Phrase(title);
	    Paragraph paragraph2 = new Paragraph(phrase);
	    paragraph1.setAlignment(Element.ALIGN_CENTER);
	    document.add(paragraph2);
	    document.add( new Paragraph("\n") );
	    
		PdfPTable table = new PdfPTable(4);
		table.setWidthPercentage(100);
		addTableHeader(table);
		addRows(table);
		document.add(table);
		document.add( new Paragraph("\n\n") );
		
		document.close();
	}

	private void addPageHeader(Document document)
			throws MalformedURLException, IOException, DocumentException, URISyntaxException {
		
		Resource resource = new ClassPathResource("pdf-images/Antet-CRDM.png");
		
		Image image = Image.getInstance(resource.getURL().getPath());
		float docW = PageSize.A4.getWidth() - 2 * PageSize.A4.getBorder();
		float docH = PageSize.A4.getHeight() - 2 * PageSize.A4.getBorder();
		image.scaleToFit(docW - 30, docH - 30);
		document.add(image);

	}

	private void addTableHeader(PdfPTable table) {
		Stream.of("Nr.", "Denumirea Bunurilor (Serviciilor)", "Cantitate", "Unitate de masura").forEach(columnTitle -> {
			PdfPCell header = new PdfPCell();
			header.setBorderWidth(1);
			header.setPhrase(new Phrase(columnTitle));
			table.addCell(header);
		});
	}

	private void addRows(PdfPTable table) {
		Integer count = 1;
		for (Purchase purchase : purchases) {
			table.addCell((count++).toString());
			table.addCell(purchase.getGood().getGood());
			table.addCell(purchase.getQuantity().toString());
			table.addCell(purchase.getGood().getUnit());
		}

	}

}
