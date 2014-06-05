package de.unibayreuth.bayceer.bayeos.objekt;

import java.util.Vector;

public class DataFrame extends Objekt {
	
	private String bezeichnung;
	
	private String beschreibung;
	private Integer timezone;

	public DataFrame(Vector v){
		super(v);
		this.bezeichnung = (String)v.elementAt(super.INDEX_COUNT + 0);
		this.beschreibung = (String)v.elementAt(super.INDEX_COUNT + 1);
		this.timezone = (Integer)v.elementAt(super.INDEX_COUNT + 2);		
	}

		
	public Integer getTimezone() {
		return timezone;
	}


	public void setTimezone(Integer timezone) {
		this.timezone = timezone;
	}



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

		
}
