package org.talend.designer.codegen.translators.xml;

import org.talend.core.model.process.INode;
import org.talend.core.model.metadata.IMetadataTable;
import org.talend.designer.codegen.config.CodeGeneratorArgument;
import org.talend.core.model.process.ElementParameterParser;
import org.talend.core.model.metadata.types.JavaTypesManager;
import org.talend.core.model.metadata.types.JavaType;
import org.talend.core.model.metadata.IMetadataColumn;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;
import org.talend.core.model.process.IConnection;
import org.talend.core.model.process.IConnectionCategory;

public class TWriteJSONFieldInMainJava
{
  protected static String nl;
  public static synchronized TWriteJSONFieldInMainJava create(String lineSeparator)
  {
    nl = lineSeparator;
    TWriteJSONFieldInMainJava result = new TWriteJSONFieldInMainJava();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "";
  protected final String TEXT_2 = NL + "                    map_";
  protected final String TEXT_3 = " = queue_";
  protected final String TEXT_4 = ".poll();" + NL + "                    str_";
  protected final String TEXT_5 = " = (String)map_";
  protected final String TEXT_6 = ".get(\"json_";
  protected final String TEXT_7 = "\");" + NL + "                    String columnValue_";
  protected final String TEXT_8 = " = \"\";";
  protected final String TEXT_9 = NL + "                        if(map_";
  protected final String TEXT_10 = ".containsKey(\"";
  protected final String TEXT_11 = "\")){" + NL + "                            Object obj_";
  protected final String TEXT_12 = " = map_";
  protected final String TEXT_13 = ".get(\"";
  protected final String TEXT_14 = "\");" + NL + "                            if(obj_";
  protected final String TEXT_15 = " == null){";
  protected final String TEXT_16 = " ";
  protected final String TEXT_17 = NL + "                                    ";
  protected final String TEXT_18 = ".";
  protected final String TEXT_19 = " = ";
  protected final String TEXT_20 = ";";
  protected final String TEXT_21 = " = null;";
  protected final String TEXT_22 = NL + "                            }else{" + NL + "                                columnValue_";
  protected final String TEXT_23 = " = obj_";
  protected final String TEXT_24 = ".toString();";
  protected final String TEXT_25 = " = columnValue_";
  protected final String TEXT_26 = " = ParserUtils.parseTo_Date(new Date(columnValue_";
  protected final String TEXT_27 = "),";
  protected final String TEXT_28 = ");";
  protected final String TEXT_29 = ".getBytes();";
  protected final String TEXT_30 = " = ParserUtils.parseTo_";
  protected final String TEXT_31 = "(columnValue_";
  protected final String TEXT_32 = NL + "                            }" + NL + "                        }";
  protected final String TEXT_33 = NL + "                    ";
  protected final String TEXT_34 = " result_";
  protected final String TEXT_35 = " = result_";
  protected final String TEXT_36 = NL + "                                ";
  protected final String TEXT_37 = NL + "        //Convert XML to JSON" + NL + "        net.sf.json.JsonStandard jsonStandard_";
  protected final String TEXT_38 = " net.sf.json.JsonStandard.WRAP_NULL_STRINGS ";
  protected final String TEXT_39 = " net.sf.json.JsonStandard.LEGACY  ";
  protected final String TEXT_40 = ";" + NL + "        xmlSerializer_";
  protected final String TEXT_41 = ".setJsonStandard(jsonStandard_";
  protected final String TEXT_42 = ");" + NL + "        net.sf.json.JSON json_";
  protected final String TEXT_43 = " = xmlSerializer_";
  protected final String TEXT_44 = ".read(str_";
  protected final String TEXT_45 = NL + "        ";
  protected final String TEXT_46 = " = net.sf.json.util.JSONUtils.jsonToStandardizedString(json_";
  protected final String TEXT_47 = ", jsonStandard_";
  protected final String TEXT_48 = ");" + NL + "    " + NL + "        nb_line_";
  protected final String TEXT_49 = "++;";
  protected final String TEXT_50 = NL;

  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
    stringBuffer.append(TEXT_1);
    
CodeGeneratorArgument codeGenArgument = (CodeGeneratorArgument) argument;
INode node = (INode)codeGenArgument.getArgument();
String cid = node.getUniqueName();
String jsonField = ElementParameterParser.getValue(node, "__JSONFIELD__");
String destination = ElementParameterParser.getValue(node, "__DESTINATION__");

List<IMetadataTable> metadatas = node.getMetadataList();
if ((metadatas!=null)&&(metadatas.size()>0)) {
    IMetadataTable metadata = metadatas.get(0);
    if (metadata!=null) {
        List< ? extends IConnection> conns = node.getOutgoingSortedConnections();
        if(conns!=null && conns.size()>0){
            IConnection conn = conns.get(0);
            if (conn.getLineStyle().hasConnectionCategory(IConnectionCategory.DATA)) {
                String outputRowStructName = conns.get(0).getName()+"Struct";//row2
                if(destination!=null && (destination.indexOf("tCouchbaseOutput_")>=0 || destination.indexOf("tCouchDBOutput_")>=0)){

    stringBuffer.append(TEXT_2);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_3);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_4);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_5);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_6);
    stringBuffer.append(destination);
    stringBuffer.append(TEXT_7);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_8);
    
                    List<IMetadataColumn> columnList = metadata.getListColumns();
                    for(IMetadataColumn column: columnList){
                        String columnName = column.getLabel();
                        String talendType = column.getTalendType();
                        String typeToGenerate = JavaTypesManager.getTypeToGenerate(talendType, column.isNullable());
                        JavaType javaType = JavaTypesManager.getJavaTypeFromId(talendType);
                        boolean isPrimitive = JavaTypesManager.isJavaPrimitiveType( javaType, column.isNullable());
                        String defaultValue = JavaTypesManager.getDefaultValueFromJavaType(typeToGenerate, column.getDefault());
                        String patternValue = column.getPattern() == null || column.getPattern().trim().length() == 0 ? null : column.getPattern();

    stringBuffer.append(TEXT_9);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_10);
    stringBuffer.append(column.getLabel());
    stringBuffer.append(TEXT_11);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_12);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_13);
    stringBuffer.append(column.getLabel());
    stringBuffer.append(TEXT_14);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_15);
    
                                if(isPrimitive){
                                
    stringBuffer.append(TEXT_16);
    stringBuffer.append(TEXT_17);
    stringBuffer.append(conn.getName());
    stringBuffer.append(TEXT_18);
    stringBuffer.append(columnName);
    stringBuffer.append(TEXT_19);
    stringBuffer.append(defaultValue );
    stringBuffer.append(TEXT_20);
    
                                }else{
                                
    stringBuffer.append(TEXT_17);
    stringBuffer.append(conn.getName());
    stringBuffer.append(TEXT_18);
    stringBuffer.append(columnName);
    stringBuffer.append(TEXT_21);
    
                                }
                                
    stringBuffer.append(TEXT_22);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_23);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_24);
    
                               if(javaType == JavaTypesManager.STRING || javaType == JavaTypesManager.OBJECT) {

    stringBuffer.append(TEXT_17);
    stringBuffer.append(conn.getName());
    stringBuffer.append(TEXT_18);
    stringBuffer.append(columnName);
    stringBuffer.append(TEXT_25);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_20);
    
                               }else if(javaType == JavaTypesManager.DATE) {

    stringBuffer.append(TEXT_17);
    stringBuffer.append(conn.getName());
    stringBuffer.append(TEXT_18);
    stringBuffer.append(columnName);
    stringBuffer.append(TEXT_26);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_27);
    stringBuffer.append(patternValue);
    stringBuffer.append(TEXT_28);
    
                               }else if(javaType == JavaTypesManager.BYTE_ARRAY){

    stringBuffer.append(TEXT_17);
    stringBuffer.append(conn.getName());
    stringBuffer.append(TEXT_18);
    stringBuffer.append(columnName);
    stringBuffer.append(TEXT_25);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_29);
    
                               }else{

    stringBuffer.append(TEXT_17);
    stringBuffer.append(conn.getName());
    stringBuffer.append(TEXT_18);
    stringBuffer.append(columnName);
    stringBuffer.append(TEXT_30);
    stringBuffer.append( typeToGenerate );
    stringBuffer.append(TEXT_31);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_28);
    
                               }

    stringBuffer.append(TEXT_32);
    
                    }
                }else{
                    
    stringBuffer.append(TEXT_33);
    stringBuffer.append( outputRowStructName );
    stringBuffer.append(TEXT_34);
    stringBuffer.append( cid );
    stringBuffer.append(TEXT_3);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_4);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_35);
    stringBuffer.append( cid );
    stringBuffer.append(TEXT_18);
    stringBuffer.append( jsonField );
    stringBuffer.append(TEXT_20);
    
                    List<Map<String,String>> groupbys = (List<Map<String,String>>)ElementParameterParser.getObjectValue(node, "__GROUPBYS__");
                    for(Map<String,String> map : groupbys){
                            String outputColumnName = map.get("OUTPUT_COLUMN");
                            
    stringBuffer.append(TEXT_36);
    stringBuffer.append(conn.getName());
    stringBuffer.append(TEXT_18);
    stringBuffer.append( outputColumnName );
    stringBuffer.append(TEXT_35);
    stringBuffer.append( cid );
    stringBuffer.append(TEXT_18);
    stringBuffer.append( outputColumnName );
    stringBuffer.append(TEXT_20);
    
                   }
            }
        boolean wrapNullStrings = "true".equals(ElementParameterParser.getValue(node, "___QUOTE_ALL_VALUES___")) && "true".equals(ElementParameterParser.getValue(node, "__QUOTE_NULL_VALUES__"));

    stringBuffer.append(TEXT_37);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_19);
     if (wrapNullStrings) { 
    stringBuffer.append(TEXT_38);
     } else { 
    stringBuffer.append(TEXT_39);
    }
    stringBuffer.append(TEXT_40);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_41);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_42);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_43);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_44);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_28);
    stringBuffer.append(TEXT_45);
    stringBuffer.append(conn.getName());
    stringBuffer.append(TEXT_18);
    stringBuffer.append(jsonField );
    stringBuffer.append(TEXT_46);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_47);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_48);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_49);
    
            }
        }
    }
}

    stringBuffer.append(TEXT_50);
    return stringBuffer.toString();
  }
}
