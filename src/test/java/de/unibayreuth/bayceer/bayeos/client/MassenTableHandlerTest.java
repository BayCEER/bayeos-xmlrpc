package de.unibayreuth.bayceer.bayeos.client;

import java.nio.ByteBuffer;
import java.util.Date;
import java.util.Random;

import org.junit.Test;

public class MassenTableHandlerTest extends AbstractClientTest{

	
	
	@Test
	public void testAddByteRowsVolume() throws Exception {		
		Integer id = 200108;
				
		
		
		Long start = new Date().getTime();		
		ByteBuffer b = ByteBuffer.allocate(1000*16);
		for(int c=0;c<10*1000;c++){
			System.out.println("c:"+ c);
			for(int i=0;i<1000;i++){
				b.putInt(id);			
				b.putLong(start);
				start = start + 1000;
				b.putFloat(new Random().nextFloat()*100);
			}					
			Object[] a = new Object[1];
			a[0] = b.array();				
			cli.getXmlRpcClient().execute("MassenTableHandler.addByteRows",a);
			b.rewind();
		}
		
	

	}

}
