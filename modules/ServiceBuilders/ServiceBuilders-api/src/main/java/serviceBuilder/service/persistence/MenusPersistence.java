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

import serviceBuilder.exception.NoSuchMenusException;

import serviceBuilder.model.Menus;

/**
 * The persistence interface for the menus service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see MenusUtil
 * @generated
 */
@ProviderType
public interface MenusPersistence extends BasePersistence<Menus> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link MenusUtil} to access the menus persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	 * Returns all the menuses where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching menuses
	 */
	public java.util.List<Menus> findByUuid(String uuid);

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
	public java.util.List<Menus> findByUuid(String uuid, int start, int end);

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
	public java.util.List<Menus> findByUuid(
		String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Menus>
			orderByComparator);

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
	public java.util.List<Menus> findByUuid(
		String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Menus>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first menus in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching menus
	 * @throws NoSuchMenusException if a matching menus could not be found
	 */
	public Menus findByUuid_First(
			String uuid,
			com.liferay.portal.kernel.util.OrderByComparator<Menus>
				orderByComparator)
		throws NoSuchMenusException;

	/**
	 * Returns the first menus in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching menus, or <code>null</code> if a matching menus could not be found
	 */
	public Menus fetchByUuid_First(
		String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<Menus>
			orderByComparator);

	/**
	 * Returns the last menus in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching menus
	 * @throws NoSuchMenusException if a matching menus could not be found
	 */
	public Menus findByUuid_Last(
			String uuid,
			com.liferay.portal.kernel.util.OrderByComparator<Menus>
				orderByComparator)
		throws NoSuchMenusException;

	/**
	 * Returns the last menus in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching menus, or <code>null</code> if a matching menus could not be found
	 */
	public Menus fetchByUuid_Last(
		String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<Menus>
			orderByComparator);

	/**
	 * Returns the menuses before and after the current menus in the ordered set where uuid = &#63;.
	 *
	 * @param id the primary key of the current menus
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next menus
	 * @throws NoSuchMenusException if a menus with the primary key could not be found
	 */
	public Menus[] findByUuid_PrevAndNext(
			String id, String uuid,
			com.liferay.portal.kernel.util.OrderByComparator<Menus>
				orderByComparator)
		throws NoSuchMenusException;

	/**
	 * Removes all the menuses where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	public void removeByUuid(String uuid);

	/**
	 * Returns the number of menuses where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching menuses
	 */
	public int countByUuid(String uuid);

	/**
	 * Returns all the menuses where code = &#63;.
	 *
	 * @param code the code
	 * @return the matching menuses
	 */
	public java.util.List<Menus> findByCode(String code);

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
	public java.util.List<Menus> findByCode(String code, int start, int end);

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
	public java.util.List<Menus> findByCode(
		String code, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Menus>
			orderByComparator);

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
	public java.util.List<Menus> findByCode(
		String code, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Menus>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first menus in the ordered set where code = &#63;.
	 *
	 * @param code the code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching menus
	 * @throws NoSuchMenusException if a matching menus could not be found
	 */
	public Menus findByCode_First(
			String code,
			com.liferay.portal.kernel.util.OrderByComparator<Menus>
				orderByComparator)
		throws NoSuchMenusException;

	/**
	 * Returns the first menus in the ordered set where code = &#63;.
	 *
	 * @param code the code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching menus, or <code>null</code> if a matching menus could not be found
	 */
	public Menus fetchByCode_First(
		String code,
		com.liferay.portal.kernel.util.OrderByComparator<Menus>
			orderByComparator);

	/**
	 * Returns the last menus in the ordered set where code = &#63;.
	 *
	 * @param code the code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching menus
	 * @throws NoSuchMenusException if a matching menus could not be found
	 */
	public Menus findByCode_Last(
			String code,
			com.liferay.portal.kernel.util.OrderByComparator<Menus>
				orderByComparator)
		throws NoSuchMenusException;

	/**
	 * Returns the last menus in the ordered set where code = &#63;.
	 *
	 * @param code the code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching menus, or <code>null</code> if a matching menus could not be found
	 */
	public Menus fetchByCode_Last(
		String code,
		com.liferay.portal.kernel.util.OrderByComparator<Menus>
			orderByComparator);

	/**
	 * Returns the menuses before and after the current menus in the ordered set where code = &#63;.
	 *
	 * @param id the primary key of the current menus
	 * @param code the code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next menus
	 * @throws NoSuchMenusException if a menus with the primary key could not be found
	 */
	public Menus[] findByCode_PrevAndNext(
			String id, String code,
			com.liferay.portal.kernel.util.OrderByComparator<Menus>
				orderByComparator)
		throws NoSuchMenusException;

	/**
	 * Removes all the menuses where code = &#63; from the database.
	 *
	 * @param code the code
	 */
	public void removeByCode(String code);

	/**
	 * Returns the number of menuses where code = &#63;.
	 *
	 * @param code the code
	 * @return the number of matching menuses
	 */
	public int countByCode(String code);

	/**
	 * Caches the menus in the entity cache if it is enabled.
	 *
	 * @param menus the menus
	 */
	public void cacheResult(Menus menus);

	/**
	 * Caches the menuses in the entity cache if it is enabled.
	 *
	 * @param menuses the menuses
	 */
	public void cacheResult(java.util.List<Menus> menuses);

	/**
	 * Creates a new menus with the primary key. Does not add the menus to the database.
	 *
	 * @param id the primary key for the new menus
	 * @return the new menus
	 */
	public Menus create(String id);

	/**
	 * Removes the menus with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param id the primary key of the menus
	 * @return the menus that was removed
	 * @throws NoSuchMenusException if a menus with the primary key could not be found
	 */
	public Menus remove(String id) throws NoSuchMenusException;

	public Menus updateImpl(Menus menus);

	/**
	 * Returns the menus with the primary key or throws a <code>NoSuchMenusException</code> if it could not be found.
	 *
	 * @param id the primary key of the menus
	 * @return the menus
	 * @throws NoSuchMenusException if a menus with the primary key could not be found
	 */
	public Menus findByPrimaryKey(String id) throws NoSuchMenusException;

	/**
	 * Returns the menus with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param id the primary key of the menus
	 * @return the menus, or <code>null</code> if a menus with the primary key could not be found
	 */
	public Menus fetchByPrimaryKey(String id);

	/**
	 * Returns all the menuses.
	 *
	 * @return the menuses
	 */
	public java.util.List<Menus> findAll();

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
	public java.util.List<Menus> findAll(int start, int end);

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
	public java.util.List<Menus> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Menus>
			orderByComparator);

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
	public java.util.List<Menus> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Menus>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Removes all the menuses from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of menuses.
	 *
	 * @return the number of menuses
	 */
	public int countAll();

}