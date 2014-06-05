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
 * TimeFilter.java
 *
 * Created on 6. Mai 2004, 15:39
 */

package de.unibayreuth.bayceer.bayeos.xmlrpc.filter;

import java.util.Vector;

import de.unibayreuth.bayceer.bayeos.xmlrpc.formats.DateFormat;



public class TimeFilter implements VectorRizeable {
    protected java.util.Date von, bis ;
    public TimeFilter(){
        this.von = new java.util.Date();
        this.bis = new java.util.Date();
    }
    
    public TimeFilter(Vector values) {
    	if (values != null){
    		this.von = (java.util.Date)values.elementAt(0);
            this.bis = (java.util.Date)values.elementAt(1);	
    	}
        
    }
    
    public TimeFilter(java.util.Date von, java.util.Date bis) {
        this.bis = bis;
        this.von = von;
    }
                            
    
    public void setVon(java.util.Date value){
        this.von = value;
    }
    
    public void setBis(java.util.Date value){
        this.bis = value;
    }
    
    public java.util.Date getVon(){
        return von;
    }
    public java.util.Date getBis(){
        return bis;
    }
    
    public Vector getVector() {
        Vector r = new Vector(2);
        r.add(von);
        r.add(bis);
        return r;
    }    
    
    public String toString(){
        StringBuffer b = new StringBuffer(DateFormat.defaultDateFormat.format(getVon()));
        b.append(" - ");
        b.append(DateFormat.defaultDateFormat.format(getBis()));
        return b.toString();
    }
    
    public static void main(String[] args){
        TimeFilter f = new TimeFilter(new java.util.Date(),new java.util.Date());
        System.out.println(f.toString());
    }
        
    
}
