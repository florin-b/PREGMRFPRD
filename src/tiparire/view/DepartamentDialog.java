package tiparire.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import javax.swing.border.Border;

import tiparire.enums.EnumTipDocument;
import tiparire.model.Database;
import tiparire.model.TipDocumentAfisat;
import tiparire.model.UserInfo;
import tiparire.model.Utils;

@SuppressWarnings("serial")
public class DepartamentDialog extends JDialog implements DataListener {

	private JButton okButton;
	private JButton cancelButton;
	private JList<Object> departamentList;
	private JList<Object> filialeList;
	private DepartamentListener departamentListener;

	Database db;

	public DepartamentDialog(JFrame parent) {
		super(parent, "Selectie departament", true);

		okButton = new JButton("Ok");
		cancelButton = new JButton("Inchide");

		db = new Database(parent);
		db.setDataListener(this);
		db.setTipDocument(EnumTipDocument.TRANSFER);

		departamentList = new JList<Object>();
		departamentList.setMinimumSize(new Dimension(100, 100));
		departamentList.setSize(new Dimension(100, 100));

		filialeList = new JList<Object>();
		filialeList.setMinimumSize(new Dimension(100, 100));
		filialeList.setSize(new Dimension(100, 100));

		setMinimumSize(new Dimension(220, 220));
		setSize(220, 220);

		pack();
		setLocationRelativeTo(parent);
		this.getRootPane().setDefaultButton(okButton);

		addLayoutComponents();

		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
			}
		});

		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				UserInfo.getInstance().setDepart(departamentList.getSelectedValue().toString());

				if (filialeList.getSelectedValue() != null)
					UserInfo.getInstance().setUnitLog(filialeList.getSelectedValue().toString());
				
				db.setDataListener(DepartamentDialog.this);

				if (TipDocumentAfisat.getInstance().isNetiparit()) {
					db.getDocumenteNetiparite();
				} else
					db.getDocumenteTiparite(TipDocumentAfisat.getInstance().getDataTiparire());

			}
		});

	}

	private void addLayoutComponents() {
		JPanel listPanel = new JPanel();
		JPanel buttonsPanel = new JPanel();

		int space = 15;
		Border spaceBorder = BorderFactory.createEmptyBorder(space, space, space, 0);

		listPanel.setBorder(spaceBorder);
		buttonsPanel.setBorder(spaceBorder);

		listPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		listPanel.add(new JScrollPane(departamentList));

		//listPanel.add(new JScrollPane(filialeList));

		buttonsPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		buttonsPanel.add(okButton);
		buttonsPanel.add(cancelButton);

		Dimension btnSize = cancelButton.getPreferredSize();
		okButton.setPreferredSize(btnSize);

		setLayout(new BorderLayout());

		add(listPanel, BorderLayout.CENTER);
		add(buttonsPanel, BorderLayout.SOUTH);
		pack();

	}

	public void setListener(DepartamentListener listener) {
		this.departamentListener = listener;
	}

	@Override
	public void dataReceived() {
		setVisible(false);
		departamentListener.departamentSelected(departamentList.getSelectedValue().toString(), db.getDocumente());

	}

	public void setDepartaments() {
		String[] userDepartaments = Utils.getUserDepartaments();
		departamentList.setListData(userDepartaments);

	}

	public void setFiliale() {

		if (filialeList != null) {

			if (UserInfo.getInstance().getTipAcces() != null && UserInfo.getInstance().getTipAcces().equals("3")) {

				String[] userFiliale = Utils.getFiliale();
				filialeList.setListData(userFiliale);
				filialeList.setVisible(true);
			} else
				filialeList.setVisible(false);
		}

	}

}
