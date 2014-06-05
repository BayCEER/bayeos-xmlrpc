package de.unibayreuth.bayceer.bayeos.objekt;


// DataType for Column in Frames 
public enum DataType {	
	STRING, DOUBLE, INTEGER, DATE, BOOLEAN;	
	
	
	
	public static DataType fromString(String value) {
		switch (value) {
			case "STRING": return STRING;
			case "INTEGER": return INTEGER;
			case "DOUBLE": return DOUBLE;
			case "DATE": return DATE;
			case "BOOLEAN": return BOOLEAN;
			default: return null;
		}
	}
			
		
}



