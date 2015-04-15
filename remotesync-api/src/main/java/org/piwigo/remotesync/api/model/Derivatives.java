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
package org.piwigo.remotesync.api.model;

import org.simpleframework.xml.Element;

public class Derivatives {
    @Element
    public Derivative square;

    @Element
    public Derivative thumb;

    @Element
    public Derivative _2small;
    
    @Element
    public Derivative xsmall;
    
    @Element
    public Derivative small;
    
    @Element
    public Derivative medium;
    
    @Element
    public Derivative large;
    
    @Element
    public Derivative xlarge;
    
    @Element
    public Derivative xxlarge;
}
