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
 * StatusFilter.java
 *
 * Created on 6. Mai 2004, 15:46
 */

package de.unibayreuth.bayceer.bayeos.xmlrpc.filter;

import java.util.Iterator;
import java.util.Vector;

/**
 *
 * @author  oliver
 */
public class StatusFilter implements VectorRizeable {
    private Vector values;
    
    /** Creates a new instance of StatusFilter */
    public StatusFilter(Vector values) {
        this.values = values;
    }
    
    public StatusFilter(){
        values = new Vector();
    }
    
    public StatusFilter(int[] status){
    	this();
    	for (int i = 0; i < status.length; i++) {
			values.add(status[i]);
		}
    }
    
    public static StatusFilter getDefault(){
    	return new StatusFilter(new int[]{1,2,3,4,5,6,7,8,9,10});
    }
    
    public Vector getVector() {
        return values;
    }
    
    
    public Vector getIds(){
        return values;
    }
    
    public void setIds(Vector values){
        this.values = values;
    }
    
    public void addId(Integer i){
        values.add(i);
    }
    
    public void removeId(Integer i){
        values.remove(i);
    }
    
    public String toString(){
        StringBuffer b = new StringBuffer();
        Iterator it = values.iterator();
        while(it.hasNext()){
            b.append(it.next());
            b.append(";");
        }
        return b.toString();
    }
            
}
