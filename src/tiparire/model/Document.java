package tiparire.model;

import java.io.Serializable;
import java.util.Hashtable;

import org.ksoap2.serialization.KvmSerializable;
import org.ksoap2.serialization.PropertyInfo;

import tiparire.enums.EnumTipTransport;

@SuppressWarnings("serial")
public class Document implements KvmSerializable, Serializable {

	private String id;
	private String dataEmitere;
	private String client;
	private String departament;
	private String filiala;
	private String seTipareste;
	private boolean marfaPregatita = true;
	private boolean isTiparit;
	private String tip;
	private String numeSofer;
	private String nrMasina;
	private String infoStatus;
	private EnumTipTransport tipTransport;

	public Document() {

	}

	public Document(String id, String dataEmiterii, String client, String departament, String filiala,
			String seTipareste, boolean marfaPregatita, boolean isTiparit, String tip, String numeSofer,
			String nrMasina, String infoStatus, EnumTipTransport tipTransport) {
		super();
		this.id = id;
		this.dataEmitere = dataEmiterii;
		this.client = client;
		this.departament = departament;
		this.filiala = filiala;
		this.seTipareste = seTipareste;
		this.marfaPregatita = marfaPregatita;
		this.isTiparit = isTiparit;
		this.tip = tip;
		this.numeSofer = numeSofer;
		this.nrMasina = nrMasina;
		this.infoStatus = infoStatus;
		this.tipTransport = tipTransport;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDataEmiterii() {
		return dataEmitere;
	}

	public void setDataEmiterii(String dataEmiterii) {
		this.dataEmitere = dataEmiterii;
	}

	public String getClient() {
		return client;
	}

	public void setClient(String client) {
		this.client = client;
	}

	public String getDepartament() {
		return departament;
	}

	public void setDepartament(String departament) {
		this.departament = departament;
	}

	public String getFiliala() {
		return filiala;
	}

	public void setFiliala(String filiala) {
		this.filiala = filiala;
	}

	public String getSeTipareste() {
		return seTipareste;
	}

	public void setSeTipareste(String seTipareste) {
		this.seTipareste = seTipareste;
	}

	public boolean isMarfaPregatita() {
		return marfaPregatita;
	}

	public void setMarfaPregatita(boolean marfaPregatita) {
		this.marfaPregatita = marfaPregatita;
	}

	public String toString() {
		return id + " , " + dataEmitere + " , " + client + " , " + departament + " , " + filiala;
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

	public Object getProperty(int arg0) {
		switch (arg0) {
		case 0:
			return id;
		case 1:
			return dataEmitere;
		case 2:
			return client;
		case 3:
			return departament;
		case 4:
			return filiala;
		case 5:
			return seTipareste;
		case 6:
			return marfaPregatita;
		case 7:
			return isTiparit;
		case 8:
			return numeSofer;
		case 9:
			return nrMasina;
		case 10:
			return infoStatus;
		case 11:
			return tipTransport.toString();

		}
		return null;
	}

	public int getPropertyCount() {
		return 6;
	}

	public void getPropertyInfo(int index, Hashtable arg1, PropertyInfo info) {
		switch (index) {
		case 0:
			info.type = PropertyInfo.STRING_CLASS;
			info.name = "id";
			break;
		case 1:
			info.type = PropertyInfo.STRING_CLASS;
			info.name = "dataEmitere";
			break;
		case 2:
			info.type = PropertyInfo.STRING_CLASS;
			info.name = "client";
			break;
		case 3:
			info.type = PropertyInfo.STRING_CLASS;
			info.name = "departament";
			break;
		case 4:
			info.type = PropertyInfo.STRING_CLASS;
			info.name = "filiala";
			break;
		case 5:
			info.type = PropertyInfo.STRING_CLASS;
			info.name = "seTipareste";
			break;

		case 6:
			info.type = PropertyInfo.BOOLEAN_CLASS;
			info.name = "marfaPregatita";
			break;
		case 7:
			info.type = PropertyInfo.BOOLEAN_CLASS;
			info.name = "isTiparit";
			break;
		case 8:
			info.type = PropertyInfo.STRING_CLASS;
			info.name = "numeSofer";
			break;
		case 9:
			info.type = PropertyInfo.STRING_CLASS;
			info.name = "nrMasina";
			break;
		case 10:
			info.type = PropertyInfo.STRING_CLASS;
			info.name = "infoStatus";
			break;

		case 11:
			info.type = PropertyInfo.STRING_CLASS;
			info.name = "tipTransport";
			break;

		default:
			break;

		}

	}

	public void setProperty(int index, Object value) {
		switch (index) {
		case 0:
			id = value.toString();
			break;
		case 1:
			dataEmitere = value.toString();
			break;
		case 2:
			client = value.toString();
			break;
		case 3:
			departament = value.toString();
			break;
		case 4:
			filiala = value.toString();
			break;
		case 5:
			seTipareste = value.toString();
			break;

		default:
			break;
		}

	}

}
