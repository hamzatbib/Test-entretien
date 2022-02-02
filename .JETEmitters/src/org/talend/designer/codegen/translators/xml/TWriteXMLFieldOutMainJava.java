package org.talend.designer.codegen.translators.xml;

import org.talend.core.model.process.INode;
import org.talend.core.model.metadata.IMetadataTable;
import org.talend.core.model.metadata.IMetadataColumn;
import org.talend.designer.codegen.config.CodeGeneratorArgument;
import org.talend.core.model.process.ElementParameterParser;
import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Map;
import org.talend.core.model.process.IConnection;
import org.talend.core.model.process.IConnectionCategory;
import org.talend.core.model.metadata.types.JavaTypesManager;
import org.talend.core.model.metadata.types.JavaType;
import org.talend.core.model.utils.NodeUtil;
import org.talend.core.model.process.EConnectionType;

/**
 * add by xzhang
 */
public class TWriteXMLFieldOutMainJava {

  protected static String nl;
  public static synchronized TWriteXMLFieldOutMainJava create(String lineSeparator)
  {
    nl = lineSeparator;
    TWriteXMLFieldOutMainJava result = new TWriteXMLFieldOutMainJava();
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
  protected final String TEXT_18 = NL + "\t\tvalueMap_";
  protected final String TEXT_19 = ".get(\"";
  protected final String TEXT_20 = "\")";
  protected final String TEXT_21 = NL + "\t\tarraysValueMap_";
  protected final String TEXT_22 = NL + "\t(";
  protected final String TEXT_23 = NL + "\t\t";
  protected final String TEXT_24 = ".";
  protected final String TEXT_25 = " != null?";
  protected final String TEXT_26 = NL + "    \t\tFormatterUtils.format_Number(";
  protected final String TEXT_27 = ".toPlainString(), ";
  protected final String TEXT_28 = ",";
  protected final String TEXT_29 = ")\t\t\t\t\t";
  protected final String TEXT_30 = NL + "    \t\tFormatterUtils.format_Number(String.valueOf(";
  protected final String TEXT_31 = "), ";
  protected final String TEXT_32 = ")\t\t\t\t\t\t";
  protected final String TEXT_33 = NL + "            String.valueOf(";
  protected final String TEXT_34 = ")";
  protected final String TEXT_35 = NL + "            FormatterUtils.format_Date(";
  protected final String TEXT_36 = NL + "\t\t\t";
  protected final String TEXT_37 = ".toPlainString()";
  protected final String TEXT_38 = NL + "            new String(";
  protected final String TEXT_39 = NL + "\t\t\tnestXMLTool_";
  protected final String TEXT_40 = ".objectToString(";
  protected final String TEXT_41 = NL + "            ";
  protected final String TEXT_42 = ".toString()";
  protected final String TEXT_43 = ":";
  protected final String TEXT_44 = "null";
  protected final String TEXT_45 = NL + "\t\t)";
  protected final String TEXT_46 = "_";
  protected final String TEXT_47 = ".setName(\"";
  protected final String TEXT_48 = "\");";
  protected final String TEXT_49 = NL + "\t\torg.dom4j.Element ";
  protected final String TEXT_50 = ";" + NL + "\t\tif (";
  protected final String TEXT_51 = ".getNamespaceForPrefix(\"";
  protected final String TEXT_52 = "\") == null) {";
  protected final String TEXT_53 = " = org.dom4j.DocumentHelper.createElement(\"";
  protected final String TEXT_54 = "\");" + NL + "        } else {" + NL + "        \t";
  protected final String TEXT_55 = "\");" + NL + "        }";
  protected final String TEXT_56 = NL + "        if(orders_";
  protected final String TEXT_57 = "[";
  protected final String TEXT_58 = "]==0){" + NL + "        \torders_";
  protected final String TEXT_59 = "] = ";
  protected final String TEXT_60 = ";" + NL + "        }" + NL + "        if(";
  protected final String TEXT_61 = " < orders_";
  protected final String TEXT_62 = ".length){" + NL + "        \t\torders_";
  protected final String TEXT_63 = "] = 0;" + NL + "        }";
  protected final String TEXT_64 = NL + "        ";
  protected final String TEXT_65 = ".elements().add(orders_";
  protected final String TEXT_66 = "]++,";
  protected final String TEXT_67 = ");";
  protected final String TEXT_68 = " = ";
  protected final String TEXT_69 = ".addElement(\"";
  protected final String TEXT_70 = NL + "\t\tsubTreeRootParent_";
  protected final String TEXT_71 = ";";
  protected final String TEXT_72 = NL + "\t\tif(";
  protected final String TEXT_73 = "!=null){";
  protected final String TEXT_74 = NL + "            nestXMLTool_";
  protected final String TEXT_75 = " .parseAndAdd(";
  protected final String TEXT_76 = NL + "        }";
  protected final String TEXT_77 = NL + "\t\telse{" + NL + "\t\t\tnestXMLTool_";
  protected final String TEXT_78 = ",\"\");" + NL + "\t\t\t";
  protected final String TEXT_79 = ".addAttribute(\"xsi:nil\",\"true\");" + NL + "\t\t}";
  protected final String TEXT_80 = "!=null){" + NL + "\t\t\tnestXMLTool_";
  protected final String TEXT_81 = " .setText(";
  protected final String TEXT_82 = ".addAttribute(\"type\", \"string\");" + NL + "\t\t\t";
  protected final String TEXT_83 = ".addAttribute(\"class\", \"string\");" + NL + "\t\t\t\t";
  protected final String TEXT_84 = ".addAttribute(\"type\", \"boolean\");";
  protected final String TEXT_85 = ".addAttribute(\"type\", \"number\");";
  protected final String TEXT_86 = NL + "\t\t}";
  protected final String TEXT_87 = NL + "\t\telse {" + NL + "\t\t\tnestXMLTool_";
  protected final String TEXT_88 = ",\"null\");" + NL + "\t\t\t";
  protected final String TEXT_89 = ".addAttribute(\"null\", \"true\");" + NL + "\t\t}";
  protected final String TEXT_90 = NL + "\t\telse{" + NL + "\t\t\t";
  protected final String TEXT_91 = ".setText(\"\");" + NL + "\t\t\t";
  protected final String TEXT_92 = NL + "\t\tnestXMLTool_";
  protected final String TEXT_93 = ".parseAndAdd(";
  protected final String TEXT_94 = ",\"";
  protected final String TEXT_95 = "!=null){" + NL + "\t\t\t";
  protected final String TEXT_96 = ".addAttribute(\"";
  protected final String TEXT_97 = "\",";
  protected final String TEXT_98 = ");" + NL + "\t\t}";
  protected final String TEXT_99 = "\", \"";
  protected final String TEXT_100 = ".addNamespace(\"";
  protected final String TEXT_101 = "\",TalendString.replaceSpecialCharForXML(";
  protected final String TEXT_102 = "));";
  protected final String TEXT_103 = NL + "        \t";
  protected final String TEXT_104 = ".setQName(org.dom4j.DocumentHelper.createQName(";
  protected final String TEXT_105 = ".getName()," + NL + "        \torg.dom4j.DocumentHelper.createNamespace(\"\",TalendString.replaceSpecialCharForXML(";
  protected final String TEXT_106 = "))));";
  protected final String TEXT_107 = "\",TalendString.replaceSpecialCharForXML(\"";
  protected final String TEXT_108 = "\"));";
  protected final String TEXT_109 = ".getName()," + NL + "        \torg.dom4j.DocumentHelper.createNamespace(\"\",TalendString.replaceSpecialCharForXML(\"";
  protected final String TEXT_110 = "\"))));";
  protected final String TEXT_111 = NL + "    \t// buffer the start tabs to group buffer" + NL + "    \tgroupBuffer_";
  protected final String TEXT_112 = "] = buf_";
  protected final String TEXT_113 = ".toString();" + NL + "        buf_";
  protected final String TEXT_114 = " = new StringBuffer();";
  protected final String TEXT_115 = NL + "\t\tstartTabs_";
  protected final String TEXT_116 = NL + "\t\tout_";
  protected final String TEXT_117 = ".write(buf_";
  protected final String TEXT_118 = ".toString());" + NL + "        buf_";
  protected final String TEXT_119 = NL + "\t\tif( false";
  protected final String TEXT_120 = " || valueMap_";
  protected final String TEXT_121 = "\") != null";
  protected final String TEXT_122 = " || true " + NL + "                    \t";
  protected final String TEXT_123 = NL + "\t\t){";
  protected final String TEXT_124 = NL + "\t\tbuf_";
  protected final String TEXT_125 = ".append(\"";
  protected final String TEXT_126 = "\");" + NL + "\t\tbuf_";
  protected final String TEXT_127 = "<";
  protected final String TEXT_128 = ".append(\" xmlns:xsi=\\\"http://www.w3.org/2001/XMLSchema-instance\\\"\");" + NL + "\t\tbuf_";
  protected final String TEXT_129 = ".append(\" xsi:noNamespaceSchemaLocation= \\\"\"+";
  protected final String TEXT_130 = "+\"\\\"\");";
  protected final String TEXT_131 = "==null){" + NL + "\t\t\tbuf_";
  protected final String TEXT_132 = ".append(\" xsi:nil=\\\"true\\\"\");" + NL + "\t\t}";
  protected final String TEXT_133 = ".append(\">\");";
  protected final String TEXT_134 = "</";
  protected final String TEXT_135 = ">\");";
  protected final String TEXT_136 = ".append(\"</";
  protected final String TEXT_137 = "!=null){" + NL + "\t\t\tbuf_";
  protected final String TEXT_138 = ".append(";
  protected final String TEXT_139 = ".append(TalendString.checkCDATAForXML(";
  protected final String TEXT_140 = "));" + NL + "\t\t}";
  protected final String TEXT_141 = ".append(\" ";
  protected final String TEXT_142 = "=\\\"\"+TalendString.replaceSpecialCharForXML(";
  protected final String TEXT_143 = ")+\"\\\"\");" + NL + "\t\t}";
  protected final String TEXT_144 = "=\\\"\"+TalendString.replaceSpecialCharForXML(\"";
  protected final String TEXT_145 = "\")+\"\\\"\");";
  protected final String TEXT_146 = NL + "        \tbuf_";
  protected final String TEXT_147 = ".append(\" xmlns=\\\"\"+TalendString.replaceSpecialCharForXML(";
  protected final String TEXT_148 = ")+\"\\\"\");";
  protected final String TEXT_149 = NL + "\t\t\tbuf_";
  protected final String TEXT_150 = ".append(\" xmlns:";
  protected final String TEXT_151 = ".append(\" xmlns=\\\"\"+TalendString.replaceSpecialCharForXML(\"";
  protected final String TEXT_152 = NL + "\tif(txf_";
  protected final String TEXT_153 = ".getLastException()!=null) {" + NL + "\t\tcurrentComponent = txf_";
  protected final String TEXT_154 = ".getCurrentComponent();" + NL + "\t\tthrow txf_";
  protected final String TEXT_155 = ".getLastException();" + NL + "\t}" + NL + "\t" + NL + "\tif(txf_";
  protected final String TEXT_156 = ".getLastError()!=null) {" + NL + "\t\tthrow txf_";
  protected final String TEXT_157 = ".getLastError();" + NL + "\t}";
  protected final String TEXT_158 = NL + "\tnb_line_";
  protected final String TEXT_159 = "++;";
  protected final String TEXT_160 = NL + "\tvalueMap_";
  protected final String TEXT_161 = ".clear();";
  protected final String TEXT_162 = NL + "\tarraysValueMap_";
  protected final String TEXT_163 = ".put(\"";
  protected final String TEXT_164 = NL + "\tflowValues_";
  protected final String TEXT_165 = " = new java.util.HashMap<String,String>();" + NL + "\tflowValues_";
  protected final String TEXT_166 = ".putAll(valueMap_";
  protected final String TEXT_167 = ");" + NL + "\tflows_";
  protected final String TEXT_168 = ".add(flowValues_";
  protected final String TEXT_169 = NL + "\t\tString strTemp_";
  protected final String TEXT_170 = " = \"\";";
  protected final String TEXT_171 = "\t\tstrTemp_";
  protected final String TEXT_172 = " = strTemp_";
  protected final String TEXT_173 = " + valueMap_";
  protected final String TEXT_174 = "\")" + NL + "\t\t\t\t\t\t\t+ valueMap_";
  protected final String TEXT_175 = "\").length();";
  protected final String TEXT_176 = NL + "\tif(strCompCache_";
  protected final String TEXT_177 = "==null){" + NL + "\t\tstrCompCache_";
  protected final String TEXT_178 = "=strTemp_";
  protected final String TEXT_179 = ";" + NL + "\t\t";
  protected final String TEXT_180 = NL + "            \trowStructOutput_";
  protected final String TEXT_181 = ";" + NL + "            \t";
  protected final String TEXT_182 = NL + "\t}else{";
  protected final String TEXT_183 = NL + "\t\t//the data read is different from the data read last time. " + NL + "\t\tif(!strCompCache_";
  protected final String TEXT_184 = ".equals(strTemp_";
  protected final String TEXT_185 = ")){\t";
  protected final String TEXT_186 = NL + "\t\t\tdoc_";
  protected final String TEXT_187 = ".getRootElement().addAttribute(\"xsi:noNamespaceSchemaLocation\", ";
  protected final String TEXT_188 = ");" + NL + "\t\t    doc_";
  protected final String TEXT_189 = ".getRootElement().addNamespace(\"xsi\", \"http://www.w3.org/2001/XMLSchema-instance\");";
  protected final String TEXT_190 = "  " + NL + "    \t\tnestXMLTool_";
  protected final String TEXT_191 = ".replaceDefaultNameSpace(doc_";
  protected final String TEXT_192 = ".getRootElement());";
  protected final String TEXT_193 = NL + "    \t\tnestXMLTool_";
  protected final String TEXT_194 = ".removeEmptyElement(doc_";
  protected final String TEXT_195 = "\t\t\t" + NL + "\t\t\tjava.io.StringWriter strWriter_";
  protected final String TEXT_196 = " = new java.io.StringWriter();\t" + NL + "\t\t\torg.dom4j.io.XMLWriter output_";
  protected final String TEXT_197 = " = new org.dom4j.io.XMLWriter(strWriter_";
  protected final String TEXT_198 = ", format_";
  protected final String TEXT_199 = ");" + NL + "\t\t\toutput_";
  protected final String TEXT_200 = ".write(doc_";
  protected final String TEXT_201 = ");" + NL + "\t\t    output_";
  protected final String TEXT_202 = ".close();" + NL + "\t\t\t";
  protected final String TEXT_203 = NL + "\t\t\tString removeHeader_";
  protected final String TEXT_204 = " = strWriter_";
  protected final String TEXT_205 = ".toString();" + NL + "\t\t\tif(removeHeader_";
  protected final String TEXT_206 = ".indexOf(\"<?xml\") >=0 ){" + NL + "\t\t\t\tremoveHeader_";
  protected final String TEXT_207 = " = removeHeader_";
  protected final String TEXT_208 = ".substring(removeHeader_";
  protected final String TEXT_209 = ".indexOf(\"?>\")+3);" + NL + "\t\t\t}" + NL + "\t\t\tlistGroupby_";
  protected final String TEXT_210 = ".add(removeHeader_";
  protected final String TEXT_211 = NL + "                map_";
  protected final String TEXT_212 = ".put(\"json_";
  protected final String TEXT_213 = "\",strWriter_";
  protected final String TEXT_214 = ".toString());" + NL + "                listGroupby_";
  protected final String TEXT_215 = ".add(map_";
  protected final String TEXT_216 = NL + "\t\t\t\t  \t\t  ";
  protected final String TEXT_217 = " row_";
  protected final String TEXT_218 = " = new ";
  protected final String TEXT_219 = "();" + NL + "\t\t\t\t\t\t  ";
  protected final String TEXT_220 = NL + "\t\t\t\t\t\t\t\trow_";
  protected final String TEXT_221 = " = rowStructOutput_";
  protected final String TEXT_222 = ";" + NL + "\t\t\t\t\t\t\t\t";
  protected final String TEXT_223 = NL + "\t\t\t\t\t     \t\trow_";
  protected final String TEXT_224 = ".toString();" + NL + "\t\t\t\t\t     \t\tlistGroupby_";
  protected final String TEXT_225 = ".add(row_";
  protected final String TEXT_226 = ");" + NL + "\t\t\t\t\t";
  protected final String TEXT_227 = NL + "\t\t\t\t\t\t\t\t\t\t    listGroupby_";
  protected final String TEXT_228 = ".add(strWriter_";
  protected final String TEXT_229 = ".toString());" + NL + "\t\t\t\t\t\t\t\t";
  protected final String TEXT_230 = NL + "\t\t    doc_";
  protected final String TEXT_231 = ".clearContent();" + NL + "\t\t\tneedRoot_";
  protected final String TEXT_232 = " = true;" + NL + "\t\t\tfor(int i_";
  protected final String TEXT_233 = "=0;i_";
  protected final String TEXT_234 = "<orders_";
  protected final String TEXT_235 = ".length;i_";
  protected final String TEXT_236 = "++){" + NL + "\t\t\t\torders_";
  protected final String TEXT_237 = "[i_";
  protected final String TEXT_238 = "] = 0;" + NL + "\t\t\t}" + NL + "\t\t\t" + NL + "\t\t\tif(groupbyList_";
  protected final String TEXT_239 = " != null && groupbyList_";
  protected final String TEXT_240 = ".size() >= 0){" + NL + "\t\t\t\tgroupbyList_";
  protected final String TEXT_241 = ".clear();" + NL + "\t\t\t}" + NL + "\t\t\tstrCompCache_";
  protected final String TEXT_242 = NL + "\t}" + NL;
  protected final String TEXT_243 = "  = new java.util.HashMap();";
  protected final String TEXT_244 = NL + "                    map_";
  protected final String TEXT_245 = NL + "\torg.dom4j.Element subTreeRootParent_";
  protected final String TEXT_246 = " = null;" + NL + "\t" + NL + "\t// build root xml tree " + NL + "\tif (needRoot_";
  protected final String TEXT_247 = ") {" + NL + "\t\tneedRoot_";
  protected final String TEXT_248 = "=false;";
  protected final String TEXT_249 = NL + "\t\troot4Group_";
  protected final String TEXT_250 = " = subTreeRootParent_";
  protected final String TEXT_251 = ";" + NL + "\t}else{" + NL + "\t\tsubTreeRootParent_";
  protected final String TEXT_252 = "=root4Group_";
  protected final String TEXT_253 = ";" + NL + "\t}" + NL + "\t// build group xml tree ";
  protected final String TEXT_254 = NL + "\tboolean isNewElememt = false;";
  protected final String TEXT_255 = NL + "\tif(isNewElememt || groupbyList_";
  protected final String TEXT_256 = ".size()<=";
  protected final String TEXT_257 = " || groupbyList_";
  protected final String TEXT_258 = ".get(";
  protected final String TEXT_259 = ")==null";
  protected final String TEXT_260 = NL + "\t|| ( groupbyList_";
  protected final String TEXT_261 = ").get(";
  protected final String TEXT_262 = ")!=null " + NL + "\t\t? !groupbyList_";
  protected final String TEXT_263 = ").equals(";
  protected final String TEXT_264 = ") " + NL + "\t\t: ";
  protected final String TEXT_265 = "!=null )";
  protected final String TEXT_266 = NL + "\t){";
  protected final String TEXT_267 = NL + "\t\tif(groupbyList_";
  protected final String TEXT_268 = "){" + NL + "        \tgroupbyList_";
  protected final String TEXT_269 = ".add(new java.util.ArrayList<String>());" + NL + "        }else{" + NL + "        \tgroupbyList_";
  protected final String TEXT_270 = ").clear();" + NL + "        }";
  protected final String TEXT_271 = NL + "\t\tgroupbyList_";
  protected final String TEXT_272 = ").add(";
  protected final String TEXT_273 = NL + "        isNewElememt=true;" + NL + "        if(groupElementList_";
  protected final String TEXT_274 = "){" + NL + "\t\t\tgroupElementList_";
  protected final String TEXT_275 = ".add(group";
  protected final String TEXT_276 = "__";
  protected final String TEXT_277 = ");" + NL + "        }else{" + NL + "        \tgroupElementList_";
  protected final String TEXT_278 = ".set(";
  protected final String TEXT_279 = ",group";
  protected final String TEXT_280 = ");" + NL + "        }" + NL + "        " + NL + "\t}else{" + NL + "\t\tsubTreeRootParent_";
  protected final String TEXT_281 = "=groupElementList_";
  protected final String TEXT_282 = ");" + NL + "\t}";
  protected final String TEXT_283 = NL + "\t// build loop xml tree";
  protected final String TEXT_284 = ")){";
  protected final String TEXT_285 = NL + NL + "\t\t // write the endtag to the StringWriter:strWriter_tWriteXMLField_1_Out" + NL + "\t\t // close the bufferWriter" + NL + "\t\t // add the data in strWriter_tWriteXMLField_1_Out to listGroupby\t\t\t\t\t\t\t " + NL + "" + NL + "\t\tif (preUnNullMaxIndex_";
  protected final String TEXT_286 = " >= 0) {" + NL + "\t        // output all buffer" + NL + "\t        for (int j_";
  protected final String TEXT_287 = " = 0; j_";
  protected final String TEXT_288 = " <= preUnNullMaxIndex_";
  protected final String TEXT_289 = "; j_";
  protected final String TEXT_290 = "++) {" + NL + "\t            if (startTabs_";
  protected final String TEXT_291 = "[j_";
  protected final String TEXT_292 = "] != null)" + NL + "\t                out_";
  protected final String TEXT_293 = ".write(startTabs_";
  protected final String TEXT_294 = "]);" + NL + "\t        }" + NL + "\t" + NL + "\t        if (preUnNullMaxIndex_";
  protected final String TEXT_295 = " < preNewTabIndex_";
  protected final String TEXT_296 = " ) {" + NL + "\t\t\t\tfor (int i_";
  protected final String TEXT_297 = " = preNewTabIndex_";
  protected final String TEXT_298 = " - 1; i_";
  protected final String TEXT_299 = " >= 0; i_";
  protected final String TEXT_300 = "--) {" + NL + "                \tif(endTabs_";
  protected final String TEXT_301 = "]!=null){" + NL + "                \t\tout_";
  protected final String TEXT_302 = ".write(endTabs_";
  protected final String TEXT_303 = "]);" + NL + "                \t}                \t" + NL + "\t                out_";
  protected final String TEXT_304 = ".write(\"";
  protected final String TEXT_305 = "\");" + NL + "\t                out_";
  protected final String TEXT_306 = ".write(endTabStrs_";
  protected final String TEXT_307 = ".get(i_";
  protected final String TEXT_308 = "));" + NL + "\t            }" + NL + "\t        } else {" + NL + "\t            for (int i_";
  protected final String TEXT_309 = " = preUnNullMaxIndex_";
  protected final String TEXT_310 = "; i_";
  protected final String TEXT_311 = "]);" + NL + "                \t}" + NL + "                \tout_";
  protected final String TEXT_312 = "));" + NL + "\t            }" + NL + "\t        }" + NL + "\t    }";
  protected final String TEXT_313 = NL + "\t\tfor (int i_";
  protected final String TEXT_314 = " = endTabStrs_";
  protected final String TEXT_315 = ".size() - 1; i_";
  protected final String TEXT_316 = "--) {" + NL + "        \tif(endTabs_";
  protected final String TEXT_317 = "]!=null){" + NL + "        \t\tout_";
  protected final String TEXT_318 = "]);" + NL + "        \t}" + NL + "\t        out_";
  protected final String TEXT_319 = "\");" + NL + "\t        out_";
  protected final String TEXT_320 = "));" + NL + "\t    }";
  protected final String TEXT_321 = " = 0; i_";
  protected final String TEXT_322 = " < endTabs_";
  protected final String TEXT_323 = ".length; i_";
  protected final String TEXT_324 = "++) {" + NL + "\t\t\tstartTabs_";
  protected final String TEXT_325 = "] = null;" + NL + "\t\t\tendTabs_";
  protected final String TEXT_326 = "] = null;" + NL + "\t\t}" + NL + "//\t\tendTabStrs_";
  protected final String TEXT_327 = ".clear();" + NL + "\t\tout_";
  protected final String TEXT_328 = "\");" + NL + "\t\tout_";
  protected final String TEXT_329 = ".close();" + NL + "\t\tlistGroupby_";
  protected final String TEXT_330 = ".toString());" + NL + "" + NL + "\t\t//create a new StringWriter and BufferWriter" + NL + "\t\t//write the head title to the StringWriter\t\t" + NL + "\t\tstrWriter_";
  protected final String TEXT_331 = " = new java.io.StringWriter();" + NL + "\t\tout_";
  protected final String TEXT_332 = " = new java.io.BufferedWriter(strWriter_";
  protected final String TEXT_333 = ".write(\"<?xml version=\\\"1.0\\\" encoding=\\\"\"+";
  protected final String TEXT_334 = "+\"\\\"?>\");" + NL + "\t\tout_";
  protected final String TEXT_335 = NL + NL + "\t\tneedRoot_";
  protected final String TEXT_336 = " = true;" + NL + "\t\tstrCompCache_";
  protected final String TEXT_337 = ";" + NL + "\t\tpreNewTabIndex_";
  protected final String TEXT_338 = " = -1;";
  protected final String TEXT_339 = "\t" + NL + "\t}\t" + NL + "\t" + NL + "\tStringBuffer buf_";
  protected final String TEXT_340 = " = new StringBuffer();" + NL + "\t//init value is 0 not -1, because it will output the root tab when all the row value is null." + NL + "\tint unNullMaxIndex_";
  protected final String TEXT_341 = " = 0;" + NL + "" + NL + "\t// build root xml tree " + NL + "\tif (needRoot_";
  protected final String TEXT_342 = NL + "\t\t){" + NL + "\t\t\tunNullMaxIndex_";
  protected final String TEXT_343 = ";" + NL + "\t\t}";
  protected final String TEXT_344 = NL + "\t\tendTabs_";
  protected final String TEXT_345 = ".toString();" + NL + "\t\tbuf_";
  protected final String TEXT_346 = NL + "\t}" + NL + "\t" + NL + "\t// build group xml tree ";
  protected final String TEXT_347 = NL + "\tboolean isNewElememt = false;" + NL + "\t//The index of group element which is the first new group in groups." + NL + "\tint newTabIndex_";
  protected final String TEXT_348 = " = -1;" + NL + "\t//Buffer all group tab XML, then set to startTabBuffer." + NL + "    String[] groupBuffer_";
  protected final String TEXT_349 = " = new String[";
  protected final String TEXT_350 = "];" + NL + "    String[] groupEndBuffer_";
  protected final String TEXT_351 = "];";
  protected final String TEXT_352 = NL + NL + "\t// need a new group element ";
  protected final String TEXT_353 = " or not" + NL + "\tif(isNewElememt || groupbyList_";
  protected final String TEXT_354 = NL + "\t){" + NL + "\t\t// Is the first new element in groups." + NL + "\t\tif(!isNewElememt && groupbyList_";
  protected final String TEXT_355 = ".size()>";
  protected final String TEXT_356 = "){" + NL + "\t\t\tnewTabIndex_";
  protected final String TEXT_357 = ";" + NL + "\t\t}" + NL + "" + NL + "\t\t// count the groupby element" + NL + "\t\tif(groupbyList_";
  protected final String TEXT_358 = NL + "        isNewElememt=true;" + NL + "\t}" + NL + "\t" + NL + "\t// subtree XML string generate";
  protected final String TEXT_359 = NL + "\tif( false";
  protected final String TEXT_360 = NL + "\t){" + NL + "\t\tunNullMaxIndex_";
  protected final String TEXT_361 = ";" + NL + "\t}";
  protected final String TEXT_362 = NL + "\t// buffer the end tabs to group buffer" + NL + "\tgroupEndBuffer_";
  protected final String TEXT_363 = ".toString();" + NL + "    buf_";
  protected final String TEXT_364 = NL + "\t//output the previous groups as there's a new group" + NL + "    if (newTabIndex_";
  protected final String TEXT_365 = " >= 0 && preNewTabIndex_";
  protected final String TEXT_366 = "!=-1) {" + NL + "        //out_";
  protected final String TEXT_367 = ".newLine();//Track code";
  protected final String TEXT_368 = NL + "\t\t// output unNull tabs in start tabs buffer" + NL + "        if (preUnNullMaxIndex_";
  protected final String TEXT_369 = " >= 0) {" + NL + "            for (int i_";
  protected final String TEXT_370 = " < startTabs_";
  protected final String TEXT_371 = "++) {" + NL + "                if (i_";
  protected final String TEXT_372 = ") {" + NL + "                    if (startTabs_";
  protected final String TEXT_373 = "] != null) {" + NL + "                        out_";
  protected final String TEXT_374 = "]);" + NL + "                    }" + NL + "                    startTabs_";
  protected final String TEXT_375 = "] = null;" + NL + "                }" + NL + "            }" + NL + "        }";
  protected final String TEXT_376 = NL + "\t\t//output all start tabs buffer" + NL + "\t\tfor (int i_";
  protected final String TEXT_377 = "++) {" + NL + "            if (startTabs_";
  protected final String TEXT_378 = "] != null) {" + NL + "                out_";
  protected final String TEXT_379 = "]);" + NL + "            }" + NL + "            startTabs_";
  protected final String TEXT_380 = "] = null;" + NL + "        }";
  protected final String TEXT_381 = NL + "        // output endtabs" + NL + "        if (preUnNullMaxIndex_";
  protected final String TEXT_382 = " >= preNewTabIndex_";
  protected final String TEXT_383 = NL + "            && preUnNullMaxIndex_";
  protected final String TEXT_384 = " >= ";
  protected final String TEXT_385 = " + newTabIndex_";
  protected final String TEXT_386 = ") {" + NL + "            for (int i_";
  protected final String TEXT_387 = "--) {" + NL + "            \tif(endTabs_";
  protected final String TEXT_388 = "]!=null){" + NL + "            \t\tout_";
  protected final String TEXT_389 = "]);" + NL + "            \t}" + NL + "            \tendTabs_";
  protected final String TEXT_390 = "] = null;" + NL + "                out_";
  protected final String TEXT_391 = "\");" + NL + "                out_";
  protected final String TEXT_392 = NL + "                        .get(i_";
  protected final String TEXT_393 = "));" + NL + "            }" + NL + "        } else {";
  protected final String TEXT_394 = NL + "            for (int i_";
  protected final String TEXT_395 = "));" + NL + "            }";
  protected final String TEXT_396 = NL + "        preNewTabIndex_";
  protected final String TEXT_397 = " = newTabIndex_";
  protected final String TEXT_398 = " + ";
  protected final String TEXT_399 = ";" + NL + "    }" + NL + "" + NL + "    // set new element groupbuffer to startbuffer" + NL + "    for (int i_";
  protected final String TEXT_400 = " < groupBuffer_";
  protected final String TEXT_401 = "++) {" + NL + "        // when newTabIndex is null, must use the perNewTabIndex" + NL + "        if (i_";
  protected final String TEXT_402 = " - ";
  protected final String TEXT_403 = ") {" + NL + "            startTabs_";
  protected final String TEXT_404 = "] = groupBuffer_";
  protected final String TEXT_405 = "];" + NL + "            endTabs_";
  protected final String TEXT_406 = "] = groupEndBuffer_";
  protected final String TEXT_407 = "];" + NL + "        }" + NL + "    }";
  protected final String TEXT_408 = NL + "\t//reset the preUnNullMaxIndex" + NL + "\tif(unNullMaxIndex_";
  protected final String TEXT_409 = ">=0){" + NL + "    \tpreUnNullMaxIndex_";
  protected final String TEXT_410 = "=unNullMaxIndex_";
  protected final String TEXT_411 = ";" + NL + "\t}else{" + NL + "\t\tif(preUnNullMaxIndex_";
  protected final String TEXT_412 = ">";
  protected final String TEXT_413 = "){" + NL + "\t\t\tpreUnNullMaxIndex_";
  protected final String TEXT_414 = "=";
  protected final String TEXT_415 = ";" + NL + "\t\t}" + NL + "\t}";
  protected final String TEXT_416 = " || true " + NL + "    \t";
  protected final String TEXT_417 = NL + "\t\t// output all buffer" + NL + "\t\tfor (int i_";
  protected final String TEXT_418 = "] != null && startTabs_";
  protected final String TEXT_419 = "].length() > 0) {" + NL + "                out_";
  protected final String TEXT_420 = "]);" + NL + "                startTabs_";
  protected final String TEXT_421 = "] = null;" + NL + "            }" + NL + "        }" + NL + "\t\tout_";
  protected final String TEXT_422 = ".toString());" + NL + "\t\tpreNewTabIndex_";
  protected final String TEXT_423 = NL + "            preUnNullMaxIndex_";
  protected final String TEXT_424 = NL;

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
	
    
//==========common part 1 begin===================================================================
CodeGeneratorArgument codeGenArgument = (CodeGeneratorArgument) argument;
INode node = (INode)codeGenArgument.getArgument();
String cid = node.getUniqueName();
String jsonField = ElementParameterParser.getValue(node, "__JSONFIELD__");
String destination4JSON = ElementParameterParser.getValue(node, "__DESTINATION__");
boolean istWriteJSONField = destination4JSON == null ? false : destination4JSON.contains("tWriteJSONField_");
boolean isQuoteAllValues = ("true").equals(ElementParameterParser.getValue(node, "__QUOTE_ALL_VALUES__"));
boolean isCompactFormat = ("true").equals(ElementParameterParser.getValue(node, "__COMPACT_FORMAT__"));
final boolean allowEmptyStrings = ("true").equals(ElementParameterParser.getValue(node, "__ALLOW_EMPTY_STRINGS__"));
final String whiteSpace;
final String rowSeparator;
if(!isCompactFormat) { // pretty format
	whiteSpace = "  ";
	rowSeparator = "\\n";
} else { // compact format
	whiteSpace = "";
	rowSeparator = "";
}
//===========common part 1 end=============================================================

    
//XMLTool
class XMLTool{
	public boolean advancedSeparator = false;
	public String thousandsSeparator = null;
 	public String decimalSeparator =null;
	public String connName = null;
	public String cid = null;
	public boolean istWriteJSONField = false;
	
	public void getValue(XMLNode node){
		getValue(node, false);
	}
	
	public void getValue(XMLNode node, boolean parseAsArray) {
		if(!parseAsArray){

    stringBuffer.append(TEXT_18);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_19);
    stringBuffer.append(node.relatedColumn.getLabel());
    stringBuffer.append(TEXT_20);
    
		}else{

    stringBuffer.append(TEXT_21);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_19);
    stringBuffer.append(node.relatedColumn.getLabel());
    stringBuffer.append(TEXT_20);
    
		}
	}

