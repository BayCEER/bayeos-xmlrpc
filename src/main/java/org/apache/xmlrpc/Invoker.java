package org.apache.xmlrpc;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Invoker implements XmlRpcHandler


{
    private Object invokeTarget;
    public Object getInvokeTarget() {
		return invokeTarget;
	}

	public void setInvokeTarget(Object invokeTarget) {
		this.invokeTarget = invokeTarget;
	}

	private Class targetClass;
    
    protected final static Logger logger = LoggerFactory.getLogger("Invoker.class");
    

    public Invoker(Object target)
    {
        invokeTarget = target;
        targetClass = (invokeTarget instanceof Class) ? (Class) invokeTarget :  invokeTarget.getClass();        
    }

    // main method, sucht methode in object, wenn gefunden dann aufrufen.
    public Object execute(String methodName, Vector params) throws Exception
    {
        

        Method method = null;

        try
        {
        	List<Method> methods = Arrays.asList(targetClass.getMethods());
			for (Method m : methods) {
				if (m.getName().equals(methodName)){
					method = m;
					break;
				}
			}			
        	
        } catch (SecurityException e) {
        	throw new XmlRpcException(0,"Method " + methodName + " not found in class " + targetClass.getCanonicalName());
		}

        if (method.getDeclaringClass() == Object.class)
        {
            throw new XmlRpcException(0, "Invoker can't call methods defined in java.lang.Object");
        }

        // invoke
        Object returnValue = null;
        try
        {
            returnValue = method.invoke(invokeTarget, params.toArray());
        }
        catch(IllegalAccessException iacc_e)
        {
            throw iacc_e;
        }
        catch(IllegalArgumentException iarg_e)
        {
            throw iarg_e;
        }
        catch(InvocationTargetException it_e)
        {
            logger.error(it_e.getTargetException().getMessage());
            
            // check whether the thrown exception is XmlRpcException
            Throwable t = it_e.getTargetException();
            if (t instanceof XmlRpcException)
            {
                throw (XmlRpcException) t;
            }
            // It is some other exception
            throw new Exception(t.toString());
        }
        return returnValue;
    }
}