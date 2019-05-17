package tiparire.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import java.util.List;

import javax.swing.BorderFactory;

import javax.swing.JLabel;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableCellRenderer;

import tiparire.model.Articol;
import tiparire.model.Document;

@SuppressWarnings("serial")
public class HeaderDocPanel extends JPanel {

	private JLabel nrDocument;
	private JLabel client;
	private JLabel dataEmitere;

	private JLabel numeSofer;
	private JLabel nrMasina;
	private JLabel tipTransport;

	private JPanel headerPanel;
	private JPanel artPanel;
	private JLabel rowNumLabel;

	private JTable artTable;
	private ArticolTableModel artTableModel;

	private JLabel alertaEmitere;
	private JLabel infoStatus;

	public HeaderDocPanel(Document document) {

		artPanel = new JPanel();
		artPanel.setLayout(new BorderLayout());

		int space = 15;
		Border spaceBorder = BorderFactory.createEmptyBorder(space, space, space, space);

		artTableModel = new ArticolTableModel();

		artTable = new JTable(artTableModel);
		artTable.setRowSelectionAllowed(false);

		artTable.getColumnModel().getColumn(0).setPreferredWidth(20);
		artTable.getColumnModel().getColumn(1).setPreferredWidth(300);
		artTable.getColumnModel().getColumn(2).setPreferredWidth(80);
		artTable.getColumnModel().getColumn(3).setPreferredWidth(75);
		artTable.getColumnModel().getColumn(4).setPreferredWidth(40);
		artTable.getColumnModel().getColumn(5).setPreferredWidth(50);
		artTable.getColumnModel().getColumn(6).setPreferredWidth(75);
		artTable.getColumnModel().getColumn(7).setPreferredWidth(130);

		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(JLabel.CENTER);
		artTable.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
		artTable.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
		artTable.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
		artTable.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);
		artTable.getColumnModel().getColumn(5).setCellRenderer(centerRenderer);
		artTable.getColumnModel().getColumn(6).setCellRenderer(centerRenderer);
		artTable.getColumnModel().getColumn(7).setCellRenderer(centerRenderer);

		artTable.setBorder(BorderFactory.createEtchedBorder());

		artPanel.add(new JScrollPane(artTable), BorderLayout.CENTER);
		artPanel.setPreferredSize(new Dimension(650, 210));

		artTable.setFont(new Font("Courier New", Font.PLAIN, 13));

		artTable.setBackground(new Color(253, 252, 220));
		artTable.setForeground(new Color(0, 100, 0));
		artTable.setRowHeight(20);

		headerPanel = new JPanel();

		headerPanel.setLayout(new GridBagLayout());

		nrDocument = new JLabel();
		nrDocument.setFont(new Font("Arial", Font.PLAIN, 15));

		alertaEmitere = new JLabel();
		alertaEmitere.setFont(new Font("Arial", Font.BOLD, 15));
		alertaEmitere.setForeground(Color.RED);

		infoStatus = new JLabel();
		infoStatus.setFont(new Font("Arial", Font.BOLD, 15));
		infoStatus.setForeground(Color.DARK_GRAY);

		client = new JLabel();
		client.setFont(new Font("Arial", Font.BOLD, 18));
		client.setForeground(new Color(126, 166, 126));

		dataEmitere = new JLabel();
		dataEmitere.setFont(new Font("Helvetica", Font.PLAIN, 14));
		dataEmitere.setForeground(new Color(97, 128, 97));

		numeSofer = new JLabel();
		numeSofer.setFont(new Font("Helvetica", Font.PLAIN, 14));
		numeSofer.setForeground(new Color(211, 34, 64));

		nrMasina = new JLabel();
		nrMasina.setFont(new Font("Helvetica", Font.PLAIN, 14));
		nrMasina.setForeground(new Color(211, 34, 64));

		tipTransport = new JLabel();
		tipTransport.setFont(new Font("Helvetica", Font.PLAIN, 14));
		tipTransport.setForeground(new Color(97, 128, 97));

		rowNumLabel = new JLabel();

		setLayout(new BorderLayout());

		GridBagConstraints gc = new GridBagConstraints();

