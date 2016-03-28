

DROP TABLE IF EXISTS `auth_dept`;

CREATE TABLE `auth_dept` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `dept_name` varchar(16) NOT NULL COMMENT '部门名称',
  `level` int(11) DEFAULT NULL COMMENT '部门层级',
  `parent_id` bigint(20) DEFAULT NULL COMMENT '上级部门ID',
  `dept_desc` varchar(128) DEFAULT NULL COMMENT '部门描述',
  `status` int(11) DEFAULT NULL COMMENT '状态，1：有效。0：无效',
  `create_user` varchar(32) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_user` varchar(32) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8;

/*Data for the table `auth_dept` */

insert  into `auth_dept`(`id`,`dept_name`,`level`,`parent_id`,`dept_desc`,`status`,`create_user`,`create_time`,`update_user`,`update_time`) values (35,'管理',1,1,'检验',1,'admin','2015-12-16 20:47:55','admin','2015-12-16 20:51:30');
insert  into `auth_dept`(`id`,`dept_name`,`level`,`parent_id`,`dept_desc`,`status`,`create_user`,`create_time`,`update_user`,`update_time`) values (36,'测试部',1,1,'检验',1,'admin','2015-12-16 20:51:42','admin','2016-01-13 11:49:15');
insert  into `auth_dept`(`id`,`dept_name`,`level`,`parent_id`,`dept_desc`,`status`,`create_user`,`create_time`,`update_user`,`update_time`) values (37,'研发部',1,1,'研发部研发部研发部研发部研发部研发部研发部',1,'admin','2015-12-17 11:22:57',NULL,NULL);
insert  into `auth_dept`(`id`,`dept_name`,`level`,`parent_id`,`dept_desc`,`status`,`create_user`,`create_time`,`update_user`,`update_time`) values (38,'ceshi',1,1,'ceshiceshiceshi',1,'admin','2015-12-17 11:27:39',NULL,NULL);
insert  into `auth_dept`(`id`,`dept_name`,`level`,`parent_id`,`dept_desc`,`status`,`create_user`,`create_time`,`update_user`,`update_time`) values (39,'qq',1,1,'qqqqqq',1,'admin','2015-12-17 11:42:00','admin','2015-12-18 16:39:05');
insert  into `auth_dept`(`id`,`dept_name`,`level`,`parent_id`,`dept_desc`,`status`,`create_user`,`create_time`,`update_user`,`update_time`) values (40,'其他',1,0,'其他临时部门',1,'admin','2015-12-04 10:40:52','admin','2015-12-08 18:18:39');

/*Table structure for table `auth_user` */

DROP TABLE IF EXISTS `auth_user`;

CREATE TABLE `auth_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `username` varchar(32) NOT NULL COMMENT '用户名（账号)',
  `password` varchar(32) DEFAULT NULL COMMENT '密码',
  `realname` varchar(32) NOT NULL COMMENT '真实姓名',
  `tel` varchar(16) DEFAULT NULL COMMENT '电话',
  `status` int(1) DEFAULT NULL COMMENT '状态：1：有效，0：无效',
  `last_login_time` datetime DEFAULT NULL COMMENT '上次登录时间',
  `last_login_ip` varchar(16) DEFAULT NULL COMMENT '上次登录IP',
  `create_user` varchar(32) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_user` varchar(32) DEFAULT NULL,
  `locked` int(11) DEFAULT NULL COMMENT '是否加锁',
  `update_time` datetime DEFAULT NULL,
  `remark` varchar(64) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`),
  KEY `idx_user_username` (`username`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=78 DEFAULT CHARSET=utf8;

/*Data for the table `auth_user` */

insert  into `auth_user`(`id`,`username`,`password`,`realname`,`tel`,`status`,`last_login_time`,`last_login_ip`,`create_user`,`create_time`,`update_user`,`locked`,`update_time`,`remark`) values (1,'admin','v22h5bcjygTLv9ARbfMi0A==','admin','18577086225',1,'2016-03-28 10:58:08','10.2.13.130',NULL,'2015-12-07 22:09:14','admin',0,'2016-03-28 10:58:08','18577086225');
insert  into `auth_user`(`id`,`username`,`password`,`realname`,`tel`,`status`,`last_login_time`,`last_login_ip`,`create_user`,`create_time`,`update_user`,`locked`,`update_time`,`remark`) values (57,'test','nA0EK9/HpA6ZN9A3qitWnQ==','test','18215573502',1,'2016-01-15 17:51:41','10.2.13.120','admin','2015-12-16 20:54:16','admin',0,'2016-01-15 17:51:41','test');
insert  into `auth_user`(`id`,`username`,`password`,`realname`,`tel`,`status`,`last_login_time`,`last_login_ip`,`create_user`,`create_time`,`update_user`,`locked`,`update_time`,`remark`) values (58,'kk','D08vdSD5MtMH0l+BRJLvwg==','kk','15102522222',1,'2016-01-12 16:25:45','app','admin','2015-12-17 11:25:07',NULL,0,'2016-01-12 16:25:45','151025222221510252222215102522222');
insert  into `auth_user`(`id`,`username`,`password`,`realname`,`tel`,`status`,`last_login_time`,`last_login_ip`,`create_user`,`create_time`,`update_user`,`locked`,`update_time`,`remark`) values (60,'111','chAfU4jiOrtoCcezH2U2/Q==','111','15102811111',1,'2015-12-17 11:25:40','10.2.13.42','admin','2015-12-17 11:27:16','admin',0,'2015-12-17 11:54:14','1111');
insert  into `auth_user`(`id`,`username`,`password`,`realname`,`tel`,`status`,`last_login_time`,`last_login_ip`,`create_user`,`create_time`,`update_user`,`locked`,`update_time`,`remark`) values (61,'dd','Cu14fd7D9hfqvlp5gcCV9Q==','dd','15102844444',1,'2015-12-17 11:52:41','10.2.13.42','admin','2015-12-17 11:54:17',NULL,0,'2015-12-17 11:52:48','123456');
insert  into `auth_user`(`id`,`username`,`password`,`realname`,`tel`,`status`,`last_login_time`,`last_login_ip`,`create_user`,`create_time`,`update_user`,`locked`,`update_time`,`remark`) values (62,'ff','DOSl/0p5EiYt+SEeDhPnJQ==','ff','15102544444',1,'2015-12-17 11:54:04',NULL,'dd','2015-12-17 11:55:40',NULL,0,NULL,'fff');
insert  into `auth_user`(`id`,`username`,`password`,`realname`,`tel`,`status`,`last_login_time`,`last_login_ip`,`create_user`,`create_time`,`update_user`,`locked`,`update_time`,`remark`) values (63,'111222','LnpkHPXW2RmAw/TMXvvfIw==','111222','18628209870',1,'2015-12-17 18:25:57','10.2.13.163','admin','2015-12-17 18:25:57',NULL,0,'2015-12-17 18:27:21','1111');
insert  into `auth_user`(`id`,`username`,`password`,`realname`,`tel`,`status`,`last_login_time`,`last_login_ip`,`create_user`,`create_time`,`update_user`,`locked`,`update_time`,`remark`) values (64,'abc1234','nI+Dw4bQSwvqqNjOuDn4kQ==','abc1234','18628209870',1,'2015-12-17 19:20:19',NULL,'admin','2015-12-17 19:20:19',NULL,0,NULL,'18628209870');
insert  into `auth_user`(`id`,`username`,`password`,`realname`,`tel`,`status`,`last_login_time`,`last_login_ip`,`create_user`,`create_time`,`update_user`,`locked`,`update_time`,`remark`) values (65,'luoman','lcYrEx2ItrVhimFYtu/fUg==','luoman','18688834041',1,'2016-01-13 11:51:02','10.2.13.137','admin','2016-01-13 11:50:59','admin',0,'2016-01-14 16:23:28','罗曼测试');
insert  into `auth_user`(`id`,`username`,`password`,`realname`,`tel`,`status`,`last_login_time`,`last_login_ip`,`create_user`,`create_time`,`update_user`,`locked`,`update_time`,`remark`) values (66,'1','5hUaodIoFtcYLIcSFggFXg==','123','13688301507',1,'2016-03-18 08:57:38',NULL,NULL,'2016-03-18 08:57:38',NULL,0,NULL,'13688301507');
insert  into `auth_user`(`id`,`username`,`password`,`realname`,`tel`,`status`,`last_login_time`,`last_login_ip`,`create_user`,`create_time`,`update_user`,`locked`,`update_time`,`remark`) values (67,'2','SueBPY2KAoZexysanT8E5A==','234','13688301506',1,'2016-03-18 08:57:38',NULL,NULL,'2016-03-18 08:57:38',NULL,0,NULL,'13688301507');
insert  into `auth_user`(`id`,`username`,`password`,`realname`,`tel`,`status`,`last_login_time`,`last_login_ip`,`create_user`,`create_time`,`update_user`,`locked`,`update_time`,`remark`) values (68,'3','slyP8gFym8VpXGPId3l7DA==','345','13688301505',1,'2016-03-18 08:57:38',NULL,NULL,'2016-03-18 08:57:38',NULL,0,NULL,'13688301507');
insert  into `auth_user`(`id`,`username`,`password`,`realname`,`tel`,`status`,`last_login_time`,`last_login_ip`,`create_user`,`create_time`,`update_user`,`locked`,`update_time`,`remark`) values (69,'养护','GNleosUTdZLb04ESvbqhbg==','678','13512345523',1,'2016-03-18 09:00:08','10.2.13.100',NULL,'2016-03-18 08:58:30',NULL,0,'2016-03-18 09:00:08','123');
insert  into `auth_user`(`id`,`username`,`password`,`realname`,`tel`,`status`,`last_login_time`,`last_login_ip`,`create_user`,`create_time`,`update_user`,`locked`,`update_time`,`remark`) values (70,'测试','eAm4NcON2zRJ891ZQlNY/Q==','789','13612345678',1,'2016-03-18 08:58:31',NULL,NULL,'2016-03-18 08:58:30',NULL,0,NULL,'234');
insert  into `auth_user`(`id`,`username`,`password`,`realname`,`tel`,`status`,`last_login_time`,`last_login_ip`,`create_user`,`create_time`,`update_user`,`locked`,`update_time`,`remark`) values (71,'liyi','UKcVw+19VGuPELyKnFJZ4A==','83986576','13688301508',1,'2016-03-18 09:48:03',NULL,NULL,'2016-03-18 09:48:03',NULL,0,NULL,'`12');
insert  into `auth_user`(`id`,`username`,`password`,`realname`,`tel`,`status`,`last_login_time`,`last_login_ip`,`create_user`,`create_time`,`update_user`,`locked`,`update_time`,`remark`) values (72,'123','QXmsBW+MKH9Mpc6cj6K+RA==','19880298','13688301508',1,'2016-03-18 09:48:03',NULL,NULL,'2016-03-18 09:48:03',NULL,0,NULL,'33');
insert  into `auth_user`(`id`,`username`,`password`,`realname`,`tel`,`status`,`last_login_time`,`last_login_ip`,`create_user`,`create_time`,`update_user`,`locked`,`update_time`,`remark`) values (73,'姓名','h1IiU2WtXbWgn5Yr8fQSdw==','zh','18628209870',1,'2016-03-18 09:54:07',NULL,NULL,'2016-03-18 09:54:06',NULL,0,NULL,'1862820987022');
insert  into `auth_user`(`id`,`username`,`password`,`realname`,`tel`,`status`,`last_login_time`,`last_login_ip`,`create_user`,`create_time`,`update_user`,`locked`,`update_time`,`remark`) values (74,'姓名2','3so9CQqp/c7xwbbYfox8RA==','zh2','18628209870',1,'2016-03-18 09:54:07',NULL,NULL,'2016-03-18 09:54:06',NULL,0,NULL,'1862820987011');
insert  into `auth_user`(`id`,`username`,`password`,`realname`,`tel`,`status`,`last_login_time`,`last_login_ip`,`create_user`,`create_time`,`update_user`,`locked`,`update_time`,`remark`) values (75,'姓名111','1tQBxrghi+W6vVwZB/e/Zw==','zh','18628209870',1,'2016-03-18 09:56:27',NULL,NULL,'2016-03-18 09:56:27',NULL,0,NULL,'asdasdsa');
insert  into `auth_user`(`id`,`username`,`password`,`realname`,`tel`,`status`,`last_login_time`,`last_login_ip`,`create_user`,`create_time`,`update_user`,`locked`,`update_time`,`remark`) values (76,'姓名222','xqkTwcfKj7UFb/ScUZEEHg==','zh','18628209870',1,'2016-03-18 09:56:27',NULL,NULL,'2016-03-18 09:56:27',NULL,0,NULL,'asdasdsa');
insert  into `auth_user`(`id`,`username`,`password`,`realname`,`tel`,`status`,`last_login_time`,`last_login_ip`,`create_user`,`create_time`,`update_user`,`locked`,`update_time`,`remark`) values (77,'真实姓名','gfq9GLUlGRfJc5TtjPvlgA==','zh','18628209870',1,'2016-03-18 09:58:16',NULL,NULL,'2016-03-18 09:58:15',NULL,0,NULL,'aaaa');

/*Table structure for table `auth_user_dept` */

DROP TABLE IF EXISTS `auth_user_dept`;

CREATE TABLE `auth_user_dept` (
  `user_id` bigint(20) NOT NULL COMMENT '用户id',
  `dept_id` bigint(20) NOT NULL COMMENT '部门id',
  `create_time` datetime DEFAULT NULL,
  `create_user` varchar(64) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `update_user` varchar(64) DEFAULT NULL,
  `status` int(11) DEFAULT NULL COMMENT '状态，1：有效。0：无效',
  PRIMARY KEY (`user_id`,`dept_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `auth_user_dept` */

insert  into `auth_user_dept`(`user_id`,`dept_id`,`create_time`,`create_user`,`update_time`,`update_user`,`status`) values (1,0,'2015-12-07 22:09:14',NULL,NULL,NULL,1);
insert  into `auth_user_dept`(`user_id`,`dept_id`,`create_time`,`create_user`,`update_time`,`update_user`,`status`) values (57,36,'2015-12-16 20:54:16','admin',NULL,NULL,1);
insert  into `auth_user_dept`(`user_id`,`dept_id`,`create_time`,`create_user`,`update_time`,`update_user`,`status`) values (58,37,'2015-12-17 11:25:07','admin',NULL,NULL,1);
insert  into `auth_user_dept`(`user_id`,`dept_id`,`create_time`,`create_user`,`update_time`,`update_user`,`status`) values (60,36,'2015-12-17 11:28:19','admin','2015-12-17 11:29:01','admin',0);
insert  into `auth_user_dept`(`user_id`,`dept_id`,`create_time`,`create_user`,`update_time`,`update_user`,`status`) values (60,37,'2015-12-17 11:27:16','admin','2015-12-17 11:46:58','admin',1);
insert  into `auth_user_dept`(`user_id`,`dept_id`,`create_time`,`create_user`,`update_time`,`update_user`,`status`) values (60,38,'2015-12-17 11:29:25','admin','2015-12-17 11:46:58','admin',1);
insert  into `auth_user_dept`(`user_id`,`dept_id`,`create_time`,`create_user`,`update_time`,`update_user`,`status`) values (61,39,'2015-12-17 11:54:17','admin',NULL,NULL,1);
insert  into `auth_user_dept`(`user_id`,`dept_id`,`create_time`,`create_user`,`update_time`,`update_user`,`status`) values (62,39,'2015-12-17 11:55:40','dd',NULL,NULL,1);
insert  into `auth_user_dept`(`user_id`,`dept_id`,`create_time`,`create_user`,`update_time`,`update_user`,`status`) values (63,0,'2015-12-17 18:25:57','admin',NULL,NULL,1);
insert  into `auth_user_dept`(`user_id`,`dept_id`,`create_time`,`create_user`,`update_time`,`update_user`,`status`) values (64,35,'2015-12-17 19:20:19','admin',NULL,NULL,1);
insert  into `auth_user_dept`(`user_id`,`dept_id`,`create_time`,`create_user`,`update_time`,`update_user`,`status`) values (64,36,'2015-12-17 19:20:19','admin',NULL,NULL,1);
insert  into `auth_user_dept`(`user_id`,`dept_id`,`create_time`,`create_user`,`update_time`,`update_user`,`status`) values (65,36,'2016-01-13 11:50:59','admin',NULL,NULL,1);
insert  into `auth_user_dept`(`user_id`,`dept_id`,`create_time`,`create_user`,`update_time`,`update_user`,`status`) values (66,39,'2016-03-18 08:57:38',NULL,NULL,NULL,1);
insert  into `auth_user_dept`(`user_id`,`dept_id`,`create_time`,`create_user`,`update_time`,`update_user`,`status`) values (67,39,'2016-03-18 08:57:38',NULL,NULL,NULL,1);
insert  into `auth_user_dept`(`user_id`,`dept_id`,`create_time`,`create_user`,`update_time`,`update_user`,`status`) values (68,39,'2016-03-18 08:57:38',NULL,NULL,NULL,1);
insert  into `auth_user_dept`(`user_id`,`dept_id`,`create_time`,`create_user`,`update_time`,`update_user`,`status`) values (69,39,'2016-03-18 08:58:30',NULL,NULL,NULL,1);
insert  into `auth_user_dept`(`user_id`,`dept_id`,`create_time`,`create_user`,`update_time`,`update_user`,`status`) values (70,39,'2016-03-18 08:58:30',NULL,NULL,NULL,1);
insert  into `auth_user_dept`(`user_id`,`dept_id`,`create_time`,`create_user`,`update_time`,`update_user`,`status`) values (71,39,'2016-03-18 09:48:03',NULL,NULL,NULL,1);
insert  into `auth_user_dept`(`user_id`,`dept_id`,`create_time`,`create_user`,`update_time`,`update_user`,`status`) values (72,39,'2016-03-18 09:48:03',NULL,NULL,NULL,1);
insert  into `auth_user_dept`(`user_id`,`dept_id`,`create_time`,`create_user`,`update_time`,`update_user`,`status`) values (73,39,'2016-03-18 09:54:06',NULL,NULL,NULL,1);
insert  into `auth_user_dept`(`user_id`,`dept_id`,`create_time`,`create_user`,`update_time`,`update_user`,`status`) values (74,39,'2016-03-18 09:54:06',NULL,NULL,NULL,1);
insert  into `auth_user_dept`(`user_id`,`dept_id`,`create_time`,`create_user`,`update_time`,`update_user`,`status`) values (75,39,'2016-03-18 09:56:27',NULL,NULL,NULL,1);
insert  into `auth_user_dept`(`user_id`,`dept_id`,`create_time`,`create_user`,`update_time`,`update_user`,`status`) values (76,39,'2016-03-18 09:56:27',NULL,NULL,NULL,1);
insert  into `auth_user_dept`(`user_id`,`dept_id`,`create_time`,`create_user`,`update_time`,`update_user`,`status`) values (77,39,'2016-03-18 09:58:15',NULL,NULL,NULL,1);

