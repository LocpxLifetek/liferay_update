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

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import org.osgi.annotation.versioning.ProviderType;

import serviceBuilder.exception.NoSuchassetlistentryException;

import serviceBuilder.model.assetlistentry;

/**
 * The persistence interface for the assetlistentry service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see assetlistentryUtil
 * @generated
 */
@ProviderType
public interface assetlistentryPersistence
	extends BasePersistence<assetlistentry> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link assetlistentryUtil} to access the assetlistentry persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	 * Returns all the assetlistentries where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching assetlistentries
	 */
	public java.util.List<assetlistentry> findByUuid(String uuid);

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
	public java.util.List<assetlistentry> findByUuid(
		String uuid, int start, int end);

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
	public java.util.List<assetlistentry> findByUuid(
		String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<assetlistentry>
			orderByComparator);

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
	public java.util.List<assetlistentry> findByUuid(
		String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<assetlistentry>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first assetlistentry in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching assetlistentry
	 * @throws NoSuchassetlistentryException if a matching assetlistentry could not be found
	 */
	public assetlistentry findByUuid_First(
			String uuid,
			com.liferay.portal.kernel.util.OrderByComparator<assetlistentry>
				orderByComparator)
		throws NoSuchassetlistentryException;

	/**
	 * Returns the first assetlistentry in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching assetlistentry, or <code>null</code> if a matching assetlistentry could not be found
	 */
	public assetlistentry fetchByUuid_First(
		String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<assetlistentry>
			orderByComparator);

	/**
	 * Returns the last assetlistentry in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching assetlistentry
	 * @throws NoSuchassetlistentryException if a matching assetlistentry could not be found
	 */
	public assetlistentry findByUuid_Last(
			String uuid,
			com.liferay.portal.kernel.util.OrderByComparator<assetlistentry>
				orderByComparator)
		throws NoSuchassetlistentryException;

	/**
	 * Returns the last assetlistentry in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching assetlistentry, or <code>null</code> if a matching assetlistentry could not be found
	 */
	public assetlistentry fetchByUuid_Last(
		String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<assetlistentry>
			orderByComparator);

	/**
	 * Returns the assetlistentries before and after the current assetlistentry in the ordered set where uuid = &#63;.
	 *
	 * @param assetlistentryid the primary key of the current assetlistentry
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next assetlistentry
	 * @throws NoSuchassetlistentryException if a assetlistentry with the primary key could not be found
	 */
	public assetlistentry[] findByUuid_PrevAndNext(
			int assetlistentryid, String uuid,
			com.liferay.portal.kernel.util.OrderByComparator<assetlistentry>
				orderByComparator)
		throws NoSuchassetlistentryException;

	/**
	 * Removes all the assetlistentries where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	public void removeByUuid(String uuid);

	/**
	 * Returns the number of assetlistentries where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching assetlistentries
	 */
	public int countByUuid(String uuid);

	/**
	 * Caches the assetlistentry in the entity cache if it is enabled.
	 *
	 * @param assetlistentry the assetlistentry
	 */
	public void cacheResult(assetlistentry assetlistentry);

	/**
	 * Caches the assetlistentries in the entity cache if it is enabled.
	 *
	 * @param assetlistentries the assetlistentries
	 */
	public void cacheResult(java.util.List<assetlistentry> assetlistentries);

	/**
	 * Creates a new assetlistentry with the primary key. Does not add the assetlistentry to the database.
	 *
	 * @param assetlistentryid the primary key for the new assetlistentry
	 * @return the new assetlistentry
	 */
	public assetlistentry create(int assetlistentryid);

	/**
	 * Removes the assetlistentry with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param assetlistentryid the primary key of the assetlistentry
	 * @return the assetlistentry that was removed
	 * @throws NoSuchassetlistentryException if a assetlistentry with the primary key could not be found
	 */
	public assetlistentry remove(int assetlistentryid)
		throws NoSuchassetlistentryException;

	public assetlistentry updateImpl(assetlistentry assetlistentry);

	/**
	 * Returns the assetlistentry with the primary key or throws a <code>NoSuchassetlistentryException</code> if it could not be found.
	 *
	 * @param assetlistentryid the primary key of the assetlistentry
	 * @return the assetlistentry
	 * @throws NoSuchassetlistentryException if a assetlistentry with the primary key could not be found
	 */
	public assetlistentry findByPrimaryKey(int assetlistentryid)
		throws NoSuchassetlistentryException;

	/**
	 * Returns the assetlistentry with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param assetlistentryid the primary key of the assetlistentry
	 * @return the assetlistentry, or <code>null</code> if a assetlistentry with the primary key could not be found
	 */
	public assetlistentry fetchByPrimaryKey(int assetlistentryid);

	/**
	 * Returns all the assetlistentries.
	 *
	 * @return the assetlistentries
	 */
	public java.util.List<assetlistentry> findAll();

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
	public java.util.List<assetlistentry> findAll(int start, int end);

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
	public java.util.List<assetlistentry> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<assetlistentry>
			orderByComparator);

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
	public java.util.List<assetlistentry> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<assetlistentry>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Removes all the assetlistentries from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of assetlistentries.
	 *
	 * @return the number of assetlistentries
	 */
	public int countAll();

}