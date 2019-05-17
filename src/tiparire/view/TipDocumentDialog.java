package tiparire.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.Border;

import tiparire.enums.EnumTipDocument;
import tiparire.model.Database;
import tiparire.model.TipDocumentAfisat;

import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;

@SuppressWarnings("serial")
public class TipDocumentDialog extends JDialog implements DataListener {

	private JButton okButton;
	private JButton cancelButton;
	private JRadioButton docNetipariteRadio;
	private JRadioButton docTipariteRadio;
	private ButtonGroup tipDocGrup;
	private JPanel dateSelPanel;
	private UtilDateModel model;
	private JDatePanelImpl datePanel;
	private JDatePickerImpl datePicker;
	private TipDocumentListener documentListener;
	private int tipDoc = 1;
	private EnumTipDocument tipDocument;

	Database db;

	public TipDocumentDialog(JFrame parent) {
		super(parent, "Documente", false);
		okButton = new JButton("Ok");
		cancelButton = new JButton("Inchide");

		db = new Database(parent);
		db.setDataListener(this);

		Dimension dim = cancelButton.getPreferredSize();
		okButton.setPreferredSize(dim);

		dateSelPanel = new JPanel();

		model = new UtilDateModel();
		datePanel = new JDatePanelImpl(model);
		datePicker = new JDatePickerImpl(datePanel);

		Calendar date = Calendar.getInstance();
		int year = date.get(Calendar.YEAR);
		int month = date.get(Calendar.MONTH);
		int day = date.get(Calendar.DAY_OF_MONTH);

		model.setDate(year, month, day);
		model.setSelected(true);

		docNetipariteRadio = new JRadioButton("Netiparite");
		docTipariteRadio = new JRadioButton("Tiparite");

		tipDocGrup = new ButtonGroup();
		tipDocGrup.add(docNetipariteRadio);
		tipDocGrup.add(docTipariteRadio);

		docNetipariteRadio.setSelected(true);

		setMinimumSize(new Dimension(290, 220));
		setSize(220, 220);
		setModal(true);

		pack();
		setLocationRelativeTo(parent);
		this.getRootPane().setDefaultButton(okButton);

		addLayoutComponents();

		docNetipariteRadio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dateSelPanel.setVisible(false);
				tipDoc = 1;

			}
		});

		docTipariteRadio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dateSelPanel.setVisible(true);
				tipDoc = 2;
			}
		});

		okButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				db.setDataListener(TipDocumentDialog.this);

				Date selectedDate = (Date) datePicker.getModel().getValue();

				Calendar cal = Calendar.getInstance();
				cal.setTime(selectedDate);
				int year = cal.get(Calendar.YEAR);
				int month = cal.get(Calendar.MONTH) + 1;
				int day = cal.get(Calendar.DAY_OF_MONTH);

				String printDate = String.valueOf(year) + String.format("%02d", month) + String.format("%02d", day);

				if (tipDoc == 1) {
					TipDocumentAfisat.getInstance().setNetiparit(true);
					db.getDocumenteNetiparite();
				}
				if (tipDoc == 2) {
					TipDocumentAfisat.getInstance().setNetiparit(false);
					TipDocumentAfisat.getInstance().setDataTiparire(printDate);
					db.getDocumenteTiparite(printDate);
				}

			}
		});

		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				setVisible(false);

			}
		});

	}

	private void addLayoutComponents() {

		JPanel buttonsPanel = new JPanel();
		buttonsPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		buttonsPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));
		buttonsPanel.add(okButton);
		buttonsPanel.add(cancelButton);

		int space = 15;
		Border spaceBorder = BorderFactory.createEmptyBorder(space, space, space, space);

		JPanel radioPanel = new JPanel();
		radioPanel.setLayout(new BorderLayout());
		radioPanel.add(docTipariteRadio, BorderLayout.EAST);
		radioPanel.add(docNetipariteRadio, BorderLayout.WEST);
		radioPanel.setBorder(spaceBorder);

		dateSelPanel.setLayout(new BorderLayout());
		dateSelPanel.add(new JLabel("La data : "), BorderLayout.CENTER);
		dateSelPanel.add(datePicker, BorderLayout.SOUTH);
		dateSelPanel.setBorder(spaceBorder);

		setLayout(new BorderLayout());

		Border titleBorder = BorderFactory.createTitledBorder("Afisati documentele");

		JPanel containerPanel = new JPanel();

		containerPanel.setBorder(BorderFactory.createCompoundBorder(spaceBorder, titleBorder));
		containerPanel.setLayout(new BorderLayout());
		containerPanel.add(radioPanel, BorderLayout.NORTH);
		containerPanel.add(dateSelPanel, BorderLayout.CENTER);

		add(containerPanel, BorderLayout.CENTER);
		add(buttonsPanel, BorderLayout.SOUTH);
		pack();
		dateSelPanel.setVisible(false);
	}

	public void setListener(TipDocumentListener documentListener) {
		this.documentListener = documentListener;
	}

	@Override
	public void dataReceived() {
		setVisible(false);
		documentListener.tipDocumentSelected(db.getDocumente());

	}

	public EnumTipDocument getTipDocument() {
		return tipDocument;
	}

	public void setTipDocument(EnumTipDocument tipDocument) {
		this.tipDocument = tipDocument;
	}

}
