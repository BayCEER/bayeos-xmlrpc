package de.unibayreuth.bayceer.bayeos.objekt;

import java.util.Vector;

public class ObjektNode
{
  private String name;
  private Integer id;
  private Integer parent_id;
  private Boolean leaf;
  private ObjektArt art;

  public ObjektArt getArt()
  {
    return this.art;
  }

  public void setArt(ObjektArt paramObjektArt)
  {
    this.art = paramObjektArt;
  }

  public String getName()
  {
    return this.name;
  }

  public void setName(String paramString)
  {
    this.name = paramString;
  }

  public Integer getId()
  {
    return this.id;
  }

  public void setId(Integer paramInteger)
  {
    this.id = paramInteger;
  }

  public Integer getParentId()
  {
    return this.parent_id;
  }

  public void setParentId(Integer paramInteger)
  {
    this.parent_id = paramInteger;
  }

  public Boolean isLeaf()
  {
    return this.leaf;
  }

  public void setLeaf(Boolean paramBoolean)
  {
    this.leaf = paramBoolean;
  }

  public Boolean isRoot()
  {
    return Boolean.valueOf(getParentId() == null);
  }

  public ObjektNode(Vector paramVector)
  {
    setId((Integer)paramVector.elementAt(2));
    setParentId((Integer)paramVector.elementAt(3));
    setArt(ObjektArt.get((String)paramVector.elementAt(4)));
    setName((String)paramVector.elementAt(5));
    setLeaf(Boolean.valueOf(!((Boolean)paramVector.elementAt(12)).booleanValue()));    
  }

  public String toString()
  {
    return this.name;
  }
}