	public void getValue(IMetadataColumn column){
		getValue(column, false);
	}
	
	public void getValue(IMetadataColumn column, boolean parseAsArray){
		JavaType javaType = JavaTypesManager.getJavaTypeFromId(column.getTalendType());
		String defaultValue=column.getDefault();
		boolean isNotSetDefault = false;
		if(defaultValue!=null){
			isNotSetDefault = defaultValue.length()==0;
		}else{
			isNotSetDefault=true;
		}

    stringBuffer.append(TEXT_22);
    
		if(column.isNullable()){

    stringBuffer.append(TEXT_23);
    stringBuffer.append(connName);
    stringBuffer.append(TEXT_24);
    stringBuffer.append(column.getLabel());
    stringBuffer.append(TEXT_25);
    
		}
		
        if(advancedSeparator && JavaTypesManager.isNumberType(javaType, column.isNullable())) { 
        	if(javaType == JavaTypesManager.BIGDECIMAL) {

    stringBuffer.append(TEXT_26);
    stringBuffer.append(connName);
    stringBuffer.append(TEXT_24);
    stringBuffer.append(column.getLabel());
    stringBuffer.append(TEXT_27);
    stringBuffer.append( thousandsSeparator);
    stringBuffer.append(TEXT_28);
    stringBuffer.append(decimalSeparator );
    stringBuffer.append(TEXT_29);
    
    		} else {

    stringBuffer.append(TEXT_30);
    stringBuffer.append(connName);
    stringBuffer.append(TEXT_24);
    stringBuffer.append(column.getLabel());
    stringBuffer.append(TEXT_31);
    stringBuffer.append( thousandsSeparator );
    stringBuffer.append(TEXT_28);
    stringBuffer.append(decimalSeparator );
    stringBuffer.append(TEXT_32);
    
	   		}
        } else if(JavaTypesManager.isJavaPrimitiveType( column.getTalendType(), column.isNullable())){

    stringBuffer.append(TEXT_33);
    stringBuffer.append(connName);
    stringBuffer.append(TEXT_24);
    stringBuffer.append(column.getLabel());
    stringBuffer.append(TEXT_34);
    
        }else if(javaType == JavaTypesManager.DATE){
            if( column.getPattern() != null && column.getPattern().trim().length() != 0 ){

    stringBuffer.append(TEXT_35);
    stringBuffer.append(connName);
    stringBuffer.append(TEXT_24);
    stringBuffer.append(column.getLabel());
    stringBuffer.append(TEXT_28);
    stringBuffer.append(column.getPattern());
    stringBuffer.append(TEXT_34);
    
            }else{

    stringBuffer.append(TEXT_36);
    stringBuffer.append(connName);
    stringBuffer.append(TEXT_24);
    stringBuffer.append(column.getLabel());
    
           }
        }else if (javaType == JavaTypesManager.BIGDECIMAL) {

    stringBuffer.append(TEXT_36);
    stringBuffer.append(connName);
    stringBuffer.append(TEXT_24);
    stringBuffer.append(column.getLabel());
    stringBuffer.append(TEXT_37);
    
        }else if (javaType == JavaTypesManager.BYTE_ARRAY) {

    stringBuffer.append(TEXT_38);
    stringBuffer.append(connName);
    stringBuffer.append(TEXT_24);
    stringBuffer.append(column.getLabel());
    stringBuffer.append(TEXT_34);
    
		}else if (istWriteJSONField && javaType == JavaTypesManager.OBJECT && parseAsArray) {

    stringBuffer.append(TEXT_39);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_40);
    stringBuffer.append(connName);
    stringBuffer.append(TEXT_24);
    stringBuffer.append(column.getLabel());
    stringBuffer.append(TEXT_34);
    
		}else{

    stringBuffer.append(TEXT_41);
    stringBuffer.append(connName);
    stringBuffer.append(TEXT_24);
    stringBuffer.append(column.getLabel());
    stringBuffer.append(TEXT_42);
    
		}
		if(column.isNullable()){
			
    stringBuffer.append(TEXT_43);
     
			if(!isNotSetDefault){
				
    stringBuffer.append(column.getDefault());
    
			}else{
				
    stringBuffer.append(TEXT_44);
    
			}
		}

