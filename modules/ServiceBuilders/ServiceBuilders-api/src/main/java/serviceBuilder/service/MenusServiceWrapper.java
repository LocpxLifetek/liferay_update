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
 * Provides a wrapper for {@link MenusService}.
 *
 * @author Brian Wing Shun Chan
 * @see MenusService
 * @generated
 */
public class MenusServiceWrapper
	implements MenusService, ServiceWrapper<MenusService> {

	public MenusServiceWrapper(MenusService menusService) {
		_menusService = menusService;
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _menusService.getOSGiServiceIdentifier();
	}

	@Override
	public MenusService getWrappedService() {
		return _menusService;
	}

	@Override
	public void setWrappedService(MenusService menusService) {
		_menusService = menusService;
	}

	private MenusService _menusService;

}