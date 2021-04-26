/**
 * <copyright> Copyright (c) 2005, 2006, 2007, 2008 Springsite BV (The Netherlands) and others All rights
 * reserved. This program and the accompanying materials are made available under the terms of the
 * Eclipse Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html Contributors: Martin Taal - Initial API and
 * implementation </copyright> $Id: StoreUtil.java,v 1.31 2010/03/28 09:20:26 mtaal Exp $
 */

package org.eclipse.emf.teneo.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.impl.NotificationImpl;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.impl.EStoreEObjectImpl;
import org.eclipse.emf.ecore.impl.EStoreEObjectImpl.EStoreEList;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.ExtendedMetaData;
import org.eclipse.emf.ecore.util.FeatureMapUtil;
import org.eclipse.emf.ecore.xmi.impl.EcoreResourceFactoryImpl;
import org.eclipse.emf.ecore.xml.type.XMLTypePackage;
import org.eclipse.emf.teneo.Constants;
import org.eclipse.emf.teneo.PackageRegistryProvider;
import org.eclipse.emf.teneo.TeneoException;
import org.eclipse.emf.teneo.ecore.EModelResolver;
import org.eclipse.emf.teneo.mapping.strategy.EntityNameStrategy;
import org.eclipse.emf.teneo.type.PersistentStoreAdapter;

/**
 * Contains different util methods.
 * 
 * @author <a href="mailto:mtaal@elver.org">Martin Taal</a>
 * @version $Revision: 1.31 $
 */

public class StoreUtil {

	/** The logger */
	private static Log log = LogFactory.getLog(StoreUtil.class);

	/** Separator between package and the extension */
	public static final char EXTENSION_SEPARATOR = '.';

	/** Separator in package names */
	private static final char PACKAGE_SEPARATOR = '.';

	/**
	 * Separator between segments of an identifying strucuralfeature or edatatype path
	 */
	public static final char PATH_SEPARATOR = '/';

	/** The Annotation source name */
	public static final String ANNOTATION_SOURCE = "http:///org/eclipse/emf/ecore/util/ExtendedMetaData";

	public static boolean isAuditEntryEClass(EClass eClass) {
		final EAnnotation eAnnotation = eClass.getEAnnotation(Constants.ANNOTATION_SOURCE_AUDITING);
		if (eAnnotation == null) {
			return false;
		}
		return eAnnotation.getDetails().containsKey(Constants.ANNOTATION_AUDITING_MODELELEMENT);
	}

	public static void resetSyntheticListInfo(EStructuralFeature eFeature, Object target) {
		if (target == null || eFeature instanceof EAttribute) {
			return;
		}
		PersistentStoreAdapter persistentStoreAdapter = StoreUtil
				.getPersistentStoreAdapter((EObject) target);
		persistentStoreAdapter.setSyntheticProperty(
				StoreUtil.getExtraLazyInverseIndexPropertyName(eFeature), null);
		persistentStoreAdapter.setSyntheticProperty(
				StoreUtil.getExtraLazyInversePropertyName(eFeature), null);
	}

	public static void setSyntheticListIndex(EStructuralFeature eFeature, Object target, Integer value) {
		if (eFeature instanceof EAttribute) {
			return;
		}
		PersistentStoreAdapter persistentStoreAdapter = StoreUtil
				.getPersistentStoreAdapter((EObject) target);
		persistentStoreAdapter.setSyntheticProperty(
				StoreUtil.getExtraLazyInverseIndexPropertyName(eFeature), value);
	}

	public static void setSyntheticListOwner(EStructuralFeature eFeature, Object target, Object owner) {
		if (eFeature instanceof EAttribute) {
			return;
		}
		PersistentStoreAdapter persistentStoreAdapter = StoreUtil
				.getPersistentStoreAdapter((EObject) target);
		persistentStoreAdapter.setSyntheticProperty(
				StoreUtil.getExtraLazyInversePropertyName(eFeature), owner);
	}

	public static String getExtraLazyInversePropertyName(EStructuralFeature eFeature) {
		return eFeature.getEContainingClass().getName() + eFeature.getName();
	}

	public static String getExtraLazyInverseIndexPropertyName(EStructuralFeature eFeature) {
		return getExtraLazyInversePropertyName(eFeature) + "idx";
	}

