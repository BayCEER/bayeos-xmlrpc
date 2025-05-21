package de.unibayreuth.bayceer.bayeos.objekt;

import java.util.Vector;


public class DataColumn extends Objekt {
	
	private String bezeichnung;
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


	private String beschreibung;
	private Integer colIndex;
	private String dataType;
		
	public DataType getDataType() {
		return DataType.valueOf(dataType);
	}

	public void setDataType(DataType value) {
		this.dataType = value.toString();
	}


	public DataColumn(Vector v){
		super(v);
		this.bezeichnung = (String)v.elementAt(super.INDEX_COUNT + 0);
		this.beschreibung = (String)v.elementAt(super.INDEX_COUNT + 1);
		this.colIndex = (Integer)v.elementAt(super.INDEX_COUNT + 2);
		this.dataType = (String)v.elementAt(super.INDEX_COUNT + 3);
	}
		
	
	public Integer getColIndex() {
		return colIndex;
	}


	public void setColIndex(Integer index) {
		this.colIndex = index;
	}


	

			

}
