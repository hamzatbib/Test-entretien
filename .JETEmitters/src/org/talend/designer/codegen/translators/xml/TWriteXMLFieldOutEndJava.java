package org.talend.designer.codegen.translators.xml;

import org.talend.core.model.process.INode;
import org.talend.designer.codegen.config.CodeGeneratorArgument;
import org.talend.core.model.process.ElementParameterParser;
import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Map;
import org.talend.core.model.metadata.IMetadataTable;
import org.talend.core.model.metadata.IMetadataColumn;
import org.talend.core.model.metadata.types.JavaType;
import org.talend.core.model.process.EConnectionType;
import org.talend.core.model.utils.NodeUtil;
import org.talend.core.model.process.IConnection;

/**
 * add by xzhang
 */
public class TWriteXMLFieldOutEndJava {

  protected static String nl;
  public static synchronized TWriteXMLFieldOutEndJava create(String lineSeparator)
  {
    nl = lineSeparator;
    TWriteXMLFieldOutEndJava result = new TWriteXMLFieldOutEndJava();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "\t\torg.dom4j.Element ";
  protected final String TEXT_2 = "_c_";
  protected final String TEXT_3 = " = ";
  protected final String TEXT_4 = "_";
  protected final String TEXT_5 = ".addElement(\"xsd:complexType\");" + NL + "\t\torg.dom4j.Element ";
  protected final String TEXT_6 = "_s_";
  protected final String TEXT_7 = ".addElement(\"xsd:sequence\");";
  protected final String TEXT_8 = NL + "\t\torg.dom4j.Element ";
  protected final String TEXT_9 = ".addElement(\"xsd:element\");" + NL + "\t\t";
  protected final String TEXT_10 = ".addAttribute(\"name\",\"";
  protected final String TEXT_11 = "\");" + NL + "\t\t";
  protected final String TEXT_12 = ".addAttribute(\"nillable\",\"true\");";
  protected final String TEXT_13 = NL + "\t\t";
  protected final String TEXT_14 = ".addAttribute(\"minOccurs\",\"0\");" + NL + "\t\t";
  protected final String TEXT_15 = ".addAttribute(\"maxOccurs\",\"unbounded\");";
  protected final String TEXT_16 = ".addElement(\"xsd:attribute\").addAttribute(\"name\",\"";
  protected final String TEXT_17 = "\");";
  protected final String TEXT_18 = NL + "\t\tout_xsd_";
  protected final String TEXT_19 = ".write(\"";
  protected final String TEXT_20 = "  <xsd:complexType>\");" + NL + "\t\tout_xsd_";
  protected final String TEXT_21 = ".newLine();" + NL + "\t\tout_xsd_";
  protected final String TEXT_22 = "    <xsd:sequence>\");" + NL + "\t\tout_xsd_";
  protected final String TEXT_23 = ".newLine();";
  protected final String TEXT_24 = "    </xsd:sequence>\");" + NL + "\t\tout_xsd_";
  protected final String TEXT_25 = "  </xsd:complexType>\");" + NL + "\t\tout_xsd_";
  protected final String TEXT_26 = "</xsd:element>\");" + NL + "\t\tout_xsd_";
  protected final String TEXT_27 = "<xsd:element name=\\\"";
  protected final String TEXT_28 = "\\\" nillable=\\\"true\\\" \"+";
  protected final String TEXT_29 = NL + "\t\t\"minOccurs=\\\"0\\\" maxOccurs=\\\"unbounded\\\"\"+";
  protected final String TEXT_30 = NL + "\t\t\">\");" + NL + "\t\tout_xsd_";
  protected final String TEXT_31 = "<xsd:attribute name= \\\"";
  protected final String TEXT_32 = "\\\"/>\");" + NL + "\t\tout_xsd_";
  protected final String TEXT_33 = NL + "if(nb_line_";
  protected final String TEXT_34 = " > 0){";
  protected final String TEXT_35 = NL + "\tdoc_";
  protected final String TEXT_36 = ".getRootElement().addAttribute(\"xsi:noNamespaceSchemaLocation\", ";
  protected final String TEXT_37 = ");" + NL + "    doc_";
  protected final String TEXT_38 = ".getRootElement().addNamespace(\"xsi\", \"http://www.w3.org/2001/XMLSchema-instance\");";
  protected final String TEXT_39 = "  " + NL + "    nestXMLTool_";
  protected final String TEXT_40 = ".replaceDefaultNameSpace(doc_";
  protected final String TEXT_41 = ".getRootElement());";
  protected final String TEXT_42 = NL + "    nestXMLTool_";
  protected final String TEXT_43 = ".removeEmptyElement(doc_";
  protected final String TEXT_44 = NL + "\tjava.io.StringWriter strWriter_";
  protected final String TEXT_45 = " = new java.io.StringWriter();" + NL + "\torg.dom4j.io.XMLWriter output_";
  protected final String TEXT_46 = " = new org.dom4j.io.XMLWriter(strWriter_";
  protected final String TEXT_47 = ", format_";
  protected final String TEXT_48 = ");" + NL + "\toutput_";
  protected final String TEXT_49 = ".write(doc_";
  protected final String TEXT_50 = ");" + NL + "    output_";
  protected final String TEXT_51 = ".close();";
  protected final String TEXT_52 = NL + "\tString removeHeader_";
  protected final String TEXT_53 = " = strWriter_";
  protected final String TEXT_54 = ".toString();" + NL + "\tif(removeHeader_";
  protected final String TEXT_55 = ".indexOf(\"<?xml\") >=0 ){" + NL + "\t\tremoveHeader_";
  protected final String TEXT_56 = " = removeHeader_";
  protected final String TEXT_57 = ".substring(removeHeader_";
  protected final String TEXT_58 = ".indexOf(\"?>\")+3);" + NL + "\t}" + NL + "\tlistGroupby_";
  protected final String TEXT_59 = ".add(removeHeader_";
  protected final String TEXT_60 = ");";
  protected final String TEXT_61 = "\t\t\t\t" + NL + "                map_";
  protected final String TEXT_62 = ".put(\"json_";
  protected final String TEXT_63 = "\",strWriter_";
  protected final String TEXT_64 = ".toString());" + NL + "                listGroupby_";
  protected final String TEXT_65 = ".add(map_";
  protected final String TEXT_66 = NL + "\t\t\t\t\t";
  protected final String TEXT_67 = " row_";
  protected final String TEXT_68 = " = new ";
  protected final String TEXT_69 = "();" + NL + "\t\t\t\t\t\t  ";
  protected final String TEXT_70 = NL + "\t\t\t\t\t\t\t\trow_";
  protected final String TEXT_71 = ".";
  protected final String TEXT_72 = " = rowStructOutput_";
  protected final String TEXT_73 = ";" + NL + "\t\t\t\t\t\t\t\t";
  protected final String TEXT_74 = NL + "\t\t\t\t\t     \t\trow_";
  protected final String TEXT_75 = ".toString();" + NL + "\t\t\t\t\t     \t\tlistGroupby_";
  protected final String TEXT_76 = ".add(row_";
  protected final String TEXT_77 = ");" + NL + "\t\t    \t\t";
  protected final String TEXT_78 = NL + "\t\t\t\t\t    listGroupby_";
  protected final String TEXT_79 = ".add(strWriter_";
  protected final String TEXT_80 = ".toString());" + NL + "\t\t\t\t\t";
  protected final String TEXT_81 = NL + "\tif (preUnNullMaxIndex_";
  protected final String TEXT_82 = " >= 0) {" + NL + "        // output all buffer" + NL + "        for (int j_";
  protected final String TEXT_83 = " = 0; j_";
  protected final String TEXT_84 = " <= preUnNullMaxIndex_";
  protected final String TEXT_85 = "; j_";
  protected final String TEXT_86 = "++) {" + NL + "            if (startTabs_";
  protected final String TEXT_87 = "[j_";
  protected final String TEXT_88 = "] != null)" + NL + "                out_";
  protected final String TEXT_89 = ".write(startTabs_";
  protected final String TEXT_90 = "]);" + NL + "        }" + NL + "" + NL + "        if (preUnNullMaxIndex_";
  protected final String TEXT_91 = " < preNewTabIndex_";
  protected final String TEXT_92 = " ) {" + NL + "\t\t\tfor (int i_";
  protected final String TEXT_93 = " = preNewTabIndex_";
  protected final String TEXT_94 = " - 1; i_";
  protected final String TEXT_95 = " >= 0; i_";
  protected final String TEXT_96 = "--) {" + NL + "\t\t\t    if(endTabs_";
  protected final String TEXT_97 = "[i_";
  protected final String TEXT_98 = "]!=null){" + NL + "            \t\tout_";
  protected final String TEXT_99 = ".write(endTabs_";
  protected final String TEXT_100 = "]);" + NL + "            \t}" + NL + "                out_";
  protected final String TEXT_101 = ".newLine();" + NL + "                out_";
  protected final String TEXT_102 = ".write(endTabStrs_";
  protected final String TEXT_103 = ".get(i_";
  protected final String TEXT_104 = "));" + NL + "            }" + NL + "        } else {" + NL + "            for (int i_";
  protected final String TEXT_105 = " = preUnNullMaxIndex_";
  protected final String TEXT_106 = "; i_";
  protected final String TEXT_107 = "--) {" + NL + "            \tif(endTabs_";
  protected final String TEXT_108 = "));" + NL + "            }" + NL + "        }" + NL + "    }";
  protected final String TEXT_109 = NL + "\tif(nb_line_";
  protected final String TEXT_110 = " > 0){" + NL + "    \tfor (int i_";
  protected final String TEXT_111 = " = endTabStrs_";
  protected final String TEXT_112 = ".size() - 1; i_";
  protected final String TEXT_113 = "--) {" + NL + "        \tif(endTabs_";
  protected final String TEXT_114 = "]!=null){" + NL + "        \t\tout_";
  protected final String TEXT_115 = "]);" + NL + "        \t}" + NL + "            out_";
  protected final String TEXT_116 = ".newLine();" + NL + "            out_";
  protected final String TEXT_117 = "));" + NL + "        }" + NL + "    }";
  protected final String TEXT_118 = NL + "\tout_";
  protected final String TEXT_119 = ".newLine();" + NL + "\tout_";
  protected final String TEXT_120 = ".close();" + NL + "\t" + NL + "\tlistGroupby_";
  protected final String TEXT_121 = ".toString());";
  protected final String TEXT_122 = NL;
  protected final String TEXT_123 = NL + "\torg.dom4j.Document doc_xsd_";
  protected final String TEXT_124 = "  = org.dom4j.DocumentHelper.createDocument();" + NL + "\t" + NL + "\torg.dom4j.Element root_xsd_";
  protected final String TEXT_125 = " = doc_xsd_";
  protected final String TEXT_126 = ".addElement(\"xsd:schema\");" + NL + "    root_xsd_";
  protected final String TEXT_127 = ".addNamespace(\"xsd\", \"http://www.w3.org/2001/XMLSchema\");";
  protected final String TEXT_128 = NL + "\tjava.io.FileOutputStream stream_xsd_";
  protected final String TEXT_129 = " = new java.io.FileOutputStream(";
  protected final String TEXT_130 = ");" + NL + "    org.dom4j.io.XMLWriter output_xsd_";
  protected final String TEXT_131 = " = new org.dom4j.io.XMLWriter(stream_xsd_";
  protected final String TEXT_132 = ");" + NL + "    output_xsd_";
  protected final String TEXT_133 = ".write(doc_xsd_";
  protected final String TEXT_134 = " );" + NL + "    output_xsd_";
  protected final String TEXT_135 = NL + "\tjava.io.BufferedWriter out_xsd_";
  protected final String TEXT_136 = " = new java.io.BufferedWriter(" + NL + "\t\tnew java.io.OutputStreamWriter(new java.io.FileOutputStream(";
  protected final String TEXT_137 = "), ";
  protected final String TEXT_138 = "));" + NL + "\tout_xsd_";
  protected final String TEXT_139 = ".write(\"<xsd:schema xmlns:xsd=\\\"http://www.w3.org/2001/XMLSchema\\\">\");" + NL + "\tout_xsd_";
  protected final String TEXT_140 = NL + "\tout_xsd_";
  protected final String TEXT_141 = ".write(\"</xsd:schema>\");" + NL + "\tout_xsd_";
  protected final String TEXT_142 = NL + "}" + NL + "globalMap.put(\"";
  protected final String TEXT_143 = "_NB_LINE\",nb_line_";
  protected final String TEXT_144 = ");" + NL + "globalMap.put(\"";
  protected final String TEXT_145 = "_FINISH\" + (listGroupby_";
  protected final String TEXT_146 = "==null?\"\":listGroupby_";
  protected final String TEXT_147 = ".hashCode()), \"true\");" + NL + "\t";
  protected final String TEXT_148 = NL + "\t\ttxf_";
  protected final String TEXT_149 = ".join();" + NL + "\t\t" + NL + "\t\tif(txf_";
  protected final String TEXT_150 = ".getLastException()!=null) {" + NL + "\t\t\tcurrentComponent = txf_";
  protected final String TEXT_151 = ".getCurrentComponent();" + NL + "\t\t\tthrow txf_";
  protected final String TEXT_152 = ".getLastException();" + NL + "\t\t}" + NL + "\t\t" + NL + "\t\tif(txf_";
  protected final String TEXT_153 = ".getLastError()!=null) {" + NL + "\t\t\tthrow txf_";
  protected final String TEXT_154 = ".getLastError();" + NL + "\t\t}" + NL + "\t";
  protected final String TEXT_155 = NL + "resourceMap.put(\"finish_";
  protected final String TEXT_156 = "\", true);";

    static class XMLNode {

        // table parameter of component
        public String name = null;

        public String path = null;

        public String type = null;

        public String column = null;
        
        public String defaultValue = null;
        
        public int order = 0;
        
        public boolean hasDefaultValue = false;

        // special node
        public int special = 0; // 1 is subtree root, 2 is subtree root parent, 4 is main

        // column
        public IMetadataColumn relatedColumn = null;

        public List<IMetadataColumn> childrenColumnList = new ArrayList<IMetadataColumn>();

        // tree variable
        public XMLNode parent = null;

        public List<XMLNode> attributes = new LinkedList<XMLNode>();

        public List<XMLNode> namespaces = new LinkedList<XMLNode>();

        public List<XMLNode> elements = new LinkedList<XMLNode>(); // the main element is the last element

        public XMLNode(String path, String type, XMLNode parent, String column, String value, int order) {
        	this.order = order;
            this.path = path;
            this.parent = parent;
            this.type = type;
            this.column = column;
            this.defaultValue = value;
            if (type.equals("ELEMENT")) {
                this.name = path.substring(path.lastIndexOf("/") + 1);
            } else {
                this.name = path;
            }
        }
        
        public boolean isMainNode(){
            return 4 == (special & 4);
        }
        
        public boolean isSubTreeRoot(){
            return 1 == (special & 1);
        }
        
        public boolean isSubTreeParent(){
            return 2 == (special & 2);
        }
    
        public int getNodeInsertIndex(){
        	int insertIndex =0;
        	if(5==(special & 5)){//group and loop main node
        		if(parent!=null && parent.elements!=null){
            		for(XMLNode tmpNode: parent.elements){
            			if(order <= tmpNode.order){
            				break;
            			}
            			insertIndex++;
            		}
        		}
        	}
        	return insertIndex;
        }
        
        public int getCurrGroupPos(){
        	int currPos =0;
        	if(5==(special & 5)){//group and loop main node
    			XMLNode tmpNode = parent;
    			while(tmpNode!=null && (5==(tmpNode.special & 5))){
    				currPos++;
    				tmpNode = tmpNode.parent;
    			}
        	}
        	return currPos;
        }
    }

    
    // return [0] is root(XMLNode), [1] is groups(List<XMLNode>), [2] loop(XMLNode)
    public Object[] getTree(List<Map<String, String>> rootTable, List<Map<String, String>> groupTable,
            List<Map<String, String>> loopTable, List<IMetadataColumn> colList) {
        List<List<Map<String, String>>> tables = new ArrayList<List<Map<String, String>>>();
        tables.add(rootTable);
        tables.add(groupTable);
        tables.add(loopTable);

        XMLNode root = null;
        List<XMLNode> mains = new ArrayList<XMLNode>();
        List<XMLNode> groups = new ArrayList<XMLNode>();
        XMLNode loop = null;

        XMLNode tmpParent = null;
        XMLNode tmpMainNode = null;
        if (loopTable == null || loopTable.size() == 0) {
            return null;
        }
        int index =0;
        int currOrder = 0;
        String mainPath = loopTable.get(0).get("PATH");
        for (List<Map<String, String>> tmpTable : tables) {
            tmpParent = tmpMainNode;
            for (Map<String, String> tmpMap : tmpTable) {
            	index++;
            	if(tmpMap.get("ORDER")!=null && !"".equals(tmpMap.get("ORDER").trim())){
            		currOrder = Integer.parseInt(tmpMap.get("ORDER"));
            	}else{
            		currOrder = index;
            	}
                XMLNode tmpNew = null;
                if (tmpMap.get("ATTRIBUTE").equals("attri")) {
                    tmpNew = new XMLNode(tmpMap.get("PATH"), "ATTRIBUTE", tmpParent, tmpMap.get("COLUMN"), tmpMap.get("VALUE"), currOrder);
                    tmpParent.attributes.add(tmpNew);
                } else if (tmpMap.get("ATTRIBUTE").equals("ns")) {
                    tmpNew = new XMLNode(tmpMap.get("PATH"), "NAMESPACE", tmpParent, tmpMap.get("COLUMN"), tmpMap.get("VALUE"), currOrder);
                    tmpParent.namespaces.add(tmpNew);
                } else {
                    if (tmpParent == null) {
                        tmpNew = new XMLNode(tmpMap.get("PATH"), "ELEMENT", tmpParent, tmpMap.get("COLUMN"), tmpMap.get("VALUE"), currOrder);
//                        tmpNew.special |= 1;
                        root = tmpNew;
                        mains.add(root);
                    } else {
                        String tmpParentPath = tmpMap.get("PATH").substring(0, tmpMap.get("PATH").lastIndexOf("/"));
                        while (tmpParent != null && !tmpParentPath.equals(tmpParent.path)) {
                            tmpParent = tmpParent.parent;
                        }
                        tmpNew = new XMLNode(tmpMap.get("PATH"), "ELEMENT", tmpParent, tmpMap.get("COLUMN"), tmpMap.get("VALUE"), currOrder);
                        tmpParent.elements.add(tmpNew);
                        if (tmpMap.get("ATTRIBUTE").equals("main")) {
                            if (tmpTable == groupTable) {
                                tmpNew.special |= 1;
                                tmpParent.special |= 2;
                                groups.add(tmpNew);
                            } else if (tmpTable == loopTable) {
                                tmpNew.special |= 1;
                                tmpParent.special |= 2;
                                loop = tmpNew;
                            }else if (tmpTable == rootTable){
                                mains.add(tmpNew);
                            }
                        }
                    }
                    if (tmpMap.get("ATTRIBUTE").equals("main")) {
                        tmpMainNode = tmpNew;
                        tmpNew.special |= 4;
                    }
                    tmpParent = tmpNew;
                }
                setIMetadataColumn(tmpNew, colList);
                setDefaultValues(tmpNew);//add by wliu
            }
        }
        return new Object[] { mains, groups, loop };
    }
    
    private void setDefaultValues(XMLNode node){
    	if(node.defaultValue != null && !"".equals(node.defaultValue)){
    		XMLNode tmp = node;
    		while(tmp !=null){
    			tmp.hasDefaultValue = true;
    			if(tmp.isMainNode()){
    				break;
    			}
    			tmp = tmp.parent;
    		}
    	}
    }

    private void setIMetadataColumn(XMLNode node, List<IMetadataColumn> colList) {
        String value = null;
        JavaType javaType = null;
        if (node.column != null && node.column.length() > 0) {
            for (IMetadataColumn column : colList) {
                if (column.getLabel().equals(node.column)) {
                    node.relatedColumn = column;
                    XMLNode tmp = node;
                    while (tmp != null) {
                        if (!tmp.childrenColumnList.contains(column)) {
                            tmp.childrenColumnList.add(column);
                        }
                        if(tmp.isMainNode()){
                            break;
                        }
                        tmp = tmp.parent;
                    }
                }
            }
        }
    }

    public List<XMLNode> getGroupByNodeList(XMLNode group) {
        List<XMLNode> list = new ArrayList<XMLNode>();
        for (XMLNode attri : group.attributes) {
            if (attri.column != null && attri.column.length() != 0) {
                list.add(attri);
            }
        }
        if (group.relatedColumn != null) {
            list.add(group);
        } else {
            for (XMLNode element : group.elements) {
                if (!element.isMainNode()) {
                    list.addAll(getGroupByNodeList(element));
                }
            }
        }
        return list;
    }

    public XMLNode removeEmptyElement(XMLNode root) {
        List<XMLNode> removeNodes = new LinkedList<XMLNode>();
        for (XMLNode attri : root.attributes) {
            if ((attri.column == null || attri.column.length() == 0) && 
            		(attri.defaultValue == null || "".equals(attri.defaultValue)) ) {
                attri.parent = null;
                removeNodes.add(attri);
            }
        }
        root.attributes.removeAll(removeNodes);

        removeNodes.clear();
        for (XMLNode ns : root.namespaces) {
            if ( (ns.column == null || ns.column.length() == 0)
            		&& (ns.defaultValue == null || "".equals(ns.defaultValue)) ) {
                ns.parent = null;
                removeNodes.add(ns);
            }
        }
        root.namespaces.removeAll(removeNodes);

        removeNodes.clear();
        for (XMLNode child : root.elements) {
            removeNodes.add(removeEmptyElement(child));
        }
        root.elements.removeAll(removeNodes);

        if (root.attributes.size() == 0 && root.namespaces.size() == 0 && root.elements.size() == 0
                && (root.column == null || root.column.length() == 0)
                && (root.defaultValue == null || "".equals(root.defaultValue)) ) {
            return root;
        } else {
            return null;
        }
    }

    public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
    
// ------------------- *** Dom4j generation mode start *** ------------------- //
class XSDToolByDom4j{
    String cid = null;
    public void generateXSD(String parent, String currEleName, XMLNode root){
    	if(("ELEMENT").equals(root.type)){
    		createElement(parent,currEleName,root);
			
			if(root.elements!=null && root.elements.size()>0
			  || root.attributes!=null && root.attributes.size()>0){

    stringBuffer.append(TEXT_1);
    stringBuffer.append(currEleName);
    stringBuffer.append(TEXT_2);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_3);
    stringBuffer.append(currEleName);
    stringBuffer.append(TEXT_4);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_5);
    stringBuffer.append(currEleName);
    stringBuffer.append(TEXT_6);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_3);
    stringBuffer.append(currEleName);
    stringBuffer.append(TEXT_2);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_7);
    
        		int index = 0;
        		for(XMLNode child:root.elements){
        			generateXSD(currEleName+"_s", currEleName+"_"+index++, child);
        		}
        		
        		for(XMLNode attri:root.attributes){
        			createAttribute(currEleName+"_c",attri);
        		}
			}
    	}
    }
    
    private void createElement(String parent, String currEleName, XMLNode node){

    stringBuffer.append(TEXT_8);
    stringBuffer.append(currEleName);
    stringBuffer.append(TEXT_4);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_3);
    stringBuffer.append(parent);
    stringBuffer.append(TEXT_4);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_9);
    stringBuffer.append(currEleName);
    stringBuffer.append(TEXT_4);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_10);
    stringBuffer.append(node.name);
    stringBuffer.append(TEXT_11);
    stringBuffer.append(currEleName);
    stringBuffer.append(TEXT_4);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_12);
    
		if(node.parent != null){

    stringBuffer.append(TEXT_13);
    stringBuffer.append(currEleName);
    stringBuffer.append(TEXT_4);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_14);
    stringBuffer.append(currEleName);
    stringBuffer.append(TEXT_4);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_15);
    
		}
    }
    
    private void createAttribute(String parent, XMLNode node){

    stringBuffer.append(TEXT_13);
    stringBuffer.append(parent);
    stringBuffer.append(TEXT_4);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_16);
    stringBuffer.append(node.name);
    stringBuffer.append(TEXT_17);
    
    }
}
// ------------------- *** Dom4j generation mode end *** ------------------- //

