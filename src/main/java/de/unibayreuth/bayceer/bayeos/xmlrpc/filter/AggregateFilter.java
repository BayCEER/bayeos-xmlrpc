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
 * AggregateFilter.java
 *
 * Created on 6. Mai 2004, 16:08
 */

package de.unibayreuth.bayceer.bayeos.xmlrpc.filter;

import java.util.Vector;

/**
 *
 * @author  oliver
 */
public class AggregateFilter implements VectorRizeable {
    
    private Vector values;
    
    /** Creates a new instance of AggregateFilter */
    public AggregateFilter(Vector values) {
    	this.values = values;
    }
    
    public AggregateFilter(){
        this.values = new Vector();
        values.add(null);
        values.add(null);
    }
    
    public AggregateFilter(Integer funcId, Integer intId){
    	this();
    	setFunctionId(funcId);
    	setIntervallId(intId);
    }
    
        
    public Integer getFunctionId(){
        return (Integer)values.elementAt(0);
    }
    
    public Integer getIntervallId(){
        return (Integer)values.elementAt(1);
    }
    

    public void setFunctionId(Integer value){
        values.setElementAt(value,0);
    }
    
    public void setIntervallId(Integer value){
        values.setElementAt(value,1);
    }

    
    public java.util.Vector getVector() {
        return values;
    }
    
    public String toString(){
        return "Function Id:" + getFunctionId() + " Intervall Id:" + getIntervallId();
    }
    
    public static void main(String[] args){
        AggregateFilter a = new AggregateFilter();
        a.setFunctionId(new Integer(19));
        System.out.println("" + a.getFunctionId());
        a.setIntervallId(new Integer(1));
        System.out.println("" + a.getIntervallId());
        
    }
}
