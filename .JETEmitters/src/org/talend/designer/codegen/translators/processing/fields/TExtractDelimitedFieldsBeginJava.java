package org.talend.designer.codegen.translators.processing.fields;

import org.talend.core.model.process.INode;
import org.talend.core.model.process.IConnection;
import org.talend.core.model.process.ElementParameterParser;
import org.talend.core.model.process.IConnectionCategory;
import org.talend.core.model.metadata.IMetadataTable;
import org.talend.core.model.metadata.IMetadataColumn;
import org.talend.designer.codegen.config.CodeGeneratorArgument;
import org.talend.core.model.metadata.types.JavaTypesManager;
import org.talend.core.model.metadata.types.JavaType;
import org.talend.core.model.utils.NodeUtil;
import java.util.List;
import java.util.ArrayList;

public class TExtractDelimitedFieldsBeginJava
{
  protected static String nl;
  public static synchronized TExtractDelimitedFieldsBeginJava create(String lineSeparator)
  {
    nl = lineSeparator;
    TExtractDelimitedFieldsBeginJava result = new TExtractDelimitedFieldsBeginJava();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "";
  protected final String TEXT_2 = NL + "int nb_line_";
  protected final String TEXT_3 = "=0;";
  protected final String TEXT_4 = NL + NL + "\tclass OptimizedCodeUtil_";
  protected final String TEXT_5 = "{";
  protected final String TEXT_6 = NL + "\t\tpublic void putOirginalValue_";
  protected final String TEXT_7 = "(final ";
  protected final String TEXT_8 = "Struct ";
  protected final String TEXT_9 = ",";
  protected final String TEXT_10 = "){";
  protected final String TEXT_11 = NL + "\t\t\t";
  protected final String TEXT_12 = ".";
  protected final String TEXT_13 = " = ";
  protected final String TEXT_14 = " ;";
  protected final String TEXT_15 = NL + "\t\t}";
  protected final String TEXT_16 = NL + "\t\tpublic void putExtractValue_";
  protected final String TEXT_17 = ",int length_";
  protected final String TEXT_18 = ",String[] newFields_";
  protected final String TEXT_19 = ",String temp_";
  protected final String TEXT_20 = " ){";
  protected final String TEXT_21 = ">=length_";
  protected final String TEXT_22 = "?\"\":newFields_";
  protected final String TEXT_23 = "[";
  protected final String TEXT_24 = "];";
  protected final String TEXT_25 = NL + "\t\t\ttemp_";
  protected final String TEXT_26 = "];" + NL + "\t\t\tif(temp_";
  protected final String TEXT_27 = ".length() > 0) {";
  protected final String TEXT_28 = NL + "\t\t\t\t";
  protected final String TEXT_29 = " = temp_";
  protected final String TEXT_30 = ".getBytes();";
  protected final String TEXT_31 = " = ParserUtils.parseTo_Date(temp_";
  protected final String TEXT_32 = ", ";
  protected final String TEXT_33 = ", false);";
  protected final String TEXT_34 = ");";
  protected final String TEXT_35 = " = ParserUtils.parseTo_";
  protected final String TEXT_36 = "(ParserUtils.parseTo_Number(temp_";
  protected final String TEXT_37 = "));";
  protected final String TEXT_38 = "(temp_";
  protected final String TEXT_39 = NL + "\t\t\t} else {\t\t\t\t\t\t";
  protected final String TEXT_40 = NL + "\t\t\t\tthrow new RuntimeException(\"Value is empty for column : '";
  protected final String TEXT_41 = "' in '";
  protected final String TEXT_42 = "' connection, value is invalid or this column should be nullable or have a default value.\");";
  protected final String TEXT_43 = NL + "\t\t";
  protected final String TEXT_44 = ";";
  protected final String TEXT_45 = NL + "\t\t\t}";
  protected final String TEXT_46 = NL + "\t\tpublic void putRejectValue_";
  protected final String TEXT_47 = NL + "\t}" + NL + "\tOptimizedCodeUtil_";
  protected final String TEXT_48 = " optimizedCodeUtil_";
  protected final String TEXT_49 = "=new OptimizedCodeUtil_";
  protected final String TEXT_50 = "();";

  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
    stringBuffer.append(TEXT_1);
    
CodeGeneratorArgument codeGenArgument = (CodeGeneratorArgument) argument;
INode node = (INode)codeGenArgument.getArgument();
String cid = node.getUniqueName();

String advancedSeparatorStr = ElementParameterParser.getValue(node, "__ADVANCED_SEPARATOR__");
boolean advancedSeparator = (advancedSeparatorStr!=null&&!("").equals(advancedSeparatorStr))?("true").equals(advancedSeparatorStr):false;
String thousandsSeparator = ElementParameterParser.getValueWithJavaType(node, "__THOUSANDS_SEPARATOR__", JavaTypesManager.CHARACTER);
String decimalSeparator = ElementParameterParser.getValueWithJavaType(node, "__DECIMAL_SEPARATOR__", JavaTypesManager.CHARACTER);

int schemaOptNum=100;
String schemaOptNumStr=ElementParameterParser.getValue(node, "__SCHEMA_OPT_NUM__");
if(schemaOptNumStr!=null && !"".equals(schemaOptNumStr) && !"\"\"".equals(schemaOptNumStr)){
	schemaOptNum  = Integer.parseInt(schemaOptNumStr);
}

boolean trim = ("true").equals(ElementParameterParser.getValue(node, "__TRIM__"));

String checkDateStr = ElementParameterParser.getValue(node,"__CHECK_DATE__");
boolean checkDate = (checkDateStr!=null&&!("").equals(checkDateStr))?("true").equals(checkDateStr):false;

String checkNumStr = ElementParameterParser.getValue(node, "__CHECK_FIELDS_NUM__");
boolean checkNum = (checkNumStr!=null&&!("").equals(checkNumStr))?("true").equals(checkNumStr):false; 

String dieOnErrorStr = ElementParameterParser.getValue(node, "__DIE_ON_ERROR__");
boolean dieOnError = (dieOnErrorStr!=null&&!("").equals(dieOnErrorStr))?("true").equals(dieOnErrorStr):false;

IConnection inConn = null;
List< ? extends IConnection> inConns = node.getIncomingConnections();
if(inConns!=null){
    for (IConnection conn : inConns) {
    	if (conn.getLineStyle().hasConnectionCategory(IConnectionCategory.DATA)) {
    		inConn = conn;
    		break;
    	}
    }
}

String rejectConnName = "";
List<IMetadataColumn> rejectColumnList = null;
List<? extends IConnection> rejectConns = node.getOutgoingConnections("REJECT");
if(rejectConns != null && rejectConns.size() > 0) {
    IConnection rejectConn = rejectConns.get(0);
    rejectColumnList = rejectConn.getMetadataTable().getListColumns();
    rejectConnName = rejectConn.getName();
}

String firstConnName = "";
IConnection outConn = null;
List< ? extends IConnection> outConns = node.getOutgoingSortedConnections();
if(outConns!=null){
    for (IConnection conn : outConns) {
    	if (conn.getLineStyle().hasConnectionCategory(IConnectionCategory.DATA)) {
    		outConn = conn;
    		firstConnName = outConn.getName();
    		break;
    	}
    }
}

    stringBuffer.append(TEXT_2);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_3);
    
