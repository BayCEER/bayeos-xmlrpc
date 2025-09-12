package de.unibayreuth.bayceer.bayeos.objekt;

public class Art {
	
	
	
	
	
	public static final String WEB_ORDNER = "web_ordner";
	public static final String WEB_LS = "web_ls";
	public static final String WEB_MIT = "web_mit";
	public static final String WEB_PRO = "web_pro";
	
	public static final String AGGREGATION = "aggregation";
	public static final String MESSUNG = "messung";
	public static final String MESS_EINHEIT = "mess_einheit";
	public static final String MESS_EINBAU = "mess_einbau";
	public static final String MESSUNG_LABORDATEN = "messung_labordaten";
	public static final String MESSUNG_MASSENDATEN = "messung_massendaten";
	public static final String MESSUNG_ORDNER = "messung_ordner";
	public static final String MESS_ZIEL = "mess_ziel";
	public static final String MESS_ORT = "mess_ort";
	public static final String MESS_KOMPARTIMENT = "mess_kompartiment";
	public static final String MESS_GERAET = "mess_geraet";
	public static final String GRUPPE = "gruppe";
	public static final String BENUTZER = "benutzer";
	
	public static final String DATA_FRAME = "data_frame";
	public static final String DATA_COLUMN = "data_column";	
	
	
	
	public static boolean isExtern(String art) {
		return art.startsWith("WEB");
	}
		
	
}
	
	
	



