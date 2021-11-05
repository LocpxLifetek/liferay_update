create table LT_Menus (
	uuid_ VARCHAR(75) null,
	id_ VARCHAR(75) not null primary key,
	createdDate DATE null,
	modifiedDate DATE null,
	createdUser VARCHAR(75) null,
	modifiedUser VARCHAR(75) null,
	code_ VARCHAR(75) null,
	displayName VARCHAR(75) null,
	description VARCHAR(75) null,
	url VARCHAR(75) null,
	func VARCHAR(75) null,
	type_ INTEGER,
	isActive BOOLEAN,
	parentId VARCHAR(75) null
);

create table LT_assetlistentry (
	uuid_ VARCHAR(75) null,
	mvccversion INTEGER,
	ctcollectionid INTEGER,
	assetlistentryid INTEGER not null primary key,
	groupid INTEGER,
	companyid INTEGER,
	userid INTEGER,
	username VARCHAR(75) null,
	createdate DATE null,
	modifieddate DATE null,
	assetlistentrykey VARCHAR(75) null,
	title VARCHAR(75) null,
	type_ INTEGER,
	assetentrysubtype VARCHAR(75) null,
	assetentrytype VARCHAR(75) null,
	lastpublishdate DATE null
);