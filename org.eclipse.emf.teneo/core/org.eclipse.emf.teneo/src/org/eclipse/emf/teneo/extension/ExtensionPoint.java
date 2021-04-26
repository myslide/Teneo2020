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
 * $Id: ExtensionPoint.java,v 1.4 2009/03/30 07:53:04 mtaal Exp $
 */

package org.eclipse.emf.teneo.extension;

/**
 * Is a marker interface to mark a class to be replacable by a user extension. The classname or
 * interface implementing this interface is also the name of the ExtensionPoint (the value of the
 * point attribute in the extension).
 * 
 * @author <a href="mailto:mtaal@elver.org">Martin Taal</a>
 * @version $Revision: 1.4 $
 */

public interface ExtensionPoint {
}