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

import org.apache.pivot.beans.BXMLSerializer;
import org.apache.pivot.beans.Bindable;
import org.apache.pivot.collections.Map;
import org.apache.pivot.util.Resources;
import org.apache.pivot.wtk.SplitPane;
import org.apache.pivot.wtk.TreeView;
import org.apache.pivot.wtk.TreeViewSelectionListener;
import org.apache.pivot.wtk.content.TreeNode;
import org.piwigo.remotesync.api.reflection.MethodReflection;

public class Reflection extends SplitPane implements Bindable {

	@Override
	public void initialize(Map<String, Object> namespace, URL location, Resources resources) {
		try {
			MethodTree methodTree = (MethodTree) new BXMLSerializer().readObject(MethodTree.class, "methodTree.bxml");
			final RequestForm requestForm = (RequestForm) new BXMLSerializer().readObject(RequestForm.class, "requestForm.bxml");

			methodTree.getTreeView().getTreeViewSelectionListeners().add(new TreeViewSelectionListener.Adapter() {
				@Override
				public void selectedNodeChanged(TreeView treeView, Object previousSelectedNode) {
					TreeNode selectedNode = (TreeNode) treeView.getSelectedNode();
					if (selectedNode.getUserData() instanceof MethodReflection) {
						MethodReflection methodReflection = (MethodReflection) selectedNode.getUserData();
						try {
							requestForm.setReflectionMethod(methodReflection);
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
			});
			
			setLeft(methodTree);
			setRight(requestForm);
			setSplitRatio(0.3f);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
