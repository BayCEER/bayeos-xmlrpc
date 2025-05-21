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
 * ObjektFactory.java
 *
 * Created on 27. November 2002, 13:30
 */

package de.unibayreuth.bayceer.bayeos.objekt;




/**
 *
 * @author  oliver
 */
public class ObjektFactory  {
    
    public static final int DEFAULT_INDEX_OBJEKTART = 4;
    
     
    public static Objekt getObjekt(java.util.Vector row)  {
        return getObjekt(row,DEFAULT_INDEX_OBJEKTART);
    }
        
    private static Objekt getObjekt(java.util.Vector row,int indexObjektArt ) {
        String name = (String)row.get(indexObjektArt);
        ObjektArt objektArt = ObjektArt.get(name);
        if (objektArt.equals(ObjektArt.MESSUNG_ORDNER)) {
            return new Messung_Ordner(row);
        } else if (objektArt.equals(ObjektArt.MESSUNG_LABORDATEN)) {
            return new Messung_Labordaten(row);
        } else if (objektArt.equals(ObjektArt.MESSUNG_MASSENDATEN)) {
            return new Messung_Massendaten(row);
        } else if (objektArt.equals(ObjektArt.MESS_KOMPARTIMENT)) {
            return new Mess_Kompartiment(row);
        } else if (objektArt.equals(ObjektArt.MESS_GERAET)) {
            return new Mess_Geraet(row);
        } else if (objektArt.equals(ObjektArt.MESS_EINBAU)) {
            return new Mess_Einbau(row);
        } else if (objektArt.equals(ObjektArt.MESS_ORT)) {
            return new Mess_Ort(row);
        } else if (objektArt.equals(ObjektArt.MESS_ZIEL)) {
            return new Mess_Ziel(row);
        } else if (objektArt.equals(ObjektArt.MESS_EINHEIT)) {
            return new Mess_Einheit(row);
        } else if (objektArt.equals(ObjektArt.BENUTZER)) {
            return new Benutzer(row);
        } else if (objektArt.equals(ObjektArt.GRUPPE)) {
            return new Gruppe(row);            
        } else if (objektArt.equals(ObjektArt.DATA_FRAME)) {
            return new DataFrame(row);
        } else if (objektArt.equals(ObjektArt.DATA_COLUMN)) {
            return new DataColumn(row);
        } else {
            throw new IllegalArgumentException("Can't find a constructor for this ObjektArt.");
        }
        
    }
    
    
    
        
}
