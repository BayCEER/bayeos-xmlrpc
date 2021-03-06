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
 * Messziel.java
 *
 * Created on 29. November 2002, 09:45
 */

package de.unibayreuth.bayceer.bayeos.objekt;

import java.util.Vector;

/**
 *
 * @author  oliver
 */
public class Mess_Ziel extends Objekt  {
    
    private String bezeichnung;
    
    private String beschreibung;
    
    private String formel;
    
    /** Creates a new instance of Messziel */
    public Mess_Ziel(Vector v) {
        super(v);
        this.bezeichnung = (String)v.elementAt(super.INDEX_COUNT + 0);
        this.beschreibung = (String)v.elementAt(super.INDEX_COUNT + 1);
        this.formel = (String)v.elementAt(super.INDEX_COUNT + 2);
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
    
    /** Getter for property formel.
     * @return Value of property formel.
     *
     */
    public java.lang.String getFormel() {
        return formel;
    }
    
    /** Setter for property formel.
     * @param formel New value of property formel.
     *
     */
    public void setFormel(java.lang.String formel) {
        this.formel = formel;
    }
    
 
    
}
