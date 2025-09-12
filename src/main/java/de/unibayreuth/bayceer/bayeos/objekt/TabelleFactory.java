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
 * TabelleFactory.java
 *
 * Created on 27. November 2002, 13:30
 */

package de.unibayreuth.bayceer.bayeos.objekt;


/**
 *
 * @author  oliver
 */
public class TabelleFactory {    

    public static Tabelle getTabelle(String name) {
        if (name == null) return null;
        if (name.equalsIgnoreCase("labordaten")) {
            return Tabelle.LABORDATEN;
        } else if (name.equalsIgnoreCase("massendaten")) {
            return Tabelle.MASSENDATEN;
        }
        return null;
     }
     
     
    
}
