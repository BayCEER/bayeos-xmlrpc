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
/*
 * Messung.java
 *
 * Created on 27. November 2002, 11:43
 */

package de.unibayreuth.bayceer.bayeos.objekt;

import java.util.Vector;

import de.unibayreuth.bayceer.bayeos.client.object.ArrayElement;

/**
 *
 * @author  oliver
 */
public class Messung extends Objekt  {
    
    private String bezeichnung;     
    private String beschreibung;    
    private Integer aufloesung;     
    private Integer intervalTypId;
    private Integer timeZoneId;
    private Double lat;
	private Double lon;
    private Double alt;
    
    /** Creates a new instance of Messung */
    public Messung(final Vector v) {
        super(v);
        this.setBezeichnung((String)v.elementAt(super.INDEX_COUNT + 0));
        this.setBeschreibung((String)v.elementAt(super.INDEX_COUNT + 1));
        this.setAufloesung((Integer)v.elementAt(super.INDEX_COUNT + 2));        
        this.setIntervalTypId((Integer)v.elementAt(super.INDEX_COUNT + 3));
        this.setTimeZoneId((Integer)v.elementAt(super.INDEX_COUNT + 4));
        this.setLat((Double)v.elementAt(super.INDEX_COUNT + 5));
        this.setLon((Double)v.elementAt(super.INDEX_COUNT + 6));
        this.setAlt((Double)v.elementAt(super.INDEX_COUNT + 7));
        
    }
    
    private void setTimeZoneId(Integer id) {
		this.timeZoneId = id;		
	}
    
    public java.lang.Integer getTimeZoneId() {
        return timeZoneId;
    }    


	/** Getter for property bezeichnung.
     * @return Value of property bezeichnung.
     *
     */
    public java.lang.String getBezeichnung() {
        return bezeichnung;
    }
    
    /** Setter for property bezeichnung.
     * @param bezeichnung New value of property bezeichnung.
     *
     */
    public void setBezeichnung(java.lang.String bezeichnung) {
        this.bezeichnung = bezeichnung;
    }
    
    /** Getter for property beschreibung.
     * @return Value of property beschreibung.
     *
     */
    public java.lang.String getBeschreibung() {
        return beschreibung;
    }
    
    /** Setter for property beschreibung.
     * @param beschreibung New value of property beschreibung.
     *
     */
    public void setBeschreibung(java.lang.String beschreibung) {
        this.beschreibung = beschreibung;
    }
    
    /** Getter for property aufloesung.
     * @return Value of property aufloesung.
     *
     */
    public java.lang.Integer getAufloesung() {
        return aufloesung;
    }
    
    /** Setter for property aufloesung.
     * @param aufloesung New value of property aufloesung.
     *
     */
    public void setAufloesung(java.lang.Integer aufloesung) {
        this.aufloesung = aufloesung;
    }
       
    

    /**
     * Getter for property Id_Intervaltyp.
     * @return Value of property Id_Intervaltyp.
     */
    public java.lang.Integer getIntervalTypId() {
        return intervalTypId;
    }    
    
    /**
     * Setter for property Id_Intervaltyp.
     * @param id New value of property intervalTypId.
     */
    public void setIntervalTypId(java.lang.Integer id) {
        this.intervalTypId = id;
    }

	public Double getLat() {
		return lat;
	}

	public void setLat(Double lat) {
		this.lat = lat;
	}

	public Double getLon() {
		return lon;
	}

	public void setLon(Double lon) {
		this.lon = lon;
	}

	public Double getAlt() {
		return alt;
	}

	public void setAlt(Double alt) {
		this.alt = alt;
	}
    
}