    stringBuffer.append(TEXT_45);
    
	}
}

// ------------------- *** Dom4j generation mode start *** ------------------- //
class GenerateToolByDom4j{
	String cid = null;
	boolean allowEmpty = false;
	boolean outputAsXSD = false;
    boolean istWriteJSONField = false;
    boolean isQuoteAllValues = false;
	XMLTool tool = null;
	public void generateCode(XMLNode node, String currEleName, String parentName){
		if(("ELEMENT").equals(node.type)){
			createElement(currEleName,node,parentName);
			setText(currEleName,node);
			for(XMLNode ns:node.namespaces){
				addNameSpace(currEleName,ns);
			}
			for(XMLNode attri:node.attributes){
				addAttribute(currEleName,attri);
			}
			if(node.name.indexOf(":")>0){

    stringBuffer.append(TEXT_36);
    stringBuffer.append(currEleName);
    stringBuffer.append(TEXT_46);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_47);
    stringBuffer.append(node.name);
    stringBuffer.append(TEXT_48);
    
			}
			int index = 0;
			for(XMLNode child:node.elements){
				if(0==(child.special & 1)){
					generateCode(child,currEleName+"_"+index++,currEleName);
				}
			}
		}
	}
	private void createElement(String currEleName, XMLNode node, String parentName){
		int index = node.name.indexOf(":");
		if(5==(node.special & 5)){
			int currPos = node.getCurrGroupPos();
			if(index>0 && node.parent!=null){

    stringBuffer.append(TEXT_49);
    stringBuffer.append(currEleName);
    stringBuffer.append(TEXT_46);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_50);
    stringBuffer.append(parentName);
    stringBuffer.append(TEXT_46);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_51);
    stringBuffer.append(node.name.substring(0,index));
    stringBuffer.append(TEXT_52);
    stringBuffer.append(TEXT_41);
    stringBuffer.append(currEleName);
    stringBuffer.append(TEXT_46);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_53);
    stringBuffer.append(node.name.substring(index+1));
    stringBuffer.append(TEXT_54);
    stringBuffer.append(currEleName);
    stringBuffer.append(TEXT_46);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_53);
    stringBuffer.append(node.name);
    stringBuffer.append(TEXT_55);
    
			}else{

    stringBuffer.append(TEXT_49);
    stringBuffer.append(currEleName);
    stringBuffer.append(TEXT_46);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_53);
    stringBuffer.append(node.name);
    stringBuffer.append(TEXT_48);
    
			}

    stringBuffer.append(TEXT_56);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_57);
    stringBuffer.append(currPos );
    stringBuffer.append(TEXT_58);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_57);
    stringBuffer.append(currPos );
    stringBuffer.append(TEXT_59);
    stringBuffer.append(node.getNodeInsertIndex() );
    stringBuffer.append(TEXT_60);
    stringBuffer.append(currPos +1 );
    stringBuffer.append(TEXT_61);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_62);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_57);
    stringBuffer.append(currPos +1 );
    stringBuffer.append(TEXT_63);
    stringBuffer.append(TEXT_64);
    stringBuffer.append(parentName);
    stringBuffer.append(TEXT_46);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_65);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_57);
    stringBuffer.append(currPos );
    stringBuffer.append(TEXT_66);
    stringBuffer.append(currEleName);
    stringBuffer.append(TEXT_46);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_67);
    
		}else{
			if(index>0 && node.parent!=null){

    stringBuffer.append(TEXT_49);
    stringBuffer.append(currEleName);
    stringBuffer.append(TEXT_46);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_50);
    stringBuffer.append(parentName);
    stringBuffer.append(TEXT_46);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_51);
    stringBuffer.append(node.name.substring(0,index));
    stringBuffer.append(TEXT_52);
    stringBuffer.append(TEXT_41);
    stringBuffer.append(currEleName);
    stringBuffer.append(TEXT_46);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_68);
    stringBuffer.append(parentName);
    stringBuffer.append(TEXT_46);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_69);
    stringBuffer.append(node.name.substring(index+1));
    stringBuffer.append(TEXT_54);
    stringBuffer.append(currEleName);
    stringBuffer.append(TEXT_46);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_68);
    stringBuffer.append(parentName);
    stringBuffer.append(TEXT_46);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_69);
    stringBuffer.append(node.name);
    stringBuffer.append(TEXT_55);
    
			}else{

    stringBuffer.append(TEXT_49);
    stringBuffer.append(currEleName);
    stringBuffer.append(TEXT_46);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_68);
    stringBuffer.append(parentName);
    stringBuffer.append(TEXT_46);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_69);
    stringBuffer.append(node.name);
    stringBuffer.append(TEXT_48);
    
			}
		}
		if(0!=(node.special & 2)){

    stringBuffer.append(TEXT_70);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_68);
    stringBuffer.append(currEleName);
    stringBuffer.append(TEXT_46);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_71);
    
		}
	}
	private void setText(String currEleName, XMLNode node){
		if(node.relatedColumn!=null){
			JavaType javaType = JavaTypesManager.getJavaTypeFromId(node.relatedColumn.getTalendType());
			if(javaType == JavaTypesManager.OBJECT){

    stringBuffer.append(TEXT_72);
    tool.getValue(node);
    stringBuffer.append(TEXT_73);
    
            if(istWriteJSONField){
                boolean parseAsArray = false;
                for (XMLNode attribute : node.attributes) {
                    if ("class".equals(attribute.name) && "array".equals(attribute.defaultValue)) {
                        parseAsArray = true;
                        break;
                    }
                }

    stringBuffer.append(TEXT_74);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_75);
    stringBuffer.append(currEleName);
    stringBuffer.append(TEXT_46);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_28);
    tool.getValue(node, parseAsArray);
    stringBuffer.append(TEXT_67);
    
            }else{

    stringBuffer.append(TEXT_74);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_75);
    stringBuffer.append(currEleName);
    stringBuffer.append(TEXT_46);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_28);
    tool.getValue(node);
    stringBuffer.append(TEXT_67);
    
            }

    stringBuffer.append(TEXT_76);
    
				if(outputAsXSD){

    stringBuffer.append(TEXT_77);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_75);
    stringBuffer.append(currEleName);
    stringBuffer.append(TEXT_46);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_78);
    stringBuffer.append(currEleName);
    stringBuffer.append(TEXT_46);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_79);
    
				}
			}else{

    stringBuffer.append(TEXT_72);
    tool.getValue(node);
    stringBuffer.append(TEXT_80);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_81);
    stringBuffer.append(currEleName);
    stringBuffer.append(TEXT_46);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_28);
    tool.getValue(node);
    stringBuffer.append(TEXT_67);
    
			if(istWriteJSONField && allowEmptyStrings) {
				if (javaType == JavaTypesManager.STRING) {
				
    stringBuffer.append(TEXT_36);
    stringBuffer.append(currEleName);
    stringBuffer.append(TEXT_46);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_82);
    stringBuffer.append(currEleName);
    stringBuffer.append(TEXT_46);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_83);
    
				}
			}
                boolean hasCustomAttrs = false;
                for (XMLNode attribute : node.attributes) {
                    if (!"type".equals(attribute.name) && !"class".equals(attribute.name)) {
                        hasCustomAttrs = true;
                        break;
                    }
                }

                if (istWriteJSONField && !isQuoteAllValues && !hasCustomAttrs) {
                    if (javaType == JavaTypesManager.BOOLEAN) {

    stringBuffer.append(TEXT_41);
    stringBuffer.append(currEleName);
    stringBuffer.append(TEXT_46);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_84);
    
                    } else if (JavaTypesManager.isNumberType(javaType)) {

    stringBuffer.append(TEXT_41);
    stringBuffer.append(currEleName);
    stringBuffer.append(TEXT_46);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_85);
    
                    }
                }

    stringBuffer.append(TEXT_86);
     			if(istWriteJSONField && allowEmptyStrings) { 
    stringBuffer.append(TEXT_87);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_81);
    stringBuffer.append(currEleName);
    stringBuffer.append(TEXT_46);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_88);
    stringBuffer.append(currEleName);
    stringBuffer.append(TEXT_46);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_89);
    
			}
				if(outputAsXSD){

    stringBuffer.append(TEXT_90);
    stringBuffer.append(currEleName);
    stringBuffer.append(TEXT_46);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_91);
    stringBuffer.append(currEleName);
    stringBuffer.append(TEXT_46);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_79);
    
				}
			}
		}else if(node.defaultValue != null && !("").equals(node.defaultValue) ){

    stringBuffer.append(TEXT_92);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_93);
    stringBuffer.append(currEleName );
    stringBuffer.append(TEXT_46);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_94);
    stringBuffer.append(node.defaultValue );
    stringBuffer.append(TEXT_48);
    
		}
	}
	private void addAttribute(String currEleName, XMLNode node){
		if(node.relatedColumn!=null){

    stringBuffer.append(TEXT_72);
    tool.getValue(node);
    stringBuffer.append(TEXT_95);
    stringBuffer.append(currEleName);
    stringBuffer.append(TEXT_46);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_96);
    stringBuffer.append(node.path);
    stringBuffer.append(TEXT_97);
    tool.getValue(node);
    stringBuffer.append(TEXT_98);
    
		}else if(node.defaultValue != null && !("").equals(node.defaultValue) ){

    stringBuffer.append(TEXT_23);
    stringBuffer.append(currEleName);
    stringBuffer.append(TEXT_46);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_96);
    stringBuffer.append(node.path);
    stringBuffer.append(TEXT_99);
    stringBuffer.append(node.defaultValue );
    stringBuffer.append(TEXT_48);
    
		}
	}
	private void addNameSpace(String currEleName, XMLNode node){
		if(node.relatedColumn!=null){

    stringBuffer.append(TEXT_72);
    tool.getValue(node);
    stringBuffer.append(TEXT_95);
    stringBuffer.append(currEleName);
    stringBuffer.append(TEXT_46);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_100);
    stringBuffer.append(node.path);
    stringBuffer.append(TEXT_101);
    tool.getValue(node);
    stringBuffer.append(TEXT_102);
    
			if(node.path ==null || node.path.length()==0){

    stringBuffer.append(TEXT_103);
    stringBuffer.append(currEleName);
    stringBuffer.append(TEXT_46);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_104);
    stringBuffer.append(currEleName);
    stringBuffer.append(TEXT_46);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_105);
    tool.getValue(node);
    stringBuffer.append(TEXT_106);
    
			}

    stringBuffer.append(TEXT_86);
    
		}else if(node.defaultValue != null && !("").equals(node.defaultValue) ){

    stringBuffer.append(TEXT_36);
    stringBuffer.append(currEleName );
    stringBuffer.append(TEXT_46);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_100);
    stringBuffer.append(node.path );
    stringBuffer.append(TEXT_107);
    stringBuffer.append(node.defaultValue );
    stringBuffer.append(TEXT_108);
    
			if(node.path ==null || node.path.length()==0){

    stringBuffer.append(TEXT_103);
    stringBuffer.append(currEleName );
    stringBuffer.append(TEXT_46);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_104);
    stringBuffer.append(currEleName );
    stringBuffer.append(TEXT_46);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_109);
    stringBuffer.append(node.defaultValue );
    stringBuffer.append(TEXT_110);
    
			}
		}
	}
}
// ------------------- *** Dom4j generation mode end *** ------------------- //

