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

import com.liferay.petra.lang.HashUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

import serviceBuilder.model.Menus;

/**
 * The cache model class for representing Menus in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class MenusCacheModel implements CacheModel<Menus>, Externalizable {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof MenusCacheModel)) {
			return false;
		}

		MenusCacheModel menusCacheModel = (MenusCacheModel)object;

		if (id.equals(menusCacheModel.id)) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, id);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(29);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", id=");
		sb.append(id);
		sb.append(", createdDate=");
		sb.append(createdDate);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", createdUser=");
		sb.append(createdUser);
		sb.append(", modifiedUser=");
		sb.append(modifiedUser);
		sb.append(", code=");
		sb.append(code);
		sb.append(", displayName=");
		sb.append(displayName);
		sb.append(", description=");
		sb.append(description);
		sb.append(", url=");
		sb.append(url);
		sb.append(", func=");
		sb.append(func);
		sb.append(", type=");
		sb.append(type);
		sb.append(", isActive=");
		sb.append(isActive);
		sb.append(", parentId=");
		sb.append(parentId);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public Menus toEntityModel() {
		MenusImpl menusImpl = new MenusImpl();

		if (uuid == null) {
			menusImpl.setUuid("");
		}
		else {
			menusImpl.setUuid(uuid);
		}

		if (id == null) {
			menusImpl.setId("");
		}
		else {
			menusImpl.setId(id);
		}

		if (createdDate == Long.MIN_VALUE) {
			menusImpl.setCreatedDate(null);
		}
		else {
			menusImpl.setCreatedDate(new Date(createdDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			menusImpl.setModifiedDate(null);
		}
		else {
			menusImpl.setModifiedDate(new Date(modifiedDate));
		}

		if (createdUser == null) {
			menusImpl.setCreatedUser("");
		}
		else {
			menusImpl.setCreatedUser(createdUser);
		}

		if (modifiedUser == null) {
			menusImpl.setModifiedUser("");
		}
		else {
			menusImpl.setModifiedUser(modifiedUser);
		}

		if (code == null) {
			menusImpl.setCode("");
		}
		else {
			menusImpl.setCode(code);
		}

		if (displayName == null) {
			menusImpl.setDisplayName("");
		}
		else {
			menusImpl.setDisplayName(displayName);
		}

		if (description == null) {
			menusImpl.setDescription("");
		}
		else {
			menusImpl.setDescription(description);
		}

		if (url == null) {
			menusImpl.setUrl("");
		}
		else {
			menusImpl.setUrl(url);
		}

		if (func == null) {
			menusImpl.setFunc("");
		}
		else {
			menusImpl.setFunc(func);
		}

		menusImpl.setType(type);
		menusImpl.setIsActive(isActive);

		if (parentId == null) {
			menusImpl.setParentId("");
		}
		else {
			menusImpl.setParentId(parentId);
		}

		menusImpl.resetOriginalValues();

		return menusImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		uuid = objectInput.readUTF();
		id = objectInput.readUTF();
		createdDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();
		createdUser = objectInput.readUTF();
		modifiedUser = objectInput.readUTF();
		code = objectInput.readUTF();
		displayName = objectInput.readUTF();
		description = objectInput.readUTF();
		url = objectInput.readUTF();
		func = objectInput.readUTF();

		type = objectInput.readInt();

		isActive = objectInput.readBoolean();
		parentId = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		if (uuid == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(uuid);
		}

		if (id == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(id);
		}

		objectOutput.writeLong(createdDate);
		objectOutput.writeLong(modifiedDate);

		if (createdUser == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(createdUser);
		}

		if (modifiedUser == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(modifiedUser);
		}

		if (code == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(code);
		}

		if (displayName == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(displayName);
		}

		if (description == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(description);
		}

		if (url == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(url);
		}

		if (func == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(func);
		}

		objectOutput.writeInt(type);

		objectOutput.writeBoolean(isActive);

		if (parentId == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(parentId);
		}
	}

	public String uuid;
	public String id;
	public long createdDate;
	public long modifiedDate;
	public String createdUser;
	public String modifiedUser;
	public String code;
	public String displayName;
	public String description;
	public String url;
	public String func;
	public int type;
	public boolean isActive;
	public String parentId;

}