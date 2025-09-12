package de.unibayreuth.bayceer.bayeos.objekt;

import java.util.Date;

import junit.framework.TestCase;

public class TestArrayConverter extends TestCase {

	
	public void testToArray() {
		
		TestArrayObjekt o = new TestArrayObjekt(null, null, null, null, null);		
		
		try {
			Object[] oa = ArrayConverter.toArray(o);			
			assertEquals(5, oa.length);
			for (Object object : oa) {
				assertNull(object);
			}
			
		} catch (ArraySerializationException e) {
			fail(e.getMessage());
		}
		
	}
	
	
	public void testWithNullToClass() {
		Date d = new Date();
		Object[] oa = {12f,12,null,"!",d};
		try {
			TestArrayObjekt o =  ArrayConverter.toClass(TestArrayObjekt.class,oa);	
			assertNotNull(o);
			assertEquals(12f, o.floatValue);
			assertEquals((Integer)12, o.intValue);
			assertNull(o.doubleValue);
			assertEquals("!", o.stringValue);
			assertEquals(d, o.dateValue);
									
		} catch (ArraySerializationException e) {
		
			fail(e.getMessage());
		} 
	}
	
	
	
	public void testToClass() {
		Date d = new Date();
		Object[] oa = {12f,12,11d,"!",d};
		try {
			TestArrayObjekt o =  ArrayConverter.toClass(TestArrayObjekt.class,oa);	
			assertNotNull(o);
			assertEquals(12f, o.floatValue);
			assertEquals((Integer)12, o.intValue);
			assertEquals(11d, o.doubleValue);
			assertEquals("!", o.stringValue);
			assertEquals(d, o.dateValue);
									
		} catch (ArraySerializationException e) {
		
			fail(e.getMessage());
		} 
	}
	
	
}