// ------------------- *** Null generation mode start *** ------------------- //
class GenerateToolByNull{
	String cid = null;
	boolean allowEmpty = false;
	boolean outputAsXSD = false;
	String fileNameXSD = "";
	XMLTool tool = null;
	
	public void generateCode(XMLNode node, String emptySpace){	
		if(("ELEMENT").equals(node.type)){
			startElement(node,emptySpace);
			setText(node);
			XMLNode mainChild = null;
			for(XMLNode child:node.elements){
				if(child.isMainNode()){ //loop dosen't have a main child node
					mainChild = child;
					break;
				}
			}
			for(XMLNode child:node.elements){
				if(mainChild!=null && mainChild.order<=child.order){ //loop dosen't have a main child node
					if(1==(node.special & 1)){ // group

    stringBuffer.append(TEXT_111);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_57);
    stringBuffer.append(node.getCurrGroupPos());
    stringBuffer.append(TEXT_112);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_113);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_114);
    
					}else{// root
    					int num = node.path.split("/").length-2;
    					if(!outputAsXSD && !allowEmpty){

    stringBuffer.append(TEXT_115);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_57);
    stringBuffer.append(num);
    stringBuffer.append(TEXT_112);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_113);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_114);
    
						}else{

    stringBuffer.append(TEXT_116);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_117);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_118);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_114);
    
						}
					}
					mainChild = null;
				}
				if(!child.isMainNode()){ //make the main node output last
					if(!outputAsXSD && !allowEmpty && (child.relatedColumn != null || child.childrenColumnList.size()>0 || child.hasDefaultValue == true)){

    stringBuffer.append(TEXT_119);
    
                    	for(IMetadataColumn column : child.childrenColumnList){
                    		
    stringBuffer.append(TEXT_120);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_19);
    stringBuffer.append(column.getLabel());
    stringBuffer.append(TEXT_121);
    
                    	}
                    	if(child.hasDefaultValue == true){
    stringBuffer.append(TEXT_122);
    }
    stringBuffer.append(TEXT_123);
    
						generateCode(child,emptySpace+whiteSpace);

    stringBuffer.append(TEXT_86);
    
            		}else{
            			generateCode(child,emptySpace+whiteSpace);
            		}
				}
			}

			if(!node.isMainNode()){ // is not main node
				endElement(node,emptySpace);
			}
		}
	}
	private void startElement(XMLNode node, String emptySpace){

    stringBuffer.append(TEXT_124);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_125);
    stringBuffer.append(rowSeparator);
    stringBuffer.append(TEXT_126);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_125);
    stringBuffer.append(emptySpace);
    stringBuffer.append(TEXT_127);
    stringBuffer.append(node.name);
    stringBuffer.append(TEXT_48);
    
		if(outputAsXSD && node.parent==null){

    stringBuffer.append(TEXT_124);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_128);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_129);
    stringBuffer.append(fileNameXSD);
    stringBuffer.append(TEXT_130);
    
		}
		for(XMLNode ns:node.namespaces){
			addNameSpace(ns);
		}
		for(XMLNode attri:node.attributes){
			addAttribute(attri);
		}
		if(outputAsXSD && node.relatedColumn != null){

    stringBuffer.append(TEXT_72);
    tool.getValue(node);
    stringBuffer.append(TEXT_131);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_132);
    
		}

    stringBuffer.append(TEXT_124);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_133);
    
	}
	
	public void endElement(XMLNode node, String emptySpace){
		if(node.elements.size()>0){

    stringBuffer.append(TEXT_124);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_125);
    stringBuffer.append(rowSeparator);
    stringBuffer.append(TEXT_126);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_125);
    stringBuffer.append(emptySpace);
    stringBuffer.append(TEXT_134);
    stringBuffer.append(node.name);
    stringBuffer.append(TEXT_135);
    
		}else{

    stringBuffer.append(TEXT_124);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_136);
    stringBuffer.append(node.name);
    stringBuffer.append(TEXT_135);
    
		}
	}
	private void setText(XMLNode node){
		if(node.relatedColumn!=null){
			JavaType javaType = JavaTypesManager.getJavaTypeFromId(node.relatedColumn.getTalendType());
			if(javaType == JavaTypesManager.OBJECT){

    stringBuffer.append(TEXT_72);
    tool.getValue(node);
    stringBuffer.append(TEXT_137);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_138);
    tool.getValue(node);
    stringBuffer.append(TEXT_98);
    
			}else{

    stringBuffer.append(TEXT_72);
    tool.getValue(node);
    stringBuffer.append(TEXT_137);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_139);
    tool.getValue(node);
    stringBuffer.append(TEXT_140);
    
			}
		}else if(node.defaultValue !=null && !("").equals(node.defaultValue) ){

    stringBuffer.append(TEXT_124);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_125);
    stringBuffer.append(node.defaultValue );
    stringBuffer.append(TEXT_48);
    
		}
	}
	private void addAttribute(XMLNode node){
		if(node.relatedColumn!=null){

    stringBuffer.append(TEXT_72);
    tool.getValue(node);
    stringBuffer.append(TEXT_137);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_141);
    stringBuffer.append(node.path);
    stringBuffer.append(TEXT_142);
    tool.getValue(node);
    stringBuffer.append(TEXT_143);
    
		}else if(node.defaultValue !=null && !("").equals(node.defaultValue) ){

    stringBuffer.append(TEXT_124);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_141);
    stringBuffer.append(node.path);
    stringBuffer.append(TEXT_144);
    stringBuffer.append(node.defaultValue );
    stringBuffer.append(TEXT_145);
    
		}
	}
	private void addNameSpace(XMLNode node){
		if(node.relatedColumn!=null){

    stringBuffer.append(TEXT_72);
    tool.getValue(node);
    stringBuffer.append(TEXT_73);
    
			if(node.path ==null || node.path.length()==0){

    stringBuffer.append(TEXT_146);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_147);
    tool.getValue(node);
    stringBuffer.append(TEXT_148);
    
			}else{

    stringBuffer.append(TEXT_149);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_150);
    stringBuffer.append(node.path);
    stringBuffer.append(TEXT_142);
    tool.getValue(node);
    stringBuffer.append(TEXT_148);
    
			}

    stringBuffer.append(TEXT_86);
    
		}else if(node.defaultValue !=null && !("").equals(node.defaultValue) ){
			if(node.path ==null || node.path.length()==0){

    stringBuffer.append(TEXT_146);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_151);
    stringBuffer.append(node.defaultValue );
    stringBuffer.append(TEXT_145);
    
			}else{

    stringBuffer.append(TEXT_149);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_150);
    stringBuffer.append(node.path);
    stringBuffer.append(TEXT_144);
    stringBuffer.append(node.defaultValue );
    stringBuffer.append(TEXT_145);
    
			}
		}
	}
}
// ------------------- *** Null generation mode end *** ------------------- //

