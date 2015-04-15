/*******************************************************************************
 * Copyright (c) 2014 Matthieu Helleboid.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/old-licenses/gpl-2.0.html
 * 
 * Contributors:
 *     Matthieu Helleboid - initial API and implementation
 ******************************************************************************/
package org.piwigo.remotesync.generator;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.piwigo.remotesync.api.client.Client;
import org.piwigo.remotesync.api.client.WSClient;
import org.piwigo.remotesync.api.reflection.ReflectionCustomization;
import org.piwigo.remotesync.api.request.ReflectionGetMethodDetailsRequest;
import org.piwigo.remotesync.api.request.ReflectionGetMethodListRequest;
import org.piwigo.remotesync.api.response.ReflectionGetMethodDetailsResponse;
import org.piwigo.remotesync.api.response.ReflectionGetMethodDetailsResponse.Parameter;
import org.piwigo.remotesync.api.xml.PersisterFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Level;

import com.floreysoft.jmte.Engine;

public class WSJavaAPIWriter {

	private static final Logger logger = LoggerFactory.getLogger(WSJavaAPIWriter.class);

	private static final String PIWIGO_URL = "http://demo.piwigo.com";

	private static final String DOT_XML = ".xml";
	private static final String DOT_JAVA = ".java";

	private static final String API_TEMPLATE = "apiTemplate.jmte";
	private static final String REFLECTION_TEMPLATE = "reflectionTemplate.jmte";
	private static final String REQUEST_TEMPLATE = "requestTemplate.jmte";
	private static final String RESPONSE_TEMPLATE = "responseTemplate.jmte";
	private static final String RESPONSE_COMMON_TEMPLATE = "responseCommonTemplate.jmte";

	private static final String API = "AbstractAPI";
	private static final String REFLECTION = "ReflectionRegistry";
	private static final String REQUEST = "Request";
	private static final String RESPONSE = "Response";
	private static final String COMMON_RESPONSE = "Common" + RESPONSE;

	private static final String SRC_MAIN_JAVA = "/src/main/java";
	private static final String SRC_MAIN_RESOURCES = "/src/main/resources";
	private static final String SRC_GEN_JAVA = "/src/gen/java";
	private static final String SRC_GEN_RESOURCES = "/src/gen/resources";

	private static final String API_DIRECTORY = "/org/piwigo/remotesync/api/";
	private static final String REQUEST_DIRECTORY = "/org/piwigo/remotesync/api/request/";
	private static final String RESPONSE_DIRECTORY = "/org/piwigo/remotesync/api/response/";
	private static final String REFLECTION_DIRECTORY = "/org/piwigo/remotesync/api/reflection/";

	private static final String GENERATED_COMMENT = "@org.piwigo.remotesync.generator.Generated";

	public static void main(String[] args) throws Exception {
		((ch.qos.logback.classic.Logger) logger).setLevel(Level.INFO);
		logger.info("=====================================================================================");
		boolean refresh = false;
		new WSJavaAPIWriter(PIWIGO_URL).writeAPI(refresh);
		logger.info("=====================================================================================");
		logger.info("Generation finished");
	}

	private String projectPath;
	private String url;

	protected void writeAPI(boolean refresh) throws Exception {
		List<ReflectionGetMethodDetailsResponse> methodDetails = null;

		if (refresh) {
			methodDetails = getMethodDetailsFromWS();
			for (ReflectionGetMethodDetailsResponse methodDetail : methodDetails)
				writeXml(methodDetail);
		} else
			methodDetails = readXml();

		ReflectionCustomization.customizeMethodDetails(methodDetails);
		checkMethodDetails(methodDetails);

		List<String> generatedCommonResponses = new ArrayList<String>();
		Map<String, Object> model = createModel(methodDetails);
		applyTemplate(model, API_TEMPLATE, API_DIRECTORY + API + DOT_JAVA);
		applyTemplate(model, REFLECTION_TEMPLATE, REFLECTION_DIRECTORY + REFLECTION + DOT_JAVA);
		for (ReflectionGetMethodDetailsResponse methodDetail : methodDetails) {
			model = createModel(methodDetail);
			applyTemplate(model, REQUEST_TEMPLATE, REQUEST_DIRECTORY + methodDetail.camelCaseName + REQUEST + DOT_JAVA);
			applyTemplate(model, RESPONSE_TEMPLATE, RESPONSE_DIRECTORY + methodDetail.camelCaseName + RESPONSE + DOT_JAVA);
			
			String commonResponseFilePath = RESPONSE_DIRECTORY + methodDetail.commonName + COMMON_RESPONSE + DOT_JAVA;
			if (!generatedCommonResponses.contains(commonResponseFilePath)) {
				generatedCommonResponses.add(commonResponseFilePath);
				applyTemplate(model, RESPONSE_COMMON_TEMPLATE, commonResponseFilePath);
			}
		}
	}