		// //////////////////////////////////////////

		gc.gridy++;

		gc.weightx = 1;
		gc.weighty = 0.1;

		gc.gridy = 1;
		gc.gridx = 1;
		gc.insets = new Insets(0, 20, 0, 0);
		gc.anchor = GridBagConstraints.LINE_START;
		headerPanel.add(client, gc);

		// //////////////////////////////////////////

		gc.gridy++;

		gc.weightx = 1;
		gc.weighty = 0.2;

		gc.gridx = 1;
		gc.insets = new Insets(0, 20, 0, 0);
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		headerPanel.add(dataEmitere, gc);

		// //////////////////////////////////////////

		gc.gridy++;

		gc.weightx = 1;
		gc.weighty = 0.2;

		gc.gridx = 1;
		gc.insets = new Insets(2, 20, 0, 0);
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		headerPanel.add(nrDocument, gc);

		// //////////////////////////////////////////

		gc.gridy++;

		gc.weightx = 1;
		gc.weighty = 0.2;

		gc.gridx = 1;
		gc.insets = new Insets(2, 20, 0, 0);
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		headerPanel.add(numeSofer, gc);

		// //////////////////////////////////////////

		gc.gridy++;

		gc.weightx = 1;
		gc.weighty = 0.2;

		gc.gridx = 1;
		gc.insets = new Insets(2, 20, 0, 0);
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		headerPanel.add(nrMasina, gc);

		// //////////////////////////////////////////

		gc.gridy++;

		gc.weightx = 1;
		gc.weighty = 0.2;

		gc.gridx = 1;
		gc.insets = new Insets(2, 20, 0, 0);
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		headerPanel.add(tipTransport, gc);

		int spaceCount = 5;
		Border spaceCountBorder = BorderFactory.createEmptyBorder(spaceCount, spaceCount, spaceCount, spaceCount);
		headerPanel.setBorder(spaceCountBorder);

		JPanel alertPanel = new JPanel();
		alertPanel.setLayout(new BorderLayout());
		alertPanel.add(alertaEmitere, BorderLayout.NORTH);
		alertPanel.add(infoStatus, BorderLayout.SOUTH);
		alertPanel.setBorder(spaceCountBorder);

		JPanel headerContainer = new JPanel();
		headerContainer.setLayout(new BorderLayout());

		rowNumLabel.setFont(new Font("Courier", Font.BOLD, 30));
		rowNumLabel.setForeground(new Color(162, 181, 205));

		headerContainer.add(rowNumLabel, BorderLayout.WEST);
		headerContainer.add(headerPanel, BorderLayout.CENTER);
		headerContainer.add(alertPanel, BorderLayout.EAST);

		headerContainer.setBorder(spaceBorder);

		add(headerContainer, BorderLayout.NORTH);
		add(artPanel, BorderLayout.CENTER);

	}

	public void setClient(String clientText) {
		client.setText(clientText);
	}

	public void setDocument(String documentText) {
		nrDocument.setText(documentText);

	}

	public void setEmitere(String emitereText) {
		dataEmitere.setText(emitereText);
	}

	public void setNumeSofer(String numeSoferText) {
		if (!numeSoferText.trim().isEmpty())
			numeSofer.setText("Sofer: " + numeSoferText);
		else
			numeSofer.setText(numeSoferText);
	}

	public void setNrMasina(String nrMasinaText) {
		if (!nrMasinaText.trim().isEmpty())
			nrMasina.setText("Nr. auto: " + nrMasinaText);
		else
			nrMasina.setText(nrMasinaText);
	}

	public void setTipTransport(String tipTransportText) {
		tipTransport.setText(tipTransportText);
	}

	public void setRownum(String rownum) {
		rowNumLabel.setText(rownum);
	}

	public void setArticolData(List<Articol> articol) {
		artTableModel.setData(articol);
		artTableModel.fireTableDataChanged();

	}

	public void setAlertEmitereText(String alertText) {
		alertaEmitere.setText(alertText);
	}

	public void setTextInfoStatus(String strStatus) {
		infoStatus.setText(strStatus);
	}

}