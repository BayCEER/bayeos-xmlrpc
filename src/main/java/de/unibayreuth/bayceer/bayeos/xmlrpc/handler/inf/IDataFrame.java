package de.unibayreuth.bayceer.bayeos.xmlrpc.handler.inf;

import java.util.Vector;

import org.apache.xmlrpc.XmlRpcException;
/**
 * 
 * 	Manage data frames (secured)
 *  
 *  @author oliver
 *  @since 1.9.0
 *  
 */
public interface IDataFrame {	
	
	
	/** Get rows of a DataFrame  
	 * @param frameId DataFrame id 
	 * @param rowIndexes Row indexes as vector of Integer values, when null all rows are returned 
	 * @return Vector[Vector&lt;colMeta&gt;, Vector&lt;rowData&gt;]	 						 
	 *   
	 *   <p>colMeta: Vector&lt;Object&gt;</p>
	 *   <ul>
	 * 		<li>[0]: colId as Integer</li>
	 * 		<li>[1]: colIndex as Integer</li>
	 * 		<li>[2]: colType as String</li>
	 * 		<li>[3]: colLabel as String</li>
	 *   </ul>  
	 *   	
	 *   rowData: Vector&lt;Object&gt;
	 *   	with Object type in {STRING|DOUBLE|INTEGER|DATE|BOOLEAN} according to column definition
	 *  
	 *  
	 * @throws XmlRpcException
	 * @since 1.9.0
	 */
	public Vector getFrameRows(Integer frameId, Vector rowIndexes) throws XmlRpcException;
		
			
	/** Write the column values to an existing frame column. 
	 * @param colId Column identifier as Integer
	 * @param values Vector&lt;STRING|DOUBLE|INTEGER|DATE|BOOLEAN&gt;
	 * @return True|False	 
	 * @throws XmlRpcException 
	 * @since 1.9.0
	 */
	public Boolean writeColValues(Integer colId, Vector values) throws XmlRpcException;
	
	/** Delete the column values of a frame column. 
	 * @param colId Column identifier
	 * @return True|False
	 * @throws XmlRpcException	  
	 * @since 1.9.0
	 */
	public Boolean deleteColValues(Integer colId) throws XmlRpcException;
	
	
	/**
	 * Get rows by column id and row index. Can be used to produce a data frame out of several columns belonging to different frames. 
	 *  
	 * @param colIds Column ids as vector of Integer values
	 * @param rowIndexes Row indexes as vector of Integer values, when null all rows are returned
	 * @return Vector[Vector&lt;colMeta&gt;, Vector&lt;rowData&gt;]   
	 * @throws XmlRpcException    
	 * @since 1.9.0		
	 * @see #getFrameRows(Integer frameId, Vector rowIndexes)
	 * 		   
	 */
	public Vector getColumnRows(Vector colIds, Vector rowIndexes) throws XmlRpcException;
	
	
	
	/**
	 * Update column values by row index, new values will be inserted 
	 * @param colId Column id as integer
	 * @param rowIndexes Row indexes as vector of Integer values
	 * @param values Vector&lt;STRING|DOUBLE|INTEGER|DATE|BOOLEAN&gt;
	 * @return True|False
	 * @throws XmlRpcException    
	 * @since 1.9.0					
	 */
	public Boolean updateColValues(Integer colId, Vector rowIndexes, Vector values) throws XmlRpcException;
	
	
	/**
	 * Get max row index of a set of columns
	 * @param colIds Column ids as Vector of Integer 
	 * @return Maximum index of columns as Integer  
	 * @throws XmlRpcException    
	 * @since 1.9.0					
	 */
	public Integer getMaxRowIndex(Vector colIds) throws XmlRpcException;
	
	
}
