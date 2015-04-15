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

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.NotImplementedException;
import org.apache.pivot.beans.BXML;
import org.apache.pivot.beans.Bindable;
import org.apache.pivot.collections.Map;
import org.apache.pivot.collections.adapter.ListAdapter;
import org.apache.pivot.util.Resources;
import org.apache.pivot.wtk.Button;
import org.apache.pivot.wtk.ButtonPressListener;
import org.apache.pivot.wtk.Checkbox;
import org.apache.pivot.wtk.Component;
import org.apache.pivot.wtk.FlowPane;
import org.apache.pivot.wtk.Label;
import org.apache.pivot.wtk.ListButton;
import org.apache.pivot.wtk.TextInput;
import org.apache.pivot.wtk.validation.FloatRangeValidator;
import org.apache.pivot.wtk.validation.FloatValidator;
import org.apache.pivot.wtk.validation.IntRangeValidator;
import org.apache.pivot.wtk.validation.IntValidator;
import org.piwigo.remotesync.api.response.ReflectionGetMethodDetailsResponse.Parameter;
import org.piwigo.remotesync.api.type.TypeSwitch;

public class RequestFormInput extends FlowPane implements Bindable {

	@BXML protected Checkbox sendCheckBox;
	@BXML private Label parameterNameLabel;
	@BXML private Component input;
	@BXML private Button addInputButton;
	@BXML private Button removeInputButton;

	protected Parameter parameter;
	protected List<Component> inputs = new ArrayList<Component>();

	public void setReflectionMethodParameter(Parameter parameter) {
		this.parameter = parameter;
		parameterNameLabel.setText(parameter.camelCaseName);
		parameterNameLabel.setTooltipText(parameter.info);

		sendCheckBox.setEnabled(parameter.optional);
		sendCheckBox.setSelected(!parameter.optional);

		inputs.clear();
		inputs.add(input);
		removeInput();
		addInput(parameter);

		removeInputButton.setVisible(parameter.acceptArray);
		addInputButton.setVisible(parameter.acceptArray);
		addInputButton.setEnabled(!parameter.optional);
	}

	private void removeInput() {
		Component component = inputs.get(inputs.size() - 1);
		remove(component);
		inputs.remove(component);
		removeInputButton.setEnabled(inputs.size() > 1);
	}

	private void addInput(Parameter parameter) {
		Component newInput = createInput();

		newInput.setPreferredWidth(80);
		newInput.setTooltipText(parameter.type.typeName);
		newInput.setEnabled(sendCheckBox.isSelected());
		insert(newInput, indexOf(addInputButton));

		inputs.add(newInput);
		removeInputButton.setEnabled(inputs.size() > 1);
	}

