package tiparire.view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JTable;

import tiparire.model.Document;

@SuppressWarnings("serial")
public class TablePanel extends JPanel {

	private JTable docsTable;
	private MainTableModel tableModel;

	public TablePanel() {

		tableModel = new MainTableModel();

		docsTable = new JTable(tableModel);

		docsTable.setDefaultRenderer(Document.class, new DocumentCell());
		docsTable.setDefaultEditor(Document.class, new DocumentCell());

		docsTable.setAutoscrolls(false);

		setLayout(new BorderLayout());
		add(docsTable, BorderLayout.CENTER);

	}

	public void autoresizeTableRowHeight() {
		for (int row = 0; row < docsTable.getRowCount(); row++) {
			int rowHeight = docsTable.getRowHeight();

			for (int column = 0; column < docsTable.getColumnCount(); column++) {
				Component comp = docsTable.prepareRenderer(docsTable.getCellRenderer(row, column), row, column);
				rowHeight = Math.max(rowHeight, comp.getPreferredSize().height);
			}

			docsTable.setRowHeight(row, rowHeight);
			

		}
		
		
	}

	public void setData(List<Document> docsList) {
		tableModel.setData(docsList);
		tableModel.fireTableDataChanged();

	}

	public List<Document> getData() {
		return tableModel.getData();
	}

	public void refresh() {
		tableModel.fireTableDataChanged();
	}

}
