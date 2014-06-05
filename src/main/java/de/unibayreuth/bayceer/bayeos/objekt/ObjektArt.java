/*******************************************************************************
 * Copyright (c) 2011 University of Bayreuth - BayCEER.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/old-licenses/gpl-2.0.html
 * 
 * Contributors:
 *     University of Bayreuth - BayCEER - initial API and implementation
 ******************************************************************************/
package de.unibayreuth.bayceer.bayeos.objekt;

public class ObjektArt {
	
	private static final String KEY_WEB_ORDNER = "web_ordner";
	private static final String KEY_WEB_LS = "web_ls";
	private static final String KEY_WEB_MIT = "web_mit";
	private static final String KEY_WEB_PRO = "web_pro";
	
	private static final String KEY_AGGREGATION = "aggregation";
	private static final String KEY_MESSUNG = "messung";
	private static final String KEY_MESS_EINHEIT = "mess_einheit";
	private static final String KEY_MESS_EINBAU = "mess_einbau";
	private static final String KEY_MESSUNG_LABORDATEN = "messung_labordaten";
	private static final String KEY_MESSUNG_MASSENDATEN = "messung_massendaten";
	private static final String KEY_MESSUNG_ORDNER = "messung_ordner";
	private static final String KEY_MESS_ZIEL = "mess_ziel";
	private static final String KEY_MESS_ORT = "mess_ort";
	private static final String KEY_MESS_KOMPARTIMENT = "mess_kompartiment";
	private static final String KEY_MESS_GERAET = "mess_geraet";
	private static final String KEY_GRUPPE = "gruppe";
	private static final String KEY_BENUTZER = "benutzer";
	
	private static final String KEY_DATA_FRAME = "data_frame";
	private static final String KEY_DATA_COLUMN = "data_column";	
	
	
	public static final ObjektArt BENUTZER = new ObjektArt(KEY_BENUTZER);
    public static final ObjektArt GRUPPE = new ObjektArt(KEY_GRUPPE);
    public static final ObjektArt MESS_GERAET = new ObjektArt(KEY_MESS_GERAET);
    public static final ObjektArt MESS_KOMPARTIMENT = new ObjektArt(KEY_MESS_KOMPARTIMENT);
    public static final ObjektArt MESS_ORT = new ObjektArt(KEY_MESS_ORT);
    public static final ObjektArt MESS_ZIEL = new ObjektArt(KEY_MESS_ZIEL);
    public static final ObjektArt MESSUNG_ORDNER  = new ObjektArt(KEY_MESSUNG_ORDNER);
    public static final ObjektArt MESSUNG_MASSENDATEN = new ObjektArt(KEY_MESSUNG_MASSENDATEN);
    public static final ObjektArt MESSUNG_LABORDATEN = new ObjektArt(KEY_MESSUNG_LABORDATEN);
    public static final ObjektArt MESS_EINBAU = new ObjektArt(KEY_MESS_EINBAU);
    public static final ObjektArt MESS_EINHEIT = new ObjektArt(KEY_MESS_EINHEIT); 
    public static final ObjektArt MESSUNG = new ObjektArt(KEY_MESSUNG); 
    public static final ObjektArt AGGREGATION = new ObjektArt(KEY_AGGREGATION);     
        
    public static final ObjektArt PROJEKT = new ObjektArt(KEY_WEB_PRO,true);     
    public static final ObjektArt MITARBEITER = new ObjektArt(KEY_WEB_MIT,true);         
    public static final ObjektArt LEHRSTUHL = new ObjektArt(KEY_WEB_LS,true);     
    public static final ObjektArt WEB_ORDNER = new ObjektArt(KEY_WEB_ORDNER,true);
    
    public static final ObjektArt DATA_FRAME = new ObjektArt(KEY_DATA_FRAME);
    public static final ObjektArt DATA_COLUMN = new ObjektArt(KEY_DATA_COLUMN);
        
    
    private final String name;
    private final Boolean extern;
    
    public Boolean isExtern() {
		return extern;
	}
   
    
    private ObjektArt(String name) {
    	this(name,false);
    }
    
    private ObjektArt(String name, boolean extern){
    	this.name = name;
    	this.extern = extern;
    }
    
    public String toString() {return name;}
    
    @Override
    public boolean equals(Object obj) {
    	return this.toString().equalsIgnoreCase(obj.toString());    	
    }
    
    
    public static ObjektArt get(String art) throws IllegalArgumentException {
        if (art.equalsIgnoreCase(KEY_BENUTZER)) {
            return BENUTZER;
        } else if (art.equalsIgnoreCase(KEY_GRUPPE)) {
            return GRUPPE;
        } else if (art.equalsIgnoreCase(KEY_MESS_KOMPARTIMENT)) {
             return MESS_KOMPARTIMENT;
        }else if (art.equalsIgnoreCase(KEY_MESS_ORT)) {
             return MESS_ORT;
        }else if (art.equalsIgnoreCase(KEY_MESS_ZIEL)) {
             return MESS_ZIEL;
        }else if (art.equalsIgnoreCase(KEY_MESSUNG)) {
             return MESSUNG;
        }else if (art.equalsIgnoreCase(KEY_MESSUNG_ORDNER)) {
             return MESSUNG_ORDNER;
        }else if (art.equalsIgnoreCase(KEY_MESSUNG_LABORDATEN)) {
             return MESSUNG_LABORDATEN;
        }else if (art.equalsIgnoreCase(KEY_MESS_EINHEIT)) {
             return MESS_EINHEIT;
        }else if (art.equalsIgnoreCase(KEY_MESSUNG_MASSENDATEN)) {
             return MESSUNG_MASSENDATEN;
        } else if (art.equalsIgnoreCase(KEY_MESS_EINBAU)) {
             return MESS_EINBAU;
        } else if (art.equalsIgnoreCase(KEY_MESS_GERAET)) {
             return MESS_GERAET;
        } else if (art.equalsIgnoreCase(KEY_WEB_MIT)) {
             return MITARBEITER;
        } else if (art.equalsIgnoreCase(KEY_WEB_PRO)) {
             return PROJEKT;
        } else if (art.equalsIgnoreCase(KEY_WEB_LS)) {
             return LEHRSTUHL;
        } else if (art.equalsIgnoreCase(KEY_WEB_ORDNER)) {
            return WEB_ORDNER;                                         
        } else if (art.equalsIgnoreCase(KEY_DATA_FRAME)) {
            return DATA_FRAME;                                         
        } else if (art.equalsIgnoreCase(KEY_DATA_COLUMN)) {
            return DATA_COLUMN;                                         
        } else {
            throw new IllegalArgumentException("ObjektArt:" + art + " not supported.");
        }
    }
    
 }
    



