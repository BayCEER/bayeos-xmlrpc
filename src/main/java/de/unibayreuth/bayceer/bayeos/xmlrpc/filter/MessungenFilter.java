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
 * MessungenFilter.java
 *
 * Created on 6. Mai 2004, 15:24
 */

package de.unibayreuth.bayceer.bayeos.xmlrpc.filter;

import java.util.Vector;

/**
 *
 * @author  oliver
 */
public class MessungenFilter implements VectorRizeable {
    
    private Vector values;
    
        
    /** Creates a new instance of MessungenFilter */
    public MessungenFilter(Vector values) {
        this.values = values;
    }
    
    public MessungenFilter(){
        values = new Vector();
    }
    
    public void add(Integer id, String objektArt){
        Vector r = new Vector();
        r.add(id);
        r.add(objektArt);
        values.add(r);
    }
    
    public void remove(Integer id ,String objektArt) {
        Vector r = new Vector();
        r.add(id);
        r.add(objektArt);
        values.remove(r);
    }
    
    public Integer getId(int index){
        Vector row = (Vector)values.elementAt(index);
        return (Integer)row.elementAt(0);
    }
    
    
    public String getObjektArt(int index){
        Vector row = (Vector)values.elementAt(index);
        return (String)row.elementAt(1);
    }    
    
    public Vector getVector() {
        return values;
    }
    
    public static void main(String[] args){
        MessungenFilter f = new MessungenFilter();
        f.add(new Integer(10), "messungen");
        System.out.println("" + f.getId(0));
        System.out.println("" + f.getObjektArt(0));
        System.out.println(""+ f.getVector().size());
        f.remove(new Integer(10), "messungen");
        System.out.println(""+ f.getVector().size());
    }
}