// ------------------- *** Common code start *** ------------------- //
IMetadataTable metadata = null;
IConnection inConn = null;
for (IConnection conn : node.getIncomingConnections()) {
	if (conn.getLineStyle().hasConnectionCategory(IConnectionCategory.FLOW)) {
		inConn = conn;
		break;
	}
}
if (inConn != null) {
	metadata = inConn.getMetadataTable();
    if (metadata!=null) {
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
    	if(conns!=null && conns.size()>0){
    		IConnection conn = conns.get(0);
    		rowNameInput = conn.getName();
    		rowStructNameInput = rowNameInput + "Struct";
    		if(conn.getLineStyle().hasConnectionCategory(IConnectionCategory.DATA)){ 
    		
            	List<Map<String, String>> rootTable = 
                	(List<Map<String,String>>)ElementParameterParser.getObjectValue(node, "__ROOT__");
                List<Map<String, String>> groupTable = 
                	(List<Map<String,String>>)ElementParameterParser.getObjectValue(node, "__GROUP__");
                List<Map<String, String>> loopTable = 
                	(List<Map<String,String>>)ElementParameterParser.getObjectValue(node, "__LOOP__");
                
                IMetadataTable inputMetadataTable= conn.getMetadataTable();
                List<IMetadataColumn> inputColumns= inputMetadataTable.getListColumns();
                
                List<Map<String,String>> groupbys = (List<Map<String,String>>)ElementParameterParser.getObjectValue(node, "__GROUPBYS__");
				
				String removeHeader = ElementParameterParser.getValue(node, "__REMOVE_HEADER__"); // add for feature7788
                String allowEmpty = ElementParameterParser.getValue(node, "__CREATE_EMPTY_ELEMENT__");
                String outputAsXSD = ElementParameterParser.getValue(node, "__OUTPUT_AS_XSD__");
                String fileNameXSD = ElementParameterParser.getValue(node, "__XSD_FILE__");
                String encoding = ElementParameterParser.getValue(node, "__ENCODING__");                
                
	            String rowNumber = ElementParameterParser.getValue(node, "__ROW_NUMBER__");
                
                String advancedSeparatorStr = ElementParameterParser.getValue(node, "__ADVANCED_SEPARATOR__");
        		boolean advancedSeparator = (advancedSeparatorStr!=null&&!("").equals(advancedSeparatorStr))?("true").equals(advancedSeparatorStr):false;
        		String thousandsSeparator = ElementParameterParser.getValueWithJavaType(node, "__THOUSANDS_SEPARATOR__", JavaTypesManager.CHARACTER);
        		String decimalSeparator = ElementParameterParser.getValueWithJavaType(node, "__DECIMAL_SEPARATOR__", JavaTypesManager.CHARACTER); 
        		
        		String mode = ElementParameterParser.getValue(node, "__GENERATION_MODE__");
        		
        		boolean storeFlow = ("true").equals(ElementParameterParser.getValue(node, "__STORE_FLOW__"));
        		                
                java.util.Map<String,IMetadataColumn> inputKeysColumns = new java.util.HashMap<String,IMetadataColumn>();
                if(inputColumns!=null){
                	for(IMetadataColumn column :inputColumns){
                		for(int i=0;i<groupbys.size();i++){
                			String columnName=groupbys.get(i).get("INPUT_COLUMN");
                			if(column.getLabel().equals(columnName)){
                				inputKeysColumns.put(columnName,column);
                				break;
                			}
                		}
                	}
                }
        		
                String destination = ElementParameterParser.getValue(node, "__DESTINATION__");
        		// init tool
                XMLTool tool = new XMLTool();
                tool.connName = conn.getName();
                tool.advancedSeparator=advancedSeparator;
                tool.thousandsSeparator=thousandsSeparator;
                tool.decimalSeparator=decimalSeparator;
                tool.cid=cid;
                tool.istWriteJSONField = istWriteJSONField;
                
                // change tables to a tree 
				Object[] treeObjs = getTree(rootTable, groupTable, loopTable, metadata.getListColumns());
            	List<XMLNode> mainList = (ArrayList<XMLNode>)treeObjs[0];
                List<XMLNode> groupList = (ArrayList<XMLNode>)treeObjs[1];
                XMLNode root = mainList.get(0);
            	XMLNode loop = (XMLNode)treeObjs[2];
                
                if(!("true").equals(allowEmpty)){
                	removeEmptyElement(root);
                }
                
                List<List<XMLNode>> groupbyNodeList = new ArrayList<List<XMLNode>>();
                for(XMLNode group:groupList){
                	groupbyNodeList.add(getGroupByNodeList(group));
                }
                IConnection nextMergeConn = NodeUtil.getNextMergeConnection(node);
				if(nextMergeConn == null || nextMergeConn.getInputId()==1){

    stringBuffer.append(TEXT_152);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_153);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_154);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_155);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_156);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_157);
    
				}

    stringBuffer.append(TEXT_158);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_159);
    
	log4jFileUtil.logCurrentRowNumberInfo();

    stringBuffer.append(TEXT_160);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_161);
    
	if (istWriteJSONField) {

    stringBuffer.append(TEXT_162);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_161);
    
	}
				for(IMetadataColumn column :inputColumns){

    stringBuffer.append(TEXT_160);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_163);
    stringBuffer.append(column.getLabel());
    stringBuffer.append(TEXT_97);
    tool.getValue(column);
    stringBuffer.append(TEXT_67);
    
	if (istWriteJSONField) {

    stringBuffer.append(TEXT_162);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_163);
    stringBuffer.append(column.getLabel());
    stringBuffer.append(TEXT_97);
    tool.getValue(column, true);
    stringBuffer.append(TEXT_67);
    
	}
				}

    if(storeFlow){
    stringBuffer.append(TEXT_164);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_165);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_166);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_167);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_168);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_67);
    }
    stringBuffer.append(TEXT_169);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_170);
    
	if(inputKeysColumns.size() !=0){
		for (IMetadataColumn column : inputColumns) {
			if(inputKeysColumns.containsKey(column.getLabel())) {

    stringBuffer.append(TEXT_171);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_172);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_173);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_19);
    stringBuffer.append(column.getLabel() );
    stringBuffer.append(TEXT_174);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_19);
    stringBuffer.append(column.getLabel() );
    stringBuffer.append(TEXT_175);
    			}
		}
	}

    stringBuffer.append(TEXT_176);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_177);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_178);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_179);
    
		if(istWriteJSONField){
            for(Map<String,String> map : groupbys){
            	String groupByColumnName = map.get("INPUT_COLUMN");
            	String outputColumnName = map.get("OUTPUT_COLUMN");
            	if (!outputColumnName.equals(jsonField)) {
            	
    stringBuffer.append(TEXT_180);
    stringBuffer.append( cid );
    stringBuffer.append(TEXT_24);
    stringBuffer.append( groupByColumnName );
    stringBuffer.append(TEXT_68);
    stringBuffer.append( rowNameInput );
    stringBuffer.append(TEXT_24);
    stringBuffer.append( groupByColumnName );
    stringBuffer.append(TEXT_181);
    
            	}
            }
		}
		
    stringBuffer.append(TEXT_182);
    