	/** Returns true if the passed EAttribute is a qname */
	public static boolean isQName(EAttribute eAttribute) {
		final EDataType eDataType = eAttribute.getEAttributeType();
		return eDataType == XMLTypePackage.eINSTANCE.getQName();
	}

	/** Reads the epackages present in the passed ecore files. */
	public static List<EPackage> readEPackages(String[] ecoreFiles) {
		final ResourceSet resourceSet = new ResourceSetImpl();
		resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap()
				.put("*", new EcoreResourceFactoryImpl());
		final ArrayList<EPackage> epackages = new ArrayList<EPackage>();
		for (String element : ecoreFiles) {

			if (log.isDebugEnabled()) {
				log.debug("Reading ecore file: " + element);
			}

			Resource res = resourceSet.getResource(URI.createFileURI(element), true);

			Iterator<EObject> it = res.getAllContents();
			while (it.hasNext()) {
				final EObject obj = it.next();
				if (obj instanceof EPackage) {
					final EPackage epack = (EPackage) obj;
					final EPackage currentEPackage = PackageRegistryProvider.getInstance()
							.getPackageRegistry().getEPackage(epack.getNsURI());
					if (currentEPackage != null) { // use the existing epackage
						if (!epackages.contains(currentEPackage)) {
							epackages.add(currentEPackage);
						}
					} else {
						PackageRegistryProvider.getInstance().getPackageRegistry().put(epack.getNsURI(), epack);
						epackages.add(epack);
					}
				}
			}
		}
		return epackages;
	}

	/**
	 * Returns true if the passed EStructuralFeature represents a map. Note that the method also
	 * handles EAttribute (returns false) for convenience reasons.
	 */
	public static boolean isMap(EStructuralFeature eStructuralFeature) {
		if (eStructuralFeature instanceof EAttribute) {
			return false;
		}
		final EReference eref = (EReference) eStructuralFeature;
		return isMapEntry(eref.getEReferenceType());
	}

	/** Same method only for an eclass */
	public static boolean isMapEntry(EClass eclass) {
		return eclass != null && eclass.getInstanceClass() != null
				&& Map.Entry.class.isAssignableFrom(eclass.getInstanceClass())
				&& eclass.getEStructuralFeatures().size() == 2
				&& eclass.getEStructuralFeature("key") != null
				&& eclass.getEStructuralFeature("value") != null;
	}

	public static boolean isEStoreList(Object o) {
		if (o == null) {
			return false;
		}
		if (o instanceof EStoreEList<?>) {
			return true;
		}
		if (o instanceof EStoreEObjectImpl.BasicEStoreEList<?>) {
			return true;
		}
		return false;
	}

	/** The nsprefix, eclass separator */
	// private static final String NSPREFIX_ECLASS_SEPARATOR = ".";
	/** Returns the name of the entity used for this feature map entry */
	public static String getEntityName(EntityNameStrategy nameStrategy, EStructuralFeature feature) {
		assert (FeatureMapUtil.isFeatureMap(feature));
		return nameStrategy.toEntityName(feature.getEContainingClass()) + "_" + feature.getName();
	}

	/** Returns a loggable string for a efeature */
	public static String toString(EStructuralFeature efeature) {
		if (efeature == null) {
			return "NULL"; // possibly throw error
		}
		return efeature.getEContainingClass().getName() + "/" + efeature.getName();
	}

