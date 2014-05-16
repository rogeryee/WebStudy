#本例使用postgreSQL
create database shiro;
\c shiro;

create table users (
  id SERIAL,
  username varchar(100),
  password varchar(100),
  password_salt varchar(100),
  PRIMARY KEY( id )
);

create table user_roles(
  id SERIAL,
  username varchar(100),
  role_name varchar(100),
  PRIMARY KEY( id )
);

create table roles_permissions(
  id SERIAL,
  role_name varchar(100),
  permission varchar(100),
  PRIMARY KEY( id )
);

insert into users(username,password)values('Roger','123');
insert into users(username,password)values('Phoebe','234');