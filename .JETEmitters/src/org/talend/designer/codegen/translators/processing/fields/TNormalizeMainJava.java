package org.talend.designer.codegen.translators.processing.fields;

import org.talend.core.model.process.INode;
import org.talend.core.model.process.ElementParameterParser;
import org.talend.core.model.metadata.IMetadataTable;
import org.talend.core.model.metadata.IMetadataColumn;
import org.talend.designer.codegen.config.CodeGeneratorArgument;
import org.talend.core.model.process.IConnection;
import org.talend.core.model.metadata.types.JavaTypesManager;
import org.talend.core.model.metadata.types.JavaType;
import java.util.List;
import org.talend.core.model.process.IConnectionCategory;
import org.talend.core.model.utils.NodeUtil;

public class TNormalizeMainJava
{
  protected static String nl;
  public static synchronized TNormalizeMainJava create(String lineSeparator)
  {
    nl = lineSeparator;
    TNormalizeMainJava result = new TNormalizeMainJava();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "";
  protected final String TEXT_2 = NL + "            normalizeRecord_";
  protected final String TEXT_3 = " = new String[1];" + NL + "            if(";
  protected final String TEXT_4 = ".";
  protected final String TEXT_5 = " != null) {" + NL + "\t\t\t\tif(\"\".equals(";
  protected final String TEXT_6 = ")){" + NL + "\t\t        \tnormalizeRecord_";
  protected final String TEXT_7 = "[0] = \"\";" + NL + "\t\t        }else{" + NL + "\t                ";
  protected final String TEXT_8 = NL + "\t                    com.talend.csv.CSVReader reader_";
  protected final String TEXT_9 = " = new com.talend.csv.CSVReader(new java.io.StringReader(";
  protected final String TEXT_10 = "), ";
  protected final String TEXT_11 = ".charAt(0));" + NL + "\t                    reader_";
  protected final String TEXT_12 = ".setTrimWhitespace(false);" + NL + "\t                    ";
  protected final String TEXT_13 = NL + "\t                    \treader_";
  protected final String TEXT_14 = ".setEscapeChar('\\\\');" + NL + "\t                   \t";
  protected final String TEXT_15 = NL + "\t                 \t\treader_";
  protected final String TEXT_16 = ".setEscapeChar(";
  protected final String TEXT_17 = ".charAt(0));" + NL + "\t                   \t";
  protected final String TEXT_18 = NL + "\t                    reader_";
  protected final String TEXT_19 = ".setQuoteChar(";
  protected final String TEXT_20 = ".charAt(0));" + NL + "\t                    if (reader_";
  protected final String TEXT_21 = ".readNext()) {" + NL + "\t                        normalizeRecord_";
  protected final String TEXT_22 = " = reader_";
  protected final String TEXT_23 = ".getValues();" + NL + "\t                    }" + NL + "\t                ";
  protected final String TEXT_24 = NL + "\t                    \tnormalizeRecord_";
  protected final String TEXT_25 = " = ";
  protected final String TEXT_26 = ".split(";
  protected final String TEXT_27 = ");" + NL + "\t                    ";
  protected final String TEXT_28 = NL + "\t                \t\tnormalizeRecord_";
  protected final String TEXT_29 = ",-1);" + NL + "\t                    ";
  protected final String TEXT_30 = "    " + NL + "                }           " + NL + "            }";
  protected final String TEXT_31 = NL + "\t\t  \t\t\tlastNoEmptyIndex_";
  protected final String TEXT_32 = "=0;" + NL + "\t\t            for(int i_";
  protected final String TEXT_33 = "=normalizeRecord_";
  protected final String TEXT_34 = ".length;i_";
  protected final String TEXT_35 = " > 0;i_";
  protected final String TEXT_36 = "--){" + NL + "\t\t            \tif(!\"\".equals(normalizeRecord_";
  protected final String TEXT_37 = "[i_";
  protected final String TEXT_38 = " - 1])){" + NL + "\t\t            \t\tlastNoEmptyIndex_";
  protected final String TEXT_39 = "=i_";
  protected final String TEXT_40 = ";" + NL + "\t\t            \t\tbreak;" + NL + "\t\t            \t}" + NL + "\t\t            }" + NL + "            \t";
  protected final String TEXT_41 = NL + "             \t\tlastNoEmptyIndex_";
  protected final String TEXT_42 = ".length;" + NL + "\t             \t";
  protected final String TEXT_43 = NL + "\t             \t\tif(lastNoEmptyIndex_";
  protected final String TEXT_44 = " == 1 && \"\".equals(normalizeRecord_";
  protected final String TEXT_45 = "[0])){" + NL + "\t             \t\t\tlastNoEmptyIndex_";
  protected final String TEXT_46 = " = 0;" + NL + "\t             \t\t}" + NL + "\t             \t";
  protected final String TEXT_47 = NL + "            \t";
  protected final String TEXT_48 = NL + "            \tfor(int i_";
  protected final String TEXT_49 = " = 0 ; i_";
  protected final String TEXT_50 = " < lastNoEmptyIndex_";
  protected final String TEXT_51 = " ; i_";
  protected final String TEXT_52 = "++) {" + NL + "\t\t  \t\t\t";
  protected final String TEXT_53 = NL + "\t\t            \tif(normalizeRecord_";
  protected final String TEXT_54 = "]!=null){" + NL + "\t\t            \t\tnormalizeRecord_";
  protected final String TEXT_55 = "]=normalizeRecord_";
  protected final String TEXT_56 = "].trim();" + NL + "\t\t            \t}" + NL + "\t\t            ";
  protected final String TEXT_57 = NL + "\t                currentRecord_";
  protected final String TEXT_58 = " = new StringBuilder();" + NL + "\t                nb_line_";
  protected final String TEXT_59 = "++;               " + NL + "\t                ";
  protected final String TEXT_60 = NL + "\t                                \ttmp_";
  protected final String TEXT_61 = " == null ? null : String.valueOf(";
  protected final String TEXT_62 = ".getTime());" + NL + "\t                                ";
  protected final String TEXT_63 = NL + "\t\t                                    tmp_";
  protected final String TEXT_64 = " == null ? null : ";
  protected final String TEXT_65 = ".toString();" + NL + "\t\t                                    ";
  protected final String TEXT_66 = " = String.valueOf(";
  protected final String TEXT_67 = ");" + NL + "\t\t                                    ";
  protected final String TEXT_68 = " == null ? null : new String(";
  protected final String TEXT_69 = ");" + NL + "\t                                ";
  protected final String TEXT_70 = NL + "\t\t                            if(tmp_";
  protected final String TEXT_71 = " != null){" + NL + "\t\t                                currentRecord_";
  protected final String TEXT_72 = ".append(tmp_";
  protected final String TEXT_73 = " + tmp_";
  protected final String TEXT_74 = ".length());" + NL + "\t\t                            }" + NL + "\t                            ";
  protected final String TEXT_75 = NL + "\t\t                            if(normalizeRecord_";
  protected final String TEXT_76 = "] != null) {" + NL + "\t\t                                currentRecord_";
  protected final String TEXT_77 = ".append(normalizeRecord_";
  protected final String TEXT_78 = "] + normalizeRecord_";
  protected final String TEXT_79 = "].length());" + NL + "\t\t                            }" + NL + "\t                            ";
  protected final String TEXT_80 = NL + "\t\t                    if(!recordSet_";
  protected final String TEXT_81 = ".contains(currentRecord_";
  protected final String TEXT_82 = ".toString())) {" + NL + "\t\t                        recordSet_";
  protected final String TEXT_83 = ".add(currentRecord_";
  protected final String TEXT_84 = ".toString());" + NL + "\t\t                    } else {" + NL + "\t\t                        continue;" + NL + "\t\t                    }                    " + NL + "\t\t                ";
  protected final String TEXT_85 = NL + "\t                        \t";
  protected final String TEXT_86 = " = normalizeRecord_";
  protected final String TEXT_87 = "];" + NL + "\t\t                    ";
  protected final String TEXT_88 = ";" + NL + "\t                        ";
  protected final String TEXT_89 = NL;

  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
    stringBuffer.append(TEXT_1);
    
CodeGeneratorArgument codeGenArgument = (CodeGeneratorArgument) argument;
INode node = (INode)codeGenArgument.getArgument();
List<IMetadataTable> metadatas = node.getMetadataList();
if ((metadatas!=null)&&(metadatas.size()>0)) {//1
    IMetadataTable metadata = metadatas.get(0);    
    if (metadata!=null) {//2
       
        String cid = node.getUniqueName();
        String normalizeColumn = ElementParameterParser.getValue(node, "__NORMALIZE_COLUMN__");
        String deduplicate = ElementParameterParser.getValue(node, "__DEDUPLICATE__");
        boolean useCSV = ("true").equals(ElementParameterParser.getValue(node, "__CSV_OPTION__"));
        String escapeMode = ElementParameterParser.getValue(node, "__ESCAPE_CHAR__");
        
        String delim = ElementParameterParser.getValue(node, "__ITEMSEPARATOR__");
        
        boolean isDiscardTrailingEmptyStr=("true").equals(ElementParameterParser.getValue(node, "__DISCARD_TRAILING_EMPTY_STR__"));
        boolean isTrim=("true").equals(ElementParameterParser.getValue(node, "__TRIM__"));
    	String textEnclosure = ElementParameterParser.getValue(node, "__TEXT_ENCLOSURE__");       
        String incomingConnName = null;
        String outgoingConnName = null;

        List< ? extends IConnection> inConns = node.getIncomingConnections();
        if (inConns != null && !inConns.isEmpty()) {
            IConnection inConn = inConns.get(0);
            incomingConnName = inConn.getName();
        }
        
        List< ? extends IConnection> outConns = NodeUtil.getOutgoingConnections(node, IConnectionCategory.DATA);
        if (outConns != null && !outConns.isEmpty()) {
            IConnection outConn = outConns.get(0);
            outgoingConnName = outConn.getName();
        }
        List< ? extends IConnection> outIterate = NodeUtil.getOutgoingConnections(node, IConnectionCategory.USE_ITERATE);
        List<IMetadataColumn> metadataColumns = metadata.getListColumns();
        if(incomingConnName != null && 
            metadataColumns != null && !metadataColumns.isEmpty()) {//3
            
    stringBuffer.append(TEXT_2);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_3);
    stringBuffer.append(incomingConnName );
    stringBuffer.append(TEXT_4);
    stringBuffer.append(normalizeColumn);
    stringBuffer.append(TEXT_5);
    stringBuffer.append(incomingConnName );
    stringBuffer.append(TEXT_4);
    stringBuffer.append(normalizeColumn);
    stringBuffer.append(TEXT_6);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_7);
    if(useCSV){
    stringBuffer.append(TEXT_8);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_9);
    stringBuffer.append(incomingConnName );
    stringBuffer.append(TEXT_4);
    stringBuffer.append(normalizeColumn);
    stringBuffer.append(TEXT_10);
    stringBuffer.append(delim );
    stringBuffer.append(TEXT_11);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_12);
    
	                    if("ESCAPE_MODE_BACKSLASH".equals(escapeMode)) {
	                    
    stringBuffer.append(TEXT_13);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_14);
    } else {
    stringBuffer.append(TEXT_15);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_16);
    stringBuffer.append(textEnclosure );
    stringBuffer.append(TEXT_17);
    } 
    stringBuffer.append(TEXT_18);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_19);
    stringBuffer.append(textEnclosure );
    stringBuffer.append(TEXT_20);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_21);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_22);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_23);
     
	                } else { 
	            		if(isDiscardTrailingEmptyStr){
	                    
    stringBuffer.append(TEXT_24);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_25);
    stringBuffer.append(incomingConnName );
    stringBuffer.append(TEXT_4);
    stringBuffer.append(normalizeColumn);
    stringBuffer.append(TEXT_26);
    stringBuffer.append(delim );
    stringBuffer.append(TEXT_27);
    
	            		}else{
	                    
    stringBuffer.append(TEXT_28);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_25);
    stringBuffer.append(incomingConnName );
    stringBuffer.append(TEXT_4);
    stringBuffer.append(normalizeColumn);
    stringBuffer.append(TEXT_26);
    stringBuffer.append(delim );
    stringBuffer.append(TEXT_29);
    
	            		}
	            	}
	                
    stringBuffer.append(TEXT_30);
    
            if(!outIterate.isEmpty()||outgoingConnName != null){
  				if(useCSV && isDiscardTrailingEmptyStr){
  				
    stringBuffer.append(TEXT_31);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_32);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_33);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_34);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_35);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_36);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_37);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_38);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_39);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_40);
    
            	}else{
           	 	
    stringBuffer.append(TEXT_41);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_33);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_42);
    if(isDiscardTrailingEmptyStr){
    stringBuffer.append(TEXT_43);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_44);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_45);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_46);
    }
    stringBuffer.append(TEXT_47);
    
       			}
            	
    stringBuffer.append(TEXT_48);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_49);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_50);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_51);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_52);
    if(isTrim){
    stringBuffer.append(TEXT_53);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_37);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_54);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_37);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_55);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_37);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_56);
    }
    stringBuffer.append(TEXT_57);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_58);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_59);
    
	                if(outgoingConnName != null ){
		                if(("true").equals(deduplicate)) {//check deduplicate start
		                    for(int i = 0 ; i < metadataColumns.size() ; i++) {
		                        IMetadataColumn metadataColumn = (IMetadataColumn)metadataColumns.get(i);
		                        JavaType javaType = JavaTypesManager.getJavaTypeFromId(metadataColumn.getTalendType());
		                        String typeName = JavaTypesManager.getTypeToGenerate(metadataColumn.getTalendType(), metadataColumn.isNullable());
		                        if(!metadataColumn.getLabel().equals(normalizeColumn)) {
		                            if(javaType == JavaTypesManager.DATE) {
	                                
    stringBuffer.append(TEXT_60);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_25);
    stringBuffer.append(incomingConnName);
    stringBuffer.append(TEXT_4);
    stringBuffer.append(metadataColumn.getLabel());
    stringBuffer.append(TEXT_61);
    stringBuffer.append(incomingConnName);
    stringBuffer.append(TEXT_4);
    stringBuffer.append(metadataColumn.getLabel());
    stringBuffer.append(TEXT_62);
    
	                            	} else if(javaType == JavaTypesManager.BYTE || 
	                                    javaType == JavaTypesManager.SHORT || 
	                                    javaType == JavaTypesManager.FLOAT ||
	                                    javaType == JavaTypesManager.DOUBLE ||
	                                    javaType == JavaTypesManager.LONG ||
	                                    javaType == JavaTypesManager.INTEGER || 
	                                    javaType == JavaTypesManager.BOOLEAN) {
		                                if(("Byte").equals(typeName) || 
		                                        ("Short").equals(typeName) || 
		                                        ("Float").equals(typeName) || 
		                                        ("Double").equals(typeName) || 
		                                        ("Long").equals(typeName) || 
		                                        ("Integer").equals(typeName) || 
		                                        ("Boolean").equals(typeName)) {                    
		                                    
    stringBuffer.append(TEXT_63);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_25);
    stringBuffer.append(incomingConnName);
    stringBuffer.append(TEXT_4);
    stringBuffer.append(metadataColumn.getLabel());
    stringBuffer.append(TEXT_64);
    stringBuffer.append(incomingConnName);
    stringBuffer.append(TEXT_4);
    stringBuffer.append(metadataColumn.getLabel());
    stringBuffer.append(TEXT_65);
    
		                                } else {
		                                    
    stringBuffer.append(TEXT_63);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_66);
    stringBuffer.append(incomingConnName);
    stringBuffer.append(TEXT_4);
    stringBuffer.append(metadataColumn.getLabel());
    stringBuffer.append(TEXT_67);
    
		                                }
	                            	} else if(javaType == JavaTypesManager.BYTE_ARRAY) {
	                                
    stringBuffer.append(TEXT_60);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_25);
    stringBuffer.append(incomingConnName);
    stringBuffer.append(TEXT_4);
    stringBuffer.append(metadataColumn.getLabel());
    stringBuffer.append(TEXT_68);
    stringBuffer.append(incomingConnName);
    stringBuffer.append(TEXT_4);
    stringBuffer.append(metadataColumn.getLabel());
    stringBuffer.append(TEXT_69);
    
	                            	} else if(javaType == JavaTypesManager.STRING){
	                                
    stringBuffer.append(TEXT_60);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_25);
    stringBuffer.append(incomingConnName);
    stringBuffer.append(TEXT_4);
    stringBuffer.append(metadataColumn.getLabel());
    stringBuffer.append(TEXT_61);
    stringBuffer.append(incomingConnName);
    stringBuffer.append(TEXT_4);
    stringBuffer.append(metadataColumn.getLabel());
    stringBuffer.append(TEXT_69);
    
	                            	} else {
	                                
    stringBuffer.append(TEXT_60);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_66);
    stringBuffer.append(incomingConnName);
    stringBuffer.append(TEXT_4);
    stringBuffer.append(metadataColumn.getLabel());
    stringBuffer.append(TEXT_69);
    
	                            	}
	                            	
    stringBuffer.append(TEXT_70);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_71);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_72);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_73);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_74);
    
	                       	 	} else {
	                            
    stringBuffer.append(TEXT_75);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_37);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_76);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_77);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_37);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_78);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_37);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_79);
    
		                        }
		                    }
		                    
    stringBuffer.append(TEXT_80);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_81);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_82);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_83);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_84);
    
		                }//check deduplicate end                
		                for(IMetadataColumn metadataColumn : metadataColumns) {
		                    if(metadataColumn.getLabel().equals(normalizeColumn)) {
	                        
    stringBuffer.append(TEXT_85);
    stringBuffer.append(outgoingConnName);
    stringBuffer.append(TEXT_4);
    stringBuffer.append(metadataColumn.getLabel());
    stringBuffer.append(TEXT_86);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_37);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_87);
    
		                    } else {
	                        
    stringBuffer.append(TEXT_85);
    stringBuffer.append(outgoingConnName);
    stringBuffer.append(TEXT_4);
    stringBuffer.append(metadataColumn.getLabel());
    stringBuffer.append(TEXT_25);
    stringBuffer.append(incomingConnName);
    stringBuffer.append(TEXT_4);
    stringBuffer.append(metadataColumn.getLabel());
    stringBuffer.append(TEXT_88);
    
	                    	}
	                	}
	                }
        	}
        }
        //3
    }
    //2    
}
//1

    stringBuffer.append(TEXT_89);
    return stringBuffer.toString();
  }
}
