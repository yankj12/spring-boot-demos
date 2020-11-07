CREATE TABLE test.sys_user (
	id INT NULL AUTO_INCREMENT,
	username varchar(100) DEFAULT '' NULL,
	password varchar(100) DEFAULT '' NULL,
	CONSTRAINT sys_user_pk PRIMARY KEY (id)
)
ENGINE=InnoDB
DEFAULT CHARSET=utf8
COLLATE=utf8_general_ci ;


CREATE TABLE test.sys_role (
	id INT NOT NULL AUTO_INCREMENT,
	name varchar(100) DEFAULT '' NULL,
	CONSTRAINT sys_role_pk PRIMARY KEY (id)
)
ENGINE=InnoDB
DEFAULT CHARSET=utf8
COLLATE=utf8_general_ci ;



CREATE TABLE test.sys_role_user (
	id INT NOT NULL AUTO_INCREMENT,
	sys_user_id INT NULL,
	sys_role_id INT NULL,
	CONSTRAINT sys_role_user_pk PRIMARY KEY (id)
)
ENGINE=InnoDB
DEFAULT CHARSET=utf8
COLLATE=utf8_general_ci ;

insert into SYS_USER (id,username, password) values (1,'admin', 'admin');
insert into SYS_USER (id,username, password) values (2,'abel', 'abel');

insert into SYS_ROLE(id,name) values(1,'ROLE_ADMIN');
insert into SYS_ROLE(id,name) values(2,'ROLE_USER');

insert into SYS_ROLE_USER(id,Sys_User_id,Sys_Role_id) values(1,1,1);
insert into SYS_ROLE_USER(id,Sys_User_id,Sys_Role_id) values(2,2,2);