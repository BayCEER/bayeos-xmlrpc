package de.unibayreuth.bayceer.bayeos.objekt;

import java.util.Date;

import de.unibayreuth.bayceer.bayeos.objekt.ArrayElement;
import de.unibayreuth.bayceer.bayeos.objekt.ArraySerializable;

@ArraySerializable
public class TestArrayObjekt {
	
	public TestArrayObjekt() {
	
	}
	
	/**
	 * @param floatValue
	 * @param intValue
	 * @param doubleValue
	 * @param stringValue
	 * @param dateValue
	 */
	public TestArrayObjekt(Float floatValue, Integer intValue, Double doubleValue, String stringValue, Date dateValue) {
		super();
		this.floatValue = floatValue;
		this.intValue = intValue;
		this.doubleValue = doubleValue;
		this.stringValue = stringValue;
		this.dateValue = dateValue;
	}

	@ArrayElement(0)
	protected Float floatValue;
	
	@ArrayElement(1)
	protected Integer intValue;
		
	@ArrayElement(2)
	protected Double doubleValue;
		
	@ArrayElement(3)
	protected String stringValue;
	
	@ArrayElement(4)
	protected Date dateValue;

	
}
