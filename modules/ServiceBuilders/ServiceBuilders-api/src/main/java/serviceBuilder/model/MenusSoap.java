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

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link serviceBuilder.service.http.MenusServiceSoap}.
 *
 * @author Brian Wing Shun Chan
 * @deprecated As of Athanasius (7.3.x), with no direct replacement
 * @generated
 */
@Deprecated
public class MenusSoap implements Serializable {

	public static MenusSoap toSoapModel(Menus model) {
		MenusSoap soapModel = new MenusSoap();

		soapModel.setUuid(model.getUuid());
		soapModel.setId(model.getId());
		soapModel.setCreatedDate(model.getCreatedDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setCreatedUser(model.getCreatedUser());
		soapModel.setModifiedUser(model.getModifiedUser());
		soapModel.setCode(model.getCode());
		soapModel.setDisplayName(model.getDisplayName());
		soapModel.setDescription(model.getDescription());
		soapModel.setUrl(model.getUrl());
		soapModel.setFunc(model.getFunc());
		soapModel.setType(model.getType());
		soapModel.setIsActive(model.isIsActive());
		soapModel.setParentId(model.getParentId());

		return soapModel;
	}

	public static MenusSoap[] toSoapModels(Menus[] models) {
		MenusSoap[] soapModels = new MenusSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static MenusSoap[][] toSoapModels(Menus[][] models) {
		MenusSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new MenusSoap[models.length][models[0].length];
		}
		else {
			soapModels = new MenusSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static MenusSoap[] toSoapModels(List<Menus> models) {
		List<MenusSoap> soapModels = new ArrayList<MenusSoap>(models.size());

		for (Menus model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new MenusSoap[soapModels.size()]);
	}

	public MenusSoap() {
	}

	public String getPrimaryKey() {
		return _id;
	}

	public void setPrimaryKey(String pk) {
		setId(pk);
	}

	public String getUuid() {
		return _uuid;
	}

	public void setUuid(String uuid) {
		_uuid = uuid;
	}

	public String getId() {
		return _id;
	}

	public void setId(String id) {
		_id = id;
	}

	public Date getCreatedDate() {
		return _createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		_createdDate = createdDate;
	}

	public Date getModifiedDate() {
		return _modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		_modifiedDate = modifiedDate;
	}

	public String getCreatedUser() {
		return _createdUser;
	}

	public void setCreatedUser(String createdUser) {
		_createdUser = createdUser;
	}

	public String getModifiedUser() {
		return _modifiedUser;
	}

	public void setModifiedUser(String modifiedUser) {
		_modifiedUser = modifiedUser;
	}

	public String getCode() {
		return _code;
	}

	public void setCode(String code) {
		_code = code;
	}

	public String getDisplayName() {
		return _displayName;
	}

	public void setDisplayName(String displayName) {
		_displayName = displayName;
	}

	public String getDescription() {
		return _description;
	}

	public void setDescription(String description) {
		_description = description;
	}

	public String getUrl() {
		return _url;
	}

	public void setUrl(String url) {
		_url = url;
	}

	public String getFunc() {
		return _func;
	}

	public void setFunc(String func) {
		_func = func;
	}

	public int getType() {
		return _type;
	}

	public void setType(int type) {
		_type = type;
	}

	public boolean getIsActive() {
		return _isActive;
	}

	public boolean isIsActive() {
		return _isActive;
	}

	public void setIsActive(boolean isActive) {
		_isActive = isActive;
	}

	public String getParentId() {
		return _parentId;
	}

	public void setParentId(String parentId) {
		_parentId = parentId;
	}

	private String _uuid;
	private String _id;
	private Date _createdDate;
	private Date _modifiedDate;
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

}