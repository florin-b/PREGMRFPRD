package tiparire.view;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import tiparire.model.Articol;

@SuppressWarnings("serial")
public class ArticolTableModelPalet extends AbstractTableModel {

	List<Articol> articol;

	public ArticolTableModelPalet() {

	}

	private String[] colNames = { "Nr.", "Nume articol", "Cod articol", "Cant. initiala", "Um", "Depozit",
			"Cant. modificata", "Modificari" };

	public int getColumnCount() {
		return colNames.length;
	}

	public int getRowCount() {
		return (articol == null) ? 0 : articol.size();
	}

	public void setData(List<Articol> articol) {
		this.articol = articol;
	}

	@Override
	public String getColumnName(int column) {
		return colNames[column];
	}

	@Override
	public Object getValueAt(int row, int col) {

		Articol art = articol.get(row);

		switch (col) {
		case 0:
			return art.getPozitie();
		case 1:
			return art.getNume();
		case 2:
			return art.getCod();
		case 3:
			return art.getCantitate();
		case 4:
			return art.getUm();
		case 5:
			return art.getDepozit();
		case 6:
			return art.getCantitateModificata();
		case 7:
			return art.getModificare();

		}

		return null;

	}
}