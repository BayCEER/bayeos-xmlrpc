package de.unibayreuth.bayceer.bayeos.client.object;

import junit.framework.TestCase;

public class TestObjectConverter extends TestCase {

	
	public void testToArray() {
		
		MessungOrdner o = new MessungOrdner();
		o.setObjektid(10);
		o.setLon(11.9);
		o.setLat(45.0);
		o.setAlt(400.0);
		
		try {
			Object[] oa = ObjectConverter.toArray(o);			
			assertNotNull(oa);
			assertEquals(10,o.getObjektid().intValue());
			assertEquals(11.9,o.getLon());
			assertEquals(45.0,o.getLat());
			assertEquals(400.0,o.getAlt());
			
		} catch (ArraySerializationException e) {
			fail();
		}
		
	}
	
	public void testToObject() {
		Object[] oa = {false,false,10};
		try {
			MessungOrdner o = (MessungOrdner) ObjectConverter.toObject(MessungOrdner.class,oa);	
			assertNotNull(o);
			assertEquals(false,o.getCheck_exec().booleanValue());
			assertEquals(false,o.getCheck_write().booleanValue());
			assertEquals(10, o.getObjektid().intValue());
		} catch (ArraySerializationException e) {
			fail();
		} 
	}

}
