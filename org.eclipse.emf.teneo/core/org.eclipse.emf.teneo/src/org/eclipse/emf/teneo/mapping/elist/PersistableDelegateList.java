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
 * $Id: PersistableDelegateList.java,v 1.9 2010/03/25 00:12:45 mtaal Exp $
 */

package org.eclipse.emf.teneo.mapping.elist;

import org.eclipse.emf.ecore.EStructuralFeature;

/**
 * A tag which signals that a list is either a persistable map, featuremap or elist.
 * 
 * @author <a href="mailto:mtaal@elver.org">Martin Taal</a>
 * @version $Revision: 1.9 $
 */

public interface PersistableDelegateList<E> {
	/** Return the delegate list/map without doing a load */
	public Object getDelegate();

	/** Returns true if the elist is loaded */
	public boolean isLoaded();

	/** If the delegate has been initialized */
	public boolean isInitialized();

	public EStructuralFeature getEStructuralFeature();
}