	private void checkMethodDetails(List<ReflectionGetMethodDetailsResponse> methodDetails) {
		for (ReflectionGetMethodDetailsResponse detailsResponse : methodDetails) {
			boolean foundMandatoryArray = false;
			for (Parameter parameter : detailsResponse.mandatoryParameters) {
				if (foundMandatoryArray)
					throw new RuntimeException("there must be only one mandatory parameter and it must be the last mandatory one");
				foundMandatoryArray = parameter.acceptArray;
			}
		}
	}

	private Map<String, Object> createModel(List<ReflectionGetMethodDetailsResponse> methodDetails) {
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("generatedComment", GENERATED_COMMENT);
		model.put("reflectionName", REFLECTION);
		model.put("apiName", API);
		model.put("request", REQUEST);
		model.put("response", RESPONSE);
		model.put("methodDetails", methodDetails);
		return model;
	}

	protected Map<String, Object> createModel(ReflectionGetMethodDetailsResponse methodDetailsResponse) {
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("generatedComment", GENERATED_COMMENT);
		model.put("description", methodDetailsResponse.description);
		model.put("methodName", methodDetailsResponse.name);
		model.put("requestName", methodDetailsResponse.camelCaseName + REQUEST);
		model.put("responseName", methodDetailsResponse.camelCaseName + RESPONSE);
		model.put("responseParentName", methodDetailsResponse.commonName + COMMON_RESPONSE);
		model.put("parameters", methodDetailsResponse.parameters);
		model.put("mandatoryParameters", methodDetailsResponse.mandatoryParameters);
		model.put("hasMandatoryParameters", methodDetailsResponse.hasMandatoryParameters);
		model.put("hasMandatoryArrayParameters", methodDetailsResponse.hasMandatoryArrayParameters);
		model.put("options", methodDetailsResponse.options);
		model.put("needPwgToken", methodDetailsResponse.needPwgToken);
		return model;
	}

	protected WSJavaAPIWriter(String url) {
		this.url = url;
		URL resource = WSJavaAPIWriter.class.getResource(WSJavaAPIWriter.class.getSimpleName() + ".class");
		this.projectPath = resource.getPath().replaceAll("remotesync-api.*", "remotesync-api");
	}

	protected List<ReflectionGetMethodDetailsResponse> getMethodDetailsFromWS() throws Exception {
		Client client = new WSClient(url);

		logger.info("get WS methods");
		List<ReflectionGetMethodDetailsResponse> methodDetailsResponses = new ArrayList<ReflectionGetMethodDetailsResponse>();
		for (String methodName : client.sendRequest(new ReflectionGetMethodListRequest()).methodNames) {
			logger.info("get method {} details", methodName);
			methodDetailsResponses.add(client.sendRequest(new ReflectionGetMethodDetailsRequest(methodName)));
		}

		return methodDetailsResponses;
	}

	protected void writeXml(ReflectionGetMethodDetailsResponse methodDetailsResponse) throws Exception {
		logger.debug("write xml file for {}", methodDetailsResponse.name);
		PersisterFactory.createPersister().write(methodDetailsResponse, getGenResourcesFile(REFLECTION_DIRECTORY + methodDetailsResponse.name + DOT_XML));
	}

	protected ArrayList<ReflectionGetMethodDetailsResponse> readXml() throws Exception {
		ArrayList<ReflectionGetMethodDetailsResponse> methodDetails = new ArrayList<ReflectionGetMethodDetailsResponse>();
		readXml(methodDetails, getMainResourcesFile(REFLECTION_DIRECTORY));
		readXml(methodDetails, getGenResourcesFile(REFLECTION_DIRECTORY));

		Collections.sort(methodDetails, new Comparator<ReflectionGetMethodDetailsResponse>() {

			@Override
			public int compare(ReflectionGetMethodDetailsResponse o1, ReflectionGetMethodDetailsResponse o2) {
				return o1.name.compareTo(o2.name);
			}
		});

		return methodDetails;
	}

