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

public class Tabelle {
    
    private final String name;
    
    private Tabelle(String name) {this.name = name;}
    public String toString() {return name;}
   
    public static final Tabelle LABORDATEN = new Tabelle("labordaten");
    public static final Tabelle MASSENDATEN = new Tabelle("massendaten");
    public static final Tabelle NULL = new Tabelle("null");

}
    



