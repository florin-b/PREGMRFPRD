package tiparire.model;

public class TipDocumentAfisat {
	private boolean netiparit;
	private String dataTiparire;

	private static TipDocumentAfisat instance = new TipDocumentAfisat();

	private TipDocumentAfisat() {

	}

	public static TipDocumentAfisat getInstance() {
		return instance;
	}

	public boolean isNetiparit() {
		return netiparit;
	}

	public void setNetiparit(boolean netiparit) {
		this.netiparit = netiparit;
	}

	public String getDataTiparire() {
		return dataTiparire;
	}

	public void setDataTiparire(String dataTiparire) {
		this.dataTiparire = dataTiparire;
	}
	
	

}
