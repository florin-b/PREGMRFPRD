package tiparire.model;

public enum EnumDepartamente {

	Lemnoase("Lemnoase"), Feronerie("Feronerie"), Parchet("Parchet"), Materiale_grele("Materiale grele"), Electrice(
			"Electrice"), Gisp("Gips"), Chimice("Chimice"), Instalatii("Instalatii"), Hidroizolatii("Hidroizolatii");

	private String nume;

	private EnumDepartamente(String nume) {
		this.nume = nume;
	}

	public String getNume() {
		return nume;
	}

	public void setNume(String nume) {
		this.nume = nume;
	}

	@Override
	public String toString() {
		return nume;
	}

}
