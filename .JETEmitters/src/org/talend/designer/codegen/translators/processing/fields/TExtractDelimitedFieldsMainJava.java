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
import org.talend.core.model.process.BlockCode;
import java.util.List;
import java.util.ArrayList;

public class TExtractDelimitedFieldsMainJava
{
  protected static String nl;
  public static synchronized TExtractDelimitedFieldsMainJava create(String lineSeparator)
  {
    nl = lineSeparator;
    TExtractDelimitedFieldsMainJava result = new TExtractDelimitedFieldsMainJava();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "\t\t\t\tlog.debug(\"";
  protected final String TEXT_2 = " - Retrieving records from the datasource.\");" + NL + "\t\t\t";
  protected final String TEXT_3 = NL + "\t\t\t\tlog.debug(\"";
  protected final String TEXT_4 = " - Retrieved records count: \"+ nb_line_";
  protected final String TEXT_5 = " + \" .\");" + NL + "\t\t\t";
  protected final String TEXT_6 = " - Retrieved records count: \"+ globalMap.get(\"";
  protected final String TEXT_7 = "_NB_LINE\") + \" .\");" + NL + "\t\t\t";
  protected final String TEXT_8 = " - Written records count: \" + nb_line_";
  protected final String TEXT_9 = NL + "\t\t\t\tfinal StringBuffer log4jSb_";
  protected final String TEXT_10 = " = new StringBuffer();" + NL + "\t\t\t";
  protected final String TEXT_11 = " - Retrieving the record \" + (nb_line_";
  protected final String TEXT_12 = ") + \".\");" + NL + "\t\t\t";
  protected final String TEXT_13 = " - Writing the record \" + nb_line_";
  protected final String TEXT_14 = " + \" to the file.\");" + NL + "\t\t\t";
  protected final String TEXT_15 = " - Processing the record \" + nb_line_";
  protected final String TEXT_16 = " + \".\");" + NL + "\t\t\t";
  protected final String TEXT_17 = " - Processed records count: \" + nb_line_";
  protected final String TEXT_18 = NL;
  protected final String TEXT_19 = " = null;";
  protected final String TEXT_20 = " = new ";
  protected final String TEXT_21 = "Struct();";
  protected final String TEXT_22 = NL + "\tString field_";
  protected final String TEXT_23 = " = ";
  protected final String TEXT_24 = ".";
  protected final String TEXT_25 = ";" + NL + "\tString[] newFields_";
  protected final String TEXT_26 = " = new String[0];";
  protected final String TEXT_27 = NL + "\tif(field_";
  protected final String TEXT_28 = "!=null){// C_01" + NL + "\t\ttry{" + NL + "\t\t\tnewFields_";
  protected final String TEXT_29 = " = field_";
  protected final String TEXT_30 = ".split(";
  protected final String TEXT_31 = ",-1);";
  protected final String TEXT_32 = NL + "\ttry{" + NL + "\t\tif(field_";
  protected final String TEXT_33 = "!=null){// C_01" + NL + "\t\t\tnewFields_";
  protected final String TEXT_34 = ",-1);" + NL + "\t\t}";
  protected final String TEXT_35 = NL + "\t\tint length_";
  protected final String TEXT_36 = " = newFields_";
  protected final String TEXT_37 = ".length;";
  protected final String TEXT_38 = NL + "\t\tfor(int i_";
  protected final String TEXT_39 = " = 0;i_";
  protected final String TEXT_40 = " < length_";
  protected final String TEXT_41 = ";i_";
  protected final String TEXT_42 = "++){" + NL + "\t\t\tnewFields_";
  protected final String TEXT_43 = "[i_";
  protected final String TEXT_44 = "] = newFields_";
  protected final String TEXT_45 = "].trim();" + NL + "\t\t}";
  protected final String TEXT_46 = NL + "\t\toptimizedCodeUtil_";
  protected final String TEXT_47 = ".putOirginalValue_";
  protected final String TEXT_48 = "(";
  protected final String TEXT_49 = ",";
  protected final String TEXT_50 = ");";
  protected final String TEXT_51 = NL + "\t\t";
  protected final String TEXT_52 = " ;";
  protected final String TEXT_53 = NL + "\tString temp_";
  protected final String TEXT_54 = ".putExtractValue_";
  protected final String TEXT_55 = ",length_";
  protected final String TEXT_56 = ",newFields_";
  protected final String TEXT_57 = ",temp_";
  protected final String TEXT_58 = " );";
  protected final String TEXT_59 = NL + "\t";
  protected final String TEXT_60 = ">=length_";
  protected final String TEXT_61 = "?\"\":newFields_";
  protected final String TEXT_62 = "[";
  protected final String TEXT_63 = "];";
  protected final String TEXT_64 = NL + "\ttemp_";
  protected final String TEXT_65 = "];" + NL + "\tif(temp_";
  protected final String TEXT_66 = ".length() > 0) {";
  protected final String TEXT_67 = " = temp_";
  protected final String TEXT_68 = ".getBytes();";
  protected final String TEXT_69 = " = ParserUtils.parseTo_Date(temp_";
  protected final String TEXT_70 = ", ";
  protected final String TEXT_71 = ", false);";
  protected final String TEXT_72 = " = ParserUtils.parseTo_";
  protected final String TEXT_73 = "(ParserUtils.parseTo_Number(temp_";
  protected final String TEXT_74 = "));";
  protected final String TEXT_75 = "(temp_";
  protected final String TEXT_76 = NL + "\t} else {\t\t\t\t\t\t";
  protected final String TEXT_77 = NL + "\t\tthrow new RuntimeException(\"Value is empty for column : '";
  protected final String TEXT_78 = "' in '";
  protected final String TEXT_79 = "' connection, value is invalid or this column should be nullable or have a default value.\");";
  protected final String TEXT_80 = ";";
  protected final String TEXT_81 = NL + "\t}";
  protected final String TEXT_82 = NL + "\t int filedsum_";
  protected final String TEXT_83 = ".length;" + NL + "\t if(filedsum_";
  protected final String TEXT_84 = " < ";
  protected final String TEXT_85 = "){" + NL + "\t \tthrow new RuntimeException(\"Column(s) missing\");" + NL + "\t } else if(filedsum_";
  protected final String TEXT_86 = " > ";
  protected final String TEXT_87 = ") {" + NL + "\t \tthrow new RuntimeException(\"Too many columns\");" + NL + "\t }     ";
  protected final String TEXT_88 = NL + "\tnb_line_";
  protected final String TEXT_89 = "++;";
  protected final String TEXT_90 = NL + "}catch(java.lang.Exception ex_";
  protected final String TEXT_91 = "){";
  protected final String TEXT_92 = NL + "\tthrow(ex_";
  protected final String TEXT_93 = NL + "\toptimizedCodeUtil_";
  protected final String TEXT_94 = ".putRejectValue_";
  protected final String TEXT_95 = NL + "    ";
  protected final String TEXT_96 = "    ";
  protected final String TEXT_97 = ".errorMessage = ex_";
  protected final String TEXT_98 = ".getMessage() + \" - Line: \" + tos_count_";
  protected final String TEXT_99 = NL + "\tlog.error(\"";
  protected final String TEXT_100 = " - \" + ex_";
  protected final String TEXT_101 = ".getMessage());";
  protected final String TEXT_102 = NL + "    System.err.println(ex_";
  protected final String TEXT_103 = NL + "}";
  protected final String TEXT_104 = NL + NL;

  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
    
	//this util class use by set log4j debug paramters
	class DefaultLog4jFileUtil {
	
		INode node = null;
	    String cid = null;
 		boolean isLog4jEnabled = false;
 		String label = null;
 		
 		public DefaultLog4jFileUtil(){
 		}
 		public DefaultLog4jFileUtil(INode node) {
 			this.node = node;
 			this.cid = node.getUniqueName();
 			this.label = cid;
			this.isLog4jEnabled = ("true").equals(org.talend.core.model.process.ElementParameterParser.getValue(node.getProcess(), "__LOG4J_ACTIVATE__"));
 		}
 		
 		public void setCid(String cid) {
 			this.cid = cid;
 		}
 		
		//for all tFileinput* components 
		public void startRetriveDataInfo() {
			if (isLog4jEnabled) {
			
    stringBuffer.append(TEXT_1);
    stringBuffer.append(label);
    stringBuffer.append(TEXT_2);
    
			}
		}
		
		public void retrievedDataNumberInfo() {
			if (isLog4jEnabled) {
			
    stringBuffer.append(TEXT_3);
    stringBuffer.append(label);
    stringBuffer.append(TEXT_4);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_5);
    
			}
		}
		
		public void retrievedDataNumberInfoFromGlobalMap(INode node) {
			if (isLog4jEnabled) {
			
    stringBuffer.append(TEXT_3);
    stringBuffer.append(label);
    stringBuffer.append(TEXT_6);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_7);
    
			}
		}
		
