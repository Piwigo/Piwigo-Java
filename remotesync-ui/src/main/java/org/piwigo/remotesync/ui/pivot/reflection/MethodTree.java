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

import org.apache.pivot.beans.BXML;
import org.apache.pivot.beans.Bindable;
import org.apache.pivot.collections.Map;
import org.apache.pivot.util.Resources;
import org.apache.pivot.wtk.Border;
import org.apache.pivot.wtk.TreeView;
import org.apache.pivot.wtk.content.TreeBranch;
import org.apache.pivot.wtk.content.TreeNode;
import org.piwigo.remotesync.api.reflection.MethodReflection;
import org.piwigo.remotesync.api.reflection.ReflectionRegistry;

public class MethodTree extends Border implements Bindable {

	@BXML private TreeView treeView;

	public TreeView getTreeView() {
		return treeView;
	}

	@Override
	public void initialize(Map<String, Object> namespace, URL location, Resources resources) {
		TreeBranch treeBranch = new TreeBranch("methods");
		treeView.setTreeData(treeBranch);

		for (MethodReflection methodReflection : ReflectionRegistry.methodDetails) {
			String[] split = methodReflection.getMethodName().split("\\.");

			TreeBranch parentBranch = treeBranch;
			for (int i = 0; i < split.length - 1; i++) {
				String string = split[i];

				boolean found = false;
				for (int j = 0; j < parentBranch.getLength(); j++) {
					if (parentBranch.get(j).getText().equals(string)) {
						parentBranch = (TreeBranch) parentBranch.get(j);
						found = true;
					}
				}

				if (!found) {
					TreeBranch newTreeBranch = new TreeBranch(string);
					parentBranch.add(newTreeBranch);
					parentBranch = newTreeBranch;
				}
			}

			TreeNode treeNode = new TreeNode(split[split.length - 1]);
			treeNode.setUserData(methodReflection);
			parentBranch.add(treeNode);
		}

	}

}
