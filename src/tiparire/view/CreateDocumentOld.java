package tiparire.view;

/*


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintException;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.SimpleDoc;
import javax.print.attribute.AttributeSet;
import javax.print.attribute.HashAttributeSet;
import javax.print.attribute.standard.PrinterName;
import javax.print.event.PrintJobAdapter;
import javax.print.event.PrintJobEvent;

import org.json.JSONException;
import org.xmlpull.v1.XmlPullParserException;

import tiparire.model.Articol;
import tiparire.model.Database;
import tiparire.model.Document;
import tiparire.model.UserInfo;
import tiparire.model.WebService;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class CreateDocumentOld {

	List<Document> document;
	List<Document> documentTiparit;
	List<Articol> articol;
	private static int CELL_PADDING = 3;
	String FILE = System.getProperty("user.dir") + "/Document.pdf";

	PrintListener printListener;

	public CreateDocumentOld() {
	}

	public void setDocumente(List<Document> document) {
		this.document = document;
	}

	public void printDocument() {

		float[] columnWidths = { 0.5f, 3.5f, 1f, 1f, 0.5f };
		Font fontHeader = new Font(Font.FontFamily.HELVETICA, 10);
		Font fontData = new Font(Font.FontFamily.COURIER, 9);

		com.itextpdf.text.Document pdfDocument = new com.itextpdf.text.Document(PageSize.A4, 30, 30, 10, 40);
		PdfWriter writer = null;
		try {
			writer = PdfWriter.getInstance(pdfDocument, new FileOutputStream(FILE));
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (DocumentException e1) {
			e1.printStackTrace();
		}

		try {

			DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");
			Date date = new Date();

			writer.setPageEvent(new PageStamper());
			pdfDocument.open();

			pdfDocument.add(new Paragraph("Gestionar: " + UserInfo.getInstance().getNume(), fontHeader));
			pdfDocument.add(new Paragraph("Data: " + dateFormat.format(date), fontHeader));

			addEmptyLine(pdfDocument, 1);

		} catch (DocumentException e) {

			e.printStackTrace();
		}

		documentTiparit = new LinkedList<Document>();

		for (int i = 0; i < document.size(); i++) {

			if (document.get(i).getSeTipareste().equals("1")) {
				try {

					documentTiparit.add(document.get(i));

					pdfDocument.add(new Paragraph("Client: " + document.get(i).getClient(), fontHeader));

					addEmptyLine(pdfDocument, 1);

					PdfPTable table = new PdfPTable(5);
					table.setWidthPercentage(100);
					table.setSplitLate(true);

					PdfPCell headerCell = new PdfPCell(new Phrase("Nr.", fontHeader));
					headerCell.setHorizontalAlignment(Element.ALIGN_CENTER);
					headerCell.setPadding(CELL_PADDING);
					table.addCell(headerCell);

					headerCell = new PdfPCell(new Phrase("Nume articol", fontHeader));
					headerCell.setHorizontalAlignment(Element.ALIGN_CENTER);
					headerCell.setPadding(CELL_PADDING);
					table.addCell(headerCell);

					headerCell = new PdfPCell(new Phrase("Cod articol", fontHeader));
					headerCell.setHorizontalAlignment(Element.ALIGN_CENTER);
					headerCell.setPadding(CELL_PADDING);
					table.addCell(headerCell);

					headerCell = new PdfPCell(new Phrase("Cantitate", fontHeader));
					headerCell.setHorizontalAlignment(Element.ALIGN_CENTER);
					headerCell.setPadding(CELL_PADDING);
					table.addCell(headerCell);

					headerCell = new PdfPCell(new Phrase("Um", fontHeader));
					headerCell.setHorizontalAlignment(Element.ALIGN_CENTER);
					headerCell.setPadding(CELL_PADDING);
					table.addCell(headerCell);

					table.setWidths(columnWidths);

					for (int ii = 0; ii < Database.articol.size(); ii++) {

						if (document.get(i).getId().equals(Database.articol.get(ii).getDocumentId())) {

							PdfPCell dataCell = new PdfPCell(
									new Phrase(Database.articol.get(ii).getPozitie(), fontData));
							dataCell.setHorizontalAlignment(Element.ALIGN_CENTER);
							dataCell.setPadding(CELL_PADDING);
							table.addCell(dataCell);

							dataCell = new PdfPCell(new Phrase(Database.articol.get(ii).getNume(), fontData));
							dataCell.setHorizontalAlignment(Element.ALIGN_LEFT);
							dataCell.setPadding(CELL_PADDING);
							table.addCell(dataCell);

							dataCell = new PdfPCell(new Phrase(Database.articol.get(ii).getCod(), fontData));
							dataCell.setHorizontalAlignment(Element.ALIGN_CENTER);
							dataCell.setPadding(CELL_PADDING);
							table.addCell(dataCell);

							dataCell = new PdfPCell(new Phrase(Database.articol.get(ii).getCantitate(), fontData));
							dataCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
							dataCell.setPadding(CELL_PADDING);
							table.addCell(dataCell);

							dataCell = new PdfPCell(new Phrase(Database.articol.get(ii).getUm(), fontData));
							dataCell.setHorizontalAlignment(Element.ALIGN_CENTER);
							dataCell.setPadding(CELL_PADDING);
							table.addCell(dataCell);

						}

					}

					pdfDocument.add(table);

					addEmptyLine(pdfDocument, 1);

				} catch (DocumentException e) {

					e.printStackTrace();
				}
			}

		}

		pdfDocument.close();
		writer.close();

		addDocumentTiparitToDb();

		if (printListener != null) {
			printListener.printFinished();
		}

	}

	private void addDocumentTiparitToDb() {

		if (documentTiparit.size() > 0) {
			WebService service = new WebService();

			try {
				try {
					service.addDocumentTiparit(documentTiparit);
				} catch (IOException | XmlPullParserException e) {
					e.printStackTrace();
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}

			printPDF();
		}

	}

	private void printPDF() {
		InputStream in;
		try {
			in = new FileInputStream(new File(FILE));

			DocFlavor flavor = DocFlavor.INPUT_STREAM.AUTOSENSE;
			AttributeSet attributeSet = new HashAttributeSet();
			attributeSet.add(new PrinterName("NPI8DA48A", null));
			PrintService service = PrintServiceLookup.lookupDefaultPrintService();

			DocPrintJob job = service.createPrintJob();
			Doc doc = new SimpleDoc(in, flavor, null);
			PrintJobWatcher watcher = new PrintJobWatcher(job);
			try {
				job.print(doc, null);
			} catch (PrintException e) {
				e.printStackTrace();
			}
			watcher.waitForDone();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}

	static class PrintJobWatcher {
		boolean done = false;

		PrintJobWatcher(DocPrintJob job) {
			job.addPrintJobListener(new PrintJobAdapter() {
				public void printJobCanceled(PrintJobEvent pje) {
					allDone();
				}

				public void printJobCompleted(PrintJobEvent pje) {
					allDone();
				}

				public void printJobFailed(PrintJobEvent pje) {
					allDone();
				}

				public void printJobNoMoreEvents(PrintJobEvent pje) {
					allDone();
				}

				void allDone() {
					synchronized (PrintJobWatcher.this) {
						done = true;
						PrintJobWatcher.this.notify();
					}
				}
			});
		}

		public synchronized void waitForDone() {
			try {
				while (!done) {
					wait();
				}
			} catch (InterruptedException e) {
			}
		}
	}

	private void printPDF2() {

		PrintService defaultPrintService = PrintServiceLookup.lookupDefaultPrintService();
		DocPrintJob printerJob = defaultPrintService.createPrintJob();
		File pdfFile = new File(FILE);
		SimpleDoc simpleDoc;

		try {
			simpleDoc = new SimpleDoc(pdfFile.toURI().toURL(), DocFlavor.URL.AUTOSENSE, null);
			printerJob.print(simpleDoc, null);

		} catch (MalformedURLException | PrintException e) {
			e.printStackTrace();
		}

	}

	public void setPrintListener(PrintListener printListener) {
		this.printListener = printListener;
	}

	private void addEmptyLine(com.itextpdf.text.Document pdfDocument, int number) {
		for (int i = 0; i < number; i++) {
			try {
				pdfDocument.add(new Paragraph(" "));
			} catch (DocumentException e) {
				e.printStackTrace();
			}
		}
	}



}

*/