		//for all tFileinput* components 
		public void retrievedDataNumberInfo(INode node) {
			if (isLog4jEnabled) {
			
    stringBuffer.append(TEXT_3);
    stringBuffer.append(label);
    stringBuffer.append(TEXT_4);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_5);
    
			}
		}
		
		public void writeDataFinishInfo(INode node) {
			if(isLog4jEnabled){
			
    stringBuffer.append(TEXT_3);
    stringBuffer.append(label);
    stringBuffer.append(TEXT_8);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_5);
    
			}
		}
		
 		//TODO delete it and remove all log4jSb parameter from components
		public void componentStartInfo(INode node) {
			if (isLog4jEnabled) {
			
    stringBuffer.append(TEXT_9);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_10);
    
			}
		}
		
		//TODO rename or delete it
		public void debugRetriveData(INode node,boolean hasIncreased) {
			if(isLog4jEnabled){
			
    stringBuffer.append(TEXT_3);
    stringBuffer.append(label);
    stringBuffer.append(TEXT_11);
    stringBuffer.append(cid);
    stringBuffer.append(hasIncreased?"":"+1");
    stringBuffer.append(TEXT_12);
    
			}
		}
		
		//TODO rename or delete it
		public void debugRetriveData(INode node) {
			debugRetriveData(node,true);
		}
		
		//TODO rename or delete it
		public void debugWriteData(INode node) {
			if(isLog4jEnabled){
			
    stringBuffer.append(TEXT_3);
    stringBuffer.append(label);
    stringBuffer.append(TEXT_13);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_14);
    
			}
		}
		
		public void logCurrentRowNumberInfo() {
			if(isLog4jEnabled){
			
    stringBuffer.append(TEXT_3);
    stringBuffer.append(label);
    stringBuffer.append(TEXT_15);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_16);
    
			}
		}
		
		public void logDataCountInfo() {
			if(isLog4jEnabled){
			
    stringBuffer.append(TEXT_3);
    stringBuffer.append(label);
    stringBuffer.append(TEXT_17);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_5);
    
			}
		}
	}
	
	final DefaultLog4jFileUtil log4jFileUtil = new DefaultLog4jFileUtil((INode)(((org.talend.designer.codegen.config.CodeGeneratorArgument)argument).getArgument()));
	
    