	/**
	 * Translates an ECLass to a string representation public static String getEClassURI(EClass
	 * eclass, String qualify) { if (eclass == EOBJECT_ECLASS) { return EOBJECT_ECLASS_URI; } if
	 * (qualify == null || qualify.compareTo(PersistenceOptions.QUALIFY_ENTITY_NAME_NO) == 0) { return
	 * eclass.getName(); } else if (qualify.compareTo(PersistenceOptions.QUALIFY_ENTITY_NAME_NSPREFIX)
	 * == 0) { return eclass.getEPackage().getNsPrefix() + "." + eclass.getName(); } throw new
	 * StoreException("Qualify type " + qualify + " unknown"); } /** Returns an estructuralfeature on
	 * the basis of the name of the eclass and the name of the feature itself. public static
	 * EStructuralFeature getEStructuralFeature(String eclassURI, String featureName, EPackage[]
	 * epackages) { EClass eclass = getEClassFromURI(eclassURI, epackages); if (eclass == null) return
	 * null; return eclass.getEStructuralFeature(featureName); } /* /** Translates an eclass uri back
	 * to an eclass / public static EClass getEClassFromURI(String theEClassURI) { final Registry
	 * packageRegistry = Registry.INSTANCE; final EPackage[] epacks = new
	 * EPackage[packageRegistry.size()]; int cnt = 0; for (Iterator it =
	 * packageRegistry.values().iterator(); it.hasNext();) { final EPackage epack = (EPackage)
	 * it.next(); epacks[cnt++] = epack; } return getEClassFromURI(theEClassURI, epacks); } /**
	 * Translates an eclass uri back to an eclass / public static EClass getEClassFromURI(String
	 * theEClassURI, EPackage[] epackages, EClassNameStrategy nameStrategy) { if
	 * (theEClassURI.compareTo(EOBJECT_ECLASS_URI) == 0) { return EcorePackage.eINSTANCE.getEObject();
	 * } String nsPrefix = null; String eClassName = theEClassURI; if
	 * (eClassName.indexOf(NSPREFIX_ECLASS_SEPARATOR) != -1) { nsPrefix = theEClassURI.substring(0,
	 * eClassName.lastIndexOf(NSPREFIX_ECLASS_SEPARATOR)); eClassName = theEClassURI.substring(1 +
	 * eClassName.lastIndexOf(NSPREFIX_ECLASS_SEPARATOR)); } ArrayList eclasses = new ArrayList(); for
	 * (int i = 0; i < epackages.length; i++) { EPackage epack = epackages[i]; if (nsPrefix != null &&
	 * epack.getNsPrefix() != null && epack.getNsPrefix().compareTo(nsPrefix) != 0) { continue; }
	 * EClassifier eclassifier = epack.getEClassifier(eClassName); if (eclassifier instanceof EClass)
	 * { eclasses.add(eclassifier); } } if (eclasses.size() == 1) { return (EClass) eclasses.get(0); }
	 * else if (eclasses.size() == 0) { return null; // throw new StoreException("The uri " +
	 * eclassURI + " can not be translated to an eclass"); } else { StringBuffer eclassList = new
	 * StringBuffer(); for (Iterator it = eclasses.iterator(); it.hasNext();) { EClass eclass =
	 * (EClass) it.next(); eclassList.append(eclass.getEPackage().getNsURI() + "/" +
	 * eclass.getName()); } throw new StoreException("The uri " + eClassName +
	 * " maps to multiple eclasses: " + eclassList.toString()); } /* int lastIndex =
	 * eclassURI.lastIndexOf(PATH_SEPARATOR); if (lastIndex == -1) { throw new
	 * StoreAnnotationsException("The uri: " + eclassURI +
	 * " has an illegal format, it can not parsed to an eclass."); } final String nsuri =
	 * eclassURI.substring(0, lastIndex); final String name = eclassURI.substring(lastIndex + 1);
	 * final EPackage epack = PackageRegistryProvider
	 * .getInstance().getPackageRegistry().getEPackage(nsuri); if (epack == null) { throw new
	 * StoreAnnotationsException("No package found for the nsuri: " + nsuri + " using the eclassURI "
	 * + eclassURI); } final EClass eclass = (EClass)epack.getEClassifier(name); if (eclass == null) {
	 * throw new StoreAnnotationsException("The nsuri " + nsuri + " and eclassname: " + name +
	 * " does not resolve to an EClass"); } return eclass; / }
	 */
	/** Sends out a notification of an elist load */
	public static void dispatchEListLoadNotification(final EObject notifier,
			final EList<? extends EObject> elist, final EStructuralFeature feature) {
		notifier.eNotify(new NotificationImpl(Constants.ELIST_LOAD_NOTIFICATION, null, elist) {
			@Override
			public Object getNotifier() {
				return notifier;
			}

			@Override
			public Object getFeature() {
				return feature;
			}

			@Override
			public int getFeatureID(Class<?> expectedClass) {
				return feature.getFeatureID();
			}
		});
	}

	/**
	 * Returns a unique string for an eclass
	 */
	public static String eClassToString(EClass eClass) {
		final String nsuri = eClass.getEPackage().getNsURI();
		final String eclassName = eClass.getName();

		return nsuri + PATH_SEPARATOR + eclassName;
	}