	private void readXml(ArrayList<ReflectionGetMethodDetailsResponse> methodDetails, File directory) throws Exception {
		if (!directory.exists())
			return;
		for (File file : directory.listFiles()) {
			if (file.getName().endsWith(DOT_XML)) {
				logger.debug("read xml file {}", file.getName());
				methodDetails.add(PersisterFactory.createPersister().read(ReflectionGetMethodDetailsResponse.class, file));
			}
		}
	}

	protected void applyTemplate(Map<String, Object> model, String templateFileName, String filePath) throws IOException, URISyntaxException, ClassNotFoundException {
		if (canOverwrite(filePath)) {
			InputStream resourceAsStream = getClass().getResourceAsStream(templateFileName);
			String template = IOUtils.toString(resourceAsStream);
			String transformed = new Engine().transform(template, model);

			// fix reserved name in generated code
			transformed = transformed.replace("public)", "_public)");

			logger.debug("generate file {}", filePath);
			writeFile(filePath, transformed);
		} else {
			logger.info("NOT generated file {}", filePath);
		}
	}

	protected File getProjectFile() {
		return new File(projectPath);
	}

	protected File getMainJavaFile(String filePath) {
		return new File(getProjectFile(), SRC_MAIN_JAVA + filePath);
	}

	protected File getGenJavaFile(String filePath) {
		return new File(getProjectFile(), SRC_GEN_JAVA + filePath);
	}

	protected File getMainResourcesFile(String filePath) {
		return new File(getProjectFile(), SRC_MAIN_RESOURCES + filePath);
	}

	protected File getGenResourcesFile(String filePath) {
		return new File(getProjectFile(), SRC_GEN_RESOURCES + filePath);
	}

	protected void writeFile(String filePath, String transformed) throws IOException {
		BufferedReader reader = null;
		BufferedWriter writer = null;
		try {
			reader = new BufferedReader(new StringReader(transformed));
			writer = new BufferedWriter(new FileWriter(getGenJavaFile(filePath)));
			String line = null;
			boolean CRLF = false;
			int level = 0;
			while((line = reader.readLine()) != null) {
				level += StringUtils.countMatches(line, "{");
				level -= StringUtils.countMatches(line, "}");
				if (line.trim().length() == 0) {
					if (!CRLF & level < 2) 
						writer.newLine();
					CRLF = true;
				} else {
					CRLF = false;
					writer.write(line);
					writer.newLine();
				}
			}
			writer.flush();
		} catch (Exception e) {
			IOUtils.closeQuietly(reader);
			IOUtils.closeQuietly(writer);
		}
	}

	protected boolean canOverwrite(String filePath) throws IOException {
		File mainJavaFile = getMainJavaFile(filePath);
		boolean mainJavaFileExists = mainJavaFile.exists();
		boolean mainJavaFileCanOverwrite = canOverwrite(mainJavaFile);

		File genJavaFile = getGenJavaFile(filePath);
		boolean genJavaFileExists = genJavaFile.exists();
		boolean genJavaFileCanOverwrite = canOverwrite(genJavaFile);

		if (mainJavaFileExists) {
			if (genJavaFileExists) {
				logger.warn("file {} in {} and {}", filePath, SRC_MAIN_JAVA, SRC_GEN_JAVA);
				return mainJavaFileCanOverwrite && genJavaFileCanOverwrite;
			} else {
				if (mainJavaFileCanOverwrite)
					logger.warn("file {} should be in {}", filePath, SRC_GEN_JAVA);
				return mainJavaFileCanOverwrite;
			}
		} else {
			if (genJavaFileExists) {
				if (!genJavaFileCanOverwrite)
					logger.warn("file {} should be in {}", filePath, SRC_MAIN_JAVA);
				return genJavaFileCanOverwrite;
			} else
				return true;
		}
	}

	private boolean canOverwrite(File file) throws IOException {
		if (!file.exists())
			return true;
		for (String line : FileUtils.readLines(file)) {
			if (GENERATED_COMMENT.equals(line)) {
				return true;
			}
		}
		return false;
	}
}
