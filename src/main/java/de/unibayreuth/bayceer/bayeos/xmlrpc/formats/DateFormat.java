package de.unibayreuth.bayceer.bayeos.xmlrpc.formats;

import java.text.SimpleDateFormat;
import java.util.TimeZone;

public class DateFormat {

	
	public final static SimpleDateFormat defaultDateFormat = new ShortDateFormat("dd.MM.yyyy HH:mm:ss");	      
	public final static SimpleDateFormat databaseDateFormat = new ShortDateFormat("yyyy-MM-dd HH:mm:ss 'GMT-1'");    
	public final static SimpleDateFormat importDateFormat = new ShortDateFormat("dd.MM.yyyy HH:mm:ss");
	public final static SimpleDateFormat exportDateFormat = new ShortDateFormat("dd.MM.yyyy HH:mm:ss");
	        

	private static class ShortDateFormat extends SimpleDateFormat  {
	    public ShortDateFormat(String pattern){
	        super(pattern);
	        setTimeZone(TimeZone.getTimeZone("GMT+1"));
	        
	    }
	}



	

	
	
	
}
