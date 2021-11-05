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

package serviceBuilder.service.persistence.impl;

import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.configuration.Configuration;
import com.liferay.portal.kernel.dao.orm.ArgumentsResolver;
import com.liferay.portal.kernel.dao.orm.EntityCache;
import com.liferay.portal.kernel.dao.orm.FinderCache;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.dao.orm.SessionFactory;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.BaseModel;
import com.liferay.portal.kernel.service.persistence.impl.BasePersistenceImpl;
import com.liferay.portal.kernel.util.MapUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.SetUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.uuid.PortalUUIDUtil;

import java.io.Serializable;

import java.lang.reflect.InvocationHandler;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import javax.sql.DataSource;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

import serviceBuilder.exception.NoSuchassetlistentryException;

import serviceBuilder.model.assetlistentry;
import serviceBuilder.model.impl.assetlistentryImpl;
import serviceBuilder.model.impl.assetlistentryModelImpl;

import serviceBuilder.service.persistence.assetlistentryPersistence;
import serviceBuilder.service.persistence.impl.constants.LTPersistenceConstants;

/**
 * The persistence implementation for the assetlistentry service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@Component(service = assetlistentryPersistence.class)
public class assetlistentryPersistenceImpl
	extends BasePersistenceImpl<assetlistentry>
	implements assetlistentryPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>assetlistentryUtil</code> to access the assetlistentry persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		assetlistentryImpl.class.getName();

	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List1";

	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List2";

	private FinderPath _finderPathWithPaginationFindAll;
	private FinderPath _finderPathWithoutPaginationFindAll;
	private FinderPath _finderPathCountAll;
	private FinderPath _finderPathWithPaginationFindByUuid;
	private FinderPath _finderPathWithoutPaginationFindByUuid;
	private FinderPath _finderPathCountByUuid;

	/**
	 * Returns all the assetlistentries where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching assetlistentries
	 */
	@Override
	public List<assetlistentry> findByUuid(String uuid) {
		return findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
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
	@Override
	public List<assetlistentry> findByUuid(String uuid, int start, int end) {
		return findByUuid(uuid, start, end, null);
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
	@Override
	public List<assetlistentry> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<assetlistentry> orderByComparator) {

		return findByUuid(uuid, start, end, orderByComparator, true);
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
	@Override
	public List<assetlistentry> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<assetlistentry> orderByComparator,
		boolean useFinderCache) {

		uuid = Objects.toString(uuid, "");

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindByUuid;
				finderArgs = new Object[] {uuid};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByUuid;
			finderArgs = new Object[] {uuid, start, end, orderByComparator};
		}

		List<assetlistentry> list = null;

		if (useFinderCache) {
			list = (List<assetlistentry>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (assetlistentry assetlistentry : list) {
					if (!uuid.equals(assetlistentry.getUuid())) {
						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler sb = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					3 + (orderByComparator.getOrderByFields().length * 2));
			}
			else {
				sb = new StringBundler(3);
			}

			sb.append(_SQL_SELECT_ASSETLISTENTRY_WHERE);

			boolean bindUuid = false;

			if (uuid.isEmpty()) {
				sb.append(_FINDER_COLUMN_UUID_UUID_3);
			}
			else {
				bindUuid = true;

				sb.append(_FINDER_COLUMN_UUID_UUID_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(assetlistentryModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				if (bindUuid) {
					queryPos.add(uuid);
				}

				list = (List<assetlistentry>)QueryUtil.list(
					query, getDialect(), start, end);

				cacheResult(list);

				if (useFinderCache) {
					finderCache.putResult(finderPath, finderArgs, list);
				}
			}
			catch (Exception exception) {
				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first assetlistentry in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching assetlistentry
	 * @throws NoSuchassetlistentryException if a matching assetlistentry could not be found
	 */
	@Override
	public assetlistentry findByUuid_First(
			String uuid, OrderByComparator<assetlistentry> orderByComparator)
		throws NoSuchassetlistentryException {

		assetlistentry assetlistentry = fetchByUuid_First(
			uuid, orderByComparator);

		if (assetlistentry != null) {
			return assetlistentry;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("uuid=");
		sb.append(uuid);

		sb.append("}");

		throw new NoSuchassetlistentryException(sb.toString());
	}

	/**
	 * Returns the first assetlistentry in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching assetlistentry, or <code>null</code> if a matching assetlistentry could not be found
	 */
	@Override
	public assetlistentry fetchByUuid_First(
		String uuid, OrderByComparator<assetlistentry> orderByComparator) {

		List<assetlistentry> list = findByUuid(uuid, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last assetlistentry in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching assetlistentry
	 * @throws NoSuchassetlistentryException if a matching assetlistentry could not be found
	 */
	@Override
	public assetlistentry findByUuid_Last(
			String uuid, OrderByComparator<assetlistentry> orderByComparator)
		throws NoSuchassetlistentryException {

		assetlistentry assetlistentry = fetchByUuid_Last(
			uuid, orderByComparator);

		if (assetlistentry != null) {
			return assetlistentry;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("uuid=");
		sb.append(uuid);

		sb.append("}");

		throw new NoSuchassetlistentryException(sb.toString());
	}

	/**
	 * Returns the last assetlistentry in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching assetlistentry, or <code>null</code> if a matching assetlistentry could not be found
	 */
	@Override
	public assetlistentry fetchByUuid_Last(
		String uuid, OrderByComparator<assetlistentry> orderByComparator) {

		int count = countByUuid(uuid);

		if (count == 0) {
			return null;
		}

		List<assetlistentry> list = findByUuid(
			uuid, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public assetlistentry[] findByUuid_PrevAndNext(
			int assetlistentryid, String uuid,
			OrderByComparator<assetlistentry> orderByComparator)
		throws NoSuchassetlistentryException {

		uuid = Objects.toString(uuid, "");

		assetlistentry assetlistentry = findByPrimaryKey(assetlistentryid);

		Session session = null;

		try {
			session = openSession();

			assetlistentry[] array = new assetlistentryImpl[3];

			array[0] = getByUuid_PrevAndNext(
				session, assetlistentry, uuid, orderByComparator, true);

			array[1] = assetlistentry;

			array[2] = getByUuid_PrevAndNext(
				session, assetlistentry, uuid, orderByComparator, false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected assetlistentry getByUuid_PrevAndNext(
		Session session, assetlistentry assetlistentry, String uuid,
		OrderByComparator<assetlistentry> orderByComparator, boolean previous) {

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				4 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			sb = new StringBundler(3);
		}

		sb.append(_SQL_SELECT_ASSETLISTENTRY_WHERE);

		boolean bindUuid = false;

		if (uuid.isEmpty()) {
			sb.append(_FINDER_COLUMN_UUID_UUID_3);
		}
		else {
			bindUuid = true;

			sb.append(_FINDER_COLUMN_UUID_UUID_2);
		}

		if (orderByComparator != null) {
			String[] orderByConditionFields =
				orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				sb.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				sb.append(_ORDER_BY_ENTITY_ALIAS);
				sb.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						sb.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(WHERE_GREATER_THAN);
					}
					else {
						sb.append(WHERE_LESSER_THAN);
					}
				}
			}

			sb.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				sb.append(_ORDER_BY_ENTITY_ALIAS);
				sb.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						sb.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(ORDER_BY_ASC);
					}
					else {
						sb.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			sb.append(assetlistentryModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		if (bindUuid) {
			queryPos.add(uuid);
		}

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						assetlistentry)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<assetlistentry> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the assetlistentries where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	@Override
	public void removeByUuid(String uuid) {
		for (assetlistentry assetlistentry :
				findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(assetlistentry);
		}
	}

	/**
	 * Returns the number of assetlistentries where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching assetlistentries
	 */
	@Override
	public int countByUuid(String uuid) {
		uuid = Objects.toString(uuid, "");

		FinderPath finderPath = _finderPathCountByUuid;

		Object[] finderArgs = new Object[] {uuid};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_ASSETLISTENTRY_WHERE);

			boolean bindUuid = false;

			if (uuid.isEmpty()) {
				sb.append(_FINDER_COLUMN_UUID_UUID_3);
			}
			else {
				bindUuid = true;

				sb.append(_FINDER_COLUMN_UUID_UUID_2);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				if (bindUuid) {
					queryPos.add(uuid);
				}

				count = (Long)query.uniqueResult();

				finderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception exception) {
				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_UUID_UUID_2 =
		"assetlistentry.uuid = ?";

	private static final String _FINDER_COLUMN_UUID_UUID_3 =
		"(assetlistentry.uuid IS NULL OR assetlistentry.uuid = '')";

	public assetlistentryPersistenceImpl() {
		Map<String, String> dbColumnNames = new HashMap<String, String>();

		dbColumnNames.put("uuid", "uuid_");

		setDBColumnNames(dbColumnNames);

		setModelClass(assetlistentry.class);

		setModelImplClass(assetlistentryImpl.class);
		setModelPKClass(int.class);
	}

	/**
	 * Caches the assetlistentry in the entity cache if it is enabled.
	 *
	 * @param assetlistentry the assetlistentry
	 */
	@Override
	public void cacheResult(assetlistentry assetlistentry) {
		entityCache.putResult(
			assetlistentryImpl.class, assetlistentry.getPrimaryKey(),
			assetlistentry);
	}

	/**
	 * Caches the assetlistentries in the entity cache if it is enabled.
	 *
	 * @param assetlistentries the assetlistentries
	 */
	@Override
	public void cacheResult(List<assetlistentry> assetlistentries) {
		for (assetlistentry assetlistentry : assetlistentries) {
			if (entityCache.getResult(
					assetlistentryImpl.class, assetlistentry.getPrimaryKey()) ==
						null) {

				cacheResult(assetlistentry);
			}
		}
	}

	/**
	 * Clears the cache for all assetlistentries.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(assetlistentryImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the assetlistentry.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(assetlistentry assetlistentry) {
		entityCache.removeResult(assetlistentryImpl.class, assetlistentry);
	}

	@Override
	public void clearCache(List<assetlistentry> assetlistentries) {
		for (assetlistentry assetlistentry : assetlistentries) {
			entityCache.removeResult(assetlistentryImpl.class, assetlistentry);
		}
	}

	@Override
	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(assetlistentryImpl.class, primaryKey);
		}
	}

	/**
	 * Creates a new assetlistentry with the primary key. Does not add the assetlistentry to the database.
	 *
	 * @param assetlistentryid the primary key for the new assetlistentry
	 * @return the new assetlistentry
	 */
	@Override
	public assetlistentry create(int assetlistentryid) {
		assetlistentry assetlistentry = new assetlistentryImpl();

		assetlistentry.setNew(true);
		assetlistentry.setPrimaryKey(assetlistentryid);

		String uuid = PortalUUIDUtil.generate();

		assetlistentry.setUuid(uuid);

		return assetlistentry;
	}

	/**
	 * Removes the assetlistentry with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param assetlistentryid the primary key of the assetlistentry
	 * @return the assetlistentry that was removed
	 * @throws NoSuchassetlistentryException if a assetlistentry with the primary key could not be found
	 */
	@Override
	public assetlistentry remove(int assetlistentryid)
		throws NoSuchassetlistentryException {

		return remove((Serializable)assetlistentryid);
	}

	/**
	 * Removes the assetlistentry with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the assetlistentry
	 * @return the assetlistentry that was removed
	 * @throws NoSuchassetlistentryException if a assetlistentry with the primary key could not be found
	 */
	@Override
	public assetlistentry remove(Serializable primaryKey)
		throws NoSuchassetlistentryException {

		Session session = null;

		try {
			session = openSession();

			assetlistentry assetlistentry = (assetlistentry)session.get(
				assetlistentryImpl.class, primaryKey);

			if (assetlistentry == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchassetlistentryException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(assetlistentry);
		}
		catch (NoSuchassetlistentryException noSuchEntityException) {
			throw noSuchEntityException;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	@Override
	protected assetlistentry removeImpl(assetlistentry assetlistentry) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(assetlistentry)) {
				assetlistentry = (assetlistentry)session.get(
					assetlistentryImpl.class,
					assetlistentry.getPrimaryKeyObj());
			}

			if (assetlistentry != null) {
				session.delete(assetlistentry);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (assetlistentry != null) {
			clearCache(assetlistentry);
		}

		return assetlistentry;
	}

	@Override
	public assetlistentry updateImpl(assetlistentry assetlistentry) {
		boolean isNew = assetlistentry.isNew();

		if (!(assetlistentry instanceof assetlistentryModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(assetlistentry.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(
					assetlistentry);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in assetlistentry proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom assetlistentry implementation " +
					assetlistentry.getClass());
		}

		assetlistentryModelImpl assetlistentryModelImpl =
			(assetlistentryModelImpl)assetlistentry;

		if (Validator.isNull(assetlistentry.getUuid())) {
			String uuid = PortalUUIDUtil.generate();

			assetlistentry.setUuid(uuid);
		}

		Session session = null;

		try {
			session = openSession();

			if (isNew) {
				session.save(assetlistentry);
			}
			else {
				assetlistentry = (assetlistentry)session.merge(assetlistentry);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		entityCache.putResult(
			assetlistentryImpl.class, assetlistentryModelImpl, false, true);

		if (isNew) {
			assetlistentry.setNew(false);
		}

		assetlistentry.resetOriginalValues();

		return assetlistentry;
	}

	/**
	 * Returns the assetlistentry with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the assetlistentry
	 * @return the assetlistentry
	 * @throws NoSuchassetlistentryException if a assetlistentry with the primary key could not be found
	 */
	@Override
	public assetlistentry findByPrimaryKey(Serializable primaryKey)
		throws NoSuchassetlistentryException {

		assetlistentry assetlistentry = fetchByPrimaryKey(primaryKey);

		if (assetlistentry == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchassetlistentryException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return assetlistentry;
	}

	/**
	 * Returns the assetlistentry with the primary key or throws a <code>NoSuchassetlistentryException</code> if it could not be found.
	 *
	 * @param assetlistentryid the primary key of the assetlistentry
	 * @return the assetlistentry
	 * @throws NoSuchassetlistentryException if a assetlistentry with the primary key could not be found
	 */
	@Override
	public assetlistentry findByPrimaryKey(int assetlistentryid)
		throws NoSuchassetlistentryException {

		return findByPrimaryKey((Serializable)assetlistentryid);
	}

	/**
	 * Returns the assetlistentry with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param assetlistentryid the primary key of the assetlistentry
	 * @return the assetlistentry, or <code>null</code> if a assetlistentry with the primary key could not be found
	 */
	@Override
	public assetlistentry fetchByPrimaryKey(int assetlistentryid) {
		return fetchByPrimaryKey((Serializable)assetlistentryid);
	}

	/**
	 * Returns all the assetlistentries.
	 *
	 * @return the assetlistentries
	 */
	@Override
	public List<assetlistentry> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
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
	@Override
	public List<assetlistentry> findAll(int start, int end) {
		return findAll(start, end, null);
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
	@Override
	public List<assetlistentry> findAll(
		int start, int end,
		OrderByComparator<assetlistentry> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
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
	@Override
	public List<assetlistentry> findAll(
		int start, int end, OrderByComparator<assetlistentry> orderByComparator,
		boolean useFinderCache) {

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindAll;
				finderArgs = FINDER_ARGS_EMPTY;
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindAll;
			finderArgs = new Object[] {start, end, orderByComparator};
		}

		List<assetlistentry> list = null;

		if (useFinderCache) {
			list = (List<assetlistentry>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_ASSETLISTENTRY);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_ASSETLISTENTRY;

				sql = sql.concat(assetlistentryModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<assetlistentry>)QueryUtil.list(
					query, getDialect(), start, end);

				cacheResult(list);

				if (useFinderCache) {
					finderCache.putResult(finderPath, finderArgs, list);
				}
			}
			catch (Exception exception) {
				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Removes all the assetlistentries from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (assetlistentry assetlistentry : findAll()) {
			remove(assetlistentry);
		}
	}

	/**
	 * Returns the number of assetlistentries.
	 *
	 * @return the number of assetlistentries
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(_SQL_COUNT_ASSETLISTENTRY);

				count = (Long)query.uniqueResult();

				finderCache.putResult(
					_finderPathCountAll, FINDER_ARGS_EMPTY, count);
			}
			catch (Exception exception) {
				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	@Override
	public Set<String> getBadColumnNames() {
		return _badColumnNames;
	}

	@Override
	protected EntityCache getEntityCache() {
		return entityCache;
	}

	@Override
	protected String getPKDBName() {
		return "assetlistentryid";
	}

	@Override
	protected String getSelectSQL() {
		return _SQL_SELECT_ASSETLISTENTRY;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return assetlistentryModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the assetlistentry persistence.
	 */
	@Activate
	public void activate(BundleContext bundleContext) {
		_bundleContext = bundleContext;

		_argumentsResolverServiceRegistration = _bundleContext.registerService(
			ArgumentsResolver.class, new assetlistentryModelArgumentsResolver(),
			MapUtil.singletonDictionary(
				"model.class.name", assetlistentry.class.getName()));

		_finderPathWithPaginationFindAll = _createFinderPath(
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0],
			new String[0], true);

		_finderPathWithoutPaginationFindAll = _createFinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0],
			new String[0], true);

		_finderPathCountAll = _createFinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll",
			new String[0], new String[0], false);

		_finderPathWithPaginationFindByUuid = _createFinderPath(
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid",
			new String[] {
				String.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			},
			new String[] {"uuid_"}, true);

		_finderPathWithoutPaginationFindByUuid = _createFinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid",
			new String[] {String.class.getName()}, new String[] {"uuid_"},
			true);

		_finderPathCountByUuid = _createFinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid",
			new String[] {String.class.getName()}, new String[] {"uuid_"},
			false);
	}

	@Deactivate
	public void deactivate() {
		entityCache.removeCache(assetlistentryImpl.class.getName());

		_argumentsResolverServiceRegistration.unregister();

		for (ServiceRegistration<FinderPath> serviceRegistration :
				_serviceRegistrations) {

			serviceRegistration.unregister();
		}
	}

	@Override
	@Reference(
		target = LTPersistenceConstants.SERVICE_CONFIGURATION_FILTER,
		unbind = "-"
	)
	public void setConfiguration(Configuration configuration) {
	}

	@Override
	@Reference(
		target = LTPersistenceConstants.ORIGIN_BUNDLE_SYMBOLIC_NAME_FILTER,
		unbind = "-"
	)
	public void setDataSource(DataSource dataSource) {
		super.setDataSource(dataSource);
	}

	@Override
	@Reference(
		target = LTPersistenceConstants.ORIGIN_BUNDLE_SYMBOLIC_NAME_FILTER,
		unbind = "-"
	)
	public void setSessionFactory(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}

	private BundleContext _bundleContext;

	@Reference
	protected EntityCache entityCache;

	@Reference
	protected FinderCache finderCache;

	private static final String _SQL_SELECT_ASSETLISTENTRY =
		"SELECT assetlistentry FROM assetlistentry assetlistentry";

	private static final String _SQL_SELECT_ASSETLISTENTRY_WHERE =
		"SELECT assetlistentry FROM assetlistentry assetlistentry WHERE ";

	private static final String _SQL_COUNT_ASSETLISTENTRY =
		"SELECT COUNT(assetlistentry) FROM assetlistentry assetlistentry";

	private static final String _SQL_COUNT_ASSETLISTENTRY_WHERE =
		"SELECT COUNT(assetlistentry) FROM assetlistentry assetlistentry WHERE ";

	private static final String _ORDER_BY_ENTITY_ALIAS = "assetlistentry.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No assetlistentry exists with the primary key ";

	private static final String _NO_SUCH_ENTITY_WITH_KEY =
		"No assetlistentry exists with the key {";

	private static final Log _log = LogFactoryUtil.getLog(
		assetlistentryPersistenceImpl.class);

	private static final Set<String> _badColumnNames = SetUtil.fromArray(
		new String[] {"uuid"});

	private FinderPath _createFinderPath(
		String cacheName, String methodName, String[] params,
		String[] columnNames, boolean baseModelResult) {

		FinderPath finderPath = new FinderPath(
			cacheName, methodName, params, columnNames, baseModelResult);

		if (!cacheName.equals(FINDER_CLASS_NAME_LIST_WITH_PAGINATION)) {
			_serviceRegistrations.add(
				_bundleContext.registerService(
					FinderPath.class, finderPath,
					MapUtil.singletonDictionary("cache.name", cacheName)));
		}

		return finderPath;
	}

	private Set<ServiceRegistration<FinderPath>> _serviceRegistrations =
		new HashSet<>();
	private ServiceRegistration<ArgumentsResolver>
		_argumentsResolverServiceRegistration;

	private static class assetlistentryModelArgumentsResolver
		implements ArgumentsResolver {

		@Override
		public Object[] getArguments(
			FinderPath finderPath, BaseModel<?> baseModel, boolean checkColumn,
			boolean original) {

			String[] columnNames = finderPath.getColumnNames();

			if ((columnNames == null) || (columnNames.length == 0)) {
				if (baseModel.isNew()) {
					return FINDER_ARGS_EMPTY;
				}

				return null;
			}

			assetlistentryModelImpl assetlistentryModelImpl =
				(assetlistentryModelImpl)baseModel;

			long columnBitmask = assetlistentryModelImpl.getColumnBitmask();

			if (!checkColumn || (columnBitmask == 0)) {
				return _getValue(
					assetlistentryModelImpl, columnNames, original);
			}

			Long finderPathColumnBitmask = _finderPathColumnBitmasksCache.get(
				finderPath);

			if (finderPathColumnBitmask == null) {
				finderPathColumnBitmask = 0L;

				for (String columnName : columnNames) {
					finderPathColumnBitmask |=
						assetlistentryModelImpl.getColumnBitmask(columnName);
				}

				_finderPathColumnBitmasksCache.put(
					finderPath, finderPathColumnBitmask);
			}

			if ((columnBitmask & finderPathColumnBitmask) != 0) {
				return _getValue(
					assetlistentryModelImpl, columnNames, original);
			}

			return null;
		}

		private static Object[] _getValue(
			assetlistentryModelImpl assetlistentryModelImpl,
			String[] columnNames, boolean original) {

			Object[] arguments = new Object[columnNames.length];

			for (int i = 0; i < arguments.length; i++) {
				String columnName = columnNames[i];

				if (original) {
					arguments[i] =
						assetlistentryModelImpl.getColumnOriginalValue(
							columnName);
				}
				else {
					arguments[i] = assetlistentryModelImpl.getColumnValue(
						columnName);
				}
			}

			return arguments;
		}

		private static final Map<FinderPath, Long>
			_finderPathColumnBitmasksCache = new ConcurrentHashMap<>();

	}

}