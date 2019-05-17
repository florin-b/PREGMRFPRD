package tiparire.view;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;

import tiparire.model.Document;

@SuppressWarnings("serial")
public class DocumentCellComponent extends JPanel {

	Document document;

	JPanel panel;
	JButton button;
	JLabel text;

	public DocumentCellComponent() {

		button = new JButton();
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, document.getDepartament());
			}
		});

		text = new JLabel();

		panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		add(text);
		add(button);
	}

	public void displayData(Document document, boolean isSelected, JTable table) {
		this.document = document;

		button.setText(document.getClient());
		text.setText(document.toString());
		
		if (isSelected) {
			setBackground(table.getSelectionBackground());
		} else {
			setBackground(table.getSelectionForeground());
		}

	}

}
