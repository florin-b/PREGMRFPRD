package tiparire.model;

import tiparire.enums.EnumTipTransport;

public class Articol {

	private String documentId;
	private String client;
	private String emitere;
	private String cod;
	private String nume;
	private String cantitate;
	private String um;
	private String pozitie;
	private boolean isPregatit;
	private boolean isTiparit;
	private String tip;
	private String depozit;
	private String numeSofer;
	private String nrMasina;
	private String modificare;
	private String cantitateModificata;
	private String infoStatus;
	private EnumTipTransport tipTransport;
	private String cantFractie;
	private String nrPaleti;
	private String umPaleti;
	private String palBuc;

	public Articol() {

	}

	public Articol(String documentId, String client, String emitere, String cod, String nume, String cantitate,
			String um, String pozitie, boolean isPregatit, boolean isTiparit, String tip, String depozit,
			String modificare, String cantitateModificata, String infoStatus, EnumTipTransport tipTransport,
			String cantFractie, String nrPaleti, String umPaleti, String palBuc) {
		super();
		this.documentId = documentId;
		this.client = client;
		this.emitere = emitere;
		this.cod = cod;
		this.nume = nume;
		this.cantitate = cantitate;
		this.um = um;
		this.pozitie = pozitie;
		this.isPregatit = isPregatit;
		this.isTiparit = isTiparit;
		this.tip = tip;
		this.depozit = depozit;
		this.modificare = modificare;
		this.cantitateModificata = cantitateModificata;
		this.infoStatus = infoStatus;
		this.tipTransport = tipTransport;
		this.cantFractie = cantFractie;
		this.nrPaleti = nrPaleti;
		this.umPaleti = umPaleti;
		this.palBuc = palBuc;

	}

	public String getDocumentId() {
		return documentId;
	}

	public void setDocumentId(String documentId) {
		this.documentId = documentId;
	}

	public String getClient() {
		return client;
	}

	public void setClient(String client) {
		this.client = client;
	}

	public String getEmitere() {
		return emitere;
	}

	public void setEmitere(String emitere) {
		this.emitere = emitere;
	}

	public String getCod() {
		return cod;
	}

	public void setCod(String cod) {
		this.cod = cod;
	}

	public String getNume() {
		return nume;
	}

	public void setNume(String nume) {
		this.nume = nume;
	}

	public String getCantitate() {
		return cantitate;
	}

	public void setCantitate(String cantitate) {
		this.cantitate = cantitate;
	}

	public String getUm() {
		return um;
	}

	public void setUm(String um) {
		this.um = um;
	}

	public String getPozitie() {
		return pozitie;
	}

	public void setPozitie(String pozitie) {
		this.pozitie = pozitie;
	}

	public boolean isPregatit() {
		return isPregatit;
	}

	public void setPregatit(boolean isPregatit) {
		this.isPregatit = isPregatit;
	}

	public boolean isTiparit() {
		return isTiparit;
	}

	public void setTiparit(boolean isTiparit) {
		this.isTiparit = isTiparit;
	}

	public String getTip() {
		return tip;
	}

	public void setTip(String tip) {
		this.tip = tip;
	}

	public String getDepozit() {
		return depozit;
	}

	public void setDepozit(String depozit) {
		this.depozit = depozit;
	}

	public String getNumeSofer() {
		return numeSofer;
	}

	public void setNumeSofer(String numeSofer) {
		this.numeSofer = numeSofer;
	}

	public String getNrMasina() {
		return nrMasina;
	}

	public void setNrMasina(String nrMasina) {
		this.nrMasina = nrMasina;
	}

	public String getModificare() {
		return modificare;
	}

	public void setModificare(String modificare) {
		this.modificare = modificare;
	}

	public String getCantitateModificata() {
		return cantitateModificata;
	}

	public void setCantitateModificata(String cantitateModificata) {
		this.cantitateModificata = cantitateModificata;
	}

	public String getInfoStatus() {
		return infoStatus;
	}

	public void setInfoStatus(String infoStatus) {
		this.infoStatus = infoStatus;
	}

	public EnumTipTransport getTipTransport() {
		return tipTransport;
	}

	public void setTipTransport(EnumTipTransport tipTransport) {
		this.tipTransport = tipTransport;
	}

	public String getCantFractie() {
		return cantFractie;
	}

	public void setCantFractie(String cantFractie) {
		this.cantFractie = cantFractie;
	}

	public String getNrPaleti() {
		return nrPaleti;
	}

	public void setNrPaleti(String nrPaleti) {
		this.nrPaleti = nrPaleti;
	}

	public String getUmPaleti() {
		return umPaleti;
	}

	public void setUmPaleti(String umPaleti) {
		this.umPaleti = umPaleti;
	}

	public String getPalBuc() {
		return palBuc;
	}

	public void setPalBuc(String palBuc) {
		this.palBuc = palBuc;
	}

	@Override
	public String toString() {
		return "Articol [documentId=" + documentId + ", client=" + client + ", emitere=" + emitere + ", cod=" + cod
				+ ", nume=" + nume + ", cantitate=" + cantitate + ", um=" + um + ", pozitie=" + pozitie
				+ ", isPregatit=" + isPregatit + ", isTiparit=" + isTiparit + ", tip=" + tip + ", depozit=" + depozit
				+ ", numeSofer=" + numeSofer + ", nrMasina=" + nrMasina + ", modificare=" + modificare
				+ ", cantitateModificata=" + cantitateModificata + ", infoStatus=" + infoStatus + "]";
	}

}
