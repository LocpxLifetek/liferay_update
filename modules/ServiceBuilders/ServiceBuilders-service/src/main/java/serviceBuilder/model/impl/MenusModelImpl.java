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

package serviceBuilder.model.impl;

import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.json.JSON;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.impl.BaseModelImpl;
import com.liferay.portal.kernel.util.DateUtil;
import com.liferay.portal.kernel.util.ProxyUtil;

import java.io.Serializable;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;

import java.sql.Types;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.BiConsumer;
import java.util.function.Function;

import serviceBuilder.model.Menus;
import serviceBuilder.model.MenusModel;
import serviceBuilder.model.MenusSoap;

/**
 * The base model implementation for the Menus service. Represents a row in the &quot;LT_Menus&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface <code>MenusModel</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link MenusImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see MenusImpl
 * @generated
 */
@JSON(strict = true)
public class MenusModelImpl extends BaseModelImpl<Menus> implements MenusModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a menus model instance should use the <code>Menus</code> interface instead.
	 */
	public static final String TABLE_NAME = "LT_Menus";

	public static final Object[][] TABLE_COLUMNS = {
		{"uuid_", Types.VARCHAR}, {"id_", Types.VARCHAR},
		{"createdDate", Types.TIMESTAMP}, {"modifiedDate", Types.TIMESTAMP},
		{"createdUser", Types.VARCHAR}, {"modifiedUser", Types.VARCHAR},
		{"code_", Types.VARCHAR}, {"displayName", Types.VARCHAR},
		{"description", Types.VARCHAR}, {"url", Types.VARCHAR},
		{"func", Types.VARCHAR}, {"type_", Types.INTEGER},
		{"isActive", Types.BOOLEAN}, {"parentId", Types.VARCHAR}
	};

	public static final Map<String, Integer> TABLE_COLUMNS_MAP =
		new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("uuid_", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("id_", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("createdDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("modifiedDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("createdUser", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("modifiedUser", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("code_", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("displayName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("description", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("url", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("func", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("type_", Types.INTEGER);
		TABLE_COLUMNS_MAP.put("isActive", Types.BOOLEAN);
		TABLE_COLUMNS_MAP.put("parentId", Types.VARCHAR);
	}

	public static final String TABLE_SQL_CREATE =
		"create table LT_Menus (uuid_ VARCHAR(75) null,id_ VARCHAR(75) not null primary key,createdDate DATE null,modifiedDate DATE null,createdUser VARCHAR(75) null,modifiedUser VARCHAR(75) null,code_ VARCHAR(75) null,displayName VARCHAR(75) null,description VARCHAR(75) null,url VARCHAR(75) null,func VARCHAR(75) null,type_ INTEGER,isActive BOOLEAN,parentId VARCHAR(75) null)";

	public static final String TABLE_SQL_DROP = "drop table LT_Menus";

	public static final String ORDER_BY_JPQL =
		" ORDER BY menus.createdDate ASC";

	public static final String ORDER_BY_SQL =
		" ORDER BY LT_Menus.createdDate ASC";

	public static final String DATA_SOURCE = "liferayDataSource";

	public static final String SESSION_FACTORY = "liferaySessionFactory";

	public static final String TX_MANAGER = "liferayTransactionManager";

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long CODE_COLUMN_BITMASK = 1L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long UUID_COLUMN_BITMASK = 2L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *		#getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long CREATEDDATE_COLUMN_BITMASK = 4L;

	/**
	 * @deprecated As of Athanasius (7.3.x), with no direct replacement
	 */
	@Deprecated
	public static void setEntityCacheEnabled(boolean entityCacheEnabled) {
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), with no direct replacement
	 */
	@Deprecated
	public static void setFinderCacheEnabled(boolean finderCacheEnabled) {
	}

	/**
	 * Converts the soap model instance into a normal model instance.
	 *
	 * @param soapModel the soap model instance to convert
	 * @return the normal model instance
	 * @deprecated As of Athanasius (7.3.x), with no direct replacement
	 */
	@Deprecated
	public static Menus toModel(MenusSoap soapModel) {
		if (soapModel == null) {
			return null;
		}

		Menus model = new MenusImpl();

		model.setUuid(soapModel.getUuid());
		model.setId(soapModel.getId());
		model.setCreatedDate(soapModel.getCreatedDate());
		model.setModifiedDate(soapModel.getModifiedDate());
		model.setCreatedUser(soapModel.getCreatedUser());
		model.setModifiedUser(soapModel.getModifiedUser());
		model.setCode(soapModel.getCode());
		model.setDisplayName(soapModel.getDisplayName());
		model.setDescription(soapModel.getDescription());
		model.setUrl(soapModel.getUrl());
		model.setFunc(soapModel.getFunc());
		model.setType(soapModel.getType());
		model.setIsActive(soapModel.isIsActive());
		model.setParentId(soapModel.getParentId());

		return model;
	}

	/**
	 * Converts the soap model instances into normal model instances.
	 *
	 * @param soapModels the soap model instances to convert
	 * @return the normal model instances
	 * @deprecated As of Athanasius (7.3.x), with no direct replacement
	 */
	@Deprecated
	public static List<Menus> toModels(MenusSoap[] soapModels) {
		if (soapModels == null) {
			return null;
		}

		List<Menus> models = new ArrayList<Menus>(soapModels.length);

		for (MenusSoap soapModel : soapModels) {
			models.add(toModel(soapModel));
		}

		return models;
	}

	public MenusModelImpl() {
	}

	@Override
	public String getPrimaryKey() {
		return _id;
	}

	@Override
	public void setPrimaryKey(String primaryKey) {
		setId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _id;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey((String)primaryKeyObj);
	}

	@Override
	public Class<?> getModelClass() {
		return Menus.class;
	}

	@Override
	public String getModelClassName() {
		return Menus.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		Map<String, Function<Menus, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		for (Map.Entry<String, Function<Menus, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<Menus, Object> attributeGetterFunction = entry.getValue();

			attributes.put(
				attributeName, attributeGetterFunction.apply((Menus)this));
		}

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Map<String, BiConsumer<Menus, Object>> attributeSetterBiConsumers =
			getAttributeSetterBiConsumers();

		for (Map.Entry<String, Object> entry : attributes.entrySet()) {
			String attributeName = entry.getKey();

			BiConsumer<Menus, Object> attributeSetterBiConsumer =
				attributeSetterBiConsumers.get(attributeName);

			if (attributeSetterBiConsumer != null) {
				attributeSetterBiConsumer.accept((Menus)this, entry.getValue());
			}
		}
	}

	public Map<String, Function<Menus, Object>> getAttributeGetterFunctions() {
		return _attributeGetterFunctions;
	}

	public Map<String, BiConsumer<Menus, Object>>
		getAttributeSetterBiConsumers() {

		return _attributeSetterBiConsumers;
	}

	private static Function<InvocationHandler, Menus>
		_getProxyProviderFunction() {

		Class<?> proxyClass = ProxyUtil.getProxyClass(
			Menus.class.getClassLoader(), Menus.class, ModelWrapper.class);

		try {
			Constructor<Menus> constructor =
				(Constructor<Menus>)proxyClass.getConstructor(
					InvocationHandler.class);

			return invocationHandler -> {
				try {
					return constructor.newInstance(invocationHandler);
				}
				catch (ReflectiveOperationException
							reflectiveOperationException) {

					throw new InternalError(reflectiveOperationException);
				}
			};
		}
		catch (NoSuchMethodException noSuchMethodException) {
			throw new InternalError(noSuchMethodException);
		}
	}

	private static final Map<String, Function<Menus, Object>>
		_attributeGetterFunctions;
	private static final Map<String, BiConsumer<Menus, Object>>
		_attributeSetterBiConsumers;

	static {
		Map<String, Function<Menus, Object>> attributeGetterFunctions =
			new LinkedHashMap<String, Function<Menus, Object>>();
		Map<String, BiConsumer<Menus, ?>> attributeSetterBiConsumers =
			new LinkedHashMap<String, BiConsumer<Menus, ?>>();

		attributeGetterFunctions.put("uuid", Menus::getUuid);
		attributeSetterBiConsumers.put(
			"uuid", (BiConsumer<Menus, String>)Menus::setUuid);
		attributeGetterFunctions.put("id", Menus::getId);
		attributeSetterBiConsumers.put(
			"id", (BiConsumer<Menus, String>)Menus::setId);
		attributeGetterFunctions.put("createdDate", Menus::getCreatedDate);
		attributeSetterBiConsumers.put(
			"createdDate", (BiConsumer<Menus, Date>)Menus::setCreatedDate);
		attributeGetterFunctions.put("modifiedDate", Menus::getModifiedDate);
		attributeSetterBiConsumers.put(
			"modifiedDate", (BiConsumer<Menus, Date>)Menus::setModifiedDate);
		attributeGetterFunctions.put("createdUser", Menus::getCreatedUser);
		attributeSetterBiConsumers.put(
			"createdUser", (BiConsumer<Menus, String>)Menus::setCreatedUser);
		attributeGetterFunctions.put("modifiedUser", Menus::getModifiedUser);
		attributeSetterBiConsumers.put(
			"modifiedUser", (BiConsumer<Menus, String>)Menus::setModifiedUser);
		attributeGetterFunctions.put("code", Menus::getCode);
		attributeSetterBiConsumers.put(
			"code", (BiConsumer<Menus, String>)Menus::setCode);
		attributeGetterFunctions.put("displayName", Menus::getDisplayName);
		attributeSetterBiConsumers.put(
			"displayName", (BiConsumer<Menus, String>)Menus::setDisplayName);
		attributeGetterFunctions.put("description", Menus::getDescription);
		attributeSetterBiConsumers.put(
			"description", (BiConsumer<Menus, String>)Menus::setDescription);
		attributeGetterFunctions.put("url", Menus::getUrl);
		attributeSetterBiConsumers.put(
			"url", (BiConsumer<Menus, String>)Menus::setUrl);
		attributeGetterFunctions.put("func", Menus::getFunc);
		attributeSetterBiConsumers.put(
			"func", (BiConsumer<Menus, String>)Menus::setFunc);
		attributeGetterFunctions.put("type", Menus::getType);
		attributeSetterBiConsumers.put(
			"type", (BiConsumer<Menus, Integer>)Menus::setType);
		attributeGetterFunctions.put("isActive", Menus::getIsActive);
		attributeSetterBiConsumers.put(
			"isActive", (BiConsumer<Menus, Boolean>)Menus::setIsActive);
		attributeGetterFunctions.put("parentId", Menus::getParentId);
		attributeSetterBiConsumers.put(
			"parentId", (BiConsumer<Menus, String>)Menus::setParentId);

		_attributeGetterFunctions = Collections.unmodifiableMap(
			attributeGetterFunctions);
		_attributeSetterBiConsumers = Collections.unmodifiableMap(
			(Map)attributeSetterBiConsumers);
	}

	@JSON
	@Override
	public String getUuid() {
		if (_uuid == null) {
			return "";
		}
		else {
			return _uuid;
		}
	}

	@Override
	public void setUuid(String uuid) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_uuid = uuid;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public String getOriginalUuid() {
		return getColumnOriginalValue("uuid_");
	}

	@JSON
	@Override
	public String getId() {
		if (_id == null) {
			return "";
		}
		else {
			return _id;
		}
	}

	@Override
	public void setId(String id) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_id = id;
	}

	@JSON
	@Override
	public Date getCreatedDate() {
		return _createdDate;
	}

	@Override
	public void setCreatedDate(Date createdDate) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_createdDate = createdDate;
	}

	@JSON
	@Override
	public Date getModifiedDate() {
		return _modifiedDate;
	}

	public boolean hasSetModifiedDate() {
		return _setModifiedDate;
	}

	@Override
	public void setModifiedDate(Date modifiedDate) {
		_setModifiedDate = true;

		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_modifiedDate = modifiedDate;
	}

	@JSON
	@Override
	public String getCreatedUser() {
		if (_createdUser == null) {
			return "";
		}
		else {
			return _createdUser;
		}
	}

	@Override
	public void setCreatedUser(String createdUser) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_createdUser = createdUser;
	}

	@JSON
	@Override
	public String getModifiedUser() {
		if (_modifiedUser == null) {
			return "";
		}
		else {
			return _modifiedUser;
		}
	}

	@Override
	public void setModifiedUser(String modifiedUser) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_modifiedUser = modifiedUser;
	}

	@JSON
	@Override
	public String getCode() {
		if (_code == null) {
			return "";
		}
		else {
			return _code;
		}
	}

	@Override
	public void setCode(String code) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_code = code;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public String getOriginalCode() {
		return getColumnOriginalValue("code_");
	}

	@JSON
	@Override
	public String getDisplayName() {
		if (_displayName == null) {
			return "";
		}
		else {
			return _displayName;
		}
	}

	@Override
	public void setDisplayName(String displayName) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_displayName = displayName;
	}

	@JSON
	@Override
	public String getDescription() {
		if (_description == null) {
			return "";
		}
		else {
			return _description;
		}
	}

	@Override
	public void setDescription(String description) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_description = description;
	}

	@JSON
	@Override
	public String getUrl() {
		if (_url == null) {
			return "";
		}
		else {
			return _url;
		}
	}

	@Override
	public void setUrl(String url) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_url = url;
	}

	@JSON
	@Override
	public String getFunc() {
		if (_func == null) {
			return "";
		}
		else {
			return _func;
		}
	}

	@Override
	public void setFunc(String func) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_func = func;
	}

	@JSON
	@Override
	public int getType() {
		return _type;
	}

	@Override
	public void setType(int type) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_type = type;
	}

	@JSON
	@Override
	public boolean getIsActive() {
		return _isActive;
	}

	@JSON
	@Override
	public boolean isIsActive() {
		return _isActive;
	}

	@Override
	public void setIsActive(boolean isActive) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_isActive = isActive;
	}

	@JSON
	@Override
	public String getParentId() {
		if (_parentId == null) {
			return "";
		}
		else {
			return _parentId;
		}
	}

	@Override
	public void setParentId(String parentId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_parentId = parentId;
	}

	public long getColumnBitmask() {
		if (_columnBitmask > 0) {
			return _columnBitmask;
		}

		if ((_columnOriginalValues == null) ||
			(_columnOriginalValues == Collections.EMPTY_MAP)) {

			return 0;
		}

		for (Map.Entry<String, Object> entry :
				_columnOriginalValues.entrySet()) {

			if (!Objects.equals(
					entry.getValue(), getColumnValue(entry.getKey()))) {

				_columnBitmask |= _columnBitmasks.get(entry.getKey());
			}
		}

		return _columnBitmask;
	}

	@Override
	public Menus toEscapedModel() {
		if (_escapedModel == null) {
			Function<InvocationHandler, Menus>
				escapedModelProxyProviderFunction =
					EscapedModelProxyProviderFunctionHolder.
						_escapedModelProxyProviderFunction;

			_escapedModel = escapedModelProxyProviderFunction.apply(
				new AutoEscapeBeanHandler(this));
		}

		return _escapedModel;
	}

	@Override
	public Object clone() {
		MenusImpl menusImpl = new MenusImpl();

		menusImpl.setUuid(getUuid());
		menusImpl.setId(getId());
		menusImpl.setCreatedDate(getCreatedDate());
		menusImpl.setModifiedDate(getModifiedDate());
		menusImpl.setCreatedUser(getCreatedUser());
		menusImpl.setModifiedUser(getModifiedUser());
		menusImpl.setCode(getCode());
		menusImpl.setDisplayName(getDisplayName());
		menusImpl.setDescription(getDescription());
		menusImpl.setUrl(getUrl());
		menusImpl.setFunc(getFunc());
		menusImpl.setType(getType());
		menusImpl.setIsActive(isIsActive());
		menusImpl.setParentId(getParentId());

		menusImpl.resetOriginalValues();

		return menusImpl;
	}

	@Override
	public int compareTo(Menus menus) {
		int value = 0;

		value = DateUtil.compareTo(getCreatedDate(), menus.getCreatedDate());

		if (value != 0) {
			return value;
		}

		return 0;
	}

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof Menus)) {
			return false;
		}

		Menus menus = (Menus)object;

		String primaryKey = menus.getPrimaryKey();

		if (getPrimaryKey().equals(primaryKey)) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		return getPrimaryKey().hashCode();
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), with no direct replacement
	 */
	@Deprecated
	@Override
	public boolean isEntityCacheEnabled() {
		return true;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), with no direct replacement
	 */
	@Deprecated
	@Override
	public boolean isFinderCacheEnabled() {
		return true;
	}

	@Override
	public void resetOriginalValues() {
		_columnOriginalValues = Collections.emptyMap();

		_setModifiedDate = false;

		_columnBitmask = 0;
	}

	@Override
	public CacheModel<Menus> toCacheModel() {
		MenusCacheModel menusCacheModel = new MenusCacheModel();

		menusCacheModel.uuid = getUuid();

		String uuid = menusCacheModel.uuid;

		if ((uuid != null) && (uuid.length() == 0)) {
			menusCacheModel.uuid = null;
		}

		menusCacheModel.id = getId();

		String id = menusCacheModel.id;

		if ((id != null) && (id.length() == 0)) {
			menusCacheModel.id = null;
		}

		Date createdDate = getCreatedDate();

		if (createdDate != null) {
			menusCacheModel.createdDate = createdDate.getTime();
		}
		else {
			menusCacheModel.createdDate = Long.MIN_VALUE;
		}

		Date modifiedDate = getModifiedDate();

		if (modifiedDate != null) {
			menusCacheModel.modifiedDate = modifiedDate.getTime();
		}
		else {
			menusCacheModel.modifiedDate = Long.MIN_VALUE;
		}

		menusCacheModel.createdUser = getCreatedUser();

		String createdUser = menusCacheModel.createdUser;

		if ((createdUser != null) && (createdUser.length() == 0)) {
			menusCacheModel.createdUser = null;
		}

		menusCacheModel.modifiedUser = getModifiedUser();

		String modifiedUser = menusCacheModel.modifiedUser;

		if ((modifiedUser != null) && (modifiedUser.length() == 0)) {
			menusCacheModel.modifiedUser = null;
		}

		menusCacheModel.code = getCode();

		String code = menusCacheModel.code;

		if ((code != null) && (code.length() == 0)) {
			menusCacheModel.code = null;
		}

		menusCacheModel.displayName = getDisplayName();

		String displayName = menusCacheModel.displayName;

		if ((displayName != null) && (displayName.length() == 0)) {
			menusCacheModel.displayName = null;
		}

		menusCacheModel.description = getDescription();

		String description = menusCacheModel.description;

		if ((description != null) && (description.length() == 0)) {
			menusCacheModel.description = null;
		}

		menusCacheModel.url = getUrl();

		String url = menusCacheModel.url;

		if ((url != null) && (url.length() == 0)) {
			menusCacheModel.url = null;
		}

		menusCacheModel.func = getFunc();

		String func = menusCacheModel.func;

		if ((func != null) && (func.length() == 0)) {
			menusCacheModel.func = null;
		}

		menusCacheModel.type = getType();

		menusCacheModel.isActive = isIsActive();

		menusCacheModel.parentId = getParentId();

		String parentId = menusCacheModel.parentId;

		if ((parentId != null) && (parentId.length() == 0)) {
			menusCacheModel.parentId = null;
		}

		return menusCacheModel;
	}

	@Override
	public String toString() {
		Map<String, Function<Menus, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			(4 * attributeGetterFunctions.size()) + 2);

		sb.append("{");

		for (Map.Entry<String, Function<Menus, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<Menus, Object> attributeGetterFunction = entry.getValue();

			sb.append(attributeName);
			sb.append("=");
			sb.append(attributeGetterFunction.apply((Menus)this));
			sb.append(", ");
		}

		if (sb.index() > 1) {
			sb.setIndex(sb.index() - 1);
		}

		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		Map<String, Function<Menus, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			(5 * attributeGetterFunctions.size()) + 4);

		sb.append("<model><model-name>");
		sb.append(getModelClassName());
		sb.append("</model-name>");

		for (Map.Entry<String, Function<Menus, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<Menus, Object> attributeGetterFunction = entry.getValue();

			sb.append("<column><column-name>");
			sb.append(attributeName);
			sb.append("</column-name><column-value><![CDATA[");
			sb.append(attributeGetterFunction.apply((Menus)this));
			sb.append("]]></column-value></column>");
		}

		sb.append("</model>");

		return sb.toString();
	}

	private static class EscapedModelProxyProviderFunctionHolder {

		private static final Function<InvocationHandler, Menus>
			_escapedModelProxyProviderFunction = _getProxyProviderFunction();

	}

	private String _uuid;
	private String _id;
	private Date _createdDate;
	private Date _modifiedDate;
	private boolean _setModifiedDate;
	private String _createdUser;
	private String _modifiedUser;
	private String _code;
	private String _displayName;
	private String _description;
	private String _url;
	private String _func;
	private int _type;
	private boolean _isActive;
	private String _parentId;

	public <T> T getColumnValue(String columnName) {
		columnName = _attributeNames.getOrDefault(columnName, columnName);

		Function<Menus, Object> function = _attributeGetterFunctions.get(
			columnName);

		if (function == null) {
			throw new IllegalArgumentException(
				"No attribute getter function found for " + columnName);
		}

		return (T)function.apply((Menus)this);
	}

	public <T> T getColumnOriginalValue(String columnName) {
		if (_columnOriginalValues == null) {
			return null;
		}

		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		return (T)_columnOriginalValues.get(columnName);
	}

	private void _setColumnOriginalValues() {
		_columnOriginalValues = new HashMap<String, Object>();

		_columnOriginalValues.put("uuid_", _uuid);
		_columnOriginalValues.put("id_", _id);
		_columnOriginalValues.put("createdDate", _createdDate);
		_columnOriginalValues.put("modifiedDate", _modifiedDate);
		_columnOriginalValues.put("createdUser", _createdUser);
		_columnOriginalValues.put("modifiedUser", _modifiedUser);
		_columnOriginalValues.put("code_", _code);
		_columnOriginalValues.put("displayName", _displayName);
		_columnOriginalValues.put("description", _description);
		_columnOriginalValues.put("url", _url);
		_columnOriginalValues.put("func", _func);
		_columnOriginalValues.put("type_", _type);
		_columnOriginalValues.put("isActive", _isActive);
		_columnOriginalValues.put("parentId", _parentId);
	}

	private static final Map<String, String> _attributeNames;

	static {
		Map<String, String> attributeNames = new HashMap<>();

		attributeNames.put("uuid_", "uuid");
		attributeNames.put("id_", "id");
		attributeNames.put("code_", "code");
		attributeNames.put("type_", "type");

		_attributeNames = Collections.unmodifiableMap(attributeNames);
	}

	private transient Map<String, Object> _columnOriginalValues;

	public static long getColumnBitmask(String columnName) {
		return _columnBitmasks.get(columnName);
	}

	private static final Map<String, Long> _columnBitmasks;

	static {
		Map<String, Long> columnBitmasks = new HashMap<>();

		columnBitmasks.put("uuid_", 1L);

		columnBitmasks.put("id_", 2L);

		columnBitmasks.put("createdDate", 4L);

		columnBitmasks.put("modifiedDate", 8L);

		columnBitmasks.put("createdUser", 16L);

		columnBitmasks.put("modifiedUser", 32L);

		columnBitmasks.put("code_", 64L);

		columnBitmasks.put("displayName", 128L);

		columnBitmasks.put("description", 256L);

		columnBitmasks.put("url", 512L);

		columnBitmasks.put("func", 1024L);

		columnBitmasks.put("type_", 2048L);

		columnBitmasks.put("isActive", 4096L);

		columnBitmasks.put("parentId", 8192L);

		_columnBitmasks = Collections.unmodifiableMap(columnBitmasks);
	}

	private long _columnBitmask;
	private Menus _escapedModel;

}