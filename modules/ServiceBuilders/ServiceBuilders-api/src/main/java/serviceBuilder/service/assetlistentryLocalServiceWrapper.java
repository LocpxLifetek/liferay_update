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

package serviceBuilder.service;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link assetlistentryLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see assetlistentryLocalService
 * @generated
 */
public class assetlistentryLocalServiceWrapper
	implements assetlistentryLocalService,
			   ServiceWrapper<assetlistentryLocalService> {

	public assetlistentryLocalServiceWrapper(
		assetlistentryLocalService assetlistentryLocalService) {

		_assetlistentryLocalService = assetlistentryLocalService;
	}

	/**
	 * Adds the assetlistentry to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect assetlistentryLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param assetlistentry the assetlistentry
	 * @return the assetlistentry that was added
	 */
	@Override
	public serviceBuilder.model.assetlistentry addassetlistentry(
		serviceBuilder.model.assetlistentry assetlistentry) {

		return _assetlistentryLocalService.addassetlistentry(assetlistentry);
	}

	/**
	 * Creates a new assetlistentry with the primary key. Does not add the assetlistentry to the database.
	 *
	 * @param assetlistentryid the primary key for the new assetlistentry
	 * @return the new assetlistentry
	 */
	@Override
	public serviceBuilder.model.assetlistentry createassetlistentry(
		int assetlistentryid) {

		return _assetlistentryLocalService.createassetlistentry(
			assetlistentryid);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel createPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _assetlistentryLocalService.createPersistedModel(primaryKeyObj);
	}

	/**
	 * Deletes the assetlistentry from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect assetlistentryLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param assetlistentry the assetlistentry
	 * @return the assetlistentry that was removed
	 */
	@Override
	public serviceBuilder.model.assetlistentry deleteassetlistentry(
		serviceBuilder.model.assetlistentry assetlistentry) {

		return _assetlistentryLocalService.deleteassetlistentry(assetlistentry);
	}

	/**
	 * Deletes the assetlistentry with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect assetlistentryLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param assetlistentryid the primary key of the assetlistentry
	 * @return the assetlistentry that was removed
	 * @throws PortalException if a assetlistentry with the primary key could not be found
	 */
	@Override
	public serviceBuilder.model.assetlistentry deleteassetlistentry(
			int assetlistentryid)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _assetlistentryLocalService.deleteassetlistentry(
			assetlistentryid);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _assetlistentryLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _assetlistentryLocalService.dynamicQuery();
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {

		return _assetlistentryLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>serviceBuilder.model.impl.assetlistentryModelImpl</code>.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @return the range of matching rows
	 */
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {

		return _assetlistentryLocalService.dynamicQuery(
			dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>serviceBuilder.model.impl.assetlistentryModelImpl</code>.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching rows
	 */
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {

		return _assetlistentryLocalService.dynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the number of rows matching the dynamic query
	 */
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {

		return _assetlistentryLocalService.dynamicQueryCount(dynamicQuery);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @param projection the projection to apply to the query
	 * @return the number of rows matching the dynamic query
	 */
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {

		return _assetlistentryLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public serviceBuilder.model.assetlistentry fetchassetlistentry(
		int assetlistentryid) {

		return _assetlistentryLocalService.fetchassetlistentry(
			assetlistentryid);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _assetlistentryLocalService.getActionableDynamicQuery();
	}

	/**
	 * Returns a range of all the assetlistentries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>serviceBuilder.model.impl.assetlistentryModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of assetlistentries
	 * @param end the upper bound of the range of assetlistentries (not inclusive)
	 * @return the range of assetlistentries
	 */
	@Override
	public java.util.List<serviceBuilder.model.assetlistentry>
		getassetlistentries(int start, int end) {

		return _assetlistentryLocalService.getassetlistentries(start, end);
	}

	/**
	 * Returns the number of assetlistentries.
	 *
	 * @return the number of assetlistentries
	 */
	@Override
	public int getassetlistentriesCount() {
		return _assetlistentryLocalService.getassetlistentriesCount();
	}

	/**
	 * Returns the assetlistentry with the primary key.
	 *
	 * @param assetlistentryid the primary key of the assetlistentry
	 * @return the assetlistentry
	 * @throws PortalException if a assetlistentry with the primary key could not be found
	 */
	@Override
	public serviceBuilder.model.assetlistentry getassetlistentry(
			int assetlistentryid)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _assetlistentryLocalService.getassetlistentry(assetlistentryid);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _assetlistentryLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _assetlistentryLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _assetlistentryLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	 * Updates the assetlistentry in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect assetlistentryLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param assetlistentry the assetlistentry
	 * @return the assetlistentry that was updated
	 */
	@Override
	public serviceBuilder.model.assetlistentry updateassetlistentry(
		serviceBuilder.model.assetlistentry assetlistentry) {

		return _assetlistentryLocalService.updateassetlistentry(assetlistentry);
	}

	@Override
	public assetlistentryLocalService getWrappedService() {
		return _assetlistentryLocalService;
	}

	@Override
	public void setWrappedService(
		assetlistentryLocalService assetlistentryLocalService) {

		_assetlistentryLocalService = assetlistentryLocalService;
	}

	private assetlistentryLocalService _assetlistentryLocalService;

}