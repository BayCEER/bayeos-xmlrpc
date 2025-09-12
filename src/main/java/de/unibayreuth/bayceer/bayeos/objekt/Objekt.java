package de.unibayreuth.bayceer.bayeos.objekt;

import java.util.Vector;

@ArraySerializable
public class Objekt {
	
	@ArrayElement(0)
	protected Boolean check_write;   
	
	@ArrayElement(1)
    protected Boolean check_exec;    
    
	@ArrayElement(2)
	protected Integer id;      
	
	@ArrayElement(3)
    protected Integer id_super;      
    
	@ArrayElement(4)
	protected String art;   
	@ArrayElement(5)
	protected Integer id_art;        
	@ArrayElement(6)
	protected Integer id_cbenutzer;  
	@ArrayElement(7)
    protected String  cbenutzer;     
	@ArrayElement(8)
    protected java.util.Date ctime;  
	@ArrayElement(9)
    protected Integer id_ubenutzer;  
	@ArrayElement(10)
    protected String  ubenutzer;     
	@ArrayElement(11)
    protected java.util.Date utime;  
	@ArrayElement(12)
    protected java.util.Date dtime;  
	@ArrayElement(13)
    protected String  de;            
	@ArrayElement(14)
    protected String  en;            
	@ArrayElement(15)
    protected java.util.Date rec_start;    
	@ArrayElement(16)
    protected java.util.Date rec_end;     
	@ArrayElement(17)
    protected java.util.Date plan_start;  
	@ArrayElement(18)
    protected java.util.Date plan_end;    
	@ArrayElement(19)
    protected Boolean inherit_perm; 
	
		
  
	
	public Objekt() {
	
	}

    public Vector updateVector() {    	
    	return null;    	
    }

	public Boolean getCheck_write() {
		return check_write;
	}



	public void setCheck_write(Boolean check_write) {
		this.check_write = check_write;
	}



	public Boolean getCheck_exec() {
		return check_exec;
	}



	public void setCheck_exec(Boolean check_exec) {
		this.check_exec = check_exec;
	}



	public Integer getId() {
		return id;
	}



	public void setId(Integer id) {
		this.id = id;
	}



	public Integer getId_super() {
		return id_super;
	}



	public void setId_super(Integer id_super) {
		this.id_super = id_super;
	}



	public String getArt() {
		return art;
	}



	public void setArt(String art) {
		this.art = art;
	}



	public Integer getId_art() {
		return id_art;
	}



	public void setId_art(Integer id_art) {
		this.id_art = id_art;
	}



	public Integer getId_cbenutzer() {
		return id_cbenutzer;
	}



	public void setId_cbenutzer(Integer id_cbenutzer) {
		this.id_cbenutzer = id_cbenutzer;
	}



	public String getCbenutzer() {
		return cbenutzer;
	}



	public void setCbenutzer(String cbenutzer) {
		this.cbenutzer = cbenutzer;
	}



	public java.util.Date getCtime() {
		return ctime;
	}



	public void setCtime(java.util.Date ctime) {
		this.ctime = ctime;
	}



	public Integer getId_ubenutzer() {
		return id_ubenutzer;
	}



	public void setId_ubenutzer(Integer id_ubenutzer) {
		this.id_ubenutzer = id_ubenutzer;
	}



	public String getUbenutzer() {
		return ubenutzer;
	}



	public void setUbenutzer(String ubenutzer) {
		this.ubenutzer = ubenutzer;
	}



	public java.util.Date getUtime() {
		return utime;
	}



	public void setUtime(java.util.Date utime) {
		this.utime = utime;
	}



	public java.util.Date getDtime() {
		return dtime;
	}



	public void setDtime(java.util.Date dtime) {
		this.dtime = dtime;
	}



	public String getDe() {
		return de;
	}



	public void setDe(String de) {
		this.de = de;
	}



	public String getEn() {
		return en;
	}



	public void setEn(String en) {
		this.en = en;
	}



	public java.util.Date getRec_start() {
		return rec_start;
	}



	public void setRec_start(java.util.Date rec_start) {
		this.rec_start = rec_start;
	}



	public java.util.Date getRec_end() {
		return rec_end;
	}



	public void setRec_end(java.util.Date rec_end) {
		this.rec_end = rec_end;
	}



	public java.util.Date getPlan_start() {
		return plan_start;
	}



	public void setPlan_start(java.util.Date plan_start) {
		this.plan_start = plan_start;
	}



	public java.util.Date getPlan_end() {
		return plan_end;
	}



	public void setPlan_end(java.util.Date plan_end) {
		this.plan_end = plan_end;
	}



	public Boolean getInherit_perm() {
		return inherit_perm;
	}



	public void setInherit_perm(Boolean inherit_perm) {
		this.inherit_perm = inherit_perm;
	}
	
	
		

}
