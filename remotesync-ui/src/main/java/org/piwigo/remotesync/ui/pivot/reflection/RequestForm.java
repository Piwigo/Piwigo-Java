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
package org.piwigo.remotesync.ui.pivot.reflection;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.pivot.beans.BXML;
import org.apache.pivot.beans.BXMLSerializer;
import org.apache.pivot.beans.Bindable;
import org.apache.pivot.collections.Map;
import org.apache.pivot.serialization.SerializationException;
import org.apache.pivot.util.Resources;
import org.apache.pivot.wtk.Button;
import org.apache.pivot.wtk.ButtonPressListener;
import org.apache.pivot.wtk.Form;
import org.apache.pivot.wtk.Form.Section;
import org.apache.pivot.wtk.Label;
import org.apache.pivot.wtk.Orientation;
import org.apache.pivot.wtk.PushButton;
import org.apache.pivot.wtk.SplitPane;
import org.apache.pivot.wtk.TextArea;
import org.piwigo.remotesync.api.ISyncConfiguration;
import org.piwigo.remotesync.api.client.AuthenticatedWSClient;
import org.piwigo.remotesync.api.conf.ConfigurationUtil;
import org.piwigo.remotesync.api.reflection.MethodReflection;
import org.piwigo.remotesync.api.reflection.ReflectiveRequest;
import org.piwigo.remotesync.api.response.BasicResponse;
import org.piwigo.remotesync.api.response.ReflectionGetMethodDetailsResponse;
import org.piwigo.remotesync.api.response.ReflectionGetMethodDetailsResponse.Parameter;

public class RequestForm extends SplitPane implements Bindable {
	@BXML private Label methodNameLabel;
	@BXML private Label descriptionLabel;
	@BXML private Form form;
	@BXML private Section mandatorySection;
	@BXML private Section optionalSection;

	@BXML private PushButton submitButton;
	@BXML private Label errorLabel;

	@BXML private TextArea resultTextArea;
	
	private MethodReflection methodReflection;
	private List<RequestFormInput> requestFormInputs = new ArrayList<RequestFormInput>();

	private AuthenticatedWSClient client;

	@Override
	public void initialize(Map<String, Object> namespace, URL location, Resources resources) {
		setOrientation(Orientation.VERTICAL);
		submitButton.getButtonPressListeners().add(new ButtonPressListener() {

			@SuppressWarnings("unchecked")
			@Override
			public void buttonPressed(Button button) {
				try {
					ReflectiveRequest request = new ReflectiveRequest(methodReflection);
					for (RequestFormInput requestFormInput : requestFormInputs) {
						if (requestFormInput.sendCheckBox.isSelected()) {
							Object inputValue = requestFormInput.getInputValue();
							if (inputValue instanceof List) 
								request.addParameterValueList(requestFormInput.parameter, (List<? extends Object>) inputValue);
							else
								request.addParameterValue(requestFormInput.parameter, inputValue);
						}
					}
					
					if (client == null) {
						ISyncConfiguration syncConfiguration = ConfigurationUtil.INSTANCE.getUserConfiguration().getCurrentSyncConfiguration();
						client = new AuthenticatedWSClient(syncConfiguration).login();
					}

					BasicResponse response = client.sendRequest(request);
					resultTextArea.setText(response.getXmlContent());
				} catch (Exception e) {
					resultTextArea.setText(ExceptionUtils.getFullStackTrace(e));
				}
			}
		});
	}

	public void setReflectionMethod(MethodReflection methodReflection) throws IOException, SerializationException  {
		this.methodReflection = methodReflection;
		
		mandatorySection.remove(0, mandatorySection.getLength());
		optionalSection.remove(0, optionalSection.getLength());
		requestFormInputs.clear();
		
		ReflectionGetMethodDetailsResponse methodDetails = methodReflection.getMethodDetails();

		methodNameLabel.setText(methodDetails.name + "(" + methodDetails.camelCaseName + ")");
		if (methodDetails.description != null)
			descriptionLabel.setText(methodDetails.description);

		for (Parameter parameter : methodDetails.parameters) {
			RequestFormInput requestFormInput = (RequestFormInput) new BXMLSerializer().readObject(RequestFormInput.class, "requestFormInput.bxml");
			requestFormInputs.add(requestFormInput);
			requestFormInput.setReflectionMethodParameter(parameter);

			if (parameter.optional)
				optionalSection.add(requestFormInput);
			else
				mandatorySection.add(requestFormInput);
		}
	}
}
