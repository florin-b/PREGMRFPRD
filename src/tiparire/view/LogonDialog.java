package tiparire.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;
import javax.swing.border.Border;

import org.xmlpull.v1.XmlPullParserException;

import tiparire.model.HandleJSONReceivedData;
import tiparire.model.WebService;

public class LogonDialog extends JDialog implements ProgressDialogListener {

	private static final long serialVersionUID = 1L;

	private JButton logonButton;
	private JButton cancelButton;
	private JTextField userField;
	private JPasswordField passField;
	private LogonListener logonListener;
	private ProgressDialog progressDialog;
	private SwingWorker<String, String> swingWorker;
	private JLabel logonStatus;

	public LogonDialog(JFrame parent) {
		super(parent, "Tiparire documente", false);

		setMinimumSize(new Dimension(300, 250));
		setSize(300, 250);
		setModal(true);

		setLocationRelativeTo(parent);

		logonButton = new JButton("Logon");
		cancelButton = new JButton("Iesire");
		progressDialog = new ProgressDialog(parent, "Verificare credentiale");
		progressDialog.setListener(this);
		logonStatus = new JLabel();

		this.getRootPane().setDefaultButton(logonButton);

		userField = new JTextField(15);
		passField = new JPasswordField(15);
		passField.setEchoChar('*');

		
		//userField.setText("CMARCUS1");
		//passField.setText("28RTrG");
		
		addLayoutControls();

		logonButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				String username = userField.getText().trim();
				char[] password = passField.getPassword();

				performLogon(username, new String(password));

			}
		});

		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});

		setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);

		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}

		});

	}

	private void performLogon(final String username, final String password) {

		SwingUtilities.invokeLater(new Runnable() {

			public void run() {

				progressDialog.setVisible(true);
			}
		});

		swingWorker = new SwingWorker<String, String>() {

			@Override
			protected String doInBackground() throws Exception {

				String logonResult = "";

				try {
					WebService service = new WebService();
					logonResult = service.performLogon(username, password);
				} catch (IOException e) {
					e.printStackTrace();
				} catch (XmlPullParserException e) {
					e.printStackTrace();
				}

				return logonResult;
			}

			protected void done() {
				if (logonListener != null) {

					progressDialog.setVisible(false);

					if (isCancelled())
						return;

					try {
						String receivedData = get();

						HandleJSONReceivedData handleData = new HandleJSONReceivedData(receivedData);
						handleData.decodeLogonInfo();

					} catch (InterruptedException e) {
						e.printStackTrace();
					} catch (ExecutionException e) {
						e.printStackTrace();
					}

					logonListener.logonSucceeded();
					setVisible(false);
				}
			}

			@Override
			protected void process(List<String> chunks) {
				super.process(chunks);
			}

		};

		swingWorker.execute();

	}

	public void setLogonListener(LogonListener logonListener) {
		this.logonListener = logonListener;
	}

	private void addLayoutControls() {

		JPanel controlsPanel = new JPanel();
		JPanel buttonsPanel = new JPanel();

		int space = 15;
		Border spaceBorder = BorderFactory.createEmptyBorder(space, space, space, space);
		Border titleBorder = BorderFactory.createTitledBorder("Autorizare acces");

		controlsPanel.setBorder(BorderFactory.createCompoundBorder(spaceBorder, titleBorder));
		buttonsPanel.setBorder(spaceBorder);

		controlsPanel.setLayout(new GridBagLayout());

		GridBagConstraints gc = new GridBagConstraints();

		gc.gridx = 0;

		Insets rightPadding = new Insets(0, 0, 0, 15);
		Insets noPadding = new Insets(0, 0, 0, 0);

		// //////////////First Row
		gc.gridy = 0;
		gc.weightx = 1;
		gc.weighty = 1;
		gc.fill = GridBagConstraints.NONE;

		gc.gridx = 0;
		gc.anchor = GridBagConstraints.EAST;
		gc.insets = rightPadding;
		controlsPanel.add(new JLabel("Utilizator: "), gc);

		gc.gridx++;
		gc.anchor = GridBagConstraints.WEST;
		gc.insets = noPadding;
		controlsPanel.add(userField, gc);

		// /////////////Next Row
		gc.gridy++;

		gc.weightx = 1;
		gc.weighty = 1;
		gc.fill = GridBagConstraints.NONE;

		gc.gridx = 0;
		gc.anchor = GridBagConstraints.EAST;
		gc.insets = rightPadding;
		controlsPanel.add(new JLabel("Parola: "), gc);

		gc.gridx++;
		gc.anchor = GridBagConstraints.WEST;
		gc.insets = noPadding;
		controlsPanel.add(passField, gc);

		// /////////////Next Row
		gc.gridy++;

		gc.weightx = 1;
		gc.weighty = 1;
		gc.fill = GridBagConstraints.NONE;

		gc.gridx = 0;
		gc.anchor = GridBagConstraints.EAST;
		gc.insets = rightPadding;

		gc.gridx++;
		gc.anchor = GridBagConstraints.EAST;
		gc.insets = noPadding;
		controlsPanel.add(logonStatus, gc);

		buttonsPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		buttonsPanel.add(logonButton);
		buttonsPanel.add(cancelButton);

		Dimension btnSize = logonButton.getPreferredSize();
		cancelButton.setPreferredSize(btnSize);

		// add panels to dialog
		setLayout(new BorderLayout());

		add(controlsPanel, BorderLayout.CENTER);
		add(buttonsPanel, BorderLayout.SOUTH);

	}

	public void setLogonStatus(String status) {
		logonStatus.setText(status);
		repaint();
	}

	public void progressDialogCancelled() {
		if (swingWorker != null) {
			swingWorker.cancel(true);
		}

	}

}
