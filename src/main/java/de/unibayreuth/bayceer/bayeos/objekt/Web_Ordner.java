package de.unibayreuth.bayceer.bayeos.objekt;

import java.util.Vector;

public class Web_Ordner {
	
	Integer id;
	Integer id_super;
	String de;
	String uname;
	
	public Web_Ordner(){		
	}
	
	public Web_Ordner(final Vector row){
		this.id = (Integer) row.get(0);
		this.id_super = (Integer)row.get(1);		
		this.de = (String)row.get(2);
		this.uname = (String)row.get(3);				
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
	public String getDe() {
		return de;
	}
	public void setDe(String de) {
		this.de = de;
	}
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	
	
}
