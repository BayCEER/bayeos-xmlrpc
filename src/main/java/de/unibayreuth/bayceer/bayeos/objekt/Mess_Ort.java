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
 * Messort.java
 *
 * Created on 29. November 2002, 10:42
 */

package de.unibayreuth.bayceer.bayeos.objekt;

import java.util.Vector;

/**
 *
 * @author  oliver
 */
public class Mess_Ort extends Objekt  {
    
    private String bezeichnung;
    
    private String beschreibung;
    
    private Double x;
    
    private Double y;
    
    private Double z;
    
    private Integer crs_id;
    
    public Integer getCRSId() {
		return crs_id;
	}

	public void setCRSId(Integer id) {
		this.crs_id = id;
	}

	/** Creates a new instance of Messort */
    @SuppressWarnings("static-access")
	public Mess_Ort(Vector v) {
        super(v);
        this.bezeichnung = (String)v.elementAt(super.INDEX_COUNT + 0);
        this.beschreibung = (String)v.elementAt(super.INDEX_COUNT + 1);
        this.x = (Double)v.elementAt(super.INDEX_COUNT + 2);
        this.y = (Double)v.elementAt(super.INDEX_COUNT + 3);
        this.z = (Double)v.elementAt(super.INDEX_COUNT + 4);
        this.crs_id = (Integer)v.elementAt(super.INDEX_COUNT + 5);        
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
    
    /** Getter for property x.
     * @return Value of property x.
     *
     */
    public java.lang.Double getX() {
        return x;
    }
    
    /** Setter for property x.
     * @param x New value of property x.
     *
     */
    public void setX(java.lang.Double x) {
        this.x = x;
    }
    
    /** Getter for property y.
     * @return Value of property y.
     *
     */
    public java.lang.Double getY() {
        return y;
    }
    
    /** Setter for property y.
     * @param y New value of property y.
     *
     */
    public void setY(java.lang.Double y) {
        this.y = y;
    }
    
    /** Getter for property z.
     * @return Value of property z.
     *
     */
    public java.lang.Double getZ() {
        return z;
    }
    
    /** Setter for property z.
     * @param z New value of property z.
     *
     */
    public void setZ(java.lang.Double z) {
        this.z = z;
    }
    
    
  
    
    

    
}
