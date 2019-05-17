package tiparire.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.swing.AbstractCellEditor;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.border.Border;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

import org.xmlpull.v1.XmlPullParserException;

import tiparire.listeners.PregatireMarfaListener;
import tiparire.model.AlertaEmitere;
import tiparire.model.Articol;
import tiparire.model.Database;
import tiparire.model.Document;
import tiparire.model.UserInfo;
import tiparire.model.Utils;
import tiparire.model.WebService;

@SuppressWarnings("serial")
public class DocumentCell extends AbstractCellEditor
		implements TableCellEditor, TableCellRenderer, PregatireMarfaListener {
	private JPanel mainPanel;

	private JButton printButton;
	private JLabel imageLabel;

	private JButton marfaPregatitaBtn;

	private JPanel buttonPanel;
	private HeaderDocPanel docPanel;

	private Document document;
	private JSplitPane splitPanel;

	private WebService service;

	private boolean isMarfaPregatita = false;

	public DocumentCell() {

		printButton = new JButton("Selecteaza");
		printButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				fireEditingStopped();

				if (document.getSeTipareste().equals("1"))
					document.setSeTipareste("0");
				else
					document.setSeTipareste("1");

			}
		});

		service = new WebService();
		service.setPregatireMarfaListener(DocumentCell.this);

		Dimension printDim = new Dimension(140, 30);
		printButton.setPreferredSize(printDim);
		printButton.setSize(printDim);
		printButton.setFont(new Font("Helvetica", Font.PLAIN, 14));

		marfaPregatitaBtn = new JButton("Marfa pregatita");
		marfaPregatitaBtn.setBackground(Color.orange);

		marfaPregatitaBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				fireEditingStopped();
				isMarfaPregatita = !isMarfaPregatita;

				try {
					service.setMarfaPregatita(document.getId(), UserInfo.getInstance().getId(), document,
							isMarfaPregatita);
				} catch (IOException | XmlPullParserException e) {
					e.printStackTrace();
				}

			}
		});

		marfaPregatitaBtn.setPreferredSize(printDim);
		marfaPregatitaBtn.setSize(printDim);
		marfaPregatitaBtn.setFont(new Font("Helvetica", Font.PLAIN, 14));

		int space = 15;
		Border spaceBorder = BorderFactory.createEmptyBorder(space, space, space, space);

		ClassLoader cl = this.getClass().getClassLoader();
		imageLabel = new JLabel(Utils.createIcon(cl.getResource("images/print_icon.png")));

		buttonPanel = new JPanel(new BorderLayout());
		buttonPanel.add(printButton, BorderLayout.NORTH);
		buttonPanel.add(imageLabel, BorderLayout.CENTER);
		buttonPanel.add(marfaPregatitaBtn, BorderLayout.SOUTH);

		imageLabel.setVisible(false);

		buttonPanel.setBorder(spaceBorder);

		docPanel = new HeaderDocPanel(document);

		mainPanel = new JPanel(new BorderLayout());

		splitPanel = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, docPanel, buttonPanel);
		splitPanel.setDividerLocation(0.6);
		splitPanel.setResizeWeight(0.6);
		splitPanel.setEnabled(false);

		mainPanel.add(splitPanel, BorderLayout.CENTER);

	}

	private void updateData(Document document, boolean isSelected, JTable table, int row) {
		this.document = document;

		String print = document.getSeTipareste();
		if (print.equals("1")) {
			this.imageLabel.setVisible(true);
			ClassLoader cl = getClass().getClassLoader();
			this.printButton.setIcon(Utils.createIcon(cl.getResource("images/select_24.png")));
		} else {
			this.imageLabel.setVisible(false);
			this.printButton.setIcon(null);
			this.printButton.revalidate();
		}

		if (document.isTiparit()) {
			this.printButton.setText("Tiparit");
			buttonPanel.setBackground(new Color(127, 197, 205));
		} else {

			buttonPanel.setBackground(new Color(238, 238, 238));
			this.printButton.setText("Selecteaza");
		}

		if (document.getInfoStatus().contains("stearsa")) {

			if (!document.isMarfaPregatita()) {
				this.isMarfaPregatita = false;
				this.marfaPregatitaBtn.setBackground(Color.ORANGE);
				this.marfaPregatitaBtn.setText("Retur marfa");
			} else {
				this.isMarfaPregatita = true;
				marfaPregatitaBtn.setBackground(new Color(238, 238, 238));
				this.marfaPregatitaBtn.setText("Anuleaza retur marfa");

			}

		} else if (document.isMarfaPregatita()) {
			this.isMarfaPregatita = true;
			marfaPregatitaBtn.setBackground(new Color(238, 238, 238));
			this.marfaPregatitaBtn.setText("Anuleaza pregatire");
		} else {
			this.isMarfaPregatita = false;
			this.marfaPregatitaBtn.setBackground(Color.ORANGE);
			this.marfaPregatitaBtn.setText("Marfa pregatita");
		}

		this.docPanel.setDocument(document.getId());
		this.docPanel.setClient(document.getClient());
		this.docPanel.setEmitere(document.getDataEmiterii());
		this.docPanel.setRownum(String.valueOf(row + 1));
		this.docPanel.setNumeSofer(document.getNumeSofer());
		this.docPanel.setNrMasina(document.getNrMasina());
		this.docPanel.setTipTransport(document.getTipTransport().toString());

		setTextAlertEmitereDocument(document);
		setTextInfoStatus(document);

		List<Articol> tempArticol = new LinkedList<Articol>();

		for (int ii = 0; ii < Database.articol.size(); ii++) {
			if (((Articol) Database.articol.get(ii)).getDocumentId().equals(document.getId())) {
				tempArticol.add((Articol) Database.articol.get(ii));
			}
		}

		this.docPanel.setArticolData(tempArticol);
	}

	private void setTextAlertEmitereDocument(Document document) {

		String textAlert = null;
		int nrOreEmitere = new AlertaEmitere().getAlertaEmitere(document);

		if (document.getTip().equals("T")) {
			if (nrOreEmitere > 8)
				textAlert = "Document emis acum " + nrOreEmitere + " ore";
		} else if (document.getTip().equals("D"))
			if (nrOreEmitere > 4)
				textAlert = "Document emis acum " + nrOreEmitere + " ore";

		if (textAlert != null)
			docPanel.setAlertEmitereText(textAlert);
		else
			docPanel.setAlertEmitereText("");
	}

	private void setTextInfoStatus(Document document) {
		this.docPanel.setTextInfoStatus(document.getInfoStatus());
	}

	public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
		Document document = (Document) value;
		updateData(document, true, table, row);
		return mainPanel;
	}

	public Object getCellEditorValue() {
		return null;
	}

	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {
		Document document = (Document) value;

		updateData(document, isSelected, table, row);
		return mainPanel;
	}

	@Override
	public void pregatireComplete(Document document, String response) {

		if (!response.equals("-1")) {
			document.setMarfaPregatita(isMarfaPregatita);

		} else {
			JOptionPane.showMessageDialog(null, "Operatie esuata!");
		}

	}

}
