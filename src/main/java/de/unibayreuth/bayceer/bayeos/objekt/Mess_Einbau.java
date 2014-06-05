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
 * Einbau.java
 *
 * Created on 29. November 2002, 10:30
 */

package de.unibayreuth.bayceer.bayeos.objekt;


import java.util.Vector;

/**
 *
 * @author  oliver
 */
public class Mess_Einbau extends Objekt  {
    
    private String bezeichnung;
    private Double hoehe;
    
    /** Creates a new instance of Einbau */
    public Mess_Einbau(Vector v) {
        super(v);
        super.setObjektart(ObjektArt.MESS_EINBAU);
        this.bezeichnung = (String)v.elementAt(super.INDEX_COUNT + 0);
        this.hoehe = (Double)v.elementAt(super.INDEX_COUNT + 1);
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
    
    /** Getter for property hoehe.
     * @return Value of property hoehe.
     *
     */
    public java.lang.Double getHoehe() {
        return hoehe;
    }
    
    /** Setter for property hoehe.
     * @param hoehe New value of property hoehe.
     *
     */
    public void setHoehe(java.lang.Double hoehe) {
        this.hoehe = hoehe;
    }
    
    
    
    
}
