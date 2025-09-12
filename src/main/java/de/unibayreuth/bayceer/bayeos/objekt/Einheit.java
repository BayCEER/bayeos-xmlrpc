
package de.unibayreuth.bayceer.bayeos.objekt;

@ArraySerializable
public class Einheit  {

	@ArrayElement(20)
	protected String bezeichnung;
	@ArrayElement(21)
	protected String beschreibung;
	@ArrayElement(22)
	protected String symbol;

	public String getBezeichnung() {
		return bezeichnung;
	}

	public void setBezeichnung(String bezeichnung) {
		this.bezeichnung = bezeichnung;
	}

	public String getBeschreibung() {
		return beschreibung;
	}

	public void setBeschreibung(String beschreibung) {
		this.beschreibung = beschreibung;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

}