// ------------------- *** Null generation mode start *** ------------------- //
class XSDToolByNull{
	String cid = null;
    public void generateXSD(String emptyspace, XMLNode root){
    	if(("ELEMENT").equals(root.type)){
    		createElement(emptyspace, root);
			
			if(root.elements!=null && root.elements.size()>0
			  || root.attributes!=null && root.attributes.size()>0){

    stringBuffer.append(TEXT_18);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_19);
    stringBuffer.append(emptyspace);
    stringBuffer.append(TEXT_20);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_21);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_19);
    stringBuffer.append(emptyspace);
    stringBuffer.append(TEXT_22);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_23);
    
				XMLNode mainNode = null;
        		for(XMLNode child:root.elements){
        			if( 1==(child.special & 4)){
        				mainNode = child;
        			}else{
        				generateXSD(emptyspace+"      ", child);
        			}
        		}
        		if(mainNode!= null){
        			generateXSD(emptyspace+"      ", mainNode);
        		}

    stringBuffer.append(TEXT_18);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_19);
    stringBuffer.append(emptyspace);
    stringBuffer.append(TEXT_24);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_23);
    
        		for(XMLNode attri:root.attributes){
        			createAttribute(emptyspace+"    ",attri);
        		}

    stringBuffer.append(TEXT_18);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_19);
    stringBuffer.append(emptyspace);
    stringBuffer.append(TEXT_25);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_23);
    
			}

    stringBuffer.append(TEXT_18);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_19);
    stringBuffer.append(emptyspace);
    stringBuffer.append(TEXT_26);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_23);
    
    	}
    }
    
    private void createElement(String emptyspace, XMLNode node){

    stringBuffer.append(TEXT_18);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_19);
    stringBuffer.append(emptyspace);
    stringBuffer.append(TEXT_27);
    stringBuffer.append(node.name);
    stringBuffer.append(TEXT_28);
    
		if(node.parent != null){

    stringBuffer.append(TEXT_29);
    
		}

    stringBuffer.append(TEXT_30);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_23);
    
    }
    
    private void createAttribute(String emptyspace, XMLNode node){

    stringBuffer.append(TEXT_18);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_19);
    stringBuffer.append(emptyspace);
    stringBuffer.append(TEXT_31);
    stringBuffer.append(node.name);
    stringBuffer.append(TEXT_32);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_23);
    
    }
}
// ------------------- *** Dom4j generation mode end *** ------------------- //