	/**
	 * Returns the string which is used to store the unique identification of this structuralfeature
	 * in the db
	 */
	public static String structuralFeatureToString(EStructuralFeature structuralFeature) {
		// the unique id will consist of three part: 1) package NSURI, 2) EClass
		// Name, 3) StructuralFeature Name
		final String nsuri = structuralFeature.getEContainingClass().getEPackage().getNsURI();
		final String eclassName = structuralFeature.getEContainingClass().getName();
		final String featureName = structuralFeature.getName();

		return nsuri + PATH_SEPARATOR + eclassName + PATH_SEPARATOR + featureName;
	}

	/** Returns true if the passed feature is a wildcard feature */
	public static boolean isWildCard(EStructuralFeature feature) {
		EAnnotation eAnnotation = feature
				.getEAnnotation("http:///org/eclipse/emf/ecore/util/ExtendedMetaData");
		if (eAnnotation == null) {
			return false;
		}
		final String kind = eAnnotation.getDetails().get("kind");
		final String wildcards = eAnnotation.getDetails().get("wildcards");
		return kind != null && kind.compareTo("elementWildcard") == 0 && wildcards != null;
		/*
		 * // todo optimise this with a cache final EAnnotation eannotation = feature
		 * .getEAnnotation("http:///org/eclipse/emf/ecore/util/ExtendedMetaData" ); if (eannotation !=
		 * null) { final String kind = (String)eannotation.getDetails().get("kind"); final String
		 * wildcards = (String)eannotation.getDetails().get("wildcards"); if (wildcards != null && kind
		 * != null && "elementWildcard".compareTo(kind) == 0) { return true; } } return false;
		 */
	}

	/** Returns true if the passed feature is a wildcard feature */
	public static boolean isMixed(EStructuralFeature feature) {
		EAnnotation eAnnotation = feature
				.getEAnnotation("http:///org/eclipse/emf/ecore/util/ExtendedMetaData");
		if (eAnnotation == null) {
			return false;
		}
		final String kind = eAnnotation.getDetails().get("kind");
		final String name = eAnnotation.getDetails().get("name");
		return kind != null && kind.compareTo("elementWildcard") == 0 && name != null
				&& name.compareTo(":mixed") == 0;
		/*
		 * // todo optimise this with a cache final EAnnotation eannotation = feature
		 * .getEAnnotation("http:///org/eclipse/emf/ecore/util/ExtendedMetaData" ); if (eannotation !=
		 * null) { final String kind = (String)eannotation.getDetails().get("kind"); final String
		 * wildcards = (String)eannotation.getDetails().get("wildcards"); if (wildcards != null && kind
		 * != null && "elementWildcard".compareTo(kind) == 0) { return true; } } return false;
		 */
	}

	public static EStructuralFeature stringToStructureFeature(
			String strid) {
		return stringToStructureFeature(PackageRegistryProvider.getInstance().getPackageRegistry(),
				strid);
	}

	/** Translates a string to a structural feature */
	public static EStructuralFeature stringToStructureFeature(EPackage.Registry packageRegistry,
			String strid) {
		// this method expects a dbid consisting of three parts separated by /
		int lastIndex = strid.lastIndexOf(PATH_SEPARATOR);
		int beforeLastIndex = strid.lastIndexOf(PATH_SEPARATOR, lastIndex - 1);

		if (lastIndex == -1 || beforeLastIndex == -1) {
			throw new TeneoException(
					"The database id stored for a structuralfeature used in a featuremap entry is invalid, dbid: "
							+ strid);
		}

		final String nsuri = strid.substring(0, beforeLastIndex);
		final String eclassName = strid.substring(beforeLastIndex + 1, lastIndex);
		final String featureName = strid.substring(lastIndex + 1);

		final EPackage epack = packageRegistry.getEPackage(nsuri);
		if (epack == null) {
			throw new TeneoException("The dbid " + strid + " and nsuri: " + nsuri
					+ " does not resolve to an epackage");
		}

		final EClass eclass = (EClass) epack.getEClassifier(eclassName);
		if (eclass == null) {
			throw new TeneoException("The dbid " + strid + " and eclassname: " + eclassName
					+ " does not resolve to an eclass");
		}

		final EStructuralFeature structFeature = eclass.getEStructuralFeature(featureName);
		if (structFeature == null) {
			throw new TeneoException("The dbid " + strid + " and featurename: " + featureName
					+ " does not resolve to a structural feature");
		}

		return structFeature;
	}

