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
 * $Id: EAnnotationParserImporter.java,v 1.8 2010/02/04 11:02:59 mtaal Exp $
 */

package org.eclipse.emf.teneo.annotations.parser;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.teneo.Constants;
import org.eclipse.emf.teneo.PersistenceOptions;
import org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedEClass;
import org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedEDataType;
import org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedEModelElement;
import org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedEPackage;
import org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedEStructuralFeature;
import org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedModel;
import org.eclipse.emf.teneo.annotations.pannotation.PannotationPackage;
import org.eclipse.emf.teneo.extension.ExtensionPoint;

/**
 * Walks over the pamodel and the paepackages and translates eannotations to pannotation types and
 * sets the corresponding values in the pamodel.
 * 
 * @author <a href="mailto:mtaal at elver.org">Martin Taal</a>
 */
public class EAnnotationParserImporter implements EClassResolver, ExtensionPoint {
	/** Log it */
	private final static Log log = LogFactory.getLog(EAnnotationParserImporter.class);

	/** annotation parser */
	private AnnotationParser annotationParser = new AnnotationParser();

	/** The prefix to distinguish jpa annotations from hb or jpox annotations */
	private static final String JPA_PREFIX = "jpa:";

	private String[] extraAnnotationsSources = new String[] {};

	private PersistenceOptions persistenceOptions = null;

	/** Parse an pamodel */
	public void process(PAnnotatedModel paModel) {
		for (PAnnotatedEPackage pap : paModel.getPaEPackages()) {
			log.debug("Processing package " + pap.getModelEPackage().getName());
			processAnnotatedModelElement(pap, pap.eClass().getEPackage());

			// and now the eclasses
			process(pap);
		}
	}

	/** Process package */
	protected void process(PAnnotatedEPackage pap) {
		for (PAnnotatedEClass pac : pap.getPaEClasses()) {
			processAnnotatedModelElement(pac, pac.getModelEClass().getEPackage());
			process(pac);
		}
		for (PAnnotatedEDataType pac : pap.getPaEDataTypes()) {
			processAnnotatedModelElement(pac, pac.getModelEDataType().getEPackage());
		}
	}

	/** Process the efeatures */
	protected void process(PAnnotatedEClass pac) {
		log.debug("Processing eclass " + pac.getModelEClass().getName());
		for (PAnnotatedEStructuralFeature paf : pac.getPaEStructuralFeatures()) {
			processAnnotatedModelElement(paf, paf.getModelEStructuralFeature().eClass().getEPackage());
		}
	}

	/** Process a type with its eannotations */
	@SuppressWarnings("unchecked")
	protected void processAnnotatedModelElement(PAnnotatedEModelElement pee, EPackage epack) {
		log.debug("Processing " + pee.getModelElement().getName());
		final ArrayList<NamedParserNode> parsedNodes = new ArrayList<NamedParserNode>();
		for (EAnnotation annotation : pee.getModelElement().getEAnnotations()) {
			parsedNodes.addAll(process(annotation, pee.getModelElement()));
		}

		// now also do the annotations on the edatatype (if any)
		/*
		 * if (pee.getAnnotatedElement() instanceof EAttribute) { final EAttribute eattr =
		 * (EAttribute)pee.getAnnotatedElement(); final EDataType edt = (EDataType)eattr.getEType(); for
		 * (Iterator it = edt.getEAnnotations().iterator(); it.hasNext();) {
		 * parsedNodes.addAll(process((EAnnotation)it.next(), pee.getAnnotatedElement())); } }
		 */

		// now the parsed nodes should be translated into features of the
		// enamedelement
		// this is done multiplelevel
		log.debug("Number of parsed typename annotations " + parsedNodes.size());
		for (NamedParserNode namedParserNode : parsedNodes) {
			final ComplexNode cn = (ComplexNode) namedParserNode;
			if (cn.isList()) {
				// find the efeature
				final EStructuralFeature ef = getEStructuralFeature(pee.eClass(), cn.getName());
				pee.eSet(ef, cn.convert(this));
			} else {
				EObject eobj = (EObject) cn.convert(this);
				boolean found = false;
				// first find exact type
				for (EReference eref : pee.eClass().getEAllReferences()) {
					if (eref.getEReferenceType() == eobj.eClass()) {
						log.debug("Found EReference " + eref.getName() + " for " + eobj.eClass().getName());
						if (eref.isMany()) {
							((List<EObject>) pee.eGet(eref)).add(eobj);
						} else {
							pee.eSet(eref, eobj);
						}
						found = true;
						break;
					}
				}
				if (!found) {
					for (EReference eref : pee.eClass().getEAllReferences()) {
						if (eref.getEReferenceType().isInstance(eobj)) {
							log.debug("Found EReference " + eref.getName() + " for " + eobj.eClass().getName());
							if (eref.isMany()) {
								((List<EObject>) pee.eGet(eref)).add(eobj);
							} else {
								pee.eSet(eref, eobj);
							}
							found = true;
							break;
						}
					}
				}
				if (!found) {
					throw new AnnotationParserException("The eclass: " + pee.eClass().getName()
							+ " does not have an efeature for " + eobj.eClass().getName());
				}
			}
		}

		// now for each eobject find which eref stores it!
		// log.debug("Find efeature for each created eobject");
		// final ArrayList objects = new ArrayList();
		// for (Iterator it = objects.iterator(); it.hasNext();) {
		// EObject eobj = (EObject)it.next();
		// log.debug("EClass " + eobj.eClass().getName());
		// }
	}