// ------------------- *** Common code start *** ------------------- //
CodeGeneratorArgument codeGenArgument = (CodeGeneratorArgument) argument;
INode node = (INode)codeGenArgument.getArgument();
List<Map<String,String>> groupbys = (List<Map<String,String>>)ElementParameterParser.getObjectValue(node, "__GROUPBYS__");
String jsonField = ElementParameterParser.getValue(node, "__JSONFIELD__");
String destination4JSON = ElementParameterParser.getValue(node, "__DESTINATION__");
boolean istWriteJSONField = destination4JSON == null ? false : destination4JSON.contains("tWriteJSONField_"); 

String removeHeader = ElementParameterParser.getValue(node, "__REMOVE_HEADER__"); // add for feature7788
String allowEmpty = ElementParameterParser.getValue(node, "__CREATE_EMPTY_ELEMENT__");
String outputAsXSD = ElementParameterParser.getValue(node, "__OUTPUT_AS_XSD__");
String fileNameXSD = ElementParameterParser.getValue(node, "__XSD_FILE__");
List<Map<String, String>> rootTable = 
	(List<Map<String,String>>)ElementParameterParser.getObjectValue(node, "__ROOT__");
List<Map<String, String>> groupTable = 
	(List<Map<String,String>>)ElementParameterParser.getObjectValue(node, "__GROUP__");
