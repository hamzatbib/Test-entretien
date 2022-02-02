package org.talend.designer.codegen.translators.esb.webservices;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.talend.core.model.process.IConnection;
import org.talend.core.model.process.INode;
import org.talend.core.model.metadata.IMetadataColumn;
import org.talend.core.model.process.ElementParameterParser;
import org.talend.designer.codegen.config.CodeGeneratorArgument;
import org.talend.designer.codegen.config.NodeConnectionsHelper;
import org.talend.designer.codegen.config.NodeParamsHelper;

public class TESBConsumerMainJava
{
  protected static String nl;
  public static synchronized TESBConsumerMainJava create(String lineSeparator)
  {
    nl = lineSeparator;
    TESBConsumerMainJava result = new TESBConsumerMainJava();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "        ";
  protected final String TEXT_2 = " = null;";
  protected final String TEXT_3 = NL + "    final javax.xml.namespace.QName serviceName_";
  protected final String TEXT_4 = " = new javax.xml.namespace.QName(\"";
  protected final String TEXT_5 = "\", \"";
  protected final String TEXT_6 = "\");" + NL + "    final javax.xml.namespace.QName portName_";
  protected final String TEXT_7 = "\");" + NL + "" + NL + "\t final List<java.util.Map<String, String>> customHttpHeaders_";
  protected final String TEXT_8 = " = new java.util.ArrayList<java.util.Map<String, String>>();" + NL + "\t final HttpHeadersFeature httpHeadersFeature_";
  protected final String TEXT_9 = " = new HttpHeadersFeature(customHttpHeaders_";
  protected final String TEXT_10 = ");" + NL + "\t" + NL + "    try {" + NL + "        routines.system.Document requestTalendDoc_";
  protected final String TEXT_11 = " = ";
  protected final String TEXT_12 = ".payload;" + NL + "        try {" + NL + "            org.dom4j.Document responseDoc_";
  protected final String TEXT_13 = " = null;" + NL;
  protected final String TEXT_14 = NL + "                final java.util.Map<String, String> slCustomProps_";
  protected final String TEXT_15 = " = new java.util.HashMap<String, String>();";
  protected final String TEXT_16 = "slCustomProps_";
  protected final String TEXT_17 = ".put(";
  protected final String TEXT_18 = ", ";
  protected final String TEXT_19 = ");";
  protected final String TEXT_20 = NL + NL + "            final java.util.Map<String, String> customProps_";
  protected final String TEXT_21 = NL + "                    customProps_";
  protected final String TEXT_22 = NL + "                class EsbJobCorrelationCallbackHandler implements org.talend.esb.policy.correlation.CorrelationIDCallbackHandler {" + NL + "                    private String correlationId;" + NL + "                    public EsbJobCorrelationCallbackHandler(final String correlationId) {" + NL + "                        if (null != correlationId && correlationId.length() > 0) {" + NL + "                            this.correlationId = correlationId;" + NL + "                        }" + NL + "                    }" + NL + "                    public String getCorrelationId() {" + NL + "                        return correlationId;" + NL + "                    };" + NL + "                }" + NL + "                final Object correlationIDCallbackHandler = new EsbJobCorrelationCallbackHandler(";
  protected final String TEXT_23 = NL + NL + "            final List<org.apache.cxf.headers.Header> soapHeaders_";
  protected final String TEXT_24 = " = new java.util.ArrayList<org.apache.cxf.headers.Header>();";
  protected final String TEXT_25 = NL + "                final Object securityToken_";
  protected final String TEXT_26 = ";";
  protected final String TEXT_27 = NL + "                    securityToken_";
  protected final String TEXT_28 = " = globalMap.get(\"";
  protected final String TEXT_29 = "_SECURITY_TOKEN\");";
  protected final String TEXT_30 = NL + "                final routines.system.Document headersTalendDoc_";
  protected final String TEXT_31 = ".headers;" + NL + "                if (null != headersTalendDoc_";
  protected final String TEXT_32 = " && null != headersTalendDoc_";
  protected final String TEXT_33 = ".getDocument()) {" + NL + "                    javax.xml.transform.dom.DOMResult result = new javax.xml.transform.dom.DOMResult();" + NL + "                    javax.xml.transform.TransformerFactory.newInstance().newTransformer().transform(new org.dom4j.io.DocumentSource(headersTalendDoc_";
  protected final String TEXT_34 = ".getDocument()), result);" + NL + "                    for (org.w3c.dom.Node node = ((org.w3c.dom.Document) result.getNode()).getDocumentElement().getFirstChild();" + NL + "                            node != null;" + NL + "                            node = node.getNextSibling()) {" + NL + "                        if (org.w3c.dom.Node.ELEMENT_NODE == node.getNodeType()) {" + NL + "                            soapHeaders_";
  protected final String TEXT_35 = ".add(new org.apache.cxf.headers.Header(new javax.xml.namespace.QName(node.getNamespaceURI(), node.getLocalName()), node));" + NL + "                        }" + NL + "                    }" + NL + "                }";
  protected final String TEXT_36 = NL + "            List<String> propagatedHeaders = new java.util.ArrayList<String>();";
  protected final String TEXT_37 = NL + "                customHttpHeaders_";
  protected final String TEXT_38 = ".add(new java.util.HashMap<String, String>() {{put(";
  protected final String TEXT_39 = ");}});" + NL + "\t\t\t\t";
  protected final String TEXT_40 = NL + "                propagatedHeaders.add(";
  protected final String TEXT_41 = ");" + NL + "\t\t\t\t";
  protected final String TEXT_42 = NL + "            final java.util.TreeMap<String, String> tESBProviderRequestHttpHeaders=(java.util.TreeMap<String, String>)globalMap.get(\"";
  protected final String TEXT_43 = "_HEADERS_HTTP\");" + NL + "            if(tESBProviderRequestHttpHeaders!=null){" + NL + "\t\t\t\t\tfor (java.util.Map.Entry<String, ?> e : tESBProviderRequestHttpHeaders.entrySet()) {" + NL + "\t\t\t\t   \tif(propagatedHeaders.contains(e.getKey().toLowerCase())){" + NL + "\t\t\t\t         if(e.getValue() instanceof java.util.List){" + NL + "\t\t\t\t\t\t\t\tfor(Object v: (java.util.List)e.getValue()){" + NL + "\t\t\t\t\t\t\t\t   java.util.Map<String, String> header = new java.util.HashMap<String, String>();" + NL + "\t\t\t\t\t\t\t\t\theader.put(e.getKey(), String.valueOf(v));" + NL + "\t\t\t\t\t\t\t\t\tcustomHttpHeaders_";
  protected final String TEXT_44 = ".add(header);" + NL + "\t\t\t\t\t\t\t\t}" + NL + "\t\t\t\t\t\t\t}else{" + NL + "\t\t\t\t\t\t\t\tjava.util.Map<String, String> header = new java.util.HashMap<String, String>();" + NL + "\t\t\t\t\t\t\t\theader.put(e.getKey(), String.valueOf(e.getValue()));" + NL + "\t\t\t\t\t\t\t\tcustomHttpHeaders_";
  protected final String TEXT_45 = ".add(header);" + NL + "\t\t\t\t\t\t\t}" + NL + "                 }" + NL + "               }" + NL + "            }";
  protected final String TEXT_46 = NL + "            " + NL + "            if (null == registry) {" + NL + "                GenericConsumer genericConsumer_";
  protected final String TEXT_47 = " = new GenericConsumer();" + NL + "                genericConsumer_";
  protected final String TEXT_48 = ".setServiceQName(serviceName_";
  protected final String TEXT_49 = ");" + NL + "                genericConsumer_";
  protected final String TEXT_50 = ".setPortQName(portName_";
  protected final String TEXT_51 = ".setOperationQName(new javax.xml.namespace.QName(\"";
  protected final String TEXT_52 = "\"));" + NL + "                genericConsumer_";
  protected final String TEXT_53 = ".setIsRequestResponse(";
  protected final String TEXT_54 = NL + "                        genericConsumer_";
  protected final String TEXT_55 = ".setSoapAction(\"";
  protected final String TEXT_56 = "\");";
  protected final String TEXT_57 = NL + NL + "                genericConsumer_";
  protected final String TEXT_58 = ".setAddress(";
  protected final String TEXT_59 = ");" + NL + "                if (null != getClass().getResourceAsStream(\"wsdl/";
  protected final String TEXT_60 = ".wsdl\")) {" + NL + "                    genericConsumer_";
  protected final String TEXT_61 = ".setWsdlURL(\"classpath:/\" + this.getClass().getPackage().getName().replace(\".\", \"/\") + \"/wsdl/";
  protected final String TEXT_62 = ".wsdl\");" + NL + "                }" + NL + "" + NL + "                java.util.Collection<org.apache.cxf.feature.Feature> esbFeatures_";
  protected final String TEXT_63 = " =" + NL + "                        new java.util.ArrayList<org.apache.cxf.feature.Feature>();" + NL;
  protected final String TEXT_64 = NL + "                    genericConsumer_";
  protected final String TEXT_65 = ".setAddress(\"locator://\");" + NL + "\t\t\t\t\torg.talend.esb.servicelocator.cxf.LocatorFeature featureSL_";
  protected final String TEXT_66 = " = null;" + NL + "" + NL + "\t\t\t\t    org.springframework.context.support.ClassPathXmlApplicationContext context_ = new org.springframework.context.support.ClassPathXmlApplicationContext(new String[] { \"META-INF/tesb/locator/beans.xml\" });" + NL + "\t\t\t\t    featureSL_";
  protected final String TEXT_67 = " = (org.talend.esb.servicelocator.cxf.LocatorFeature) context_.getBean(org.talend.esb.servicelocator.cxf.LocatorFeature.class);" + NL + "\t\t\t        " + NL + "                    esbFeatures_";
  protected final String TEXT_68 = ".add(featureSL_";
  protected final String TEXT_69 = NL + "                    org.springframework.context.support.ClassPathXmlApplicationContext classpathXmlApplicationContext =" + NL + "                            new org.springframework.context.support.ClassPathXmlApplicationContext(\"META-INF/tesb/agent-context.xml\");" + NL + "                    org.talend.esb.sam.agent.feature.EventFeature featureSAM_";
  protected final String TEXT_70 = " = classpathXmlApplicationContext.getBean(org.talend.esb.sam.agent.feature.EventFeature.class);" + NL + "                    esbFeatures_";
  protected final String TEXT_71 = ".add(featureSAM_";
  protected final String TEXT_72 = ");" + NL + "                    genericConsumer_";
  protected final String TEXT_73 = ".setSamCustomProperties(customProps_";
  protected final String TEXT_74 = "\t\t\t\t";
  protected final String TEXT_75 = NL + "                    esbFeatures_";
  protected final String TEXT_76 = ".add(new org.apache.cxf.transport.common.gzip.GZIPFeature());";
  protected final String TEXT_77 = ".add(new org.talend.esb.policy.correlation.feature.CorrelationIDFeature());";
  protected final String TEXT_78 = ".setCorrelationIDCallbackHandler(correlationIDCallbackHandler);";
  protected final String TEXT_79 = NL + "                genericConsumer_";
  protected final String TEXT_80 = ".setEsbFeatures(esbFeatures_";
  protected final String TEXT_81 = ");" + NL + "" + NL + "                boolean use_auth_";
  protected final String TEXT_82 = ";" + NL + "                ";
  protected final String TEXT_83 = NL + "\t                if (use_auth_";
  protected final String TEXT_84 = ") {" + NL + "\t                    genericConsumer_";
  protected final String TEXT_85 = ".setAuthType((String) ";
  protected final String TEXT_86 = ");" + NL + "\t                    genericConsumer_";
  protected final String TEXT_87 = ".setUsername(";
  protected final String TEXT_88 = ");" + NL + "\t                    ";
  protected final String TEXT_89 = NL + "\t                    ";
  protected final String TEXT_90 = " " + NL + "\tfinal String decryptedPassword_";
  protected final String TEXT_91 = " = routines.system.PasswordEncryptUtil.decryptPassword(";
  protected final String TEXT_92 = NL + "\tfinal String decryptedPassword_";
  protected final String TEXT_93 = "; ";
  protected final String TEXT_94 = NL + "\t                    genericConsumer_";
  protected final String TEXT_95 = ".setPassword(decryptedPassword_";
  protected final String TEXT_96 = ");" + NL + "\t                }" + NL + "\t            ";
  protected final String TEXT_97 = NL + NL + "                boolean use_proxy_";
  protected final String TEXT_98 = NL + "\t                if (use_proxy_";
  protected final String TEXT_99 = "){" + NL + "\t                    genericConsumer_";
  protected final String TEXT_100 = ".setProxyServer(";
  protected final String TEXT_101 = ".setProxyPort(";
  protected final String TEXT_102 = ".setProxyUsername(";
  protected final String TEXT_103 = ".setProxyPassword(decryptedPassword_";
  protected final String TEXT_104 = ");" + NL + "\t                }";
  protected final String TEXT_105 = NL + "                " + NL + "                genericConsumer_";
  protected final String TEXT_106 = ".setConnectionTimeout((long)(";
  protected final String TEXT_107 = " * 1000L));" + NL + "                genericConsumer_";
  protected final String TEXT_108 = ".setReceiveTimeout((long)(";
  protected final String TEXT_109 = ".setAutoRedirect(";
  protected final String TEXT_110 = ".setRedirectSameHostOnly(";
  protected final String TEXT_111 = ");" + NL + "                ";
  protected final String TEXT_112 = ".setDisableChunking(";
  protected final String TEXT_113 = ".setLogMessages(";
  protected final String TEXT_114 = ");" + NL;
  protected final String TEXT_115 = NL + "                    {";
  protected final String TEXT_116 = NL + "                        System.setProperty(\"javax.net.ssl.trustStore\", ";
  protected final String TEXT_117 = ");" + NL + "                        System.setProperty(\"javax.net.ssl.trustStorePassword\", decryptedPassword_";
  protected final String TEXT_118 = ");" + NL + "                    }";
  protected final String TEXT_119 = NL + NL + "                if (!soapHeaders_";
  protected final String TEXT_120 = ".isEmpty()) {" + NL + "                    genericConsumer_";
  protected final String TEXT_121 = ".setSoapHeaders(soapHeaders_";
  protected final String TEXT_122 = ");" + NL + "                }" + NL + "\t\t\t\t\t esbFeatures_";
  protected final String TEXT_123 = ".add(httpHeadersFeature_";
  protected final String TEXT_124 = ");" + NL + "                responseDoc_";
  protected final String TEXT_125 = " = genericConsumer_";
  protected final String TEXT_126 = ".invoke(requestTalendDoc_";
  protected final String TEXT_127 = ".getDocument());";
  protected final String TEXT_128 = NL + "                    globalMap.put(\"";
  protected final String TEXT_129 = "_CORRELATION_ID\", genericConsumer_";
  protected final String TEXT_130 = ".getCorrelationID());";
  protected final String TEXT_131 = NL + "                globalMap.put(\"";
  protected final String TEXT_132 = "_HTTP_HEADERS\", httpHeadersFeature_";
  protected final String TEXT_133 = ".getResponseHeaders());" + NL + "                globalMap.put(\"";
  protected final String TEXT_134 = "_HTTP_RESPONSE_CODE\", httpHeadersFeature_";
  protected final String TEXT_135 = ".getResponseCode());" + NL + "            } else {" + NL + "                ESBConsumer consumer_";
  protected final String TEXT_136 = " = registry.createConsumer(" + NL + "                    new ESBEndpointInfo() {" + NL + "" + NL + "                        @SuppressWarnings(\"serial\")" + NL + "                        private java.util.Map<String, Object> props = new java.util.HashMap<String, Object>() {{" + NL + "                            put(\"dataFormat\", \"PAYLOAD\");" + NL + "                            put(\"portName\", portName_";
  protected final String TEXT_137 = ".toString());" + NL + "                            put(\"serviceName\", serviceName_";
  protected final String TEXT_138 = ".toString());" + NL + "                            put(\"defaultOperationName\", \"";
  protected final String TEXT_139 = "\");" + NL + "                            put(\"operationNamespace\", \"";
  protected final String TEXT_140 = "\");" + NL + "                            put(\"soapAction\", \"";
  protected final String TEXT_141 = "\");" + NL + "                            if (null != getClass().getResourceAsStream(\"wsdl/";
  protected final String TEXT_142 = ".wsdl\")) {" + NL + "                                put(\"wsdlURL\", \"classpath:/\" + this.getClass().getPackage().getName().replace(\".\", \"/\") + \"/wsdl/";
  protected final String TEXT_143 = ".wsdl\");" + NL + "                            }" + NL + "                            put(\"publishedEndpointUrl\", ";
  protected final String TEXT_144 = ");" + NL + "                            put(\"COMMUNICATION_STYLE\", \"";
  protected final String TEXT_145 = "\");" + NL + "" + NL + "                            put(\"logMessages\", ";
  protected final String TEXT_146 = ");" + NL + "" + NL + "                            // use Service Locator" + NL + "                            put(\"useServiceLocator\", ";
  protected final String TEXT_147 = NL + "                                put(\"LocatorSelectionStrategy\", \"";
  protected final String TEXT_148 = NL + "\t\t\t\t\t\t\t";
  protected final String TEXT_149 = NL + "                                put(\"SL-PROPS\", slCustomProps_";
  protected final String TEXT_150 = NL + "                            ";
  protected final String TEXT_151 = NL + "                                put(\"SAM-PROPS\", customProps_";
  protected final String TEXT_152 = NL + NL + "                            // use Service Activity Monitor" + NL + "                            put(\"useServiceActivityMonitor\", ";
  protected final String TEXT_153 = ");" + NL + "\t\t\t    " + NL + "\t\t\t\t\t\t    // use Service Registry" + NL + "\t\t\t\t\t\t    put(\"useServiceRegistry\", ";
  protected final String TEXT_154 = ");" + NL + "" + NL + "                            boolean use_auth_";
  protected final String TEXT_155 = ";" + NL + "                            " + NL + "\t\t\t\t\t\t\t";
  protected final String TEXT_156 = NL + "\t                            if (use_auth_";
  protected final String TEXT_157 = " || ";
  protected final String TEXT_158 = ") {" + NL + "\t                            ";
  protected final String TEXT_159 = NL + "\t                            ";
  protected final String TEXT_160 = NL + "\t                            if (";
  protected final String TEXT_161 = ") {" + NL + "\t                                put(\"username\", ";
  protected final String TEXT_162 = ");" + NL + "\t                                put(\"password\", decryptedPassword_";
  protected final String TEXT_163 = ");" + NL + "\t                                ";
  protected final String TEXT_164 = NL + "\t                                    put(\"role\", ";
  protected final String TEXT_165 = NL + "\t                                ";
  protected final String TEXT_166 = NL + "\t                                    put(\"useCrypto\", ";
  protected final String TEXT_167 = NL + "\t                            } else if (use_auth_";
  protected final String TEXT_168 = ") {" + NL + "\t                                if (\"SAML\".equals(";
  protected final String TEXT_169 = ")) {" + NL + "\t                                ";
  protected final String TEXT_170 = NL + "\t                                }" + NL + "\t                                put(\"esbSecurity\", ";
  protected final String TEXT_171 = ");" + NL + "\t                                put(\"username\", ";
  protected final String TEXT_172 = ");" + NL + "\t                               }" + NL + "\t                            }" + NL + "\t\t\t\t\t\t\t" + NL + "\t\t\t\t\t\t\t";
  protected final String TEXT_173 = NL + "\t\t\t\t\t\t\t" + NL + "" + NL + "                            if (!soapHeaders_";
  protected final String TEXT_174 = ".isEmpty()) {" + NL + "                                put(\"soapHeaders\", soapHeaders_";
  protected final String TEXT_175 = ");" + NL + "                            }" + NL + "\t\t\t\t\t\t\tput(\"httpHeadersFeature\", httpHeadersFeature_";
  protected final String TEXT_176 = NL + "                                put(\"securityToken\", securityToken_";
  protected final String TEXT_177 = NL + "                                String alias_";
  protected final String TEXT_178 = ";" + NL + "                                if (null != alias_";
  protected final String TEXT_179 = " && !\"\".equals(alias_";
  protected final String TEXT_180 = ".trim())) {" + NL + "                                    put(\"alias\", alias_";
  protected final String TEXT_181 = ");" + NL + "                                }";
  protected final String TEXT_182 = NL + "                                put(org.talend.esb.policy.correlation.feature.CorrelationIDFeature.CORRELATION_ID_CALLBACK_HANDLER, correlationIDCallbackHandler);" + NL + "                                put(\"enhancedResponse\", true);";
  protected final String TEXT_183 = NL + "                                put(\"useGZipCompression\", true);";
  protected final String TEXT_184 = "\t\t\t\t\t\t\t" + NL + "\t\t\t\t\t\t\t" + NL + "                        }};" + NL + "" + NL + "                        public String getEndpointUri() {" + NL + "                            // projectName + \"_\" + processName + \"_\" + componentName" + NL + "                            return \"";
  protected final String TEXT_185 = "_";
  protected final String TEXT_186 = "\";" + NL + "                        }" + NL + "" + NL + "                        public java.util.Map<String, Object> getEndpointProperties() {" + NL + "                            return props;" + NL + "                        }" + NL + "" + NL + "                        public String getEndpointKey() {" + NL + "                            return \"cxf\";" + NL + "                        }" + NL + "                    }" + NL + "                );" + NL + "" + NL + "                Object request_";
  protected final String TEXT_187 = " = wrapPayload(requestTalendDoc_";
  protected final String TEXT_188 = ".getDocument());" + NL + "                Object consumerResponse_";
  protected final String TEXT_189 = " = consumer_";
  protected final String TEXT_190 = ".invoke(request_";
  protected final String TEXT_191 = ");" + NL + "                if (null == consumerResponse_";
  protected final String TEXT_192 = ") {" + NL + "                    // one way call" + NL + "                } else {" + NL + "                    if(consumerResponse_";
  protected final String TEXT_193 = " instanceof java.util.Map) {" + NL + "                        java.util.Map<?,?> responseMap_";
  protected final String TEXT_194 = " = (java.util.Map<?,?>)consumerResponse_";
  protected final String TEXT_195 = ";" + NL + "                        responseDoc_";
  protected final String TEXT_196 = " = (org.dom4j.Document) responseMap_";
  protected final String TEXT_197 = ".get(\"payload\");";
  protected final String TEXT_198 = NL + "                            globalMap.put(\"";
  protected final String TEXT_199 = "_CORRELATION_ID\", responseMap_";
  protected final String TEXT_200 = ".get(org.talend.esb.policy.correlation.feature.CorrelationIDFeature.MESSAGE_CORRELATION_ID));";
  protected final String TEXT_201 = NL + "                    } else if (consumerResponse_";
  protected final String TEXT_202 = " instanceof org.dom4j.Document) {" + NL + "                        responseDoc_";
  protected final String TEXT_203 = " = (org.dom4j.Document) consumerResponse_";
  protected final String TEXT_204 = ";" + NL + "                    } else {" + NL + "                        throw new RuntimeException(\"Incompatible consumer response: \" + consumerResponse_";
  protected final String TEXT_205 = ".getClass().getName());" + NL + "                    }" + NL + "                    " + NL + "                    globalMap.put(\"";
  protected final String TEXT_206 = ".getResponseHeaders());" + NL + "               \t    globalMap.put(\"";
  protected final String TEXT_207 = ".getResponseCode());" + NL + "                }" + NL + "            }";
  protected final String TEXT_208 = NL + "                if (";
  protected final String TEXT_209 = " == null) {";
  protected final String TEXT_210 = NL + "                    ";
  protected final String TEXT_211 = " = new ";
  protected final String TEXT_212 = "Struct();" + NL + "                }" + NL + "                routines.system.Document responseTalendDoc_";
  protected final String TEXT_213 = " = null;" + NL + "                if (null != responseDoc_";
  protected final String TEXT_214 = ") {" + NL + "                    responseTalendDoc_";
  protected final String TEXT_215 = " = new routines.system.Document();" + NL + "                    responseTalendDoc_";
  protected final String TEXT_216 = ".setDocument(responseDoc_";
  protected final String TEXT_217 = ");" + NL + "                }";
  protected final String TEXT_218 = NL + "                ";
  protected final String TEXT_219 = ".payload = responseTalendDoc_";
  protected final String TEXT_220 = NL + "        } catch (Exception e_";
  protected final String TEXT_221 = ") {" + NL + "            java.util.Map<String, Object> faultInfo_";
  protected final String TEXT_222 = " = collectFaultInfo(e_";
  protected final String TEXT_223 = ");" + NL + "            if (null == faultInfo_";
  protected final String TEXT_224 = ") {" + NL + "                throw e_";
  protected final String TEXT_225 = "; // non fault exception" + NL + "            }";
  protected final String TEXT_226 = "Struct();" + NL + "                }" + NL;
  protected final String TEXT_227 = ".faultCode = (String) faultInfo_";
  protected final String TEXT_228 = ".get(\"faultCode\");";
  protected final String TEXT_229 = ".faultString = (String) faultInfo_";
  protected final String TEXT_230 = ".get(\"faultString\");";
  protected final String TEXT_231 = ".faultActor = (String) faultInfo_";
  protected final String TEXT_232 = ".get(\"faultActor\");";
  protected final String TEXT_233 = ".faultNode = (String) faultInfo_";
  protected final String TEXT_234 = ".get(\"faultNode\");";
  protected final String TEXT_235 = ".faultRole = (String) faultInfo_";
  protected final String TEXT_236 = ".get(\"faultRole\");" + NL + "" + NL + "                if (null != faultInfo_";
  protected final String TEXT_237 = ".get(\"faultDetail\")) {";
  protected final String TEXT_238 = ".faultDetail = new routines.system.Document();";
  protected final String TEXT_239 = ".faultDetail.setDocument((org.dom4j.Document) faultInfo_";
  protected final String TEXT_240 = ".get(\"faultDetail\"));" + NL + "                }" + NL;
  protected final String TEXT_241 = NL + "                printFaultInfo(faultInfo_";
  protected final String TEXT_242 = ");" + NL + "                e_";
  protected final String TEXT_243 = ".printStackTrace(System.err);";
  protected final String TEXT_244 = NL + "        }" + NL + "    } catch (Exception e_";
  protected final String TEXT_245 = "){";
  protected final String TEXT_246 = NL + "            throw(e_";
  protected final String TEXT_247 = NL + "            new TalendException(e_";
  protected final String TEXT_248 = ", currentComponent, globalMap).printStackTrace();";
  protected final String TEXT_249 = NL + "    }" + NL + "    " + NL + "    if(httpHeadersFeature_";
  protected final String TEXT_250 = "!=null){" + NL + "    \t globalMap.put(\"";
  protected final String TEXT_251 = ".getResponseHeaders());" + NL + "       globalMap.put(\"";
  protected final String TEXT_252 = "_HTTP_RESPONSE_CODE\",  httpHeadersFeature_";
  protected final String TEXT_253 = ".getResponseCode());" + NL + "    }" + NL;
  protected final String TEXT_254 = NL;

  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
    
CodeGeneratorArgument codeGenArgument = (CodeGeneratorArgument) argument;
INode node = (INode) codeGenArgument.getArgument();
/*INIT PARAMETERS AND INDICATORS.*/
NodeConnectionsHelper connsHelper = new NodeConnectionsHelper(node, true);
IConnection inputConn = connsHelper.getInputConn();
if (inputConn == null) {
    return ""; //not generate any code if no input connection.
}
IConnection connResponse = connsHelper.getOutputConnResponse();
IConnection connFault = connsHelper.getOutputConnFault();
List<IConnection> dataOutputs = connsHelper.getDataOutputs();

String projectName = codeGenArgument.getCurrentProjectName();
String cid = node.getUniqueName();
String processName = node.getProcess().getName();
String passwordFieldName;

NodeParamsHelper paramsHelper = new NodeParamsHelper(node);
String serviceNS = paramsHelper.getStringParam("__SERVICE_NS__");
String serviceName = paramsHelper.getStringParam("__SERVICE_NAME__");
String portNS = paramsHelper.getStringParam("__PORT_NS__");
String portName = paramsHelper.getStringParam("__PORT_NAME__");

String methodNS = paramsHelper.getStringParam("__METHOD_NS__");
if (methodNS == null || "".equals(methodNS)) {
    methodNS = serviceNS;
}
String methodTemp = paramsHelper.getStringParam("__METHOD__");
String method = methodTemp.indexOf("(") != -1 ? methodTemp.substring(0, methodTemp.indexOf("(")) : methodTemp;

boolean logMessages = paramsHelper.getBoolParam("__LOG_MESSAGES__");
String connTimeout = paramsHelper.getStringParam("__CONNECTION_TIMEOUT__");
String receiveTimeout = paramsHelper.getStringParam("__RECEIVE_TIMEOUT__");

String useProxy = ElementParameterParser.getValue(node, "__USE_PROXY__");
if (!node.getElementParameter("USE_PROXY").isContextMode()) {
    // non-dynamic (bug with non updating parameter value after removing field from dynamic)
    useProxy = "true".equals(useProxy) ? "true" : "false";
}

// use paramsHelper.getVisibleBoolParam to return false if the controller of this parameter is not visible.
boolean useSR = paramsHelper.getVisibleBoolParam("__USE_SR__");

boolean useSl = paramsHelper.getVisibleBoolParam("__SERVICE_LOCATOR__");
boolean useSAM = paramsHelper.getVisibleBoolParam("__SERVICE_ACTIVITY_MONITOR__");

boolean useBusinessCorrelation = paramsHelper.getVisibleBoolParam("__USE_BUSINESS_CORRELATION__");
String correlationValue = paramsHelper.getStringParam("__CORRELATION_VALUE__");

boolean useGZipCompression = paramsHelper.getVisibleBoolParam("__ENABLE_CXF_MESSAGE_GZIP__");

String useAuth = ElementParameterParser.getValue(node, "__NEED_AUTH__");
if (!node.getElementParameter("NEED_AUTH").isContextMode()) {
    // non-dynamic (bug with non updating parameter value after removing field from dynamic)
    useAuth = "true".equals(useAuth) ? "true" : "false";
}

String authType = paramsHelper.getStringParam("__AUTH_TYPE__");
if (!node.getElementParameter("AUTH_TYPE").isContextMode()) {
    authType = "\"" + authType + "\"";
}

String alias = paramsHelper.getStringParam("__AUTH_ALIAS__");
String username = paramsHelper.getStringParam("__AUTH_USERNAME__");

// saml features
boolean useAuthorization = paramsHelper.getVisibleBoolParam("__NEED_AUTHORIZATION__");
String authorizationRole = paramsHelper.getStringParam("__ROLE__");

boolean authPropagateUP = false;
boolean authPropagateCertificate = false;
if (useSR) {
    if (paramsHelper.getVisibleBoolParam("__AUTH_PROPAGATE__")) {
        authPropagateUP = true;
        if (alias != null && !"".equals(alias.trim())) {
            authPropagateCertificate = true;
        }
    }
} else {
    String authPropatateType = paramsHelper.getVisibleStringParam("__PROPAGATE_TYPE__");
    authPropagateUP = authPropatateType.equals("U_P");
    authPropagateCertificate = authPropatateType.equals("CERT");
}

boolean useCrypto = paramsHelper.getVisibleBoolParam("__NEED_ENCRYPTION__");

// init headers
boolean hasHeaders = false;
if (inputConn != null) {
    for (IMetadataColumn connColumn : inputConn.getMetadataTable().getListColumns()) {
        if ("headers".equals(connColumn.getLabel())) {
            hasHeaders = true;
            break;
        }
    }
}

List<Map<String, String>> customHttpHeaders = (List<Map<String,String>>)ElementParameterParser.getObjectValue(node,  "__HEADERS__");

String esbProviderRequestCid = null;
if (!node.getProcess().getNodesOfType("tESBProviderRequest").isEmpty()) {
	for (INode tESBProviderRequestNode : node.getProcess().getNodesOfType("tESBProviderRequest")){
		esbProviderRequestCid = tESBProviderRequestNode.getUniqueName();
	}
}

/*GENERATE CODE*/
if (inputConn != null) {
    for (IConnection conn : dataOutputs) {

    stringBuffer.append(TEXT_1);
    stringBuffer.append(conn.getName());
    stringBuffer.append(TEXT_2);
    
    }

    stringBuffer.append(TEXT_3);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_4);
    stringBuffer.append(serviceNS);
    stringBuffer.append(TEXT_5);
    stringBuffer.append(serviceName);
    stringBuffer.append(TEXT_6);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_4);
    stringBuffer.append(portNS);
    stringBuffer.append(TEXT_5);
    stringBuffer.append(portName);
    stringBuffer.append(TEXT_7);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_8);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_9);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_10);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_11);
    stringBuffer.append(inputConn.getName());
    stringBuffer.append(TEXT_12);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_13);
     if ((useSl) || (useSR)){ 
    stringBuffer.append(TEXT_14);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_15);
    
                for(Entry<String, String> prop : paramsHelper.getPropertiesPram("__SERVICE_LOCATOR_CUSTOM_PROPERTIES__")) {
                    
    stringBuffer.append(TEXT_16);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_17);
    stringBuffer.append(prop.getKey());
    stringBuffer.append(TEXT_18);
    stringBuffer.append(prop.getValue());
    stringBuffer.append(TEXT_19);
    
                }
            } 
    stringBuffer.append(TEXT_20);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_15);
     if ((useSAM) || (useSR)) {
                List<Entry<String, String>> customProperties = paramsHelper.getPropertiesPram("__SERVICE_ACTIVITY_CUSTOM_PROPERTIES__");
                if (!customProperties.isEmpty()) { 
     for (Entry<String, String> prop : customProperties) { 
    stringBuffer.append(TEXT_21);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_17);
    stringBuffer.append(prop.getKey());
    stringBuffer.append(TEXT_18);
    stringBuffer.append(prop.getValue());
    stringBuffer.append(TEXT_19);
     } 
     } 
     } 
     if (useBusinessCorrelation || useSR) { 
    stringBuffer.append(TEXT_22);
    stringBuffer.append(correlationValue);
    stringBuffer.append(TEXT_19);
     } 
    stringBuffer.append(TEXT_23);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_24);
     if (authPropagateUP || authPropagateCertificate) { 
    stringBuffer.append(TEXT_25);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_26);
     List<? extends INode> nodesProviderRequest = node.getProcess().getNodesOfType("tESBProviderRequest");
                if (!nodesProviderRequest.isEmpty()) { 
    stringBuffer.append(TEXT_27);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_28);
    stringBuffer.append(nodesProviderRequest.iterator().next().getUniqueName());
    stringBuffer.append(TEXT_29);
     }
            } if (hasHeaders) { 
    stringBuffer.append(TEXT_30);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_11);
    stringBuffer.append(inputConn.getName());
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
    if(esbProviderRequestCid!=null){
    stringBuffer.append(TEXT_36);
    }
            for (Map<String, String> h : customHttpHeaders) {
					if(!java.lang.Boolean.parseBoolean(h.get("PROPAGATE").replaceAll("\"",""))){
				
    stringBuffer.append(TEXT_37);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_38);
    stringBuffer.append(h.get("NAME"));
    stringBuffer.append(TEXT_18);
    stringBuffer.append(h.get("VALUE"));
    stringBuffer.append(TEXT_39);
    } else if(esbProviderRequestCid!=null && h.get("NAME")!=null){
    stringBuffer.append(TEXT_40);
    stringBuffer.append(h.get("NAME").toLowerCase());
    stringBuffer.append(TEXT_41);
    }
				}
				if(esbProviderRequestCid!=null){
    stringBuffer.append(TEXT_42);
    stringBuffer.append(esbProviderRequestCid);
    stringBuffer.append(TEXT_43);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_44);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_45);
    }
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
    stringBuffer.append(TEXT_49);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_51);
    stringBuffer.append(methodNS);
    stringBuffer.append(TEXT_5);
    stringBuffer.append(method);
    stringBuffer.append(TEXT_52);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_53);
    stringBuffer.append("request-response".equals(paramsHelper.getStringParam("__COMMUNICATION_STYLE__")));
    stringBuffer.append(TEXT_19);
     String soapAction = paramsHelper.getStringParam("__SOAP_ACTION__");
                    if (soapAction != null && (!soapAction.matches("\\s*"))) { 
    stringBuffer.append(TEXT_54);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_55);
    stringBuffer.append(soapAction);
    stringBuffer.append(TEXT_56);
      } 
    stringBuffer.append(TEXT_57);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_58);
    stringBuffer.append(paramsHelper.getStringParam("__ESB_ENDPOINT__"));
    stringBuffer.append(TEXT_59);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_60);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_61);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_62);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_63);
     if (useSl) { 
    stringBuffer.append(TEXT_64);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_65);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_66);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_67);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_68);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_19);
     } 
     if (useSAM) { 
    stringBuffer.append(TEXT_69);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_70);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_71);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_72);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_73);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_19);
     } 
    stringBuffer.append(TEXT_74);
     if (useGZipCompression) { 
    stringBuffer.append(TEXT_75);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_76);
     } 
     if (useBusinessCorrelation) { 
    stringBuffer.append(TEXT_75);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_77);
     } 
     if(useBusinessCorrelation || useSR) { 
    stringBuffer.append(TEXT_64);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_78);
     } 
    stringBuffer.append(TEXT_79);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_80);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_81);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_11);
    stringBuffer.append(useAuth);
    stringBuffer.append(TEXT_82);
    if("true".equals(useAuth)) {
    stringBuffer.append(TEXT_83);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_84);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_85);
    stringBuffer.append(authType);
    stringBuffer.append(TEXT_86);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_87);
    stringBuffer.append(username);
    stringBuffer.append(TEXT_88);
     passwordFieldName = "__AUTH_PASSWORD__"; 
    stringBuffer.append(TEXT_89);
    if (ElementParameterParser.canEncrypt(node, passwordFieldName)) {
    stringBuffer.append(TEXT_90);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_91);
    stringBuffer.append(ElementParameterParser.getEncryptedValue(node, passwordFieldName));
    stringBuffer.append(TEXT_19);
    } else {
    stringBuffer.append(TEXT_92);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_11);
    stringBuffer.append( ElementParameterParser.getValue(node, passwordFieldName));
    stringBuffer.append(TEXT_93);
    }
    stringBuffer.append(TEXT_94);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_95);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_96);
    }
    stringBuffer.append(TEXT_97);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_11);
    stringBuffer.append(useProxy);
    stringBuffer.append(TEXT_82);
    if("true".equals(useProxy)) {
    stringBuffer.append(TEXT_98);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_99);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_100);
    stringBuffer.append(paramsHelper.getStringParam("__PROXY_HOST__"));
    stringBuffer.append(TEXT_86);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_101);
    stringBuffer.append(paramsHelper.getStringParam("__PROXY_PORT__"));
    stringBuffer.append(TEXT_86);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_102);
    stringBuffer.append(paramsHelper.getStringParam("__PROXY_USERNAME__"));
    stringBuffer.append(TEXT_88);
     passwordFieldName = "__PROXY_PASSWORD__"; 
    stringBuffer.append(TEXT_89);
    if (ElementParameterParser.canEncrypt(node, passwordFieldName)) {
    stringBuffer.append(TEXT_90);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_91);
    stringBuffer.append(ElementParameterParser.getEncryptedValue(node, passwordFieldName));
    stringBuffer.append(TEXT_19);
    } else {
    stringBuffer.append(TEXT_92);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_11);
    stringBuffer.append( ElementParameterParser.getValue(node, passwordFieldName));
    stringBuffer.append(TEXT_93);
    }
    stringBuffer.append(TEXT_94);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_103);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_104);
    }
    stringBuffer.append(TEXT_105);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_106);
    stringBuffer.append(connTimeout);
    stringBuffer.append(TEXT_107);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_108);
    stringBuffer.append(receiveTimeout);
    stringBuffer.append(TEXT_107);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_109);
    stringBuffer.append(paramsHelper.getBoolParam("__FOLLOW_REDIRECTS__"));
    stringBuffer.append(TEXT_49);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_110);
    stringBuffer.append(!paramsHelper.getBoolParam("__REDIRECT_NON_SAME_HOST__"));
    stringBuffer.append(TEXT_111);
     if(!paramsHelper.getBoolParam("__FOLLOW_REDIRECTS__")){ 
    stringBuffer.append(TEXT_64);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_112);
    stringBuffer.append(paramsHelper.getBoolParam("__DISABLE_CHUNKING__"));
    stringBuffer.append(TEXT_19);
     } 
    stringBuffer.append(TEXT_57);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_113);
    stringBuffer.append(logMessages);
    stringBuffer.append(TEXT_114);
     if (paramsHelper.getBoolParam("__NEED_SSL_TO_TRUSTSERVER__")) {
                    passwordFieldName = "__SSL_TRUSTSERVER_PASSWORD__"; 
    stringBuffer.append(TEXT_115);
    if (ElementParameterParser.canEncrypt(node, passwordFieldName)) {
    stringBuffer.append(TEXT_90);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_91);
    stringBuffer.append(ElementParameterParser.getEncryptedValue(node, passwordFieldName));
    stringBuffer.append(TEXT_19);
    } else {
    stringBuffer.append(TEXT_92);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_11);
    stringBuffer.append( ElementParameterParser.getValue(node, passwordFieldName));
    stringBuffer.append(TEXT_93);
    }
    stringBuffer.append(TEXT_116);
    stringBuffer.append(paramsHelper.getStringParam("__SSL_TRUSTSERVER_TRUSTSTORE__"));
    stringBuffer.append(TEXT_117);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_118);
     } 
    stringBuffer.append(TEXT_119);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_120);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_121);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_122);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_123);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_124);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_125);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_126);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_127);
     if (useBusinessCorrelation || useSR) { 
    stringBuffer.append(TEXT_128);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_129);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_130);
     } 
    stringBuffer.append(TEXT_131);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_132);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_133);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_134);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_135);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_136);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_137);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_138);
    stringBuffer.append(method);
    stringBuffer.append(TEXT_139);
    stringBuffer.append(methodNS);
    stringBuffer.append(TEXT_140);
    stringBuffer.append(paramsHelper.getStringParam("__SOAP_ACTION__"));
    stringBuffer.append(TEXT_141);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_142);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_143);
    stringBuffer.append(paramsHelper.getStringParam("__ESB_ENDPOINT__"));
    stringBuffer.append(TEXT_144);
    stringBuffer.append(paramsHelper.getStringParam("__COMMUNICATION_STYLE__"));
    stringBuffer.append(TEXT_145);
    stringBuffer.append(logMessages);
    stringBuffer.append(TEXT_146);
    stringBuffer.append(useSl);
    stringBuffer.append(TEXT_19);
     if (useSl){ 
    stringBuffer.append(TEXT_147);
    stringBuffer.append(paramsHelper.getStringParam("__SERVICE_LOCATOR_STRATEGY__"));
    stringBuffer.append(TEXT_56);
     } 
    stringBuffer.append(TEXT_148);
     if ((useSl) || (useSR)){ 
    stringBuffer.append(TEXT_149);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_19);
     } 
    stringBuffer.append(TEXT_150);
     if ((useSAM) || (useSR)){ 
    stringBuffer.append(TEXT_151);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_19);
     } 
    stringBuffer.append(TEXT_152);
    stringBuffer.append(useSAM);
    stringBuffer.append(TEXT_153);
    stringBuffer.append(useSR);
    stringBuffer.append(TEXT_154);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_11);
    stringBuffer.append(useAuth);
    stringBuffer.append(TEXT_155);
     if("true".equals(useAuth) || useSR){
    stringBuffer.append(TEXT_156);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_157);
    stringBuffer.append(useSR);
    stringBuffer.append(TEXT_158);
     passwordFieldName = "__AUTH_PASSWORD__"; 
    stringBuffer.append(TEXT_159);
    if (ElementParameterParser.canEncrypt(node, passwordFieldName)) {
    stringBuffer.append(TEXT_90);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_91);
    stringBuffer.append(ElementParameterParser.getEncryptedValue(node, passwordFieldName));
    stringBuffer.append(TEXT_19);
    } else {
    stringBuffer.append(TEXT_92);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_11);
    stringBuffer.append( ElementParameterParser.getValue(node, passwordFieldName));
    stringBuffer.append(TEXT_93);
    }
    stringBuffer.append(TEXT_160);
    stringBuffer.append(useSR);
    stringBuffer.append(TEXT_161);
    stringBuffer.append(username);
    stringBuffer.append(TEXT_162);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_163);
     if (!authorizationRole.isEmpty() && !"\"\"".equals(authorizationRole)) { 
    stringBuffer.append(TEXT_164);
    stringBuffer.append(authorizationRole);
    stringBuffer.append(TEXT_163);
     } 
    stringBuffer.append(TEXT_165);
     if (useCrypto) { 
    stringBuffer.append(TEXT_166);
    stringBuffer.append(useCrypto);
    stringBuffer.append(TEXT_163);
     } 
    stringBuffer.append(TEXT_167);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_168);
    stringBuffer.append(authType);
    stringBuffer.append(TEXT_169);
     if (useAuthorization) { 
    stringBuffer.append(TEXT_164);
    stringBuffer.append(authorizationRole);
    stringBuffer.append(TEXT_163);
     }if (useCrypto) { 
    stringBuffer.append(TEXT_166);
    stringBuffer.append(useCrypto);
    stringBuffer.append(TEXT_163);
     } 
    stringBuffer.append(TEXT_170);
    stringBuffer.append(authType);
    stringBuffer.append(TEXT_171);
    stringBuffer.append(username);
    stringBuffer.append(TEXT_162);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_172);
    }
    stringBuffer.append(TEXT_173);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_174);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_175);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_19);
     if (authPropagateUP || authPropagateCertificate) { 
    stringBuffer.append(TEXT_176);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_19);
     }
                            if (authPropagateCertificate) { 
    stringBuffer.append(TEXT_177);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_11);
    stringBuffer.append(alias);
    stringBuffer.append(TEXT_178);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_179);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_180);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_181);
     }
                            if (useBusinessCorrelation || useSR) { 
    stringBuffer.append(TEXT_182);
     }
                            if (useGZipCompression) { 
    stringBuffer.append(TEXT_183);
     } 
    stringBuffer.append(TEXT_184);
    stringBuffer.append(projectName);
    stringBuffer.append(TEXT_185);
    stringBuffer.append(processName);
    stringBuffer.append(TEXT_185);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_186);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_187);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_188);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_189);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_190);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_191);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_192);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_193);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_194);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_195);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_196);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_197);
     if (useBusinessCorrelation || useSR) { 
    stringBuffer.append(TEXT_198);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_199);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_200);
     } 
    stringBuffer.append(TEXT_201);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_202);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_203);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_204);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_205);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_132);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_206);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_134);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_207);
     if (null != connResponse) {
                String connResponseName = connResponse.getName(); 
    stringBuffer.append(TEXT_208);
    stringBuffer.append(connResponseName);
    stringBuffer.append(TEXT_209);
    stringBuffer.append(TEXT_210);
    stringBuffer.append(connResponseName);
    stringBuffer.append(TEXT_211);
    stringBuffer.append(connResponseName);
    stringBuffer.append(TEXT_212);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_213);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_214);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_215);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_216);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_217);
    stringBuffer.append(TEXT_218);
    stringBuffer.append(connResponseName);
    stringBuffer.append(TEXT_219);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_26);
     } 
    stringBuffer.append(TEXT_220);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_221);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_222);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_223);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_224);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_225);
     if (null != connFault) {
                String connFaultName = connFault.getName(); 
    stringBuffer.append(TEXT_208);
    stringBuffer.append(connFaultName);
    stringBuffer.append(TEXT_209);
    stringBuffer.append(TEXT_210);
    stringBuffer.append(connFaultName);
    stringBuffer.append(TEXT_211);
    stringBuffer.append(connFaultName);
    stringBuffer.append(TEXT_226);
    stringBuffer.append(TEXT_218);
    stringBuffer.append(connFaultName);
    stringBuffer.append(TEXT_227);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_228);
    stringBuffer.append(TEXT_218);
    stringBuffer.append(connFaultName);
    stringBuffer.append(TEXT_229);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_230);
    stringBuffer.append(TEXT_218);
    stringBuffer.append(connFaultName);
    stringBuffer.append(TEXT_231);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_232);
    stringBuffer.append(TEXT_218);
    stringBuffer.append(connFaultName);
    stringBuffer.append(TEXT_233);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_234);
    stringBuffer.append(TEXT_218);
    stringBuffer.append(connFaultName);
    stringBuffer.append(TEXT_235);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_236);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_237);
    stringBuffer.append(TEXT_210);
    stringBuffer.append(connFaultName);
    stringBuffer.append(TEXT_238);
    stringBuffer.append(TEXT_210);
    stringBuffer.append(connFaultName);
    stringBuffer.append(TEXT_239);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_240);
     } else { 
    stringBuffer.append(TEXT_241);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_242);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_243);
     } 
    stringBuffer.append(TEXT_244);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_245);
     if (paramsHelper.getBoolParam("__DIE_ON_ERROR__")) { 
    stringBuffer.append(TEXT_246);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_19);
     } else { 
    stringBuffer.append(TEXT_247);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_248);
     } 
    stringBuffer.append(TEXT_249);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_250);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_132);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_251);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_252);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_253);
     } 
    stringBuffer.append(TEXT_254);
    return stringBuffer.toString();
  }
}
