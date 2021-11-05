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
 * This class is a wrapper for {@link assetlistentry}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see assetlistentry
 * @generated
 */
public class assetlistentryWrapper
	extends BaseModelWrapper<assetlistentry>
	implements assetlistentry, ModelWrapper<assetlistentry> {

	public assetlistentryWrapper(assetlistentry assetlistentry) {
		super(assetlistentry);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("mvccversion", getMvccversion());
		attributes.put("ctcollectionid", getCtcollectionid());
		attributes.put("assetlistentryid", getAssetlistentryid());
		attributes.put("groupid", getGroupid());
		attributes.put("companyid", getCompanyid());
		attributes.put("userid", getUserid());
		attributes.put("username", getUsername());
		attributes.put("createdate", getCreatedate());
		attributes.put("modifieddate", getModifieddate());
		attributes.put("assetlistentrykey", getAssetlistentrykey());
		attributes.put("title", getTitle());
		attributes.put("type_", getType_());
		attributes.put("assetentrysubtype", getAssetentrysubtype());
		attributes.put("assetentrytype", getAssetentrytype());
		attributes.put("lastpublishdate", getLastpublishdate());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Integer mvccversion = (Integer)attributes.get("mvccversion");

		if (mvccversion != null) {
			setMvccversion(mvccversion);
		}

		Integer ctcollectionid = (Integer)attributes.get("ctcollectionid");

		if (ctcollectionid != null) {
			setCtcollectionid(ctcollectionid);
		}

		Integer assetlistentryid = (Integer)attributes.get("assetlistentryid");

		if (assetlistentryid != null) {
			setAssetlistentryid(assetlistentryid);
		}

		Integer groupid = (Integer)attributes.get("groupid");

		if (groupid != null) {
			setGroupid(groupid);
		}

		Integer companyid = (Integer)attributes.get("companyid");

		if (companyid != null) {
			setCompanyid(companyid);
		}

		Integer userid = (Integer)attributes.get("userid");

		if (userid != null) {
			setUserid(userid);
		}

		String username = (String)attributes.get("username");

		if (username != null) {
			setUsername(username);
		}

		Date createdate = (Date)attributes.get("createdate");

		if (createdate != null) {
			setCreatedate(createdate);
		}

		Date modifieddate = (Date)attributes.get("modifieddate");

		if (modifieddate != null) {
			setModifieddate(modifieddate);
		}

		String assetlistentrykey = (String)attributes.get("assetlistentrykey");

		if (assetlistentrykey != null) {
			setAssetlistentrykey(assetlistentrykey);
		}

		String title = (String)attributes.get("title");

		if (title != null) {
			setTitle(title);
		}

		Integer type_ = (Integer)attributes.get("type_");

		if (type_ != null) {
			setType_(type_);
		}

		String assetentrysubtype = (String)attributes.get("assetentrysubtype");

		if (assetentrysubtype != null) {
			setAssetentrysubtype(assetentrysubtype);
		}

		String assetentrytype = (String)attributes.get("assetentrytype");

		if (assetentrytype != null) {
			setAssetentrytype(assetentrytype);
		}

		Date lastpublishdate = (Date)attributes.get("lastpublishdate");

		if (lastpublishdate != null) {
			setLastpublishdate(lastpublishdate);
		}
	}

	/**
	 * Returns the assetentrysubtype of this assetlistentry.
	 *
	 * @return the assetentrysubtype of this assetlistentry
	 */
	@Override
	public String getAssetentrysubtype() {
		return model.getAssetentrysubtype();
	}

	/**
	 * Returns the assetentrytype of this assetlistentry.
	 *
	 * @return the assetentrytype of this assetlistentry
	 */
	@Override
	public String getAssetentrytype() {
		return model.getAssetentrytype();
	}

	/**
	 * Returns the assetlistentryid of this assetlistentry.
	 *
	 * @return the assetlistentryid of this assetlistentry
	 */
	@Override
	public int getAssetlistentryid() {
		return model.getAssetlistentryid();
	}

	/**
	 * Returns the assetlistentrykey of this assetlistentry.
	 *
	 * @return the assetlistentrykey of this assetlistentry
	 */
	@Override
	public String getAssetlistentrykey() {
		return model.getAssetlistentrykey();
	}

	/**
	 * Returns the companyid of this assetlistentry.
	 *
	 * @return the companyid of this assetlistentry
	 */
	@Override
	public int getCompanyid() {
		return model.getCompanyid();
	}

	/**
	 * Returns the createdate of this assetlistentry.
	 *
	 * @return the createdate of this assetlistentry
	 */
	@Override
	public Date getCreatedate() {
		return model.getCreatedate();
	}

	/**
	 * Returns the ctcollectionid of this assetlistentry.
	 *
	 * @return the ctcollectionid of this assetlistentry
	 */
	@Override
	public int getCtcollectionid() {
		return model.getCtcollectionid();
	}

	/**
	 * Returns the groupid of this assetlistentry.
	 *
	 * @return the groupid of this assetlistentry
	 */
	@Override
	public int getGroupid() {
		return model.getGroupid();
	}

	/**
	 * Returns the lastpublishdate of this assetlistentry.
	 *
	 * @return the lastpublishdate of this assetlistentry
	 */
	@Override
	public Date getLastpublishdate() {
		return model.getLastpublishdate();
	}

	/**
	 * Returns the modifieddate of this assetlistentry.
	 *
	 * @return the modifieddate of this assetlistentry
	 */
	@Override
	public Date getModifieddate() {
		return model.getModifieddate();
	}

	/**
	 * Returns the mvccversion of this assetlistentry.
	 *
	 * @return the mvccversion of this assetlistentry
	 */
	@Override
	public int getMvccversion() {
		return model.getMvccversion();
	}

	/**
	 * Returns the primary key of this assetlistentry.
	 *
	 * @return the primary key of this assetlistentry
	 */
	@Override
	public int getPrimaryKey() {
		return model.getPrimaryKey();
	}

	/**
	 * Returns the title of this assetlistentry.
	 *
	 * @return the title of this assetlistentry
	 */
	@Override
	public String getTitle() {
		return model.getTitle();
	}

	/**
	 * Returns the type_ of this assetlistentry.
	 *
	 * @return the type_ of this assetlistentry
	 */
	@Override
	public int getType_() {
		return model.getType_();
	}

	/**
	 * Returns the userid of this assetlistentry.
	 *
	 * @return the userid of this assetlistentry
	 */
	@Override
	public int getUserid() {
		return model.getUserid();
	}

	/**
	 * Returns the username of this assetlistentry.
	 *
	 * @return the username of this assetlistentry
	 */
	@Override
	public String getUsername() {
		return model.getUsername();
	}

	/**
	 * Returns the uuid of this assetlistentry.
	 *
	 * @return the uuid of this assetlistentry
	 */
	@Override
	public String getUuid() {
		return model.getUuid();
	}

	@Override
	public void persist() {
		model.persist();
	}

	/**
	 * Sets the assetentrysubtype of this assetlistentry.
	 *
	 * @param assetentrysubtype the assetentrysubtype of this assetlistentry
	 */
	@Override
	public void setAssetentrysubtype(String assetentrysubtype) {
		model.setAssetentrysubtype(assetentrysubtype);
	}

	/**
	 * Sets the assetentrytype of this assetlistentry.
	 *
	 * @param assetentrytype the assetentrytype of this assetlistentry
	 */
	@Override
	public void setAssetentrytype(String assetentrytype) {
		model.setAssetentrytype(assetentrytype);
	}

	/**
	 * Sets the assetlistentryid of this assetlistentry.
	 *
	 * @param assetlistentryid the assetlistentryid of this assetlistentry
	 */
	@Override
	public void setAssetlistentryid(int assetlistentryid) {
		model.setAssetlistentryid(assetlistentryid);
	}

	/**
	 * Sets the assetlistentrykey of this assetlistentry.
	 *
	 * @param assetlistentrykey the assetlistentrykey of this assetlistentry
	 */
	@Override
	public void setAssetlistentrykey(String assetlistentrykey) {
		model.setAssetlistentrykey(assetlistentrykey);
	}

	/**
	 * Sets the companyid of this assetlistentry.
	 *
	 * @param companyid the companyid of this assetlistentry
	 */
	@Override
	public void setCompanyid(int companyid) {
		model.setCompanyid(companyid);
	}

	/**
	 * Sets the createdate of this assetlistentry.
	 *
	 * @param createdate the createdate of this assetlistentry
	 */
	@Override
	public void setCreatedate(Date createdate) {
		model.setCreatedate(createdate);
	}

	/**
	 * Sets the ctcollectionid of this assetlistentry.
	 *
	 * @param ctcollectionid the ctcollectionid of this assetlistentry
	 */
	@Override
	public void setCtcollectionid(int ctcollectionid) {
		model.setCtcollectionid(ctcollectionid);
	}

	/**
	 * Sets the groupid of this assetlistentry.
	 *
	 * @param groupid the groupid of this assetlistentry
	 */
	@Override
	public void setGroupid(int groupid) {
		model.setGroupid(groupid);
	}

	/**
	 * Sets the lastpublishdate of this assetlistentry.
	 *
	 * @param lastpublishdate the lastpublishdate of this assetlistentry
	 */
	@Override
	public void setLastpublishdate(Date lastpublishdate) {
		model.setLastpublishdate(lastpublishdate);
	}

	/**
	 * Sets the modifieddate of this assetlistentry.
	 *
	 * @param modifieddate the modifieddate of this assetlistentry
	 */
	@Override
	public void setModifieddate(Date modifieddate) {
		model.setModifieddate(modifieddate);
	}

	/**
	 * Sets the mvccversion of this assetlistentry.
	 *
	 * @param mvccversion the mvccversion of this assetlistentry
	 */
	@Override
	public void setMvccversion(int mvccversion) {
		model.setMvccversion(mvccversion);
	}

	/**
	 * Sets the primary key of this assetlistentry.
	 *
	 * @param primaryKey the primary key of this assetlistentry
	 */
	@Override
	public void setPrimaryKey(int primaryKey) {
		model.setPrimaryKey(primaryKey);
	}

	/**
	 * Sets the title of this assetlistentry.
	 *
	 * @param title the title of this assetlistentry
	 */
	@Override
	public void setTitle(String title) {
		model.setTitle(title);
	}

	/**
	 * Sets the type_ of this assetlistentry.
	 *
	 * @param type_ the type_ of this assetlistentry
	 */
	@Override
	public void setType_(int type_) {
		model.setType_(type_);
	}

	/**
	 * Sets the userid of this assetlistentry.
	 *
	 * @param userid the userid of this assetlistentry
	 */
	@Override
	public void setUserid(int userid) {
		model.setUserid(userid);
	}

	/**
	 * Sets the username of this assetlistentry.
	 *
	 * @param username the username of this assetlistentry
	 */
	@Override
	public void setUsername(String username) {
		model.setUsername(username);
	}

	/**
	 * Sets the uuid of this assetlistentry.
	 *
	 * @param uuid the uuid of this assetlistentry
	 */
	@Override
	public void setUuid(String uuid) {
		model.setUuid(uuid);
	}

	@Override
	protected assetlistentryWrapper wrap(assetlistentry assetlistentry) {
		return new assetlistentryWrapper(assetlistentry);
	}

}