	//set original columns
List<IMetadataColumn> newColumnList = new ArrayList<IMetadataColumn>();
List<IMetadataColumn> oirginalColumnsList = new ArrayList<IMetadataColumn>();
IMetadataTable inputMetadataTable = inConn.getMetadataTable();
IMetadataTable outputMetadataTable = outConn.getMetadataTable();

for (IMetadataColumn outputCol : outputMetadataTable.getListColumns()) {
	boolean isOirginalColumn = false;
	for (IMetadataColumn inputCol : inputMetadataTable.getListColumns()) {
		if( outputCol.getLabel().equals( inputCol.getLabel()) ){
			isOirginalColumn = true;
			oirginalColumnsList.add(outputCol);
			break;
		}
	}
	if(!isOirginalColumn){
		if(!("").equals(rejectConnName)&&rejectConnName.equals(firstConnName)
			&& (outputCol.getLabel().equals("errorMessage") || outputCol.getLabel().equals("errorCode"))){
		}else{
			newColumnList.add(outputCol);
		}
	}
}
if(outConn!=null && inConn!=null){
	int sizeColumns=outputMetadataTable.getListColumns().size();
	if(sizeColumns> schemaOptNum){//D1

    stringBuffer.append(TEXT_4);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_5);
    
		for(int i=0; i<oirginalColumnsList.size();i++){
			IMetadataColumn column = oirginalColumnsList.get(i);
			if(i%schemaOptNum==0){

    stringBuffer.append(TEXT_6);
    stringBuffer.append(i/schemaOptNum);
    stringBuffer.append(TEXT_7);
    stringBuffer.append(NodeUtil.getPrivateConnClassName(inConn) );
    stringBuffer.append(TEXT_8);
    stringBuffer.append(inConn.getName() );
    stringBuffer.append(TEXT_9);
    stringBuffer.append(outConn.getName());
    stringBuffer.append(TEXT_8);
    stringBuffer.append(outConn.getName());
    stringBuffer.append(TEXT_10);
    
			}

    stringBuffer.append(TEXT_11);
    stringBuffer.append(outConn.getName());
    stringBuffer.append(TEXT_12);
    stringBuffer.append(column.getLabel());
    stringBuffer.append(TEXT_13);
    stringBuffer.append(inConn.getName());
    stringBuffer.append(TEXT_12);
    stringBuffer.append(column.getLabel());
    stringBuffer.append(TEXT_14);
    
			if((i+1)%schemaOptNum==0){

    stringBuffer.append(TEXT_15);
    
			}
		}
		if(oirginalColumnsList.size()>0&&(oirginalColumnsList.size()%schemaOptNum)>0){

    stringBuffer.append(TEXT_15);
    
		}
		for(int i=0; i<newColumnList.size();i++){
			IMetadataColumn column = newColumnList.get(i);
			String typeToGenerate = JavaTypesManager.getTypeToGenerate(column.getTalendType(), column.isNullable());
			JavaType javaType = JavaTypesManager.getJavaTypeFromId(column.getTalendType());
			String patternValue = column.getPattern() == null || column.getPattern().trim().length() == 0 ? null : column.getPattern();
			if(i%schemaOptNum==0){

    stringBuffer.append(TEXT_16);
    stringBuffer.append(i/schemaOptNum);
    stringBuffer.append(TEXT_7);
    stringBuffer.append(NodeUtil.getPrivateConnClassName(inConn) );
    stringBuffer.append(TEXT_8);
    stringBuffer.append(inConn.getName() );
    stringBuffer.append(TEXT_9);
    stringBuffer.append(outConn.getName());
    stringBuffer.append(TEXT_8);
    stringBuffer.append(outConn.getName());
    stringBuffer.append(TEXT_17);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_18);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_19);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_20);
    
			}
			if(javaType == JavaTypesManager.STRING || javaType == JavaTypesManager.OBJECT){

    stringBuffer.append(TEXT_11);
    stringBuffer.append(outConn.getName());
    stringBuffer.append(TEXT_12);
    stringBuffer.append(column.getLabel() );
    stringBuffer.append(TEXT_13);
    stringBuffer.append(i);
    stringBuffer.append(TEXT_21);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_22);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_23);
    stringBuffer.append(i);
    stringBuffer.append(TEXT_24);
    
			}else{ 

    stringBuffer.append(TEXT_25);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_13);
    stringBuffer.append(i);
    stringBuffer.append(TEXT_21);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_22);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_23);
    stringBuffer.append(i);
    stringBuffer.append(TEXT_26);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_27);
    
				if(javaType == JavaTypesManager.BYTE_ARRAY){ 

    stringBuffer.append(TEXT_28);
    stringBuffer.append(outConn.getName() );
    stringBuffer.append(TEXT_12);
    stringBuffer.append(column.getLabel() );
    stringBuffer.append(TEXT_29);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_30);
    
				}else if(javaType == JavaTypesManager.DATE) {
					if(checkNum || checkDate){

    stringBuffer.append(TEXT_28);
    stringBuffer.append(outConn.getName() );
    stringBuffer.append(TEXT_12);
    stringBuffer.append(column.getLabel() );
    stringBuffer.append(TEXT_31);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_32);
    stringBuffer.append( patternValue );
    stringBuffer.append(TEXT_33);
    
					}else{

    stringBuffer.append(TEXT_28);
    stringBuffer.append(outConn.getName() );
    stringBuffer.append(TEXT_12);
    stringBuffer.append(column.getLabel() );
    stringBuffer.append(TEXT_31);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_32);
    stringBuffer.append( patternValue );
    stringBuffer.append(TEXT_34);
    
					}
				}else if(advancedSeparator && JavaTypesManager.isNumberType(javaType)) { 

    stringBuffer.append(TEXT_28);
    stringBuffer.append(outConn.getName() );
    stringBuffer.append(TEXT_12);
    stringBuffer.append(column.getLabel() );
    stringBuffer.append(TEXT_35);
    stringBuffer.append( typeToGenerate );
    stringBuffer.append(TEXT_36);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_32);
    stringBuffer.append( thousandsSeparator );
    stringBuffer.append(TEXT_32);
    stringBuffer.append( decimalSeparator );
    stringBuffer.append(TEXT_37);
    
				} else {

    stringBuffer.append(TEXT_28);
    stringBuffer.append(outConn.getName() );
    stringBuffer.append(TEXT_12);
    stringBuffer.append(column.getLabel() );
    stringBuffer.append(TEXT_35);
    stringBuffer.append( typeToGenerate );
    stringBuffer.append(TEXT_38);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_34);
    
				}

    stringBuffer.append(TEXT_39);
    
				String defaultValue = JavaTypesManager.getDefaultValueFromJavaType(typeToGenerate, column.getDefault());
				if(defaultValue == null) {

    stringBuffer.append(TEXT_40);
    stringBuffer.append( column.getLabel() );
    stringBuffer.append(TEXT_41);
    stringBuffer.append(outConn.getName() );
    stringBuffer.append(TEXT_42);
    
				} else {

    stringBuffer.append(TEXT_43);
    stringBuffer.append(outConn.getName() );
    stringBuffer.append(TEXT_12);
    stringBuffer.append(column.getLabel() );
    stringBuffer.append(TEXT_13);
    stringBuffer.append(defaultValue );
    stringBuffer.append(TEXT_44);
    
				}

    stringBuffer.append(TEXT_45);
    
			}
			if((i+1)%schemaOptNum==0){

    stringBuffer.append(TEXT_15);
    
			}
		}
		if(newColumnList.size()>0&&(newColumnList.size()%schemaOptNum)>0){

    stringBuffer.append(TEXT_15);
    
		}
		if(!dieOnError){
			if(!("").equals(rejectConnName)&&!rejectConnName.equals(firstConnName)&&rejectColumnList != null && rejectColumnList.size() > 0) {
				for(int i=0; i<sizeColumns;i++){
					IMetadataColumn column = outputMetadataTable.getListColumns().get(i);
					if(i%schemaOptNum==0){

    stringBuffer.append(TEXT_46);
    stringBuffer.append(i/schemaOptNum);
    stringBuffer.append(TEXT_7);
    stringBuffer.append(firstConnName );
    stringBuffer.append(TEXT_8);
    stringBuffer.append(firstConnName );
    stringBuffer.append(TEXT_9);
    stringBuffer.append(rejectConnName);
    stringBuffer.append(TEXT_8);
    stringBuffer.append(rejectConnName);
    stringBuffer.append(TEXT_10);
    
					}

    stringBuffer.append(TEXT_11);
    stringBuffer.append(rejectConnName);
    stringBuffer.append(TEXT_12);
    stringBuffer.append(column.getLabel());
    stringBuffer.append(TEXT_13);
    stringBuffer.append(firstConnName);
    stringBuffer.append(TEXT_12);
    stringBuffer.append(column.getLabel());
    stringBuffer.append(TEXT_14);
    
					if((i+1)%schemaOptNum==0){

    stringBuffer.append(TEXT_15);
    
					}
				}
				if(sizeColumns>0&&(sizeColumns%schemaOptNum)>0){

    stringBuffer.append(TEXT_15);
    
				}
			}
		}

    stringBuffer.append(TEXT_47);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_48);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_49);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_50);
    
	}
}

    return stringBuffer.toString();
  }
}
