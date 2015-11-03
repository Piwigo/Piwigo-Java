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
package org.piwigo.remotesync.ui.pivot;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.apache.pivot.beans.BXML;
import org.apache.pivot.beans.Bindable;
import org.apache.pivot.collections.Map;
import org.apache.pivot.collections.adapter.ListAdapter;
import org.apache.pivot.util.Resources;
import org.apache.pivot.wtk.Border;
import org.apache.pivot.wtk.Button;
import org.apache.pivot.wtk.ButtonPressListener;
import org.apache.pivot.wtk.Checkbox;
import org.apache.pivot.wtk.Component;
import org.apache.pivot.wtk.ComponentStateListener;
import org.apache.pivot.wtk.FileBrowserSheet;
import org.apache.pivot.wtk.Form;
import org.apache.pivot.wtk.Label;
import org.apache.pivot.wtk.ListButton;
import org.apache.pivot.wtk.MessageType;
import org.apache.pivot.wtk.PushButton;
import org.apache.pivot.wtk.Sheet;
import org.apache.pivot.wtk.SheetCloseListener;
import org.apache.pivot.wtk.Slider;
import org.apache.pivot.wtk.SliderValueListener;
import org.apache.pivot.wtk.TextInput;
import org.apache.pivot.wtk.content.ListButtonDataRenderer;
import org.apache.pivot.wtk.content.ListViewItemRenderer;
import org.apache.pivot.wtk.validation.Validator;
import org.piwigo.remotesync.api.conf.UserConfiguration;
import org.piwigo.remotesync.api.ISyncConfiguration;
import org.piwigo.remotesync.api.conf.ConfigurationUtil;
import org.piwigo.remotesync.api.conf.SyncConfiguration;
import org.piwigo.remotesync.api.conf.SyncConfigurationValidator;
import org.piwigo.remotesync.api.conf.SyncConfigurationValidator.SyncValidationException;

public class SyncConfigurationForm extends Border implements Bindable {
	@BXML private Form form;
	
	@BXML private ListButton configurationListButton;

	@BXML private TextInput urlTextInput;
	@BXML private TextInput loginTextInput;
	@BXML private TextInput passwordTextInput;

	@BXML private TextInput directoryTextInput;
	@BXML private PushButton directoryButton;
	
	@BXML private Slider chunkSizeSlider;
	@BXML private Label chunkSizeLabel;

	@BXML private Checkbox proxyCheckbox;
	@BXML private TextInput proxyUrlTextInput;
	@BXML private TextInput proxyPortTextInput;
	@BXML private TextInput proxyUsernameTextInput;
	@BXML private TextInput proxyPasswordTextInput;

	@BXML private Label errorLabel;
	@BXML private PushButton reloadButton;
	
	private List<FieldAccessor<? extends Component>> accessors;
	
	@Override
	public void initialize(Map<String, Object> arg0, URL arg1, Resources arg2) {
		accessors = new ArrayList<FieldAccessor<? extends Component>>();
		accessors.add(new TextInputFieldAccessor(urlTextInput));
		accessors.add(new TextInputFieldAccessor(loginTextInput));
		accessors.add(new TextInputFieldAccessor(passwordTextInput));
		accessors.add(new TextInputFieldAccessor(directoryTextInput));
		accessors.add(new SliderFieldAccessor(chunkSizeSlider, "chunkSize"));
		accessors.add(new CheckBoxFieldAccessor(proxyCheckbox, "usesProxy"));
		accessors.add(new TextInputFieldAccessor(proxyUrlTextInput));
		accessors.add(new TextInputFieldAccessor(proxyPortTextInput));
		accessors.add(new TextInputFieldAccessor(proxyUsernameTextInput));
		accessors.add(new TextInputFieldAccessor(proxyPasswordTextInput));
		
		ComponentStateListener componentStateListener = new ComponentStateListener() {
			
			@Override
			public void focusedChanged(Component component, Component obverseComponent) {
				saveAccessors();
			}
			
			@Override
			public void enabledChanged(Component component) {
			}
		};
		
		for (FieldAccessor<? extends Component> accessor : accessors) {
			accessor.component.getComponentStateListeners().add(componentStateListener);
		}
		
		configurationListButton.setItemRenderer(new ListViewItemRenderer() {
			@Override
			public String toString(Object item) {
				if (item instanceof SyncConfiguration) {
					ISyncConfiguration syncConfiguration = (ISyncConfiguration) item;
					return syncConfiguration.getUsername() + "@" + syncConfiguration.getUrl();
				}
				return super.toString(item);
			}
		});
		
		configurationListButton.setDataRenderer(new ListButtonDataRenderer() {
			@Override
			public String toString(Object data) {
				if (data instanceof SyncConfiguration) {
					ISyncConfiguration syncConfiguration = (ISyncConfiguration) data;
					return syncConfiguration.getUsername() + "@" + syncConfiguration.getUrl();
				}
				return super.toString(data);
			}
		});
		
		directoryTextInput.setValidator(new Validator() {
			
			@Override
			public boolean isValid(String text) {
				File file = new File(text);
				return file.exists() && file.isDirectory();
			}
		});
		
		directoryButton.getButtonPressListeners().add(new ButtonPressListener() {
			
			@Override
			public void buttonPressed(Button button) {
				final FileBrowserSheet fileBrowserSheet = new FileBrowserSheet();
				File rootDirectory = findRootDirectory();
				fileBrowserSheet.setRootDirectory(rootDirectory);
				fileBrowserSheet.setMode(FileBrowserSheet.Mode.SAVE_TO);
                fileBrowserSheet.open(getWindow(), new SheetCloseListener() {
                    @Override
                    public void sheetClosed(Sheet sheet) {
                    	if (sheet.getResult()) {
                    		directoryTextInput.setText(fileBrowserSheet.getSelectedFile().getAbsolutePath());
                    	}
                	}
                });				
			}

			protected File findRootDirectory() {
				File rootDirectory = new File(directoryTextInput.getText());
				while (rootDirectory != null && !rootDirectory.exists() && !rootDirectory.isDirectory())
					rootDirectory = rootDirectory.getParentFile();
				if (rootDirectory == null)
					return ConfigurationUtil.INSTANCE.getUserCurrentDirectory();
				return rootDirectory;
			}
		});
		
		chunkSizeSlider.getSliderValueListeners().add(new SliderValueListener() {
			
			@Override
			public void valueChanged(Slider slider, int previousValue) {
				updateChunkSizeLabel();
			}
		});
		
		proxyCheckbox.getButtonPressListeners().add(new ButtonPressListener() {
			
			@Override
			public void buttonPressed(Button button) {
				updateProxyState();
			}
		});
		
		reloadButton.getButtonPressListeners().add(new ButtonPressListener() {
            @Override
            public void buttonPressed(Button button) {
            	ConfigurationUtil.INSTANCE.loadUserConfig();
            	loadAccessors();
            }
        });
	}
	
