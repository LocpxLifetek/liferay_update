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
 * This class is used by SOAP remote services, specifically {@link serviceBuilder.service.http.assetlistentryServiceSoap}.
 *
 * @author Brian Wing Shun Chan
 * @deprecated As of Athanasius (7.3.x), with no direct replacement
 * @generated
 */
@Deprecated
public class assetlistentrySoap implements Serializable {

	public static assetlistentrySoap toSoapModel(assetlistentry model) {
		assetlistentrySoap soapModel = new assetlistentrySoap();

		soapModel.setUuid(model.getUuid());
		soapModel.setMvccversion(model.getMvccversion());
		soapModel.setCtcollectionid(model.getCtcollectionid());
		soapModel.setAssetlistentryid(model.getAssetlistentryid());
		soapModel.setGroupid(model.getGroupid());
		soapModel.setCompanyid(model.getCompanyid());
		soapModel.setUserid(model.getUserid());
		soapModel.setUsername(model.getUsername());
		soapModel.setCreatedate(model.getCreatedate());
		soapModel.setModifieddate(model.getModifieddate());
		soapModel.setAssetlistentrykey(model.getAssetlistentrykey());
		soapModel.setTitle(model.getTitle());
		soapModel.setType_(model.getType_());
		soapModel.setAssetentrysubtype(model.getAssetentrysubtype());
		soapModel.setAssetentrytype(model.getAssetentrytype());
		soapModel.setLastpublishdate(model.getLastpublishdate());

		return soapModel;
	}

	public static assetlistentrySoap[] toSoapModels(assetlistentry[] models) {
		assetlistentrySoap[] soapModels = new assetlistentrySoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static assetlistentrySoap[][] toSoapModels(
		assetlistentry[][] models) {

		assetlistentrySoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels =
				new assetlistentrySoap[models.length][models[0].length];
		}
		else {
			soapModels = new assetlistentrySoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static assetlistentrySoap[] toSoapModels(
		List<assetlistentry> models) {

		List<assetlistentrySoap> soapModels = new ArrayList<assetlistentrySoap>(
			models.size());

		for (assetlistentry model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new assetlistentrySoap[soapModels.size()]);
	}

	public assetlistentrySoap() {
	}

	public int getPrimaryKey() {
		return _assetlistentryid;
	}

	public void setPrimaryKey(int pk) {
		setAssetlistentryid(pk);
	}

	public String getUuid() {
		return _uuid;
	}

	public void setUuid(String uuid) {
		_uuid = uuid;
	}

	public int getMvccversion() {
		return _mvccversion;
	}

	public void setMvccversion(int mvccversion) {
		_mvccversion = mvccversion;
	}

	public int getCtcollectionid() {
		return _ctcollectionid;
	}

	public void setCtcollectionid(int ctcollectionid) {
		_ctcollectionid = ctcollectionid;
	}

	public int getAssetlistentryid() {
		return _assetlistentryid;
	}

	public void setAssetlistentryid(int assetlistentryid) {
		_assetlistentryid = assetlistentryid;
	}

	public int getGroupid() {
		return _groupid;
	}

	public void setGroupid(int groupid) {
		_groupid = groupid;
	}

	public int getCompanyid() {
		return _companyid;
	}

	public void setCompanyid(int companyid) {
		_companyid = companyid;
	}

	public int getUserid() {
		return _userid;
	}

	public void setUserid(int userid) {
		_userid = userid;
	}

	public String getUsername() {
		return _username;
	}

	public void setUsername(String username) {
		_username = username;
	}

	public Date getCreatedate() {
		return _createdate;
	}

	public void setCreatedate(Date createdate) {
		_createdate = createdate;
	}

	public Date getModifieddate() {
		return _modifieddate;
	}

	public void setModifieddate(Date modifieddate) {
		_modifieddate = modifieddate;
	}

	public String getAssetlistentrykey() {
		return _assetlistentrykey;
	}

	public void setAssetlistentrykey(String assetlistentrykey) {
		_assetlistentrykey = assetlistentrykey;
	}

	public String getTitle() {
		return _title;
	}

	public void setTitle(String title) {
		_title = title;
	}

	public int getType_() {
		return _type_;
	}

	public void setType_(int type_) {
		_type_ = type_;
	}

	public String getAssetentrysubtype() {
		return _assetentrysubtype;
	}

	public void setAssetentrysubtype(String assetentrysubtype) {
		_assetentrysubtype = assetentrysubtype;
	}

	public String getAssetentrytype() {
		return _assetentrytype;
	}

	public void setAssetentrytype(String assetentrytype) {
		_assetentrytype = assetentrytype;
	}

	public Date getLastpublishdate() {
		return _lastpublishdate;
	}

	public void setLastpublishdate(Date lastpublishdate) {
		_lastpublishdate = lastpublishdate;
	}

	private String _uuid;
	private int _mvccversion;
	private int _ctcollectionid;
	private int _assetlistentryid;
	private int _groupid;
	private int _companyid;
	private int _userid;
	private String _username;
	private Date _createdate;
	private Date _modifieddate;
	private String _assetlistentrykey;
	private String _title;
	private int _type_;
	private String _assetentrysubtype;
	private String _assetentrytype;
	private Date _lastpublishdate;

}