// ------------------- *** Common code end *** ------------------- //

// ------------------- *** Dom4j generation mode start *** ------------------- //
if(("Dom4j").equals(mode)){
		if(inputKeysColumns.size() !=0){

    stringBuffer.append(TEXT_183);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_184);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_185);
    		}
		if(("true").equals(outputAsXSD)){

    stringBuffer.append(TEXT_186);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_187);
    stringBuffer.append(fileNameXSD);
    stringBuffer.append(TEXT_188);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_189);
    
		}

    stringBuffer.append(TEXT_190);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_191);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_192);
    
		if(!("true").equals(outputAsXSD) && !("true").equals(allowEmpty)){

    stringBuffer.append(TEXT_193);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_194);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_192);
    
		}

    stringBuffer.append(TEXT_195);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_196);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_197);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_198);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_199);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_200);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_201);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_202);
    
		if(("true").equals(removeHeader)){

    stringBuffer.append(TEXT_203);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_204);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_205);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_206);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_207);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_208);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_209);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_210);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_67);
    
		}else{
            if(destination!=null && (destination.indexOf("tCouchbaseOutput_")==0) || destination.indexOf("tCouchDBOutput_")==0){

    stringBuffer.append(TEXT_211);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_212);
    stringBuffer.append(destination);
    stringBuffer.append(TEXT_213);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_214);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_215);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_67);
    
            }else{
				if(istWriteJSONField){
					
    stringBuffer.append(TEXT_216);
    stringBuffer.append( rowStructNameOutput );
    stringBuffer.append(TEXT_217);
    stringBuffer.append( cid );
    stringBuffer.append(TEXT_218);
    stringBuffer.append( rowStructNameOutput );
    stringBuffer.append(TEXT_219);
    
					      for(Map<String,String> map : groupbys){
								String groupByColumnName = map.get("INPUT_COLUMN");
								String outputColumnName = map.get("OUTPUT_COLUMN");
								if (!outputColumnName.equals(jsonField)) {
								
    stringBuffer.append(TEXT_220);
    stringBuffer.append( cid );
    stringBuffer.append(TEXT_24);
    stringBuffer.append( outputColumnName );
    stringBuffer.append(TEXT_221);
    stringBuffer.append( cid );
    stringBuffer.append(TEXT_24);
    stringBuffer.append( groupByColumnName );
    stringBuffer.append(TEXT_222);
    
								}
					     }
					
    stringBuffer.append(TEXT_223);
    stringBuffer.append( cid );
    stringBuffer.append(TEXT_24);
    stringBuffer.append( jsonField );
    stringBuffer.append(TEXT_204);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_224);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_225);
    stringBuffer.append( cid );
    stringBuffer.append(TEXT_226);
    
				}else{
								
    stringBuffer.append(TEXT_227);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_228);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_229);
    
				}
            }
		}

    stringBuffer.append(TEXT_230);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_231);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_232);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_233);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_234);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_235);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_236);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_237);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_238);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_239);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_240);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_241);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_178);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_71);
    
		if(istWriteJSONField){
			for(Map<String,String> map : groupbys){
            	String groupByColumnName = map.get("INPUT_COLUMN");
            	String outputColumnName = map.get("OUTPUT_COLUMN");
            	if (!outputColumnName.equals(jsonField)) {
            	
    stringBuffer.append(TEXT_180);
    stringBuffer.append( cid );
    stringBuffer.append(TEXT_24);
    stringBuffer.append( groupByColumnName );
    stringBuffer.append(TEXT_68);
    stringBuffer.append( rowNameInput );
    stringBuffer.append(TEXT_24);
    stringBuffer.append( groupByColumnName );
    stringBuffer.append(TEXT_181);
    
            	}
            }
		}
		if(inputKeysColumns.size() !=0){

    stringBuffer.append(TEXT_86);
    
		}

    stringBuffer.append(TEXT_242);
    
	//init the generate tool.
	GenerateToolByDom4j generateToolByDom4j = new GenerateToolByDom4j();
    if(("true").equals(outputAsXSD)){
    	generateToolByDom4j.outputAsXSD = true;
    }
    if(("true").equals(allowEmpty)){
    	generateToolByDom4j.allowEmpty = true;
    }
    generateToolByDom4j.istWriteJSONField = istWriteJSONField;
    generateToolByDom4j.isQuoteAllValues = isQuoteAllValues;
    generateToolByDom4j.cid = cid;
    generateToolByDom4j.tool = tool;
    
    //start generate code
    if(destination!=null && (destination.indexOf("tCouchbaseOutput_")==0 || destination.indexOf("tCouchDBOutput_")==0)){
        INode previousNode = conn.getSource();
        List<IMetadataTable> previous_metadatas = previousNode.getMetadataList();
        if ((previous_metadatas!=null)&&(previous_metadatas.size()>0)) {
            IMetadataTable previous_metadata = previous_metadatas.get(0);
            if (previous_metadata!=null) {
                List<IMetadataColumn> columnList = previous_metadata.getListColumns();

    stringBuffer.append(TEXT_211);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_243);
    
                for(IMetadataColumn colum: columnList){

    stringBuffer.append(TEXT_244);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_163);
    stringBuffer.append(colum.getLabel());
    stringBuffer.append(TEXT_97);
    stringBuffer.append(conn.getName());
    stringBuffer.append(TEXT_24);
    stringBuffer.append(colum.getLabel());
    stringBuffer.append(TEXT_67);
    
                }
            }
        }
    }

    stringBuffer.append(TEXT_245);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_246);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_247);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_248);
    
	generateToolByDom4j.generateCode(root,"root","doc");

    stringBuffer.append(TEXT_249);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_250);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_251);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_252);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_253);
    
	if(groupTable.size()>0){

    stringBuffer.append(TEXT_254);
    
	}
	for(int i=0;i<groupList.size();i++){
		XMLNode groupRootNode = groupList.get(i);

    stringBuffer.append(TEXT_255);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_256);
    stringBuffer.append(i);
    stringBuffer.append(TEXT_257);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_258);
    stringBuffer.append(i);
    stringBuffer.append(TEXT_259);
    
		for(int j=0;j<groupbyNodeList.get(i).size();j++){
			XMLNode attr = groupbyNodeList.get(i).get(j);
			if(attr.relatedColumn!=null){

    stringBuffer.append(TEXT_260);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_258);
    stringBuffer.append(i);
    stringBuffer.append(TEXT_261);
    stringBuffer.append(j);
    stringBuffer.append(TEXT_262);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_258);
    stringBuffer.append(i);
    stringBuffer.append(TEXT_261);
    stringBuffer.append(j);
    stringBuffer.append(TEXT_263);
    tool.getValue(attr);
    stringBuffer.append(TEXT_264);
    tool.getValue(attr);
    stringBuffer.append(TEXT_265);
    
			}
		}

    stringBuffer.append(TEXT_266);
    
		generateToolByDom4j.generateCode(groupList.get(i),"group"+i+"_","subTreeRootParent");

    stringBuffer.append(TEXT_267);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_256);
    stringBuffer.append(i);
    stringBuffer.append(TEXT_268);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_269);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_258);
    stringBuffer.append(i);
    stringBuffer.append(TEXT_270);
    
		for(int j=0;j<groupbyNodeList.get(i).size();j++){
			XMLNode attr = groupbyNodeList.get(i).get(j);

    stringBuffer.append(TEXT_271);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_258);
    stringBuffer.append(i);
    stringBuffer.append(TEXT_272);
    tool.getValue(attr);
    stringBuffer.append(TEXT_67);
    
		}

    stringBuffer.append(TEXT_273);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_256);
    stringBuffer.append(i);
    stringBuffer.append(TEXT_274);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_275);
    stringBuffer.append(i);
    stringBuffer.append(TEXT_276);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_277);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_278);
    stringBuffer.append(i);
    stringBuffer.append(TEXT_279);
    stringBuffer.append(i);
    stringBuffer.append(TEXT_276);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_280);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_281);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_258);
    stringBuffer.append(i);
    stringBuffer.append(TEXT_282);
    
	}

    stringBuffer.append(TEXT_283);
    
	generateToolByDom4j.generateCode(loop,"loop","subTreeRootParent");
}
// ------------------- *** Dom4j generation mode end *** ------------------- //

