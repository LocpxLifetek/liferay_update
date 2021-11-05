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

import serviceBuilder.model.assetlistentry;

/**
 * The cache model class for representing assetlistentry in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class assetlistentryCacheModel
	implements CacheModel<assetlistentry>, Externalizable {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof assetlistentryCacheModel)) {
			return false;
		}

		assetlistentryCacheModel assetlistentryCacheModel =
			(assetlistentryCacheModel)object;

		if (assetlistentryid == assetlistentryCacheModel.assetlistentryid) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, assetlistentryid);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(33);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", mvccversion=");
		sb.append(mvccversion);
		sb.append(", ctcollectionid=");
		sb.append(ctcollectionid);
		sb.append(", assetlistentryid=");
		sb.append(assetlistentryid);
		sb.append(", groupid=");
		sb.append(groupid);
		sb.append(", companyid=");
		sb.append(companyid);
		sb.append(", userid=");
		sb.append(userid);
		sb.append(", username=");
		sb.append(username);
		sb.append(", createdate=");
		sb.append(createdate);
		sb.append(", modifieddate=");
		sb.append(modifieddate);
		sb.append(", assetlistentrykey=");
		sb.append(assetlistentrykey);
		sb.append(", title=");
		sb.append(title);
		sb.append(", type_=");
		sb.append(type_);
		sb.append(", assetentrysubtype=");
		sb.append(assetentrysubtype);
		sb.append(", assetentrytype=");
		sb.append(assetentrytype);
		sb.append(", lastpublishdate=");
		sb.append(lastpublishdate);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public assetlistentry toEntityModel() {
		assetlistentryImpl assetlistentryImpl = new assetlistentryImpl();

		if (uuid == null) {
			assetlistentryImpl.setUuid("");
		}
		else {
			assetlistentryImpl.setUuid(uuid);
		}

		assetlistentryImpl.setMvccversion(mvccversion);
		assetlistentryImpl.setCtcollectionid(ctcollectionid);
		assetlistentryImpl.setAssetlistentryid(assetlistentryid);
		assetlistentryImpl.setGroupid(groupid);
		assetlistentryImpl.setCompanyid(companyid);
		assetlistentryImpl.setUserid(userid);

		if (username == null) {
			assetlistentryImpl.setUsername("");
		}
		else {
			assetlistentryImpl.setUsername(username);
		}

		if (createdate == Long.MIN_VALUE) {
			assetlistentryImpl.setCreatedate(null);
		}
		else {
			assetlistentryImpl.setCreatedate(new Date(createdate));
		}

		if (modifieddate == Long.MIN_VALUE) {
			assetlistentryImpl.setModifieddate(null);
		}
		else {
			assetlistentryImpl.setModifieddate(new Date(modifieddate));
		}

		if (assetlistentrykey == null) {
			assetlistentryImpl.setAssetlistentrykey("");
		}
		else {
			assetlistentryImpl.setAssetlistentrykey(assetlistentrykey);
		}

		if (title == null) {
			assetlistentryImpl.setTitle("");
		}
		else {
			assetlistentryImpl.setTitle(title);
		}

		assetlistentryImpl.setType_(type_);

		if (assetentrysubtype == null) {
			assetlistentryImpl.setAssetentrysubtype("");
		}
		else {
			assetlistentryImpl.setAssetentrysubtype(assetentrysubtype);
		}

		if (assetentrytype == null) {
			assetlistentryImpl.setAssetentrytype("");
		}
		else {
			assetlistentryImpl.setAssetentrytype(assetentrytype);
		}

		if (lastpublishdate == Long.MIN_VALUE) {
			assetlistentryImpl.setLastpublishdate(null);
		}
		else {
			assetlistentryImpl.setLastpublishdate(new Date(lastpublishdate));
		}

		assetlistentryImpl.resetOriginalValues();

		return assetlistentryImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		uuid = objectInput.readUTF();

		mvccversion = objectInput.readInt();

		ctcollectionid = objectInput.readInt();

		assetlistentryid = objectInput.readInt();

		groupid = objectInput.readInt();

		companyid = objectInput.readInt();

		userid = objectInput.readInt();
		username = objectInput.readUTF();
		createdate = objectInput.readLong();
		modifieddate = objectInput.readLong();
		assetlistentrykey = objectInput.readUTF();
		title = objectInput.readUTF();

		type_ = objectInput.readInt();
		assetentrysubtype = objectInput.readUTF();
		assetentrytype = objectInput.readUTF();
		lastpublishdate = objectInput.readLong();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		if (uuid == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(uuid);
		}

		objectOutput.writeInt(mvccversion);

		objectOutput.writeInt(ctcollectionid);

		objectOutput.writeInt(assetlistentryid);

		objectOutput.writeInt(groupid);

		objectOutput.writeInt(companyid);

		objectOutput.writeInt(userid);

		if (username == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(username);
		}

		objectOutput.writeLong(createdate);
		objectOutput.writeLong(modifieddate);

		if (assetlistentrykey == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(assetlistentrykey);
		}

		if (title == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(title);
		}

		objectOutput.writeInt(type_);

		if (assetentrysubtype == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(assetentrysubtype);
		}

		if (assetentrytype == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(assetentrytype);
		}

		objectOutput.writeLong(lastpublishdate);
	}

	public String uuid;
	public int mvccversion;
	public int ctcollectionid;
	public int assetlistentryid;
	public int groupid;
	public int companyid;
	public int userid;
	public String username;
	public long createdate;
	public long modifieddate;
	public String assetlistentrykey;
	public String title;
	public int type_;
	public String assetentrysubtype;
	public String assetentrytype;
	public long lastpublishdate;

}