CodeGeneratorArgument codeGenArgument = (CodeGeneratorArgument) argument;
INode node = (INode)codeGenArgument.getArgument();
String cid = node.getUniqueName();

String field = ElementParameterParser.getValue(node, "__FIELD__");
boolean ignoreSourceNull="true".equalsIgnoreCase(ElementParameterParser.getValue(node, "__IGNORE_SOURCE_NULL__"));
String separator = ElementParameterParser.getValue(node, "__FIELDSEPARATOR__");

int schemaOptNum=100;
String schemaOptNumStr=ElementParameterParser.getValue(node, "__SCHEMA_OPT_NUM__");
if(schemaOptNumStr!=null && !"".equals(schemaOptNumStr) && !"\"\"".equals(schemaOptNumStr)){
	schemaOptNum  = Integer.parseInt(schemaOptNumStr);
}

String advancedSeparatorStr = ElementParameterParser.getValue(node, "__ADVANCED_SEPARATOR__");
boolean advancedSeparator = (advancedSeparatorStr!=null&&!("").equals(advancedSeparatorStr))?("true").equals(advancedSeparatorStr):false;
String thousandsSeparator = ElementParameterParser.getValueWithJavaType(node, "__THOUSANDS_SEPARATOR__", JavaTypesManager.CHARACTER);
String decimalSeparator = ElementParameterParser.getValueWithJavaType(node, "__DECIMAL_SEPARATOR__", JavaTypesManager.CHARACTER);

