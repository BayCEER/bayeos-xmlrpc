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
 * Kompartiment.java
 *
 * Created on 29. November 2002, 10:22
 */

package de.unibayreuth.bayceer.bayeos.objekt;

import java.util.Vector;

/**
 *
 * @author  oliver
 */
public class Mess_Kompartiment extends Objekt  {
    
    private String bezeichnung;
    
    private String beschreibung;
    
    /** Creates a new instance of Kompartiment */
    public Mess_Kompartiment(Vector v) {
        super(v);
        this.bezeichnung = (String)v.elementAt(super.INDEX_COUNT + 0);
        this.beschreibung = (String)v.elementAt(super.INDEX_COUNT + 1);
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
    
     
    
}
