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
 *   Martin Taal - Initial API and implementation
 *
 * </copyright>
 *
 * $Id: ExtensionManagerFactory.java,v 1.4 2009/03/30 07:53:04 mtaal Exp $
 */

package org.eclipse.emf.teneo.extension;

/**
 * Factory which creates ExtensionManagers. A customer factory can be set by calling setInstance().
 * 
 * @author <a href="mailto:mtaal@elver.org">Martin Taal</a>
 * @version $Revision: 1.4 $
 */
public class ExtensionManagerFactory {

	private static ExtensionManagerFactory instance = new ExtensionManagerFactory();

	/**
	 * @return the instance
	 */
	public static ExtensionManagerFactory getInstance() {
		return instance;
	}

	/**
	 * @param instance
	 *          the instance to set
	 */
	public static void setInstance(ExtensionManagerFactory instance) {
		ExtensionManagerFactory.instance = instance;
	}

	public ExtensionManager create() {
		return new DefaultExtensionManager();
	}
}