	/** Translates a string to a eclass */
	public static EClass stringToEClass(EPackage.Registry registry, String strid) {
		int lastIndex = strid.lastIndexOf(PATH_SEPARATOR);

		if (lastIndex == -1) {
			throw new TeneoException("Invalid eclass string " + strid);
		}

		final String nsuri = strid.substring(0, lastIndex);
		final String eclassName = strid.substring(lastIndex + 1);

		final EPackage epack = registry.getEPackage(nsuri);
		if (epack == null) {
			throw new TeneoException("The strid " + strid + " and nsuri: " + nsuri
					+ " does not resolve to an epackage");
		}

		final EClass eClass = (EClass) epack.getEClassifier(eclassName);
		if (eClass == null) {
			throw new TeneoException("The strid " + strid + " and eclassname: " + eclassName
					+ " does not resolve to an eclass");
		}
		return eClass;
	}

	/**
	 * Returns the string which is used to store the unique identification of the passed edatatype
	 */
	public static String edatatypeToString(EDataType edatatype) {
		// the unique id will consist of two part: 1) package NSURI, 2) Name
		final String nsuri = edatatype.getEPackage().getNsURI();
		final String name = edatatype.getName();
		return nsuri + PATH_SEPARATOR + name;
	}

	/** Translates a string to an edatatype */
	public static EDataType stringToEDataType(String strid) {
		// this method expects a dbid consisting of three parts separated by /
		int lastIndex = strid.lastIndexOf(PATH_SEPARATOR);

		if (lastIndex == -1) {
			throw new TeneoException("The database id stored for a datatype is invalid, dbid: " + strid);
		}

		final String nsuri = strid.substring(0, lastIndex);
		final String name = strid.substring(lastIndex + 1);

		final EPackage epack = PackageRegistryProvider.getInstance().getPackageRegistry()
				.getEPackage(nsuri);
		if (epack == null) {
			throw new TeneoException("The dbid " + strid + " and nsuri: " + nsuri
					+ " does not resolve to an epackage");
		}

		final EDataType edatatype = (EDataType) epack.getEClassifier(name);
		if (edatatype == null) {
			throw new TeneoException("The dbid " + strid + " and eclassname: " + name
					+ " does not resolve to an EDataType");
		}
		return edatatype;
	}

	/**
	 * Based on the eobject and the fieldname returns the structural feature. Caching should be added
	 * here.
	 */
	public static EStructuralFeature getEStructuralFeature(EObject emfObj, String fieldName) {
		return getEStructuralFeature(emfObj.eClass(), fieldName);
	}

	/**
	 * Returns all the direct content based on the eclass of the object, null content is not returned
	 */
	public static Object[] getObjectContent(EObject eo) {
		final ArrayList<Object> result = new ArrayList<Object>();
		for (EStructuralFeature estruct : eo.eClass().getEAllStructuralFeatures()) {
			final Object val = eo.eGet(estruct);
			if (val != null) {
				result.add(val);
			}
		}
		return result.toArray(new Object[result.size()]);
	}

	/**
	 * Based on the eclass and the fieldname returns the structural feature. Caching should be added
	 * here.
	 */
	public static EStructuralFeature getEStructuralFeature(EClass eclass, String fieldName) {
		EStructuralFeature reserve = null;
		for (EStructuralFeature estruct : eclass.getEAllStructuralFeatures()) {
			String name = estruct.getName();
			if (name.compareTo(fieldName) == 0) {
				return estruct;
			}
			if (name.compareToIgnoreCase(fieldName) == 0) {
				reserve = estruct; // use this if all else fails
			} else if ((name + "_").compareToIgnoreCase(fieldName) == 0) { // reserved
				// word
				reserve = estruct; // use this if all else fails
			}
		}

		if (reserve != null) {
			return reserve;
		}

		return null;

		// throw new StoreException("The fieldname " + fieldName + " is not
		// present as a ereference type in the class: "
		// + eclass.getName());
	}

