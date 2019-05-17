package tiparire.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

import tiparire.model.Utils;

@SuppressWarnings("serial")
public class Toolbar extends JPanel {

	private JButton refreshButton;
	private JButton printButton;
	private JButton selectAllButton;
	private JLabel departLabel;
	private JLabel numeGestLabel;
	private JLabel tipDocLabel;
	private JPanel textPanel;
	private boolean selectAllItems;
	private ToolbarListener toolbarListener;
	private ClassLoader cl;

	public Toolbar() {
		refreshButton = new JButton("Actualizeaza");
		printButton = new JButton("Tipareste");
		selectAllButton = new JButton("Toate");
		departLabel = new JLabel();
		numeGestLabel = new JLabel();

		tipDocLabel = new JLabel();
		tipDocLabel.setHorizontalAlignment(JLabel.CENTER);
		tipDocLabel.setVerticalAlignment(JLabel.CENTER);
		tipDocLabel.setFont(new Font("Helvetica", Font.PLAIN, 16));
		tipDocLabel.setForeground(new Color(0, 0, 139));

		textPanel = new JPanel();

		JPanel buttonsPanel = new JPanel();

		cl = getClass().getClassLoader();

		printButton.setIcon(Utils.createIcon(cl.getResource("images/print_icon24.png")));
		refreshButton.setIcon(Utils.createIcon(cl.getResource("images/refresh_24.png")));
		selectAllButton.setIcon(Utils.createIcon(cl.getResource("images/allItems.png")));
		printButton.setFont(new Font("Helvetica", Font.PLAIN, 14));
		refreshButton.setFont(new Font("Helvetica", Font.PLAIN, 14));
		selectAllButton.setFont(new Font("Helvetica", Font.PLAIN, 14));

		buttonsPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		buttonsPanel.add(refreshButton);
		buttonsPanel.add(selectAllButton);
		buttonsPanel.add(printButton);

		Dimension dim = refreshButton.getPreferredSize();
		printButton.setPreferredSize(dim);
		selectAllButton.setPreferredSize(dim);
		refreshButton.setFocusPainted(false);

		textPanel.setLayout(new BorderLayout());

		departLabel.setFont(new Font("Helvetica", Font.PLAIN, 13));
		numeGestLabel.setFont(new Font("Helvetica", Font.PLAIN, 13));
		departLabel.setForeground(new Color(0, 0, 139));
		numeGestLabel.setForeground(new Color(0, 0, 139));

		textPanel.add(departLabel, BorderLayout.NORTH);
		textPanel.add(numeGestLabel, BorderLayout.SOUTH);

		int spaceCount = 5;
		Border spaceCountBorder = BorderFactory.createEmptyBorder(spaceCount, spaceCount, spaceCount, spaceCount);
		textPanel.setBorder(spaceCountBorder);

		setLayout(new BorderLayout());

		add(textPanel, BorderLayout.WEST);
		add(tipDocLabel, BorderLayout.CENTER);
		add(buttonsPanel, BorderLayout.EAST);

		printButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (toolbarListener != null) {
					toolbarListener.printEventOccured();
					selectAllItems = true;
					selectAllButton.doClick();
				}

			}
		});

		refreshButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (toolbarListener != null) {
					toolbarListener.refreshEventOccured();
					selectAllItems = true;
					selectAllButton.doClick();
				}

			}
		});

		selectAllButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (toolbarListener != null) {

					if (selectAllItems) {
						selectAllButton.setText("Toate");
						selectAllButton.setIcon(Utils.createIcon(cl.getResource("images/allItems.png")));
					} else {
						selectAllButton.setText("Niciunul");
						selectAllButton.setIcon(null);
					}

					selectAllItems = !selectAllItems;
					toolbarListener.selectAllItems(selectAllItems);

				}

			}
		});

	}

	public void setToolbarListener(ToolbarListener toolbarListener) {
		this.toolbarListener = toolbarListener;
	}

	public void setDepartamentString(String departament) {
		departLabel.setText("Departament: " + departament);
	}

	public void setNumeGest(String numeGest) {
		this.numeGestLabel.setText("Gestionar: " + numeGest);
	}

	public void setTipDocument(String tipDocument) {
		tipDocLabel.setText(tipDocument);
	}

}
