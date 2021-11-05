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
 * Provides a wrapper for {@link MenusLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see MenusLocalService
 * @generated
 */
public class MenusLocalServiceWrapper
	implements MenusLocalService, ServiceWrapper<MenusLocalService> {

	public MenusLocalServiceWrapper(MenusLocalService menusLocalService) {
		_menusLocalService = menusLocalService;
	}

	/**
	 * Adds the menus to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect MenusLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param menus the menus
	 * @return the menus that was added
	 */
	@Override
	public serviceBuilder.model.Menus addMenus(
		serviceBuilder.model.Menus menus) {

		return _menusLocalService.addMenus(menus);
	}

	/**
	 * Creates a new menus with the primary key. Does not add the menus to the database.
	 *
	 * @param id the primary key for the new menus
	 * @return the new menus
	 */
	@Override
	public serviceBuilder.model.Menus createMenus(String id) {
		return _menusLocalService.createMenus(id);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel createPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _menusLocalService.createPersistedModel(primaryKeyObj);
	}

	/**
	 * Deletes the menus from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect MenusLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param menus the menus
	 * @return the menus that was removed
	 */
	@Override
	public serviceBuilder.model.Menus deleteMenus(
		serviceBuilder.model.Menus menus) {

		return _menusLocalService.deleteMenus(menus);
	}

	/**
	 * Deletes the menus with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect MenusLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param id the primary key of the menus
	 * @return the menus that was removed
	 * @throws PortalException if a menus with the primary key could not be found
	 */
	@Override
	public serviceBuilder.model.Menus deleteMenus(String id)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _menusLocalService.deleteMenus(id);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _menusLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _menusLocalService.dynamicQuery();
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

		return _menusLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>serviceBuilder.model.impl.MenusModelImpl</code>.
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

		return _menusLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>serviceBuilder.model.impl.MenusModelImpl</code>.
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

		return _menusLocalService.dynamicQuery(
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

		return _menusLocalService.dynamicQueryCount(dynamicQuery);
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

		return _menusLocalService.dynamicQueryCount(dynamicQuery, projection);
	}

	@Override
	public serviceBuilder.model.Menus fetchMenus(String id) {
		return _menusLocalService.fetchMenus(id);
	}

	/**
	 * Returns the menus with the primary key.
	 *
	 * @param id the primary key of the menus
	 * @return the menus
	 * @throws PortalException if a menus with the primary key could not be found
	 */
	@Override
	public serviceBuilder.model.Menus getMenus(String id)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _menusLocalService.getMenus(id);
	}

	/**
	 * Returns a range of all the menuses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>serviceBuilder.model.impl.MenusModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of menuses
	 * @param end the upper bound of the range of menuses (not inclusive)
	 * @return the range of menuses
	 */
	@Override
	public java.util.List<serviceBuilder.model.Menus> getMenuses(
		int start, int end) {

		return _menusLocalService.getMenuses(start, end);
	}

	/**
	 * Returns the number of menuses.
	 *
	 * @return the number of menuses
	 */
	@Override
	public int getMenusesCount() {
		return _menusLocalService.getMenusesCount();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _menusLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _menusLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	 * Updates the menus in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect MenusLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param menus the menus
	 * @return the menus that was updated
	 */
	@Override
	public serviceBuilder.model.Menus updateMenus(
		serviceBuilder.model.Menus menus) {

		return _menusLocalService.updateMenus(menus);
	}

	@Override
	public MenusLocalService getWrappedService() {
		return _menusLocalService;
	}

	@Override
	public void setWrappedService(MenusLocalService menusLocalService) {
		_menusLocalService = menusLocalService;
	}

	private MenusLocalService _menusLocalService;

}