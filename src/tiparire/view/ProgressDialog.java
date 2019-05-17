package tiparire.view;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JProgressBar;
import javax.swing.SwingUtilities;

public class ProgressDialog extends JDialog {

	
	private static final long serialVersionUID = 1L;
	private JButton cancelButton;
	private JProgressBar progressBar;
	private ProgressDialogListener listener;

	public ProgressDialog(Window parent, String title) {
		super(parent, title, ModalityType.APPLICATION_MODAL);

		cancelButton = new JButton("Renunta");
		progressBar = new JProgressBar();
		progressBar.setStringPainted(true);
		progressBar.setString("Asteptati...");
		progressBar.setIndeterminate(true);

		setLayout(new FlowLayout());

		Dimension size = cancelButton.getPreferredSize();
		size.width = 300;
		progressBar.setPreferredSize(size);

		add(progressBar);
		add(cancelButton);

		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (listener != null) {
					listener.progressDialogCancelled();
				}

			}
		});


		
		setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent arg0) {
				if(listener != null) {
					listener.progressDialogCancelled();
				}
			}
		});
		
		pack();
		setLocationRelativeTo(parent);		
		

	}

	public void setListener(ProgressDialogListener listener) {
		this.listener = listener;
	}

	@Override
	public void setVisible(final boolean visible) {

		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				ProgressDialog.super.setVisible(visible);
			}
		});
	}

}
