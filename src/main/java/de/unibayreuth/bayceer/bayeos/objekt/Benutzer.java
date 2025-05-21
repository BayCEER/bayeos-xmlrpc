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
 * Benutzer.java
 *
 * Created on 29. November 2002, 10:51
 */

package de.unibayreuth.bayceer.bayeos.objekt;


import java.util.Vector;

/**
 *
 * @author  oliver
 */
public class Benutzer extends Objekt {
    
    private String login;
    private Boolean locked;
    
    /** Creates a new instance of Benutzer */
    public Benutzer(Vector v) {
        super(v);
        this.login = (String)v.elementAt(super.INDEX_COUNT + 0);
        this.locked = (Boolean)v.elementAt(super.INDEX_COUNT + 1);
        
    }
    
}
