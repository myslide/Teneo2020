/**
 * <copyright> Copyright (c) 2005, 2006, 2007, 2008 Springsite BV (The Netherlands) and others All rights
 * reserved. This program and the accompanying materials are made available under the terms of the
 * Eclipse Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html Contributors: Martin Taal </copyright> $Id:
 * StoreEclipseException.java,v 1.2 2007/02/01 12:35:18 mtaal Exp $
 */

package org.eclipse.emf.teneo.eclipse;

import org.eclipse.emf.teneo.TeneoException;

/**
 * Is used to throw runtime plugin exception. This class offers automatic logging to commons
 * logging. Note that this class extends RuntimeException, so no forced throws and catch statements.
 * Although there are very differing views on this topic but it is our experience that to many
 * checked exceptions only distract the programmer and have no added value.
 * 
 * @author <a href="mailto:mtaal@elver.org">Martin Taal</a>
 * @version $Revision: 1.4 $
 */

public class StoreEclipseException extends TeneoException {
	/**
	 * Serializable id
	 */
	private static final long serialVersionUID = 7433341056815136427L;

	/**
	 * The constructor, logs the exception also
	 */
	public StoreEclipseException(String msg, Throwable cause) {
		super(msg, cause);
	}

	/**
	 * The constructor, logs the exception also
	 */
	public StoreEclipseException(String msg) {
		super(msg);
	}
}