List<Map<String, String>> loopTable = 
	(List<Map<String,String>>)ElementParameterParser.getObjectValue(node, "__LOOP__");

String encoding = ElementParameterParser.getValue(node, "__ENCODING__");
String mode = ElementParameterParser.getValue(node, "__GENERATION_MODE__");

boolean storeFlow = ("true").equals(ElementParameterParser.getValue(node, "__STORE_FLOW__"));
String cid = node.getUniqueName();

String virtualTargetCid = node.getOutgoingConnections(EConnectionType.ON_COMPONENT_OK).get(0).getTarget().getUniqueName();

List<IMetadataTable> metadatas = node.getMetadataList();

if ((metadatas!=null)&&(metadatas.size()>0)) {
	    List< ? extends IConnection> conns = node.getIncomingConnections();
    	List< ? extends IConnection> connsOut = NodeUtil.getOutgoingConnections(node,EConnectionType.ON_COMPONENT_OK);
    	String rowStructNameOutput = null;
    	if (connsOut != null && connsOut.size() > 0 && istWriteJSONField) {
    		List< ? extends IConnection> connsTarget = connsOut.get(0).getTarget().getOutgoingConnections();
			if(connsTarget != null && connsTarget.size()>0){
				rowStructNameOutput = connsTarget.get(0).getName();
	    		rowStructNameOutput += "Struct";
			}
    	}

    	String rowNameInput = null;
    	String rowStructNameInput = null;
    	if(conns!=null && conns.size()>0 && istWriteJSONField){
    	    rowNameInput = conns.get(0).getName();
    	    rowStructNameInput = rowNameInput + "Struct";
        }


    stringBuffer.append(TEXT_33);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_34);
    
	if(("Dom4j").equals(mode)){
		if(("true").equals(outputAsXSD)){

    stringBuffer.append(TEXT_35);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_36);
    stringBuffer.append(fileNameXSD);
    stringBuffer.append(TEXT_37);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_38);
    
		}

    stringBuffer.append(TEXT_39);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_40);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_41);
    
		if(!("true").equals(outputAsXSD) && !("true").equals(allowEmpty)){

    stringBuffer.append(TEXT_42);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_43);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_41);
    
		}

    stringBuffer.append(TEXT_44);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_45);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_46);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_47);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_48);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_49);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_50);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_51);
    
		if(("true").equals(removeHeader)){

    stringBuffer.append(TEXT_52);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_53);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_54);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_55);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_56);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_57);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_58);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_59);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_60);
    
		}else{
		    String destination = ElementParameterParser.getValue(node, "__DESTINATION__");
		    if(destination.indexOf("tCouchbaseOutput_")==0 || destination.indexOf("tCouchDBOutput_")==0){

    stringBuffer.append(TEXT_61);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_62);
    stringBuffer.append(destination);
    stringBuffer.append(TEXT_63);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_64);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_65);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_60);
    
		    }else{
				 if(istWriteJSONField){
				  	
    stringBuffer.append(TEXT_66);
    stringBuffer.append( rowStructNameOutput );
    stringBuffer.append(TEXT_67);
    stringBuffer.append( cid );
    stringBuffer.append(TEXT_68);
    stringBuffer.append( rowStructNameOutput );
    stringBuffer.append(TEXT_69);
    
					      for(Map<String,String> map : groupbys){
								String groupByColumnName = map.get("INPUT_COLUMN");
								String outputColumnName = map.get("OUTPUT_COLUMN");
								if (!outputColumnName.equals(jsonField)) {
								
    stringBuffer.append(TEXT_70);
    stringBuffer.append( cid );
    stringBuffer.append(TEXT_71);
    stringBuffer.append( outputColumnName );
    stringBuffer.append(TEXT_72);
    stringBuffer.append( cid );
    stringBuffer.append(TEXT_71);
    stringBuffer.append( groupByColumnName );
    stringBuffer.append(TEXT_73);
    
								}
					       }
							
    stringBuffer.append(TEXT_74);
    stringBuffer.append( cid );
    stringBuffer.append(TEXT_71);
    stringBuffer.append( jsonField );
    stringBuffer.append(TEXT_53);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_75);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_76);
    stringBuffer.append( cid );
    stringBuffer.append(TEXT_77);
    
				}else{
					
    stringBuffer.append(TEXT_78);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_79);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_80);
    
				}
		    }
		}
	}else if(("Null").equals(mode)){
		if(!("true").equals(outputAsXSD) && !("true").equals(allowEmpty)){

    stringBuffer.append(TEXT_81);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_82);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_83);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_84);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_85);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_86);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_87);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_88);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_89);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_87);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_90);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_91);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_92);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_93);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_94);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_95);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_96);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_97);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_98);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_99);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_97);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_100);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_101);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_102);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_103);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_104);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_105);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_106);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_95);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_107);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_97);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_98);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_99);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_97);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_100);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_101);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_102);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_103);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_108);
    
		}else{
			if(loopTable.size()>0){

    stringBuffer.append(TEXT_109);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_110);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_111);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_112);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_95);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_113);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_97);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_114);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_99);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_97);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_115);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_116);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_102);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_103);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_117);
    
			}
		}

    stringBuffer.append(TEXT_118);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_119);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_120);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_79);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_121);
    
	}

    stringBuffer.append(TEXT_122);
    

