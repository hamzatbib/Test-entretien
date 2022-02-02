package org.talend.designer.codegen.translators.xml;

import org.talend.core.model.process.INode;
import org.talend.designer.codegen.config.CodeGeneratorArgument;
import org.talend.core.model.process.ElementParameterParser;
import java.util.List;
import org.talend.core.model.metadata.IMetadataTable;
import org.talend.core.model.process.IConnection;
import org.talend.core.model.process.IConnectionCategory;
import org.talend.core.model.utils.NodeUtil;
import org.talend.core.model.process.EConnectionType;

public class TWriteJSONFieldInEndJava
{
  protected static String nl;
  public static synchronized TWriteJSONFieldInEndJava create(String lineSeparator)
  {
    nl = lineSeparator;
    TWriteJSONFieldInEndJava result = new TWriteJSONFieldInEndJava();
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
  protected final String TEXT_18 = NL + "\t\t\t\t\t}" + NL + "\t\t\t\t}" + NL + "\t\t\t\t";
  protected final String TEXT_19 = NL + "\t\t\t\t\ttxf_";
  protected final String TEXT_20 = ".join();" + NL + "\t\t\t\t\tif(txf_";
  protected final String TEXT_21 = ".getLastException()!=null) {" + NL + "\t\t\t\t\t\tcurrentComponent = txf_";
  protected final String TEXT_22 = ".getCurrentComponent();" + NL + "\t\t\t\t\t\tthrow txf_";
  protected final String TEXT_23 = ".getLastException();" + NL + "\t\t\t\t\t}" + NL + "\t\t\t\t\tif(txf_";
  protected final String TEXT_24 = ".getLastError()!=null) {" + NL + "\t\t\t\t\t\tthrow txf_";
  protected final String TEXT_25 = ".getLastError();" + NL + "\t\t\t\t\t}" + NL + "\t\t\t\t\tglobalMap.remove(\"queue_";
  protected final String TEXT_26 = "\");" + NL + "\t\t\t\t";
  protected final String TEXT_27 = NL + "\t\t\t\t\tString readFinishWithExceptionMarkWithPipeId_";
  protected final String TEXT_28 = " = \"";
  protected final String TEXT_29 = "_FINISH_WITH_EXCEPTION\"+(queue_";
  protected final String TEXT_30 = "==null?\"\":queue_";
  protected final String TEXT_31 = ".hashCode());" + NL + "\t\t\t\t\tif(globalMap.containsKey(readFinishWithExceptionMarkWithPipeId_";
  protected final String TEXT_32 = ")){" + NL + "\t\t\t\t\t\tif(!(globalMap instanceof java.util.concurrent.ConcurrentHashMap)) {" + NL + "\t\t\t\t\t\t\tglobalMap.put(readFinishWithExceptionMarkWithPipeId_";
  protected final String TEXT_33 = ", null);// syn" + NL + "\t\t\t\t\t\t}" + NL + "\t\t\t\t\t\tglobalMap.remove(readFinishWithExceptionMarkWithPipeId_";
  protected final String TEXT_34 = ");" + NL + "\t\t\t\t\t\treturn;" + NL + "\t\t\t\t\t}" + NL + "\t\t\t\t\tglobalMap.remove(\"queue_";
  protected final String TEXT_35 = "\");" + NL + "    \t\t\t";
  protected final String TEXT_36 = NL + "\t\t\t\tif(!(globalMap instanceof java.util.concurrent.ConcurrentHashMap)) {" + NL + "\t\t\t\t\tglobalMap.put(readFinishMarkWithPipeId_";
  protected final String TEXT_37 = ",null);//syn" + NL + "\t\t\t\t}" + NL + "\t\t\t\tglobalMap.remove(readFinishMarkWithPipeId_";
  protected final String TEXT_38 = ");" + NL + "\t\t\t";
  protected final String TEXT_39 = NL + "globalMap.put(\"";
  protected final String TEXT_40 = "_NB_LINE\",nb_line_";
  protected final String TEXT_41 = ");";

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
String destination = ElementParameterParser.getValue(node, "__DESTINATION__");

List<IMetadataTable> metadatas = node.getMetadataList();
if ((metadatas!=null)&&(metadatas.size()>0)) {
    IMetadataTable metadata = metadatas.get(0);
    if (metadata!=null) {
    	List< ? extends IConnection> conns = node.getOutgoingSortedConnections();
		if(conns!=null && conns.size()>0){
			if (conns.get(0).getLineStyle().hasConnectionCategory(IConnectionCategory.DATA)) {
			
    stringBuffer.append(TEXT_18);
    
				INode sourceNode = node.getIncomingConnections(EConnectionType.ON_COMPONENT_OK).get(0).getSource();
				String virtualSourceCid = sourceNode.getUniqueName();
				INode startNode = NodeUtil.getSpecificStartNode(sourceNode);
				String startNodeCid = null; 
				if(startNode != null){
					startNodeCid = startNode.getUniqueName();
				} 
				IConnection nextMergeConn = NodeUtil.getNextMergeConnection(node);
				if(nextMergeConn != null && nextMergeConn.getInputId()>1 && startNodeCid != null){
	   			
    stringBuffer.append(TEXT_19);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_20);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_21);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_22);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_23);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_24);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_25);
    stringBuffer.append(virtualSourceCid);
    stringBuffer.append(TEXT_26);
    
    			}else{
				
    stringBuffer.append(TEXT_27);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_28);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_29);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_30);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_31);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_32);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_33);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_34);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_35);
    
    			}
				
    stringBuffer.append(TEXT_36);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_37);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_38);
    
			}
		}
	}
}

String strJobCid="";
if(destination !=null && !("").equals(destination.trim()))
	strJobCid=destination;
else{
	strJobCid= cid.substring(0,cid.length()-3);
}

    stringBuffer.append(TEXT_39);
    stringBuffer.append(strJobCid );
    stringBuffer.append(TEXT_40);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_41);
    
log4jFileUtil.logDataCountInfo();

    return stringBuffer.toString();
  }
}
