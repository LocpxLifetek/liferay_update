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

package serviceBuilder.model;

import com.liferay.portal.kernel.annotation.ImplementationClassName;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.Accessor;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The extended model interface for the assetlistentry service. Represents a row in the &quot;LT_assetlistentry&quot; database table, with each column mapped to a property of this class.
 *
 * @author Brian Wing Shun Chan
 * @see assetlistentryModel
 * @generated
 */
@ImplementationClassName("serviceBuilder.model.impl.assetlistentryImpl")
@ProviderType
public interface assetlistentry extends assetlistentryModel, PersistedModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to <code>serviceBuilder.model.impl.assetlistentryImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<assetlistentry, Integer>
		ASSETLISTENTRYID_ACCESSOR = new Accessor<assetlistentry, Integer>() {

			@Override
			public Integer get(assetlistentry assetlistentry) {
				return assetlistentry.getAssetlistentryid();
			}

			@Override
			public Class<Integer> getAttributeClass() {
				return Integer.class;
			}

			@Override
			public Class<assetlistentry> getTypeClass() {
				return assetlistentry.class;
			}

		};

}