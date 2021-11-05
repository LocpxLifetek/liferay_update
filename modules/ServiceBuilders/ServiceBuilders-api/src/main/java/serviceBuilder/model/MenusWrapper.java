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

import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.wrapper.BaseModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link Menus}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see Menus
 * @generated
 */
public class MenusWrapper
	extends BaseModelWrapper<Menus> implements Menus, ModelWrapper<Menus> {

	public MenusWrapper(Menus menus) {
		super(menus);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("id", getId());
		attributes.put("createdDate", getCreatedDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("createdUser", getCreatedUser());
		attributes.put("modifiedUser", getModifiedUser());
		attributes.put("code", getCode());
		attributes.put("displayName", getDisplayName());
		attributes.put("description", getDescription());
		attributes.put("url", getUrl());
		attributes.put("func", getFunc());
		attributes.put("type", getType());
		attributes.put("isActive", isIsActive());
		attributes.put("parentId", getParentId());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		String id = (String)attributes.get("id");

		if (id != null) {
			setId(id);
		}

		Date createdDate = (Date)attributes.get("createdDate");

		if (createdDate != null) {
			setCreatedDate(createdDate);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}

		String createdUser = (String)attributes.get("createdUser");

		if (createdUser != null) {
			setCreatedUser(createdUser);
		}

		String modifiedUser = (String)attributes.get("modifiedUser");

		if (modifiedUser != null) {
			setModifiedUser(modifiedUser);
		}

		String code = (String)attributes.get("code");

		if (code != null) {
			setCode(code);
		}

		String displayName = (String)attributes.get("displayName");

		if (displayName != null) {
			setDisplayName(displayName);
		}

		String description = (String)attributes.get("description");

		if (description != null) {
			setDescription(description);
		}

		String url = (String)attributes.get("url");

		if (url != null) {
			setUrl(url);
		}

		String func = (String)attributes.get("func");

		if (func != null) {
			setFunc(func);
		}

		Integer type = (Integer)attributes.get("type");

		if (type != null) {
			setType(type);
		}

		Boolean isActive = (Boolean)attributes.get("isActive");

		if (isActive != null) {
			setIsActive(isActive);
		}

		String parentId = (String)attributes.get("parentId");

		if (parentId != null) {
			setParentId(parentId);
		}
	}

	/**
	 * Returns the code of this menus.
	 *
	 * @return the code of this menus
	 */
	@Override
	public String getCode() {
		return model.getCode();
	}

	/**
	 * Returns the created date of this menus.
	 *
	 * @return the created date of this menus
	 */
	@Override
	public Date getCreatedDate() {
		return model.getCreatedDate();
	}

	/**
	 * Returns the created user of this menus.
	 *
	 * @return the created user of this menus
	 */
	@Override
	public String getCreatedUser() {
		return model.getCreatedUser();
	}

	/**
	 * Returns the description of this menus.
	 *
	 * @return the description of this menus
	 */
	@Override
	public String getDescription() {
		return model.getDescription();
	}

	/**
	 * Returns the display name of this menus.
	 *
	 * @return the display name of this menus
	 */
	@Override
	public String getDisplayName() {
		return model.getDisplayName();
	}

	/**
	 * Returns the func of this menus.
	 *
	 * @return the func of this menus
	 */
	@Override
	public String getFunc() {
		return model.getFunc();
	}

	/**
	 * Returns the ID of this menus.
	 *
	 * @return the ID of this menus
	 */
	@Override
	public String getId() {
		return model.getId();
	}

	/**
	 * Returns the is active of this menus.
	 *
	 * @return the is active of this menus
	 */
	@Override
	public boolean getIsActive() {
		return model.getIsActive();
	}

	/**
	 * Returns the modified date of this menus.
	 *
	 * @return the modified date of this menus
	 */
	@Override
	public Date getModifiedDate() {
		return model.getModifiedDate();
	}

	/**
	 * Returns the modified user of this menus.
	 *
	 * @return the modified user of this menus
	 */
	@Override
	public String getModifiedUser() {
		return model.getModifiedUser();
	}

	/**
	 * Returns the parent ID of this menus.
	 *
	 * @return the parent ID of this menus
	 */
	@Override
	public String getParentId() {
		return model.getParentId();
	}

	/**
	 * Returns the primary key of this menus.
	 *
	 * @return the primary key of this menus
	 */
	@Override
	public String getPrimaryKey() {
		return model.getPrimaryKey();
	}

	/**
	 * Returns the type of this menus.
	 *
	 * @return the type of this menus
	 */
	@Override
	public int getType() {
		return model.getType();
	}

	/**
	 * Returns the url of this menus.
	 *
	 * @return the url of this menus
	 */
	@Override
	public String getUrl() {
		return model.getUrl();
	}

	/**
	 * Returns the uuid of this menus.
	 *
	 * @return the uuid of this menus
	 */
	@Override
	public String getUuid() {
		return model.getUuid();
	}

	/**
	 * Returns <code>true</code> if this menus is is active.
	 *
	 * @return <code>true</code> if this menus is is active; <code>false</code> otherwise
	 */
	@Override
	public boolean isIsActive() {
		return model.isIsActive();
	}

	@Override
	public void persist() {
		model.persist();
	}

	/**
	 * Sets the code of this menus.
	 *
	 * @param code the code of this menus
	 */
	@Override
	public void setCode(String code) {
		model.setCode(code);
	}

	/**
	 * Sets the created date of this menus.
	 *
	 * @param createdDate the created date of this menus
	 */
	@Override
	public void setCreatedDate(Date createdDate) {
		model.setCreatedDate(createdDate);
	}

	/**
	 * Sets the created user of this menus.
	 *
	 * @param createdUser the created user of this menus
	 */
	@Override
	public void setCreatedUser(String createdUser) {
		model.setCreatedUser(createdUser);
	}

	/**
	 * Sets the description of this menus.
	 *
	 * @param description the description of this menus
	 */
	@Override
	public void setDescription(String description) {
		model.setDescription(description);
	}

	/**
	 * Sets the display name of this menus.
	 *
	 * @param displayName the display name of this menus
	 */
	@Override
	public void setDisplayName(String displayName) {
		model.setDisplayName(displayName);
	}

	/**
	 * Sets the func of this menus.
	 *
	 * @param func the func of this menus
	 */
	@Override
	public void setFunc(String func) {
		model.setFunc(func);
	}

	/**
	 * Sets the ID of this menus.
	 *
	 * @param id the ID of this menus
	 */
	@Override
	public void setId(String id) {
		model.setId(id);
	}

	/**
	 * Sets whether this menus is is active.
	 *
	 * @param isActive the is active of this menus
	 */
	@Override
	public void setIsActive(boolean isActive) {
		model.setIsActive(isActive);
	}

	/**
	 * Sets the modified date of this menus.
	 *
	 * @param modifiedDate the modified date of this menus
	 */
	@Override
	public void setModifiedDate(Date modifiedDate) {
		model.setModifiedDate(modifiedDate);
	}

	/**
	 * Sets the modified user of this menus.
	 *
	 * @param modifiedUser the modified user of this menus
	 */
	@Override
	public void setModifiedUser(String modifiedUser) {
		model.setModifiedUser(modifiedUser);
	}

	/**
	 * Sets the parent ID of this menus.
	 *
	 * @param parentId the parent ID of this menus
	 */
	@Override
	public void setParentId(String parentId) {
		model.setParentId(parentId);
	}

	/**
	 * Sets the primary key of this menus.
	 *
	 * @param primaryKey the primary key of this menus
	 */
	@Override
	public void setPrimaryKey(String primaryKey) {
		model.setPrimaryKey(primaryKey);
	}

	/**
	 * Sets the type of this menus.
	 *
	 * @param type the type of this menus
	 */
	@Override
	public void setType(int type) {
		model.setType(type);
	}

	/**
	 * Sets the url of this menus.
	 *
	 * @param url the url of this menus
	 */
	@Override
	public void setUrl(String url) {
		model.setUrl(url);
	}

	/**
	 * Sets the uuid of this menus.
	 *
	 * @param uuid the uuid of this menus
	 */
	@Override
	public void setUuid(String uuid) {
		model.setUuid(uuid);
	}

	@Override
	protected MenusWrapper wrap(Menus menus) {
		return new MenusWrapper(menus);
	}

}