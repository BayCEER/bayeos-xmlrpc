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

public class Right {
	private final String name;
    
    private Right(String name) {
    	this.name = name;
    }
    
    public String toString() {return name;}
    
    public boolean equals(Right right) {
        return this.toString().equalsIgnoreCase(name.toString());
    }
    
    public static final Right read = new Right("read");
    public static final Right write = new Right("write");
    public static final Right exec = new Right("exec");
    public static final Right inherit = new Right("inherit");
    
    public static Right get(String value) throws IllegalArgumentException {
        if (value.equalsIgnoreCase("read")) {
            return read;        
        } else if (value.equalsIgnoreCase("write")) {
             return write;
        } else if (value.equalsIgnoreCase("exec")) {
            return exec;
        } else if (value.equalsIgnoreCase("inherit")) {
            return inherit;    
        } else {
            throw new IllegalArgumentException("Right not supported");
        }
    }
    
    
    
}
