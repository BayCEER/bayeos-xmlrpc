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

import java.util.Vector;


/**
 *
 * @author  oliver
 */
public class Objekt  {
    
    private Boolean check_write;    // 0
    private Boolean check_exec;     // 1
    private Integer objektid;       // 2
    private Integer id_super;       // 3
    private ObjektArt objektart;    // 4
    private Integer id_art;         // 5
    private Integer id_cbenutzer;   // 6
    private String  cbenutzer;      // 7
    private java.util.Date ctime;   // 8
    private Integer id_ubenutzer;   // 9
    private String  ubenutzer;      // 10
    private java.util.Date utime;   // 11
    private java.util.Date dtime;   // 12
    private String  de;             // 13
    private String  en;             // 14
    private java.util.Date rec_start;     // 15
    private java.util.Date rec_end;     // 16
    private java.util.Date plan_start;     // 17
    private java.util.Date plan_end;     // 18
    private Boolean inherit_perm;   // 19
    
   
    public static final int INDEX_COUNT = 20;
          
    public String toString(){
        return de;
    }
    
    public Objekt(){
    }
    
    public Objekt(final Vector v){
        setCheck_write((Boolean)v.elementAt(0));
        setCheck_exec((Boolean)v.elementAt(1));
        setId((Integer)v.elementAt(2));
        setId_super((Integer)v.elementAt(3));
        setObjektart(ObjektArt.get((String)v.elementAt(4))); 
        setId_art((Integer)v.elementAt(5));
        setId_cbenutzer((Integer)v.elementAt(6));
        setCbenutzer((String)v.elementAt(7));
        setCtime((java.util.Date)v.elementAt(8));
        setId_ubenutzer((Integer)v.elementAt(9));
        setUbenutzer((String)v.elementAt(10));
        setUtime((java.util.Date)v.elementAt(11));
        setDtime((java.util.Date)v.elementAt(12));
        setDe((String)v.elementAt(13));
        setEn((String)v.elementAt(14));
        setPlan_start((java.util.Date)v.elementAt(15));
        setPlan_end((java.util.Date)v.elementAt(16));
        setRec_start((java.util.Date)v.elementAt(17));
        setRec_end((java.util.Date)v.elementAt(18));
        setInherit_perm((Boolean)v.elementAt(19));
    }
    
   
    
    /** Getter for property id.
     * @return Value of property id.
     *
     */
    public java.lang.Integer getId() {
        return objektid;
    }    
     
    /** Setter for property id.
     * @param id New value of property id.
     *
     */
    public void setId(java.lang.Integer id) {
        this.objektid = id;
    }
    
    /** Getter for property id_super.
     * @return Value of property id_super.
     *
     */
    public java.lang.Integer getId_super() {
        return id_super;
    }
    
    /** Setter for property id_super.
     * @param id_super New value of property id_super.
     *
     */
    public void setId_super(java.lang.Integer id_super) {
        this.id_super = id_super;
    }
    
    /** Getter for property id_cbenutzer.
     * @return Value of property id_cbenutzer.
     *
     */
    public java.lang.Integer getId_cbenutzer() {
        return id_cbenutzer;
    }
    
    /** Setter for property id_cbenutzer.
     * @param id_cbenutzer New value of property id_cbenutzer.
     *
     */
    public void setId_cbenutzer(java.lang.Integer id_cbenutzer) {
        this.id_cbenutzer = id_cbenutzer;
    }
    
    /** Getter for property ctime.
     * @return Value of property ctime.
     *
     */
    public java.util.Date getCtime() {
        return ctime;
    }
    
    /** Setter for property ctime.
     * @param ctime New value of property ctime.
     *
     */
    public void setCtime(java.util.Date ctime) {
        this.ctime = ctime;
    }
    
 
    /** Getter for property utime.
     * @return Value of property utime.
     *
     */
    public java.util.Date getUtime() {
        return utime;
    }
    
    /** Setter for property utime.
     * @param utime New value of property utime.
     *
     */
    public void setUtime(java.util.Date utime) {
        this.utime = utime;
    }
    
    /** Getter for property de.
     * @return Value of property de.
     *
     */
    public java.lang.String getDe() {
        return de;
    }
    
    /** Setter for property de.
     * @param de New value of property de.
     *
     */
    public void setDe(java.lang.String de) {
        this.de = de;
    }
    
    
    /** Getter for property id_ubenutzer.
     * @return Value of property id_ubenutzer.
     *
     */
    public java.lang.Integer getId_ubenutzer() {
        return id_ubenutzer;
    }
    
    /** Setter for property id_ubenutzer.
     * @param id_ubenutzer New value of property id_ubenutzer.
     *
     */
    public void setId_ubenutzer(java.lang.Integer id_ubenutzer) {
        this.id_ubenutzer = id_ubenutzer;
    }
    
