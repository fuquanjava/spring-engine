DROP TABLE IF EXISTS dept;
CREATE TABLE IF NOT EXISTS dept(
  id int PRIMARY KEY AUTO_INCREMENT,
  deptno int primary key ,
  dname varchar(20),
  loc varchar(50)
);
insert into dept(deptno, dname, loc)  values (10,'testing','beijing');
insert into dept(deptno, dname, loc)  values (20,'saling','shanghai');


DROP TABLE IF EXISTS user;
CREATE TABLE IF NOT EXISTS user(
  id int PRIMARY KEY AUTO_INCREMENT,
  name VARCHAR(20),
  email VARBINARY(20),
  password VARCHAR(32),
  lastLoginTime DATE
) ;