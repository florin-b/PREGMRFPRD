package tiparire.model;

public enum EnumLogonStatus {

	Status_0("Cont inexistent"), Status_1("Cont blocat"), Status_2("Parola incorecta"), Status_4("Cont inactiv"), Status_5(
			"Acces interzis");

	private String statusName;

	private EnumLogonStatus(String statusName) {
		this.statusName = statusName;

	}

	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

}
