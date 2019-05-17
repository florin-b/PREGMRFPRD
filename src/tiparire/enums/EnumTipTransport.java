package tiparire.enums;

public enum EnumTipTransport {

	TCLI("Transport client"), TRAP("Transport Arabesque"), TERT("Trasnport tert");

	EnumTipTransport(String tipTransport) {
		this.tipTransport = tipTransport;
	}

	private String tipTransport;

	public String getTipTransport() {
		return tipTransport;
	}

	public void setTipTransport(String tipTransport) {
		this.tipTransport = tipTransport;
	}

	@Override
	public String toString() {
		return this.tipTransport;
	}
}
