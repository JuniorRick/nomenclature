package crdm.nomenclature.controller;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.sql.Date;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfWriter;

import crdm.nomenclature.entity.Purchase;
import crdm.nomenclature.entity.Settings;

public class PdfGenerator {

	private List<Purchase> purchases;
	private Settings settings;

	public PdfGenerator(List<Purchase> purchases, Settings settings) {
		this.purchases = purchases;
		this.settings = settings;
	}

	public void generatePDF(String filePath) throws DocumentException, URISyntaxException, IOException {

		float left = 40;
		float right = 20;
		float top = 20;
		float bottom = 20;
		Document document = new Document(PageSize.A4, left, right, top, bottom);

		PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(filePath));
		writer.setPageEvent(new MyFooter());
		
		document.open();
		MyFooter footer = new MyFooter();
		footer.onEndPage(writer, document, settings);
		
		addPageHeader(document);
		document.add(new Paragraph(""));
		Paragraph paragraph1 = new Paragraph(
				new Phrase(purchases.get(0).getGood().getContract().getProvider().getName()));
		paragraph1.setAlignment(Element.ALIGN_RIGHT);
		document.add(paragraph1);
		document.add(new Paragraph("\n\n"));

		Date date = purchases.get(0).getGood().getContract().getStartDate();

		final String title = "IMSP Centrul Republican de Diagnosticare "
				+ "Medicala solicita livrearea urmatoarelor consumabile, " + "conform contractului "
				+ purchases.get(0).getGood().getContract().getNumber() + " din " + formatDate(date);

		Phrase phrase = new Phrase(title);
		Paragraph paragraph2 = new Paragraph(phrase);
		paragraph1.setAlignment(Element.ALIGN_CENTER);
		document.add(paragraph2);
		document.add(new Paragraph("\n"));

		PdfPTable table = new PdfPTable(4);
		table.setWidths(new float[] { 1, 4, 2, 2 });
		table.setWidthPercentage(100);
//		table.setLockedWidth(true);

		addTableHeader(table);
		addRows(table);
		document.add(table);
		document.add(new Paragraph("\n"));

		addExecutor(document);

		document.close();
	}

	private String formatDate(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);

		String day = Integer.toString(calendar.get(Calendar.DAY_OF_MONTH));
		day = day.length() > 1 ? day : '0' + day;

		String month = Integer.toString(calendar.get(Calendar.MONTH) + 1);
		month = month.length() > 1 ? month : '0' + month;

		String year = Integer.toString(calendar.get(Calendar.YEAR));

		return day + "." + month + "." + year;

	}

	private void addExecutor(Document document) throws DocumentException {

		document.add(new Paragraph("\n"));

		PdfPTable tbl = new PdfPTable(3);
		tbl.setHorizontalAlignment(Element.ALIGN_CENTER);

		PdfPCell cell = new PdfPCell(new Phrase("Presedintele gr. de lucru"));
		cell.disableBorderSide(Rectangle.BOX);
		tbl.addCell(cell);
		
		cell = new PdfPCell(new Phrase(""));
		cell.disableBorderSide(Rectangle.BOX);
		tbl.addCell(cell);
		
		cell = new PdfPCell(new Phrase(settings.getDirector()));
		cell.disableBorderSide(Rectangle.BOX);
		tbl.addCell(cell);

		document.add(tbl);
	}

	private void addPageHeader(Document document)
			throws MalformedURLException, IOException, DocumentException, URISyntaxException {

		Resource resource = new ClassPathResource("pdf-images/Antet-CRDM.png");

		Image image = Image.getInstance(resource.getURL().getPath());
		float docW = PageSize.A4.getWidth() - 2 * PageSize.A4.getBorder();
		float docH = PageSize.A4.getHeight() - 2 * PageSize.A4.getBorder();
		image.scaleToFit(docW - 50, docH - 50);
		document.add(image);

	}

	private void addTableHeader(PdfPTable table) {
		Stream.of("Nr.", "Denumirea Bunurilor (Serviciilor)", "Cantitate", "Unitate de masura").forEach(columnTitle -> {
			PdfPCell header = new PdfPCell();
			header.setHorizontalAlignment(Element.ALIGN_CENTER);
			header.setBorderWidth(1);
			header.setPhrase(new Phrase(columnTitle));
			table.addCell(header);
		});
	}

	private void addRows(PdfPTable table) {
		Integer count = 1;

		for (Purchase purchase : purchases) {
			PdfPCell cell = new PdfPCell(new Phrase((count++).toString()));
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(cell);

			cell = new PdfPCell(new Phrase(purchase.getGood().getGood()));
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(cell);

			cell = new PdfPCell(new Phrase(purchase.getQuantity().toString()));
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(cell);

			cell = new PdfPCell(new Phrase(purchase.getGood().getUnit()));
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(cell);

		}

	}

}

//TODO use this class to add footer
class MyFooter extends PdfPageEventHelper {
	private Settings settings;
	public void onEndPage(PdfWriter writer, Document document, Settings settings) {
		this.settings = settings;
		
		PdfContentByte cb = writer.getDirectContent();
		
		ColumnText.showTextAligned(cb, Element.ALIGN_LEFT, footer()[0],
				document.left() + document.leftMargin(), document.bottom() + 10, 0);
		ColumnText.showTextAligned(cb, Element.ALIGN_LEFT, footer()[1],
				document.left() + document.leftMargin(), document.bottom(), 0);
	}

	private Phrase[] footer() {
		
		Phrase[] p = new Phrase[2];
		p[0] = new Phrase("Ex. " + settings.getExecutor(), FontFactory.getFont(FontFactory.HELVETICA, 8));
		p[1] = new Phrase("Tel. " + settings.getTel(), FontFactory.getFont(FontFactory.HELVETICA, 8));
		
		return p;
	}
}