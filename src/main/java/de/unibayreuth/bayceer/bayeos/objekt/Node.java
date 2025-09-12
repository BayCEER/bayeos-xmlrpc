package de.unibayreuth.bayceer.bayeos.objekt;

import java.util.Date;

@ArraySerializable
public class Node
{
	@ArrayElement(0)
	 protected Boolean checkWrite;
	@ArrayElement(1)
	 protected Boolean checkExec;
	@ArrayElement(2)
	 protected Integer id;
	@ArrayElement(3)
	 protected Integer idSuper;
	@ArrayElement(4)
	 protected String art;
	@ArrayElement(5)
	 protected String name;
	@ArrayElement(6)
	 protected Date recStart;
	@ArrayElement(7)
	 protected Date recEnd;
	@ArrayElement(8)
	 protected Date planStart;
	@ArrayElement(9)
	 protected Date planEnd;
	@ArrayElement(10)
	 protected Boolean active;
	@ArrayElement(11)
	 protected Boolean recordsMissing;
	@ArrayElement(12)
	 protected Boolean hasChild;
	
	
	
	
	public Node() {
		
	}
		
	public Boolean getCheckWrite() {
		return checkWrite;
	}
	public void setCheckWrite(Boolean checkWrite) {
		this.checkWrite = checkWrite;
	}
	public Boolean getCheckExec() {
		return checkExec;
	}
	public void setCheckExec(Boolean checkExec) {
		this.checkExec = checkExec;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getIdSuper() {
		return idSuper;
	}
	public void setIdSuper(Integer idSuper) {
		this.idSuper = idSuper;
	}
	public String getArt() {
		return art;
	}
	public void setArt(String art) {
		this.art = art;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getRecStart() {
		return recStart;
	}
	public void setRecStart(Date recStart) {
		this.recStart = recStart;
	}
	public Date getRecEnd() {
		return recEnd;
	}
	public void setRecEnd(Date recEend) {
		this.recEnd = recEend;
	}
	public Date getPlanStart() {
		return planStart;
	}
	public void setPlanStart(Date planStart) {
		this.planStart = planStart;
	}
	public Date getPlanEnd() {
		return planEnd;
	}
	public void setPlanEnd(Date planEnd) {
		this.planEnd = planEnd;
	}
	public Boolean getActive() {
		return active;
	}
	public void setActive(Boolean active) {
		this.active = active;
	}
	public Boolean getRecordsMissing() {
		return recordsMissing;
	}
	public void setRecordsMissing(Boolean recordsMissing) {
		this.recordsMissing = recordsMissing;
	}
	public Boolean getHasChild() {
		return hasChild;
	}
	public void setHasChild(Boolean hasChild) {
		this.hasChild = hasChild;
	}
}
	
    
	

  
  