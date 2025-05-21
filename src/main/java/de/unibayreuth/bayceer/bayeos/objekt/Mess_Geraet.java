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
 * geraete.java
 *
 * Created on 26. November 2002, 16:12
 */

package de.unibayreuth.bayceer.bayeos.objekt;

import java.util.Vector;


/**
 *
 * @author  oliver
 */
public class Mess_Geraet extends Objekt  {
    
    private String bezeichnung;     
    private String beschreibung;    
    private String seriennr;
    
    public Mess_Geraet(final Vector vector) {
        super(vector);
        this.setBezeichnung((String)vector.elementAt(super.INDEX_COUNT + 0));
        this.setBeschreibung((String)vector.elementAt(super.INDEX_COUNT + 1));
        this.setSeriennr((String)vector.elementAt(super.INDEX_COUNT + 2));
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
    
    /** Getter for property seriennr.
     * @return Value of property seriennr.
     *
     */
    public java.lang.String getSeriennr() {
        return seriennr;
    }
    
    /** Setter for property seriennr.
     * @param seriennr New value of property seriennr.
     *
     */
    public void setSeriennr(java.lang.String seriennr) {
        this.seriennr = seriennr;
    }
    

    
}