// ------------------- *** Null generation mode start *** ------------------- //
else if(("Null").equals(mode)){
//	String fileNameXSD = ElementParameterParser.getValue(node, "__XSD_FILE__");
	//init the generate tool.
	GenerateToolByNull generateToolByNull = new GenerateToolByNull();
    if(("true").equals(outputAsXSD)){
    	generateToolByNull.outputAsXSD = true;
    	generateToolByNull.fileNameXSD = fileNameXSD;
    }
    if(("true").equals(allowEmpty)){
    	generateToolByNull.allowEmpty = true;
    }
    generateToolByNull.cid = cid;
    generateToolByNull.tool = tool;

	if(inputKeysColumns.size() !=0){
	
    stringBuffer.append(TEXT_183);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_184);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_284);
    	}
    
		if(!("true").equals(outputAsXSD) && !("true").equals(allowEmpty)){

    stringBuffer.append(TEXT_285);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_286);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_287);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_288);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_289);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_290);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_291);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_292);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_293);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_291);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_294);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_295);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_296);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_297);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_298);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_299);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_300);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_237);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_301);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_302);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_237);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_303);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_304);
    stringBuffer.append(rowSeparator);
    stringBuffer.append(TEXT_305);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_306);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_307);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_308);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_309);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_310);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_299);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_300);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_237);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_301);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_302);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_237);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_311);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_304);
    stringBuffer.append(rowSeparator);
    stringBuffer.append(TEXT_305);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_306);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_307);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_312);
    
		}else{
			if(loopTable.size()>0){

    stringBuffer.append(TEXT_313);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_314);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_315);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_299);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_316);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_237);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_317);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_302);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_237);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_318);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_304);
    stringBuffer.append(rowSeparator);
    stringBuffer.append(TEXT_319);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_306);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_307);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_320);
    
			}
		}

    stringBuffer.append(TEXT_313);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_321);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_322);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_323);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_324);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_237);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_325);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_237);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_326);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_327);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_304);
    stringBuffer.append(rowSeparator);
    stringBuffer.append(TEXT_328);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_329);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_228);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_330);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_331);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_332);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_67);
    
	if(!("true").equals(removeHeader)){

    stringBuffer.append(TEXT_116);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_333);
    stringBuffer.append(encoding );
    stringBuffer.append(TEXT_334);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_304);
    stringBuffer.append(rowSeparator);
    stringBuffer.append(TEXT_48);
    
	}

    stringBuffer.append(TEXT_335);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_336);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_178);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_337);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_338);
    	if(inputKeysColumns.size() !=0){
    stringBuffer.append(TEXT_86);
    	}
    stringBuffer.append(TEXT_339);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_340);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_341);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_247);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_248);
    
	String rootEmptySpace = "";
	for(int i=0;i<mainList.size();i++){
		generateToolByNull.generateCode(mainList.get(i),rootEmptySpace);
		rootEmptySpace+=whiteSpace;
		
		if(!generateToolByNull.outputAsXSD && !generateToolByNull.allowEmpty){
			if(mainList.get(i).relatedColumn != null || mainList.get(i).childrenColumnList.size()>0){

    stringBuffer.append(TEXT_119);
    
                	for(IMetadataColumn column : mainList.get(i).childrenColumnList){
                		
    stringBuffer.append(TEXT_120);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_19);
    stringBuffer.append(column.getLabel());
    stringBuffer.append(TEXT_121);
    
                	}

    stringBuffer.append(TEXT_342);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_68);
    stringBuffer.append(i);
    stringBuffer.append(TEXT_343);
    
			}
		}

    stringBuffer.append(TEXT_344);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_57);
    stringBuffer.append(i);
    stringBuffer.append(TEXT_112);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_345);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_114);
    

	}

    stringBuffer.append(TEXT_346);
    
	if(groupTable.size()>0){

    stringBuffer.append(TEXT_347);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_348);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_349);
    stringBuffer.append(groupList.size());
    stringBuffer.append(TEXT_350);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_349);
    stringBuffer.append(groupList.size());
    stringBuffer.append(TEXT_351);
    
	}
	for(int i=0;i<groupList.size();i++){
		XMLNode groupRootNode = groupList.get(i);

    stringBuffer.append(TEXT_352);
    stringBuffer.append(groupRootNode.name);
    stringBuffer.append(TEXT_353);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_256);
    stringBuffer.append(i);
    stringBuffer.append(TEXT_257);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_258);
    stringBuffer.append(i);
    stringBuffer.append(TEXT_259);
    
		for(int j=0;j<groupbyNodeList.get(i).size();j++){
			XMLNode attr = groupbyNodeList.get(i).get(j);
			if(attr.relatedColumn!=null){

    stringBuffer.append(TEXT_260);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_258);
    stringBuffer.append(i);
    stringBuffer.append(TEXT_261);
    stringBuffer.append(j);
    stringBuffer.append(TEXT_262);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_258);
    stringBuffer.append(i);
    stringBuffer.append(TEXT_261);
    stringBuffer.append(j);
    stringBuffer.append(TEXT_263);
    tool.getValue(attr);
    stringBuffer.append(TEXT_264);
    tool.getValue(attr);
    stringBuffer.append(TEXT_265);
    
			}
		}

    stringBuffer.append(TEXT_354);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_355);
    stringBuffer.append(i);
    stringBuffer.append(TEXT_356);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_68);
    stringBuffer.append(i);
    stringBuffer.append(TEXT_357);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_256);
    stringBuffer.append(i);
    stringBuffer.append(TEXT_268);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_269);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_258);
    stringBuffer.append(i);
    stringBuffer.append(TEXT_270);
    
		for(int j=0;j<groupbyNodeList.get(i).size();j++){
			XMLNode attr = groupbyNodeList.get(i).get(j);

    stringBuffer.append(TEXT_271);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_258);
    stringBuffer.append(i);
    stringBuffer.append(TEXT_272);
    tool.getValue(attr);
    stringBuffer.append(TEXT_67);
    
		}

    stringBuffer.append(TEXT_358);
    
		String emptySpace = "";
		for(int len = groupList.get(i).path.split("/").length-1;len>1;len--){
			emptySpace +=whiteSpace;
		}
		generateToolByNull.generateCode(groupList.get(i),emptySpace);
		
		if(!("true").equals(outputAsXSD) && !("true").equals(allowEmpty)){
			if((groupList.get(i).relatedColumn != null || groupList.get(i).childrenColumnList.size()>0)){

    stringBuffer.append(TEXT_359);
    
            	for(IMetadataColumn column : groupList.get(i).childrenColumnList){
            		
    stringBuffer.append(TEXT_120);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_19);
    stringBuffer.append(column.getLabel());
    stringBuffer.append(TEXT_121);
    
            	}

    stringBuffer.append(TEXT_360);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_68);
    stringBuffer.append(i+mainList.size());
    stringBuffer.append(TEXT_361);
    
			}
		}

    stringBuffer.append(TEXT_362);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_57);
    stringBuffer.append(i);
    stringBuffer.append(TEXT_112);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_363);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_114);
    
	}//End of groupList loop
	
	if(groupTable.size()>0){

    stringBuffer.append(TEXT_364);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_365);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_366);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_367);
    
		if(!("true").equals(outputAsXSD) && !("true").equals(allowEmpty)){

    stringBuffer.append(TEXT_368);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_369);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_321);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_370);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_323);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_371);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_288);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_372);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_237);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_373);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_293);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_237);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_374);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_237);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_375);
    
		}else{

    stringBuffer.append(TEXT_376);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_321);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_370);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_323);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_377);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_237);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_378);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_293);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_237);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_379);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_237);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_380);
    
		}
		if(!("true").equals(outputAsXSD) && !("true").equals(allowEmpty)){

    stringBuffer.append(TEXT_381);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_382);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_383);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_384);
    stringBuffer.append(mainList.size());
    stringBuffer.append(TEXT_385);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_386);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_309);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_310);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_384);
    stringBuffer.append(mainList.size());
    stringBuffer.append(TEXT_385);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_310);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_387);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_237);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_388);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_302);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_237);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_389);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_237);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_390);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_304);
    stringBuffer.append(rowSeparator);
    stringBuffer.append(TEXT_391);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_306);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_392);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_393);
    
		}

    stringBuffer.append(TEXT_394);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_297);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_298);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_384);
    stringBuffer.append(mainList.size());
    stringBuffer.append(TEXT_385);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_310);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_387);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_237);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_388);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_302);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_237);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_389);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_237);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_390);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_304);
    stringBuffer.append(rowSeparator);
    stringBuffer.append(TEXT_391);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_306);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_392);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_395);
    
		if(!("true").equals(outputAsXSD) && !("true").equals(allowEmpty)){

    stringBuffer.append(TEXT_76);
    
		}

    stringBuffer.append(TEXT_396);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_397);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_398);
    stringBuffer.append(mainList.size());
    stringBuffer.append(TEXT_399);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_321);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_400);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_323);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_401);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_382);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_402);
    stringBuffer.append(mainList.size());
    stringBuffer.append(TEXT_403);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_237);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_398);
    stringBuffer.append(mainList.size());
    stringBuffer.append(TEXT_404);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_237);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_405);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_237);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_398);
    stringBuffer.append(mainList.size());
    stringBuffer.append(TEXT_406);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_237);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_407);
    
	}
	if(!("true").equals(outputAsXSD) && !("true").equals(allowEmpty)){

    stringBuffer.append(TEXT_408);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_409);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_410);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_411);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_412);
    stringBuffer.append(mainList.size()-1);
    stringBuffer.append(TEXT_413);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_414);
    stringBuffer.append(mainList.size()-1);
    stringBuffer.append(TEXT_415);
    
	}

    stringBuffer.append(TEXT_283);
    
	String emptySpace = "";
	for(int len =loop.path.split("/").length-1;len>1;len--){
		emptySpace +=whiteSpace;
	}
	if(!("true").equals(outputAsXSD) && !("true").equals(allowEmpty)){

    stringBuffer.append(TEXT_119);
    
    	for(IMetadataColumn column : loop.childrenColumnList){
    		
    stringBuffer.append(TEXT_120);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_19);
    stringBuffer.append(column.getLabel());
    stringBuffer.append(TEXT_121);
    
    	}
    	if(loop.hasDefaultValue == true){
    stringBuffer.append(TEXT_416);
    }
    stringBuffer.append(TEXT_123);
    
	}
	generateToolByNull.generateCode(loop,emptySpace);
	generateToolByNull.endElement(loop,emptySpace);

    stringBuffer.append(TEXT_417);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_321);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_370);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_323);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_377);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_237);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_418);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_237);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_419);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_293);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_237);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_420);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_237);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_421);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_117);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_422);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_68);
    stringBuffer.append(groupList.size()+mainList.size());
    stringBuffer.append(TEXT_71);
    
	if(!("true").equals(outputAsXSD) && !("true").equals(allowEmpty)){

    stringBuffer.append(TEXT_423);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_68);
    stringBuffer.append(groupList.size()+mainList.size()-1);
    stringBuffer.append(TEXT_343);
    
	}
}
// ------------------- *** Null generation mode end *** ------------------- //

// ------------------- *** Common code start *** ------------------- //
			}
		}
	}
}
// ------------------- *** Common code end *** ------------------- //

    stringBuffer.append(TEXT_424);
    return stringBuffer.toString();
  }
}
