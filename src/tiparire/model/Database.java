package tiparire.model;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;

import org.xmlpull.v1.XmlPullParserException;

import tiparire.enums.EnumTipDocument;
import tiparire.view.DataListener;
import tiparire.view.ProgressDialog;
import tiparire.view.ProgressDialogListener;

public class Database implements ProgressDialogListener {

	public static List<Articol> articol;
	List<Document> documente;

	private DataListener dataListener;
	private ProgressDialog progressDialog;
	private SwingWorker<String, String> swingWorker;
	private JFrame parent;
	private EnumTipDocument tipDocument;

	public Database() {

	}

	public Database(JFrame parent) {
		this.parent = parent;

	}

	public void getDocumenteNetiparite() {

		progressDialog = new ProgressDialog(parent, "Asteptati...");
		progressDialog.setLocationRelativeTo(null);
		progressDialog.setListener(this);

		SwingUtilities.invokeLater(new Runnable() {

			public void run() {

				progressDialog.setVisible(true);
			}
		});

		swingWorker = new SwingWorker<String, String>() {

			@Override
			protected String doInBackground() throws Exception {

				String docResult = "";

				try {
					WebService service = new WebService();
					docResult = service.getDocumente(tipDocument);

				} catch (IOException e) {
					e.printStackTrace();
				} catch (XmlPullParserException e) {
					e.printStackTrace();
				}

				return docResult;
			}

			protected void done() {

				if (dataListener != null) {
					progressDialog.setVisible(false);

					if (isCancelled())
						return;

					String receivedData = "";

					try {
						receivedData = get();

						articol = new LinkedList<Articol>();
						HandleJSONReceivedData handleData = new HandleJSONReceivedData(receivedData);
						articol = handleData.decodeDocumentData();
						buildDocumentList();

					} catch (InterruptedException e) {
						e.printStackTrace();
					} catch (ExecutionException e) {
						e.printStackTrace();
					}

					dataListener.dataReceived();

				}

			}

			@Override
			protected void process(List<String> chunks) {
				super.process(chunks);
			}

		};

		swingWorker.execute();

	}

	public void getDocumenteTiparite(final String dataTiparire) {
		progressDialog = new ProgressDialog(parent, "Asteptati...");
		progressDialog.setLocationRelativeTo(null);
		progressDialog.setListener(this);

		SwingUtilities.invokeLater(new Runnable() {

			public void run() {

				progressDialog.setVisible(true);
			}
		});

		swingWorker = new SwingWorker<String, String>() {

			@Override
			protected String doInBackground() throws Exception {

				String docResult = "";

				try {
					WebService service = new WebService();
					docResult = service.getDocumenteTiparite(dataTiparire);
				} catch (IOException e) {
					e.printStackTrace();
				} catch (XmlPullParserException e) {
					e.printStackTrace();
				}

				return docResult;
			}

			protected void done() {

				if (dataListener != null) {
					progressDialog.setVisible(false);

					if (isCancelled())
						return;

					String receivedData = "";

					try {
						receivedData = get();

						articol = new LinkedList<Articol>();

						HandleJSONReceivedData handleData = new HandleJSONReceivedData(receivedData);
						articol = handleData.decodeDocumentData();
						buildDocumentList();

					} catch (InterruptedException e) {
						e.printStackTrace();
					} catch (ExecutionException e) {
						e.printStackTrace();
					}

					dataListener.dataReceived();

				}

			}

			@Override
			protected void process(List<String> chunks) {
				super.process(chunks);
			}

		};

		swingWorker.execute();

	}

	private void buildDocumentList() {

		documente = new LinkedList<Document>();
		documente.clear();

		if (articol.size() > 0) {

			boolean docFound = false;

			for (int i = 0; i < articol.size(); i++) {
				docFound = false;

				for (int j = 0; j < documente.size(); j++) {
					if (articol.get(i).getDocumentId().equals(documente.get(j).getId())) {
						docFound = true;

					}
				}

				if (!docFound) {
					Document unDocument = new Document(articol.get(i).getDocumentId(), articol.get(i).getEmitere(),
							articol.get(i).getClient(), "00", "00", "0", articol.get(i).isPregatit(), articol.get(i)
									.isTiparit(), articol.get(i).getTip(), articol.get(i).getNumeSofer(), articol
									.get(i).getNrMasina(), getStatusDocument(articol, articol.get(i).getDocumentId()),
							articol.get(i).getTipTransport());
					documente.add(unDocument);
				}

			}

		}

	}

	private String getStatusDocument(List<Articol> listArticole, String documentId) {
		String status = "";

		for (Articol articol : listArticole) {
			if (articol.getDocumentId().equals(documentId)) {
				if (!articol.getInfoStatus().isEmpty()) {
					status = articol.getInfoStatus();
					break;
				}
			}

		}

		return status;
	}

	public List<Document> getDocumente() {
		return this.documente;
	}

	public void setDocumente(List<Document> documente) {
		this.documente = documente;
	}

	public void setDataListener(DataListener dataListener) {
		this.dataListener = dataListener;
	}

	public void clearListDocumente() {
		this.documente.clear();
	}

	public void setTipDocument(EnumTipDocument tipDocument) {
		this.tipDocument = tipDocument;
	}

	public EnumTipDocument getTipDocument() {
		return tipDocument;
	}

	public void progressDialogCancelled() {
		if (swingWorker != null) {
			swingWorker.cancel(true);
		}

	}

}
