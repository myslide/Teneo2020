/**
 * <copyright>
 * </copyright>
 *
 * $Id: HbAnnotatedETypeElementValidator.java,v 1.3 2008/04/23 15:44:25 mtaal Exp $
 */
package org.eclipse.emf.teneo.hibernate.hbmodel.validation;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.teneo.annotations.pannotation.Column;

import org.eclipse.emf.teneo.hibernate.hbannotation.Cascade;
import org.eclipse.emf.teneo.hibernate.hbannotation.CollectionOfElements;
import org.eclipse.emf.teneo.hibernate.hbannotation.Filter;
import org.eclipse.emf.teneo.hibernate.hbannotation.IdBag;
import org.eclipse.emf.teneo.hibernate.hbannotation.Index;
import org.eclipse.emf.teneo.hibernate.hbannotation.MapKey;
import org.eclipse.emf.teneo.hibernate.hbannotation.Where;

/**
 * A sample validator interface for
 * {@link org.eclipse.emf.teneo.hibernate.hbmodel.HbAnnotatedETypeElement}. This doesn't really do
 * anything, and it's not a real EMF artifact. It was generated by the
 * org.eclipse.emf.examples.generator.validator plug-in to illustrate how EMF's code generator can
 * be extended. This can be disabled with -vmargs
 * -Dorg.eclipse.emf.examples.generator.validator=false.
 */
public interface HbAnnotatedETypeElementValidator {
	boolean validate();

	boolean validateHbWhere(Where value);

	boolean validateHbCollectionOfElements(CollectionOfElements value);

	boolean validateHbMapKey(MapKey value);

	boolean validateHbColumns(EList<Column> value);

	boolean validateHbCascade(Cascade value);

	boolean validateHbIdBag(IdBag value);

	boolean validateHbIndex(Index value);

	boolean validateFilter(EList<Filter> value);
}
