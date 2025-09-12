package de.unibayreuth.bayceer.bayeos.objekt;


@ArraySerializable
public class Benutzer  {
    
	@ArrayElement(20)
    protected String login;
	@ArrayElement(21)
    protected Boolean locked;
	
	
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public Boolean getLocked() {
		return locked;
	}
	public void setLocked(Boolean locked) {
		this.locked = locked;
	}
    
    
    
}
