/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package serviceBuilder.service.persistence;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.io.Serializable;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;
import org.osgi.util.tracker.ServiceTracker;

import serviceBuilder.model.assetlistentry;

/**
 * The persistence utility for the assetlistentry service. This utility wraps <code>serviceBuilder.service.persistence.impl.assetlistentryPersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see assetlistentryPersistence
 * @generated
 */
public class assetlistentryUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#clearCache()
	 */
	public static void clearCache() {
		getPersistence().clearCache();
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#clearCache(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static void clearCache(assetlistentry assetlistentry) {
		getPersistence().clearCache(assetlistentry);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#countWithDynamicQuery(DynamicQuery)
	 */
	public static long countWithDynamicQuery(DynamicQuery dynamicQuery) {
		return getPersistence().countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#fetchByPrimaryKeys(Set)
	 */
	public static Map<Serializable, assetlistentry> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<assetlistentry> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {

		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<assetlistentry> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<assetlistentry> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<assetlistentry> orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static assetlistentry update(assetlistentry assetlistentry) {
		return getPersistence().update(assetlistentry);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static assetlistentry update(
		assetlistentry assetlistentry, ServiceContext serviceContext) {

		return getPersistence().update(assetlistentry, serviceContext);
	}

	/**
	 * Returns all the assetlistentries where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching assetlistentries
	 */
	public static List<assetlistentry> findByUuid(String uuid) {
		return getPersistence().findByUuid(uuid);
	}

	/**
	 * Returns a range of all the assetlistentries where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>assetlistentryModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of assetlistentries
	 * @param end the upper bound of the range of assetlistentries (not inclusive)
	 * @return the range of matching assetlistentries
	 */
	public static List<assetlistentry> findByUuid(
		String uuid, int start, int end) {

		return getPersistence().findByUuid(uuid, start, end);
	}

	/**
	 * Returns an ordered range of all the assetlistentries where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>assetlistentryModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of assetlistentries
	 * @param end the upper bound of the range of assetlistentries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching assetlistentries
	 */
	public static List<assetlistentry> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<assetlistentry> orderByComparator) {

		return getPersistence().findByUuid(uuid, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the assetlistentries where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>assetlistentryModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of assetlistentries
	 * @param end the upper bound of the range of assetlistentries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching assetlistentries
	 */
	public static List<assetlistentry> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<assetlistentry> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByUuid(
			uuid, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first assetlistentry in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching assetlistentry
	 * @throws NoSuchassetlistentryException if a matching assetlistentry could not be found
	 */
	public static assetlistentry findByUuid_First(
			String uuid, OrderByComparator<assetlistentry> orderByComparator)
		throws serviceBuilder.exception.NoSuchassetlistentryException {

		return getPersistence().findByUuid_First(uuid, orderByComparator);
	}

	/**
	 * Returns the first assetlistentry in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching assetlistentry, or <code>null</code> if a matching assetlistentry could not be found
	 */
	public static assetlistentry fetchByUuid_First(
		String uuid, OrderByComparator<assetlistentry> orderByComparator) {

		return getPersistence().fetchByUuid_First(uuid, orderByComparator);
	}

	/**
	 * Returns the last assetlistentry in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching assetlistentry
	 * @throws NoSuchassetlistentryException if a matching assetlistentry could not be found
	 */
	public static assetlistentry findByUuid_Last(
			String uuid, OrderByComparator<assetlistentry> orderByComparator)
		throws serviceBuilder.exception.NoSuchassetlistentryException {

		return getPersistence().findByUuid_Last(uuid, orderByComparator);
	}

	/**
	 * Returns the last assetlistentry in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching assetlistentry, or <code>null</code> if a matching assetlistentry could not be found
	 */
	public static assetlistentry fetchByUuid_Last(
		String uuid, OrderByComparator<assetlistentry> orderByComparator) {

		return getPersistence().fetchByUuid_Last(uuid, orderByComparator);
	}

	/**
	 * Returns the assetlistentries before and after the current assetlistentry in the ordered set where uuid = &#63;.
	 *
	 * @param assetlistentryid the primary key of the current assetlistentry
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next assetlistentry
	 * @throws NoSuchassetlistentryException if a assetlistentry with the primary key could not be found
	 */
	public static assetlistentry[] findByUuid_PrevAndNext(
			int assetlistentryid, String uuid,
			OrderByComparator<assetlistentry> orderByComparator)
		throws serviceBuilder.exception.NoSuchassetlistentryException {

		return getPersistence().findByUuid_PrevAndNext(
			assetlistentryid, uuid, orderByComparator);
	}

	/**
	 * Removes all the assetlistentries where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	public static void removeByUuid(String uuid) {
		getPersistence().removeByUuid(uuid);
	}

	/**
	 * Returns the number of assetlistentries where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching assetlistentries
	 */
	public static int countByUuid(String uuid) {
		return getPersistence().countByUuid(uuid);
	}

	/**
	 * Caches the assetlistentry in the entity cache if it is enabled.
	 *
	 * @param assetlistentry the assetlistentry
	 */
	public static void cacheResult(assetlistentry assetlistentry) {
		getPersistence().cacheResult(assetlistentry);
	}

	/**
	 * Caches the assetlistentries in the entity cache if it is enabled.
	 *
	 * @param assetlistentries the assetlistentries
	 */
	public static void cacheResult(List<assetlistentry> assetlistentries) {
		getPersistence().cacheResult(assetlistentries);
	}

	/**
	 * Creates a new assetlistentry with the primary key. Does not add the assetlistentry to the database.
	 *
	 * @param assetlistentryid the primary key for the new assetlistentry
	 * @return the new assetlistentry
	 */
	public static assetlistentry create(int assetlistentryid) {
		return getPersistence().create(assetlistentryid);
	}

	/**
	 * Removes the assetlistentry with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param assetlistentryid the primary key of the assetlistentry
	 * @return the assetlistentry that was removed
	 * @throws NoSuchassetlistentryException if a assetlistentry with the primary key could not be found
	 */
	public static assetlistentry remove(int assetlistentryid)
		throws serviceBuilder.exception.NoSuchassetlistentryException {

		return getPersistence().remove(assetlistentryid);
	}

	public static assetlistentry updateImpl(assetlistentry assetlistentry) {
		return getPersistence().updateImpl(assetlistentry);
	}

	/**
	 * Returns the assetlistentry with the primary key or throws a <code>NoSuchassetlistentryException</code> if it could not be found.
	 *
	 * @param assetlistentryid the primary key of the assetlistentry
	 * @return the assetlistentry
	 * @throws NoSuchassetlistentryException if a assetlistentry with the primary key could not be found
	 */
	public static assetlistentry findByPrimaryKey(int assetlistentryid)
		throws serviceBuilder.exception.NoSuchassetlistentryException {

		return getPersistence().findByPrimaryKey(assetlistentryid);
	}

	/**
	 * Returns the assetlistentry with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param assetlistentryid the primary key of the assetlistentry
	 * @return the assetlistentry, or <code>null</code> if a assetlistentry with the primary key could not be found
	 */
	public static assetlistentry fetchByPrimaryKey(int assetlistentryid) {
		return getPersistence().fetchByPrimaryKey(assetlistentryid);
	}

	/**
	 * Returns all the assetlistentries.
	 *
	 * @return the assetlistentries
	 */
	public static List<assetlistentry> findAll() {
		return getPersistence().findAll();
	}

	/**
	 * Returns a range of all the assetlistentries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>assetlistentryModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of assetlistentries
	 * @param end the upper bound of the range of assetlistentries (not inclusive)
	 * @return the range of assetlistentries
	 */
	public static List<assetlistentry> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	 * Returns an ordered range of all the assetlistentries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>assetlistentryModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of assetlistentries
	 * @param end the upper bound of the range of assetlistentries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of assetlistentries
	 */
	public static List<assetlistentry> findAll(
		int start, int end,
		OrderByComparator<assetlistentry> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the assetlistentries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>assetlistentryModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of assetlistentries
	 * @param end the upper bound of the range of assetlistentries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of assetlistentries
	 */
	public static List<assetlistentry> findAll(
		int start, int end, OrderByComparator<assetlistentry> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Removes all the assetlistentries from the database.
	 */
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	 * Returns the number of assetlistentries.
	 *
	 * @return the number of assetlistentries
	 */
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static assetlistentryPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker
		<assetlistentryPersistence, assetlistentryPersistence> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(
			assetlistentryPersistence.class);

		ServiceTracker<assetlistentryPersistence, assetlistentryPersistence>
			serviceTracker =
				new ServiceTracker
					<assetlistentryPersistence, assetlistentryPersistence>(
						bundle.getBundleContext(),
						assetlistentryPersistence.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}

}