	protected void updateChunkSizeLabel() {
		chunkSizeLabel.setText(chunkSizeSlider.getValue() + " Ko");
	}

	protected void updateProxyState() {
		proxyUrlTextInput.setVisible(proxyCheckbox.isSelected());
		proxyPortTextInput.setVisible(proxyCheckbox.isSelected());
		proxyUsernameTextInput.setVisible(proxyCheckbox.isSelected());
		proxyPasswordTextInput.setVisible(proxyCheckbox.isSelected());
	}
	
	public void loadAccessors() {
		UserConfiguration userConfiguration = ConfigurationUtil.INSTANCE.getUserConfiguration();
		SyncConfiguration syncConfiguration = userConfiguration.getCurrentSyncConfiguration();
		
		configurationListButton.setListData(new ListAdapter<SyncConfiguration>(userConfiguration.getSyncConfigurations()));
		configurationListButton.setSelectedItem(syncConfiguration);

		for (FieldAccessor<? extends Component> accessor : accessors) {
			accessor.setValue(syncConfiguration.getValue(accessor.getFieldName()));
		}
		updateChunkSizeLabel();
		updateProxyState();
	}
	
	public void saveAccessors() {
		UserConfiguration userConfiguration = ConfigurationUtil.INSTANCE.getUserConfiguration();
		SyncConfiguration syncConfiguration = userConfiguration.getCurrentSyncConfiguration();

		
		
		for (FieldAccessor<? extends Component> accessor : accessors) {
			syncConfiguration.setValue(accessor.getFieldName(), accessor.getValue());
		}
	}
	
	public void validateAccessors() {
    	form.clearFlags();
        errorLabel.setText("");

		for (FieldAccessor<? extends Component> accessor : accessors) {
			try {
				SyncConfigurationValidator.INSTANCE.validate(accessor.getFieldName(), accessor.getValue(), proxyCheckbox.isSelected());
			} catch (SyncValidationException e) {
				Component component = accessor.getComponent();
    			if (component.isEnabled() && component.isVisible() && component instanceof TextInput) {
    				TextInput input = (TextInput) component;
    				if (input.getTextKey().equals(e.fieldName)) {
    					Form.setFlag(component, new Form.Flag(MessageType.ERROR, "is invalid"));
    					errorLabel.setText("Some required information is missing.");
    					return;
    				}
    			}
			}
		}
	}
	
	private static abstract class FieldAccessor<T extends Component> {
		protected T component;
		
		protected String fieldName;

		public FieldAccessor (T component, String fieldName) {
			this.component = component;
			this.fieldName = fieldName;
		}

		public T getComponent() {
			return component;
		}
		
		public String getFieldName() {
			return fieldName;
		};
		
		public abstract String getValue();
		public abstract void setValue(String value);
	}
	
	public static class TextInputFieldAccessor extends FieldAccessor<TextInput> {

		public TextInputFieldAccessor(TextInput component) {
			super(component, component.getTextKey());
		}

		@Override
		public String getValue() {
			return component.getText();
		}

		@Override
		public void setValue(String value) {
			if (value != null)
				component.setText(value);
		}
	}
	
	public static class SliderFieldAccessor extends FieldAccessor<Slider> {

		public SliderFieldAccessor(Slider component, String fieldName) {
			super(component, fieldName);
		}

		@Override
		public String getValue() {
			return component.getValue() + "";
		}

		@Override
		public void setValue(String value) {
			component.setValue(Integer.parseInt(value));
		}
		
	}
	
	public static class CheckBoxFieldAccessor extends FieldAccessor<Checkbox> {

		public CheckBoxFieldAccessor(Checkbox component, String fieldName) {
			super(component, fieldName);
		}
		
		@Override
		public String getValue() {
			return Boolean.toString(component.isSelected());
		}

		@Override
		public void setValue(String value) {
			component.setSelected(Boolean.parseBoolean(value));
		}
		
	}
}