    /** Getter for property dtime.
     * @return Value of property dtime.
     *
     */
    public java.util.Date getDtime() {
        return dtime;
    }
    
    /** Setter for property dtime.
     * @param dtime New value of property dtime.
     *
     */
    public void setDtime(java.util.Date dtime) {
        this.dtime = dtime;
    }
    
    /** Getter for property en.
     * @return Value of property en.
     *
     */
    public java.lang.String getEn() {
        return en;
    }
    
    /** Setter for property en.
     * @param en New value of property en.
     *
     */
    public void setEn(java.lang.String en) {
        this.en = en;
    }
    
    /** Getter for property inherit_perm.
     * @return Value of property inherit_perm.
     *
     */
    public java.lang.Boolean getInherit_perm() {
        return inherit_perm;
    }
    
    /** Setter for property inherit_perm.
     * @param inherit_perm New value of property inherit_perm.
     *
     */
    public void setInherit_perm(java.lang.Boolean inherit_perm) {
        this.inherit_perm = inherit_perm;
    }
    
    
    /** Getter for property check_write.
     * @return Value of property check_write.
     *
     */
    public java.lang.Boolean getCheck_write() {
        return check_write;
    }
    
    /** Setter for property check_write.
     * @param check_write New value of property check_write.
     *
     */
    public void setCheck_write(java.lang.Boolean check_write) {
        this.check_write = check_write;
    }
    
    /** Getter for property check_exec.
     * @return Value of property check_exec.
     *
     */
    public java.lang.Boolean getCheck_exec() {
        return check_exec;
    }
    
    /** Setter for property check_exec.
     * @param check_exec New value of property check_exec.
     *
     */
    public void setCheck_exec(java.lang.Boolean check_exec) {
        this.check_exec = check_exec;
    }
    
    /** Getter for property objektart.
     * @return Value of property objektart.
     *
     */
    public ObjektArt getObjektart() {
        return objektart;
    }    
    
    /** Setter for property objektart.
     * @param objektart New value of property objektart.
     *
     */
    public void setObjektart(ObjektArt objektart) {
        this.objektart = objektart;
    }
    
   
    
    public boolean hasFullAccess() {
        return (this.check_exec.booleanValue() && this.check_write.booleanValue());
    }
        
    /** Getter for property cbenutzer.
     * @return Value of property cbenutzer.
     *
     */
    public java.lang.String getCbenutzer() {
        return cbenutzer;
    }    
    
    /** Setter for property cbenutzer.
     * @param cbenutzer New value of property cbenutzer.
     *
     */
    public void setCbenutzer(java.lang.String cbenutzer) {
        this.cbenutzer = cbenutzer;
    }
    
    /** Getter for property ubenutzer.
     * @return Value of property ubenutzer.
     *
     */
    public java.lang.String getUbenutzer() {
        return ubenutzer;
    }
    
    /** Setter for property ubenutzer.
     * @param ubenutzer New value of property ubenutzer.
     *
     */
    public void setUbenutzer(java.lang.String ubenutzer) {
        this.ubenutzer = ubenutzer;
    }
    
    
    /**
     * Getter for property id_art.
     * @return Value of property id_art.
     */
    public java.lang.Integer getId_art() {
        return id_art;
    }
    
    /**
     * Setter for property id_art.
     * @param id_art New value of property id_art.
     */
    public void setId_art(java.lang.Integer id_art) {
        this.id_art = id_art;
    }
    
    /**
     * Getter for property mrec_start.
     * @return Value of property mrec_start.
     */
    public java.util.Date getRec_start() {
        return rec_start;
    }
    
    /**
     * Setter for property mrec_start.
     * @param mrec_start New value of property mrec_start.
     */
    public void setRec_start(java.util.Date rec_start) {
        this.rec_start = rec_start;
    }
    
    /**
     * Getter for property mrec_end.
     * @return Value of property mrec_end.
     */
    public java.util.Date getRec_end() {
        return rec_end;
    }
    
    /**
     * Setter for property mrec_end.
     * @param mrec_end New value of property mrec_end.
     */
    public void setRec_end(java.util.Date rec_end) {
        this.rec_end = rec_end;
    }
    
    /**
     * Getter for property plan_start.
     * @return Value of property plan_start.
     */
    public java.util.Date getPlan_start() {
        return plan_start;
    }
    
    /**
     * Setter for property plan_start.
     * @param plan_start New value of property plan_start.
     */
    public void setPlan_start(java.util.Date plan_start) {
        this.plan_start = plan_start;
    }
    
    /**
     * Getter for property plan_end.
     * @return Value of property plan_end.
     */
    public java.util.Date getPlan_end() {
        return plan_end;
    }
    
    /**
     * Setter for property plan_end.
     * @param plan_end New value of property plan_end.
     */
    public void setPlan_end(java.util.Date plan_end) {
        this.plan_end = plan_end;
    }
    
}