	/**
	 * Returns a list of all ereferences with many of the emfObj. TODO add caching
	 */
	public static EReference[] getManyGroupEReferences(EObject emfObj) {
		final ArrayList<EReference> manyRefs = new ArrayList<EReference>();
		for (EStructuralFeature estruct : emfObj.eClass().getEAllStructuralFeatures()) {
			if (estruct instanceof EReference
					&& (((EReference) estruct).isMany() || isGroupFeature(estruct) || isWildCard(estruct))) {
				manyRefs.add((EReference) estruct);
			}
		}
		return manyRefs.toArray(new EReference[manyRefs.size()]);
	}

	/** Checks if a structural feature is a group feature */
	public static boolean isGroupFeature(EStructuralFeature estruct) {
		final EAnnotation annotation = estruct.getEAnnotation(ANNOTATION_SOURCE);

		if (annotation == null) {
			return false;
		}

		final EMap<String, String> map = annotation.getDetails();
		for (String key : map.keySet()) {
			final String value = map.get(key);
			if ("kind".compareTo(key) == 0 && "group".compareTo(value) == 0) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Returns the name used for the group feature, if the feature is not a group then null is
	 * returned
	 */
	public static String getGroupName(EStructuralFeature estruct) {
		final EAnnotation annotation = estruct.getEAnnotation(ANNOTATION_SOURCE);

		if (annotation == null) {
			return null;
		}

		boolean isGroup = false;
		String name = null;

		final EMap<String, String> map = annotation.getDetails();
		for (String key : map.keySet()) {
			final String value = map.get(key);
			if ("kind".compareTo(key) == 0 && "group".compareTo(value) == 0) {
				// can not return here because we also need to get the name
				isGroup = true;
			}
			if ("name".compareTo(key) == 0) {
				name = value;
			}
		}

		if (isGroup) {
			assert (name != null);
			return name;
		}

		return null;
	}

	/** Checks if a feature is an element of the passed group */
	public static boolean isElementOfGroup(EStructuralFeature estruct, EStructuralFeature groupFeature) {
		final EStructuralFeature ef = ExtendedMetaData.INSTANCE.getGroup(estruct);
		return ef == groupFeature;
		/*
		 * final EAnnotation annotation = estruct.getEAnnotation(ANNOTATION_SOURCE); if (annotation ==
		 * null) return false; final EMap map = annotation.getDetails(); final Iterator keys =
		 * map.keySet().iterator(); while (keys.hasNext()) { final String key = (String)keys.next();
		 * final String value = (String)map.get(key); // for a choice the groupName in the group element
		 * starts with # for substitution it // doesn't therefore the endsWith if
		 * ("group".compareTo(key) == 0 && value != null && groupName.endsWith(value)) { return true; }
		 * } return false;
		 */
	}

	/** Checks if a feature is an element of a group */
	public static boolean isElementOfAGroup(EStructuralFeature estruct) {
		final EStructuralFeature ef = ExtendedMetaData.INSTANCE.getGroup(estruct);
		return ef != null && ef != estruct;
		/*
		 * // group elements are always transient if (!estruct.isTransient()) return false; final
		 * EAnnotation annotation = estruct.getEAnnotation(ANNOTATION_SOURCE); if (annotation == null)
		 * return false; final EMap map = annotation.getDetails(); final Iterator keys =
		 * map.keySet().iterator(); while (keys.hasNext()) { final String key = (String)keys.next();
		 * final String value = (String)map.get(key); if ("group".compareTo(key) == 0 && value != null)
		 * return true; } return false;
		 */
	}

	/**
	 * Returns the list of estructuralfeatures which belong to a certain feature map entry
	 */
	public static List<EStructuralFeature> getFeaturesOfGroup(EAttribute eattr) {
		final ArrayList<EStructuralFeature> result = new ArrayList<EStructuralFeature>();
		for (EStructuralFeature efeature : eattr.getEContainingClass().getEStructuralFeatures()) {
			if (StoreUtil.isElementOfGroup(efeature, eattr)) {
				result.add(efeature);
			}
		}
		return result;
	}

	/**
	 * Returns an array of epackages on the basis of a string with comma separated epackage ns uris
	 */
	public static EPackage[] getEPackages(String nsuris) {
		final String[] epacknsuris = nsuris.split(",");
		final EPackage[] epacks = new EPackage[epacknsuris.length];
		for (int i = 0; i < epacknsuris.length; i++) {
			final EPackage epack = PackageRegistryProvider.getInstance().getPackageRegistry()
					.getEPackage(epacknsuris[i]);
			if (epack == null) {
				throw new TeneoException("EPackage with nsuri: " + epacknsuris[i] + " can not be found,");
			}
			epacks[i] = epack;
		}
		return epacks;
	}

	/** Sets a field and wraps the exceptions */
	public static void setField(Field field, Object obj, Object value) {
		try {
			field.set(obj, value);
		} catch (IllegalAccessException e) {
			throw new TeneoException("IllegalAccessException " + obj.getClass().getName() + " field; "
					+ field.getName());
		}
	}

	/**
	 * Determines the list of available mapping files on the basis of the suffix
	 */
	public static String[] getFileList(String fileName, String additionalLocation) {
		final ArrayList<String> result = new ArrayList<String>();
		if (log.isDebugEnabled()) {
			log.debug(">>>> Building or descriptor file List");
		}

		if (additionalLocation != null) {
			result.add(additionalLocation);
		}

		final String[] packagelist = buildPackagelist();
		for (final String packagePath : packagelist) {
			final String filePath = packagePath + fileName;

			if (log.isDebugEnabled()) {
				log.debug("Try path: " + filePath);
			}

			final URL url = StoreUtil.class.getResource(filePath);
			if (url != null) // file exists
			{
				if (log.isDebugEnabled()) {
					log.debug("!!Found!!");
				}
				result.add(filePath);
			}
		}
		return result.toArray(new String[result.size()]);
	}

	/** Build list of possible or descriptor file paths */
	private static String[] buildPackagelist() {
		// walk through all the classnames
		final ArrayList<String> newPackagePathList = new ArrayList<String>();
		newPackagePathList.add(File.pathSeparator); // add the root package

		// TODO: move this to the EModelResolver!
		for (Class<?> clazz : EModelResolver.instance().getAllClassesAndInterfaces()) {
			final String className = clazz.getName();
			final int classNameIndex = className.lastIndexOf(PACKAGE_SEPARATOR);
			final String trunkClassName = className.substring(0, classNameIndex);
			final String startPath = PATH_SEPARATOR
					+ trunkClassName.replace(PACKAGE_SEPARATOR, PATH_SEPARATOR);

			buildPackagePathFromClassName(startPath, newPackagePathList);
		}
		return newPackagePathList.toArray(new String[newPackagePathList.size()]);
	}

	/** Take care of one class */
	private static void buildPackagePathFromClassName(String path,
			ArrayList<String> newPackagePathList) {
		if (newPackagePathList.contains(path + PATH_SEPARATOR)) {
			return;
		}
		newPackagePathList.add(path + PATH_SEPARATOR);

		final int sepIndex = path.lastIndexOf(PATH_SEPARATOR);
		if (sepIndex == -1) {
			return;
		}
		buildPackagePathFromClassName(path.substring(0, sepIndex), newPackagePathList);
	}

	/** Copies a file */
	public static void copyFile(File src, File dst) {
		try {
			if (log.isDebugEnabled()) {
				log.debug("Copy file from " + src.getAbsolutePath() + " to " + dst.getAbsolutePath());
			}
			InputStream in = new FileInputStream(src);
			OutputStream out = new FileOutputStream(dst);

			// Transfer bytes from in to out
			byte[] buf = new byte[1024];
			int len;
			while ((len = in.read(buf)) > 0) {
				out.write(buf, 0, len);
			}
			in.close();
			out.close();
		} catch (Exception e) {
			throw new TeneoException("Exception while copying from/to " + src.getAbsolutePath() + "/"
					+ dst.getAbsolutePath(), e);
		}
	}

	/**
	 * Checks if an object has a HibernatePersistentStoreAdapter and if it doesn't creates one and
	 * returns it.
	 */
	public static PersistentStoreAdapter getPersistentStoreAdapter(EObject eObject) {
		for (Adapter adapter : eObject.eAdapters()) {
			if (PersistentStoreAdapter.class.isAssignableFrom(adapter.getClass())) {
				return (PersistentStoreAdapter) adapter;
			}
		}
		final PersistentStoreAdapter adapter = new PersistentStoreAdapter();
		eObject.eAdapters().add(adapter);
		return adapter;
	}

}