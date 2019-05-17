package tiparire.view;

import java.util.List;

import tiparire.model.Document;


public interface DepartamentListener {
	public void departamentSelected(String departament, List<Document> listDocumente);
}
