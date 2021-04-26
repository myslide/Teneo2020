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
 * $Id: DatastoreAware.java,v 1.4 2009/03/30 07:53:04 mtaal Exp $
 */

package org.eclipse.emf.teneo.extension;

import org.eclipse.emf.teneo.DataStore;

/**
 * An extension implementing this interface will be able to 'receive' an instance of a Pamodel.
 * 
 * @author <a href="mailto:mtaal@elver.org">Martin Taal</a>
 * @version $Revision: 1.4 $
 */

public interface DatastoreAware {
	/** Set the relevant pamodel */
	void setDatastore(DataStore dataStore);
}