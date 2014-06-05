/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package de.unibayreuth.bayceer.bayeos.objekt;

import java.util.Vector;

/**
 *
 * @author oliver
 */
public  class SimpleObjektNode {
        private String name;
        private Integer id;
        private Integer parent_id;
        private Boolean leaf;
        private ObjektArt art;

    public ObjektArt getArt() {
        return art;
    }

    public void setArt(ObjektArt art) {
        this.art = art;
    }

        


        public SimpleObjektNode(){

        }


        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public Integer getParentId() {
            return parent_id;
        }

        public void setParentId(Integer parent_id) {
            this.parent_id = parent_id;
        }

        public Boolean isLeaf() {
            return leaf;
        }

        public void setLeaf(Boolean leaf) {
            this.leaf = leaf;
        }
        
        public Boolean isRoot(){
        	return getParentId() == null;
        }

        public static SimpleObjektNode getNode(Vector r) {
            SimpleObjektNode d = new SimpleObjektNode();
            d.setId((Integer)r.elementAt(2));
            d.setParentId((Integer)r.elementAt(3));
            d.setArt(ObjektArt.get((String)r.elementAt(4)));
            d.setName((String)r.elementAt(5));
            d.setLeaf(!(Boolean)r.elementAt(12));
            return d;
    }
        
        public static SimpleObjektNode getNode(Object[] r) {
            SimpleObjektNode d = new SimpleObjektNode();
            d.setId((Integer)r[2]);
            d.setParentId((Integer)r[3]);
            d.setArt(ObjektArt.get((String)r[4]));
            d.setName((String)r[5]);
            d.setLeaf(!(Boolean)r[12]);
            return d;
    }

    @Override
        public String toString(){
            return name;
        }
}