boolean trim = ("true").equals(ElementParameterParser.getValue(node, "__TRIM__"));

String checkDateStr = ElementParameterParser.getValue(node,"__CHECK_DATE__");
boolean checkDate = (checkDateStr!=null&&!("").equals(checkDateStr))?("true").equals(checkDateStr):false;

String checkNumStr = ElementParameterParser.getValue(node, "__CHECK_FIELDS_NUM__");
boolean checkNum = (checkNumStr!=null&&!("").equals(checkNumStr))?("true").equals(checkNumStr):false; 

String dieOnErrorStr = ElementParameterParser.getValue(node, "__DIE_ON_ERROR__");
boolean dieOnError = (dieOnErrorStr!=null&&!("").equals(dieOnErrorStr))?("true").equals(dieOnErrorStr):false;

boolean isLog4jEnabled = "true".equals(ElementParameterParser.getValue(node.getProcess(), "__LOG4J_ACTIVATE__"));

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

if(outConns!=null){
    for (IConnection conn : outConns) {
    	if (conn.getLineStyle().hasConnectionCategory(IConnectionCategory.DATA)) {

    stringBuffer.append(TEXT_18);
    stringBuffer.append(conn.getName() );
    stringBuffer.append(TEXT_19);
    
    	}
    }
}

//get field column
if(outConn!=null && inConn!=null){

    stringBuffer.append(TEXT_18);
    stringBuffer.append(TEXT_18);
    stringBuffer.append(outConn.getName() );
    stringBuffer.append(TEXT_20);
    stringBuffer.append(outConn.getName() );
    stringBuffer.append(TEXT_21);
    
	IMetadataTable inputMetadataTable = inConn.getMetadataTable();
	for (IMetadataColumn inputCol : inputMetadataTable.getListColumns()) {
		if(inputCol.getLabel().equals(field)){

    stringBuffer.append(TEXT_22);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_23);
    stringBuffer.append(inConn.getName());
    stringBuffer.append(TEXT_24);
    stringBuffer.append(field);
    stringBuffer.append(TEXT_25);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_26);
    
	if(ignoreSourceNull){

    stringBuffer.append(TEXT_27);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_28);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_29);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_30);
    stringBuffer.append(separator);
    stringBuffer.append(TEXT_31);
    
		List<BlockCode> blockCodes = new java.util.ArrayList<BlockCode>(1);
		blockCodes.add(new BlockCode("C_01"));
		((org.talend.core.model.process.AbstractNode) node).setBlocksCodeToClose(blockCodes);
	}else{

    stringBuffer.append(TEXT_32);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_33);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_29);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_30);
    stringBuffer.append(separator);
    stringBuffer.append(TEXT_34);
    
	}

    stringBuffer.append(TEXT_35);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_36);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_37);
    
			if(trim){

    stringBuffer.append(TEXT_38);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_39);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_40);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_41);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_42);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_43);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_44);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_43);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_45);
    
			}
			break;
		}
	}