// ------------------- *** xsd output code start *** ------------------- //
	if(("true").equals(outputAsXSD)){

        // change tables to a tree 
		Object[] treeObjs = getTree(rootTable, groupTable, loopTable, metadatas.get(0).getListColumns());
    	List<XMLNode> mainList = (ArrayList<XMLNode>)treeObjs[0];
        XMLNode root = mainList.get(0);

        if(!("true").equals(allowEmpty)){
        	removeEmptyElement(root);
        }

		if(("Dom4j").equals(mode)){

    stringBuffer.append(TEXT_123);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_124);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_125);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_126);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_127);
    
	XSDToolByDom4j xsdTool = new XSDToolByDom4j();
	xsdTool.cid = cid;
	xsdTool.generateXSD("root_xsd","ele_xsd",root);

    stringBuffer.append(TEXT_128);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_129);
    stringBuffer.append(fileNameXSD);
    stringBuffer.append(TEXT_130);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_131);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_47);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_132);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_133);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_134);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_51);
    
		}else if(("Null").equals(mode)){

    stringBuffer.append(TEXT_135);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_136);
    stringBuffer.append(fileNameXSD);
    stringBuffer.append(TEXT_137);
    stringBuffer.append(encoding);
    stringBuffer.append(TEXT_138);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_139);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_23);
    
	XSDToolByNull xsdTool = new XSDToolByNull();
	xsdTool.cid=cid;
	xsdTool.generateXSD("  ",root);

    stringBuffer.append(TEXT_140);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_141);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_51);
    
		}
    }

    stringBuffer.append(TEXT_142);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_143);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_144);
    stringBuffer.append(virtualTargetCid);
    stringBuffer.append(TEXT_145);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_146);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_147);
    
	IConnection nextMergeConn = NodeUtil.getNextMergeConnection(node);
	if(nextMergeConn == null || nextMergeConn.getInputId()==1){
	
    stringBuffer.append(TEXT_148);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_149);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_150);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_151);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_152);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_153);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_154);
    
	}
}

    stringBuffer.append(TEXT_155);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_156);
    return stringBuffer.toString();
  }
}
