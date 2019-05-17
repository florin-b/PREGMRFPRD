package tiparire.view;

import java.awt.Component;

import javax.swing.AbstractCellEditor;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;

import tiparire.model.Document;

@SuppressWarnings("serial")
public class MainTableCellEditor extends AbstractCellEditor implements TableCellEditor {

	DocumentCellComponent documentComponent;

	public MainTableCellEditor() {
		this.documentComponent = new DocumentCellComponent();
	}

	public Object getCellEditorValue() {
		return null;
	}

	@Override
	public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
		Document document = (Document) value;
		documentComponent.displayData(document, isSelected, table);

		return documentComponent;
	}

}