	private static final TypeSwitch<Component, Parameter> INPUT_COMPONENT_SWITCH = new TypeSwitch<Component, Parameter>() {
		@Override
		public Component caseBool(Parameter parameter) {
			Checkbox checkbox = new Checkbox();
			if (parameter.defaultValue != null)
				checkbox.setSelected(parameter.defaultValue.equals("1"));
			return checkbox;
		}

		@Override
		public Component caseFile(Parameter parameter) {
			return new FileInput();
		}

		@Override
		public Component caseFloat(Parameter parameter) {
			TextInput textInput = new TextInput();
			if (parameter.defaultValue != null)
				textInput.setText(parameter.defaultValue);
			if (parameter.maxValue != null)
				textInput.setValidator(new FloatRangeValidator(Float.MIN_VALUE, parameter.maxValue));
			else
				textInput.setValidator(new FloatValidator());
			return textInput;
		}

		@Override
		public Component caseFloatPositive(Parameter parameter) {
			TextInput textInput = new TextInput();
			if (parameter.defaultValue != null)
				textInput.setText(parameter.defaultValue);
			if (parameter.maxValue != null)
				textInput.setValidator(new FloatRangeValidator(0, parameter.maxValue));
			else
				textInput.setValidator(new FloatRangeValidator(0, Float.MAX_VALUE));
			return textInput;
		}

		@Override
		public Component caseInt(Parameter parameter) {
			TextInput textInput = new TextInput();
			if (parameter.defaultValue != null)
				textInput.setText(parameter.defaultValue);
			if (parameter.maxValue != null)
				textInput.setValidator(new IntRangeValidator(Integer.MIN_VALUE, parameter.maxValue));
			else
				textInput.setValidator(new IntValidator());
			return textInput;
		}

		@Override
		public Component caseIntPositive(Parameter parameter) {
			TextInput textInput = new TextInput();
			if (parameter.defaultValue != null)
				textInput.setText(parameter.defaultValue);
			if (parameter.maxValue != null)
				textInput.setValidator(new IntRangeValidator(0, parameter.maxValue));
			else
				textInput.setValidator(new IntRangeValidator(0, Integer.MAX_VALUE));
			return textInput;
		}

		@Override
		public Component caseIntPositiveNotNull(Parameter parameter) {
			TextInput textInput = new TextInput();
			if (parameter.defaultValue != null)
				textInput.setText(parameter.defaultValue);
			// FIXME not null?
			if (parameter.maxValue != null)
				textInput.setValidator(new IntRangeValidator(0, parameter.maxValue));
			else
				textInput.setValidator(new IntRangeValidator(0, Integer.MAX_VALUE));
			return textInput;
		}

		@Override
		public Component caseMixed(Parameter parameter) {
			TextInput textInput = new TextInput();
			if (parameter.defaultValue != null)
				textInput.setText(parameter.defaultValue);
			return textInput;
		}
		
		@Override
		public Component caseEnum(Parameter parameter) {
			return new ListButton(new ListAdapter<String>(parameter.type.values));
		};

		@Override
		public Component defaultCase(Parameter parameter) {
			throw new NotImplementedException();
		}
	};

	private Component createInput() {
		return INPUT_COMPONENT_SWITCH.doSwitch(parameter.type, parameter);
	}

	private static final TypeSwitch<Object, Component> INPUT_VALUE_SWITCH = new TypeSwitch<Object, Component>() {
		@Override
		public Object caseBool(Component component) {
			return ((Checkbox) component).isSelected();
		};

		@Override
		public Object caseFile(Component component) {
			return ((FileInput) component).getFile();
		};

		@Override
		public Object caseFloat(Component component) {
			return Float.parseFloat(((TextInput) component).getText());
		};

		@Override
		public Object caseFloatPositive(Component component) {
			return Float.parseFloat(((TextInput) component).getText());
		};

		@Override
		public Object caseInt(Component component) {
			return Integer.parseInt(((TextInput) component).getText());
		};

		@Override
		public Object caseIntPositive(Component component) {
			return Integer.parseInt(((TextInput) component).getText());
		};

		@Override
		public Object caseIntPositiveNotNull(Component component) {
			return Integer.parseInt(((TextInput) component).getText());
		};

		@Override
		public Object caseMixed(Component component) {
			return ((TextInput) component).getText();
		};
		
		@Override
		public Object caseEnum(Component component) {
			//FIXME should be handled in wsclient
			//FIXME possible NPE
			return ((String) ((ListButton) component).getSelectedItem()).toLowerCase();
		};

		@Override
		public Object defaultCase(Component component) {
			throw new NotImplementedException();
		}
	};

	protected Object getInputValue() {
		ArrayList<Object> arrayList = new ArrayList<Object>();

		for (Component component : inputs)
			arrayList.add(INPUT_VALUE_SWITCH.doSwitch(parameter.type, component));

		if (arrayList.size() == 1)
			return arrayList.get(0);

		return arrayList;
	}

	@Override
	public void initialize(Map<String, Object> namespace, URL location, Resources resources) {
		sendCheckBox.getButtonPressListeners().add(new ButtonPressListener() {

			@Override
			public void buttonPressed(Button button) {
				for (Component component : inputs) {
					component.setEnabled(sendCheckBox.isSelected());
				}
				addInputButton.setEnabled(sendCheckBox.isSelected());
				removeInputButton.setEnabled(sendCheckBox.isSelected() && inputs.size() > 1);
			}
		});

		addInputButton.getButtonPressListeners().add(new ButtonPressListener() {

			@Override
			public void buttonPressed(Button button) {
				addInput(parameter);
			}
		});

		removeInputButton.getButtonPressListeners().add(new ButtonPressListener() {

			@Override
			public void buttonPressed(Button button) {
				removeInput();
			}
		});
	}

}
