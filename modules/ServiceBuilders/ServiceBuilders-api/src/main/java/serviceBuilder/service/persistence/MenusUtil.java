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

import serviceBuilder.model.Menus;

/**
 * The persistence utility for the menus service. This utility wraps <code>serviceBuilder.service.persistence.impl.MenusPersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see MenusPersistence
 * @generated
 */
public class MenusUtil {

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
	public static void clearCache(Menus menus) {
		getPersistence().clearCache(menus);
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
	public static Map<Serializable, Menus> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<Menus> findWithDynamicQuery(DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<Menus> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<Menus> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<Menus> orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static Menus update(Menus menus) {
		return getPersistence().update(menus);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static Menus update(Menus menus, ServiceContext serviceContext) {
		return getPersistence().update(menus, serviceContext);
	}

	/**
	 * Returns all the menuses where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching menuses
	 */
	public static List<Menus> findByUuid(String uuid) {
		return getPersistence().findByUuid(uuid);
	}

	/**
	 * Returns a range of all the menuses where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>MenusModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of menuses
	 * @param end the upper bound of the range of menuses (not inclusive)
	 * @return the range of matching menuses
	 */
	public static List<Menus> findByUuid(String uuid, int start, int end) {
		return getPersistence().findByUuid(uuid, start, end);
	}

	/**
	 * Returns an ordered range of all the menuses where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>MenusModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of menuses
	 * @param end the upper bound of the range of menuses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching menuses
	 */
	public static List<Menus> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<Menus> orderByComparator) {

		return getPersistence().findByUuid(uuid, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the menuses where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>MenusModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of menuses
	 * @param end the upper bound of the range of menuses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching menuses
	 */
	public static List<Menus> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<Menus> orderByComparator, boolean useFinderCache) {

		return getPersistence().findByUuid(
			uuid, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first menus in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching menus
	 * @throws NoSuchMenusException if a matching menus could not be found
	 */
	public static Menus findByUuid_First(
			String uuid, OrderByComparator<Menus> orderByComparator)
		throws serviceBuilder.exception.NoSuchMenusException {

		return getPersistence().findByUuid_First(uuid, orderByComparator);
	}

	/**
	 * Returns the first menus in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching menus, or <code>null</code> if a matching menus could not be found
	 */
	public static Menus fetchByUuid_First(
		String uuid, OrderByComparator<Menus> orderByComparator) {

		return getPersistence().fetchByUuid_First(uuid, orderByComparator);
	}

	/**
	 * Returns the last menus in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching menus
	 * @throws NoSuchMenusException if a matching menus could not be found
	 */
	public static Menus findByUuid_Last(
			String uuid, OrderByComparator<Menus> orderByComparator)
		throws serviceBuilder.exception.NoSuchMenusException {

		return getPersistence().findByUuid_Last(uuid, orderByComparator);
	}

	/**
	 * Returns the last menus in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching menus, or <code>null</code> if a matching menus could not be found
	 */
	public static Menus fetchByUuid_Last(
		String uuid, OrderByComparator<Menus> orderByComparator) {

		return getPersistence().fetchByUuid_Last(uuid, orderByComparator);
	}

	/**
	 * Returns the menuses before and after the current menus in the ordered set where uuid = &#63;.
	 *
	 * @param id the primary key of the current menus
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next menus
	 * @throws NoSuchMenusException if a menus with the primary key could not be found
	 */
	public static Menus[] findByUuid_PrevAndNext(
			String id, String uuid, OrderByComparator<Menus> orderByComparator)
		throws serviceBuilder.exception.NoSuchMenusException {

		return getPersistence().findByUuid_PrevAndNext(
			id, uuid, orderByComparator);
	}

	/**
	 * Removes all the menuses where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	public static void removeByUuid(String uuid) {
		getPersistence().removeByUuid(uuid);
	}

	/**
	 * Returns the number of menuses where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching menuses
	 */
	public static int countByUuid(String uuid) {
		return getPersistence().countByUuid(uuid);
	}

	/**
	 * Returns all the menuses where code = &#63;.
	 *
	 * @param code the code
	 * @return the matching menuses
	 */
	public static List<Menus> findByCode(String code) {
		return getPersistence().findByCode(code);
	}

	/**
	 * Returns a range of all the menuses where code = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>MenusModelImpl</code>.
	 * </p>
	 *
	 * @param code the code
	 * @param start the lower bound of the range of menuses
	 * @param end the upper bound of the range of menuses (not inclusive)
	 * @return the range of matching menuses
	 */
	public static List<Menus> findByCode(String code, int start, int end) {
		return getPersistence().findByCode(code, start, end);
	}

	/**
	 * Returns an ordered range of all the menuses where code = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>MenusModelImpl</code>.
	 * </p>
	 *
	 * @param code the code
	 * @param start the lower bound of the range of menuses
	 * @param end the upper bound of the range of menuses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching menuses
	 */
	public static List<Menus> findByCode(
		String code, int start, int end,
		OrderByComparator<Menus> orderByComparator) {

		return getPersistence().findByCode(code, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the menuses where code = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>MenusModelImpl</code>.
	 * </p>
	 *
	 * @param code the code
	 * @param start the lower bound of the range of menuses
	 * @param end the upper bound of the range of menuses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching menuses
	 */
	public static List<Menus> findByCode(
		String code, int start, int end,
		OrderByComparator<Menus> orderByComparator, boolean useFinderCache) {

		return getPersistence().findByCode(
			code, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first menus in the ordered set where code = &#63;.
	 *
	 * @param code the code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching menus
	 * @throws NoSuchMenusException if a matching menus could not be found
	 */
	public static Menus findByCode_First(
			String code, OrderByComparator<Menus> orderByComparator)
		throws serviceBuilder.exception.NoSuchMenusException {

		return getPersistence().findByCode_First(code, orderByComparator);
	}

	/**
	 * Returns the first menus in the ordered set where code = &#63;.
	 *
	 * @param code the code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching menus, or <code>null</code> if a matching menus could not be found
	 */
	public static Menus fetchByCode_First(
		String code, OrderByComparator<Menus> orderByComparator) {

		return getPersistence().fetchByCode_First(code, orderByComparator);
	}

	/**
	 * Returns the last menus in the ordered set where code = &#63;.
	 *
	 * @param code the code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching menus
	 * @throws NoSuchMenusException if a matching menus could not be found
	 */
	public static Menus findByCode_Last(
			String code, OrderByComparator<Menus> orderByComparator)
		throws serviceBuilder.exception.NoSuchMenusException {

		return getPersistence().findByCode_Last(code, orderByComparator);
	}

	/**
	 * Returns the last menus in the ordered set where code = &#63;.
	 *
	 * @param code the code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching menus, or <code>null</code> if a matching menus could not be found
	 */
	public static Menus fetchByCode_Last(
		String code, OrderByComparator<Menus> orderByComparator) {

		return getPersistence().fetchByCode_Last(code, orderByComparator);
	}

	/**
	 * Returns the menuses before and after the current menus in the ordered set where code = &#63;.
	 *
	 * @param id the primary key of the current menus
	 * @param code the code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next menus
	 * @throws NoSuchMenusException if a menus with the primary key could not be found
	 */
	public static Menus[] findByCode_PrevAndNext(
			String id, String code, OrderByComparator<Menus> orderByComparator)
		throws serviceBuilder.exception.NoSuchMenusException {

		return getPersistence().findByCode_PrevAndNext(
			id, code, orderByComparator);
	}

	/**
	 * Removes all the menuses where code = &#63; from the database.
	 *
	 * @param code the code
	 */
	public static void removeByCode(String code) {
		getPersistence().removeByCode(code);
	}

	/**
	 * Returns the number of menuses where code = &#63;.
	 *
	 * @param code the code
	 * @return the number of matching menuses
	 */
	public static int countByCode(String code) {
		return getPersistence().countByCode(code);
	}

	/**
	 * Caches the menus in the entity cache if it is enabled.
	 *
	 * @param menus the menus
	 */
	public static void cacheResult(Menus menus) {
		getPersistence().cacheResult(menus);
	}

	/**
	 * Caches the menuses in the entity cache if it is enabled.
	 *
	 * @param menuses the menuses
	 */
	public static void cacheResult(List<Menus> menuses) {
		getPersistence().cacheResult(menuses);
	}

	/**
	 * Creates a new menus with the primary key. Does not add the menus to the database.
	 *
	 * @param id the primary key for the new menus
	 * @return the new menus
	 */
	public static Menus create(String id) {
		return getPersistence().create(id);
	}

	/**
	 * Removes the menus with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param id the primary key of the menus
	 * @return the menus that was removed
	 * @throws NoSuchMenusException if a menus with the primary key could not be found
	 */
	public static Menus remove(String id)
		throws serviceBuilder.exception.NoSuchMenusException {

		return getPersistence().remove(id);
	}

	public static Menus updateImpl(Menus menus) {
		return getPersistence().updateImpl(menus);
	}

	/**
	 * Returns the menus with the primary key or throws a <code>NoSuchMenusException</code> if it could not be found.
	 *
	 * @param id the primary key of the menus
	 * @return the menus
	 * @throws NoSuchMenusException if a menus with the primary key could not be found
	 */
	public static Menus findByPrimaryKey(String id)
		throws serviceBuilder.exception.NoSuchMenusException {

		return getPersistence().findByPrimaryKey(id);
	}

	/**
	 * Returns the menus with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param id the primary key of the menus
	 * @return the menus, or <code>null</code> if a menus with the primary key could not be found
	 */
	public static Menus fetchByPrimaryKey(String id) {
		return getPersistence().fetchByPrimaryKey(id);
	}

	/**
	 * Returns all the menuses.
	 *
	 * @return the menuses
	 */
	public static List<Menus> findAll() {
		return getPersistence().findAll();
	}

	/**
	 * Returns a range of all the menuses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>MenusModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of menuses
	 * @param end the upper bound of the range of menuses (not inclusive)
	 * @return the range of menuses
	 */
	public static List<Menus> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	 * Returns an ordered range of all the menuses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>MenusModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of menuses
	 * @param end the upper bound of the range of menuses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of menuses
	 */
	public static List<Menus> findAll(
		int start, int end, OrderByComparator<Menus> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the menuses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>MenusModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of menuses
	 * @param end the upper bound of the range of menuses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of menuses
	 */
	public static List<Menus> findAll(
		int start, int end, OrderByComparator<Menus> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Removes all the menuses from the database.
	 */
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	 * Returns the number of menuses.
	 *
	 * @return the number of menuses
	 */
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static MenusPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<MenusPersistence, MenusPersistence>
		_serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(MenusPersistence.class);

		ServiceTracker<MenusPersistence, MenusPersistence> serviceTracker =
			new ServiceTracker<MenusPersistence, MenusPersistence>(
				bundle.getBundleContext(), MenusPersistence.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}

}