	/** Processes EAnnotations */
	private ArrayList<NamedParserNode> process(EAnnotation ea, ENamedElement ene) {
		final ArrayList<NamedParserNode> result = new ArrayList<NamedParserNode>();

		if (!isValidSource(ea.getSource(), ene.getEAnnotations())) {
			return result;
		}


		log.debug("Processing annotations ");
		for (Map.Entry<String, String> pAnnotationDetails : ea.getDetails().entrySet()) {
			final String fName = pAnnotationDetails.getKey();
			// todo externalize
			if (fName.compareToIgnoreCase(Constants.ANNOTATION_KEY_APPINFO) == 0
					|| fName.compareToIgnoreCase(Constants.ANNOTATION_KEY_VALUE) == 0) {
				log.debug("Annotation content: \n " + pAnnotationDetails.getValue());
				final String content = removeCommentLines(pAnnotationDetails.getValue());
				result.addAll(annotationParser.parse(ene, content));
			}
		}
		return result;
	}

	// removes the lines which start with a //
	private String removeCommentLines(String content) {
		if (content.indexOf("//") == -1) {
			return content;
		}
		final String[] lines = content.split("\n");
		final StringBuilder sb = new StringBuilder();
		for (String line : lines) {
			if (line.trim().startsWith("//")) {
				continue;
			}
			if (sb.length() > 0) {
				sb.append("\n");
			}
			sb.append(line);
		}
		return sb.toString();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.teneo.annotations.parser.EClassResolver#getEClass(java .lang.String)
	 */
	public EClass getEClass(String name) {
		if (name.startsWith(JPA_PREFIX)) {
			return getEClass(name.substring(JPA_PREFIX.length()));
		}
		return (EClass) PannotationPackage.eINSTANCE.getEClassifier(name);
	}

	/** Is a valid source */
	protected boolean isValidSource(String source, EList<EAnnotation> eAnnotations) {
		if (source == null) {
			return false;
		}

		if (extraAnnotationsSources.length > 0) {
			for (String annotationSource : extraAnnotationsSources) {
				if (source.equals(annotationSource)) {
					return true;
				}
			}

			// check if there is an extra annotation which could have been used
			// if so then return false, it should already have been caught in the above if
			if (persistenceOptions != null && persistenceOptions.isExtraAnnotationsOverridesDefault()) {
				for (EAnnotation eAnnotation : eAnnotations) {
					for (String annotationSource : extraAnnotationsSources) {
						if (eAnnotation.getSource().equals(annotationSource)) {
							return false;
						}
					}
				}
			}
		}

		if (source.equals(Constants.ANNOTATION_SOURCE_TENEO_JPA_AUDITING)) {
			return false;
		}

		return source.startsWith(Constants.ANNOTATION_SOURCE_TENEO_JPA)
				|| source.startsWith(Constants.ANNOTATION_SOURCE_TENEO_MAPPING);
	}



	/** Find the efeature */
	public EStructuralFeature getEStructuralFeature(EClass eClass, String name) {
		return ParserUtil.getEStructuralFeature(eClass, name);
	}

	public void setExtraAnnotationSources(PersistenceOptions po) {
		persistenceOptions = po;
		if (po.getExtraAnnotationSources() != null
				&& po.getExtraAnnotationSources().trim().length() > 0) {
			extraAnnotationsSources = po.getExtraAnnotationSources().split(",");
			for (int i = 0; i < extraAnnotationsSources.length; i++) {
				extraAnnotationsSources[i] = extraAnnotationsSources[i].trim();
				if (extraAnnotationsSources[i].startsWith(Constants.ANNOTATION_SOURCE_TENEO_JPA)
						|| extraAnnotationsSources[i].startsWith(Constants.ANNOTATION_SOURCE_TENEO_MAPPING)) {
					log.warn("Extra annotation source ("
							+ extraAnnotationsSources[i]
							+ ") starts with the default Teneo annotation source: "
							+ Constants.ANNOTATION_SOURCE_TENEO_JPA
							+ " or "
							+ Constants.ANNOTATION_SOURCE_TENEO_MAPPING
							+ ". Annotations which should sometimes be "
							+ "ignored or disabled should not start with these values as they are always considered by Teneo");
				}
			}
		}
	}
}