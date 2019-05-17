package tiparire.view;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import tiparire.model.Document;

@SuppressWarnings("serial")
public class MainTableModel extends AbstractTableModel {

	List<Document> documente;

	public MainTableModel() {

	}

	public Class<?> getColumnClass(int columnIndex) {
		return Document.class;
	}

	public void setData(List<Document> documente) {
		this.documente = documente;
	}

	public List<Document> getData() {
		return this.documente;
	}

	public int getColumnCount() {
		return 1;
	}

	public String getColumnName(int columnIndex) {
		return "Documente";
	}

	public int getRowCount() {
		return (documente == null) ? 0 : documente.size();
	}

	public Object getValueAt(int rowIndex, int columnIndex) {
		return (documente == null) ? null : documente.get(rowIndex);
	}

	public boolean isCellEditable(int columnIndex, int rowIndex) {
		return true;
	}

}
