package de.unibayreuth.bayceer.bayeos.xmlrpc.formats;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public class NumericFormat {
	
	
	public final static DecimalFormat defaultDecimalFormat = new DotDecimalFormat("#.####");
	
	private static class DotDecimalFormat extends DecimalFormat  {
	    public DotDecimalFormat(String pattern){
	        super(pattern);
	        DecimalFormatSymbols s = new DecimalFormatSymbols(Locale.GERMAN);
	        s.setDecimalSeparator('.');
	        setDecimalFormatSymbols(s);
	    }
	}

}
