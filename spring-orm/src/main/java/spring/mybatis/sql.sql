create table dept(
  deptno int primary key,
  dname varchar(20),
  loc varchar(50)
);
insert into dept values (10,'testing','beijing');
insert into dept values (20,'saling','shanghai');



CREATE TABLE IF NOT EXISTS t_user(
  id int PRIMARY KEY ,
  name VARCHAR(20),
  email VARBINARY(20),
  password VARCHAR(32),
  lastLoginTime DATE
) ;