/**
 * <copyright>
 *
 * Copyright (c) 2005, 2006, 2007, 2008 Springsite BV (The Netherlands) and others
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Martin Taal
 * </copyright>
 *
 * $Id: OpenResource.java,v 1.3 2008/02/28 07:07:59 mtaal Exp $
 */

package org.eclipse.emf.teneo.hibernate.eclipse;

import java.util.Properties;

import org.eclipse.emf.teneo.eclipse.resourcehandler.StoreOpenResource;
import org.eclipse.emf.teneo.hibernate.HbUtil;

/**
 * Performs the open resource action based on the information in the .ehb file
 * 
 * @author <a href="mailto:mtaal@elver.org">Martin Taal</a>
 * @version $Revision: 1.3 $
 */
public class OpenResource extends StoreOpenResource {
	/** Ensure that the data store is opened */
	protected void openDataStore(Properties props) {
		HbUtil.getCreateDataStore(props);
	}
}