//set original columns
List<IMetadataColumn> newColumnList = new ArrayList<IMetadataColumn>();
List<IMetadataColumn> oirginalColumnsList = new ArrayList<IMetadataColumn>();
IMetadataTable outputMetadataTable = outConn.getMetadataTable();
int sizeColumns=outputMetadataTable.getListColumns().size();

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
	for(int i=0; i<oirginalColumnsList.size();i++){
		IMetadataColumn column = oirginalColumnsList.get(i);
		if(sizeColumns> schemaOptNum){//D1
			if(i%schemaOptNum==0){

    stringBuffer.append(TEXT_46);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_47);
    stringBuffer.append(i/schemaOptNum);
    stringBuffer.append(TEXT_48);
    stringBuffer.append(inConn.getName() );
    stringBuffer.append(TEXT_49);
    stringBuffer.append(outConn.getName());
    stringBuffer.append(TEXT_50);
    
			}
		}else{

    stringBuffer.append(TEXT_51);
    stringBuffer.append(outConn.getName());
    stringBuffer.append(TEXT_24);
    stringBuffer.append(column.getLabel());
    stringBuffer.append(TEXT_23);
    stringBuffer.append(inConn.getName());
    stringBuffer.append(TEXT_24);
    stringBuffer.append(column.getLabel());
    stringBuffer.append(TEXT_52);
    
		}//D1
	}

    stringBuffer.append(TEXT_53);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_19);
    
	for(int i=0; i<newColumnList.size();i++){
		IMetadataColumn column = newColumnList.get(i);
		String typeToGenerate = JavaTypesManager.getTypeToGenerate(column.getTalendType(), column.isNullable());
		JavaType javaType = JavaTypesManager.getJavaTypeFromId(column.getTalendType());
		String patternValue = column.getPattern() == null || column.getPattern().trim().length() == 0 ? null : column.getPattern();
    
		if(sizeColumns> schemaOptNum){//D2
			if(i%schemaOptNum==0){

    stringBuffer.append(TEXT_46);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_54);
    stringBuffer.append(i/schemaOptNum);
    stringBuffer.append(TEXT_48);
    stringBuffer.append(inConn.getName() );
    stringBuffer.append(TEXT_49);
    stringBuffer.append(outConn.getName());
    stringBuffer.append(TEXT_55);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_56);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_57);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_58);
    
			}
		}else{
		  if(javaType == JavaTypesManager.STRING || javaType == JavaTypesManager.OBJECT){

    stringBuffer.append(TEXT_59);
    stringBuffer.append(outConn.getName());
    stringBuffer.append(TEXT_24);
    stringBuffer.append(column.getLabel() );
    stringBuffer.append(TEXT_23);
    stringBuffer.append(i);
    stringBuffer.append(TEXT_60);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_61);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_62);
    stringBuffer.append(i);
    stringBuffer.append(TEXT_63);
    
		  }else{ 

    stringBuffer.append(TEXT_64);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_23);
    stringBuffer.append(i);
    stringBuffer.append(TEXT_60);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_61);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_62);
    stringBuffer.append(i);
    stringBuffer.append(TEXT_65);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_66);
    
			if(javaType == JavaTypesManager.BYTE_ARRAY){ 

    stringBuffer.append(TEXT_51);
    stringBuffer.append(outConn.getName() );
    stringBuffer.append(TEXT_24);
    stringBuffer.append(column.getLabel() );
    stringBuffer.append(TEXT_67);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_68);
    
			}else if(javaType == JavaTypesManager.DATE) {
				if(checkNum || checkDate){

    stringBuffer.append(TEXT_51);
    stringBuffer.append(outConn.getName() );
    stringBuffer.append(TEXT_24);
    stringBuffer.append(column.getLabel() );
    stringBuffer.append(TEXT_69);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_70);
    stringBuffer.append( patternValue );
    stringBuffer.append(TEXT_71);
    
				}else{

    stringBuffer.append(TEXT_51);
    stringBuffer.append(outConn.getName() );
    stringBuffer.append(TEXT_24);
    stringBuffer.append(column.getLabel() );
    stringBuffer.append(TEXT_69);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_70);
    stringBuffer.append( patternValue );
    stringBuffer.append(TEXT_50);
    
				}
			}else if(advancedSeparator && JavaTypesManager.isNumberType(javaType)) { 

    stringBuffer.append(TEXT_51);
    stringBuffer.append(outConn.getName() );
    stringBuffer.append(TEXT_24);
    stringBuffer.append(column.getLabel() );
    stringBuffer.append(TEXT_72);
    stringBuffer.append( typeToGenerate );
    stringBuffer.append(TEXT_73);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_70);
    stringBuffer.append( thousandsSeparator );
    stringBuffer.append(TEXT_70);
    stringBuffer.append( decimalSeparator );
    stringBuffer.append(TEXT_74);
    
			} else {

    stringBuffer.append(TEXT_51);
    stringBuffer.append(outConn.getName() );
    stringBuffer.append(TEXT_24);
    stringBuffer.append(column.getLabel() );
    stringBuffer.append(TEXT_72);
    stringBuffer.append( typeToGenerate );
    stringBuffer.append(TEXT_75);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_50);
    
			}

    stringBuffer.append(TEXT_76);
    
			String defaultValue = JavaTypesManager.getDefaultValueFromJavaType(typeToGenerate, column.getDefault());
			if(defaultValue == null) {

    stringBuffer.append(TEXT_77);
    stringBuffer.append( column.getLabel() );
    stringBuffer.append(TEXT_78);
    stringBuffer.append(outConn.getName() );
    stringBuffer.append(TEXT_79);
    
			} else {

    stringBuffer.append(TEXT_51);
    stringBuffer.append(outConn.getName() );
    stringBuffer.append(TEXT_24);
    stringBuffer.append(column.getLabel() );
    stringBuffer.append(TEXT_23);
    stringBuffer.append(defaultValue );
    stringBuffer.append(TEXT_80);
    
			}

    stringBuffer.append(TEXT_81);
    
		 }
		}
	}
	
	if(checkNum) {

    stringBuffer.append(TEXT_82);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_36);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_83);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_84);
    stringBuffer.append(newColumnList.size() );
    stringBuffer.append(TEXT_85);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_86);
    stringBuffer.append(newColumnList.size() );
    stringBuffer.append(TEXT_87);
    
	}
	
	if(!("").equals(rejectConnName) && rejectConnName.equals(firstConnName)){

    stringBuffer.append(TEXT_59);
    stringBuffer.append(outConn.getName() );
    stringBuffer.append(TEXT_19);
    
	}

    stringBuffer.append(TEXT_88);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_89);
    
	log4jFileUtil.logCurrentRowNumberInfo();

    stringBuffer.append(TEXT_90);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_91);
    
	if(dieOnError){

    stringBuffer.append(TEXT_92);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_50);
    
	}else{
		if(!("").equals(rejectConnName)&&!rejectConnName.equals(firstConnName)&&rejectColumnList != null && rejectColumnList.size() > 0) {

    stringBuffer.append(TEXT_59);
    stringBuffer.append(rejectConnName );
    stringBuffer.append(TEXT_20);
    stringBuffer.append(rejectConnName );
    stringBuffer.append(TEXT_21);
    
			for(int i=0; i<sizeColumns;i++){
				IMetadataColumn column = outputMetadataTable.getListColumns().get(i);
				if(sizeColumns> schemaOptNum){//D1
					if(i%schemaOptNum==0){

    stringBuffer.append(TEXT_93);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_94);
    stringBuffer.append(i/schemaOptNum);
    stringBuffer.append(TEXT_48);
    stringBuffer.append(firstConnName );
    stringBuffer.append(TEXT_49);
    stringBuffer.append(rejectConnName);
    stringBuffer.append(TEXT_50);
    
					}
				}else{

    stringBuffer.append(TEXT_95);
    stringBuffer.append(rejectConnName);
    stringBuffer.append(TEXT_24);
    stringBuffer.append(column.getLabel());
    stringBuffer.append(TEXT_23);
    stringBuffer.append(firstConnName );
    stringBuffer.append(TEXT_24);
    stringBuffer.append(column.getLabel() );
    stringBuffer.append(TEXT_80);
    
				}
			}

    stringBuffer.append(TEXT_96);
    stringBuffer.append(TEXT_95);
    stringBuffer.append(rejectConnName);
    stringBuffer.append(TEXT_97);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_98);
    stringBuffer.append(node.getUniqueName() );
    stringBuffer.append(TEXT_80);
    stringBuffer.append(TEXT_95);
    stringBuffer.append(firstConnName );
    stringBuffer.append(TEXT_19);
    
		} else if(("").equals(rejectConnName)){
			if(isLog4jEnabled) {

    stringBuffer.append(TEXT_99);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_100);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_101);
    
			}

    stringBuffer.append(TEXT_102);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_101);
    stringBuffer.append(TEXT_95);
    stringBuffer.append(firstConnName );
    stringBuffer.append(TEXT_19);
    
		} else if(rejectConnName.equals(firstConnName)){

    stringBuffer.append(TEXT_59);
    stringBuffer.append(rejectConnName);
    stringBuffer.append(TEXT_97);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_98);
    stringBuffer.append(node.getUniqueName() );
    stringBuffer.append(TEXT_80);
    
		}
	}

    stringBuffer.append(TEXT_103);
    
}

    stringBuffer.append(TEXT_104);
    stringBuffer.append(TEXT_18);
    return stringBuffer.toString();
  }
}
