package tiparire.model;

public class UserInfo {

	private String id;
	private String nume;
	private String depart;
	private String filiala;
	private String unitLog;
	private String tipAcces;
	private String logonStatus;
	private String initDepart;
	private String depozit;

	private static UserInfo instance = new UserInfo();

	private UserInfo() {

	}

	public static UserInfo getInstance() {
		return instance;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNume() {
		return nume;
	}

	public void setNume(String nume) {
		this.nume = nume;
	}

	public String getDepart() {
		return depart;
	}

	public void setDepart(String depart) {
		this.depart = depart;
	}

	public String getFiliala() {
		return filiala;
	}

	public void setFiliala(String filiala) {
		this.filiala = filiala;
	}

	public String getLogonStatus() {
		return logonStatus;
	}

	public void setLogonStatus(String logonStatus) {
		this.logonStatus = logonStatus;
	}

	public String getTipAcces() {
		return tipAcces;
	}

	public void setTipAcces(String tipAcces) {
		this.tipAcces = tipAcces;
	}

	public String getUnitLog() {
		return unitLog;
	}

	public void setUnitLog(String unitLog) {
		this.unitLog = unitLog;
	}

	public String getInitDepart() {
		return initDepart;
	}

	public void setInitDepart(String initDepart) {
		this.initDepart = initDepart;
	}

	public String getDepozit() {
		return depozit;
	}

	public void setDepozit(String depozit) {
		this.depozit = depozit;
	}
	
	

}
